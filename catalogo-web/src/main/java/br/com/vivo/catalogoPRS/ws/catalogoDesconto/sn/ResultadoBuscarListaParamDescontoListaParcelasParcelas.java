/**
 * ResultadoBuscarListaParamDescontoListaParcelasParcelas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public class ResultadoBuscarListaParamDescontoListaParcelasParcelas  implements java.io.Serializable {
    private long idDescontoCondPagto;

    private java.math.BigDecimal fatorPreco;

    private long idCondicaoPagamento;

    private java.lang.Long nrParcela;

    public ResultadoBuscarListaParamDescontoListaParcelasParcelas() {
    }

    public ResultadoBuscarListaParamDescontoListaParcelasParcelas(
           long idDescontoCondPagto,
           java.math.BigDecimal fatorPreco,
           long idCondicaoPagamento,
           java.lang.Long nrParcela) {
           this.idDescontoCondPagto = idDescontoCondPagto;
           this.fatorPreco = fatorPreco;
           this.idCondicaoPagamento = idCondicaoPagamento;
           this.nrParcela = nrParcela;
    }


    /**
     * Gets the idDescontoCondPagto value for this ResultadoBuscarListaParamDescontoListaParcelasParcelas.
     * 
     * @return idDescontoCondPagto
     */
    public long getIdDescontoCondPagto() {
        return idDescontoCondPagto;
    }


    /**
     * Sets the idDescontoCondPagto value for this ResultadoBuscarListaParamDescontoListaParcelasParcelas.
     * 
     * @param idDescontoCondPagto
     */
    public void setIdDescontoCondPagto(long idDescontoCondPagto) {
        this.idDescontoCondPagto = idDescontoCondPagto;
    }


    /**
     * Gets the fatorPreco value for this ResultadoBuscarListaParamDescontoListaParcelasParcelas.
     * 
     * @return fatorPreco
     */
    public java.math.BigDecimal getFatorPreco() {
        return fatorPreco;
    }


    /**
     * Sets the fatorPreco value for this ResultadoBuscarListaParamDescontoListaParcelasParcelas.
     * 
     * @param fatorPreco
     */
    public void setFatorPreco(java.math.BigDecimal fatorPreco) {
        this.fatorPreco = fatorPreco;
    }


    /**
     * Gets the idCondicaoPagamento value for this ResultadoBuscarListaParamDescontoListaParcelasParcelas.
     * 
     * @return idCondicaoPagamento
     */
    public long getIdCondicaoPagamento() {
        return idCondicaoPagamento;
    }


    /**
     * Sets the idCondicaoPagamento value for this ResultadoBuscarListaParamDescontoListaParcelasParcelas.
     * 
     * @param idCondicaoPagamento
     */
    public void setIdCondicaoPagamento(long idCondicaoPagamento) {
        this.idCondicaoPagamento = idCondicaoPagamento;
    }


    /**
     * Gets the nrParcela value for this ResultadoBuscarListaParamDescontoListaParcelasParcelas.
     * 
     * @return nrParcela
     */
    public java.lang.Long getNrParcela() {
        return nrParcela;
    }


    /**
     * Sets the nrParcela value for this ResultadoBuscarListaParamDescontoListaParcelasParcelas.
     * 
     * @param nrParcela
     */
    public void setNrParcela(java.lang.Long nrParcela) {
        this.nrParcela = nrParcela;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaParamDescontoListaParcelasParcelas)) return false;
        ResultadoBuscarListaParamDescontoListaParcelasParcelas other = (ResultadoBuscarListaParamDescontoListaParcelasParcelas) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idDescontoCondPagto == other.getIdDescontoCondPagto() &&
            ((this.fatorPreco==null && other.getFatorPreco()==null) || 
             (this.fatorPreco!=null &&
              this.fatorPreco.equals(other.getFatorPreco()))) &&
            this.idCondicaoPagamento == other.getIdCondicaoPagamento() &&
            ((this.nrParcela==null && other.getNrParcela()==null) || 
             (this.nrParcela!=null &&
              this.nrParcela.equals(other.getNrParcela())));
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
        _hashCode += new Long(getIdDescontoCondPagto()).hashCode();
        if (getFatorPreco() != null) {
            _hashCode += getFatorPreco().hashCode();
        }
        _hashCode += new Long(getIdCondicaoPagamento()).hashCode();
        if (getNrParcela() != null) {
            _hashCode += getNrParcela().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaParamDescontoListaParcelasParcelas.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">>>ResultadoBuscarListaParamDesconto>ListaParcelas>Parcelas"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idDescontoCondPagto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "idDescontoCondPagto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fatorPreco");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "fatorPreco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCondicaoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "idCondicaoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nrParcela");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "nrParcela"));
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
