/**
 * ObterVLAVistaVL10VezesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ObterVLAVistaVL10VezesResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoObterVLAVistaVL10Vezes resultadoObterVLAVistaVL10Vezes;

    public ObterVLAVistaVL10VezesResponse() {
    }

    public ObterVLAVistaVL10VezesResponse(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoObterVLAVistaVL10Vezes resultadoObterVLAVistaVL10Vezes) {
           this.resultadoObterVLAVistaVL10Vezes = resultadoObterVLAVistaVL10Vezes;
    }


    /**
     * Gets the resultadoObterVLAVistaVL10Vezes value for this ObterVLAVistaVL10VezesResponse.
     * 
     * @return resultadoObterVLAVistaVL10Vezes
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoObterVLAVistaVL10Vezes getResultadoObterVLAVistaVL10Vezes() {
        return resultadoObterVLAVistaVL10Vezes;
    }


    /**
     * Sets the resultadoObterVLAVistaVL10Vezes value for this ObterVLAVistaVL10VezesResponse.
     * 
     * @param resultadoObterVLAVistaVL10Vezes
     */
    public void setResultadoObterVLAVistaVL10Vezes(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoObterVLAVistaVL10Vezes resultadoObterVLAVistaVL10Vezes) {
        this.resultadoObterVLAVistaVL10Vezes = resultadoObterVLAVistaVL10Vezes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObterVLAVistaVL10VezesResponse)) return false;
        ObterVLAVistaVL10VezesResponse other = (ObterVLAVistaVL10VezesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoObterVLAVistaVL10Vezes==null && other.getResultadoObterVLAVistaVL10Vezes()==null) || 
             (this.resultadoObterVLAVistaVL10Vezes!=null &&
              this.resultadoObterVLAVistaVL10Vezes.equals(other.getResultadoObterVLAVistaVL10Vezes())));
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
        if (getResultadoObterVLAVistaVL10Vezes() != null) {
            _hashCode += getResultadoObterVLAVistaVL10Vezes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObterVLAVistaVL10VezesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">obterVLAVistaVL10VezesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoObterVLAVistaVL10Vezes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ResultadoObterVLAVistaVL10Vezes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoObterVLAVistaVL10Vezes"));
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
