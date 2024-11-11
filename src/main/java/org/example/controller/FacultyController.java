package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.example.model.Faculty;
import org.example.model.Student;
import org.example.service.FacultyService;

import java.util.List;

@Tag(name = "Факультеты", description = "Эндпоинты для работы с факультетами")
@RequestMapping("/faculty")
@RestController
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    @Operation(summary = "Создание факультета")
    public Faculty create(@RequestBody Faculty faculty) {
        return facultyService.create(faculty);
    }

    @PutMapping("/{id}")
    public Faculty update(@PathVariable Long id, @RequestBody Faculty faculty) {
        return facultyService.update(id, faculty);
    }

    @DeleteMapping("/{id}")
    public Faculty delete(@PathVariable Long id) {
        return facultyService.delete(id);
    }

    @GetMapping("/{id}")
    public Faculty get(@PathVariable Long id) {
        return facultyService.get(id);
    }

    @GetMapping(params = {"color"})
    @Operation(summary = "Поиск факультета по цвету")
    public List<Faculty> findByColor(@RequestParam(required = false) String color) {
        return facultyService.findByColor(color);
    }
    @GetMapping(params = {"name"})
    @Operation(summary = "Поиск факультета по названию")
    public List<Faculty> findByName(@RequestParam(required = false) String name) {
        return facultyService.findByName(name);
    }
    @GetMapping(params = {"nameOrColor"})
    @Operation(summary = "Поиск факультета по названию или цвету")
    public List<Faculty> findByNameOrColor(@RequestParam(required = false) String nameOrColor) {
        return facultyService.findByNameOrColor(nameOrColor);
    }

    @GetMapping("/{id}/students")
    public List<Student> findStudents(@PathVariable Long id) {
        return facultyService.findStudents(id);
    }

}
