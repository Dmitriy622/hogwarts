package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FacultyNotFoundException extends RuntimeException{
    private final long id;

    public FacultyNotFoundException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Нет такого факультета с id = %d , сходи еще поищи".formatted(id);
    }
}
