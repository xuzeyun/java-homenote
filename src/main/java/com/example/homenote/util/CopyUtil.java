package com.example.homenote.util;

import org.springframework.beans.BeanUtils;

public class CopyUtil {
    /* 单体复制 */
    public static <T> T copy(Object source, Class<T> clazz){
        if(source == null){
            return null;
        }
        T obj = null;

        try {
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }
}
