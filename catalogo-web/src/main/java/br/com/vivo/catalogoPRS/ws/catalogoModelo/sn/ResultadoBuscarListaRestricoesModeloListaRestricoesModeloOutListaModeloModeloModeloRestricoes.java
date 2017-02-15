/**
 * ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes  implements java.io.Serializable {
    private long idModelo;

    private java.lang.String sgCarteira;

    private java.lang.String sgSegmento;

    private java.lang.String nmUf;

    private java.lang.String nmCanal;

    private java.lang.String nmTipoCliente;

    public ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes() {
    }

    public ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes(
           long idModelo,
           java.lang.String sgCarteira,
           java.lang.String sgSegmento,
           java.lang.String nmUf,
           java.lang.String nmCanal,
           java.lang.String nmTipoCliente) {
           this.idModelo = idModelo;
           this.sgCarteira = sgCarteira;
           this.sgSegmento = sgSegmento;
           this.nmUf = nmUf;
           this.nmCanal = nmCanal;
           this.nmTipoCliente = nmTipoCliente;
    }


    /**
     * Gets the idModelo value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.
     * 
     * @return idModelo
     */
    public long getIdModelo() {
        return idModelo;
    }


    /**
     * Sets the idModelo value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.
     * 
     * @param idModelo
     */
    public void setIdModelo(long idModelo) {
        this.idModelo = idModelo;
    }


    /**
     * Gets the sgCarteira value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.
     * 
     * @return sgCarteira
     */
    public java.lang.String getSgCarteira() {
        return sgCarteira;
    }


    /**
     * Sets the sgCarteira value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.
     * 
     * @param sgCarteira
     */
    public void setSgCarteira(java.lang.String sgCarteira) {
        this.sgCarteira = sgCarteira;
    }


    /**
     * Gets the sgSegmento value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.
     * 
     * @return sgSegmento
     */
    public java.lang.String getSgSegmento() {
        return sgSegmento;
    }


    /**
     * Sets the sgSegmento value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.
     * 
     * @param sgSegmento
     */
    public void setSgSegmento(java.lang.String sgSegmento) {
        this.sgSegmento = sgSegmento;
    }


    /**
     * Gets the nmUf value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.
     * 
     * @return nmUf
     */
    public java.lang.String getNmUf() {
        return nmUf;
    }


    /**
     * Sets the nmUf value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.
     * 
     * @param nmUf
     */
    public void setNmUf(java.lang.String nmUf) {
        this.nmUf = nmUf;
    }


    /**
     * Gets the nmCanal value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.
     * 
     * @return nmCanal
     */
    public java.lang.String getNmCanal() {
        return nmCanal;
    }


    /**
     * Sets the nmCanal value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.
     * 
     * @param nmCanal
     */
    public void setNmCanal(java.lang.String nmCanal) {
        this.nmCanal = nmCanal;
    }


    /**
     * Gets the nmTipoCliente value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.
     * 
     * @return nmTipoCliente
     */
    public java.lang.String getNmTipoCliente() {
        return nmTipoCliente;
    }


    /**
     * Sets the nmTipoCliente value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.
     * 
     * @param nmTipoCliente
     */
    public void setNmTipoCliente(java.lang.String nmTipoCliente) {
        this.nmTipoCliente = nmTipoCliente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes)) return false;
        ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes other = (ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idModelo == other.getIdModelo() &&
            ((this.sgCarteira==null && other.getSgCarteira()==null) || 
             (this.sgCarteira!=null &&
              this.sgCarteira.equals(other.getSgCarteira()))) &&
            ((this.sgSegmento==null && other.getSgSegmento()==null) || 
             (this.sgSegmento!=null &&
              this.sgSegmento.equals(other.getSgSegmento()))) &&
            ((this.nmUf==null && other.getNmUf()==null) || 
             (this.nmUf!=null &&
              this.nmUf.equals(other.getNmUf()))) &&
            ((this.nmCanal==null && other.getNmCanal()==null) || 
             (this.nmCanal!=null &&
              this.nmCanal.equals(other.getNmCanal()))) &&
            ((this.nmTipoCliente==null && other.getNmTipoCliente()==null) || 
             (this.nmTipoCliente!=null &&
              this.nmTipoCliente.equals(other.getNmTipoCliente())));
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
        _hashCode += new Long(getIdModelo()).hashCode();
        if (getSgCarteira() != null) {
            _hashCode += getSgCarteira().hashCode();
        }
        if (getSgSegmento() != null) {
            _hashCode += getSgSegmento().hashCode();
        }
        if (getNmUf() != null) {
            _hashCode += getNmUf().hashCode();
        }
        if (getNmCanal() != null) {
            _hashCode += getNmCanal().hashCode();
        }
        if (getNmTipoCliente() != null) {
            _hashCode += getNmTipoCliente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModeloModeloRestricoes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>>ResultadoBuscarListaRestricoesModelo>ListaRestricoesModeloOut>ListaModelo>Modelo>ModeloRestricoes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgCarteira");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "sgCarteira"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgSegmento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "sgSegmento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("nmCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmCanal"));
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
