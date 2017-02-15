/**
 * ParametrosDisponibilizarProduto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ParametrosDisponibilizarProduto  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDisponibilizarProdutoParametrosDispProduto parametrosDispProduto;

    public ParametrosDisponibilizarProduto() {
    }

    public ParametrosDisponibilizarProduto(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDisponibilizarProdutoParametrosDispProduto parametrosDispProduto) {
           this.parametrosDispProduto = parametrosDispProduto;
    }


    /**
     * Gets the parametrosDispProduto value for this ParametrosDisponibilizarProduto.
     * 
     * @return parametrosDispProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDisponibilizarProdutoParametrosDispProduto getParametrosDispProduto() {
        return parametrosDispProduto;
    }


    /**
     * Sets the parametrosDispProduto value for this ParametrosDisponibilizarProduto.
     * 
     * @param parametrosDispProduto
     */
    public void setParametrosDispProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDisponibilizarProdutoParametrosDispProduto parametrosDispProduto) {
        this.parametrosDispProduto = parametrosDispProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosDisponibilizarProduto)) return false;
        ParametrosDisponibilizarProduto other = (ParametrosDisponibilizarProduto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosDispProduto==null && other.getParametrosDispProduto()==null) || 
             (this.parametrosDispProduto!=null &&
              this.parametrosDispProduto.equals(other.getParametrosDispProduto())));
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
        if (getParametrosDispProduto() != null) {
            _hashCode += getParametrosDispProduto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosDisponibilizarProduto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosDisponibilizarProduto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosDispProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ParametrosDispProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ParametrosDisponibilizarProduto>ParametrosDispProduto"));
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
