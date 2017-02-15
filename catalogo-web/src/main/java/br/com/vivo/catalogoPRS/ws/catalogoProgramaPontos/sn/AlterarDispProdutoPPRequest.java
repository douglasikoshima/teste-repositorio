/**
 * AlterarDispProdutoPPRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

public class AlterarDispProdutoPPRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamAlterarDispProdutoPP paramAlterarDispProdutoPP;

    public AlterarDispProdutoPPRequest() {
    }

    public AlterarDispProdutoPPRequest(
           br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamAlterarDispProdutoPP paramAlterarDispProdutoPP) {
           this.paramAlterarDispProdutoPP = paramAlterarDispProdutoPP;
    }


    /**
     * Gets the paramAlterarDispProdutoPP value for this AlterarDispProdutoPPRequest.
     * 
     * @return paramAlterarDispProdutoPP
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamAlterarDispProdutoPP getParamAlterarDispProdutoPP() {
        return paramAlterarDispProdutoPP;
    }


    /**
     * Sets the paramAlterarDispProdutoPP value for this AlterarDispProdutoPPRequest.
     * 
     * @param paramAlterarDispProdutoPP
     */
    public void setParamAlterarDispProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamAlterarDispProdutoPP paramAlterarDispProdutoPP) {
        this.paramAlterarDispProdutoPP = paramAlterarDispProdutoPP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarDispProdutoPPRequest)) return false;
        AlterarDispProdutoPPRequest other = (AlterarDispProdutoPPRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paramAlterarDispProdutoPP==null && other.getParamAlterarDispProdutoPP()==null) || 
             (this.paramAlterarDispProdutoPP!=null &&
              this.paramAlterarDispProdutoPP.equals(other.getParamAlterarDispProdutoPP())));
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
        if (getParamAlterarDispProdutoPP() != null) {
            _hashCode += getParamAlterarDispProdutoPP().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarDispProdutoPPRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">alterarDispProdutoPPRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramAlterarDispProdutoPP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ParamAlterarDispProdutoPP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ParamAlterarDispProdutoPP"));
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
