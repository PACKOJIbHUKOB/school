package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;

import ru.hogwarts.school.UtilTest;

import ru.hogwarts.school.exception.StudentExceptionNotFound;

import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.hogwarts.school.UtilTest.*;

class StudentServiceTest {

    private StudentService studentService = new StudentService();

    @Test
    void creatNewStudent_success() {
        //подготовка входных данных
        long id = ID1;
        String name = NAME_1;
        int age = AGE_1;
        Student test = new Student(id,name,age);
        //подготовка ожидаемого результата
        Student expected = UtilTest.student1();
        //начало теста
        Student actual = studentService.creatNewStudent(test);
        assertEquals(expected,actual);
    }

    @Test
    void findStudent_success() {
        //подготовка входных данных
        long id = ID1;
        //подготовка ожидаемого результата
        Student expected = student1();
        //начало теста
        studentService.creatNewStudent(student1());
        Student actual = studentService.findStudent(id);
        assertEquals(expected,actual);
    }

    @Test
    void getAllStudents_success() {
        //подготовка входных данных
        Student test1=student1();
        Student test2=student2();
        Student test3=student3();
        //подготовка ожидаемого результата
        List<Student> expected = getAllStudent();
        //начало теста
        studentService.creatNewStudent(test1);
        studentService.creatNewStudent(test2);
        studentService.creatNewStudent(test3);
        List<Student> actual = studentService.getAllStudents();
        assertEquals(expected,actual);
    }

    @Test
    void editStudent_success() {
        //подготовка входных данных
        Student test1 = student2();
        Student test2=student1();
        //подготовка входных данных
        Student expected = test2;
        //начало теста
        studentService.creatNewStudent(test1);
        Student actual = studentService.editStudent(ID1,test2);
        assertEquals(expected,actual);
    }
    @Test
    void findStudent_Exception() {
        //подготовка входных данных
        Long id = ID1;
        //подготовка входных данных
        String exceptionMessage = "студент c таким id "+id+" не найден";

        //начало теста
        Exception exception = assertThrows(
                StudentExceptionNotFound.class,
                () -> studentService.findStudent(id)
        );
        assertEquals(exceptionMessage, exception.getMessage());


    }

    @Test
    void removeStudent_success() {
        //подготовка входных данных
        //подготовка ожидаемого результата
        Student expected = student1();
        //начало теста
        studentService.creatNewStudent(student1());
        Student actual = studentService.removeStudent(ID1);
        assertEquals(expected,actual);

    }

    @Test
    void findStudentByColor_success() {
        //подготовка входных данных
        Student test1=student1();
        Student test2=student2();
        Student test3=student3();
        //подготовка ожидаемого результата
        List<Student> expected = new ArrayList<>();
        expected.add(test1);
        //начало теста
        studentService.creatNewStudent(test1);
        studentService.creatNewStudent(test2);
        studentService.creatNewStudent(test3);
        List<Student> actual = studentService.findStudentByAge(AGE_1);
        assertEquals(expected,actual);
    }
}