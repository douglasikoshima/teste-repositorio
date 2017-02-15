/**
 * BuscarGrupoProdutoPorIdRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class BuscarGrupoProdutoPorIdRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloPorId buscarModeloPorId;

    public BuscarGrupoProdutoPorIdRequest() {
    }

    public BuscarGrupoProdutoPorIdRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloPorId buscarModeloPorId) {
           this.buscarModeloPorId = buscarModeloPorId;
    }


    /**
     * Gets the buscarModeloPorId value for this BuscarGrupoProdutoPorIdRequest.
     * 
     * @return buscarModeloPorId
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloPorId getBuscarModeloPorId() {
        return buscarModeloPorId;
    }


    /**
     * Sets the buscarModeloPorId value for this BuscarGrupoProdutoPorIdRequest.
     * 
     * @param buscarModeloPorId
     */
    public void setBuscarModeloPorId(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloPorId buscarModeloPorId) {
        this.buscarModeloPorId = buscarModeloPorId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarGrupoProdutoPorIdRequest)) return false;
        BuscarGrupoProdutoPorIdRequest other = (BuscarGrupoProdutoPorIdRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.buscarModeloPorId==null && other.getBuscarModeloPorId()==null) || 
             (this.buscarModeloPorId!=null &&
              this.buscarModeloPorId.equals(other.getBuscarModeloPorId())));
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
        if (getBuscarModeloPorId() != null) {
            _hashCode += getBuscarModeloPorId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarGrupoProdutoPorIdRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">buscarGrupoProdutoPorIdRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buscarModeloPorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "BuscarModeloPorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">BuscarModeloPorId"));
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
