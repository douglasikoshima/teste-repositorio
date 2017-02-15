/**
 * ResultadoModeloPorIdGrupoProduto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoModeloPorIdGrupoProduto  implements java.io.Serializable {
    private long idGrupoProduto;

    private java.lang.String nmGrupoProduto;

    private java.lang.String indisponivel;

    private long idFabricante;

    private java.util.Calendar dtCriacao;

    private long idTipoProduto;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private java.lang.String inFimVida;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoFabricante fabricante;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoTipoProduto tipoProduto;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia[] listaMultimidia;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaProdutoProduto[] listaProduto;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia[] listaGrupoProdutoTecnologia;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia[] listaTecnologia;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoVariaveisGrupoProdutoVariaveis[] listaGrupoProdutoVariaveis;

    public ResultadoModeloPorIdGrupoProduto() {
    }

    public ResultadoModeloPorIdGrupoProduto(
           long idGrupoProduto,
           java.lang.String nmGrupoProduto,
           java.lang.String indisponivel,
           long idFabricante,
           java.util.Calendar dtCriacao,
           long idTipoProduto,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           java.lang.String inFimVida,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoFabricante fabricante,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoTipoProduto tipoProduto,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia[] listaMultimidia,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaProdutoProduto[] listaProduto,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia[] listaGrupoProdutoTecnologia,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia[] listaTecnologia,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoVariaveisGrupoProdutoVariaveis[] listaGrupoProdutoVariaveis) {
           this.idGrupoProduto = idGrupoProduto;
           this.nmGrupoProduto = nmGrupoProduto;
           this.indisponivel = indisponivel;
           this.idFabricante = idFabricante;
           this.dtCriacao = dtCriacao;
           this.idTipoProduto = idTipoProduto;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.inFimVida = inFimVida;
           this.fabricante = fabricante;
           this.tipoProduto = tipoProduto;
           this.listaMultimidia = listaMultimidia;
           this.listaProduto = listaProduto;
           this.listaGrupoProdutoTecnologia = listaGrupoProdutoTecnologia;
           this.listaTecnologia = listaTecnologia;
           this.listaGrupoProdutoVariaveis = listaGrupoProdutoVariaveis;
    }


    /**
     * Gets the idGrupoProduto value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return idGrupoProduto
     */
    public long getIdGrupoProduto() {
        return idGrupoProduto;
    }


    /**
     * Sets the idGrupoProduto value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param idGrupoProduto
     */
    public void setIdGrupoProduto(long idGrupoProduto) {
        this.idGrupoProduto = idGrupoProduto;
    }


    /**
     * Gets the nmGrupoProduto value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return nmGrupoProduto
     */
    public java.lang.String getNmGrupoProduto() {
        return nmGrupoProduto;
    }


    /**
     * Sets the nmGrupoProduto value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param nmGrupoProduto
     */
    public void setNmGrupoProduto(java.lang.String nmGrupoProduto) {
        this.nmGrupoProduto = nmGrupoProduto;
    }


    /**
     * Gets the indisponivel value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return indisponivel
     */
    public java.lang.String getIndisponivel() {
        return indisponivel;
    }


    /**
     * Sets the indisponivel value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param indisponivel
     */
    public void setIndisponivel(java.lang.String indisponivel) {
        this.indisponivel = indisponivel;
    }


    /**
     * Gets the idFabricante value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return idFabricante
     */
    public long getIdFabricante() {
        return idFabricante;
    }


    /**
     * Sets the idFabricante value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param idFabricante
     */
    public void setIdFabricante(long idFabricante) {
        this.idFabricante = idFabricante;
    }


    /**
     * Gets the dtCriacao value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the idTipoProduto value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return idTipoProduto
     */
    public long getIdTipoProduto() {
        return idTipoProduto;
    }


    /**
     * Sets the idTipoProduto value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param idTipoProduto
     */
    public void setIdTipoProduto(long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the inFimVida value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return inFimVida
     */
    public java.lang.String getInFimVida() {
        return inFimVida;
    }


    /**
     * Sets the inFimVida value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param inFimVida
     */
    public void setInFimVida(java.lang.String inFimVida) {
        this.inFimVida = inFimVida;
    }


    /**
     * Gets the fabricante value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return fabricante
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoFabricante getFabricante() {
        return fabricante;
    }


    /**
     * Sets the fabricante value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param fabricante
     */
    public void setFabricante(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoFabricante fabricante) {
        this.fabricante = fabricante;
    }


    /**
     * Gets the tipoProduto value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return tipoProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoTipoProduto getTipoProduto() {
        return tipoProduto;
    }


    /**
     * Sets the tipoProduto value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param tipoProduto
     */
    public void setTipoProduto(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoTipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }


    /**
     * Gets the listaMultimidia value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return listaMultimidia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia[] getListaMultimidia() {
        return listaMultimidia;
    }


    /**
     * Sets the listaMultimidia value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param listaMultimidia
     */
    public void setListaMultimidia(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaMultimidiaMultimidia[] listaMultimidia) {
        this.listaMultimidia = listaMultimidia;
    }


    /**
     * Gets the listaProduto value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return listaProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaProdutoProduto[] getListaProduto() {
        return listaProduto;
    }


    /**
     * Sets the listaProduto value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param listaProduto
     */
    public void setListaProduto(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaProdutoProduto[] listaProduto) {
        this.listaProduto = listaProduto;
    }


    /**
     * Gets the listaGrupoProdutoTecnologia value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return listaGrupoProdutoTecnologia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia[] getListaGrupoProdutoTecnologia() {
        return listaGrupoProdutoTecnologia;
    }


    /**
     * Sets the listaGrupoProdutoTecnologia value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param listaGrupoProdutoTecnologia
     */
    public void setListaGrupoProdutoTecnologia(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia[] listaGrupoProdutoTecnologia) {
        this.listaGrupoProdutoTecnologia = listaGrupoProdutoTecnologia;
    }


    /**
     * Gets the listaTecnologia value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return listaTecnologia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia[] getListaTecnologia() {
        return listaTecnologia;
    }


    /**
     * Sets the listaTecnologia value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param listaTecnologia
     */
    public void setListaTecnologia(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaTecnologiaTecnologia[] listaTecnologia) {
        this.listaTecnologia = listaTecnologia;
    }


    /**
     * Gets the listaGrupoProdutoVariaveis value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @return listaGrupoProdutoVariaveis
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoVariaveisGrupoProdutoVariaveis[] getListaGrupoProdutoVariaveis() {
        return listaGrupoProdutoVariaveis;
    }


    /**
     * Sets the listaGrupoProdutoVariaveis value for this ResultadoModeloPorIdGrupoProduto.
     * 
     * @param listaGrupoProdutoVariaveis
     */
    public void setListaGrupoProdutoVariaveis(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoVariaveisGrupoProdutoVariaveis[] listaGrupoProdutoVariaveis) {
        this.listaGrupoProdutoVariaveis = listaGrupoProdutoVariaveis;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoModeloPorIdGrupoProduto)) return false;
        ResultadoModeloPorIdGrupoProduto other = (ResultadoModeloPorIdGrupoProduto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idGrupoProduto == other.getIdGrupoProduto() &&
            ((this.nmGrupoProduto==null && other.getNmGrupoProduto()==null) || 
             (this.nmGrupoProduto!=null &&
              this.nmGrupoProduto.equals(other.getNmGrupoProduto()))) &&
            ((this.indisponivel==null && other.getIndisponivel()==null) || 
             (this.indisponivel!=null &&
              this.indisponivel.equals(other.getIndisponivel()))) &&
            this.idFabricante == other.getIdFabricante() &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            this.idTipoProduto == other.getIdTipoProduto() &&
            ((this.nmUsuarioCriacao==null && other.getNmUsuarioCriacao()==null) || 
             (this.nmUsuarioCriacao!=null &&
              this.nmUsuarioCriacao.equals(other.getNmUsuarioCriacao()))) &&
            ((this.dtUltimaAlteracao==null && other.getDtUltimaAlteracao()==null) || 
             (this.dtUltimaAlteracao!=null &&
              this.dtUltimaAlteracao.equals(other.getDtUltimaAlteracao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            ((this.inFimVida==null && other.getInFimVida()==null) || 
             (this.inFimVida!=null &&
              this.inFimVida.equals(other.getInFimVida()))) &&
            ((this.fabricante==null && other.getFabricante()==null) || 
             (this.fabricante!=null &&
              this.fabricante.equals(other.getFabricante()))) &&
            ((this.tipoProduto==null && other.getTipoProduto()==null) || 
             (this.tipoProduto!=null &&
              this.tipoProduto.equals(other.getTipoProduto()))) &&
            ((this.listaMultimidia==null && other.getListaMultimidia()==null) || 
             (this.listaMultimidia!=null &&
              java.util.Arrays.equals(this.listaMultimidia, other.getListaMultimidia()))) &&
            ((this.listaProduto==null && other.getListaProduto()==null) || 
             (this.listaProduto!=null &&
              java.util.Arrays.equals(this.listaProduto, other.getListaProduto()))) &&
            ((this.listaGrupoProdutoTecnologia==null && other.getListaGrupoProdutoTecnologia()==null) || 
             (this.listaGrupoProdutoTecnologia!=null &&
              java.util.Arrays.equals(this.listaGrupoProdutoTecnologia, other.getListaGrupoProdutoTecnologia()))) &&
            ((this.listaTecnologia==null && other.getListaTecnologia()==null) || 
             (this.listaTecnologia!=null &&
              java.util.Arrays.equals(this.listaTecnologia, other.getListaTecnologia()))) &&
            ((this.listaGrupoProdutoVariaveis==null && other.getListaGrupoProdutoVariaveis()==null) || 
             (this.listaGrupoProdutoVariaveis!=null &&
              java.util.Arrays.equals(this.listaGrupoProdutoVariaveis, other.getListaGrupoProdutoVariaveis())));
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
        _hashCode += new Long(getIdGrupoProduto()).hashCode();
        if (getNmGrupoProduto() != null) {
            _hashCode += getNmGrupoProduto().hashCode();
        }
        if (getIndisponivel() != null) {
            _hashCode += getIndisponivel().hashCode();
        }
        _hashCode += new Long(getIdFabricante()).hashCode();
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        _hashCode += new Long(getIdTipoProduto()).hashCode();
        if (getNmUsuarioCriacao() != null) {
            _hashCode += getNmUsuarioCriacao().hashCode();
        }
        if (getDtUltimaAlteracao() != null) {
            _hashCode += getDtUltimaAlteracao().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        if (getInFimVida() != null) {
            _hashCode += getInFimVida().hashCode();
        }
        if (getFabricante() != null) {
            _hashCode += getFabricante().hashCode();
        }
        if (getTipoProduto() != null) {
            _hashCode += getTipoProduto().hashCode();
        }
        if (getListaMultimidia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaMultimidia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaMultimidia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaProduto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaProduto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaProduto(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaGrupoProdutoTecnologia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaGrupoProdutoTecnologia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaGrupoProdutoTecnologia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaTecnologia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTecnologia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTecnologia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaGrupoProdutoVariaveis() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaGrupoProdutoVariaveis());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaGrupoProdutoVariaveis(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoModeloPorIdGrupoProduto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ResultadoModeloPorId>GrupoProduto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "indisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFabricante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idFabricante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("idTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "dtUltimaAlteracao"));
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
        elemField.setFieldName("inFimVida");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "inFimVida"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fabricante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "Fabricante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>ResultadoModeloPorId>GrupoProduto>Fabricante"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "TipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>ResultadoModeloPorId>GrupoProduto>TipoProduto"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoModeloPorId>GrupoProduto>ListaMultimidia>Multimidia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "Multimidia"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoModeloPorId>GrupoProduto>ListaProduto>Produto"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "Produto"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaGrupoProdutoTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaGrupoProdutoTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoModeloPorId>GrupoProduto>ListaGrupoProdutoTecnologia>GrupoProdutoTecnologia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "GrupoProdutoTecnologia"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoModeloPorId>GrupoProduto>ListaTecnologia>Tecnologia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "Tecnologia"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaGrupoProdutoVariaveis");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaGrupoProdutoVariaveis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoModeloPorId>GrupoProduto>ListaGrupoProdutoVariaveis>GrupoProdutoVariaveis"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "GrupoProdutoVariaveis"));
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
