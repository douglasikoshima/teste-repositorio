/**
 * ParametrosExcluirListaValorCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class ParametrosExcluirListaValorCaracteristica  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosExcluirListaValorCaracteristicaListaValorCaracteristicaExclusaoValorCaracteristicaExclusao[] listaValorCaracteristicaExclusao;

    public ParametrosExcluirListaValorCaracteristica() {
    }

    public ParametrosExcluirListaValorCaracteristica(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosExcluirListaValorCaracteristicaListaValorCaracteristicaExclusaoValorCaracteristicaExclusao[] listaValorCaracteristicaExclusao) {
           this.listaValorCaracteristicaExclusao = listaValorCaracteristicaExclusao;
    }


    /**
     * Gets the listaValorCaracteristicaExclusao value for this ParametrosExcluirListaValorCaracteristica.
     * 
     * @return listaValorCaracteristicaExclusao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosExcluirListaValorCaracteristicaListaValorCaracteristicaExclusaoValorCaracteristicaExclusao[] getListaValorCaracteristicaExclusao() {
        return listaValorCaracteristicaExclusao;
    }


    /**
     * Sets the listaValorCaracteristicaExclusao value for this ParametrosExcluirListaValorCaracteristica.
     * 
     * @param listaValorCaracteristicaExclusao
     */
    public void setListaValorCaracteristicaExclusao(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosExcluirListaValorCaracteristicaListaValorCaracteristicaExclusaoValorCaracteristicaExclusao[] listaValorCaracteristicaExclusao) {
        this.listaValorCaracteristicaExclusao = listaValorCaracteristicaExclusao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosExcluirListaValorCaracteristica)) return false;
        ParametrosExcluirListaValorCaracteristica other = (ParametrosExcluirListaValorCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaValorCaracteristicaExclusao==null && other.getListaValorCaracteristicaExclusao()==null) || 
             (this.listaValorCaracteristicaExclusao!=null &&
              java.util.Arrays.equals(this.listaValorCaracteristicaExclusao, other.getListaValorCaracteristicaExclusao())));
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
        if (getListaValorCaracteristicaExclusao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaValorCaracteristicaExclusao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaValorCaracteristicaExclusao(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosExcluirListaValorCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosExcluirListaValorCaracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaValorCaracteristicaExclusao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ListaValorCaracteristicaExclusao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">>>ParametrosExcluirListaValorCaracteristica>ListaValorCaracteristicaExclusao>ValorCaracteristicaExclusao"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ValorCaracteristicaExclusao"));
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
