/**
 * ListaClassificacaoMultimidiaClassificacaoMultimidia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class ListaClassificacaoMultimidiaClassificacaoMultimidia  implements java.io.Serializable {
    private long idClassificacao;

    private java.lang.String sgClassificacao;

    private java.lang.String nmClassificacao;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    public ListaClassificacaoMultimidiaClassificacaoMultimidia() {
    }

    public ListaClassificacaoMultimidiaClassificacaoMultimidia(
           long idClassificacao,
           java.lang.String sgClassificacao,
           java.lang.String nmClassificacao,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtAlteracao,
           java.lang.String nmUsuarioAlteracao) {
           this.idClassificacao = idClassificacao;
           this.sgClassificacao = sgClassificacao;
           this.nmClassificacao = nmClassificacao;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtAlteracao = dtAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the idClassificacao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @return idClassificacao
     */
    public long getIdClassificacao() {
        return idClassificacao;
    }


    /**
     * Sets the idClassificacao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @param idClassificacao
     */
    public void setIdClassificacao(long idClassificacao) {
        this.idClassificacao = idClassificacao;
    }


    /**
     * Gets the sgClassificacao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @return sgClassificacao
     */
    public java.lang.String getSgClassificacao() {
        return sgClassificacao;
    }


    /**
     * Sets the sgClassificacao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @param sgClassificacao
     */
    public void setSgClassificacao(java.lang.String sgClassificacao) {
        this.sgClassificacao = sgClassificacao;
    }


    /**
     * Gets the nmClassificacao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @return nmClassificacao
     */
    public java.lang.String getNmClassificacao() {
        return nmClassificacao;
    }


    /**
     * Sets the nmClassificacao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @param nmClassificacao
     */
    public void setNmClassificacao(java.lang.String nmClassificacao) {
        this.nmClassificacao = nmClassificacao;
    }


    /**
     * Gets the dtCriacao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtAlteracao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @return dtAlteracao
     */
    public java.util.Calendar getDtAlteracao() {
        return dtAlteracao;
    }


    /**
     * Sets the dtAlteracao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @param dtAlteracao
     */
    public void setDtAlteracao(java.util.Calendar dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ListaClassificacaoMultimidiaClassificacaoMultimidia.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaClassificacaoMultimidiaClassificacaoMultimidia)) return false;
        ListaClassificacaoMultimidiaClassificacaoMultimidia other = (ListaClassificacaoMultimidiaClassificacaoMultimidia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idClassificacao == other.getIdClassificacao() &&
            ((this.sgClassificacao==null && other.getSgClassificacao()==null) || 
             (this.sgClassificacao!=null &&
              this.sgClassificacao.equals(other.getSgClassificacao()))) &&
            ((this.nmClassificacao==null && other.getNmClassificacao()==null) || 
             (this.nmClassificacao!=null &&
              this.nmClassificacao.equals(other.getNmClassificacao()))) &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            ((this.nmUsuarioCriacao==null && other.getNmUsuarioCriacao()==null) || 
             (this.nmUsuarioCriacao!=null &&
              this.nmUsuarioCriacao.equals(other.getNmUsuarioCriacao()))) &&
            ((this.dtAlteracao==null && other.getDtAlteracao()==null) || 
             (this.dtAlteracao!=null &&
              this.dtAlteracao.equals(other.getDtAlteracao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao())));
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
        _hashCode += new Long(getIdClassificacao()).hashCode();
        if (getSgClassificacao() != null) {
            _hashCode += getSgClassificacao().hashCode();
        }
        if (getNmClassificacao() != null) {
            _hashCode += getNmClassificacao().hashCode();
        }
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        if (getNmUsuarioCriacao() != null) {
            _hashCode += getNmUsuarioCriacao().hashCode();
        }
        if (getDtAlteracao() != null) {
            _hashCode += getDtAlteracao().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaClassificacaoMultimidiaClassificacaoMultimidia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">>ListaClassificacaoMultimidia>ClassificacaoMultimidia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idClassificacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "idClassificacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgClassificacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "sgClassificacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmClassificacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "nmClassificacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "nmUsuarioCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "dtAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "nmUsuarioAlteracao"));
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
