package ir.online.bookstore.exception;

import java.text.MessageFormat;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String input) {
        super(MessageFormat.format("Could not find the Category with this details: {0}",input));
    }
}
