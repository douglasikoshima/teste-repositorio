/**
 * ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode  implements java.io.Serializable {
    private long idServico;

    private java.lang.String nmComercial;

    private java.lang.String cdTpServico;

    public ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode() {
    }

    public ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode(
           long idServico,
           java.lang.String nmComercial,
           java.lang.String cdTpServico) {
           this.idServico = idServico;
           this.nmComercial = nmComercial;
           this.cdTpServico = cdTpServico;
    }


    /**
     * Gets the idServico value for this ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode.
     * 
     * @return idServico
     */
    public long getIdServico() {
        return idServico;
    }


    /**
     * Sets the idServico value for this ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode.
     * 
     * @param idServico
     */
    public void setIdServico(long idServico) {
        this.idServico = idServico;
    }


    /**
     * Gets the nmComercial value for this ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the cdTpServico value for this ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode.
     * 
     * @return cdTpServico
     */
    public java.lang.String getCdTpServico() {
        return cdTpServico;
    }


    /**
     * Sets the cdTpServico value for this ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode.
     * 
     * @param cdTpServico
     */
    public void setCdTpServico(java.lang.String cdTpServico) {
        this.cdTpServico = cdTpServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode)) return false;
        ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode other = (ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idServico == other.getIdServico() &&
            ((this.nmComercial==null && other.getNmComercial()==null) || 
             (this.nmComercial!=null &&
              this.nmComercial.equals(other.getNmComercial()))) &&
            ((this.cdTpServico==null && other.getCdTpServico()==null) || 
             (this.cdTpServico!=null &&
              this.cdTpServico.equals(other.getCdTpServico())));
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
        _hashCode += new Long(getIdServico()).hashCode();
        if (getNmComercial() != null) {
            _hashCode += getNmComercial().hashCode();
        }
        if (getCdTpServico() != null) {
            _hashCode += getCdTpServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>ResultadoBuscarListaServicoPorSvTypeCode>ListaServicoPorSvTypeCode>ServicoPorSvTypeCode"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmComercial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmComercial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdTpServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "cdTpServico"));
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
