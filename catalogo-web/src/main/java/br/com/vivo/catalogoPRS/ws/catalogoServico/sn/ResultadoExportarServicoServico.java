/**
 * ResultadoExportarServicoServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoExportarServicoServico  implements java.io.Serializable {
    private java.lang.String categoria;

    private java.lang.String cdTipoServico;

    private java.lang.String descTipoServico;

    private java.lang.String inDisponivel;

    private java.lang.String servico;

    private java.lang.String descricao;

    private java.lang.String cdCodigo;

    private java.lang.Float valor;

    private java.lang.String tpTarifa;

    private java.util.Calendar dtInicial;

    private java.util.Calendar dtFinal;

    private java.lang.Long cdAnatel;

    private java.lang.String categoriaCatalogo;

    private java.lang.Long idTpServicoCatalogo;

    private java.math.BigDecimal qtdMinNativLegado;

    private java.math.BigDecimal qtdMinNativCatalogo;

    private java.math.BigDecimal qtdMaxNativCatalogo;

    private java.math.BigDecimal qtdMaxNativLegado;

    private java.lang.String isServicoMestre;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa[] listaServicoTarifa;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaAtributoAtributo[] listaAtributo;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaPlanosPlano[] listaPlanos;

    public ResultadoExportarServicoServico() {
    }

    public ResultadoExportarServicoServico(
           java.lang.String categoria,
           java.lang.String cdTipoServico,
           java.lang.String descTipoServico,
           java.lang.String inDisponivel,
           java.lang.String servico,
           java.lang.String descricao,
           java.lang.String cdCodigo,
           java.lang.Float valor,
           java.lang.String tpTarifa,
           java.util.Calendar dtInicial,
           java.util.Calendar dtFinal,
           java.lang.Long cdAnatel,
           java.lang.String categoriaCatalogo,
           java.lang.Long idTpServicoCatalogo,
           java.math.BigDecimal qtdMinNativLegado,
           java.math.BigDecimal qtdMinNativCatalogo,
           java.math.BigDecimal qtdMaxNativCatalogo,
           java.math.BigDecimal qtdMaxNativLegado,
           java.lang.String isServicoMestre,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa[] listaServicoTarifa,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaAtributoAtributo[] listaAtributo,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaPlanosPlano[] listaPlanos) {
           this.categoria = categoria;
           this.cdTipoServico = cdTipoServico;
           this.descTipoServico = descTipoServico;
           this.inDisponivel = inDisponivel;
           this.servico = servico;
           this.descricao = descricao;
           this.cdCodigo = cdCodigo;
           this.valor = valor;
           this.tpTarifa = tpTarifa;
           this.dtInicial = dtInicial;
           this.dtFinal = dtFinal;
           this.cdAnatel = cdAnatel;
           this.categoriaCatalogo = categoriaCatalogo;
           this.idTpServicoCatalogo = idTpServicoCatalogo;
           this.qtdMinNativLegado = qtdMinNativLegado;
           this.qtdMinNativCatalogo = qtdMinNativCatalogo;
           this.qtdMaxNativCatalogo = qtdMaxNativCatalogo;
           this.qtdMaxNativLegado = qtdMaxNativLegado;
           this.isServicoMestre = isServicoMestre;
           this.listaServicoTarifa = listaServicoTarifa;
           this.listaAtributo = listaAtributo;
           this.listaPlanos = listaPlanos;
    }


    /**
     * Gets the categoria value for this ResultadoExportarServicoServico.
     * 
     * @return categoria
     */
    public java.lang.String getCategoria() {
        return categoria;
    }


    /**
     * Sets the categoria value for this ResultadoExportarServicoServico.
     * 
     * @param categoria
     */
    public void setCategoria(java.lang.String categoria) {
        this.categoria = categoria;
    }


    /**
     * Gets the cdTipoServico value for this ResultadoExportarServicoServico.
     * 
     * @return cdTipoServico
     */
    public java.lang.String getCdTipoServico() {
        return cdTipoServico;
    }


    /**
     * Sets the cdTipoServico value for this ResultadoExportarServicoServico.
     * 
     * @param cdTipoServico
     */
    public void setCdTipoServico(java.lang.String cdTipoServico) {
        this.cdTipoServico = cdTipoServico;
    }


    /**
     * Gets the descTipoServico value for this ResultadoExportarServicoServico.
     * 
     * @return descTipoServico
     */
    public java.lang.String getDescTipoServico() {
        return descTipoServico;
    }


    /**
     * Sets the descTipoServico value for this ResultadoExportarServicoServico.
     * 
     * @param descTipoServico
     */
    public void setDescTipoServico(java.lang.String descTipoServico) {
        this.descTipoServico = descTipoServico;
    }


    /**
     * Gets the inDisponivel value for this ResultadoExportarServicoServico.
     * 
     * @return inDisponivel
     */
    public java.lang.String getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ResultadoExportarServicoServico.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(java.lang.String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the servico value for this ResultadoExportarServicoServico.
     * 
     * @return servico
     */
    public java.lang.String getServico() {
        return servico;
    }


    /**
     * Sets the servico value for this ResultadoExportarServicoServico.
     * 
     * @param servico
     */
    public void setServico(java.lang.String servico) {
        this.servico = servico;
    }


    /**
     * Gets the descricao value for this ResultadoExportarServicoServico.
     * 
     * @return descricao
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this ResultadoExportarServicoServico.
     * 
     * @param descricao
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }


    /**
     * Gets the cdCodigo value for this ResultadoExportarServicoServico.
     * 
     * @return cdCodigo
     */
    public java.lang.String getCdCodigo() {
        return cdCodigo;
    }


    /**
     * Sets the cdCodigo value for this ResultadoExportarServicoServico.
     * 
     * @param cdCodigo
     */
    public void setCdCodigo(java.lang.String cdCodigo) {
        this.cdCodigo = cdCodigo;
    }


    /**
     * Gets the valor value for this ResultadoExportarServicoServico.
     * 
     * @return valor
     */
    public java.lang.Float getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this ResultadoExportarServicoServico.
     * 
     * @param valor
     */
    public void setValor(java.lang.Float valor) {
        this.valor = valor;
    }


    /**
     * Gets the tpTarifa value for this ResultadoExportarServicoServico.
     * 
     * @return tpTarifa
     */
    public java.lang.String getTpTarifa() {
        return tpTarifa;
    }


    /**
     * Sets the tpTarifa value for this ResultadoExportarServicoServico.
     * 
     * @param tpTarifa
     */
    public void setTpTarifa(java.lang.String tpTarifa) {
        this.tpTarifa = tpTarifa;
    }


    /**
     * Gets the dtInicial value for this ResultadoExportarServicoServico.
     * 
     * @return dtInicial
     */
    public java.util.Calendar getDtInicial() {
        return dtInicial;
    }


    /**
     * Sets the dtInicial value for this ResultadoExportarServicoServico.
     * 
     * @param dtInicial
     */
    public void setDtInicial(java.util.Calendar dtInicial) {
        this.dtInicial = dtInicial;
    }


    /**
     * Gets the dtFinal value for this ResultadoExportarServicoServico.
     * 
     * @return dtFinal
     */
    public java.util.Calendar getDtFinal() {
        return dtFinal;
    }


    /**
     * Sets the dtFinal value for this ResultadoExportarServicoServico.
     * 
     * @param dtFinal
     */
    public void setDtFinal(java.util.Calendar dtFinal) {
        this.dtFinal = dtFinal;
    }


    /**
     * Gets the cdAnatel value for this ResultadoExportarServicoServico.
     * 
     * @return cdAnatel
     */
    public java.lang.Long getCdAnatel() {
        return cdAnatel;
    }


    /**
     * Sets the cdAnatel value for this ResultadoExportarServicoServico.
     * 
     * @param cdAnatel
     */
    public void setCdAnatel(java.lang.Long cdAnatel) {
        this.cdAnatel = cdAnatel;
    }


    /**
     * Gets the categoriaCatalogo value for this ResultadoExportarServicoServico.
     * 
     * @return categoriaCatalogo
     */
    public java.lang.String getCategoriaCatalogo() {
        return categoriaCatalogo;
    }


    /**
     * Sets the categoriaCatalogo value for this ResultadoExportarServicoServico.
     * 
     * @param categoriaCatalogo
     */
    public void setCategoriaCatalogo(java.lang.String categoriaCatalogo) {
        this.categoriaCatalogo = categoriaCatalogo;
    }


    /**
     * Gets the idTpServicoCatalogo value for this ResultadoExportarServicoServico.
     * 
     * @return idTpServicoCatalogo
     */
    public java.lang.Long getIdTpServicoCatalogo() {
        return idTpServicoCatalogo;
    }


    /**
     * Sets the idTpServicoCatalogo value for this ResultadoExportarServicoServico.
     * 
     * @param idTpServicoCatalogo
     */
    public void setIdTpServicoCatalogo(java.lang.Long idTpServicoCatalogo) {
        this.idTpServicoCatalogo = idTpServicoCatalogo;
    }


    /**
     * Gets the qtdMinNativLegado value for this ResultadoExportarServicoServico.
     * 
     * @return qtdMinNativLegado
     */
    public java.math.BigDecimal getQtdMinNativLegado() {
        return qtdMinNativLegado;
    }


    /**
     * Sets the qtdMinNativLegado value for this ResultadoExportarServicoServico.
     * 
     * @param qtdMinNativLegado
     */
    public void setQtdMinNativLegado(java.math.BigDecimal qtdMinNativLegado) {
        this.qtdMinNativLegado = qtdMinNativLegado;
    }


    /**
     * Gets the qtdMinNativCatalogo value for this ResultadoExportarServicoServico.
     * 
     * @return qtdMinNativCatalogo
     */
    public java.math.BigDecimal getQtdMinNativCatalogo() {
        return qtdMinNativCatalogo;
    }


    /**
     * Sets the qtdMinNativCatalogo value for this ResultadoExportarServicoServico.
     * 
     * @param qtdMinNativCatalogo
     */
    public void setQtdMinNativCatalogo(java.math.BigDecimal qtdMinNativCatalogo) {
        this.qtdMinNativCatalogo = qtdMinNativCatalogo;
    }


    /**
     * Gets the qtdMaxNativCatalogo value for this ResultadoExportarServicoServico.
     * 
     * @return qtdMaxNativCatalogo
     */
    public java.math.BigDecimal getQtdMaxNativCatalogo() {
        return qtdMaxNativCatalogo;
    }


    /**
     * Sets the qtdMaxNativCatalogo value for this ResultadoExportarServicoServico.
     * 
     * @param qtdMaxNativCatalogo
     */
    public void setQtdMaxNativCatalogo(java.math.BigDecimal qtdMaxNativCatalogo) {
        this.qtdMaxNativCatalogo = qtdMaxNativCatalogo;
    }


    /**
     * Gets the qtdMaxNativLegado value for this ResultadoExportarServicoServico.
     * 
     * @return qtdMaxNativLegado
     */
    public java.math.BigDecimal getQtdMaxNativLegado() {
        return qtdMaxNativLegado;
    }


    /**
     * Sets the qtdMaxNativLegado value for this ResultadoExportarServicoServico.
     * 
     * @param qtdMaxNativLegado
     */
    public void setQtdMaxNativLegado(java.math.BigDecimal qtdMaxNativLegado) {
        this.qtdMaxNativLegado = qtdMaxNativLegado;
    }


    /**
     * Gets the isServicoMestre value for this ResultadoExportarServicoServico.
     * 
     * @return isServicoMestre
     */
    public java.lang.String getIsServicoMestre() {
        return isServicoMestre;
    }


    /**
     * Sets the isServicoMestre value for this ResultadoExportarServicoServico.
     * 
     * @param isServicoMestre
     */
    public void setIsServicoMestre(java.lang.String isServicoMestre) {
        this.isServicoMestre = isServicoMestre;
    }


    /**
     * Gets the listaServicoTarifa value for this ResultadoExportarServicoServico.
     * 
     * @return listaServicoTarifa
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa[] getListaServicoTarifa() {
        return listaServicoTarifa;
    }


    /**
     * Sets the listaServicoTarifa value for this ResultadoExportarServicoServico.
     * 
     * @param listaServicoTarifa
     */
    public void setListaServicoTarifa(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa[] listaServicoTarifa) {
        this.listaServicoTarifa = listaServicoTarifa;
    }


    /**
     * Gets the listaAtributo value for this ResultadoExportarServicoServico.
     * 
     * @return listaAtributo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaAtributoAtributo[] getListaAtributo() {
        return listaAtributo;
    }


    /**
     * Sets the listaAtributo value for this ResultadoExportarServicoServico.
     * 
     * @param listaAtributo
     */
    public void setListaAtributo(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaAtributoAtributo[] listaAtributo) {
        this.listaAtributo = listaAtributo;
    }


    /**
     * Gets the listaPlanos value for this ResultadoExportarServicoServico.
     * 
     * @return listaPlanos
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaPlanosPlano[] getListaPlanos() {
        return listaPlanos;
    }


    /**
     * Sets the listaPlanos value for this ResultadoExportarServicoServico.
     * 
     * @param listaPlanos
     */
    public void setListaPlanos(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaPlanosPlano[] listaPlanos) {
        this.listaPlanos = listaPlanos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoExportarServicoServico)) return false;
        ResultadoExportarServicoServico other = (ResultadoExportarServicoServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.categoria==null && other.getCategoria()==null) || 
             (this.categoria!=null &&
              this.categoria.equals(other.getCategoria()))) &&
            ((this.cdTipoServico==null && other.getCdTipoServico()==null) || 
             (this.cdTipoServico!=null &&
              this.cdTipoServico.equals(other.getCdTipoServico()))) &&
            ((this.descTipoServico==null && other.getDescTipoServico()==null) || 
             (this.descTipoServico!=null &&
              this.descTipoServico.equals(other.getDescTipoServico()))) &&
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel()))) &&
            ((this.servico==null && other.getServico()==null) || 
             (this.servico!=null &&
              this.servico.equals(other.getServico()))) &&
            ((this.descricao==null && other.getDescricao()==null) || 
             (this.descricao!=null &&
              this.descricao.equals(other.getDescricao()))) &&
            ((this.cdCodigo==null && other.getCdCodigo()==null) || 
             (this.cdCodigo!=null &&
              this.cdCodigo.equals(other.getCdCodigo()))) &&
            ((this.valor==null && other.getValor()==null) || 
             (this.valor!=null &&
              this.valor.equals(other.getValor()))) &&
            ((this.tpTarifa==null && other.getTpTarifa()==null) || 
             (this.tpTarifa!=null &&
              this.tpTarifa.equals(other.getTpTarifa()))) &&
            ((this.dtInicial==null && other.getDtInicial()==null) || 
             (this.dtInicial!=null &&
              this.dtInicial.equals(other.getDtInicial()))) &&
            ((this.dtFinal==null && other.getDtFinal()==null) || 
             (this.dtFinal!=null &&
              this.dtFinal.equals(other.getDtFinal()))) &&
            ((this.cdAnatel==null && other.getCdAnatel()==null) || 
             (this.cdAnatel!=null &&
              this.cdAnatel.equals(other.getCdAnatel()))) &&
            ((this.categoriaCatalogo==null && other.getCategoriaCatalogo()==null) || 
             (this.categoriaCatalogo!=null &&
              this.categoriaCatalogo.equals(other.getCategoriaCatalogo()))) &&
            ((this.idTpServicoCatalogo==null && other.getIdTpServicoCatalogo()==null) || 
             (this.idTpServicoCatalogo!=null &&
              this.idTpServicoCatalogo.equals(other.getIdTpServicoCatalogo()))) &&
            ((this.qtdMinNativLegado==null && other.getQtdMinNativLegado()==null) || 
             (this.qtdMinNativLegado!=null &&
              this.qtdMinNativLegado.equals(other.getQtdMinNativLegado()))) &&
            ((this.qtdMinNativCatalogo==null && other.getQtdMinNativCatalogo()==null) || 
             (this.qtdMinNativCatalogo!=null &&
              this.qtdMinNativCatalogo.equals(other.getQtdMinNativCatalogo()))) &&
            ((this.qtdMaxNativCatalogo==null && other.getQtdMaxNativCatalogo()==null) || 
             (this.qtdMaxNativCatalogo!=null &&
              this.qtdMaxNativCatalogo.equals(other.getQtdMaxNativCatalogo()))) &&
            ((this.qtdMaxNativLegado==null && other.getQtdMaxNativLegado()==null) || 
             (this.qtdMaxNativLegado!=null &&
              this.qtdMaxNativLegado.equals(other.getQtdMaxNativLegado()))) &&
            ((this.isServicoMestre==null && other.getIsServicoMestre()==null) || 
             (this.isServicoMestre!=null &&
              this.isServicoMestre.equals(other.getIsServicoMestre()))) &&
            ((this.listaServicoTarifa==null && other.getListaServicoTarifa()==null) || 
             (this.listaServicoTarifa!=null &&
              java.util.Arrays.equals(this.listaServicoTarifa, other.getListaServicoTarifa()))) &&
            ((this.listaAtributo==null && other.getListaAtributo()==null) || 
             (this.listaAtributo!=null &&
              java.util.Arrays.equals(this.listaAtributo, other.getListaAtributo()))) &&
            ((this.listaPlanos==null && other.getListaPlanos()==null) || 
             (this.listaPlanos!=null &&
              java.util.Arrays.equals(this.listaPlanos, other.getListaPlanos())));
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
        if (getCategoria() != null) {
            _hashCode += getCategoria().hashCode();
        }
        if (getCdTipoServico() != null) {
            _hashCode += getCdTipoServico().hashCode();
        }
        if (getDescTipoServico() != null) {
            _hashCode += getDescTipoServico().hashCode();
        }
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
        }
        if (getServico() != null) {
            _hashCode += getServico().hashCode();
        }
        if (getDescricao() != null) {
            _hashCode += getDescricao().hashCode();
        }
        if (getCdCodigo() != null) {
            _hashCode += getCdCodigo().hashCode();
        }
        if (getValor() != null) {
            _hashCode += getValor().hashCode();
        }
        if (getTpTarifa() != null) {
            _hashCode += getTpTarifa().hashCode();
        }
        if (getDtInicial() != null) {
            _hashCode += getDtInicial().hashCode();
        }
        if (getDtFinal() != null) {
            _hashCode += getDtFinal().hashCode();
        }
        if (getCdAnatel() != null) {
            _hashCode += getCdAnatel().hashCode();
        }
        if (getCategoriaCatalogo() != null) {
            _hashCode += getCategoriaCatalogo().hashCode();
        }
        if (getIdTpServicoCatalogo() != null) {
            _hashCode += getIdTpServicoCatalogo().hashCode();
        }
        if (getQtdMinNativLegado() != null) {
            _hashCode += getQtdMinNativLegado().hashCode();
        }
        if (getQtdMinNativCatalogo() != null) {
            _hashCode += getQtdMinNativCatalogo().hashCode();
        }
        if (getQtdMaxNativCatalogo() != null) {
            _hashCode += getQtdMaxNativCatalogo().hashCode();
        }
        if (getQtdMaxNativLegado() != null) {
            _hashCode += getQtdMaxNativLegado().hashCode();
        }
        if (getIsServicoMestre() != null) {
            _hashCode += getIsServicoMestre().hashCode();
        }
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
        if (getListaAtributo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAtributo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAtributo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaPlanos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaPlanos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaPlanos(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoExportarServicoServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>ResultadoExportarServico>Servico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "categoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdTipoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "cdTipoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descTipoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "descTipoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "inDisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "servico"));
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
        elemField.setFieldName("cdCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "cdCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "valor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
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
        elemField.setFieldName("dtInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "dtInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("cdAnatel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "cdAnatel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoriaCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "CategoriaCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTpServicoCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idTpServicoCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMinNativLegado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdMinNativLegado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMinNativCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdMinNativCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMaxNativCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdMaxNativCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMaxNativLegado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdMaxNativLegado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isServicoMestre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "isServicoMestre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaServicoTarifa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaServicoTarifa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ListaServicoTarifa"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAtributo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaAtributo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>>ResultadoExportarServico>Servico>ListaAtributo>Atributo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "Atributo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaPlanos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaPlanos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>>ResultadoExportarServico>Servico>ListaPlanos>Plano"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "Plano"));
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
