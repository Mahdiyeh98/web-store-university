//package ir.online.bookstore.service;
//
//import ir.online.bookstore.domain.Books;
//import ir.online.bookstore.dto.BooksDTO;
//import ir.online.bookstore.dto.BooksInputDTO;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ConvertBook {
//
//
//    public ArrayList<BooksInputDTO> covertBooks(List<Books> booksList) {
//
//        ArrayList<BooksInputDTO> booksDTOList=new ArrayList<>();
//
//        for (int i = 0; i < booksList.size(); i++) {
//
//            BooksInputDTO booksDTO = new BooksInputDTO();
//
//            booksDTO.setISBN(booksList.get(i).getISBN());
//            booksDTO.setName(booksList.get(i).getName());
//            booksDTO.setPrice(booksList.get(i).getPrice());
//            booksDTO.setYear(booksList.get(i).getYear());
//            booksDTO.setPublisherName(booksList.get(i).getPublisherName());
//            booksDTO.setDescription(booksList.get(i).getDescription());
//            booksDTO.setQuantity(booksList.get(i).getQuantity());
//            booksDTO.setPicture(booksList.get(i).getPicture());
//            booksDTO.setAuthorName(booksList.get(i).getAuthors().get(0).getAuthors().getName());
//            booksDTO.setAuthorLastName(booksList.get(i).getAuthors().get(0).getAuthors().getLastName());
////            booksDTO.setCategoryName(booksList.get(i).getBookCategories().get(0).getCategory().getName());
//
//            booksDTOList.add(booksDTO);
//        }
//        return booksDTOList;
//    }
//}
