/**
 * ExcluirListaGrupoServicoCatalogoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn;

public class ExcluirListaGrupoServicoCatalogoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosExcluirListaGrupoServicoCatalogo parametrosExcluirListaGrupoServicoCatalogo;

    public ExcluirListaGrupoServicoCatalogoRequest() {
    }

    public ExcluirListaGrupoServicoCatalogoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosExcluirListaGrupoServicoCatalogo parametrosExcluirListaGrupoServicoCatalogo) {
           this.parametrosExcluirListaGrupoServicoCatalogo = parametrosExcluirListaGrupoServicoCatalogo;
    }


    /**
     * Gets the parametrosExcluirListaGrupoServicoCatalogo value for this ExcluirListaGrupoServicoCatalogoRequest.
     * 
     * @return parametrosExcluirListaGrupoServicoCatalogo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosExcluirListaGrupoServicoCatalogo getParametrosExcluirListaGrupoServicoCatalogo() {
        return parametrosExcluirListaGrupoServicoCatalogo;
    }


    /**
     * Sets the parametrosExcluirListaGrupoServicoCatalogo value for this ExcluirListaGrupoServicoCatalogoRequest.
     * 
     * @param parametrosExcluirListaGrupoServicoCatalogo
     */
    public void setParametrosExcluirListaGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosExcluirListaGrupoServicoCatalogo parametrosExcluirListaGrupoServicoCatalogo) {
        this.parametrosExcluirListaGrupoServicoCatalogo = parametrosExcluirListaGrupoServicoCatalogo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExcluirListaGrupoServicoCatalogoRequest)) return false;
        ExcluirListaGrupoServicoCatalogoRequest other = (ExcluirListaGrupoServicoCatalogoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosExcluirListaGrupoServicoCatalogo==null && other.getParametrosExcluirListaGrupoServicoCatalogo()==null) || 
             (this.parametrosExcluirListaGrupoServicoCatalogo!=null &&
              this.parametrosExcluirListaGrupoServicoCatalogo.equals(other.getParametrosExcluirListaGrupoServicoCatalogo())));
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
        if (getParametrosExcluirListaGrupoServicoCatalogo() != null) {
            _hashCode += getParametrosExcluirListaGrupoServicoCatalogo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExcluirListaGrupoServicoCatalogoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">excluirListaGrupoServicoCatalogoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosExcluirListaGrupoServicoCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "ParametrosExcluirListaGrupoServicoCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ParametrosExcluirListaGrupoServicoCatalogo"));
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
