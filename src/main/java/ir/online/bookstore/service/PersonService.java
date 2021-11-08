package ir.online.bookstore.service;

import ir.online.bookstore.dao.PersonDAO;
import ir.online.bookstore.domain.Person;
import ir.online.bookstore.dto.PersonCreateDTO;
import ir.online.bookstore.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonService {
    private final PersonDAO personDAO;

    @Autowired
    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public PersonDTO save(PersonCreateDTO person) {
        Optional<Person> user = personDAO.findByNationalCode(person.getNationalCode());
        if (user.isPresent()) {
            Person toPerson = person.toPerson(user.get().getId(), user.get().getOrders());
            personDAO.save(toPerson);
        } else {
             personDAO.save(person.toPerson(0L, null));
        }
        return person.toPerson(0L, null).toDto();
    }

    public Optional<Person> getByNationalCode(String nationalCode){
        return personDAO.findByNationalCode(nationalCode);
    }

    @Transactional
    public Boolean deleteByNationalCode(String nationalCode){
        Optional<Person> person = personDAO.findByNationalCode(nationalCode);
        Boolean result =false;
        if (person.isPresent()){
            personDAO.deleteById(person.get().getId());
            result=true;
        }
        return result;
    }
}
