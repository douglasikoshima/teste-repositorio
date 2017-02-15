/**
 * AlterarModeloVendaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class AlterarModeloVendaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosAlterarModeloVenda parametrosAlterarModeloVenda;

    public AlterarModeloVendaRequest() {
    }

    public AlterarModeloVendaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosAlterarModeloVenda parametrosAlterarModeloVenda) {
           this.parametrosAlterarModeloVenda = parametrosAlterarModeloVenda;
    }


    /**
     * Gets the parametrosAlterarModeloVenda value for this AlterarModeloVendaRequest.
     * 
     * @return parametrosAlterarModeloVenda
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosAlterarModeloVenda getParametrosAlterarModeloVenda() {
        return parametrosAlterarModeloVenda;
    }


    /**
     * Sets the parametrosAlterarModeloVenda value for this AlterarModeloVendaRequest.
     * 
     * @param parametrosAlterarModeloVenda
     */
    public void setParametrosAlterarModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ParametrosAlterarModeloVenda parametrosAlterarModeloVenda) {
        this.parametrosAlterarModeloVenda = parametrosAlterarModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarModeloVendaRequest)) return false;
        AlterarModeloVendaRequest other = (AlterarModeloVendaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarModeloVenda==null && other.getParametrosAlterarModeloVenda()==null) || 
             (this.parametrosAlterarModeloVenda!=null &&
              this.parametrosAlterarModeloVenda.equals(other.getParametrosAlterarModeloVenda())));
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
        if (getParametrosAlterarModeloVenda() != null) {
            _hashCode += getParametrosAlterarModeloVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarModeloVendaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">alterarModeloVendaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ParametrosAlterarModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ParametrosAlterarModeloVenda"));
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
