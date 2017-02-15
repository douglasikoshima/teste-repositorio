/**
 * VerificarItemMatrizOfertaServicoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class VerificarItemMatrizOfertaServicoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoVerificarItemMatrizOfertaServico resultadoVerificarItemMatrizOfertaServico;

    public VerificarItemMatrizOfertaServicoResponse() {
    }

    public VerificarItemMatrizOfertaServicoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoVerificarItemMatrizOfertaServico resultadoVerificarItemMatrizOfertaServico) {
           this.resultadoVerificarItemMatrizOfertaServico = resultadoVerificarItemMatrizOfertaServico;
    }


    /**
     * Gets the resultadoVerificarItemMatrizOfertaServico value for this VerificarItemMatrizOfertaServicoResponse.
     * 
     * @return resultadoVerificarItemMatrizOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoVerificarItemMatrizOfertaServico getResultadoVerificarItemMatrizOfertaServico() {
        return resultadoVerificarItemMatrizOfertaServico;
    }


    /**
     * Sets the resultadoVerificarItemMatrizOfertaServico value for this VerificarItemMatrizOfertaServicoResponse.
     * 
     * @param resultadoVerificarItemMatrizOfertaServico
     */
    public void setResultadoVerificarItemMatrizOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoVerificarItemMatrizOfertaServico resultadoVerificarItemMatrizOfertaServico) {
        this.resultadoVerificarItemMatrizOfertaServico = resultadoVerificarItemMatrizOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VerificarItemMatrizOfertaServicoResponse)) return false;
        VerificarItemMatrizOfertaServicoResponse other = (VerificarItemMatrizOfertaServicoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoVerificarItemMatrizOfertaServico==null && other.getResultadoVerificarItemMatrizOfertaServico()==null) || 
             (this.resultadoVerificarItemMatrizOfertaServico!=null &&
              this.resultadoVerificarItemMatrizOfertaServico.equals(other.getResultadoVerificarItemMatrizOfertaServico())));
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
        if (getResultadoVerificarItemMatrizOfertaServico() != null) {
            _hashCode += getResultadoVerificarItemMatrizOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VerificarItemMatrizOfertaServicoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">verificarItemMatrizOfertaServicoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoVerificarItemMatrizOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ResultadoVerificarItemMatrizOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ResultadoVerificarItemMatrizOfertaServico"));
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
