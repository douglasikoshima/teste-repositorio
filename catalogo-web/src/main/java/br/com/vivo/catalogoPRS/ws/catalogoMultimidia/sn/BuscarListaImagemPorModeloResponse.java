/**
 * BuscarListaImagemPorModeloResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class BuscarListaImagemPorModeloResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultadoBuscarListaImagemPorModeloMultimidia[] resultadoBuscarListaImagemPorModelo;

    public BuscarListaImagemPorModeloResponse() {
    }

    public BuscarListaImagemPorModeloResponse(
           br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultadoBuscarListaImagemPorModeloMultimidia[] resultadoBuscarListaImagemPorModelo) {
           this.resultadoBuscarListaImagemPorModelo = resultadoBuscarListaImagemPorModelo;
    }


    /**
     * Gets the resultadoBuscarListaImagemPorModelo value for this BuscarListaImagemPorModeloResponse.
     * 
     * @return resultadoBuscarListaImagemPorModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultadoBuscarListaImagemPorModeloMultimidia[] getResultadoBuscarListaImagemPorModelo() {
        return resultadoBuscarListaImagemPorModelo;
    }


    /**
     * Sets the resultadoBuscarListaImagemPorModelo value for this BuscarListaImagemPorModeloResponse.
     * 
     * @param resultadoBuscarListaImagemPorModelo
     */
    public void setResultadoBuscarListaImagemPorModelo(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultadoBuscarListaImagemPorModeloMultimidia[] resultadoBuscarListaImagemPorModelo) {
        this.resultadoBuscarListaImagemPorModelo = resultadoBuscarListaImagemPorModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaImagemPorModeloResponse)) return false;
        BuscarListaImagemPorModeloResponse other = (BuscarListaImagemPorModeloResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaImagemPorModelo==null && other.getResultadoBuscarListaImagemPorModelo()==null) || 
             (this.resultadoBuscarListaImagemPorModelo!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaImagemPorModelo, other.getResultadoBuscarListaImagemPorModelo())));
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
        if (getResultadoBuscarListaImagemPorModelo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaImagemPorModelo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaImagemPorModelo(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaImagemPorModeloResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">buscarListaImagemPorModeloResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaImagemPorModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "ResultadoBuscarListaImagemPorModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">ResultadoBuscarListaImagemPorModelo"));
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
