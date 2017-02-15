/**
 * CabecalhoVivoNomeAplicacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoRegional.mc.geral;

public class CabecalhoVivoNomeAplicacao implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected CabecalhoVivoNomeAplicacao(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Vivo360 = "Vivo360";
    public static final java.lang.String _VivoSolicitacao = "VivoSolicitacao";
    public static final java.lang.String _VivoSCA = "VivoSCA";
    public static final java.lang.String _VivoSigan = "VivoSigan";
    public static final java.lang.String _VivoSPN = "VivoSPN";
    public static final java.lang.String _VivoAtlys = "VivoAtlys";
    public static final java.lang.String _VivoNGIN = "VivoNGIN";
    public static final java.lang.String _VivoSAP = "VivoSAP";
    public static final java.lang.String _VivoVivonet = "VivoVivonet";
    public static final java.lang.String _VivoPP = "VivoPP";
    public static final java.lang.String _VivoCatalogo = "VivoCatalogo";
    public static final java.lang.String _VivoGSIM = "VivoGSIM";
    public static final java.lang.String _VivoSCC = "VivoSCC";
    public static final java.lang.String _VivoSGP = "VivoSGP";
    public static final java.lang.String _VivoURA = "VivoURA";
    public static final java.lang.String _VivoGRC = "VivoGRC";
    public static final java.lang.String _VivoRA = "VivoRA";
    public static final java.lang.String _VivoMediacao = "VivoMediacao";
    public static final java.lang.String _VivoAGV = "VivoAGV";
    public static final java.lang.String _VivoPortalFornecedores = "VivoPortalFornecedores";
    public static final java.lang.String _SistemaNaoMapeado = "SistemaNaoMapeado";
    public static final CabecalhoVivoNomeAplicacao Vivo360 = new CabecalhoVivoNomeAplicacao(_Vivo360);
    public static final CabecalhoVivoNomeAplicacao VivoSolicitacao = new CabecalhoVivoNomeAplicacao(_VivoSolicitacao);
    public static final CabecalhoVivoNomeAplicacao VivoSCA = new CabecalhoVivoNomeAplicacao(_VivoSCA);
    public static final CabecalhoVivoNomeAplicacao VivoSigan = new CabecalhoVivoNomeAplicacao(_VivoSigan);
    public static final CabecalhoVivoNomeAplicacao VivoSPN = new CabecalhoVivoNomeAplicacao(_VivoSPN);
    public static final CabecalhoVivoNomeAplicacao VivoAtlys = new CabecalhoVivoNomeAplicacao(_VivoAtlys);
    public static final CabecalhoVivoNomeAplicacao VivoNGIN = new CabecalhoVivoNomeAplicacao(_VivoNGIN);
    public static final CabecalhoVivoNomeAplicacao VivoSAP = new CabecalhoVivoNomeAplicacao(_VivoSAP);
    public static final CabecalhoVivoNomeAplicacao VivoVivonet = new CabecalhoVivoNomeAplicacao(_VivoVivonet);
    public static final CabecalhoVivoNomeAplicacao VivoPP = new CabecalhoVivoNomeAplicacao(_VivoPP);
    public static final CabecalhoVivoNomeAplicacao VivoCatalogo = new CabecalhoVivoNomeAplicacao(_VivoCatalogo);
    public static final CabecalhoVivoNomeAplicacao VivoGSIM = new CabecalhoVivoNomeAplicacao(_VivoGSIM);
    public static final CabecalhoVivoNomeAplicacao VivoSCC = new CabecalhoVivoNomeAplicacao(_VivoSCC);
    public static final CabecalhoVivoNomeAplicacao VivoSGP = new CabecalhoVivoNomeAplicacao(_VivoSGP);
    public static final CabecalhoVivoNomeAplicacao VivoURA = new CabecalhoVivoNomeAplicacao(_VivoURA);
    public static final CabecalhoVivoNomeAplicacao VivoGRC = new CabecalhoVivoNomeAplicacao(_VivoGRC);
    public static final CabecalhoVivoNomeAplicacao VivoRA = new CabecalhoVivoNomeAplicacao(_VivoRA);
    public static final CabecalhoVivoNomeAplicacao VivoMediacao = new CabecalhoVivoNomeAplicacao(_VivoMediacao);
    public static final CabecalhoVivoNomeAplicacao VivoAGV = new CabecalhoVivoNomeAplicacao(_VivoAGV);
    public static final CabecalhoVivoNomeAplicacao VivoPortalFornecedores = new CabecalhoVivoNomeAplicacao(_VivoPortalFornecedores);
    public static final CabecalhoVivoNomeAplicacao SistemaNaoMapeado = new CabecalhoVivoNomeAplicacao(_SistemaNaoMapeado);
    public java.lang.String getValue() { return _value_;}
    public static CabecalhoVivoNomeAplicacao fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        CabecalhoVivoNomeAplicacao enumeration = (CabecalhoVivoNomeAplicacao)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static CabecalhoVivoNomeAplicacao fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(CabecalhoVivoNomeAplicacao.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>nomeAplicacao"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
