/**
 * ResultBuscarIdMatrizOfertaArquivo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ResultBuscarIdMatrizOfertaArquivo  implements java.io.Serializable {
    private long idMatrizOfertaArquivo;

    public ResultBuscarIdMatrizOfertaArquivo() {
    }

    public ResultBuscarIdMatrizOfertaArquivo(
           long idMatrizOfertaArquivo) {
           this.idMatrizOfertaArquivo = idMatrizOfertaArquivo;
    }


    /**
     * Gets the idMatrizOfertaArquivo value for this ResultBuscarIdMatrizOfertaArquivo.
     * 
     * @return idMatrizOfertaArquivo
     */
    public long getIdMatrizOfertaArquivo() {
        return idMatrizOfertaArquivo;
    }


    /**
     * Sets the idMatrizOfertaArquivo value for this ResultBuscarIdMatrizOfertaArquivo.
     * 
     * @param idMatrizOfertaArquivo
     */
    public void setIdMatrizOfertaArquivo(long idMatrizOfertaArquivo) {
        this.idMatrizOfertaArquivo = idMatrizOfertaArquivo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultBuscarIdMatrizOfertaArquivo)) return false;
        ResultBuscarIdMatrizOfertaArquivo other = (ResultBuscarIdMatrizOfertaArquivo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idMatrizOfertaArquivo == other.getIdMatrizOfertaArquivo();
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
        _hashCode += new Long(getIdMatrizOfertaArquivo()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultBuscarIdMatrizOfertaArquivo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultBuscarIdMatrizOfertaArquivo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMatrizOfertaArquivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idMatrizOfertaArquivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
