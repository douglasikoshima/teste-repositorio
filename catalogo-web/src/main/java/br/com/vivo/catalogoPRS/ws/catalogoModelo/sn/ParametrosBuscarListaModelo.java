/**
 * ParametrosBuscarListaModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosBuscarListaModelo  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloParametrosModeloIn parametrosModeloIn;

    public ParametrosBuscarListaModelo() {
    }

    public ParametrosBuscarListaModelo(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloParametrosModeloIn parametrosModeloIn) {
           this.parametrosModeloIn = parametrosModeloIn;
    }


    /**
     * Gets the parametrosModeloIn value for this ParametrosBuscarListaModelo.
     * 
     * @return parametrosModeloIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloParametrosModeloIn getParametrosModeloIn() {
        return parametrosModeloIn;
    }


    /**
     * Sets the parametrosModeloIn value for this ParametrosBuscarListaModelo.
     * 
     * @param parametrosModeloIn
     */
    public void setParametrosModeloIn(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloParametrosModeloIn parametrosModeloIn) {
        this.parametrosModeloIn = parametrosModeloIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaModelo)) return false;
        ParametrosBuscarListaModelo other = (ParametrosBuscarListaModelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosModeloIn==null && other.getParametrosModeloIn()==null) || 
             (this.parametrosModeloIn!=null &&
              this.parametrosModeloIn.equals(other.getParametrosModeloIn())));
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
        if (getParametrosModeloIn() != null) {
            _hashCode += getParametrosModeloIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosBuscarListaModelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosModeloIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosModeloIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosBuscarListaModelo>ParametrosModeloIn"));
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
