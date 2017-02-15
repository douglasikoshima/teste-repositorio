/**
 * BuscarListaServicoComOfertaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class BuscarListaServicoComOfertaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaServicoComOferta resultadoBuscarListaServicoComOferta;

    public BuscarListaServicoComOfertaResponse() {
    }

    public BuscarListaServicoComOfertaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaServicoComOferta resultadoBuscarListaServicoComOferta) {
           this.resultadoBuscarListaServicoComOferta = resultadoBuscarListaServicoComOferta;
    }


    /**
     * Gets the resultadoBuscarListaServicoComOferta value for this BuscarListaServicoComOfertaResponse.
     * 
     * @return resultadoBuscarListaServicoComOferta
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaServicoComOferta getResultadoBuscarListaServicoComOferta() {
        return resultadoBuscarListaServicoComOferta;
    }


    /**
     * Sets the resultadoBuscarListaServicoComOferta value for this BuscarListaServicoComOfertaResponse.
     * 
     * @param resultadoBuscarListaServicoComOferta
     */
    public void setResultadoBuscarListaServicoComOferta(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaServicoComOferta resultadoBuscarListaServicoComOferta) {
        this.resultadoBuscarListaServicoComOferta = resultadoBuscarListaServicoComOferta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaServicoComOfertaResponse)) return false;
        BuscarListaServicoComOfertaResponse other = (BuscarListaServicoComOfertaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaServicoComOferta==null && other.getResultadoBuscarListaServicoComOferta()==null) || 
             (this.resultadoBuscarListaServicoComOferta!=null &&
              this.resultadoBuscarListaServicoComOferta.equals(other.getResultadoBuscarListaServicoComOferta())));
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
        if (getResultadoBuscarListaServicoComOferta() != null) {
            _hashCode += getResultadoBuscarListaServicoComOferta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaServicoComOfertaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">buscarListaServicoComOfertaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaServicoComOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ResultadoBuscarListaServicoComOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ResultadoBuscarListaServicoComOferta"));
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
