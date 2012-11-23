
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
 *         &lt;element name="login_validationResult" type="{http://my.netgear.com/NetgearWS/TrainingPortal_User}Users" minOccurs="0"/>
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
    "loginValidationResult"
})
@XmlRootElement(name = "login_validationResponse")
public class LoginValidationResponse {

    @XmlElement(name = "login_validationResult")
    protected Users loginValidationResult;

    /**
     * Gets the value of the loginValidationResult property.
     * 
     * @return
     *     possible object is
     *     {@link Users }
     *     
     */
    public Users getLoginValidationResult() {
        return loginValidationResult;
    }

    /**
     * Sets the value of the loginValidationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Users }
     *     
     */
    public void setLoginValidationResult(Users value) {
        this.loginValidationResult = value;
    }

}
