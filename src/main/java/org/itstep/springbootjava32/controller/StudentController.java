package org.itstep.springbootjava32.controller;

import org.itstep.springbootjava32.model.Department;
import org.itstep.springbootjava32.model.Group;
import org.itstep.springbootjava32.model.Student;
import org.itstep.springbootjava32.model.Teacher;
import org.itstep.springbootjava32.service.DepartmentService;
import org.itstep.springbootjava32.service.GroupService;
import org.itstep.springbootjava32.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {


    private StudentService studentService;
    private GroupService groupService;
    private DepartmentService departmentService;


    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/student")
    public String form(Model model) {
        model.addAttribute("student", new Student());
        return "student";
    }


    @PostMapping("/student")
    public String form(@ModelAttribute Student student) {
        studentService.save(student);

        return "redirect:/all-students";
    }

    @GetMapping("/all-students")
    public String allStudents(Model model) {
        model.addAttribute("students", studentService.findAll());

        for (Student student : studentService.findAll()) {
            System.out.println("Name:" + student.getName());
            if (student.getGroup() != null) {
                System.out.println("\t\tGroup: " + student.getGroup().getName());
            }
        }
        return "all-students";
    }


    @GetMapping("/show-student/{id}")
    public String showStudent(@PathVariable(value = "id") String id, Model model) {
        System.out.println("==============================");

        Student student = studentService.findById(Integer.parseInt(id));


        System.out.println("========== Student: " + student.getName() + " =================");
//        for (Teacher teacher : student.getTeachers()) {
//            System.out.println("+++++++++++++++ Teacher: " + teacher.getName()+" ++++++++++++++++");
//        }

        model.addAttribute("student", student);

        return "show-student";
    }


    @GetMapping("/student/delete{id}")
    public String delete(@PathVariable(value = "id") String id) {
        List<Student> all = studentService.findAll();
        for (Student student : all) {
            if (student.getId() == Integer.parseInt(id)) {
                studentService.delete(student);
            }
        }
        return "redirect:/all-students";
    }

    @GetMapping("/student/update{id}")
    public String update(@PathVariable(value = "id") String id, Model model) {
        Student student = studentService.findById(Integer.parseInt(id));
        model.addAttribute("student", student);
        return "student";
    }


    @PostMapping("/student/group")
    public String group(@RequestParam(value = "search") String search, Model model) {

        System.out.println("search:" + search);

        Group groupByStudentName = groupService.findGroupByStudentName(search);
        System.out.println("GroupByStudentName: " + groupByStudentName.getName());

        model.addAttribute("search", search);
        model.addAttribute("group", groupByStudentName);

        return "group";
    }

    @PostMapping("/student/department")
    public String department(@RequestParam(value = "search") String search, Model model) {

        System.out.println("search:" + search);

        Department departmentByStudentName = departmentService.findDepartmentByStudentName(search);

        System.out.println("departmentByStudentName = " + departmentByStudentName.getName());

        model.addAttribute("search", search);
        model.addAttribute("department", departmentByStudentName);

        //======================

        Student studentByName = studentService.findStudentByName(search);
        String depName = studentByName.getGroup().getDepartment().getName();

        System.out.println("Depatrment Name = " + depName);


        return "group";
    }

}
