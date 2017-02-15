/**
 * BuscarListaServicoTarifaPorIdServicoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class BuscarListaServicoTarifaPorIdServicoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoTarifaPorIdServico resultadoBuscarListaServicoTarifaPorIdServico;

    public BuscarListaServicoTarifaPorIdServicoResponse() {
    }

    public BuscarListaServicoTarifaPorIdServicoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoTarifaPorIdServico resultadoBuscarListaServicoTarifaPorIdServico) {
           this.resultadoBuscarListaServicoTarifaPorIdServico = resultadoBuscarListaServicoTarifaPorIdServico;
    }


    /**
     * Gets the resultadoBuscarListaServicoTarifaPorIdServico value for this BuscarListaServicoTarifaPorIdServicoResponse.
     * 
     * @return resultadoBuscarListaServicoTarifaPorIdServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoTarifaPorIdServico getResultadoBuscarListaServicoTarifaPorIdServico() {
        return resultadoBuscarListaServicoTarifaPorIdServico;
    }


    /**
     * Sets the resultadoBuscarListaServicoTarifaPorIdServico value for this BuscarListaServicoTarifaPorIdServicoResponse.
     * 
     * @param resultadoBuscarListaServicoTarifaPorIdServico
     */
    public void setResultadoBuscarListaServicoTarifaPorIdServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoTarifaPorIdServico resultadoBuscarListaServicoTarifaPorIdServico) {
        this.resultadoBuscarListaServicoTarifaPorIdServico = resultadoBuscarListaServicoTarifaPorIdServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaServicoTarifaPorIdServicoResponse)) return false;
        BuscarListaServicoTarifaPorIdServicoResponse other = (BuscarListaServicoTarifaPorIdServicoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaServicoTarifaPorIdServico==null && other.getResultadoBuscarListaServicoTarifaPorIdServico()==null) || 
             (this.resultadoBuscarListaServicoTarifaPorIdServico!=null &&
              this.resultadoBuscarListaServicoTarifaPorIdServico.equals(other.getResultadoBuscarListaServicoTarifaPorIdServico())));
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
        if (getResultadoBuscarListaServicoTarifaPorIdServico() != null) {
            _hashCode += getResultadoBuscarListaServicoTarifaPorIdServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaServicoTarifaPorIdServicoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">buscarListaServicoTarifaPorIdServicoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaServicoTarifaPorIdServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ResultadoBuscarListaServicoTarifaPorIdServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ResultadoBuscarListaServicoTarifaPorIdServico"));
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
