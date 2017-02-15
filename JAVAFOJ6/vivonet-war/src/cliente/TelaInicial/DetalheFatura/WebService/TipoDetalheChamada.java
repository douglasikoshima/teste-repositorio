/**
 * TipoDetalheChamada.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cliente.TelaInicial.DetalheFatura.WebService;


/**
 * Indica se a chamada eh originada, internacional,
 * 				nacional, a cobrar etc.
 */
public class TipoDetalheChamada  implements java.io.Serializable {
    /* Detalhe da ligacao
     * 						(Dominio: 0 - Todas 
     * 								  1 - Originada 
     * 								  2 - Local 
     * 								  3 - Longa Distancia Nacional
     * 								  4 - Longa Distancia Internacional 
     * 								  5 - A cobrar) */
    private java.math.BigInteger codigo;

    /* Descricao do tipo de detalhe da chamada */
    private java.lang.String descricao;

    public TipoDetalheChamada() {
    }

    public TipoDetalheChamada(
           java.math.BigInteger codigo,
           java.lang.String descricao) {
           this.codigo = codigo;
           this.descricao = descricao;
    }


    /**
     * Gets the codigo value for this TipoDetalheChamada.
     * 
     * @return codigo   * Detalhe da ligacao
     * 						(Dominio: 0 - Todas 
     * 								  1 - Originada 
     * 								  2 - Local 
     * 								  3 - Longa Distancia Nacional
     * 								  4 - Longa Distancia Internacional 
     * 								  5 - A cobrar)
     */
    public java.math.BigInteger getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this TipoDetalheChamada.
     * 
     * @param codigo   * Detalhe da ligacao
     * 						(Dominio: 0 - Todas 
     * 								  1 - Originada 
     * 								  2 - Local 
     * 								  3 - Longa Distancia Nacional
     * 								  4 - Longa Distancia Internacional 
     * 								  5 - A cobrar)
     */
    public void setCodigo(java.math.BigInteger codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the descricao value for this TipoDetalheChamada.
     * 
     * @return descricao   * Descricao do tipo de detalhe da chamada
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this TipoDetalheChamada.
     * 
     * @param descricao   * Descricao do tipo de detalhe da chamada
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoDetalheChamada)) return false;
        TipoDetalheChamada other = (TipoDetalheChamada) obj;
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
        if (getDescricao() != null) {
            _hashCode += getDescricao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoDetalheChamada.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "TipoDetalheChamada"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "descricao"));
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
