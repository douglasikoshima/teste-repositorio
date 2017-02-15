/**
 * BuscarListaVariaveisPorPlanoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class BuscarListaVariaveisPorPlanoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaVariaveisPorPlano resultadoBuscarListaVariaveisPorPlano;

    public BuscarListaVariaveisPorPlanoResponse() {
    }

    public BuscarListaVariaveisPorPlanoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaVariaveisPorPlano resultadoBuscarListaVariaveisPorPlano) {
           this.resultadoBuscarListaVariaveisPorPlano = resultadoBuscarListaVariaveisPorPlano;
    }


    /**
     * Gets the resultadoBuscarListaVariaveisPorPlano value for this BuscarListaVariaveisPorPlanoResponse.
     * 
     * @return resultadoBuscarListaVariaveisPorPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaVariaveisPorPlano getResultadoBuscarListaVariaveisPorPlano() {
        return resultadoBuscarListaVariaveisPorPlano;
    }


    /**
     * Sets the resultadoBuscarListaVariaveisPorPlano value for this BuscarListaVariaveisPorPlanoResponse.
     * 
     * @param resultadoBuscarListaVariaveisPorPlano
     */
    public void setResultadoBuscarListaVariaveisPorPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaVariaveisPorPlano resultadoBuscarListaVariaveisPorPlano) {
        this.resultadoBuscarListaVariaveisPorPlano = resultadoBuscarListaVariaveisPorPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaVariaveisPorPlanoResponse)) return false;
        BuscarListaVariaveisPorPlanoResponse other = (BuscarListaVariaveisPorPlanoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaVariaveisPorPlano==null && other.getResultadoBuscarListaVariaveisPorPlano()==null) || 
             (this.resultadoBuscarListaVariaveisPorPlano!=null &&
              this.resultadoBuscarListaVariaveisPorPlano.equals(other.getResultadoBuscarListaVariaveisPorPlano())));
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
        if (getResultadoBuscarListaVariaveisPorPlano() != null) {
            _hashCode += getResultadoBuscarListaVariaveisPorPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaVariaveisPorPlanoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">buscarListaVariaveisPorPlanoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaVariaveisPorPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ResultadoBuscarListaVariaveisPorPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ResultadoBuscarListaVariaveisPorPlano"));
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
