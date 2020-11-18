package com.quzy.coding.ui.activity;

import android.app.Application;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quzy.coding.BuildConfig;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.base.BaseApplication;
import com.quzy.coding.util.RecordVoiceMp3View;
import com.quzy.coding.util.VoiceManager;
import com.zlw.main.recorderlib.RecordManager;
import com.zlw.main.recorderlib.recorder.RecordConfig;
import com.zlw.main.recorderlib.recorder.RecordHelper;
import com.zlw.main.recorderlib.recorder.listener.RecordFftDataListener;
import com.zlw.main.recorderlib.recorder.listener.RecordResultListener;
import com.zlw.main.recorderlib.recorder.listener.RecordSoundSizeListener;
import com.zlw.main.recorderlib.recorder.listener.RecordStateListener;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import butterknife.BindView;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/11/16
 * desc   :
 * version: 1.0
 */


public class RecordMp3Activity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.startRecord)
     LinearLayout startRecord;
    @BindView(R.id.recording)
     RelativeLayout recording;
    @BindView(R.id.playLayout)
     RelativeLayout playLayout;
    @BindView(R.id.stopRecord)
     ImageView stopRecord;
    @BindView(R.id.pauseRecord)
     ImageView pauseRecord;
    @BindView(R.id.deleteRecord)
     ImageView deleteRecord;
    @BindView(R.id.playRecord)
     ImageView playRecord;
    @BindView(R.id.recordStopView)
     LinearLayout recordStopView;
    @BindView(R.id.recordLength)
     TextView recordLength;
    @BindView(R.id.recordHintTv)
     TextView recordHintTv;
    final RecordManager recordManager = RecordManager.getInstance();
    private VoiceManager voiceManager;


    @Override
    protected void onViewCreated() {
        initRecord();
        voiceManager =VoiceManager.getInstance(BaseApplication.getContext());
        startRecord.setOnClickListener(this);
        deleteRecord.setOnClickListener(this);
        stopRecord.setOnClickListener(this);
        pauseRecord.setOnClickListener(this);
        playLayout.setOnClickListener(this);
    }

    private void initRecord() {
        recordManager.init((Application) BaseApplication.getContext(), BuildConfig.DEBUG);
        recordManager.changeFormat(RecordConfig.RecordFormat.MP3);
        String recordDir = String.format(Locale.getDefault(), "%s/Record/com.zlw.main/",
                Environment.getExternalStorageDirectory().getAbsolutePath());
        recordManager.changeRecordDir(recordDir);
        initRecordEvent();
    }

    private void initRecordEvent() {
        recordManager.setRecordStateListener(new RecordStateListener() {
            @Override
            public void onStateChange(RecordHelper.RecordState state) {
                // Logger.i(TAG, "onStateChange %s", state.name());
                switch (state) {
                    case PAUSE:
                        recordHintTv.setText("暂停中");
                        break;
                    case IDLE:
                        recordHintTv.setText("空闲中");
                        break;
                    case RECORDING:
                        recordHintTv.setText("录音中");
                        break;
                    case STOP:
                        recordHintTv.setText("停止");
                        break;
                    case FINISH:
                        recordHintTv.setText("录音结束");
                        //tvSoundSize.setText("---");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(String error) {
                //   Logger.i(TAG, "onError %s", error);
            }
        });
        recordManager.setRecordSoundSizeListener(new RecordSoundSizeListener() {
            @Override
            public void onSoundSize(int soundSize) {
            }
        });
        recordManager.setRecordResultListener(new RecordResultListener() {
            @Override
            public void onResult(File result) {
                recordPath = result.getAbsolutePath();
                Log.d("PathDrution",getAudioFileVoiceTime(recordPath)+"");
                recordLength.setText(getAudioFileVoiceTime(recordPath)+"");
                Toast.makeText(RecordMp3Activity.this, "录音文件： " + result.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            }
        });
        recordManager.setRecordFftDataListener(new RecordFftDataListener() {
            @Override
            public void onFftData(byte[] data) {
                //audioView.setWaveData(data);
            }
        });
    }

    /**
     * 播放语音
     */
    // 语音动画
    private AnimationDrawable voiceAnimation;
    public void playRecordSound(String filePath){
        if (voiceAnimation != null) {
            voiceAnimation.stop();
            voiceAnimation.selectDrawable(0);
        }
        if (voiceManager.isPlaying()) {
            voiceManager.stopPlay();
        }else{
            voiceManager.stopPlay();
            voiceAnimation = (AnimationDrawable) playRecord.getBackground();
            voiceAnimation.start();
            voiceManager.setVoicePlayListener(new VoiceManager.VoicePlayCallBack() {
                @Override
                public void voiceTotalLength(long time, String strTime) {
                }

                @Override
                public void playDoing(long time, String strTime) {
                }

                @Override
                public void playPause() {
                }

                @Override
                public void playStart() {
                }

                @Override
                public void playFinish() {
                    if (voiceAnimation != null) {
                        voiceAnimation.stop();
                        voiceAnimation.selectDrawable(0);
                    }
                }
            });
            voiceManager.startPlay(filePath);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mp3;
    }


    private String recordPath;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startRecord://开始录音布局
                startRecord();
                break;
            case R.id.deleteRecord://删除语音,回复到初始状态
                if(new File(recordPath).exists()){
                    new File(recordPath).delete();
                }
                prepareRecord();
                break;
            case R.id.stopRecord:
                completeRecord();
                break;
            case R.id.pauseRecord:
                doPlay();
                break;
            case R.id.playLayout:
                playRecordSound(recordPath);
                break;

        }
    }

    /**
     * 结束录音
     */
    public void completeRecord(){
        doStop();
        startRecord.setVisibility(View.GONE);
        recording.setVisibility(View.GONE);
        recordStopView.setVisibility(View.VISIBLE);
       // recordLength.setText(strLength);
    }

    /**
     * 准备录音
     */
    public void prepareRecord(){
        startRecord.setVisibility(View.VISIBLE);
        recording.setVisibility(View.GONE);
        recordStopView.setVisibility(View.GONE);
    }


    private boolean isStart = false;
    private boolean isPause = false;
    private void doStop() {
        recordManager.stop();
     //   btRecord.setText("开始");
        isPause = false;
        isStart = false;
    }

    private void doPlay() {
        if (isStart) {
            pauseRecord.setImageResource(R.mipmap.replay_record);
            recordManager.pause();
            //btRecord.setText("开始");
            isPause = true;
            isStart = false;
        } else {
            pauseRecord.setImageResource(R.mipmap.pause_record);
            if (isPause) {
                recordManager.resume();
            } else {
                recordManager.start();
            }
           // btRecord.setText("暂停");
            isStart = true;
        }
    }

    public void startRecord() {
        doPlay();
        startRecord.setVisibility(View.GONE);
        recording.setVisibility(View.VISIBLE);
        recordStopView.setVisibility(View.GONE);
        //recordHintTv.setText("00:00");
    }

    /**
     * 获取音频文件的总时长大小
     *
     * @param filePath 音频文件路径
     * @return 返回时长大小
     */
    public long getAudioFileVoiceTime(String filePath) {
        long mediaPlayerDuration = 0L;
        if (filePath == null || filePath.isEmpty()) {
            return 0;
        }
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            mediaPlayerDuration = mediaPlayer.getDuration();
        } catch (IOException ioException) {
            //LogUtil.i(TAG, ioException.getMessage());
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        return mediaPlayerDuration;
    }
}
