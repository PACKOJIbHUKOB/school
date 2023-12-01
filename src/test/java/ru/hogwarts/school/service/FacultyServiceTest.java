package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.UtilTest;
import ru.hogwarts.school.exception.FacultyExceptionNotFound;
import ru.hogwarts.school.model.Faculty;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static ru.hogwarts.school.UtilTest.*;

class FacultyServiceTest {
    private FacultyService facultyService = new FacultyService();

    @Test
    void creatNewFaculty_success() {
        //подготовка входных данных
        long id = ID1;
        String name = NAME_1;
        String color = COLOR_1;
        Faculty test = new Faculty(ID1, NAME_1, COLOR_1);
        //подготовка ожидаемого результата
        Faculty expected = faculty1();
        //начало теста
        Faculty actual = facultyService.creatNewFaculty(test);
        assertEquals(expected,actual);
    }

    @Test
    void findFaculty_success() {
        //подготовка входных данных
        long id = ID1;
        //подготовка ожидаемого результата
        Faculty expected = faculty1();
        //начало теста
        facultyService.creatNewFaculty(faculty1());
        Faculty actual = facultyService.findFaculty(id);
        assertEquals(expected,actual);
    }

    @Test
    void getAllFaculties_success() {
        //подготовка входных данных
        Faculty test1=faculty1();
        Faculty test2=(faculty2());
        Faculty test3=(faculty3());
        //подготовка ожидаемого результата
       List<Faculty> expected = getAllFaculty();
        //начало теста
        facultyService.creatNewFaculty(test1);
        facultyService.creatNewFaculty(test2);
        facultyService.creatNewFaculty(test3);
        List<Faculty> actual = facultyService.getAllFaculties();
        assertEquals(expected,actual);
    }

    @Test
    void editFaculty_success() {
        //подготовка входных данных
        Faculty test1 = faculty2();
        Faculty test2=faculty1();
        //подготовка входных данных
        Faculty expected = test2;
        //начало теста
        facultyService.creatNewFaculty(test1);
        Faculty actual = facultyService.editFaculty(ID1,test2);
        assertEquals(expected,actual);
    }
    @Test
    void findFaculty_Exception() {
        //подготовка входных данных
        Long id = ID1;
        //подготовка входных данных
        String exceptionMessage = "факультета с данным id "+id+" ненайдено";

        //начало теста
        Exception exception = assertThrows(
                FacultyExceptionNotFound.class,
                () -> facultyService.findFaculty(id)
        );
        assertEquals(exceptionMessage, exception.getMessage());


    }

    @Test
    void removeFaculty_success() {
        //подготовка входных данных
        //подготовка ожидаемого результата
        Faculty expected = faculty1();
        //начало теста
        facultyService.creatNewFaculty(faculty1());
        Faculty actual = facultyService.removeFaculty(ID1);
        assertEquals(expected,actual);

    }

    @Test
    void findFacultyByColor_success() {
        //подготовка входных данных
        Faculty test1=faculty1();
        Faculty test2=faculty2();
        Faculty test3=faculty3();
        //подготовка ожидаемого результата
        List<Faculty> expected = new ArrayList<>();
        expected.add(test1);
        //начало теста
        facultyService.creatNewFaculty(test1);
        facultyService.creatNewFaculty(test2);
        facultyService.creatNewFaculty(test3);
        List<Faculty> actual = facultyService.findFacultyByColor(COLOR_1);
        assertEquals(expected,actual);
    }
}