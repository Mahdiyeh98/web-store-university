package ir.online.bookstore.exception;

import java.text.MessageFormat;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(String input) {
        super(MessageFormat.format("Could not find the AUTHOR with this details: {0}",input));
    }
}
