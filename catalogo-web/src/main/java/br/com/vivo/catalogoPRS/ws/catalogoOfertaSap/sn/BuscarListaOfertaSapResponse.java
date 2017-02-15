/**
 * BuscarListaOfertaSapResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class BuscarListaOfertaSapResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaOfertaSap resultadoBuscarListaOfertaSap;

    public BuscarListaOfertaSapResponse() {
    }

    public BuscarListaOfertaSapResponse(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaOfertaSap resultadoBuscarListaOfertaSap) {
           this.resultadoBuscarListaOfertaSap = resultadoBuscarListaOfertaSap;
    }


    /**
     * Gets the resultadoBuscarListaOfertaSap value for this BuscarListaOfertaSapResponse.
     * 
     * @return resultadoBuscarListaOfertaSap
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaOfertaSap getResultadoBuscarListaOfertaSap() {
        return resultadoBuscarListaOfertaSap;
    }


    /**
     * Sets the resultadoBuscarListaOfertaSap value for this BuscarListaOfertaSapResponse.
     * 
     * @param resultadoBuscarListaOfertaSap
     */
    public void setResultadoBuscarListaOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaOfertaSap resultadoBuscarListaOfertaSap) {
        this.resultadoBuscarListaOfertaSap = resultadoBuscarListaOfertaSap;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaOfertaSapResponse)) return false;
        BuscarListaOfertaSapResponse other = (BuscarListaOfertaSapResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaOfertaSap==null && other.getResultadoBuscarListaOfertaSap()==null) || 
             (this.resultadoBuscarListaOfertaSap!=null &&
              this.resultadoBuscarListaOfertaSap.equals(other.getResultadoBuscarListaOfertaSap())));
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
        if (getResultadoBuscarListaOfertaSap() != null) {
            _hashCode += getResultadoBuscarListaOfertaSap().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaOfertaSapResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">buscarListaOfertaSapResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaOfertaSap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ResultadoBuscarListaOfertaSap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">ResultadoBuscarListaOfertaSap"));
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
