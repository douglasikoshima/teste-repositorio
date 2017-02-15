/**
 * ResultadoBuscarListaPlanoParametrizacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ResultadoBuscarListaPlanoParametrizacao  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoOut paginacaoOut;

    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao[] listaBuscarListaPlanoParametrizacao;

    public ResultadoBuscarListaPlanoParametrizacao() {
    }

    public ResultadoBuscarListaPlanoParametrizacao(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoOut paginacaoOut,
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao[] listaBuscarListaPlanoParametrizacao) {
           this.paginacaoOut = paginacaoOut;
           this.listaBuscarListaPlanoParametrizacao = listaBuscarListaPlanoParametrizacao;
    }


    /**
     * Gets the paginacaoOut value for this ResultadoBuscarListaPlanoParametrizacao.
     * 
     * @return paginacaoOut
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoOut getPaginacaoOut() {
        return paginacaoOut;
    }


    /**
     * Sets the paginacaoOut value for this ResultadoBuscarListaPlanoParametrizacao.
     * 
     * @param paginacaoOut
     */
    public void setPaginacaoOut(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoOut paginacaoOut) {
        this.paginacaoOut = paginacaoOut;
    }


    /**
     * Gets the listaBuscarListaPlanoParametrizacao value for this ResultadoBuscarListaPlanoParametrizacao.
     * 
     * @return listaBuscarListaPlanoParametrizacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao[] getListaBuscarListaPlanoParametrizacao() {
        return listaBuscarListaPlanoParametrizacao;
    }


    /**
     * Sets the listaBuscarListaPlanoParametrizacao value for this ResultadoBuscarListaPlanoParametrizacao.
     * 
     * @param listaBuscarListaPlanoParametrizacao
     */
    public void setListaBuscarListaPlanoParametrizacao(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao[] listaBuscarListaPlanoParametrizacao) {
        this.listaBuscarListaPlanoParametrizacao = listaBuscarListaPlanoParametrizacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaPlanoParametrizacao)) return false;
        ResultadoBuscarListaPlanoParametrizacao other = (ResultadoBuscarListaPlanoParametrizacao) obj;
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
            ((this.listaBuscarListaPlanoParametrizacao==null && other.getListaBuscarListaPlanoParametrizacao()==null) || 
             (this.listaBuscarListaPlanoParametrizacao!=null &&
              java.util.Arrays.equals(this.listaBuscarListaPlanoParametrizacao, other.getListaBuscarListaPlanoParametrizacao())));
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
        if (getListaBuscarListaPlanoParametrizacao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaBuscarListaPlanoParametrizacao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaBuscarListaPlanoParametrizacao(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaPlanoParametrizacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ResultadoBuscarListaPlanoParametrizacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "PaginacaoOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">PaginacaoOut"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaBuscarListaPlanoParametrizacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ListaBuscarListaPlanoParametrizacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>>ResultadoBuscarListaPlanoParametrizacao>ListaBuscarListaPlanoParametrizacao>RetornoBuscarListaPlanoParametrizacao"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "RetornoBuscarListaPlanoParametrizacao"));
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
