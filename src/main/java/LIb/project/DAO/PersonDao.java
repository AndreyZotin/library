package LIb.project.DAO;

import LIb.project.Models.Book;
import LIb.project.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }


    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fio, dateOfBirth) VALUES(?,?)",
                person.getFio(), person.getDateOfBirth());
    }

    public void  update(int id, Person updatePerson){
        jdbcTemplate.update("UPDATE Person SET (fio, dateOfBirth), VALUES(?,?)",
                updatePerson.getFio(),updatePerson.getDateOfBirth(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE Person WHERE id=?", id);
    }

    public Optional<Person> getPersonByFullName(String fio){
        return jdbcTemplate.query("SELECT * FROM Person WHERE fio=?", new Object[]{fio},
                new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public List<Book> getBookByPersonId(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id_person=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class));
    }
}
