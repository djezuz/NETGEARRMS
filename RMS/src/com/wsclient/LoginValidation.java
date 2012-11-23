
package com.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="param_username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="param_password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "paramUsername",
    "paramPassword"
})
@XmlRootElement(name = "login_validation")
public class LoginValidation {

    @XmlElement(name = "param_username")
    protected String paramUsername;
    @XmlElement(name = "param_password")
    protected String paramPassword;

    /**
     * Gets the value of the paramUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamUsername() {
        return paramUsername;
    }

    /**
     * Sets the value of the paramUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamUsername(String value) {
        this.paramUsername = value;
    }

    /**
     * Gets the value of the paramPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamPassword() {
        return paramPassword;
    }

    /**
     * Sets the value of the paramPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamPassword(String value) {
        this.paramPassword = value;
    }

}
