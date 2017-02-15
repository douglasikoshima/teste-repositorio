/**
 * BuscarDetalhesServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class BuscarDetalhesServicoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarDetalhesServico parametrosBuscarDetalhesServico;

    public BuscarDetalhesServicoRequest() {
    }

    public BuscarDetalhesServicoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarDetalhesServico parametrosBuscarDetalhesServico) {
           this.parametrosBuscarDetalhesServico = parametrosBuscarDetalhesServico;
    }


    /**
     * Gets the parametrosBuscarDetalhesServico value for this BuscarDetalhesServicoRequest.
     * 
     * @return parametrosBuscarDetalhesServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarDetalhesServico getParametrosBuscarDetalhesServico() {
        return parametrosBuscarDetalhesServico;
    }


    /**
     * Sets the parametrosBuscarDetalhesServico value for this BuscarDetalhesServicoRequest.
     * 
     * @param parametrosBuscarDetalhesServico
     */
    public void setParametrosBuscarDetalhesServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarDetalhesServico parametrosBuscarDetalhesServico) {
        this.parametrosBuscarDetalhesServico = parametrosBuscarDetalhesServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarDetalhesServicoRequest)) return false;
        BuscarDetalhesServicoRequest other = (BuscarDetalhesServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarDetalhesServico==null && other.getParametrosBuscarDetalhesServico()==null) || 
             (this.parametrosBuscarDetalhesServico!=null &&
              this.parametrosBuscarDetalhesServico.equals(other.getParametrosBuscarDetalhesServico())));
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
        if (getParametrosBuscarDetalhesServico() != null) {
            _hashCode += getParametrosBuscarDetalhesServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarDetalhesServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">buscarDetalhesServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarDetalhesServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ParametrosBuscarDetalhesServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosBuscarDetalhesServico"));
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
