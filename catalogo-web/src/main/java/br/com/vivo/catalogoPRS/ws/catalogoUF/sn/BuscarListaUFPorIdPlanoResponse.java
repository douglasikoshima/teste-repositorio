/**
 * BuscarListaUFPorIdPlanoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoUF.sn;

public class BuscarListaUFPorIdPlanoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ResultadoBuscarListaUFUF[] resultadoBuscarListaUF;

    public BuscarListaUFPorIdPlanoResponse() {
    }

    public BuscarListaUFPorIdPlanoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ResultadoBuscarListaUFUF[] resultadoBuscarListaUF) {
           this.resultadoBuscarListaUF = resultadoBuscarListaUF;
    }


    /**
     * Gets the resultadoBuscarListaUF value for this BuscarListaUFPorIdPlanoResponse.
     * 
     * @return resultadoBuscarListaUF
     */
    public br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ResultadoBuscarListaUFUF[] getResultadoBuscarListaUF() {
        return resultadoBuscarListaUF;
    }


    /**
     * Sets the resultadoBuscarListaUF value for this BuscarListaUFPorIdPlanoResponse.
     * 
     * @param resultadoBuscarListaUF
     */
    public void setResultadoBuscarListaUF(br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ResultadoBuscarListaUFUF[] resultadoBuscarListaUF) {
        this.resultadoBuscarListaUF = resultadoBuscarListaUF;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaUFPorIdPlanoResponse)) return false;
        BuscarListaUFPorIdPlanoResponse other = (BuscarListaUFPorIdPlanoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaUF==null && other.getResultadoBuscarListaUF()==null) || 
             (this.resultadoBuscarListaUF!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaUF, other.getResultadoBuscarListaUF())));
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
        if (getResultadoBuscarListaUF() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaUF());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaUF(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaUFPorIdPlanoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoUF", ">buscarListaUFPorIdPlanoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaUF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoUF", "ResultadoBuscarListaUF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoUF", ">ResultadoBuscarListaUF"));
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
