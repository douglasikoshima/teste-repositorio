/**
 * BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaVlfrequencia[] resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia;

    public BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse() {
    }

    public BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaVlfrequencia[] resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia) {
           this.resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia = resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia;
    }


    /**
     * Gets the resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia value for this BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse.
     * 
     * @return resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaVlfrequencia[] getResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia() {
        return resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia;
    }


    /**
     * Sets the resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia value for this BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse.
     * 
     * @param resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia
     */
    public void setResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaVlfrequencia[] resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia) {
        this.resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia = resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse)) return false;
        BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse other = (BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia==null && other.getResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia()==null) || 
             (this.resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia, other.getResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia())));
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
        if (getResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">buscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia"));
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
