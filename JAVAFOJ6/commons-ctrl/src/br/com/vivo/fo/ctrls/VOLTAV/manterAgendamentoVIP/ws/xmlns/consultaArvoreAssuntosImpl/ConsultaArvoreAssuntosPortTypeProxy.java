package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaArvoreAssuntosImpl;

public class ConsultaArvoreAssuntosPortTypeProxy implements br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaArvoreAssuntosImpl.ConsultaArvoreAssuntosPortType {
  private String _endpoint = null;
  private br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaArvoreAssuntosImpl.ConsultaArvoreAssuntosPortType consultaArvoreAssuntosPortType = null;
  
  public ConsultaArvoreAssuntosPortTypeProxy() {
    _initConsultaArvoreAssuntosPortTypeProxy();
  }
  
  public ConsultaArvoreAssuntosPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultaArvoreAssuntosPortTypeProxy();
  }
  
  private void _initConsultaArvoreAssuntosPortTypeProxy() {
    try {
      consultaArvoreAssuntosPortType = (new br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaArvoreAssuntosImpl.SoapProcessLocator()).getSoapProcessHttpPort();
      if (consultaArvoreAssuntosPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaArvoreAssuntosPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaArvoreAssuntosPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaArvoreAssuntosPortType != null)
      ((javax.xml.rpc.Stub)consultaArvoreAssuntosPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaArvoreAssuntosImpl.ConsultaArvoreAssuntosPortType getConsultaArvoreAssuntosPortType() {
    if (consultaArvoreAssuntosPortType == null)
      _initConsultaArvoreAssuntosPortTypeProxy();
    return consultaArvoreAssuntosPortType;
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaArvoreAssuntos.output.ConsultaArvoreAssuntosOutputType consultaArvoreAssuntos(br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaArvoreAssuntos.input.ConsultaArvoreAssuntosInputType input) throws java.rmi.RemoteException{
    if (consultaArvoreAssuntosPortType == null)
      _initConsultaArvoreAssuntosPortTypeProxy();
    return consultaArvoreAssuntosPortType.consultaArvoreAssuntos(input);
  }
  
  
}