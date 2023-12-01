package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyExceptionNotFound;
import ru.hogwarts.school.model.Faculty;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class FacultyService {
    private Map<Long, Faculty> faculties = new HashMap();
    private long lastId = 0;

    public Faculty creatNewFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id) {
        if(!faculties.containsKey(id)){
            throw new FacultyExceptionNotFound("факультета с данным id "+id+" ненайдено");
        }
        return faculties.get(id);
    }

    public List<Faculty> getAllFaculties() {
        return new ArrayList<>(faculties.values());
    }

    public Faculty editFaculty(long id,Faculty faculty) {
        Faculty exsistfaculty =findFaculty(id);
        exsistfaculty.setName(faculty.getName());
        exsistfaculty.setColor(faculty.getColor());
        return exsistfaculty;
    }

    public Faculty removeFaculty(Long id) {
        return faculties.remove(id);
    }
    public List<Faculty> findFacultyByColor(String color) {
        return getAllFaculties()
                .stream()
                .filter (e->e.getColor().equals(color))
                .collect(toList());
    }
}