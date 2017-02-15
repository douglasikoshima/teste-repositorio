/**
 * ConsultaAgendamentoPorLinhaOutputType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.output;

public class ConsultaAgendamentoPorLinhaOutputType  implements java.io.Serializable {
    private java.lang.String status;

    private java.lang.String errorCode;

    private java.lang.String errorDesc;

    private java.lang.String legacyCode;

    private java.lang.String legacyDomain;

    private java.lang.String ipOrigem;

    private br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.output.AgendamentoType[] agendamentos;

    public ConsultaAgendamentoPorLinhaOutputType() {
    }

    public ConsultaAgendamentoPorLinhaOutputType(
           java.lang.String status,
           java.lang.String errorCode,
           java.lang.String errorDesc,
           java.lang.String legacyCode,
           java.lang.String legacyDomain,
           java.lang.String ipOrigem,
           br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.output.AgendamentoType[] agendamentos) {
           this.status = status;
           this.errorCode = errorCode;
           this.errorDesc = errorDesc;
           this.legacyCode = legacyCode;
           this.legacyDomain = legacyDomain;
           this.ipOrigem = ipOrigem;
           this.agendamentos = agendamentos;
    }


    /**
     * Gets the status value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the errorCode value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @return errorCode
     */
    public java.lang.String getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.lang.String errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the errorDesc value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @return errorDesc
     */
    public java.lang.String getErrorDesc() {
        return errorDesc;
    }


    /**
     * Sets the errorDesc value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @param errorDesc
     */
    public void setErrorDesc(java.lang.String errorDesc) {
        this.errorDesc = errorDesc;
    }


    /**
     * Gets the legacyCode value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @return legacyCode
     */
    public java.lang.String getLegacyCode() {
        return legacyCode;
    }


    /**
     * Sets the legacyCode value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @param legacyCode
     */
    public void setLegacyCode(java.lang.String legacyCode) {
        this.legacyCode = legacyCode;
    }


    /**
     * Gets the legacyDomain value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @return legacyDomain
     */
    public java.lang.String getLegacyDomain() {
        return legacyDomain;
    }


    /**
     * Sets the legacyDomain value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @param legacyDomain
     */
    public void setLegacyDomain(java.lang.String legacyDomain) {
        this.legacyDomain = legacyDomain;
    }


    /**
     * Gets the ipOrigem value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @return ipOrigem
     */
    public java.lang.String getIpOrigem() {
        return ipOrigem;
    }


    /**
     * Sets the ipOrigem value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @param ipOrigem
     */
    public void setIpOrigem(java.lang.String ipOrigem) {
        this.ipOrigem = ipOrigem;
    }


    /**
     * Gets the agendamentos value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @return agendamentos
     */
    public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.output.AgendamentoType[] getAgendamentos() {
        return agendamentos;
    }


    /**
     * Sets the agendamentos value for this ConsultaAgendamentoPorLinhaOutputType.
     * 
     * @param agendamentos
     */
    public void setAgendamentos(br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.output.AgendamentoType[] agendamentos) {
        this.agendamentos = agendamentos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaAgendamentoPorLinhaOutputType)) return false;
        ConsultaAgendamentoPorLinhaOutputType other = (ConsultaAgendamentoPorLinhaOutputType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.errorDesc==null && other.getErrorDesc()==null) || 
             (this.errorDesc!=null &&
              this.errorDesc.equals(other.getErrorDesc()))) &&
            ((this.legacyCode==null && other.getLegacyCode()==null) || 
             (this.legacyCode!=null &&
              this.legacyCode.equals(other.getLegacyCode()))) &&
            ((this.legacyDomain==null && other.getLegacyDomain()==null) || 
             (this.legacyDomain!=null &&
              this.legacyDomain.equals(other.getLegacyDomain()))) &&
            ((this.ipOrigem==null && other.getIpOrigem()==null) || 
             (this.ipOrigem!=null &&
              this.ipOrigem.equals(other.getIpOrigem()))) &&
            ((this.agendamentos==null && other.getAgendamentos()==null) || 
             (this.agendamentos!=null &&
              java.util.Arrays.equals(this.agendamentos, other.getAgendamentos())));
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
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getErrorDesc() != null) {
            _hashCode += getErrorDesc().hashCode();
        }
        if (getLegacyCode() != null) {
            _hashCode += getLegacyCode().hashCode();
        }
        if (getLegacyDomain() != null) {
            _hashCode += getLegacyDomain().hashCode();
        }
        if (getIpOrigem() != null) {
            _hashCode += getIpOrigem().hashCode();
        }
        if (getAgendamentos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAgendamentos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAgendamentos(), i);
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
        new org.apache.axis.description.TypeDesc(ConsultaAgendamentoPorLinhaOutputType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaAgendamentoPorLinha/output", "consultaAgendamentoPorLinhaOutputType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaAgendamentoPorLinha/output", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaAgendamentoPorLinha/output", "errorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaAgendamentoPorLinha/output", "errorDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("legacyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaAgendamentoPorLinha/output", "legacyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("legacyDomain");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaAgendamentoPorLinha/output", "legacyDomain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipOrigem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaAgendamentoPorLinha/output", "ipOrigem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agendamentos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaAgendamentoPorLinha/output", "agendamentos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaAgendamentoPorLinha/output", "agendamentoType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaAgendamentoPorLinha/output", "agendamento"));
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
