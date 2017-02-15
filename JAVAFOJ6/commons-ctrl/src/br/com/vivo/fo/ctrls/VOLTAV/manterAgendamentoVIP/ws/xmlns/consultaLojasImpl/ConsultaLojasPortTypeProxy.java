package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaLojasImpl;

public class ConsultaLojasPortTypeProxy implements br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaLojasImpl.ConsultaLojasPortType {
  private String _endpoint = null;
  private br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaLojasImpl.ConsultaLojasPortType consultaLojasPortType = null;
  
  public ConsultaLojasPortTypeProxy() {
    _initConsultaLojasPortTypeProxy();
  }
  
  public ConsultaLojasPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultaLojasPortTypeProxy();
  }
  
  private void _initConsultaLojasPortTypeProxy() {
    try {
      consultaLojasPortType = (new br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaLojasImpl.SoapProcessLocator()).getSoapProcessHttpPort();
      if (consultaLojasPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaLojasPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaLojasPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaLojasPortType != null)
      ((javax.xml.rpc.Stub)consultaLojasPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaLojasImpl.ConsultaLojasPortType getConsultaLojasPortType() {
    if (consultaLojasPortType == null)
      _initConsultaLojasPortTypeProxy();
    return consultaLojasPortType;
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaLojas.output.ConsultaLojasOutputType consultaLojas(br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaLojas.input.ConsultaLojasInputType input) throws java.rmi.RemoteException{
    if (consultaLojasPortType == null)
      _initConsultaLojasPortTypeProxy();
    return consultaLojasPortType.consultaLojas(input);
  }
  
  
}