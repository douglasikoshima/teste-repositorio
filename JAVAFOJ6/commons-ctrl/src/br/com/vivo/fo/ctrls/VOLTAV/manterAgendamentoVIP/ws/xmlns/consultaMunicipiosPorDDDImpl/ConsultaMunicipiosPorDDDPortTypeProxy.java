package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaMunicipiosPorDDDImpl;

public class ConsultaMunicipiosPorDDDPortTypeProxy implements br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaMunicipiosPorDDDImpl.ConsultaMunicipiosPorDDDPortType {
  private String _endpoint = null;
  private br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaMunicipiosPorDDDImpl.ConsultaMunicipiosPorDDDPortType consultaMunicipiosPorDDDPortType = null;
  
  public ConsultaMunicipiosPorDDDPortTypeProxy() {
    _initConsultaMunicipiosPorDDDPortTypeProxy();
  }
  
  public ConsultaMunicipiosPorDDDPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultaMunicipiosPorDDDPortTypeProxy();
  }
  
  private void _initConsultaMunicipiosPorDDDPortTypeProxy() {
    try {
      consultaMunicipiosPorDDDPortType = (new br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaMunicipiosPorDDDImpl.SoapProcessLocator()).getSoapProcessHttpPort();
      if (consultaMunicipiosPorDDDPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaMunicipiosPorDDDPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaMunicipiosPorDDDPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaMunicipiosPorDDDPortType != null)
      ((javax.xml.rpc.Stub)consultaMunicipiosPorDDDPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaMunicipiosPorDDDImpl.ConsultaMunicipiosPorDDDPortType getConsultaMunicipiosPorDDDPortType() {
    if (consultaMunicipiosPorDDDPortType == null)
      _initConsultaMunicipiosPorDDDPortTypeProxy();
    return consultaMunicipiosPorDDDPortType;
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaMunicipiosPorDDD.output.ConsultaMunicipiosPorDDDOutputType consultaMunicipiosPorDDD(br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaMunicipiosPorDDD.input.ConsultaMunicipiosPorDDDInputType input) throws java.rmi.RemoteException{
    if (consultaMunicipiosPorDDDPortType == null)
      _initConsultaMunicipiosPorDDDPortTypeProxy();
    return consultaMunicipiosPorDDDPortType.consultaMunicipiosPorDDD(input);
  }
  
  
}