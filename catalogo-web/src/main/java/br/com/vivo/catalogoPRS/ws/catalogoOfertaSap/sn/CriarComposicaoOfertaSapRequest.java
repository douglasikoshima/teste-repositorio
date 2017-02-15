/**
 * CriarComposicaoOfertaSapRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class CriarComposicaoOfertaSapRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosCriarComposicaoOferta parametrosCriarComposicaoOferta;

    public CriarComposicaoOfertaSapRequest() {
    }

    public CriarComposicaoOfertaSapRequest(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosCriarComposicaoOferta parametrosCriarComposicaoOferta) {
           this.parametrosCriarComposicaoOferta = parametrosCriarComposicaoOferta;
    }


    /**
     * Gets the parametrosCriarComposicaoOferta value for this CriarComposicaoOfertaSapRequest.
     * 
     * @return parametrosCriarComposicaoOferta
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosCriarComposicaoOferta getParametrosCriarComposicaoOferta() {
        return parametrosCriarComposicaoOferta;
    }


    /**
     * Sets the parametrosCriarComposicaoOferta value for this CriarComposicaoOfertaSapRequest.
     * 
     * @param parametrosCriarComposicaoOferta
     */
    public void setParametrosCriarComposicaoOferta(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosCriarComposicaoOferta parametrosCriarComposicaoOferta) {
        this.parametrosCriarComposicaoOferta = parametrosCriarComposicaoOferta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CriarComposicaoOfertaSapRequest)) return false;
        CriarComposicaoOfertaSapRequest other = (CriarComposicaoOfertaSapRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosCriarComposicaoOferta==null && other.getParametrosCriarComposicaoOferta()==null) || 
             (this.parametrosCriarComposicaoOferta!=null &&
              this.parametrosCriarComposicaoOferta.equals(other.getParametrosCriarComposicaoOferta())));
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
        if (getParametrosCriarComposicaoOferta() != null) {
            _hashCode += getParametrosCriarComposicaoOferta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CriarComposicaoOfertaSapRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">criarComposicaoOfertaSapRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosCriarComposicaoOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ParametrosCriarComposicaoOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">ParametrosCriarComposicaoOferta"));
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
