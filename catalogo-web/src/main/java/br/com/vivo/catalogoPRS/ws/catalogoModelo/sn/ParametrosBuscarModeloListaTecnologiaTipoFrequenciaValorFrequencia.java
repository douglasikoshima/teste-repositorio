/**
 * ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscaCaracteristicaProdutoModelo buscaCaracteristicaProdutoModelo;

    public ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia() {
    }

    public ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscaCaracteristicaProdutoModelo buscaCaracteristicaProdutoModelo) {
           this.buscaCaracteristicaProdutoModelo = buscaCaracteristicaProdutoModelo;
    }


    /**
     * Gets the buscaCaracteristicaProdutoModelo value for this ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia.
     * 
     * @return buscaCaracteristicaProdutoModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscaCaracteristicaProdutoModelo getBuscaCaracteristicaProdutoModelo() {
        return buscaCaracteristicaProdutoModelo;
    }


    /**
     * Sets the buscaCaracteristicaProdutoModelo value for this ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia.
     * 
     * @param buscaCaracteristicaProdutoModelo
     */
    public void setBuscaCaracteristicaProdutoModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscaCaracteristicaProdutoModelo buscaCaracteristicaProdutoModelo) {
        this.buscaCaracteristicaProdutoModelo = buscaCaracteristicaProdutoModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia)) return false;
        ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia other = (ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.buscaCaracteristicaProdutoModelo==null && other.getBuscaCaracteristicaProdutoModelo()==null) || 
             (this.buscaCaracteristicaProdutoModelo!=null &&
              this.buscaCaracteristicaProdutoModelo.equals(other.getBuscaCaracteristicaProdutoModelo())));
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
        if (getBuscaCaracteristicaProdutoModelo() != null) {
            _hashCode += getBuscaCaracteristicaProdutoModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buscaCaracteristicaProdutoModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "BuscaCaracteristicaProdutoModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">BuscaCaracteristicaProdutoModelo"));
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