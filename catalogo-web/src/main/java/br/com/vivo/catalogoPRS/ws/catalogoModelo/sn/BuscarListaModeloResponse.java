/**
 * BuscarListaModeloResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class BuscarListaModeloResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaModelo resultadoBuscarListaModelo;

    public BuscarListaModeloResponse() {
    }

    public BuscarListaModeloResponse(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaModelo resultadoBuscarListaModelo) {
           this.resultadoBuscarListaModelo = resultadoBuscarListaModelo;
    }


    /**
     * Gets the resultadoBuscarListaModelo value for this BuscarListaModeloResponse.
     * 
     * @return resultadoBuscarListaModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaModelo getResultadoBuscarListaModelo() {
        return resultadoBuscarListaModelo;
    }


    /**
     * Sets the resultadoBuscarListaModelo value for this BuscarListaModeloResponse.
     * 
     * @param resultadoBuscarListaModelo
     */
    public void setResultadoBuscarListaModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaModelo resultadoBuscarListaModelo) {
        this.resultadoBuscarListaModelo = resultadoBuscarListaModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaModeloResponse)) return false;
        BuscarListaModeloResponse other = (BuscarListaModeloResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaModelo==null && other.getResultadoBuscarListaModelo()==null) || 
             (this.resultadoBuscarListaModelo!=null &&
              this.resultadoBuscarListaModelo.equals(other.getResultadoBuscarListaModelo())));
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
        if (getResultadoBuscarListaModelo() != null) {
            _hashCode += getResultadoBuscarListaModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaModeloResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">buscarListaModeloResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ResultadoBuscarListaModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ResultadoBuscarListaModelo"));
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
