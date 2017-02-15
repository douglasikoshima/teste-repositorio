/**
 * ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia  implements java.io.Serializable {
    private long idTecnologia;

    private java.lang.String nmTecnologia;

    private java.lang.String sgTecnologia;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.lang.String nmUsuarioAlteracao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.Long idTecnologiaPai;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologiaListaTipoFrequenciaTipoFrequencia[] listaTipoFrequencia;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologiaTecnologiaReferencia tecnologiaReferencia;

    public ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia() {
    }

    public ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia(
           long idTecnologia,
           java.lang.String nmTecnologia,
           java.lang.String sgTecnologia,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.lang.String nmUsuarioAlteracao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.Long idTecnologiaPai,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologiaListaTipoFrequenciaTipoFrequencia[] listaTipoFrequencia,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologiaTecnologiaReferencia tecnologiaReferencia) {
           this.idTecnologia = idTecnologia;
           this.nmTecnologia = nmTecnologia;
           this.sgTecnologia = sgTecnologia;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.idTecnologiaPai = idTecnologiaPai;
           this.listaTipoFrequencia = listaTipoFrequencia;
           this.tecnologiaReferencia = tecnologiaReferencia;
    }


    /**
     * Gets the idTecnologia value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @return idTecnologia
     */
    public long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the nmTecnologia value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @return nmTecnologia
     */
    public java.lang.String getNmTecnologia() {
        return nmTecnologia;
    }


    /**
     * Sets the nmTecnologia value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @param nmTecnologia
     */
    public void setNmTecnologia(java.lang.String nmTecnologia) {
        this.nmTecnologia = nmTecnologia;
    }


    /**
     * Gets the sgTecnologia value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @return sgTecnologia
     */
    public java.lang.String getSgTecnologia() {
        return sgTecnologia;
    }


    /**
     * Sets the sgTecnologia value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @param sgTecnologia
     */
    public void setSgTecnologia(java.lang.String sgTecnologia) {
        this.sgTecnologia = sgTecnologia;
    }


    /**
     * Gets the dtCriacao value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the idTecnologiaPai value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @return idTecnologiaPai
     */
    public java.lang.Long getIdTecnologiaPai() {
        return idTecnologiaPai;
    }


    /**
     * Sets the idTecnologiaPai value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @param idTecnologiaPai
     */
    public void setIdTecnologiaPai(java.lang.Long idTecnologiaPai) {
        this.idTecnologiaPai = idTecnologiaPai;
    }


    /**
     * Gets the listaTipoFrequencia value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @return listaTipoFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologiaListaTipoFrequenciaTipoFrequencia[] getListaTipoFrequencia() {
        return listaTipoFrequencia;
    }


    /**
     * Sets the listaTipoFrequencia value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @param listaTipoFrequencia
     */
    public void setListaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologiaListaTipoFrequenciaTipoFrequencia[] listaTipoFrequencia) {
        this.listaTipoFrequencia = listaTipoFrequencia;
    }


    /**
     * Gets the tecnologiaReferencia value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @return tecnologiaReferencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologiaTecnologiaReferencia getTecnologiaReferencia() {
        return tecnologiaReferencia;
    }


    /**
     * Sets the tecnologiaReferencia value for this ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.
     * 
     * @param tecnologiaReferencia
     */
    public void setTecnologiaReferencia(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologiaTecnologiaReferencia tecnologiaReferencia) {
        this.tecnologiaReferencia = tecnologiaReferencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia)) return false;
        ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia other = (ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTecnologia == other.getIdTecnologia() &&
            ((this.nmTecnologia==null && other.getNmTecnologia()==null) || 
             (this.nmTecnologia!=null &&
              this.nmTecnologia.equals(other.getNmTecnologia()))) &&
            ((this.sgTecnologia==null && other.getSgTecnologia()==null) || 
             (this.sgTecnologia!=null &&
              this.sgTecnologia.equals(other.getSgTecnologia()))) &&
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
            ((this.idTecnologiaPai==null && other.getIdTecnologiaPai()==null) || 
             (this.idTecnologiaPai!=null &&
              this.idTecnologiaPai.equals(other.getIdTecnologiaPai()))) &&
            ((this.listaTipoFrequencia==null && other.getListaTipoFrequencia()==null) || 
             (this.listaTipoFrequencia!=null &&
              java.util.Arrays.equals(this.listaTipoFrequencia, other.getListaTipoFrequencia()))) &&
            ((this.tecnologiaReferencia==null && other.getTecnologiaReferencia()==null) || 
             (this.tecnologiaReferencia!=null &&
              this.tecnologiaReferencia.equals(other.getTecnologiaReferencia())));
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
        _hashCode += new Long(getIdTecnologia()).hashCode();
        if (getNmTecnologia() != null) {
            _hashCode += getNmTecnologia().hashCode();
        }
        if (getSgTecnologia() != null) {
            _hashCode += getSgTecnologia().hashCode();
        }
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
        if (getIdTecnologiaPai() != null) {
            _hashCode += getIdTecnologiaPai().hashCode();
        }
        if (getListaTipoFrequencia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTipoFrequencia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTipoFrequencia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTecnologiaReferencia() != null) {
            _hashCode += getTecnologiaReferencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoModeloPorId>GrupoProduto>ListaTecnologia>Tecnologia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "sgTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmUsuarioAlteracao"));
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
        elemField.setFieldName("idTecnologiaPai");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTecnologiaPai"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>>>ResultadoModeloPorId>GrupoProduto>ListaTecnologia>Tecnologia>ListaTipoFrequencia>TipoFrequencia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "TipoFrequencia"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tecnologiaReferencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "TecnologiaReferencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>>ResultadoModeloPorId>GrupoProduto>ListaTecnologia>Tecnologia>TecnologiaReferencia"));
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
