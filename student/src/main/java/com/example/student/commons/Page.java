package com.example.student.commons;

import lombok.Data;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.student.commons
 * @description
 * @date 2020/4/5 16:33
 */
@Data
public class Page {

    public Page() {}

    public Page(Integer start, Integer pageSize) {
        this.start = start;
        this.pageSize = pageSize;
    }

    private Integer start;

    private Integer pageSize;
}
