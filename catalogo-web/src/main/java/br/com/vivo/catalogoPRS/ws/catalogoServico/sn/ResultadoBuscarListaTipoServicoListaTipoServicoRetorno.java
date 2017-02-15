/**
 * ResultadoBuscarListaTipoServicoListaTipoServicoRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoBuscarListaTipoServicoListaTipoServicoRetorno  implements java.io.Serializable {
    private long idTipoServico;

    private java.lang.String cdTipoServico;

    private java.lang.String dscTipoServico;

    public ResultadoBuscarListaTipoServicoListaTipoServicoRetorno() {
    }

    public ResultadoBuscarListaTipoServicoListaTipoServicoRetorno(
           long idTipoServico,
           java.lang.String cdTipoServico,
           java.lang.String dscTipoServico) {
           this.idTipoServico = idTipoServico;
           this.cdTipoServico = cdTipoServico;
           this.dscTipoServico = dscTipoServico;
    }


    /**
     * Gets the idTipoServico value for this ResultadoBuscarListaTipoServicoListaTipoServicoRetorno.
     * 
     * @return idTipoServico
     */
    public long getIdTipoServico() {
        return idTipoServico;
    }


    /**
     * Sets the idTipoServico value for this ResultadoBuscarListaTipoServicoListaTipoServicoRetorno.
     * 
     * @param idTipoServico
     */
    public void setIdTipoServico(long idTipoServico) {
        this.idTipoServico = idTipoServico;
    }


    /**
     * Gets the cdTipoServico value for this ResultadoBuscarListaTipoServicoListaTipoServicoRetorno.
     * 
     * @return cdTipoServico
     */
    public java.lang.String getCdTipoServico() {
        return cdTipoServico;
    }


    /**
     * Sets the cdTipoServico value for this ResultadoBuscarListaTipoServicoListaTipoServicoRetorno.
     * 
     * @param cdTipoServico
     */
    public void setCdTipoServico(java.lang.String cdTipoServico) {
        this.cdTipoServico = cdTipoServico;
    }


    /**
     * Gets the dscTipoServico value for this ResultadoBuscarListaTipoServicoListaTipoServicoRetorno.
     * 
     * @return dscTipoServico
     */
    public java.lang.String getDscTipoServico() {
        return dscTipoServico;
    }


    /**
     * Sets the dscTipoServico value for this ResultadoBuscarListaTipoServicoListaTipoServicoRetorno.
     * 
     * @param dscTipoServico
     */
    public void setDscTipoServico(java.lang.String dscTipoServico) {
        this.dscTipoServico = dscTipoServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaTipoServicoListaTipoServicoRetorno)) return false;
        ResultadoBuscarListaTipoServicoListaTipoServicoRetorno other = (ResultadoBuscarListaTipoServicoListaTipoServicoRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTipoServico == other.getIdTipoServico() &&
            ((this.cdTipoServico==null && other.getCdTipoServico()==null) || 
             (this.cdTipoServico!=null &&
              this.cdTipoServico.equals(other.getCdTipoServico()))) &&
            ((this.dscTipoServico==null && other.getDscTipoServico()==null) || 
             (this.dscTipoServico!=null &&
              this.dscTipoServico.equals(other.getDscTipoServico())));
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
        _hashCode += new Long(getIdTipoServico()).hashCode();
        if (getCdTipoServico() != null) {
            _hashCode += getCdTipoServico().hashCode();
        }
        if (getDscTipoServico() != null) {
            _hashCode += getDscTipoServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaTipoServicoListaTipoServicoRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>ResultadoBuscarListaTipoServico>ListaTipoServico>Retorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idTipoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdTipoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "cdTipoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dscTipoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "dscTipoServico"));
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
