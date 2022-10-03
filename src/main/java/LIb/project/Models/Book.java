package LIb.project.Models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int id;

    @NotEmpty(message = "Графа не может быть пустой")
    @Size(min = 1, max = 100, message = "Название не может быть меньше 3 или больше 30 симолов")
    private String titleOfTheBook;

    @NotEmpty(message = "Графа не может быть пустой")
    @Size(min = 1, max = 100, message = "Имя автора не может быть меньше 3 или больше 50 симолов")
    private String author;

    @Min(value = 1, message = "Дата публикации не может быть младше 1 года")
    @Max(value = 2023, message = "Дата публикации не может быть старше 2023 года")
    private String yearOfPublication;

    public Book(String titleOfTheBook, String author, String yearOfPublication) {
        this.titleOfTheBook = titleOfTheBook;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleOfTheBook() {
        return titleOfTheBook;
    }

    public void setTitleOfTheBook(String titleOfTheBook) {
        this.titleOfTheBook = titleOfTheBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
