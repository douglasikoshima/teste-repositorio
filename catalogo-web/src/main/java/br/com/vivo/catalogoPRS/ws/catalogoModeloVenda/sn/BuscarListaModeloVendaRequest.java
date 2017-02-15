/**
 * BuscarListaModeloVendaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class BuscarListaModeloVendaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosBuscarListaModeloVenda parametrosBuscarListaModeloVenda;

    public BuscarListaModeloVendaRequest() {
    }

    public BuscarListaModeloVendaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosBuscarListaModeloVenda parametrosBuscarListaModeloVenda) {
           this.parametrosBuscarListaModeloVenda = parametrosBuscarListaModeloVenda;
    }


    /**
     * Gets the parametrosBuscarListaModeloVenda value for this BuscarListaModeloVendaRequest.
     * 
     * @return parametrosBuscarListaModeloVenda
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosBuscarListaModeloVenda getParametrosBuscarListaModeloVenda() {
        return parametrosBuscarListaModeloVenda;
    }


    /**
     * Sets the parametrosBuscarListaModeloVenda value for this BuscarListaModeloVendaRequest.
     * 
     * @param parametrosBuscarListaModeloVenda
     */
    public void setParametrosBuscarListaModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosBuscarListaModeloVenda parametrosBuscarListaModeloVenda) {
        this.parametrosBuscarListaModeloVenda = parametrosBuscarListaModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaModeloVendaRequest)) return false;
        BuscarListaModeloVendaRequest other = (BuscarListaModeloVendaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaModeloVenda==null && other.getParametrosBuscarListaModeloVenda()==null) || 
             (this.parametrosBuscarListaModeloVenda!=null &&
              this.parametrosBuscarListaModeloVenda.equals(other.getParametrosBuscarListaModeloVenda())));
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
        if (getParametrosBuscarListaModeloVenda() != null) {
            _hashCode += getParametrosBuscarListaModeloVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaModeloVendaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">buscarListaModeloVendaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ParametrosBuscarListaModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ParametrosBuscarListaModeloVenda"));
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
