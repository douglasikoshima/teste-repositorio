/**
 * Ciclo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package  br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta;


/**
 * Mantem informacoes dos ciclos de billing por tipo de
 * 				pessoa e dia de vencimento.
 */
public class Ciclo  implements java.io.Serializable {
    /* Dia de vencimento do ciclo. */
    private java.math.BigDecimal diaVencimento;

    /* Codigo que identifica o ciclo de faturamento da
     * 						conta. */
    private java.lang.String codigo;

    /* Referencia a entidade TipoPessoa */
    private br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Pessoa.TipoPessoa[] tiposPessoa;

    public Ciclo() {
    }

    public Ciclo(
           java.math.BigDecimal diaVencimento,
           java.lang.String codigo,
           br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Pessoa.TipoPessoa[] tiposPessoa) {
           this.diaVencimento = diaVencimento;
           this.codigo = codigo;
           this.tiposPessoa = tiposPessoa;
    }


    /**
     * Gets the diaVencimento value for this Ciclo.
     * 
     * @return diaVencimento   * Dia de vencimento do ciclo.
     */
    public java.math.BigDecimal getDiaVencimento() {
        return diaVencimento;
    }


    /**
     * Sets the diaVencimento value for this Ciclo.
     * 
     * @param diaVencimento   * Dia de vencimento do ciclo.
     */
    public void setDiaVencimento(java.math.BigDecimal diaVencimento) {
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


    /**
     * Gets the tiposPessoa value for this Ciclo.
     * 
     * @return tiposPessoa   * Referencia a entidade TipoPessoa
     */
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Pessoa.TipoPessoa[] getTiposPessoa() {
        return tiposPessoa;
    }


    /**
     * Sets the tiposPessoa value for this Ciclo.
     * 
     * @param tiposPessoa   * Referencia a entidade TipoPessoa
     */
    public void setTiposPessoa(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Pessoa.TipoPessoa[] tiposPessoa) {
        this.tiposPessoa = tiposPessoa;
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
              this.codigo.equals(other.getCodigo()))) &&
            ((this.tiposPessoa==null && other.getTiposPessoa()==null) || 
             (this.tiposPessoa!=null &&
              java.util.Arrays.equals(this.tiposPessoa, other.getTiposPessoa())));
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
        if (getTiposPessoa() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTiposPessoa());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTiposPessoa(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tiposPessoa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "tiposPessoa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "TipoPessoa"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "tipoPessoa"));
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
