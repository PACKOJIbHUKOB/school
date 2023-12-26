package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class StudentService {
  private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createNewStudent(String name, Integer age) {
        logger.info("Был вызван метод createNewStudent");
        Student newStudent = new Student(name, age);
        newStudent = studentRepository.save(newStudent);
        return newStudent;
    }
    public Optional<Student> findStudent(Long id) {
        logger.info("Был вызван метод findStudent");
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents() {
        logger.info("Был вызван метод getAllStudents");
        return studentRepository.findAll();
    }

    public Student editStudent(long id, StudentDto student) {
        logger.info("Был вызван метод editStudent");
        Student existanceStudent = studentRepository.findById(id).get();
        existanceStudent.setName(student.getName());
        existanceStudent.setAge(student.getAge());
        studentRepository.save(existanceStudent);
        return existanceStudent;

    }
    public Student removeStudent(Long id) {
        logger.info("Был вызван метод removeStudent");
        Student existanceStudent = studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return existanceStudent;
    }

    public List<Student> findStudentByAge(int age) {
        logger.info("Был вызван метод findStudentByAge");
        return studentRepository.findAll()
                .stream()
                .filter (e->e.getAge() == age)
                .collect(toList());
    }
    public List<String> getAllStudentsStartWitchH(){
        logger.info("Был вызван метод getAllStudentsStartWitchH");
        String startsimbol = "H";
        return studentRepository.findAll()
                .stream()
                .map(student -> student.getName().toUpperCase())
                .filter(name->name.startsWith(startsimbol.toUpperCase()))
                .sorted()
                .collect(toList());
    }
    public Collection<Student> findAllByAgeBetween(Integer min, Integer max){
        logger.info("Был вызван метод findAllByAgeBetween");
        return studentRepository.findAllByAgeBetween(min, max);
    }
    public Faculty getFacultyByStudentId(Long id) {
        logger.info("Был вызван метод getFacultyByStudentId");
        return studentRepository.findById(id).get().getFaculty();
    }
    public List<Student> getByFacultyId(Long facultyId) {
        logger.info("Был вызван метод getByFacultyId");
        return studentRepository.findByFacultyId(facultyId);
    }

    public Integer getCount(){
        logger.info("Был вызван метод getCount");
        return studentRepository.getCount();
    }

    public Double getAvgAge(){
        logger.info("Был вызван метод getAvgAge");
        return studentRepository.getAvgAge();
    }
    public Double getAvgAgeToStream(){
        logger.info("Был вызван метод getAvgAgeToStream");
        return studentRepository.findAll()
                .stream()
                .mapToDouble(student->(double) student.getAge())
                .average()
                .orElse(0);
    }
    public List<Student> getLastFiveStudent(){
        logger.info("Был вызван метод getLastFiveStudent");
        return studentRepository.getLastFiveStudent();
    }

    public void findAllStudentsParallel(){
        List<Student> students = studentRepository.findAll();
        printStudent(students.get(0));
        printStudent(students.get(1));
        Thread tread1 =new Thread(()->{

            printStudent(students.get(2));
            printStudent(students.get(3));
        });tread1.start();
        Thread tread2 =new Thread(()->{

            printStudent(students.get(4));
            printStudent(students.get(5));
        });tread2.start();
        Thread tread3 =new Thread(()->{

        printStudent(students.get(6));
        });tread3.start();

    }

    public void findAllSynchronized() {
        List<Student> students = studentRepository.findAll();
        printStudentSync(students.get(0));
        printStudentSync(students.get(1));
        Thread tread1 =new Thread(()->{

            printStudentSync(students.get(2));
            printStudentSync(students.get(3));
        });tread1.start();
        Thread tread2 =new Thread(()->{

            printStudentSync(students.get(4));
            printStudentSync(students.get(5));
        });tread2.start();
        Thread tread3 =new Thread(()->{

            printStudentSync(students.get(6));
        });tread3.start();


    }

    public void printStudent(Student student) {
        logger.info(Thread.currentThread()+""+student);
    }
    public synchronized void  printStudentSync(Student student) {
        logger.info(Thread.currentThread()+""+student);}
}
