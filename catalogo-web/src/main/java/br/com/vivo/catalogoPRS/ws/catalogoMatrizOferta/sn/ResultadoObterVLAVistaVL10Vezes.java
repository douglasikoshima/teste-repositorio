/**
 * ResultadoObterVLAVistaVL10Vezes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ResultadoObterVLAVistaVL10Vezes  implements java.io.Serializable {
    private java.math.BigDecimal valorParcelado;

    public ResultadoObterVLAVistaVL10Vezes() {
    }

    public ResultadoObterVLAVistaVL10Vezes(
           java.math.BigDecimal valorParcelado) {
           this.valorParcelado = valorParcelado;
    }


    /**
     * Gets the valorParcelado value for this ResultadoObterVLAVistaVL10Vezes.
     * 
     * @return valorParcelado
     */
    public java.math.BigDecimal getValorParcelado() {
        return valorParcelado;
    }


    /**
     * Sets the valorParcelado value for this ResultadoObterVLAVistaVL10Vezes.
     * 
     * @param valorParcelado
     */
    public void setValorParcelado(java.math.BigDecimal valorParcelado) {
        this.valorParcelado = valorParcelado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoObterVLAVistaVL10Vezes)) return false;
        ResultadoObterVLAVistaVL10Vezes other = (ResultadoObterVLAVistaVL10Vezes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.valorParcelado==null && other.getValorParcelado()==null) || 
             (this.valorParcelado!=null &&
              this.valorParcelado.equals(other.getValorParcelado())));
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
        if (getValorParcelado() != null) {
            _hashCode += getValorParcelado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoObterVLAVistaVL10Vezes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoObterVLAVistaVL10Vezes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorParcelado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "valorParcelado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
