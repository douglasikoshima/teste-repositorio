/**
 * ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda  implements java.io.Serializable {
    private java.lang.String sgOrganizacaoVendas;

    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVendaListaDDDDdd[] listaDDD;

    public ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda() {
    }

    public ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda(
           java.lang.String sgOrganizacaoVendas,
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVendaListaDDDDdd[] listaDDD) {
           this.sgOrganizacaoVendas = sgOrganizacaoVendas;
           this.listaDDD = listaDDD;
    }


    /**
     * Gets the sgOrganizacaoVendas value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda.
     * 
     * @return sgOrganizacaoVendas
     */
    public java.lang.String getSgOrganizacaoVendas() {
        return sgOrganizacaoVendas;
    }


    /**
     * Sets the sgOrganizacaoVendas value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda.
     * 
     * @param sgOrganizacaoVendas
     */
    public void setSgOrganizacaoVendas(java.lang.String sgOrganizacaoVendas) {
        this.sgOrganizacaoVendas = sgOrganizacaoVendas;
    }


    /**
     * Gets the listaDDD value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda.
     * 
     * @return listaDDD
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVendaListaDDDDdd[] getListaDDD() {
        return listaDDD;
    }


    /**
     * Sets the listaDDD value for this ResultadoBuscarListaResultadoImportacaoListaResultadoImportacaoResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda.
     * 
     * @param listaDDD
     */
    public void setListaDDD(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVendaListaDDDDdd[] listaDDD) {
        this.listaDDD = listaDDD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda)) return false;
        ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda other = (ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sgOrganizacaoVendas==null && other.getSgOrganizacaoVendas()==null) || 
             (this.sgOrganizacaoVendas!=null &&
              this.sgOrganizacaoVendas.equals(other.getSgOrganizacaoVendas()))) &&
            ((this.listaDDD==null && other.getListaDDD()==null) || 
             (this.listaDDD!=null &&
              java.util.Arrays.equals(this.listaDDD, other.getListaDDD())));
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
        if (getSgOrganizacaoVendas() != null) {
            _hashCode += getSgOrganizacaoVendas().hashCode();
        }
        if (getListaDDD() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaDDD());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaDDD(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao>ResultadoImportacao>ListaIdOrganizacaoVendaComDDD>OrganizacaoVenda"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgOrganizacaoVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "sgOrganizacaoVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaDDD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ListaDDD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>>>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao>ResultadoImportacao>ListaIdOrganizacaoVendaComDDD>OrganizacaoVenda>ListaDDD>Ddd"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "Ddd"));
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
