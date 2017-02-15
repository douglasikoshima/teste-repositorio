/**
 * ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica  implements java.io.Serializable {
    private long idCaracteristica;

    private java.lang.Long idValorCaracteristica;

    public ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica() {
    }

    public ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica(
           long idCaracteristica,
           java.lang.Long idValorCaracteristica) {
           this.idCaracteristica = idCaracteristica;
           this.idValorCaracteristica = idValorCaracteristica;
    }


    /**
     * Gets the idCaracteristica value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica.
     * 
     * @return idCaracteristica
     */
    public long getIdCaracteristica() {
        return idCaracteristica;
    }


    /**
     * Sets the idCaracteristica value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica.
     * 
     * @param idCaracteristica
     */
    public void setIdCaracteristica(long idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }


    /**
     * Gets the idValorCaracteristica value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica.
     * 
     * @return idValorCaracteristica
     */
    public java.lang.Long getIdValorCaracteristica() {
        return idValorCaracteristica;
    }


    /**
     * Sets the idValorCaracteristica value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica.
     * 
     * @param idValorCaracteristica
     */
    public void setIdValorCaracteristica(java.lang.Long idValorCaracteristica) {
        this.idValorCaracteristica = idValorCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica)) return false;
        ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica other = (ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idCaracteristica == other.getIdCaracteristica() &&
            ((this.idValorCaracteristica==null && other.getIdValorCaracteristica()==null) || 
             (this.idValorCaracteristica!=null &&
              this.idValorCaracteristica.equals(other.getIdValorCaracteristica())));
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
        _hashCode += new Long(getIdCaracteristica()).hashCode();
        if (getIdValorCaracteristica() != null) {
            _hashCode += getIdValorCaracteristica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>ParametrosAssociarModeloCaracteristica>ParametrosAssociarModeloCaracteristica>ValorCaracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idValorCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idValorCaracteristica"));
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
