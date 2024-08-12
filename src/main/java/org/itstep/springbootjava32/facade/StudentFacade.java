package org.itstep.springbootjava32.facade;

import org.itstep.springbootjava32.dto.StudentDTO;
import org.itstep.springbootjava32.model.Student;
import org.springframework.stereotype.Component;


@Component
public class StudentFacade {

    public StudentDTO studentToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        return studentDTO;

    }


}
