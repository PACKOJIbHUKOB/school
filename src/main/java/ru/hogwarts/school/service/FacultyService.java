package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.FacultyRepository;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentService studentService;

    public FacultyService(FacultyRepository facultyRepository, StudentService studentService) {
        this.facultyRepository = facultyRepository;
        this.studentService = studentService;
    }


    public Faculty createNewFaculty(Faculty faculty) {
        return facultyRepository.save(faculty) ;
    }

    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty editFaculty(long id,Faculty faculty) {
        Faculty exsistfaculty =findFaculty(id);
        exsistfaculty.setName(faculty.getName());
        exsistfaculty.setColor(faculty.getColor());
        return facultyRepository.save(exsistfaculty);
    }

    public Faculty removeFaculty(Long id) {
        Faculty exsistfaculty =findFaculty(id);
        facultyRepository.deleteById(id);
        return exsistfaculty;
    }
    public List<Faculty> findFacultyByColor(String color) {
        return getAllFaculties()
                .stream()
                .filter (e->e.getColor().equals(color))
                .collect(toList());
    }
    public Collection<Faculty> findAllByNameOrColorIgnoreCase(String param){
        Set<Faculty> result = new HashSet<>();
        result.addAll(facultyRepository.findByColorIgnoreCase(param));
        result.addAll(facultyRepository.findByNameIgnoreCase(param));
        return result;
    }
    public List<Student> getStudentsByFacultyId(Long id) {
        return studentService.getByFacultyId(id);
    }
}