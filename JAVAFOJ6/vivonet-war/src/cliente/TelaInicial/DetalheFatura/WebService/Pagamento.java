/**
 * Pagamento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cliente.TelaInicial.DetalheFatura.WebService;


/**
 * Mantem informacoes dos pagamentos das faturas
 */
public class Pagamento  implements java.io.Serializable {
    private java.lang.String dataPagamento;

    /* Valor pago pela fatura que pode ser diferente do
     * 						valor da fatura. */
    private java.math.BigDecimal valorPagamento;

    /* Referencia a entidade TipoPagamento */
    private TipoPagamento tipoPagamento;

    private java.util.Date dataReversaoPagamento;

    /* Valor da reversao do pagamento */
    private java.math.BigDecimal valorReversaoPagamento;

    public Pagamento() {
    }

    public Pagamento(
           java.lang.String dataPagamento,
           java.math.BigDecimal valorPagamento,
           TipoPagamento tipoPagamento,
           java.util.Date dataReversaoPagamento,
           java.math.BigDecimal valorReversaoPagamento) {
           this.dataPagamento = dataPagamento;
           this.valorPagamento = valorPagamento;
           this.tipoPagamento = tipoPagamento;
           this.dataReversaoPagamento = dataReversaoPagamento;
           this.valorReversaoPagamento = valorReversaoPagamento;
    }


    /**
     * Gets the dataPagamento value for this Pagamento.
     * 
     * @return dataPagamento
     */
    public java.lang.String getDataPagamento() {
        return dataPagamento;
    }


    /**
     * Sets the dataPagamento value for this Pagamento.
     * 
     * @param dataPagamento
     */
    public void setDataPagamento(java.lang.String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }


    /**
     * Gets the valorPagamento value for this Pagamento.
     * 
     * @return valorPagamento   * Valor pago pela fatura que pode ser diferente do
     * 						valor da fatura.
     */
    public java.math.BigDecimal getValorPagamento() {
        return valorPagamento;
    }


    /**
     * Sets the valorPagamento value for this Pagamento.
     * 
     * @param valorPagamento   * Valor pago pela fatura que pode ser diferente do
     * 						valor da fatura.
     */
    public void setValorPagamento(java.math.BigDecimal valorPagamento) {
        this.valorPagamento = valorPagamento;
    }


    /**
     * Gets the tipoPagamento value for this Pagamento.
     * 
     * @return tipoPagamento   * Referencia a entidade TipoPagamento
     */
    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }


    /**
     * Sets the tipoPagamento value for this Pagamento.
     * 
     * @param tipoPagamento   * Referencia a entidade TipoPagamento
     */
    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }


    /**
     * Gets the dataReversaoPagamento value for this Pagamento.
     * 
     * @return dataReversaoPagamento
     */
    public java.util.Date getDataReversaoPagamento() {
        return dataReversaoPagamento;
    }


    /**
     * Sets the dataReversaoPagamento value for this Pagamento.
     * 
     * @param dataReversaoPagamento
     */
    public void setDataReversaoPagamento(java.util.Date dataReversaoPagamento) {
        this.dataReversaoPagamento = dataReversaoPagamento;
    }


    /**
     * Gets the valorReversaoPagamento value for this Pagamento.
     * 
     * @return valorReversaoPagamento   * Valor da reversao do pagamento
     */
    public java.math.BigDecimal getValorReversaoPagamento() {
        return valorReversaoPagamento;
    }


    /**
     * Sets the valorReversaoPagamento value for this Pagamento.
     * 
     * @param valorReversaoPagamento   * Valor da reversao do pagamento
     */
    public void setValorReversaoPagamento(java.math.BigDecimal valorReversaoPagamento) {
        this.valorReversaoPagamento = valorReversaoPagamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Pagamento)) return false;
        Pagamento other = (Pagamento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataPagamento==null && other.getDataPagamento()==null) || 
             (this.dataPagamento!=null &&
              this.dataPagamento.equals(other.getDataPagamento()))) &&
            ((this.valorPagamento==null && other.getValorPagamento()==null) || 
             (this.valorPagamento!=null &&
              this.valorPagamento.equals(other.getValorPagamento()))) &&
            ((this.tipoPagamento==null && other.getTipoPagamento()==null) || 
             (this.tipoPagamento!=null &&
              this.tipoPagamento.equals(other.getTipoPagamento()))) &&
            ((this.dataReversaoPagamento==null && other.getDataReversaoPagamento()==null) || 
             (this.dataReversaoPagamento!=null &&
              this.dataReversaoPagamento.equals(other.getDataReversaoPagamento()))) &&
            ((this.valorReversaoPagamento==null && other.getValorReversaoPagamento()==null) || 
             (this.valorReversaoPagamento!=null &&
              this.valorReversaoPagamento.equals(other.getValorReversaoPagamento())));
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
        if (getDataPagamento() != null) {
            _hashCode += getDataPagamento().hashCode();
        }
        if (getValorPagamento() != null) {
            _hashCode += getValorPagamento().hashCode();
        }
        if (getTipoPagamento() != null) {
            _hashCode += getTipoPagamento().hashCode();
        }
        if (getDataReversaoPagamento() != null) {
            _hashCode += getDataReversaoPagamento().hashCode();
        }
        if (getValorReversaoPagamento() != null) {
            _hashCode += getValorReversaoPagamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Pagamento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "Pagamento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "valorPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "tipoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "TipoPagamento"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataReversaoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataReversaoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorReversaoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "valorReversaoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
