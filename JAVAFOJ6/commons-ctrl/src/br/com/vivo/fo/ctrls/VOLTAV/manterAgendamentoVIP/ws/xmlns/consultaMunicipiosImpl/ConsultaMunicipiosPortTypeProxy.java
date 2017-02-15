package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaMunicipiosImpl;

public class ConsultaMunicipiosPortTypeProxy implements br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaMunicipiosImpl.ConsultaMunicipiosPortType {
  private String _endpoint = null;
  private br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaMunicipiosImpl.ConsultaMunicipiosPortType consultaMunicipiosPortType = null;
  
  public ConsultaMunicipiosPortTypeProxy() {
    _initConsultaMunicipiosPortTypeProxy();
  }
  
  public ConsultaMunicipiosPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultaMunicipiosPortTypeProxy();
  }
  
  private void _initConsultaMunicipiosPortTypeProxy() {
    try {
      consultaMunicipiosPortType = (new br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaMunicipiosImpl.SoapProcessLocator()).getSoapProcessHttpPort();
      if (consultaMunicipiosPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaMunicipiosPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaMunicipiosPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaMunicipiosPortType != null)
      ((javax.xml.rpc.Stub)consultaMunicipiosPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaMunicipiosImpl.ConsultaMunicipiosPortType getConsultaMunicipiosPortType() {
    if (consultaMunicipiosPortType == null)
      _initConsultaMunicipiosPortTypeProxy();
    return consultaMunicipiosPortType;
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaMunicipios.output.ConsultaMunicipiosOutputType consultaMunicipios(br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaMunicipios.input.ConsultaMunicipiosInputType input) throws java.rmi.RemoteException{
    if (consultaMunicipiosPortType == null)
      _initConsultaMunicipiosPortTypeProxy();
    return consultaMunicipiosPortType.consultaMunicipios(input);
  }
  
  
}