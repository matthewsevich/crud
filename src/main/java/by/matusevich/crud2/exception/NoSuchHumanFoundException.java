package by.matusevich.crud2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchHumanFoundException extends RuntimeException {
    public NoSuchHumanFoundException(String message) {
        super(message);
    }

    public NoSuchHumanFoundException() {
    }
}
