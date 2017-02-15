/**
 * ParametrosAtualizarStatusOrdemVenda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP;

public class ParametrosAtualizarStatusOrdemVenda  implements java.io.Serializable {
    private java.lang.String loginUsuario;

    private long numeroOrdemVenda;

    private java.lang.String codigoMotivo;

    private java.lang.String descricaoMotivo;

    /* 0 --> Sem Restrição (Default) 1 --> Rejeitado
     * 						SEFAZ 2 --> Nota Fiscal Denegada */
    private java.math.BigInteger retornoRejeicao;

    public ParametrosAtualizarStatusOrdemVenda() {
    }

    public ParametrosAtualizarStatusOrdemVenda(
           java.lang.String loginUsuario,
           long numeroOrdemVenda,
           java.lang.String codigoMotivo,
           java.lang.String descricaoMotivo,
           java.math.BigInteger retornoRejeicao) {
           this.loginUsuario = loginUsuario;
           this.numeroOrdemVenda = numeroOrdemVenda;
           this.codigoMotivo = codigoMotivo;
           this.descricaoMotivo = descricaoMotivo;
           this.retornoRejeicao = retornoRejeicao;
    }


    /**
     * Gets the loginUsuario value for this ParametrosAtualizarStatusOrdemVenda.
     * 
     * @return loginUsuario
     */
    public java.lang.String getLoginUsuario() {
        return loginUsuario;
    }


    /**
     * Sets the loginUsuario value for this ParametrosAtualizarStatusOrdemVenda.
     * 
     * @param loginUsuario
     */
    public void setLoginUsuario(java.lang.String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }


    /**
     * Gets the numeroOrdemVenda value for this ParametrosAtualizarStatusOrdemVenda.
     * 
     * @return numeroOrdemVenda
     */
    public long getNumeroOrdemVenda() {
        return numeroOrdemVenda;
    }


    /**
     * Sets the numeroOrdemVenda value for this ParametrosAtualizarStatusOrdemVenda.
     * 
     * @param numeroOrdemVenda
     */
    public void setNumeroOrdemVenda(long numeroOrdemVenda) {
        this.numeroOrdemVenda = numeroOrdemVenda;
    }


    /**
     * Gets the codigoMotivo value for this ParametrosAtualizarStatusOrdemVenda.
     * 
     * @return codigoMotivo
     */
    public java.lang.String getCodigoMotivo() {
        return codigoMotivo;
    }


    /**
     * Sets the codigoMotivo value for this ParametrosAtualizarStatusOrdemVenda.
     * 
     * @param codigoMotivo
     */
    public void setCodigoMotivo(java.lang.String codigoMotivo) {
        this.codigoMotivo = codigoMotivo;
    }


    /**
     * Gets the descricaoMotivo value for this ParametrosAtualizarStatusOrdemVenda.
     * 
     * @return descricaoMotivo
     */
    public java.lang.String getDescricaoMotivo() {
        return descricaoMotivo;
    }


    /**
     * Sets the descricaoMotivo value for this ParametrosAtualizarStatusOrdemVenda.
     * 
     * @param descricaoMotivo
     */
    public void setDescricaoMotivo(java.lang.String descricaoMotivo) {
        this.descricaoMotivo = descricaoMotivo;
    }


    /**
     * Gets the retornoRejeicao value for this ParametrosAtualizarStatusOrdemVenda.
     * 
     * @return retornoRejeicao   * 0 --> Sem Restrição (Default) 1 --> Rejeitado
     * 						SEFAZ 2 --> Nota Fiscal Denegada
     */
    public java.math.BigInteger getRetornoRejeicao() {
        return retornoRejeicao;
    }


    /**
     * Sets the retornoRejeicao value for this ParametrosAtualizarStatusOrdemVenda.
     * 
     * @param retornoRejeicao   * 0 --> Sem Restrição (Default) 1 --> Rejeitado
     * 						SEFAZ 2 --> Nota Fiscal Denegada
     */
    public void setRetornoRejeicao(java.math.BigInteger retornoRejeicao) {
        this.retornoRejeicao = retornoRejeicao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAtualizarStatusOrdemVenda)) return false;
        ParametrosAtualizarStatusOrdemVenda other = (ParametrosAtualizarStatusOrdemVenda) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.loginUsuario==null && other.getLoginUsuario()==null) || 
             (this.loginUsuario!=null &&
              this.loginUsuario.equals(other.getLoginUsuario()))) &&
            this.numeroOrdemVenda == other.getNumeroOrdemVenda() &&
            ((this.codigoMotivo==null && other.getCodigoMotivo()==null) || 
             (this.codigoMotivo!=null &&
              this.codigoMotivo.equals(other.getCodigoMotivo()))) &&
            ((this.descricaoMotivo==null && other.getDescricaoMotivo()==null) || 
             (this.descricaoMotivo!=null &&
              this.descricaoMotivo.equals(other.getDescricaoMotivo()))) &&
            ((this.retornoRejeicao==null && other.getRetornoRejeicao()==null) || 
             (this.retornoRejeicao!=null &&
              this.retornoRejeicao.equals(other.getRetornoRejeicao())));
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
        if (getLoginUsuario() != null) {
            _hashCode += getLoginUsuario().hashCode();
        }
        _hashCode += new Long(getNumeroOrdemVenda()).hashCode();
        if (getCodigoMotivo() != null) {
            _hashCode += getCodigoMotivo().hashCode();
        }
        if (getDescricaoMotivo() != null) {
            _hashCode += getDescricaoMotivo().hashCode();
        }
        if (getRetornoRejeicao() != null) {
            _hashCode += getRetornoRejeicao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAtualizarStatusOrdemVenda.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "ParametrosAtualizarStatusOrdemVenda"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loginUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroOrdemVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroOrdemVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMotivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoMotivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricaoMotivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descricaoMotivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retornoRejeicao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "retornoRejeicao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
