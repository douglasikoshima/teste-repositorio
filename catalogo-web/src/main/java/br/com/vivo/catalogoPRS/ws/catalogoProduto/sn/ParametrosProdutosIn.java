/**
 * ParametrosProdutosIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ParametrosProdutosIn  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.PaginacaoIn paginacaoIn;

    private long idTipoProduto;

    private java.lang.Long idFabricante;

    private java.lang.Long idTecnologia;

    private java.lang.String cdCodigo;

    private java.lang.Long idModelo;

    public ParametrosProdutosIn() {
    }

    public ParametrosProdutosIn(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.PaginacaoIn paginacaoIn,
           long idTipoProduto,
           java.lang.Long idFabricante,
           java.lang.Long idTecnologia,
           java.lang.String cdCodigo,
           java.lang.Long idModelo) {
           this.paginacaoIn = paginacaoIn;
           this.idTipoProduto = idTipoProduto;
           this.idFabricante = idFabricante;
           this.idTecnologia = idTecnologia;
           this.cdCodigo = cdCodigo;
           this.idModelo = idModelo;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosProdutosIn.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosProdutosIn.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the idTipoProduto value for this ParametrosProdutosIn.
     * 
     * @return idTipoProduto
     */
    public long getIdTipoProduto() {
        return idTipoProduto;
    }


    /**
     * Sets the idTipoProduto value for this ParametrosProdutosIn.
     * 
     * @param idTipoProduto
     */
    public void setIdTipoProduto(long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }


    /**
     * Gets the idFabricante value for this ParametrosProdutosIn.
     * 
     * @return idFabricante
     */
    public java.lang.Long getIdFabricante() {
        return idFabricante;
    }


    /**
     * Sets the idFabricante value for this ParametrosProdutosIn.
     * 
     * @param idFabricante
     */
    public void setIdFabricante(java.lang.Long idFabricante) {
        this.idFabricante = idFabricante;
    }


    /**
     * Gets the idTecnologia value for this ParametrosProdutosIn.
     * 
     * @return idTecnologia
     */
    public java.lang.Long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ParametrosProdutosIn.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(java.lang.Long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the cdCodigo value for this ParametrosProdutosIn.
     * 
     * @return cdCodigo
     */
    public java.lang.String getCdCodigo() {
        return cdCodigo;
    }


    /**
     * Sets the cdCodigo value for this ParametrosProdutosIn.
     * 
     * @param cdCodigo
     */
    public void setCdCodigo(java.lang.String cdCodigo) {
        this.cdCodigo = cdCodigo;
    }


    /**
     * Gets the idModelo value for this ParametrosProdutosIn.
     * 
     * @return idModelo
     */
    public java.lang.Long getIdModelo() {
        return idModelo;
    }


    /**
     * Sets the idModelo value for this ParametrosProdutosIn.
     * 
     * @param idModelo
     */
    public void setIdModelo(java.lang.Long idModelo) {
        this.idModelo = idModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosProdutosIn)) return false;
        ParametrosProdutosIn other = (ParametrosProdutosIn) obj;
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
            this.idTipoProduto == other.getIdTipoProduto() &&
            ((this.idFabricante==null && other.getIdFabricante()==null) || 
             (this.idFabricante!=null &&
              this.idFabricante.equals(other.getIdFabricante()))) &&
            ((this.idTecnologia==null && other.getIdTecnologia()==null) || 
             (this.idTecnologia!=null &&
              this.idTecnologia.equals(other.getIdTecnologia()))) &&
            ((this.cdCodigo==null && other.getCdCodigo()==null) || 
             (this.cdCodigo!=null &&
              this.cdCodigo.equals(other.getCdCodigo()))) &&
            ((this.idModelo==null && other.getIdModelo()==null) || 
             (this.idModelo!=null &&
              this.idModelo.equals(other.getIdModelo())));
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
        _hashCode += new Long(getIdTipoProduto()).hashCode();
        if (getIdFabricante() != null) {
            _hashCode += getIdFabricante().hashCode();
        }
        if (getIdTecnologia() != null) {
            _hashCode += getIdTecnologia().hashCode();
        }
        if (getCdCodigo() != null) {
            _hashCode += getCdCodigo().hashCode();
        }
        if (getIdModelo() != null) {
            _hashCode += getIdModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosProdutosIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosProdutosIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFabricante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idFabricante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "cdCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
