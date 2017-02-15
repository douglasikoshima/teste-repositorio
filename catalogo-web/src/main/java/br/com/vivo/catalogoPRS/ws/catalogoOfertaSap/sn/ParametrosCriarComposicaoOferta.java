/**
 * ParametrosCriarComposicaoOferta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class ParametrosCriarComposicaoOferta  implements java.io.Serializable {
    private long idOfertaSap;

    private long[] listaIdSistemaPlano;

    private java.lang.Long idOfertaServico;

    private long idMatrizOferta;

    public ParametrosCriarComposicaoOferta() {
    }

    public ParametrosCriarComposicaoOferta(
           long idOfertaSap,
           long[] listaIdSistemaPlano,
           java.lang.Long idOfertaServico,
           long idMatrizOferta) {
           this.idOfertaSap = idOfertaSap;
           this.listaIdSistemaPlano = listaIdSistemaPlano;
           this.idOfertaServico = idOfertaServico;
           this.idMatrizOferta = idMatrizOferta;
    }


    /**
     * Gets the idOfertaSap value for this ParametrosCriarComposicaoOferta.
     * 
     * @return idOfertaSap
     */
    public long getIdOfertaSap() {
        return idOfertaSap;
    }


    /**
     * Sets the idOfertaSap value for this ParametrosCriarComposicaoOferta.
     * 
     * @param idOfertaSap
     */
    public void setIdOfertaSap(long idOfertaSap) {
        this.idOfertaSap = idOfertaSap;
    }


    /**
     * Gets the listaIdSistemaPlano value for this ParametrosCriarComposicaoOferta.
     * 
     * @return listaIdSistemaPlano
     */
    public long[] getListaIdSistemaPlano() {
        return listaIdSistemaPlano;
    }


    /**
     * Sets the listaIdSistemaPlano value for this ParametrosCriarComposicaoOferta.
     * 
     * @param listaIdSistemaPlano
     */
    public void setListaIdSistemaPlano(long[] listaIdSistemaPlano) {
        this.listaIdSistemaPlano = listaIdSistemaPlano;
    }


    /**
     * Gets the idOfertaServico value for this ParametrosCriarComposicaoOferta.
     * 
     * @return idOfertaServico
     */
    public java.lang.Long getIdOfertaServico() {
        return idOfertaServico;
    }


    /**
     * Sets the idOfertaServico value for this ParametrosCriarComposicaoOferta.
     * 
     * @param idOfertaServico
     */
    public void setIdOfertaServico(java.lang.Long idOfertaServico) {
        this.idOfertaServico = idOfertaServico;
    }


    /**
     * Gets the idMatrizOferta value for this ParametrosCriarComposicaoOferta.
     * 
     * @return idMatrizOferta
     */
    public long getIdMatrizOferta() {
        return idMatrizOferta;
    }


    /**
     * Sets the idMatrizOferta value for this ParametrosCriarComposicaoOferta.
     * 
     * @param idMatrizOferta
     */
    public void setIdMatrizOferta(long idMatrizOferta) {
        this.idMatrizOferta = idMatrizOferta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosCriarComposicaoOferta)) return false;
        ParametrosCriarComposicaoOferta other = (ParametrosCriarComposicaoOferta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idOfertaSap == other.getIdOfertaSap() &&
            ((this.listaIdSistemaPlano==null && other.getListaIdSistemaPlano()==null) || 
             (this.listaIdSistemaPlano!=null &&
              java.util.Arrays.equals(this.listaIdSistemaPlano, other.getListaIdSistemaPlano()))) &&
            ((this.idOfertaServico==null && other.getIdOfertaServico()==null) || 
             (this.idOfertaServico!=null &&
              this.idOfertaServico.equals(other.getIdOfertaServico()))) &&
            this.idMatrizOferta == other.getIdMatrizOferta();
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
        _hashCode += new Long(getIdOfertaSap()).hashCode();
        if (getListaIdSistemaPlano() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdSistemaPlano());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdSistemaPlano(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdOfertaServico() != null) {
            _hashCode += getIdOfertaServico().hashCode();
        }
        _hashCode += new Long(getIdMatrizOferta()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosCriarComposicaoOferta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">ParametrosCriarComposicaoOferta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOfertaSap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "idOfertaSap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdSistemaPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ListaIdSistemaPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "idSistemaPlano"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "idOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMatrizOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "idMatrizOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
