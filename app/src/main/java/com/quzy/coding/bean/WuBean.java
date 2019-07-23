package com.quzy.coding.bean;


import com.quzy.coding.R;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class WuBean {

  private String text;
  private String str;
  private int icon = R.mipmap.avatar;

  public WuBean(String text) {
    this.text = text;
  }

  public WuBean(String text, String str) {
    this.text = text;
    this.str = str;
  }

  public WuBean(String text, String str, int icon) {
    this.text = text;
    this.str = str;
    this.icon = icon;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getStr() {
    return str;
  }

  public void setStr(String str) {
    this.str = str;
  }

  public int getIcon() {
    return icon;
  }

  public void setIcon(int icon) {
    this.icon = icon;
  }
}
