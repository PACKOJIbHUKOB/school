package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FacultyExceptionNotFound extends RuntimeException {
    public FacultyExceptionNotFound() {
    }

    public FacultyExceptionNotFound(String message) {
        super(message);
    }
}
