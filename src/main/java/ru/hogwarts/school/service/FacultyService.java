package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.FacultyRepository;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;
    private final StudentService studentService;

    public FacultyService(FacultyRepository facultyRepository, StudentService studentService) {
        this.facultyRepository = facultyRepository;
        this.studentService = studentService;
    }


    public Faculty createNewFaculty(String name, String color) {
        logger.info("Был вызван метод createNewFaculty");
        Faculty newFaculty = new Faculty(name, color);
        newFaculty = facultyRepository.save(newFaculty);
        return newFaculty;
    }

    public Faculty findFaculty(Long id) {
        logger.info("Был вызван метод findFaculty");
        return facultyRepository.findById(id).get();
    }

    public List<Faculty> getAllFaculties() {
        logger.info("Был вызван метод createNewStudent");
        return facultyRepository.findAll();
    }

    public Faculty editFaculty(long id,Faculty faculty) {
        logger.info("Был вызван метод editFaculty");
        Faculty exsistfaculty =findFaculty(id);
        exsistfaculty.setName(faculty.getName());
        exsistfaculty.setColor(faculty.getColor());
        return facultyRepository.save(exsistfaculty);
    }

    public Faculty removeFaculty(Long id) {
        logger.info("Был вызван метод removeFaculty");
        Faculty exsistfaculty =findFaculty(id);
        facultyRepository.deleteById(id);
        return exsistfaculty;
    }
    public List<Faculty> findFacultyByColor(String color) {
        logger.info("Был вызван метод findFacultyByColor");
        return getAllFaculties()
                .stream()
                .filter (e->e.getColor().equals(color))
                .collect(toList());
    }
    public Collection<Faculty> findAllByNameOrColorIgnoreCase(String param){
        logger.info("Был вызван метод findAllByNameOrColorIgnoreCase");
        Set<Faculty> result = new HashSet<>();
        result.addAll(facultyRepository.findByColorIgnoreCase(param));
        result.addAll(facultyRepository.findByNameIgnoreCase(param));
        return result;
    }
    public List<Student> getStudentsByFacultyId(Long id) {
        logger.info("Был вызван метод getStudentsByFacultyId");
        return studentService.getByFacultyId(id);
    }
}