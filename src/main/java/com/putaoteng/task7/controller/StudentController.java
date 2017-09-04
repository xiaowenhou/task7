package com.putaoteng.task7.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.putaoteng.task7.utils.other.Storage;
import com.putaoteng.task7.utils.other.Transfer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.putaoteng.task7.model.BasicVo;
import com.putaoteng.task7.model.Student;
import com.putaoteng.task7.service.StudentDaoService;
import com.putaoteng.task7.service.VerificationDaoService;

@Controller
public class StudentController {
	@Resource(name = "studentDaoService")
	private StudentDaoService studentDaoService;
	@Resource (name = "verificationDaoService")
	private VerificationDaoService verifiDaoService;
	@Resource (name="transfer")
	private Transfer transfer;
	@Resource (name = "storage")
	private Storage storage;
	// 主页
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	// 录入信息页
	@RequestMapping(value = "/u/inputdata", method = RequestMethod.GET)
	public String inputData() {
		return "addStudent";
	}

	// 操作页面
	@RequestMapping(value = "/u/operations", method = RequestMethod.GET)
	public String operate(HttpServletRequest request) {
		if (transfer.getToOther().equalsIgnoreCase("ali-to-qiniu")){
			request.getSession().setAttribute("info", "目前的状态是:阿里云迁移到七牛云");
			request.getSession().setMaxInactiveInterval(1);
		} else if (transfer.getToOther().equalsIgnoreCase("qiniu-to-ali")){
			request.getSession().setAttribute("info", "目前的状态是:七牛迁移到阿里云");
			request.getSession().setMaxInactiveInterval(1);
		}

		return "operations";
	}

	// 成功页面
	@RequestMapping(value = "/u/success", method = RequestMethod.GET)
	public String success() {
		return "success";
	}

	// 失败页面
	@RequestMapping(value = "/u/failed", method = RequestMethod.GET)
	public String failed() {
		return "failed";
	}

	// 编辑信息页
	@RequestMapping(value = "/u/editdata", method = RequestMethod.POST)
	public String editData(Model model, Long id) {
		Student student = studentDaoService.findByPK(id);

		model.addAttribute("student", student);
		return "editStudent";
	}

	// 所有学生列表
	@RequestMapping(value = "/u/list", method = RequestMethod.GET)
	public String findAll(Model model) {
		List<BasicVo> list = studentDaoService.findAll();

		model.addAttribute("studentList", list);
		return "studentList";
	}

	// 按姓名查找列表
	@RequestMapping(value = "/u/student", method = RequestMethod.GET)
	public String findByName(Model model, String name) {
		List<BasicVo> list = studentDaoService.findByStudentName(name);

		if (list.isEmpty()) {
			return "failed";
		}

		model.addAttribute("studentList", list);
		return "student";
	}

	// 新增学员
	@RequestMapping(value = "/u/student", method = RequestMethod.POST)
	public String save(Model model, Student student) {
		student.setCreateAt(System.currentTimeMillis());
		student.setUpdateAt(System.currentTimeMillis());

		int result = studentDaoService.save(student);

		return result == 0 ? "failed" : "success";
	}

	// 更新学员数据
	@RequestMapping(value = "/u/student", method = RequestMethod.PUT)
	public String update(Model model, Student student) {
		student.setUpdateAt(System.currentTimeMillis());
		int result = studentDaoService.update(student);
		return result == 0 ? "failed" : "success";
	}

	// 删除学员数据
	@RequestMapping(value = "/u/student", method = RequestMethod.DELETE)
	public String delete(HttpServletRequest request, Long id) {
		//获取空间中的文件名
		String fileName = studentDaoService.findByPK(id).getImage();
		String[] strArray = fileName.split("/");
		fileName = strArray[strArray.length-3] + "/" + strArray[strArray.length-2] + "/" + strArray[strArray.length-1];

		int result = studentDaoService.deleteByPK(id);
		//删除数据的同时也删除云存储空间中的文件
		if (result != 0 && storage.deleteFile(fileName)){
			return "success";
		}
		return "failed";
	}
}
