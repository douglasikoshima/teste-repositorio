package br.com.vivo.catalogoPRS.ws.catalogoAreaRegistro.sn;

public class AreaRegistroPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoAreaRegistro.sn.AreaRegistroPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoAreaRegistro.sn.AreaRegistroPortType areaRegistroPortType = null;
  
  public AreaRegistroPortTypeProxy() {
    _initAreaRegistroPortTypeProxy();
  }
  
  public AreaRegistroPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initAreaRegistroPortTypeProxy();
  }
  
  private void _initAreaRegistroPortTypeProxy() {
    try {
      areaRegistroPortType = (new br.com.vivo.catalogoPRS.ws.catalogoAreaRegistro.sn.AreaRegistroServiceLocator()).getAreaRegistroPort();
      if (areaRegistroPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)areaRegistroPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)areaRegistroPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (areaRegistroPortType != null)
      ((javax.xml.rpc.Stub)areaRegistroPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAreaRegistro.sn.AreaRegistroPortType getAreaRegistroPortType() {
    if (areaRegistroPortType == null)
      _initAreaRegistroPortTypeProxy();
    return areaRegistroPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAreaRegistro.sn.BuscarListaAreaRegistroResponse buscarListaAreaRegistro(br.com.vivo.catalogoPRS.ws.catalogoAreaRegistro.sn.BuscarListaAreaRegistroRequest parameters) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAreaRegistro.mc.geral.ErroInfo{
    if (areaRegistroPortType == null)
      _initAreaRegistroPortTypeProxy();
    return areaRegistroPortType.buscarListaAreaRegistro(parameters);
  }
  
  
}