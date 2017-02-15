/**
 * BuscarServicoPorGrupoServicoCatalogoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn;

public class BuscarServicoPorGrupoServicoCatalogoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoBuscarServicoPorGrupoServicoCatalogo resultadoBuscarServicoPorGrupoServicoCatalogo;

    public BuscarServicoPorGrupoServicoCatalogoResponse() {
    }

    public BuscarServicoPorGrupoServicoCatalogoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoBuscarServicoPorGrupoServicoCatalogo resultadoBuscarServicoPorGrupoServicoCatalogo) {
           this.resultadoBuscarServicoPorGrupoServicoCatalogo = resultadoBuscarServicoPorGrupoServicoCatalogo;
    }


    /**
     * Gets the resultadoBuscarServicoPorGrupoServicoCatalogo value for this BuscarServicoPorGrupoServicoCatalogoResponse.
     * 
     * @return resultadoBuscarServicoPorGrupoServicoCatalogo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoBuscarServicoPorGrupoServicoCatalogo getResultadoBuscarServicoPorGrupoServicoCatalogo() {
        return resultadoBuscarServicoPorGrupoServicoCatalogo;
    }


    /**
     * Sets the resultadoBuscarServicoPorGrupoServicoCatalogo value for this BuscarServicoPorGrupoServicoCatalogoResponse.
     * 
     * @param resultadoBuscarServicoPorGrupoServicoCatalogo
     */
    public void setResultadoBuscarServicoPorGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoBuscarServicoPorGrupoServicoCatalogo resultadoBuscarServicoPorGrupoServicoCatalogo) {
        this.resultadoBuscarServicoPorGrupoServicoCatalogo = resultadoBuscarServicoPorGrupoServicoCatalogo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarServicoPorGrupoServicoCatalogoResponse)) return false;
        BuscarServicoPorGrupoServicoCatalogoResponse other = (BuscarServicoPorGrupoServicoCatalogoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarServicoPorGrupoServicoCatalogo==null && other.getResultadoBuscarServicoPorGrupoServicoCatalogo()==null) || 
             (this.resultadoBuscarServicoPorGrupoServicoCatalogo!=null &&
              this.resultadoBuscarServicoPorGrupoServicoCatalogo.equals(other.getResultadoBuscarServicoPorGrupoServicoCatalogo())));
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
        if (getResultadoBuscarServicoPorGrupoServicoCatalogo() != null) {
            _hashCode += getResultadoBuscarServicoPorGrupoServicoCatalogo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarServicoPorGrupoServicoCatalogoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">buscarServicoPorGrupoServicoCatalogoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarServicoPorGrupoServicoCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "ResultadoBuscarServicoPorGrupoServicoCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ResultadoBuscarServicoPorGrupoServicoCatalogo"));
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
