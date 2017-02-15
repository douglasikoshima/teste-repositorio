/**
 * ParametrosAlterarDescricaoCatalogo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ParametrosAlterarDescricaoCatalogo  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAlterarDescricaoCatalogoDescricaoCatalogo descricaoCatalogo;

    public ParametrosAlterarDescricaoCatalogo() {
    }

    public ParametrosAlterarDescricaoCatalogo(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAlterarDescricaoCatalogoDescricaoCatalogo descricaoCatalogo) {
           this.descricaoCatalogo = descricaoCatalogo;
    }


    /**
     * Gets the descricaoCatalogo value for this ParametrosAlterarDescricaoCatalogo.
     * 
     * @return descricaoCatalogo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAlterarDescricaoCatalogoDescricaoCatalogo getDescricaoCatalogo() {
        return descricaoCatalogo;
    }


    /**
     * Sets the descricaoCatalogo value for this ParametrosAlterarDescricaoCatalogo.
     * 
     * @param descricaoCatalogo
     */
    public void setDescricaoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAlterarDescricaoCatalogoDescricaoCatalogo descricaoCatalogo) {
        this.descricaoCatalogo = descricaoCatalogo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarDescricaoCatalogo)) return false;
        ParametrosAlterarDescricaoCatalogo other = (ParametrosAlterarDescricaoCatalogo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descricaoCatalogo==null && other.getDescricaoCatalogo()==null) || 
             (this.descricaoCatalogo!=null &&
              this.descricaoCatalogo.equals(other.getDescricaoCatalogo())));
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
        if (getDescricaoCatalogo() != null) {
            _hashCode += getDescricaoCatalogo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarDescricaoCatalogo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosAlterarDescricaoCatalogo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricaoCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "DescricaoCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ParametrosAlterarDescricaoCatalogo>DescricaoCatalogo"));
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
