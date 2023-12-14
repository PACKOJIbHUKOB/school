package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.StudentRepository;

import java.util.*;
import static java.util.stream.Collectors.toList;

@Service
public class StudentService {
  private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createNewStudent(String name, Integer age) {
        Student newStudent = new Student(name, age);
        newStudent = studentRepository.save(newStudent);
        return newStudent;
    }
    public Optional<Student> findStudent(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student editStudent(long id, StudentDto student) {
        Student existanceStudent = studentRepository.findById(id).get();
        existanceStudent.setName(student.getName());
        existanceStudent.setAge(student.getAge());
        studentRepository.save(existanceStudent);
        return existanceStudent;

    }
    public Student removeStudent(Long id) {
        Student existanceStudent = studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return existanceStudent;
    }

    public List<Student> findStudentByAge(int age) {
        return getAllStudents()
                .stream()
                .filter (e->e.getAge() == age)
                .collect(toList());
    }
    public Collection<Student> findAllByAgeBetween(Integer min, Integer max){
        return studentRepository.findAllByAgeBetween(min, max);
    }
    public Faculty getFacultyByStudentId(Long id) {
        return studentRepository.findById(id).get().getFaculty();
    }
    public List<Student> getByFacultyId(Long facultyId) {
        return studentRepository.findByFacultyId(facultyId);
    }

    public Integer getCount(){
        return studentRepository.getCount();
    }

    public Double getAvgAge(){
        return studentRepository.getAvgAge();
    }
    public List<Student> getLastFiveStudent(){
        return studentRepository.getLastFiveStudent();
    }

}
