/**
 * EnderecoFiscal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa;


/**
 * Mantem informacoes de endereco necessarias para
 * 				autoridades fiscais.
 */
public class EnderecoFiscal  implements java.io.Serializable {
    /* Identificador de registros de autoridade fiscal
     * 						no sistema (seqNbr no Atlys) */
    private java.math.BigInteger codigo;

    /* Codigo de bairro para autoridade fiscal */
    private java.lang.String codigoBairro;

    /* Codigo que identifica a cidade para autoridade
     * 						fiscal (codigo nacional de localidade) */
    private java.lang.String codigoNacLocalidade;

    /* Sigla nacional de localidade. */
    private java.lang.String siglaNacLocalidade;

    private java.lang.String codigoIBGE;

    public EnderecoFiscal() {
    }

    public EnderecoFiscal(
           java.math.BigInteger codigo,
           java.lang.String codigoBairro,
           java.lang.String codigoNacLocalidade,
           java.lang.String siglaNacLocalidade,
           java.lang.String codigoIBGE) {
           this.codigo = codigo;
           this.codigoBairro = codigoBairro;
           this.codigoNacLocalidade = codigoNacLocalidade;
           this.siglaNacLocalidade = siglaNacLocalidade;
           this.codigoIBGE = codigoIBGE;
    }


    /**
     * Gets the codigo value for this EnderecoFiscal.
     * 
     * @return codigo   * Identificador de registros de autoridade fiscal
     * 						no sistema (seqNbr no Atlys)
     */
    public java.math.BigInteger getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this EnderecoFiscal.
     * 
     * @param codigo   * Identificador de registros de autoridade fiscal
     * 						no sistema (seqNbr no Atlys)
     */
    public void setCodigo(java.math.BigInteger codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the codigoBairro value for this EnderecoFiscal.
     * 
     * @return codigoBairro   * Codigo de bairro para autoridade fiscal
     */
    public java.lang.String getCodigoBairro() {
        return codigoBairro;
    }


    /**
     * Sets the codigoBairro value for this EnderecoFiscal.
     * 
     * @param codigoBairro   * Codigo de bairro para autoridade fiscal
     */
    public void setCodigoBairro(java.lang.String codigoBairro) {
        this.codigoBairro = codigoBairro;
    }


    /**
     * Gets the codigoNacLocalidade value for this EnderecoFiscal.
     * 
     * @return codigoNacLocalidade   * Codigo que identifica a cidade para autoridade
     * 						fiscal (codigo nacional de localidade)
     */
    public java.lang.String getCodigoNacLocalidade() {
        return codigoNacLocalidade;
    }


    /**
     * Sets the codigoNacLocalidade value for this EnderecoFiscal.
     * 
     * @param codigoNacLocalidade   * Codigo que identifica a cidade para autoridade
     * 						fiscal (codigo nacional de localidade)
     */
    public void setCodigoNacLocalidade(java.lang.String codigoNacLocalidade) {
        this.codigoNacLocalidade = codigoNacLocalidade;
    }


    /**
     * Gets the siglaNacLocalidade value for this EnderecoFiscal.
     * 
     * @return siglaNacLocalidade   * Sigla nacional de localidade.
     */
    public java.lang.String getSiglaNacLocalidade() {
        return siglaNacLocalidade;
    }


    /**
     * Sets the siglaNacLocalidade value for this EnderecoFiscal.
     * 
     * @param siglaNacLocalidade   * Sigla nacional de localidade.
     */
    public void setSiglaNacLocalidade(java.lang.String siglaNacLocalidade) {
        this.siglaNacLocalidade = siglaNacLocalidade;
    }


    /**
     * Gets the codigoIBGE value for this EnderecoFiscal.
     * 
     * @return codigoIBGE
     */
    public java.lang.String getCodigoIBGE() {
        return codigoIBGE;
    }


    /**
     * Sets the codigoIBGE value for this EnderecoFiscal.
     * 
     * @param codigoIBGE
     */
    public void setCodigoIBGE(java.lang.String codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EnderecoFiscal)) return false;
        EnderecoFiscal other = (EnderecoFiscal) obj;
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
            ((this.codigoBairro==null && other.getCodigoBairro()==null) || 
             (this.codigoBairro!=null &&
              this.codigoBairro.equals(other.getCodigoBairro()))) &&
            ((this.codigoNacLocalidade==null && other.getCodigoNacLocalidade()==null) || 
             (this.codigoNacLocalidade!=null &&
              this.codigoNacLocalidade.equals(other.getCodigoNacLocalidade()))) &&
            ((this.siglaNacLocalidade==null && other.getSiglaNacLocalidade()==null) || 
             (this.siglaNacLocalidade!=null &&
              this.siglaNacLocalidade.equals(other.getSiglaNacLocalidade()))) &&
            ((this.codigoIBGE==null && other.getCodigoIBGE()==null) || 
             (this.codigoIBGE!=null &&
              this.codigoIBGE.equals(other.getCodigoIBGE())));
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
        if (getCodigoBairro() != null) {
            _hashCode += getCodigoBairro().hashCode();
        }
        if (getCodigoNacLocalidade() != null) {
            _hashCode += getCodigoNacLocalidade().hashCode();
        }
        if (getSiglaNacLocalidade() != null) {
            _hashCode += getSiglaNacLocalidade().hashCode();
        }
        if (getCodigoIBGE() != null) {
            _hashCode += getCodigoIBGE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EnderecoFiscal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "EnderecoFiscal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoBairro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "codigoBairro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoNacLocalidade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "codigoNacLocalidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siglaNacLocalidade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "siglaNacLocalidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoIBGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "codigoIBGE"));
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
