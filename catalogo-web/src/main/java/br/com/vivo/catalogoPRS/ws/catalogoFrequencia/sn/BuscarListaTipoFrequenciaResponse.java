/**
 * BuscarListaTipoFrequenciaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class BuscarListaTipoFrequenciaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao[] resultadoBuscarListaTipoFrequencia;

    public BuscarListaTipoFrequenciaResponse() {
    }

    public BuscarListaTipoFrequenciaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao[] resultadoBuscarListaTipoFrequencia) {
           this.resultadoBuscarListaTipoFrequencia = resultadoBuscarListaTipoFrequencia;
    }


    /**
     * Gets the resultadoBuscarListaTipoFrequencia value for this BuscarListaTipoFrequenciaResponse.
     * 
     * @return resultadoBuscarListaTipoFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao[] getResultadoBuscarListaTipoFrequencia() {
        return resultadoBuscarListaTipoFrequencia;
    }


    /**
     * Sets the resultadoBuscarListaTipoFrequencia value for this BuscarListaTipoFrequenciaResponse.
     * 
     * @param resultadoBuscarListaTipoFrequencia
     */
    public void setResultadoBuscarListaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao[] resultadoBuscarListaTipoFrequencia) {
        this.resultadoBuscarListaTipoFrequencia = resultadoBuscarListaTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaTipoFrequenciaResponse)) return false;
        BuscarListaTipoFrequenciaResponse other = (BuscarListaTipoFrequenciaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaTipoFrequencia==null && other.getResultadoBuscarListaTipoFrequencia()==null) || 
             (this.resultadoBuscarListaTipoFrequencia!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaTipoFrequencia, other.getResultadoBuscarListaTipoFrequencia())));
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
        if (getResultadoBuscarListaTipoFrequencia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaTipoFrequencia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaTipoFrequencia(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaTipoFrequenciaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">buscarListaTipoFrequenciaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ResultadoBuscarListaTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ResultadoBuscarListaTipoFrequencia"));
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
