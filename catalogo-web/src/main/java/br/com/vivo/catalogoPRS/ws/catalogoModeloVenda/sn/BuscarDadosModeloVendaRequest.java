/**
 * BuscarDadosModeloVendaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class BuscarDadosModeloVendaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosBuscarDadosModeloVenda parametrosBuscarDadosModeloVenda;

    public BuscarDadosModeloVendaRequest() {
    }

    public BuscarDadosModeloVendaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosBuscarDadosModeloVenda parametrosBuscarDadosModeloVenda) {
           this.parametrosBuscarDadosModeloVenda = parametrosBuscarDadosModeloVenda;
    }


    /**
     * Gets the parametrosBuscarDadosModeloVenda value for this BuscarDadosModeloVendaRequest.
     * 
     * @return parametrosBuscarDadosModeloVenda
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosBuscarDadosModeloVenda getParametrosBuscarDadosModeloVenda() {
        return parametrosBuscarDadosModeloVenda;
    }


    /**
     * Sets the parametrosBuscarDadosModeloVenda value for this BuscarDadosModeloVendaRequest.
     * 
     * @param parametrosBuscarDadosModeloVenda
     */
    public void setParametrosBuscarDadosModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosBuscarDadosModeloVenda parametrosBuscarDadosModeloVenda) {
        this.parametrosBuscarDadosModeloVenda = parametrosBuscarDadosModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarDadosModeloVendaRequest)) return false;
        BuscarDadosModeloVendaRequest other = (BuscarDadosModeloVendaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarDadosModeloVenda==null && other.getParametrosBuscarDadosModeloVenda()==null) || 
             (this.parametrosBuscarDadosModeloVenda!=null &&
              this.parametrosBuscarDadosModeloVenda.equals(other.getParametrosBuscarDadosModeloVenda())));
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
        if (getParametrosBuscarDadosModeloVenda() != null) {
            _hashCode += getParametrosBuscarDadosModeloVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarDadosModeloVendaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">buscarDadosModeloVendaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarDadosModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ParametrosBuscarDadosModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ParametrosBuscarDadosModeloVenda"));
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
