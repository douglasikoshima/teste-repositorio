/**
 * ExportarModeloRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ExportarModeloRequest  implements java.io.Serializable {
    private long[] parametrosExportarModelo;

    public ExportarModeloRequest() {
    }

    public ExportarModeloRequest(
           long[] parametrosExportarModelo) {
           this.parametrosExportarModelo = parametrosExportarModelo;
    }


    /**
     * Gets the parametrosExportarModelo value for this ExportarModeloRequest.
     * 
     * @return parametrosExportarModelo
     */
    public long[] getParametrosExportarModelo() {
        return parametrosExportarModelo;
    }


    /**
     * Sets the parametrosExportarModelo value for this ExportarModeloRequest.
     * 
     * @param parametrosExportarModelo
     */
    public void setParametrosExportarModelo(long[] parametrosExportarModelo) {
        this.parametrosExportarModelo = parametrosExportarModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExportarModeloRequest)) return false;
        ExportarModeloRequest other = (ExportarModeloRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosExportarModelo==null && other.getParametrosExportarModelo()==null) || 
             (this.parametrosExportarModelo!=null &&
              java.util.Arrays.equals(this.parametrosExportarModelo, other.getParametrosExportarModelo())));
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
        if (getParametrosExportarModelo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParametrosExportarModelo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParametrosExportarModelo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExportarModeloRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">exportarModeloRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosExportarModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosExportarModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosExportarModelo"));
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
