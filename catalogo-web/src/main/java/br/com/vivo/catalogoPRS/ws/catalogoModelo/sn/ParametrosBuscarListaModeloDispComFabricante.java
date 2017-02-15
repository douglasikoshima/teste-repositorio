/**
 * ParametrosBuscarListaModeloDispComFabricante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosBuscarListaModeloDispComFabricante  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn parametrosModelosDisponiveisComFabricanteIn;

    public ParametrosBuscarListaModeloDispComFabricante() {
    }

    public ParametrosBuscarListaModeloDispComFabricante(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn parametrosModelosDisponiveisComFabricanteIn) {
           this.parametrosModelosDisponiveisComFabricanteIn = parametrosModelosDisponiveisComFabricanteIn;
    }


    /**
     * Gets the parametrosModelosDisponiveisComFabricanteIn value for this ParametrosBuscarListaModeloDispComFabricante.
     * 
     * @return parametrosModelosDisponiveisComFabricanteIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn getParametrosModelosDisponiveisComFabricanteIn() {
        return parametrosModelosDisponiveisComFabricanteIn;
    }


    /**
     * Sets the parametrosModelosDisponiveisComFabricanteIn value for this ParametrosBuscarListaModeloDispComFabricante.
     * 
     * @param parametrosModelosDisponiveisComFabricanteIn
     */
    public void setParametrosModelosDisponiveisComFabricanteIn(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn parametrosModelosDisponiveisComFabricanteIn) {
        this.parametrosModelosDisponiveisComFabricanteIn = parametrosModelosDisponiveisComFabricanteIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaModeloDispComFabricante)) return false;
        ParametrosBuscarListaModeloDispComFabricante other = (ParametrosBuscarListaModeloDispComFabricante) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosModelosDisponiveisComFabricanteIn==null && other.getParametrosModelosDisponiveisComFabricanteIn()==null) || 
             (this.parametrosModelosDisponiveisComFabricanteIn!=null &&
              this.parametrosModelosDisponiveisComFabricanteIn.equals(other.getParametrosModelosDisponiveisComFabricanteIn())));
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
        if (getParametrosModelosDisponiveisComFabricanteIn() != null) {
            _hashCode += getParametrosModelosDisponiveisComFabricanteIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaModeloDispComFabricante.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosBuscarListaModeloDispComFabricante"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosModelosDisponiveisComFabricanteIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosModelosDisponiveisComFabricanteIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosBuscarListaModeloDispComFabricante>ParametrosModelosDisponiveisComFabricanteIn"));
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
