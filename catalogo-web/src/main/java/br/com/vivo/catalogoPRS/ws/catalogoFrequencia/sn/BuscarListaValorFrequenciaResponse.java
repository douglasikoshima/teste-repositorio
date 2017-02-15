/**
 * BuscarListaValorFrequenciaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class BuscarListaValorFrequenciaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoListarValorFrequenciaVlfrequencia[] resultadoListarValorFrequencia;

    public BuscarListaValorFrequenciaResponse() {
    }

    public BuscarListaValorFrequenciaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoListarValorFrequenciaVlfrequencia[] resultadoListarValorFrequencia) {
           this.resultadoListarValorFrequencia = resultadoListarValorFrequencia;
    }


    /**
     * Gets the resultadoListarValorFrequencia value for this BuscarListaValorFrequenciaResponse.
     * 
     * @return resultadoListarValorFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoListarValorFrequenciaVlfrequencia[] getResultadoListarValorFrequencia() {
        return resultadoListarValorFrequencia;
    }


    /**
     * Sets the resultadoListarValorFrequencia value for this BuscarListaValorFrequenciaResponse.
     * 
     * @param resultadoListarValorFrequencia
     */
    public void setResultadoListarValorFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoListarValorFrequenciaVlfrequencia[] resultadoListarValorFrequencia) {
        this.resultadoListarValorFrequencia = resultadoListarValorFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaValorFrequenciaResponse)) return false;
        BuscarListaValorFrequenciaResponse other = (BuscarListaValorFrequenciaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoListarValorFrequencia==null && other.getResultadoListarValorFrequencia()==null) || 
             (this.resultadoListarValorFrequencia!=null &&
              java.util.Arrays.equals(this.resultadoListarValorFrequencia, other.getResultadoListarValorFrequencia())));
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
        if (getResultadoListarValorFrequencia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoListarValorFrequencia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoListarValorFrequencia(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaValorFrequenciaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">buscarListaValorFrequenciaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoListarValorFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ResultadoListarValorFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ResultadoListarValorFrequencia"));
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
