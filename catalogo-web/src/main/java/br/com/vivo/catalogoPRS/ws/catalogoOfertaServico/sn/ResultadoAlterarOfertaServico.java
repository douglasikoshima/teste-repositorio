/**
 * ResultadoAlterarOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoAlterarOfertaServico  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAlterarOfertaServicoListaAlterarOfertaServico listaAlterarOfertaServico;

    public ResultadoAlterarOfertaServico() {
    }

    public ResultadoAlterarOfertaServico(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAlterarOfertaServicoListaAlterarOfertaServico listaAlterarOfertaServico) {
           this.listaAlterarOfertaServico = listaAlterarOfertaServico;
    }


    /**
     * Gets the listaAlterarOfertaServico value for this ResultadoAlterarOfertaServico.
     * 
     * @return listaAlterarOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAlterarOfertaServicoListaAlterarOfertaServico getListaAlterarOfertaServico() {
        return listaAlterarOfertaServico;
    }


    /**
     * Sets the listaAlterarOfertaServico value for this ResultadoAlterarOfertaServico.
     * 
     * @param listaAlterarOfertaServico
     */
    public void setListaAlterarOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoAlterarOfertaServicoListaAlterarOfertaServico listaAlterarOfertaServico) {
        this.listaAlterarOfertaServico = listaAlterarOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoAlterarOfertaServico)) return false;
        ResultadoAlterarOfertaServico other = (ResultadoAlterarOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaAlterarOfertaServico==null && other.getListaAlterarOfertaServico()==null) || 
             (this.listaAlterarOfertaServico!=null &&
              this.listaAlterarOfertaServico.equals(other.getListaAlterarOfertaServico())));
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
        if (getListaAlterarOfertaServico() != null) {
            _hashCode += getListaAlterarOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoAlterarOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ResultadoAlterarOfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAlterarOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ListaAlterarOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>ResultadoAlterarOfertaServico>ListaAlterarOfertaServico"));
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
