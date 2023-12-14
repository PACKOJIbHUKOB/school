package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentExceptionNotFound extends RuntimeException{


    public StudentExceptionNotFound() {
        super();
    }

    public StudentExceptionNotFound(String message) {
        super(message);
    }
}
