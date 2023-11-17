package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
        return students.get(id);
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }
    public Student removeStudent(Long id) {
        return students.remove(id);
    }

    public Collection<Student> findStudentByAge(int age) {
        return getAllStudents()
                .stream()
                .filter (e->e.getAge() == age)
                .collect(toList());
    }
}
