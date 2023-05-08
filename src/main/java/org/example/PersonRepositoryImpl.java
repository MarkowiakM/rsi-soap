package org.example;

import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {
    private List<Person> personList;
    public PersonRepositoryImpl() {
        personList = new ArrayList<>();
        personList.add(new Person(1, "Mariusz" , 9, "mariusz@gmail.com"));
        personList.add(new Person(2, "Andrzej", 10, "andrzej@gmail.com"));
        personList.add(new Person(3, "Maria", 11, "maria@gmail.com"));
        personList.add(new Person(4, "Remigiusz", 12, "remigiusz@gmail.com"));
    }
    public List<Person> getAllPersons() {
        return personList;
    }
    public Person getPerson(int id) throws PersonNotFoundEx {
        for (Person person : personList) {
            if (person.getId() == id) {
                return person;
            }
        }
        throw new PersonNotFoundEx();
    }
    public Person addPerson(int id, String name, int age, String email) throws
            PersonExistsEx {
        for (Person person : personList) {
            if (person.getId() == id) {
                throw new PersonExistsEx();
            }
        }
        Person person = new Person(id, name, age, email);
        personList.add(person);
        return person;
    }
    public boolean deletePerson(int id) throws PersonNotFoundEx {
        for (Person person : personList) {
            if (person.getId() == id) {
                personList.remove(person);
                return true;
            }
        }
        throw new PersonNotFoundEx();
    }
    public Person updatePerson(int id, String name, int age, String email) throws
            PersonNotFoundEx {
        for (Person person : personList) {
            if (person.getId() == id) {
                person.setFirstName(name);
                person.setAge(age);
                person.setEmail(email);
                return person;
            }
        }
        throw new PersonNotFoundEx();
    }
    public int countPersons() {
        return personList.size();
    }
}
