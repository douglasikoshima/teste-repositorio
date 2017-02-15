/**
 * ParametrosBuscarListaModeloVenda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class ParametrosBuscarListaModeloVenda  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn;

    private java.lang.String nmModeloVenda;

    private java.lang.String cdModeloVenda;

    public ParametrosBuscarListaModeloVenda() {
    }

    public ParametrosBuscarListaModeloVenda(
           br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn,
           java.lang.String nmModeloVenda,
           java.lang.String cdModeloVenda) {
           this.paginacaoIn = paginacaoIn;
           this.nmModeloVenda = nmModeloVenda;
           this.cdModeloVenda = cdModeloVenda;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarListaModeloVenda.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarListaModeloVenda.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the nmModeloVenda value for this ParametrosBuscarListaModeloVenda.
     * 
     * @return nmModeloVenda
     */
    public java.lang.String getNmModeloVenda() {
        return nmModeloVenda;
    }


    /**
     * Sets the nmModeloVenda value for this ParametrosBuscarListaModeloVenda.
     * 
     * @param nmModeloVenda
     */
    public void setNmModeloVenda(java.lang.String nmModeloVenda) {
        this.nmModeloVenda = nmModeloVenda;
    }


    /**
     * Gets the cdModeloVenda value for this ParametrosBuscarListaModeloVenda.
     * 
     * @return cdModeloVenda
     */
    public java.lang.String getCdModeloVenda() {
        return cdModeloVenda;
    }


    /**
     * Sets the cdModeloVenda value for this ParametrosBuscarListaModeloVenda.
     * 
     * @param cdModeloVenda
     */
    public void setCdModeloVenda(java.lang.String cdModeloVenda) {
        this.cdModeloVenda = cdModeloVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaModeloVenda)) return false;
        ParametrosBuscarListaModeloVenda other = (ParametrosBuscarListaModeloVenda) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paginacaoIn==null && other.getPaginacaoIn()==null) || 
             (this.paginacaoIn!=null &&
              this.paginacaoIn.equals(other.getPaginacaoIn()))) &&
            ((this.nmModeloVenda==null && other.getNmModeloVenda()==null) || 
             (this.nmModeloVenda!=null &&
              this.nmModeloVenda.equals(other.getNmModeloVenda()))) &&
            ((this.cdModeloVenda==null && other.getCdModeloVenda()==null) || 
             (this.cdModeloVenda!=null &&
              this.cdModeloVenda.equals(other.getCdModeloVenda())));
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
        if (getPaginacaoIn() != null) {
            _hashCode += getPaginacaoIn().hashCode();
        }
        if (getNmModeloVenda() != null) {
            _hashCode += getNmModeloVenda().hashCode();
        }
        if (getCdModeloVenda() != null) {
            _hashCode += getCdModeloVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaModeloVenda.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ParametrosBuscarListaModeloVenda"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "nmModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "cdModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
