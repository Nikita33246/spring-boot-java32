package org.itstep.springbootjava32.controller;

import jakarta.validation.Valid;
import org.itstep.springbootjava32.email.test.TestMailSender;
import org.itstep.springbootjava32.model.Department;
import org.itstep.springbootjava32.model.Faculties;
import org.itstep.springbootjava32.model.Group;
import org.itstep.springbootjava32.model.Image;
import org.itstep.springbootjava32.model.Student;
import org.itstep.springbootjava32.service.DepartmentService;
import org.itstep.springbootjava32.service.FacultiesService;
import org.itstep.springbootjava32.service.GroupService;
import org.itstep.springbootjava32.service.ImageUploadService;
import org.itstep.springbootjava32.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Binding;
import java.io.IOException;
import java.util.List;

@Controller
public class StudentController {


    private StudentService studentService;
    private GroupService groupService;
    private DepartmentService departmentService;
    private ImageUploadService imageUploadService;
    private TestMailSender mailSender;
    private final FacultiesService facultiesService;


    @Autowired
    public void setMailSender(TestMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setImageUploadService(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    @Autowired
    public StudentController(FacultiesService facultiesService) {
        this.facultiesService = facultiesService;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //    @Autowired
//    public StudentController(StudentService studentService, GroupService groupService, DepartmentService departmentService, FacultiesService facultiesService) {
//        this.studentService = studentService;
//        this.groupService = groupService;
//        this.departmentService = departmentService;
//        this.facultiesService = facultiesService;
//    }

    @GetMapping("/student")
    public String form(Model model) {
        model.addAttribute("student", new Student());
        return "student";
    }


    @PostMapping("/student")
    public String form(@Valid @ModelAttribute Student student,
                       BindingResult bindingResult,
                       @RequestParam("file") MultipartFile file, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("student", student);
            return "student";
        }
        studentService.save(student);
        mailSender.sendTestMessage(student);

        if (!file.isEmpty()) {
            try {
                imageUploadService.uploadImageToStudent(file, student);
            } catch (IOException e) {
            }
        }
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

    @PostMapping("/student/sorting")
    public String sorting(@RequestParam(value = "sorting") String value, Model model) {

        System.out.println("value = " + value);

        List<Student> students = studentService.sortingByValue(value);

        System.out.println("students = " + students);

        model.addAttribute("students", students);

        return "all-students";
    }

    @GetMapping("/image/{idStudent}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable(value = "idStudent") String idStudent) {
        Image image = imageUploadService.getImageToStudent(studentService.findById(Integer.parseInt(idStudent)));
        if (image == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok() // HTTP 200
                .body(image.getContent());
    }


    @PostMapping("/student/faculties")
    public String faculties(@RequestParam(value = "search") String search, Model model) {
        System.out.println("search:" + search);
        Faculties facultiesByStudentName = facultiesService.findFacultiesByName(search);

        System.out.println("facultiesByStudentName = " + facultiesByStudentName.getName());

        model.addAttribute("search", search);
        model.addAttribute("faculties", facultiesByStudentName);

        return "group";
    }


}
