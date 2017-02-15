/**
 * ResultadoBuscarListaServicoPendValidacaoListaServicoServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoBuscarListaServicoPendValidacaoListaServicoServico  implements java.io.Serializable {
    private java.lang.Long idServico;

    private java.lang.String nmTecnico;

    private java.lang.String nmComercial;

    private java.lang.Long qtdeMaxAtivacaoCatalogo;

    private java.lang.Long qtdeMinAtivacaoCatalogo;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPendValidacaoListaServicoServicoInDisponibilidadeCatalogo inDisponibilidadeCatalogo;

    public ResultadoBuscarListaServicoPendValidacaoListaServicoServico() {
    }

    public ResultadoBuscarListaServicoPendValidacaoListaServicoServico(
           java.lang.Long idServico,
           java.lang.String nmTecnico,
           java.lang.String nmComercial,
           java.lang.Long qtdeMaxAtivacaoCatalogo,
           java.lang.Long qtdeMinAtivacaoCatalogo,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPendValidacaoListaServicoServicoInDisponibilidadeCatalogo inDisponibilidadeCatalogo) {
           this.idServico = idServico;
           this.nmTecnico = nmTecnico;
           this.nmComercial = nmComercial;
           this.qtdeMaxAtivacaoCatalogo = qtdeMaxAtivacaoCatalogo;
           this.qtdeMinAtivacaoCatalogo = qtdeMinAtivacaoCatalogo;
           this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
    }


    /**
     * Gets the idServico value for this ResultadoBuscarListaServicoPendValidacaoListaServicoServico.
     * 
     * @return idServico
     */
    public java.lang.Long getIdServico() {
        return idServico;
    }


    /**
     * Sets the idServico value for this ResultadoBuscarListaServicoPendValidacaoListaServicoServico.
     * 
     * @param idServico
     */
    public void setIdServico(java.lang.Long idServico) {
        this.idServico = idServico;
    }


    /**
     * Gets the nmTecnico value for this ResultadoBuscarListaServicoPendValidacaoListaServicoServico.
     * 
     * @return nmTecnico
     */
    public java.lang.String getNmTecnico() {
        return nmTecnico;
    }


    /**
     * Sets the nmTecnico value for this ResultadoBuscarListaServicoPendValidacaoListaServicoServico.
     * 
     * @param nmTecnico
     */
    public void setNmTecnico(java.lang.String nmTecnico) {
        this.nmTecnico = nmTecnico;
    }


    /**
     * Gets the nmComercial value for this ResultadoBuscarListaServicoPendValidacaoListaServicoServico.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ResultadoBuscarListaServicoPendValidacaoListaServicoServico.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the qtdeMaxAtivacaoCatalogo value for this ResultadoBuscarListaServicoPendValidacaoListaServicoServico.
     * 
     * @return qtdeMaxAtivacaoCatalogo
     */
    public java.lang.Long getQtdeMaxAtivacaoCatalogo() {
        return qtdeMaxAtivacaoCatalogo;
    }


    /**
     * Sets the qtdeMaxAtivacaoCatalogo value for this ResultadoBuscarListaServicoPendValidacaoListaServicoServico.
     * 
     * @param qtdeMaxAtivacaoCatalogo
     */
    public void setQtdeMaxAtivacaoCatalogo(java.lang.Long qtdeMaxAtivacaoCatalogo) {
        this.qtdeMaxAtivacaoCatalogo = qtdeMaxAtivacaoCatalogo;
    }


    /**
     * Gets the qtdeMinAtivacaoCatalogo value for this ResultadoBuscarListaServicoPendValidacaoListaServicoServico.
     * 
     * @return qtdeMinAtivacaoCatalogo
     */
    public java.lang.Long getQtdeMinAtivacaoCatalogo() {
        return qtdeMinAtivacaoCatalogo;
    }


    /**
     * Sets the qtdeMinAtivacaoCatalogo value for this ResultadoBuscarListaServicoPendValidacaoListaServicoServico.
     * 
     * @param qtdeMinAtivacaoCatalogo
     */
    public void setQtdeMinAtivacaoCatalogo(java.lang.Long qtdeMinAtivacaoCatalogo) {
        this.qtdeMinAtivacaoCatalogo = qtdeMinAtivacaoCatalogo;
    }


    /**
     * Gets the inDisponibilidadeCatalogo value for this ResultadoBuscarListaServicoPendValidacaoListaServicoServico.
     * 
     * @return inDisponibilidadeCatalogo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPendValidacaoListaServicoServicoInDisponibilidadeCatalogo getInDisponibilidadeCatalogo() {
        return inDisponibilidadeCatalogo;
    }


    /**
     * Sets the inDisponibilidadeCatalogo value for this ResultadoBuscarListaServicoPendValidacaoListaServicoServico.
     * 
     * @param inDisponibilidadeCatalogo
     */
    public void setInDisponibilidadeCatalogo(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPendValidacaoListaServicoServicoInDisponibilidadeCatalogo inDisponibilidadeCatalogo) {
        this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaServicoPendValidacaoListaServicoServico)) return false;
        ResultadoBuscarListaServicoPendValidacaoListaServicoServico other = (ResultadoBuscarListaServicoPendValidacaoListaServicoServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idServico==null && other.getIdServico()==null) || 
             (this.idServico!=null &&
              this.idServico.equals(other.getIdServico()))) &&
            ((this.nmTecnico==null && other.getNmTecnico()==null) || 
             (this.nmTecnico!=null &&
              this.nmTecnico.equals(other.getNmTecnico()))) &&
            ((this.nmComercial==null && other.getNmComercial()==null) || 
             (this.nmComercial!=null &&
              this.nmComercial.equals(other.getNmComercial()))) &&
            ((this.qtdeMaxAtivacaoCatalogo==null && other.getQtdeMaxAtivacaoCatalogo()==null) || 
             (this.qtdeMaxAtivacaoCatalogo!=null &&
              this.qtdeMaxAtivacaoCatalogo.equals(other.getQtdeMaxAtivacaoCatalogo()))) &&
            ((this.qtdeMinAtivacaoCatalogo==null && other.getQtdeMinAtivacaoCatalogo()==null) || 
             (this.qtdeMinAtivacaoCatalogo!=null &&
              this.qtdeMinAtivacaoCatalogo.equals(other.getQtdeMinAtivacaoCatalogo()))) &&
            ((this.inDisponibilidadeCatalogo==null && other.getInDisponibilidadeCatalogo()==null) || 
             (this.inDisponibilidadeCatalogo!=null &&
              this.inDisponibilidadeCatalogo.equals(other.getInDisponibilidadeCatalogo())));
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
        if (getIdServico() != null) {
            _hashCode += getIdServico().hashCode();
        }
        if (getNmTecnico() != null) {
            _hashCode += getNmTecnico().hashCode();
        }
        if (getNmComercial() != null) {
            _hashCode += getNmComercial().hashCode();
        }
        if (getQtdeMaxAtivacaoCatalogo() != null) {
            _hashCode += getQtdeMaxAtivacaoCatalogo().hashCode();
        }
        if (getQtdeMinAtivacaoCatalogo() != null) {
            _hashCode += getQtdeMinAtivacaoCatalogo().hashCode();
        }
        if (getInDisponibilidadeCatalogo() != null) {
            _hashCode += getInDisponibilidadeCatalogo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaServicoPendValidacaoListaServicoServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>ResultadoBuscarListaServicoPendValidacao>ListaServico>Servico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTecnico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmTecnico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("qtdeMaxAtivacaoCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdeMaxAtivacaoCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdeMinAtivacaoCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdeMinAtivacaoCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponibilidadeCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "inDisponibilidadeCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>>ResultadoBuscarListaServicoPendValidacao>ListaServico>Servico>inDisponibilidadeCatalogo"));
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
