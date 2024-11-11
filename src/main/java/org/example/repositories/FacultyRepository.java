package org.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
List<Faculty> findByColorIgnoreCase(String color);
List<Faculty> findByNameIgnoreCase(String name);
List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name,String color);
}
