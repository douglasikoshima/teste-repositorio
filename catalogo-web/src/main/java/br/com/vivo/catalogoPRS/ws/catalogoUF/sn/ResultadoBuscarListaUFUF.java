/**
 * ResultadoBuscarListaUFUF.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoUF.sn;

public class ResultadoBuscarListaUFUF  implements java.io.Serializable {
    private long idUf;

    private java.lang.String nmUF;

    private long idRegional;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.lang.String nmUsuarioAlteracao;

    private java.util.Calendar dtUltimaAlteracao;

    private br.com.vivo.catalogoPRS.ws.catalogoWEB.ListaAreaRegistroAreaRegistro[] listaAreaRegistro;

    public ResultadoBuscarListaUFUF() {
    }

    public ResultadoBuscarListaUFUF(
           long idUf,
           java.lang.String nmUF,
           long idRegional,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.lang.String nmUsuarioAlteracao,
           java.util.Calendar dtUltimaAlteracao,
           br.com.vivo.catalogoPRS.ws.catalogoWEB.ListaAreaRegistroAreaRegistro[] listaAreaRegistro) {
           this.idUf = idUf;
           this.nmUF = nmUF;
           this.idRegional = idRegional;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.listaAreaRegistro = listaAreaRegistro;
    }


    /**
     * Gets the idUf value for this ResultadoBuscarListaUFUF.
     * 
     * @return idUf
     */
    public long getIdUf() {
        return idUf;
    }


    /**
     * Sets the idUf value for this ResultadoBuscarListaUFUF.
     * 
     * @param idUf
     */
    public void setIdUf(long idUf) {
        this.idUf = idUf;
    }


    /**
     * Gets the nmUF value for this ResultadoBuscarListaUFUF.
     * 
     * @return nmUF
     */
    public java.lang.String getNmUF() {
        return nmUF;
    }


    /**
     * Sets the nmUF value for this ResultadoBuscarListaUFUF.
     * 
     * @param nmUF
     */
    public void setNmUF(java.lang.String nmUF) {
        this.nmUF = nmUF;
    }


    /**
     * Gets the idRegional value for this ResultadoBuscarListaUFUF.
     * 
     * @return idRegional
     */
    public long getIdRegional() {
        return idRegional;
    }


    /**
     * Sets the idRegional value for this ResultadoBuscarListaUFUF.
     * 
     * @param idRegional
     */
    public void setIdRegional(long idRegional) {
        this.idRegional = idRegional;
    }


    /**
     * Gets the dtCriacao value for this ResultadoBuscarListaUFUF.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ResultadoBuscarListaUFUF.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ResultadoBuscarListaUFUF.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ResultadoBuscarListaUFUF.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoBuscarListaUFUF.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoBuscarListaUFUF.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ResultadoBuscarListaUFUF.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ResultadoBuscarListaUFUF.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the listaAreaRegistro value for this ResultadoBuscarListaUFUF.
     * 
     * @return listaAreaRegistro
     */
    public br.com.vivo.catalogoPRS.ws.catalogoWEB.ListaAreaRegistroAreaRegistro[] getListaAreaRegistro() {
        return listaAreaRegistro;
    }


    /**
     * Sets the listaAreaRegistro value for this ResultadoBuscarListaUFUF.
     * 
     * @param listaAreaRegistro
     */
    public void setListaAreaRegistro(br.com.vivo.catalogoPRS.ws.catalogoWEB.ListaAreaRegistroAreaRegistro[] listaAreaRegistro) {
        this.listaAreaRegistro = listaAreaRegistro;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaUFUF)) return false;
        ResultadoBuscarListaUFUF other = (ResultadoBuscarListaUFUF) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idUf == other.getIdUf() &&
            ((this.nmUF==null && other.getNmUF()==null) || 
             (this.nmUF!=null &&
              this.nmUF.equals(other.getNmUF()))) &&
            this.idRegional == other.getIdRegional() &&
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
            ((this.listaAreaRegistro==null && other.getListaAreaRegistro()==null) || 
             (this.listaAreaRegistro!=null &&
              java.util.Arrays.equals(this.listaAreaRegistro, other.getListaAreaRegistro())));
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
        _hashCode += new Long(getIdUf()).hashCode();
        if (getNmUF() != null) {
            _hashCode += getNmUF().hashCode();
        }
        _hashCode += new Long(getIdRegional()).hashCode();
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
        if (getListaAreaRegistro() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAreaRegistro());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAreaRegistro(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaUFUF.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoUF", ">>ResultadoBuscarListaUF>UF"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoUF", "idUf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoUF", "nmUF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idRegional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoUF", "idRegional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoUF", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoUF", "nmUsuarioCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoUF", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoUF", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAreaRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/nfo/catalogoWEB", "ListaAreaRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/nfo/catalogoWEB", ">ListaAreaRegistro"));
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
