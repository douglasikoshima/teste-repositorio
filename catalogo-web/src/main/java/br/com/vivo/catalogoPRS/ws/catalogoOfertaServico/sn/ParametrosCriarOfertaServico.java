/**
 * ParametrosCriarOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ParametrosCriarOfertaServico  implements java.io.Serializable {
    private java.lang.String cdOfertaServico;

    private java.lang.String nmOfertaServico;

    private java.lang.String dsNota;

    public ParametrosCriarOfertaServico() {
    }

    public ParametrosCriarOfertaServico(
           java.lang.String cdOfertaServico,
           java.lang.String nmOfertaServico,
           java.lang.String dsNota) {
           this.cdOfertaServico = cdOfertaServico;
           this.nmOfertaServico = nmOfertaServico;
           this.dsNota = dsNota;
    }


    /**
     * Gets the cdOfertaServico value for this ParametrosCriarOfertaServico.
     * 
     * @return cdOfertaServico
     */
    public java.lang.String getCdOfertaServico() {
        return cdOfertaServico;
    }


    /**
     * Sets the cdOfertaServico value for this ParametrosCriarOfertaServico.
     * 
     * @param cdOfertaServico
     */
    public void setCdOfertaServico(java.lang.String cdOfertaServico) {
        this.cdOfertaServico = cdOfertaServico;
    }


    /**
     * Gets the nmOfertaServico value for this ParametrosCriarOfertaServico.
     * 
     * @return nmOfertaServico
     */
    public java.lang.String getNmOfertaServico() {
        return nmOfertaServico;
    }


    /**
     * Sets the nmOfertaServico value for this ParametrosCriarOfertaServico.
     * 
     * @param nmOfertaServico
     */
    public void setNmOfertaServico(java.lang.String nmOfertaServico) {
        this.nmOfertaServico = nmOfertaServico;
    }


    /**
     * Gets the dsNota value for this ParametrosCriarOfertaServico.
     * 
     * @return dsNota
     */
    public java.lang.String getDsNota() {
        return dsNota;
    }


    /**
     * Sets the dsNota value for this ParametrosCriarOfertaServico.
     * 
     * @param dsNota
     */
    public void setDsNota(java.lang.String dsNota) {
        this.dsNota = dsNota;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosCriarOfertaServico)) return false;
        ParametrosCriarOfertaServico other = (ParametrosCriarOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cdOfertaServico==null && other.getCdOfertaServico()==null) || 
             (this.cdOfertaServico!=null &&
              this.cdOfertaServico.equals(other.getCdOfertaServico()))) &&
            ((this.nmOfertaServico==null && other.getNmOfertaServico()==null) || 
             (this.nmOfertaServico!=null &&
              this.nmOfertaServico.equals(other.getNmOfertaServico()))) &&
            ((this.dsNota==null && other.getDsNota()==null) || 
             (this.dsNota!=null &&
              this.dsNota.equals(other.getDsNota())));
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
        if (getCdOfertaServico() != null) {
            _hashCode += getCdOfertaServico().hashCode();
        }
        if (getNmOfertaServico() != null) {
            _hashCode += getNmOfertaServico().hashCode();
        }
        if (getDsNota() != null) {
            _hashCode += getDsNota().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosCriarOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ParametrosCriarOfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "cdOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "nmOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsNota");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "dsNota"));
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
