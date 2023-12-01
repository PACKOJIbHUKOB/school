package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentExceptionNotFound;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.StudentRepositories;

import java.util.*;
import static java.util.stream.Collectors.toList;

@Service
public class StudentService {
  private final StudentRepositories studentRepositories;

    public StudentService(StudentRepositories studentRepositories) {
        this.studentRepositories = studentRepositories;
    }

    public Student creatNewStudent(Student student) {
        return studentRepositories.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepositories.findById(id).get();
    }

    public List<Student> getAllStudents() {
        return studentRepositories.findAll();
    }

    public Student editStudent(long id,Student student) {
        Student existanceStudent = findStudent(id);
        existanceStudent.setName(student.getName());
        existanceStudent.setAge(student.getAge());
        studentRepositories.save(existanceStudent);
        return existanceStudent;

    }
    public Student removeStudent(Long id) {
        Student existanceStudent = findStudent(id);
        studentRepositories.deleteById(id);
        return existanceStudent;
    }

    public List<Student> findStudentByAge(int age) {
        return getAllStudents()
                .stream()
                .filter (e->e.getAge() == age)
                .collect(toList());
    }
    public Collection<Student> findAllByAgeBetween(Integer min, Integer max){
        return studentRepositories.findAllByAgeBetween(min, max);
    }

}
