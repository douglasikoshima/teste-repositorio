/**
 * ParametrosAlterarTecnologiaTipoFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ParametrosAlterarTecnologiaTipoFrequencia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao parametrosTecnologiaTipoFrequenciaAlteracao;

    public ParametrosAlterarTecnologiaTipoFrequencia() {
    }

    public ParametrosAlterarTecnologiaTipoFrequencia(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao parametrosTecnologiaTipoFrequenciaAlteracao) {
           this.parametrosTecnologiaTipoFrequenciaAlteracao = parametrosTecnologiaTipoFrequenciaAlteracao;
    }


    /**
     * Gets the parametrosTecnologiaTipoFrequenciaAlteracao value for this ParametrosAlterarTecnologiaTipoFrequencia.
     * 
     * @return parametrosTecnologiaTipoFrequenciaAlteracao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao getParametrosTecnologiaTipoFrequenciaAlteracao() {
        return parametrosTecnologiaTipoFrequenciaAlteracao;
    }


    /**
     * Sets the parametrosTecnologiaTipoFrequenciaAlteracao value for this ParametrosAlterarTecnologiaTipoFrequencia.
     * 
     * @param parametrosTecnologiaTipoFrequenciaAlteracao
     */
    public void setParametrosTecnologiaTipoFrequenciaAlteracao(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao parametrosTecnologiaTipoFrequenciaAlteracao) {
        this.parametrosTecnologiaTipoFrequenciaAlteracao = parametrosTecnologiaTipoFrequenciaAlteracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarTecnologiaTipoFrequencia)) return false;
        ParametrosAlterarTecnologiaTipoFrequencia other = (ParametrosAlterarTecnologiaTipoFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosTecnologiaTipoFrequenciaAlteracao==null && other.getParametrosTecnologiaTipoFrequenciaAlteracao()==null) || 
             (this.parametrosTecnologiaTipoFrequenciaAlteracao!=null &&
              this.parametrosTecnologiaTipoFrequenciaAlteracao.equals(other.getParametrosTecnologiaTipoFrequenciaAlteracao())));
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
        if (getParametrosTecnologiaTipoFrequenciaAlteracao() != null) {
            _hashCode += getParametrosTecnologiaTipoFrequenciaAlteracao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarTecnologiaTipoFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ParametrosAlterarTecnologiaTipoFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosTecnologiaTipoFrequenciaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ParametrosTecnologiaTipoFrequenciaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">>ParametrosAlterarTecnologiaTipoFrequencia>ParametrosTecnologiaTipoFrequenciaAlteracao"));
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
