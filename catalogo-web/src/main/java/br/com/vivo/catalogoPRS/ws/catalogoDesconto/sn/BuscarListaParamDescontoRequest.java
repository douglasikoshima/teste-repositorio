/**
 * BuscarListaParamDescontoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public class BuscarListaParamDescontoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosBuscarListaParamDesconto parametrosBuscarListaParamDesconto;

    public BuscarListaParamDescontoRequest() {
    }

    public BuscarListaParamDescontoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosBuscarListaParamDesconto parametrosBuscarListaParamDesconto) {
           this.parametrosBuscarListaParamDesconto = parametrosBuscarListaParamDesconto;
    }


    /**
     * Gets the parametrosBuscarListaParamDesconto value for this BuscarListaParamDescontoRequest.
     * 
     * @return parametrosBuscarListaParamDesconto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosBuscarListaParamDesconto getParametrosBuscarListaParamDesconto() {
        return parametrosBuscarListaParamDesconto;
    }


    /**
     * Sets the parametrosBuscarListaParamDesconto value for this BuscarListaParamDescontoRequest.
     * 
     * @param parametrosBuscarListaParamDesconto
     */
    public void setParametrosBuscarListaParamDesconto(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosBuscarListaParamDesconto parametrosBuscarListaParamDesconto) {
        this.parametrosBuscarListaParamDesconto = parametrosBuscarListaParamDesconto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaParamDescontoRequest)) return false;
        BuscarListaParamDescontoRequest other = (BuscarListaParamDescontoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaParamDesconto==null && other.getParametrosBuscarListaParamDesconto()==null) || 
             (this.parametrosBuscarListaParamDesconto!=null &&
              this.parametrosBuscarListaParamDesconto.equals(other.getParametrosBuscarListaParamDesconto())));
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
        if (getParametrosBuscarListaParamDesconto() != null) {
            _hashCode += getParametrosBuscarListaParamDesconto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaParamDescontoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">buscarListaParamDescontoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaParamDesconto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "ParametrosBuscarListaParamDesconto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">ParametrosBuscarListaParamDesconto"));
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
