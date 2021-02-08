package com.coding.qzy.baselibrary.widget.external_resource;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/02/08
 * desc   :
 * version: 1.0
 */
public class ResourceFactory implements LayoutInflater.Factory2 {

    //收集当前Activity中用到资源的控件
    List<SkinView> viewList = new ArrayList<>();

    //系统原生控件包名
    private static final String[] prxfixList = {
            "android.widget.",
            "android.view.",
            "android.webkit"
    };

    public void setResource() {
        for (SkinView skinView : viewList) {
            skinView.setResource();
        }
    }

    /**
     *
     * @param view
     * @param viewClassName  控件全路径 androidx.appcompat.widget.ActionBarOverlayLayout
     * @param context
     * @param attributeSet
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@Nullable View view, @NonNull String viewClassName, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View my_view = null;
        Log.d("MN--------------->", viewClassName);
        //1. 实例化这个控件
        if (viewClassName.contains(".")) {
            my_view = onCreateView(viewClassName, context, attributeSet);
        } else {
            //不带包名 遍历加上系统原生包名 匹配下看有没这个控件
            for (String s1 : prxfixList) {
                String viewName = s1 + viewClassName;
                my_view = onCreateView(viewName, context, attributeSet);
                if (my_view != null) {
                    break;
                }
            }
        }
        // 2.收集用到了资源的控件
        if (my_view != null) {
            collView(my_view, viewClassName, attributeSet);
        }
        return my_view;
    }

    /**
     * 收集了用到了容器的控件
     * @param view
     * @param s
     * @param attributeSet
     */
    private void collView(View view, String s, AttributeSet attributeSet) {
        List<SkinItem> skinItems = new ArrayList<>();
        for (int x = 0; x < attributeSet.getAttributeCount(); x++) {
            //判断每一条属性 看是否是我们需要的 “用到的资源”  自定义属性resource_vlue就是符合条件的
            //获取到属性的名字
            String attribuName = attributeSet.getAttributeName(x);
            //你是用到了资源的  需要我们去资源包中加载资源然后设置给这个控件
            if(attribuName.equals("resource_vlue")){
                //获取到的属性值 background/drawable/bg
                String attributeValue = attributeSet.getAttributeValue(x);
                String[] resources = attributeValue.split("/");
                if(resources.length == 3){
                    SkinItem skinItem = new SkinItem(resources[0], resources[1], resources[2]);
                    skinItems.add(skinItem);
                }else{
                    throw new RuntimeException("自定义属性格式异常，参考格式：background/drawable/xxx.png  ");
                }
            }
            /*if (attribuName.contains("background") || attribuName.contains("src")) {
                //todo    这里要换成自定义属性
                //获取到的是 ID  @
                String attributeValue = attributeSet.getAttributeValue(x);
                //获取到资源 ID
                int resId = Integer.parseInt(attributeValue.substring(1));
                //获取资源的名字 如 xxx.png
                String resourceEntryName = view.getResources().getResourceEntryName(resId);
                //获取资源类型，如mipmap  drawable
                String resourceTypeName = view.getResources().getResourceTypeName(resId);
                SkinItem skinItem = new SkinItem(attribuName, resourceTypeName, resourceEntryName);
                skinItems.add(skinItem);
            }*/
        }

        //说明至少有一条属性要加载资源
        if (skinItems.size() > 0) {
            SkinView skinView = new SkinView(view, skinItems);
            viewList.add(skinView);
        }

    }

    class SkinView {
        View view;
        List<SkinItem> skinItems;

        public SkinView(View view, List<SkinItem> skinItems) {
            this.view = view;
            this.skinItems = skinItems;
        }

        public void setResource() {
            for (SkinItem skinItem : skinItems) {
                if (skinItem.getName().equals("src")) {
                    if (skinItem.getTypeName().equals("drawable")) {
                        //将资源ID  传给ResourceManager 去进行资源匹配  如果匹配了就直接设置给控件
                        //如果没有匹配到  就把之前的资源ID用来设置控件
                        Drawable drawable = SkinManager.getInstance().getDrawable(skinItem.getEntryName(), skinItem.getTypeName());
                        ((ImageView) view).setImageDrawable(drawable);
                    }
                } else if (skinItem.getName().equals("background")) {
                    if (skinItem.getTypeName().equals("drawable") || skinItem.getTypeName().equals("mipmap")) {
                        //将资源ID  传给ResourceManager 去进行资源匹配  如果匹配了就直接设置给控件
                        //如果没有匹配到  就把之前的资源ID用来设置控件
                        Drawable drawable = SkinManager.getInstance().getDrawable(skinItem.getEntryName(), skinItem.getTypeName());
                        if (null == drawable) {
                            //todo  使用默认图片 代替
                        }
                        view.setBackground(drawable);
                    }
                }

            }
        }
    }

    /**
     * 每条属性的封装对象
     */
    class SkinItem {
        //属性的名字  textClolor   text     background
        String name;
        //属性的值的类型   color   mipmap
        String typeName;
        //属性的值的名字   color Primary
        String entryName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getEntryName() {
            return entryName;
        }

        public void setEntryName(String entryName) {
            this.entryName = entryName;
        }

        public SkinItem(String name, String typeName, String entryName) {
            this.name = name;
            this.typeName = typeName;
            this.entryName = entryName;
        }
    }

    /**
     * 通过 属性值 反射自来生成VIew
     * @param s
     * @param context
     * @param attributeSet
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull String s, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View view = null;
        try {
            Class aClass = context.getClassLoader().loadClass(s);
            Constructor<? extends View> constructor = aClass.getConstructor(Context.class, AttributeSet.class);
            view = constructor.newInstance(context, attributeSet);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return view;
    }
}


