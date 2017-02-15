/**
 * ParametrosExcluirListaModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosExcluirListaModelo  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosExcluirListaModeloListaModeloExclusaoModeloExclusao[] listaModeloExclusao;

    public ParametrosExcluirListaModelo() {
    }

    public ParametrosExcluirListaModelo(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosExcluirListaModeloListaModeloExclusaoModeloExclusao[] listaModeloExclusao) {
           this.listaModeloExclusao = listaModeloExclusao;
    }


    /**
     * Gets the listaModeloExclusao value for this ParametrosExcluirListaModelo.
     * 
     * @return listaModeloExclusao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosExcluirListaModeloListaModeloExclusaoModeloExclusao[] getListaModeloExclusao() {
        return listaModeloExclusao;
    }


    /**
     * Sets the listaModeloExclusao value for this ParametrosExcluirListaModelo.
     * 
     * @param listaModeloExclusao
     */
    public void setListaModeloExclusao(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosExcluirListaModeloListaModeloExclusaoModeloExclusao[] listaModeloExclusao) {
        this.listaModeloExclusao = listaModeloExclusao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosExcluirListaModelo)) return false;
        ParametrosExcluirListaModelo other = (ParametrosExcluirListaModelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaModeloExclusao==null && other.getListaModeloExclusao()==null) || 
             (this.listaModeloExclusao!=null &&
              java.util.Arrays.equals(this.listaModeloExclusao, other.getListaModeloExclusao())));
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
        if (getListaModeloExclusao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaModeloExclusao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaModeloExclusao(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosExcluirListaModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosExcluirListaModelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaModeloExclusao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaModeloExclusao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>ParametrosExcluirListaModelo>ListaModeloExclusao>ModeloExclusao"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ModeloExclusao"));
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
