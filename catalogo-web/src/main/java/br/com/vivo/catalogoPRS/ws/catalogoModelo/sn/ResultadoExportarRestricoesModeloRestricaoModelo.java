/**
 * ResultadoExportarRestricoesModeloRestricaoModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoExportarRestricoesModeloRestricaoModelo  implements java.io.Serializable {
    private java.lang.String nmGrupoProduto;

    private java.lang.String nmUf;

    private java.lang.String nmTipoCliente;

    private java.lang.String sgSegmento;

    private java.lang.String sgCarteira;

    private java.lang.String nmCanal;

    public ResultadoExportarRestricoesModeloRestricaoModelo() {
    }

    public ResultadoExportarRestricoesModeloRestricaoModelo(
           java.lang.String nmGrupoProduto,
           java.lang.String nmUf,
           java.lang.String nmTipoCliente,
           java.lang.String sgSegmento,
           java.lang.String sgCarteira,
           java.lang.String nmCanal) {
           this.nmGrupoProduto = nmGrupoProduto;
           this.nmUf = nmUf;
           this.nmTipoCliente = nmTipoCliente;
           this.sgSegmento = sgSegmento;
           this.sgCarteira = sgCarteira;
           this.nmCanal = nmCanal;
    }


    /**
     * Gets the nmGrupoProduto value for this ResultadoExportarRestricoesModeloRestricaoModelo.
     * 
     * @return nmGrupoProduto
     */
    public java.lang.String getNmGrupoProduto() {
        return nmGrupoProduto;
    }


    /**
     * Sets the nmGrupoProduto value for this ResultadoExportarRestricoesModeloRestricaoModelo.
     * 
     * @param nmGrupoProduto
     */
    public void setNmGrupoProduto(java.lang.String nmGrupoProduto) {
        this.nmGrupoProduto = nmGrupoProduto;
    }


    /**
     * Gets the nmUf value for this ResultadoExportarRestricoesModeloRestricaoModelo.
     * 
     * @return nmUf
     */
    public java.lang.String getNmUf() {
        return nmUf;
    }


    /**
     * Sets the nmUf value for this ResultadoExportarRestricoesModeloRestricaoModelo.
     * 
     * @param nmUf
     */
    public void setNmUf(java.lang.String nmUf) {
        this.nmUf = nmUf;
    }


    /**
     * Gets the nmTipoCliente value for this ResultadoExportarRestricoesModeloRestricaoModelo.
     * 
     * @return nmTipoCliente
     */
    public java.lang.String getNmTipoCliente() {
        return nmTipoCliente;
    }


    /**
     * Sets the nmTipoCliente value for this ResultadoExportarRestricoesModeloRestricaoModelo.
     * 
     * @param nmTipoCliente
     */
    public void setNmTipoCliente(java.lang.String nmTipoCliente) {
        this.nmTipoCliente = nmTipoCliente;
    }


    /**
     * Gets the sgSegmento value for this ResultadoExportarRestricoesModeloRestricaoModelo.
     * 
     * @return sgSegmento
     */
    public java.lang.String getSgSegmento() {
        return sgSegmento;
    }


    /**
     * Sets the sgSegmento value for this ResultadoExportarRestricoesModeloRestricaoModelo.
     * 
     * @param sgSegmento
     */
    public void setSgSegmento(java.lang.String sgSegmento) {
        this.sgSegmento = sgSegmento;
    }


    /**
     * Gets the sgCarteira value for this ResultadoExportarRestricoesModeloRestricaoModelo.
     * 
     * @return sgCarteira
     */
    public java.lang.String getSgCarteira() {
        return sgCarteira;
    }


    /**
     * Sets the sgCarteira value for this ResultadoExportarRestricoesModeloRestricaoModelo.
     * 
     * @param sgCarteira
     */
    public void setSgCarteira(java.lang.String sgCarteira) {
        this.sgCarteira = sgCarteira;
    }


    /**
     * Gets the nmCanal value for this ResultadoExportarRestricoesModeloRestricaoModelo.
     * 
     * @return nmCanal
     */
    public java.lang.String getNmCanal() {
        return nmCanal;
    }


    /**
     * Sets the nmCanal value for this ResultadoExportarRestricoesModeloRestricaoModelo.
     * 
     * @param nmCanal
     */
    public void setNmCanal(java.lang.String nmCanal) {
        this.nmCanal = nmCanal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoExportarRestricoesModeloRestricaoModelo)) return false;
        ResultadoExportarRestricoesModeloRestricaoModelo other = (ResultadoExportarRestricoesModeloRestricaoModelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nmGrupoProduto==null && other.getNmGrupoProduto()==null) || 
             (this.nmGrupoProduto!=null &&
              this.nmGrupoProduto.equals(other.getNmGrupoProduto()))) &&
            ((this.nmUf==null && other.getNmUf()==null) || 
             (this.nmUf!=null &&
              this.nmUf.equals(other.getNmUf()))) &&
            ((this.nmTipoCliente==null && other.getNmTipoCliente()==null) || 
             (this.nmTipoCliente!=null &&
              this.nmTipoCliente.equals(other.getNmTipoCliente()))) &&
            ((this.sgSegmento==null && other.getSgSegmento()==null) || 
             (this.sgSegmento!=null &&
              this.sgSegmento.equals(other.getSgSegmento()))) &&
            ((this.sgCarteira==null && other.getSgCarteira()==null) || 
             (this.sgCarteira!=null &&
              this.sgCarteira.equals(other.getSgCarteira()))) &&
            ((this.nmCanal==null && other.getNmCanal()==null) || 
             (this.nmCanal!=null &&
              this.nmCanal.equals(other.getNmCanal())));
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
        if (getNmGrupoProduto() != null) {
            _hashCode += getNmGrupoProduto().hashCode();
        }
        if (getNmUf() != null) {
            _hashCode += getNmUf().hashCode();
        }
        if (getNmTipoCliente() != null) {
            _hashCode += getNmTipoCliente().hashCode();
        }
        if (getSgSegmento() != null) {
            _hashCode += getSgSegmento().hashCode();
        }
        if (getSgCarteira() != null) {
            _hashCode += getSgCarteira().hashCode();
        }
        if (getNmCanal() != null) {
            _hashCode += getNmCanal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoExportarRestricoesModeloRestricaoModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ResultadoExportarRestricoesModelo>RestricaoModelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmUf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTipoCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmTipoCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgSegmento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "sgSegmento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgCarteira");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "sgCarteira"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmCanal"));
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
