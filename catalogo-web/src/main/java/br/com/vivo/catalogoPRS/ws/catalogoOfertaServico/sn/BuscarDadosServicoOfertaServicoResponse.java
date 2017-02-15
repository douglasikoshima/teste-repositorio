/**
 * BuscarDadosServicoOfertaServicoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class BuscarDadosServicoOfertaServicoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServico resultadoBuscarDadosServicoOfertaServico;

    public BuscarDadosServicoOfertaServicoResponse() {
    }

    public BuscarDadosServicoOfertaServicoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServico resultadoBuscarDadosServicoOfertaServico) {
           this.resultadoBuscarDadosServicoOfertaServico = resultadoBuscarDadosServicoOfertaServico;
    }


    /**
     * Gets the resultadoBuscarDadosServicoOfertaServico value for this BuscarDadosServicoOfertaServicoResponse.
     * 
     * @return resultadoBuscarDadosServicoOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServico getResultadoBuscarDadosServicoOfertaServico() {
        return resultadoBuscarDadosServicoOfertaServico;
    }


    /**
     * Sets the resultadoBuscarDadosServicoOfertaServico value for this BuscarDadosServicoOfertaServicoResponse.
     * 
     * @param resultadoBuscarDadosServicoOfertaServico
     */
    public void setResultadoBuscarDadosServicoOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServico resultadoBuscarDadosServicoOfertaServico) {
        this.resultadoBuscarDadosServicoOfertaServico = resultadoBuscarDadosServicoOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarDadosServicoOfertaServicoResponse)) return false;
        BuscarDadosServicoOfertaServicoResponse other = (BuscarDadosServicoOfertaServicoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarDadosServicoOfertaServico==null && other.getResultadoBuscarDadosServicoOfertaServico()==null) || 
             (this.resultadoBuscarDadosServicoOfertaServico!=null &&
              this.resultadoBuscarDadosServicoOfertaServico.equals(other.getResultadoBuscarDadosServicoOfertaServico())));
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
        if (getResultadoBuscarDadosServicoOfertaServico() != null) {
            _hashCode += getResultadoBuscarDadosServicoOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarDadosServicoOfertaServicoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">buscarDadosServicoOfertaServicoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarDadosServicoOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ResultadoBuscarDadosServicoOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ResultadoBuscarDadosServicoOfertaServico"));
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
