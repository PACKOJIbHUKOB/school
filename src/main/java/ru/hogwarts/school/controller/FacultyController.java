package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
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
    public Collection<Faculty> getAll(){
        return facultyService.getAllFaculties();
    }
    @GetMapping(params = "color")
    public Collection<Faculty> findFacultyByColor(@RequestParam String color){
        return facultyService.findFacultyByColor(color);
    }
    @PostMapping
    public Faculty creatFaculty (@RequestBody Faculty faculty){
        return facultyService.creatNewFaculty(faculty);
    }
    @PutMapping("{id}")
    public Faculty editFaculty(@PathVariable long id,@RequestBody Faculty faculty) {
        return facultyService.editFaculty(id,faculty);

    }
    @DeleteMapping("{id}")
    public Faculty removeFaculty(@PathVariable long id){
        return facultyService.removeFaculty(id);
    }
}