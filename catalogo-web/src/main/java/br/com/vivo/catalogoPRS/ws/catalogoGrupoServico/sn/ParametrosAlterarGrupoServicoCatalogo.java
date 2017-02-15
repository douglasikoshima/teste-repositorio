/**
 * ParametrosAlterarGrupoServicoCatalogo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn;

public class ParametrosAlterarGrupoServicoCatalogo  implements java.io.Serializable {
    private long idGrupoServico;

    private java.lang.String nmGrupoServico;

    private java.lang.String indisponivel;

    private java.lang.String nmUsuarioAlteracao;

    private java.util.Calendar dtAlteracao;

    public ParametrosAlterarGrupoServicoCatalogo() {
    }

    public ParametrosAlterarGrupoServicoCatalogo(
           long idGrupoServico,
           java.lang.String nmGrupoServico,
           java.lang.String indisponivel,
           java.lang.String nmUsuarioAlteracao,
           java.util.Calendar dtAlteracao) {
           this.idGrupoServico = idGrupoServico;
           this.nmGrupoServico = nmGrupoServico;
           this.indisponivel = indisponivel;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.dtAlteracao = dtAlteracao;
    }


    /**
     * Gets the idGrupoServico value for this ParametrosAlterarGrupoServicoCatalogo.
     * 
     * @return idGrupoServico
     */
    public long getIdGrupoServico() {
        return idGrupoServico;
    }


    /**
     * Sets the idGrupoServico value for this ParametrosAlterarGrupoServicoCatalogo.
     * 
     * @param idGrupoServico
     */
    public void setIdGrupoServico(long idGrupoServico) {
        this.idGrupoServico = idGrupoServico;
    }


    /**
     * Gets the nmGrupoServico value for this ParametrosAlterarGrupoServicoCatalogo.
     * 
     * @return nmGrupoServico
     */
    public java.lang.String getNmGrupoServico() {
        return nmGrupoServico;
    }


    /**
     * Sets the nmGrupoServico value for this ParametrosAlterarGrupoServicoCatalogo.
     * 
     * @param nmGrupoServico
     */
    public void setNmGrupoServico(java.lang.String nmGrupoServico) {
        this.nmGrupoServico = nmGrupoServico;
    }


    /**
     * Gets the indisponivel value for this ParametrosAlterarGrupoServicoCatalogo.
     * 
     * @return indisponivel
     */
    public java.lang.String getIndisponivel() {
        return indisponivel;
    }


    /**
     * Sets the indisponivel value for this ParametrosAlterarGrupoServicoCatalogo.
     * 
     * @param indisponivel
     */
    public void setIndisponivel(java.lang.String indisponivel) {
        this.indisponivel = indisponivel;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ParametrosAlterarGrupoServicoCatalogo.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ParametrosAlterarGrupoServicoCatalogo.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the dtAlteracao value for this ParametrosAlterarGrupoServicoCatalogo.
     * 
     * @return dtAlteracao
     */
    public java.util.Calendar getDtAlteracao() {
        return dtAlteracao;
    }


    /**
     * Sets the dtAlteracao value for this ParametrosAlterarGrupoServicoCatalogo.
     * 
     * @param dtAlteracao
     */
    public void setDtAlteracao(java.util.Calendar dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarGrupoServicoCatalogo)) return false;
        ParametrosAlterarGrupoServicoCatalogo other = (ParametrosAlterarGrupoServicoCatalogo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idGrupoServico == other.getIdGrupoServico() &&
            ((this.nmGrupoServico==null && other.getNmGrupoServico()==null) || 
             (this.nmGrupoServico!=null &&
              this.nmGrupoServico.equals(other.getNmGrupoServico()))) &&
            ((this.indisponivel==null && other.getIndisponivel()==null) || 
             (this.indisponivel!=null &&
              this.indisponivel.equals(other.getIndisponivel()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            ((this.dtAlteracao==null && other.getDtAlteracao()==null) || 
             (this.dtAlteracao!=null &&
              this.dtAlteracao.equals(other.getDtAlteracao())));
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
        _hashCode += new Long(getIdGrupoServico()).hashCode();
        if (getNmGrupoServico() != null) {
            _hashCode += getNmGrupoServico().hashCode();
        }
        if (getIndisponivel() != null) {
            _hashCode += getIndisponivel().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        if (getDtAlteracao() != null) {
            _hashCode += getDtAlteracao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarGrupoServicoCatalogo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ParametrosAlterarGrupoServicoCatalogo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "idGrupoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmGrupoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "nmGrupoServico"));
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
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "dtAlteracao"));
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
