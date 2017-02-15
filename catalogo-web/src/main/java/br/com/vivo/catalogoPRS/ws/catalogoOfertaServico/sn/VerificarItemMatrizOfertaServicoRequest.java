/**
 * VerificarItemMatrizOfertaServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class VerificarItemMatrizOfertaServicoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosVerificarItemMatrizOfertaServico parametrosVerificarItemMatrizOfertaServico;

    public VerificarItemMatrizOfertaServicoRequest() {
    }

    public VerificarItemMatrizOfertaServicoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosVerificarItemMatrizOfertaServico parametrosVerificarItemMatrizOfertaServico) {
           this.parametrosVerificarItemMatrizOfertaServico = parametrosVerificarItemMatrizOfertaServico;
    }


    /**
     * Gets the parametrosVerificarItemMatrizOfertaServico value for this VerificarItemMatrizOfertaServicoRequest.
     * 
     * @return parametrosVerificarItemMatrizOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosVerificarItemMatrizOfertaServico getParametrosVerificarItemMatrizOfertaServico() {
        return parametrosVerificarItemMatrizOfertaServico;
    }


    /**
     * Sets the parametrosVerificarItemMatrizOfertaServico value for this VerificarItemMatrizOfertaServicoRequest.
     * 
     * @param parametrosVerificarItemMatrizOfertaServico
     */
    public void setParametrosVerificarItemMatrizOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosVerificarItemMatrizOfertaServico parametrosVerificarItemMatrizOfertaServico) {
        this.parametrosVerificarItemMatrizOfertaServico = parametrosVerificarItemMatrizOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VerificarItemMatrizOfertaServicoRequest)) return false;
        VerificarItemMatrizOfertaServicoRequest other = (VerificarItemMatrizOfertaServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosVerificarItemMatrizOfertaServico==null && other.getParametrosVerificarItemMatrizOfertaServico()==null) || 
             (this.parametrosVerificarItemMatrizOfertaServico!=null &&
              this.parametrosVerificarItemMatrizOfertaServico.equals(other.getParametrosVerificarItemMatrizOfertaServico())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getParametrosVerificarItemMatrizOfertaServico() != null) {
            _hashCode += getParametrosVerificarItemMatrizOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VerificarItemMatrizOfertaServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">verificarItemMatrizOfertaServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosVerificarItemMatrizOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ParametrosVerificarItemMatrizOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ParametrosVerificarItemMatrizOfertaServico"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
