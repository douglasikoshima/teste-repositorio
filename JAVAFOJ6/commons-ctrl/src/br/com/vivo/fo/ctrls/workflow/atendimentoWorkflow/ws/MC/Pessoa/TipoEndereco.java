/**
 * TipoEndereco.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa;


/**
 * Mantem informacoes dos tipos de enderecos validos, se
 * 				residencial, cobranca ou comercial.
 */
public class TipoEndereco  implements java.io.Serializable {
    /* Codigo que identifica um tipo de endereco (se
     * 						cobranca, comercial ou residencial). */
    private java.math.BigInteger codigo;

    /* Sigla que identifica o tipo de endereco, se
     * 						cobranca, residencial ou comercial. */
    private java.lang.String sigla;

    /* Descricao do tipo de endereco. */
    private java.lang.String descricao;

    public TipoEndereco() {
    }

    public TipoEndereco(
           java.math.BigInteger codigo,
           java.lang.String sigla,
           java.lang.String descricao) {
           this.codigo = codigo;
           this.sigla = sigla;
           this.descricao = descricao;
    }


    /**
     * Gets the codigo value for this TipoEndereco.
     * 
     * @return codigo   * Codigo que identifica um tipo de endereco (se
     * 						cobranca, comercial ou residencial).
     */
    public java.math.BigInteger getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this TipoEndereco.
     * 
     * @param codigo   * Codigo que identifica um tipo de endereco (se
     * 						cobranca, comercial ou residencial).
     */
    public void setCodigo(java.math.BigInteger codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the sigla value for this TipoEndereco.
     * 
     * @return sigla   * Sigla que identifica o tipo de endereco, se
     * 						cobranca, residencial ou comercial.
     */
    public java.lang.String getSigla() {
        return sigla;
    }


    /**
     * Sets the sigla value for this TipoEndereco.
     * 
     * @param sigla   * Sigla que identifica o tipo de endereco, se
     * 						cobranca, residencial ou comercial.
     */
    public void setSigla(java.lang.String sigla) {
        this.sigla = sigla;
    }


    /**
     * Gets the descricao value for this TipoEndereco.
     * 
     * @return descricao   * Descricao do tipo de endereco.
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this TipoEndereco.
     * 
     * @param descricao   * Descricao do tipo de endereco.
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoEndereco)) return false;
        TipoEndereco other = (TipoEndereco) obj;
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
            ((this.sigla==null && other.getSigla()==null) || 
             (this.sigla!=null &&
              this.sigla.equals(other.getSigla()))) &&
            ((this.descricao==null && other.getDescricao()==null) || 
             (this.descricao!=null &&
              this.descricao.equals(other.getDescricao())));
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
        if (getSigla() != null) {
            _hashCode += getSigla().hashCode();
        }
        if (getDescricao() != null) {
            _hashCode += getDescricao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoEndereco.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "TipoEndereco"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sigla");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "sigla"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "descricao"));
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

}
