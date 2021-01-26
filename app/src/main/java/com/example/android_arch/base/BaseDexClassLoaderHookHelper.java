package com.example.android_arch.base;

import com.example.android_arch.hook.RefInvoke;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import utils.LogUtils;

public class BaseDexClassLoaderHookHelper {
    public static void patchClassLoader(ClassLoader classLoader, File dexFile, File apkFile) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, IOException {
        Class<? super DexClassLoader> baseDexClass = DexClassLoader.class.getSuperclass();
        Field pathList = baseDexClass.getDeclaredField("pathList");
        pathList.setAccessible(true);
        Object pathListObj = pathList.get(classLoader);

        Class<?> pathListClass = pathListObj.getClass();
        Field dexElementsField = pathListClass.getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        Object[] dexElements = (Object[]) dexElementsField.get(pathListObj);
        Class<?> elementClass = dexElementsField.get(pathListObj).getClass().getComponentType();

        Object[] newElements = (Object[]) Array.newInstance(elementClass, dexElements.length + 1);

        Class[] elementTypes = {DexFile.class, File.class};
        Object[] elementObjs = {DexFile.loadDex(dexFile.getCanonicalPath(), apkFile.getAbsolutePath(), 0), apkFile};
        Constructor<?> declaredConstructor = elementClass.getDeclaredConstructor(elementTypes);
        declaredConstructor.setAccessible(true);
        Object element = declaredConstructor.newInstance(elementObjs);

        Object[] toAddElementArray = new Object[] { element };
        // 把原始的elements复制进去
        System.arraycopy(dexElements, 0, newElements, 0, dexElements.length);
        // 插件的那个element复制进去
        System.arraycopy(toAddElementArray, 0, newElements, dexElements.length, toAddElementArray.length);

        // 替换
        RefInvoke.setFieldObject(pathListObj, "dexElements", newElements);

        for (int i = 0; i < newElements.length; i++) {
            LogUtils.INSTANCE.d("dexElements" + newElements[i]);
        }

    }
}
