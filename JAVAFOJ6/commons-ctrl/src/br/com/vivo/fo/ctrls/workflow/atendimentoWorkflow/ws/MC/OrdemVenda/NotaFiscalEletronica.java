/**
 * NotaFiscalEletronica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.OrdemVenda;

public class NotaFiscalEletronica implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    /* Chave de acesso gerada no SAP para nota fiscal
     * 						eletronica. */
    private java.math.BigInteger chaveAcesso;

    /* Data em que a chave de acesso foi gerada. */
    private java.util.Date dataCriacaoChaveAcesso;

    public NotaFiscalEletronica() {
    }

    public NotaFiscalEletronica(
            java.math.BigInteger chaveAcesso,
            java.util.Date dataCriacaoChaveAcesso) {
        this.chaveAcesso = chaveAcesso;
        this.dataCriacaoChaveAcesso = dataCriacaoChaveAcesso;
    }

    /**
     * Gets the chaveAcesso value for this NotaFiscalEletronica.
     * 
     * @return chaveAcesso   * Chave de acesso gerada no SAP para nota fiscal
     * 						eletronica.
     */
    public java.math.BigInteger getChaveAcesso() {
        return chaveAcesso;
    }

    /**
     * Sets the chaveAcesso value for this NotaFiscalEletronica.
     * 
     * @param chaveAcesso   * Chave de acesso gerada no SAP para nota fiscal
     * 						eletronica.
     */
    public void setChaveAcesso(java.math.BigInteger chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    /**
     * Gets the dataCriacaoChaveAcesso value for this NotaFiscalEletronica.
     * 
     * @return dataCriacaoChaveAcesso   * Data em que a chave de acesso foi gerada.
     */
    public java.util.Date getDataCriacaoChaveAcesso() {
        return dataCriacaoChaveAcesso;
    }

    /**
     * Sets the dataCriacaoChaveAcesso value for this NotaFiscalEletronica.
     * 
     * @param dataCriacaoChaveAcesso   * Data em que a chave de acesso foi gerada.
     */
    public void setDataCriacaoChaveAcesso(java.util.Date dataCriacaoChaveAcesso) {
        this.dataCriacaoChaveAcesso = dataCriacaoChaveAcesso;
    }
    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NotaFiscalEletronica)) {
            return false;
        }
        NotaFiscalEletronica other = (NotaFiscalEletronica) obj;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true
                && ((this.chaveAcesso == null && other.getChaveAcesso() == null)
                || (this.chaveAcesso != null
                && this.chaveAcesso.equals(other.getChaveAcesso())))
                && ((this.dataCriacaoChaveAcesso == null && other.getDataCriacaoChaveAcesso() == null)
                || (this.dataCriacaoChaveAcesso != null
                && this.dataCriacaoChaveAcesso.equals(other.getDataCriacaoChaveAcesso())));
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
        if (getChaveAcesso() != null) {
            _hashCode += getChaveAcesso().hashCode();
        }
        if (getDataCriacaoChaveAcesso() != null) {
            _hashCode += getDataCriacaoChaveAcesso().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
            new org.apache.axis.description.TypeDesc(NotaFiscalEletronica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "NotaFiscalEletronica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chaveAcesso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "chaveAcesso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCriacaoChaveAcesso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataCriacaoChaveAcesso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
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
        return new org.apache.axis.encoding.ser.BeanSerializer(
                _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
            java.lang.String mechType,
            java.lang.Class _javaType,
            javax.xml.namespace.QName _xmlType) {
        return new org.apache.axis.encoding.ser.BeanDeserializer(
                _javaType, _xmlType, typeDesc);
    }
}
