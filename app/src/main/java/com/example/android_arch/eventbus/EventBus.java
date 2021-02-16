package com.example.android_arch.eventbus;

/**
 * @author caichen QQ:345233199
 * @name android_arch
 * @class name：com.example.android_arch.eventbus
 * @class describe
 * @time 2021/2/16 13:39
 * @class describe
 */
class EventBus {
    private static EventBus instance;

    private EventBus() {

    }

    /**
     * 单例获取eventBus
     *
     * @return
     */
    public static EventBus getDefault() {
        if (instance == null) {
            synchronized (EventBus.class) {
                if (instance == null) {
                    return new EventBus();
                }
            }
        }
        return instance;
    }
}
