/**
 * ExcluirModeloVendaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class ExcluirModeloVendaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosExcluirModeloVenda parametrosExcluirModeloVenda;

    public ExcluirModeloVendaRequest() {
    }

    public ExcluirModeloVendaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosExcluirModeloVenda parametrosExcluirModeloVenda) {
           this.parametrosExcluirModeloVenda = parametrosExcluirModeloVenda;
    }


    /**
     * Gets the parametrosExcluirModeloVenda value for this ExcluirModeloVendaRequest.
     * 
     * @return parametrosExcluirModeloVenda
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosExcluirModeloVenda getParametrosExcluirModeloVenda() {
        return parametrosExcluirModeloVenda;
    }


    /**
     * Sets the parametrosExcluirModeloVenda value for this ExcluirModeloVendaRequest.
     * 
     * @param parametrosExcluirModeloVenda
     */
    public void setParametrosExcluirModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosExcluirModeloVenda parametrosExcluirModeloVenda) {
        this.parametrosExcluirModeloVenda = parametrosExcluirModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExcluirModeloVendaRequest)) return false;
        ExcluirModeloVendaRequest other = (ExcluirModeloVendaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosExcluirModeloVenda==null && other.getParametrosExcluirModeloVenda()==null) || 
             (this.parametrosExcluirModeloVenda!=null &&
              this.parametrosExcluirModeloVenda.equals(other.getParametrosExcluirModeloVenda())));
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
        if (getParametrosExcluirModeloVenda() != null) {
            _hashCode += getParametrosExcluirModeloVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExcluirModeloVendaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">excluirModeloVendaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosExcluirModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ParametrosExcluirModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ParametrosExcluirModeloVenda"));
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
