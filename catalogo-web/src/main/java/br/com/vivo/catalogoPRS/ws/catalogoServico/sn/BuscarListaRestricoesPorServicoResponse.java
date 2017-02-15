/**
 * BuscarListaRestricoesPorServicoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class BuscarListaRestricoesPorServicoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoRestricoesPorServico resultadoRestricoesPorServico;

    public BuscarListaRestricoesPorServicoResponse() {
    }

    public BuscarListaRestricoesPorServicoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoRestricoesPorServico resultadoRestricoesPorServico) {
           this.resultadoRestricoesPorServico = resultadoRestricoesPorServico;
    }


    /**
     * Gets the resultadoRestricoesPorServico value for this BuscarListaRestricoesPorServicoResponse.
     * 
     * @return resultadoRestricoesPorServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoRestricoesPorServico getResultadoRestricoesPorServico() {
        return resultadoRestricoesPorServico;
    }


    /**
     * Sets the resultadoRestricoesPorServico value for this BuscarListaRestricoesPorServicoResponse.
     * 
     * @param resultadoRestricoesPorServico
     */
    public void setResultadoRestricoesPorServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoRestricoesPorServico resultadoRestricoesPorServico) {
        this.resultadoRestricoesPorServico = resultadoRestricoesPorServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaRestricoesPorServicoResponse)) return false;
        BuscarListaRestricoesPorServicoResponse other = (BuscarListaRestricoesPorServicoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoRestricoesPorServico==null && other.getResultadoRestricoesPorServico()==null) || 
             (this.resultadoRestricoesPorServico!=null &&
              this.resultadoRestricoesPorServico.equals(other.getResultadoRestricoesPorServico())));
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
        if (getResultadoRestricoesPorServico() != null) {
            _hashCode += getResultadoRestricoesPorServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaRestricoesPorServicoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">buscarListaRestricoesPorServicoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoRestricoesPorServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ResultadoRestricoesPorServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ResultadoRestricoesPorServico"));
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
