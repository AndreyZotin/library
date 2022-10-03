package LIb.project.Contrrollers;

import LIb.project.DAO.PersonDao;
import LIb.project.Models.Person;
import LIb.project.Util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao;
    private final PersonValidator personValidator;

    public PeopleController(PersonDao personDao, PersonValidator personValidator) {
        this.personDao = personDao;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {    //poluchaem vsex people iz DAO
        model.addAttribute("people", personDao.index());
        return "people/index";     //отпралем в index.html список из людей под ключом people
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) { //poluchim 1 cheloveka po id iz DAO i peredadimna otobrozenie d view
        model.addAttribute("person", personDao.show(id));
        model.addAttribute("book", personDao.getBookByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDao.show(id));
        return "people/edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";

        personDao.save(person);
        return "redirect:/people";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";

        personDao.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDao.delete(id);
        return "redirect:/people";
    }
}