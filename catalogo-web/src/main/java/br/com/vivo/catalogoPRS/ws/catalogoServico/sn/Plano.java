/**
 * Plano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class Plano  implements java.io.Serializable {
    private long idPlano;

    private java.lang.String nmComercial;

    private java.util.Calendar dtInicial;

    private java.lang.String indisponivel;

    private java.lang.String descricao;

    private java.math.BigDecimal valorAssinaturaMensal;

    private java.lang.Long idPlataforma;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtFinal;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private java.math.BigDecimal qtMinFranquia;

    private java.lang.Long idPlanoTitular;

    private java.lang.Long idRegional;

    private java.lang.Long qtMinimaDependentes;

    private java.lang.Long qtMaximaDependentes;

    private java.lang.String cdAnatel;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.SistemaPlano sistemaPlano;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaPlanoVariaveisPlanoVariaveis[] listaPlanoVariaveis;

    public Plano() {
    }

    public Plano(
           long idPlano,
           java.lang.String nmComercial,
           java.util.Calendar dtInicial,
           java.lang.String indisponivel,
           java.lang.String descricao,
           java.math.BigDecimal valorAssinaturaMensal,
           java.lang.Long idPlataforma,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtFinal,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           java.math.BigDecimal qtMinFranquia,
           java.lang.Long idPlanoTitular,
           java.lang.Long idRegional,
           java.lang.Long qtMinimaDependentes,
           java.lang.Long qtMaximaDependentes,
           java.lang.String cdAnatel,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.SistemaPlano sistemaPlano,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaPlanoVariaveisPlanoVariaveis[] listaPlanoVariaveis) {
           this.idPlano = idPlano;
           this.nmComercial = nmComercial;
           this.dtInicial = dtInicial;
           this.indisponivel = indisponivel;
           this.descricao = descricao;
           this.valorAssinaturaMensal = valorAssinaturaMensal;
           this.idPlataforma = idPlataforma;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtFinal = dtFinal;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.qtMinFranquia = qtMinFranquia;
           this.idPlanoTitular = idPlanoTitular;
           this.idRegional = idRegional;
           this.qtMinimaDependentes = qtMinimaDependentes;
           this.qtMaximaDependentes = qtMaximaDependentes;
           this.cdAnatel = cdAnatel;
           this.sistemaPlano = sistemaPlano;
           this.listaPlanoVariaveis = listaPlanoVariaveis;
    }


    /**
     * Gets the idPlano value for this Plano.
     * 
     * @return idPlano
     */
    public long getIdPlano() {
        return idPlano;
    }


    /**
     * Sets the idPlano value for this Plano.
     * 
     * @param idPlano
     */
    public void setIdPlano(long idPlano) {
        this.idPlano = idPlano;
    }


    /**
     * Gets the nmComercial value for this Plano.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this Plano.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the dtInicial value for this Plano.
     * 
     * @return dtInicial
     */
    public java.util.Calendar getDtInicial() {
        return dtInicial;
    }


    /**
     * Sets the dtInicial value for this Plano.
     * 
     * @param dtInicial
     */
    public void setDtInicial(java.util.Calendar dtInicial) {
        this.dtInicial = dtInicial;
    }


    /**
     * Gets the indisponivel value for this Plano.
     * 
     * @return indisponivel
     */
    public java.lang.String getIndisponivel() {
        return indisponivel;
    }


    /**
     * Sets the indisponivel value for this Plano.
     * 
     * @param indisponivel
     */
    public void setIndisponivel(java.lang.String indisponivel) {
        this.indisponivel = indisponivel;
    }


    /**
     * Gets the descricao value for this Plano.
     * 
     * @return descricao
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this Plano.
     * 
     * @param descricao
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }


    /**
     * Gets the valorAssinaturaMensal value for this Plano.
     * 
     * @return valorAssinaturaMensal
     */
    public java.math.BigDecimal getValorAssinaturaMensal() {
        return valorAssinaturaMensal;
    }


    /**
     * Sets the valorAssinaturaMensal value for this Plano.
     * 
     * @param valorAssinaturaMensal
     */
    public void setValorAssinaturaMensal(java.math.BigDecimal valorAssinaturaMensal) {
        this.valorAssinaturaMensal = valorAssinaturaMensal;
    }


    /**
     * Gets the idPlataforma value for this Plano.
     * 
     * @return idPlataforma
     */
    public java.lang.Long getIdPlataforma() {
        return idPlataforma;
    }


    /**
     * Sets the idPlataforma value for this Plano.
     * 
     * @param idPlataforma
     */
    public void setIdPlataforma(java.lang.Long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }


    /**
     * Gets the dtCriacao value for this Plano.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this Plano.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this Plano.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this Plano.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtFinal value for this Plano.
     * 
     * @return dtFinal
     */
    public java.util.Calendar getDtFinal() {
        return dtFinal;
    }


    /**
     * Sets the dtFinal value for this Plano.
     * 
     * @param dtFinal
     */
    public void setDtFinal(java.util.Calendar dtFinal) {
        this.dtFinal = dtFinal;
    }


    /**
     * Gets the dtUltimaAlteracao value for this Plano.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this Plano.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this Plano.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this Plano.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the qtMinFranquia value for this Plano.
     * 
     * @return qtMinFranquia
     */
    public java.math.BigDecimal getQtMinFranquia() {
        return qtMinFranquia;
    }


    /**
     * Sets the qtMinFranquia value for this Plano.
     * 
     * @param qtMinFranquia
     */
    public void setQtMinFranquia(java.math.BigDecimal qtMinFranquia) {
        this.qtMinFranquia = qtMinFranquia;
    }


    /**
     * Gets the idPlanoTitular value for this Plano.
     * 
     * @return idPlanoTitular
     */
    public java.lang.Long getIdPlanoTitular() {
        return idPlanoTitular;
    }


    /**
     * Sets the idPlanoTitular value for this Plano.
     * 
     * @param idPlanoTitular
     */
    public void setIdPlanoTitular(java.lang.Long idPlanoTitular) {
        this.idPlanoTitular = idPlanoTitular;
    }


    /**
     * Gets the idRegional value for this Plano.
     * 
     * @return idRegional
     */
    public java.lang.Long getIdRegional() {
        return idRegional;
    }


    /**
     * Sets the idRegional value for this Plano.
     * 
     * @param idRegional
     */
    public void setIdRegional(java.lang.Long idRegional) {
        this.idRegional = idRegional;
    }


    /**
     * Gets the qtMinimaDependentes value for this Plano.
     * 
     * @return qtMinimaDependentes
     */
    public java.lang.Long getQtMinimaDependentes() {
        return qtMinimaDependentes;
    }


    /**
     * Sets the qtMinimaDependentes value for this Plano.
     * 
     * @param qtMinimaDependentes
     */
    public void setQtMinimaDependentes(java.lang.Long qtMinimaDependentes) {
        this.qtMinimaDependentes = qtMinimaDependentes;
    }


    /**
     * Gets the qtMaximaDependentes value for this Plano.
     * 
     * @return qtMaximaDependentes
     */
    public java.lang.Long getQtMaximaDependentes() {
        return qtMaximaDependentes;
    }


    /**
     * Sets the qtMaximaDependentes value for this Plano.
     * 
     * @param qtMaximaDependentes
     */
    public void setQtMaximaDependentes(java.lang.Long qtMaximaDependentes) {
        this.qtMaximaDependentes = qtMaximaDependentes;
    }


    /**
     * Gets the cdAnatel value for this Plano.
     * 
     * @return cdAnatel
     */
    public java.lang.String getCdAnatel() {
        return cdAnatel;
    }


    /**
     * Sets the cdAnatel value for this Plano.
     * 
     * @param cdAnatel
     */
    public void setCdAnatel(java.lang.String cdAnatel) {
        this.cdAnatel = cdAnatel;
    }


    /**
     * Gets the sistemaPlano value for this Plano.
     * 
     * @return sistemaPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.SistemaPlano getSistemaPlano() {
        return sistemaPlano;
    }


    /**
     * Sets the sistemaPlano value for this Plano.
     * 
     * @param sistemaPlano
     */
    public void setSistemaPlano(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.SistemaPlano sistemaPlano) {
        this.sistemaPlano = sistemaPlano;
    }


    /**
     * Gets the listaPlanoVariaveis value for this Plano.
     * 
     * @return listaPlanoVariaveis
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaPlanoVariaveisPlanoVariaveis[] getListaPlanoVariaveis() {
        return listaPlanoVariaveis;
    }


    /**
     * Sets the listaPlanoVariaveis value for this Plano.
     * 
     * @param listaPlanoVariaveis
     */
    public void setListaPlanoVariaveis(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaPlanoVariaveisPlanoVariaveis[] listaPlanoVariaveis) {
        this.listaPlanoVariaveis = listaPlanoVariaveis;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Plano)) return false;
        Plano other = (Plano) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idPlano == other.getIdPlano() &&
            ((this.nmComercial==null && other.getNmComercial()==null) || 
             (this.nmComercial!=null &&
              this.nmComercial.equals(other.getNmComercial()))) &&
            ((this.dtInicial==null && other.getDtInicial()==null) || 
             (this.dtInicial!=null &&
              this.dtInicial.equals(other.getDtInicial()))) &&
            ((this.indisponivel==null && other.getIndisponivel()==null) || 
             (this.indisponivel!=null &&
              this.indisponivel.equals(other.getIndisponivel()))) &&
            ((this.descricao==null && other.getDescricao()==null) || 
             (this.descricao!=null &&
              this.descricao.equals(other.getDescricao()))) &&
            ((this.valorAssinaturaMensal==null && other.getValorAssinaturaMensal()==null) || 
             (this.valorAssinaturaMensal!=null &&
              this.valorAssinaturaMensal.equals(other.getValorAssinaturaMensal()))) &&
            this.idPlataforma == other.getIdPlataforma() &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            ((this.nmUsuarioCriacao==null && other.getNmUsuarioCriacao()==null) || 
             (this.nmUsuarioCriacao!=null &&
              this.nmUsuarioCriacao.equals(other.getNmUsuarioCriacao()))) &&
            ((this.dtFinal==null && other.getDtFinal()==null) || 
             (this.dtFinal!=null &&
              this.dtFinal.equals(other.getDtFinal()))) &&
            ((this.dtUltimaAlteracao==null && other.getDtUltimaAlteracao()==null) || 
             (this.dtUltimaAlteracao!=null &&
              this.dtUltimaAlteracao.equals(other.getDtUltimaAlteracao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            ((this.qtMinFranquia==null && other.getQtMinFranquia()==null) || 
             (this.qtMinFranquia!=null &&
              this.qtMinFranquia.equals(other.getQtMinFranquia()))) &&
            ((this.idPlanoTitular==null && other.getIdPlanoTitular()==null) || 
             (this.idPlanoTitular!=null &&
              this.idPlanoTitular.equals(other.getIdPlanoTitular()))) &&
            ((this.idRegional==null && other.getIdRegional()==null) || 
             (this.idRegional!=null &&
              this.idRegional.equals(other.getIdRegional()))) &&
            ((this.qtMinimaDependentes==null && other.getQtMinimaDependentes()==null) || 
             (this.qtMinimaDependentes!=null &&
              this.qtMinimaDependentes.equals(other.getQtMinimaDependentes()))) &&
            ((this.qtMaximaDependentes==null && other.getQtMaximaDependentes()==null) || 
             (this.qtMaximaDependentes!=null &&
              this.qtMaximaDependentes.equals(other.getQtMaximaDependentes()))) &&
            ((this.cdAnatel==null && other.getCdAnatel()==null) || 
             (this.cdAnatel!=null &&
              this.cdAnatel.equals(other.getCdAnatel()))) &&
            ((this.sistemaPlano==null && other.getSistemaPlano()==null) || 
             (this.sistemaPlano!=null &&
              this.sistemaPlano.equals(other.getSistemaPlano()))) &&
            ((this.listaPlanoVariaveis==null && other.getListaPlanoVariaveis()==null) || 
             (this.listaPlanoVariaveis!=null &&
              java.util.Arrays.equals(this.listaPlanoVariaveis, other.getListaPlanoVariaveis())));
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
        _hashCode += new Long(getIdPlano()).hashCode();
        if (getNmComercial() != null) {
            _hashCode += getNmComercial().hashCode();
        }
        if (getDtInicial() != null) {
            _hashCode += getDtInicial().hashCode();
        }
        if (getIndisponivel() != null) {
            _hashCode += getIndisponivel().hashCode();
        }
        if (getDescricao() != null) {
            _hashCode += getDescricao().hashCode();
        }
        if (getValorAssinaturaMensal() != null) {
            _hashCode += getValorAssinaturaMensal().hashCode();
        }
        _hashCode += new Long(getIdPlataforma()).hashCode();
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        if (getNmUsuarioCriacao() != null) {
            _hashCode += getNmUsuarioCriacao().hashCode();
        }
        if (getDtFinal() != null) {
            _hashCode += getDtFinal().hashCode();
        }
        if (getDtUltimaAlteracao() != null) {
            _hashCode += getDtUltimaAlteracao().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        if (getQtMinFranquia() != null) {
            _hashCode += getQtMinFranquia().hashCode();
        }
        if (getIdPlanoTitular() != null) {
            _hashCode += getIdPlanoTitular().hashCode();
        }
        if (getIdRegional() != null) {
            _hashCode += getIdRegional().hashCode();
        }
        if (getQtMinimaDependentes() != null) {
            _hashCode += getQtMinimaDependentes().hashCode();
        }
        if (getQtMaximaDependentes() != null) {
            _hashCode += getQtMaximaDependentes().hashCode();
        }
        if (getCdAnatel() != null) {
            _hashCode += getCdAnatel().hashCode();
        }
        if (getSistemaPlano() != null) {
            _hashCode += getSistemaPlano().hashCode();
        }
        if (getListaPlanoVariaveis() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaPlanoVariaveis());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaPlanoVariaveis(), i);
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
        new org.apache.axis.description.TypeDesc(Plano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">Plano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("dtInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "dtInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("descricao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "descricao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorAssinaturaMensal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "valorAssinaturaMensal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("dtFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "dtFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("qtMinFranquia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtMinFranquia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlanoTitular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idPlanoTitular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idRegional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idRegional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtMinimaDependentes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtMinimaDependentes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtMaximaDependentes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtMaximaDependentes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("sistemaPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "SistemaPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">SistemaPlano"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaPlanoVariaveis");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaPlanoVariaveis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ListaPlanoVariaveis"));
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
