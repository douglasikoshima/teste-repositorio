/**
 * BuscarListaServicoParametrizacaoSemPagResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class BuscarListaServicoParametrizacaoSemPagResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoSemPag resultadoBuscarListaServicoParametrizacaoSemPag;

    public BuscarListaServicoParametrizacaoSemPagResponse() {
    }

    public BuscarListaServicoParametrizacaoSemPagResponse(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoSemPag resultadoBuscarListaServicoParametrizacaoSemPag) {
           this.resultadoBuscarListaServicoParametrizacaoSemPag = resultadoBuscarListaServicoParametrizacaoSemPag;
    }


    /**
     * Gets the resultadoBuscarListaServicoParametrizacaoSemPag value for this BuscarListaServicoParametrizacaoSemPagResponse.
     * 
     * @return resultadoBuscarListaServicoParametrizacaoSemPag
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoSemPag getResultadoBuscarListaServicoParametrizacaoSemPag() {
        return resultadoBuscarListaServicoParametrizacaoSemPag;
    }


    /**
     * Sets the resultadoBuscarListaServicoParametrizacaoSemPag value for this BuscarListaServicoParametrizacaoSemPagResponse.
     * 
     * @param resultadoBuscarListaServicoParametrizacaoSemPag
     */
    public void setResultadoBuscarListaServicoParametrizacaoSemPag(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoSemPag resultadoBuscarListaServicoParametrizacaoSemPag) {
        this.resultadoBuscarListaServicoParametrizacaoSemPag = resultadoBuscarListaServicoParametrizacaoSemPag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaServicoParametrizacaoSemPagResponse)) return false;
        BuscarListaServicoParametrizacaoSemPagResponse other = (BuscarListaServicoParametrizacaoSemPagResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaServicoParametrizacaoSemPag==null && other.getResultadoBuscarListaServicoParametrizacaoSemPag()==null) || 
             (this.resultadoBuscarListaServicoParametrizacaoSemPag!=null &&
              this.resultadoBuscarListaServicoParametrizacaoSemPag.equals(other.getResultadoBuscarListaServicoParametrizacaoSemPag())));
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
        if (getResultadoBuscarListaServicoParametrizacaoSemPag() != null) {
            _hashCode += getResultadoBuscarListaServicoParametrizacaoSemPag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaServicoParametrizacaoSemPagResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">buscarListaServicoParametrizacaoSemPagResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaServicoParametrizacaoSemPag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ResultadoBuscarListaServicoParametrizacaoSemPag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ResultadoBuscarListaServicoParametrizacaoSemPag"));
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
