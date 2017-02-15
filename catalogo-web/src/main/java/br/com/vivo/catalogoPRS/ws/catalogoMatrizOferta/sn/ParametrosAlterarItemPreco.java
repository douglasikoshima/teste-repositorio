/**
 * ParametrosAlterarItemPreco.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ParametrosAlterarItemPreco  implements java.io.Serializable {
    private long idMatrizOfertaItemPreco;

    private double valor;

    private java.lang.String nmUsuarioAlteracao;

    public ParametrosAlterarItemPreco() {
    }

    public ParametrosAlterarItemPreco(
           long idMatrizOfertaItemPreco,
           double valor,
           java.lang.String nmUsuarioAlteracao) {
           this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
           this.valor = valor;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the idMatrizOfertaItemPreco value for this ParametrosAlterarItemPreco.
     * 
     * @return idMatrizOfertaItemPreco
     */
    public long getIdMatrizOfertaItemPreco() {
        return idMatrizOfertaItemPreco;
    }


    /**
     * Sets the idMatrizOfertaItemPreco value for this ParametrosAlterarItemPreco.
     * 
     * @param idMatrizOfertaItemPreco
     */
    public void setIdMatrizOfertaItemPreco(long idMatrizOfertaItemPreco) {
        this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
    }


    /**
     * Gets the valor value for this ParametrosAlterarItemPreco.
     * 
     * @return valor
     */
    public double getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this ParametrosAlterarItemPreco.
     * 
     * @param valor
     */
    public void setValor(double valor) {
        this.valor = valor;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ParametrosAlterarItemPreco.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ParametrosAlterarItemPreco.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarItemPreco)) return false;
        ParametrosAlterarItemPreco other = (ParametrosAlterarItemPreco) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idMatrizOfertaItemPreco == other.getIdMatrizOfertaItemPreco() &&
            this.valor == other.getValor() &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao())));
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
        _hashCode += new Long(getIdMatrizOfertaItemPreco()).hashCode();
        _hashCode += new Double(getValor()).hashCode();
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarItemPreco.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosAlterarItemPreco"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMatrizOfertaItemPreco");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idMatrizOfertaItemPreco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "valor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "nmUsuarioAlteracao"));
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
