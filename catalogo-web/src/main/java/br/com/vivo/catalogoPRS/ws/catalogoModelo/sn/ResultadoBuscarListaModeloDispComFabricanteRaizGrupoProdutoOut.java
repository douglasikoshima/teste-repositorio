/**
 * ResultadoBuscarListaModeloDispComFabricanteRaizGrupoProdutoOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoBuscarListaModeloDispComFabricanteRaizGrupoProdutoOut  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoOut paginacaoOut;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Modelo[] listaGrupoProduto;

    public ResultadoBuscarListaModeloDispComFabricanteRaizGrupoProdutoOut() {
    }

    public ResultadoBuscarListaModeloDispComFabricanteRaizGrupoProdutoOut(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoOut paginacaoOut,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Modelo[] listaGrupoProduto) {
           this.paginacaoOut = paginacaoOut;
           this.listaGrupoProduto = listaGrupoProduto;
    }


    /**
     * Gets the paginacaoOut value for this ResultadoBuscarListaModeloDispComFabricanteRaizGrupoProdutoOut.
     * 
     * @return paginacaoOut
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoOut getPaginacaoOut() {
        return paginacaoOut;
    }


    /**
     * Sets the paginacaoOut value for this ResultadoBuscarListaModeloDispComFabricanteRaizGrupoProdutoOut.
     * 
     * @param paginacaoOut
     */
    public void setPaginacaoOut(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoOut paginacaoOut) {
        this.paginacaoOut = paginacaoOut;
    }


    /**
     * Gets the listaGrupoProduto value for this ResultadoBuscarListaModeloDispComFabricanteRaizGrupoProdutoOut.
     * 
     * @return listaGrupoProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Modelo[] getListaGrupoProduto() {
        return listaGrupoProduto;
    }


    /**
     * Sets the listaGrupoProduto value for this ResultadoBuscarListaModeloDispComFabricanteRaizGrupoProdutoOut.
     * 
     * @param listaGrupoProduto
     */
    public void setListaGrupoProduto(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Modelo[] listaGrupoProduto) {
        this.listaGrupoProduto = listaGrupoProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaModeloDispComFabricanteRaizGrupoProdutoOut)) return false;
        ResultadoBuscarListaModeloDispComFabricanteRaizGrupoProdutoOut other = (ResultadoBuscarListaModeloDispComFabricanteRaizGrupoProdutoOut) obj;
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
            ((this.listaGrupoProduto==null && other.getListaGrupoProduto()==null) || 
             (this.listaGrupoProduto!=null &&
              java.util.Arrays.equals(this.listaGrupoProduto, other.getListaGrupoProduto())));
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
        if (getListaGrupoProduto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaGrupoProduto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaGrupoProduto(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaModeloDispComFabricanteRaizGrupoProdutoOut.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ResultadoBuscarListaModeloDispComFabricante>RaizGrupoProdutoOut"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "PaginacaoOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">PaginacaoOut"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "Modelo"));
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
