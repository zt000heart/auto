package com.example.zhangtao21.auto;

import com.annotation.MyAnnotation;

/**
 * Created by zhangtao21 on 2017/3/24.
 */

@MyAnnotation
public class Bean {

    public String name;
    public String address;
    public Bean(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return StringUtil.createString(this);
    }
}