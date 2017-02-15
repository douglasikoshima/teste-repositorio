/**
 * ParametrosAlterarModeloVenda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class ParametrosAlterarModeloVenda  implements java.io.Serializable {
    private long idModeloVenda;

    private java.lang.String nmModeloVenda;

    public ParametrosAlterarModeloVenda() {
    }

    public ParametrosAlterarModeloVenda(
           long idModeloVenda,
           java.lang.String nmModeloVenda) {
           this.idModeloVenda = idModeloVenda;
           this.nmModeloVenda = nmModeloVenda;
    }


    /**
     * Gets the idModeloVenda value for this ParametrosAlterarModeloVenda.
     * 
     * @return idModeloVenda
     */
    public long getIdModeloVenda() {
        return idModeloVenda;
    }


    /**
     * Sets the idModeloVenda value for this ParametrosAlterarModeloVenda.
     * 
     * @param idModeloVenda
     */
    public void setIdModeloVenda(long idModeloVenda) {
        this.idModeloVenda = idModeloVenda;
    }


    /**
     * Gets the nmModeloVenda value for this ParametrosAlterarModeloVenda.
     * 
     * @return nmModeloVenda
     */
    public java.lang.String getNmModeloVenda() {
        return nmModeloVenda;
    }


    /**
     * Sets the nmModeloVenda value for this ParametrosAlterarModeloVenda.
     * 
     * @param nmModeloVenda
     */
    public void setNmModeloVenda(java.lang.String nmModeloVenda) {
        this.nmModeloVenda = nmModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarModeloVenda)) return false;
        ParametrosAlterarModeloVenda other = (ParametrosAlterarModeloVenda) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idModeloVenda == other.getIdModeloVenda() &&
            ((this.nmModeloVenda==null && other.getNmModeloVenda()==null) || 
             (this.nmModeloVenda!=null &&
              this.nmModeloVenda.equals(other.getNmModeloVenda())));
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
        _hashCode += new Long(getIdModeloVenda()).hashCode();
        if (getNmModeloVenda() != null) {
            _hashCode += getNmModeloVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarModeloVenda.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ParametrosAlterarModeloVenda"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "idModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "nmModeloVenda"));
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
