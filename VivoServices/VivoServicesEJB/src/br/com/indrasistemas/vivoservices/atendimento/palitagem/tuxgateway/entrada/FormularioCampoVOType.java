//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.10.31 at 04:26:42 PM BRST 
//


package br.com.indrasistemas.vivoservices.atendimento.palitagem.tuxgateway.entrada;


/**
 * Java content class for FormularioCampoVOType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/D:/Indra%20SDK/workspaces/vivoservices/VivoServicesEJB/src/br/com/indrasistemas/vivoservices/atendimento/palitagem/tuxgateway/entrada/regPalitageFoIN.xsd line 43)
 * <p>
 * <pre>
 * &lt;complexType name="FormularioCampoVOType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idContatoFolhaCampo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idCampo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="FormularioCampoValorVO" type="{}FormularioCampoValorVOType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface FormularioCampoVOType {


    /**
     * Gets the value of the idContatoFolhaCampo property.
     * 
     */
    long getIdContatoFolhaCampo();

    /**
     * Sets the value of the idContatoFolhaCampo property.
     * 
     */
    void setIdContatoFolhaCampo(long value);

    /**
     * Gets the value of the idCampo property.
     * 
     */
    long getIdCampo();

    /**
     * Sets the value of the idCampo property.
     * 
     */
    void setIdCampo(long value);

    /**
     * Gets the value of the FormularioCampoValorVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the FormularioCampoValorVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormularioCampoValorVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link br.com.indrasistemas.vivoservices.atendimento.palitagem.tuxgateway.entrada.FormularioCampoValorVOType}
     * 
     */
    java.util.List getFormularioCampoValorVO();

}
