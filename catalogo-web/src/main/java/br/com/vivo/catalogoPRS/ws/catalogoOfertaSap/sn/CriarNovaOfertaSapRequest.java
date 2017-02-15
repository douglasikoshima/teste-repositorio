/**
 * CriarNovaOfertaSapRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class CriarNovaOfertaSapRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosCriarNovaOfertaSap parametrosCriarNovaOfertaSap;

    public CriarNovaOfertaSapRequest() {
    }

    public CriarNovaOfertaSapRequest(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosCriarNovaOfertaSap parametrosCriarNovaOfertaSap) {
           this.parametrosCriarNovaOfertaSap = parametrosCriarNovaOfertaSap;
    }


    /**
     * Gets the parametrosCriarNovaOfertaSap value for this CriarNovaOfertaSapRequest.
     * 
     * @return parametrosCriarNovaOfertaSap
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosCriarNovaOfertaSap getParametrosCriarNovaOfertaSap() {
        return parametrosCriarNovaOfertaSap;
    }


    /**
     * Sets the parametrosCriarNovaOfertaSap value for this CriarNovaOfertaSapRequest.
     * 
     * @param parametrosCriarNovaOfertaSap
     */
    public void setParametrosCriarNovaOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosCriarNovaOfertaSap parametrosCriarNovaOfertaSap) {
        this.parametrosCriarNovaOfertaSap = parametrosCriarNovaOfertaSap;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CriarNovaOfertaSapRequest)) return false;
        CriarNovaOfertaSapRequest other = (CriarNovaOfertaSapRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosCriarNovaOfertaSap==null && other.getParametrosCriarNovaOfertaSap()==null) || 
             (this.parametrosCriarNovaOfertaSap!=null &&
              this.parametrosCriarNovaOfertaSap.equals(other.getParametrosCriarNovaOfertaSap())));
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
        if (getParametrosCriarNovaOfertaSap() != null) {
            _hashCode += getParametrosCriarNovaOfertaSap().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CriarNovaOfertaSapRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">criarNovaOfertaSapRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosCriarNovaOfertaSap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ParametrosCriarNovaOfertaSap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">ParametrosCriarNovaOfertaSap"));
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
