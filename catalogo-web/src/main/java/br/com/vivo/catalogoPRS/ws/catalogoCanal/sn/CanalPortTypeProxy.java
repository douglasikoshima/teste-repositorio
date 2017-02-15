package br.com.vivo.catalogoPRS.ws.catalogoCanal.sn;

public class CanalPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.CanalPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.CanalPortType canalPortType = null;
  
  public CanalPortTypeProxy() {
    _initCanalPortTypeProxy();
  }
  
  public CanalPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initCanalPortTypeProxy();
  }
  
  private void _initCanalPortTypeProxy() {
    try {
      canalPortType = (new br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.CanalServiceLocator()).getCanalSoapPort();
      if (canalPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)canalPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)canalPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (canalPortType != null)
      ((javax.xml.rpc.Stub)canalPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.CanalPortType getCanalPortType() {
    if (canalPortType == null)
      _initCanalPortTypeProxy();
    return canalPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.BuscarListaCanalResponse buscarListaCanal(br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.BuscarListaCanalRequest buscarListaCanalRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanal.mc.geral.ErroInfo{
    if (canalPortType == null)
      _initCanalPortTypeProxy();
    return canalPortType.buscarListaCanal(buscarListaCanalRequest, username, password);
  }
  
  
}