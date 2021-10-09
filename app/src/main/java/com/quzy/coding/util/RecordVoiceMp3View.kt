package com.quzy.coding.util

import android.app.Application
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Environment
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.quzy.coding.BuildConfig
import com.quzy.coding.R
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.util.VoiceManager.VoicePlayCallBack
import com.zlw.main.recorderlib.RecordManager
import com.zlw.main.recorderlib.recorder.RecordConfig
import com.zlw.main.recorderlib.recorder.RecordHelper
import com.zlw.main.recorderlib.recorder.listener.RecordStateListener
import java.io.File
import java.io.IOException
import java.util.*

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/11/16
 * desc   :
 * version: 1.0
 */
class RecordVoiceMp3View : RelativeLayout, View.OnClickListener {
    private var startRecord: LinearLayout? = null
    private var recording: RelativeLayout? = null
    private var playLayout: RelativeLayout? = null
    private var stopRecord: ImageView? = null
    private var pauseRecord: ImageView? = null
    private var deleteRecord: ImageView? = null
    private var playRecord: ImageView? = null
    private var recordStopView: LinearLayout? = null
    private var recordLength: TextView? = null
    private var recordHintTv: TextView? = null

    //private VoiceManager voiceManager;
    private var mContext: Context
    var application: Application? = null
    val recordManager = RecordManager.getInstance()
    private var voiceManager: VoiceManager? = null
    private fun init() {
        setOnClickListener(this)
        voiceManager = VoiceManager.getInstance(BaseApplication.getContext())
        initRecord()
    }

    constructor(context: Context) : super(context) {
        mContext = context
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        mContext = context
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mContext = context
        init()
        inflate(context, R.layout.record_voice_view, this)
        startRecord = findViewById<View>(R.id.startRecord) as LinearLayout
        recording = findViewById<View>(R.id.recording) as RelativeLayout
        playLayout = findViewById<View>(R.id.playLayout) as RelativeLayout
        recordStopView = findViewById<View>(R.id.recordStopView) as LinearLayout
        stopRecord = findViewById<View>(R.id.stopRecord) as ImageView
        pauseRecord = findViewById<View>(R.id.pauseRecord) as ImageView
        deleteRecord = findViewById<View>(R.id.deleteRecord) as ImageView
        playRecord = findViewById<View>(R.id.playRecord) as ImageView
        recordLength = findViewById<View>(R.id.recordLength) as TextView
        recordHintTv = findViewById<View>(R.id.recordHintTv) as TextView
        startRecord?.setOnClickListener(this)
        deleteRecord!!.setOnClickListener(this)
        stopRecord!!.setOnClickListener(this)
        pauseRecord!!.setOnClickListener(this)
        playLayout!!.setOnClickListener(this)
    }

    private fun initRecord() {
        recordManager.init(BaseApplication.getContext() as Application, BuildConfig.DEBUG)
        recordManager.changeFormat(RecordConfig.RecordFormat.MP3)
        val recordDir = String.format(Locale.getDefault(), "%s/Record/com.zlw.main/",
                Environment.getExternalStorageDirectory().absolutePath)
        recordManager.changeRecordDir(recordDir)
        initRecordEvent()
    }

    private fun initRecordEvent() {
        recordManager.setRecordStateListener(object : RecordStateListener {
            override fun onStateChange(state: RecordHelper.RecordState) {
                // Logger.i(TAG, "onStateChange %s", state.name());
                when (state) {
                    RecordHelper.RecordState.PAUSE -> recordHintTv!!.text = "暂停中"
                    RecordHelper.RecordState.IDLE -> recordHintTv!!.text = "空闲中"
                    RecordHelper.RecordState.RECORDING -> recordHintTv!!.text = "录音中"
                    RecordHelper.RecordState.STOP -> recordHintTv!!.text = "停止"
                    RecordHelper.RecordState.FINISH -> recordHintTv!!.text = "录音结束"
                    else -> {
                    }
                }
            }

            override fun onError(error: String) {
                //   Logger.i(TAG, "onError %s", error);
            }
        })
        recordManager.setRecordSoundSizeListener { }
        recordManager.setRecordResultListener { result ->
            recordPath = result.absolutePath
            Log.d("PathDrution", getAudioFileVoiceTime(recordPath).toString() + "")
            Log.d("Path", recordPath)
            recordLength!!.text = getAudioFileVoiceTime(recordPath).toString() + ""
        }
        recordManager.setRecordFftDataListener {
            //audioView.setWaveData(data);
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.startRecord -> startRecord()
            R.id.deleteRecord -> {
                if (File(recordPath).exists()) {
                    File(recordPath).delete()
                }
                prepareRecord()
            }
            R.id.stopRecord -> completeRecord()
            R.id.pauseRecord -> doPlay()
            R.id.playLayout -> playRecordSound(recordPath)
        }
    }

