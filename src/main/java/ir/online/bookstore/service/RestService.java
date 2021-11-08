package ir.online.bookstore.service;

import com.google.gson.Gson;
import ir.online.bookstore.domain.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RestService {
    final RestTemplate restTemplate;

    @Autowired
    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Books> getBookFromJson() throws JSONException {
        String url = "here you should put link"; //TODO
        String json = restTemplate.getForObject(url, String.class);
        JSONArray jsonArray = new JSONArray(json);
        Gson gson = new Gson();
        List<Books> bookList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Books book = gson.fromJson(String.valueOf(jsonArray.get(i)), Books.class);
            book.setName(book.getName());
            book.setPicture(book.getPicture());
            book.setISBN(book.getISBN());
            book.setDescription(book.getDescription());
            book.setPrice(book.getPrice());
            book.setQuantity(book.getQuantity());
            book.setPublisherName(book.getPublisherName());
            book.setYear(book.getYear());
//            book.setAuthorBooks(book.getAuthorBooks());
//            book.setBookCategories(book.getBookCategories());
            bookList.add(book);
        }
        for (Books book : bookList) {
            System.out.println("\nbook = " + book);
        }
        return bookList;

    }

    // TODO اینجا باید تست کنیم ببینیم تابع بالایی بهتره یا پایینی .. پایینی کوتاه تره اگه اجرا بشه بهتره
    public List<Books> getBookFromJson2() {
        String url = "here you should put link"; //TODO
        Gson gson = new Gson();
        String json = restTemplate.getForObject(url, String.class);
        Books[] bookArray = gson.fromJson(json, Books[].class);
        for (Books book : bookArray) {
            System.out.println("book = " + book);
        }
        return Arrays.asList(bookArray);
    }
}
/*
    More information for Second function:
        https://howtodoinjava.com/gson/gson-parse-json-array/
 */