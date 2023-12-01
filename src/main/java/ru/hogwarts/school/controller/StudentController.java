package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("{id}")
    public Student findStudent (@PathVariable long id){
        return studentService.findStudent(id);
    }
    @GetMapping
    public Collection<Student> getAllStudent (){
        return studentService.getAllStudents();
    }
    @GetMapping(params = "age")
    public Collection<Student> findStudentByAge (@RequestParam int age){
        return studentService.findStudentByAge(age);
    }

    @PostMapping
    public Student creatStudent (@RequestBody Student student) {
        return studentService.creatNewStudent(student);
    }
    @PutMapping("{id}")
    public Student editStudent (@PathVariable long id,@RequestBody Student student){
        return studentService.editStudent(id, student);
    }

    @DeleteMapping("{id}")
    public Student removeStudent(@PathVariable Long id) {
        return studentService.removeStudent(id);
    }
}
