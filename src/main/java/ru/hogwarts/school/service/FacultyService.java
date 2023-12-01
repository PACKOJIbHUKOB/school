package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyExceptionNotFound;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositiries.FacultyRepositories;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class FacultyService {
    private final FacultyRepositories facultyRepositories;

    public FacultyService(FacultyRepositories facultyRepositories) {
        this.facultyRepositories = facultyRepositories;
    }


    public Faculty creatNewFaculty(Faculty faculty) {
        return facultyRepositories.save(faculty) ;
    }

    public Faculty findFaculty(Long id) {
        return facultyRepositories.findById(id).get();
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepositories.findAll();
    }

    public Faculty editFaculty(long id,Faculty faculty) {
        Faculty exsistfaculty =findFaculty(id);
        exsistfaculty.setName(faculty.getName());
        exsistfaculty.setColor(faculty.getColor());
        return facultyRepositories.save(exsistfaculty);
    }

    public Faculty removeFaculty(Long id) {
        Faculty exsistfaculty =findFaculty(id);
        facultyRepositories.deleteById(id);
        return exsistfaculty;
    }
    public List<Faculty> findFacultyByColor(String color) {
        return getAllFaculties()
                .stream()
                .filter (e->e.getColor().equals(color))
                .collect(toList());
    }
}