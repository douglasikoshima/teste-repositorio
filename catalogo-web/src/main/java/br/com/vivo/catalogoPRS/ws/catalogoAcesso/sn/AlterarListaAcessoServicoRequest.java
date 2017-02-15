/**
 * AlterarListaAcessoServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class AlterarListaAcessoServicoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoServico parametrosAlterarListaAcessoServico;

    public AlterarListaAcessoServicoRequest() {
    }

    public AlterarListaAcessoServicoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoServico parametrosAlterarListaAcessoServico) {
           this.parametrosAlterarListaAcessoServico = parametrosAlterarListaAcessoServico;
    }


    /**
     * Gets the parametrosAlterarListaAcessoServico value for this AlterarListaAcessoServicoRequest.
     * 
     * @return parametrosAlterarListaAcessoServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoServico getParametrosAlterarListaAcessoServico() {
        return parametrosAlterarListaAcessoServico;
    }


    /**
     * Sets the parametrosAlterarListaAcessoServico value for this AlterarListaAcessoServicoRequest.
     * 
     * @param parametrosAlterarListaAcessoServico
     */
    public void setParametrosAlterarListaAcessoServico(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoServico parametrosAlterarListaAcessoServico) {
        this.parametrosAlterarListaAcessoServico = parametrosAlterarListaAcessoServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarListaAcessoServicoRequest)) return false;
        AlterarListaAcessoServicoRequest other = (AlterarListaAcessoServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarListaAcessoServico==null && other.getParametrosAlterarListaAcessoServico()==null) || 
             (this.parametrosAlterarListaAcessoServico!=null &&
              this.parametrosAlterarListaAcessoServico.equals(other.getParametrosAlterarListaAcessoServico())));
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
        if (getParametrosAlterarListaAcessoServico() != null) {
            _hashCode += getParametrosAlterarListaAcessoServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarListaAcessoServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">alterarListaAcessoServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarListaAcessoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ParametrosAlterarListaAcessoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosAlterarListaAcessoServico"));
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
