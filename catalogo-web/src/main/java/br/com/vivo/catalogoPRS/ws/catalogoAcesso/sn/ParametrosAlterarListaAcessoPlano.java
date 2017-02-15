/**
 * ParametrosAlterarListaAcessoPlano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class ParametrosAlterarListaAcessoPlano  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] listaAcessoPlano;

    public ParametrosAlterarListaAcessoPlano() {
    }

    public ParametrosAlterarListaAcessoPlano(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] listaAcessoPlano) {
           this.listaAcessoPlano = listaAcessoPlano;
    }


    /**
     * Gets the listaAcessoPlano value for this ParametrosAlterarListaAcessoPlano.
     * 
     * @return listaAcessoPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] getListaAcessoPlano() {
        return listaAcessoPlano;
    }


    /**
     * Sets the listaAcessoPlano value for this ParametrosAlterarListaAcessoPlano.
     * 
     * @param listaAcessoPlano
     */
    public void setListaAcessoPlano(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] listaAcessoPlano) {
        this.listaAcessoPlano = listaAcessoPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarListaAcessoPlano)) return false;
        ParametrosAlterarListaAcessoPlano other = (ParametrosAlterarListaAcessoPlano) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaAcessoPlano==null && other.getListaAcessoPlano()==null) || 
             (this.listaAcessoPlano!=null &&
              java.util.Arrays.equals(this.listaAcessoPlano, other.getListaAcessoPlano())));
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
        if (getListaAcessoPlano() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAcessoPlano());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAcessoPlano(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosAlterarListaAcessoPlano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosAlterarListaAcessoPlano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAcessoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ListaAcessoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>>ParametrosAlterarListaAcessoPlano>ListaAcessoPlano>AcessoPlano"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "AcessoPlano"));
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
