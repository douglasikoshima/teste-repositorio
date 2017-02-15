package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendamentosPorLinhaImpl;

public class ConsultaAgendamentosPorLinhaPortTypeProxy implements br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendamentosPorLinhaImpl.ConsultaAgendamentosPorLinhaPortType {
  private String _endpoint = null;
  private br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendamentosPorLinhaImpl.ConsultaAgendamentosPorLinhaPortType consultaAgendamentosPorLinhaPortType = null;
  
  public ConsultaAgendamentosPorLinhaPortTypeProxy() {
    _initConsultaAgendamentosPorLinhaPortTypeProxy();
  }
  
  public ConsultaAgendamentosPorLinhaPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultaAgendamentosPorLinhaPortTypeProxy();
  }
  
  private void _initConsultaAgendamentosPorLinhaPortTypeProxy() {
    try {
      consultaAgendamentosPorLinhaPortType = (new br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendamentosPorLinhaImpl.SoapProcessLocator()).getSoapProcessHttpPort();
      if (consultaAgendamentosPorLinhaPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaAgendamentosPorLinhaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaAgendamentosPorLinhaPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaAgendamentosPorLinhaPortType != null)
      ((javax.xml.rpc.Stub)consultaAgendamentosPorLinhaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendamentosPorLinhaImpl.ConsultaAgendamentosPorLinhaPortType getConsultaAgendamentosPorLinhaPortType() {
    if (consultaAgendamentosPorLinhaPortType == null)
      _initConsultaAgendamentosPorLinhaPortTypeProxy();
    return consultaAgendamentosPorLinhaPortType;
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.output.ConsultaAgendamentoPorLinhaOutputType consultaAgendamentosPorLinha(br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.input.ConsultaAgendamentoPorLinhaInputType input) throws java.rmi.RemoteException{
    if (consultaAgendamentosPorLinhaPortType == null)
      _initConsultaAgendamentosPorLinhaPortTypeProxy();
    return consultaAgendamentosPorLinhaPortType.consultaAgendamentosPorLinha(input);
  }
  
  
}