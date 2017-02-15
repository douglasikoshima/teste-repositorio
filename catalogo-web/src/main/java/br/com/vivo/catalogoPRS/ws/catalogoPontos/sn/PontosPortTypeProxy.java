package br.com.vivo.catalogoPRS.ws.catalogoPontos.sn;

public class PontosPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.PontosPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.PontosPortType pontosPortType = null;
  
  public PontosPortTypeProxy() {
    _initPontosPortTypeProxy();
  }
  
  public PontosPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initPontosPortTypeProxy();
  }
  
  private void _initPontosPortTypeProxy() {
    try {
      pontosPortType = (new br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.PontosBindingQSServiceLocator()).getPontosBindingQSPort();
      if (pontosPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)pontosPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)pontosPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (pontosPortType != null)
      ((javax.xml.rpc.Stub)pontosPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.PontosPortType getPontosPortType() {
    if (pontosPortType == null)
      _initPontosPortTypeProxy();
    return pontosPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.ResultadoBuscarAcaoMarketing buscarAcaoMarketing(br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.ParametrosBuscarAcaoMarketing buscarAcaoMarketingRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPontos.mc.geral.ErroInfo{
    if (pontosPortType == null)
      _initPontosPortTypeProxy();
    return pontosPortType.buscarAcaoMarketing(buscarAcaoMarketingRequest, username, password);
  }
  
  
}