package org.itstep.springbootjava32.service;

import org.itstep.springbootjava32.model.Group;
import org.itstep.springbootjava32.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private GroupRepository groupRepository;

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group findGroupByStudentName(String studentName){
        return groupRepository.findByStudentName(studentName);
    }
}
