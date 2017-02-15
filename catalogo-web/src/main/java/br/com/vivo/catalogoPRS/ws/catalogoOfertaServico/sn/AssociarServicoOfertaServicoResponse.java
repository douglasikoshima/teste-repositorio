/**
 * AssociarServicoOfertaServicoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class AssociarServicoOfertaServicoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAssociarServicoOfertaServico resultadoAssociarServicoOfertaServico;

    public AssociarServicoOfertaServicoResponse() {
    }

    public AssociarServicoOfertaServicoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAssociarServicoOfertaServico resultadoAssociarServicoOfertaServico) {
           this.resultadoAssociarServicoOfertaServico = resultadoAssociarServicoOfertaServico;
    }


    /**
     * Gets the resultadoAssociarServicoOfertaServico value for this AssociarServicoOfertaServicoResponse.
     * 
     * @return resultadoAssociarServicoOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAssociarServicoOfertaServico getResultadoAssociarServicoOfertaServico() {
        return resultadoAssociarServicoOfertaServico;
    }


    /**
     * Sets the resultadoAssociarServicoOfertaServico value for this AssociarServicoOfertaServicoResponse.
     * 
     * @param resultadoAssociarServicoOfertaServico
     */
    public void setResultadoAssociarServicoOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAssociarServicoOfertaServico resultadoAssociarServicoOfertaServico) {
        this.resultadoAssociarServicoOfertaServico = resultadoAssociarServicoOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssociarServicoOfertaServicoResponse)) return false;
        AssociarServicoOfertaServicoResponse other = (AssociarServicoOfertaServicoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoAssociarServicoOfertaServico==null && other.getResultadoAssociarServicoOfertaServico()==null) || 
             (this.resultadoAssociarServicoOfertaServico!=null &&
              this.resultadoAssociarServicoOfertaServico.equals(other.getResultadoAssociarServicoOfertaServico())));
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
        if (getResultadoAssociarServicoOfertaServico() != null) {
            _hashCode += getResultadoAssociarServicoOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssociarServicoOfertaServicoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">associarServicoOfertaServicoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoAssociarServicoOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ResultadoAssociarServicoOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ResultadoAssociarServicoOfertaServico"));
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
