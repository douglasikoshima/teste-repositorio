/**
 * BuscarListaPlanosSemOfertaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class BuscarListaPlanosSemOfertaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosBuscarListaPlanosSemOferta parametrosBuscarListaPlanosSemOferta;

    public BuscarListaPlanosSemOfertaRequest() {
    }

    public BuscarListaPlanosSemOfertaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosBuscarListaPlanosSemOferta parametrosBuscarListaPlanosSemOferta) {
           this.parametrosBuscarListaPlanosSemOferta = parametrosBuscarListaPlanosSemOferta;
    }


    /**
     * Gets the parametrosBuscarListaPlanosSemOferta value for this BuscarListaPlanosSemOfertaRequest.
     * 
     * @return parametrosBuscarListaPlanosSemOferta
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosBuscarListaPlanosSemOferta getParametrosBuscarListaPlanosSemOferta() {
        return parametrosBuscarListaPlanosSemOferta;
    }


    /**
     * Sets the parametrosBuscarListaPlanosSemOferta value for this BuscarListaPlanosSemOfertaRequest.
     * 
     * @param parametrosBuscarListaPlanosSemOferta
     */
    public void setParametrosBuscarListaPlanosSemOferta(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosBuscarListaPlanosSemOferta parametrosBuscarListaPlanosSemOferta) {
        this.parametrosBuscarListaPlanosSemOferta = parametrosBuscarListaPlanosSemOferta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaPlanosSemOfertaRequest)) return false;
        BuscarListaPlanosSemOfertaRequest other = (BuscarListaPlanosSemOfertaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaPlanosSemOferta==null && other.getParametrosBuscarListaPlanosSemOferta()==null) || 
             (this.parametrosBuscarListaPlanosSemOferta!=null &&
              this.parametrosBuscarListaPlanosSemOferta.equals(other.getParametrosBuscarListaPlanosSemOferta())));
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
        if (getParametrosBuscarListaPlanosSemOferta() != null) {
            _hashCode += getParametrosBuscarListaPlanosSemOferta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaPlanosSemOfertaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">buscarListaPlanosSemOfertaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaPlanosSemOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ParametrosBuscarListaPlanosSemOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">ParametrosBuscarListaPlanosSemOferta"));
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
