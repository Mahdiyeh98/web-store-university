//package ir.online.bookstore.controller;
//
//import com.google.gson.Gson;
//import ir.online.bookstore.dao.BookDAO;
//import ir.online.bookstore.domain.Books;
//import ir.online.bookstore.service.ConvertBook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/get")
//public class GetEntity {
//    @Autowired
//    private Gson gson;
//    @Autowired
//    BookDAO bookDAO;
//    @Autowired
//    ConvertBook convertBook;
//
//    @GetMapping("/entity")
//    public String getEntity()  {
//        List<Books> all = bookDAO.findAll();
//        ArrayList<BooksDTO> booksDTOS = convertBook.covertBooks(all);
//        return gson.toJson(booksDTOList);
//    }
//}
