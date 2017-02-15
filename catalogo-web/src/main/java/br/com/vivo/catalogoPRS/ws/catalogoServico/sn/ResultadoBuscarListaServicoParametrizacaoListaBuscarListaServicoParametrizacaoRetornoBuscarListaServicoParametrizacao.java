/**
 * ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao  implements java.io.Serializable {
    private long idServico;

    private java.lang.String cdServico;

    private java.lang.String nmComercial;

    private java.lang.Long idCategoriaCatalogo;

    private java.lang.String nmCategoriaCatalogo;

    private java.lang.String idCategoriaLegado;

    private java.lang.String nmCategoriaLegado;

    private java.lang.Long idTpServico;

    private java.lang.String dscTipoServico;

    private java.lang.Long qtdMinAtivCatalogo;

    private java.lang.Long qtdMinAtivLegado;

    private java.lang.Long qtdMaxAtivCatalogo;

    private java.lang.Long qtdMaxAtivLegado;

    private java.util.Calendar dtAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrizacaoRetornoBuscarListaServicoParametrizacaoInCatalogo inDisponibilidadeCatalogo;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrizacaoRetornoBuscarListaServicoParametrizacaoInDisponibilidadeLegado inDisponibilidadeLegado;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacaoAtivaWifi ativaWifi;

    public ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao() {
    }

    public ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao(
           long idServico,
           java.lang.String cdServico,
           java.lang.String nmComercial,
           java.lang.Long idCategoriaCatalogo,
           java.lang.String nmCategoriaCatalogo,
           java.lang.String idCategoriaLegado,
           java.lang.String nmCategoriaLegado,
           java.lang.Long idTpServico,
           java.lang.String dscTipoServico,
           java.lang.Long qtdMinAtivCatalogo,
           java.lang.Long qtdMinAtivLegado,
           java.lang.Long qtdMaxAtivCatalogo,
           java.lang.Long qtdMaxAtivLegado,
           java.util.Calendar dtAlteracao,
           java.lang.String nmUsuarioAlteracao,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrizacaoRetornoBuscarListaServicoParametrizacaoInCatalogo inDisponibilidadeCatalogo,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrizacaoRetornoBuscarListaServicoParametrizacaoInDisponibilidadeLegado inDisponibilidadeLegado,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacaoAtivaWifi ativaWifi) {
           this.idServico = idServico;
           this.cdServico = cdServico;
           this.nmComercial = nmComercial;
           this.idCategoriaCatalogo = idCategoriaCatalogo;
           this.nmCategoriaCatalogo = nmCategoriaCatalogo;
           this.idCategoriaLegado = idCategoriaLegado;
           this.nmCategoriaLegado = nmCategoriaLegado;
           this.idTpServico = idTpServico;
           this.dscTipoServico = dscTipoServico;
           this.qtdMinAtivCatalogo = qtdMinAtivCatalogo;
           this.qtdMinAtivLegado = qtdMinAtivLegado;
           this.qtdMaxAtivCatalogo = qtdMaxAtivCatalogo;
           this.qtdMaxAtivLegado = qtdMaxAtivLegado;
           this.dtAlteracao = dtAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
           this.inDisponibilidadeLegado = inDisponibilidadeLegado;
           this.ativaWifi = ativaWifi;
    }


    /**
     * Gets the idServico value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return idServico
     */
    public long getIdServico() {
        return idServico;
    }


    /**
     * Sets the idServico value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param idServico
     */
    public void setIdServico(long idServico) {
        this.idServico = idServico;
    }


    /**
     * Gets the cdServico value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return cdServico
     */
    public java.lang.String getCdServico() {
        return cdServico;
    }


    /**
     * Sets the cdServico value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param cdServico
     */
    public void setCdServico(java.lang.String cdServico) {
        this.cdServico = cdServico;
    }


    /**
     * Gets the nmComercial value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the idCategoriaCatalogo value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return idCategoriaCatalogo
     */
    public java.lang.Long getIdCategoriaCatalogo() {
        return idCategoriaCatalogo;
    }


    /**
     * Sets the idCategoriaCatalogo value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param idCategoriaCatalogo
     */
    public void setIdCategoriaCatalogo(java.lang.Long idCategoriaCatalogo) {
        this.idCategoriaCatalogo = idCategoriaCatalogo;
    }


    /**
     * Gets the nmCategoriaCatalogo value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return nmCategoriaCatalogo
     */
    public java.lang.String getNmCategoriaCatalogo() {
        return nmCategoriaCatalogo;
    }


    /**
     * Sets the nmCategoriaCatalogo value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param nmCategoriaCatalogo
     */
    public void setNmCategoriaCatalogo(java.lang.String nmCategoriaCatalogo) {
        this.nmCategoriaCatalogo = nmCategoriaCatalogo;
    }


    /**
     * Gets the idCategoriaLegado value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return idCategoriaLegado
     */
    public java.lang.String getIdCategoriaLegado() {
        return idCategoriaLegado;
    }


    /**
     * Sets the idCategoriaLegado value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param idCategoriaLegado
     */
    public void setIdCategoriaLegado(java.lang.String idCategoriaLegado) {
        this.idCategoriaLegado = idCategoriaLegado;
    }


    /**
     * Gets the nmCategoriaLegado value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return nmCategoriaLegado
     */
    public java.lang.String getNmCategoriaLegado() {
        return nmCategoriaLegado;
    }


    /**
     * Sets the nmCategoriaLegado value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param nmCategoriaLegado
     */
    public void setNmCategoriaLegado(java.lang.String nmCategoriaLegado) {
        this.nmCategoriaLegado = nmCategoriaLegado;
    }


    /**
     * Gets the idTpServico value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return idTpServico
     */
    public java.lang.Long getIdTpServico() {
        return idTpServico;
    }


    /**
     * Sets the idTpServico value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param idTpServico
     */
    public void setIdTpServico(java.lang.Long idTpServico) {
        this.idTpServico = idTpServico;
    }


    /**
     * Gets the dscTipoServico value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return dscTipoServico
     */
    public java.lang.String getDscTipoServico() {
        return dscTipoServico;
    }


    /**
     * Sets the dscTipoServico value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param dscTipoServico
     */
    public void setDscTipoServico(java.lang.String dscTipoServico) {
        this.dscTipoServico = dscTipoServico;
    }


    /**
     * Gets the qtdMinAtivCatalogo value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return qtdMinAtivCatalogo
     */
    public java.lang.Long getQtdMinAtivCatalogo() {
        return qtdMinAtivCatalogo;
    }


    /**
     * Sets the qtdMinAtivCatalogo value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param qtdMinAtivCatalogo
     */
    public void setQtdMinAtivCatalogo(java.lang.Long qtdMinAtivCatalogo) {
        this.qtdMinAtivCatalogo = qtdMinAtivCatalogo;
    }


    /**
     * Gets the qtdMinAtivLegado value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return qtdMinAtivLegado
     */
    public java.lang.Long getQtdMinAtivLegado() {
        return qtdMinAtivLegado;
    }


    /**
     * Sets the qtdMinAtivLegado value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param qtdMinAtivLegado
     */
    public void setQtdMinAtivLegado(java.lang.Long qtdMinAtivLegado) {
        this.qtdMinAtivLegado = qtdMinAtivLegado;
    }


    /**
     * Gets the qtdMaxAtivCatalogo value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return qtdMaxAtivCatalogo
     */
    public java.lang.Long getQtdMaxAtivCatalogo() {
        return qtdMaxAtivCatalogo;
    }


    /**
     * Sets the qtdMaxAtivCatalogo value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param qtdMaxAtivCatalogo
     */
    public void setQtdMaxAtivCatalogo(java.lang.Long qtdMaxAtivCatalogo) {
        this.qtdMaxAtivCatalogo = qtdMaxAtivCatalogo;
    }


    /**
     * Gets the qtdMaxAtivLegado value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return qtdMaxAtivLegado
     */
    public java.lang.Long getQtdMaxAtivLegado() {
        return qtdMaxAtivLegado;
    }


    /**
     * Sets the qtdMaxAtivLegado value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param qtdMaxAtivLegado
     */
    public void setQtdMaxAtivLegado(java.lang.Long qtdMaxAtivLegado) {
        this.qtdMaxAtivLegado = qtdMaxAtivLegado;
    }


    /**
     * Gets the dtAlteracao value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return dtAlteracao
     */
    public java.util.Calendar getDtAlteracao() {
        return dtAlteracao;
    }


    /**
     * Sets the dtAlteracao value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param dtAlteracao
     */
    public void setDtAlteracao(java.util.Calendar dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the inDisponibilidadeCatalogo value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return inDisponibilidadeCatalogo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrizacaoRetornoBuscarListaServicoParametrizacaoInCatalogo getInDisponibilidadeCatalogo() {
        return inDisponibilidadeCatalogo;
    }


    /**
     * Sets the inDisponibilidadeCatalogo value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param inDisponibilidadeCatalogo
     */
    public void setInDisponibilidadeCatalogo(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrizacaoRetornoBuscarListaServicoParametrizacaoInCatalogo inDisponibilidadeCatalogo) {
        this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
    }


    /**
     * Gets the inDisponibilidadeLegado value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return inDisponibilidadeLegado
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrizacaoRetornoBuscarListaServicoParametrizacaoInDisponibilidadeLegado getInDisponibilidadeLegado() {
        return inDisponibilidadeLegado;
    }


    /**
     * Sets the inDisponibilidadeLegado value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param inDisponibilidadeLegado
     */
    public void setInDisponibilidadeLegado(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrizacaoRetornoBuscarListaServicoParametrizacaoInDisponibilidadeLegado inDisponibilidadeLegado) {
        this.inDisponibilidadeLegado = inDisponibilidadeLegado;
    }


    /**
     * Gets the ativaWifi value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @return ativaWifi
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacaoAtivaWifi getAtivaWifi() {
        return ativaWifi;
    }


    /**
     * Sets the ativaWifi value for this ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.
     * 
     * @param ativaWifi
     */
    public void setAtivaWifi(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacaoAtivaWifi ativaWifi) {
        this.ativaWifi = ativaWifi;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao)) return false;
        ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao other = (ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idServico == other.getIdServico() &&
            ((this.cdServico==null && other.getCdServico()==null) || 
             (this.cdServico!=null &&
              this.cdServico.equals(other.getCdServico()))) &&
            ((this.nmComercial==null && other.getNmComercial()==null) || 
             (this.nmComercial!=null &&
              this.nmComercial.equals(other.getNmComercial()))) &&
            ((this.idCategoriaCatalogo==null && other.getIdCategoriaCatalogo()==null) || 
             (this.idCategoriaCatalogo!=null &&
              this.idCategoriaCatalogo.equals(other.getIdCategoriaCatalogo()))) &&
            ((this.nmCategoriaCatalogo==null && other.getNmCategoriaCatalogo()==null) || 
             (this.nmCategoriaCatalogo!=null &&
              this.nmCategoriaCatalogo.equals(other.getNmCategoriaCatalogo()))) &&
            ((this.idCategoriaLegado==null && other.getIdCategoriaLegado()==null) || 
             (this.idCategoriaLegado!=null &&
              this.idCategoriaLegado.equals(other.getIdCategoriaLegado()))) &&
            ((this.nmCategoriaLegado==null && other.getNmCategoriaLegado()==null) || 
             (this.nmCategoriaLegado!=null &&
              this.nmCategoriaLegado.equals(other.getNmCategoriaLegado()))) &&
            ((this.idTpServico==null && other.getIdTpServico()==null) || 
             (this.idTpServico!=null &&
              this.idTpServico.equals(other.getIdTpServico()))) &&
            ((this.dscTipoServico==null && other.getDscTipoServico()==null) || 
             (this.dscTipoServico!=null &&
              this.dscTipoServico.equals(other.getDscTipoServico()))) &&
            ((this.qtdMinAtivCatalogo==null && other.getQtdMinAtivCatalogo()==null) || 
             (this.qtdMinAtivCatalogo!=null &&
              this.qtdMinAtivCatalogo.equals(other.getQtdMinAtivCatalogo()))) &&
            ((this.qtdMinAtivLegado==null && other.getQtdMinAtivLegado()==null) || 
             (this.qtdMinAtivLegado!=null &&
              this.qtdMinAtivLegado.equals(other.getQtdMinAtivLegado()))) &&
            ((this.qtdMaxAtivCatalogo==null && other.getQtdMaxAtivCatalogo()==null) || 
             (this.qtdMaxAtivCatalogo!=null &&
              this.qtdMaxAtivCatalogo.equals(other.getQtdMaxAtivCatalogo()))) &&
            ((this.qtdMaxAtivLegado==null && other.getQtdMaxAtivLegado()==null) || 
             (this.qtdMaxAtivLegado!=null &&
              this.qtdMaxAtivLegado.equals(other.getQtdMaxAtivLegado()))) &&
            ((this.dtAlteracao==null && other.getDtAlteracao()==null) || 
             (this.dtAlteracao!=null &&
              this.dtAlteracao.equals(other.getDtAlteracao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            ((this.inDisponibilidadeCatalogo==null && other.getInDisponibilidadeCatalogo()==null) || 
             (this.inDisponibilidadeCatalogo!=null &&
              this.inDisponibilidadeCatalogo.equals(other.getInDisponibilidadeCatalogo()))) &&
            ((this.inDisponibilidadeLegado==null && other.getInDisponibilidadeLegado()==null) || 
             (this.inDisponibilidadeLegado!=null &&
              this.inDisponibilidadeLegado.equals(other.getInDisponibilidadeLegado()))) &&
            ((this.ativaWifi==null && other.getAtivaWifi()==null) || 
             (this.ativaWifi!=null &&
              this.ativaWifi.equals(other.getAtivaWifi())));
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
        if (getCdServico() != null) {
            _hashCode += getCdServico().hashCode();
        }
        if (getNmComercial() != null) {
            _hashCode += getNmComercial().hashCode();
        }
        if (getIdCategoriaCatalogo() != null) {
            _hashCode += getIdCategoriaCatalogo().hashCode();
        }
        if (getNmCategoriaCatalogo() != null) {
            _hashCode += getNmCategoriaCatalogo().hashCode();
        }
        if (getIdCategoriaLegado() != null) {
            _hashCode += getIdCategoriaLegado().hashCode();
        }
        if (getNmCategoriaLegado() != null) {
            _hashCode += getNmCategoriaLegado().hashCode();
        }
        if (getIdTpServico() != null) {
            _hashCode += getIdTpServico().hashCode();
        }
        if (getDscTipoServico() != null) {
            _hashCode += getDscTipoServico().hashCode();
        }
        if (getQtdMinAtivCatalogo() != null) {
            _hashCode += getQtdMinAtivCatalogo().hashCode();
        }
        if (getQtdMinAtivLegado() != null) {
            _hashCode += getQtdMinAtivLegado().hashCode();
        }
        if (getQtdMaxAtivCatalogo() != null) {
            _hashCode += getQtdMaxAtivCatalogo().hashCode();
        }
        if (getQtdMaxAtivLegado() != null) {
            _hashCode += getQtdMaxAtivLegado().hashCode();
        }
        if (getDtAlteracao() != null) {
            _hashCode += getDtAlteracao().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        if (getInDisponibilidadeCatalogo() != null) {
            _hashCode += getInDisponibilidadeCatalogo().hashCode();
        }
        if (getInDisponibilidadeLegado() != null) {
            _hashCode += getInDisponibilidadeLegado().hashCode();
        }
        if (getAtivaWifi() != null) {
            _hashCode += getAtivaWifi().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>ResultadoBuscarListaServicoParametrizacao>ListaBuscarListaServicoParametrizacao>RetornoBuscarListaServicoParametrizacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "cdServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmComercial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmComercial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCategoriaCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idCategoriaCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCategoriaCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmCategoriaCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCategoriaLegado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idCategoriaLegado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCategoriaLegado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmCategoriaLegado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTpServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idTpServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dscTipoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "dscTipoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMinAtivCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdMinAtivCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMinAtivLegado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdMinAtivLegado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMaxAtivCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdMaxAtivCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMaxAtivLegado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdMaxAtivLegado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "dtAlteracao"));
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
        elemField.setFieldName("inDisponibilidadeCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "inDisponibilidadeCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>>ResultadoBuscarListaServicoParametrizacao>ListaBuscarListaServicoParametrizacao>RetornoBuscarListaServicoParametrizacao>inDisponibilidadeCatalogo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponibilidadeLegado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "inDisponibilidadeLegado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>>ResultadoBuscarListaServicoParametrizacao>ListaBuscarListaServicoParametrizacao>RetornoBuscarListaServicoParametrizacao>inDisponibilidadeLegado"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ativaWifi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ativaWifi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>>ResultadoBuscarListaServicoParametrizacao>ListaBuscarListaServicoParametrizacao>RetornoBuscarListaServicoParametrizacao>ativaWifi"));
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
