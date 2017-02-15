/**
 * BuscarListaTipoServicoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class BuscarListaTipoServicoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaTipoServico resultadoBuscarListaTipoServico;

    public BuscarListaTipoServicoResponse() {
    }

    public BuscarListaTipoServicoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaTipoServico resultadoBuscarListaTipoServico) {
           this.resultadoBuscarListaTipoServico = resultadoBuscarListaTipoServico;
    }


    /**
     * Gets the resultadoBuscarListaTipoServico value for this BuscarListaTipoServicoResponse.
     * 
     * @return resultadoBuscarListaTipoServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaTipoServico getResultadoBuscarListaTipoServico() {
        return resultadoBuscarListaTipoServico;
    }


    /**
     * Sets the resultadoBuscarListaTipoServico value for this BuscarListaTipoServicoResponse.
     * 
     * @param resultadoBuscarListaTipoServico
     */
    public void setResultadoBuscarListaTipoServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaTipoServico resultadoBuscarListaTipoServico) {
        this.resultadoBuscarListaTipoServico = resultadoBuscarListaTipoServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaTipoServicoResponse)) return false;
        BuscarListaTipoServicoResponse other = (BuscarListaTipoServicoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaTipoServico==null && other.getResultadoBuscarListaTipoServico()==null) || 
             (this.resultadoBuscarListaTipoServico!=null &&
              this.resultadoBuscarListaTipoServico.equals(other.getResultadoBuscarListaTipoServico())));
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
        if (getResultadoBuscarListaTipoServico() != null) {
            _hashCode += getResultadoBuscarListaTipoServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaTipoServicoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">buscarListaTipoServicoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaTipoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ResultadoBuscarListaTipoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ResultadoBuscarListaTipoServico"));
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
