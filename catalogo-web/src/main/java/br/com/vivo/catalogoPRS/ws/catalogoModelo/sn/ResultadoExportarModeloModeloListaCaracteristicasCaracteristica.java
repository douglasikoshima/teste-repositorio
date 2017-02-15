/**
 * ResultadoExportarModeloModeloListaCaracteristicasCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoExportarModeloModeloListaCaracteristicasCaracteristica  implements java.io.Serializable {
    private java.lang.String caracteristica;

    private java.lang.String inSimulador;

    private java.lang.String inDisponivel;

    private java.lang.String valor;

    public ResultadoExportarModeloModeloListaCaracteristicasCaracteristica() {
    }

    public ResultadoExportarModeloModeloListaCaracteristicasCaracteristica(
           java.lang.String caracteristica,
           java.lang.String inSimulador,
           java.lang.String inDisponivel,
           java.lang.String valor) {
           this.caracteristica = caracteristica;
           this.inSimulador = inSimulador;
           this.inDisponivel = inDisponivel;
           this.valor = valor;
    }


    /**
     * Gets the caracteristica value for this ResultadoExportarModeloModeloListaCaracteristicasCaracteristica.
     * 
     * @return caracteristica
     */
    public java.lang.String getCaracteristica() {
        return caracteristica;
    }


    /**
     * Sets the caracteristica value for this ResultadoExportarModeloModeloListaCaracteristicasCaracteristica.
     * 
     * @param caracteristica
     */
    public void setCaracteristica(java.lang.String caracteristica) {
        this.caracteristica = caracteristica;
    }


    /**
     * Gets the inSimulador value for this ResultadoExportarModeloModeloListaCaracteristicasCaracteristica.
     * 
     * @return inSimulador
     */
    public java.lang.String getInSimulador() {
        return inSimulador;
    }


    /**
     * Sets the inSimulador value for this ResultadoExportarModeloModeloListaCaracteristicasCaracteristica.
     * 
     * @param inSimulador
     */
    public void setInSimulador(java.lang.String inSimulador) {
        this.inSimulador = inSimulador;
    }


    /**
     * Gets the inDisponivel value for this ResultadoExportarModeloModeloListaCaracteristicasCaracteristica.
     * 
     * @return inDisponivel
     */
    public java.lang.String getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ResultadoExportarModeloModeloListaCaracteristicasCaracteristica.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(java.lang.String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the valor value for this ResultadoExportarModeloModeloListaCaracteristicasCaracteristica.
     * 
     * @return valor
     */
    public java.lang.String getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this ResultadoExportarModeloModeloListaCaracteristicasCaracteristica.
     * 
     * @param valor
     */
    public void setValor(java.lang.String valor) {
        this.valor = valor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoExportarModeloModeloListaCaracteristicasCaracteristica)) return false;
        ResultadoExportarModeloModeloListaCaracteristicasCaracteristica other = (ResultadoExportarModeloModeloListaCaracteristicasCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.caracteristica==null && other.getCaracteristica()==null) || 
             (this.caracteristica!=null &&
              this.caracteristica.equals(other.getCaracteristica()))) &&
            ((this.inSimulador==null && other.getInSimulador()==null) || 
             (this.inSimulador!=null &&
              this.inSimulador.equals(other.getInSimulador()))) &&
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel()))) &&
            ((this.valor==null && other.getValor()==null) || 
             (this.valor!=null &&
              this.valor.equals(other.getValor())));
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
        if (getCaracteristica() != null) {
            _hashCode += getCaracteristica().hashCode();
        }
        if (getInSimulador() != null) {
            _hashCode += getInSimulador().hashCode();
        }
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
        }
        if (getValor() != null) {
            _hashCode += getValor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoExportarModeloModeloListaCaracteristicasCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoExportarModelo>Modelo>ListaCaracteristicas>Caracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "caracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inSimulador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "inSimulador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "inDisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "valor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
