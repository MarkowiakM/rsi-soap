package org.example;
import jakarta.xml.ws.WebFault;

@WebFault
public class PersonNotFoundEx extends Exception {
    public PersonNotFoundEx() {
        super("Nie znaleziono wskazanej osoby");
    }
}
