/**
 * ParametrosAtualizarCadastroCliente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP;

public class ParametrosAtualizarCadastroCliente  implements java.io.Serializable {
    private java.lang.String numeroOrdemVenda;

    private br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.DadosCliente dadosCliente;

    public ParametrosAtualizarCadastroCliente() {
    }

    public ParametrosAtualizarCadastroCliente(
           java.lang.String numeroOrdemVenda,
           br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.DadosCliente dadosCliente) {
           this.numeroOrdemVenda = numeroOrdemVenda;
           this.dadosCliente = dadosCliente;
    }


    /**
     * Gets the numeroOrdemVenda value for this ParametrosAtualizarCadastroCliente.
     * 
     * @return numeroOrdemVenda
     */
    public java.lang.String getNumeroOrdemVenda() {
        return numeroOrdemVenda;
    }


    /**
     * Sets the numeroOrdemVenda value for this ParametrosAtualizarCadastroCliente.
     * 
     * @param numeroOrdemVenda
     */
    public void setNumeroOrdemVenda(java.lang.String numeroOrdemVenda) {
        this.numeroOrdemVenda = numeroOrdemVenda;
    }


    /**
     * Gets the dadosCliente value for this ParametrosAtualizarCadastroCliente.
     * 
     * @return dadosCliente
     */
    public br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.DadosCliente getDadosCliente() {
        return dadosCliente;
    }


    /**
     * Sets the dadosCliente value for this ParametrosAtualizarCadastroCliente.
     * 
     * @param dadosCliente
     */
    public void setDadosCliente(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.DadosCliente dadosCliente) {
        this.dadosCliente = dadosCliente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAtualizarCadastroCliente)) return false;
        ParametrosAtualizarCadastroCliente other = (ParametrosAtualizarCadastroCliente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numeroOrdemVenda==null && other.getNumeroOrdemVenda()==null) || 
             (this.numeroOrdemVenda!=null &&
              this.numeroOrdemVenda.equals(other.getNumeroOrdemVenda()))) &&
            ((this.dadosCliente==null && other.getDadosCliente()==null) || 
             (this.dadosCliente!=null &&
              this.dadosCliente.equals(other.getDadosCliente())));
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
        if (getNumeroOrdemVenda() != null) {
            _hashCode += getNumeroOrdemVenda().hashCode();
        }
        if (getDadosCliente() != null) {
            _hashCode += getDadosCliente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAtualizarCadastroCliente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "ParametrosAtualizarCadastroCliente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroOrdemVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "numeroOrdemVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dadosCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "dadosCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "dadosCliente"));
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
