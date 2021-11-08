package ir.online.bookstore.controller;

import com.google.gson.Gson;
import ir.online.bookstore.domain.Person;
import ir.online.bookstore.dto.PersonCreateDTO;
import ir.online.bookstore.dto.PersonDTO;
import ir.online.bookstore.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    Gson gson;

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody PersonCreateDTO personCreate) {
        return new ResponseEntity<String>(gson.toJson(personService.save(personCreate)), HttpStatus.CREATED);
    }

    @GetMapping("/{nationalCode}")
    public ResponseEntity<String> getByNationalCode(@PathVariable String nationalCode) {
        Optional<Person> person = personService.getByNationalCode(nationalCode);
        String result ="";
        if (!person.isPresent()){
            result ="user not found";
        }else{
            result= gson.toJson(person.get().toDto());
        }
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{nationalCode}")
    public ResponseEntity<String> deleteByNationalCode(@PathVariable String nationalCode) {
        Boolean result = personService.deleteByNationalCode(nationalCode);
        String resultOfDelete = "";
        if (result) {
            resultOfDelete = "user deleted";
        } else resultOfDelete = "user not found";
        return new ResponseEntity<String>(gson.toJson(resultOfDelete), HttpStatus.OK);
    }
}
