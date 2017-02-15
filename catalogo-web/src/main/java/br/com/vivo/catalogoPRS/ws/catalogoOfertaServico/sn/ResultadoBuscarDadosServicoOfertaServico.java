/**
 * ResultadoBuscarDadosServicoOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoBuscarDadosServicoOfertaServico  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma[] listaServicoOfertaServicoPlataforma;

    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoTecnologiaServicoOfertaServicoTecnologia[] listaServicoOfertaServicoTecnologia;

    public ResultadoBuscarDadosServicoOfertaServico() {
    }

    public ResultadoBuscarDadosServicoOfertaServico(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma[] listaServicoOfertaServicoPlataforma,
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoTecnologiaServicoOfertaServicoTecnologia[] listaServicoOfertaServicoTecnologia) {
           this.listaServicoOfertaServicoPlataforma = listaServicoOfertaServicoPlataforma;
           this.listaServicoOfertaServicoTecnologia = listaServicoOfertaServicoTecnologia;
    }


    /**
     * Gets the listaServicoOfertaServicoPlataforma value for this ResultadoBuscarDadosServicoOfertaServico.
     * 
     * @return listaServicoOfertaServicoPlataforma
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma[] getListaServicoOfertaServicoPlataforma() {
        return listaServicoOfertaServicoPlataforma;
    }


    /**
     * Sets the listaServicoOfertaServicoPlataforma value for this ResultadoBuscarDadosServicoOfertaServico.
     * 
     * @param listaServicoOfertaServicoPlataforma
     */
    public void setListaServicoOfertaServicoPlataforma(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma[] listaServicoOfertaServicoPlataforma) {
        this.listaServicoOfertaServicoPlataforma = listaServicoOfertaServicoPlataforma;
    }


    /**
     * Gets the listaServicoOfertaServicoTecnologia value for this ResultadoBuscarDadosServicoOfertaServico.
     * 
     * @return listaServicoOfertaServicoTecnologia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoTecnologiaServicoOfertaServicoTecnologia[] getListaServicoOfertaServicoTecnologia() {
        return listaServicoOfertaServicoTecnologia;
    }


    /**
     * Sets the listaServicoOfertaServicoTecnologia value for this ResultadoBuscarDadosServicoOfertaServico.
     * 
     * @param listaServicoOfertaServicoTecnologia
     */
    public void setListaServicoOfertaServicoTecnologia(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoTecnologiaServicoOfertaServicoTecnologia[] listaServicoOfertaServicoTecnologia) {
        this.listaServicoOfertaServicoTecnologia = listaServicoOfertaServicoTecnologia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarDadosServicoOfertaServico)) return false;
        ResultadoBuscarDadosServicoOfertaServico other = (ResultadoBuscarDadosServicoOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaServicoOfertaServicoPlataforma==null && other.getListaServicoOfertaServicoPlataforma()==null) || 
             (this.listaServicoOfertaServicoPlataforma!=null &&
              java.util.Arrays.equals(this.listaServicoOfertaServicoPlataforma, other.getListaServicoOfertaServicoPlataforma()))) &&
            ((this.listaServicoOfertaServicoTecnologia==null && other.getListaServicoOfertaServicoTecnologia()==null) || 
             (this.listaServicoOfertaServicoTecnologia!=null &&
              java.util.Arrays.equals(this.listaServicoOfertaServicoTecnologia, other.getListaServicoOfertaServicoTecnologia())));
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
        if (getListaServicoOfertaServicoPlataforma() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaServicoOfertaServicoPlataforma());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaServicoOfertaServicoPlataforma(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaServicoOfertaServicoTecnologia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaServicoOfertaServicoTecnologia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaServicoOfertaServicoTecnologia(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarDadosServicoOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ResultadoBuscarDadosServicoOfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaServicoOfertaServicoPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ListaServicoOfertaServicoPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>>ResultadoBuscarDadosServicoOfertaServico>ListaServicoOfertaServicoPlataforma>ServicoOfertaServicoPlataforma"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ServicoOfertaServicoPlataforma"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaServicoOfertaServicoTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ListaServicoOfertaServicoTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>>ResultadoBuscarDadosServicoOfertaServico>ListaServicoOfertaServicoTecnologia>ServicoOfertaServicoTecnologia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ServicoOfertaServicoTecnologia"));
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
