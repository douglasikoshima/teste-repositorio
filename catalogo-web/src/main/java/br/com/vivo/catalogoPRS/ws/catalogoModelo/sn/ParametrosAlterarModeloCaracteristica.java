/**
 * ParametrosAlterarModeloCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosAlterarModeloCaracteristica  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAlterarModeloCaracteristicaParametrosAssociarModeloCaracteristica parametrosAssociarModeloCaracteristica;

    public ParametrosAlterarModeloCaracteristica() {
    }

    public ParametrosAlterarModeloCaracteristica(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAlterarModeloCaracteristicaParametrosAssociarModeloCaracteristica parametrosAssociarModeloCaracteristica) {
           this.parametrosAssociarModeloCaracteristica = parametrosAssociarModeloCaracteristica;
    }


    /**
     * Gets the parametrosAssociarModeloCaracteristica value for this ParametrosAlterarModeloCaracteristica.
     * 
     * @return parametrosAssociarModeloCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAlterarModeloCaracteristicaParametrosAssociarModeloCaracteristica getParametrosAssociarModeloCaracteristica() {
        return parametrosAssociarModeloCaracteristica;
    }


    /**
     * Sets the parametrosAssociarModeloCaracteristica value for this ParametrosAlterarModeloCaracteristica.
     * 
     * @param parametrosAssociarModeloCaracteristica
     */
    public void setParametrosAssociarModeloCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAlterarModeloCaracteristicaParametrosAssociarModeloCaracteristica parametrosAssociarModeloCaracteristica) {
        this.parametrosAssociarModeloCaracteristica = parametrosAssociarModeloCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarModeloCaracteristica)) return false;
        ParametrosAlterarModeloCaracteristica other = (ParametrosAlterarModeloCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAssociarModeloCaracteristica==null && other.getParametrosAssociarModeloCaracteristica()==null) || 
             (this.parametrosAssociarModeloCaracteristica!=null &&
              this.parametrosAssociarModeloCaracteristica.equals(other.getParametrosAssociarModeloCaracteristica())));
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
        if (getParametrosAssociarModeloCaracteristica() != null) {
            _hashCode += getParametrosAssociarModeloCaracteristica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarModeloCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosAlterarModeloCaracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAssociarModeloCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosAssociarModeloCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosAlterarModeloCaracteristica>ParametrosAssociarModeloCaracteristica"));
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
