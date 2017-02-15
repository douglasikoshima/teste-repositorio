/**
 * AlterarFormaCondPagtoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn;

public class AlterarFormaCondPagtoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ParametrosAlterarFormaCondPagto parametrosAlterarFormaCondPagto;

    public AlterarFormaCondPagtoRequest() {
    }

    public AlterarFormaCondPagtoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ParametrosAlterarFormaCondPagto parametrosAlterarFormaCondPagto) {
           this.parametrosAlterarFormaCondPagto = parametrosAlterarFormaCondPagto;
    }


    /**
     * Gets the parametrosAlterarFormaCondPagto value for this AlterarFormaCondPagtoRequest.
     * 
     * @return parametrosAlterarFormaCondPagto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ParametrosAlterarFormaCondPagto getParametrosAlterarFormaCondPagto() {
        return parametrosAlterarFormaCondPagto;
    }


    /**
     * Sets the parametrosAlterarFormaCondPagto value for this AlterarFormaCondPagtoRequest.
     * 
     * @param parametrosAlterarFormaCondPagto
     */
    public void setParametrosAlterarFormaCondPagto(br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ParametrosAlterarFormaCondPagto parametrosAlterarFormaCondPagto) {
        this.parametrosAlterarFormaCondPagto = parametrosAlterarFormaCondPagto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarFormaCondPagtoRequest)) return false;
        AlterarFormaCondPagtoRequest other = (AlterarFormaCondPagtoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarFormaCondPagto==null && other.getParametrosAlterarFormaCondPagto()==null) || 
             (this.parametrosAlterarFormaCondPagto!=null &&
              this.parametrosAlterarFormaCondPagto.equals(other.getParametrosAlterarFormaCondPagto())));
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
        if (getParametrosAlterarFormaCondPagto() != null) {
            _hashCode += getParametrosAlterarFormaCondPagto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarFormaCondPagtoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", ">alterarFormaCondPagtoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarFormaCondPagto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "ParametrosAlterarFormaCondPagto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", ">ParametrosAlterarFormaCondPagto"));
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
