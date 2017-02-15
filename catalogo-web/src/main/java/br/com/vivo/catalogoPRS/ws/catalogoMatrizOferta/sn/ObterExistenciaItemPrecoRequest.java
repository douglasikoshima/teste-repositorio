/**
 * ObterExistenciaItemPrecoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ObterExistenciaItemPrecoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosObterExistenciaItemPreco parametrosObterExistenciaItemPreco;

    public ObterExistenciaItemPrecoRequest() {
    }

    public ObterExistenciaItemPrecoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosObterExistenciaItemPreco parametrosObterExistenciaItemPreco) {
           this.parametrosObterExistenciaItemPreco = parametrosObterExistenciaItemPreco;
    }


    /**
     * Gets the parametrosObterExistenciaItemPreco value for this ObterExistenciaItemPrecoRequest.
     * 
     * @return parametrosObterExistenciaItemPreco
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosObterExistenciaItemPreco getParametrosObterExistenciaItemPreco() {
        return parametrosObterExistenciaItemPreco;
    }


    /**
     * Sets the parametrosObterExistenciaItemPreco value for this ObterExistenciaItemPrecoRequest.
     * 
     * @param parametrosObterExistenciaItemPreco
     */
    public void setParametrosObterExistenciaItemPreco(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosObterExistenciaItemPreco parametrosObterExistenciaItemPreco) {
        this.parametrosObterExistenciaItemPreco = parametrosObterExistenciaItemPreco;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObterExistenciaItemPrecoRequest)) return false;
        ObterExistenciaItemPrecoRequest other = (ObterExistenciaItemPrecoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosObterExistenciaItemPreco==null && other.getParametrosObterExistenciaItemPreco()==null) || 
             (this.parametrosObterExistenciaItemPreco!=null &&
              this.parametrosObterExistenciaItemPreco.equals(other.getParametrosObterExistenciaItemPreco())));
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
        if (getParametrosObterExistenciaItemPreco() != null) {
            _hashCode += getParametrosObterExistenciaItemPreco().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObterExistenciaItemPrecoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">obterExistenciaItemPrecoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosObterExistenciaItemPreco");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ParametrosObterExistenciaItemPreco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosObterExistenciaItemPreco"));
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
