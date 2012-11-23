
package com.wsclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.wsclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Users_QNAME = new QName("http://my.netgear.com/NetgearWS/TrainingPortal_User", "Users");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wsclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Users }
     * 
     */
    public Users createUsers() {
        return new Users();
    }

    /**
     * Create an instance of {@link LoginValidationResponse }
     * 
     */
    public LoginValidationResponse createLoginValidationResponse() {
        return new LoginValidationResponse();
    }

    /**
     * Create an instance of {@link LoginValidation }
     * 
     */
    public LoginValidation createLoginValidation() {
        return new LoginValidation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Users }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://my.netgear.com/NetgearWS/TrainingPortal_User", name = "Users")
    public JAXBElement<Users> createUsers(Users value) {
        return new JAXBElement<Users>(_Users_QNAME, Users.class, null, value);
    }

}
