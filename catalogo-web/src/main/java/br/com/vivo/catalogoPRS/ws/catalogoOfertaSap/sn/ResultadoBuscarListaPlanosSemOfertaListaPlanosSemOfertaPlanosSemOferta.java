/**
 * ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta  implements java.io.Serializable {
    private long idSistemaPlano;

    private long idPlano;

    private java.lang.String nmComercial;

    private java.lang.String nmTecnico;

    private java.util.Date dtInicial;

    private java.util.Date dtFinal;

    private java.lang.String inDisponivel;

    public ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta() {
    }

    public ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta(
           long idSistemaPlano,
           long idPlano,
           java.lang.String nmComercial,
           java.lang.String nmTecnico,
           java.util.Date dtInicial,
           java.util.Date dtFinal,
           java.lang.String inDisponivel) {
           this.idSistemaPlano = idSistemaPlano;
           this.idPlano = idPlano;
           this.nmComercial = nmComercial;
           this.nmTecnico = nmTecnico;
           this.dtInicial = dtInicial;
           this.dtFinal = dtFinal;
           this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the idSistemaPlano value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @return idSistemaPlano
     */
    public long getIdSistemaPlano() {
        return idSistemaPlano;
    }


    /**
     * Sets the idSistemaPlano value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @param idSistemaPlano
     */
    public void setIdSistemaPlano(long idSistemaPlano) {
        this.idSistemaPlano = idSistemaPlano;
    }


    /**
     * Gets the idPlano value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @return idPlano
     */
    public long getIdPlano() {
        return idPlano;
    }


    /**
     * Sets the idPlano value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @param idPlano
     */
    public void setIdPlano(long idPlano) {
        this.idPlano = idPlano;
    }


    /**
     * Gets the nmComercial value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the nmTecnico value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @return nmTecnico
     */
    public java.lang.String getNmTecnico() {
        return nmTecnico;
    }


    /**
     * Sets the nmTecnico value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @param nmTecnico
     */
    public void setNmTecnico(java.lang.String nmTecnico) {
        this.nmTecnico = nmTecnico;
    }


    /**
     * Gets the dtInicial value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @return dtInicial
     */
    public java.util.Date getDtInicial() {
        return dtInicial;
    }


    /**
     * Sets the dtInicial value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @param dtInicial
     */
    public void setDtInicial(java.util.Date dtInicial) {
        this.dtInicial = dtInicial;
    }


    /**
     * Gets the dtFinal value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @return dtFinal
     */
    public java.util.Date getDtFinal() {
        return dtFinal;
    }


    /**
     * Sets the dtFinal value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @param dtFinal
     */
    public void setDtFinal(java.util.Date dtFinal) {
        this.dtFinal = dtFinal;
    }


    /**
     * Gets the inDisponivel value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @return inDisponivel
     */
    public java.lang.String getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(java.lang.String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta)) return false;
        ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta other = (ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idSistemaPlano == other.getIdSistemaPlano() &&
            this.idPlano == other.getIdPlano() &&
            ((this.nmComercial==null && other.getNmComercial()==null) || 
             (this.nmComercial!=null &&
              this.nmComercial.equals(other.getNmComercial()))) &&
            ((this.nmTecnico==null && other.getNmTecnico()==null) || 
             (this.nmTecnico!=null &&
              this.nmTecnico.equals(other.getNmTecnico()))) &&
            ((this.dtInicial==null && other.getDtInicial()==null) || 
             (this.dtInicial!=null &&
              this.dtInicial.equals(other.getDtInicial()))) &&
            ((this.dtFinal==null && other.getDtFinal()==null) || 
             (this.dtFinal!=null &&
              this.dtFinal.equals(other.getDtFinal()))) &&
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel())));
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
        _hashCode += new Long(getIdSistemaPlano()).hashCode();
        _hashCode += new Long(getIdPlano()).hashCode();
        if (getNmComercial() != null) {
            _hashCode += getNmComercial().hashCode();
        }
        if (getNmTecnico() != null) {
            _hashCode += getNmTecnico().hashCode();
        }
        if (getDtInicial() != null) {
            _hashCode += getDtInicial().hashCode();
        }
        if (getDtFinal() != null) {
            _hashCode += getDtFinal().hashCode();
        }
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">>>ResultadoBuscarListaPlanosSemOferta>ListaPlanosSemOferta>PlanosSemOferta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSistemaPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "idSistemaPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "idPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmComercial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "nmComercial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTecnico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "nmTecnico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "dtInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "dtFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "inDisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
