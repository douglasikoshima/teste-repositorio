/**
 * ParametrosBuscarListaModeloPorTipoFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ParametrosBuscarListaModeloPorTipoFrequencia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTipoFrequenciaParametrosModeloPorTipoFrequenciaIn parametrosModeloPorTipoFrequenciaIn;

    public ParametrosBuscarListaModeloPorTipoFrequencia() {
    }

    public ParametrosBuscarListaModeloPorTipoFrequencia(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTipoFrequenciaParametrosModeloPorTipoFrequenciaIn parametrosModeloPorTipoFrequenciaIn) {
           this.parametrosModeloPorTipoFrequenciaIn = parametrosModeloPorTipoFrequenciaIn;
    }


    /**
     * Gets the parametrosModeloPorTipoFrequenciaIn value for this ParametrosBuscarListaModeloPorTipoFrequencia.
     * 
     * @return parametrosModeloPorTipoFrequenciaIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTipoFrequenciaParametrosModeloPorTipoFrequenciaIn getParametrosModeloPorTipoFrequenciaIn() {
        return parametrosModeloPorTipoFrequenciaIn;
    }


    /**
     * Sets the parametrosModeloPorTipoFrequenciaIn value for this ParametrosBuscarListaModeloPorTipoFrequencia.
     * 
     * @param parametrosModeloPorTipoFrequenciaIn
     */
    public void setParametrosModeloPorTipoFrequenciaIn(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTipoFrequenciaParametrosModeloPorTipoFrequenciaIn parametrosModeloPorTipoFrequenciaIn) {
        this.parametrosModeloPorTipoFrequenciaIn = parametrosModeloPorTipoFrequenciaIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaModeloPorTipoFrequencia)) return false;
        ParametrosBuscarListaModeloPorTipoFrequencia other = (ParametrosBuscarListaModeloPorTipoFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosModeloPorTipoFrequenciaIn==null && other.getParametrosModeloPorTipoFrequenciaIn()==null) || 
             (this.parametrosModeloPorTipoFrequenciaIn!=null &&
              this.parametrosModeloPorTipoFrequenciaIn.equals(other.getParametrosModeloPorTipoFrequenciaIn())));
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
        if (getParametrosModeloPorTipoFrequenciaIn() != null) {
            _hashCode += getParametrosModeloPorTipoFrequenciaIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaModeloPorTipoFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ParametrosBuscarListaModeloPorTipoFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosModeloPorTipoFrequenciaIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ParametrosModeloPorTipoFrequenciaIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">>ParametrosBuscarListaModeloPorTipoFrequencia>ParametrosModeloPorTipoFrequenciaIn"));
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
