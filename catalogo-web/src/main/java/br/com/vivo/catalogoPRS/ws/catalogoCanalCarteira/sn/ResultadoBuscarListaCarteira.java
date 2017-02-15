/**
 * ResultadoBuscarListaCarteira.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn;

public class ResultadoBuscarListaCarteira  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.ResultadoBuscarListaCarteiraListaCarteiraCarteira[] listaCarteira;

    public ResultadoBuscarListaCarteira() {
    }

    public ResultadoBuscarListaCarteira(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.ResultadoBuscarListaCarteiraListaCarteiraCarteira[] listaCarteira) {
           this.listaCarteira = listaCarteira;
    }


    /**
     * Gets the listaCarteira value for this ResultadoBuscarListaCarteira.
     * 
     * @return listaCarteira
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.ResultadoBuscarListaCarteiraListaCarteiraCarteira[] getListaCarteira() {
        return listaCarteira;
    }


    /**
     * Sets the listaCarteira value for this ResultadoBuscarListaCarteira.
     * 
     * @param listaCarteira
     */
    public void setListaCarteira(br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.ResultadoBuscarListaCarteiraListaCarteiraCarteira[] listaCarteira) {
        this.listaCarteira = listaCarteira;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaCarteira)) return false;
        ResultadoBuscarListaCarteira other = (ResultadoBuscarListaCarteira) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaCarteira==null && other.getListaCarteira()==null) || 
             (this.listaCarteira!=null &&
              java.util.Arrays.equals(this.listaCarteira, other.getListaCarteira())));
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
        if (getListaCarteira() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCarteira());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCarteira(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaCarteira.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCarteira", ">ResultadoBuscarListaCarteira"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCarteira");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCarteira", "ListaCarteira"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCarteira", ">>>ResultadoBuscarListaCarteira>ListaCarteira>Carteira"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCarteira", "Carteira"));
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
