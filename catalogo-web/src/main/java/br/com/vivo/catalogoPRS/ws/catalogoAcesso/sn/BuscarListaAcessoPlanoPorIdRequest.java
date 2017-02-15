/**
 * BuscarListaAcessoPlanoPorIdRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class BuscarListaAcessoPlanoPorIdRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoPlanoPorId parametrosBuscarListaAcessoPlanoPorId;

    public BuscarListaAcessoPlanoPorIdRequest() {
    }

    public BuscarListaAcessoPlanoPorIdRequest(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoPlanoPorId parametrosBuscarListaAcessoPlanoPorId) {
           this.parametrosBuscarListaAcessoPlanoPorId = parametrosBuscarListaAcessoPlanoPorId;
    }


    /**
     * Gets the parametrosBuscarListaAcessoPlanoPorId value for this BuscarListaAcessoPlanoPorIdRequest.
     * 
     * @return parametrosBuscarListaAcessoPlanoPorId
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoPlanoPorId getParametrosBuscarListaAcessoPlanoPorId() {
        return parametrosBuscarListaAcessoPlanoPorId;
    }


    /**
     * Sets the parametrosBuscarListaAcessoPlanoPorId value for this BuscarListaAcessoPlanoPorIdRequest.
     * 
     * @param parametrosBuscarListaAcessoPlanoPorId
     */
    public void setParametrosBuscarListaAcessoPlanoPorId(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoPlanoPorId parametrosBuscarListaAcessoPlanoPorId) {
        this.parametrosBuscarListaAcessoPlanoPorId = parametrosBuscarListaAcessoPlanoPorId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaAcessoPlanoPorIdRequest)) return false;
        BuscarListaAcessoPlanoPorIdRequest other = (BuscarListaAcessoPlanoPorIdRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaAcessoPlanoPorId==null && other.getParametrosBuscarListaAcessoPlanoPorId()==null) || 
             (this.parametrosBuscarListaAcessoPlanoPorId!=null &&
              this.parametrosBuscarListaAcessoPlanoPorId.equals(other.getParametrosBuscarListaAcessoPlanoPorId())));
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
        if (getParametrosBuscarListaAcessoPlanoPorId() != null) {
            _hashCode += getParametrosBuscarListaAcessoPlanoPorId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaAcessoPlanoPorIdRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">buscarListaAcessoPlanoPorIdRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaAcessoPlanoPorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ParametrosBuscarListaAcessoPlanoPorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosBuscarListaAcessoPlanoPorId"));
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
