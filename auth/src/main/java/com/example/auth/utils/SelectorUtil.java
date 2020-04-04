package com.example.auth.utils;

import com.example.auth.commons.interfaces.CacheSearch;
import com.example.auth.commons.interfaces.DbSearch;
import org.springframework.stereotype.Component;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.auth.utils
 * @description 查询工具
 * @date 2020/4/4 20:22
 */
@Component
public class SelectorUtil {

    private SelectorUtil() {}

    /**
     * 从缓存或者数据库里查询
     * @param cacheSearch
     * @param dbSearch
     * @param <T>
     * @return
     */
    public static <T> T selectCacheAndDb(CacheSearch<T> cacheSearch, DbSearch<T> dbSearch) {
        T t = cacheSearch.select();
        if (t == null) {
            t = dbSearch.select();
        }
        return t;
    }
}
