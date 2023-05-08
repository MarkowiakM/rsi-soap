package org.example;

import com.sun.xml.ws.client.BindingProviderProperties;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.WebServiceException;
import org.example.jaxws.server_topdown.PersonExistsEx_Exception;
import org.example.jaxws.server_topdown.PersonNotFoundEx_Exception;
import org.example.jaxws.server_topdown.PersonService_Service;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ESClient {


    static PersonService_Service pService = new PersonService_Service();
    static org.example.jaxws.server_topdown.PersonService pServiceProxy = pService.getPersonServiceImplPort();

    public static void addPersonMenu() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Podaj id: ");
            int id = sc.nextInt();
            System.out.print("Podaj imię: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Podaj wiek: ");
            int wiek = sc.nextInt();
            System.out.print("Podaj email: ");
            sc.nextLine();
            String email = sc.nextLine();
            pServiceProxy.addPerson(id, name, wiek, email);
            System.out.println("Dodano osobę o id " + id);
        } catch (InputMismatchException e) {
            System.out.println("Podano błędny typ.");
            addPersonMenu();
        } catch (PersonExistsEx_Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void updatePersonMenu(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Podaj id: ");
            int id = sc.nextInt();
            System.out.print("Podaj imię: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Podaj wiek: ");
            int wiek = sc.nextInt();
            System.out.print("Podaj email: ");
            sc.nextLine();
            String email = sc.nextLine();
            pServiceProxy.updatePerson(id, name, wiek, email);
            System.out.println("Zaktualizowano dane osoby o id " + id);
        } catch (InputMismatchException e) {
            System.out.println("Podano błędny typ.");
            updatePersonMenu();
        } catch (PersonNotFoundEx_Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void deletePersonMenu(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Podaj id: ");
            int id = sc.nextInt();
            pServiceProxy.deletePerson(id);
            System.out.println("Usunięto osobę o id " + id);
        } catch (InputMismatchException e) {
            System.out.println("Podano błędny typ.");
            deletePersonMenu();
        } catch (PersonNotFoundEx_Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void getPersonMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj id: ");
        try {
            int id = sc.nextInt();
            org.example.jaxws.server_topdown.Person person = pServiceProxy.getPerson(id);
            System.out.println("Osoba: " + person.getFirstName() + ", wiek: " + person.getAge() + ", email: " + person.getEmail());
        } catch (InputMismatchException e) {
            System.out.println("Podano błędny typ.");
            getPersonMenu();
        } catch (PersonNotFoundEx_Exception ex) {
            System.out.println(ex.getMessage());
        } catch (WebServiceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof SocketTimeoutException) {
                System.out.println("The operation timed out");
            } else {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    public static void getPeopleMenu(){
        List<org.example.jaxws.server_topdown.Person> list = pServiceProxy.getAllPersons();
        System.out.println("Lista osób: ");
        for (org.example.jaxws.server_topdown.Person value : list) {
            System.out.println("id: " + value.getId() +", imię: "+ value.getFirstName() + ", wiek:" + value.getAge()  + ", email: " + value.getEmail());
        }
    }

    public static void getCountMenu(){
        int num;
        num = pServiceProxy.countPersons();
        System.out.println("Liczba osób: " + num);
    }

    public static void menu(){

        while (true){
            System.out.println("|| ----------------------------- ||");
            System.out.println("|| Wybierz, co chcesz zrobić:    ||");
            System.out.println("|| 1. Dodaj osobę                ||");
            System.out.println("|| 2. Zmień dane osoby           ||");
            System.out.println("|| 3. Usuń osobę                 ||");
            System.out.println("|| 4. Wyświetl dane osoby        ||");
            System.out.println("|| 5. Wyświetl wszystkie osoby   ||");
            System.out.println("|| 6. Wyświetl liczbę osób       ||");
            System.out.println("|| 7. Zakończ                    ||");
            System.out.println("|| ----------------------------- ||");
            System.out.print("|| Twój wybór: ");

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            switch (input){
                case ("1") -> addPersonMenu();
                case ("2") -> updatePersonMenu();
                case ("3") -> deletePersonMenu();
                case ("4") -> getPersonMenu();
                case ("5") -> getPeopleMenu();
                case ("6") -> getCountMenu();
                case ("7") -> { return; }
                default -> {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            ((BindingProvider) pServiceProxy).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 1000);
            MyData.myInfo();
        } catch (UnknownHostException e) {
            System.out.println("Nie znaleziono hosta");
        }
        menu();
    }
}
