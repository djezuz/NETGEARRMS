
package com.wsclient;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TrainingPortal_User", targetNamespace = "http://my.netgear.com/NetgearWS/TrainingPortal_User", wsdlLocation = "http://my.netgear.com/NetgearWS/TrainingPortal_User.asmx?WSDL")
public class TrainingPortalUser
    extends Service
{

    private final static URL TRAININGPORTALUSER_WSDL_LOCATION;
    private final static WebServiceException TRAININGPORTALUSER_EXCEPTION;
    private final static QName TRAININGPORTALUSER_QNAME = new QName("http://my.netgear.com/NetgearWS/TrainingPortal_User", "TrainingPortal_User");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://my.netgear.com/NetgearWS/TrainingPortal_User.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TRAININGPORTALUSER_WSDL_LOCATION = url;
        TRAININGPORTALUSER_EXCEPTION = e;
    }

    public TrainingPortalUser() {
        super(__getWsdlLocation(), TRAININGPORTALUSER_QNAME);
    }

    public TrainingPortalUser(WebServiceFeature... features) {
        super(__getWsdlLocation(), TRAININGPORTALUSER_QNAME, features);
    }

    public TrainingPortalUser(URL wsdlLocation) {
        super(wsdlLocation, TRAININGPORTALUSER_QNAME);
    }

    public TrainingPortalUser(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TRAININGPORTALUSER_QNAME, features);
    }

    public TrainingPortalUser(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TrainingPortalUser(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TrainingPortalUserSoap
     */
    @WebEndpoint(name = "TrainingPortal_UserSoap")
    public TrainingPortalUserSoap getTrainingPortalUserSoap() {
        return super.getPort(new QName("http://my.netgear.com/NetgearWS/TrainingPortal_User", "TrainingPortal_UserSoap"), TrainingPortalUserSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TrainingPortalUserSoap
     */
    @WebEndpoint(name = "TrainingPortal_UserSoap")
    public TrainingPortalUserSoap getTrainingPortalUserSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://my.netgear.com/NetgearWS/TrainingPortal_User", "TrainingPortal_UserSoap"), TrainingPortalUserSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TRAININGPORTALUSER_EXCEPTION!= null) {
            throw TRAININGPORTALUSER_EXCEPTION;
        }
        return TRAININGPORTALUSER_WSDL_LOCATION;
    }

}