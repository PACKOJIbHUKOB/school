package ru.hogwarts.school;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Arrays;
import java.util.List;

public class UtilTest {

    static public final String NAME_1 = "Иван";
    static public final String COLOR_1 = "orange";
    static public final int AGE_1 = 12;

    static public final String NAME_2 = "Николай";
    static public final String COLOR_2 = "grin";
    static public final int AGE_2 = 13;

    static public final String NAME_3 = "Сергей";
    static public final String COLOR_3 = "grey";
    static public final int AGE_3 = 14;

    static public final long ID1 = 1;
    static public final long ID2=2;
    static public final long ID3=3;

    public static Faculty faculty1(){
        return new Faculty(ID1,NAME_1,COLOR_1);
    }
    public static Faculty faculty2() {
        return new Faculty(ID2, NAME_2, COLOR_2);
    }
    public static Faculty faculty3() {
        return new Faculty(ID3, NAME_3, COLOR_3);
    }

    public static List<Faculty> getAllFaculty() {
        return Arrays.asList(faculty1(), faculty2(), faculty3());
    }

    public static Student student1() {
        return new Student(ID1, NAME_1, AGE_1);
    }
    public static Student student2() {
        return new Student(ID2, NAME_2, AGE_2);
    }
    public static Student student3() {
        return new Student(ID3, NAME_3, AGE_3);
    }
    public static List<Student> getAllStudent(){
        return Arrays.asList(student1(),student2(),student3());
    }
}
