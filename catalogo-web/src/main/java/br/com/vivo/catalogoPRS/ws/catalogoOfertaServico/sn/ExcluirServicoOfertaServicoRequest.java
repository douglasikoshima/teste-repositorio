/**
 * ExcluirServicoOfertaServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ExcluirServicoOfertaServicoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosExcluirServicoOfertaServico parametrosExcluirServicoOfertaServico;

    public ExcluirServicoOfertaServicoRequest() {
    }

    public ExcluirServicoOfertaServicoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosExcluirServicoOfertaServico parametrosExcluirServicoOfertaServico) {
           this.parametrosExcluirServicoOfertaServico = parametrosExcluirServicoOfertaServico;
    }


    /**
     * Gets the parametrosExcluirServicoOfertaServico value for this ExcluirServicoOfertaServicoRequest.
     * 
     * @return parametrosExcluirServicoOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosExcluirServicoOfertaServico getParametrosExcluirServicoOfertaServico() {
        return parametrosExcluirServicoOfertaServico;
    }


    /**
     * Sets the parametrosExcluirServicoOfertaServico value for this ExcluirServicoOfertaServicoRequest.
     * 
     * @param parametrosExcluirServicoOfertaServico
     */
    public void setParametrosExcluirServicoOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosExcluirServicoOfertaServico parametrosExcluirServicoOfertaServico) {
        this.parametrosExcluirServicoOfertaServico = parametrosExcluirServicoOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExcluirServicoOfertaServicoRequest)) return false;
        ExcluirServicoOfertaServicoRequest other = (ExcluirServicoOfertaServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosExcluirServicoOfertaServico==null && other.getParametrosExcluirServicoOfertaServico()==null) || 
             (this.parametrosExcluirServicoOfertaServico!=null &&
              this.parametrosExcluirServicoOfertaServico.equals(other.getParametrosExcluirServicoOfertaServico())));
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
        if (getParametrosExcluirServicoOfertaServico() != null) {
            _hashCode += getParametrosExcluirServicoOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExcluirServicoOfertaServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">excluirServicoOfertaServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosExcluirServicoOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ParametrosExcluirServicoOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ParametrosExcluirServicoOfertaServico"));
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
