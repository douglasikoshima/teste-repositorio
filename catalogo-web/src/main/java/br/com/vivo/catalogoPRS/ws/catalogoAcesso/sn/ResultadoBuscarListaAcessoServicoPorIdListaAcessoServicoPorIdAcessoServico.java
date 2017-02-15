/**
 * ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico  implements java.io.Serializable {
    private long idAcessoServico;

    private long idPerfil;

    private java.lang.String nmComercial;

    private java.lang.String inRestricaoConsulta;

    private java.lang.String inRestricaoAtivacao;

    private java.lang.String inRestricaoDesativacao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private long idSistemaAcesso;

    public ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico() {
    }

    public ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico(
           long idAcessoServico,
           long idPerfil,
           java.lang.String nmComercial,
           java.lang.String inRestricaoConsulta,
           java.lang.String inRestricaoAtivacao,
           java.lang.String inRestricaoDesativacao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           long idSistemaAcesso) {
           this.idAcessoServico = idAcessoServico;
           this.idPerfil = idPerfil;
           this.nmComercial = nmComercial;
           this.inRestricaoConsulta = inRestricaoConsulta;
           this.inRestricaoAtivacao = inRestricaoAtivacao;
           this.inRestricaoDesativacao = inRestricaoDesativacao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.idSistemaAcesso = idSistemaAcesso;
    }


    /**
     * Gets the idAcessoServico value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @return idAcessoServico
     */
    public long getIdAcessoServico() {
        return idAcessoServico;
    }


    /**
     * Sets the idAcessoServico value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @param idAcessoServico
     */
    public void setIdAcessoServico(long idAcessoServico) {
        this.idAcessoServico = idAcessoServico;
    }


    /**
     * Gets the idPerfil value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @return idPerfil
     */
    public long getIdPerfil() {
        return idPerfil;
    }


    /**
     * Sets the idPerfil value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @param idPerfil
     */
    public void setIdPerfil(long idPerfil) {
        this.idPerfil = idPerfil;
    }


    /**
     * Gets the nmComercial value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the inRestricaoConsulta value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @return inRestricaoConsulta
     */
    public java.lang.String getInRestricaoConsulta() {
        return inRestricaoConsulta;
    }


    /**
     * Sets the inRestricaoConsulta value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @param inRestricaoConsulta
     */
    public void setInRestricaoConsulta(java.lang.String inRestricaoConsulta) {
        this.inRestricaoConsulta = inRestricaoConsulta;
    }


    /**
     * Gets the inRestricaoAtivacao value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @return inRestricaoAtivacao
     */
    public java.lang.String getInRestricaoAtivacao() {
        return inRestricaoAtivacao;
    }


    /**
     * Sets the inRestricaoAtivacao value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @param inRestricaoAtivacao
     */
    public void setInRestricaoAtivacao(java.lang.String inRestricaoAtivacao) {
        this.inRestricaoAtivacao = inRestricaoAtivacao;
    }


    /**
     * Gets the inRestricaoDesativacao value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @return inRestricaoDesativacao
     */
    public java.lang.String getInRestricaoDesativacao() {
        return inRestricaoDesativacao;
    }


    /**
     * Sets the inRestricaoDesativacao value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @param inRestricaoDesativacao
     */
    public void setInRestricaoDesativacao(java.lang.String inRestricaoDesativacao) {
        this.inRestricaoDesativacao = inRestricaoDesativacao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the idSistemaAcesso value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @return idSistemaAcesso
     */
    public long getIdSistemaAcesso() {
        return idSistemaAcesso;
    }


    /**
     * Sets the idSistemaAcesso value for this ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.
     * 
     * @param idSistemaAcesso
     */
    public void setIdSistemaAcesso(long idSistemaAcesso) {
        this.idSistemaAcesso = idSistemaAcesso;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico)) return false;
        ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico other = (ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idAcessoServico == other.getIdAcessoServico() &&
            this.idPerfil == other.getIdPerfil() &&
            ((this.nmComercial==null && other.getNmComercial()==null) || 
             (this.nmComercial!=null &&
              this.nmComercial.equals(other.getNmComercial()))) &&
            ((this.inRestricaoConsulta==null && other.getInRestricaoConsulta()==null) || 
             (this.inRestricaoConsulta!=null &&
              this.inRestricaoConsulta.equals(other.getInRestricaoConsulta()))) &&
            ((this.inRestricaoAtivacao==null && other.getInRestricaoAtivacao()==null) || 
             (this.inRestricaoAtivacao!=null &&
              this.inRestricaoAtivacao.equals(other.getInRestricaoAtivacao()))) &&
            ((this.inRestricaoDesativacao==null && other.getInRestricaoDesativacao()==null) || 
             (this.inRestricaoDesativacao!=null &&
              this.inRestricaoDesativacao.equals(other.getInRestricaoDesativacao()))) &&
            ((this.dtUltimaAlteracao==null && other.getDtUltimaAlteracao()==null) || 
             (this.dtUltimaAlteracao!=null &&
              this.dtUltimaAlteracao.equals(other.getDtUltimaAlteracao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            this.idSistemaAcesso == other.getIdSistemaAcesso();
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
        _hashCode += new Long(getIdAcessoServico()).hashCode();
        _hashCode += new Long(getIdPerfil()).hashCode();
        if (getNmComercial() != null) {
            _hashCode += getNmComercial().hashCode();
        }
        if (getInRestricaoConsulta() != null) {
            _hashCode += getInRestricaoConsulta().hashCode();
        }
        if (getInRestricaoAtivacao() != null) {
            _hashCode += getInRestricaoAtivacao().hashCode();
        }
        if (getInRestricaoDesativacao() != null) {
            _hashCode += getInRestricaoDesativacao().hashCode();
        }
        if (getDtUltimaAlteracao() != null) {
            _hashCode += getDtUltimaAlteracao().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        _hashCode += new Long(getIdSistemaAcesso()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>>ResultadoBuscarListaAcessoServicoPorId>ListaAcessoServicoPorId>AcessoServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAcessoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idAcessoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPerfil");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idPerfil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmComercial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "nmComercial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inRestricaoConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "inRestricaoConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inRestricaoAtivacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "inRestricaoAtivacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inRestricaoDesativacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "inRestricaoDesativacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSistemaAcesso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idSistemaAcesso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
