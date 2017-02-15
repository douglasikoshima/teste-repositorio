/**
 * ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVendaListaTecnologiaPaiTecnologiaPai.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVendaListaTecnologiaPaiTecnologiaPai  implements java.io.Serializable {
    private java.lang.Long idTecnologiaPai;

    private java.lang.String nmTecnologia;

    public ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVendaListaTecnologiaPaiTecnologiaPai() {
    }

    public ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVendaListaTecnologiaPaiTecnologiaPai(
           java.lang.Long idTecnologiaPai,
           java.lang.String nmTecnologia) {
           this.idTecnologiaPai = idTecnologiaPai;
           this.nmTecnologia = nmTecnologia;
    }


    /**
     * Gets the idTecnologiaPai value for this ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVendaListaTecnologiaPaiTecnologiaPai.
     * 
     * @return idTecnologiaPai
     */
    public java.lang.Long getIdTecnologiaPai() {
        return idTecnologiaPai;
    }


    /**
     * Sets the idTecnologiaPai value for this ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVendaListaTecnologiaPaiTecnologiaPai.
     * 
     * @param idTecnologiaPai
     */
    public void setIdTecnologiaPai(java.lang.Long idTecnologiaPai) {
        this.idTecnologiaPai = idTecnologiaPai;
    }


    /**
     * Gets the nmTecnologia value for this ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVendaListaTecnologiaPaiTecnologiaPai.
     * 
     * @return nmTecnologia
     */
    public java.lang.String getNmTecnologia() {
        return nmTecnologia;
    }


    /**
     * Sets the nmTecnologia value for this ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVendaListaTecnologiaPaiTecnologiaPai.
     * 
     * @param nmTecnologia
     */
    public void setNmTecnologia(java.lang.String nmTecnologia) {
        this.nmTecnologia = nmTecnologia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVendaListaTecnologiaPaiTecnologiaPai)) return false;
        ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVendaListaTecnologiaPaiTecnologiaPai other = (ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVendaListaTecnologiaPaiTecnologiaPai) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idTecnologiaPai==null && other.getIdTecnologiaPai()==null) || 
             (this.idTecnologiaPai!=null &&
              this.idTecnologiaPai.equals(other.getIdTecnologiaPai()))) &&
            ((this.nmTecnologia==null && other.getNmTecnologia()==null) || 
             (this.nmTecnologia!=null &&
              this.nmTecnologia.equals(other.getNmTecnologia())));
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
        if (getIdTecnologiaPai() != null) {
            _hashCode += getIdTecnologiaPai().hashCode();
        }
        if (getNmTecnologia() != null) {
            _hashCode += getNmTecnologia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarDadosModeloVendaListaBuscarDadosModeloVendaDadosModeloVendaListaTecnologiaPaiTecnologiaPai.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">>>>>ResultadoBuscarDadosModeloVenda>ListaBuscarDadosModeloVenda>DadosModeloVenda>ListaTecnologiaPai>TecnologiaPai"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologiaPai");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "idTecnologiaPai"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "nmTecnologia"));
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
