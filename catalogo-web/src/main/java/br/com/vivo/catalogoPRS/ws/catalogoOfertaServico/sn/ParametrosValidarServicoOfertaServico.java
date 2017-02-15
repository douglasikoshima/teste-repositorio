/**
 * ParametrosValidarServicoOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ParametrosValidarServicoOfertaServico  implements java.io.Serializable {
    private long idOfertaServico;

    private java.lang.String listaIdServico;

    public ParametrosValidarServicoOfertaServico() {
    }

    public ParametrosValidarServicoOfertaServico(
           long idOfertaServico,
           java.lang.String listaIdServico) {
           this.idOfertaServico = idOfertaServico;
           this.listaIdServico = listaIdServico;
    }


    /**
     * Gets the idOfertaServico value for this ParametrosValidarServicoOfertaServico.
     * 
     * @return idOfertaServico
     */
    public long getIdOfertaServico() {
        return idOfertaServico;
    }


    /**
     * Sets the idOfertaServico value for this ParametrosValidarServicoOfertaServico.
     * 
     * @param idOfertaServico
     */
    public void setIdOfertaServico(long idOfertaServico) {
        this.idOfertaServico = idOfertaServico;
    }


    /**
     * Gets the listaIdServico value for this ParametrosValidarServicoOfertaServico.
     * 
     * @return listaIdServico
     */
    public java.lang.String getListaIdServico() {
        return listaIdServico;
    }


    /**
     * Sets the listaIdServico value for this ParametrosValidarServicoOfertaServico.
     * 
     * @param listaIdServico
     */
    public void setListaIdServico(java.lang.String listaIdServico) {
        this.listaIdServico = listaIdServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosValidarServicoOfertaServico)) return false;
        ParametrosValidarServicoOfertaServico other = (ParametrosValidarServicoOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idOfertaServico == other.getIdOfertaServico() &&
            ((this.listaIdServico==null && other.getListaIdServico()==null) || 
             (this.listaIdServico!=null &&
              this.listaIdServico.equals(other.getListaIdServico())));
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
        _hashCode += new Long(getIdOfertaServico()).hashCode();
        if (getListaIdServico() != null) {
            _hashCode += getListaIdServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosValidarServicoOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ParametrosValidarServicoOfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "idOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "listaIdServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
