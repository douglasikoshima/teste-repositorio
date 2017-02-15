/**
 * ParametrosAlterarServicoParametrizacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ParametrosAlterarServicoParametrizacao  implements java.io.Serializable {
    private long idServico;

    private long idCategoriaCatalogo;

    private long idTpServico;

    private long qtdMinAtivacaoCatalogo;

    private long qtdMaxAtivacaoCatalogo;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarServicoParametrizacaoInDisponibilidadeCatalogo inDisponibilidadeCatalogo;

    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarServicoParametrizacaoAtivaWiFi ativaWiFi;

    public ParametrosAlterarServicoParametrizacao() {
    }

    public ParametrosAlterarServicoParametrizacao(
           long idServico,
           long idCategoriaCatalogo,
           long idTpServico,
           long qtdMinAtivacaoCatalogo,
           long qtdMaxAtivacaoCatalogo,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarServicoParametrizacaoInDisponibilidadeCatalogo inDisponibilidadeCatalogo,
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarServicoParametrizacaoAtivaWiFi ativaWiFi) {
           this.idServico = idServico;
           this.idCategoriaCatalogo = idCategoriaCatalogo;
           this.idTpServico = idTpServico;
           this.qtdMinAtivacaoCatalogo = qtdMinAtivacaoCatalogo;
           this.qtdMaxAtivacaoCatalogo = qtdMaxAtivacaoCatalogo;
           this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
           this.ativaWiFi = ativaWiFi;
    }


    /**
     * Gets the idServico value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @return idServico
     */
    public long getIdServico() {
        return idServico;
    }


    /**
     * Sets the idServico value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @param idServico
     */
    public void setIdServico(long idServico) {
        this.idServico = idServico;
    }


    /**
     * Gets the idCategoriaCatalogo value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @return idCategoriaCatalogo
     */
    public long getIdCategoriaCatalogo() {
        return idCategoriaCatalogo;
    }


    /**
     * Sets the idCategoriaCatalogo value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @param idCategoriaCatalogo
     */
    public void setIdCategoriaCatalogo(long idCategoriaCatalogo) {
        this.idCategoriaCatalogo = idCategoriaCatalogo;
    }


    /**
     * Gets the idTpServico value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @return idTpServico
     */
    public long getIdTpServico() {
        return idTpServico;
    }


    /**
     * Sets the idTpServico value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @param idTpServico
     */
    public void setIdTpServico(long idTpServico) {
        this.idTpServico = idTpServico;
    }


    /**
     * Gets the qtdMinAtivacaoCatalogo value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @return qtdMinAtivacaoCatalogo
     */
    public long getQtdMinAtivacaoCatalogo() {
        return qtdMinAtivacaoCatalogo;
    }


    /**
     * Sets the qtdMinAtivacaoCatalogo value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @param qtdMinAtivacaoCatalogo
     */
    public void setQtdMinAtivacaoCatalogo(long qtdMinAtivacaoCatalogo) {
        this.qtdMinAtivacaoCatalogo = qtdMinAtivacaoCatalogo;
    }


    /**
     * Gets the qtdMaxAtivacaoCatalogo value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @return qtdMaxAtivacaoCatalogo
     */
    public long getQtdMaxAtivacaoCatalogo() {
        return qtdMaxAtivacaoCatalogo;
    }


    /**
     * Sets the qtdMaxAtivacaoCatalogo value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @param qtdMaxAtivacaoCatalogo
     */
    public void setQtdMaxAtivacaoCatalogo(long qtdMaxAtivacaoCatalogo) {
        this.qtdMaxAtivacaoCatalogo = qtdMaxAtivacaoCatalogo;
    }


    /**
     * Gets the inDisponibilidadeCatalogo value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @return inDisponibilidadeCatalogo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarServicoParametrizacaoInDisponibilidadeCatalogo getInDisponibilidadeCatalogo() {
        return inDisponibilidadeCatalogo;
    }


    /**
     * Sets the inDisponibilidadeCatalogo value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @param inDisponibilidadeCatalogo
     */
    public void setInDisponibilidadeCatalogo(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarServicoParametrizacaoInDisponibilidadeCatalogo inDisponibilidadeCatalogo) {
        this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
    }


    /**
     * Gets the ativaWiFi value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @return ativaWiFi
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarServicoParametrizacaoAtivaWiFi getAtivaWiFi() {
        return ativaWiFi;
    }


    /**
     * Sets the ativaWiFi value for this ParametrosAlterarServicoParametrizacao.
     * 
     * @param ativaWiFi
     */
    public void setAtivaWiFi(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarServicoParametrizacaoAtivaWiFi ativaWiFi) {
        this.ativaWiFi = ativaWiFi;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarServicoParametrizacao)) return false;
        ParametrosAlterarServicoParametrizacao other = (ParametrosAlterarServicoParametrizacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idServico == other.getIdServico() &&
            this.idCategoriaCatalogo == other.getIdCategoriaCatalogo() &&
            this.idTpServico == other.getIdTpServico() &&
            this.qtdMinAtivacaoCatalogo == other.getQtdMinAtivacaoCatalogo() &&
            this.qtdMaxAtivacaoCatalogo == other.getQtdMaxAtivacaoCatalogo() &&
            ((this.inDisponibilidadeCatalogo==null && other.getInDisponibilidadeCatalogo()==null) || 
             (this.inDisponibilidadeCatalogo!=null &&
              this.inDisponibilidadeCatalogo.equals(other.getInDisponibilidadeCatalogo()))) &&
            ((this.ativaWiFi==null && other.getAtivaWiFi()==null) || 
             (this.ativaWiFi!=null &&
              this.ativaWiFi.equals(other.getAtivaWiFi())));
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
        _hashCode += new Long(getIdServico()).hashCode();
        _hashCode += new Long(getIdCategoriaCatalogo()).hashCode();
        _hashCode += new Long(getIdTpServico()).hashCode();
        _hashCode += new Long(getQtdMinAtivacaoCatalogo()).hashCode();
        _hashCode += new Long(getQtdMaxAtivacaoCatalogo()).hashCode();
        if (getInDisponibilidadeCatalogo() != null) {
            _hashCode += getInDisponibilidadeCatalogo().hashCode();
        }
        if (getAtivaWiFi() != null) {
            _hashCode += getAtivaWiFi().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarServicoParametrizacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosAlterarServicoParametrizacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCategoriaCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idCategoriaCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTpServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idTpServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMinAtivacaoCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdMinAtivacaoCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMaxAtivacaoCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "qtdMaxAtivacaoCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponibilidadeCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "inDisponibilidadeCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>ParametrosAlterarServicoParametrizacao>inDisponibilidadeCatalogo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ativaWiFi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ativaWiFi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>ParametrosAlterarServicoParametrizacao>ativaWiFi"));
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
