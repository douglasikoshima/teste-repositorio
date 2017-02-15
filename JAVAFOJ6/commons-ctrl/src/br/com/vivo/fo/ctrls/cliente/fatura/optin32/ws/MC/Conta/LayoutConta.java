/**
 * LayoutConta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package  br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta;


/**
 * Mantem informacoes dos layout de contas validos.
 * 				Exemplos: resumida, detalhada, semi-detalhada e nao
 * 				classificado.
 */
public class LayoutConta  implements java.io.Serializable {
    /* Codigo que identifica o layout da conta, se é
     * 						resumida, detalhada, semi-detalhada ou não
     * 						classificada. */
    private br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.LayoutContaCodigo codigo;

    /* Sigla que identifica o layout da conta.
     * 						Exemplos: RES, SDE, DET ou FI. */
    private java.lang.String sigla;

    /* Descricao do layout da conta, como: resumida,
     * 						detalhada, etc. */
    private java.lang.String descricao;

    public LayoutConta() {
    }

    public LayoutConta(
           br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.LayoutContaCodigo codigo,
           java.lang.String sigla,
           java.lang.String descricao) {
           this.codigo = codigo;
           this.sigla = sigla;
           this.descricao = descricao;
    }


    /**
     * Gets the codigo value for this LayoutConta.
     * 
     * @return codigo   * Codigo que identifica o layout da conta, se é
     * 						resumida, detalhada, semi-detalhada ou não
     * 						classificada.
     */
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.LayoutContaCodigo getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this LayoutConta.
     * 
     * @param codigo   * Codigo que identifica o layout da conta, se é
     * 						resumida, detalhada, semi-detalhada ou não
     * 						classificada.
     */
    public void setCodigo(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.LayoutContaCodigo codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the sigla value for this LayoutConta.
     * 
     * @return sigla   * Sigla que identifica o layout da conta.
     * 						Exemplos: RES, SDE, DET ou FI.
     */
    public java.lang.String getSigla() {
        return sigla;
    }


    /**
     * Sets the sigla value for this LayoutConta.
     * 
     * @param sigla   * Sigla que identifica o layout da conta.
     * 						Exemplos: RES, SDE, DET ou FI.
     */
    public void setSigla(java.lang.String sigla) {
        this.sigla = sigla;
    }


    /**
     * Gets the descricao value for this LayoutConta.
     * 
     * @return descricao   * Descricao do layout da conta, como: resumida,
     * 						detalhada, etc.
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this LayoutConta.
     * 
     * @param descricao   * Descricao do layout da conta, como: resumida,
     * 						detalhada, etc.
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LayoutConta)) return false;
        LayoutConta other = (LayoutConta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.sigla==null && other.getSigla()==null) || 
             (this.sigla!=null &&
              this.sigla.equals(other.getSigla()))) &&
            ((this.descricao==null && other.getDescricao()==null) || 
             (this.descricao!=null &&
              this.descricao.equals(other.getDescricao())));
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
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getSigla() != null) {
            _hashCode += getSigla().hashCode();
        }
        if (getDescricao() != null) {
            _hashCode += getDescricao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LayoutConta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "LayoutConta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", ">LayoutConta>codigo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sigla");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "sigla"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "descricao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
