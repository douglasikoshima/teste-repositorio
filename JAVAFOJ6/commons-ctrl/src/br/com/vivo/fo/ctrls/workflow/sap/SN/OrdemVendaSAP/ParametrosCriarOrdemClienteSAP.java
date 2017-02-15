/**
 * ParametrosCriarOrdemClienteSAP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP;

public class ParametrosCriarOrdemClienteSAP  implements java.io.Serializable {
    private br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPCdTipoOperacao cdTipoOperacao;

    private br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPClienteOrdem[] clienteOrdem;

    private br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPCabecalhoOrdem[] cabecalhoOrdem;

    private br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPElementoOrdem[] elementoOrdem;

    public ParametrosCriarOrdemClienteSAP() {
    }

    public ParametrosCriarOrdemClienteSAP(
           br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPCdTipoOperacao cdTipoOperacao,
           br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPClienteOrdem[] clienteOrdem,
           br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPCabecalhoOrdem[] cabecalhoOrdem,
           br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPElementoOrdem[] elementoOrdem) {
           this.cdTipoOperacao = cdTipoOperacao;
           this.clienteOrdem = clienteOrdem;
           this.cabecalhoOrdem = cabecalhoOrdem;
           this.elementoOrdem = elementoOrdem;
    }


    /**
     * Gets the cdTipoOperacao value for this ParametrosCriarOrdemClienteSAP.
     * 
     * @return cdTipoOperacao
     */
    public br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPCdTipoOperacao getCdTipoOperacao() {
        return cdTipoOperacao;
    }


    /**
     * Sets the cdTipoOperacao value for this ParametrosCriarOrdemClienteSAP.
     * 
     * @param cdTipoOperacao
     */
    public void setCdTipoOperacao(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPCdTipoOperacao cdTipoOperacao) {
        this.cdTipoOperacao = cdTipoOperacao;
    }


    /**
     * Gets the clienteOrdem value for this ParametrosCriarOrdemClienteSAP.
     * 
     * @return clienteOrdem
     */
    public br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPClienteOrdem[] getClienteOrdem() {
        return clienteOrdem;
    }


    /**
     * Sets the clienteOrdem value for this ParametrosCriarOrdemClienteSAP.
     * 
     * @param clienteOrdem
     */
    public void setClienteOrdem(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPClienteOrdem[] clienteOrdem) {
        this.clienteOrdem = clienteOrdem;
    }

    public br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPClienteOrdem getClienteOrdem(int i) {
        return this.clienteOrdem[i];
    }

    public void setClienteOrdem(int i, br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPClienteOrdem _value) {
        this.clienteOrdem[i] = _value;
    }


    /**
     * Gets the cabecalhoOrdem value for this ParametrosCriarOrdemClienteSAP.
     * 
     * @return cabecalhoOrdem
     */
    public br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPCabecalhoOrdem[] getCabecalhoOrdem() {
        return cabecalhoOrdem;
    }


    /**
     * Sets the cabecalhoOrdem value for this ParametrosCriarOrdemClienteSAP.
     * 
     * @param cabecalhoOrdem
     */
    public void setCabecalhoOrdem(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPCabecalhoOrdem[] cabecalhoOrdem) {
        this.cabecalhoOrdem = cabecalhoOrdem;
    }

    public br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPCabecalhoOrdem getCabecalhoOrdem(int i) {
        return this.cabecalhoOrdem[i];
    }

    public void setCabecalhoOrdem(int i, br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPCabecalhoOrdem _value) {
        this.cabecalhoOrdem[i] = _value;
    }


    /**
     * Gets the elementoOrdem value for this ParametrosCriarOrdemClienteSAP.
     * 
     * @return elementoOrdem
     */
    public br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPElementoOrdem[] getElementoOrdem() {
        return elementoOrdem;
    }


    /**
     * Sets the elementoOrdem value for this ParametrosCriarOrdemClienteSAP.
     * 
     * @param elementoOrdem
     */
    public void setElementoOrdem(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPElementoOrdem[] elementoOrdem) {
        this.elementoOrdem = elementoOrdem;
    }

    public br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPElementoOrdem getElementoOrdem(int i) {
        return this.elementoOrdem[i];
    }

    public void setElementoOrdem(int i, br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAPElementoOrdem _value) {
        this.elementoOrdem[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosCriarOrdemClienteSAP)) return false;
        ParametrosCriarOrdemClienteSAP other = (ParametrosCriarOrdemClienteSAP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cdTipoOperacao==null && other.getCdTipoOperacao()==null) || 
             (this.cdTipoOperacao!=null &&
              this.cdTipoOperacao.equals(other.getCdTipoOperacao()))) &&
            ((this.clienteOrdem==null && other.getClienteOrdem()==null) || 
             (this.clienteOrdem!=null &&
              java.util.Arrays.equals(this.clienteOrdem, other.getClienteOrdem()))) &&
            ((this.cabecalhoOrdem==null && other.getCabecalhoOrdem()==null) || 
             (this.cabecalhoOrdem!=null &&
              java.util.Arrays.equals(this.cabecalhoOrdem, other.getCabecalhoOrdem()))) &&
            ((this.elementoOrdem==null && other.getElementoOrdem()==null) || 
             (this.elementoOrdem!=null &&
              java.util.Arrays.equals(this.elementoOrdem, other.getElementoOrdem())));
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
        if (getCdTipoOperacao() != null) {
            _hashCode += getCdTipoOperacao().hashCode();
        }
        if (getClienteOrdem() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getClienteOrdem());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getClienteOrdem(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCabecalhoOrdem() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCabecalhoOrdem());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCabecalhoOrdem(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getElementoOrdem() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getElementoOrdem());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getElementoOrdem(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosCriarOrdemClienteSAP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "ParametrosCriarOrdemClienteSAP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdTipoOperacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "cdTipoOperacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", ">ParametrosCriarOrdemClienteSAP>cdTipoOperacao"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clienteOrdem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "clienteOrdem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", ">ParametrosCriarOrdemClienteSAP>clienteOrdem"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cabecalhoOrdem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "cabecalhoOrdem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", ">ParametrosCriarOrdemClienteSAP>cabecalhoOrdem"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementoOrdem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", "elementoOrdem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/OrdemVendaSAP", ">ParametrosCriarOrdemClienteSAP>elementoOrdem"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
