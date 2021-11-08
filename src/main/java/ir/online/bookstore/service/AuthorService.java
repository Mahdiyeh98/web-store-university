package ir.online.bookstore.service;

import ir.online.bookstore.dao.AuthorDAO;
import ir.online.bookstore.domain.Authors;
import ir.online.bookstore.exception.AuthorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorService {
    private final AuthorDAO authorDAO;

    public AuthorService(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

//CREATE--------------------------------------------------------------------------------

    public List<Authors> createAuthor(List<Authors> authors) {
        for (Authors author:authors ) {
            authorDAO.save(author);
        }
        return authorDAO.findAll();
    }

    //READ------------------------------------------------------------------------------
    public List<Authors> getAuthors() {
        return authorDAO.findAll();
    }

    public Optional<Authors> getAuthorById(Long id) {
        return Optional.ofNullable(authorDAO.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(String.valueOf(id))));

    }

    public Optional<List<Authors>> getAuthorsByName(String name) {
        return Optional.ofNullable(authorDAO.findByName(name).orElse(null));
    }

    public Optional<List<Authors>> getAuthorsByLastName(String lastName) {
        return Optional.ofNullable(authorDAO.findByLastName(lastName).orElseThrow(() ->
                new AuthorNotFoundException(lastName)));
    }

    public Optional<List<Authors>> getAuthorsByNameOrLastName(String input) {
        return Optional.ofNullable(authorDAO.findByNameContainingOrLastNameContaining(input, input)
                .orElseThrow(() -> new AuthorNotFoundException(input)));
    }

    //UPDATE-----------------------------------------------------------------------------------------------
    public Optional<Authors> updateById(Long id, Authors author) {
        Optional<Authors> authorById = getAuthorById(id);
        authorById.get().setName(author.getName());
        authorById.get().setLastName(author.getLastName());
        authorDAO.save(authorById.get());
        return authorById;
    }

    //DELETE-----------------------------------------------------------------------------------------------
    public Optional<Authors> deleteById(Long id) {
        Optional<Authors> authorById = getAuthorById(id);
        authorDAO.delete(authorById.get());
        return authorById;
    }
}
