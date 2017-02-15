/**
 * BuscarListaTecnologiaPorModeloResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class BuscarListaTecnologiaPorModeloResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaPorModeloTecnologia[] resultadoBuscarListaTecnologiaPorModelo;

    public BuscarListaTecnologiaPorModeloResponse() {
    }

    public BuscarListaTecnologiaPorModeloResponse(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaPorModeloTecnologia[] resultadoBuscarListaTecnologiaPorModelo) {
           this.resultadoBuscarListaTecnologiaPorModelo = resultadoBuscarListaTecnologiaPorModelo;
    }


    /**
     * Gets the resultadoBuscarListaTecnologiaPorModelo value for this BuscarListaTecnologiaPorModeloResponse.
     * 
     * @return resultadoBuscarListaTecnologiaPorModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaPorModeloTecnologia[] getResultadoBuscarListaTecnologiaPorModelo() {
        return resultadoBuscarListaTecnologiaPorModelo;
    }


    /**
     * Sets the resultadoBuscarListaTecnologiaPorModelo value for this BuscarListaTecnologiaPorModeloResponse.
     * 
     * @param resultadoBuscarListaTecnologiaPorModelo
     */
    public void setResultadoBuscarListaTecnologiaPorModelo(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaPorModeloTecnologia[] resultadoBuscarListaTecnologiaPorModelo) {
        this.resultadoBuscarListaTecnologiaPorModelo = resultadoBuscarListaTecnologiaPorModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaTecnologiaPorModeloResponse)) return false;
        BuscarListaTecnologiaPorModeloResponse other = (BuscarListaTecnologiaPorModeloResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaTecnologiaPorModelo==null && other.getResultadoBuscarListaTecnologiaPorModelo()==null) || 
             (this.resultadoBuscarListaTecnologiaPorModelo!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaTecnologiaPorModelo, other.getResultadoBuscarListaTecnologiaPorModelo())));
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
        if (getResultadoBuscarListaTecnologiaPorModelo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaTecnologiaPorModelo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaTecnologiaPorModelo(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaTecnologiaPorModeloResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">buscarListaTecnologiaPorModeloResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaTecnologiaPorModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ResultadoBuscarListaTecnologiaPorModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ResultadoBuscarListaTecnologiaPorModelo"));
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