    /**
     * 开始录音
     */
    private var recordPath: String? = null
    private var isStart = false
    private var isPause = false

    /**
     * 结束录音
     */
    fun completeRecord() {
        doStop()
        startRecord!!.visibility = GONE
        recording!!.visibility = GONE
        recordStopView!!.visibility = VISIBLE
    }

    /**
     * 准备录音
     */
    fun prepareRecord() {
        startRecord!!.visibility = VISIBLE
        recording!!.visibility = GONE
        recordStopView!!.visibility = GONE
    }

    fun startRecord() {
        doPlay()
        startRecord!!.visibility = GONE
        recording!!.visibility = VISIBLE
        recordStopView!!.visibility = GONE
    }

    private fun doPlay() {
        if (isStart) {
            pauseRecord!!.setImageResource(R.mipmap.replay_record)
            recordManager.pause()
            //btRecord.setText("开始");
            isPause = true
            isStart = false
        } else {
            pauseRecord!!.setImageResource(R.mipmap.pause_record)
            if (isPause) {
                recordManager.resume()
            } else {
                recordManager.start()
            }
            // btRecord.setText("暂停");
            isStart = true
        }
    }

    /**
     * 播放语音
     */
    // 语音动画
    private var voiceAnimation: AnimationDrawable? = null
    fun playRecordSound(filePath: String?) {
        if (voiceAnimation != null) {
            voiceAnimation!!.stop()
            voiceAnimation!!.selectDrawable(0)
        }
        if (voiceManager!!.isPlaying) {
            voiceManager!!.stopPlay()
        } else {
            voiceManager!!.stopPlay()
            voiceAnimation = playRecord!!.background as AnimationDrawable
            voiceAnimation!!.start()
            voiceManager!!.setVoicePlayListener(object : VoicePlayCallBack {
                override fun voiceTotalLength(time: Long, strTime: String) {}
                override fun playDoing(time: Long, strTime: String) {}
                override fun playPause() {}
                override fun playStart() {}
                override fun playFinish() {
                    if (voiceAnimation != null) {
                        voiceAnimation!!.stop()
                        voiceAnimation!!.selectDrawable(0)
                    }
                }
            })
            voiceManager!!.startPlay(filePath)
        }
    }

    private fun doStop() {
        recordManager.stop()
        //  btRecord.setText("开始");
        isPause = false
        isStart = false
    }

    /**
     * 结束回调监听
     */
    interface RecordVoiceListener {
        fun onFinishRecord(length: Long, strLength: String?, filePath: String?)
    }

    /**
     * 设置录音分类
     * @param recordVoiceListener
     */
    private var recordVoiceListener: RecordVoiceListener? = null
    fun setRecordVoiceListener(recordVoiceListener: RecordVoiceListener?) {
        this.recordVoiceListener = recordVoiceListener
    }

    /**
     * 获取音频文件的总时长大小
     *
     * @param filePath 音频文件路径
     * @return 返回时长大小
     */
    fun getAudioFileVoiceTime(filePath: String?): Long {
        var mediaPlayerDuration = 0L
        if (filePath == null || filePath.isEmpty()) {
            return 0
        }
        val mediaPlayer = MediaPlayer()
        try {
            mediaPlayer.setDataSource(filePath)
            mediaPlayer.prepare()
            mediaPlayerDuration = mediaPlayer.duration.toLong()
        } catch (ioException: IOException) {
            //LogUtil.i(TAG, ioException.getMessage());
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }
        return mediaPlayerDuration
    }
}