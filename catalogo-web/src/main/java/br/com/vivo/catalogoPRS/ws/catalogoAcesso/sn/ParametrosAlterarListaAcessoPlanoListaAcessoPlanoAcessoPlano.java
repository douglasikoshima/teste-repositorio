/**
 * ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano  implements java.io.Serializable {
    private long idAcessoPlano;

    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoConsulta inRestricaoConsulta;

    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoAtivacao inRestricaoAtivacao;

    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoDesativacao inRestricaoDesativacao;

    public ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano() {
    }

    public ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano(
           long idAcessoPlano,
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoConsulta inRestricaoConsulta,
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoAtivacao inRestricaoAtivacao,
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoDesativacao inRestricaoDesativacao) {
           this.idAcessoPlano = idAcessoPlano;
           this.inRestricaoConsulta = inRestricaoConsulta;
           this.inRestricaoAtivacao = inRestricaoAtivacao;
           this.inRestricaoDesativacao = inRestricaoDesativacao;
    }


    /**
     * Gets the idAcessoPlano value for this ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.
     * 
     * @return idAcessoPlano
     */
    public long getIdAcessoPlano() {
        return idAcessoPlano;
    }


    /**
     * Sets the idAcessoPlano value for this ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.
     * 
     * @param idAcessoPlano
     */
    public void setIdAcessoPlano(long idAcessoPlano) {
        this.idAcessoPlano = idAcessoPlano;
    }


    /**
     * Gets the inRestricaoConsulta value for this ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.
     * 
     * @return inRestricaoConsulta
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoConsulta getInRestricaoConsulta() {
        return inRestricaoConsulta;
    }


    /**
     * Sets the inRestricaoConsulta value for this ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.
     * 
     * @param inRestricaoConsulta
     */
    public void setInRestricaoConsulta(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoConsulta inRestricaoConsulta) {
        this.inRestricaoConsulta = inRestricaoConsulta;
    }


    /**
     * Gets the inRestricaoAtivacao value for this ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.
     * 
     * @return inRestricaoAtivacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoAtivacao getInRestricaoAtivacao() {
        return inRestricaoAtivacao;
    }


    /**
     * Sets the inRestricaoAtivacao value for this ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.
     * 
     * @param inRestricaoAtivacao
     */
    public void setInRestricaoAtivacao(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoAtivacao inRestricaoAtivacao) {
        this.inRestricaoAtivacao = inRestricaoAtivacao;
    }


    /**
     * Gets the inRestricaoDesativacao value for this ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.
     * 
     * @return inRestricaoDesativacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoDesativacao getInRestricaoDesativacao() {
        return inRestricaoDesativacao;
    }


    /**
     * Sets the inRestricaoDesativacao value for this ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.
     * 
     * @param inRestricaoDesativacao
     */
    public void setInRestricaoDesativacao(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoDesativacao inRestricaoDesativacao) {
        this.inRestricaoDesativacao = inRestricaoDesativacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano)) return false;
        ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano other = (ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idAcessoPlano == other.getIdAcessoPlano() &&
            ((this.inRestricaoConsulta==null && other.getInRestricaoConsulta()==null) || 
             (this.inRestricaoConsulta!=null &&
              this.inRestricaoConsulta.equals(other.getInRestricaoConsulta()))) &&
            ((this.inRestricaoAtivacao==null && other.getInRestricaoAtivacao()==null) || 
             (this.inRestricaoAtivacao!=null &&
              this.inRestricaoAtivacao.equals(other.getInRestricaoAtivacao()))) &&
            ((this.inRestricaoDesativacao==null && other.getInRestricaoDesativacao()==null) || 
             (this.inRestricaoDesativacao!=null &&
              this.inRestricaoDesativacao.equals(other.getInRestricaoDesativacao())));
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
        _hashCode += new Long(getIdAcessoPlano()).hashCode();
        if (getInRestricaoConsulta() != null) {
            _hashCode += getInRestricaoConsulta().hashCode();
        }
        if (getInRestricaoAtivacao() != null) {
            _hashCode += getInRestricaoAtivacao().hashCode();
        }
        if (getInRestricaoDesativacao() != null) {
            _hashCode += getInRestricaoDesativacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>>ParametrosAlterarListaAcessoPlano>ListaAcessoPlano>AcessoPlano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAcessoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idAcessoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inRestricaoConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "inRestricaoConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>>>ParametrosAlterarListaAcessoPlano>ListaAcessoPlano>AcessoPlano>inRestricaoConsulta"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inRestricaoAtivacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "inRestricaoAtivacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>>>ParametrosAlterarListaAcessoPlano>ListaAcessoPlano>AcessoPlano>inRestricaoAtivacao"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inRestricaoDesativacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "inRestricaoDesativacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>>>ParametrosAlterarListaAcessoPlano>ListaAcessoPlano>AcessoPlano>inRestricaoDesativacao"));
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
