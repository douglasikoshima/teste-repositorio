//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.17 at 03:54:49 PM BRST 
//


package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway.entrada.usrinserir;


/**
 * Java content class for UFOperadoraPessoaVOType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/D:/Indra%20SDK/workspaces/vivoservices/VivoServicesEJB/src/br/com/indrasistemas/vivoservices/administracaosistema/usuario/manter/tuxgateway/entrada/usrinserir/UsuarioVO.xsd line 66)
 * <p>
 * <pre>
 * &lt;complexType name="UFOperadoraPessoaVOType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idUFOperadora" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dsUFOperadora" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UFVO" type="{}UFVOType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface UFOperadoraPessoaVOType {


    /**
     * Gets the value of the ufvo property.
     * 
     * @return
     *     possible object is
     *     {@link br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway.entrada.usrinserir.UFVOType}
     */
    br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway.entrada.usrinserir.UFVOType getUFVO();

    /**
     * Sets the value of the ufvo property.
     * 
     * @param value
     *     allowed object is
     *     {@link br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway.entrada.usrinserir.UFVOType}
     */
    void setUFVO(br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway.entrada.usrinserir.UFVOType value);

    /**
     * Gets the value of the idUFOperadora property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getIdUFOperadora();

    /**
     * Sets the value of the idUFOperadora property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setIdUFOperadora(java.lang.String value);

    /**
     * Gets the value of the dsUFOperadora property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getDsUFOperadora();

    /**
     * Sets the value of the dsUFOperadora property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setDsUFOperadora(java.lang.String value);

}
