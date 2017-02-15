/**
 * CarregarDispProdutoPPRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

public class CarregarDispProdutoPPRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamCarregarDispProdutoPP paramCarregarDispProdutoPP;

    public CarregarDispProdutoPPRequest() {
    }

    public CarregarDispProdutoPPRequest(
           br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamCarregarDispProdutoPP paramCarregarDispProdutoPP) {
           this.paramCarregarDispProdutoPP = paramCarregarDispProdutoPP;
    }


    /**
     * Gets the paramCarregarDispProdutoPP value for this CarregarDispProdutoPPRequest.
     * 
     * @return paramCarregarDispProdutoPP
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamCarregarDispProdutoPP getParamCarregarDispProdutoPP() {
        return paramCarregarDispProdutoPP;
    }


    /**
     * Sets the paramCarregarDispProdutoPP value for this CarregarDispProdutoPPRequest.
     * 
     * @param paramCarregarDispProdutoPP
     */
    public void setParamCarregarDispProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamCarregarDispProdutoPP paramCarregarDispProdutoPP) {
        this.paramCarregarDispProdutoPP = paramCarregarDispProdutoPP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CarregarDispProdutoPPRequest)) return false;
        CarregarDispProdutoPPRequest other = (CarregarDispProdutoPPRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paramCarregarDispProdutoPP==null && other.getParamCarregarDispProdutoPP()==null) || 
             (this.paramCarregarDispProdutoPP!=null &&
              this.paramCarregarDispProdutoPP.equals(other.getParamCarregarDispProdutoPP())));
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
        if (getParamCarregarDispProdutoPP() != null) {
            _hashCode += getParamCarregarDispProdutoPP().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CarregarDispProdutoPPRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">carregarDispProdutoPPRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramCarregarDispProdutoPP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ParamCarregarDispProdutoPP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ParamCarregarDispProdutoPP"));
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
