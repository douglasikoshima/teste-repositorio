/**
 * ListaProdutoProduto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ListaProdutoProduto  implements java.io.Serializable {
    private long idProduto;

    private java.lang.Long idFabricante;

    private java.lang.String nmUsuariCriacao;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioAlteracao;

    private java.lang.Long idGama;

    private java.util.Calendar dtUltimaAlteracao;

    private long idTipoProduto;

    private java.lang.Long idTecnologia;

    private java.util.Calendar dtInicial;

    private java.util.Calendar dtFinal;

    private java.lang.String indisponivel;

    private java.lang.String dsProduto;

    private java.lang.String dsSap;

    private java.lang.String sgClasseAvaliacao;

    private java.lang.String nmProduto;

    private java.lang.String sgGrupoContabil;

    private java.lang.String sgSetorAtividade;

    private long idProdutoModelo;

    private java.lang.String nmModelo;

    private long idModelo;

    private java.lang.String inFimVida;

    private java.lang.String nmTipoProduto;

    private java.lang.String nmTecnologia;

    private java.lang.String nmFabricante;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaSistemaProdutoSistemaProduto[] listaSistemaProduto;

    public ListaProdutoProduto() {
    }

    public ListaProdutoProduto(
           long idProduto,
           java.lang.Long idFabricante,
           java.lang.String nmUsuariCriacao,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioAlteracao,
           java.lang.Long idGama,
           java.util.Calendar dtUltimaAlteracao,
           long idTipoProduto,
           java.lang.Long idTecnologia,
           java.util.Calendar dtInicial,
           java.util.Calendar dtFinal,
           java.lang.String indisponivel,
           java.lang.String dsProduto,
           java.lang.String dsSap,
           java.lang.String sgClasseAvaliacao,
           java.lang.String nmProduto,
           java.lang.String sgGrupoContabil,
           java.lang.String sgSetorAtividade,
           long idProdutoModelo,
           java.lang.String nmModelo,
           long idModelo,
           java.lang.String inFimVida,
           java.lang.String nmTipoProduto,
           java.lang.String nmTecnologia,
           java.lang.String nmFabricante,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaSistemaProdutoSistemaProduto[] listaSistemaProduto) {
           this.idProduto = idProduto;
           this.idFabricante = idFabricante;
           this.nmUsuariCriacao = nmUsuariCriacao;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.idGama = idGama;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.idTipoProduto = idTipoProduto;
           this.idTecnologia = idTecnologia;
           this.dtInicial = dtInicial;
           this.dtFinal = dtFinal;
           this.indisponivel = indisponivel;
           this.dsProduto = dsProduto;
           this.dsSap = dsSap;
           this.sgClasseAvaliacao = sgClasseAvaliacao;
           this.nmProduto = nmProduto;
           this.sgGrupoContabil = sgGrupoContabil;
           this.sgSetorAtividade = sgSetorAtividade;
           this.idProdutoModelo = idProdutoModelo;
           this.nmModelo = nmModelo;
           this.idModelo = idModelo;
           this.inFimVida = inFimVida;
           this.nmTipoProduto = nmTipoProduto;
           this.nmTecnologia = nmTecnologia;
           this.nmFabricante = nmFabricante;
           this.listaSistemaProduto = listaSistemaProduto;
    }


    /**
     * Gets the idProduto value for this ListaProdutoProduto.
     * 
     * @return idProduto
     */
    public long getIdProduto() {
        return idProduto;
    }


    /**
     * Sets the idProduto value for this ListaProdutoProduto.
     * 
     * @param idProduto
     */
    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }


    /**
     * Gets the idFabricante value for this ListaProdutoProduto.
     * 
     * @return idFabricante
     */
    public java.lang.Long getIdFabricante() {
        return idFabricante;
    }


    /**
     * Sets the idFabricante value for this ListaProdutoProduto.
     * 
     * @param idFabricante
     */
    public void setIdFabricante(java.lang.Long idFabricante) {
        this.idFabricante = idFabricante;
    }


    /**
     * Gets the nmUsuariCriacao value for this ListaProdutoProduto.
     * 
     * @return nmUsuariCriacao
     */
    public java.lang.String getNmUsuariCriacao() {
        return nmUsuariCriacao;
    }


    /**
     * Sets the nmUsuariCriacao value for this ListaProdutoProduto.
     * 
     * @param nmUsuariCriacao
     */
    public void setNmUsuariCriacao(java.lang.String nmUsuariCriacao) {
        this.nmUsuariCriacao = nmUsuariCriacao;
    }


    /**
     * Gets the dtCriacao value for this ListaProdutoProduto.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ListaProdutoProduto.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ListaProdutoProduto.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ListaProdutoProduto.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the idGama value for this ListaProdutoProduto.
     * 
     * @return idGama
     */
    public java.lang.Long getIdGama() {
        return idGama;
    }


    /**
     * Sets the idGama value for this ListaProdutoProduto.
     * 
     * @param idGama
     */
    public void setIdGama(java.lang.Long idGama) {
        this.idGama = idGama;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ListaProdutoProduto.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ListaProdutoProduto.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the idTipoProduto value for this ListaProdutoProduto.
     * 
     * @return idTipoProduto
     */
    public long getIdTipoProduto() {
        return idTipoProduto;
    }


    /**
     * Sets the idTipoProduto value for this ListaProdutoProduto.
     * 
     * @param idTipoProduto
     */
    public void setIdTipoProduto(long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }


    /**
     * Gets the idTecnologia value for this ListaProdutoProduto.
     * 
     * @return idTecnologia
     */
    public java.lang.Long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ListaProdutoProduto.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(java.lang.Long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the dtInicial value for this ListaProdutoProduto.
     * 
     * @return dtInicial
     */
    public java.util.Calendar getDtInicial() {
        return dtInicial;
    }


    /**
     * Sets the dtInicial value for this ListaProdutoProduto.
     * 
     * @param dtInicial
     */
    public void setDtInicial(java.util.Calendar dtInicial) {
        this.dtInicial = dtInicial;
    }


    /**
     * Gets the dtFinal value for this ListaProdutoProduto.
     * 
     * @return dtFinal
     */
    public java.util.Calendar getDtFinal() {
        return dtFinal;
    }


    /**
     * Sets the dtFinal value for this ListaProdutoProduto.
     * 
     * @param dtFinal
     */
    public void setDtFinal(java.util.Calendar dtFinal) {
        this.dtFinal = dtFinal;
    }


    /**
     * Gets the indisponivel value for this ListaProdutoProduto.
     * 
     * @return indisponivel
     */
    public java.lang.String getIndisponivel() {
        return indisponivel;
    }


    /**
     * Sets the indisponivel value for this ListaProdutoProduto.
     * 
     * @param indisponivel
     */
    public void setIndisponivel(java.lang.String indisponivel) {
        this.indisponivel = indisponivel;
    }


    /**
     * Gets the dsProduto value for this ListaProdutoProduto.
     * 
     * @return dsProduto
     */
    public java.lang.String getDsProduto() {
        return dsProduto;
    }


    /**
     * Sets the dsProduto value for this ListaProdutoProduto.
     * 
     * @param dsProduto
     */
    public void setDsProduto(java.lang.String dsProduto) {
        this.dsProduto = dsProduto;
    }


    /**
     * Gets the dsSap value for this ListaProdutoProduto.
     * 
     * @return dsSap
     */
    public java.lang.String getDsSap() {
        return dsSap;
    }


    /**
     * Sets the dsSap value for this ListaProdutoProduto.
     * 
     * @param dsSap
     */
    public void setDsSap(java.lang.String dsSap) {
        this.dsSap = dsSap;
    }


    /**
     * Gets the sgClasseAvaliacao value for this ListaProdutoProduto.
     * 
     * @return sgClasseAvaliacao
     */
    public java.lang.String getSgClasseAvaliacao() {
        return sgClasseAvaliacao;
    }


    /**
     * Sets the sgClasseAvaliacao value for this ListaProdutoProduto.
     * 
     * @param sgClasseAvaliacao
     */
    public void setSgClasseAvaliacao(java.lang.String sgClasseAvaliacao) {
        this.sgClasseAvaliacao = sgClasseAvaliacao;
    }


    /**
     * Gets the nmProduto value for this ListaProdutoProduto.
     * 
     * @return nmProduto
     */
    public java.lang.String getNmProduto() {
        return nmProduto;
    }


    /**
     * Sets the nmProduto value for this ListaProdutoProduto.
     * 
     * @param nmProduto
     */
    public void setNmProduto(java.lang.String nmProduto) {
        this.nmProduto = nmProduto;
    }


    /**
     * Gets the sgGrupoContabil value for this ListaProdutoProduto.
     * 
     * @return sgGrupoContabil
     */
    public java.lang.String getSgGrupoContabil() {
        return sgGrupoContabil;
    }


    /**
     * Sets the sgGrupoContabil value for this ListaProdutoProduto.
     * 
     * @param sgGrupoContabil
     */
    public void setSgGrupoContabil(java.lang.String sgGrupoContabil) {
        this.sgGrupoContabil = sgGrupoContabil;
    }


    /**
     * Gets the sgSetorAtividade value for this ListaProdutoProduto.
     * 
     * @return sgSetorAtividade
     */
    public java.lang.String getSgSetorAtividade() {
        return sgSetorAtividade;
    }


    /**
     * Sets the sgSetorAtividade value for this ListaProdutoProduto.
     * 
     * @param sgSetorAtividade
     */
    public void setSgSetorAtividade(java.lang.String sgSetorAtividade) {
        this.sgSetorAtividade = sgSetorAtividade;
    }


    /**
     * Gets the idProdutoModelo value for this ListaProdutoProduto.
     * 
     * @return idProdutoModelo
     */
    public long getIdProdutoModelo() {
        return idProdutoModelo;
    }


    /**
     * Sets the idProdutoModelo value for this ListaProdutoProduto.
     * 
     * @param idProdutoModelo
     */
    public void setIdProdutoModelo(long idProdutoModelo) {
        this.idProdutoModelo = idProdutoModelo;
    }


    /**
     * Gets the nmModelo value for this ListaProdutoProduto.
     * 
     * @return nmModelo
     */
    public java.lang.String getNmModelo() {
        return nmModelo;
    }


    /**
     * Sets the nmModelo value for this ListaProdutoProduto.
     * 
     * @param nmModelo
     */
    public void setNmModelo(java.lang.String nmModelo) {
        this.nmModelo = nmModelo;
    }


    /**
     * Gets the idModelo value for this ListaProdutoProduto.
     * 
     * @return idModelo
     */
    public long getIdModelo() {
        return idModelo;
    }


    /**
     * Sets the idModelo value for this ListaProdutoProduto.
     * 
     * @param idModelo
     */
    public void setIdModelo(long idModelo) {
        this.idModelo = idModelo;
    }


    /**
     * Gets the inFimVida value for this ListaProdutoProduto.
     * 
     * @return inFimVida
     */
    public java.lang.String getInFimVida() {
        return inFimVida;
    }


    /**
     * Sets the inFimVida value for this ListaProdutoProduto.
     * 
     * @param inFimVida
     */
    public void setInFimVida(java.lang.String inFimVida) {
        this.inFimVida = inFimVida;
    }


    /**
     * Gets the nmTipoProduto value for this ListaProdutoProduto.
     * 
     * @return nmTipoProduto
     */
    public java.lang.String getNmTipoProduto() {
        return nmTipoProduto;
    }


    /**
     * Sets the nmTipoProduto value for this ListaProdutoProduto.
     * 
     * @param nmTipoProduto
     */
    public void setNmTipoProduto(java.lang.String nmTipoProduto) {
        this.nmTipoProduto = nmTipoProduto;
    }


    /**
     * Gets the nmTecnologia value for this ListaProdutoProduto.
     * 
     * @return nmTecnologia
     */
    public java.lang.String getNmTecnologia() {
        return nmTecnologia;
    }


    /**
     * Sets the nmTecnologia value for this ListaProdutoProduto.
     * 
     * @param nmTecnologia
     */
    public void setNmTecnologia(java.lang.String nmTecnologia) {
        this.nmTecnologia = nmTecnologia;
    }


    /**
     * Gets the nmFabricante value for this ListaProdutoProduto.
     * 
     * @return nmFabricante
     */
    public java.lang.String getNmFabricante() {
        return nmFabricante;
    }


    /**
     * Sets the nmFabricante value for this ListaProdutoProduto.
     * 
     * @param nmFabricante
     */
    public void setNmFabricante(java.lang.String nmFabricante) {
        this.nmFabricante = nmFabricante;
    }


    /**
     * Gets the listaSistemaProduto value for this ListaProdutoProduto.
     * 
     * @return listaSistemaProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaSistemaProdutoSistemaProduto[] getListaSistemaProduto() {
        return listaSistemaProduto;
    }


    /**
     * Sets the listaSistemaProduto value for this ListaProdutoProduto.
     * 
     * @param listaSistemaProduto
     */
    public void setListaSistemaProduto(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaSistemaProdutoSistemaProduto[] listaSistemaProduto) {
        this.listaSistemaProduto = listaSistemaProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaProdutoProduto)) return false;
        ListaProdutoProduto other = (ListaProdutoProduto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idProduto == other.getIdProduto() &&
            ((this.idFabricante==null && other.getIdFabricante()==null) || 
             (this.idFabricante!=null &&
              this.idFabricante.equals(other.getIdFabricante()))) &&
            ((this.nmUsuariCriacao==null && other.getNmUsuariCriacao()==null) || 
             (this.nmUsuariCriacao!=null &&
              this.nmUsuariCriacao.equals(other.getNmUsuariCriacao()))) &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            ((this.idGama==null && other.getIdGama()==null) || 
             (this.idGama!=null &&
              this.idGama.equals(other.getIdGama()))) &&
            ((this.dtUltimaAlteracao==null && other.getDtUltimaAlteracao()==null) || 
             (this.dtUltimaAlteracao!=null &&
              this.dtUltimaAlteracao.equals(other.getDtUltimaAlteracao()))) &&
            this.idTipoProduto == other.getIdTipoProduto() &&
            ((this.idTecnologia==null && other.getIdTecnologia()==null) || 
             (this.idTecnologia!=null &&
              this.idTecnologia.equals(other.getIdTecnologia()))) &&
            ((this.dtInicial==null && other.getDtInicial()==null) || 
             (this.dtInicial!=null &&
              this.dtInicial.equals(other.getDtInicial()))) &&
            ((this.dtFinal==null && other.getDtFinal()==null) || 
             (this.dtFinal!=null &&
              this.dtFinal.equals(other.getDtFinal()))) &&
            ((this.indisponivel==null && other.getIndisponivel()==null) || 
             (this.indisponivel!=null &&
              this.indisponivel.equals(other.getIndisponivel()))) &&
            ((this.dsProduto==null && other.getDsProduto()==null) || 
             (this.dsProduto!=null &&
              this.dsProduto.equals(other.getDsProduto()))) &&
            ((this.dsSap==null && other.getDsSap()==null) || 
             (this.dsSap!=null &&
              this.dsSap.equals(other.getDsSap()))) &&
            ((this.sgClasseAvaliacao==null && other.getSgClasseAvaliacao()==null) || 
             (this.sgClasseAvaliacao!=null &&
              this.sgClasseAvaliacao.equals(other.getSgClasseAvaliacao()))) &&
            ((this.nmProduto==null && other.getNmProduto()==null) || 
             (this.nmProduto!=null &&
              this.nmProduto.equals(other.getNmProduto()))) &&
            ((this.sgGrupoContabil==null && other.getSgGrupoContabil()==null) || 
             (this.sgGrupoContabil!=null &&
              this.sgGrupoContabil.equals(other.getSgGrupoContabil()))) &&
            ((this.sgSetorAtividade==null && other.getSgSetorAtividade()==null) || 
             (this.sgSetorAtividade!=null &&
              this.sgSetorAtividade.equals(other.getSgSetorAtividade()))) &&
            this.idProdutoModelo == other.getIdProdutoModelo() &&
            ((this.nmModelo==null && other.getNmModelo()==null) || 
             (this.nmModelo!=null &&
              this.nmModelo.equals(other.getNmModelo()))) &&
            this.idModelo == other.getIdModelo() &&
            ((this.inFimVida==null && other.getInFimVida()==null) || 
             (this.inFimVida!=null &&
              this.inFimVida.equals(other.getInFimVida()))) &&
            ((this.nmTipoProduto==null && other.getNmTipoProduto()==null) || 
             (this.nmTipoProduto!=null &&
              this.nmTipoProduto.equals(other.getNmTipoProduto()))) &&
            ((this.nmTecnologia==null && other.getNmTecnologia()==null) || 
             (this.nmTecnologia!=null &&
              this.nmTecnologia.equals(other.getNmTecnologia()))) &&
            ((this.nmFabricante==null && other.getNmFabricante()==null) || 
             (this.nmFabricante!=null &&
              this.nmFabricante.equals(other.getNmFabricante()))) &&
            ((this.listaSistemaProduto==null && other.getListaSistemaProduto()==null) || 
             (this.listaSistemaProduto!=null &&
              java.util.Arrays.equals(this.listaSistemaProduto, other.getListaSistemaProduto())));
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
        _hashCode += new Long(getIdProduto()).hashCode();
        if (getIdFabricante() != null) {
            _hashCode += getIdFabricante().hashCode();
        }
        if (getNmUsuariCriacao() != null) {
            _hashCode += getNmUsuariCriacao().hashCode();
        }
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        if (getIdGama() != null) {
            _hashCode += getIdGama().hashCode();
        }
        if (getDtUltimaAlteracao() != null) {
            _hashCode += getDtUltimaAlteracao().hashCode();
        }
        _hashCode += new Long(getIdTipoProduto()).hashCode();
        if (getIdTecnologia() != null) {
            _hashCode += getIdTecnologia().hashCode();
        }
        if (getDtInicial() != null) {
            _hashCode += getDtInicial().hashCode();
        }
        if (getDtFinal() != null) {
            _hashCode += getDtFinal().hashCode();
        }
        if (getIndisponivel() != null) {
            _hashCode += getIndisponivel().hashCode();
        }
        if (getDsProduto() != null) {
            _hashCode += getDsProduto().hashCode();
        }
        if (getDsSap() != null) {
            _hashCode += getDsSap().hashCode();
        }
        if (getSgClasseAvaliacao() != null) {
            _hashCode += getSgClasseAvaliacao().hashCode();
        }
        if (getNmProduto() != null) {
            _hashCode += getNmProduto().hashCode();
        }
        if (getSgGrupoContabil() != null) {
            _hashCode += getSgGrupoContabil().hashCode();
        }
        if (getSgSetorAtividade() != null) {
            _hashCode += getSgSetorAtividade().hashCode();
        }
        _hashCode += new Long(getIdProdutoModelo()).hashCode();
        if (getNmModelo() != null) {
            _hashCode += getNmModelo().hashCode();
        }
        _hashCode += new Long(getIdModelo()).hashCode();
        if (getInFimVida() != null) {
            _hashCode += getInFimVida().hashCode();
        }
        if (getNmTipoProduto() != null) {
            _hashCode += getNmTipoProduto().hashCode();
        }
        if (getNmTecnologia() != null) {
            _hashCode += getNmTecnologia().hashCode();
        }
        if (getNmFabricante() != null) {
            _hashCode += getNmFabricante().hashCode();
        }
        if (getListaSistemaProduto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaSistemaProduto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaSistemaProduto(), i);
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
        new org.apache.axis.description.TypeDesc(ListaProdutoProduto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ListaProduto>Produto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFabricante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idFabricante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuariCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmUsuariCriacao"));
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
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGama");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idGama"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("idTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "dtInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "dtFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "indisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "dsProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsSap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "dsSap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgClasseAvaliacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "sgClasseAvaliacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgGrupoContabil");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "sgGrupoContabil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgSetorAtividade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "sgSetorAtividade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProdutoModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idProdutoModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inFimVida");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "inFimVida"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmFabricante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmFabricante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaSistemaProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaSistemaProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ListaSistemaProduto"));
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
