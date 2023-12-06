package ru.hogwarts.school.webMvcTest;



import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.AvatarRepository;
import ru.hogwarts.school.repositiries.FacultyRepository;
import ru.hogwarts.school.repositiries.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class SchoolAplicationStudentTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AvatarRepository avatarRepository;
    @SpyBean
    private FacultyService facultyService;
    @SpyBean
    private StudentService studentService;
    @SpyBean
    private AvatarService avatarService;
    @InjectMocks
    private StudentController studentController;


    @Test
    public void StudentCreateTest() throws Exception {
        final String name = "test1";
        final Integer age = 1;
        final String name1 = "test2";
        final Integer age1 = 2;
        final Long id = 1L;

        JSONObject objectStudent = new JSONObject();
        objectStudent.put("name", name);
        objectStudent.put("age", age);

        JSONObject objectStudent2 = new JSONObject();
        objectStudent2.put("name", name1);
        objectStudent2.put("age", age1);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student") //URL запрос
                        .content(objectStudent.toString())  //передача body
                        .contentType(MediaType.APPLICATION_JSON) // перевод тела в JSON
                        .accept(MediaType.APPLICATION_JSON)) // не помню для чего
                .andExpect(status().isOk()) //проверка статуса
                .andExpect(jsonPath("$.id").value(id)) // сравнение id
                .andExpect(jsonPath("$.name").value(name)) // сравнение name
                .andExpect(jsonPath("$.age").value(age));// сравнение age
    }

    @Test
    public void StudentFindTest() throws Exception {
        final String name = "test1";
        final Integer age = 1;
        final Long id = 1L;

        JSONObject objectStudent = new JSONObject();
        objectStudent.put("name", name);
        objectStudent.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/"+id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }
    @Test
    public void StudentEditTest() throws Exception {
        final String name = "test1";
        final Integer age = 1;
        final String name1 = "test2";
        final Integer age1 = 2;
        final Long id = 1L;


        JSONObject objectStudent2 = new JSONObject();
        objectStudent2.put("name", name1);
        objectStudent2.put("age", age1);
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/1")
                        .content(objectStudent2.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.age").value(age1));
    }
    @Test
    public void StudentDeleteTest() throws Exception {
        final String name = "test1";
        final Integer age = 1;
        final Long id = 1L;

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }
}

