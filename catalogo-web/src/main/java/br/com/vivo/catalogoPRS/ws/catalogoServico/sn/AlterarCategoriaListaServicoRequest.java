/**
 * AlterarCategoriaListaServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class AlterarCategoriaListaServicoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarCategoriaListaServico parametrosAlterarCategoriaListaServico;

    public AlterarCategoriaListaServicoRequest() {
    }

    public AlterarCategoriaListaServicoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarCategoriaListaServico parametrosAlterarCategoriaListaServico) {
           this.parametrosAlterarCategoriaListaServico = parametrosAlterarCategoriaListaServico;
    }


    /**
     * Gets the parametrosAlterarCategoriaListaServico value for this AlterarCategoriaListaServicoRequest.
     * 
     * @return parametrosAlterarCategoriaListaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarCategoriaListaServico getParametrosAlterarCategoriaListaServico() {
        return parametrosAlterarCategoriaListaServico;
    }


    /**
     * Sets the parametrosAlterarCategoriaListaServico value for this AlterarCategoriaListaServicoRequest.
     * 
     * @param parametrosAlterarCategoriaListaServico
     */
    public void setParametrosAlterarCategoriaListaServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarCategoriaListaServico parametrosAlterarCategoriaListaServico) {
        this.parametrosAlterarCategoriaListaServico = parametrosAlterarCategoriaListaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarCategoriaListaServicoRequest)) return false;
        AlterarCategoriaListaServicoRequest other = (AlterarCategoriaListaServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarCategoriaListaServico==null && other.getParametrosAlterarCategoriaListaServico()==null) || 
             (this.parametrosAlterarCategoriaListaServico!=null &&
              this.parametrosAlterarCategoriaListaServico.equals(other.getParametrosAlterarCategoriaListaServico())));
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
        if (getParametrosAlterarCategoriaListaServico() != null) {
            _hashCode += getParametrosAlterarCategoriaListaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarCategoriaListaServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">alterarCategoriaListaServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarCategoriaListaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ParametrosAlterarCategoriaListaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosAlterarCategoriaListaServico"));
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
