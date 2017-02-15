package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public class DescontoPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.DescontoPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.DescontoPortType descontoPortType = null;
  
  public DescontoPortTypeProxy() {
    _initDescontoPortTypeProxy();
  }
  
  public DescontoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initDescontoPortTypeProxy();
  }
  
  private void _initDescontoPortTypeProxy() {
    try {
      descontoPortType = (new br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.DescontoServiceLocator()).getDescontoService();
      if (descontoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)descontoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)descontoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (descontoPortType != null)
      ((javax.xml.rpc.Stub)descontoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.DescontoPortType getDescontoPortType() {
    if (descontoPortType == null)
      _initDescontoPortTypeProxy();
    return descontoPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.BuscarListaParamDescontoResponse buscarListaParamDesconto(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.BuscarListaParamDescontoRequest parameters, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoDesconto.mc.geral.ErroInfo{
    if (descontoPortType == null)
      _initDescontoPortTypeProxy();
    return descontoPortType.buscarListaParamDesconto(parameters, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.AlterarParamDescontoResponse alterarParamDesconto(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.AlterarParamDescontoRequest parameters, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoDesconto.mc.geral.ErroInfo{
    if (descontoPortType == null)
      _initDescontoPortTypeProxy();
    return descontoPortType.alterarParamDesconto(parameters, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.AlterarFormaPagtoCanalParamResponse alterarFormaPagtoCanalParam(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.AlterarFormaPagtoCanalParamRequest parameters, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoDesconto.mc.geral.ErroInfo{
    if (descontoPortType == null)
      _initDescontoPortTypeProxy();
    return descontoPortType.alterarFormaPagtoCanalParam(parameters, username, password);
  }
  
  
}