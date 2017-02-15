/**
 * ResultadoBuscarListaServicoListaServicoRetornoServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoBuscarListaServicoListaServicoRetornoServico  implements java.io.Serializable {
    private long idServico;

    private java.lang.String nmComercial;

    private java.lang.String inPlano;

    private java.util.Calendar dtInicial;

    private java.util.Calendar dtFinal;

    private java.math.BigDecimal valor;

    private java.lang.Long idCategoria;

    private java.lang.String nmCategoria;

    private java.lang.String inSimulacao;

    private java.lang.String descricao;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private java.lang.String indisponivel;

    private java.lang.String cdAnatel;

    private java.lang.String tpTarifa;

    private java.lang.String cdCodigo;

    private java.lang.String dscTipoServico;

    private long rNum;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa[] listaServicoTarifa;

    public ResultadoBuscarListaServicoListaServicoRetornoServico() {
    }

    public ResultadoBuscarListaServicoListaServicoRetornoServico(
           long idServico,
           java.lang.String nmComercial,
           java.lang.String inPlano,
           java.util.Calendar dtInicial,
           java.util.Calendar dtFinal,
           java.math.BigDecimal valor,
           java.lang.Long idCategoria,
           java.lang.String nmCategoria,
           java.lang.String inSimulacao,
           java.lang.String descricao,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           java.lang.String indisponivel,
           java.lang.String cdAnatel,
           java.lang.String tpTarifa,
           java.lang.String cdCodigo,
           java.lang.String dscTipoServico,
           long rNum,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa[] listaServicoTarifa) {
           this.idServico = idServico;
           this.nmComercial = nmComercial;
           this.inPlano = inPlano;
           this.dtInicial = dtInicial;
           this.dtFinal = dtFinal;
           this.valor = valor;
           this.idCategoria = idCategoria;
           this.nmCategoria = nmCategoria;
           this.inSimulacao = inSimulacao;
           this.descricao = descricao;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.indisponivel = indisponivel;
           this.cdAnatel = cdAnatel;
           this.tpTarifa = tpTarifa;
           this.cdCodigo = cdCodigo;
           this.dscTipoServico = dscTipoServico;
           this.rNum = rNum;
           this.listaServicoTarifa = listaServicoTarifa;
    }


    /**
     * Gets the idServico value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return idServico
     */
    public long getIdServico() {
        return idServico;
    }


    /**
     * Sets the idServico value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param idServico
     */
    public void setIdServico(long idServico) {
        this.idServico = idServico;
    }


    /**
     * Gets the nmComercial value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the inPlano value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return inPlano
     */
    public java.lang.String getInPlano() {
        return inPlano;
    }


    /**
     * Sets the inPlano value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param inPlano
     */
    public void setInPlano(java.lang.String inPlano) {
        this.inPlano = inPlano;
    }


    /**
     * Gets the dtInicial value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return dtInicial
     */
    public java.util.Calendar getDtInicial() {
        return dtInicial;
    }


    /**
     * Sets the dtInicial value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param dtInicial
     */
    public void setDtInicial(java.util.Calendar dtInicial) {
        this.dtInicial = dtInicial;
    }


    /**
     * Gets the dtFinal value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return dtFinal
     */
    public java.util.Calendar getDtFinal() {
        return dtFinal;
    }


    /**
     * Sets the dtFinal value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param dtFinal
     */
    public void setDtFinal(java.util.Calendar dtFinal) {
        this.dtFinal = dtFinal;
    }


    /**
     * Gets the valor value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return valor
     */
    public java.math.BigDecimal getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param valor
     */
    public void setValor(java.math.BigDecimal valor) {
        this.valor = valor;
    }


    /**
     * Gets the idCategoria value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return idCategoria
     */
    public java.lang.Long getIdCategoria() {
        return idCategoria;
    }


    /**
     * Sets the idCategoria value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param idCategoria
     */
    public void setIdCategoria(java.lang.Long idCategoria) {
        this.idCategoria = idCategoria;
    }


    /**
     * Gets the nmCategoria value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return nmCategoria
     */
    public java.lang.String getNmCategoria() {
        return nmCategoria;
    }


    /**
     * Sets the nmCategoria value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param nmCategoria
     */
    public void setNmCategoria(java.lang.String nmCategoria) {
        this.nmCategoria = nmCategoria;
    }


    /**
     * Gets the inSimulacao value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return inSimulacao
     */
    public java.lang.String getInSimulacao() {
        return inSimulacao;
    }


    /**
     * Sets the inSimulacao value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param inSimulacao
     */
    public void setInSimulacao(java.lang.String inSimulacao) {
        this.inSimulacao = inSimulacao;
    }


    /**
     * Gets the descricao value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return descricao
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param descricao
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }


    /**
     * Gets the dtCriacao value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the indisponivel value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return indisponivel
     */
    public java.lang.String getIndisponivel() {
        return indisponivel;
    }


    /**
     * Sets the indisponivel value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param indisponivel
     */
    public void setIndisponivel(java.lang.String indisponivel) {
        this.indisponivel = indisponivel;
    }


    /**
     * Gets the cdAnatel value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return cdAnatel
     */
    public java.lang.String getCdAnatel() {
        return cdAnatel;
    }


    /**
     * Sets the cdAnatel value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param cdAnatel
     */
    public void setCdAnatel(java.lang.String cdAnatel) {
        this.cdAnatel = cdAnatel;
    }


    /**
     * Gets the tpTarifa value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return tpTarifa
     */
    public java.lang.String getTpTarifa() {
        return tpTarifa;
    }


    /**
     * Sets the tpTarifa value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param tpTarifa
     */
    public void setTpTarifa(java.lang.String tpTarifa) {
        this.tpTarifa = tpTarifa;
    }


    /**
     * Gets the cdCodigo value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return cdCodigo
     */
    public java.lang.String getCdCodigo() {
        return cdCodigo;
    }


    /**
     * Sets the cdCodigo value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param cdCodigo
     */
    public void setCdCodigo(java.lang.String cdCodigo) {
        this.cdCodigo = cdCodigo;
    }


    /**
     * Gets the dscTipoServico value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return dscTipoServico
     */
    public java.lang.String getDscTipoServico() {
        return dscTipoServico;
    }


    /**
     * Sets the dscTipoServico value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param dscTipoServico
     */
    public void setDscTipoServico(java.lang.String dscTipoServico) {
        this.dscTipoServico = dscTipoServico;
    }


    /**
     * Gets the rNum value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return rNum
     */
    public long getRNum() {
        return rNum;
    }


    /**
     * Sets the rNum value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param rNum
     */
    public void setRNum(long rNum) {
        this.rNum = rNum;
    }


    /**
     * Gets the listaServicoTarifa value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @return listaServicoTarifa
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa[] getListaServicoTarifa() {
        return listaServicoTarifa;
    }


    /**
     * Sets the listaServicoTarifa value for this ResultadoBuscarListaServicoListaServicoRetornoServico.
     * 
     * @param listaServicoTarifa
     */
    public void setListaServicoTarifa(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa[] listaServicoTarifa) {
        this.listaServicoTarifa = listaServicoTarifa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaServicoListaServicoRetornoServico)) return false;
        ResultadoBuscarListaServicoListaServicoRetornoServico other = (ResultadoBuscarListaServicoListaServicoRetornoServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idServico == other.getIdServico() &&
            ((this.nmComercial==null && other.getNmComercial()==null) || 
             (this.nmComercial!=null &&
              this.nmComercial.equals(other.getNmComercial()))) &&
            ((this.inPlano==null && other.getInPlano()==null) || 
             (this.inPlano!=null &&
              this.inPlano.equals(other.getInPlano()))) &&
            ((this.dtInicial==null && other.getDtInicial()==null) || 
             (this.dtInicial!=null &&
              this.dtInicial.equals(other.getDtInicial()))) &&
            ((this.dtFinal==null && other.getDtFinal()==null) || 
             (this.dtFinal!=null &&
              this.dtFinal.equals(other.getDtFinal()))) &&
            ((this.valor==null && other.getValor()==null) || 
             (this.valor!=null &&
              this.valor.equals(other.getValor()))) &&
            ((this.idCategoria==null && other.getIdCategoria()==null) || 
             (this.idCategoria!=null &&
              this.idCategoria.equals(other.getIdCategoria()))) &&
            ((this.nmCategoria==null && other.getNmCategoria()==null) || 
             (this.nmCategoria!=null &&
              this.nmCategoria.equals(other.getNmCategoria()))) &&
            ((this.inSimulacao==null && other.getInSimulacao()==null) || 
             (this.inSimulacao!=null &&
              this.inSimulacao.equals(other.getInSimulacao()))) &&
            ((this.descricao==null && other.getDescricao()==null) || 
             (this.descricao!=null &&
              this.descricao.equals(other.getDescricao()))) &&
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
            ((this.indisponivel==null && other.getIndisponivel()==null) || 
             (this.indisponivel!=null &&
              this.indisponivel.equals(other.getIndisponivel()))) &&
            ((this.cdAnatel==null && other.getCdAnatel()==null) || 
             (this.cdAnatel!=null &&
              this.cdAnatel.equals(other.getCdAnatel()))) &&
            ((this.tpTarifa==null && other.getTpTarifa()==null) || 
             (this.tpTarifa!=null &&
              this.tpTarifa.equals(other.getTpTarifa()))) &&
            ((this.cdCodigo==null && other.getCdCodigo()==null) || 
             (this.cdCodigo!=null &&
              this.cdCodigo.equals(other.getCdCodigo()))) &&
            ((this.dscTipoServico==null && other.getDscTipoServico()==null) || 
             (this.dscTipoServico!=null &&
              this.dscTipoServico.equals(other.getDscTipoServico()))) &&
            this.rNum == other.getRNum() &&
            ((this.listaServicoTarifa==null && other.getListaServicoTarifa()==null) || 
             (this.listaServicoTarifa!=null &&
              java.util.Arrays.equals(this.listaServicoTarifa, other.getListaServicoTarifa())));
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
        _hashCode += new Long(getIdServico()).hashCode();
        if (getNmComercial() != null) {
            _hashCode += getNmComercial().hashCode();
        }
        if (getInPlano() != null) {
            _hashCode += getInPlano().hashCode();
        }
        if (getDtInicial() != null) {
            _hashCode += getDtInicial().hashCode();
        }
        if (getDtFinal() != null) {
            _hashCode += getDtFinal().hashCode();
        }
        if (getValor() != null) {
            _hashCode += getValor().hashCode();
        }
        if (getIdCategoria() != null) {
            _hashCode += getIdCategoria().hashCode();
        }
        if (getNmCategoria() != null) {
            _hashCode += getNmCategoria().hashCode();
        }
        if (getInSimulacao() != null) {
            _hashCode += getInSimulacao().hashCode();
        }
        if (getDescricao() != null) {
            _hashCode += getDescricao().hashCode();
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
        if (getIndisponivel() != null) {
            _hashCode += getIndisponivel().hashCode();
        }
        if (getCdAnatel() != null) {
            _hashCode += getCdAnatel().hashCode();
        }
        if (getTpTarifa() != null) {
            _hashCode += getTpTarifa().hashCode();
        }
        if (getCdCodigo() != null) {
            _hashCode += getCdCodigo().hashCode();
        }
        if (getDscTipoServico() != null) {
            _hashCode += getDscTipoServico().hashCode();
        }
        _hashCode += new Long(getRNum()).hashCode();
        if (getListaServicoTarifa() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaServicoTarifa());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaServicoTarifa(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaServicoListaServicoRetornoServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>ResultadoBuscarListaServico>ListaServico>RetornoServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmComercial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmComercial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "inPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "dtInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "dtFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "valor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCategoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idCategoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCategoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmCategoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inSimulacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "inSimulacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "descricao"));
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
        elemField.setFieldName("indisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "indisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdAnatel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "cdAnatel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tpTarifa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "tpTarifa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "cdCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dscTipoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "dscTipoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "rNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaServicoTarifa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaServicoTarifa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ListaServicoTarifa"));
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
