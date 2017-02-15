/**
 * ListaValorFrequenciaValorFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ListaValorFrequenciaValorFrequencia  implements java.io.Serializable {
    private long idTecnologiaTpFrequenciaVl;

    private long idVlFrequencia;

    private java.lang.String vlFrequencia;

    public ListaValorFrequenciaValorFrequencia() {
    }

    public ListaValorFrequenciaValorFrequencia(
           long idTecnologiaTpFrequenciaVl,
           long idVlFrequencia,
           java.lang.String vlFrequencia) {
           this.idTecnologiaTpFrequenciaVl = idTecnologiaTpFrequenciaVl;
           this.idVlFrequencia = idVlFrequencia;
           this.vlFrequencia = vlFrequencia;
    }


    /**
     * Gets the idTecnologiaTpFrequenciaVl value for this ListaValorFrequenciaValorFrequencia.
     * 
     * @return idTecnologiaTpFrequenciaVl
     */
    public long getIdTecnologiaTpFrequenciaVl() {
        return idTecnologiaTpFrequenciaVl;
    }


    /**
     * Sets the idTecnologiaTpFrequenciaVl value for this ListaValorFrequenciaValorFrequencia.
     * 
     * @param idTecnologiaTpFrequenciaVl
     */
    public void setIdTecnologiaTpFrequenciaVl(long idTecnologiaTpFrequenciaVl) {
        this.idTecnologiaTpFrequenciaVl = idTecnologiaTpFrequenciaVl;
    }


    /**
     * Gets the idVlFrequencia value for this ListaValorFrequenciaValorFrequencia.
     * 
     * @return idVlFrequencia
     */
    public long getIdVlFrequencia() {
        return idVlFrequencia;
    }


    /**
     * Sets the idVlFrequencia value for this ListaValorFrequenciaValorFrequencia.
     * 
     * @param idVlFrequencia
     */
    public void setIdVlFrequencia(long idVlFrequencia) {
        this.idVlFrequencia = idVlFrequencia;
    }


    /**
     * Gets the vlFrequencia value for this ListaValorFrequenciaValorFrequencia.
     * 
     * @return vlFrequencia
     */
    public java.lang.String getVlFrequencia() {
        return vlFrequencia;
    }


    /**
     * Sets the vlFrequencia value for this ListaValorFrequenciaValorFrequencia.
     * 
     * @param vlFrequencia
     */
    public void setVlFrequencia(java.lang.String vlFrequencia) {
        this.vlFrequencia = vlFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaValorFrequenciaValorFrequencia)) return false;
        ListaValorFrequenciaValorFrequencia other = (ListaValorFrequenciaValorFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTecnologiaTpFrequenciaVl == other.getIdTecnologiaTpFrequenciaVl() &&
            this.idVlFrequencia == other.getIdVlFrequencia() &&
            ((this.vlFrequencia==null && other.getVlFrequencia()==null) || 
             (this.vlFrequencia!=null &&
              this.vlFrequencia.equals(other.getVlFrequencia())));
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
        _hashCode += new Long(getIdTecnologiaTpFrequenciaVl()).hashCode();
        _hashCode += new Long(getIdVlFrequencia()).hashCode();
        if (getVlFrequencia() != null) {
            _hashCode += getVlFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaValorFrequenciaValorFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">>ListaValorFrequencia>ValorFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologiaTpFrequenciaVl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "idTecnologiaTpFrequenciaVl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idVlFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "idVlFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vlFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "vlFrequencia"));
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