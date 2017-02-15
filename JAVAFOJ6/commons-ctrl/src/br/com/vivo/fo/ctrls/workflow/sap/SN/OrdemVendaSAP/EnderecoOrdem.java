/**
 * EnderecoOrdem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP;

public class EnderecoOrdem  implements java.io.Serializable {
    private java.lang.String logradouro;

    private java.lang.String numeroLogradouro;

    private java.lang.String complemento;

    private java.lang.String nomeBairro;

    private java.lang.String nomeCidade;

    private java.lang.String numeroCep;

    private java.lang.String codigoEstado;

    private java.lang.String codigoPais;

    public EnderecoOrdem() {
    }

    public EnderecoOrdem(
           java.lang.String logradouro,
           java.lang.String numeroLogradouro,
           java.lang.String complemento,
           java.lang.String nomeBairro,
           java.lang.String nomeCidade,
           java.lang.String numeroCep,
           java.lang.String codigoEstado,
           java.lang.String codigoPais) {
           this.logradouro = logradouro;
           this.numeroLogradouro = numeroLogradouro;
           this.complemento = complemento;
           this.nomeBairro = nomeBairro;
           this.nomeCidade = nomeCidade;
           this.numeroCep = numeroCep;
           this.codigoEstado = codigoEstado;
           this.codigoPais = codigoPais;
    }


    /**
     * Gets the logradouro value for this EnderecoOrdem.
     * 
     * @return logradouro
     */
    public java.lang.String getLogradouro() {
        return logradouro;
    }


    /**
     * Sets the logradouro value for this EnderecoOrdem.
     * 
     * @param logradouro
     */
    public void setLogradouro(java.lang.String logradouro) {
        this.logradouro = logradouro;
    }


    /**
     * Gets the numeroLogradouro value for this EnderecoOrdem.
     * 
     * @return numeroLogradouro
     */
    public java.lang.String getNumeroLogradouro() {
        return numeroLogradouro;
    }


    /**
     * Sets the numeroLogradouro value for this EnderecoOrdem.
     * 
     * @param numeroLogradouro
     */
    public void setNumeroLogradouro(java.lang.String numeroLogradouro) {
        this.numeroLogradouro = numeroLogradouro;
    }


    /**
     * Gets the complemento value for this EnderecoOrdem.
     * 
     * @return complemento
     */
    public java.lang.String getComplemento() {
        return complemento;
    }


    /**
     * Sets the complemento value for this EnderecoOrdem.
     * 
     * @param complemento
     */
    public void setComplemento(java.lang.String complemento) {
        this.complemento = complemento;
    }


    /**
     * Gets the nomeBairro value for this EnderecoOrdem.
     * 
     * @return nomeBairro
     */
    public java.lang.String getNomeBairro() {
        return nomeBairro;
    }


    /**
     * Sets the nomeBairro value for this EnderecoOrdem.
     * 
     * @param nomeBairro
     */
    public void setNomeBairro(java.lang.String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }


    /**
     * Gets the nomeCidade value for this EnderecoOrdem.
     * 
     * @return nomeCidade
     */
    public java.lang.String getNomeCidade() {
        return nomeCidade;
    }


    /**
     * Sets the nomeCidade value for this EnderecoOrdem.
     * 
     * @param nomeCidade
     */
    public void setNomeCidade(java.lang.String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }


    /**
     * Gets the numeroCep value for this EnderecoOrdem.
     * 
     * @return numeroCep
     */
    public java.lang.String getNumeroCep() {
        return numeroCep;
    }


    /**
     * Sets the numeroCep value for this EnderecoOrdem.
     * 
     * @param numeroCep
     */
    public void setNumeroCep(java.lang.String numeroCep) {
        this.numeroCep = numeroCep;
    }


    /**
     * Gets the codigoEstado value for this EnderecoOrdem.
     * 
     * @return codigoEstado
     */
    public java.lang.String getCodigoEstado() {
        return codigoEstado;
    }


    /**
     * Sets the codigoEstado value for this EnderecoOrdem.
     * 
     * @param codigoEstado
     */
    public void setCodigoEstado(java.lang.String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }


    /**
     * Gets the codigoPais value for this EnderecoOrdem.
     * 
     * @return codigoPais
     */
    public java.lang.String getCodigoPais() {
        return codigoPais;
    }


    /**
     * Sets the codigoPais value for this EnderecoOrdem.
     * 
     * @param codigoPais
     */
    public void setCodigoPais(java.lang.String codigoPais) {
        this.codigoPais = codigoPais;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EnderecoOrdem)) return false;
        EnderecoOrdem other = (EnderecoOrdem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.logradouro==null && other.getLogradouro()==null) || 
             (this.logradouro!=null &&
              this.logradouro.equals(other.getLogradouro()))) &&
            ((this.numeroLogradouro==null && other.getNumeroLogradouro()==null) || 
             (this.numeroLogradouro!=null &&
              this.numeroLogradouro.equals(other.getNumeroLogradouro()))) &&
            ((this.complemento==null && other.getComplemento()==null) || 
             (this.complemento!=null &&
              this.complemento.equals(other.getComplemento()))) &&
            ((this.nomeBairro==null && other.getNomeBairro()==null) || 
             (this.nomeBairro!=null &&
              this.nomeBairro.equals(other.getNomeBairro()))) &&
            ((this.nomeCidade==null && other.getNomeCidade()==null) || 
             (this.nomeCidade!=null &&
              this.nomeCidade.equals(other.getNomeCidade()))) &&
            ((this.numeroCep==null && other.getNumeroCep()==null) || 
             (this.numeroCep!=null &&
              this.numeroCep.equals(other.getNumeroCep()))) &&
            ((this.codigoEstado==null && other.getCodigoEstado()==null) || 
             (this.codigoEstado!=null &&
              this.codigoEstado.equals(other.getCodigoEstado()))) &&
            ((this.codigoPais==null && other.getCodigoPais()==null) || 
             (this.codigoPais!=null &&
              this.codigoPais.equals(other.getCodigoPais())));
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
        if (getLogradouro() != null) {
            _hashCode += getLogradouro().hashCode();
        }
        if (getNumeroLogradouro() != null) {
            _hashCode += getNumeroLogradouro().hashCode();
        }
        if (getComplemento() != null) {
            _hashCode += getComplemento().hashCode();
        }
        if (getNomeBairro() != null) {
            _hashCode += getNomeBairro().hashCode();
        }
        if (getNomeCidade() != null) {
            _hashCode += getNomeCidade().hashCode();
        }
        if (getNumeroCep() != null) {
            _hashCode += getNumeroCep().hashCode();
        }
        if (getCodigoEstado() != null) {
            _hashCode += getCodigoEstado().hashCode();
        }
        if (getCodigoPais() != null) {
            _hashCode += getCodigoPais().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EnderecoOrdem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "enderecoOrdem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logradouro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "logradouro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroLogradouro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "numeroLogradouro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complemento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "complemento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeBairro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "nomeBairro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeCidade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "nomeCidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "numeroCep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "codigoEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "codigoPais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
