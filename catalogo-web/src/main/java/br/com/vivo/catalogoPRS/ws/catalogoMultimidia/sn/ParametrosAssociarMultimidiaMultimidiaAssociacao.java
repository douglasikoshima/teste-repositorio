/**
 * ParametrosAssociarMultimidiaMultimidiaAssociacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class ParametrosAssociarMultimidiaMultimidiaAssociacao  implements java.io.Serializable {
    private java.lang.Long idGrupoProduto;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.lang.String nmMultimidia;

    private java.lang.Long idCor;

    private java.lang.Long idClassificacao;

    private java.lang.Long idTipoMultimidia;

    public ParametrosAssociarMultimidiaMultimidiaAssociacao() {
    }

    public ParametrosAssociarMultimidiaMultimidiaAssociacao(
           java.lang.Long idGrupoProduto,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.lang.String nmMultimidia,
           java.lang.Long idCor,
           java.lang.Long idClassificacao,
           java.lang.Long idTipoMultimidia) {
           this.idGrupoProduto = idGrupoProduto;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.nmMultimidia = nmMultimidia;
           this.idCor = idCor;
           this.idClassificacao = idClassificacao;
           this.idTipoMultimidia = idTipoMultimidia;
    }


    /**
     * Gets the idGrupoProduto value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @return idGrupoProduto
     */
    public java.lang.Long getIdGrupoProduto() {
        return idGrupoProduto;
    }


    /**
     * Sets the idGrupoProduto value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @param idGrupoProduto
     */
    public void setIdGrupoProduto(java.lang.Long idGrupoProduto) {
        this.idGrupoProduto = idGrupoProduto;
    }


    /**
     * Gets the dtCriacao value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the nmMultimidia value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @return nmMultimidia
     */
    public java.lang.String getNmMultimidia() {
        return nmMultimidia;
    }


    /**
     * Sets the nmMultimidia value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @param nmMultimidia
     */
    public void setNmMultimidia(java.lang.String nmMultimidia) {
        this.nmMultimidia = nmMultimidia;
    }


    /**
     * Gets the idCor value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @return idCor
     */
    public java.lang.Long getIdCor() {
        return idCor;
    }


    /**
     * Sets the idCor value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @param idCor
     */
    public void setIdCor(java.lang.Long idCor) {
        this.idCor = idCor;
    }


    /**
     * Gets the idClassificacao value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @return idClassificacao
     */
    public java.lang.Long getIdClassificacao() {
        return idClassificacao;
    }


    /**
     * Sets the idClassificacao value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @param idClassificacao
     */
    public void setIdClassificacao(java.lang.Long idClassificacao) {
        this.idClassificacao = idClassificacao;
    }


    /**
     * Gets the idTipoMultimidia value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @return idTipoMultimidia
     */
    public java.lang.Long getIdTipoMultimidia() {
        return idTipoMultimidia;
    }


    /**
     * Sets the idTipoMultimidia value for this ParametrosAssociarMultimidiaMultimidiaAssociacao.
     * 
     * @param idTipoMultimidia
     */
    public void setIdTipoMultimidia(java.lang.Long idTipoMultimidia) {
        this.idTipoMultimidia = idTipoMultimidia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAssociarMultimidiaMultimidiaAssociacao)) return false;
        ParametrosAssociarMultimidiaMultimidiaAssociacao other = (ParametrosAssociarMultimidiaMultimidiaAssociacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idGrupoProduto==null && other.getIdGrupoProduto()==null) || 
             (this.idGrupoProduto!=null &&
              this.idGrupoProduto.equals(other.getIdGrupoProduto()))) &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            ((this.nmUsuarioCriacao==null && other.getNmUsuarioCriacao()==null) || 
             (this.nmUsuarioCriacao!=null &&
              this.nmUsuarioCriacao.equals(other.getNmUsuarioCriacao()))) &&
            ((this.nmMultimidia==null && other.getNmMultimidia()==null) || 
             (this.nmMultimidia!=null &&
              this.nmMultimidia.equals(other.getNmMultimidia()))) &&
            ((this.idCor==null && other.getIdCor()==null) || 
             (this.idCor!=null &&
              this.idCor.equals(other.getIdCor()))) &&
            ((this.idClassificacao==null && other.getIdClassificacao()==null) || 
             (this.idClassificacao!=null &&
              this.idClassificacao.equals(other.getIdClassificacao()))) &&
            ((this.idTipoMultimidia==null && other.getIdTipoMultimidia()==null) || 
             (this.idTipoMultimidia!=null &&
              this.idTipoMultimidia.equals(other.getIdTipoMultimidia())));
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
        if (getIdGrupoProduto() != null) {
            _hashCode += getIdGrupoProduto().hashCode();
        }
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        if (getNmUsuarioCriacao() != null) {
            _hashCode += getNmUsuarioCriacao().hashCode();
        }
        if (getNmMultimidia() != null) {
            _hashCode += getNmMultimidia().hashCode();
        }
        if (getIdCor() != null) {
            _hashCode += getIdCor().hashCode();
        }
        if (getIdClassificacao() != null) {
            _hashCode += getIdClassificacao().hashCode();
        }
        if (getIdTipoMultimidia() != null) {
            _hashCode += getIdTipoMultimidia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAssociarMultimidiaMultimidiaAssociacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">>ParametrosAssociarMultimidia>MultimidiaAssociacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "idGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "nmUsuarioCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "nmMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "idCor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idClassificacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "idClassificacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "idTipoMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
