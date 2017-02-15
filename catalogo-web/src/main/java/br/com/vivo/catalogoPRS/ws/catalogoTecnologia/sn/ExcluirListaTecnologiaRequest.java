/**
 * ExcluirListaTecnologiaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ExcluirListaTecnologiaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosExcluirListaTecnologia parametrosExcluirListaTecnologia;

    public ExcluirListaTecnologiaRequest() {
    }

    public ExcluirListaTecnologiaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosExcluirListaTecnologia parametrosExcluirListaTecnologia) {
           this.parametrosExcluirListaTecnologia = parametrosExcluirListaTecnologia;
    }


    /**
     * Gets the parametrosExcluirListaTecnologia value for this ExcluirListaTecnologiaRequest.
     * 
     * @return parametrosExcluirListaTecnologia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosExcluirListaTecnologia getParametrosExcluirListaTecnologia() {
        return parametrosExcluirListaTecnologia;
    }


    /**
     * Sets the parametrosExcluirListaTecnologia value for this ExcluirListaTecnologiaRequest.
     * 
     * @param parametrosExcluirListaTecnologia
     */
    public void setParametrosExcluirListaTecnologia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosExcluirListaTecnologia parametrosExcluirListaTecnologia) {
        this.parametrosExcluirListaTecnologia = parametrosExcluirListaTecnologia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExcluirListaTecnologiaRequest)) return false;
        ExcluirListaTecnologiaRequest other = (ExcluirListaTecnologiaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosExcluirListaTecnologia==null && other.getParametrosExcluirListaTecnologia()==null) || 
             (this.parametrosExcluirListaTecnologia!=null &&
              this.parametrosExcluirListaTecnologia.equals(other.getParametrosExcluirListaTecnologia())));
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
        if (getParametrosExcluirListaTecnologia() != null) {
            _hashCode += getParametrosExcluirListaTecnologia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExcluirListaTecnologiaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">excluirListaTecnologiaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosExcluirListaTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ParametrosExcluirListaTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ParametrosExcluirListaTecnologia"));
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
