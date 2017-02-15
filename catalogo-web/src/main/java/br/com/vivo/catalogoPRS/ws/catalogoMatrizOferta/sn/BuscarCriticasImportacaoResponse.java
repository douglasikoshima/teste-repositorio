/**
 * BuscarCriticasImportacaoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class BuscarCriticasImportacaoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarCriticasImportacao resultadoBuscarCriticasImportacao;

    public BuscarCriticasImportacaoResponse() {
    }

    public BuscarCriticasImportacaoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarCriticasImportacao resultadoBuscarCriticasImportacao) {
           this.resultadoBuscarCriticasImportacao = resultadoBuscarCriticasImportacao;
    }


    /**
     * Gets the resultadoBuscarCriticasImportacao value for this BuscarCriticasImportacaoResponse.
     * 
     * @return resultadoBuscarCriticasImportacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarCriticasImportacao getResultadoBuscarCriticasImportacao() {
        return resultadoBuscarCriticasImportacao;
    }


    /**
     * Sets the resultadoBuscarCriticasImportacao value for this BuscarCriticasImportacaoResponse.
     * 
     * @param resultadoBuscarCriticasImportacao
     */
    public void setResultadoBuscarCriticasImportacao(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarCriticasImportacao resultadoBuscarCriticasImportacao) {
        this.resultadoBuscarCriticasImportacao = resultadoBuscarCriticasImportacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarCriticasImportacaoResponse)) return false;
        BuscarCriticasImportacaoResponse other = (BuscarCriticasImportacaoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarCriticasImportacao==null && other.getResultadoBuscarCriticasImportacao()==null) || 
             (this.resultadoBuscarCriticasImportacao!=null &&
              this.resultadoBuscarCriticasImportacao.equals(other.getResultadoBuscarCriticasImportacao())));
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
        if (getResultadoBuscarCriticasImportacao() != null) {
            _hashCode += getResultadoBuscarCriticasImportacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarCriticasImportacaoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarCriticasImportacaoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarCriticasImportacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ResultadoBuscarCriticasImportacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoBuscarCriticasImportacao"));
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
