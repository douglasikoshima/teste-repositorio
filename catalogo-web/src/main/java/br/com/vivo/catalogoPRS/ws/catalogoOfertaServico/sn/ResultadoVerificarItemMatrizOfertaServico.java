/**
 * ResultadoVerificarItemMatrizOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoVerificarItemMatrizOfertaServico  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoVerificarItemMatrizOfertaServicoListaVerificarItemMatrizOfertaServico listaVerificarItemMatrizOfertaServico;

    public ResultadoVerificarItemMatrizOfertaServico() {
    }

    public ResultadoVerificarItemMatrizOfertaServico(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoVerificarItemMatrizOfertaServicoListaVerificarItemMatrizOfertaServico listaVerificarItemMatrizOfertaServico) {
           this.listaVerificarItemMatrizOfertaServico = listaVerificarItemMatrizOfertaServico;
    }


    /**
     * Gets the listaVerificarItemMatrizOfertaServico value for this ResultadoVerificarItemMatrizOfertaServico.
     * 
     * @return listaVerificarItemMatrizOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoVerificarItemMatrizOfertaServicoListaVerificarItemMatrizOfertaServico getListaVerificarItemMatrizOfertaServico() {
        return listaVerificarItemMatrizOfertaServico;
    }


    /**
     * Sets the listaVerificarItemMatrizOfertaServico value for this ResultadoVerificarItemMatrizOfertaServico.
     * 
     * @param listaVerificarItemMatrizOfertaServico
     */
    public void setListaVerificarItemMatrizOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoVerificarItemMatrizOfertaServicoListaVerificarItemMatrizOfertaServico listaVerificarItemMatrizOfertaServico) {
        this.listaVerificarItemMatrizOfertaServico = listaVerificarItemMatrizOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoVerificarItemMatrizOfertaServico)) return false;
        ResultadoVerificarItemMatrizOfertaServico other = (ResultadoVerificarItemMatrizOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaVerificarItemMatrizOfertaServico==null && other.getListaVerificarItemMatrizOfertaServico()==null) || 
             (this.listaVerificarItemMatrizOfertaServico!=null &&
              this.listaVerificarItemMatrizOfertaServico.equals(other.getListaVerificarItemMatrizOfertaServico())));
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
        if (getListaVerificarItemMatrizOfertaServico() != null) {
            _hashCode += getListaVerificarItemMatrizOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoVerificarItemMatrizOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ResultadoVerificarItemMatrizOfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaVerificarItemMatrizOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ListaVerificarItemMatrizOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>ResultadoVerificarItemMatrizOfertaServico>ListaVerificarItemMatrizOfertaServico"));
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
