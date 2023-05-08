package org.example;
import jakarta.xml.ws.WebFault;
@WebFault
public class PersonExistsEx extends Exception {
    public PersonExistsEx() {
        super("Taka osoba już istnieje");
    }
}
