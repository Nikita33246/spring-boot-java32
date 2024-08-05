package org.itstep.springbootjava32.service;

import org.itstep.springbootjava32.model.Curator;
import org.itstep.springbootjava32.repository.CuratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuratorService {

    private CuratorRepository curatorRepository;

    @Autowired
    public void setCuratorRepository(CuratorRepository curatorRepository) {
        this.curatorRepository = curatorRepository;
    }

    public Curator findCuratorByGroupName(String groupName){
        return curatorRepository.findByGroupName(groupName);
    }
}
