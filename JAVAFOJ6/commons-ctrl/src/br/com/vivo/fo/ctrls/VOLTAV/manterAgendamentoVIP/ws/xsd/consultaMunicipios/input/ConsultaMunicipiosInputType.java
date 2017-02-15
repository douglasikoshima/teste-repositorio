/**
 * ConsultaMunicipiosInputType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaMunicipios.input;

public class ConsultaMunicipiosInputType  implements java.io.Serializable {
    private java.lang.String ipOrigem;

    private java.lang.String legacyDomain;

    private java.lang.String legacyName;

    private java.lang.String login;

    private java.lang.String password;

    private java.lang.Long timeOut;

    private java.lang.String trackingId;

    private java.lang.String uf;

    public ConsultaMunicipiosInputType() {
    }

    public ConsultaMunicipiosInputType(
           java.lang.String ipOrigem,
           java.lang.String legacyDomain,
           java.lang.String legacyName,
           java.lang.String login,
           java.lang.String password,
           java.lang.Long timeOut,
           java.lang.String trackingId,
           java.lang.String uf) {
           this.ipOrigem = ipOrigem;
           this.legacyDomain = legacyDomain;
           this.legacyName = legacyName;
           this.login = login;
           this.password = password;
           this.timeOut = timeOut;
           this.trackingId = trackingId;
           this.uf = uf;
    }


    /**
     * Gets the ipOrigem value for this ConsultaMunicipiosInputType.
     * 
     * @return ipOrigem
     */
    public java.lang.String getIpOrigem() {
        return ipOrigem;
    }


    /**
     * Sets the ipOrigem value for this ConsultaMunicipiosInputType.
     * 
     * @param ipOrigem
     */
    public void setIpOrigem(java.lang.String ipOrigem) {
        this.ipOrigem = ipOrigem;
    }


    /**
     * Gets the legacyDomain value for this ConsultaMunicipiosInputType.
     * 
     * @return legacyDomain
     */
    public java.lang.String getLegacyDomain() {
        return legacyDomain;
    }


    /**
     * Sets the legacyDomain value for this ConsultaMunicipiosInputType.
     * 
     * @param legacyDomain
     */
    public void setLegacyDomain(java.lang.String legacyDomain) {
        this.legacyDomain = legacyDomain;
    }


    /**
     * Gets the legacyName value for this ConsultaMunicipiosInputType.
     * 
     * @return legacyName
     */
    public java.lang.String getLegacyName() {
        return legacyName;
    }


    /**
     * Sets the legacyName value for this ConsultaMunicipiosInputType.
     * 
     * @param legacyName
     */
    public void setLegacyName(java.lang.String legacyName) {
        this.legacyName = legacyName;
    }


    /**
     * Gets the login value for this ConsultaMunicipiosInputType.
     * 
     * @return login
     */
    public java.lang.String getLogin() {
        return login;
    }


    /**
     * Sets the login value for this ConsultaMunicipiosInputType.
     * 
     * @param login
     */
    public void setLogin(java.lang.String login) {
        this.login = login;
    }


    /**
     * Gets the password value for this ConsultaMunicipiosInputType.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this ConsultaMunicipiosInputType.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the timeOut value for this ConsultaMunicipiosInputType.
     * 
     * @return timeOut
     */
    public java.lang.Long getTimeOut() {
        return timeOut;
    }


    /**
     * Sets the timeOut value for this ConsultaMunicipiosInputType.
     * 
     * @param timeOut
     */
    public void setTimeOut(java.lang.Long timeOut) {
        this.timeOut = timeOut;
    }


    /**
     * Gets the trackingId value for this ConsultaMunicipiosInputType.
     * 
     * @return trackingId
     */
    public java.lang.String getTrackingId() {
        return trackingId;
    }


    /**
     * Sets the trackingId value for this ConsultaMunicipiosInputType.
     * 
     * @param trackingId
     */
    public void setTrackingId(java.lang.String trackingId) {
        this.trackingId = trackingId;
    }


    /**
     * Gets the uf value for this ConsultaMunicipiosInputType.
     * 
     * @return uf
     */
    public java.lang.String getUf() {
        return uf;
    }


    /**
     * Sets the uf value for this ConsultaMunicipiosInputType.
     * 
     * @param uf
     */
    public void setUf(java.lang.String uf) {
        this.uf = uf;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaMunicipiosInputType)) return false;
        ConsultaMunicipiosInputType other = (ConsultaMunicipiosInputType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ipOrigem==null && other.getIpOrigem()==null) || 
             (this.ipOrigem!=null &&
              this.ipOrigem.equals(other.getIpOrigem()))) &&
            ((this.legacyDomain==null && other.getLegacyDomain()==null) || 
             (this.legacyDomain!=null &&
              this.legacyDomain.equals(other.getLegacyDomain()))) &&
            ((this.legacyName==null && other.getLegacyName()==null) || 
             (this.legacyName!=null &&
              this.legacyName.equals(other.getLegacyName()))) &&
            ((this.login==null && other.getLogin()==null) || 
             (this.login!=null &&
              this.login.equals(other.getLogin()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.timeOut==null && other.getTimeOut()==null) || 
             (this.timeOut!=null &&
              this.timeOut.equals(other.getTimeOut()))) &&
            ((this.trackingId==null && other.getTrackingId()==null) || 
             (this.trackingId!=null &&
              this.trackingId.equals(other.getTrackingId()))) &&
            ((this.uf==null && other.getUf()==null) || 
             (this.uf!=null &&
              this.uf.equals(other.getUf())));
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
        if (getIpOrigem() != null) {
            _hashCode += getIpOrigem().hashCode();
        }
        if (getLegacyDomain() != null) {
            _hashCode += getLegacyDomain().hashCode();
        }
        if (getLegacyName() != null) {
            _hashCode += getLegacyName().hashCode();
        }
        if (getLogin() != null) {
            _hashCode += getLogin().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getTimeOut() != null) {
            _hashCode += getTimeOut().hashCode();
        }
        if (getTrackingId() != null) {
            _hashCode += getTrackingId().hashCode();
        }
        if (getUf() != null) {
            _hashCode += getUf().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaMunicipiosInputType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaMunicipios/input", "consultaMunicipiosInputType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipOrigem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaMunicipios/input", "ipOrigem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("legacyDomain");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaMunicipios/input", "legacyDomain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("legacyName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaMunicipios/input", "legacyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("login");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaMunicipios/input", "login"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaMunicipios/input", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaMunicipios/input", "timeOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaMunicipios/input", "trackingId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://br.com.vivo.eai/xsd/consultaMunicipios/input", "uf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
