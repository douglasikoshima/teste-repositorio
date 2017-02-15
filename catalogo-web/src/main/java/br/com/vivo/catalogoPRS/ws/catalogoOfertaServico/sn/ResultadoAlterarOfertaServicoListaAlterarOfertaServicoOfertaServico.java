/**
 * ResultadoAlterarOfertaServicoListaAlterarOfertaServicoOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoAlterarOfertaServicoListaAlterarOfertaServicoOfertaServico  implements java.io.Serializable {
    private java.lang.Long idOfertaServico;

    public ResultadoAlterarOfertaServicoListaAlterarOfertaServicoOfertaServico() {
    }

    public ResultadoAlterarOfertaServicoListaAlterarOfertaServicoOfertaServico(
           java.lang.Long idOfertaServico) {
           this.idOfertaServico = idOfertaServico;
    }


    /**
     * Gets the idOfertaServico value for this ResultadoAlterarOfertaServicoListaAlterarOfertaServicoOfertaServico.
     * 
     * @return idOfertaServico
     */
    public java.lang.Long getIdOfertaServico() {
        return idOfertaServico;
    }


    /**
     * Sets the idOfertaServico value for this ResultadoAlterarOfertaServicoListaAlterarOfertaServicoOfertaServico.
     * 
     * @param idOfertaServico
     */
    public void setIdOfertaServico(java.lang.Long idOfertaServico) {
        this.idOfertaServico = idOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoAlterarOfertaServicoListaAlterarOfertaServicoOfertaServico)) return false;
        ResultadoAlterarOfertaServicoListaAlterarOfertaServicoOfertaServico other = (ResultadoAlterarOfertaServicoListaAlterarOfertaServicoOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idOfertaServico==null && other.getIdOfertaServico()==null) || 
             (this.idOfertaServico!=null &&
              this.idOfertaServico.equals(other.getIdOfertaServico())));
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
        if (getIdOfertaServico() != null) {
            _hashCode += getIdOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoAlterarOfertaServicoListaAlterarOfertaServicoOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>>ResultadoAlterarOfertaServico>ListaAlterarOfertaServico>OfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "idOfertaServico"));
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
