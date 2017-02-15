/**
 * Modelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class Modelo  implements java.io.Serializable {
    private long idModelo;

    private java.lang.String nmModelo;

    private java.lang.String indisponivel;

    private long idFabricante;

    private java.util.Calendar dtCriacao;

    private long idTipoProduto;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private java.lang.String inFimVida;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Fabricante fabricante;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.TipoProduto tipoProduto;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaMultimidiaMultimidia[] listaMultimidia;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaProdutoProduto[] listaProduto;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaModeloTecnologiaModeloTecnologia[] listaModeloTecnologia;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaTecnologiaTecnologia[] listaTecnologia;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaModeloVariaveisModeloVariaveis[] listaModeloVariaveis;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ModeloListaCoresCor[] listaCores;

    public Modelo() {
    }

    public Modelo(
           long idModelo,
           java.lang.String nmModelo,
           java.lang.String indisponivel,
           long idFabricante,
           java.util.Calendar dtCriacao,
           long idTipoProduto,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           java.lang.String inFimVida,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Fabricante fabricante,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.TipoProduto tipoProduto,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaMultimidiaMultimidia[] listaMultimidia,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaProdutoProduto[] listaProduto,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaModeloTecnologiaModeloTecnologia[] listaModeloTecnologia,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaTecnologiaTecnologia[] listaTecnologia,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaModeloVariaveisModeloVariaveis[] listaModeloVariaveis,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ModeloListaCoresCor[] listaCores) {
           this.idModelo = idModelo;
           this.nmModelo = nmModelo;
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
           this.listaModeloTecnologia = listaModeloTecnologia;
           this.listaTecnologia = listaTecnologia;
           this.listaModeloVariaveis = listaModeloVariaveis;
           this.listaCores = listaCores;
    }


    /**
     * Gets the idModelo value for this Modelo.
     * 
     * @return idModelo
     */
    public long getIdModelo() {
        return idModelo;
    }


    /**
     * Sets the idModelo value for this Modelo.
     * 
     * @param idModelo
     */
    public void setIdModelo(long idModelo) {
        this.idModelo = idModelo;
    }


    /**
     * Gets the nmModelo value for this Modelo.
     * 
     * @return nmModelo
     */
    public java.lang.String getNmModelo() {
        return nmModelo;
    }


    /**
     * Sets the nmModelo value for this Modelo.
     * 
     * @param nmModelo
     */
    public void setNmModelo(java.lang.String nmModelo) {
        this.nmModelo = nmModelo;
    }


    /**
     * Gets the indisponivel value for this Modelo.
     * 
     * @return indisponivel
     */
    public java.lang.String getIndisponivel() {
        return indisponivel;
    }


    /**
     * Sets the indisponivel value for this Modelo.
     * 
     * @param indisponivel
     */
    public void setIndisponivel(java.lang.String indisponivel) {
        this.indisponivel = indisponivel;
    }


    /**
     * Gets the idFabricante value for this Modelo.
     * 
     * @return idFabricante
     */
    public long getIdFabricante() {
        return idFabricante;
    }


    /**
     * Sets the idFabricante value for this Modelo.
     * 
     * @param idFabricante
     */
    public void setIdFabricante(long idFabricante) {
        this.idFabricante = idFabricante;
    }


    /**
     * Gets the dtCriacao value for this Modelo.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this Modelo.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the idTipoProduto value for this Modelo.
     * 
     * @return idTipoProduto
     */
    public long getIdTipoProduto() {
        return idTipoProduto;
    }


    /**
     * Sets the idTipoProduto value for this Modelo.
     * 
     * @param idTipoProduto
     */
    public void setIdTipoProduto(long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }


    /**
     * Gets the nmUsuarioCriacao value for this Modelo.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this Modelo.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this Modelo.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this Modelo.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this Modelo.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this Modelo.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the inFimVida value for this Modelo.
     * 
     * @return inFimVida
     */
    public java.lang.String getInFimVida() {
        return inFimVida;
    }


    /**
     * Sets the inFimVida value for this Modelo.
     * 
     * @param inFimVida
     */
    public void setInFimVida(java.lang.String inFimVida) {
        this.inFimVida = inFimVida;
    }


    /**
     * Gets the fabricante value for this Modelo.
     * 
     * @return fabricante
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Fabricante getFabricante() {
        return fabricante;
    }


    /**
     * Sets the fabricante value for this Modelo.
     * 
     * @param fabricante
     */
    public void setFabricante(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Fabricante fabricante) {
        this.fabricante = fabricante;
    }


    /**
     * Gets the tipoProduto value for this Modelo.
     * 
     * @return tipoProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.TipoProduto getTipoProduto() {
        return tipoProduto;
    }


    /**
     * Sets the tipoProduto value for this Modelo.
     * 
     * @param tipoProduto
     */
    public void setTipoProduto(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }


    /**
     * Gets the listaMultimidia value for this Modelo.
     * 
     * @return listaMultimidia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaMultimidiaMultimidia[] getListaMultimidia() {
        return listaMultimidia;
    }


    /**
     * Sets the listaMultimidia value for this Modelo.
     * 
     * @param listaMultimidia
     */
    public void setListaMultimidia(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaMultimidiaMultimidia[] listaMultimidia) {
        this.listaMultimidia = listaMultimidia;
    }


    /**
     * Gets the listaProduto value for this Modelo.
     * 
     * @return listaProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaProdutoProduto[] getListaProduto() {
        return listaProduto;
    }


    /**
     * Sets the listaProduto value for this Modelo.
     * 
     * @param listaProduto
     */
    public void setListaProduto(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaProdutoProduto[] listaProduto) {
        this.listaProduto = listaProduto;
    }


    /**
     * Gets the listaModeloTecnologia value for this Modelo.
     * 
     * @return listaModeloTecnologia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaModeloTecnologiaModeloTecnologia[] getListaModeloTecnologia() {
        return listaModeloTecnologia;
    }


    /**
     * Sets the listaModeloTecnologia value for this Modelo.
     * 
     * @param listaModeloTecnologia
     */
    public void setListaModeloTecnologia(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaModeloTecnologiaModeloTecnologia[] listaModeloTecnologia) {
        this.listaModeloTecnologia = listaModeloTecnologia;
    }


    /**
     * Gets the listaTecnologia value for this Modelo.
     * 
     * @return listaTecnologia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaTecnologiaTecnologia[] getListaTecnologia() {
        return listaTecnologia;
    }


    /**
     * Sets the listaTecnologia value for this Modelo.
     * 
     * @param listaTecnologia
     */
    public void setListaTecnologia(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaTecnologiaTecnologia[] listaTecnologia) {
        this.listaTecnologia = listaTecnologia;
    }


    /**
     * Gets the listaModeloVariaveis value for this Modelo.
     * 
     * @return listaModeloVariaveis
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaModeloVariaveisModeloVariaveis[] getListaModeloVariaveis() {
        return listaModeloVariaveis;
    }


    /**
     * Sets the listaModeloVariaveis value for this Modelo.
     * 
     * @param listaModeloVariaveis
     */
    public void setListaModeloVariaveis(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaModeloVariaveisModeloVariaveis[] listaModeloVariaveis) {
        this.listaModeloVariaveis = listaModeloVariaveis;
    }


    /**
     * Gets the listaCores value for this Modelo.
     * 
     * @return listaCores
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ModeloListaCoresCor[] getListaCores() {
        return listaCores;
    }


    /**
     * Sets the listaCores value for this Modelo.
     * 
     * @param listaCores
     */
    public void setListaCores(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ModeloListaCoresCor[] listaCores) {
        this.listaCores = listaCores;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Modelo)) return false;
        Modelo other = (Modelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idModelo == other.getIdModelo() &&
            ((this.nmModelo==null && other.getNmModelo()==null) || 
             (this.nmModelo!=null &&
              this.nmModelo.equals(other.getNmModelo()))) &&
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
            ((this.listaModeloTecnologia==null && other.getListaModeloTecnologia()==null) || 
             (this.listaModeloTecnologia!=null &&
              java.util.Arrays.equals(this.listaModeloTecnologia, other.getListaModeloTecnologia()))) &&
            ((this.listaTecnologia==null && other.getListaTecnologia()==null) || 
             (this.listaTecnologia!=null &&
              java.util.Arrays.equals(this.listaTecnologia, other.getListaTecnologia()))) &&
            ((this.listaModeloVariaveis==null && other.getListaModeloVariaveis()==null) || 
             (this.listaModeloVariaveis!=null &&
              java.util.Arrays.equals(this.listaModeloVariaveis, other.getListaModeloVariaveis()))) &&
            ((this.listaCores==null && other.getListaCores()==null) || 
             (this.listaCores!=null &&
              java.util.Arrays.equals(this.listaCores, other.getListaCores())));
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
        _hashCode += new Long(getIdModelo()).hashCode();
        if (getNmModelo() != null) {
            _hashCode += getNmModelo().hashCode();
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
        if (getListaModeloTecnologia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaModeloTecnologia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaModeloTecnologia(), i);
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
        if (getListaModeloVariaveis() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaModeloVariaveis());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaModeloVariaveis(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaCores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCores(), i);
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
        new org.apache.axis.description.TypeDesc(Modelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">Modelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idModelo"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">Fabricante"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "TipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">TipoProduto"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ListaMultimidia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ListaProduto"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaModeloTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaModeloTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ListaModeloTecnologia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ListaTecnologia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaModeloVariaveis");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaModeloVariaveis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ListaModeloVariaveis"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaCores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>Modelo>ListaCores>Cor"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "Cor"));
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
