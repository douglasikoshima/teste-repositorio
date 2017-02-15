/**
 * BuscarListaNmOfSAPPorMatrizResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class BuscarListaNmOfSAPPorMatrizResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaNmOfSAPPorMatrizOfertaSAP[] resultadoBuscarListaNmOfSAPPorMatriz;

    public BuscarListaNmOfSAPPorMatrizResponse() {
    }

    public BuscarListaNmOfSAPPorMatrizResponse(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaNmOfSAPPorMatrizOfertaSAP[] resultadoBuscarListaNmOfSAPPorMatriz) {
           this.resultadoBuscarListaNmOfSAPPorMatriz = resultadoBuscarListaNmOfSAPPorMatriz;
    }


    /**
     * Gets the resultadoBuscarListaNmOfSAPPorMatriz value for this BuscarListaNmOfSAPPorMatrizResponse.
     * 
     * @return resultadoBuscarListaNmOfSAPPorMatriz
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaNmOfSAPPorMatrizOfertaSAP[] getResultadoBuscarListaNmOfSAPPorMatriz() {
        return resultadoBuscarListaNmOfSAPPorMatriz;
    }


    /**
     * Sets the resultadoBuscarListaNmOfSAPPorMatriz value for this BuscarListaNmOfSAPPorMatrizResponse.
     * 
     * @param resultadoBuscarListaNmOfSAPPorMatriz
     */
    public void setResultadoBuscarListaNmOfSAPPorMatriz(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaNmOfSAPPorMatrizOfertaSAP[] resultadoBuscarListaNmOfSAPPorMatriz) {
        this.resultadoBuscarListaNmOfSAPPorMatriz = resultadoBuscarListaNmOfSAPPorMatriz;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaNmOfSAPPorMatrizResponse)) return false;
        BuscarListaNmOfSAPPorMatrizResponse other = (BuscarListaNmOfSAPPorMatrizResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaNmOfSAPPorMatriz==null && other.getResultadoBuscarListaNmOfSAPPorMatriz()==null) || 
             (this.resultadoBuscarListaNmOfSAPPorMatriz!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaNmOfSAPPorMatriz, other.getResultadoBuscarListaNmOfSAPPorMatriz())));
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
        if (getResultadoBuscarListaNmOfSAPPorMatriz() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaNmOfSAPPorMatriz());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaNmOfSAPPorMatriz(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaNmOfSAPPorMatrizResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaNmOfSAPPorMatrizResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaNmOfSAPPorMatriz");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ResultadoBuscarListaNmOfSAPPorMatriz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoBuscarListaNmOfSAPPorMatriz"));
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
