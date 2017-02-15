/**
 * ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn;

public class ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda  implements java.io.Serializable {
    private java.lang.Long idOrganizacaoVendas;

    private java.lang.String nmOrganizacaoVendas;

    private java.lang.String sgOrganizacaoVendas;

    private br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVendaListaDDDDdd[] listaDDD;

    public ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda() {
    }

    public ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda(
           java.lang.Long idOrganizacaoVendas,
           java.lang.String nmOrganizacaoVendas,
           java.lang.String sgOrganizacaoVendas,
           br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVendaListaDDDDdd[] listaDDD) {
           this.idOrganizacaoVendas = idOrganizacaoVendas;
           this.nmOrganizacaoVendas = nmOrganizacaoVendas;
           this.sgOrganizacaoVendas = sgOrganizacaoVendas;
           this.listaDDD = listaDDD;
    }


    /**
     * Gets the idOrganizacaoVendas value for this ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda.
     * 
     * @return idOrganizacaoVendas
     */
    public java.lang.Long getIdOrganizacaoVendas() {
        return idOrganizacaoVendas;
    }


    /**
     * Sets the idOrganizacaoVendas value for this ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda.
     * 
     * @param idOrganizacaoVendas
     */
    public void setIdOrganizacaoVendas(java.lang.Long idOrganizacaoVendas) {
        this.idOrganizacaoVendas = idOrganizacaoVendas;
    }


    /**
     * Gets the nmOrganizacaoVendas value for this ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda.
     * 
     * @return nmOrganizacaoVendas
     */
    public java.lang.String getNmOrganizacaoVendas() {
        return nmOrganizacaoVendas;
    }


    /**
     * Sets the nmOrganizacaoVendas value for this ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda.
     * 
     * @param nmOrganizacaoVendas
     */
    public void setNmOrganizacaoVendas(java.lang.String nmOrganizacaoVendas) {
        this.nmOrganizacaoVendas = nmOrganizacaoVendas;
    }


    /**
     * Gets the sgOrganizacaoVendas value for this ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda.
     * 
     * @return sgOrganizacaoVendas
     */
    public java.lang.String getSgOrganizacaoVendas() {
        return sgOrganizacaoVendas;
    }


    /**
     * Sets the sgOrganizacaoVendas value for this ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda.
     * 
     * @param sgOrganizacaoVendas
     */
    public void setSgOrganizacaoVendas(java.lang.String sgOrganizacaoVendas) {
        this.sgOrganizacaoVendas = sgOrganizacaoVendas;
    }


    /**
     * Gets the listaDDD value for this ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda.
     * 
     * @return listaDDD
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVendaListaDDDDdd[] getListaDDD() {
        return listaDDD;
    }


    /**
     * Sets the listaDDD value for this ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda.
     * 
     * @param listaDDD
     */
    public void setListaDDD(br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVendaListaDDDDdd[] listaDDD) {
        this.listaDDD = listaDDD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda)) return false;
        ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda other = (ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idOrganizacaoVendas==null && other.getIdOrganizacaoVendas()==null) || 
             (this.idOrganizacaoVendas!=null &&
              this.idOrganizacaoVendas.equals(other.getIdOrganizacaoVendas()))) &&
            ((this.nmOrganizacaoVendas==null && other.getNmOrganizacaoVendas()==null) || 
             (this.nmOrganizacaoVendas!=null &&
              this.nmOrganizacaoVendas.equals(other.getNmOrganizacaoVendas()))) &&
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
        if (getIdOrganizacaoVendas() != null) {
            _hashCode += getIdOrganizacaoVendas().hashCode();
        }
        if (getNmOrganizacaoVendas() != null) {
            _hashCode += getNmOrganizacaoVendas().hashCode();
        }
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", ">>>ResultadoBuscarListaOrgVendasComDDD>ListaOrganizacaoVendaComDDD>OrganizacaoVenda"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrganizacaoVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", "idOrganizacaoVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmOrganizacaoVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", "nmOrganizacaoVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgOrganizacaoVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", "sgOrganizacaoVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaDDD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", "ListaDDD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", ">>>>>ResultadoBuscarListaOrgVendasComDDD>ListaOrganizacaoVendaComDDD>OrganizacaoVenda>ListaDDD>Ddd"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", "Ddd"));
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
