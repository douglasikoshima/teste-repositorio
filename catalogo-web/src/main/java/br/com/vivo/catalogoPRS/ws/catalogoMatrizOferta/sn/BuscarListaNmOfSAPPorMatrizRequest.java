/**
 * BuscarListaNmOfSAPPorMatrizRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class BuscarListaNmOfSAPPorMatrizRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaNmOfSAPPorMatriz parametrosBuscarListaNmOfSAPPorMatriz;

    public BuscarListaNmOfSAPPorMatrizRequest() {
    }

    public BuscarListaNmOfSAPPorMatrizRequest(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaNmOfSAPPorMatriz parametrosBuscarListaNmOfSAPPorMatriz) {
           this.parametrosBuscarListaNmOfSAPPorMatriz = parametrosBuscarListaNmOfSAPPorMatriz;
    }


    /**
     * Gets the parametrosBuscarListaNmOfSAPPorMatriz value for this BuscarListaNmOfSAPPorMatrizRequest.
     * 
     * @return parametrosBuscarListaNmOfSAPPorMatriz
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaNmOfSAPPorMatriz getParametrosBuscarListaNmOfSAPPorMatriz() {
        return parametrosBuscarListaNmOfSAPPorMatriz;
    }


    /**
     * Sets the parametrosBuscarListaNmOfSAPPorMatriz value for this BuscarListaNmOfSAPPorMatrizRequest.
     * 
     * @param parametrosBuscarListaNmOfSAPPorMatriz
     */
    public void setParametrosBuscarListaNmOfSAPPorMatriz(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaNmOfSAPPorMatriz parametrosBuscarListaNmOfSAPPorMatriz) {
        this.parametrosBuscarListaNmOfSAPPorMatriz = parametrosBuscarListaNmOfSAPPorMatriz;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaNmOfSAPPorMatrizRequest)) return false;
        BuscarListaNmOfSAPPorMatrizRequest other = (BuscarListaNmOfSAPPorMatrizRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaNmOfSAPPorMatriz==null && other.getParametrosBuscarListaNmOfSAPPorMatriz()==null) || 
             (this.parametrosBuscarListaNmOfSAPPorMatriz!=null &&
              this.parametrosBuscarListaNmOfSAPPorMatriz.equals(other.getParametrosBuscarListaNmOfSAPPorMatriz())));
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
        if (getParametrosBuscarListaNmOfSAPPorMatriz() != null) {
            _hashCode += getParametrosBuscarListaNmOfSAPPorMatriz().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaNmOfSAPPorMatrizRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaNmOfSAPPorMatrizRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaNmOfSAPPorMatriz");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ParametrosBuscarListaNmOfSAPPorMatriz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosBuscarListaNmOfSAPPorMatriz"));
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
