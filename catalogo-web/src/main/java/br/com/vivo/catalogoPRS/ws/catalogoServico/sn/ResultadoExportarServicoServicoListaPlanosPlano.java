/**
 * ResultadoExportarServicoServicoListaPlanosPlano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoExportarServicoServicoListaPlanosPlano  implements java.io.Serializable {
    private java.lang.String nmPlano;

    public ResultadoExportarServicoServicoListaPlanosPlano() {
    }

    public ResultadoExportarServicoServicoListaPlanosPlano(
           java.lang.String nmPlano) {
           this.nmPlano = nmPlano;
    }


    /**
     * Gets the nmPlano value for this ResultadoExportarServicoServicoListaPlanosPlano.
     * 
     * @return nmPlano
     */
    public java.lang.String getNmPlano() {
        return nmPlano;
    }


    /**
     * Sets the nmPlano value for this ResultadoExportarServicoServicoListaPlanosPlano.
     * 
     * @param nmPlano
     */
    public void setNmPlano(java.lang.String nmPlano) {
        this.nmPlano = nmPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoExportarServicoServicoListaPlanosPlano)) return false;
        ResultadoExportarServicoServicoListaPlanosPlano other = (ResultadoExportarServicoServicoListaPlanosPlano) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nmPlano==null && other.getNmPlano()==null) || 
             (this.nmPlano!=null &&
              this.nmPlano.equals(other.getNmPlano())));
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
        if (getNmPlano() != null) {
            _hashCode += getNmPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoExportarServicoServicoListaPlanosPlano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>>ResultadoExportarServico>Servico>ListaPlanos>Plano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmPlano"));
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
