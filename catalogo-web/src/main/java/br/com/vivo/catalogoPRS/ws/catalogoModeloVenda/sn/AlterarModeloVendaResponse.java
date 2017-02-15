/**
 * AlterarModeloVendaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class AlterarModeloVendaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoAlterarModeloVenda resultadoAlterarModeloVenda;

    public AlterarModeloVendaResponse() {
    }

    public AlterarModeloVendaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoAlterarModeloVenda resultadoAlterarModeloVenda) {
           this.resultadoAlterarModeloVenda = resultadoAlterarModeloVenda;
    }


    /**
     * Gets the resultadoAlterarModeloVenda value for this AlterarModeloVendaResponse.
     * 
     * @return resultadoAlterarModeloVenda
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoAlterarModeloVenda getResultadoAlterarModeloVenda() {
        return resultadoAlterarModeloVenda;
    }


    /**
     * Sets the resultadoAlterarModeloVenda value for this AlterarModeloVendaResponse.
     * 
     * @param resultadoAlterarModeloVenda
     */
    public void setResultadoAlterarModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoAlterarModeloVenda resultadoAlterarModeloVenda) {
        this.resultadoAlterarModeloVenda = resultadoAlterarModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarModeloVendaResponse)) return false;
        AlterarModeloVendaResponse other = (AlterarModeloVendaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoAlterarModeloVenda==null && other.getResultadoAlterarModeloVenda()==null) || 
             (this.resultadoAlterarModeloVenda!=null &&
              this.resultadoAlterarModeloVenda.equals(other.getResultadoAlterarModeloVenda())));
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
        if (getResultadoAlterarModeloVenda() != null) {
            _hashCode += getResultadoAlterarModeloVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarModeloVendaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">alterarModeloVendaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoAlterarModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ResultadoAlterarModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ResultadoAlterarModeloVenda"));
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
