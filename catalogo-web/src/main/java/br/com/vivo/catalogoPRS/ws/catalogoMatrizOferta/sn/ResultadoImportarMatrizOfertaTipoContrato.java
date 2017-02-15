/**
 * ResultadoImportarMatrizOfertaTipoContrato.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ResultadoImportarMatrizOfertaTipoContrato  implements java.io.Serializable {
    private long idMatrizOfertaTipoContratoArquivo;

    public ResultadoImportarMatrizOfertaTipoContrato() {
    }

    public ResultadoImportarMatrizOfertaTipoContrato(
           long idMatrizOfertaTipoContratoArquivo) {
           this.idMatrizOfertaTipoContratoArquivo = idMatrizOfertaTipoContratoArquivo;
    }


    /**
     * Gets the idMatrizOfertaTipoContratoArquivo value for this ResultadoImportarMatrizOfertaTipoContrato.
     * 
     * @return idMatrizOfertaTipoContratoArquivo
     */
    public long getIdMatrizOfertaTipoContratoArquivo() {
        return idMatrizOfertaTipoContratoArquivo;
    }


    /**
     * Sets the idMatrizOfertaTipoContratoArquivo value for this ResultadoImportarMatrizOfertaTipoContrato.
     * 
     * @param idMatrizOfertaTipoContratoArquivo
     */
    public void setIdMatrizOfertaTipoContratoArquivo(long idMatrizOfertaTipoContratoArquivo) {
        this.idMatrizOfertaTipoContratoArquivo = idMatrizOfertaTipoContratoArquivo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoImportarMatrizOfertaTipoContrato)) return false;
        ResultadoImportarMatrizOfertaTipoContrato other = (ResultadoImportarMatrizOfertaTipoContrato) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idMatrizOfertaTipoContratoArquivo == other.getIdMatrizOfertaTipoContratoArquivo();
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
        _hashCode += new Long(getIdMatrizOfertaTipoContratoArquivo()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoImportarMatrizOfertaTipoContrato.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoImportarMatrizOfertaTipoContrato"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMatrizOfertaTipoContratoArquivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idMatrizOfertaTipoContratoArquivo"));
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
