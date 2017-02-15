package br.com.vivo.catalogoPRS.ws.catalogoAcao.sn;

public class AcaoPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.AcaoPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.AcaoPortType acaoPortType = null;
  
  public AcaoPortTypeProxy() {
    _initAcaoPortTypeProxy();
  }
  
  public AcaoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initAcaoPortTypeProxy();
  }
  
  private void _initAcaoPortTypeProxy() {
    try {
      acaoPortType = (new br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.AcaoSoapServiceLocator()).getAcaoSOAP();
      if (acaoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)acaoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)acaoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (acaoPortType != null)
      ((javax.xml.rpc.Stub)acaoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.AcaoPortType getAcaoPortType() {
    if (acaoPortType == null)
      _initAcaoPortTypeProxy();
    return acaoPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.BuscarListaAcaoResponse buscarListaAcao(br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.BuscarListaAcaoRequest buscarListaAcaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcao.mc.geral.ErroInfo{
    if (acaoPortType == null)
      _initAcaoPortTypeProxy();
    return acaoPortType.buscarListaAcao(buscarListaAcaoRequest, username, password);
  }
  
  
}