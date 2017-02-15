/**
 * ResultadoBuscarListaAtributoPorIdServicoAtributo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoBuscarListaAtributoPorIdServicoAtributo  implements java.io.Serializable {
    private long idAtributo;

    private long idSistemaServico;

    private java.lang.String svcAttrNm;

    private java.lang.Long svcAttrSeq;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private java.lang.String svcAttrDesc;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaAtributoPorIdServicoAtributoListaAtributoValorAtributoValor[] listaAtributoValor;

    public ResultadoBuscarListaAtributoPorIdServicoAtributo() {
    }

    public ResultadoBuscarListaAtributoPorIdServicoAtributo(
           long idAtributo,
           long idSistemaServico,
           java.lang.String svcAttrNm,
           java.lang.Long svcAttrSeq,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           java.lang.String svcAttrDesc,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaAtributoPorIdServicoAtributoListaAtributoValorAtributoValor[] listaAtributoValor) {
           this.idAtributo = idAtributo;
           this.idSistemaServico = idSistemaServico;
           this.svcAttrNm = svcAttrNm;
           this.svcAttrSeq = svcAttrSeq;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.svcAttrDesc = svcAttrDesc;
           this.listaAtributoValor = listaAtributoValor;
    }


    /**
     * Gets the idAtributo value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @return idAtributo
     */
    public long getIdAtributo() {
        return idAtributo;
    }


    /**
     * Sets the idAtributo value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @param idAtributo
     */
    public void setIdAtributo(long idAtributo) {
        this.idAtributo = idAtributo;
    }


    /**
     * Gets the idSistemaServico value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @return idSistemaServico
     */
    public long getIdSistemaServico() {
        return idSistemaServico;
    }


    /**
     * Sets the idSistemaServico value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @param idSistemaServico
     */
    public void setIdSistemaServico(long idSistemaServico) {
        this.idSistemaServico = idSistemaServico;
    }


    /**
     * Gets the svcAttrNm value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @return svcAttrNm
     */
    public java.lang.String getSvcAttrNm() {
        return svcAttrNm;
    }


    /**
     * Sets the svcAttrNm value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @param svcAttrNm
     */
    public void setSvcAttrNm(java.lang.String svcAttrNm) {
        this.svcAttrNm = svcAttrNm;
    }


    /**
     * Gets the svcAttrSeq value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @return svcAttrSeq
     */
    public java.lang.Long getSvcAttrSeq() {
        return svcAttrSeq;
    }


    /**
     * Sets the svcAttrSeq value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @param svcAttrSeq
     */
    public void setSvcAttrSeq(java.lang.Long svcAttrSeq) {
        this.svcAttrSeq = svcAttrSeq;
    }


    /**
     * Gets the dtCriacao value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the svcAttrDesc value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @return svcAttrDesc
     */
    public java.lang.String getSvcAttrDesc() {
        return svcAttrDesc;
    }


    /**
     * Sets the svcAttrDesc value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @param svcAttrDesc
     */
    public void setSvcAttrDesc(java.lang.String svcAttrDesc) {
        this.svcAttrDesc = svcAttrDesc;
    }


    /**
     * Gets the listaAtributoValor value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @return listaAtributoValor
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaAtributoPorIdServicoAtributoListaAtributoValorAtributoValor[] getListaAtributoValor() {
        return listaAtributoValor;
    }


    /**
     * Sets the listaAtributoValor value for this ResultadoBuscarListaAtributoPorIdServicoAtributo.
     * 
     * @param listaAtributoValor
     */
    public void setListaAtributoValor(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaAtributoPorIdServicoAtributoListaAtributoValorAtributoValor[] listaAtributoValor) {
        this.listaAtributoValor = listaAtributoValor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaAtributoPorIdServicoAtributo)) return false;
        ResultadoBuscarListaAtributoPorIdServicoAtributo other = (ResultadoBuscarListaAtributoPorIdServicoAtributo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idAtributo == other.getIdAtributo() &&
            this.idSistemaServico == other.getIdSistemaServico() &&
            ((this.svcAttrNm==null && other.getSvcAttrNm()==null) || 
             (this.svcAttrNm!=null &&
              this.svcAttrNm.equals(other.getSvcAttrNm()))) &&
            ((this.svcAttrSeq==null && other.getSvcAttrSeq()==null) || 
             (this.svcAttrSeq!=null &&
              this.svcAttrSeq.equals(other.getSvcAttrSeq()))) &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            ((this.nmUsuarioCriacao==null && other.getNmUsuarioCriacao()==null) || 
             (this.nmUsuarioCriacao!=null &&
              this.nmUsuarioCriacao.equals(other.getNmUsuarioCriacao()))) &&
            ((this.dtUltimaAlteracao==null && other.getDtUltimaAlteracao()==null) || 
             (this.dtUltimaAlteracao!=null &&
              this.dtUltimaAlteracao.equals(other.getDtUltimaAlteracao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            ((this.svcAttrDesc==null && other.getSvcAttrDesc()==null) || 
             (this.svcAttrDesc!=null &&
              this.svcAttrDesc.equals(other.getSvcAttrDesc()))) &&
            ((this.listaAtributoValor==null && other.getListaAtributoValor()==null) || 
             (this.listaAtributoValor!=null &&
              java.util.Arrays.equals(this.listaAtributoValor, other.getListaAtributoValor())));
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
        _hashCode += new Long(getIdAtributo()).hashCode();
        _hashCode += new Long(getIdSistemaServico()).hashCode();
        if (getSvcAttrNm() != null) {
            _hashCode += getSvcAttrNm().hashCode();
        }
        if (getSvcAttrSeq() != null) {
            _hashCode += getSvcAttrSeq().hashCode();
        }
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        if (getNmUsuarioCriacao() != null) {
            _hashCode += getNmUsuarioCriacao().hashCode();
        }
        if (getDtUltimaAlteracao() != null) {
            _hashCode += getDtUltimaAlteracao().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        if (getSvcAttrDesc() != null) {
            _hashCode += getSvcAttrDesc().hashCode();
        }
        if (getListaAtributoValor() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAtributoValor());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAtributoValor(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaAtributoPorIdServicoAtributo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>ResultadoBuscarListaAtributoPorIdServico>Atributo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAtributo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idAtributo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSistemaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idSistemaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("svcAttrNm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "svcAttrNm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("svcAttrSeq");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "svcAttrSeq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmUsuarioCriacao"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("svcAttrDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "svcAttrDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAtributoValor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaAtributoValor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>>ResultadoBuscarListaAtributoPorIdServico>Atributo>ListaAtributoValor>AtributoValor"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "AtributoValor"));
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
