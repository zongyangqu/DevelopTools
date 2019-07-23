package com.quzy.coding.bean;


import com.quzy.coding.R;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class QianBean {

  private String text;
  private String str;
  private int index;
  private int icon = R.mipmap.avatar;

  public QianBean(String text) {
    this.text = text;
  }

  public QianBean(int index,String text) {
    this.index = index;
    this.text = text;
  }

  public QianBean(String text, String str) {
    this.text = text;
    this.str = str;
  }

  public QianBean(String text, String str, int icon) {
    this.text = text;
    this.str = str;
    this.icon = icon;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
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
