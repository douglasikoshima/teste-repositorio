/**
 * ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano  implements java.io.Serializable {
    private long idPlano;

    private java.lang.String nmComercial;

    private java.lang.Long qtMaximaDependentes;

    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlanoInDisponibilidadeCatalogo inDisponibilidadeCatalogo;

    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlanoInTitDep inTitDep;

    private java.lang.String nmTecnico;

    public ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano() {
    }

    public ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano(
           long idPlano,
           java.lang.String nmComercial,
           java.lang.Long qtMaximaDependentes,
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlanoInDisponibilidadeCatalogo inDisponibilidadeCatalogo,
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlanoInTitDep inTitDep,
           java.lang.String nmTecnico) {
           this.idPlano = idPlano;
           this.nmComercial = nmComercial;
           this.qtMaximaDependentes = qtMaximaDependentes;
           this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
           this.inTitDep = inTitDep;
           this.nmTecnico = nmTecnico;
    }


    /**
     * Gets the idPlano value for this ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.
     * 
     * @return idPlano
     */
    public long getIdPlano() {
        return idPlano;
    }


    /**
     * Sets the idPlano value for this ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.
     * 
     * @param idPlano
     */
    public void setIdPlano(long idPlano) {
        this.idPlano = idPlano;
    }


    /**
     * Gets the nmComercial value for this ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the qtMaximaDependentes value for this ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.
     * 
     * @return qtMaximaDependentes
     */
    public java.lang.Long getQtMaximaDependentes() {
        return qtMaximaDependentes;
    }


    /**
     * Sets the qtMaximaDependentes value for this ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.
     * 
     * @param qtMaximaDependentes
     */
    public void setQtMaximaDependentes(java.lang.Long qtMaximaDependentes) {
        this.qtMaximaDependentes = qtMaximaDependentes;
    }


    /**
     * Gets the inDisponibilidadeCatalogo value for this ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.
     * 
     * @return inDisponibilidadeCatalogo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlanoInDisponibilidadeCatalogo getInDisponibilidadeCatalogo() {
        return inDisponibilidadeCatalogo;
    }


    /**
     * Sets the inDisponibilidadeCatalogo value for this ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.
     * 
     * @param inDisponibilidadeCatalogo
     */
    public void setInDisponibilidadeCatalogo(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlanoInDisponibilidadeCatalogo inDisponibilidadeCatalogo) {
        this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
    }


    /**
     * Gets the inTitDep value for this ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.
     * 
     * @return inTitDep
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlanoInTitDep getInTitDep() {
        return inTitDep;
    }


    /**
     * Sets the inTitDep value for this ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.
     * 
     * @param inTitDep
     */
    public void setInTitDep(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlanoInTitDep inTitDep) {
        this.inTitDep = inTitDep;
    }


    /**
     * Gets the nmTecnico value for this ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.
     * 
     * @return nmTecnico
     */
    public java.lang.String getNmTecnico() {
        return nmTecnico;
    }


    /**
     * Sets the nmTecnico value for this ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.
     * 
     * @param nmTecnico
     */
    public void setNmTecnico(java.lang.String nmTecnico) {
        this.nmTecnico = nmTecnico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano)) return false;
        ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano other = (ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano) obj;
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
            ((this.qtMaximaDependentes==null && other.getQtMaximaDependentes()==null) || 
             (this.qtMaximaDependentes!=null &&
              this.qtMaximaDependentes.equals(other.getQtMaximaDependentes()))) &&
            ((this.inDisponibilidadeCatalogo==null && other.getInDisponibilidadeCatalogo()==null) || 
             (this.inDisponibilidadeCatalogo!=null &&
              this.inDisponibilidadeCatalogo.equals(other.getInDisponibilidadeCatalogo()))) &&
            ((this.inTitDep==null && other.getInTitDep()==null) || 
             (this.inTitDep!=null &&
              this.inTitDep.equals(other.getInTitDep()))) &&
            ((this.nmTecnico==null && other.getNmTecnico()==null) || 
             (this.nmTecnico!=null &&
              this.nmTecnico.equals(other.getNmTecnico())));
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
        if (getQtMaximaDependentes() != null) {
            _hashCode += getQtMaximaDependentes().hashCode();
        }
        if (getInDisponibilidadeCatalogo() != null) {
            _hashCode += getInDisponibilidadeCatalogo().hashCode();
        }
        if (getInTitDep() != null) {
            _hashCode += getInTitDep().hashCode();
        }
        if (getNmTecnico() != null) {
            _hashCode += getNmTecnico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>>ResultadoBuscarListaPlanoPendValidacao>ListaPlano>RetornoPlano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("qtMaximaDependentes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "qtMaximaDependentes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponibilidadeCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "inDisponibilidadeCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>>>ResultadoBuscarListaPlanoPendValidacao>ListaPlano>RetornoPlano>inDisponibilidadeCatalogo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inTitDep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "inTitDep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>>>ResultadoBuscarListaPlanoPendValidacao>ListaPlano>RetornoPlano>inTitDep"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTecnico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "nmTecnico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
