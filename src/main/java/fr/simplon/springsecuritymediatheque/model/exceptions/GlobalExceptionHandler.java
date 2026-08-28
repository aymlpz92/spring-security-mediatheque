package fr.simplon.springsecuritymediatheque.model.exceptions;

import java.time.LocalDateTime;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.access.AccessDeniedException;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionData> handleBookNotFound(BookNotFoundException exception) {
        log.error("{}", exception.getMessage());

        ExceptionData exceptionData = customExceptionData(exception, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionData, HttpStatusCode.valueOf(exceptionData.getStatus()));
    }

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<ExceptionData> handleBookNotFound(EmailAlreadyExists exception) {
        log.error("{}", exception.getMessage());

        ExceptionData exceptionData = customExceptionData(exception, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionData, HttpStatusCode.valueOf(exceptionData.getStatus()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionData> handleAccessDenied(AccessDeniedException exception) {
        ExceptionData exceptionData = customExceptionData(exception, exception.getMessage(), LocalDateTime.now());
        exceptionData.setMessage("Accès refusé : rôle insuffisant");

        log.error("{}", exceptionData.getMessage());
        return new ResponseEntity<>(exceptionData, HttpStatusCode.valueOf(exceptionData.getStatus()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionData> handleAuthentication(AuthenticationException exception) {
        ExceptionData exceptionData = customExceptionData(exception, exception.getMessage(), LocalDateTime.now());
        exceptionData.setMessage("Erreur d'authentification : identifiants incorrectes");

        log.error("{}", exceptionData.getMessage());
        return new ResponseEntity<>(exceptionData, HttpStatusCode.valueOf(exceptionData.getStatus()));
    }


    private ExceptionData customExceptionData(Exception exception, String message, LocalDateTime timestamp) {
        ExceptionData exceptionData = new ExceptionData();
        switch (exception) {
            case BookNotFoundException bookNotFoundException -> exceptionData.setStatus(HttpStatus.NOT_FOUND.value());
            case EmailAlreadyExists emailAlreadyExists -> exceptionData.setStatus(HttpStatus.CONFLICT.value());
            case AccessDeniedException accessDeniedException -> exceptionData.setStatus(HttpStatus.FORBIDDEN.value());
            case AuthenticationException authenticationException -> exceptionData.setStatus(HttpStatus.UNAUTHORIZED.value());
            default -> exceptionData.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        exceptionData.setMessage(message);
        exceptionData.setTimestamp(timestamp);
        return exceptionData;
    }
}
