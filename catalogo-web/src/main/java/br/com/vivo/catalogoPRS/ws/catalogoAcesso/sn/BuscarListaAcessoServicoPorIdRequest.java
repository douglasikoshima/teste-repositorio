/**
 * BuscarListaAcessoServicoPorIdRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class BuscarListaAcessoServicoPorIdRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoServicoPorId parametrosBuscarListaAcessoServicoPorId;

    public BuscarListaAcessoServicoPorIdRequest() {
    }

    public BuscarListaAcessoServicoPorIdRequest(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoServicoPorId parametrosBuscarListaAcessoServicoPorId) {
           this.parametrosBuscarListaAcessoServicoPorId = parametrosBuscarListaAcessoServicoPorId;
    }


    /**
     * Gets the parametrosBuscarListaAcessoServicoPorId value for this BuscarListaAcessoServicoPorIdRequest.
     * 
     * @return parametrosBuscarListaAcessoServicoPorId
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoServicoPorId getParametrosBuscarListaAcessoServicoPorId() {
        return parametrosBuscarListaAcessoServicoPorId;
    }


    /**
     * Sets the parametrosBuscarListaAcessoServicoPorId value for this BuscarListaAcessoServicoPorIdRequest.
     * 
     * @param parametrosBuscarListaAcessoServicoPorId
     */
    public void setParametrosBuscarListaAcessoServicoPorId(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoServicoPorId parametrosBuscarListaAcessoServicoPorId) {
        this.parametrosBuscarListaAcessoServicoPorId = parametrosBuscarListaAcessoServicoPorId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaAcessoServicoPorIdRequest)) return false;
        BuscarListaAcessoServicoPorIdRequest other = (BuscarListaAcessoServicoPorIdRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaAcessoServicoPorId==null && other.getParametrosBuscarListaAcessoServicoPorId()==null) || 
             (this.parametrosBuscarListaAcessoServicoPorId!=null &&
              this.parametrosBuscarListaAcessoServicoPorId.equals(other.getParametrosBuscarListaAcessoServicoPorId())));
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
        if (getParametrosBuscarListaAcessoServicoPorId() != null) {
            _hashCode += getParametrosBuscarListaAcessoServicoPorId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaAcessoServicoPorIdRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">buscarListaAcessoServicoPorIdRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaAcessoServicoPorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ParametrosBuscarListaAcessoServicoPorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosBuscarListaAcessoServicoPorId"));
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
