/**
 * ExportarServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ExportarServicoRequest  implements java.io.Serializable {
    private long[] parametrosExportarServico;

    public ExportarServicoRequest() {
    }

    public ExportarServicoRequest(
           long[] parametrosExportarServico) {
           this.parametrosExportarServico = parametrosExportarServico;
    }


    /**
     * Gets the parametrosExportarServico value for this ExportarServicoRequest.
     * 
     * @return parametrosExportarServico
     */
    public long[] getParametrosExportarServico() {
        return parametrosExportarServico;
    }


    /**
     * Sets the parametrosExportarServico value for this ExportarServicoRequest.
     * 
     * @param parametrosExportarServico
     */
    public void setParametrosExportarServico(long[] parametrosExportarServico) {
        this.parametrosExportarServico = parametrosExportarServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExportarServicoRequest)) return false;
        ExportarServicoRequest other = (ExportarServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosExportarServico==null && other.getParametrosExportarServico()==null) || 
             (this.parametrosExportarServico!=null &&
              java.util.Arrays.equals(this.parametrosExportarServico, other.getParametrosExportarServico())));
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
        if (getParametrosExportarServico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParametrosExportarServico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParametrosExportarServico(), i);
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
        new org.apache.axis.description.TypeDesc(ExportarServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">exportarServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosExportarServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ParametrosExportarServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosExportarServico"));
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
