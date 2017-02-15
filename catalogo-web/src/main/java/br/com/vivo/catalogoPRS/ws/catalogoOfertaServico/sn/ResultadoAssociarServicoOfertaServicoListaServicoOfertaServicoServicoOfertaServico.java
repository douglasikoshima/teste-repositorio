/**
 * ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico  implements java.io.Serializable {
    private java.lang.Long idServicoOfertaServico;

    public ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico() {
    }

    public ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico(
           java.lang.Long idServicoOfertaServico) {
           this.idServicoOfertaServico = idServicoOfertaServico;
    }


    /**
     * Gets the idServicoOfertaServico value for this ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico.
     * 
     * @return idServicoOfertaServico
     */
    public java.lang.Long getIdServicoOfertaServico() {
        return idServicoOfertaServico;
    }


    /**
     * Sets the idServicoOfertaServico value for this ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico.
     * 
     * @param idServicoOfertaServico
     */
    public void setIdServicoOfertaServico(java.lang.Long idServicoOfertaServico) {
        this.idServicoOfertaServico = idServicoOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico)) return false;
        ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico other = (ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idServicoOfertaServico==null && other.getIdServicoOfertaServico()==null) || 
             (this.idServicoOfertaServico!=null &&
              this.idServicoOfertaServico.equals(other.getIdServicoOfertaServico())));
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
        if (getIdServicoOfertaServico() != null) {
            _hashCode += getIdServicoOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>>ResultadoAssociarServicoOfertaServico>ListaServicoOfertaServico>ServicoOfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServicoOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "idServicoOfertaServico"));
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
