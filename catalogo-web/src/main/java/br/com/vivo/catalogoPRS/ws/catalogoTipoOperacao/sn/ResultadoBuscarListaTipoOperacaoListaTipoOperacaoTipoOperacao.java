/**
 * ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn;

public class ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao  implements java.io.Serializable {
    private long idTipoOperacao;

    private java.lang.String sgTipoOperacao;

    private java.lang.String nmOperacao;

    public ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao() {
    }

    public ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao(
           long idTipoOperacao,
           java.lang.String sgTipoOperacao,
           java.lang.String nmOperacao) {
           this.idTipoOperacao = idTipoOperacao;
           this.sgTipoOperacao = sgTipoOperacao;
           this.nmOperacao = nmOperacao;
    }


    /**
     * Gets the idTipoOperacao value for this ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao.
     * 
     * @return idTipoOperacao
     */
    public long getIdTipoOperacao() {
        return idTipoOperacao;
    }


    /**
     * Sets the idTipoOperacao value for this ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao.
     * 
     * @param idTipoOperacao
     */
    public void setIdTipoOperacao(long idTipoOperacao) {
        this.idTipoOperacao = idTipoOperacao;
    }


    /**
     * Gets the sgTipoOperacao value for this ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao.
     * 
     * @return sgTipoOperacao
     */
    public java.lang.String getSgTipoOperacao() {
        return sgTipoOperacao;
    }


    /**
     * Sets the sgTipoOperacao value for this ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao.
     * 
     * @param sgTipoOperacao
     */
    public void setSgTipoOperacao(java.lang.String sgTipoOperacao) {
        this.sgTipoOperacao = sgTipoOperacao;
    }


    /**
     * Gets the nmOperacao value for this ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao.
     * 
     * @return nmOperacao
     */
    public java.lang.String getNmOperacao() {
        return nmOperacao;
    }


    /**
     * Sets the nmOperacao value for this ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao.
     * 
     * @param nmOperacao
     */
    public void setNmOperacao(java.lang.String nmOperacao) {
        this.nmOperacao = nmOperacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao)) return false;
        ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao other = (ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTipoOperacao == other.getIdTipoOperacao() &&
            ((this.sgTipoOperacao==null && other.getSgTipoOperacao()==null) || 
             (this.sgTipoOperacao!=null &&
              this.sgTipoOperacao.equals(other.getSgTipoOperacao()))) &&
            ((this.nmOperacao==null && other.getNmOperacao()==null) || 
             (this.nmOperacao!=null &&
              this.nmOperacao.equals(other.getNmOperacao())));
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
        _hashCode += new Long(getIdTipoOperacao()).hashCode();
        if (getSgTipoOperacao() != null) {
            _hashCode += getSgTipoOperacao().hashCode();
        }
        if (getNmOperacao() != null) {
            _hashCode += getNmOperacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoOperacao", ">>>ResultadoBuscarListaTipoOperacao>ListaTipoOperacao>TipoOperacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoOperacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoOperacao", "idTipoOperacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgTipoOperacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoOperacao", "sgTipoOperacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmOperacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoOperacao", "nmOperacao"));
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
