package br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn;

public class PlataformaPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortType plataformaPortType = null;
  
  public PlataformaPortTypeProxy() {
    _initPlataformaPortTypeProxy();
  }
  
  public PlataformaPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initPlataformaPortTypeProxy();
  }
  
  private void _initPlataformaPortTypeProxy() {
    try {
      plataformaPortType = (new br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaSoapServiceLocator()).getPlataformaSoapPort();
      if (plataformaPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)plataformaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)plataformaPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (plataformaPortType != null)
      ((javax.xml.rpc.Stub)plataformaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortType getPlataformaPortType() {
    if (plataformaPortType == null)
      _initPlataformaPortTypeProxy();
    return plataformaPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaResponse buscarListaPlataforma(br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaRequest buscarListaPlataformaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlataforma.mc.geral.ErroInfo{
    if (plataformaPortType == null)
      _initPlataformaPortTypeProxy();
    return plataformaPortType.buscarListaPlataforma(buscarListaPlataformaRequest, username, password);
  }
  
  
}