/**
 * TituloLogradouro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa;


/**
 * Mantem informacoes dos titulos de logradouro validos, como
 * 			princesa, duque, Dr. etc.
 */
public class TituloLogradouro  implements java.io.Serializable {
    /* Identifica titulo de logradouro */
    private java.math.BigInteger codigo;

    /* Descricao abreviada do titulo do logradouro,
     * 					exemplos: Dr, Pe, Dra, Prof, etc. */
    private java.lang.String descricaoAbreviada;

    /* Descricao do titulo do logradouro */
    private java.lang.String descricao;

    public TituloLogradouro() {
    }

    public TituloLogradouro(
           java.math.BigInteger codigo,
           java.lang.String descricaoAbreviada,
           java.lang.String descricao) {
           this.codigo = codigo;
           this.descricaoAbreviada = descricaoAbreviada;
           this.descricao = descricao;
    }


    /**
     * Gets the codigo value for this TituloLogradouro.
     * 
     * @return codigo   * Identifica titulo de logradouro
     */
    public java.math.BigInteger getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this TituloLogradouro.
     * 
     * @param codigo   * Identifica titulo de logradouro
     */
    public void setCodigo(java.math.BigInteger codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the descricaoAbreviada value for this TituloLogradouro.
     * 
     * @return descricaoAbreviada   * Descricao abreviada do titulo do logradouro,
     * 					exemplos: Dr, Pe, Dra, Prof, etc.
     */
    public java.lang.String getDescricaoAbreviada() {
        return descricaoAbreviada;
    }


    /**
     * Sets the descricaoAbreviada value for this TituloLogradouro.
     * 
     * @param descricaoAbreviada   * Descricao abreviada do titulo do logradouro,
     * 					exemplos: Dr, Pe, Dra, Prof, etc.
     */
    public void setDescricaoAbreviada(java.lang.String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }


    /**
     * Gets the descricao value for this TituloLogradouro.
     * 
     * @return descricao   * Descricao do titulo do logradouro
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this TituloLogradouro.
     * 
     * @param descricao   * Descricao do titulo do logradouro
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TituloLogradouro)) return false;
        TituloLogradouro other = (TituloLogradouro) obj;
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
            ((this.descricaoAbreviada==null && other.getDescricaoAbreviada()==null) || 
             (this.descricaoAbreviada!=null &&
              this.descricaoAbreviada.equals(other.getDescricaoAbreviada()))) &&
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
        if (getDescricaoAbreviada() != null) {
            _hashCode += getDescricaoAbreviada().hashCode();
        }
        if (getDescricao() != null) {
            _hashCode += getDescricao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TituloLogradouro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "TituloLogradouro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricaoAbreviada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "descricaoAbreviada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "descricao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
