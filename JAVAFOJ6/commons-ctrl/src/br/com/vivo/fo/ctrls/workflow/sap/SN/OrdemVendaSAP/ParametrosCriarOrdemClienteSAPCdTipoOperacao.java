/**
 * ParametrosCriarOrdemClienteSAPCdTipoOperacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP;

public class ParametrosCriarOrdemClienteSAPCdTipoOperacao implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ParametrosCriarOrdemClienteSAPCdTipoOperacao(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _PTOPJ = "PTOPJ";
    public static final java.lang.String _SGDR = "SGDR";
    public static final java.lang.String _FLEX = "FLEX";
    public static final java.lang.String _ZCSB = "ZCSB";
    public static final java.lang.String _RESGATEPJ = "RESGATEPJ";
    public static final ParametrosCriarOrdemClienteSAPCdTipoOperacao PTOPJ = new ParametrosCriarOrdemClienteSAPCdTipoOperacao(_PTOPJ);
    public static final ParametrosCriarOrdemClienteSAPCdTipoOperacao SGDR = new ParametrosCriarOrdemClienteSAPCdTipoOperacao(_SGDR);
    public static final ParametrosCriarOrdemClienteSAPCdTipoOperacao FLEX = new ParametrosCriarOrdemClienteSAPCdTipoOperacao(_FLEX);
    public static final ParametrosCriarOrdemClienteSAPCdTipoOperacao ZCSB = new ParametrosCriarOrdemClienteSAPCdTipoOperacao(_ZCSB);
    public static final ParametrosCriarOrdemClienteSAPCdTipoOperacao RESGATEPJ = new ParametrosCriarOrdemClienteSAPCdTipoOperacao(_RESGATEPJ);
    public java.lang.String getValue() { return _value_;}
    public static ParametrosCriarOrdemClienteSAPCdTipoOperacao fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ParametrosCriarOrdemClienteSAPCdTipoOperacao enumeration = (ParametrosCriarOrdemClienteSAPCdTipoOperacao)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ParametrosCriarOrdemClienteSAPCdTipoOperacao fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosCriarOrdemClienteSAPCdTipoOperacao.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", ">ParametrosCriarOrdemClienteSAP>cdTipoOperacao"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
