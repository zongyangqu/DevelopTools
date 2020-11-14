package com.coding.qzy.baselibrary.widget.recorderlib.recorder.listener;

/**
 * @author quzongyang on 2018/7/11.
 */
public interface RecordDataListener {

    /**
     * 当前的录音状态发生变化
     *
     * @param data 当前音频数据
     */
    void onData(byte[] data);

}
