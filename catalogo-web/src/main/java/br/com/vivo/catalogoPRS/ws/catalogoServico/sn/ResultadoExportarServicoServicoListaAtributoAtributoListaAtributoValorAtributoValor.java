/**
 * ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor  implements java.io.Serializable {
    private java.lang.String valor;

    private java.lang.String indefault;

    public ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor() {
    }

    public ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor(
           java.lang.String valor,
           java.lang.String indefault) {
           this.valor = valor;
           this.indefault = indefault;
    }


    /**
     * Gets the valor value for this ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor.
     * 
     * @return valor
     */
    public java.lang.String getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor.
     * 
     * @param valor
     */
    public void setValor(java.lang.String valor) {
        this.valor = valor;
    }


    /**
     * Gets the indefault value for this ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor.
     * 
     * @return indefault
     */
    public java.lang.String getIndefault() {
        return indefault;
    }


    /**
     * Sets the indefault value for this ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor.
     * 
     * @param indefault
     */
    public void setIndefault(java.lang.String indefault) {
        this.indefault = indefault;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor)) return false;
        ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor other = (ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.valor==null && other.getValor()==null) || 
             (this.valor!=null &&
              this.valor.equals(other.getValor()))) &&
            ((this.indefault==null && other.getIndefault()==null) || 
             (this.indefault!=null &&
              this.indefault.equals(other.getIndefault())));
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
        if (getValor() != null) {
            _hashCode += getValor().hashCode();
        }
        if (getIndefault() != null) {
            _hashCode += getIndefault().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>>>>ResultadoExportarServico>Servico>ListaAtributo>Atributo>ListaAtributoValor>AtributoValor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "valor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indefault");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "indefault"));
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
