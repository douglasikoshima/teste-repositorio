/**
 * AlterarMultimidiaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class AlterarMultimidiaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamAlterarMultimidia paramAlterarMultimidia;

    public AlterarMultimidiaRequest() {
    }

    public AlterarMultimidiaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamAlterarMultimidia paramAlterarMultimidia) {
           this.paramAlterarMultimidia = paramAlterarMultimidia;
    }


    /**
     * Gets the paramAlterarMultimidia value for this AlterarMultimidiaRequest.
     * 
     * @return paramAlterarMultimidia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamAlterarMultimidia getParamAlterarMultimidia() {
        return paramAlterarMultimidia;
    }


    /**
     * Sets the paramAlterarMultimidia value for this AlterarMultimidiaRequest.
     * 
     * @param paramAlterarMultimidia
     */
    public void setParamAlterarMultimidia(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamAlterarMultimidia paramAlterarMultimidia) {
        this.paramAlterarMultimidia = paramAlterarMultimidia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarMultimidiaRequest)) return false;
        AlterarMultimidiaRequest other = (AlterarMultimidiaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paramAlterarMultimidia==null && other.getParamAlterarMultimidia()==null) || 
             (this.paramAlterarMultimidia!=null &&
              this.paramAlterarMultimidia.equals(other.getParamAlterarMultimidia())));
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
        if (getParamAlterarMultimidia() != null) {
            _hashCode += getParamAlterarMultimidia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarMultimidiaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">alterarMultimidiaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramAlterarMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "ParamAlterarMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">ParamAlterarMultimidia"));
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
