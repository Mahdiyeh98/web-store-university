package ir.online.bookstore.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import ir.online.bookstore.domain.Authors;
import ir.online.bookstore.dto.AuthorsDTO;
import ir.online.bookstore.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    private final Gson gson;
    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public AuthorController(AuthorService authorService, Gson gson) {
        this.authorService = authorService;
        this.gson = gson;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAuthor(@RequestBody final List<AuthorsDTO> authorsDTOS) {
        ArrayList authors = new ArrayList();
        for (AuthorsDTO authorsDTO : authorsDTOS
        ) {
            Authors author = modelMapper.map(authorsDTO, Authors.class);
            authors.add(author);
        }

        String toJson = gson.toJson(authorService.createAuthor(authors));
        return ResponseEntity.ok(toJson);
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllAuthors() {
        List<Authors> authorList = authorService.getAuthors();
        JsonArray jsonArray = new JsonArray();
        for (Authors authors : authorList)
            jsonArray.add(gson.toJson(authors));

        return ResponseEntity.ok(gson.toJson(jsonArray));
    }

    @GetMapping("/search/{id}")//TODO
    public ResponseEntity<String> getAuthorById(@PathVariable final Long id) {
        Optional<Authors> authorById = authorService.getAuthorById(id);
        Authors authors = authorById.get();
        AuthorsDTO authorDTO = modelMapper.map(authors, AuthorsDTO.class);
        return ResponseEntity.ok(gson.toJson(authorDTO));
    }

    @GetMapping("/search/name/{input}")
    public ResponseEntity<String> getAuthors(@PathVariable final String input) {
        Optional<List<Authors>> authorsByNameOrLastName = authorService.getAuthorsByNameOrLastName(input);
        List<Authors> authorsList = new ArrayList<>();
        for (Authors authors : authorsByNameOrLastName.get()) {
            authorsList.add(authors);
        }

        List<AuthorsDTO> authorsDTOList = new ArrayList<>();
        for (Authors authors : authorsList) {
            authorsDTOList.add(modelMapper.map(authors, AuthorsDTO.class));
        }

        return ResponseEntity.ok(gson.toJson(authorsDTOList));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAuthorById(@PathVariable final Long id, @RequestBody AuthorsDTO authorDTO) {
        Authors author = modelMapper.map(authorDTO, Authors.class);
        Optional<Authors> updatedAuthors = authorService.updateById(id, author);
        return ResponseEntity.ok(gson.toJson(updatedAuthors));
    }

//    @PutMapping("/update/{input}")
//    public ResponseEntity<String> updateAuthor(@PathVariable final String input, AuthorsDTO authorDTO) {
//        Authors author = modelMapper.map(authorDTO, Authors.class);
//        Optional<Authors> updatedAuthors = authorService.updateById(author.getId(), author);
//        return ResponseEntity.ok(gson.toJson(updatedAuthors));
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable final Long id) {
        Optional<Authors> deletedAuthors = authorService.deleteById(id);
        return ResponseEntity.ok(gson.toJson(deletedAuthors));
    }

//    @DeleteMapping("delete/{input}")
//    public ResponseEntity<String> deleteAuthor(@PathVariable final String input, AuthorsDTO authorDTO) {
//        Authors author = modelMapper.map(authorDTO, Authors.class);
//        Optional<Authors> deletedAuthor = authorService.deleteById(author.getId(), author);
//        return ResponseEntity.ok(gson.toJson(deletedAuthor));
//    }
}



















