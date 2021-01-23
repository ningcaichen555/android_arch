package com.top.plugindemo;

import com.top.pluginlibrary.IBean;
import com.top.pluginlibrary.IcallBack;

/**
 * @author caichen QQ:345233199
 * @name PluginDemo
 * @class name：com.top.plugindemo
 * @class describe
 * @time 2021/1/23 17:03
 * @class describe
 */
public class Bean implements IBean {
    private String name = "zxxxx";
    private IcallBack icallBack;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setIcallBack(IcallBack icallBack) {
        this.icallBack = icallBack;
        icallBack.sendResult("我是result");
    }
}
