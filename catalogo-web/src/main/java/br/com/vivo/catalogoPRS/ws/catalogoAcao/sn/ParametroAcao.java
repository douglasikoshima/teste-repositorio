/**
 * ParametroAcao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcao.sn;

public class ParametroAcao  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ParametroAcaoInDisponivel inDisponivel;

    public ParametroAcao() {
    }

    public ParametroAcao(
           br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ParametroAcaoInDisponivel inDisponivel) {
           this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the inDisponivel value for this ParametroAcao.
     * 
     * @return inDisponivel
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ParametroAcaoInDisponivel getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ParametroAcao.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ParametroAcaoInDisponivel inDisponivel) {
        this.inDisponivel = inDisponivel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametroAcao)) return false;
        ParametroAcao other = (ParametroAcao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel())));
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
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametroAcao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", ">ParametroAcao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "inDisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", ">>ParametroAcao>inDisponivel"));
        elemField.setMinOccurs(0);
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
