/**
 * BuscarListaProdSemModeloVendaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class BuscarListaProdSemModeloVendaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosBuscarListaProdSemModeloVenda parametrosBuscarListaProdSemModeloVenda;

    public BuscarListaProdSemModeloVendaRequest() {
    }

    public BuscarListaProdSemModeloVendaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosBuscarListaProdSemModeloVenda parametrosBuscarListaProdSemModeloVenda) {
           this.parametrosBuscarListaProdSemModeloVenda = parametrosBuscarListaProdSemModeloVenda;
    }


    /**
     * Gets the parametrosBuscarListaProdSemModeloVenda value for this BuscarListaProdSemModeloVendaRequest.
     * 
     * @return parametrosBuscarListaProdSemModeloVenda
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosBuscarListaProdSemModeloVenda getParametrosBuscarListaProdSemModeloVenda() {
        return parametrosBuscarListaProdSemModeloVenda;
    }


    /**
     * Sets the parametrosBuscarListaProdSemModeloVenda value for this BuscarListaProdSemModeloVendaRequest.
     * 
     * @param parametrosBuscarListaProdSemModeloVenda
     */
    public void setParametrosBuscarListaProdSemModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosBuscarListaProdSemModeloVenda parametrosBuscarListaProdSemModeloVenda) {
        this.parametrosBuscarListaProdSemModeloVenda = parametrosBuscarListaProdSemModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaProdSemModeloVendaRequest)) return false;
        BuscarListaProdSemModeloVendaRequest other = (BuscarListaProdSemModeloVendaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaProdSemModeloVenda==null && other.getParametrosBuscarListaProdSemModeloVenda()==null) || 
             (this.parametrosBuscarListaProdSemModeloVenda!=null &&
              this.parametrosBuscarListaProdSemModeloVenda.equals(other.getParametrosBuscarListaProdSemModeloVenda())));
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
        if (getParametrosBuscarListaProdSemModeloVenda() != null) {
            _hashCode += getParametrosBuscarListaProdSemModeloVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaProdSemModeloVendaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">buscarListaProdSemModeloVendaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaProdSemModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ParametrosBuscarListaProdSemModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ParametrosBuscarListaProdSemModeloVenda"));
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
