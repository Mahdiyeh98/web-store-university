package ir.online.bookstore.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorsDTO implements Serializable {
    private String name;
    private String lastName;
}
