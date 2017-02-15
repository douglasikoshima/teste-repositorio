/**
 * ParametrosBuscarListaModeloPorValorFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ParametrosBuscarListaModeloPorValorFrequencia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn parametrosModeloPorValorFrequenciaIn;

    public ParametrosBuscarListaModeloPorValorFrequencia() {
    }

    public ParametrosBuscarListaModeloPorValorFrequencia(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn parametrosModeloPorValorFrequenciaIn) {
           this.parametrosModeloPorValorFrequenciaIn = parametrosModeloPorValorFrequenciaIn;
    }


    /**
     * Gets the parametrosModeloPorValorFrequenciaIn value for this ParametrosBuscarListaModeloPorValorFrequencia.
     * 
     * @return parametrosModeloPorValorFrequenciaIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn getParametrosModeloPorValorFrequenciaIn() {
        return parametrosModeloPorValorFrequenciaIn;
    }


    /**
     * Sets the parametrosModeloPorValorFrequenciaIn value for this ParametrosBuscarListaModeloPorValorFrequencia.
     * 
     * @param parametrosModeloPorValorFrequenciaIn
     */
    public void setParametrosModeloPorValorFrequenciaIn(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn parametrosModeloPorValorFrequenciaIn) {
        this.parametrosModeloPorValorFrequenciaIn = parametrosModeloPorValorFrequenciaIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaModeloPorValorFrequencia)) return false;
        ParametrosBuscarListaModeloPorValorFrequencia other = (ParametrosBuscarListaModeloPorValorFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosModeloPorValorFrequenciaIn==null && other.getParametrosModeloPorValorFrequenciaIn()==null) || 
             (this.parametrosModeloPorValorFrequenciaIn!=null &&
              this.parametrosModeloPorValorFrequenciaIn.equals(other.getParametrosModeloPorValorFrequenciaIn())));
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
        if (getParametrosModeloPorValorFrequenciaIn() != null) {
            _hashCode += getParametrosModeloPorValorFrequenciaIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaModeloPorValorFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ParametrosBuscarListaModeloPorValorFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosModeloPorValorFrequenciaIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ParametrosModeloPorValorFrequenciaIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">>ParametrosBuscarListaModeloPorValorFrequencia>ParametrosModeloPorValorFrequenciaIn"));
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
