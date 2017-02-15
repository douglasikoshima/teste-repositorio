/**
 * TrackingAparelho.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda;

public class TrackingAparelho  implements java.io.Serializable {
    /* Data em que o aparelho eh separado no estoque. */
    private java.util.Calendar dataPicking;

    /* Data confirmacao de picking. */
    private java.util.Calendar dataConfirmacaoPicking;

    /* Data em que o aparelho eh separado no estoque. */
    private java.util.Calendar dataFornecimento;

    /* Numero do picking */
    private java.math.BigInteger numeroPicking;

    /* Numero de fornecimento */
    private java.math.BigInteger numeroFornecimento;

    /* Data de saida da mercadoria. */
    private java.util.Calendar dataSaidaMercadoria;

    public TrackingAparelho() {
    }

    public TrackingAparelho(
           java.util.Calendar dataPicking,
           java.util.Calendar dataConfirmacaoPicking,
           java.util.Calendar dataFornecimento,
           java.math.BigInteger numeroPicking,
           java.math.BigInteger numeroFornecimento,
           java.util.Calendar dataSaidaMercadoria) {
           this.dataPicking = dataPicking;
           this.dataConfirmacaoPicking = dataConfirmacaoPicking;
           this.dataFornecimento = dataFornecimento;
           this.numeroPicking = numeroPicking;
           this.numeroFornecimento = numeroFornecimento;
           this.dataSaidaMercadoria = dataSaidaMercadoria;
    }


    /**
     * Gets the dataPicking value for this TrackingAparelho.
     * 
     * @return dataPicking   * Data em que o aparelho eh separado no estoque.
     */
    public java.util.Calendar getDataPicking() {
        return dataPicking;
    }


    /**
     * Sets the dataPicking value for this TrackingAparelho.
     * 
     * @param dataPicking   * Data em que o aparelho eh separado no estoque.
     */
    public void setDataPicking(java.util.Calendar dataPicking) {
        this.dataPicking = dataPicking;
    }


    /**
     * Gets the dataConfirmacaoPicking value for this TrackingAparelho.
     * 
     * @return dataConfirmacaoPicking   * Data confirmacao de picking.
     */
    public java.util.Calendar getDataConfirmacaoPicking() {
        return dataConfirmacaoPicking;
    }


    /**
     * Sets the dataConfirmacaoPicking value for this TrackingAparelho.
     * 
     * @param dataConfirmacaoPicking   * Data confirmacao de picking.
     */
    public void setDataConfirmacaoPicking(java.util.Calendar dataConfirmacaoPicking) {
        this.dataConfirmacaoPicking = dataConfirmacaoPicking;
    }


    /**
     * Gets the dataFornecimento value for this TrackingAparelho.
     * 
     * @return dataFornecimento   * Data em que o aparelho eh separado no estoque.
     */
    public java.util.Calendar getDataFornecimento() {
        return dataFornecimento;
    }


    /**
     * Sets the dataFornecimento value for this TrackingAparelho.
     * 
     * @param dataFornecimento   * Data em que o aparelho eh separado no estoque.
     */
    public void setDataFornecimento(java.util.Calendar dataFornecimento) {
        this.dataFornecimento = dataFornecimento;
    }


    /**
     * Gets the numeroPicking value for this TrackingAparelho.
     * 
     * @return numeroPicking   * Numero do picking
     */
    public java.math.BigInteger getNumeroPicking() {
        return numeroPicking;
    }


    /**
     * Sets the numeroPicking value for this TrackingAparelho.
     * 
     * @param numeroPicking   * Numero do picking
     */
    public void setNumeroPicking(java.math.BigInteger numeroPicking) {
        this.numeroPicking = numeroPicking;
    }


    /**
     * Gets the numeroFornecimento value for this TrackingAparelho.
     * 
     * @return numeroFornecimento   * Numero de fornecimento
     */
    public java.math.BigInteger getNumeroFornecimento() {
        return numeroFornecimento;
    }


    /**
     * Sets the numeroFornecimento value for this TrackingAparelho.
     * 
     * @param numeroFornecimento   * Numero de fornecimento
     */
    public void setNumeroFornecimento(java.math.BigInteger numeroFornecimento) {
        this.numeroFornecimento = numeroFornecimento;
    }


    /**
     * Gets the dataSaidaMercadoria value for this TrackingAparelho.
     * 
     * @return dataSaidaMercadoria   * Data de saida da mercadoria.
     */
    public java.util.Calendar getDataSaidaMercadoria() {
        return dataSaidaMercadoria;
    }


    /**
     * Sets the dataSaidaMercadoria value for this TrackingAparelho.
     * 
     * @param dataSaidaMercadoria   * Data de saida da mercadoria.
     */
    public void setDataSaidaMercadoria(java.util.Calendar dataSaidaMercadoria) {
        this.dataSaidaMercadoria = dataSaidaMercadoria;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TrackingAparelho)) return false;
        TrackingAparelho other = (TrackingAparelho) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataPicking==null && other.getDataPicking()==null) || 
             (this.dataPicking!=null &&
              this.dataPicking.equals(other.getDataPicking()))) &&
            ((this.dataConfirmacaoPicking==null && other.getDataConfirmacaoPicking()==null) || 
             (this.dataConfirmacaoPicking!=null &&
              this.dataConfirmacaoPicking.equals(other.getDataConfirmacaoPicking()))) &&
            ((this.dataFornecimento==null && other.getDataFornecimento()==null) || 
             (this.dataFornecimento!=null &&
              this.dataFornecimento.equals(other.getDataFornecimento()))) &&
            ((this.numeroPicking==null && other.getNumeroPicking()==null) || 
             (this.numeroPicking!=null &&
              this.numeroPicking.equals(other.getNumeroPicking()))) &&
            ((this.numeroFornecimento==null && other.getNumeroFornecimento()==null) || 
             (this.numeroFornecimento!=null &&
              this.numeroFornecimento.equals(other.getNumeroFornecimento()))) &&
            ((this.dataSaidaMercadoria==null && other.getDataSaidaMercadoria()==null) || 
             (this.dataSaidaMercadoria!=null &&
              this.dataSaidaMercadoria.equals(other.getDataSaidaMercadoria())));
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
        if (getDataPicking() != null) {
            _hashCode += getDataPicking().hashCode();
        }
        if (getDataConfirmacaoPicking() != null) {
            _hashCode += getDataConfirmacaoPicking().hashCode();
        }
        if (getDataFornecimento() != null) {
            _hashCode += getDataFornecimento().hashCode();
        }
        if (getNumeroPicking() != null) {
            _hashCode += getNumeroPicking().hashCode();
        }
        if (getNumeroFornecimento() != null) {
            _hashCode += getNumeroFornecimento().hashCode();
        }
        if (getDataSaidaMercadoria() != null) {
            _hashCode += getDataSaidaMercadoria().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TrackingAparelho.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "TrackingAparelho"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPicking");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataPicking"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataConfirmacaoPicking");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataConfirmacaoPicking"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFornecimento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataFornecimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroPicking");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "numeroPicking"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroFornecimento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "numeroFornecimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataSaidaMercadoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataSaidaMercadoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
