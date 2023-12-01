package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentExceptionNotFound;
import ru.hogwarts.school.model.Student;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
     private long lastId;

    public Student creatNewStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent(Long id) {
        if (!students.containsKey(id)){
            throw new StudentExceptionNotFound("студент c таким id "+id+" не найден");
        }
        return students.get(id);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<> (students.values());
    }

    public Student editStudent(long id,Student student) {
        Student existanceStudent = findStudent(id);
        existanceStudent.setName(student.getName());
        existanceStudent.setAge(student.getAge());
        return existanceStudent;

    }
    public Student removeStudent(Long id) {
        return students.remove(id);
    }

    public List<Student> findStudentByAge(int age) {
        return getAllStudents()
                .stream()
                .filter (e->e.getAge() == age)
                .collect(toList());
    }
}
