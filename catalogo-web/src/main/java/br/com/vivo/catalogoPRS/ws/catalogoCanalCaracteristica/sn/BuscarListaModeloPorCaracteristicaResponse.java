/**
 * BuscarListaModeloPorCaracteristicaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class BuscarListaModeloPorCaracteristicaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoBuscarListaModeloPorCaracteristicaModeloPorCaracteristica[] resultadoBuscarListaModeloPorCaracteristica;

    public BuscarListaModeloPorCaracteristicaResponse() {
    }

    public BuscarListaModeloPorCaracteristicaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoBuscarListaModeloPorCaracteristicaModeloPorCaracteristica[] resultadoBuscarListaModeloPorCaracteristica) {
           this.resultadoBuscarListaModeloPorCaracteristica = resultadoBuscarListaModeloPorCaracteristica;
    }


    /**
     * Gets the resultadoBuscarListaModeloPorCaracteristica value for this BuscarListaModeloPorCaracteristicaResponse.
     * 
     * @return resultadoBuscarListaModeloPorCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoBuscarListaModeloPorCaracteristicaModeloPorCaracteristica[] getResultadoBuscarListaModeloPorCaracteristica() {
        return resultadoBuscarListaModeloPorCaracteristica;
    }


    /**
     * Sets the resultadoBuscarListaModeloPorCaracteristica value for this BuscarListaModeloPorCaracteristicaResponse.
     * 
     * @param resultadoBuscarListaModeloPorCaracteristica
     */
    public void setResultadoBuscarListaModeloPorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoBuscarListaModeloPorCaracteristicaModeloPorCaracteristica[] resultadoBuscarListaModeloPorCaracteristica) {
        this.resultadoBuscarListaModeloPorCaracteristica = resultadoBuscarListaModeloPorCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaModeloPorCaracteristicaResponse)) return false;
        BuscarListaModeloPorCaracteristicaResponse other = (BuscarListaModeloPorCaracteristicaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaModeloPorCaracteristica==null && other.getResultadoBuscarListaModeloPorCaracteristica()==null) || 
             (this.resultadoBuscarListaModeloPorCaracteristica!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaModeloPorCaracteristica, other.getResultadoBuscarListaModeloPorCaracteristica())));
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
        if (getResultadoBuscarListaModeloPorCaracteristica() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaModeloPorCaracteristica());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaModeloPorCaracteristica(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaModeloPorCaracteristicaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">buscarListaModeloPorCaracteristicaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaModeloPorCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ResultadoBuscarListaModeloPorCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ResultadoBuscarListaModeloPorCaracteristica"));
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
