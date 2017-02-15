/**
 * ListaCaracteristicaCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class ListaCaracteristicaCaracteristica  implements java.io.Serializable {
    private long idCaracteristica;

    private java.lang.String nmCaracteristica;

    private java.lang.String inSimulador;

    private java.lang.String inDisponivel;

    private java.lang.String descricao;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private java.lang.Long idGrupoCaracteristica;

    private java.lang.String indComparavel;

    private java.lang.String indExibivel;

    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ListaCaracteristicaCaracteristicaListaValorCaracteristicaValorCaracteristica[] listaValorCaracteristica;

    public ListaCaracteristicaCaracteristica() {
    }

    public ListaCaracteristicaCaracteristica(
           long idCaracteristica,
           java.lang.String nmCaracteristica,
           java.lang.String inSimulador,
           java.lang.String inDisponivel,
           java.lang.String descricao,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           java.lang.Long idGrupoCaracteristica,
           java.lang.String indComparavel,
           java.lang.String indExibivel,
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ListaCaracteristicaCaracteristicaListaValorCaracteristicaValorCaracteristica[] listaValorCaracteristica) {
           this.idCaracteristica = idCaracteristica;
           this.nmCaracteristica = nmCaracteristica;
           this.inSimulador = inSimulador;
           this.inDisponivel = inDisponivel;
           this.descricao = descricao;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.idGrupoCaracteristica = idGrupoCaracteristica;
           this.indComparavel = indComparavel;
           this.indExibivel = indExibivel;
           this.listaValorCaracteristica = listaValorCaracteristica;
    }


    /**
     * Gets the idCaracteristica value for this ListaCaracteristicaCaracteristica.
     * 
     * @return idCaracteristica
     */
    public long getIdCaracteristica() {
        return idCaracteristica;
    }


    /**
     * Sets the idCaracteristica value for this ListaCaracteristicaCaracteristica.
     * 
     * @param idCaracteristica
     */
    public void setIdCaracteristica(long idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }


    /**
     * Gets the nmCaracteristica value for this ListaCaracteristicaCaracteristica.
     * 
     * @return nmCaracteristica
     */
    public java.lang.String getNmCaracteristica() {
        return nmCaracteristica;
    }


    /**
     * Sets the nmCaracteristica value for this ListaCaracteristicaCaracteristica.
     * 
     * @param nmCaracteristica
     */
    public void setNmCaracteristica(java.lang.String nmCaracteristica) {
        this.nmCaracteristica = nmCaracteristica;
    }


    /**
     * Gets the inSimulador value for this ListaCaracteristicaCaracteristica.
     * 
     * @return inSimulador
     */
    public java.lang.String getInSimulador() {
        return inSimulador;
    }


    /**
     * Sets the inSimulador value for this ListaCaracteristicaCaracteristica.
     * 
     * @param inSimulador
     */
    public void setInSimulador(java.lang.String inSimulador) {
        this.inSimulador = inSimulador;
    }


    /**
     * Gets the inDisponivel value for this ListaCaracteristicaCaracteristica.
     * 
     * @return inDisponivel
     */
    public java.lang.String getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ListaCaracteristicaCaracteristica.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(java.lang.String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the descricao value for this ListaCaracteristicaCaracteristica.
     * 
     * @return descricao
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this ListaCaracteristicaCaracteristica.
     * 
     * @param descricao
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }


    /**
     * Gets the dtCriacao value for this ListaCaracteristicaCaracteristica.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ListaCaracteristicaCaracteristica.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ListaCaracteristicaCaracteristica.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ListaCaracteristicaCaracteristica.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ListaCaracteristicaCaracteristica.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ListaCaracteristicaCaracteristica.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ListaCaracteristicaCaracteristica.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ListaCaracteristicaCaracteristica.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the idGrupoCaracteristica value for this ListaCaracteristicaCaracteristica.
     * 
     * @return idGrupoCaracteristica
     */
    public java.lang.Long getIdGrupoCaracteristica() {
        return idGrupoCaracteristica;
    }


    /**
     * Sets the idGrupoCaracteristica value for this ListaCaracteristicaCaracteristica.
     * 
     * @param idGrupoCaracteristica
     */
    public void setIdGrupoCaracteristica(java.lang.Long idGrupoCaracteristica) {
        this.idGrupoCaracteristica = idGrupoCaracteristica;
    }


    /**
     * Gets the indComparavel value for this ListaCaracteristicaCaracteristica.
     * 
     * @return indComparavel
     */
    public java.lang.String getIndComparavel() {
        return indComparavel;
    }


    /**
     * Sets the indComparavel value for this ListaCaracteristicaCaracteristica.
     * 
     * @param indComparavel
     */
    public void setIndComparavel(java.lang.String indComparavel) {
        this.indComparavel = indComparavel;
    }


    /**
     * Gets the indExibivel value for this ListaCaracteristicaCaracteristica.
     * 
     * @return indExibivel
     */
    public java.lang.String getIndExibivel() {
        return indExibivel;
    }


    /**
     * Sets the indExibivel value for this ListaCaracteristicaCaracteristica.
     * 
     * @param indExibivel
     */
    public void setIndExibivel(java.lang.String indExibivel) {
        this.indExibivel = indExibivel;
    }


    /**
     * Gets the listaValorCaracteristica value for this ListaCaracteristicaCaracteristica.
     * 
     * @return listaValorCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ListaCaracteristicaCaracteristicaListaValorCaracteristicaValorCaracteristica[] getListaValorCaracteristica() {
        return listaValorCaracteristica;
    }


    /**
     * Sets the listaValorCaracteristica value for this ListaCaracteristicaCaracteristica.
     * 
     * @param listaValorCaracteristica
     */
    public void setListaValorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ListaCaracteristicaCaracteristicaListaValorCaracteristicaValorCaracteristica[] listaValorCaracteristica) {
        this.listaValorCaracteristica = listaValorCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaCaracteristicaCaracteristica)) return false;
        ListaCaracteristicaCaracteristica other = (ListaCaracteristicaCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idCaracteristica == other.getIdCaracteristica() &&
            ((this.nmCaracteristica==null && other.getNmCaracteristica()==null) || 
             (this.nmCaracteristica!=null &&
              this.nmCaracteristica.equals(other.getNmCaracteristica()))) &&
            ((this.inSimulador==null && other.getInSimulador()==null) || 
             (this.inSimulador!=null &&
              this.inSimulador.equals(other.getInSimulador()))) &&
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel()))) &&
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
            ((this.idGrupoCaracteristica==null && other.getIdGrupoCaracteristica()==null) || 
             (this.idGrupoCaracteristica!=null &&
              this.idGrupoCaracteristica.equals(other.getIdGrupoCaracteristica()))) &&
            ((this.indComparavel==null && other.getIndComparavel()==null) || 
             (this.indComparavel!=null &&
              this.indComparavel.equals(other.getIndComparavel()))) &&
            ((this.indExibivel==null && other.getIndExibivel()==null) || 
             (this.indExibivel!=null &&
              this.indExibivel.equals(other.getIndExibivel()))) &&
            ((this.listaValorCaracteristica==null && other.getListaValorCaracteristica()==null) || 
             (this.listaValorCaracteristica!=null &&
              java.util.Arrays.equals(this.listaValorCaracteristica, other.getListaValorCaracteristica())));
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
        _hashCode += new Long(getIdCaracteristica()).hashCode();
        if (getNmCaracteristica() != null) {
            _hashCode += getNmCaracteristica().hashCode();
        }
        if (getInSimulador() != null) {
            _hashCode += getInSimulador().hashCode();
        }
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
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
        if (getIdGrupoCaracteristica() != null) {
            _hashCode += getIdGrupoCaracteristica().hashCode();
        }
        if (getIndComparavel() != null) {
            _hashCode += getIndComparavel().hashCode();
        }
        if (getIndExibivel() != null) {
            _hashCode += getIndExibivel().hashCode();
        }
        if (getListaValorCaracteristica() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaValorCaracteristica());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaValorCaracteristica(), i);
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
        new org.apache.axis.description.TypeDesc(ListaCaracteristicaCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">>ListaCaracteristica>Caracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "IdCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "nmCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inSimulador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "inSimulador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "inDisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "descricao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "nmUsuarioCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "idGrupoCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indComparavel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "indComparavel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indExibivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "indExibivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaValorCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ListaValorCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">>>>ListaCaracteristica>Caracteristica>ListaValorCaracteristica>ValorCaracteristica"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ValorCaracteristica"));
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
