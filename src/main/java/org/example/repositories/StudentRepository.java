package org.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByAge(int age);
    List<Student> findByAgeBetween(int minAge, int maxAge);
    List<Student> findByAgeGreaterThan(int minAge);
    List<Student> findByAgeLessThan(int maxAge);
    List<Student> findByFaculty_Id(Long faculty_id);
}
