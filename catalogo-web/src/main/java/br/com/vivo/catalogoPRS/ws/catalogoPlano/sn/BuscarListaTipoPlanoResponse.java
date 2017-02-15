/**
 * BuscarListaTipoPlanoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class BuscarListaTipoPlanoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaTipoPlano resultadoBuscarListaTipoPlano;

    public BuscarListaTipoPlanoResponse() {
    }

    public BuscarListaTipoPlanoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaTipoPlano resultadoBuscarListaTipoPlano) {
           this.resultadoBuscarListaTipoPlano = resultadoBuscarListaTipoPlano;
    }


    /**
     * Gets the resultadoBuscarListaTipoPlano value for this BuscarListaTipoPlanoResponse.
     * 
     * @return resultadoBuscarListaTipoPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaTipoPlano getResultadoBuscarListaTipoPlano() {
        return resultadoBuscarListaTipoPlano;
    }


    /**
     * Sets the resultadoBuscarListaTipoPlano value for this BuscarListaTipoPlanoResponse.
     * 
     * @param resultadoBuscarListaTipoPlano
     */
    public void setResultadoBuscarListaTipoPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaTipoPlano resultadoBuscarListaTipoPlano) {
        this.resultadoBuscarListaTipoPlano = resultadoBuscarListaTipoPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaTipoPlanoResponse)) return false;
        BuscarListaTipoPlanoResponse other = (BuscarListaTipoPlanoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaTipoPlano==null && other.getResultadoBuscarListaTipoPlano()==null) || 
             (this.resultadoBuscarListaTipoPlano!=null &&
              this.resultadoBuscarListaTipoPlano.equals(other.getResultadoBuscarListaTipoPlano())));
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
        if (getResultadoBuscarListaTipoPlano() != null) {
            _hashCode += getResultadoBuscarListaTipoPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaTipoPlanoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">buscarListaTipoPlanoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaTipoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ResultadoBuscarListaTipoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ResultadoBuscarListaTipoPlano"));
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
