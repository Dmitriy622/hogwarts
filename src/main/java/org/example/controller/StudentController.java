package org.example.controller;

import org.example.model.Student;
import org.example.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public Student delete(@PathVariable long id) {
        return studentService.delete(id);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable long id) {
        return studentService.get(id);
    }

    @GetMapping("/")
    public List<Student> find(@RequestParam int age){
        return studentService.find(age);
    }

}
