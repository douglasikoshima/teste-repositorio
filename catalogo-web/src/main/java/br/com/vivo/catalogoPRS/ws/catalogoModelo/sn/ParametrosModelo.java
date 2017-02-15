/**
 * ParametrosModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosModelo  implements java.io.Serializable {
    private java.lang.Long idModelo;

    private java.lang.String nmModelo;

    private java.lang.String indisponivel;

    private java.lang.Long idFabricante;

    private java.util.Calendar dtCriacao;

    private java.lang.Long idTipoProduto;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private java.lang.String inFimVida;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosModeloTecnologia[] tecnologia;

    private java.lang.String justificativa;

    private java.lang.String dsNota;

    private java.lang.Long idCorPadrao;

    public ParametrosModelo() {
    }

    public ParametrosModelo(
           java.lang.Long idModelo,
           java.lang.String nmModelo,
           java.lang.String indisponivel,
           java.lang.Long idFabricante,
           java.util.Calendar dtCriacao,
           java.lang.Long idTipoProduto,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           java.lang.String inFimVida,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosModeloTecnologia[] tecnologia,
           java.lang.String justificativa,
           java.lang.String dsNota,
           java.lang.Long idCorPadrao) {
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
           this.tecnologia = tecnologia;
           this.justificativa = justificativa;
           this.dsNota = dsNota;
           this.idCorPadrao = idCorPadrao;
    }


    /**
     * Gets the idModelo value for this ParametrosModelo.
     * 
     * @return idModelo
     */
    public java.lang.Long getIdModelo() {
        return idModelo;
    }


    /**
     * Sets the idModelo value for this ParametrosModelo.
     * 
     * @param idModelo
     */
    public void setIdModelo(java.lang.Long idModelo) {
        this.idModelo = idModelo;
    }


    /**
     * Gets the nmModelo value for this ParametrosModelo.
     * 
     * @return nmModelo
     */
    public java.lang.String getNmModelo() {
        return nmModelo;
    }


    /**
     * Sets the nmModelo value for this ParametrosModelo.
     * 
     * @param nmModelo
     */
    public void setNmModelo(java.lang.String nmModelo) {
        this.nmModelo = nmModelo;
    }


    /**
     * Gets the indisponivel value for this ParametrosModelo.
     * 
     * @return indisponivel
     */
    public java.lang.String getIndisponivel() {
        return indisponivel;
    }


    /**
     * Sets the indisponivel value for this ParametrosModelo.
     * 
     * @param indisponivel
     */
    public void setIndisponivel(java.lang.String indisponivel) {
        this.indisponivel = indisponivel;
    }


    /**
     * Gets the idFabricante value for this ParametrosModelo.
     * 
     * @return idFabricante
     */
    public java.lang.Long getIdFabricante() {
        return idFabricante;
    }


    /**
     * Sets the idFabricante value for this ParametrosModelo.
     * 
     * @param idFabricante
     */
    public void setIdFabricante(java.lang.Long idFabricante) {
        this.idFabricante = idFabricante;
    }


    /**
     * Gets the dtCriacao value for this ParametrosModelo.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ParametrosModelo.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the idTipoProduto value for this ParametrosModelo.
     * 
     * @return idTipoProduto
     */
    public java.lang.Long getIdTipoProduto() {
        return idTipoProduto;
    }


    /**
     * Sets the idTipoProduto value for this ParametrosModelo.
     * 
     * @param idTipoProduto
     */
    public void setIdTipoProduto(java.lang.Long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ParametrosModelo.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ParametrosModelo.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ParametrosModelo.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ParametrosModelo.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ParametrosModelo.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ParametrosModelo.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the inFimVida value for this ParametrosModelo.
     * 
     * @return inFimVida
     */
    public java.lang.String getInFimVida() {
        return inFimVida;
    }


    /**
     * Sets the inFimVida value for this ParametrosModelo.
     * 
     * @param inFimVida
     */
    public void setInFimVida(java.lang.String inFimVida) {
        this.inFimVida = inFimVida;
    }


    /**
     * Gets the tecnologia value for this ParametrosModelo.
     * 
     * @return tecnologia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosModeloTecnologia[] getTecnologia() {
        return tecnologia;
    }


    /**
     * Sets the tecnologia value for this ParametrosModelo.
     * 
     * @param tecnologia
     */
    public void setTecnologia(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosModeloTecnologia[] tecnologia) {
        this.tecnologia = tecnologia;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosModeloTecnologia getTecnologia(int i) {
        return this.tecnologia[i];
    }

    public void setTecnologia(int i, br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosModeloTecnologia _value) {
        this.tecnologia[i] = _value;
    }


    /**
     * Gets the justificativa value for this ParametrosModelo.
     * 
     * @return justificativa
     */
    public java.lang.String getJustificativa() {
        return justificativa;
    }


    /**
     * Sets the justificativa value for this ParametrosModelo.
     * 
     * @param justificativa
     */
    public void setJustificativa(java.lang.String justificativa) {
        this.justificativa = justificativa;
    }


    /**
     * Gets the dsNota value for this ParametrosModelo.
     * 
     * @return dsNota
     */
    public java.lang.String getDsNota() {
        return dsNota;
    }


    /**
     * Sets the dsNota value for this ParametrosModelo.
     * 
     * @param dsNota
     */
    public void setDsNota(java.lang.String dsNota) {
        this.dsNota = dsNota;
    }


    /**
     * Gets the idCorPadrao value for this ParametrosModelo.
     * 
     * @return idCorPadrao
     */
    public java.lang.Long getIdCorPadrao() {
        return idCorPadrao;
    }


    /**
     * Sets the idCorPadrao value for this ParametrosModelo.
     * 
     * @param idCorPadrao
     */
    public void setIdCorPadrao(java.lang.Long idCorPadrao) {
        this.idCorPadrao = idCorPadrao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosModelo)) return false;
        ParametrosModelo other = (ParametrosModelo) obj;
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
            ((this.indisponivel==null && other.getIndisponivel()==null) || 
             (this.indisponivel!=null &&
              this.indisponivel.equals(other.getIndisponivel()))) &&
            ((this.idFabricante==null && other.getIdFabricante()==null) || 
             (this.idFabricante!=null &&
              this.idFabricante.equals(other.getIdFabricante()))) &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            ((this.idTipoProduto==null && other.getIdTipoProduto()==null) || 
             (this.idTipoProduto!=null &&
              this.idTipoProduto.equals(other.getIdTipoProduto()))) &&
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
            ((this.tecnologia==null && other.getTecnologia()==null) || 
             (this.tecnologia!=null &&
              java.util.Arrays.equals(this.tecnologia, other.getTecnologia()))) &&
            ((this.justificativa==null && other.getJustificativa()==null) || 
             (this.justificativa!=null &&
              this.justificativa.equals(other.getJustificativa()))) &&
            ((this.dsNota==null && other.getDsNota()==null) || 
             (this.dsNota!=null &&
              this.dsNota.equals(other.getDsNota()))) &&
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
        if (getIndisponivel() != null) {
            _hashCode += getIndisponivel().hashCode();
        }
        if (getIdFabricante() != null) {
            _hashCode += getIdFabricante().hashCode();
        }
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        if (getIdTipoProduto() != null) {
            _hashCode += getIdTipoProduto().hashCode();
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
        if (getInFimVida() != null) {
            _hashCode += getInFimVida().hashCode();
        }
        if (getTecnologia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTecnologia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTecnologia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getJustificativa() != null) {
            _hashCode += getJustificativa().hashCode();
        }
        if (getDsNota() != null) {
            _hashCode += getDsNota().hashCode();
        }
        if (getIdCorPadrao() != null) {
            _hashCode += getIdCorPadrao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosModelo"));
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
        elemField.setFieldName("idTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
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
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "Tecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosModelo>Tecnologia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("justificativa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "justificativa"));
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
