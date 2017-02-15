/**
 * BuscarListaProdSemModeloVendaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class BuscarListaProdSemModeloVendaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarListaProdSemModeloVenda resultadoBuscarListaProdSemModeloVenda;

    public BuscarListaProdSemModeloVendaResponse() {
    }

    public BuscarListaProdSemModeloVendaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarListaProdSemModeloVenda resultadoBuscarListaProdSemModeloVenda) {
           this.resultadoBuscarListaProdSemModeloVenda = resultadoBuscarListaProdSemModeloVenda;
    }


    /**
     * Gets the resultadoBuscarListaProdSemModeloVenda value for this BuscarListaProdSemModeloVendaResponse.
     * 
     * @return resultadoBuscarListaProdSemModeloVenda
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarListaProdSemModeloVenda getResultadoBuscarListaProdSemModeloVenda() {
        return resultadoBuscarListaProdSemModeloVenda;
    }


    /**
     * Sets the resultadoBuscarListaProdSemModeloVenda value for this BuscarListaProdSemModeloVendaResponse.
     * 
     * @param resultadoBuscarListaProdSemModeloVenda
     */
    public void setResultadoBuscarListaProdSemModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarListaProdSemModeloVenda resultadoBuscarListaProdSemModeloVenda) {
        this.resultadoBuscarListaProdSemModeloVenda = resultadoBuscarListaProdSemModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaProdSemModeloVendaResponse)) return false;
        BuscarListaProdSemModeloVendaResponse other = (BuscarListaProdSemModeloVendaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaProdSemModeloVenda==null && other.getResultadoBuscarListaProdSemModeloVenda()==null) || 
             (this.resultadoBuscarListaProdSemModeloVenda!=null &&
              this.resultadoBuscarListaProdSemModeloVenda.equals(other.getResultadoBuscarListaProdSemModeloVenda())));
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
        if (getResultadoBuscarListaProdSemModeloVenda() != null) {
            _hashCode += getResultadoBuscarListaProdSemModeloVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaProdSemModeloVendaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">buscarListaProdSemModeloVendaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaProdSemModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ResultadoBuscarListaProdSemModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ResultadoBuscarListaProdSemModeloVenda"));
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
