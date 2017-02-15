/**
 * TipoPessoa.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Pessoa;


/**
 * Mantem informacoes que identificam se a pessoa é pessoa
 * 				fisica ou juridica.
 */
public class TipoPessoa  implements java.io.Serializable {
    /* Codigo que identifica o tipo da pessoa, se
     * 						fisico, juridico, etc. */
    private java.math.BigInteger codigo;

    /* Sigla que identifica o tipo da pessoa, se
     * 						fisica, juridica ou nao classificado. */
    private br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Pessoa.TipoPessoaSigla sigla;

    /* Descricao do tipo de pessoa. */
    private java.lang.String descricao;

    public TipoPessoa() {
    }

    public TipoPessoa(
           java.math.BigInteger codigo,
           br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Pessoa.TipoPessoaSigla sigla,
           java.lang.String descricao) {
           this.codigo = codigo;
           this.sigla = sigla;
           this.descricao = descricao;
    }


    /**
     * Gets the codigo value for this TipoPessoa.
     * 
     * @return codigo   * Codigo que identifica o tipo da pessoa, se
     * 						fisico, juridico, etc.
     */
    public java.math.BigInteger getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this TipoPessoa.
     * 
     * @param codigo   * Codigo que identifica o tipo da pessoa, se
     * 						fisico, juridico, etc.
     */
    public void setCodigo(java.math.BigInteger codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the sigla value for this TipoPessoa.
     * 
     * @return sigla   * Sigla que identifica o tipo da pessoa, se
     * 						fisica, juridica ou nao classificado.
     */
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Pessoa.TipoPessoaSigla getSigla() {
        return sigla;
    }


    /**
     * Sets the sigla value for this TipoPessoa.
     * 
     * @param sigla   * Sigla que identifica o tipo da pessoa, se
     * 						fisica, juridica ou nao classificado.
     */
    public void setSigla(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Pessoa.TipoPessoaSigla sigla) {
        this.sigla = sigla;
    }


    /**
     * Gets the descricao value for this TipoPessoa.
     * 
     * @return descricao   * Descricao do tipo de pessoa.
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this TipoPessoa.
     * 
     * @param descricao   * Descricao do tipo de pessoa.
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoPessoa)) return false;
        TipoPessoa other = (TipoPessoa) obj;
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
        new org.apache.axis.description.TypeDesc(TipoPessoa.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "TipoPessoa"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", ">TipoPessoa>sigla"));
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
