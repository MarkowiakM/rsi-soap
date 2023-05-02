
package org.example.jaxws.server_topdown;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.2
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "PersonExistsEx", targetNamespace = "http://example.org/")
public class PersonExistsEx_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PersonExistsEx faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public PersonExistsEx_Exception(String message, PersonExistsEx faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public PersonExistsEx_Exception(String message, PersonExistsEx faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.example.jaxws.server_topdown.PersonExistsEx
     */
    public PersonExistsEx getFaultInfo() {
        return faultInfo;
    }

}
