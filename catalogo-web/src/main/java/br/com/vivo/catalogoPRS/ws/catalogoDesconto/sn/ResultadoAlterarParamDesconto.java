/**
 * ResultadoAlterarParamDesconto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public class ResultadoAlterarParamDesconto  implements java.io.Serializable {
    private java.lang.Long retornoDescontoAlteracao;

    public ResultadoAlterarParamDesconto() {
    }

    public ResultadoAlterarParamDesconto(
           java.lang.Long retornoDescontoAlteracao) {
           this.retornoDescontoAlteracao = retornoDescontoAlteracao;
    }


    /**
     * Gets the retornoDescontoAlteracao value for this ResultadoAlterarParamDesconto.
     * 
     * @return retornoDescontoAlteracao
     */
    public java.lang.Long getRetornoDescontoAlteracao() {
        return retornoDescontoAlteracao;
    }


    /**
     * Sets the retornoDescontoAlteracao value for this ResultadoAlterarParamDesconto.
     * 
     * @param retornoDescontoAlteracao
     */
    public void setRetornoDescontoAlteracao(java.lang.Long retornoDescontoAlteracao) {
        this.retornoDescontoAlteracao = retornoDescontoAlteracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoAlterarParamDesconto)) return false;
        ResultadoAlterarParamDesconto other = (ResultadoAlterarParamDesconto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.retornoDescontoAlteracao==null && other.getRetornoDescontoAlteracao()==null) || 
             (this.retornoDescontoAlteracao!=null &&
              this.retornoDescontoAlteracao.equals(other.getRetornoDescontoAlteracao())));
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
        if (getRetornoDescontoAlteracao() != null) {
            _hashCode += getRetornoDescontoAlteracao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoAlterarParamDesconto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">ResultadoAlterarParamDesconto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retornoDescontoAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "retornoDescontoAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
