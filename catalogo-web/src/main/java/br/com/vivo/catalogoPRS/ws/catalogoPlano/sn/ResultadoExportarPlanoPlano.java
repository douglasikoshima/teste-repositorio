/**
 * ResultadoExportarPlanoPlano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ResultadoExportarPlanoPlano  implements java.io.Serializable {
    private java.lang.String nmPlano;

    private java.lang.String plataforma;

    private java.lang.String tecnologia;

    private java.lang.String tipoCliente;

    private java.lang.String canal;

    private java.lang.String SGSegmento;

    private java.lang.String SGCarteira;

    private java.lang.String uf;

    private java.lang.String DDD;

    public ResultadoExportarPlanoPlano() {
    }

    public ResultadoExportarPlanoPlano(
           java.lang.String nmPlano,
           java.lang.String plataforma,
           java.lang.String tecnologia,
           java.lang.String tipoCliente,
           java.lang.String canal,
           java.lang.String SGSegmento,
           java.lang.String SGCarteira,
           java.lang.String uf,
           java.lang.String DDD) {
           this.nmPlano = nmPlano;
           this.plataforma = plataforma;
           this.tecnologia = tecnologia;
           this.tipoCliente = tipoCliente;
           this.canal = canal;
           this.SGSegmento = SGSegmento;
           this.SGCarteira = SGCarteira;
           this.uf = uf;
           this.DDD = DDD;
    }


    /**
     * Gets the nmPlano value for this ResultadoExportarPlanoPlano.
     * 
     * @return nmPlano
     */
    public java.lang.String getNmPlano() {
        return nmPlano;
    }


    /**
     * Sets the nmPlano value for this ResultadoExportarPlanoPlano.
     * 
     * @param nmPlano
     */
    public void setNmPlano(java.lang.String nmPlano) {
        this.nmPlano = nmPlano;
    }


    /**
     * Gets the plataforma value for this ResultadoExportarPlanoPlano.
     * 
     * @return plataforma
     */
    public java.lang.String getPlataforma() {
        return plataforma;
    }


    /**
     * Sets the plataforma value for this ResultadoExportarPlanoPlano.
     * 
     * @param plataforma
     */
    public void setPlataforma(java.lang.String plataforma) {
        this.plataforma = plataforma;
    }


    /**
     * Gets the tecnologia value for this ResultadoExportarPlanoPlano.
     * 
     * @return tecnologia
     */
    public java.lang.String getTecnologia() {
        return tecnologia;
    }


    /**
     * Sets the tecnologia value for this ResultadoExportarPlanoPlano.
     * 
     * @param tecnologia
     */
    public void setTecnologia(java.lang.String tecnologia) {
        this.tecnologia = tecnologia;
    }


    /**
     * Gets the tipoCliente value for this ResultadoExportarPlanoPlano.
     * 
     * @return tipoCliente
     */
    public java.lang.String getTipoCliente() {
        return tipoCliente;
    }


    /**
     * Sets the tipoCliente value for this ResultadoExportarPlanoPlano.
     * 
     * @param tipoCliente
     */
    public void setTipoCliente(java.lang.String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }


    /**
     * Gets the canal value for this ResultadoExportarPlanoPlano.
     * 
     * @return canal
     */
    public java.lang.String getCanal() {
        return canal;
    }


    /**
     * Sets the canal value for this ResultadoExportarPlanoPlano.
     * 
     * @param canal
     */
    public void setCanal(java.lang.String canal) {
        this.canal = canal;
    }


    /**
     * Gets the SGSegmento value for this ResultadoExportarPlanoPlano.
     * 
     * @return SGSegmento
     */
    public java.lang.String getSGSegmento() {
        return SGSegmento;
    }


    /**
     * Sets the SGSegmento value for this ResultadoExportarPlanoPlano.
     * 
     * @param SGSegmento
     */
    public void setSGSegmento(java.lang.String SGSegmento) {
        this.SGSegmento = SGSegmento;
    }


    /**
     * Gets the SGCarteira value for this ResultadoExportarPlanoPlano.
     * 
     * @return SGCarteira
     */
    public java.lang.String getSGCarteira() {
        return SGCarteira;
    }


    /**
     * Sets the SGCarteira value for this ResultadoExportarPlanoPlano.
     * 
     * @param SGCarteira
     */
    public void setSGCarteira(java.lang.String SGCarteira) {
        this.SGCarteira = SGCarteira;
    }


    /**
     * Gets the uf value for this ResultadoExportarPlanoPlano.
     * 
     * @return uf
     */
    public java.lang.String getUf() {
        return uf;
    }


    /**
     * Sets the uf value for this ResultadoExportarPlanoPlano.
     * 
     * @param uf
     */
    public void setUf(java.lang.String uf) {
        this.uf = uf;
    }


    /**
     * Gets the DDD value for this ResultadoExportarPlanoPlano.
     * 
     * @return DDD
     */
    public java.lang.String getDDD() {
        return DDD;
    }


    /**
     * Sets the DDD value for this ResultadoExportarPlanoPlano.
     * 
     * @param DDD
     */
    public void setDDD(java.lang.String DDD) {
        this.DDD = DDD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoExportarPlanoPlano)) return false;
        ResultadoExportarPlanoPlano other = (ResultadoExportarPlanoPlano) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nmPlano==null && other.getNmPlano()==null) || 
             (this.nmPlano!=null &&
              this.nmPlano.equals(other.getNmPlano()))) &&
            ((this.plataforma==null && other.getPlataforma()==null) || 
             (this.plataforma!=null &&
              this.plataforma.equals(other.getPlataforma()))) &&
            ((this.tecnologia==null && other.getTecnologia()==null) || 
             (this.tecnologia!=null &&
              this.tecnologia.equals(other.getTecnologia()))) &&
            ((this.tipoCliente==null && other.getTipoCliente()==null) || 
             (this.tipoCliente!=null &&
              this.tipoCliente.equals(other.getTipoCliente()))) &&
            ((this.canal==null && other.getCanal()==null) || 
             (this.canal!=null &&
              this.canal.equals(other.getCanal()))) &&
            ((this.SGSegmento==null && other.getSGSegmento()==null) || 
             (this.SGSegmento!=null &&
              this.SGSegmento.equals(other.getSGSegmento()))) &&
            ((this.SGCarteira==null && other.getSGCarteira()==null) || 
             (this.SGCarteira!=null &&
              this.SGCarteira.equals(other.getSGCarteira()))) &&
            ((this.uf==null && other.getUf()==null) || 
             (this.uf!=null &&
              this.uf.equals(other.getUf()))) &&
            ((this.DDD==null && other.getDDD()==null) || 
             (this.DDD!=null &&
              this.DDD.equals(other.getDDD())));
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
        if (getNmPlano() != null) {
            _hashCode += getNmPlano().hashCode();
        }
        if (getPlataforma() != null) {
            _hashCode += getPlataforma().hashCode();
        }
        if (getTecnologia() != null) {
            _hashCode += getTecnologia().hashCode();
        }
        if (getTipoCliente() != null) {
            _hashCode += getTipoCliente().hashCode();
        }
        if (getCanal() != null) {
            _hashCode += getCanal().hashCode();
        }
        if (getSGSegmento() != null) {
            _hashCode += getSGSegmento().hashCode();
        }
        if (getSGCarteira() != null) {
            _hashCode += getSGCarteira().hashCode();
        }
        if (getUf() != null) {
            _hashCode += getUf().hashCode();
        }
        if (getDDD() != null) {
            _hashCode += getDDD().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoExportarPlanoPlano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>ResultadoExportarPlano>Plano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "nmPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("plataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "Plataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "Tecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "TipoCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("canal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "Canal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SGSegmento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "SGSegmento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SGCarteira");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "SGCarteira"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "Uf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DDD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "DDD"));
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
