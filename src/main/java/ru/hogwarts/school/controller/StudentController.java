package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("{id}")
    public Optional<Student> findStudent (@PathVariable long id){
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
    public Student creatStudent (@RequestBody StudentDto student) {
        return studentService.createNewStudent(student.getName(), student.getAge());
    }
    @PutMapping("{id}")
    public Student editStudent (@PathVariable long id,@RequestBody StudentDto student){
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

    @GetMapping("/count")
    public Integer getCount() {
        return studentService.getCount();
    }
    @GetMapping("/avg-age")
    public Double getAvgAge(){
        return studentService.getAvgAge();
    }

    @GetMapping("/start-name-H")
    public List<String> getAllStudentsStartWitchH(){
        return studentService.getAllStudentsStartWitchH();
    }

    @GetMapping("/last-five-student")
    public List<Student> getLastFiveStudent() {
        return studentService.getLastFiveStudent();
    }

    @GetMapping("/avg-age-sream")
    public Double getAvgAgeToStream(){
        return studentService.getAvgAgeToStream();
    }

    @GetMapping("/print-parallel")
    public void findAllStudentsParallel(){
        studentService.findAllStudentsParallel();
    }
    @GetMapping("/print-synchronized")
    public void findAllSynchronized(){
        studentService.findAllSynchronized();
    }
}
