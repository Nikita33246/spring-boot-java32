package org.itstep.springbootjava32.controller;

import org.itstep.springbootjava32.model.Student;
import org.itstep.springbootjava32.model.Teacher;
import org.itstep.springbootjava32.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {


    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/student")
    public String form(Model model) {
        //model.addAttribute("student", new Student());
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
            if (student.getTeam() != null) {
                System.out.println("\t\tGroup: " + student.getTeam().getName());
            }
        }
        return "all-students";
    }


    @GetMapping("/show-student/{id}")
    public String showStudent(@PathVariable(value = "id") String id, Model model) {
        System.out.println("==============================");
        Student student = new Student();
        for (Student student1 : studentService.findAll()) {
            if (student1.getId() == Integer.parseInt(id)) {
                student = student1;
                break;
            }
        }
        //Student student = studentDAO.findById(Integer.parseInt(id));


        System.out.println("========== Student: " + student.getName() + " =================");
        for (Teacher teacher : student.getTeachers()) {
            System.out.println("+++++++++++++++ Teacher: " + teacher.getName()+" ++++++++++++++++");
        }

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


}
