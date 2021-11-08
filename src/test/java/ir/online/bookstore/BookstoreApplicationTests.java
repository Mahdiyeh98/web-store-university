//package ir.online.bookstore;
//
//
//import ir.online.bookstore.dao.BookDAO;
//import ir.online.bookstore.dao.CategoryDAO;
//import ir.online.bookstore.domain.Books;
//import ir.online.bookstore.domain.Categories;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//
//@SpringBootTest
//class BookstoreApplicationTests {
//
//    @Autowired
//    BookDAO bookDAO;
//
//    @Autowired
//    CategoryDAO categoryDAO;
//
//    @Test
//    void contextLoads() {
//        Books books = new Books();
//        books.setName("test");
//        books.setDescription("test asdg usay gduy asgdiuasgd y");
//        books.setPrice((long) 24352);
//        books.setISBN("123456789");
//        books.setYear(1378);
//        books.setQuantity(111);
//
//        bookDAO.save(books);
//
//        Categories categories1 =new Categories();
//        categories1.setName("mehdi 1");
//
//        Categories categories2 =new Categories();
//        categories2.setName("mehdi 2");
//
//        Categories categories3 =new Categories();
//        categories3.setName("mehdi 3");
//
//        categoryDAO.saveAll(Arrays.asList(categories1,
//                categories2,categories3));
//
//        books.getCategories().addAll(Arrays.asList(categories1,
//                categories2,categories3));
//
//        bookDAO.save(books);
//
//    }
//
//}
