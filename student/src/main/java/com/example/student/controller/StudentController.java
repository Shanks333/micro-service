package com.example.student.controller;

import com.alibaba.fastjson.JSON;
import com.example.student.commons.ResponseData;
import com.example.student.domain.StuInfo;
import com.example.student.domain.StuLog;
import com.example.student.mq.SendMq;
import com.example.student.service.StuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private SendMq sendMq;

    @Value("${custom.header.username}")
    private String usernameHeader;

    @GetMapping("/stuinfos/page-{page}")
    public ResponseData getStudentsByPage(@PathVariable String page, HttpServletRequest request) {
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
    public ResponseData changeStuInfo(StuInfo stuInfo, HttpServletRequest request) {
        if (stuInfoService.changeStuInfo(stuInfo)) {
            StuLog log = new StuLog();
            log.setUsername(request.getHeader(usernameHeader));
            log.setDesc("修改了id为：" + stuInfo.getId() + "的" + "学生信息！");
            sendMq.send(JSON.toJSONString(log));
            return new ResponseData()
                    .setData("修改成功")
                    .setStatus(200);
        }
        return new ResponseData()
                .setData("修改失败")
                .setStatus(405);
    }

    @PutMapping("/stuinfo")
    public ResponseData addStuInfo(StuInfo stuInfo, HttpServletRequest request) {
        if (stuInfoService.addStuInfo(stuInfo)) {
            StuLog log = new StuLog();
            log.setUsername(request.getHeader(usernameHeader));
            log.setDesc("添加了" + stuInfo.getStuId() + stuInfo.getStuName() + "学生的信息！");
            sendMq.send(JSON.toJSONString(log));
            return new ResponseData()
                    .setData("添加成功")
                    .setStatus(200);
        }
        return new ResponseData()
                .setData("添加失败")
                .setStatus(405);
    }

    @DeleteMapping("/stuinfo/id-{id}")
    public ResponseData delStuInfoById(@PathVariable String id, HttpServletRequest request) {
        if (stuInfoService.removeStuInfoById(id)) {
            StuLog log = new StuLog();
            log.setUsername(request.getHeader(usernameHeader));
            log.setDesc("删除了id为" + id + "的学生信息！");
            sendMq.send(JSON.toJSONString(log));
            return new ResponseData()
                    .setData("删除成功")
                    .setStatus(200);
        }
        return new ResponseData()
                .setData("删除失败")
                .setStatus(405);
    }

    @DeleteMapping("/stuinfo/stuid-{stuId}")
    public ResponseData delStuInfoByStuId(@PathVariable String stuId, HttpServletRequest request) {
        if (stuInfoService.removeStuInfoByStuId(stuId)) {
            StuLog log = new StuLog();
            log.setUsername(request.getHeader(usernameHeader));
            log.setDesc("删除了学号为" + stuId + "的学生信息！");
            sendMq.send(JSON.toJSONString(log));
            return new ResponseData()
                    .setData("删除成功")
                    .setStatus(200);
        }
        return new ResponseData()
                .setData("删除失败")
                .setStatus(405);
    }
}
