//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.03.31 at 06:44:46 PM GMT 
//


package br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida;


/**
 * Java content class for msgHdrType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/D:/Indra%20SDK/workspaces/vivoservices/VivoServicesEJB/src/br/com/indrasistemas/vivoservices/portabilidade/cliente/servicegateway/tuxedo/portCliente/saida/PortClienteOUT.xsd line 12)
 * <p>
 * <pre>
 * &lt;complexType name="msgHdrType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serverElapsedTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface MsgHdrType {


    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getStatusCode();

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setStatusCode(java.lang.String value);

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getUser();

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setUser(java.lang.String value);

    /**
     * Gets the value of the serverElapsedTime property.
     * 
     */
    int getServerElapsedTime();

    /**
     * Sets the value of the serverElapsedTime property.
     * 
     */
    void setServerElapsedTime(int value);

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getService();

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setService(java.lang.String value);

    /**
     * Gets the value of the subSystem property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getSubSystem();

    /**
     * Sets the value of the subSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setSubSystem(java.lang.String value);

    /**
     * Gets the value of the statusText property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getStatusText();

    /**
     * Sets the value of the statusText property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setStatusText(java.lang.String value);

}
