/**
 * ResultadoCriarOfertaServicoListaCriarOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoCriarOfertaServicoListaCriarOfertaServico  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoCriarOfertaServicoListaCriarOfertaServicoOfertaServico ofertaServico;

    public ResultadoCriarOfertaServicoListaCriarOfertaServico() {
    }

    public ResultadoCriarOfertaServicoListaCriarOfertaServico(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoCriarOfertaServicoListaCriarOfertaServicoOfertaServico ofertaServico) {
           this.ofertaServico = ofertaServico;
    }


    /**
     * Gets the ofertaServico value for this ResultadoCriarOfertaServicoListaCriarOfertaServico.
     * 
     * @return ofertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoCriarOfertaServicoListaCriarOfertaServicoOfertaServico getOfertaServico() {
        return ofertaServico;
    }


    /**
     * Sets the ofertaServico value for this ResultadoCriarOfertaServicoListaCriarOfertaServico.
     * 
     * @param ofertaServico
     */
    public void setOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoCriarOfertaServicoListaCriarOfertaServicoOfertaServico ofertaServico) {
        this.ofertaServico = ofertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoCriarOfertaServicoListaCriarOfertaServico)) return false;
        ResultadoCriarOfertaServicoListaCriarOfertaServico other = (ResultadoCriarOfertaServicoListaCriarOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ofertaServico==null && other.getOfertaServico()==null) || 
             (this.ofertaServico!=null &&
              this.ofertaServico.equals(other.getOfertaServico())));
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
        if (getOfertaServico() != null) {
            _hashCode += getOfertaServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoCriarOfertaServicoListaCriarOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>ResultadoCriarOfertaServico>ListaCriarOfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ofertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "OfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>>ResultadoCriarOfertaServico>ListaCriarOfertaServico>OfertaServico"));
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
