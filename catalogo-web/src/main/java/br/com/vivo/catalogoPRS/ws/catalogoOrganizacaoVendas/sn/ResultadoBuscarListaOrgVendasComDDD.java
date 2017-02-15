/**
 * ResultadoBuscarListaOrgVendasComDDD.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn;

public class ResultadoBuscarListaOrgVendasComDDD  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda[] listaOrganizacaoVendaComDDD;

    public ResultadoBuscarListaOrgVendasComDDD() {
    }

    public ResultadoBuscarListaOrgVendasComDDD(
           br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda[] listaOrganizacaoVendaComDDD) {
           this.listaOrganizacaoVendaComDDD = listaOrganizacaoVendaComDDD;
    }


    /**
     * Gets the listaOrganizacaoVendaComDDD value for this ResultadoBuscarListaOrgVendasComDDD.
     * 
     * @return listaOrganizacaoVendaComDDD
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda[] getListaOrganizacaoVendaComDDD() {
        return listaOrganizacaoVendaComDDD;
    }


    /**
     * Sets the listaOrganizacaoVendaComDDD value for this ResultadoBuscarListaOrgVendasComDDD.
     * 
     * @param listaOrganizacaoVendaComDDD
     */
    public void setListaOrganizacaoVendaComDDD(br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda[] listaOrganizacaoVendaComDDD) {
        this.listaOrganizacaoVendaComDDD = listaOrganizacaoVendaComDDD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaOrgVendasComDDD)) return false;
        ResultadoBuscarListaOrgVendasComDDD other = (ResultadoBuscarListaOrgVendasComDDD) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaOrganizacaoVendaComDDD==null && other.getListaOrganizacaoVendaComDDD()==null) || 
             (this.listaOrganizacaoVendaComDDD!=null &&
              java.util.Arrays.equals(this.listaOrganizacaoVendaComDDD, other.getListaOrganizacaoVendaComDDD())));
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
        if (getListaOrganizacaoVendaComDDD() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaOrganizacaoVendaComDDD());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaOrganizacaoVendaComDDD(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaOrgVendasComDDD.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", ">ResultadoBuscarListaOrgVendasComDDD"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaOrganizacaoVendaComDDD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", "ListaOrganizacaoVendaComDDD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", ">>>ResultadoBuscarListaOrgVendasComDDD>ListaOrganizacaoVendaComDDD>OrganizacaoVenda"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOrganizacaoVendas", "OrganizacaoVenda"));
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
