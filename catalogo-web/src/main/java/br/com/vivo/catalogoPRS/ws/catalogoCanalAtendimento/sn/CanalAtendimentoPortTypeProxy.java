package br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn;

public class CanalAtendimentoPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.CanalAtendimentoPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.CanalAtendimentoPortType canalAtendimentoPortType = null;
  
  public CanalAtendimentoPortTypeProxy() {
    _initCanalAtendimentoPortTypeProxy();
  }
  
  public CanalAtendimentoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initCanalAtendimentoPortTypeProxy();
  }
  
  private void _initCanalAtendimentoPortTypeProxy() {
    try {
      canalAtendimentoPortType = (new br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.CanalAtendimentoBindingQSServiceLocator()).getCanalAtendimentoBindingQSPort();
      if (canalAtendimentoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)canalAtendimentoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)canalAtendimentoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (canalAtendimentoPortType != null)
      ((javax.xml.rpc.Stub)canalAtendimentoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.CanalAtendimentoPortType getCanalAtendimentoPortType() {
    if (canalAtendimentoPortType == null)
      _initCanalAtendimentoPortTypeProxy();
    return canalAtendimentoPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.ResultadoBuscarCanalAtendimentoCanalAtendimento[] buscarCanalAtendimento(br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.ParametroBuscarCanalAtendimento buscarCanalAtendimentoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.mc.geral.ErroInfo{
    if (canalAtendimentoPortType == null)
      _initCanalAtendimentoPortTypeProxy();
    return canalAtendimentoPortType.buscarCanalAtendimento(buscarCanalAtendimentoRequest, username, password);
  }
  
  
}