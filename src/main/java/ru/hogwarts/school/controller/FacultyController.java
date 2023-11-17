package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping(path = "faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @GetMapping ("{id}")
    public ResponseEntity<Faculty> findFaculty (@PathVariable long id){
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
           return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(faculty);
    }
    @GetMapping
    public Collection<Faculty> getAll(){
        return facultyService.getAllFaculties();
    }
    @GetMapping("/findColor")
    public Collection<Faculty> findFacultyByColor(@RequestParam String color){
        return facultyService.findFacultyByColor(color);
    }
    @PostMapping
    public Faculty creatFaculty (@RequestBody Faculty faculty){
        return facultyService.creatNewFaculty(faculty);
    }
    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty){
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @DeleteMapping("{id}")
    public Faculty removeFaculty(@PathVariable long id){
        return facultyService.removeFaculty(id);
    }
}
