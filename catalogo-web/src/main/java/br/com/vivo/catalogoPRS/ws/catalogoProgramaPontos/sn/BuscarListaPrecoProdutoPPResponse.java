/**
 * BuscarListaPrecoProdutoPPResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

public class BuscarListaPrecoProdutoPPResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPP resultBuscarPrecoProdutoPP;

    public BuscarListaPrecoProdutoPPResponse() {
    }

    public BuscarListaPrecoProdutoPPResponse(
           br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPP resultBuscarPrecoProdutoPP) {
           this.resultBuscarPrecoProdutoPP = resultBuscarPrecoProdutoPP;
    }


    /**
     * Gets the resultBuscarPrecoProdutoPP value for this BuscarListaPrecoProdutoPPResponse.
     * 
     * @return resultBuscarPrecoProdutoPP
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPP getResultBuscarPrecoProdutoPP() {
        return resultBuscarPrecoProdutoPP;
    }


    /**
     * Sets the resultBuscarPrecoProdutoPP value for this BuscarListaPrecoProdutoPPResponse.
     * 
     * @param resultBuscarPrecoProdutoPP
     */
    public void setResultBuscarPrecoProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPP resultBuscarPrecoProdutoPP) {
        this.resultBuscarPrecoProdutoPP = resultBuscarPrecoProdutoPP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaPrecoProdutoPPResponse)) return false;
        BuscarListaPrecoProdutoPPResponse other = (BuscarListaPrecoProdutoPPResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultBuscarPrecoProdutoPP==null && other.getResultBuscarPrecoProdutoPP()==null) || 
             (this.resultBuscarPrecoProdutoPP!=null &&
              this.resultBuscarPrecoProdutoPP.equals(other.getResultBuscarPrecoProdutoPP())));
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
        if (getResultBuscarPrecoProdutoPP() != null) {
            _hashCode += getResultBuscarPrecoProdutoPP().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaPrecoProdutoPPResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">buscarListaPrecoProdutoPPResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultBuscarPrecoProdutoPP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ResultBuscarPrecoProdutoPP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ResultBuscarPrecoProdutoPP"));
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
