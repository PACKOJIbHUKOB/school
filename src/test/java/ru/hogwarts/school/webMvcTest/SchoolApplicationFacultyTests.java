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
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
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
public class SchoolApplicationFacultyTests {

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
	private FacultyController facultyController;


	@Test
	public void FacultyCreateTest () throws Exception {
        final String name = "test1";
        final String color = "test1";
        final Long id = 1L;


        JSONObject objectFaculty = new JSONObject();
        objectFaculty.put("name", name);
        objectFaculty.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(objectFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

        @Test
        public void FacultyFindTest() throws Exception {
            final String name = "test1";
            final String color = "test1";
            final Long id = 1L;

            Faculty faculty = new Faculty();
            faculty.setId(id);
            faculty.setName(name);
            faculty.setColor(color);
            when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
            when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

            mockMvc.perform(MockMvcRequestBuilders
                            .get("/faculty/1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id))
                    .andExpect(jsonPath("$.name").value(name))
                    .andExpect(jsonPath("$.color").value(color));
        }
    @Test
    public void FacultyEditTest() throws Exception {
        final String name = "test1";
        final String color = "test1";
        final String name1 = "test2";
        final String color1 = "test2";
        final Long id = 1L;

        JSONObject objectFaculty2 = new JSONObject();
        objectFaculty2.put("name", name1);
        objectFaculty2.put("color", color1);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/1")
                        .content(objectFaculty2.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.color").value(color1));
    }
    @Test
    public void FacultyDeleteTest() throws Exception {
        final String name = "test1";
        final String color = "test1";
        final Long id = 1L;

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

}
