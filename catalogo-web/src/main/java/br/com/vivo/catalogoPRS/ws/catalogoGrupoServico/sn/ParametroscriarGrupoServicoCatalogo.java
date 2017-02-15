/**
 * ParametroscriarGrupoServicoCatalogo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn;

public class ParametroscriarGrupoServicoCatalogo  implements java.io.Serializable {
    private java.lang.String nomeGrupoServico;

    private java.lang.String indisponivel;

    private java.lang.String nomeUsuariosCricacao;

    private java.util.Calendar dataCriacao;

    public ParametroscriarGrupoServicoCatalogo() {
    }

    public ParametroscriarGrupoServicoCatalogo(
           java.lang.String nomeGrupoServico,
           java.lang.String indisponivel,
           java.lang.String nomeUsuariosCricacao,
           java.util.Calendar dataCriacao) {
           this.nomeGrupoServico = nomeGrupoServico;
           this.indisponivel = indisponivel;
           this.nomeUsuariosCricacao = nomeUsuariosCricacao;
           this.dataCriacao = dataCriacao;
    }


    /**
     * Gets the nomeGrupoServico value for this ParametroscriarGrupoServicoCatalogo.
     * 
     * @return nomeGrupoServico
     */
    public java.lang.String getNomeGrupoServico() {
        return nomeGrupoServico;
    }


    /**
     * Sets the nomeGrupoServico value for this ParametroscriarGrupoServicoCatalogo.
     * 
     * @param nomeGrupoServico
     */
    public void setNomeGrupoServico(java.lang.String nomeGrupoServico) {
        this.nomeGrupoServico = nomeGrupoServico;
    }


    /**
     * Gets the indisponivel value for this ParametroscriarGrupoServicoCatalogo.
     * 
     * @return indisponivel
     */
    public java.lang.String getIndisponivel() {
        return indisponivel;
    }


    /**
     * Sets the indisponivel value for this ParametroscriarGrupoServicoCatalogo.
     * 
     * @param indisponivel
     */
    public void setIndisponivel(java.lang.String indisponivel) {
        this.indisponivel = indisponivel;
    }


    /**
     * Gets the nomeUsuariosCricacao value for this ParametroscriarGrupoServicoCatalogo.
     * 
     * @return nomeUsuariosCricacao
     */
    public java.lang.String getNomeUsuariosCricacao() {
        return nomeUsuariosCricacao;
    }


    /**
     * Sets the nomeUsuariosCricacao value for this ParametroscriarGrupoServicoCatalogo.
     * 
     * @param nomeUsuariosCricacao
     */
    public void setNomeUsuariosCricacao(java.lang.String nomeUsuariosCricacao) {
        this.nomeUsuariosCricacao = nomeUsuariosCricacao;
    }


    /**
     * Gets the dataCriacao value for this ParametroscriarGrupoServicoCatalogo.
     * 
     * @return dataCriacao
     */
    public java.util.Calendar getDataCriacao() {
        return dataCriacao;
    }


    /**
     * Sets the dataCriacao value for this ParametroscriarGrupoServicoCatalogo.
     * 
     * @param dataCriacao
     */
    public void setDataCriacao(java.util.Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametroscriarGrupoServicoCatalogo)) return false;
        ParametroscriarGrupoServicoCatalogo other = (ParametroscriarGrupoServicoCatalogo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nomeGrupoServico==null && other.getNomeGrupoServico()==null) || 
             (this.nomeGrupoServico!=null &&
              this.nomeGrupoServico.equals(other.getNomeGrupoServico()))) &&
            ((this.indisponivel==null && other.getIndisponivel()==null) || 
             (this.indisponivel!=null &&
              this.indisponivel.equals(other.getIndisponivel()))) &&
            ((this.nomeUsuariosCricacao==null && other.getNomeUsuariosCricacao()==null) || 
             (this.nomeUsuariosCricacao!=null &&
              this.nomeUsuariosCricacao.equals(other.getNomeUsuariosCricacao()))) &&
            ((this.dataCriacao==null && other.getDataCriacao()==null) || 
             (this.dataCriacao!=null &&
              this.dataCriacao.equals(other.getDataCriacao())));
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
        if (getNomeGrupoServico() != null) {
            _hashCode += getNomeGrupoServico().hashCode();
        }
        if (getIndisponivel() != null) {
            _hashCode += getIndisponivel().hashCode();
        }
        if (getNomeUsuariosCricacao() != null) {
            _hashCode += getNomeUsuariosCricacao().hashCode();
        }
        if (getDataCriacao() != null) {
            _hashCode += getDataCriacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametroscriarGrupoServicoCatalogo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ParametroscriarGrupoServicoCatalogo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeGrupoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "nomeGrupoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "indisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeUsuariosCricacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "nomeUsuariosCricacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "dataCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
