/**
 * ParametrosAlterarItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ParametrosAlterarItem  implements java.io.Serializable {
    private long idMatrizOfertaItemPreco;

    private double valor;

    private java.util.Calendar dtInicial;

    private java.util.Calendar dtFinal;

    public ParametrosAlterarItem() {
    }

    public ParametrosAlterarItem(
           long idMatrizOfertaItemPreco,
           double valor,
           java.util.Calendar dtInicial,
           java.util.Calendar dtFinal) {
           this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
           this.valor = valor;
           this.dtInicial = dtInicial;
           this.dtFinal = dtFinal;
    }


    /**
     * Gets the idMatrizOfertaItemPreco value for this ParametrosAlterarItem.
     * 
     * @return idMatrizOfertaItemPreco
     */
    public long getIdMatrizOfertaItemPreco() {
        return idMatrizOfertaItemPreco;
    }


    /**
     * Sets the idMatrizOfertaItemPreco value for this ParametrosAlterarItem.
     * 
     * @param idMatrizOfertaItemPreco
     */
    public void setIdMatrizOfertaItemPreco(long idMatrizOfertaItemPreco) {
        this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
    }


    /**
     * Gets the valor value for this ParametrosAlterarItem.
     * 
     * @return valor
     */
    public double getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this ParametrosAlterarItem.
     * 
     * @param valor
     */
    public void setValor(double valor) {
        this.valor = valor;
    }


    /**
     * Gets the dtInicial value for this ParametrosAlterarItem.
     * 
     * @return dtInicial
     */
    public java.util.Calendar getDtInicial() {
        return dtInicial;
    }


    /**
     * Sets the dtInicial value for this ParametrosAlterarItem.
     * 
     * @param dtInicial
     */
    public void setDtInicial(java.util.Calendar dtInicial) {
        this.dtInicial = dtInicial;
    }


    /**
     * Gets the dtFinal value for this ParametrosAlterarItem.
     * 
     * @return dtFinal
     */
    public java.util.Calendar getDtFinal() {
        return dtFinal;
    }


    /**
     * Sets the dtFinal value for this ParametrosAlterarItem.
     * 
     * @param dtFinal
     */
    public void setDtFinal(java.util.Calendar dtFinal) {
        this.dtFinal = dtFinal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarItem)) return false;
        ParametrosAlterarItem other = (ParametrosAlterarItem) obj;
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
            ((this.dtInicial==null && other.getDtInicial()==null) || 
             (this.dtInicial!=null &&
              this.dtInicial.equals(other.getDtInicial()))) &&
            ((this.dtFinal==null && other.getDtFinal()==null) || 
             (this.dtFinal!=null &&
              this.dtFinal.equals(other.getDtFinal())));
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
        if (getDtInicial() != null) {
            _hashCode += getDtInicial().hashCode();
        }
        if (getDtFinal() != null) {
            _hashCode += getDtFinal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosAlterarItem"));
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
        elemField.setFieldName("dtInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dtInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dtFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
