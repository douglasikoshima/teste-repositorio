/**
 * ResultadoBuscarListaParamDescontoDesconto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public class ResultadoBuscarListaParamDescontoDesconto  implements java.io.Serializable {
    private long idFormaPagtoCanalParam;

    private java.math.BigDecimal taxaJuros;

    private java.lang.Long nrParcelasMax;

    private java.math.BigInteger nrMaxParcSemJuros;

    public ResultadoBuscarListaParamDescontoDesconto() {
    }

    public ResultadoBuscarListaParamDescontoDesconto(
           long idFormaPagtoCanalParam,
           java.math.BigDecimal taxaJuros,
           java.lang.Long nrParcelasMax,
           java.math.BigInteger nrMaxParcSemJuros) {
           this.idFormaPagtoCanalParam = idFormaPagtoCanalParam;
           this.taxaJuros = taxaJuros;
           this.nrParcelasMax = nrParcelasMax;
           this.nrMaxParcSemJuros = nrMaxParcSemJuros;
    }


    /**
     * Gets the idFormaPagtoCanalParam value for this ResultadoBuscarListaParamDescontoDesconto.
     * 
     * @return idFormaPagtoCanalParam
     */
    public long getIdFormaPagtoCanalParam() {
        return idFormaPagtoCanalParam;
    }


    /**
     * Sets the idFormaPagtoCanalParam value for this ResultadoBuscarListaParamDescontoDesconto.
     * 
     * @param idFormaPagtoCanalParam
     */
    public void setIdFormaPagtoCanalParam(long idFormaPagtoCanalParam) {
        this.idFormaPagtoCanalParam = idFormaPagtoCanalParam;
    }


    /**
     * Gets the taxaJuros value for this ResultadoBuscarListaParamDescontoDesconto.
     * 
     * @return taxaJuros
     */
    public java.math.BigDecimal getTaxaJuros() {
        return taxaJuros;
    }


    /**
     * Sets the taxaJuros value for this ResultadoBuscarListaParamDescontoDesconto.
     * 
     * @param taxaJuros
     */
    public void setTaxaJuros(java.math.BigDecimal taxaJuros) {
        this.taxaJuros = taxaJuros;
    }


    /**
     * Gets the nrParcelasMax value for this ResultadoBuscarListaParamDescontoDesconto.
     * 
     * @return nrParcelasMax
     */
    public java.lang.Long getNrParcelasMax() {
        return nrParcelasMax;
    }


    /**
     * Sets the nrParcelasMax value for this ResultadoBuscarListaParamDescontoDesconto.
     * 
     * @param nrParcelasMax
     */
    public void setNrParcelasMax(java.lang.Long nrParcelasMax) {
        this.nrParcelasMax = nrParcelasMax;
    }


    /**
     * Gets the nrMaxParcSemJuros value for this ResultadoBuscarListaParamDescontoDesconto.
     * 
     * @return nrMaxParcSemJuros
     */
    public java.math.BigInteger getNrMaxParcSemJuros() {
        return nrMaxParcSemJuros;
    }


    /**
     * Sets the nrMaxParcSemJuros value for this ResultadoBuscarListaParamDescontoDesconto.
     * 
     * @param nrMaxParcSemJuros
     */
    public void setNrMaxParcSemJuros(java.math.BigInteger nrMaxParcSemJuros) {
        this.nrMaxParcSemJuros = nrMaxParcSemJuros;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaParamDescontoDesconto)) return false;
        ResultadoBuscarListaParamDescontoDesconto other = (ResultadoBuscarListaParamDescontoDesconto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idFormaPagtoCanalParam == other.getIdFormaPagtoCanalParam() &&
            ((this.taxaJuros==null && other.getTaxaJuros()==null) || 
             (this.taxaJuros!=null &&
              this.taxaJuros.equals(other.getTaxaJuros()))) &&
            ((this.nrParcelasMax==null && other.getNrParcelasMax()==null) || 
             (this.nrParcelasMax!=null &&
              this.nrParcelasMax.equals(other.getNrParcelasMax()))) &&
            ((this.nrMaxParcSemJuros==null && other.getNrMaxParcSemJuros()==null) || 
             (this.nrMaxParcSemJuros!=null &&
              this.nrMaxParcSemJuros.equals(other.getNrMaxParcSemJuros())));
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
        _hashCode += new Long(getIdFormaPagtoCanalParam()).hashCode();
        if (getTaxaJuros() != null) {
            _hashCode += getTaxaJuros().hashCode();
        }
        if (getNrParcelasMax() != null) {
            _hashCode += getNrParcelasMax().hashCode();
        }
        if (getNrMaxParcSemJuros() != null) {
            _hashCode += getNrMaxParcSemJuros().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaParamDescontoDesconto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">>ResultadoBuscarListaParamDesconto>Desconto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFormaPagtoCanalParam");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "idFormaPagtoCanalParam"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxaJuros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "taxaJuros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nrParcelasMax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "nrParcelasMax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nrMaxParcSemJuros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "nrMaxParcSemJuros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
