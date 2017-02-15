/**
 * BuscarListaTecnologiaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class BuscarListaTecnologiaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaTecnologia[] resultadoBuscarListaTecnologia;

    public BuscarListaTecnologiaResponse() {
    }

    public BuscarListaTecnologiaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaTecnologia[] resultadoBuscarListaTecnologia) {
           this.resultadoBuscarListaTecnologia = resultadoBuscarListaTecnologia;
    }


    /**
     * Gets the resultadoBuscarListaTecnologia value for this BuscarListaTecnologiaResponse.
     * 
     * @return resultadoBuscarListaTecnologia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaTecnologia[] getResultadoBuscarListaTecnologia() {
        return resultadoBuscarListaTecnologia;
    }


    /**
     * Sets the resultadoBuscarListaTecnologia value for this BuscarListaTecnologiaResponse.
     * 
     * @param resultadoBuscarListaTecnologia
     */
    public void setResultadoBuscarListaTecnologia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaTecnologia[] resultadoBuscarListaTecnologia) {
        this.resultadoBuscarListaTecnologia = resultadoBuscarListaTecnologia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaTecnologiaResponse)) return false;
        BuscarListaTecnologiaResponse other = (BuscarListaTecnologiaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaTecnologia==null && other.getResultadoBuscarListaTecnologia()==null) || 
             (this.resultadoBuscarListaTecnologia!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaTecnologia, other.getResultadoBuscarListaTecnologia())));
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
        if (getResultadoBuscarListaTecnologia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaTecnologia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaTecnologia(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaTecnologiaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">buscarListaTecnologiaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ResultadoBuscarListaTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ResultadoBuscarListaTecnologia"));
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
