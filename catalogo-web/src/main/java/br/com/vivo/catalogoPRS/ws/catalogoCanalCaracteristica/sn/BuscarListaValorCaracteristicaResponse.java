/**
 * BuscarListaValorCaracteristicaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class BuscarListaValorCaracteristicaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoBuscarListaValorCaracteristica resultadoBuscarListaValorCaracteristica;

    public BuscarListaValorCaracteristicaResponse() {
    }

    public BuscarListaValorCaracteristicaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoBuscarListaValorCaracteristica resultadoBuscarListaValorCaracteristica) {
           this.resultadoBuscarListaValorCaracteristica = resultadoBuscarListaValorCaracteristica;
    }


    /**
     * Gets the resultadoBuscarListaValorCaracteristica value for this BuscarListaValorCaracteristicaResponse.
     * 
     * @return resultadoBuscarListaValorCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoBuscarListaValorCaracteristica getResultadoBuscarListaValorCaracteristica() {
        return resultadoBuscarListaValorCaracteristica;
    }


    /**
     * Sets the resultadoBuscarListaValorCaracteristica value for this BuscarListaValorCaracteristicaResponse.
     * 
     * @param resultadoBuscarListaValorCaracteristica
     */
    public void setResultadoBuscarListaValorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoBuscarListaValorCaracteristica resultadoBuscarListaValorCaracteristica) {
        this.resultadoBuscarListaValorCaracteristica = resultadoBuscarListaValorCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaValorCaracteristicaResponse)) return false;
        BuscarListaValorCaracteristicaResponse other = (BuscarListaValorCaracteristicaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaValorCaracteristica==null && other.getResultadoBuscarListaValorCaracteristica()==null) || 
             (this.resultadoBuscarListaValorCaracteristica!=null &&
              this.resultadoBuscarListaValorCaracteristica.equals(other.getResultadoBuscarListaValorCaracteristica())));
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
        if (getResultadoBuscarListaValorCaracteristica() != null) {
            _hashCode += getResultadoBuscarListaValorCaracteristica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaValorCaracteristicaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">buscarListaValorCaracteristicaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaValorCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ResultadoBuscarListaValorCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ResultadoBuscarListaValorCaracteristica"));
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
