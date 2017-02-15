/**
 * ParametrosBuscarAssociacaoTecnologiaTpFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ParametrosBuscarAssociacaoTecnologiaTpFrequencia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarAssociacaoTecnologiaTpFrequenciaParametrosBuscarAssociacaoIn parametrosBuscarAssociacaoIn;

    public ParametrosBuscarAssociacaoTecnologiaTpFrequencia() {
    }

    public ParametrosBuscarAssociacaoTecnologiaTpFrequencia(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarAssociacaoTecnologiaTpFrequenciaParametrosBuscarAssociacaoIn parametrosBuscarAssociacaoIn) {
           this.parametrosBuscarAssociacaoIn = parametrosBuscarAssociacaoIn;
    }


    /**
     * Gets the parametrosBuscarAssociacaoIn value for this ParametrosBuscarAssociacaoTecnologiaTpFrequencia.
     * 
     * @return parametrosBuscarAssociacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarAssociacaoTecnologiaTpFrequenciaParametrosBuscarAssociacaoIn getParametrosBuscarAssociacaoIn() {
        return parametrosBuscarAssociacaoIn;
    }


    /**
     * Sets the parametrosBuscarAssociacaoIn value for this ParametrosBuscarAssociacaoTecnologiaTpFrequencia.
     * 
     * @param parametrosBuscarAssociacaoIn
     */
    public void setParametrosBuscarAssociacaoIn(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarAssociacaoTecnologiaTpFrequenciaParametrosBuscarAssociacaoIn parametrosBuscarAssociacaoIn) {
        this.parametrosBuscarAssociacaoIn = parametrosBuscarAssociacaoIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarAssociacaoTecnologiaTpFrequencia)) return false;
        ParametrosBuscarAssociacaoTecnologiaTpFrequencia other = (ParametrosBuscarAssociacaoTecnologiaTpFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarAssociacaoIn==null && other.getParametrosBuscarAssociacaoIn()==null) || 
             (this.parametrosBuscarAssociacaoIn!=null &&
              this.parametrosBuscarAssociacaoIn.equals(other.getParametrosBuscarAssociacaoIn())));
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
        if (getParametrosBuscarAssociacaoIn() != null) {
            _hashCode += getParametrosBuscarAssociacaoIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarAssociacaoTecnologiaTpFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ParametrosBuscarAssociacaoTecnologiaTpFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarAssociacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ParametrosBuscarAssociacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">>ParametrosBuscarAssociacaoTecnologiaTpFrequencia>ParametrosBuscarAssociacaoIn"));
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
