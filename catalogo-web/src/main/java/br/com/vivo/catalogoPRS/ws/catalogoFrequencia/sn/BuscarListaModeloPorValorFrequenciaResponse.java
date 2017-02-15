/**
 * BuscarListaModeloPorValorFrequenciaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class BuscarListaModeloPorValorFrequenciaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaModeloPorValorFrequenciaModeloPorValorFrequencia[] resultadoBuscarListaModeloPorValorFrequencia;

    public BuscarListaModeloPorValorFrequenciaResponse() {
    }

    public BuscarListaModeloPorValorFrequenciaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaModeloPorValorFrequenciaModeloPorValorFrequencia[] resultadoBuscarListaModeloPorValorFrequencia) {
           this.resultadoBuscarListaModeloPorValorFrequencia = resultadoBuscarListaModeloPorValorFrequencia;
    }


    /**
     * Gets the resultadoBuscarListaModeloPorValorFrequencia value for this BuscarListaModeloPorValorFrequenciaResponse.
     * 
     * @return resultadoBuscarListaModeloPorValorFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaModeloPorValorFrequenciaModeloPorValorFrequencia[] getResultadoBuscarListaModeloPorValorFrequencia() {
        return resultadoBuscarListaModeloPorValorFrequencia;
    }


    /**
     * Sets the resultadoBuscarListaModeloPorValorFrequencia value for this BuscarListaModeloPorValorFrequenciaResponse.
     * 
     * @param resultadoBuscarListaModeloPorValorFrequencia
     */
    public void setResultadoBuscarListaModeloPorValorFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaModeloPorValorFrequenciaModeloPorValorFrequencia[] resultadoBuscarListaModeloPorValorFrequencia) {
        this.resultadoBuscarListaModeloPorValorFrequencia = resultadoBuscarListaModeloPorValorFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaModeloPorValorFrequenciaResponse)) return false;
        BuscarListaModeloPorValorFrequenciaResponse other = (BuscarListaModeloPorValorFrequenciaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaModeloPorValorFrequencia==null && other.getResultadoBuscarListaModeloPorValorFrequencia()==null) || 
             (this.resultadoBuscarListaModeloPorValorFrequencia!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaModeloPorValorFrequencia, other.getResultadoBuscarListaModeloPorValorFrequencia())));
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
        if (getResultadoBuscarListaModeloPorValorFrequencia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaModeloPorValorFrequencia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaModeloPorValorFrequencia(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaModeloPorValorFrequenciaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">buscarListaModeloPorValorFrequenciaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaModeloPorValorFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ResultadoBuscarListaModeloPorValorFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ResultadoBuscarListaModeloPorValorFrequencia"));
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
