package com.example.android_arch.eventbus;

import com.example.android_arch.eventbus.custom.SubscribeMethod;
import com.example.android_arch.eventbus.custom.Subscribetions;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<Class<?>, List<Subscribetions>> subscribetionByEventType = new HashMap<>();
    private Map<Object, List<Class<?>>> eventTypeBySubscriber = new HashMap();

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

    private void register(Object subscriber) {
        List<SubscribeMethod> subscribeMethods = findSubscriberMethods(subscriber);
        for (int i = 0; i < subscribeMethods.size(); i++) {
            subscriber(subscriber,subscribeMethods.get(i));
        }
    }

    private void subscriber(Object subscriber, SubscribeMethod subscribeMethod) {

    }

    private List<SubscribeMethod> findSubscriberMethods(Object subscriber) {
        return null;
    }

    private void unRegister(Object subscriber) {

    }

    private void post(Object event) {
        try {
            postSingleEventType(event, event.getClass());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void postSingleEventType(Object event, Class<?> clazz) throws InvocationTargetException, IllegalAccessException {
        List<Subscribetions> subscribetions = subscribetionByEventType.get(clazz);
        for (int i = 0; i < subscribetions.size(); i++) {
            subscribetions.get(i).getSubscribeMethod().getMethod().invoke(subscribetions.get(i).getSubscriber(), event);
        }
    }
}
