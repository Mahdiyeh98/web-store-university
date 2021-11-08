package ir.online.bookstore.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Books")
@Table(name = "books")

public class Books implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(name = "isbn", unique = true, nullable = false, length = 15) // isbn is 13 digits
    private String ISBN;
    @Column(nullable = false, length = 10)// maximum 10,000,000Rial
    private Long price;
    @Column(nullable = false, length = 4)// example 1399
    private int year;
    @Column(length = 60)
    private String publisherName;
    @Column
    private String description;
    @Column(nullable = false, length = 4)
    private int quantity;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String picture;


    public Books() {
    }

    public Books(String name, String ISBN, Long price, int year, String publisherName, String description, int quantity, String picture) {
        this.name = name;
        this.ISBN = ISBN;
        this.price = price;
        this.year = year;
        this.publisherName = publisherName;
        this.description = description;
        this.quantity = quantity;
        this.picture = picture;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "books", fetch = FetchType.EAGER)
    private List<BookCategory> bookCategories;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "books", fetch = FetchType.LAZY)
    private List<AuthorBook> authors = new ArrayList<>();


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "books", fetch = FetchType.LAZY)
    private List<OrderBookDetails> orderBookDetails = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<BookCategory> getBookCategories() {
        return bookCategories;
    }

    public void setBookCategories(List<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }

    public List<AuthorBook> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorBook> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return year == books.year && quantity == books.quantity &&
                Objects.equals(name, books.name) &&
                Objects.equals(ISBN, books.ISBN) &&
                Objects.equals(price, books.price) &&
                Objects.equals(publisherName, books.publisherName) &&
                Objects.equals(description, books.description) &&
                Objects.equals(picture, books.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ISBN, price, year, publisherName, description, quantity, picture);
    }
}
