/**
 * ListaTipoMultimidiaTipoMultimidia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class ListaTipoMultimidiaTipoMultimidia  implements java.io.Serializable {
    private long idTipoMultimidia;

    private java.lang.String sgTipoMultimidia;

    private java.lang.String nmTipoMultimidia;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    public ListaTipoMultimidiaTipoMultimidia() {
    }

    public ListaTipoMultimidiaTipoMultimidia(
           long idTipoMultimidia,
           java.lang.String sgTipoMultimidia,
           java.lang.String nmTipoMultimidia,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtAlteracao,
           java.lang.String nmUsuarioAlteracao) {
           this.idTipoMultimidia = idTipoMultimidia;
           this.sgTipoMultimidia = sgTipoMultimidia;
           this.nmTipoMultimidia = nmTipoMultimidia;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtAlteracao = dtAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the idTipoMultimidia value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @return idTipoMultimidia
     */
    public long getIdTipoMultimidia() {
        return idTipoMultimidia;
    }


    /**
     * Sets the idTipoMultimidia value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @param idTipoMultimidia
     */
    public void setIdTipoMultimidia(long idTipoMultimidia) {
        this.idTipoMultimidia = idTipoMultimidia;
    }


    /**
     * Gets the sgTipoMultimidia value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @return sgTipoMultimidia
     */
    public java.lang.String getSgTipoMultimidia() {
        return sgTipoMultimidia;
    }


    /**
     * Sets the sgTipoMultimidia value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @param sgTipoMultimidia
     */
    public void setSgTipoMultimidia(java.lang.String sgTipoMultimidia) {
        this.sgTipoMultimidia = sgTipoMultimidia;
    }


    /**
     * Gets the nmTipoMultimidia value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @return nmTipoMultimidia
     */
    public java.lang.String getNmTipoMultimidia() {
        return nmTipoMultimidia;
    }


    /**
     * Sets the nmTipoMultimidia value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @param nmTipoMultimidia
     */
    public void setNmTipoMultimidia(java.lang.String nmTipoMultimidia) {
        this.nmTipoMultimidia = nmTipoMultimidia;
    }


    /**
     * Gets the dtCriacao value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtAlteracao value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @return dtAlteracao
     */
    public java.util.Calendar getDtAlteracao() {
        return dtAlteracao;
    }


    /**
     * Sets the dtAlteracao value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @param dtAlteracao
     */
    public void setDtAlteracao(java.util.Calendar dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ListaTipoMultimidiaTipoMultimidia.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaTipoMultimidiaTipoMultimidia)) return false;
        ListaTipoMultimidiaTipoMultimidia other = (ListaTipoMultimidiaTipoMultimidia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTipoMultimidia == other.getIdTipoMultimidia() &&
            ((this.sgTipoMultimidia==null && other.getSgTipoMultimidia()==null) || 
             (this.sgTipoMultimidia!=null &&
              this.sgTipoMultimidia.equals(other.getSgTipoMultimidia()))) &&
            ((this.nmTipoMultimidia==null && other.getNmTipoMultimidia()==null) || 
             (this.nmTipoMultimidia!=null &&
              this.nmTipoMultimidia.equals(other.getNmTipoMultimidia()))) &&
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
        _hashCode += new Long(getIdTipoMultimidia()).hashCode();
        if (getSgTipoMultimidia() != null) {
            _hashCode += getSgTipoMultimidia().hashCode();
        }
        if (getNmTipoMultimidia() != null) {
            _hashCode += getNmTipoMultimidia().hashCode();
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
        new org.apache.axis.description.TypeDesc(ListaTipoMultimidiaTipoMultimidia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">>ListaTipoMultimidia>TipoMultimidia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "idTipoMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgTipoMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "sgTipoMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTipoMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "nmTipoMultimidia"));
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
