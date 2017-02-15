/**
 * BuscarListaTipoClienteResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn;

public class BuscarListaTipoClienteResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.ResultadoBuscarListaTipoClienteTipoCliente[] resultadoBuscarListaTipoCliente;

    public BuscarListaTipoClienteResponse() {
    }

    public BuscarListaTipoClienteResponse(
           br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.ResultadoBuscarListaTipoClienteTipoCliente[] resultadoBuscarListaTipoCliente) {
           this.resultadoBuscarListaTipoCliente = resultadoBuscarListaTipoCliente;
    }


    /**
     * Gets the resultadoBuscarListaTipoCliente value for this BuscarListaTipoClienteResponse.
     * 
     * @return resultadoBuscarListaTipoCliente
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.ResultadoBuscarListaTipoClienteTipoCliente[] getResultadoBuscarListaTipoCliente() {
        return resultadoBuscarListaTipoCliente;
    }


    /**
     * Sets the resultadoBuscarListaTipoCliente value for this BuscarListaTipoClienteResponse.
     * 
     * @param resultadoBuscarListaTipoCliente
     */
    public void setResultadoBuscarListaTipoCliente(br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.ResultadoBuscarListaTipoClienteTipoCliente[] resultadoBuscarListaTipoCliente) {
        this.resultadoBuscarListaTipoCliente = resultadoBuscarListaTipoCliente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaTipoClienteResponse)) return false;
        BuscarListaTipoClienteResponse other = (BuscarListaTipoClienteResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaTipoCliente==null && other.getResultadoBuscarListaTipoCliente()==null) || 
             (this.resultadoBuscarListaTipoCliente!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaTipoCliente, other.getResultadoBuscarListaTipoCliente())));
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
        if (getResultadoBuscarListaTipoCliente() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaTipoCliente());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaTipoCliente(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaTipoClienteResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoCliente", ">buscarListaTipoClienteResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaTipoCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoCliente", "ResultadoBuscarListaTipoCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoCliente", ">ResultadoBuscarListaTipoCliente"));
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
