//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.10.27 at 04:51:52 PM GMT-03:00 
//


package br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot;


/**
 * Java content class for msgType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/D:/Indra%20SDK/workspaces/VivoServicesEJB/src/br/com/indrasistemas/vivoservices/atendimento/protocolo/tuxgateway/saida/atdabreprot/OUT-ATDABREPROT.xsd line 4)
 * <p>
 * <pre>
 * &lt;complexType name="msgType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="msgHdr" type="{}msgHdrType"/>
 *         &lt;element name="msgBody" type="{}msgBodyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface MsgType {


    /**
     * Gets the value of the msgBody property.
     * 
     * @return
     *     possible object is
     *     {@link br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.MsgBodyType}
     */
    br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.MsgBodyType getMsgBody();

    /**
     * Sets the value of the msgBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.MsgBodyType}
     */
    void setMsgBody(br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.MsgBodyType value);

    /**
     * Gets the value of the msgHdr property.
     * 
     * @return
     *     possible object is
     *     {@link br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.MsgHdrType}
     */
    br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.MsgHdrType getMsgHdr();

    /**
     * Sets the value of the msgHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.MsgHdrType}
     */
    void setMsgHdr(br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.MsgHdrType value);

}
