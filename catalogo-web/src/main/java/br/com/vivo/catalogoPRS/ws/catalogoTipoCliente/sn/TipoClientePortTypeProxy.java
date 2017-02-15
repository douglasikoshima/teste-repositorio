package br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn;

public class TipoClientePortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.TipoClientePortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.TipoClientePortType tipoClientePortType = null;
  
  public TipoClientePortTypeProxy() {
    _initTipoClientePortTypeProxy();
  }
  
  public TipoClientePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initTipoClientePortTypeProxy();
  }
  
  private void _initTipoClientePortTypeProxy() {
    try {
      tipoClientePortType = (new br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.TipoClienteSoapServiceLocator()).getTipoClienteSoapPort();
      if (tipoClientePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)tipoClientePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)tipoClientePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (tipoClientePortType != null)
      ((javax.xml.rpc.Stub)tipoClientePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.TipoClientePortType getTipoClientePortType() {
    if (tipoClientePortType == null)
      _initTipoClientePortTypeProxy();
    return tipoClientePortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.BuscarListaTipoClienteResponse buscarListaTipoCliente(br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.BuscarListaTipoClienteRequest buscarListaTipoClienteRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.mc.geral.ErroInfo{
    if (tipoClientePortType == null)
      _initTipoClientePortTypeProxy();
    return tipoClientePortType.buscarListaTipoCliente(buscarListaTipoClienteRequest,username,password);
  }
  
  
}