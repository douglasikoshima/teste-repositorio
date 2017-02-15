/**
 * ParametrosCopiarPerfil.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class ParametrosCopiarPerfil  implements java.io.Serializable {
    private long idPerfilOrigem;

    private long idPerfilDestino;

    private long idSistema;

    public ParametrosCopiarPerfil() {
    }

    public ParametrosCopiarPerfil(
           long idPerfilOrigem,
           long idPerfilDestino,
           long idSistema) {
           this.idPerfilOrigem = idPerfilOrigem;
           this.idPerfilDestino = idPerfilDestino;
           this.idSistema = idSistema;
    }


    /**
     * Gets the idPerfilOrigem value for this ParametrosCopiarPerfil.
     * 
     * @return idPerfilOrigem
     */
    public long getIdPerfilOrigem() {
        return idPerfilOrigem;
    }


    /**
     * Sets the idPerfilOrigem value for this ParametrosCopiarPerfil.
     * 
     * @param idPerfilOrigem
     */
    public void setIdPerfilOrigem(long idPerfilOrigem) {
        this.idPerfilOrigem = idPerfilOrigem;
    }


    /**
     * Gets the idPerfilDestino value for this ParametrosCopiarPerfil.
     * 
     * @return idPerfilDestino
     */
    public long getIdPerfilDestino() {
        return idPerfilDestino;
    }


    /**
     * Sets the idPerfilDestino value for this ParametrosCopiarPerfil.
     * 
     * @param idPerfilDestino
     */
    public void setIdPerfilDestino(long idPerfilDestino) {
        this.idPerfilDestino = idPerfilDestino;
    }


    /**
     * Gets the idSistema value for this ParametrosCopiarPerfil.
     * 
     * @return idSistema
     */
    public long getIdSistema() {
        return idSistema;
    }


    /**
     * Sets the idSistema value for this ParametrosCopiarPerfil.
     * 
     * @param idSistema
     */
    public void setIdSistema(long idSistema) {
        this.idSistema = idSistema;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosCopiarPerfil)) return false;
        ParametrosCopiarPerfil other = (ParametrosCopiarPerfil) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idPerfilOrigem == other.getIdPerfilOrigem() &&
            this.idPerfilDestino == other.getIdPerfilDestino() &&
            this.idSistema == other.getIdSistema();
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
        _hashCode += new Long(getIdPerfilOrigem()).hashCode();
        _hashCode += new Long(getIdPerfilDestino()).hashCode();
        _hashCode += new Long(getIdSistema()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosCopiarPerfil.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosCopiarPerfil"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPerfilOrigem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idPerfilOrigem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPerfilDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idPerfilDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSistema");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idSistema"));
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
