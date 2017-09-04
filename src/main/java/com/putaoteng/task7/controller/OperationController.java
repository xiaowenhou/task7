package com.putaoteng.task7.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.putaoteng.task7.model.BasicVo;
import com.putaoteng.task7.utils.other.AliStorage;
import com.putaoteng.task7.utils.other.QiniuStorage;
import com.putaoteng.task7.utils.other.Storage;
import com.putaoteng.task7.utils.other.Transfer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.putaoteng.task7.model.Student;
import com.putaoteng.task7.service.StudentDaoService;

import java.util.List;

@Controller
public class OperationController {
    @Resource(name = "studentDaoService")
    private StudentDaoService studentDaoService;
    @Resource(name = "storage")
    private Storage storage;
    @Resource(name = "aliStorage")
    private AliStorage aliStorage;
    @Resource(name = "qiniuStorage")
    protected QiniuStorage qiniuStorage;
    @Resource(name = "transfer")
    private Transfer transfer;

    @RequestMapping(value = "/u/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public String upload(@RequestParam(value = "file", required = false) CommonsMultipartFile file,
                         HttpServletRequest request, long id) {
        //获取上传的文件名,且只允许上传图片
        String fileName = file.getOriginalFilename();
        if (fileName.equals("") || !(fileName.endsWith("jpg") ||
                fileName.endsWith("gif") || fileName.endsWith("png"))) {
            return "failed";
        }

        fileName = "user/images/" + id + "." + fileName.substring(fileName.length() - 3);
        //将文件转换为字节数组
        byte[] uploadBytes = file.getBytes();
        boolean result = storage.uploadFileByByteArray(uploadBytes, fileName);

        if (!result){
            return "failed";
        }
        //确定图片路径,存储在数据库中
        String imagePath;
        if (storage.getChoice().equals("qiniu")) {
            imagePath = qiniuStorage.getEndpoint() + "/" + fileName;
        } else {
            //通过ali的endpoint拼接出图片的最终imagePath
            String url = aliStorage.getEndpoint();
            imagePath = url.substring(0, 7) + aliStorage.getBucketName() + "." + url.substring(7) + "/" + fileName;
        }

        Student student = studentDaoService.findByPK(id);
        student.setImage(imagePath);
        studentDaoService.update(student);

        return "success";
    }

    @RequestMapping(value = "/u/transfer", method = RequestMethod.GET)
    public String transfer() {
        //根据配置文件要求迁移数据
        boolean result = transfer.transfer();
        if (!result) {
            return "failed";
        }
        String prefix;
        String suffix;
        //根据不同情况获取头像链接前缀
        if (transfer.getToOther().equalsIgnoreCase("ali-to-qiniu")) {
            prefix = qiniuStorage.getEndpoint() + "/";

        } else if (transfer.getToOther().equalsIgnoreCase("qiniu-to-ali")) {
            String url = aliStorage.getEndpoint();
            prefix = url.substring(0, 7) + aliStorage.getBucketName() + "." + url.substring(7) + "/";
        } else {
            return "failed";
        }

        List<BasicVo> studentList = studentDaoService.findAll();
        for (BasicVo basicVo : studentList) {
            //获取头像后缀
            Student student = (Student) basicVo;
            int index = student.getImage().lastIndexOf("/");
            suffix = "user/images" + student.getImage().substring(index);
            //迁移的同时更新数据库中的内容
            student.setImage(prefix + suffix);
            studentDaoService.update(student);
        }
        return "success";
    }
}
