package controllers;

import java.util.List;
import java.util.ArrayList;
import models.Person;
/**
 *
 * @author Matheus Max
 */
public class ListPersons {
    
    private static List<Person> pessoas = new ArrayList<Person>();

    public void addPessoa(Person p) {
        pessoas.add(p);
    }
    
    public List<Person> getPessoas() {
        return pessoas;
    }
}
