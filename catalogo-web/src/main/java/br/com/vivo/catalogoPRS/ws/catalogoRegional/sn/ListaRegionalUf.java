/**
 * ListaRegionalUf.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoRegional.sn;

public class ListaRegionalUf  implements java.io.Serializable {
    private java.math.BigInteger idRegional;

    private java.lang.String nmRegional;

    private br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.ListaRegionalUfListaUFUf[] listaUF;

    public ListaRegionalUf() {
    }

    public ListaRegionalUf(
           java.math.BigInteger idRegional,
           java.lang.String nmRegional,
           br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.ListaRegionalUfListaUFUf[] listaUF) {
           this.idRegional = idRegional;
           this.nmRegional = nmRegional;
           this.listaUF = listaUF;
    }


    /**
     * Gets the idRegional value for this ListaRegionalUf.
     * 
     * @return idRegional
     */
    public java.math.BigInteger getIdRegional() {
        return idRegional;
    }


    /**
     * Sets the idRegional value for this ListaRegionalUf.
     * 
     * @param idRegional
     */
    public void setIdRegional(java.math.BigInteger idRegional) {
        this.idRegional = idRegional;
    }


    /**
     * Gets the nmRegional value for this ListaRegionalUf.
     * 
     * @return nmRegional
     */
    public java.lang.String getNmRegional() {
        return nmRegional;
    }


    /**
     * Sets the nmRegional value for this ListaRegionalUf.
     * 
     * @param nmRegional
     */
    public void setNmRegional(java.lang.String nmRegional) {
        this.nmRegional = nmRegional;
    }


    /**
     * Gets the listaUF value for this ListaRegionalUf.
     * 
     * @return listaUF
     */
    public br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.ListaRegionalUfListaUFUf[] getListaUF() {
        return listaUF;
    }


    /**
     * Sets the listaUF value for this ListaRegionalUf.
     * 
     * @param listaUF
     */
    public void setListaUF(br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.ListaRegionalUfListaUFUf[] listaUF) {
        this.listaUF = listaUF;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaRegionalUf)) return false;
        ListaRegionalUf other = (ListaRegionalUf) obj;
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
            ((this.nmRegional==null && other.getNmRegional()==null) || 
             (this.nmRegional!=null &&
              this.nmRegional.equals(other.getNmRegional()))) &&
            ((this.listaUF==null && other.getListaUF()==null) || 
             (this.listaUF!=null &&
              java.util.Arrays.equals(this.listaUF, other.getListaUF())));
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
        if (getNmRegional() != null) {
            _hashCode += getNmRegional().hashCode();
        }
        if (getListaUF() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaUF());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaUF(), i);
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
        new org.apache.axis.description.TypeDesc(ListaRegionalUf.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoRegional", ">ListaRegionalUf"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idRegional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoRegional", "idRegional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmRegional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoRegional", "nmRegional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaUF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoRegional", "ListaUF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoRegional", ">>>ListaRegionalUf>ListaUF>Uf"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoRegional", "Uf"));
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
