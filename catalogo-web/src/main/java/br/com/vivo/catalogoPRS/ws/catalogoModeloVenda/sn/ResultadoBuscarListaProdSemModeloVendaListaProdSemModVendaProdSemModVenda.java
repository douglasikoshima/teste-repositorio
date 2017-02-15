/**
 * ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda  implements java.io.Serializable {
    private java.lang.Long idProduto;

    private java.lang.String cdCodigo;

    private java.lang.String dsSap;

    private java.lang.String dsProduto;

    public ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda() {
    }

    public ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda(
           java.lang.Long idProduto,
           java.lang.String cdCodigo,
           java.lang.String dsSap,
           java.lang.String dsProduto) {
           this.idProduto = idProduto;
           this.cdCodigo = cdCodigo;
           this.dsSap = dsSap;
           this.dsProduto = dsProduto;
    }


    /**
     * Gets the idProduto value for this ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda.
     * 
     * @return idProduto
     */
    public java.lang.Long getIdProduto() {
        return idProduto;
    }


    /**
     * Sets the idProduto value for this ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda.
     * 
     * @param idProduto
     */
    public void setIdProduto(java.lang.Long idProduto) {
        this.idProduto = idProduto;
    }


    /**
     * Gets the cdCodigo value for this ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda.
     * 
     * @return cdCodigo
     */
    public java.lang.String getCdCodigo() {
        return cdCodigo;
    }


    /**
     * Sets the cdCodigo value for this ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda.
     * 
     * @param cdCodigo
     */
    public void setCdCodigo(java.lang.String cdCodigo) {
        this.cdCodigo = cdCodigo;
    }


    /**
     * Gets the dsSap value for this ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda.
     * 
     * @return dsSap
     */
    public java.lang.String getDsSap() {
        return dsSap;
    }


    /**
     * Sets the dsSap value for this ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda.
     * 
     * @param dsSap
     */
    public void setDsSap(java.lang.String dsSap) {
        this.dsSap = dsSap;
    }


    /**
     * Gets the dsProduto value for this ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda.
     * 
     * @return dsProduto
     */
    public java.lang.String getDsProduto() {
        return dsProduto;
    }


    /**
     * Sets the dsProduto value for this ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda.
     * 
     * @param dsProduto
     */
    public void setDsProduto(java.lang.String dsProduto) {
        this.dsProduto = dsProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda)) return false;
        ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda other = (ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idProduto==null && other.getIdProduto()==null) || 
             (this.idProduto!=null &&
              this.idProduto.equals(other.getIdProduto()))) &&
            ((this.cdCodigo==null && other.getCdCodigo()==null) || 
             (this.cdCodigo!=null &&
              this.cdCodigo.equals(other.getCdCodigo()))) &&
            ((this.dsSap==null && other.getDsSap()==null) || 
             (this.dsSap!=null &&
              this.dsSap.equals(other.getDsSap()))) &&
            ((this.dsProduto==null && other.getDsProduto()==null) || 
             (this.dsProduto!=null &&
              this.dsProduto.equals(other.getDsProduto())));
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
        if (getIdProduto() != null) {
            _hashCode += getIdProduto().hashCode();
        }
        if (getCdCodigo() != null) {
            _hashCode += getCdCodigo().hashCode();
        }
        if (getDsSap() != null) {
            _hashCode += getDsSap().hashCode();
        }
        if (getDsProduto() != null) {
            _hashCode += getDsProduto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaProdSemModeloVendaListaProdSemModVendaProdSemModVenda.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">>>ResultadoBuscarListaProdSemModeloVenda>ListaProdSemModVenda>ProdSemModVenda"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "idProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "cdCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsSap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "dsSap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "dsProduto"));
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
