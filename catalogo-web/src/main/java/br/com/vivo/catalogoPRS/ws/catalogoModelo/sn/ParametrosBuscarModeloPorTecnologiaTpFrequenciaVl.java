/**
 * ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn parametrosModelosPorVlFrequenciaIn;

    public ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl() {
    }

    public ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn parametrosModelosPorVlFrequenciaIn) {
           this.parametrosModelosPorVlFrequenciaIn = parametrosModelosPorVlFrequenciaIn;
    }


    /**
     * Gets the parametrosModelosPorVlFrequenciaIn value for this ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl.
     * 
     * @return parametrosModelosPorVlFrequenciaIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn getParametrosModelosPorVlFrequenciaIn() {
        return parametrosModelosPorVlFrequenciaIn;
    }


    /**
     * Sets the parametrosModelosPorVlFrequenciaIn value for this ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl.
     * 
     * @param parametrosModelosPorVlFrequenciaIn
     */
    public void setParametrosModelosPorVlFrequenciaIn(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn parametrosModelosPorVlFrequenciaIn) {
        this.parametrosModelosPorVlFrequenciaIn = parametrosModelosPorVlFrequenciaIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl)) return false;
        ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl other = (ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosModelosPorVlFrequenciaIn==null && other.getParametrosModelosPorVlFrequenciaIn()==null) || 
             (this.parametrosModelosPorVlFrequenciaIn!=null &&
              this.parametrosModelosPorVlFrequenciaIn.equals(other.getParametrosModelosPorVlFrequenciaIn())));
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
        if (getParametrosModelosPorVlFrequenciaIn() != null) {
            _hashCode += getParametrosModelosPorVlFrequenciaIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosModelosPorVlFrequenciaIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosModelosPorVlFrequenciaIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl>ParametrosModelosPorVlFrequenciaIn"));
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
