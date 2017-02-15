package br.com.vivo.catalogoPRS.ws.catalogoUF.sn;

public class UFPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoUF.sn.UFPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoUF.sn.UFPortType uFPortType = null;
  
  public UFPortTypeProxy() {
    _initUFPortTypeProxy();
  }
  
  public UFPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initUFPortTypeProxy();
  }
  
  private void _initUFPortTypeProxy() {
    try {
      uFPortType = (new br.com.vivo.catalogoPRS.ws.catalogoUF.sn.UFSoapServiceLocator()).getUFSoapPort();
      if (uFPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)uFPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)uFPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (uFPortType != null)
      ((javax.xml.rpc.Stub)uFPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoUF.sn.UFPortType getUFPortType() {
    if (uFPortType == null)
      _initUFPortTypeProxy();
    return uFPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFPorIdPlanoResponse buscarListaUFPorIdPlano(br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFPorIdPlanoRequest buscarListaUFPorIdPlanoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoUF.mc.geral.ErroInfo{
    if (uFPortType == null)
      _initUFPortTypeProxy();
    return uFPortType.buscarListaUFPorIdPlano(buscarListaUFPorIdPlanoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDPorIdPlanoResponse buscarListaUFComDDDPorIdPlano(br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDPorIdPlanoRequest buscarListaUFComDDDPorIdPlanoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoUF.mc.geral.ErroInfo{
    if (uFPortType == null)
      _initUFPortTypeProxy();
    return uFPortType.buscarListaUFComDDDPorIdPlano(buscarListaUFComDDDPorIdPlanoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFResponse buscarListaUF(br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFRequest buscarListaUFRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoUF.mc.geral.ErroInfo{
    if (uFPortType == null)
      _initUFPortTypeProxy();
    return uFPortType.buscarListaUF(buscarListaUFRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDResponse buscarListaUFComDDD(br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDRequest buscarListaUFComDDDRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoUF.mc.geral.ErroInfo{
    if (uFPortType == null)
      _initUFPortTypeProxy();
    return uFPortType.buscarListaUFComDDD(buscarListaUFComDDDRequest, username, password);
  }
  
  
}