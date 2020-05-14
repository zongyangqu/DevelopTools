package com.quzy.coding.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.coding.qzy.baselibrary.utils.log.LogTools;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/05/14
 * desc   :  对开源库LogUtils进行了封装
 *             https://github.com/pengwei1024/LogUtils
 * version: 1.0
 */


public class LogUtilActivity extends BaseActivity {

    public final String Tag1 = "LogTools";
    public final String Tag2 = "LogUtils";

    @BindView(R.id.logJson)
    Button logJson;
    @BindView(R.id.logMap)
    TextView logMap;

    private String JSON_CONTENT = "{\"weatherinfo\":{\"city\":\"北京\",\"cityid\":\"101010100\"," +
            "\"temp\":\"18\",\"WD\":\"东南风\",\"WS\":\"1级\",\"SD\":\"17%\",\"WSE\":\"1\"," +
            "\"time\":\"17:05\",\"isRadar\":\"1\",\"Radar\":\"JC_RADAR_AZ9010_JB\"," +
            "\"njd\":\"暂无实况\",\"qy\":\"1011\",\"rain\":\"0\"}}";
    private Map<String, String> map;


    @Override
    protected void onViewCreated() {
        map = new HashMap<>();
        map.put("key", "value");
        map.put("key1", "value2");

        /*logJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogTools.tag(Tag1).json(Log.DEBUG , JSON_CONTENT);
                LogUtils.tag(Tag2).json(JSON_CONTENT);
            }
        });

        logMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogTools.tag(Tag1).d(map);
                LogUtils.tag(Tag2).d(map);
            }
        });*/
    }

    @OnClick({R.id.logJson,R.id.logMap})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.logJson:
                //LogTools.json(Log.DEBUG , JSON_CONTENT);
                Toast.makeText(getActivity(),"toast",Toast.LENGTH_SHORT).show();
                LogTools.tag(Tag1).json(Log.DEBUG , JSON_CONTENT);
                LogUtils.tag(Tag2).json(JSON_CONTENT);
                break;
            case R.id.logMap:
                LogTools.tag(Tag1).d(map);
                LogUtils.tag(Tag2).d(map);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_log_util;
    }
}
