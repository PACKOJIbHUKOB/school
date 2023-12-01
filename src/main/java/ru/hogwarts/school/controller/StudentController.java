package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;


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
    public Collection<Student> findStudent (@RequestParam(required = false)Integer min,
                                            @RequestParam(required = false)Integer max,
                                            @RequestParam(required = false) Integer age){
        if (min != null&&max!=null) {
            return studentService.findAllByAgeBetween(min, max);
        }
        if (age != null) {
            return studentService.findStudentByAge(age);
        }
        return studentService.getAllStudents();
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
    @GetMapping("/faculty-by-student-id")
    public Faculty getFacultyByStudentId(@RequestParam Long id) {
        return studentService.getFacultyByStudentId(id);
    }
}
