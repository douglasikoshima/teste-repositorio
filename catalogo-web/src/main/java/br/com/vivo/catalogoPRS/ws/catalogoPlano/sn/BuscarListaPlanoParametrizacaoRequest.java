/**
 * BuscarListaPlanoParametrizacaoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class BuscarListaPlanoParametrizacaoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlanoParametrizacao parametrosBuscarListaPlanoParametrizacao;

    public BuscarListaPlanoParametrizacaoRequest() {
    }

    public BuscarListaPlanoParametrizacaoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlanoParametrizacao parametrosBuscarListaPlanoParametrizacao) {
           this.parametrosBuscarListaPlanoParametrizacao = parametrosBuscarListaPlanoParametrizacao;
    }


    /**
     * Gets the parametrosBuscarListaPlanoParametrizacao value for this BuscarListaPlanoParametrizacaoRequest.
     * 
     * @return parametrosBuscarListaPlanoParametrizacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlanoParametrizacao getParametrosBuscarListaPlanoParametrizacao() {
        return parametrosBuscarListaPlanoParametrizacao;
    }


    /**
     * Sets the parametrosBuscarListaPlanoParametrizacao value for this BuscarListaPlanoParametrizacaoRequest.
     * 
     * @param parametrosBuscarListaPlanoParametrizacao
     */
    public void setParametrosBuscarListaPlanoParametrizacao(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlanoParametrizacao parametrosBuscarListaPlanoParametrizacao) {
        this.parametrosBuscarListaPlanoParametrizacao = parametrosBuscarListaPlanoParametrizacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaPlanoParametrizacaoRequest)) return false;
        BuscarListaPlanoParametrizacaoRequest other = (BuscarListaPlanoParametrizacaoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaPlanoParametrizacao==null && other.getParametrosBuscarListaPlanoParametrizacao()==null) || 
             (this.parametrosBuscarListaPlanoParametrizacao!=null &&
              this.parametrosBuscarListaPlanoParametrizacao.equals(other.getParametrosBuscarListaPlanoParametrizacao())));
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
        if (getParametrosBuscarListaPlanoParametrizacao() != null) {
            _hashCode += getParametrosBuscarListaPlanoParametrizacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaPlanoParametrizacaoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">buscarListaPlanoParametrizacaoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaPlanoParametrizacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ParametrosBuscarListaPlanoParametrizacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ParametrosBuscarListaPlanoParametrizacao"));
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
