/**
 * ParametroAlterarPlano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ParametroAlterarPlano  implements java.io.Serializable {
    private long idPlano;

    private java.lang.Long idCategoria;

    private java.lang.Long qtdMaxDependentes;

    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlanoInDisponibilidadeCatalogo inDisponibilidadeCatalogo;

    private java.lang.Long idTipoPlano;

    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlanoAtivaWiFi ativaWiFi;

    public ParametroAlterarPlano() {
    }

    public ParametroAlterarPlano(
           long idPlano,
           java.lang.Long idCategoria,
           java.lang.Long qtdMaxDependentes,
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlanoInDisponibilidadeCatalogo inDisponibilidadeCatalogo,
           java.lang.Long idTipoPlano,
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlanoAtivaWiFi ativaWiFi) {
           this.idPlano = idPlano;
           this.idCategoria = idCategoria;
           this.qtdMaxDependentes = qtdMaxDependentes;
           this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
           this.idTipoPlano = idTipoPlano;
           this.ativaWiFi = ativaWiFi;
    }


    /**
     * Gets the idPlano value for this ParametroAlterarPlano.
     * 
     * @return idPlano
     */
    public long getIdPlano() {
        return idPlano;
    }


    /**
     * Sets the idPlano value for this ParametroAlterarPlano.
     * 
     * @param idPlano
     */
    public void setIdPlano(long idPlano) {
        this.idPlano = idPlano;
    }


    /**
     * Gets the idCategoria value for this ParametroAlterarPlano.
     * 
     * @return idCategoria
     */
    public java.lang.Long getIdCategoria() {
        return idCategoria;
    }


    /**
     * Sets the idCategoria value for this ParametroAlterarPlano.
     * 
     * @param idCategoria
     */
    public void setIdCategoria(java.lang.Long idCategoria) {
        this.idCategoria = idCategoria;
    }


    /**
     * Gets the qtdMaxDependentes value for this ParametroAlterarPlano.
     * 
     * @return qtdMaxDependentes
     */
    public java.lang.Long getQtdMaxDependentes() {
        return qtdMaxDependentes;
    }


    /**
     * Sets the qtdMaxDependentes value for this ParametroAlterarPlano.
     * 
     * @param qtdMaxDependentes
     */
    public void setQtdMaxDependentes(java.lang.Long qtdMaxDependentes) {
        this.qtdMaxDependentes = qtdMaxDependentes;
    }


    /**
     * Gets the inDisponibilidadeCatalogo value for this ParametroAlterarPlano.
     * 
     * @return inDisponibilidadeCatalogo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlanoInDisponibilidadeCatalogo getInDisponibilidadeCatalogo() {
        return inDisponibilidadeCatalogo;
    }


    /**
     * Sets the inDisponibilidadeCatalogo value for this ParametroAlterarPlano.
     * 
     * @param inDisponibilidadeCatalogo
     */
    public void setInDisponibilidadeCatalogo(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlanoInDisponibilidadeCatalogo inDisponibilidadeCatalogo) {
        this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
    }


    /**
     * Gets the idTipoPlano value for this ParametroAlterarPlano.
     * 
     * @return idTipoPlano
     */
    public java.lang.Long getIdTipoPlano() {
        return idTipoPlano;
    }


    /**
     * Sets the idTipoPlano value for this ParametroAlterarPlano.
     * 
     * @param idTipoPlano
     */
    public void setIdTipoPlano(java.lang.Long idTipoPlano) {
        this.idTipoPlano = idTipoPlano;
    }


    /**
     * Gets the ativaWiFi value for this ParametroAlterarPlano.
     * 
     * @return ativaWiFi
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlanoAtivaWiFi getAtivaWiFi() {
        return ativaWiFi;
    }


    /**
     * Sets the ativaWiFi value for this ParametroAlterarPlano.
     * 
     * @param ativaWiFi
     */
    public void setAtivaWiFi(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlanoAtivaWiFi ativaWiFi) {
        this.ativaWiFi = ativaWiFi;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametroAlterarPlano)) return false;
        ParametroAlterarPlano other = (ParametroAlterarPlano) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idPlano == other.getIdPlano() &&
            ((this.idCategoria==null && other.getIdCategoria()==null) || 
             (this.idCategoria!=null &&
              this.idCategoria.equals(other.getIdCategoria()))) &&
            ((this.qtdMaxDependentes==null && other.getQtdMaxDependentes()==null) || 
             (this.qtdMaxDependentes!=null &&
              this.qtdMaxDependentes.equals(other.getQtdMaxDependentes()))) &&
            ((this.inDisponibilidadeCatalogo==null && other.getInDisponibilidadeCatalogo()==null) || 
             (this.inDisponibilidadeCatalogo!=null &&
              this.inDisponibilidadeCatalogo.equals(other.getInDisponibilidadeCatalogo()))) &&
            ((this.idTipoPlano==null && other.getIdTipoPlano()==null) || 
             (this.idTipoPlano!=null &&
              this.idTipoPlano.equals(other.getIdTipoPlano()))) &&
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
        _hashCode += new Long(getIdPlano()).hashCode();
        if (getIdCategoria() != null) {
            _hashCode += getIdCategoria().hashCode();
        }
        if (getQtdMaxDependentes() != null) {
            _hashCode += getQtdMaxDependentes().hashCode();
        }
        if (getInDisponibilidadeCatalogo() != null) {
            _hashCode += getInDisponibilidadeCatalogo().hashCode();
        }
        if (getIdTipoPlano() != null) {
            _hashCode += getIdTipoPlano().hashCode();
        }
        if (getAtivaWiFi() != null) {
            _hashCode += getAtivaWiFi().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametroAlterarPlano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ParametroAlterarPlano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCategoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idCategoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdMaxDependentes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "qtdMaxDependentes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponibilidadeCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "inDisponibilidadeCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>ParametroAlterarPlano>inDisponibilidadeCatalogo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idTipoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ativaWiFi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ativaWiFi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>ParametroAlterarPlano>ativaWiFi"));
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
