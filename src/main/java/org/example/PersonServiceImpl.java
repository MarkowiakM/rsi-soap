package org.example;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebService(serviceName = "PersonService",
        endpointInterface = "org.example.PersonService")
public class PersonServiceImpl implements PersonService {
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private PersonRepository dataRepository = new PersonRepositoryImpl();

    @WebMethod
    public Person getPerson(int id) throws PersonNotFoundEx {
        System.out.println("...called getPerson id=" + id);
        try {
            return executor.submit(() -> {
                try {
                    Thread.sleep(1000);
                    return dataRepository.getPerson(id);
                } catch(InterruptedException e ) {
                    throw new RuntimeException(e);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e ){
            throw new RuntimeException(e);
        }

    }

    @WebMethod
    public List<Person> getAllPersons() {
        System.out.println("...called getAllPersons");
        return dataRepository.getAllPersons();
    }

    @WebMethod
    public Person updatePerson(int id, String name, int age, String email) throws PersonNotFoundEx {
        System.out.println("...called updatePerson");
        return dataRepository.updatePerson(id, name, age, email);
    }

    @WebMethod
    public boolean deletePerson(int id) throws PersonNotFoundEx {
        System.out.println("...called deletePerson");
        return dataRepository.deletePerson(id);

    }

    @WebMethod
    public Person addPerson(int id, String name, int age, String email) throws PersonExistsEx {
        System.out.println("...called addPerson");
        return dataRepository.addPerson(id, name, age, email);
    }

    @WebMethod
    public int countPersons() {
        System.out.println("...called countPersons");
        return dataRepository.countPersons();
    }

}
