/**
 * ListaModelosPorIdModeloOutModeloPorIdModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ListaModelosPorIdModeloOutModeloPorIdModelo  implements java.io.Serializable {
    private java.lang.Long idModelo;

    private java.lang.String nmModelo;

    private java.lang.String inDisponivel;

    private long idFabricante;

    private java.util.Calendar dtCriacao;

    private long idTipoProduto;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private java.lang.String inFimVida;

    private long qtdProdutosAfetados;

    private java.lang.String inCaracteristica;

    private java.lang.String inMultimidia;

    private java.lang.String dsNota;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaModelosPorIdModeloOutModeloPorIdModeloLink link;

    private java.lang.Long idCorPadrao;

    public ListaModelosPorIdModeloOutModeloPorIdModelo() {
    }

    public ListaModelosPorIdModeloOutModeloPorIdModelo(
           java.lang.Long idModelo,
           java.lang.String nmModelo,
           java.lang.String inDisponivel,
           long idFabricante,
           java.util.Calendar dtCriacao,
           long idTipoProduto,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           java.lang.String inFimVida,
           long qtdProdutosAfetados,
           java.lang.String inCaracteristica,
           java.lang.String inMultimidia,
           java.lang.String dsNota,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaModelosPorIdModeloOutModeloPorIdModeloLink link,
           java.lang.Long idCorPadrao) {
           this.idModelo = idModelo;
           this.nmModelo = nmModelo;
           this.inDisponivel = inDisponivel;
           this.idFabricante = idFabricante;
           this.dtCriacao = dtCriacao;
           this.idTipoProduto = idTipoProduto;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.inFimVida = inFimVida;
           this.qtdProdutosAfetados = qtdProdutosAfetados;
           this.inCaracteristica = inCaracteristica;
           this.inMultimidia = inMultimidia;
           this.dsNota = dsNota;
           this.link = link;
           this.idCorPadrao = idCorPadrao;
    }


    /**
     * Gets the idModelo value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return idModelo
     */
    public java.lang.Long getIdModelo() {
        return idModelo;
    }


    /**
     * Sets the idModelo value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param idModelo
     */
    public void setIdModelo(java.lang.Long idModelo) {
        this.idModelo = idModelo;
    }


    /**
     * Gets the nmModelo value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return nmModelo
     */
    public java.lang.String getNmModelo() {
        return nmModelo;
    }


    /**
     * Sets the nmModelo value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param nmModelo
     */
    public void setNmModelo(java.lang.String nmModelo) {
        this.nmModelo = nmModelo;
    }


    /**
     * Gets the inDisponivel value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return inDisponivel
     */
    public java.lang.String getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(java.lang.String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the idFabricante value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return idFabricante
     */
    public long getIdFabricante() {
        return idFabricante;
    }


    /**
     * Sets the idFabricante value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param idFabricante
     */
    public void setIdFabricante(long idFabricante) {
        this.idFabricante = idFabricante;
    }


    /**
     * Gets the dtCriacao value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the idTipoProduto value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return idTipoProduto
     */
    public long getIdTipoProduto() {
        return idTipoProduto;
    }


    /**
     * Sets the idTipoProduto value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param idTipoProduto
     */
    public void setIdTipoProduto(long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the inFimVida value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return inFimVida
     */
    public java.lang.String getInFimVida() {
        return inFimVida;
    }


    /**
     * Sets the inFimVida value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param inFimVida
     */
    public void setInFimVida(java.lang.String inFimVida) {
        this.inFimVida = inFimVida;
    }


    /**
     * Gets the qtdProdutosAfetados value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return qtdProdutosAfetados
     */
    public long getQtdProdutosAfetados() {
        return qtdProdutosAfetados;
    }


    /**
     * Sets the qtdProdutosAfetados value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param qtdProdutosAfetados
     */
    public void setQtdProdutosAfetados(long qtdProdutosAfetados) {
        this.qtdProdutosAfetados = qtdProdutosAfetados;
    }


    /**
     * Gets the inCaracteristica value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return inCaracteristica
     */
    public java.lang.String getInCaracteristica() {
        return inCaracteristica;
    }


    /**
     * Sets the inCaracteristica value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param inCaracteristica
     */
    public void setInCaracteristica(java.lang.String inCaracteristica) {
        this.inCaracteristica = inCaracteristica;
    }


    /**
     * Gets the inMultimidia value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return inMultimidia
     */
    public java.lang.String getInMultimidia() {
        return inMultimidia;
    }


    /**
     * Sets the inMultimidia value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param inMultimidia
     */
    public void setInMultimidia(java.lang.String inMultimidia) {
        this.inMultimidia = inMultimidia;
    }


    /**
     * Gets the dsNota value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return dsNota
     */
    public java.lang.String getDsNota() {
        return dsNota;
    }


    /**
     * Sets the dsNota value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param dsNota
     */
    public void setDsNota(java.lang.String dsNota) {
        this.dsNota = dsNota;
    }


    /**
     * Gets the link value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return link
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaModelosPorIdModeloOutModeloPorIdModeloLink getLink() {
        return link;
    }


    /**
     * Sets the link value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param link
     */
    public void setLink(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaModelosPorIdModeloOutModeloPorIdModeloLink link) {
        this.link = link;
    }


    /**
     * Gets the idCorPadrao value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @return idCorPadrao
     */
    public java.lang.Long getIdCorPadrao() {
        return idCorPadrao;
    }


    /**
     * Sets the idCorPadrao value for this ListaModelosPorIdModeloOutModeloPorIdModelo.
     * 
     * @param idCorPadrao
     */
    public void setIdCorPadrao(java.lang.Long idCorPadrao) {
        this.idCorPadrao = idCorPadrao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaModelosPorIdModeloOutModeloPorIdModelo)) return false;
        ListaModelosPorIdModeloOutModeloPorIdModelo other = (ListaModelosPorIdModeloOutModeloPorIdModelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idModelo==null && other.getIdModelo()==null) || 
             (this.idModelo!=null &&
              this.idModelo.equals(other.getIdModelo()))) &&
            ((this.nmModelo==null && other.getNmModelo()==null) || 
             (this.nmModelo!=null &&
              this.nmModelo.equals(other.getNmModelo()))) &&
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel()))) &&
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
            this.qtdProdutosAfetados == other.getQtdProdutosAfetados() &&
            ((this.inCaracteristica==null && other.getInCaracteristica()==null) || 
             (this.inCaracteristica!=null &&
              this.inCaracteristica.equals(other.getInCaracteristica()))) &&
            ((this.inMultimidia==null && other.getInMultimidia()==null) || 
             (this.inMultimidia!=null &&
              this.inMultimidia.equals(other.getInMultimidia()))) &&
            ((this.dsNota==null && other.getDsNota()==null) || 
             (this.dsNota!=null &&
              this.dsNota.equals(other.getDsNota()))) &&
            ((this.link==null && other.getLink()==null) || 
             (this.link!=null &&
              this.link.equals(other.getLink()))) &&
            ((this.idCorPadrao==null && other.getIdCorPadrao()==null) || 
             (this.idCorPadrao!=null &&
              this.idCorPadrao.equals(other.getIdCorPadrao())));
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
        if (getIdModelo() != null) {
            _hashCode += getIdModelo().hashCode();
        }
        if (getNmModelo() != null) {
            _hashCode += getNmModelo().hashCode();
        }
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
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
        _hashCode += new Long(getQtdProdutosAfetados()).hashCode();
        if (getInCaracteristica() != null) {
            _hashCode += getInCaracteristica().hashCode();
        }
        if (getInMultimidia() != null) {
            _hashCode += getInMultimidia().hashCode();
        }
        if (getDsNota() != null) {
            _hashCode += getDsNota().hashCode();
        }
        if (getLink() != null) {
            _hashCode += getLink().hashCode();
        }
        if (getIdCorPadrao() != null) {
            _hashCode += getIdCorPadrao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaModelosPorIdModeloOutModeloPorIdModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ListaModelosPorIdModeloOut>ModeloPorIdModelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "inDisponivel"));
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
        elemField.setFieldName("qtdProdutosAfetados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "qtdProdutosAfetados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "inCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "inMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsNota");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "dsNota"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("link");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "Link"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>ListaModelosPorIdModeloOut>ModeloPorIdModelo>Link"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCorPadrao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idCorPadrao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
