/**
 * CriarTipoFrequenciaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class CriarTipoFrequenciaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosCriarTipoFrequencia parametrosCriarTipoFrequencia;

    public CriarTipoFrequenciaRequest() {
    }

    public CriarTipoFrequenciaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosCriarTipoFrequencia parametrosCriarTipoFrequencia) {
           this.parametrosCriarTipoFrequencia = parametrosCriarTipoFrequencia;
    }


    /**
     * Gets the parametrosCriarTipoFrequencia value for this CriarTipoFrequenciaRequest.
     * 
     * @return parametrosCriarTipoFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosCriarTipoFrequencia getParametrosCriarTipoFrequencia() {
        return parametrosCriarTipoFrequencia;
    }


    /**
     * Sets the parametrosCriarTipoFrequencia value for this CriarTipoFrequenciaRequest.
     * 
     * @param parametrosCriarTipoFrequencia
     */
    public void setParametrosCriarTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosCriarTipoFrequencia parametrosCriarTipoFrequencia) {
        this.parametrosCriarTipoFrequencia = parametrosCriarTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CriarTipoFrequenciaRequest)) return false;
        CriarTipoFrequenciaRequest other = (CriarTipoFrequenciaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosCriarTipoFrequencia==null && other.getParametrosCriarTipoFrequencia()==null) || 
             (this.parametrosCriarTipoFrequencia!=null &&
              this.parametrosCriarTipoFrequencia.equals(other.getParametrosCriarTipoFrequencia())));
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
        if (getParametrosCriarTipoFrequencia() != null) {
            _hashCode += getParametrosCriarTipoFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CriarTipoFrequenciaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">criarTipoFrequenciaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosCriarTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ParametrosCriarTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ParametrosCriarTipoFrequencia"));
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
