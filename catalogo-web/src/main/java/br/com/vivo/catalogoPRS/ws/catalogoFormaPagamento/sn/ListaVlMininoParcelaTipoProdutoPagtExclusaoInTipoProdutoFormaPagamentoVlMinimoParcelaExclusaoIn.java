/**
 * ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn;

public class ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn  implements java.io.Serializable {
    private long idTipoProduto;

    private java.lang.Long idPlataforma;

    private long idCanal;

    private long idFormaPagamento;

    public ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn() {
    }

    public ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn(
           long idTipoProduto,
           java.lang.Long idPlataforma,
           long idCanal,
           long idFormaPagamento) {
           this.idTipoProduto = idTipoProduto;
           this.idPlataforma = idPlataforma;
           this.idCanal = idCanal;
           this.idFormaPagamento = idFormaPagamento;
    }


    /**
     * Gets the idTipoProduto value for this ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn.
     * 
     * @return idTipoProduto
     */
    public long getIdTipoProduto() {
        return idTipoProduto;
    }


    /**
     * Sets the idTipoProduto value for this ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn.
     * 
     * @param idTipoProduto
     */
    public void setIdTipoProduto(long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }


    /**
     * Gets the idPlataforma value for this ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn.
     * 
     * @return idPlataforma
     */
    public java.lang.Long getIdPlataforma() {
        return idPlataforma;
    }


    /**
     * Sets the idPlataforma value for this ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn.
     * 
     * @param idPlataforma
     */
    public void setIdPlataforma(java.lang.Long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }


    /**
     * Gets the idCanal value for this ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn.
     * 
     * @return idCanal
     */
    public long getIdCanal() {
        return idCanal;
    }


    /**
     * Sets the idCanal value for this ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn.
     * 
     * @param idCanal
     */
    public void setIdCanal(long idCanal) {
        this.idCanal = idCanal;
    }


    /**
     * Gets the idFormaPagamento value for this ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn.
     * 
     * @return idFormaPagamento
     */
    public long getIdFormaPagamento() {
        return idFormaPagamento;
    }


    /**
     * Sets the idFormaPagamento value for this ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn.
     * 
     * @param idFormaPagamento
     */
    public void setIdFormaPagamento(long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn)) return false;
        ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn other = (ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTipoProduto == other.getIdTipoProduto() &&
            ((this.idPlataforma==null && other.getIdPlataforma()==null) || 
             (this.idPlataforma!=null &&
              this.idPlataforma.equals(other.getIdPlataforma()))) &&
            this.idCanal == other.getIdCanal() &&
            this.idFormaPagamento == other.getIdFormaPagamento();
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
        if (getIdPlataforma() != null) {
            _hashCode += getIdPlataforma().hashCode();
        }
        _hashCode += new Long(getIdCanal()).hashCode();
        _hashCode += new Long(getIdFormaPagamento()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", ">>ListaVlMininoParcelaTipoProdutoPagtExclusaoIn>TipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "IdTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "IdPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "IdCanal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFormaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "IdFormaPagamento"));
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
