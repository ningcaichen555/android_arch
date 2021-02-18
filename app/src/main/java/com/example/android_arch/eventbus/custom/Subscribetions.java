package com.example.android_arch.eventbus.custom;

/**
 * @author caichen QQ:345233199
 * @name android_arch
 * @class name：com.example.android_arch.eventbus.custom
 * @class describe
 * @time 2021/2/17 10:20
 * @class describe
 */
public class Subscribetions {
    private SubscribeMethod subscribeMethod;
    private Object subscriber;

    public SubscribeMethod getSubscribeMethod() {
        return subscribeMethod;
    }

    public void setSubscribeMethod(SubscribeMethod subscribeMethod) {
        this.subscribeMethod = subscribeMethod;
    }

    public Object getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Object subscriber) {
        this.subscriber = subscriber;
    }

    public Subscribetions(SubscribeMethod subscribeMethod, Object subscriber){
        this.subscribeMethod = subscribeMethod;
        this.subscriber = subscriber;
    }
}
