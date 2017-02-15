/**
 * ErroInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.sap.MC.Geral;


/**
 * Mantem padrao definir as mensagens de erro.
 */
public class ErroInfo  extends org.apache.axis.AxisFault  implements java.io.Serializable {
    /* Codigo que identifica o erro. Qualquer retorno
     * 						diferente de 0 indica um possivel erro. */
    private java.lang.String codigo;

    /* Descricao do erro ocorrido nas chamadas a
     * 						servicos. */
    private java.lang.String descricao;

    private java.util.Calendar dataTransacao;

    /* Dados adicionais que podem ser necessarios para
     * 						identificar um erro. */
    private java.lang.Object dadosAdicionais;

    /* Codigo da classificacao do erro, indicando se e
     * 						emergencial, critico, warning, etc. */
    private java.math.BigInteger codigoClassificacao;

    /* Descricao da classificacao */
    private java.lang.String descClassificacao;

    public ErroInfo() {
    }

    public ErroInfo(
           java.lang.String codigo,
           java.lang.String descricao,
           java.util.Calendar dataTransacao,
           java.lang.Object dadosAdicionais,
           java.math.BigInteger codigoClassificacao,
           java.lang.String descClassificacao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.dataTransacao = dataTransacao;
        this.dadosAdicionais = dadosAdicionais;
        this.codigoClassificacao = codigoClassificacao;
        this.descClassificacao = descClassificacao;
    }


    /**
     * Gets the codigo value for this ErroInfo.
     * 
     * @return codigo   * Codigo que identifica o erro. Qualquer retorno
     * 						diferente de 0 indica um possivel erro.
     */
    public java.lang.String getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this ErroInfo.
     * 
     * @param codigo   * Codigo que identifica o erro. Qualquer retorno
     * 						diferente de 0 indica um possivel erro.
     */
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the descricao value for this ErroInfo.
     * 
     * @return descricao   * Descricao do erro ocorrido nas chamadas a
     * 						servicos.
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this ErroInfo.
     * 
     * @param descricao   * Descricao do erro ocorrido nas chamadas a
     * 						servicos.
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }


    /**
     * Gets the dataTransacao value for this ErroInfo.
     * 
     * @return dataTransacao
     */
    public java.util.Calendar getDataTransacao() {
        return dataTransacao;
    }


    /**
     * Sets the dataTransacao value for this ErroInfo.
     * 
     * @param dataTransacao
     */
    public void setDataTransacao(java.util.Calendar dataTransacao) {
        this.dataTransacao = dataTransacao;
    }


    /**
     * Gets the dadosAdicionais value for this ErroInfo.
     * 
     * @return dadosAdicionais   * Dados adicionais que podem ser necessarios para
     * 						identificar um erro.
     */
    public java.lang.Object getDadosAdicionais() {
        return dadosAdicionais;
    }


    /**
     * Sets the dadosAdicionais value for this ErroInfo.
     * 
     * @param dadosAdicionais   * Dados adicionais que podem ser necessarios para
     * 						identificar um erro.
     */
    public void setDadosAdicionais(java.lang.Object dadosAdicionais) {
        this.dadosAdicionais = dadosAdicionais;
    }


    /**
     * Gets the codigoClassificacao value for this ErroInfo.
     * 
     * @return codigoClassificacao   * Codigo da classificacao do erro, indicando se e
     * 						emergencial, critico, warning, etc.
     */
    public java.math.BigInteger getCodigoClassificacao() {
        return codigoClassificacao;
    }


    /**
     * Sets the codigoClassificacao value for this ErroInfo.
     * 
     * @param codigoClassificacao   * Codigo da classificacao do erro, indicando se e
     * 						emergencial, critico, warning, etc.
     */
    public void setCodigoClassificacao(java.math.BigInteger codigoClassificacao) {
        this.codigoClassificacao = codigoClassificacao;
    }


    /**
     * Gets the descClassificacao value for this ErroInfo.
     * 
     * @return descClassificacao   * Descricao da classificacao
     */
    public java.lang.String getDescClassificacao() {
        return descClassificacao;
    }


    /**
     * Sets the descClassificacao value for this ErroInfo.
     * 
     * @param descClassificacao   * Descricao da classificacao
     */
    public void setDescClassificacao(java.lang.String descClassificacao) {
        this.descClassificacao = descClassificacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ErroInfo)) return false;
        ErroInfo other = (ErroInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.descricao==null && other.getDescricao()==null) || 
             (this.descricao!=null &&
              this.descricao.equals(other.getDescricao()))) &&
            ((this.dataTransacao==null && other.getDataTransacao()==null) || 
             (this.dataTransacao!=null &&
              this.dataTransacao.equals(other.getDataTransacao()))) &&
            ((this.dadosAdicionais==null && other.getDadosAdicionais()==null) || 
             (this.dadosAdicionais!=null &&
              this.dadosAdicionais.equals(other.getDadosAdicionais()))) &&
            ((this.codigoClassificacao==null && other.getCodigoClassificacao()==null) || 
             (this.codigoClassificacao!=null &&
              this.codigoClassificacao.equals(other.getCodigoClassificacao()))) &&
            ((this.descClassificacao==null && other.getDescClassificacao()==null) || 
             (this.descClassificacao!=null &&
              this.descClassificacao.equals(other.getDescClassificacao())));
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
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getDescricao() != null) {
            _hashCode += getDescricao().hashCode();
        }
        if (getDataTransacao() != null) {
            _hashCode += getDataTransacao().hashCode();
        }
        if (getDadosAdicionais() != null) {
            _hashCode += getDadosAdicionais().hashCode();
        }
        if (getCodigoClassificacao() != null) {
            _hashCode += getCodigoClassificacao().hashCode();
        }
        if (getDescClassificacao() != null) {
            _hashCode += getDescClassificacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ErroInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "descricao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataTransacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "dataTransacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dadosAdicionais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "dadosAdicionais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoClassificacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "codigoClassificacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descClassificacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "descClassificacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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


    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
