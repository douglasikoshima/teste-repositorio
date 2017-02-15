/**
 * AlterarOfertaServicoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class AlterarOfertaServicoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAlterarOfertaServico resultadoAlterarOfertaServico;

    public AlterarOfertaServicoResponse() {
    }

    public AlterarOfertaServicoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAlterarOfertaServico resultadoAlterarOfertaServico) {
           this.resultadoAlterarOfertaServico = resultadoAlterarOfertaServico;
    }


    /**
     * Gets the resultadoAlterarOfertaServico value for this AlterarOfertaServicoResponse.
     * 
     * @return resultadoAlterarOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAlterarOfertaServico getResultadoAlterarOfertaServico() {
        return resultadoAlterarOfertaServico;
    }


    /**
     * Sets the resultadoAlterarOfertaServico value for this AlterarOfertaServicoResponse.
     * 
     * @param resultadoAlterarOfertaServico
     */
    public void setResultadoAlterarOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAlterarOfertaServico resultadoAlterarOfertaServico) {
        this.resultadoAlterarOfertaServico = resultadoAlterarOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarOfertaServicoResponse)) return false;
        AlterarOfertaServicoResponse other = (AlterarOfertaServicoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoAlterarOfertaServico==null && other.getResultadoAlterarOfertaServico()==null) || 
             (this.resultadoAlterarOfertaServico!=null &&
              this.resultadoAlterarOfertaServico.equals(other.getResultadoAlterarOfertaServico())));
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
        if (getResultadoAlterarOfertaServico() != null) {
            _hashCode += getResultadoAlterarOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarOfertaServicoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">alterarOfertaServicoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoAlterarOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ResultadoAlterarOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ResultadoAlterarOfertaServico"));
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
