package LIb.project.Models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.crypto.Data;
import java.util.Date;

public class Person  {

    private int id;

    @NotEmpty(message = "Графа не может быть пустой")
    @Size(min = 1,max = 100,message = "ФИО не может быть меньше 3 или больше 30 симолов")
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+,[A-Z]\\w+", message = "ФИО должны начнаться с заглавной буквы")
    private String fio;

    @NotEmpty(message = "Графа не может быть пустой")
    @Pattern(regexp = "YYYY/MM/DD", message = "Введена некорректная дата")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    Date dateOfBirth;



    public Person(){}

    public Person(int id, String fio, Date dateOfBirth) {
        this.id = id;
        this.fio = fio;
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }



}
