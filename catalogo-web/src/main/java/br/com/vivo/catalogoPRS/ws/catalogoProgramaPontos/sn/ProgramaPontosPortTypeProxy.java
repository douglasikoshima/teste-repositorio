package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

public class ProgramaPontosPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ProgramaPontosPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ProgramaPontosPortType programaPontosPortType = null;
  
  public ProgramaPontosPortTypeProxy() {
    _initProgramaPontosPortTypeProxy();
  }
  
  public ProgramaPontosPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initProgramaPontosPortTypeProxy();
  }
  
  private void _initProgramaPontosPortTypeProxy() {
    try {
      programaPontosPortType = (new br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ProgramaPontosSoapServiceLocator()).getProgramaPontosSoapPort();
      if (programaPontosPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)programaPontosPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)programaPontosPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (programaPontosPortType != null)
      ((javax.xml.rpc.Stub)programaPontosPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ProgramaPontosPortType getProgramaPontosPortType() {
    if (programaPontosPortType == null)
      _initProgramaPontosPortTypeProxy();
    return programaPontosPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPResponse buscarListaPrecoProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPRequest buscarListaPrecoProdutoPPRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo{
    if (programaPontosPortType == null)
      _initProgramaPontosPortTypeProxy();
    return programaPontosPortType.buscarListaPrecoProdutoPP(buscarListaPrecoProdutoPPRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPResponse alterarDispProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPRequest alterarDispProdutoPPRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo{
    if (programaPontosPortType == null)
      _initProgramaPontosPortTypeProxy();
    return programaPontosPortType.alterarDispProdutoPP(alterarDispProdutoPPRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPResponse buscarConfigProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPRequest buscarConfigProdutoPPRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo{
    if (programaPontosPortType == null)
      _initProgramaPontosPortTypeProxy();
    return programaPontosPortType.buscarConfigProdutoPP(buscarConfigProdutoPPRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPResponse carregarDispProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPRequest carregarDispProdutoPPRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo{
    if (programaPontosPortType == null)
      _initProgramaPontosPortTypeProxy();
    return programaPontosPortType.carregarDispProdutoPP(carregarDispProdutoPPRequest, username, password);
  }
  
  
}