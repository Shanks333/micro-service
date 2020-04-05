package com.example.student.controller;

import com.example.student.commons.ResponseData;
import com.example.student.domain.StuInfo;
import com.example.student.service.StuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.student.controller
 * @description
 * @date 2020/4/5 15:22
 */
@RestController
public class StudentController {

    @Autowired
    private StuInfoService stuInfoService;

    @GetMapping("/stuinfos/page-{page}")
    public ResponseData getStudentsByPage(@PathVariable String page) {
        Integer pageNumber = null;
        try {
            pageNumber = Integer.parseInt(page);
        } catch (Exception e){
            pageNumber = 1;
        }
        pageNumber = pageNumber < 1 ? 1 : pageNumber;
        List<StuInfo> stuInfos = stuInfoService.getStudentsByPage(pageNumber);
        return new ResponseData()
                .setData(stuInfos)
                .setStatus(200);
    }

    @PostMapping("/stuinfo")
    public ResponseData changeStuInfo(StuInfo stuInfo) {
        if (stuInfoService.changeStuInfo(stuInfo)) {
            return new ResponseData()
                    .setData("修改成功")
                    .setStatus(200);
        }
        return new ResponseData()
                .setData("修改失败")
                .setStatus(405);
    }

    @PutMapping("/stuinfo")
    public ResponseData addStuInfo(StuInfo stuInfo) {
        if (stuInfoService.addStuInfo(stuInfo)) {
            return new ResponseData()
                    .setData("添加成功")
                    .setStatus(200);
        }
        return new ResponseData()
                .setData("添加失败")
                .setStatus(405);
    }

    @DeleteMapping("/stuinfo/id-{id}")
    public ResponseData delStuInfoById(@PathVariable String id) {
        if (stuInfoService.removeStuInfoById(id)) {
            return new ResponseData()
                    .setData("删除成功")
                    .setStatus(200);
        }
        return new ResponseData()
                .setData("删除失败")
                .setStatus(405);
    }

    @DeleteMapping("/stuinfo/stuid-{stuId}")
    public ResponseData delStuInfoByStuId(@PathVariable String stuId) {
        if (stuInfoService.removeStuInfoByStuId(stuId)) {
            return new ResponseData()
                    .setData("删除成功")
                    .setStatus(200);
        }
        return new ResponseData()
                .setData("删除失败")
                .setStatus(405);
    }
}
