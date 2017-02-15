/**
 * ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta  implements java.io.Serializable {
    private long idServicoOfertaServico;

    private java.lang.String nmComercial;

    private java.lang.String nmCategoria;

    private java.lang.String dscTipoServico;

    private java.lang.Long rnum;

    public ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta() {
    }

    public ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta(
           long idServicoOfertaServico,
           java.lang.String nmComercial,
           java.lang.String nmCategoria,
           java.lang.String dscTipoServico,
           java.lang.Long rnum) {
           this.idServicoOfertaServico = idServicoOfertaServico;
           this.nmComercial = nmComercial;
           this.nmCategoria = nmCategoria;
           this.dscTipoServico = dscTipoServico;
           this.rnum = rnum;
    }


    /**
     * Gets the idServicoOfertaServico value for this ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta.
     * 
     * @return idServicoOfertaServico
     */
    public long getIdServicoOfertaServico() {
        return idServicoOfertaServico;
    }


    /**
     * Sets the idServicoOfertaServico value for this ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta.
     * 
     * @param idServicoOfertaServico
     */
    public void setIdServicoOfertaServico(long idServicoOfertaServico) {
        this.idServicoOfertaServico = idServicoOfertaServico;
    }


    /**
     * Gets the nmComercial value for this ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the nmCategoria value for this ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta.
     * 
     * @return nmCategoria
     */
    public java.lang.String getNmCategoria() {
        return nmCategoria;
    }


    /**
     * Sets the nmCategoria value for this ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta.
     * 
     * @param nmCategoria
     */
    public void setNmCategoria(java.lang.String nmCategoria) {
        this.nmCategoria = nmCategoria;
    }


    /**
     * Gets the dscTipoServico value for this ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta.
     * 
     * @return dscTipoServico
     */
    public java.lang.String getDscTipoServico() {
        return dscTipoServico;
    }


    /**
     * Sets the dscTipoServico value for this ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta.
     * 
     * @param dscTipoServico
     */
    public void setDscTipoServico(java.lang.String dscTipoServico) {
        this.dscTipoServico = dscTipoServico;
    }


    /**
     * Gets the rnum value for this ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta.
     * 
     * @return rnum
     */
    public java.lang.Long getRnum() {
        return rnum;
    }


    /**
     * Sets the rnum value for this ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta.
     * 
     * @param rnum
     */
    public void setRnum(java.lang.Long rnum) {
        this.rnum = rnum;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta)) return false;
        ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta other = (ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idServicoOfertaServico == other.getIdServicoOfertaServico() &&
            ((this.nmComercial==null && other.getNmComercial()==null) || 
             (this.nmComercial!=null &&
              this.nmComercial.equals(other.getNmComercial()))) &&
            ((this.nmCategoria==null && other.getNmCategoria()==null) || 
             (this.nmCategoria!=null &&
              this.nmCategoria.equals(other.getNmCategoria()))) &&
            ((this.dscTipoServico==null && other.getDscTipoServico()==null) || 
             (this.dscTipoServico!=null &&
              this.dscTipoServico.equals(other.getDscTipoServico()))) &&
            ((this.rnum==null && other.getRnum()==null) || 
             (this.rnum!=null &&
              this.rnum.equals(other.getRnum())));
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
        _hashCode += new Long(getIdServicoOfertaServico()).hashCode();
        if (getNmComercial() != null) {
            _hashCode += getNmComercial().hashCode();
        }
        if (getNmCategoria() != null) {
            _hashCode += getNmCategoria().hashCode();
        }
        if (getDscTipoServico() != null) {
            _hashCode += getDscTipoServico().hashCode();
        }
        if (getRnum() != null) {
            _hashCode += getRnum().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>>ResultadoBuscarListaServicoComOferta>ListaServicoComOferta>ServicoComOferta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServicoOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "idServicoOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmComercial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "nmComercial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCategoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "nmCategoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dscTipoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "dscTipoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rnum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "rnum"));
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
