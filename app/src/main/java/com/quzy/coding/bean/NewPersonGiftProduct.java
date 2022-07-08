package com.quzy.coding.bean;

/**
 * CreateDate:2022/7/4 16:37
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
public class NewPersonGiftProduct implements Comparable<NewPersonGiftProduct> {

    public String title;
    public ProPrice price;


    @Override
    public int compareTo(NewPersonGiftProduct o) {
        float flag = Float.parseFloat(this.price.price) - Float.parseFloat(o.price.price);
        if(flag >= 0){
            return 1;
        }else{
            return -1;
        }
    }
}


