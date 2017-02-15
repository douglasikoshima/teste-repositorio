/**
 * ParametrosBuscarListaCarteira.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn;

public class ParametrosBuscarListaCarteira  implements java.io.Serializable {
    private java.lang.Long idTipoCliente;

    public ParametrosBuscarListaCarteira() {
    }

    public ParametrosBuscarListaCarteira(
           java.lang.Long idTipoCliente) {
           this.idTipoCliente = idTipoCliente;
    }


    /**
     * Gets the idTipoCliente value for this ParametrosBuscarListaCarteira.
     * 
     * @return idTipoCliente
     */
    public java.lang.Long getIdTipoCliente() {
        return idTipoCliente;
    }


    /**
     * Sets the idTipoCliente value for this ParametrosBuscarListaCarteira.
     * 
     * @param idTipoCliente
     */
    public void setIdTipoCliente(java.lang.Long idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaCarteira)) return false;
        ParametrosBuscarListaCarteira other = (ParametrosBuscarListaCarteira) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idTipoCliente==null && other.getIdTipoCliente()==null) || 
             (this.idTipoCliente!=null &&
              this.idTipoCliente.equals(other.getIdTipoCliente())));
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
        if (getIdTipoCliente() != null) {
            _hashCode += getIdTipoCliente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaCarteira.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCarteira", ">ParametrosBuscarListaCarteira"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCarteira", "idTipoCliente"));
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
