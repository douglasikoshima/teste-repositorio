/**
 * AlterarOfertaServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class AlterarOfertaServicoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosAlterarOfertaServico parametrosAlterarOfertaServico;

    public AlterarOfertaServicoRequest() {
    }

    public AlterarOfertaServicoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosAlterarOfertaServico parametrosAlterarOfertaServico) {
           this.parametrosAlterarOfertaServico = parametrosAlterarOfertaServico;
    }


    /**
     * Gets the parametrosAlterarOfertaServico value for this AlterarOfertaServicoRequest.
     * 
     * @return parametrosAlterarOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosAlterarOfertaServico getParametrosAlterarOfertaServico() {
        return parametrosAlterarOfertaServico;
    }


    /**
     * Sets the parametrosAlterarOfertaServico value for this AlterarOfertaServicoRequest.
     * 
     * @param parametrosAlterarOfertaServico
     */
    public void setParametrosAlterarOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosAlterarOfertaServico parametrosAlterarOfertaServico) {
        this.parametrosAlterarOfertaServico = parametrosAlterarOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarOfertaServicoRequest)) return false;
        AlterarOfertaServicoRequest other = (AlterarOfertaServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarOfertaServico==null && other.getParametrosAlterarOfertaServico()==null) || 
             (this.parametrosAlterarOfertaServico!=null &&
              this.parametrosAlterarOfertaServico.equals(other.getParametrosAlterarOfertaServico())));
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
        if (getParametrosAlterarOfertaServico() != null) {
            _hashCode += getParametrosAlterarOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarOfertaServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">alterarOfertaServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ParametrosAlterarOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ParametrosAlterarOfertaServico"));
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
