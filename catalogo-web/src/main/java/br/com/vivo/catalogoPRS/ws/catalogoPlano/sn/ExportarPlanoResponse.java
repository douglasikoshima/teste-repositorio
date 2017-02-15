/**
 * ExportarPlanoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ExportarPlanoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlano resultadoExportarPlano;

    public ExportarPlanoResponse() {
    }

    public ExportarPlanoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlano resultadoExportarPlano) {
           this.resultadoExportarPlano = resultadoExportarPlano;
    }


    /**
     * Gets the resultadoExportarPlano value for this ExportarPlanoResponse.
     * 
     * @return resultadoExportarPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlano getResultadoExportarPlano() {
        return resultadoExportarPlano;
    }


    /**
     * Sets the resultadoExportarPlano value for this ExportarPlanoResponse.
     * 
     * @param resultadoExportarPlano
     */
    public void setResultadoExportarPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlano resultadoExportarPlano) {
        this.resultadoExportarPlano = resultadoExportarPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExportarPlanoResponse)) return false;
        ExportarPlanoResponse other = (ExportarPlanoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoExportarPlano==null && other.getResultadoExportarPlano()==null) || 
             (this.resultadoExportarPlano!=null &&
              this.resultadoExportarPlano.equals(other.getResultadoExportarPlano())));
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
        if (getResultadoExportarPlano() != null) {
            _hashCode += getResultadoExportarPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExportarPlanoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">exportarPlanoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoExportarPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ResultadoExportarPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ResultadoExportarPlano"));
        elemField.setMinOccurs(0);
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
