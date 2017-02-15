/**
 * ParamAlterarAtivacaoWiFi.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ParamAlterarAtivacaoWiFi  implements java.io.Serializable {
    private long[] listaIdPlano;

    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParamAlterarAtivacaoWiFiStatus status;

    public ParamAlterarAtivacaoWiFi() {
    }

    public ParamAlterarAtivacaoWiFi(
           long[] listaIdPlano,
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParamAlterarAtivacaoWiFiStatus status) {
           this.listaIdPlano = listaIdPlano;
           this.status = status;
    }


    /**
     * Gets the listaIdPlano value for this ParamAlterarAtivacaoWiFi.
     * 
     * @return listaIdPlano
     */
    public long[] getListaIdPlano() {
        return listaIdPlano;
    }


    /**
     * Sets the listaIdPlano value for this ParamAlterarAtivacaoWiFi.
     * 
     * @param listaIdPlano
     */
    public void setListaIdPlano(long[] listaIdPlano) {
        this.listaIdPlano = listaIdPlano;
    }


    /**
     * Gets the status value for this ParamAlterarAtivacaoWiFi.
     * 
     * @return status
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParamAlterarAtivacaoWiFiStatus getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ParamAlterarAtivacaoWiFi.
     * 
     * @param status
     */
    public void setStatus(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParamAlterarAtivacaoWiFiStatus status) {
        this.status = status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamAlterarAtivacaoWiFi)) return false;
        ParamAlterarAtivacaoWiFi other = (ParamAlterarAtivacaoWiFi) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaIdPlano==null && other.getListaIdPlano()==null) || 
             (this.listaIdPlano!=null &&
              java.util.Arrays.equals(this.listaIdPlano, other.getListaIdPlano()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus())));
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
        if (getListaIdPlano() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdPlano());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdPlano(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamAlterarAtivacaoWiFi.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ParamAlterarAtivacaoWiFi"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ListaIdPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idPlano"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>ParamAlterarAtivacaoWiFi>status"));
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
