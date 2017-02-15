/**
 * ListaModeloTecnologiaModeloTecnologia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ListaModeloTecnologiaModeloTecnologia  implements java.io.Serializable {
    private long idModeloTecn;

    private long idModelo;

    private long idTecnologia;

    public ListaModeloTecnologiaModeloTecnologia() {
    }

    public ListaModeloTecnologiaModeloTecnologia(
           long idModeloTecn,
           long idModelo,
           long idTecnologia) {
           this.idModeloTecn = idModeloTecn;
           this.idModelo = idModelo;
           this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the idModeloTecn value for this ListaModeloTecnologiaModeloTecnologia.
     * 
     * @return idModeloTecn
     */
    public long getIdModeloTecn() {
        return idModeloTecn;
    }


    /**
     * Sets the idModeloTecn value for this ListaModeloTecnologiaModeloTecnologia.
     * 
     * @param idModeloTecn
     */
    public void setIdModeloTecn(long idModeloTecn) {
        this.idModeloTecn = idModeloTecn;
    }


    /**
     * Gets the idModelo value for this ListaModeloTecnologiaModeloTecnologia.
     * 
     * @return idModelo
     */
    public long getIdModelo() {
        return idModelo;
    }


    /**
     * Sets the idModelo value for this ListaModeloTecnologiaModeloTecnologia.
     * 
     * @param idModelo
     */
    public void setIdModelo(long idModelo) {
        this.idModelo = idModelo;
    }


    /**
     * Gets the idTecnologia value for this ListaModeloTecnologiaModeloTecnologia.
     * 
     * @return idTecnologia
     */
    public long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ListaModeloTecnologiaModeloTecnologia.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaModeloTecnologiaModeloTecnologia)) return false;
        ListaModeloTecnologiaModeloTecnologia other = (ListaModeloTecnologiaModeloTecnologia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idModeloTecn == other.getIdModeloTecn() &&
            this.idModelo == other.getIdModelo() &&
            this.idTecnologia == other.getIdTecnologia();
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
        _hashCode += new Long(getIdModeloTecn()).hashCode();
        _hashCode += new Long(getIdModelo()).hashCode();
        _hashCode += new Long(getIdTecnologia()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaModeloTecnologiaModeloTecnologia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ListaModeloTecnologia>ModeloTecnologia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModeloTecn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idModeloTecn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTecnologia"));
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
