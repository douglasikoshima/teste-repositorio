/**
 * ListaAreaRegistroAreaRegistro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoWEB;

public class ListaAreaRegistroAreaRegistro  implements java.io.Serializable {
    private long idAreaRegistro;

    private long codigoArea;

    private long idUf;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.lang.String nmUsuarioAlteracao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.Long idOrganizacaoVendas;

    public ListaAreaRegistroAreaRegistro() {
    }

    public ListaAreaRegistroAreaRegistro(
           long idAreaRegistro,
           long codigoArea,
           long idUf,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.lang.String nmUsuarioAlteracao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.Long idOrganizacaoVendas) {
           this.idAreaRegistro = idAreaRegistro;
           this.codigoArea = codigoArea;
           this.idUf = idUf;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.idOrganizacaoVendas = idOrganizacaoVendas;
    }


    /**
     * Gets the idAreaRegistro value for this ListaAreaRegistroAreaRegistro.
     * 
     * @return idAreaRegistro
     */
    public long getIdAreaRegistro() {
        return idAreaRegistro;
    }


    /**
     * Sets the idAreaRegistro value for this ListaAreaRegistroAreaRegistro.
     * 
     * @param idAreaRegistro
     */
    public void setIdAreaRegistro(long idAreaRegistro) {
        this.idAreaRegistro = idAreaRegistro;
    }


    /**
     * Gets the codigoArea value for this ListaAreaRegistroAreaRegistro.
     * 
     * @return codigoArea
     */
    public long getCodigoArea() {
        return codigoArea;
    }


    /**
     * Sets the codigoArea value for this ListaAreaRegistroAreaRegistro.
     * 
     * @param codigoArea
     */
    public void setCodigoArea(long codigoArea) {
        this.codigoArea = codigoArea;
    }


    /**
     * Gets the idUf value for this ListaAreaRegistroAreaRegistro.
     * 
     * @return idUf
     */
    public long getIdUf() {
        return idUf;
    }


    /**
     * Sets the idUf value for this ListaAreaRegistroAreaRegistro.
     * 
     * @param idUf
     */
    public void setIdUf(long idUf) {
        this.idUf = idUf;
    }


    /**
     * Gets the dtCriacao value for this ListaAreaRegistroAreaRegistro.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ListaAreaRegistroAreaRegistro.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ListaAreaRegistroAreaRegistro.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ListaAreaRegistroAreaRegistro.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ListaAreaRegistroAreaRegistro.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ListaAreaRegistroAreaRegistro.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ListaAreaRegistroAreaRegistro.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ListaAreaRegistroAreaRegistro.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the idOrganizacaoVendas value for this ListaAreaRegistroAreaRegistro.
     * 
     * @return idOrganizacaoVendas
     */
    public java.lang.Long getIdOrganizacaoVendas() {
        return idOrganizacaoVendas;
    }


    /**
     * Sets the idOrganizacaoVendas value for this ListaAreaRegistroAreaRegistro.
     * 
     * @param idOrganizacaoVendas
     */
    public void setIdOrganizacaoVendas(java.lang.Long idOrganizacaoVendas) {
        this.idOrganizacaoVendas = idOrganizacaoVendas;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaAreaRegistroAreaRegistro)) return false;
        ListaAreaRegistroAreaRegistro other = (ListaAreaRegistroAreaRegistro) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idAreaRegistro == other.getIdAreaRegistro() &&
            this.codigoArea == other.getCodigoArea() &&
            this.idUf == other.getIdUf() &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            ((this.nmUsuarioCriacao==null && other.getNmUsuarioCriacao()==null) || 
             (this.nmUsuarioCriacao!=null &&
              this.nmUsuarioCriacao.equals(other.getNmUsuarioCriacao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            ((this.dtUltimaAlteracao==null && other.getDtUltimaAlteracao()==null) || 
             (this.dtUltimaAlteracao!=null &&
              this.dtUltimaAlteracao.equals(other.getDtUltimaAlteracao()))) &&
            ((this.idOrganizacaoVendas==null && other.getIdOrganizacaoVendas()==null) || 
             (this.idOrganizacaoVendas!=null &&
              this.idOrganizacaoVendas.equals(other.getIdOrganizacaoVendas())));
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
        _hashCode += new Long(getIdAreaRegistro()).hashCode();
        _hashCode += new Long(getCodigoArea()).hashCode();
        _hashCode += new Long(getIdUf()).hashCode();
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        if (getNmUsuarioCriacao() != null) {
            _hashCode += getNmUsuarioCriacao().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        if (getDtUltimaAlteracao() != null) {
            _hashCode += getDtUltimaAlteracao().hashCode();
        }
        if (getIdOrganizacaoVendas() != null) {
            _hashCode += getIdOrganizacaoVendas().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaAreaRegistroAreaRegistro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/nfo/catalogoWEB", ">>ListaAreaRegistro>AreaRegistro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAreaRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/nfo/catalogoWEB", "idAreaRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/nfo/catalogoWEB", "codigoArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/nfo/catalogoWEB", "idUf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/nfo/catalogoWEB", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/nfo/catalogoWEB", "nmUsuarioCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/nfo/catalogoWEB", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/nfo/catalogoWEB", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrganizacaoVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/nfo/catalogoWEB", "idOrganizacaoVendas"));
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
