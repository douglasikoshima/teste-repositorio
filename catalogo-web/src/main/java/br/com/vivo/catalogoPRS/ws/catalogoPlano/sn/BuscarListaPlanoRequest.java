/**
 * BuscarListaPlanoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class BuscarListaPlanoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlano parametrosBuscarListaPlano;

    public BuscarListaPlanoRequest() {
    }

    public BuscarListaPlanoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlano parametrosBuscarListaPlano) {
           this.parametrosBuscarListaPlano = parametrosBuscarListaPlano;
    }


    /**
     * Gets the parametrosBuscarListaPlano value for this BuscarListaPlanoRequest.
     * 
     * @return parametrosBuscarListaPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlano getParametrosBuscarListaPlano() {
        return parametrosBuscarListaPlano;
    }


    /**
     * Sets the parametrosBuscarListaPlano value for this BuscarListaPlanoRequest.
     * 
     * @param parametrosBuscarListaPlano
     */
    public void setParametrosBuscarListaPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlano parametrosBuscarListaPlano) {
        this.parametrosBuscarListaPlano = parametrosBuscarListaPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaPlanoRequest)) return false;
        BuscarListaPlanoRequest other = (BuscarListaPlanoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaPlano==null && other.getParametrosBuscarListaPlano()==null) || 
             (this.parametrosBuscarListaPlano!=null &&
              this.parametrosBuscarListaPlano.equals(other.getParametrosBuscarListaPlano())));
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
        if (getParametrosBuscarListaPlano() != null) {
            _hashCode += getParametrosBuscarListaPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaPlanoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">buscarListaPlanoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ParametrosBuscarListaPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ParametrosBuscarListaPlano"));
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
