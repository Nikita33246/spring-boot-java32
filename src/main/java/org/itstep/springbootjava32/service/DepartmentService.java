package org.itstep.springbootjava32.service;


import org.itstep.springbootjava32.model.Department;
import org.itstep.springbootjava32.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
            this.departmentRepository = departmentRepository;
    }


    public Department findDepartmentByStudentName(String studentName) {
        return departmentRepository.findByStudentName(studentName);
    }

}
