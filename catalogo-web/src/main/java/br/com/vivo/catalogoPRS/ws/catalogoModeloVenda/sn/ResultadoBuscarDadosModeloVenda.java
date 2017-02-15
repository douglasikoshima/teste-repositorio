/**
 * ResultadoBuscarDadosModeloVenda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class ResultadoBuscarDadosModeloVenda  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut;

    private br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVenda[] listaBuscarDadosModeloVenda;

    public ResultadoBuscarDadosModeloVenda() {
    }

    public ResultadoBuscarDadosModeloVenda(
           br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut,
           br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVenda[] listaBuscarDadosModeloVenda) {
           this.paginacaoOut = paginacaoOut;
           this.listaBuscarDadosModeloVenda = listaBuscarDadosModeloVenda;
    }


    /**
     * Gets the paginacaoOut value for this ResultadoBuscarDadosModeloVenda.
     * 
     * @return paginacaoOut
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut getPaginacaoOut() {
        return paginacaoOut;
    }


    /**
     * Sets the paginacaoOut value for this ResultadoBuscarDadosModeloVenda.
     * 
     * @param paginacaoOut
     */
    public void setPaginacaoOut(br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut) {
        this.paginacaoOut = paginacaoOut;
    }


    /**
     * Gets the listaBuscarDadosModeloVenda value for this ResultadoBuscarDadosModeloVenda.
     * 
     * @return listaBuscarDadosModeloVenda
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVenda[] getListaBuscarDadosModeloVenda() {
        return listaBuscarDadosModeloVenda;
    }


    /**
     * Sets the listaBuscarDadosModeloVenda value for this ResultadoBuscarDadosModeloVenda.
     * 
     * @param listaBuscarDadosModeloVenda
     */
    public void setListaBuscarDadosModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVenda[] listaBuscarDadosModeloVenda) {
        this.listaBuscarDadosModeloVenda = listaBuscarDadosModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarDadosModeloVenda)) return false;
        ResultadoBuscarDadosModeloVenda other = (ResultadoBuscarDadosModeloVenda) obj;
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
            ((this.listaBuscarDadosModeloVenda==null && other.getListaBuscarDadosModeloVenda()==null) || 
             (this.listaBuscarDadosModeloVenda!=null &&
              java.util.Arrays.equals(this.listaBuscarDadosModeloVenda, other.getListaBuscarDadosModeloVenda())));
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
        if (getListaBuscarDadosModeloVenda() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaBuscarDadosModeloVenda());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaBuscarDadosModeloVenda(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarDadosModeloVenda.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ResultadoBuscarDadosModeloVenda"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "PaginacaoOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoOut"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaBuscarDadosModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ListaBuscarDadosModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">>>ResultadoBuscarDadosModeloVenda>ListaBuscarDadosModeloVenda>DadosModeloVenda"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "DadosModeloVenda"));
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
