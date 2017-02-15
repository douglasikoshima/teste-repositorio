/**
 * ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional  implements java.io.Serializable {
    private java.lang.Long idRegional;

    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegionalListaUfUf[] listaUf;

    public ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional() {
    }

    public ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional(
           java.lang.Long idRegional,
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegionalListaUfUf[] listaUf) {
           this.idRegional = idRegional;
           this.listaUf = listaUf;
    }


    /**
     * Gets the idRegional value for this ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional.
     * 
     * @return idRegional
     */
    public java.lang.Long getIdRegional() {
        return idRegional;
    }


    /**
     * Sets the idRegional value for this ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional.
     * 
     * @param idRegional
     */
    public void setIdRegional(java.lang.Long idRegional) {
        this.idRegional = idRegional;
    }


    /**
     * Gets the listaUf value for this ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional.
     * 
     * @return listaUf
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegionalListaUfUf[] getListaUf() {
        return listaUf;
    }


    /**
     * Sets the listaUf value for this ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional.
     * 
     * @param listaUf
     */
    public void setListaUf(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegionalListaUfUf[] listaUf) {
        this.listaUf = listaUf;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional)) return false;
        ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional other = (ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idRegional==null && other.getIdRegional()==null) || 
             (this.idRegional!=null &&
              this.idRegional.equals(other.getIdRegional()))) &&
            ((this.listaUf==null && other.getListaUf()==null) || 
             (this.listaUf!=null &&
              java.util.Arrays.equals(this.listaUf, other.getListaUf())));
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
        if (getIdRegional() != null) {
            _hashCode += getIdRegional().hashCode();
        }
        if (getListaUf() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaUf());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaUf(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>>ParametrosBuscarListaPlanoParametrizacao>ListaRegional>Regional"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idRegional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idRegional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaUf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ListaUf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>>>>ParametrosBuscarListaPlanoParametrizacao>ListaRegional>Regional>ListaUf>Uf"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "Uf"));
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
