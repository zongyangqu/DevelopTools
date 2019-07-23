package com.quzy.coding.bean;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/5/22
 *
 * 类描述：
 */

public class Recorder {
    public float time;
    public String filePath;

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Recorder(float time, String filePath) {
        super();
        this.time = time;
        this.filePath = filePath;
    }
}
