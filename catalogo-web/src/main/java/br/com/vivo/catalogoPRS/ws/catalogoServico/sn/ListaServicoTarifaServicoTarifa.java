/**
 * ListaServicoTarifaServicoTarifa.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ListaServicoTarifaServicoTarifa  implements java.io.Serializable {
    private long idServicoTarifa;

    private long idServico;

    private java.math.BigDecimal precoBase;

    private java.lang.String dscTarifa;

    private java.lang.String tpUnidadeTarifa;

    private java.lang.String tpDebitoTarifa;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioAlteracao;

    private java.util.Calendar dtUltimaAlteracao;

    public ListaServicoTarifaServicoTarifa() {
    }

    public ListaServicoTarifaServicoTarifa(
           long idServicoTarifa,
           long idServico,
           java.math.BigDecimal precoBase,
           java.lang.String dscTarifa,
           java.lang.String tpUnidadeTarifa,
           java.lang.String tpDebitoTarifa,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioAlteracao,
           java.util.Calendar dtUltimaAlteracao) {
           this.idServicoTarifa = idServicoTarifa;
           this.idServico = idServico;
           this.precoBase = precoBase;
           this.dscTarifa = dscTarifa;
           this.tpUnidadeTarifa = tpUnidadeTarifa;
           this.tpDebitoTarifa = tpDebitoTarifa;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the idServicoTarifa value for this ListaServicoTarifaServicoTarifa.
     * 
     * @return idServicoTarifa
     */
    public long getIdServicoTarifa() {
        return idServicoTarifa;
    }


    /**
     * Sets the idServicoTarifa value for this ListaServicoTarifaServicoTarifa.
     * 
     * @param idServicoTarifa
     */
    public void setIdServicoTarifa(long idServicoTarifa) {
        this.idServicoTarifa = idServicoTarifa;
    }


    /**
     * Gets the idServico value for this ListaServicoTarifaServicoTarifa.
     * 
     * @return idServico
     */
    public long getIdServico() {
        return idServico;
    }


    /**
     * Sets the idServico value for this ListaServicoTarifaServicoTarifa.
     * 
     * @param idServico
     */
    public void setIdServico(long idServico) {
        this.idServico = idServico;
    }


    /**
     * Gets the precoBase value for this ListaServicoTarifaServicoTarifa.
     * 
     * @return precoBase
     */
    public java.math.BigDecimal getPrecoBase() {
        return precoBase;
    }


    /**
     * Sets the precoBase value for this ListaServicoTarifaServicoTarifa.
     * 
     * @param precoBase
     */
    public void setPrecoBase(java.math.BigDecimal precoBase) {
        this.precoBase = precoBase;
    }


    /**
     * Gets the dscTarifa value for this ListaServicoTarifaServicoTarifa.
     * 
     * @return dscTarifa
     */
    public java.lang.String getDscTarifa() {
        return dscTarifa;
    }


    /**
     * Sets the dscTarifa value for this ListaServicoTarifaServicoTarifa.
     * 
     * @param dscTarifa
     */
    public void setDscTarifa(java.lang.String dscTarifa) {
        this.dscTarifa = dscTarifa;
    }


    /**
     * Gets the tpUnidadeTarifa value for this ListaServicoTarifaServicoTarifa.
     * 
     * @return tpUnidadeTarifa
     */
    public java.lang.String getTpUnidadeTarifa() {
        return tpUnidadeTarifa;
    }


    /**
     * Sets the tpUnidadeTarifa value for this ListaServicoTarifaServicoTarifa.
     * 
     * @param tpUnidadeTarifa
     */
    public void setTpUnidadeTarifa(java.lang.String tpUnidadeTarifa) {
        this.tpUnidadeTarifa = tpUnidadeTarifa;
    }


    /**
     * Gets the tpDebitoTarifa value for this ListaServicoTarifaServicoTarifa.
     * 
     * @return tpDebitoTarifa
     */
    public java.lang.String getTpDebitoTarifa() {
        return tpDebitoTarifa;
    }


    /**
     * Sets the tpDebitoTarifa value for this ListaServicoTarifaServicoTarifa.
     * 
     * @param tpDebitoTarifa
     */
    public void setTpDebitoTarifa(java.lang.String tpDebitoTarifa) {
        this.tpDebitoTarifa = tpDebitoTarifa;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ListaServicoTarifaServicoTarifa.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ListaServicoTarifaServicoTarifa.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtCriacao value for this ListaServicoTarifaServicoTarifa.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ListaServicoTarifaServicoTarifa.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ListaServicoTarifaServicoTarifa.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ListaServicoTarifaServicoTarifa.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ListaServicoTarifaServicoTarifa.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ListaServicoTarifaServicoTarifa.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaServicoTarifaServicoTarifa)) return false;
        ListaServicoTarifaServicoTarifa other = (ListaServicoTarifaServicoTarifa) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idServicoTarifa == other.getIdServicoTarifa() &&
            this.idServico == other.getIdServico() &&
            ((this.precoBase==null && other.getPrecoBase()==null) || 
             (this.precoBase!=null &&
              this.precoBase.equals(other.getPrecoBase()))) &&
            ((this.dscTarifa==null && other.getDscTarifa()==null) || 
             (this.dscTarifa!=null &&
              this.dscTarifa.equals(other.getDscTarifa()))) &&
            ((this.tpUnidadeTarifa==null && other.getTpUnidadeTarifa()==null) || 
             (this.tpUnidadeTarifa!=null &&
              this.tpUnidadeTarifa.equals(other.getTpUnidadeTarifa()))) &&
            ((this.tpDebitoTarifa==null && other.getTpDebitoTarifa()==null) || 
             (this.tpDebitoTarifa!=null &&
              this.tpDebitoTarifa.equals(other.getTpDebitoTarifa()))) &&
            ((this.nmUsuarioCriacao==null && other.getNmUsuarioCriacao()==null) || 
             (this.nmUsuarioCriacao!=null &&
              this.nmUsuarioCriacao.equals(other.getNmUsuarioCriacao()))) &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            ((this.dtUltimaAlteracao==null && other.getDtUltimaAlteracao()==null) || 
             (this.dtUltimaAlteracao!=null &&
              this.dtUltimaAlteracao.equals(other.getDtUltimaAlteracao())));
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
        _hashCode += new Long(getIdServicoTarifa()).hashCode();
        _hashCode += new Long(getIdServico()).hashCode();
        if (getPrecoBase() != null) {
            _hashCode += getPrecoBase().hashCode();
        }
        if (getDscTarifa() != null) {
            _hashCode += getDscTarifa().hashCode();
        }
        if (getTpUnidadeTarifa() != null) {
            _hashCode += getTpUnidadeTarifa().hashCode();
        }
        if (getTpDebitoTarifa() != null) {
            _hashCode += getTpDebitoTarifa().hashCode();
        }
        if (getNmUsuarioCriacao() != null) {
            _hashCode += getNmUsuarioCriacao().hashCode();
        }
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        if (getDtUltimaAlteracao() != null) {
            _hashCode += getDtUltimaAlteracao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaServicoTarifaServicoTarifa.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>ListaServicoTarifa>ServicoTarifa"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServicoTarifa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idServicoTarifa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("precoBase");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "precoBase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dscTarifa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "dscTarifa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tpUnidadeTarifa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "tpUnidadeTarifa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tpDebitoTarifa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "tpDebitoTarifa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmUsuarioCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
