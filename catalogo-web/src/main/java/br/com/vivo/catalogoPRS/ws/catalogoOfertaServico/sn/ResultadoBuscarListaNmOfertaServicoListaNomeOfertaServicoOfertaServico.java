/**
 * ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico  implements java.io.Serializable {
    private long idOfertaServico;

    private java.lang.String cdOfertaServico;

    private java.lang.String nmOfertaServico;

    public ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico() {
    }

    public ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico(
           long idOfertaServico,
           java.lang.String cdOfertaServico,
           java.lang.String nmOfertaServico) {
           this.idOfertaServico = idOfertaServico;
           this.cdOfertaServico = cdOfertaServico;
           this.nmOfertaServico = nmOfertaServico;
    }


    /**
     * Gets the idOfertaServico value for this ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico.
     * 
     * @return idOfertaServico
     */
    public long getIdOfertaServico() {
        return idOfertaServico;
    }


    /**
     * Sets the idOfertaServico value for this ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico.
     * 
     * @param idOfertaServico
     */
    public void setIdOfertaServico(long idOfertaServico) {
        this.idOfertaServico = idOfertaServico;
    }


    /**
     * Gets the cdOfertaServico value for this ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico.
     * 
     * @return cdOfertaServico
     */
    public java.lang.String getCdOfertaServico() {
        return cdOfertaServico;
    }


    /**
     * Sets the cdOfertaServico value for this ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico.
     * 
     * @param cdOfertaServico
     */
    public void setCdOfertaServico(java.lang.String cdOfertaServico) {
        this.cdOfertaServico = cdOfertaServico;
    }


    /**
     * Gets the nmOfertaServico value for this ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico.
     * 
     * @return nmOfertaServico
     */
    public java.lang.String getNmOfertaServico() {
        return nmOfertaServico;
    }


    /**
     * Sets the nmOfertaServico value for this ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico.
     * 
     * @param nmOfertaServico
     */
    public void setNmOfertaServico(java.lang.String nmOfertaServico) {
        this.nmOfertaServico = nmOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico)) return false;
        ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico other = (ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idOfertaServico == other.getIdOfertaServico() &&
            ((this.cdOfertaServico==null && other.getCdOfertaServico()==null) || 
             (this.cdOfertaServico!=null &&
              this.cdOfertaServico.equals(other.getCdOfertaServico()))) &&
            ((this.nmOfertaServico==null && other.getNmOfertaServico()==null) || 
             (this.nmOfertaServico!=null &&
              this.nmOfertaServico.equals(other.getNmOfertaServico())));
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
        _hashCode += new Long(getIdOfertaServico()).hashCode();
        if (getCdOfertaServico() != null) {
            _hashCode += getCdOfertaServico().hashCode();
        }
        if (getNmOfertaServico() != null) {
            _hashCode += getNmOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>>ResultadoBuscarListaNmOfertaServico>ListaNomeOfertaServico>OfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "idOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
