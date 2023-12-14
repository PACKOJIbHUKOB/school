package ru.hogwarts.school.restTemplateTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }

    @Test
    void createStudentTest() {
        //Подготовка входных данных
        Student studentForCreate = new Student("test", 17);

        //Подготовка ожидаемого результата
        Student expectedStudent = new Student("test", 17);

        //Начало теста
        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForCreate, Student.class);
        assertThat(postedStudent).isNotNull();
        assertEquals(expectedStudent.getName(), postedStudent.getName());
        assertEquals(expectedStudent.getAge(), postedStudent.getAge());
    }

    @Test
    void findStudentTest() {

        Student studentForCreate = new Student("test", 17);

        //Начало теста
        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForCreate, Student.class);
        Student actualStudentOpt = this.restTemplate.getForObject("http://localhost:" + port + "/student/"+postedStudent.getId(), Student.class);
        assertEquals(postedStudent.getName(), actualStudentOpt.getName());
        assertEquals(postedStudent.getAge(), actualStudentOpt.getAge());
    }

    @Test
    void editStudentTest() {

        Student studentForCreate = new Student("test", 17);
        Student studentForEdit = new Student("twst2",25);
        Student studentExpected = new Student("twst2",25);

        //Начало теста
        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForCreate, Student.class);
        this.restTemplate.put("http://localhost:" + port + "/student/"+postedStudent.getId(),studentForEdit);
        Student actualStudentOpt = this.restTemplate.getForObject("http://localhost:" + port + "/student/"+postedStudent.getId(), Student.class);
        assertEquals(studentExpected.getName(), actualStudentOpt.getName());
        assertEquals(studentExpected.getAge(), actualStudentOpt.getAge());

    }

    @Test
    void deleteStudentTest() {
        //Подготовка входных данных
        Student studentForDelete = new Student("Иван", 20);
        Student expected = new Student();

        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForDelete, Student.class);
        this.restTemplate.delete("http://localhost:" + port + "/student" + postedStudent.getId());
        Student actual = this.restTemplate.getForObject("http://localhost:" + port + "/student" + postedStudent.getId(), Student.class);
        assertEquals(expected,actual);
    }
}
