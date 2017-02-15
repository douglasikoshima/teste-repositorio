/**
 * ParametrosCriarTecnologia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ParametrosCriarTecnologia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosCriarTecnologiaTecnologiaCriacao tecnologiaCriacao;

    public ParametrosCriarTecnologia() {
    }

    public ParametrosCriarTecnologia(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosCriarTecnologiaTecnologiaCriacao tecnologiaCriacao) {
           this.tecnologiaCriacao = tecnologiaCriacao;
    }


    /**
     * Gets the tecnologiaCriacao value for this ParametrosCriarTecnologia.
     * 
     * @return tecnologiaCriacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosCriarTecnologiaTecnologiaCriacao getTecnologiaCriacao() {
        return tecnologiaCriacao;
    }


    /**
     * Sets the tecnologiaCriacao value for this ParametrosCriarTecnologia.
     * 
     * @param tecnologiaCriacao
     */
    public void setTecnologiaCriacao(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosCriarTecnologiaTecnologiaCriacao tecnologiaCriacao) {
        this.tecnologiaCriacao = tecnologiaCriacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosCriarTecnologia)) return false;
        ParametrosCriarTecnologia other = (ParametrosCriarTecnologia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tecnologiaCriacao==null && other.getTecnologiaCriacao()==null) || 
             (this.tecnologiaCriacao!=null &&
              this.tecnologiaCriacao.equals(other.getTecnologiaCriacao())));
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
        if (getTecnologiaCriacao() != null) {
            _hashCode += getTecnologiaCriacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosCriarTecnologia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ParametrosCriarTecnologia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tecnologiaCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "TecnologiaCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">>ParametrosCriarTecnologia>TecnologiaCriacao"));
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
