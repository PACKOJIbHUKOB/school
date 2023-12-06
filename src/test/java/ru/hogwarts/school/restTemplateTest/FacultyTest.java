package ru.hogwarts.school.restTemplateTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyTest {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception{
        Assertions.assertThat(facultyController).isNotNull();
    }
    @Test
    public void testCreateFaculty () throws Exception{
         String name = "test";
         String color = "test";
        Faculty facultyForCreate = new Faculty(name,color);

        Faculty expectedFaculty = new Faculty(name,color);

        Faculty actual=this.restTemplate.postForObject("http://localhost:"+port+"/faculty",facultyForCreate, Faculty.class);
        assertThat(actual).isNotNull();
        assertEquals(expectedFaculty.getName(),actual.getName());
        assertEquals(expectedFaculty.getColor(),actual.getColor());

    }
    @Test
    public void testFindFaculty () throws Exception{
        String name = "test";
        String color = "test";
        Faculty facultyForCreate = new Faculty(name,color);

        Faculty postedFaculty=this.restTemplate.postForObject("http://localhost:"+port+"/faculty",facultyForCreate, Faculty.class);
        Faculty actualFacultyOpt = this.restTemplate.getForObject("http://localhost:" + port + "/faculty/" + postedFaculty.getId(), Faculty.class);
        assertEquals(postedFaculty.getName(), actualFacultyOpt.getName());
        assertEquals(postedFaculty.getColor(), actualFacultyOpt.getColor());

    }

    @Test
    public void testEditFaculty () throws Exception{
        String name = "test";
        String color = "test";
        String name2 = "test2";
        String color2 = "test2";
        Faculty facultyForCreate = new Faculty(name,color);
        Faculty facultyForEdit = new Faculty(name2, color2);
        Faculty facultyExpected = new Faculty(name2,color2);

        Faculty postedFaculty=this.restTemplate.postForObject("http://localhost:"+port+"/faculty",facultyForCreate, Faculty.class);
        this.restTemplate.put("http://localhost:" + port + "/faculty/" + postedFaculty.getId(),facultyForEdit);
        Faculty actualFacultyOpt = this.restTemplate.getForObject("http://localhost:" + port + "/faculty/" + postedFaculty.getId(), Faculty.class);
        assertEquals(facultyExpected.getName(), actualFacultyOpt.getName());
        assertEquals(facultyExpected.getColor(), actualFacultyOpt.getColor());
    }
    @Test
    public void testDeleteFaculty () throws Exception {
        String name = "test";
        String color = "test";
        Faculty facultyForCreate = new Faculty(name, color);
        Faculty expected = new Faculty();

        Faculty postedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", facultyForCreate, Faculty.class);
        this.restTemplate.delete("http://localhost:" + port + "/faculty/" + postedFaculty.getId());
       Faculty actual= this.restTemplate.getForObject("http://localhost:" + port + "/faculty/" + postedFaculty.getId(),Faculty.class);
       assertEquals(expected,actual);

    }





}
