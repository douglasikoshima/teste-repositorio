/**
 * ParametrosExcluirListaCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class ParametrosExcluirListaCaracteristica  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosExcluirListaCaracteristicaListaCaracteristicaExclusaoCaracteristicaExclusao[] listaCaracteristicaExclusao;

    public ParametrosExcluirListaCaracteristica() {
    }

    public ParametrosExcluirListaCaracteristica(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosExcluirListaCaracteristicaListaCaracteristicaExclusaoCaracteristicaExclusao[] listaCaracteristicaExclusao) {
           this.listaCaracteristicaExclusao = listaCaracteristicaExclusao;
    }


    /**
     * Gets the listaCaracteristicaExclusao value for this ParametrosExcluirListaCaracteristica.
     * 
     * @return listaCaracteristicaExclusao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosExcluirListaCaracteristicaListaCaracteristicaExclusaoCaracteristicaExclusao[] getListaCaracteristicaExclusao() {
        return listaCaracteristicaExclusao;
    }


    /**
     * Sets the listaCaracteristicaExclusao value for this ParametrosExcluirListaCaracteristica.
     * 
     * @param listaCaracteristicaExclusao
     */
    public void setListaCaracteristicaExclusao(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosExcluirListaCaracteristicaListaCaracteristicaExclusaoCaracteristicaExclusao[] listaCaracteristicaExclusao) {
        this.listaCaracteristicaExclusao = listaCaracteristicaExclusao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosExcluirListaCaracteristica)) return false;
        ParametrosExcluirListaCaracteristica other = (ParametrosExcluirListaCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaCaracteristicaExclusao==null && other.getListaCaracteristicaExclusao()==null) || 
             (this.listaCaracteristicaExclusao!=null &&
              java.util.Arrays.equals(this.listaCaracteristicaExclusao, other.getListaCaracteristicaExclusao())));
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
        if (getListaCaracteristicaExclusao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCaracteristicaExclusao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCaracteristicaExclusao(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosExcluirListaCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosExcluirListaCaracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCaracteristicaExclusao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ListaCaracteristicaExclusao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">>>ParametrosExcluirListaCaracteristica>ListaCaracteristicaExclusao>CaracteristicaExclusao"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "CaracteristicaExclusao"));
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
