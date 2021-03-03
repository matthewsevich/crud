package by.matusevich.crud2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchHouseFoundException extends RuntimeException {
    public NoSuchHouseFoundException(String message) {
        super(message);
    }

    public NoSuchHouseFoundException() {
    }
}

