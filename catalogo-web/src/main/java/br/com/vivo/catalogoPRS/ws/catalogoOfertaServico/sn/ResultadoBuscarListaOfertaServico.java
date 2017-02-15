/**
 * ResultadoBuscarListaOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoBuscarListaOfertaServico  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut;

    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico[] listaOfertaServico;

    public ResultadoBuscarListaOfertaServico() {
    }

    public ResultadoBuscarListaOfertaServico(
           br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut,
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico[] listaOfertaServico) {
           this.paginacaoOut = paginacaoOut;
           this.listaOfertaServico = listaOfertaServico;
    }


    /**
     * Gets the paginacaoOut value for this ResultadoBuscarListaOfertaServico.
     * 
     * @return paginacaoOut
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut getPaginacaoOut() {
        return paginacaoOut;
    }


    /**
     * Sets the paginacaoOut value for this ResultadoBuscarListaOfertaServico.
     * 
     * @param paginacaoOut
     */
    public void setPaginacaoOut(br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut) {
        this.paginacaoOut = paginacaoOut;
    }


    /**
     * Gets the listaOfertaServico value for this ResultadoBuscarListaOfertaServico.
     * 
     * @return listaOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico[] getListaOfertaServico() {
        return listaOfertaServico;
    }


    /**
     * Sets the listaOfertaServico value for this ResultadoBuscarListaOfertaServico.
     * 
     * @param listaOfertaServico
     */
    public void setListaOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico[] listaOfertaServico) {
        this.listaOfertaServico = listaOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaOfertaServico)) return false;
        ResultadoBuscarListaOfertaServico other = (ResultadoBuscarListaOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paginacaoOut==null && other.getPaginacaoOut()==null) || 
             (this.paginacaoOut!=null &&
              this.paginacaoOut.equals(other.getPaginacaoOut()))) &&
            ((this.listaOfertaServico==null && other.getListaOfertaServico()==null) || 
             (this.listaOfertaServico!=null &&
              java.util.Arrays.equals(this.listaOfertaServico, other.getListaOfertaServico())));
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
        if (getPaginacaoOut() != null) {
            _hashCode += getPaginacaoOut().hashCode();
        }
        if (getListaOfertaServico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaOfertaServico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaOfertaServico(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ResultadoBuscarListaOfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "PaginacaoOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoOut"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ListaOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>>ResultadoBuscarListaOfertaServico>ListaOfertaServico>OfertaServico"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "OfertaServico"));
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
