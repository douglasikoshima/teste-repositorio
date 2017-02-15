/**
 * DesassociarListaMultimidiaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class DesassociarListaMultimidiaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParametrosDesassociarListaMultimidia parametrosDesassociarListaMultimidia;

    public DesassociarListaMultimidiaRequest() {
    }

    public DesassociarListaMultimidiaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParametrosDesassociarListaMultimidia parametrosDesassociarListaMultimidia) {
           this.parametrosDesassociarListaMultimidia = parametrosDesassociarListaMultimidia;
    }


    /**
     * Gets the parametrosDesassociarListaMultimidia value for this DesassociarListaMultimidiaRequest.
     * 
     * @return parametrosDesassociarListaMultimidia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParametrosDesassociarListaMultimidia getParametrosDesassociarListaMultimidia() {
        return parametrosDesassociarListaMultimidia;
    }


    /**
     * Sets the parametrosDesassociarListaMultimidia value for this DesassociarListaMultimidiaRequest.
     * 
     * @param parametrosDesassociarListaMultimidia
     */
    public void setParametrosDesassociarListaMultimidia(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParametrosDesassociarListaMultimidia parametrosDesassociarListaMultimidia) {
        this.parametrosDesassociarListaMultimidia = parametrosDesassociarListaMultimidia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DesassociarListaMultimidiaRequest)) return false;
        DesassociarListaMultimidiaRequest other = (DesassociarListaMultimidiaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosDesassociarListaMultimidia==null && other.getParametrosDesassociarListaMultimidia()==null) || 
             (this.parametrosDesassociarListaMultimidia!=null &&
              this.parametrosDesassociarListaMultimidia.equals(other.getParametrosDesassociarListaMultimidia())));
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
        if (getParametrosDesassociarListaMultimidia() != null) {
            _hashCode += getParametrosDesassociarListaMultimidia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DesassociarListaMultimidiaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">desassociarListaMultimidiaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosDesassociarListaMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "ParametrosDesassociarListaMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">ParametrosDesassociarListaMultimidia"));
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
