/**
 * BuscarPlanoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class BuscarPlanoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarPlano resultadoBuscarPlano;

    public BuscarPlanoResponse() {
    }

    public BuscarPlanoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarPlano resultadoBuscarPlano) {
           this.resultadoBuscarPlano = resultadoBuscarPlano;
    }


    /**
     * Gets the resultadoBuscarPlano value for this BuscarPlanoResponse.
     * 
     * @return resultadoBuscarPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarPlano getResultadoBuscarPlano() {
        return resultadoBuscarPlano;
    }


    /**
     * Sets the resultadoBuscarPlano value for this BuscarPlanoResponse.
     * 
     * @param resultadoBuscarPlano
     */
    public void setResultadoBuscarPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarPlano resultadoBuscarPlano) {
        this.resultadoBuscarPlano = resultadoBuscarPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarPlanoResponse)) return false;
        BuscarPlanoResponse other = (BuscarPlanoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarPlano==null && other.getResultadoBuscarPlano()==null) || 
             (this.resultadoBuscarPlano!=null &&
              this.resultadoBuscarPlano.equals(other.getResultadoBuscarPlano())));
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
        if (getResultadoBuscarPlano() != null) {
            _hashCode += getResultadoBuscarPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarPlanoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">buscarPlanoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ResultadoBuscarPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ResultadoBuscarPlano"));
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
