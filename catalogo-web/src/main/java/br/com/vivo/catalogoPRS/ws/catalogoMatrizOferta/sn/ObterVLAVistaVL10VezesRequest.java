/**
 * ObterVLAVistaVL10VezesRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ObterVLAVistaVL10VezesRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosObterVLAVistaVL10Vezes parametrosObterVLAVistaVL10Vezes;

    public ObterVLAVistaVL10VezesRequest() {
    }

    public ObterVLAVistaVL10VezesRequest(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosObterVLAVistaVL10Vezes parametrosObterVLAVistaVL10Vezes) {
           this.parametrosObterVLAVistaVL10Vezes = parametrosObterVLAVistaVL10Vezes;
    }


    /**
     * Gets the parametrosObterVLAVistaVL10Vezes value for this ObterVLAVistaVL10VezesRequest.
     * 
     * @return parametrosObterVLAVistaVL10Vezes
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosObterVLAVistaVL10Vezes getParametrosObterVLAVistaVL10Vezes() {
        return parametrosObterVLAVistaVL10Vezes;
    }


    /**
     * Sets the parametrosObterVLAVistaVL10Vezes value for this ObterVLAVistaVL10VezesRequest.
     * 
     * @param parametrosObterVLAVistaVL10Vezes
     */
    public void setParametrosObterVLAVistaVL10Vezes(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosObterVLAVistaVL10Vezes parametrosObterVLAVistaVL10Vezes) {
        this.parametrosObterVLAVistaVL10Vezes = parametrosObterVLAVistaVL10Vezes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObterVLAVistaVL10VezesRequest)) return false;
        ObterVLAVistaVL10VezesRequest other = (ObterVLAVistaVL10VezesRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosObterVLAVistaVL10Vezes==null && other.getParametrosObterVLAVistaVL10Vezes()==null) || 
             (this.parametrosObterVLAVistaVL10Vezes!=null &&
              this.parametrosObterVLAVistaVL10Vezes.equals(other.getParametrosObterVLAVistaVL10Vezes())));
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
        if (getParametrosObterVLAVistaVL10Vezes() != null) {
            _hashCode += getParametrosObterVLAVistaVL10Vezes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObterVLAVistaVL10VezesRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">obterVLAVistaVL10VezesRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosObterVLAVistaVL10Vezes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ParametrosObterVLAVistaVL10Vezes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosObterVLAVistaVL10Vezes"));
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
