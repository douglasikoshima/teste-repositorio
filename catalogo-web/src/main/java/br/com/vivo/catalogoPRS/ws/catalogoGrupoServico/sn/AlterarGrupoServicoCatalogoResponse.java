/**
 * AlterarGrupoServicoCatalogoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn;

public class AlterarGrupoServicoCatalogoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoAlterarGrupoServicoCatalogo resultadoAlterarGrupoServicoCatalogo;

    public AlterarGrupoServicoCatalogoResponse() {
    }

    public AlterarGrupoServicoCatalogoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoAlterarGrupoServicoCatalogo resultadoAlterarGrupoServicoCatalogo) {
           this.resultadoAlterarGrupoServicoCatalogo = resultadoAlterarGrupoServicoCatalogo;
    }


    /**
     * Gets the resultadoAlterarGrupoServicoCatalogo value for this AlterarGrupoServicoCatalogoResponse.
     * 
     * @return resultadoAlterarGrupoServicoCatalogo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoAlterarGrupoServicoCatalogo getResultadoAlterarGrupoServicoCatalogo() {
        return resultadoAlterarGrupoServicoCatalogo;
    }


    /**
     * Sets the resultadoAlterarGrupoServicoCatalogo value for this AlterarGrupoServicoCatalogoResponse.
     * 
     * @param resultadoAlterarGrupoServicoCatalogo
     */
    public void setResultadoAlterarGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoAlterarGrupoServicoCatalogo resultadoAlterarGrupoServicoCatalogo) {
        this.resultadoAlterarGrupoServicoCatalogo = resultadoAlterarGrupoServicoCatalogo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarGrupoServicoCatalogoResponse)) return false;
        AlterarGrupoServicoCatalogoResponse other = (AlterarGrupoServicoCatalogoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoAlterarGrupoServicoCatalogo==null && other.getResultadoAlterarGrupoServicoCatalogo()==null) || 
             (this.resultadoAlterarGrupoServicoCatalogo!=null &&
              this.resultadoAlterarGrupoServicoCatalogo.equals(other.getResultadoAlterarGrupoServicoCatalogo())));
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
        if (getResultadoAlterarGrupoServicoCatalogo() != null) {
            _hashCode += getResultadoAlterarGrupoServicoCatalogo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarGrupoServicoCatalogoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">alterarGrupoServicoCatalogoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoAlterarGrupoServicoCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "ResultadoAlterarGrupoServicoCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ResultadoAlterarGrupoServicoCatalogo"));
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
