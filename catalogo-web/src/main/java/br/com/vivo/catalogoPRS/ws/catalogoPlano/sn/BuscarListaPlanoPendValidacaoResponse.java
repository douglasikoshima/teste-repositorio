/**
 * BuscarListaPlanoPendValidacaoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class BuscarListaPlanoPendValidacaoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacao resultadoBuscarListaPlanoPendValidacao;

    public BuscarListaPlanoPendValidacaoResponse() {
    }

    public BuscarListaPlanoPendValidacaoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacao resultadoBuscarListaPlanoPendValidacao) {
           this.resultadoBuscarListaPlanoPendValidacao = resultadoBuscarListaPlanoPendValidacao;
    }


    /**
     * Gets the resultadoBuscarListaPlanoPendValidacao value for this BuscarListaPlanoPendValidacaoResponse.
     * 
     * @return resultadoBuscarListaPlanoPendValidacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacao getResultadoBuscarListaPlanoPendValidacao() {
        return resultadoBuscarListaPlanoPendValidacao;
    }


    /**
     * Sets the resultadoBuscarListaPlanoPendValidacao value for this BuscarListaPlanoPendValidacaoResponse.
     * 
     * @param resultadoBuscarListaPlanoPendValidacao
     */
    public void setResultadoBuscarListaPlanoPendValidacao(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacao resultadoBuscarListaPlanoPendValidacao) {
        this.resultadoBuscarListaPlanoPendValidacao = resultadoBuscarListaPlanoPendValidacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaPlanoPendValidacaoResponse)) return false;
        BuscarListaPlanoPendValidacaoResponse other = (BuscarListaPlanoPendValidacaoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaPlanoPendValidacao==null && other.getResultadoBuscarListaPlanoPendValidacao()==null) || 
             (this.resultadoBuscarListaPlanoPendValidacao!=null &&
              this.resultadoBuscarListaPlanoPendValidacao.equals(other.getResultadoBuscarListaPlanoPendValidacao())));
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
        if (getResultadoBuscarListaPlanoPendValidacao() != null) {
            _hashCode += getResultadoBuscarListaPlanoPendValidacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaPlanoPendValidacaoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">buscarListaPlanoPendValidacaoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaPlanoPendValidacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ResultadoBuscarListaPlanoPendValidacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ResultadoBuscarListaPlanoPendValidacao"));
        elemField.setMinOccurs(0);
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
