/**
 * BuscarDetalhesServicoPorIdPlanoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class BuscarDetalhesServicoPorIdPlanoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarDetalhesServicoPorIdPlano parametrosBuscarDetalhesServicoPorIdPlano;

    public BuscarDetalhesServicoPorIdPlanoRequest() {
    }

    public BuscarDetalhesServicoPorIdPlanoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarDetalhesServicoPorIdPlano parametrosBuscarDetalhesServicoPorIdPlano) {
           this.parametrosBuscarDetalhesServicoPorIdPlano = parametrosBuscarDetalhesServicoPorIdPlano;
    }


    /**
     * Gets the parametrosBuscarDetalhesServicoPorIdPlano value for this BuscarDetalhesServicoPorIdPlanoRequest.
     * 
     * @return parametrosBuscarDetalhesServicoPorIdPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarDetalhesServicoPorIdPlano getParametrosBuscarDetalhesServicoPorIdPlano() {
        return parametrosBuscarDetalhesServicoPorIdPlano;
    }


    /**
     * Sets the parametrosBuscarDetalhesServicoPorIdPlano value for this BuscarDetalhesServicoPorIdPlanoRequest.
     * 
     * @param parametrosBuscarDetalhesServicoPorIdPlano
     */
    public void setParametrosBuscarDetalhesServicoPorIdPlano(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarDetalhesServicoPorIdPlano parametrosBuscarDetalhesServicoPorIdPlano) {
        this.parametrosBuscarDetalhesServicoPorIdPlano = parametrosBuscarDetalhesServicoPorIdPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarDetalhesServicoPorIdPlanoRequest)) return false;
        BuscarDetalhesServicoPorIdPlanoRequest other = (BuscarDetalhesServicoPorIdPlanoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarDetalhesServicoPorIdPlano==null && other.getParametrosBuscarDetalhesServicoPorIdPlano()==null) || 
             (this.parametrosBuscarDetalhesServicoPorIdPlano!=null &&
              this.parametrosBuscarDetalhesServicoPorIdPlano.equals(other.getParametrosBuscarDetalhesServicoPorIdPlano())));
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
        if (getParametrosBuscarDetalhesServicoPorIdPlano() != null) {
            _hashCode += getParametrosBuscarDetalhesServicoPorIdPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarDetalhesServicoPorIdPlanoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">buscarDetalhesServicoPorIdPlanoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarDetalhesServicoPorIdPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ParametrosBuscarDetalhesServicoPorIdPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosBuscarDetalhesServicoPorIdPlano"));
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
