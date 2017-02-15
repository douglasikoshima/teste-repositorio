/**
 * ResultadoExportarServicoServicoListaAtributoAtributo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoExportarServicoServicoListaAtributoAtributo  implements java.io.Serializable {
    private java.lang.String svcattrnm;

    private java.lang.Long svcattrseq;

    private java.lang.String svcattrdesc;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor[] listaAtributoValor;

    public ResultadoExportarServicoServicoListaAtributoAtributo() {
    }

    public ResultadoExportarServicoServicoListaAtributoAtributo(
           java.lang.String svcattrnm,
           java.lang.Long svcattrseq,
           java.lang.String svcattrdesc,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor[] listaAtributoValor) {
           this.svcattrnm = svcattrnm;
           this.svcattrseq = svcattrseq;
           this.svcattrdesc = svcattrdesc;
           this.listaAtributoValor = listaAtributoValor;
    }


    /**
     * Gets the svcattrnm value for this ResultadoExportarServicoServicoListaAtributoAtributo.
     * 
     * @return svcattrnm
     */
    public java.lang.String getSvcattrnm() {
        return svcattrnm;
    }


    /**
     * Sets the svcattrnm value for this ResultadoExportarServicoServicoListaAtributoAtributo.
     * 
     * @param svcattrnm
     */
    public void setSvcattrnm(java.lang.String svcattrnm) {
        this.svcattrnm = svcattrnm;
    }


    /**
     * Gets the svcattrseq value for this ResultadoExportarServicoServicoListaAtributoAtributo.
     * 
     * @return svcattrseq
     */
    public java.lang.Long getSvcattrseq() {
        return svcattrseq;
    }


    /**
     * Sets the svcattrseq value for this ResultadoExportarServicoServicoListaAtributoAtributo.
     * 
     * @param svcattrseq
     */
    public void setSvcattrseq(java.lang.Long svcattrseq) {
        this.svcattrseq = svcattrseq;
    }


    /**
     * Gets the svcattrdesc value for this ResultadoExportarServicoServicoListaAtributoAtributo.
     * 
     * @return svcattrdesc
     */
    public java.lang.String getSvcattrdesc() {
        return svcattrdesc;
    }


    /**
     * Sets the svcattrdesc value for this ResultadoExportarServicoServicoListaAtributoAtributo.
     * 
     * @param svcattrdesc
     */
    public void setSvcattrdesc(java.lang.String svcattrdesc) {
        this.svcattrdesc = svcattrdesc;
    }


    /**
     * Gets the listaAtributoValor value for this ResultadoExportarServicoServicoListaAtributoAtributo.
     * 
     * @return listaAtributoValor
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor[] getListaAtributoValor() {
        return listaAtributoValor;
    }


    /**
     * Sets the listaAtributoValor value for this ResultadoExportarServicoServicoListaAtributoAtributo.
     * 
     * @param listaAtributoValor
     */
    public void setListaAtributoValor(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor[] listaAtributoValor) {
        this.listaAtributoValor = listaAtributoValor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoExportarServicoServicoListaAtributoAtributo)) return false;
        ResultadoExportarServicoServicoListaAtributoAtributo other = (ResultadoExportarServicoServicoListaAtributoAtributo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.svcattrnm==null && other.getSvcattrnm()==null) || 
             (this.svcattrnm!=null &&
              this.svcattrnm.equals(other.getSvcattrnm()))) &&
            ((this.svcattrseq==null && other.getSvcattrseq()==null) || 
             (this.svcattrseq!=null &&
              this.svcattrseq.equals(other.getSvcattrseq()))) &&
            ((this.svcattrdesc==null && other.getSvcattrdesc()==null) || 
             (this.svcattrdesc!=null &&
              this.svcattrdesc.equals(other.getSvcattrdesc()))) &&
            ((this.listaAtributoValor==null && other.getListaAtributoValor()==null) || 
             (this.listaAtributoValor!=null &&
              java.util.Arrays.equals(this.listaAtributoValor, other.getListaAtributoValor())));
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
        if (getSvcattrnm() != null) {
            _hashCode += getSvcattrnm().hashCode();
        }
        if (getSvcattrseq() != null) {
            _hashCode += getSvcattrseq().hashCode();
        }
        if (getSvcattrdesc() != null) {
            _hashCode += getSvcattrdesc().hashCode();
        }
        if (getListaAtributoValor() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAtributoValor());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAtributoValor(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoExportarServicoServicoListaAtributoAtributo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>>ResultadoExportarServico>Servico>ListaAtributo>Atributo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("svcattrnm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "svcattrnm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("svcattrseq");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "svcattrseq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("svcattrdesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "svcattrdesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAtributoValor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaAtributoValor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>>>>ResultadoExportarServico>Servico>ListaAtributo>Atributo>ListaAtributoValor>AtributoValor"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "AtributoValor"));
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
