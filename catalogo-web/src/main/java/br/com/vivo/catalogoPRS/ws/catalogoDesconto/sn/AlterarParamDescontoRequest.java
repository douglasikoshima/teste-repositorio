/**
 * AlterarParamDescontoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public class AlterarParamDescontoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarParamDesconto parametrosAlterarParamDesconto;

    public AlterarParamDescontoRequest() {
    }

    public AlterarParamDescontoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarParamDesconto parametrosAlterarParamDesconto) {
           this.parametrosAlterarParamDesconto = parametrosAlterarParamDesconto;
    }


    /**
     * Gets the parametrosAlterarParamDesconto value for this AlterarParamDescontoRequest.
     * 
     * @return parametrosAlterarParamDesconto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarParamDesconto getParametrosAlterarParamDesconto() {
        return parametrosAlterarParamDesconto;
    }


    /**
     * Sets the parametrosAlterarParamDesconto value for this AlterarParamDescontoRequest.
     * 
     * @param parametrosAlterarParamDesconto
     */
    public void setParametrosAlterarParamDesconto(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarParamDesconto parametrosAlterarParamDesconto) {
        this.parametrosAlterarParamDesconto = parametrosAlterarParamDesconto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarParamDescontoRequest)) return false;
        AlterarParamDescontoRequest other = (AlterarParamDescontoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarParamDesconto==null && other.getParametrosAlterarParamDesconto()==null) || 
             (this.parametrosAlterarParamDesconto!=null &&
              this.parametrosAlterarParamDesconto.equals(other.getParametrosAlterarParamDesconto())));
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
        if (getParametrosAlterarParamDesconto() != null) {
            _hashCode += getParametrosAlterarParamDesconto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarParamDescontoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">alterarParamDescontoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarParamDesconto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "ParametrosAlterarParamDesconto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">ParametrosAlterarParamDesconto"));
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
