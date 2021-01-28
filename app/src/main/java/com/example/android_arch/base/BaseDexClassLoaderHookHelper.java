package com.example.android_arch.base;

import com.example.android_arch.hook.RefInvoke;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import utils.LogUtils;

public class BaseDexClassLoaderHookHelper {
    public static void patchClassLoader(ClassLoader classLoader, File dexFile, File apkFile) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, IOException {
//        //classLoader ---> dexClassLoader
//        LogUtils.INSTANCE.d(classLoader.getClass() + "");
//
//        //获取classloader中的pathList字段
//        Class<? super BaseDexClassLoader> baseDexClass = BaseDexClassLoader.class;
//        Field pathList = baseDexClass.getDeclaredField("pathList");
//        pathList.setAccessible(true);
//        Object pathListObj = pathList.get(classLoader);
//
//        //获取pathList中的dexElements主要存放的是dex列表
//        Class<?> pathListClass = pathListObj.getClass();
//        Field dexElementsField = pathListClass.getDeclaredField("dexElements");
//        dexElementsField.setAccessible(true);
//        Object[] dexElements = (Object[]) dexElementsField.get(pathListObj);
//        Class<?> elementClass = dexElementsField.get(pathListObj).getClass().getComponentType();
//
//        Constructor<?>[] declaredConstructors = elementClass.getDeclaredConstructors();
//        for (int i = 0; i < declaredConstructors.length; i++) {
//            LogUtils.INSTANCE.d(declaredConstructors[i] + "");
//        }
//        Object[] newElements = (Object[]) Array.newInstance(elementClass, dexElements.length + 1);
//        Class[] elementTypes = {File.class, boolean.class, File.class, DexFile.class};
//        LogUtils.INSTANCE.d("dexFile.getCanonicalPath()" + dexFile.getCanonicalPath());
//        LogUtils.INSTANCE.d("apkFile.getAbsolutePath()" + apkFile.getAbsolutePath());
//        Object[] elementObjs = {apkFile, false, apkFile, DexFile.loadDex(dexFile.getCanonicalPath(), apkFile.getAbsolutePath(), 0)};
//        Constructor<?> declaredConstructor = elementClass.getDeclaredConstructor(elementTypes);
//        declaredConstructor.setAccessible(true);
//        Object element = declaredConstructor.newInstance(elementObjs);
//
//        Object[] toAddElementArray = new Object[]{element};
//        // 把原始的elements复制进去
//        System.arraycopy(dexElements, 0, newElements, 0, dexElements.length);
//        // 插件的那个element复制进去
//        System.arraycopy(toAddElementArray, 0, newElements, dexElements.length, toAddElementArray.length);
//
//        // 替换
//        RefInvoke.setFieldObject(pathListObj, "dexElements", newElements);
//
//        for (int i = 0; i < newElements.length; i++) {
//            LogUtils.INSTANCE.d("dexElements" + newElements[i]);
//        }


        // 获取 BaseDexClassLoader : pathList
        Object pathListObj = RefInvoke.getFieldObject(DexClassLoader.class.getSuperclass(), classLoader, "pathList");

        // 获取 PathList: Element[] dexElements
        Object[] dexElements = (Object[]) RefInvoke.getFieldObject(pathListObj, "dexElements");

        // Element 类型
        Class<?> elementClass = dexElements.getClass().getComponentType();

        // 创建一个数组, 用来替换原始的数组
        Object[] newElements = (Object[]) Array.newInstance(elementClass, dexElements.length + 1);

        // 构造插件Element(File file, boolean isDirectory, File zip, DexFile dexFile) 这个构造函数
        Class[] p1 = {File.class, boolean.class, File.class, DexFile.class};
        Object[] v1 = {apkFile, false, apkFile, DexFile.loadDex(apkFile.getCanonicalPath(), dexFile.getAbsolutePath(), 0)};
        Object o = RefInvoke.createObject(elementClass, p1, v1);

        Object[] toAddElementArray = new Object[]{o};
        // 把原始的elements复制进去
        System.arraycopy(dexElements, 0, newElements, 0, dexElements.length);
        // 插件的那个element复制进去
        System.arraycopy(toAddElementArray, 0, newElements, dexElements.length, toAddElementArray.length);

        // 替换
        RefInvoke.setFieldObject(pathListObj, "dexElements", newElements);
    }
}
