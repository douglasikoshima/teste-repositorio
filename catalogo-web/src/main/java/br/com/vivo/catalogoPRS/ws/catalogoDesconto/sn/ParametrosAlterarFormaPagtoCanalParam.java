/**
 * ParametrosAlterarFormaPagtoCanalParam.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public class ParametrosAlterarFormaPagtoCanalParam  implements java.io.Serializable {
    private long idCanal;

    private long idFormaPagamento;

    private java.lang.Long nrMaxParcSemJuros;

    private java.math.BigDecimal taxaJuros;

    private long nrParcelasMax;

    public ParametrosAlterarFormaPagtoCanalParam() {
    }

    public ParametrosAlterarFormaPagtoCanalParam(
           long idCanal,
           long idFormaPagamento,
           java.lang.Long nrMaxParcSemJuros,
           java.math.BigDecimal taxaJuros,
           long nrParcelasMax) {
           this.idCanal = idCanal;
           this.idFormaPagamento = idFormaPagamento;
           this.nrMaxParcSemJuros = nrMaxParcSemJuros;
           this.taxaJuros = taxaJuros;
           this.nrParcelasMax = nrParcelasMax;
    }


    /**
     * Gets the idCanal value for this ParametrosAlterarFormaPagtoCanalParam.
     * 
     * @return idCanal
     */
    public long getIdCanal() {
        return idCanal;
    }


    /**
     * Sets the idCanal value for this ParametrosAlterarFormaPagtoCanalParam.
     * 
     * @param idCanal
     */
    public void setIdCanal(long idCanal) {
        this.idCanal = idCanal;
    }


    /**
     * Gets the idFormaPagamento value for this ParametrosAlterarFormaPagtoCanalParam.
     * 
     * @return idFormaPagamento
     */
    public long getIdFormaPagamento() {
        return idFormaPagamento;
    }


    /**
     * Sets the idFormaPagamento value for this ParametrosAlterarFormaPagtoCanalParam.
     * 
     * @param idFormaPagamento
     */
    public void setIdFormaPagamento(long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }


    /**
     * Gets the nrMaxParcSemJuros value for this ParametrosAlterarFormaPagtoCanalParam.
     * 
     * @return nrMaxParcSemJuros
     */
    public java.lang.Long getNrMaxParcSemJuros() {
        return nrMaxParcSemJuros;
    }


    /**
     * Sets the nrMaxParcSemJuros value for this ParametrosAlterarFormaPagtoCanalParam.
     * 
     * @param nrMaxParcSemJuros
     */
    public void setNrMaxParcSemJuros(java.lang.Long nrMaxParcSemJuros) {
        this.nrMaxParcSemJuros = nrMaxParcSemJuros;
    }


    /**
     * Gets the taxaJuros value for this ParametrosAlterarFormaPagtoCanalParam.
     * 
     * @return taxaJuros
     */
    public java.math.BigDecimal getTaxaJuros() {
        return taxaJuros;
    }


    /**
     * Sets the taxaJuros value for this ParametrosAlterarFormaPagtoCanalParam.
     * 
     * @param taxaJuros
     */
    public void setTaxaJuros(java.math.BigDecimal taxaJuros) {
        this.taxaJuros = taxaJuros;
    }


    /**
     * Gets the nrParcelasMax value for this ParametrosAlterarFormaPagtoCanalParam.
     * 
     * @return nrParcelasMax
     */
    public long getNrParcelasMax() {
        return nrParcelasMax;
    }


    /**
     * Sets the nrParcelasMax value for this ParametrosAlterarFormaPagtoCanalParam.
     * 
     * @param nrParcelasMax
     */
    public void setNrParcelasMax(long nrParcelasMax) {
        this.nrParcelasMax = nrParcelasMax;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarFormaPagtoCanalParam)) return false;
        ParametrosAlterarFormaPagtoCanalParam other = (ParametrosAlterarFormaPagtoCanalParam) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idCanal == other.getIdCanal() &&
            this.idFormaPagamento == other.getIdFormaPagamento() &&
            ((this.nrMaxParcSemJuros==null && other.getNrMaxParcSemJuros()==null) || 
             (this.nrMaxParcSemJuros!=null &&
              this.nrMaxParcSemJuros.equals(other.getNrMaxParcSemJuros()))) &&
            ((this.taxaJuros==null && other.getTaxaJuros()==null) || 
             (this.taxaJuros!=null &&
              this.taxaJuros.equals(other.getTaxaJuros()))) &&
            this.nrParcelasMax == other.getNrParcelasMax();
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
        _hashCode += new Long(getIdCanal()).hashCode();
        _hashCode += new Long(getIdFormaPagamento()).hashCode();
        if (getNrMaxParcSemJuros() != null) {
            _hashCode += getNrMaxParcSemJuros().hashCode();
        }
        if (getTaxaJuros() != null) {
            _hashCode += getTaxaJuros().hashCode();
        }
        _hashCode += new Long(getNrParcelasMax()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarFormaPagtoCanalParam.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">ParametrosAlterarFormaPagtoCanalParam"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "IdCanal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFormaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "idFormaPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nrMaxParcSemJuros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "nrMaxParcSemJuros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
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
