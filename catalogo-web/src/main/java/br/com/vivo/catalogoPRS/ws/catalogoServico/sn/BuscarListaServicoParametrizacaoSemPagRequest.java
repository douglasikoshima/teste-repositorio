/**
 * BuscarListaServicoParametrizacaoSemPagRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class BuscarListaServicoParametrizacaoSemPagRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoParametrizacaoSemPag parametrosBuscarListaServicoParametrizacaoSemPag;

    public BuscarListaServicoParametrizacaoSemPagRequest() {
    }

    public BuscarListaServicoParametrizacaoSemPagRequest(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoParametrizacaoSemPag parametrosBuscarListaServicoParametrizacaoSemPag) {
           this.parametrosBuscarListaServicoParametrizacaoSemPag = parametrosBuscarListaServicoParametrizacaoSemPag;
    }


    /**
     * Gets the parametrosBuscarListaServicoParametrizacaoSemPag value for this BuscarListaServicoParametrizacaoSemPagRequest.
     * 
     * @return parametrosBuscarListaServicoParametrizacaoSemPag
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoParametrizacaoSemPag getParametrosBuscarListaServicoParametrizacaoSemPag() {
        return parametrosBuscarListaServicoParametrizacaoSemPag;
    }


    /**
     * Sets the parametrosBuscarListaServicoParametrizacaoSemPag value for this BuscarListaServicoParametrizacaoSemPagRequest.
     * 
     * @param parametrosBuscarListaServicoParametrizacaoSemPag
     */
    public void setParametrosBuscarListaServicoParametrizacaoSemPag(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoParametrizacaoSemPag parametrosBuscarListaServicoParametrizacaoSemPag) {
        this.parametrosBuscarListaServicoParametrizacaoSemPag = parametrosBuscarListaServicoParametrizacaoSemPag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaServicoParametrizacaoSemPagRequest)) return false;
        BuscarListaServicoParametrizacaoSemPagRequest other = (BuscarListaServicoParametrizacaoSemPagRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaServicoParametrizacaoSemPag==null && other.getParametrosBuscarListaServicoParametrizacaoSemPag()==null) || 
             (this.parametrosBuscarListaServicoParametrizacaoSemPag!=null &&
              this.parametrosBuscarListaServicoParametrizacaoSemPag.equals(other.getParametrosBuscarListaServicoParametrizacaoSemPag())));
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
        if (getParametrosBuscarListaServicoParametrizacaoSemPag() != null) {
            _hashCode += getParametrosBuscarListaServicoParametrizacaoSemPag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaServicoParametrizacaoSemPagRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">buscarListaServicoParametrizacaoSemPagRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaServicoParametrizacaoSemPag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ParametrosBuscarListaServicoParametrizacaoSemPag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosBuscarListaServicoParametrizacaoSemPag"));
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
