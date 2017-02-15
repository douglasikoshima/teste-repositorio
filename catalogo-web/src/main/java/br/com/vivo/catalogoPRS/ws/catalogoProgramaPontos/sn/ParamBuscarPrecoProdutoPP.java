/**
 * ParamBuscarPrecoProdutoPP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

public class ParamBuscarPrecoProdutoPP  implements java.io.Serializable {
    private long idTipoProduto;

    private long idFabricante;

    private long idModelo;

    private long idOrgVendas;

    private br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoIn paginacaoIn;

    public ParamBuscarPrecoProdutoPP() {
    }

    public ParamBuscarPrecoProdutoPP(
           long idTipoProduto,
           long idFabricante,
           long idModelo,
           long idOrgVendas,
           br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoIn paginacaoIn) {
           this.idTipoProduto = idTipoProduto;
           this.idFabricante = idFabricante;
           this.idModelo = idModelo;
           this.idOrgVendas = idOrgVendas;
           this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the idTipoProduto value for this ParamBuscarPrecoProdutoPP.
     * 
     * @return idTipoProduto
     */
    public long getIdTipoProduto() {
        return idTipoProduto;
    }


    /**
     * Sets the idTipoProduto value for this ParamBuscarPrecoProdutoPP.
     * 
     * @param idTipoProduto
     */
    public void setIdTipoProduto(long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }


    /**
     * Gets the idFabricante value for this ParamBuscarPrecoProdutoPP.
     * 
     * @return idFabricante
     */
    public long getIdFabricante() {
        return idFabricante;
    }


    /**
     * Sets the idFabricante value for this ParamBuscarPrecoProdutoPP.
     * 
     * @param idFabricante
     */
    public void setIdFabricante(long idFabricante) {
        this.idFabricante = idFabricante;
    }


    /**
     * Gets the idModelo value for this ParamBuscarPrecoProdutoPP.
     * 
     * @return idModelo
     */
    public long getIdModelo() {
        return idModelo;
    }


    /**
     * Sets the idModelo value for this ParamBuscarPrecoProdutoPP.
     * 
     * @param idModelo
     */
    public void setIdModelo(long idModelo) {
        this.idModelo = idModelo;
    }


    /**
     * Gets the idOrgVendas value for this ParamBuscarPrecoProdutoPP.
     * 
     * @return idOrgVendas
     */
    public long getIdOrgVendas() {
        return idOrgVendas;
    }


    /**
     * Sets the idOrgVendas value for this ParamBuscarPrecoProdutoPP.
     * 
     * @param idOrgVendas
     */
    public void setIdOrgVendas(long idOrgVendas) {
        this.idOrgVendas = idOrgVendas;
    }


    /**
     * Gets the paginacaoIn value for this ParamBuscarPrecoProdutoPP.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParamBuscarPrecoProdutoPP.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamBuscarPrecoProdutoPP)) return false;
        ParamBuscarPrecoProdutoPP other = (ParamBuscarPrecoProdutoPP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTipoProduto == other.getIdTipoProduto() &&
            this.idFabricante == other.getIdFabricante() &&
            this.idModelo == other.getIdModelo() &&
            this.idOrgVendas == other.getIdOrgVendas() &&
            ((this.paginacaoIn==null && other.getPaginacaoIn()==null) || 
             (this.paginacaoIn!=null &&
              this.paginacaoIn.equals(other.getPaginacaoIn())));
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
        _hashCode += new Long(getIdTipoProduto()).hashCode();
        _hashCode += new Long(getIdFabricante()).hashCode();
        _hashCode += new Long(getIdModelo()).hashCode();
        _hashCode += new Long(getIdOrgVendas()).hashCode();
        if (getPaginacaoIn() != null) {
            _hashCode += getPaginacaoIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamBuscarPrecoProdutoPP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ParamBuscarPrecoProdutoPP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFabricante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idFabricante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrgVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idOrgVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">PaginacaoIn"));
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
