package br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn;

public class FabricantePortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.FabricantePortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.FabricantePortType fabricantePortType = null;
  
  public FabricantePortTypeProxy() {
    _initFabricantePortTypeProxy();
  }
  
  public FabricantePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initFabricantePortTypeProxy();
  }
  
  private void _initFabricantePortTypeProxy() {
    try {
      fabricantePortType = (new br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.FabricanteServiceLocator()).getFabricanteSoapPort();
      if (fabricantePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)fabricantePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)fabricantePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (fabricantePortType != null)
      ((javax.xml.rpc.Stub)fabricantePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.FabricantePortType getFabricantePortType() {
    if (fabricantePortType == null)
      _initFabricantePortTypeProxy();
    return fabricantePortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.BuscarListaFabricanteResponse buscarListaFabricante(br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.BuscarListaFabricanteRequest parameters, String username, String password) throws java.rmi.RemoteException{
    if (fabricantePortType == null)
      _initFabricantePortTypeProxy();
    return fabricantePortType.buscarListaFabricante(parameters,username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.BuscarListaFabricantePorTipoProdutoResponse buscarListaFabricantePorTipoProduto(br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.BuscarListaFabricantePorTipoProdutoRequest parameters, String username, String password) throws java.rmi.RemoteException{
    if (fabricantePortType == null)
      _initFabricantePortTypeProxy();
    return fabricantePortType.buscarListaFabricantePorTipoProduto(parameters,username, password);
  }
  
  
}