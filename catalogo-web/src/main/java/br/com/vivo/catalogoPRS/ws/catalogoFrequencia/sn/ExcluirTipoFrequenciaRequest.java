/**
 * ExcluirTipoFrequenciaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ExcluirTipoFrequenciaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosExcluirTipoFrequencia parametrosExcluirTipoFrequencia;

    public ExcluirTipoFrequenciaRequest() {
    }

    public ExcluirTipoFrequenciaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosExcluirTipoFrequencia parametrosExcluirTipoFrequencia) {
           this.parametrosExcluirTipoFrequencia = parametrosExcluirTipoFrequencia;
    }


    /**
     * Gets the parametrosExcluirTipoFrequencia value for this ExcluirTipoFrequenciaRequest.
     * 
     * @return parametrosExcluirTipoFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosExcluirTipoFrequencia getParametrosExcluirTipoFrequencia() {
        return parametrosExcluirTipoFrequencia;
    }


    /**
     * Sets the parametrosExcluirTipoFrequencia value for this ExcluirTipoFrequenciaRequest.
     * 
     * @param parametrosExcluirTipoFrequencia
     */
    public void setParametrosExcluirTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosExcluirTipoFrequencia parametrosExcluirTipoFrequencia) {
        this.parametrosExcluirTipoFrequencia = parametrosExcluirTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExcluirTipoFrequenciaRequest)) return false;
        ExcluirTipoFrequenciaRequest other = (ExcluirTipoFrequenciaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosExcluirTipoFrequencia==null && other.getParametrosExcluirTipoFrequencia()==null) || 
             (this.parametrosExcluirTipoFrequencia!=null &&
              this.parametrosExcluirTipoFrequencia.equals(other.getParametrosExcluirTipoFrequencia())));
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
        if (getParametrosExcluirTipoFrequencia() != null) {
            _hashCode += getParametrosExcluirTipoFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExcluirTipoFrequenciaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">excluirTipoFrequenciaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosExcluirTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ParametrosExcluirTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ParametrosExcluirTipoFrequencia"));
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
