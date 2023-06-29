package com.quzy.coding.util;

import android.content.Context;

import com.google.gson.Gson;
import com.quzy.coding.Question;
import com.quzy.coding.bean.AssetInfo;
import com.quzy.coding.bean.CouponNewCustomerResultBean;
import com.quzy.coding.bean.HotelEntity;
import com.quzy.coding.bean.PayQuickBean;
import com.quzy.coding.bean.QuestionInfo;
import com.quzy.coding.bean.ViewReportConfigBean;
import com.quzy.coding.bean.WareEntity;

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

    public static AssetInfo analysisAssetInfoJsonFile(Context context, String fileName){
        String content = FileUtils.readJsonFile(context,fileName);
        Gson gson = new Gson();
        AssetInfo entity = gson.fromJson(content, AssetInfo.class);
        return  entity;
    }

    public static WareEntity analysisWareJsonFile(Context context, String fileName){
        String content = FileUtils.readJsonFile(context,fileName);
        Gson gson = new Gson();
        WareEntity entity = gson.fromJson(content, WareEntity.class);
        return  entity;
    }

    public static Question analysisQuestionJsonFile(Context context, String fileName){
        String content = FileUtils.readJsonFile(context,fileName);
        Gson gson = new Gson();
        Question entity = gson.fromJson(content, Question.class);
        return  entity;
    }

    public static CouponNewCustomerResultBean analysisNewCouponsJsonFile(Context context, String fileName){
        String content = FileUtils.readJsonFile(context,fileName);
        Gson gson = new Gson();
        CouponNewCustomerResultBean entity = gson.fromJson(content, CouponNewCustomerResultBean.class);
        return  entity;
    }

    public static PayQuickBean analysisPayQuickJsonFile(Context context, String fileName){
        String content = FileUtils.readJsonFile(context,fileName);
        Gson gson = new Gson();
        PayQuickBean entity = gson.fromJson(content, PayQuickBean.class);
        return  entity;
    }
}
