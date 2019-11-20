package com.quzy.coding.util;

import android.content.Context;

import com.google.gson.Gson;
import com.quzy.coding.bean.HotelEntity;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/11/20
 * desc   :
 * version: 1.0
 */


public class JsonUtils {
    public static HotelEntity analysisJsonFile(Context context, String fileName){
        String content = FileUtils.readJsonFile(context,fileName);
        Gson gson = new Gson();
        HotelEntity entity = gson.fromJson(content, HotelEntity.class);
        return  entity;

    }
}
