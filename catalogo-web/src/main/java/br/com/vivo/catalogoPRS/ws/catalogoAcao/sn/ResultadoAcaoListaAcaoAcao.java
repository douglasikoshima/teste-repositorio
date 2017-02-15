/**
 * ResultadoAcaoListaAcaoAcao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcao.sn;

public class ResultadoAcaoListaAcaoAcao  implements java.io.Serializable {
    private long idAcao;

    private java.lang.String nmAcao;

    private java.lang.String sgAcao;

    private java.lang.String dsAcao;

    private java.lang.String inDisponivel;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    public ResultadoAcaoListaAcaoAcao() {
    }

    public ResultadoAcaoListaAcaoAcao(
           long idAcao,
           java.lang.String nmAcao,
           java.lang.String sgAcao,
           java.lang.String dsAcao,
           java.lang.String inDisponivel,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtAlteracao,
           java.lang.String nmUsuarioAlteracao) {
           this.idAcao = idAcao;
           this.nmAcao = nmAcao;
           this.sgAcao = sgAcao;
           this.dsAcao = dsAcao;
           this.inDisponivel = inDisponivel;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtAlteracao = dtAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the idAcao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @return idAcao
     */
    public long getIdAcao() {
        return idAcao;
    }


    /**
     * Sets the idAcao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @param idAcao
     */
    public void setIdAcao(long idAcao) {
        this.idAcao = idAcao;
    }


    /**
     * Gets the nmAcao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @return nmAcao
     */
    public java.lang.String getNmAcao() {
        return nmAcao;
    }


    /**
     * Sets the nmAcao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @param nmAcao
     */
    public void setNmAcao(java.lang.String nmAcao) {
        this.nmAcao = nmAcao;
    }


    /**
     * Gets the sgAcao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @return sgAcao
     */
    public java.lang.String getSgAcao() {
        return sgAcao;
    }


    /**
     * Sets the sgAcao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @param sgAcao
     */
    public void setSgAcao(java.lang.String sgAcao) {
        this.sgAcao = sgAcao;
    }


    /**
     * Gets the dsAcao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @return dsAcao
     */
    public java.lang.String getDsAcao() {
        return dsAcao;
    }


    /**
     * Sets the dsAcao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @param dsAcao
     */
    public void setDsAcao(java.lang.String dsAcao) {
        this.dsAcao = dsAcao;
    }


    /**
     * Gets the inDisponivel value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @return inDisponivel
     */
    public java.lang.String getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(java.lang.String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the dtCriacao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtAlteracao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @return dtAlteracao
     */
    public java.util.Calendar getDtAlteracao() {
        return dtAlteracao;
    }


    /**
     * Sets the dtAlteracao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @param dtAlteracao
     */
    public void setDtAlteracao(java.util.Calendar dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoAcaoListaAcaoAcao.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoAcaoListaAcaoAcao)) return false;
        ResultadoAcaoListaAcaoAcao other = (ResultadoAcaoListaAcaoAcao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idAcao == other.getIdAcao() &&
            ((this.nmAcao==null && other.getNmAcao()==null) || 
             (this.nmAcao!=null &&
              this.nmAcao.equals(other.getNmAcao()))) &&
            ((this.sgAcao==null && other.getSgAcao()==null) || 
             (this.sgAcao!=null &&
              this.sgAcao.equals(other.getSgAcao()))) &&
            ((this.dsAcao==null && other.getDsAcao()==null) || 
             (this.dsAcao!=null &&
              this.dsAcao.equals(other.getDsAcao()))) &&
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel()))) &&
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
        _hashCode += new Long(getIdAcao()).hashCode();
        if (getNmAcao() != null) {
            _hashCode += getNmAcao().hashCode();
        }
        if (getSgAcao() != null) {
            _hashCode += getSgAcao().hashCode();
        }
        if (getDsAcao() != null) {
            _hashCode += getDsAcao().hashCode();
        }
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
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
        new org.apache.axis.description.TypeDesc(ResultadoAcaoListaAcaoAcao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", ">>>ResultadoAcao>ListaAcao>Acao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAcao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "idAcao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmAcao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "nmAcao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgAcao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "sgAcao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsAcao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "dsAcao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "inDisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "nmUsuarioCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "dtAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "nmUsuarioAlteracao"));
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
