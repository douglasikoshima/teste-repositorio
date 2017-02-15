/**
 * ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao  implements java.io.Serializable {
    private java.lang.Long idPlano;

    private java.lang.Long idTipoPlano;

    private java.lang.String cdCodigo;

    private java.lang.String nmComercial;

    private java.lang.String indTitDep;

    private java.lang.Long qtdMaxDependenteCatalogo;

    private java.lang.Long qtdMaxDependenteLegado;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacaoInDisponibilidadeCatalogo inDisponibilidadeCatalogo;

    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacaoInDisponibilidadeLegado inDisponibilidadeLegado;

    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacaoAtivaWiFi ativaWiFi;

    public ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao() {
    }

    public ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao(
           java.lang.Long idPlano,
           java.lang.Long idTipoPlano,
           java.lang.String cdCodigo,
           java.lang.String nmComercial,
           java.lang.String indTitDep,
           java.lang.Long qtdMaxDependenteCatalogo,
           java.lang.Long qtdMaxDependenteLegado,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacaoInDisponibilidadeCatalogo inDisponibilidadeCatalogo,
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacaoInDisponibilidadeLegado inDisponibilidadeLegado,
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacaoAtivaWiFi ativaWiFi) {
           this.idPlano = idPlano;
           this.idTipoPlano = idTipoPlano;
           this.cdCodigo = cdCodigo;
           this.nmComercial = nmComercial;
           this.indTitDep = indTitDep;
           this.qtdMaxDependenteCatalogo = qtdMaxDependenteCatalogo;
           this.qtdMaxDependenteLegado = qtdMaxDependenteLegado;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
           this.inDisponibilidadeLegado = inDisponibilidadeLegado;
           this.ativaWiFi = ativaWiFi;
    }


    /**
     * Gets the idPlano value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @return idPlano
     */
    public java.lang.Long getIdPlano() {
        return idPlano;
    }


    /**
     * Sets the idPlano value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @param idPlano
     */
    public void setIdPlano(java.lang.Long idPlano) {
        this.idPlano = idPlano;
    }


    /**
     * Gets the idTipoPlano value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @return idTipoPlano
     */
    public java.lang.Long getIdTipoPlano() {
        return idTipoPlano;
    }


    /**
     * Sets the idTipoPlano value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @param idTipoPlano
     */
    public void setIdTipoPlano(java.lang.Long idTipoPlano) {
        this.idTipoPlano = idTipoPlano;
    }


    /**
     * Gets the cdCodigo value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @return cdCodigo
     */
    public java.lang.String getCdCodigo() {
        return cdCodigo;
    }


    /**
     * Sets the cdCodigo value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @param cdCodigo
     */
    public void setCdCodigo(java.lang.String cdCodigo) {
        this.cdCodigo = cdCodigo;
    }


    /**
     * Gets the nmComercial value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the indTitDep value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @return indTitDep
     */
    public java.lang.String getIndTitDep() {
        return indTitDep;
    }


    /**
     * Sets the indTitDep value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @param indTitDep
     */
    public void setIndTitDep(java.lang.String indTitDep) {
        this.indTitDep = indTitDep;
    }


    /**
     * Gets the qtdMaxDependenteCatalogo value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @return qtdMaxDependenteCatalogo
     */
    public java.lang.Long getQtdMaxDependenteCatalogo() {
        return qtdMaxDependenteCatalogo;
    }


    /**
     * Sets the qtdMaxDependenteCatalogo value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @param qtdMaxDependenteCatalogo
     */
    public void setQtdMaxDependenteCatalogo(java.lang.Long qtdMaxDependenteCatalogo) {
        this.qtdMaxDependenteCatalogo = qtdMaxDependenteCatalogo;
    }


    /**
     * Gets the qtdMaxDependenteLegado value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @return qtdMaxDependenteLegado
     */
    public java.lang.Long getQtdMaxDependenteLegado() {
        return qtdMaxDependenteLegado;
    }


    /**
     * Sets the qtdMaxDependenteLegado value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @param qtdMaxDependenteLegado
     */
    public void setQtdMaxDependenteLegado(java.lang.Long qtdMaxDependenteLegado) {
        this.qtdMaxDependenteLegado = qtdMaxDependenteLegado;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the inDisponibilidadeCatalogo value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @return inDisponibilidadeCatalogo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacaoInDisponibilidadeCatalogo getInDisponibilidadeCatalogo() {
        return inDisponibilidadeCatalogo;
    }


    /**
     * Sets the inDisponibilidadeCatalogo value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @param inDisponibilidadeCatalogo
     */
    public void setInDisponibilidadeCatalogo(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacaoInDisponibilidadeCatalogo inDisponibilidadeCatalogo) {
        this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
    }


    /**
     * Gets the inDisponibilidadeLegado value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @return inDisponibilidadeLegado
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacaoInDisponibilidadeLegado getInDisponibilidadeLegado() {
        return inDisponibilidadeLegado;
    }


    /**
     * Sets the inDisponibilidadeLegado value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @param inDisponibilidadeLegado
     */
    public void setInDisponibilidadeLegado(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacaoInDisponibilidadeLegado inDisponibilidadeLegado) {
        this.inDisponibilidadeLegado = inDisponibilidadeLegado;
    }


    /**
     * Gets the ativaWiFi value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @return ativaWiFi
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacaoAtivaWiFi getAtivaWiFi() {
        return ativaWiFi;
    }


    /**
     * Sets the ativaWiFi value for this ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.
     * 
     * @param ativaWiFi
     */
    public void setAtivaWiFi(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacaoAtivaWiFi ativaWiFi) {
        this.ativaWiFi = ativaWiFi;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao)) return false;
        ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao other = (ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idPlano==null && other.getIdPlano()==null) || 
             (this.idPlano!=null &&
              this.idPlano.equals(other.getIdPlano()))) &&
            ((this.idTipoPlano==null && other.getIdTipoPlano()==null) || 
             (this.idTipoPlano!=null &&
              this.idTipoPlano.equals(other.getIdTipoPlano()))) &&
            ((this.cdCodigo==null && other.getCdCodigo()==null) || 
             (this.cdCodigo!=null &&
              this.cdCodigo.equals(other.getCdCodigo()))) &&
            ((this.nmComercial==null && other.getNmComercial()==null) || 
             (this.nmComercial!=null &&
              this.nmComercial.equals(other.getNmComercial()))) &&
            ((this.indTitDep==null && other.getIndTitDep()==null) || 
             (this.indTitDep!=null &&
              this.indTitDep.equals(other.getIndTitDep()))) &&
            ((this.qtdMaxDependenteCatalogo==null && other.getQtdMaxDependenteCatalogo()==null) || 
             (this.qtdMaxDependenteCatalogo!=null &&
              this.qtdMaxDependenteCatalogo.equals(other.getQtdMaxDependenteCatalogo()))) &&
            ((this.qtdMaxDependenteLegado==null && other.getQtdMaxDependenteLegado()==null) || 
             (this.qtdMaxDependenteLegado!=null &&
              this.qtdMaxDependenteLegado.equals(other.getQtdMaxDependenteLegado()))) &&
            ((this.dtUltimaAlteracao==null && other.getDtUltimaAlteracao()==null) || 
             (this.dtUltimaAlteracao!=null &&
              this.dtUltimaAlteracao.equals(other.getDtUltimaAlteracao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            ((this.inDisponibilidadeCatalogo==null && other.getInDisponibilidadeCatalogo()==null) || 
             (this.inDisponibilidadeCatalogo!=null &&
              this.inDisponibilidadeCatalogo.equals(other.getInDisponibilidadeCatalogo()))) &&
            ((this.inDisponibilidadeLegado==null && other.getInDisponibilidadeLegado()==null) || 
             (this.inDisponibilidadeLegado!=null &&
              this.inDisponibilidadeLegado.equals(other.getInDisponibilidadeLegado()))) &&
            ((this.ativaWiFi==null && other.getAtivaWiFi()==null) || 
             (this.ativaWiFi!=null &&
              this.ativaWiFi.equals(other.getAtivaWiFi())));
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
        if (getIdPlano() != null) {
            _hashCode += getIdPlano().hashCode();
        }
        if (getIdTipoPlano() != null) {
            _hashCode += getIdTipoPlano().hashCode();
        }
        if (getCdCodigo() != null) {
            _hashCode += getCdCodigo().hashCode();
        }
        if (getNmComercial() != null) {
            _hashCode += getNmComercial().hashCode();
        }
        if (getIndTitDep() != null) {
            _hashCode += getIndTitDep().hashCode();
        }
        if (getQtdMaxDependenteCatalogo() != null) {
            _hashCode += getQtdMaxDependenteCatalogo().hashCode();
        }
        if (getQtdMaxDependenteLegado() != null) {
            _hashCode += getQtdMaxDependenteLegado().hashCode();
        }
        if (getDtUltimaAlteracao() != null) {
            _hashCode += getDtUltimaAlteracao().hashCode();
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
        if (getAtivaWiFi() != null) {
            _hashCode += getAtivaWiFi().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>>ResultadoBuscarListaPlanoParametrizacao>ListaBuscarListaPlanoParametrizacao>RetornoBuscarListaPlanoParametrizacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idTipoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "cdCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmComercial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "nmComercial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indTitDep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "indTitDep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMaxDependenteCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "qtdMaxDependenteCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMaxDependenteLegado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "qtdMaxDependenteLegado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponibilidadeCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "inDisponibilidadeCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>>>ResultadoBuscarListaPlanoParametrizacao>ListaBuscarListaPlanoParametrizacao>RetornoBuscarListaPlanoParametrizacao>inDisponibilidadeCatalogo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponibilidadeLegado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "inDisponibilidadeLegado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>>>ResultadoBuscarListaPlanoParametrizacao>ListaBuscarListaPlanoParametrizacao>RetornoBuscarListaPlanoParametrizacao>inDisponibilidadeLegado"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ativaWiFi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ativaWiFi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>>>ResultadoBuscarListaPlanoParametrizacao>ListaBuscarListaPlanoParametrizacao>RetornoBuscarListaPlanoParametrizacao>ativaWiFi"));
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
