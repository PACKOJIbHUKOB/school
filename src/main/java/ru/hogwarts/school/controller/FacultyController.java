package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;


@RestController
@RequestMapping(path = "faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @GetMapping ("{id}")
    public Faculty findFaculty (@PathVariable long id) {
        return facultyService.findFaculty(id);
    }

    @GetMapping
    public ResponseEntity findFaculty(@RequestParam(required = false) String param,@RequestParam (required = false)String color){
        if (param != null ) {
            return ResponseEntity.ok(facultyService.findAllByNameOrColorIgnoreCase(param));
        }
        if (color!=null) {
            return ResponseEntity.ok(facultyService.findFacultyByColor(color));
        }
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @PostMapping
    public Faculty creatFaculty (@RequestBody Faculty faculty){
        return facultyService.createNewFaculty(faculty);
    }
    @PutMapping("{id}")
    public Faculty editFaculty(@PathVariable long id,@RequestBody Faculty faculty) {
        return facultyService.editFaculty(id,faculty);

    }
    @DeleteMapping("{id}")
    public Faculty removeFaculty(@PathVariable long id){
        return facultyService.removeFaculty(id);
    }
    @GetMapping("/students-by-faculty-id")
    public List<Student> getStudentsByFacultyId(@RequestParam Long id) {
        return facultyService.getStudentsByFacultyId(id);
    }
}