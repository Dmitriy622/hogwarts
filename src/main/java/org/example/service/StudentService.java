package org.example.service;

import org.springframework.stereotype.Service;
import org.example.model.Faculty;
import org.example.exception.FacultyNotFoundException;
import org.example.exception.StudentNotFoundException;
import org.example.model.Student;
import org.example.repositories.FacultyRepository;
import org.example.repositories.StudentRepository;

import java.util.List;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {

        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }


    public Student create(Student student) {
        if (student.getFaculty() != null && student.getFaculty().getId() != null) {
            Faculty faculty = facultyRepository.findById(student.getFaculty().getId())
                    .orElseThrow(() -> new FacultyNotFoundException(student.getFaculty().getId()));
            student.setFaculty(faculty);
        }
        return studentRepository.save(student);
    }

    public Student update(long id, Student student) {
        return studentRepository.findById(id)
                .map(oldStudent -> {
                    oldStudent.setName(student.getName());
                    oldStudent.setAge(student.getAge());
                    if (student.getFaculty() != null && student.getFaculty().getId() != null) {
                        Faculty faculty = facultyRepository.findById(student.getFaculty().getId())
                                .orElseThrow(() -> new FacultyNotFoundException(student.getFaculty().getId()));
                        oldStudent.setFaculty(faculty);
                    }
                    return studentRepository.save(oldStudent);
                })
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student delete(long id) {
        return studentRepository.findById(id)
                .map(student->{
                    studentRepository.delete(student);
                    return student;
                })
                .orElseThrow(()->new StudentNotFoundException(id));
    }

    public Student get(long id) {
        return studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException(id));
    }

    public List<Student> find(int age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> findByAgeBetween(int minAge,int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findByAgeGreaterThan(int minAge) {
        return studentRepository.findByAgeGreaterThan(minAge);
    }

    public List<Student> findByAgeLessThan(int maxAge) {
        return studentRepository.findByAgeLessThan(maxAge);
    }


    public Faculty findFaculty(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        return student.getFaculty();
    }
}
