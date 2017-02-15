/**
 * BuscarListaComposicaoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class BuscarListaComposicaoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosBuscarListaComposicao parametrosBuscarListaComposicao;

    public BuscarListaComposicaoRequest() {
    }

    public BuscarListaComposicaoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosBuscarListaComposicao parametrosBuscarListaComposicao) {
           this.parametrosBuscarListaComposicao = parametrosBuscarListaComposicao;
    }


    /**
     * Gets the parametrosBuscarListaComposicao value for this BuscarListaComposicaoRequest.
     * 
     * @return parametrosBuscarListaComposicao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosBuscarListaComposicao getParametrosBuscarListaComposicao() {
        return parametrosBuscarListaComposicao;
    }


    /**
     * Sets the parametrosBuscarListaComposicao value for this BuscarListaComposicaoRequest.
     * 
     * @param parametrosBuscarListaComposicao
     */
    public void setParametrosBuscarListaComposicao(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosBuscarListaComposicao parametrosBuscarListaComposicao) {
        this.parametrosBuscarListaComposicao = parametrosBuscarListaComposicao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaComposicaoRequest)) return false;
        BuscarListaComposicaoRequest other = (BuscarListaComposicaoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaComposicao==null && other.getParametrosBuscarListaComposicao()==null) || 
             (this.parametrosBuscarListaComposicao!=null &&
              this.parametrosBuscarListaComposicao.equals(other.getParametrosBuscarListaComposicao())));
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
        if (getParametrosBuscarListaComposicao() != null) {
            _hashCode += getParametrosBuscarListaComposicao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaComposicaoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">buscarListaComposicaoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaComposicao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ParametrosBuscarListaComposicao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">ParametrosBuscarListaComposicao"));
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
