package com.example.student.service;

import com.example.student.domain.StuInfo;

import java.util.List;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.student.service
 * @description
 * @date 2020/4/5
 */
public interface StuInfoService {
    /**
     * 通过页码查询学生数
     * @param pageNumber
     * @return
     */
    List<StuInfo> getStudentsByPage(Integer pageNumber);

    /**
     * 添加学生信息
     * @param stuInfo
     * @return
     */
    Boolean addStuInfo(StuInfo stuInfo);

    /**
     * 通过id删除学生
     * @param id
     * @return
     */
    Boolean removeStuInfoById(String id);

    /**
     * 通过学号删除学生
     * @param stuId
     * @return
     */
    Boolean removeStuInfoByStuId(String stuId);

    /**
     * 修改学生信息
     * @param stuInfo
     * @return
     */
    Boolean changeStuInfo(StuInfo stuInfo);
}
