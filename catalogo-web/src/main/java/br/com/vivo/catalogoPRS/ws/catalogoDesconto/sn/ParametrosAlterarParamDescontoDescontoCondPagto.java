/**
 * ParametrosAlterarParamDescontoDescontoCondPagto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public class ParametrosAlterarParamDescontoDescontoCondPagto  implements java.io.Serializable {
    private long idCondicaoPagamento;

    private java.lang.Float fatorPreco;

    public ParametrosAlterarParamDescontoDescontoCondPagto() {
    }

    public ParametrosAlterarParamDescontoDescontoCondPagto(
           long idCondicaoPagamento,
           java.lang.Float fatorPreco) {
           this.idCondicaoPagamento = idCondicaoPagamento;
           this.fatorPreco = fatorPreco;
    }


    /**
     * Gets the idCondicaoPagamento value for this ParametrosAlterarParamDescontoDescontoCondPagto.
     * 
     * @return idCondicaoPagamento
     */
    public long getIdCondicaoPagamento() {
        return idCondicaoPagamento;
    }


    /**
     * Sets the idCondicaoPagamento value for this ParametrosAlterarParamDescontoDescontoCondPagto.
     * 
     * @param idCondicaoPagamento
     */
    public void setIdCondicaoPagamento(long idCondicaoPagamento) {
        this.idCondicaoPagamento = idCondicaoPagamento;
    }


    /**
     * Gets the fatorPreco value for this ParametrosAlterarParamDescontoDescontoCondPagto.
     * 
     * @return fatorPreco
     */
    public java.lang.Float getFatorPreco() {
        return fatorPreco;
    }


    /**
     * Sets the fatorPreco value for this ParametrosAlterarParamDescontoDescontoCondPagto.
     * 
     * @param fatorPreco
     */
    public void setFatorPreco(java.lang.Float fatorPreco) {
        this.fatorPreco = fatorPreco;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarParamDescontoDescontoCondPagto)) return false;
        ParametrosAlterarParamDescontoDescontoCondPagto other = (ParametrosAlterarParamDescontoDescontoCondPagto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idCondicaoPagamento == other.getIdCondicaoPagamento() &&
            ((this.fatorPreco==null && other.getFatorPreco()==null) || 
             (this.fatorPreco!=null &&
              this.fatorPreco.equals(other.getFatorPreco())));
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
        _hashCode += new Long(getIdCondicaoPagamento()).hashCode();
        if (getFatorPreco() != null) {
            _hashCode += getFatorPreco().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarParamDescontoDescontoCondPagto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">>ParametrosAlterarParamDesconto>DescontoCondPagto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCondicaoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IdCondicaoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fatorPreco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FatorPreco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
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
