/**
 * ResultadoBuscarListaFormaCondPagtoFormaPagamento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn;

public class ResultadoBuscarListaFormaCondPagtoFormaPagamento  implements java.io.Serializable {
    private long idFormaPagamento;

    private java.lang.String nmFormaPagamento;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private java.lang.String sgFormaPagamento;

    private java.lang.String nmTipoProduto;

    private java.lang.String nmPlataforma;

    private java.lang.String nmCanal;

    private java.lang.Double vlMinimoParcela;

    //private br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamentoSelecionada[][] listaCondicaoPagamentoSelecionada;
    
    private br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamentoSelecionada[] listaCondicaoPagamentoSelecionada;

    private br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamento[] listaCondicaoPagamento;

    public ResultadoBuscarListaFormaCondPagtoFormaPagamento() {
    }

    public ResultadoBuscarListaFormaCondPagtoFormaPagamento(
           long idFormaPagamento,
           java.lang.String nmFormaPagamento,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           java.lang.String sgFormaPagamento,
           java.lang.String nmTipoProduto,
           java.lang.String nmPlataforma,
           java.lang.String nmCanal,
           java.lang.Double vlMinimoParcela,
           br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamentoSelecionada[] listaCondicaoPagamentoSelecionada,
           br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamento[] listaCondicaoPagamento) {
           this.idFormaPagamento = idFormaPagamento;
           this.nmFormaPagamento = nmFormaPagamento;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.sgFormaPagamento = sgFormaPagamento;
           this.nmTipoProduto = nmTipoProduto;
           this.nmPlataforma = nmPlataforma;
           this.nmCanal = nmCanal;
           this.vlMinimoParcela = vlMinimoParcela;
           this.listaCondicaoPagamentoSelecionada = listaCondicaoPagamentoSelecionada;
           this.listaCondicaoPagamento = listaCondicaoPagamento;
    }


    /**
     * Gets the idFormaPagamento value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return idFormaPagamento
     */
    public long getIdFormaPagamento() {
        return idFormaPagamento;
    }


    /**
     * Sets the idFormaPagamento value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param idFormaPagamento
     */
    public void setIdFormaPagamento(long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }


    /**
     * Gets the nmFormaPagamento value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return nmFormaPagamento
     */
    public java.lang.String getNmFormaPagamento() {
        return nmFormaPagamento;
    }


    /**
     * Sets the nmFormaPagamento value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param nmFormaPagamento
     */
    public void setNmFormaPagamento(java.lang.String nmFormaPagamento) {
        this.nmFormaPagamento = nmFormaPagamento;
    }


    /**
     * Gets the dtCriacao value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the sgFormaPagamento value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return sgFormaPagamento
     */
    public java.lang.String getSgFormaPagamento() {
        return sgFormaPagamento;
    }


    /**
     * Sets the sgFormaPagamento value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param sgFormaPagamento
     */
    public void setSgFormaPagamento(java.lang.String sgFormaPagamento) {
        this.sgFormaPagamento = sgFormaPagamento;
    }


    /**
     * Gets the nmTipoProduto value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return nmTipoProduto
     */
    public java.lang.String getNmTipoProduto() {
        return nmTipoProduto;
    }


    /**
     * Sets the nmTipoProduto value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param nmTipoProduto
     */
    public void setNmTipoProduto(java.lang.String nmTipoProduto) {
        this.nmTipoProduto = nmTipoProduto;
    }


    /**
     * Gets the nmPlataforma value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return nmPlataforma
     */
    public java.lang.String getNmPlataforma() {
        return nmPlataforma;
    }


    /**
     * Sets the nmPlataforma value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param nmPlataforma
     */
    public void setNmPlataforma(java.lang.String nmPlataforma) {
        this.nmPlataforma = nmPlataforma;
    }


    /**
     * Gets the nmCanal value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return nmCanal
     */
    public java.lang.String getNmCanal() {
        return nmCanal;
    }


    /**
     * Sets the nmCanal value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param nmCanal
     */
    public void setNmCanal(java.lang.String nmCanal) {
        this.nmCanal = nmCanal;
    }


    /**
     * Gets the vlMinimoParcela value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return vlMinimoParcela
     */
    public java.lang.Double getVlMinimoParcela() {
        return vlMinimoParcela;
    }


    /**
     * Sets the vlMinimoParcela value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param vlMinimoParcela
     */
    public void setVlMinimoParcela(java.lang.Double vlMinimoParcela) {
        this.vlMinimoParcela = vlMinimoParcela;
    }

    
    
    /**
     * Gets the listaCondicaoPagamentoSelecionada value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return listaCondicaoPagamentoSelecionada
     */
   /* public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamentoSelecionada[][] getListaCondicaoPagamentoSelecionada() {
        return listaCondicaoPagamentoSelecionada;
    }*/


    /**
     * Sets the listaCondicaoPagamentoSelecionada value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param listaCondicaoPagamentoSelecionada
     */
    /*public void setListaCondicaoPagamentoSelecionada(br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamentoSelecionada[][] listaCondicaoPagamentoSelecionada) {
        this.listaCondicaoPagamentoSelecionada = listaCondicaoPagamentoSelecionada;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamentoSelecionada[] getListaCondicaoPagamentoSelecionada(int i) {
        return this.listaCondicaoPagamentoSelecionada[i];
    }

    public void setListaCondicaoPagamentoSelecionada(int i, br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamentoSelecionada[] _value) {
        this.listaCondicaoPagamentoSelecionada[i] = _value;
    }*/


    public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamentoSelecionada[] getListaCondicaoPagamentoSelecionada() {
		return listaCondicaoPagamentoSelecionada;
	}

	public void setListaCondicaoPagamentoSelecionada(
			br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamentoSelecionada[] listaCondicaoPagamentoSelecionada) {
		this.listaCondicaoPagamentoSelecionada = listaCondicaoPagamentoSelecionada;
	}

	/**
     * Gets the listaCondicaoPagamento value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @return listaCondicaoPagamento
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamento[] getListaCondicaoPagamento() {
        return listaCondicaoPagamento;
    }


    /**
     * Sets the listaCondicaoPagamento value for this ResultadoBuscarListaFormaCondPagtoFormaPagamento.
     * 
     * @param listaCondicaoPagamento
     */
    public void setListaCondicaoPagamento(br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamento[] listaCondicaoPagamento) {
        this.listaCondicaoPagamento = listaCondicaoPagamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaFormaCondPagtoFormaPagamento)) return false;
        ResultadoBuscarListaFormaCondPagtoFormaPagamento other = (ResultadoBuscarListaFormaCondPagtoFormaPagamento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idFormaPagamento == other.getIdFormaPagamento() &&
            ((this.nmFormaPagamento==null && other.getNmFormaPagamento()==null) || 
             (this.nmFormaPagamento!=null &&
              this.nmFormaPagamento.equals(other.getNmFormaPagamento()))) &&
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
            ((this.sgFormaPagamento==null && other.getSgFormaPagamento()==null) || 
             (this.sgFormaPagamento!=null &&
              this.sgFormaPagamento.equals(other.getSgFormaPagamento()))) &&
            ((this.nmTipoProduto==null && other.getNmTipoProduto()==null) || 
             (this.nmTipoProduto!=null &&
              this.nmTipoProduto.equals(other.getNmTipoProduto()))) &&
            ((this.nmPlataforma==null && other.getNmPlataforma()==null) || 
             (this.nmPlataforma!=null &&
              this.nmPlataforma.equals(other.getNmPlataforma()))) &&
            ((this.nmCanal==null && other.getNmCanal()==null) || 
             (this.nmCanal!=null &&
              this.nmCanal.equals(other.getNmCanal()))) &&
            ((this.vlMinimoParcela==null && other.getVlMinimoParcela()==null) || 
             (this.vlMinimoParcela!=null &&
              this.vlMinimoParcela.equals(other.getVlMinimoParcela()))) &&
            ((this.listaCondicaoPagamentoSelecionada==null && other.getListaCondicaoPagamentoSelecionada()==null) || 
             (this.listaCondicaoPagamentoSelecionada!=null &&
              java.util.Arrays.equals(this.listaCondicaoPagamentoSelecionada, other.getListaCondicaoPagamentoSelecionada()))) &&
            ((this.listaCondicaoPagamento==null && other.getListaCondicaoPagamento()==null) || 
             (this.listaCondicaoPagamento!=null &&
              java.util.Arrays.equals(this.listaCondicaoPagamento, other.getListaCondicaoPagamento())));
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
        _hashCode += new Long(getIdFormaPagamento()).hashCode();
        if (getNmFormaPagamento() != null) {
            _hashCode += getNmFormaPagamento().hashCode();
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
        if (getSgFormaPagamento() != null) {
            _hashCode += getSgFormaPagamento().hashCode();
        }
        if (getNmTipoProduto() != null) {
            _hashCode += getNmTipoProduto().hashCode();
        }
        if (getNmPlataforma() != null) {
            _hashCode += getNmPlataforma().hashCode();
        }
        if (getNmCanal() != null) {
            _hashCode += getNmCanal().hashCode();
        }
        if (getVlMinimoParcela() != null) {
            _hashCode += getVlMinimoParcela().hashCode();
        }
        if (getListaCondicaoPagamentoSelecionada() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCondicaoPagamentoSelecionada());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCondicaoPagamentoSelecionada(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaCondicaoPagamento() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCondicaoPagamento());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCondicaoPagamento(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaFormaCondPagtoFormaPagamento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", ">>ResultadoBuscarListaFormaCondPagto>FormaPagamento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFormaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "idFormaPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmFormaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "nmFormaPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "nmUsuarioCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgFormaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "sgFormaPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "nmTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "nmPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "nmCanal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vlMinimoParcela");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "vlMinimoParcela"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCondicaoPagamentoSelecionada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "ListaCondicaoPagamentoSelecionada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", ">>>ResultadoBuscarListaFormaCondPagto>FormaPagamento>ListaCondicaoPagamentoSelecionada"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCondicaoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "ListaCondicaoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "CondicaoPagamento"));
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
