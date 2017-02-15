/**
 * AssociarProdutoModeloVendaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class AssociarProdutoModeloVendaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosAssociarProdutoModeloVenda parametrosAssociarProdutoModeloVenda;

    public AssociarProdutoModeloVendaRequest() {
    }

    public AssociarProdutoModeloVendaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosAssociarProdutoModeloVenda parametrosAssociarProdutoModeloVenda) {
           this.parametrosAssociarProdutoModeloVenda = parametrosAssociarProdutoModeloVenda;
    }


    /**
     * Gets the parametrosAssociarProdutoModeloVenda value for this AssociarProdutoModeloVendaRequest.
     * 
     * @return parametrosAssociarProdutoModeloVenda
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosAssociarProdutoModeloVenda getParametrosAssociarProdutoModeloVenda() {
        return parametrosAssociarProdutoModeloVenda;
    }


    /**
     * Sets the parametrosAssociarProdutoModeloVenda value for this AssociarProdutoModeloVendaRequest.
     * 
     * @param parametrosAssociarProdutoModeloVenda
     */
    public void setParametrosAssociarProdutoModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosAssociarProdutoModeloVenda parametrosAssociarProdutoModeloVenda) {
        this.parametrosAssociarProdutoModeloVenda = parametrosAssociarProdutoModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssociarProdutoModeloVendaRequest)) return false;
        AssociarProdutoModeloVendaRequest other = (AssociarProdutoModeloVendaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAssociarProdutoModeloVenda==null && other.getParametrosAssociarProdutoModeloVenda()==null) || 
             (this.parametrosAssociarProdutoModeloVenda!=null &&
              this.parametrosAssociarProdutoModeloVenda.equals(other.getParametrosAssociarProdutoModeloVenda())));
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
        if (getParametrosAssociarProdutoModeloVenda() != null) {
            _hashCode += getParametrosAssociarProdutoModeloVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssociarProdutoModeloVendaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">associarProdutoModeloVendaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAssociarProdutoModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ParametrosAssociarProdutoModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ParametrosAssociarProdutoModeloVenda"));
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
