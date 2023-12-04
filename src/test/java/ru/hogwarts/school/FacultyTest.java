package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;

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
        Long id = 1L;
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/faculty/"+id,String.class))
                .isNotNull();
    }



}
