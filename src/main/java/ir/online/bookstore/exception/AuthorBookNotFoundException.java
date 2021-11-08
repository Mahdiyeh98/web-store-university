package ir.online.bookstore.exception;

import java.text.MessageFormat;

public class AuthorBookNotFoundException extends RuntimeException {

    public AuthorBookNotFoundException(String input) {
        super(MessageFormat.format("Could not find the AUTHORBOOK with this details: {0}",input));
    }
}
