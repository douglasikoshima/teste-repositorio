package br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn;

public class CarteiraPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.CarteiraPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.CarteiraPortType carteiraPortType = null;
  
  public CarteiraPortTypeProxy() {
    _initCarteiraPortTypeProxy();
  }
  
  public CarteiraPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initCarteiraPortTypeProxy();
  }
  
  private void _initCarteiraPortTypeProxy() {
    try {
      carteiraPortType = (new br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.CarteiraServiceLocator()).getCarteiraSoapPort();
      if (carteiraPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)carteiraPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)carteiraPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (carteiraPortType != null)
      ((javax.xml.rpc.Stub)carteiraPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.CarteiraPortType getCarteiraPortType() {
    if (carteiraPortType == null)
      _initCarteiraPortTypeProxy();
    return carteiraPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.BuscarListaCarteiraResponse buscarListaCarteira(br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.BuscarListaCarteiraRequest buscarListaCarteiraRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.mc.geral.ErroInfo{
    if (carteiraPortType == null)
      _initCarteiraPortTypeProxy();
    return carteiraPortType.buscarListaCarteira(buscarListaCarteiraRequest, username, password);
  }
  
  
}