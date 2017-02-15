/**
 * ResultBuscarPrecoProdutoPPListaProdutoProduto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

public class ResultBuscarPrecoProdutoPPListaProdutoProduto  implements java.io.Serializable {
    private long idProduto;

    private java.lang.String cdCodigo;

    private java.lang.String dsSap;

    private java.lang.String nmProduto;

    private java.lang.String nmTecnologia;

    private double valor;

    private java.lang.String inDisponivel;

    private long idOrgVendas;

    private java.lang.String sgOrgVendas;

    public ResultBuscarPrecoProdutoPPListaProdutoProduto() {
    }

    public ResultBuscarPrecoProdutoPPListaProdutoProduto(
           long idProduto,
           java.lang.String cdCodigo,
           java.lang.String dsSap,
           java.lang.String nmProduto,
           java.lang.String nmTecnologia,
           double valor,
           java.lang.String inDisponivel,
           long idOrgVendas,
           java.lang.String sgOrgVendas) {
           this.idProduto = idProduto;
           this.cdCodigo = cdCodigo;
           this.dsSap = dsSap;
           this.nmProduto = nmProduto;
           this.nmTecnologia = nmTecnologia;
           this.valor = valor;
           this.inDisponivel = inDisponivel;
           this.idOrgVendas = idOrgVendas;
           this.sgOrgVendas = sgOrgVendas;
    }


    /**
     * Gets the idProduto value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @return idProduto
     */
    public long getIdProduto() {
        return idProduto;
    }


    /**
     * Sets the idProduto value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @param idProduto
     */
    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }


    /**
     * Gets the cdCodigo value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @return cdCodigo
     */
    public java.lang.String getCdCodigo() {
        return cdCodigo;
    }


    /**
     * Sets the cdCodigo value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @param cdCodigo
     */
    public void setCdCodigo(java.lang.String cdCodigo) {
        this.cdCodigo = cdCodigo;
    }


    /**
     * Gets the dsSap value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @return dsSap
     */
    public java.lang.String getDsSap() {
        return dsSap;
    }


    /**
     * Sets the dsSap value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @param dsSap
     */
    public void setDsSap(java.lang.String dsSap) {
        this.dsSap = dsSap;
    }


    /**
     * Gets the nmProduto value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @return nmProduto
     */
    public java.lang.String getNmProduto() {
        return nmProduto;
    }


    /**
     * Sets the nmProduto value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @param nmProduto
     */
    public void setNmProduto(java.lang.String nmProduto) {
        this.nmProduto = nmProduto;
    }


    /**
     * Gets the nmTecnologia value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @return nmTecnologia
     */
    public java.lang.String getNmTecnologia() {
        return nmTecnologia;
    }


    /**
     * Sets the nmTecnologia value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @param nmTecnologia
     */
    public void setNmTecnologia(java.lang.String nmTecnologia) {
        this.nmTecnologia = nmTecnologia;
    }


    /**
     * Gets the valor value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @return valor
     */
    public double getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @param valor
     */
    public void setValor(double valor) {
        this.valor = valor;
    }


    /**
     * Gets the inDisponivel value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @return inDisponivel
     */
    public java.lang.String getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(java.lang.String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the idOrgVendas value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @return idOrgVendas
     */
    public long getIdOrgVendas() {
        return idOrgVendas;
    }


    /**
     * Sets the idOrgVendas value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @param idOrgVendas
     */
    public void setIdOrgVendas(long idOrgVendas) {
        this.idOrgVendas = idOrgVendas;
    }


    /**
     * Gets the sgOrgVendas value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @return sgOrgVendas
     */
    public java.lang.String getSgOrgVendas() {
        return sgOrgVendas;
    }


    /**
     * Sets the sgOrgVendas value for this ResultBuscarPrecoProdutoPPListaProdutoProduto.
     * 
     * @param sgOrgVendas
     */
    public void setSgOrgVendas(java.lang.String sgOrgVendas) {
        this.sgOrgVendas = sgOrgVendas;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultBuscarPrecoProdutoPPListaProdutoProduto)) return false;
        ResultBuscarPrecoProdutoPPListaProdutoProduto other = (ResultBuscarPrecoProdutoPPListaProdutoProduto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idProduto == other.getIdProduto() &&
            ((this.cdCodigo==null && other.getCdCodigo()==null) || 
             (this.cdCodigo!=null &&
              this.cdCodigo.equals(other.getCdCodigo()))) &&
            ((this.dsSap==null && other.getDsSap()==null) || 
             (this.dsSap!=null &&
              this.dsSap.equals(other.getDsSap()))) &&
            ((this.nmProduto==null && other.getNmProduto()==null) || 
             (this.nmProduto!=null &&
              this.nmProduto.equals(other.getNmProduto()))) &&
            ((this.nmTecnologia==null && other.getNmTecnologia()==null) || 
             (this.nmTecnologia!=null &&
              this.nmTecnologia.equals(other.getNmTecnologia()))) &&
            this.valor == other.getValor() &&
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel()))) &&
            this.idOrgVendas == other.getIdOrgVendas() &&
            ((this.sgOrgVendas==null && other.getSgOrgVendas()==null) || 
             (this.sgOrgVendas!=null &&
              this.sgOrgVendas.equals(other.getSgOrgVendas())));
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
        _hashCode += new Long(getIdProduto()).hashCode();
        if (getCdCodigo() != null) {
            _hashCode += getCdCodigo().hashCode();
        }
        if (getDsSap() != null) {
            _hashCode += getDsSap().hashCode();
        }
        if (getNmProduto() != null) {
            _hashCode += getNmProduto().hashCode();
        }
        if (getNmTecnologia() != null) {
            _hashCode += getNmTecnologia().hashCode();
        }
        _hashCode += new Double(getValor()).hashCode();
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
        }
        _hashCode += new Long(getIdOrgVendas()).hashCode();
        if (getSgOrgVendas() != null) {
            _hashCode += getSgOrgVendas().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultBuscarPrecoProdutoPPListaProdutoProduto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>>ResultBuscarPrecoProdutoPP>ListaProduto>Produto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "cdCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsSap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "dsSap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "nmProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "nmTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "valor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "inDisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrgVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idOrgVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgOrgVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "sgOrgVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
