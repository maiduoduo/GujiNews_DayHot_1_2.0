package com.cnews.guji.smart.common.bean.basebean;

import java.io.Serializable;

/**
 * author：JSYL-DCL
 * des:封装服务器返回数据
 */
public class BaseRespose <T> implements Serializable {
    public String code;
    public String msg;

    public T data;

    public boolean success() {
        return "1".equals(code);
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
