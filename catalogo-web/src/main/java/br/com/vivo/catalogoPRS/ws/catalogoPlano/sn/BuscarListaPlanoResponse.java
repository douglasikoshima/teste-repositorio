/**
 * BuscarListaPlanoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class BuscarListaPlanoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlano resultadoBuscarListaPlano;

    public BuscarListaPlanoResponse() {
    }

    public BuscarListaPlanoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlano resultadoBuscarListaPlano) {
           this.resultadoBuscarListaPlano = resultadoBuscarListaPlano;
    }


    /**
     * Gets the resultadoBuscarListaPlano value for this BuscarListaPlanoResponse.
     * 
     * @return resultadoBuscarListaPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlano getResultadoBuscarListaPlano() {
        return resultadoBuscarListaPlano;
    }


    /**
     * Sets the resultadoBuscarListaPlano value for this BuscarListaPlanoResponse.
     * 
     * @param resultadoBuscarListaPlano
     */
    public void setResultadoBuscarListaPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlano resultadoBuscarListaPlano) {
        this.resultadoBuscarListaPlano = resultadoBuscarListaPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaPlanoResponse)) return false;
        BuscarListaPlanoResponse other = (BuscarListaPlanoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaPlano==null && other.getResultadoBuscarListaPlano()==null) || 
             (this.resultadoBuscarListaPlano!=null &&
              this.resultadoBuscarListaPlano.equals(other.getResultadoBuscarListaPlano())));
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
        if (getResultadoBuscarListaPlano() != null) {
            _hashCode += getResultadoBuscarListaPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaPlanoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">buscarListaPlanoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ResultadoBuscarListaPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ResultadoBuscarListaPlano"));
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
