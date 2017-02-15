/**
 * ParametrosBuscarListaTipoPlano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ParametrosBuscarListaTipoPlano  implements java.io.Serializable {
    private java.lang.Long idPlataforma;

    private java.lang.Long idTipoPlano;

    public ParametrosBuscarListaTipoPlano() {
    }

    public ParametrosBuscarListaTipoPlano(
           java.lang.Long idPlataforma,
           java.lang.Long idTipoPlano) {
           this.idPlataforma = idPlataforma;
           this.idTipoPlano = idTipoPlano;
    }


    /**
     * Gets the idPlataforma value for this ParametrosBuscarListaTipoPlano.
     * 
     * @return idPlataforma
     */
    public java.lang.Long getIdPlataforma() {
        return idPlataforma;
    }


    /**
     * Sets the idPlataforma value for this ParametrosBuscarListaTipoPlano.
     * 
     * @param idPlataforma
     */
    public void setIdPlataforma(java.lang.Long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }


    /**
     * Gets the idTipoPlano value for this ParametrosBuscarListaTipoPlano.
     * 
     * @return idTipoPlano
     */
    public java.lang.Long getIdTipoPlano() {
        return idTipoPlano;
    }


    /**
     * Sets the idTipoPlano value for this ParametrosBuscarListaTipoPlano.
     * 
     * @param idTipoPlano
     */
    public void setIdTipoPlano(java.lang.Long idTipoPlano) {
        this.idTipoPlano = idTipoPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaTipoPlano)) return false;
        ParametrosBuscarListaTipoPlano other = (ParametrosBuscarListaTipoPlano) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idPlataforma==null && other.getIdPlataforma()==null) || 
             (this.idPlataforma!=null &&
              this.idPlataforma.equals(other.getIdPlataforma()))) &&
            ((this.idTipoPlano==null && other.getIdTipoPlano()==null) || 
             (this.idTipoPlano!=null &&
              this.idTipoPlano.equals(other.getIdTipoPlano())));
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
        if (getIdPlataforma() != null) {
            _hashCode += getIdPlataforma().hashCode();
        }
        if (getIdTipoPlano() != null) {
            _hashCode += getIdTipoPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaTipoPlano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ParametrosBuscarListaTipoPlano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idTipoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
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
