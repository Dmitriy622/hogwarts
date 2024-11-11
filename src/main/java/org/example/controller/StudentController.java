package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.model.Faculty;
import org.example.model.Student;
import org.example.service.StudentService;

import java.util.List;

@Tag(name = "Студенты", description = "Эндпоинты для работы со студентами")
@RequestMapping("/student")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @Operation(summary = "Создание студента")
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление информации о студенте")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление студента")
    public Student delete(@PathVariable Long id) {
        return studentService.delete(id);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable Long id) {
        return studentService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение всех студентов")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping(params = {"age"})
    @Operation(summary = "Получение студентов определенного возраста")
    public ResponseEntity<List<Student>> getStudentsByAge(@RequestParam(required = false) Integer age) {
        List<Student> students = studentService.find(age);
        return ResponseEntity.ok(students);
    }

    @GetMapping(params = {"minAge", "maxAge"})
    @Operation(summary = "Получение студентов в диапазоне возрастов")
    public ResponseEntity<List<Student>> getStudentsByAgeRange(@RequestParam(required = false) Integer minAge,
                                                               @RequestParam(required = false) Integer maxAge) {
        List<Student> students = studentService.findByAgeBetween(minAge, maxAge);
        return ResponseEntity.ok(students);
    }

    @GetMapping(params = {"minAge"})
    @Operation(summary = "Получение студентов старше определенного возраста")
    public ResponseEntity<List<Student>> getStudentsByAgeGreaterThan(@RequestParam(required = false) Integer minAge) {
        List<Student> students = studentService.findByAgeGreaterThan(minAge);
        return ResponseEntity.ok(students);
    }

    @GetMapping(params = {"maxAge"})
    @Operation(summary = "Получение студентов младше определенного возраста")
    public ResponseEntity<List<Student>> getStudentsByAgeLessThan(@RequestParam(required = false) Integer maxAge) {
        List<Student> students = studentService.findByAgeLessThan(maxAge);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/faculty")
    public Faculty findFaculty(@PathVariable Long id) {
        return studentService.findFaculty(id);
    }
}