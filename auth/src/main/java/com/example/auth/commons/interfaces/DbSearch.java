package com.example.auth.commons.interfaces;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.auth.commons.interfaces
 * @description 数据库查询
 * @date 2020/4/4
 */
public interface DbSearch<T> {
    T select();
}
