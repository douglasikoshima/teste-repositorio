//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.10.27 at 04:50:37 PM GMT-03:00 
//


package br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.entrada.atdabreprot;


/**
 * Java content class for AbreProtocoloTOType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/D:/Indra%20SDK/workspaces/VivoServicesEJB/src/br/com/indrasistemas/vivoservices/atendimento/protocolo/tuxgateway/entrada/atdabreprot/IN-ATDABREPROT.xsd line 21)
 * <p>
 * <pre>
 * &lt;complexType name="AbreProtocoloTOType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTipoAberturaProtocolo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cdAreaRegistro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nrTelefone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cdAreaRegistroSMS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nrTelefoneSMS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idSistemaOrigem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cdConta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface AbreProtocoloTOType {


    /**
     * Gets the value of the nrTelefone property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getNrTelefone();

    /**
     * Sets the value of the nrTelefone property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setNrTelefone(java.lang.String value);

    /**
     * Gets the value of the cdAreaRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getCdAreaRegistro();

    /**
     * Sets the value of the cdAreaRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setCdAreaRegistro(java.lang.String value);

    /**
     * Gets the value of the idTipoAberturaProtocolo property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getIdTipoAberturaProtocolo();

    /**
     * Sets the value of the idTipoAberturaProtocolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setIdTipoAberturaProtocolo(java.lang.String value);

    /**
     * Gets the value of the idSistemaOrigem property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getIdSistemaOrigem();

    /**
     * Sets the value of the idSistemaOrigem property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setIdSistemaOrigem(java.lang.String value);

    /**
     * Gets the value of the cdAreaRegistroSMS property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getCdAreaRegistroSMS();

    /**
     * Sets the value of the cdAreaRegistroSMS property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setCdAreaRegistroSMS(java.lang.String value);

    /**
     * Gets the value of the cdConta property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getCdConta();

    /**
     * Sets the value of the cdConta property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setCdConta(java.lang.String value);

    /**
     * Gets the value of the nrTelefoneSMS property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getNrTelefoneSMS();

    /**
     * Sets the value of the nrTelefoneSMS property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setNrTelefoneSMS(java.lang.String value);

}
