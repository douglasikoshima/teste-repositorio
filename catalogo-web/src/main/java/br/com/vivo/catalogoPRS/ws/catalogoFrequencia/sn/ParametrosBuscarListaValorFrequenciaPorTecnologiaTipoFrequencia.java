/**
 * ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaParametrosVlFrequenciaIn parametrosVlFrequenciaIn;

    public ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia() {
    }

    public ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaParametrosVlFrequenciaIn parametrosVlFrequenciaIn) {
           this.parametrosVlFrequenciaIn = parametrosVlFrequenciaIn;
    }


    /**
     * Gets the parametrosVlFrequenciaIn value for this ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia.
     * 
     * @return parametrosVlFrequenciaIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaParametrosVlFrequenciaIn getParametrosVlFrequenciaIn() {
        return parametrosVlFrequenciaIn;
    }


    /**
     * Sets the parametrosVlFrequenciaIn value for this ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia.
     * 
     * @param parametrosVlFrequenciaIn
     */
    public void setParametrosVlFrequenciaIn(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaParametrosVlFrequenciaIn parametrosVlFrequenciaIn) {
        this.parametrosVlFrequenciaIn = parametrosVlFrequenciaIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia)) return false;
        ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia other = (ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosVlFrequenciaIn==null && other.getParametrosVlFrequenciaIn()==null) || 
             (this.parametrosVlFrequenciaIn!=null &&
              this.parametrosVlFrequenciaIn.equals(other.getParametrosVlFrequenciaIn())));
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
        if (getParametrosVlFrequenciaIn() != null) {
            _hashCode += getParametrosVlFrequenciaIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosVlFrequenciaIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ParametrosVlFrequenciaIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">>ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia>ParametrosVlFrequenciaIn"));
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
