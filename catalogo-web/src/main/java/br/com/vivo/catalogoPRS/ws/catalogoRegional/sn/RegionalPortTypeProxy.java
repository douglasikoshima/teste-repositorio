package br.com.vivo.catalogoPRS.ws.catalogoRegional.sn;

public class RegionalPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.RegionalPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.RegionalPortType regionalPortType = null;
  
  public RegionalPortTypeProxy() {
    _initRegionalPortTypeProxy();
  }
  
  public RegionalPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initRegionalPortTypeProxy();
  }
  
  private void _initRegionalPortTypeProxy() {
    try {
      regionalPortType = (new br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.RegionalSoapServiceLocator()).getRegionalSOAP();
      if (regionalPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)regionalPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)regionalPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (regionalPortType != null)
      ((javax.xml.rpc.Stub)regionalPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.RegionalPortType getRegionalPortType() {
    if (regionalPortType == null)
      _initRegionalPortTypeProxy();
    return regionalPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.ListaRegionalUf[] buscarListaRegionalComUF(br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.BuscarListaRegionalComUFRequest buscarListaRegionalComUFRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoRegional.mc.geral.ErroInfo{
    if (regionalPortType == null)
      _initRegionalPortTypeProxy();
    return regionalPortType.buscarListaRegionalComUF(buscarListaRegionalComUFRequest, username, password);
  }
  
  
}