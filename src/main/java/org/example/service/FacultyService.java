package org.example.service;

import org.example.exception.FacultyNotFoundException;
import org.example.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FacultyService {
    private final Map<Long, Faculty> map = new HashMap<>();

    private long idGenerator = 1;

    public Faculty create(Faculty faculty) {
        faculty.setId(idGenerator++);
        map.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty update(long id, Faculty faculty) {
        if (!map.containsKey(id)) {
            throw new FacultyNotFoundException(id);
        }
        Faculty old = map.get(id);
        old.setName(faculty.getName());
        old.setColor(faculty.getColor());
        return old;
    }

    public Faculty delete(long id) {
        if (!map.containsKey(id)) {
            throw new FacultyNotFoundException(id);
        }
        return map.remove(id);
    }

    public Faculty get(long id) {
        if (!map.containsKey(id)) {
            throw new FacultyNotFoundException(id);
        }
        return map.get(id);
    }
    public List<Faculty> find(String color) {
        return map.values().stream()
                .filter(student -> student.getColor() == color)
                .collect(Collectors.toList());
    }
}
