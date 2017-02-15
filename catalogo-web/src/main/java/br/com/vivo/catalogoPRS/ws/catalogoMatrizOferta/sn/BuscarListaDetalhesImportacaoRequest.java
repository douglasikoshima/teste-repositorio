/**
 * BuscarListaDetalhesImportacaoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class BuscarListaDetalhesImportacaoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaDetalhesImportacao parametrosBuscarListaDetalhesImportacao;

    public BuscarListaDetalhesImportacaoRequest() {
    }

    public BuscarListaDetalhesImportacaoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaDetalhesImportacao parametrosBuscarListaDetalhesImportacao) {
           this.parametrosBuscarListaDetalhesImportacao = parametrosBuscarListaDetalhesImportacao;
    }


    /**
     * Gets the parametrosBuscarListaDetalhesImportacao value for this BuscarListaDetalhesImportacaoRequest.
     * 
     * @return parametrosBuscarListaDetalhesImportacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaDetalhesImportacao getParametrosBuscarListaDetalhesImportacao() {
        return parametrosBuscarListaDetalhesImportacao;
    }


    /**
     * Sets the parametrosBuscarListaDetalhesImportacao value for this BuscarListaDetalhesImportacaoRequest.
     * 
     * @param parametrosBuscarListaDetalhesImportacao
     */
    public void setParametrosBuscarListaDetalhesImportacao(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaDetalhesImportacao parametrosBuscarListaDetalhesImportacao) {
        this.parametrosBuscarListaDetalhesImportacao = parametrosBuscarListaDetalhesImportacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaDetalhesImportacaoRequest)) return false;
        BuscarListaDetalhesImportacaoRequest other = (BuscarListaDetalhesImportacaoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaDetalhesImportacao==null && other.getParametrosBuscarListaDetalhesImportacao()==null) || 
             (this.parametrosBuscarListaDetalhesImportacao!=null &&
              this.parametrosBuscarListaDetalhesImportacao.equals(other.getParametrosBuscarListaDetalhesImportacao())));
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
        if (getParametrosBuscarListaDetalhesImportacao() != null) {
            _hashCode += getParametrosBuscarListaDetalhesImportacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaDetalhesImportacaoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaDetalhesImportacaoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaDetalhesImportacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ParametrosBuscarListaDetalhesImportacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosBuscarListaDetalhesImportacao"));
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
