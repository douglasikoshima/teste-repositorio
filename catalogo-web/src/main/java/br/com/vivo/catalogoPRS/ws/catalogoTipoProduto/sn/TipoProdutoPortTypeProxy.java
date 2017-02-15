package br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn;

public class TipoProdutoPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoPortType tipoProdutoPortType = null;
  
  public TipoProdutoPortTypeProxy() {
    _initTipoProdutoPortTypeProxy();
  }
  
  public TipoProdutoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initTipoProdutoPortTypeProxy();
  }
  
  private void _initTipoProdutoPortTypeProxy() {
    try {
      tipoProdutoPortType = (new br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoSoapServiceLocator()).getTipoProdutoSoapPort();
      if (tipoProdutoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)tipoProdutoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)tipoProdutoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (tipoProdutoPortType != null)
      ((javax.xml.rpc.Stub)tipoProdutoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoPortType getTipoProdutoPortType() {
    if (tipoProdutoPortType == null)
      _initTipoProdutoPortTypeProxy();
    return tipoProdutoPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoResponse buscarListaTipoProduto(br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoRequest buscarListaTipoProdutoRequest,String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.mc.geral.ErroInfo{
    if (tipoProdutoPortType == null)
      _initTipoProdutoPortTypeProxy();
    return tipoProdutoPortType.buscarListaTipoProduto(buscarListaTipoProdutoRequest,username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoPorFabricanteResponse buscarListaTipoProdutoPorFabricante(br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoPorFabricanteRequest buscarListaTipoProdutoPorFabricanteRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.mc.geral.ErroInfo{
    if (tipoProdutoPortType == null)
      _initTipoProdutoPortTypeProxy();
    return tipoProdutoPortType.buscarListaTipoProdutoPorFabricante(buscarListaTipoProdutoPorFabricanteRequest,username, password);
  }
  
  
}