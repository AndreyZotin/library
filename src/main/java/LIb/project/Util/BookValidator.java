package LIb.project.Util;

import LIb.project.DAO.BookDao;
import LIb.project.Models.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private final BookDao bookDao;

    @Autowired
    public BookValidator(BookDao bookDao) {
        this.bookDao = bookDao;
    }


    @Override
    public boolean supports(Class<?> aClazz) {
        return Book.class.equals(aClazz);
    }

    @Override
    public void validate(Object ob, Errors errors) {
        Book book = (Book) ob;

    }
}
