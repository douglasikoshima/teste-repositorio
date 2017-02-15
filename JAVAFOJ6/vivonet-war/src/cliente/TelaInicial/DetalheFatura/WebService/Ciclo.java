/**
 * Ciclo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cliente.TelaInicial.DetalheFatura.WebService;


/**
 * Mantem informacoes dos ciclos de billing por tipo de
 * 				pessoa e dia de vencimento.
 */
public class Ciclo  implements java.io.Serializable {

    /* Dia de vencimento do ciclo. */
    private String diaVencimento;

    /* Codigo que identifica o ciclo de faturamento da
     * 						conta. */
    private java.lang.String codigo;

    public Ciclo() {
    }

    public Ciclo(
           String diaVencimento,
           java.lang.String codigo) {
           this.diaVencimento = diaVencimento;
           this.codigo = codigo;
    }


    /**
     * Gets the diaVencimento value for this Ciclo.
     * 
     * @return diaVencimento   * Dia de vencimento do ciclo.
     */
    public String getDiaVencimento() {
        return diaVencimento;
    }


    /**
     * Sets the diaVencimento value for this Ciclo.
     * 
     * @param diaVencimento   * Dia de vencimento do ciclo.
     */
    public void setDiaVencimento(String diaVencimento) {
        this.diaVencimento = diaVencimento;
    }


    /**
     * Gets the codigo value for this Ciclo.
     * 
     * @return codigo   * Codigo que identifica o ciclo de faturamento da
     * 						conta.
     */
    public java.lang.String getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this Ciclo.
     * 
     * @param codigo   * Codigo que identifica o ciclo de faturamento da
     * 						conta.
     */
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Ciclo)) return false;
        Ciclo other = (Ciclo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.diaVencimento==null && other.getDiaVencimento()==null) || 
             (this.diaVencimento!=null &&
              this.diaVencimento.equals(other.getDiaVencimento()))) &&
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo())));
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
        if (getDiaVencimento() != null) {
            _hashCode += getDiaVencimento().hashCode();
        }
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Ciclo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "Ciclo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diaVencimento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "diaVencimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "codigo"));
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
