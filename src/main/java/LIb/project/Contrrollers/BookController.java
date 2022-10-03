package LIb.project.Contrrollers;

import LIb.project.DAO.BookDao;
import LIb.project.DAO.PersonDao;
import LIb.project.Models.Book;
import LIb.project.Models.Person;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDao bookDao;
    private final PersonDao personDao;

    public BookController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }


    @GetMapping()
    public String indexBook(Model model) {    //poluchaem vsex people iz DAO
        model.addAttribute("books", bookDao.indexBook());
        return "books/indexB";     //отпралем в index.html список из людей под ключом people
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person")Person person) {
        model.addAttribute("book", bookDao.showBook(id));

        Optional<Person> bookOwner = bookDao.getBookOwner(id);

        if(bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
            else
                model.addAttribute("person", personDao.index());

        return "books/showB";
    }

    @GetMapping("/newB")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/newB";
    }

    @GetMapping("/{id}/editB")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.showBook(id));
        return "books/editB";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                          BindingResult bindingResult) {

        if (bindingResult.hasErrors())

        return "books/newB";

        bookDao.saveBook(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult, @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "books/editB";

        bookDao.updateBook(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDao.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}release")
    public String release(@PathVariable("id") int id){
        bookDao.release(id);
        return "redirect:/books" + id;
    }

    @PatchMapping("/{id}assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson){
        bookDao.assign(id,selectedPerson);
        return "redirect:/books" + id;
    }



}
