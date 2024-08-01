package org.itstep.springbootjava32.service;

import org.itstep.springbootjava32.model.Faculties;
import org.itstep.springbootjava32.repository.FacultiesRepository;
import org.springframework.stereotype.Service;

@Service
public class FacultiesService {

    private final FacultiesRepository facultiesRepository;

    public FacultiesService(FacultiesRepository facultiesRepository) {
        this.facultiesRepository = facultiesRepository;
    }

    public Faculties findFacultiesByName(String name) {
        return facultiesRepository.findByName(name);
    }
}
