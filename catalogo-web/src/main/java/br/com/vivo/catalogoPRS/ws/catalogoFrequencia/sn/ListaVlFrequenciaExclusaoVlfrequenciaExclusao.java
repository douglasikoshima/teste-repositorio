/**
 * ListaVlFrequenciaExclusaoVlfrequenciaExclusao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ListaVlFrequenciaExclusaoVlfrequenciaExclusao  implements java.io.Serializable {
    private long idvlfrequencia;

    public ListaVlFrequenciaExclusaoVlfrequenciaExclusao() {
    }

    public ListaVlFrequenciaExclusaoVlfrequenciaExclusao(
           long idvlfrequencia) {
           this.idvlfrequencia = idvlfrequencia;
    }


    /**
     * Gets the idvlfrequencia value for this ListaVlFrequenciaExclusaoVlfrequenciaExclusao.
     * 
     * @return idvlfrequencia
     */
    public long getIdvlfrequencia() {
        return idvlfrequencia;
    }


    /**
     * Sets the idvlfrequencia value for this ListaVlFrequenciaExclusaoVlfrequenciaExclusao.
     * 
     * @param idvlfrequencia
     */
    public void setIdvlfrequencia(long idvlfrequencia) {
        this.idvlfrequencia = idvlfrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaVlFrequenciaExclusaoVlfrequenciaExclusao)) return false;
        ListaVlFrequenciaExclusaoVlfrequenciaExclusao other = (ListaVlFrequenciaExclusaoVlfrequenciaExclusao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idvlfrequencia == other.getIdvlfrequencia();
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
        _hashCode += new Long(getIdvlfrequencia()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaVlFrequenciaExclusaoVlfrequenciaExclusao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">>ListaVlFrequenciaExclusao>VlfrequenciaExclusao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idvlfrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "idvlfrequencia"));
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
