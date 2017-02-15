/**
 * AlterarOfertaSapRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class AlterarOfertaSapRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosAlterarOfertaSap parametrosAlterarOfertaSap;

    public AlterarOfertaSapRequest() {
    }

    public AlterarOfertaSapRequest(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosAlterarOfertaSap parametrosAlterarOfertaSap) {
           this.parametrosAlterarOfertaSap = parametrosAlterarOfertaSap;
    }


    /**
     * Gets the parametrosAlterarOfertaSap value for this AlterarOfertaSapRequest.
     * 
     * @return parametrosAlterarOfertaSap
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosAlterarOfertaSap getParametrosAlterarOfertaSap() {
        return parametrosAlterarOfertaSap;
    }


    /**
     * Sets the parametrosAlterarOfertaSap value for this AlterarOfertaSapRequest.
     * 
     * @param parametrosAlterarOfertaSap
     */
    public void setParametrosAlterarOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosAlterarOfertaSap parametrosAlterarOfertaSap) {
        this.parametrosAlterarOfertaSap = parametrosAlterarOfertaSap;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarOfertaSapRequest)) return false;
        AlterarOfertaSapRequest other = (AlterarOfertaSapRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarOfertaSap==null && other.getParametrosAlterarOfertaSap()==null) || 
             (this.parametrosAlterarOfertaSap!=null &&
              this.parametrosAlterarOfertaSap.equals(other.getParametrosAlterarOfertaSap())));
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
        if (getParametrosAlterarOfertaSap() != null) {
            _hashCode += getParametrosAlterarOfertaSap().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarOfertaSapRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">alterarOfertaSapRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarOfertaSap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ParametrosAlterarOfertaSap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">ParametrosAlterarOfertaSap"));
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
