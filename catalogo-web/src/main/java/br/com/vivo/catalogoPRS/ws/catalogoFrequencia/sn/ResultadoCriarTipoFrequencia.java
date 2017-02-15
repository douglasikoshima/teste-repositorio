/**
 * ResultadoCriarTipoFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ResultadoCriarTipoFrequencia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoCriarTipoFrequenciaVlFrequenciaCriacao_KEY vlFrequenciaCriacao_KEY;

    public ResultadoCriarTipoFrequencia() {
    }

    public ResultadoCriarTipoFrequencia(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoCriarTipoFrequenciaVlFrequenciaCriacao_KEY vlFrequenciaCriacao_KEY) {
           this.vlFrequenciaCriacao_KEY = vlFrequenciaCriacao_KEY;
    }


    /**
     * Gets the vlFrequenciaCriacao_KEY value for this ResultadoCriarTipoFrequencia.
     * 
     * @return vlFrequenciaCriacao_KEY
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoCriarTipoFrequenciaVlFrequenciaCriacao_KEY getVlFrequenciaCriacao_KEY() {
        return vlFrequenciaCriacao_KEY;
    }


    /**
     * Sets the vlFrequenciaCriacao_KEY value for this ResultadoCriarTipoFrequencia.
     * 
     * @param vlFrequenciaCriacao_KEY
     */
    public void setVlFrequenciaCriacao_KEY(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoCriarTipoFrequenciaVlFrequenciaCriacao_KEY vlFrequenciaCriacao_KEY) {
        this.vlFrequenciaCriacao_KEY = vlFrequenciaCriacao_KEY;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoCriarTipoFrequencia)) return false;
        ResultadoCriarTipoFrequencia other = (ResultadoCriarTipoFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.vlFrequenciaCriacao_KEY==null && other.getVlFrequenciaCriacao_KEY()==null) || 
             (this.vlFrequenciaCriacao_KEY!=null &&
              this.vlFrequenciaCriacao_KEY.equals(other.getVlFrequenciaCriacao_KEY())));
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
        if (getVlFrequenciaCriacao_KEY() != null) {
            _hashCode += getVlFrequenciaCriacao_KEY().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoCriarTipoFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ResultadoCriarTipoFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vlFrequenciaCriacao_KEY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "VlFrequenciaCriacao_KEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">>ResultadoCriarTipoFrequencia>VlFrequenciaCriacao_KEY"));
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
