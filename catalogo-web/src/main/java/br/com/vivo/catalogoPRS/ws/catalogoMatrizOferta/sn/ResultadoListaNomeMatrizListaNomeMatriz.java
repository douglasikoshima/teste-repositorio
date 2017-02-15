/**
 * ResultadoListaNomeMatrizListaNomeMatriz.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ResultadoListaNomeMatrizListaNomeMatriz  implements java.io.Serializable {
    private long idMatrizOferta;

    private java.lang.String nmMatrizOferta;

    public ResultadoListaNomeMatrizListaNomeMatriz() {
    }

    public ResultadoListaNomeMatrizListaNomeMatriz(
           long idMatrizOferta,
           java.lang.String nmMatrizOferta) {
           this.idMatrizOferta = idMatrizOferta;
           this.nmMatrizOferta = nmMatrizOferta;
    }


    /**
     * Gets the idMatrizOferta value for this ResultadoListaNomeMatrizListaNomeMatriz.
     * 
     * @return idMatrizOferta
     */
    public long getIdMatrizOferta() {
        return idMatrizOferta;
    }


    /**
     * Sets the idMatrizOferta value for this ResultadoListaNomeMatrizListaNomeMatriz.
     * 
     * @param idMatrizOferta
     */
    public void setIdMatrizOferta(long idMatrizOferta) {
        this.idMatrizOferta = idMatrizOferta;
    }


    /**
     * Gets the nmMatrizOferta value for this ResultadoListaNomeMatrizListaNomeMatriz.
     * 
     * @return nmMatrizOferta
     */
    public java.lang.String getNmMatrizOferta() {
        return nmMatrizOferta;
    }


    /**
     * Sets the nmMatrizOferta value for this ResultadoListaNomeMatrizListaNomeMatriz.
     * 
     * @param nmMatrizOferta
     */
    public void setNmMatrizOferta(java.lang.String nmMatrizOferta) {
        this.nmMatrizOferta = nmMatrizOferta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoListaNomeMatrizListaNomeMatriz)) return false;
        ResultadoListaNomeMatrizListaNomeMatriz other = (ResultadoListaNomeMatrizListaNomeMatriz) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idMatrizOferta == other.getIdMatrizOferta() &&
            ((this.nmMatrizOferta==null && other.getNmMatrizOferta()==null) || 
             (this.nmMatrizOferta!=null &&
              this.nmMatrizOferta.equals(other.getNmMatrizOferta())));
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
        _hashCode += new Long(getIdMatrizOferta()).hashCode();
        if (getNmMatrizOferta() != null) {
            _hashCode += getNmMatrizOferta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoListaNomeMatrizListaNomeMatriz.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoListaNomeMatriz>ListaNomeMatriz"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMatrizOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idMatrizOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmMatrizOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "nmMatrizOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
