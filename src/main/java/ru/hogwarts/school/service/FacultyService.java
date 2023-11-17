package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
        return faculties.get(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return faculties.values();
    }

    public Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty removeFaculty(Long id) {
        faculties.remove(id);
        return faculties.get(id);
    }
    public Collection<Faculty> findFacultyByColor(String color) {
        return getAllFaculties()
                .stream()
                .filter (e->e.getColor().equals(color))
                .collect(toList());
    }
}