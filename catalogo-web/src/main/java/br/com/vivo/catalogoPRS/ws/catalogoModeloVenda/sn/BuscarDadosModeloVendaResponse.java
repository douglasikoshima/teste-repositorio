/**
 * BuscarDadosModeloVendaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class BuscarDadosModeloVendaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarDadosModeloVenda resultadoBuscarDadosModeloVenda;

    public BuscarDadosModeloVendaResponse() {
    }

    public BuscarDadosModeloVendaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarDadosModeloVenda resultadoBuscarDadosModeloVenda) {
           this.resultadoBuscarDadosModeloVenda = resultadoBuscarDadosModeloVenda;
    }


    /**
     * Gets the resultadoBuscarDadosModeloVenda value for this BuscarDadosModeloVendaResponse.
     * 
     * @return resultadoBuscarDadosModeloVenda
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarDadosModeloVenda getResultadoBuscarDadosModeloVenda() {
        return resultadoBuscarDadosModeloVenda;
    }


    /**
     * Sets the resultadoBuscarDadosModeloVenda value for this BuscarDadosModeloVendaResponse.
     * 
     * @param resultadoBuscarDadosModeloVenda
     */
    public void setResultadoBuscarDadosModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarDadosModeloVenda resultadoBuscarDadosModeloVenda) {
        this.resultadoBuscarDadosModeloVenda = resultadoBuscarDadosModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarDadosModeloVendaResponse)) return false;
        BuscarDadosModeloVendaResponse other = (BuscarDadosModeloVendaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarDadosModeloVenda==null && other.getResultadoBuscarDadosModeloVenda()==null) || 
             (this.resultadoBuscarDadosModeloVenda!=null &&
              this.resultadoBuscarDadosModeloVenda.equals(other.getResultadoBuscarDadosModeloVenda())));
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
        if (getResultadoBuscarDadosModeloVenda() != null) {
            _hashCode += getResultadoBuscarDadosModeloVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarDadosModeloVendaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">buscarDadosModeloVendaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarDadosModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ResultadoBuscarDadosModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ResultadoBuscarDadosModeloVenda"));
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
