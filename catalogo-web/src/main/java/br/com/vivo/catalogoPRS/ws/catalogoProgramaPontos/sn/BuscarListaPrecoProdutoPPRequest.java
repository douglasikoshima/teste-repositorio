/**
 * BuscarListaPrecoProdutoPPRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

public class BuscarListaPrecoProdutoPPRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamBuscarPrecoProdutoPP paramBuscarPrecoProdutoPP;

    public BuscarListaPrecoProdutoPPRequest() {
    }

    public BuscarListaPrecoProdutoPPRequest(
           br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamBuscarPrecoProdutoPP paramBuscarPrecoProdutoPP) {
           this.paramBuscarPrecoProdutoPP = paramBuscarPrecoProdutoPP;
    }


    /**
     * Gets the paramBuscarPrecoProdutoPP value for this BuscarListaPrecoProdutoPPRequest.
     * 
     * @return paramBuscarPrecoProdutoPP
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamBuscarPrecoProdutoPP getParamBuscarPrecoProdutoPP() {
        return paramBuscarPrecoProdutoPP;
    }


    /**
     * Sets the paramBuscarPrecoProdutoPP value for this BuscarListaPrecoProdutoPPRequest.
     * 
     * @param paramBuscarPrecoProdutoPP
     */
    public void setParamBuscarPrecoProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamBuscarPrecoProdutoPP paramBuscarPrecoProdutoPP) {
        this.paramBuscarPrecoProdutoPP = paramBuscarPrecoProdutoPP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaPrecoProdutoPPRequest)) return false;
        BuscarListaPrecoProdutoPPRequest other = (BuscarListaPrecoProdutoPPRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paramBuscarPrecoProdutoPP==null && other.getParamBuscarPrecoProdutoPP()==null) || 
             (this.paramBuscarPrecoProdutoPP!=null &&
              this.paramBuscarPrecoProdutoPP.equals(other.getParamBuscarPrecoProdutoPP())));
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
        if (getParamBuscarPrecoProdutoPP() != null) {
            _hashCode += getParamBuscarPrecoProdutoPP().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaPrecoProdutoPPRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">buscarListaPrecoProdutoPPRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramBuscarPrecoProdutoPP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ParamBuscarPrecoProdutoPP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ParamBuscarPrecoProdutoPP"));
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
