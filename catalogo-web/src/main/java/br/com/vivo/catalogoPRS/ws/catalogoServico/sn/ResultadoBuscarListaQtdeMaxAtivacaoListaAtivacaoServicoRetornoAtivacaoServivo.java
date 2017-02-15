/**
 * ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo  implements java.io.Serializable {
    private long qtdeMaxAtivacao;

    public ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo() {
    }

    public ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo(
           long qtdeMaxAtivacao) {
           this.qtdeMaxAtivacao = qtdeMaxAtivacao;
    }


    /**
     * Gets the qtdeMaxAtivacao value for this ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo.
     * 
     * @return qtdeMaxAtivacao
     */
    public long getQtdeMaxAtivacao() {
        return qtdeMaxAtivacao;
    }


    /**
     * Sets the qtdeMaxAtivacao value for this ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo.
     * 
     * @param qtdeMaxAtivacao
     */
    public void setQtdeMaxAtivacao(long qtdeMaxAtivacao) {
        this.qtdeMaxAtivacao = qtdeMaxAtivacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo)) return false;
        ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo other = (ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.qtdeMaxAtivacao == other.getQtdeMaxAtivacao();
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
        _hashCode += new Long(getQtdeMaxAtivacao()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>ResultadoBuscarListaQtdeMaxAtivacao>ListaAtivacaoServico>RetornoAtivacaoServivo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdeMaxAtivacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdeMaxAtivacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
