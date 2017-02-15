/**
 * CriarGrupoServicoCatalogoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn;

public class CriarGrupoServicoCatalogoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametroscriarGrupoServicoCatalogo parametroscriarGrupoServicoCatalogo;

    public CriarGrupoServicoCatalogoRequest() {
    }

    public CriarGrupoServicoCatalogoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametroscriarGrupoServicoCatalogo parametroscriarGrupoServicoCatalogo) {
           this.parametroscriarGrupoServicoCatalogo = parametroscriarGrupoServicoCatalogo;
    }


    /**
     * Gets the parametroscriarGrupoServicoCatalogo value for this CriarGrupoServicoCatalogoRequest.
     * 
     * @return parametroscriarGrupoServicoCatalogo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametroscriarGrupoServicoCatalogo getParametroscriarGrupoServicoCatalogo() {
        return parametroscriarGrupoServicoCatalogo;
    }


    /**
     * Sets the parametroscriarGrupoServicoCatalogo value for this CriarGrupoServicoCatalogoRequest.
     * 
     * @param parametroscriarGrupoServicoCatalogo
     */
    public void setParametroscriarGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametroscriarGrupoServicoCatalogo parametroscriarGrupoServicoCatalogo) {
        this.parametroscriarGrupoServicoCatalogo = parametroscriarGrupoServicoCatalogo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CriarGrupoServicoCatalogoRequest)) return false;
        CriarGrupoServicoCatalogoRequest other = (CriarGrupoServicoCatalogoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametroscriarGrupoServicoCatalogo==null && other.getParametroscriarGrupoServicoCatalogo()==null) || 
             (this.parametroscriarGrupoServicoCatalogo!=null &&
              this.parametroscriarGrupoServicoCatalogo.equals(other.getParametroscriarGrupoServicoCatalogo())));
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
        if (getParametroscriarGrupoServicoCatalogo() != null) {
            _hashCode += getParametroscriarGrupoServicoCatalogo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CriarGrupoServicoCatalogoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">criarGrupoServicoCatalogoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametroscriarGrupoServicoCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "ParametroscriarGrupoServicoCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ParametroscriarGrupoServicoCatalogo"));
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
