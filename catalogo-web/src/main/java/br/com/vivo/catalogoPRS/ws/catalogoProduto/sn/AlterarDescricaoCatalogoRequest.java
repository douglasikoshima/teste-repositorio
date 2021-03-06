/**
 * AlterarDescricaoCatalogoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class AlterarDescricaoCatalogoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAlterarDescricaoCatalogo parametrosAlterarDescricaoCatalogo;

    public AlterarDescricaoCatalogoRequest() {
    }

    public AlterarDescricaoCatalogoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAlterarDescricaoCatalogo parametrosAlterarDescricaoCatalogo) {
           this.parametrosAlterarDescricaoCatalogo = parametrosAlterarDescricaoCatalogo;
    }


    /**
     * Gets the parametrosAlterarDescricaoCatalogo value for this AlterarDescricaoCatalogoRequest.
     * 
     * @return parametrosAlterarDescricaoCatalogo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAlterarDescricaoCatalogo getParametrosAlterarDescricaoCatalogo() {
        return parametrosAlterarDescricaoCatalogo;
    }


    /**
     * Sets the parametrosAlterarDescricaoCatalogo value for this AlterarDescricaoCatalogoRequest.
     * 
     * @param parametrosAlterarDescricaoCatalogo
     */
    public void setParametrosAlterarDescricaoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAlterarDescricaoCatalogo parametrosAlterarDescricaoCatalogo) {
        this.parametrosAlterarDescricaoCatalogo = parametrosAlterarDescricaoCatalogo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarDescricaoCatalogoRequest)) return false;
        AlterarDescricaoCatalogoRequest other = (AlterarDescricaoCatalogoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarDescricaoCatalogo==null && other.getParametrosAlterarDescricaoCatalogo()==null) || 
             (this.parametrosAlterarDescricaoCatalogo!=null &&
              this.parametrosAlterarDescricaoCatalogo.equals(other.getParametrosAlterarDescricaoCatalogo())));
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
        if (getParametrosAlterarDescricaoCatalogo() != null) {
            _hashCode += getParametrosAlterarDescricaoCatalogo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarDescricaoCatalogoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">alterarDescricaoCatalogoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarDescricaoCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ParametrosAlterarDescricaoCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosAlterarDescricaoCatalogo"));
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
