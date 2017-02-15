/**
 * ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal  implements java.io.Serializable {
    private java.lang.Long idCanal;

    private java.lang.String nmCanal;

    public ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal() {
    }

    public ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal(
           java.lang.Long idCanal,
           java.lang.String nmCanal) {
           this.idCanal = idCanal;
           this.nmCanal = nmCanal;
    }


    /**
     * Gets the idCanal value for this ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal.
     * 
     * @return idCanal
     */
    public java.lang.Long getIdCanal() {
        return idCanal;
    }


    /**
     * Sets the idCanal value for this ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal.
     * 
     * @param idCanal
     */
    public void setIdCanal(java.lang.Long idCanal) {
        this.idCanal = idCanal;
    }


    /**
     * Gets the nmCanal value for this ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal.
     * 
     * @return nmCanal
     */
    public java.lang.String getNmCanal() {
        return nmCanal;
    }


    /**
     * Sets the nmCanal value for this ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal.
     * 
     * @param nmCanal
     */
    public void setNmCanal(java.lang.String nmCanal) {
        this.nmCanal = nmCanal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal)) return false;
        ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal other = (ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idCanal==null && other.getIdCanal()==null) || 
             (this.idCanal!=null &&
              this.idCanal.equals(other.getIdCanal()))) &&
            ((this.nmCanal==null && other.getNmCanal()==null) || 
             (this.nmCanal!=null &&
              this.nmCanal.equals(other.getNmCanal())));
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
        if (getIdCanal() != null) {
            _hashCode += getIdCanal().hashCode();
        }
        if (getNmCanal() != null) {
            _hashCode += getNmCanal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho>ListaCanal>Canal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idCanal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "nmCanal"));
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
