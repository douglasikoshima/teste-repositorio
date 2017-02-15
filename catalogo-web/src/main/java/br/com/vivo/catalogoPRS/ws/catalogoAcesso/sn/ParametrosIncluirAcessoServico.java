/**
 * ParametrosIncluirAcessoServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class ParametrosIncluirAcessoServico  implements java.io.Serializable {
    private long[] listaIdPerfil;

    private long idServico;

    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoConsulta inRestricaoConsulta;

    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoAtivacao inRestricaoAtivacao;

    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoDesativacao inRestricaoDesativacao;

    private long[] listaIdSistema;

    public ParametrosIncluirAcessoServico() {
    }

    public ParametrosIncluirAcessoServico(
           long[] listaIdPerfil,
           long idServico,
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoConsulta inRestricaoConsulta,
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoAtivacao inRestricaoAtivacao,
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoDesativacao inRestricaoDesativacao,
           long[] listaIdSistema) {
           this.listaIdPerfil = listaIdPerfil;
           this.idServico = idServico;
           this.inRestricaoConsulta = inRestricaoConsulta;
           this.inRestricaoAtivacao = inRestricaoAtivacao;
           this.inRestricaoDesativacao = inRestricaoDesativacao;
           this.listaIdSistema = listaIdSistema;
    }


    /**
     * Gets the listaIdPerfil value for this ParametrosIncluirAcessoServico.
     * 
     * @return listaIdPerfil
     */
    public long[] getListaIdPerfil() {
        return listaIdPerfil;
    }


    /**
     * Sets the listaIdPerfil value for this ParametrosIncluirAcessoServico.
     * 
     * @param listaIdPerfil
     */
    public void setListaIdPerfil(long[] listaIdPerfil) {
        this.listaIdPerfil = listaIdPerfil;
    }


    /**
     * Gets the idServico value for this ParametrosIncluirAcessoServico.
     * 
     * @return idServico
     */
    public long getIdServico() {
        return idServico;
    }


    /**
     * Sets the idServico value for this ParametrosIncluirAcessoServico.
     * 
     * @param idServico
     */
    public void setIdServico(long idServico) {
        this.idServico = idServico;
    }


    /**
     * Gets the inRestricaoConsulta value for this ParametrosIncluirAcessoServico.
     * 
     * @return inRestricaoConsulta
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoConsulta getInRestricaoConsulta() {
        return inRestricaoConsulta;
    }


    /**
     * Sets the inRestricaoConsulta value for this ParametrosIncluirAcessoServico.
     * 
     * @param inRestricaoConsulta
     */
    public void setInRestricaoConsulta(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoConsulta inRestricaoConsulta) {
        this.inRestricaoConsulta = inRestricaoConsulta;
    }


    /**
     * Gets the inRestricaoAtivacao value for this ParametrosIncluirAcessoServico.
     * 
     * @return inRestricaoAtivacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoAtivacao getInRestricaoAtivacao() {
        return inRestricaoAtivacao;
    }


    /**
     * Sets the inRestricaoAtivacao value for this ParametrosIncluirAcessoServico.
     * 
     * @param inRestricaoAtivacao
     */
    public void setInRestricaoAtivacao(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoAtivacao inRestricaoAtivacao) {
        this.inRestricaoAtivacao = inRestricaoAtivacao;
    }


    /**
     * Gets the inRestricaoDesativacao value for this ParametrosIncluirAcessoServico.
     * 
     * @return inRestricaoDesativacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoDesativacao getInRestricaoDesativacao() {
        return inRestricaoDesativacao;
    }


    /**
     * Sets the inRestricaoDesativacao value for this ParametrosIncluirAcessoServico.
     * 
     * @param inRestricaoDesativacao
     */
    public void setInRestricaoDesativacao(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoDesativacao inRestricaoDesativacao) {
        this.inRestricaoDesativacao = inRestricaoDesativacao;
    }


    /**
     * Gets the listaIdSistema value for this ParametrosIncluirAcessoServico.
     * 
     * @return listaIdSistema
     */
    public long[] getListaIdSistema() {
        return listaIdSistema;
    }


    /**
     * Sets the listaIdSistema value for this ParametrosIncluirAcessoServico.
     * 
     * @param listaIdSistema
     */
    public void setListaIdSistema(long[] listaIdSistema) {
        this.listaIdSistema = listaIdSistema;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosIncluirAcessoServico)) return false;
        ParametrosIncluirAcessoServico other = (ParametrosIncluirAcessoServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaIdPerfil==null && other.getListaIdPerfil()==null) || 
             (this.listaIdPerfil!=null &&
              java.util.Arrays.equals(this.listaIdPerfil, other.getListaIdPerfil()))) &&
            this.idServico == other.getIdServico() &&
            ((this.inRestricaoConsulta==null && other.getInRestricaoConsulta()==null) || 
             (this.inRestricaoConsulta!=null &&
              this.inRestricaoConsulta.equals(other.getInRestricaoConsulta()))) &&
            ((this.inRestricaoAtivacao==null && other.getInRestricaoAtivacao()==null) || 
             (this.inRestricaoAtivacao!=null &&
              this.inRestricaoAtivacao.equals(other.getInRestricaoAtivacao()))) &&
            ((this.inRestricaoDesativacao==null && other.getInRestricaoDesativacao()==null) || 
             (this.inRestricaoDesativacao!=null &&
              this.inRestricaoDesativacao.equals(other.getInRestricaoDesativacao()))) &&
            ((this.listaIdSistema==null && other.getListaIdSistema()==null) || 
             (this.listaIdSistema!=null &&
              java.util.Arrays.equals(this.listaIdSistema, other.getListaIdSistema())));
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
        if (getListaIdPerfil() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdPerfil());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdPerfil(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getIdServico()).hashCode();
        if (getInRestricaoConsulta() != null) {
            _hashCode += getInRestricaoConsulta().hashCode();
        }
        if (getInRestricaoAtivacao() != null) {
            _hashCode += getInRestricaoAtivacao().hashCode();
        }
        if (getInRestricaoDesativacao() != null) {
            _hashCode += getInRestricaoDesativacao().hashCode();
        }
        if (getListaIdSistema() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdSistema());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdSistema(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosIncluirAcessoServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosIncluirAcessoServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdPerfil");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ListaIdPerfil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idPerfilSCA"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inRestricaoConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "inRestricaoConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>ParametrosIncluirAcessoServico>inRestricaoConsulta"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inRestricaoAtivacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "inRestricaoAtivacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>ParametrosIncluirAcessoServico>inRestricaoAtivacao"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inRestricaoDesativacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "inRestricaoDesativacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>ParametrosIncluirAcessoServico>inRestricaoDesativacao"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdSistema");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ListaIdSistema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idSistemaOrigem"));
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
