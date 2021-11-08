package ir.online.bookstore.exception;

import java.text.MessageFormat;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String input) {
        super(MessageFormat.format("Could not find the BOOK with this details: {0}",input));
    }

}
