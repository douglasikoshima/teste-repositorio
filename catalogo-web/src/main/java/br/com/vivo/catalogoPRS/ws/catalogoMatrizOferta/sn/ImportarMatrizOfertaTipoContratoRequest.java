/**
 * ImportarMatrizOfertaTipoContratoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ImportarMatrizOfertaTipoContratoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosImportarMatrizOfertaTipoContrato parametrosImportarMatrizOfertaTipoContrato;

    public ImportarMatrizOfertaTipoContratoRequest() {
    }

    public ImportarMatrizOfertaTipoContratoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosImportarMatrizOfertaTipoContrato parametrosImportarMatrizOfertaTipoContrato) {
           this.parametrosImportarMatrizOfertaTipoContrato = parametrosImportarMatrizOfertaTipoContrato;
    }


    /**
     * Gets the parametrosImportarMatrizOfertaTipoContrato value for this ImportarMatrizOfertaTipoContratoRequest.
     * 
     * @return parametrosImportarMatrizOfertaTipoContrato
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosImportarMatrizOfertaTipoContrato getParametrosImportarMatrizOfertaTipoContrato() {
        return parametrosImportarMatrizOfertaTipoContrato;
    }


    /**
     * Sets the parametrosImportarMatrizOfertaTipoContrato value for this ImportarMatrizOfertaTipoContratoRequest.
     * 
     * @param parametrosImportarMatrizOfertaTipoContrato
     */
    public void setParametrosImportarMatrizOfertaTipoContrato(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosImportarMatrizOfertaTipoContrato parametrosImportarMatrizOfertaTipoContrato) {
        this.parametrosImportarMatrizOfertaTipoContrato = parametrosImportarMatrizOfertaTipoContrato;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ImportarMatrizOfertaTipoContratoRequest)) return false;
        ImportarMatrizOfertaTipoContratoRequest other = (ImportarMatrizOfertaTipoContratoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosImportarMatrizOfertaTipoContrato==null && other.getParametrosImportarMatrizOfertaTipoContrato()==null) || 
             (this.parametrosImportarMatrizOfertaTipoContrato!=null &&
              this.parametrosImportarMatrizOfertaTipoContrato.equals(other.getParametrosImportarMatrizOfertaTipoContrato())));
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
        if (getParametrosImportarMatrizOfertaTipoContrato() != null) {
            _hashCode += getParametrosImportarMatrizOfertaTipoContrato().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ImportarMatrizOfertaTipoContratoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">importarMatrizOfertaTipoContratoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosImportarMatrizOfertaTipoContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ParametrosImportarMatrizOfertaTipoContrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosImportarMatrizOfertaTipoContrato"));
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
