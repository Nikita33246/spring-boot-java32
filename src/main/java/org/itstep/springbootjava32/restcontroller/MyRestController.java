package org.itstep.springbootjava32.restcontroller;


import jakarta.validation.Valid;
import org.itstep.springbootjava32.dto.StudentDTO;
import org.itstep.springbootjava32.facade.StudentFacade;
import org.itstep.springbootjava32.model.Student;
import org.itstep.springbootjava32.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("v2")
public class MyRestController {


    private final StudentService studentService;

    private final StudentFacade studentFacade;

    @Autowired
    public MyRestController(StudentService studentService, StudentFacade studentFacade) {
        this.studentService = studentService;
        this.studentFacade = studentFacade;
    }


    @GetMapping("/all-students")
    public ResponseEntity<List<StudentDTO>> getStudents() {

        List<Student> studentList = studentService.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student student : studentList) {
            studentDTOList.add(studentFacade.studentToStudentDTO(student));
        }

        return ResponseEntity.ok(studentDTOList);

    }


    @PostMapping("/student")
    public ResponseEntity<Object> form(@RequestBody Student student) throws IOException {
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }



}
