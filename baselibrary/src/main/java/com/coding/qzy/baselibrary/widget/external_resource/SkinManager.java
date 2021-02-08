package com.coding.qzy.baselibrary.widget.external_resource;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Method;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/02/08
 * desc   : 外部资源加载管理类
 * version: 1.0
 */
public class SkinManager {

    private static SkinManager skinManager = new SkinManager();
    //外部资源包的资源对象
    private Resources resources;
    //外部资源包名
    private String packageName;
    private Context context;

    private SkinManager(){}

    public boolean loadRespurceSucess(){
        return null != resources;
    }

    public static SkinManager getInstance(){
        return skinManager;
    }

    public void init(Context context){
        this.context = context;
    }

    /**
     * 根据资源包的存储路径去加载它
     * @param path  外部资源包路径
     */
    public void loadResourceApk(String path){
        try {
            //获取到资源包管理器
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(path,
                    PackageManager.GET_ACTIVITIES);
            packageName = packageArchiveInfo.packageName;
            AssetManager assetManager = AssetManager.class.newInstance();
            //让这个assetManager能够管理到资源包的资源
            Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath",String.class);
            addAssetPath.invoke(assetManager,path);
            //获取外部资源包中的资源
            resources = new Resources(assetManager,context.getResources().getDisplayMetrics(),
                    context.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据名字和类型去资源包中匹配资源 获取ID
     * @param name  xxx.png
     * @param type  background  /  mipmap
     * @return
     */
    public int getColor(String name,String type){
        if(name.isEmpty() || type.isEmpty()){
            return 0;
        }
        int identifier = resources.getIdentifier(name,type,packageName);
        if(identifier == 0){
            return 0;
        }
        return resources.getColor(identifier);
    }

    /**
     * 根据名字和类型去资源包中匹配资源 获取Drawable
     * @param name  xxx.png
     * @param type  background  /  mipmap
     * @return
     */
    public Drawable getDrawable(String name, String type){
        if(name.isEmpty() || type.isEmpty()){
            return null;
        }
        int identifier = resources.getIdentifier(name,type,packageName);
        if(identifier == 0){
            return null;
        }
        return resources.getDrawable(identifier);
    }

    /**
     * 根据名字和类型去资源包中匹配资源 获取ID
     * @param name  xxx.png
     * @param type  background  /  mipmap
     * @return
     */
    public int getDrawableResId(String name, String type){
        if(name.isEmpty() || type.isEmpty()){
            return 0;
        }
        int identifier = resources.getIdentifier(name,type,packageName);
        if(identifier == 0){
            return 0;
        }
        return identifier;
    }


}

