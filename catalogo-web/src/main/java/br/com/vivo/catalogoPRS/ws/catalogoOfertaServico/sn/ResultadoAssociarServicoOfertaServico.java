/**
 * ResultadoAssociarServicoOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoAssociarServicoOfertaServico  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico[] listaServicoOfertaServico;

    public ResultadoAssociarServicoOfertaServico() {
    }

    public ResultadoAssociarServicoOfertaServico(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico[] listaServicoOfertaServico) {
           this.listaServicoOfertaServico = listaServicoOfertaServico;
    }


    /**
     * Gets the listaServicoOfertaServico value for this ResultadoAssociarServicoOfertaServico.
     * 
     * @return listaServicoOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico[] getListaServicoOfertaServico() {
        return listaServicoOfertaServico;
    }


    /**
     * Sets the listaServicoOfertaServico value for this ResultadoAssociarServicoOfertaServico.
     * 
     * @param listaServicoOfertaServico
     */
    public void setListaServicoOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAssociarServicoOfertaServicoListaServicoOfertaServicoServicoOfertaServico[] listaServicoOfertaServico) {
        this.listaServicoOfertaServico = listaServicoOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoAssociarServicoOfertaServico)) return false;
        ResultadoAssociarServicoOfertaServico other = (ResultadoAssociarServicoOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaServicoOfertaServico==null && other.getListaServicoOfertaServico()==null) || 
             (this.listaServicoOfertaServico!=null &&
              java.util.Arrays.equals(this.listaServicoOfertaServico, other.getListaServicoOfertaServico())));
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
        if (getListaServicoOfertaServico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaServicoOfertaServico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaServicoOfertaServico(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoAssociarServicoOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ResultadoAssociarServicoOfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaServicoOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ListaServicoOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>>ResultadoAssociarServicoOfertaServico>ListaServicoOfertaServico>ServicoOfertaServico"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ServicoOfertaServico"));
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
