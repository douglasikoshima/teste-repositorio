//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.09.03 at 11:57:55 AM BRT 
//


package br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha.saida;


/**
 * Java content class for msgBodyType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/D:/Indra%20SDK/workspaces/vivoservices/VivoServicesEJB/src/br/com/indrasistemas/vivoservices/autenticacao/tuxgateway/alterarSenha/saida/alteraSenhaOUT.xsd line 22)
 * <p>
 * <pre>
 * &lt;complexType name="msgBodyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="AlteraSenha">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="statusText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface MsgBodyType {


    /**
     * Gets the value of the alteraSenha property.
     * 
     * @return
     *     possible object is
     *     {@link br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha.saida.MsgBodyType.AlteraSenhaType}
     */
    br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha.saida.MsgBodyType.AlteraSenhaType getAlteraSenha();

    /**
     * Sets the value of the alteraSenha property.
     * 
     * @param value
     *     allowed object is
     *     {@link br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha.saida.MsgBodyType.AlteraSenhaType}
     */
    void setAlteraSenha(br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha.saida.MsgBodyType.AlteraSenhaType value);


    /**
     * Java content class for anonymous complex type.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/D:/Indra%20SDK/workspaces/vivoservices/VivoServicesEJB/src/br/com/indrasistemas/vivoservices/autenticacao/tuxgateway/alterarSenha/saida/alteraSenhaOUT.xsd line 25)
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="statusText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     */
    public interface AlteraSenhaType {


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

}
