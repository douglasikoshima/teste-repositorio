//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.10.27 at 04:51:52 PM GMT-03:00 
//


package br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot;


/**
 * Java content class for msgBodyType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/D:/Indra%20SDK/workspaces/VivoServicesEJB/src/br/com/indrasistemas/vivoservices/atendimento/protocolo/tuxgateway/saida/atdabreprot/OUT-ATDABREPROT.xsd line 20)
 * <p>
 * <pre>
 * &lt;complexType name="msgBodyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbreProtocoloOutTO" type="{}AbreProtocoloOutTOType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface MsgBodyType {


    /**
     * Gets the value of the abreProtocoloOutTO property.
     * 
     * @return
     *     possible object is
     *     {@link br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.AbreProtocoloOutTOType}
     */
    br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.AbreProtocoloOutTOType getAbreProtocoloOutTO();

    /**
     * Sets the value of the abreProtocoloOutTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.AbreProtocoloOutTOType}
     */
    void setAbreProtocoloOutTO(br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.AbreProtocoloOutTOType value);

}
