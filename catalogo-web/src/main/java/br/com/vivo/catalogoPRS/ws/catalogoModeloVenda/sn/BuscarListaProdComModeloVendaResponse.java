/**
 * BuscarListaProdComModeloVendaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class BuscarListaProdComModeloVendaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarListaProdComModeloVenda resultadoBuscarListaProdComModeloVenda;

    public BuscarListaProdComModeloVendaResponse() {
    }

    public BuscarListaProdComModeloVendaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarListaProdComModeloVenda resultadoBuscarListaProdComModeloVenda) {
           this.resultadoBuscarListaProdComModeloVenda = resultadoBuscarListaProdComModeloVenda;
    }


    /**
     * Gets the resultadoBuscarListaProdComModeloVenda value for this BuscarListaProdComModeloVendaResponse.
     * 
     * @return resultadoBuscarListaProdComModeloVenda
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarListaProdComModeloVenda getResultadoBuscarListaProdComModeloVenda() {
        return resultadoBuscarListaProdComModeloVenda;
    }


    /**
     * Sets the resultadoBuscarListaProdComModeloVenda value for this BuscarListaProdComModeloVendaResponse.
     * 
     * @param resultadoBuscarListaProdComModeloVenda
     */
    public void setResultadoBuscarListaProdComModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarListaProdComModeloVenda resultadoBuscarListaProdComModeloVenda) {
        this.resultadoBuscarListaProdComModeloVenda = resultadoBuscarListaProdComModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaProdComModeloVendaResponse)) return false;
        BuscarListaProdComModeloVendaResponse other = (BuscarListaProdComModeloVendaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaProdComModeloVenda==null && other.getResultadoBuscarListaProdComModeloVenda()==null) || 
             (this.resultadoBuscarListaProdComModeloVenda!=null &&
              this.resultadoBuscarListaProdComModeloVenda.equals(other.getResultadoBuscarListaProdComModeloVenda())));
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
        if (getResultadoBuscarListaProdComModeloVenda() != null) {
            _hashCode += getResultadoBuscarListaProdComModeloVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaProdComModeloVendaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">buscarListaProdComModeloVendaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaProdComModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ResultadoBuscarListaProdComModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ResultadoBuscarListaProdComModeloVenda"));
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
