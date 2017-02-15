/**
 * CriarGrupoServicoCatalogoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn;

public class CriarGrupoServicoCatalogoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoCriarGrupoServico resultadoCriarGrupoServico;

    public CriarGrupoServicoCatalogoResponse() {
    }

    public CriarGrupoServicoCatalogoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoCriarGrupoServico resultadoCriarGrupoServico) {
           this.resultadoCriarGrupoServico = resultadoCriarGrupoServico;
    }


    /**
     * Gets the resultadoCriarGrupoServico value for this CriarGrupoServicoCatalogoResponse.
     * 
     * @return resultadoCriarGrupoServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoCriarGrupoServico getResultadoCriarGrupoServico() {
        return resultadoCriarGrupoServico;
    }


    /**
     * Sets the resultadoCriarGrupoServico value for this CriarGrupoServicoCatalogoResponse.
     * 
     * @param resultadoCriarGrupoServico
     */
    public void setResultadoCriarGrupoServico(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoCriarGrupoServico resultadoCriarGrupoServico) {
        this.resultadoCriarGrupoServico = resultadoCriarGrupoServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CriarGrupoServicoCatalogoResponse)) return false;
        CriarGrupoServicoCatalogoResponse other = (CriarGrupoServicoCatalogoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoCriarGrupoServico==null && other.getResultadoCriarGrupoServico()==null) || 
             (this.resultadoCriarGrupoServico!=null &&
              this.resultadoCriarGrupoServico.equals(other.getResultadoCriarGrupoServico())));
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
        if (getResultadoCriarGrupoServico() != null) {
            _hashCode += getResultadoCriarGrupoServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CriarGrupoServicoCatalogoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">criarGrupoServicoCatalogoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoCriarGrupoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "ResultadoCriarGrupoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ResultadoCriarGrupoServico"));
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
