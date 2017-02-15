/**
 * PaginacaoOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class PaginacaoOut  implements java.io.Serializable {
    private int paginaAtual;

    private int totalRegistros;

    public PaginacaoOut() {
    }

    public PaginacaoOut(
           int paginaAtual,
           int totalRegistros) {
           this.paginaAtual = paginaAtual;
           this.totalRegistros = totalRegistros;
    }


    /**
     * Gets the paginaAtual value for this PaginacaoOut.
     * 
     * @return paginaAtual
     */
    public int getPaginaAtual() {
        return paginaAtual;
    }


    /**
     * Sets the paginaAtual value for this PaginacaoOut.
     * 
     * @param paginaAtual
     */
    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }


    /**
     * Gets the totalRegistros value for this PaginacaoOut.
     * 
     * @return totalRegistros
     */
    public int getTotalRegistros() {
        return totalRegistros;
    }


    /**
     * Sets the totalRegistros value for this PaginacaoOut.
     * 
     * @param totalRegistros
     */
    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PaginacaoOut)) return false;
        PaginacaoOut other = (PaginacaoOut) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.paginaAtual == other.getPaginaAtual() &&
            this.totalRegistros == other.getTotalRegistros();
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
        _hashCode += getPaginaAtual();
        _hashCode += getTotalRegistros();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PaginacaoOut.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoOut"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginaAtual");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "paginaAtual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalRegistros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "totalRegistros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
