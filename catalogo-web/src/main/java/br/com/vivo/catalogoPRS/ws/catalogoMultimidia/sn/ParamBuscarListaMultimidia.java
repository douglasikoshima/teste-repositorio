/**
 * ParamBuscarListaMultimidia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class ParamBuscarListaMultimidia  implements java.io.Serializable {
    private java.lang.Long idMultimidia;

    public ParamBuscarListaMultimidia() {
    }

    public ParamBuscarListaMultimidia(
           java.lang.Long idMultimidia) {
           this.idMultimidia = idMultimidia;
    }


    /**
     * Gets the idMultimidia value for this ParamBuscarListaMultimidia.
     * 
     * @return idMultimidia
     */
    public java.lang.Long getIdMultimidia() {
        return idMultimidia;
    }


    /**
     * Sets the idMultimidia value for this ParamBuscarListaMultimidia.
     * 
     * @param idMultimidia
     */
    public void setIdMultimidia(java.lang.Long idMultimidia) {
        this.idMultimidia = idMultimidia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamBuscarListaMultimidia)) return false;
        ParamBuscarListaMultimidia other = (ParamBuscarListaMultimidia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idMultimidia==null && other.getIdMultimidia()==null) || 
             (this.idMultimidia!=null &&
              this.idMultimidia.equals(other.getIdMultimidia())));
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
        if (getIdMultimidia() != null) {
            _hashCode += getIdMultimidia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamBuscarListaMultimidia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">ParamBuscarListaMultimidia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "idMultimidia"));
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
