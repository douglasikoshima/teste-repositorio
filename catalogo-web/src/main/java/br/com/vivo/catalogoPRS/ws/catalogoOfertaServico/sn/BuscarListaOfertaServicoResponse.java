/**
 * BuscarListaOfertaServicoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class BuscarListaOfertaServicoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaOfertaServico resultadoBuscarListaOfertaServico;

    public BuscarListaOfertaServicoResponse() {
    }

    public BuscarListaOfertaServicoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaOfertaServico resultadoBuscarListaOfertaServico) {
           this.resultadoBuscarListaOfertaServico = resultadoBuscarListaOfertaServico;
    }


    /**
     * Gets the resultadoBuscarListaOfertaServico value for this BuscarListaOfertaServicoResponse.
     * 
     * @return resultadoBuscarListaOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaOfertaServico getResultadoBuscarListaOfertaServico() {
        return resultadoBuscarListaOfertaServico;
    }


    /**
     * Sets the resultadoBuscarListaOfertaServico value for this BuscarListaOfertaServicoResponse.
     * 
     * @param resultadoBuscarListaOfertaServico
     */
    public void setResultadoBuscarListaOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaOfertaServico resultadoBuscarListaOfertaServico) {
        this.resultadoBuscarListaOfertaServico = resultadoBuscarListaOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaOfertaServicoResponse)) return false;
        BuscarListaOfertaServicoResponse other = (BuscarListaOfertaServicoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaOfertaServico==null && other.getResultadoBuscarListaOfertaServico()==null) || 
             (this.resultadoBuscarListaOfertaServico!=null &&
              this.resultadoBuscarListaOfertaServico.equals(other.getResultadoBuscarListaOfertaServico())));
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
        if (getResultadoBuscarListaOfertaServico() != null) {
            _hashCode += getResultadoBuscarListaOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaOfertaServicoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">buscarListaOfertaServicoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ResultadoBuscarListaOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ResultadoBuscarListaOfertaServico"));
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
