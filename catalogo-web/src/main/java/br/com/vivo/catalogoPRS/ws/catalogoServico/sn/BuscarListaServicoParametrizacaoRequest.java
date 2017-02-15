/**
 * BuscarListaServicoParametrizacaoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class BuscarListaServicoParametrizacaoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoParametrizacao parametrosBuscarListaServicoParametrizacao;

    public BuscarListaServicoParametrizacaoRequest() {
    }

    public BuscarListaServicoParametrizacaoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoParametrizacao parametrosBuscarListaServicoParametrizacao) {
           this.parametrosBuscarListaServicoParametrizacao = parametrosBuscarListaServicoParametrizacao;
    }


    /**
     * Gets the parametrosBuscarListaServicoParametrizacao value for this BuscarListaServicoParametrizacaoRequest.
     * 
     * @return parametrosBuscarListaServicoParametrizacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoParametrizacao getParametrosBuscarListaServicoParametrizacao() {
        return parametrosBuscarListaServicoParametrizacao;
    }


    /**
     * Sets the parametrosBuscarListaServicoParametrizacao value for this BuscarListaServicoParametrizacaoRequest.
     * 
     * @param parametrosBuscarListaServicoParametrizacao
     */
    public void setParametrosBuscarListaServicoParametrizacao(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoParametrizacao parametrosBuscarListaServicoParametrizacao) {
        this.parametrosBuscarListaServicoParametrizacao = parametrosBuscarListaServicoParametrizacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaServicoParametrizacaoRequest)) return false;
        BuscarListaServicoParametrizacaoRequest other = (BuscarListaServicoParametrizacaoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaServicoParametrizacao==null && other.getParametrosBuscarListaServicoParametrizacao()==null) || 
             (this.parametrosBuscarListaServicoParametrizacao!=null &&
              this.parametrosBuscarListaServicoParametrizacao.equals(other.getParametrosBuscarListaServicoParametrizacao())));
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
        if (getParametrosBuscarListaServicoParametrizacao() != null) {
            _hashCode += getParametrosBuscarListaServicoParametrizacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaServicoParametrizacaoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">buscarListaServicoParametrizacaoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaServicoParametrizacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ParametrosBuscarListaServicoParametrizacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosBuscarListaServicoParametrizacao"));
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
