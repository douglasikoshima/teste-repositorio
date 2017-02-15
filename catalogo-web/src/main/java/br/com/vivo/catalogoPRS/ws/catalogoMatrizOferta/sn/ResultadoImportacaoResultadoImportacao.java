/**
 * ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ResultadoImportacaoResultadoImportacao  implements java.io.Serializable {
    private java.lang.Long idMatrizOfertaArquivo;

    private java.lang.String nmArquivo;

    private java.util.Calendar dtImportacao;

    private java.util.Calendar dtProcessamento;

    private java.lang.String usuarioResponsavel;

    private java.lang.String descStatus;

    private java.lang.String dscErro;

    private java.lang.String dsmensagemadvertencia;

    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda[] listaIdOrganizacaoVendaComDDD;

    public ResultadoImportacaoResultadoImportacao() {
    }

    public ResultadoImportacaoResultadoImportacao(
           java.lang.Long idMatrizOfertaArquivo,
           java.lang.String nmArquivo,
           java.util.Calendar dtImportacao,
           java.util.Calendar dtProcessamento,
           java.lang.String usuarioResponsavel,
           java.lang.String descStatus,
           java.lang.String dscErro,
           java.lang.String dsmensagemadvertencia,
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda[] listaIdOrganizacaoVendaComDDD) {
           this.idMatrizOfertaArquivo = idMatrizOfertaArquivo;
           this.nmArquivo = nmArquivo;
           this.dtImportacao = dtImportacao;
           this.dtProcessamento = dtProcessamento;
           this.usuarioResponsavel = usuarioResponsavel;
           this.descStatus = descStatus;
           this.dscErro = dscErro;
           this.dsmensagemadvertencia = dsmensagemadvertencia;
           this.listaIdOrganizacaoVendaComDDD = listaIdOrganizacaoVendaComDDD;
    }


    /**
     * Gets the idMatrizOfertaArquivo value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @return idMatrizOfertaArquivo
     */
    public java.lang.Long getIdMatrizOfertaArquivo() {
        return idMatrizOfertaArquivo;
    }


    /**
     * Sets the idMatrizOfertaArquivo value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @param idMatrizOfertaArquivo
     */
    public void setIdMatrizOfertaArquivo(java.lang.Long idMatrizOfertaArquivo) {
        this.idMatrizOfertaArquivo = idMatrizOfertaArquivo;
    }


    /**
     * Gets the nmArquivo value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @return nmArquivo
     */
    public java.lang.String getNmArquivo() {
        return nmArquivo;
    }


    /**
     * Sets the nmArquivo value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @param nmArquivo
     */
    public void setNmArquivo(java.lang.String nmArquivo) {
        this.nmArquivo = nmArquivo;
    }


    /**
     * Gets the dtImportacao value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @return dtImportacao
     */
    public java.util.Calendar getDtImportacao() {
        return dtImportacao;
    }


    /**
     * Sets the dtImportacao value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @param dtImportacao
     */
    public void setDtImportacao(java.util.Calendar dtImportacao) {
        this.dtImportacao = dtImportacao;
    }


    /**
     * Gets the dtProcessamento value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @return dtProcessamento
     */
    public java.util.Calendar getDtProcessamento() {
        return dtProcessamento;
    }


    /**
     * Sets the dtProcessamento value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @param dtProcessamento
     */
    public void setDtProcessamento(java.util.Calendar dtProcessamento) {
        this.dtProcessamento = dtProcessamento;
    }


    /**
     * Gets the usuarioResponsavel value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @return usuarioResponsavel
     */
    public java.lang.String getUsuarioResponsavel() {
        return usuarioResponsavel;
    }


    /**
     * Sets the usuarioResponsavel value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @param usuarioResponsavel
     */
    public void setUsuarioResponsavel(java.lang.String usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }


    /**
     * Gets the descStatus value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @return descStatus
     */
    public java.lang.String getDescStatus() {
        return descStatus;
    }


    /**
     * Sets the descStatus value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @param descStatus
     */
    public void setDescStatus(java.lang.String descStatus) {
        this.descStatus = descStatus;
    }


    /**
     * Gets the dscErro value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @return dscErro
     */
    public java.lang.String getDscErro() {
        return dscErro;
    }


    /**
     * Sets the dscErro value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @param dscErro
     */
    public void setDscErro(java.lang.String dscErro) {
        this.dscErro = dscErro;
    }


    /**
     * Gets the dsmensagemadvertencia value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @return dsmensagemadvertencia
     */
    public java.lang.String getDsmensagemadvertencia() {
        return dsmensagemadvertencia;
    }


    /**
     * Sets the dsmensagemadvertencia value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @param dsmensagemadvertencia
     */
    public void setDsmensagemadvertencia(java.lang.String dsmensagemadvertencia) {
        this.dsmensagemadvertencia = dsmensagemadvertencia;
    }


    /**
     * Gets the listaIdOrganizacaoVendaComDDD value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @return listaIdOrganizacaoVendaComDDD
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda[] getListaIdOrganizacaoVendaComDDD() {
        return listaIdOrganizacaoVendaComDDD;
    }


    /**
     * Sets the listaIdOrganizacaoVendaComDDD value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacao.
     * 
     * @param listaIdOrganizacaoVendaComDDD
     */
    public void setListaIdOrganizacaoVendaComDDD(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda[] listaIdOrganizacaoVendaComDDD) {
        this.listaIdOrganizacaoVendaComDDD = listaIdOrganizacaoVendaComDDD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoImportacaoResultadoImportacao)) return false;
        ResultadoImportacaoResultadoImportacao other = (ResultadoImportacaoResultadoImportacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idMatrizOfertaArquivo==null && other.getIdMatrizOfertaArquivo()==null) || 
             (this.idMatrizOfertaArquivo!=null &&
              this.idMatrizOfertaArquivo.equals(other.getIdMatrizOfertaArquivo()))) &&
            ((this.nmArquivo==null && other.getNmArquivo()==null) || 
             (this.nmArquivo!=null &&
              this.nmArquivo.equals(other.getNmArquivo()))) &&
            ((this.dtImportacao==null && other.getDtImportacao()==null) || 
             (this.dtImportacao!=null &&
              this.dtImportacao.equals(other.getDtImportacao()))) &&
            ((this.dtProcessamento==null && other.getDtProcessamento()==null) || 
             (this.dtProcessamento!=null &&
              this.dtProcessamento.equals(other.getDtProcessamento()))) &&
            ((this.usuarioResponsavel==null && other.getUsuarioResponsavel()==null) || 
             (this.usuarioResponsavel!=null &&
              this.usuarioResponsavel.equals(other.getUsuarioResponsavel()))) &&
            ((this.descStatus==null && other.getDescStatus()==null) || 
             (this.descStatus!=null &&
              this.descStatus.equals(other.getDescStatus()))) &&
            ((this.dscErro==null && other.getDscErro()==null) || 
             (this.dscErro!=null &&
              this.dscErro.equals(other.getDscErro()))) &&
            ((this.dsmensagemadvertencia==null && other.getDsmensagemadvertencia()==null) || 
             (this.dsmensagemadvertencia!=null &&
              this.dsmensagemadvertencia.equals(other.getDsmensagemadvertencia()))) &&
            ((this.listaIdOrganizacaoVendaComDDD==null && other.getListaIdOrganizacaoVendaComDDD()==null) || 
             (this.listaIdOrganizacaoVendaComDDD!=null &&
              java.util.Arrays.equals(this.listaIdOrganizacaoVendaComDDD, other.getListaIdOrganizacaoVendaComDDD())));
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
        if (getIdMatrizOfertaArquivo() != null) {
            _hashCode += getIdMatrizOfertaArquivo().hashCode();
        }
        if (getNmArquivo() != null) {
            _hashCode += getNmArquivo().hashCode();
        }
        if (getDtImportacao() != null) {
            _hashCode += getDtImportacao().hashCode();
        }
        if (getDtProcessamento() != null) {
            _hashCode += getDtProcessamento().hashCode();
        }
        if (getUsuarioResponsavel() != null) {
            _hashCode += getUsuarioResponsavel().hashCode();
        }
        if (getDescStatus() != null) {
            _hashCode += getDescStatus().hashCode();
        }
        if (getDscErro() != null) {
            _hashCode += getDscErro().hashCode();
        }
        if (getDsmensagemadvertencia() != null) {
            _hashCode += getDsmensagemadvertencia().hashCode();
        }
        if (getListaIdOrganizacaoVendaComDDD() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdOrganizacaoVendaComDDD());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdOrganizacaoVendaComDDD(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoImportacaoResultadoImportacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao>ResultadoImportacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMatrizOfertaArquivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idMatrizOfertaArquivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmArquivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "nmArquivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtImportacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dtImportacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtProcessamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dtProcessamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuarioResponsavel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "usuarioResponsavel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "descStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dscErro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dscErro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsmensagemadvertencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dsmensagemadvertencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdOrganizacaoVendaComDDD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ListaIdOrganizacaoVendaComDDD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao>ResultadoImportacao>ListaIdOrganizacaoVendaComDDD>OrganizacaoVenda"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "OrganizacaoVenda"));
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
