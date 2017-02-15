/**
 * ResultadorBuscarQtdeRestricaoPerfil.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class ResultadorBuscarQtdeRestricaoPerfil  implements java.io.Serializable {
    private long qtdeRestricao;

    public ResultadorBuscarQtdeRestricaoPerfil() {
    }

    public ResultadorBuscarQtdeRestricaoPerfil(
           long qtdeRestricao) {
           this.qtdeRestricao = qtdeRestricao;
    }


    /**
     * Gets the qtdeRestricao value for this ResultadorBuscarQtdeRestricaoPerfil.
     * 
     * @return qtdeRestricao
     */
    public long getQtdeRestricao() {
        return qtdeRestricao;
    }


    /**
     * Sets the qtdeRestricao value for this ResultadorBuscarQtdeRestricaoPerfil.
     * 
     * @param qtdeRestricao
     */
    public void setQtdeRestricao(long qtdeRestricao) {
        this.qtdeRestricao = qtdeRestricao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadorBuscarQtdeRestricaoPerfil)) return false;
        ResultadorBuscarQtdeRestricaoPerfil other = (ResultadorBuscarQtdeRestricaoPerfil) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.qtdeRestricao == other.getQtdeRestricao();
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
        _hashCode += new Long(getQtdeRestricao()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadorBuscarQtdeRestricaoPerfil.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ResultadorBuscarQtdeRestricaoPerfil"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdeRestricao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "qtdeRestricao"));
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
