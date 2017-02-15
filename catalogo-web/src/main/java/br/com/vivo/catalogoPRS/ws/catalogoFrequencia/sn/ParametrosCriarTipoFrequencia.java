/**
 * ParametrosCriarTipoFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ParametrosCriarTipoFrequencia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosCriarTipoFrequenciaTipoFrequenciaCriacao tipoFrequenciaCriacao;

    public ParametrosCriarTipoFrequencia() {
    }

    public ParametrosCriarTipoFrequencia(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosCriarTipoFrequenciaTipoFrequenciaCriacao tipoFrequenciaCriacao) {
           this.tipoFrequenciaCriacao = tipoFrequenciaCriacao;
    }


    /**
     * Gets the tipoFrequenciaCriacao value for this ParametrosCriarTipoFrequencia.
     * 
     * @return tipoFrequenciaCriacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosCriarTipoFrequenciaTipoFrequenciaCriacao getTipoFrequenciaCriacao() {
        return tipoFrequenciaCriacao;
    }


    /**
     * Sets the tipoFrequenciaCriacao value for this ParametrosCriarTipoFrequencia.
     * 
     * @param tipoFrequenciaCriacao
     */
    public void setTipoFrequenciaCriacao(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosCriarTipoFrequenciaTipoFrequenciaCriacao tipoFrequenciaCriacao) {
        this.tipoFrequenciaCriacao = tipoFrequenciaCriacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosCriarTipoFrequencia)) return false;
        ParametrosCriarTipoFrequencia other = (ParametrosCriarTipoFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoFrequenciaCriacao==null && other.getTipoFrequenciaCriacao()==null) || 
             (this.tipoFrequenciaCriacao!=null &&
              this.tipoFrequenciaCriacao.equals(other.getTipoFrequenciaCriacao())));
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
        if (getTipoFrequenciaCriacao() != null) {
            _hashCode += getTipoFrequenciaCriacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosCriarTipoFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ParametrosCriarTipoFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoFrequenciaCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "TipoFrequenciaCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">>ParametrosCriarTipoFrequencia>TipoFrequenciaCriacao"));
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
