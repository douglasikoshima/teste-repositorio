package br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn;

public class TipoOperacaoPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.TipoOperacaoPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.TipoOperacaoPortType tipoOperacaoPortType = null;
  
  public TipoOperacaoPortTypeProxy() {
    _initTipoOperacaoPortTypeProxy();
  }
  
  public TipoOperacaoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initTipoOperacaoPortTypeProxy();
  }
  
  private void _initTipoOperacaoPortTypeProxy() {
    try {
      tipoOperacaoPortType = (new br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.TipoOperacaoSoapServiceLocator()).getTipoOperacaoSoapPort();
      if (tipoOperacaoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)tipoOperacaoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)tipoOperacaoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (tipoOperacaoPortType != null)
      ((javax.xml.rpc.Stub)tipoOperacaoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.TipoOperacaoPortType getTipoOperacaoPortType() {
    if (tipoOperacaoPortType == null)
      _initTipoOperacaoPortTypeProxy();
    return tipoOperacaoPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.BuscarListaTipoOperacaoResponse buscarListaTipoOperacao(br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.BuscarListaTipoOperacaoRequest buscarListaTipoOperacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.mc.geral.ErroInfo{
    if (tipoOperacaoPortType == null)
      _initTipoOperacaoPortTypeProxy();
    return tipoOperacaoPortType.buscarListaTipoOperacao(buscarListaTipoOperacaoRequest, username, password);
  }
  
  
}