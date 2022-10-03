package LIb.project.DAO;

import LIb.project.Models.Book;
import LIb.project.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> indexBook() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }


    public Book showBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id_book=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findFirst().orElse(null);
    }

    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book (authr, titleOfTheBook, yearOfPublication) VALUES(?,?,?)",
                book.getAuthor(), book.getTitleOfTheBook(), book.getYearOfPublication());
    }

    public void updateBook(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET author=?,titleOfTheBook=?,yearOfPublication=? WHERE id_book=?",
                updateBook.getAuthor(), updateBook.getTitleOfTheBook(), updateBook.getYearOfPublication(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id_book=?", id);
    }


    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.id_person=Person.id WHERE Book.id_book=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);

    }

    public void assign(int id, Person selectedPerson){
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedPerson.getId(),id);
    }
}

