package com.coding.qzy.baselibrary.utils.permission;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/05/15
 * desc   :
 * version: 1.0
 */

public class UnSupportSourceTypeException extends RuntimeException {

    public UnSupportSourceTypeException() {
    }

    public UnSupportSourceTypeException(String detailMessage) {
        super(detailMessage);
    }

    public UnSupportSourceTypeException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public UnSupportSourceTypeException(Throwable throwable) {
        super(throwable);
    }
}
