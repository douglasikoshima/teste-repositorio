/**
 * ParametrosListarServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ParametrosListarServico  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosListarServicoParametrosServicoIn parametrosServicoIn;

    public ParametrosListarServico() {
    }

    public ParametrosListarServico(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosListarServicoParametrosServicoIn parametrosServicoIn) {
           this.parametrosServicoIn = parametrosServicoIn;
    }


    /**
     * Gets the parametrosServicoIn value for this ParametrosListarServico.
     * 
     * @return parametrosServicoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosListarServicoParametrosServicoIn getParametrosServicoIn() {
        return parametrosServicoIn;
    }


    /**
     * Sets the parametrosServicoIn value for this ParametrosListarServico.
     * 
     * @param parametrosServicoIn
     */
    public void setParametrosServicoIn(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosListarServicoParametrosServicoIn parametrosServicoIn) {
        this.parametrosServicoIn = parametrosServicoIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosListarServico)) return false;
        ParametrosListarServico other = (ParametrosListarServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosServicoIn==null && other.getParametrosServicoIn()==null) || 
             (this.parametrosServicoIn!=null &&
              this.parametrosServicoIn.equals(other.getParametrosServicoIn())));
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
        if (getParametrosServicoIn() != null) {
            _hashCode += getParametrosServicoIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosListarServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosListarServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosServicoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ParametrosServicoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>ParametrosListarServico>ParametrosServicoIn"));
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