/**
 * ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico  implements java.io.Serializable {
    private long idAcessoServico;

    private long idPerfilSCA;

    private java.lang.String nmComercial;

    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoConsulta inRestricaoConsulta;

    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoAtivacao inRestricaoAtivacao;

    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoDesativacao inRestricaoDesativacao;

    private long idSistemaAcesso;

    public ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico() {
    }

    public ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico(
           long idAcessoServico,
           long idPerfilSCA,
           java.lang.String nmComercial,
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoConsulta inRestricaoConsulta,
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoAtivacao inRestricaoAtivacao,
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoDesativacao inRestricaoDesativacao,
           long idSistemaAcesso) {
           this.idAcessoServico = idAcessoServico;
           this.idPerfilSCA = idPerfilSCA;
           this.nmComercial = nmComercial;
           this.inRestricaoConsulta = inRestricaoConsulta;
           this.inRestricaoAtivacao = inRestricaoAtivacao;
           this.inRestricaoDesativacao = inRestricaoDesativacao;
           this.idSistemaAcesso = idSistemaAcesso;
    }


    /**
     * Gets the idAcessoServico value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @return idAcessoServico
     */
    public long getIdAcessoServico() {
        return idAcessoServico;
    }


    /**
     * Sets the idAcessoServico value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @param idAcessoServico
     */
    public void setIdAcessoServico(long idAcessoServico) {
        this.idAcessoServico = idAcessoServico;
    }


    /**
     * Gets the idPerfilSCA value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @return idPerfilSCA
     */
    public long getIdPerfilSCA() {
        return idPerfilSCA;
    }


    /**
     * Sets the idPerfilSCA value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @param idPerfilSCA
     */
    public void setIdPerfilSCA(long idPerfilSCA) {
        this.idPerfilSCA = idPerfilSCA;
    }


    /**
     * Gets the nmComercial value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the inRestricaoConsulta value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @return inRestricaoConsulta
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoConsulta getInRestricaoConsulta() {
        return inRestricaoConsulta;
    }


    /**
     * Sets the inRestricaoConsulta value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @param inRestricaoConsulta
     */
    public void setInRestricaoConsulta(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoConsulta inRestricaoConsulta) {
        this.inRestricaoConsulta = inRestricaoConsulta;
    }


    /**
     * Gets the inRestricaoAtivacao value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @return inRestricaoAtivacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoAtivacao getInRestricaoAtivacao() {
        return inRestricaoAtivacao;
    }


    /**
     * Sets the inRestricaoAtivacao value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @param inRestricaoAtivacao
     */
    public void setInRestricaoAtivacao(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoAtivacao inRestricaoAtivacao) {
        this.inRestricaoAtivacao = inRestricaoAtivacao;
    }


    /**
     * Gets the inRestricaoDesativacao value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @return inRestricaoDesativacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoDesativacao getInRestricaoDesativacao() {
        return inRestricaoDesativacao;
    }


    /**
     * Sets the inRestricaoDesativacao value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @param inRestricaoDesativacao
     */
    public void setInRestricaoDesativacao(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoDesativacao inRestricaoDesativacao) {
        this.inRestricaoDesativacao = inRestricaoDesativacao;
    }


    /**
     * Gets the idSistemaAcesso value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @return idSistemaAcesso
     */
    public long getIdSistemaAcesso() {
        return idSistemaAcesso;
    }


    /**
     * Sets the idSistemaAcesso value for this ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.
     * 
     * @param idSistemaAcesso
     */
    public void setIdSistemaAcesso(long idSistemaAcesso) {
        this.idSistemaAcesso = idSistemaAcesso;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico)) return false;
        ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico other = (ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idAcessoServico == other.getIdAcessoServico() &&
            this.idPerfilSCA == other.getIdPerfilSCA() &&
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
        _hashCode += new Long(getIdPerfilSCA()).hashCode();
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
        _hashCode += new Long(getIdSistemaAcesso()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>>ResultadoBuscarListaAcessoServico>ListaAcessoServico>AcessoServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAcessoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idAcessoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPerfilSCA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idPerfilSCA"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>>>ResultadoBuscarListaAcessoServico>ListaAcessoServico>AcessoServico>inRestricaoConsulta"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inRestricaoAtivacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "inRestricaoAtivacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>>>ResultadoBuscarListaAcessoServico>ListaAcessoServico>AcessoServico>inRestricaoAtivacao"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inRestricaoDesativacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "inRestricaoDesativacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>>>ResultadoBuscarListaAcessoServico>ListaAcessoServico>AcessoServico>inRestricaoDesativacao"));
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
