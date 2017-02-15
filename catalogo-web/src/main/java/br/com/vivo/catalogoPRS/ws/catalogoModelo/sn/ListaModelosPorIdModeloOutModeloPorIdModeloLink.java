/**
 * ListaModelosPorIdModeloOutModeloPorIdModeloLink.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ListaModelosPorIdModeloOutModeloPorIdModeloLink  implements java.io.Serializable {
    private java.lang.Long idMultimidiaLink;

    private java.lang.String nmMultimidiaLink;

    public ListaModelosPorIdModeloOutModeloPorIdModeloLink() {
    }

    public ListaModelosPorIdModeloOutModeloPorIdModeloLink(
           java.lang.Long idMultimidiaLink,
           java.lang.String nmMultimidiaLink) {
           this.idMultimidiaLink = idMultimidiaLink;
           this.nmMultimidiaLink = nmMultimidiaLink;
    }


    /**
     * Gets the idMultimidiaLink value for this ListaModelosPorIdModeloOutModeloPorIdModeloLink.
     * 
     * @return idMultimidiaLink
     */
    public java.lang.Long getIdMultimidiaLink() {
        return idMultimidiaLink;
    }


    /**
     * Sets the idMultimidiaLink value for this ListaModelosPorIdModeloOutModeloPorIdModeloLink.
     * 
     * @param idMultimidiaLink
     */
    public void setIdMultimidiaLink(java.lang.Long idMultimidiaLink) {
        this.idMultimidiaLink = idMultimidiaLink;
    }


    /**
     * Gets the nmMultimidiaLink value for this ListaModelosPorIdModeloOutModeloPorIdModeloLink.
     * 
     * @return nmMultimidiaLink
     */
    public java.lang.String getNmMultimidiaLink() {
        return nmMultimidiaLink;
    }


    /**
     * Sets the nmMultimidiaLink value for this ListaModelosPorIdModeloOutModeloPorIdModeloLink.
     * 
     * @param nmMultimidiaLink
     */
    public void setNmMultimidiaLink(java.lang.String nmMultimidiaLink) {
        this.nmMultimidiaLink = nmMultimidiaLink;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaModelosPorIdModeloOutModeloPorIdModeloLink)) return false;
        ListaModelosPorIdModeloOutModeloPorIdModeloLink other = (ListaModelosPorIdModeloOutModeloPorIdModeloLink) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idMultimidiaLink==null && other.getIdMultimidiaLink()==null) || 
             (this.idMultimidiaLink!=null &&
              this.idMultimidiaLink.equals(other.getIdMultimidiaLink()))) &&
            ((this.nmMultimidiaLink==null && other.getNmMultimidiaLink()==null) || 
             (this.nmMultimidiaLink!=null &&
              this.nmMultimidiaLink.equals(other.getNmMultimidiaLink())));
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
        if (getIdMultimidiaLink() != null) {
            _hashCode += getIdMultimidiaLink().hashCode();
        }
        if (getNmMultimidiaLink() != null) {
            _hashCode += getNmMultimidiaLink().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaModelosPorIdModeloOutModeloPorIdModeloLink.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>ListaModelosPorIdModeloOut>ModeloPorIdModelo>Link"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMultimidiaLink");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idMultimidiaLink"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmMultimidiaLink");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmMultimidiaLink"));
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
