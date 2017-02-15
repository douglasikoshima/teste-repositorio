/**
 * BuscarListaOfertaServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class BuscarListaOfertaServicoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosBuscarListaOfertaServico parametrosBuscarListaOfertaServico;

    public BuscarListaOfertaServicoRequest() {
    }

    public BuscarListaOfertaServicoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosBuscarListaOfertaServico parametrosBuscarListaOfertaServico) {
           this.parametrosBuscarListaOfertaServico = parametrosBuscarListaOfertaServico;
    }


    /**
     * Gets the parametrosBuscarListaOfertaServico value for this BuscarListaOfertaServicoRequest.
     * 
     * @return parametrosBuscarListaOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosBuscarListaOfertaServico getParametrosBuscarListaOfertaServico() {
        return parametrosBuscarListaOfertaServico;
    }


    /**
     * Sets the parametrosBuscarListaOfertaServico value for this BuscarListaOfertaServicoRequest.
     * 
     * @param parametrosBuscarListaOfertaServico
     */
    public void setParametrosBuscarListaOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosBuscarListaOfertaServico parametrosBuscarListaOfertaServico) {
        this.parametrosBuscarListaOfertaServico = parametrosBuscarListaOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaOfertaServicoRequest)) return false;
        BuscarListaOfertaServicoRequest other = (BuscarListaOfertaServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaOfertaServico==null && other.getParametrosBuscarListaOfertaServico()==null) || 
             (this.parametrosBuscarListaOfertaServico!=null &&
              this.parametrosBuscarListaOfertaServico.equals(other.getParametrosBuscarListaOfertaServico())));
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
        if (getParametrosBuscarListaOfertaServico() != null) {
            _hashCode += getParametrosBuscarListaOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaOfertaServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">buscarListaOfertaServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ParametrosBuscarListaOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ParametrosBuscarListaOfertaServico"));
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
