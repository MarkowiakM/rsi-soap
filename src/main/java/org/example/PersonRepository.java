package org.example;

import java.util.List;

public interface PersonRepository {
    List<Person> getAllPersons();

    Person getPerson(int id) throws PersonNotFoundEx;

    Person updatePerson(int id, String name, int age, String email) throws
            PersonNotFoundEx;

    boolean deletePerson(int id) throws PersonNotFoundEx;

    Person addPerson(int id, String name, int age, String email) throws PersonExistsEx;

    int countPersons();
}
