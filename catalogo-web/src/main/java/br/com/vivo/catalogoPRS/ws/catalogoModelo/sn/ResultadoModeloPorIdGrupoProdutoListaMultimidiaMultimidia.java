/**
 * ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia  implements java.io.Serializable {
    private long idMultimidia;

    private java.lang.Long idGrupoProduto;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private java.lang.String nmMultimidia;

    public ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia() {
    }

    public ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia(
           long idMultimidia,
           java.lang.Long idGrupoProduto,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           java.lang.String nmMultimidia) {
           this.idMultimidia = idMultimidia;
           this.idGrupoProduto = idGrupoProduto;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.nmMultimidia = nmMultimidia;
    }


    /**
     * Gets the idMultimidia value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @return idMultimidia
     */
    public long getIdMultimidia() {
        return idMultimidia;
    }


    /**
     * Sets the idMultimidia value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @param idMultimidia
     */
    public void setIdMultimidia(long idMultimidia) {
        this.idMultimidia = idMultimidia;
    }


    /**
     * Gets the idGrupoProduto value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @return idGrupoProduto
     */
    public java.lang.Long getIdGrupoProduto() {
        return idGrupoProduto;
    }


    /**
     * Sets the idGrupoProduto value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @param idGrupoProduto
     */
    public void setIdGrupoProduto(java.lang.Long idGrupoProduto) {
        this.idGrupoProduto = idGrupoProduto;
    }


    /**
     * Gets the dtCriacao value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the nmMultimidia value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @return nmMultimidia
     */
    public java.lang.String getNmMultimidia() {
        return nmMultimidia;
    }


    /**
     * Sets the nmMultimidia value for this ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.
     * 
     * @param nmMultimidia
     */
    public void setNmMultimidia(java.lang.String nmMultimidia) {
        this.nmMultimidia = nmMultimidia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia)) return false;
        ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia other = (ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idMultimidia == other.getIdMultimidia() &&
            ((this.idGrupoProduto==null && other.getIdGrupoProduto()==null) || 
             (this.idGrupoProduto!=null &&
              this.idGrupoProduto.equals(other.getIdGrupoProduto()))) &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            ((this.nmUsuarioCriacao==null && other.getNmUsuarioCriacao()==null) || 
             (this.nmUsuarioCriacao!=null &&
              this.nmUsuarioCriacao.equals(other.getNmUsuarioCriacao()))) &&
            ((this.dtUltimaAlteracao==null && other.getDtUltimaAlteracao()==null) || 
             (this.dtUltimaAlteracao!=null &&
              this.dtUltimaAlteracao.equals(other.getDtUltimaAlteracao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            ((this.nmMultimidia==null && other.getNmMultimidia()==null) || 
             (this.nmMultimidia!=null &&
              this.nmMultimidia.equals(other.getNmMultimidia())));
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
        _hashCode += new Long(getIdMultimidia()).hashCode();
        if (getIdGrupoProduto() != null) {
            _hashCode += getIdGrupoProduto().hashCode();
        }
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        if (getNmUsuarioCriacao() != null) {
            _hashCode += getNmUsuarioCriacao().hashCode();
        }
        if (getDtUltimaAlteracao() != null) {
            _hashCode += getDtUltimaAlteracao().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        if (getNmMultimidia() != null) {
            _hashCode += getNmMultimidia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoModeloPorId>GrupoProduto>ListaMultimidia>Multimidia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmUsuarioCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmMultimidia"));
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
