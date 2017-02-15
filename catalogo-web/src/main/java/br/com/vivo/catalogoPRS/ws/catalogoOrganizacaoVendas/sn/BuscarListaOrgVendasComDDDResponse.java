/**
 * BuscarListaOrgVendasComDDDResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn;

public class BuscarListaOrgVendasComDDDResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDD resultadoBuscarListaOrgVendasComDDD;

    public BuscarListaOrgVendasComDDDResponse() {
    }

    public BuscarListaOrgVendasComDDDResponse(
           br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDD resultadoBuscarListaOrgVendasComDDD) {
           this.resultadoBuscarListaOrgVendasComDDD = resultadoBuscarListaOrgVendasComDDD;
    }


    /**
     * Gets the resultadoBuscarListaOrgVendasComDDD value for this BuscarListaOrgVendasComDDDResponse.
     * 
     * @return resultadoBuscarListaOrgVendasComDDD
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDD getResultadoBuscarListaOrgVendasComDDD() {
        return resultadoBuscarListaOrgVendasComDDD;
    }


    /**
     * Sets the resultadoBuscarListaOrgVendasComDDD value for this BuscarListaOrgVendasComDDDResponse.
     * 
     * @param resultadoBuscarListaOrgVendasComDDD
     */
    public void setResultadoBuscarListaOrgVendasComDDD(br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDD resultadoBuscarListaOrgVendasComDDD) {
        this.resultadoBuscarListaOrgVendasComDDD = resultadoBuscarListaOrgVendasComDDD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaOrgVendasComDDDResponse)) return false;
        BuscarListaOrgVendasComDDDResponse other = (BuscarListaOrgVendasComDDDResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaOrgVendasComDDD==null && other.getResultadoBuscarListaOrgVendasComDDD()==null) || 
             (this.resultadoBuscarListaOrgVendasComDDD!=null &&
              this.resultadoBuscarListaOrgVendasComDDD.equals(other.getResultadoBuscarListaOrgVendasComDDD())));
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
        if (getResultadoBuscarListaOrgVendasComDDD() != null) {
            _hashCode += getResultadoBuscarListaOrgVendasComDDD().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaOrgVendasComDDDResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", ">buscarListaOrgVendasComDDDResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaOrgVendasComDDD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", "ResultadoBuscarListaOrgVendasComDDD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", ">ResultadoBuscarListaOrgVendasComDDD"));
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
