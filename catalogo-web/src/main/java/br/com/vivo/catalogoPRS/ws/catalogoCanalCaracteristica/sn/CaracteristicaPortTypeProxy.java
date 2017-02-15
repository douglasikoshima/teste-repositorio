package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class CaracteristicaPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaPortType caracteristicaPortType = null;
  
  public CaracteristicaPortTypeProxy() {
    _initCaracteristicaPortTypeProxy();
  }
  
  public CaracteristicaPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initCaracteristicaPortTypeProxy();
  }
  
  private void _initCaracteristicaPortTypeProxy() {
    try {
      caracteristicaPortType = (new br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaSoapServiceLocator()).getCaracteristicaSoapPort();
      if (caracteristicaPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)caracteristicaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)caracteristicaPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (caracteristicaPortType != null)
      ((javax.xml.rpc.Stub)caracteristicaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaPortType getCaracteristicaPortType() {
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.AlterarCaracteristicaResponse alterarCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.AlterarCaracteristicaRequest alterarCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.alterarCaracteristica(alterarCaracteristicaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ExcluirListaCaracteristicaResponse excluirListaCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ExcluirListaCaracteristicaRequest excluirListaCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.excluirListaCaracteristica(excluirListaCaracteristicaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaValorCaracteristicaResponse buscarListaValorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaValorCaracteristicaRequest buscarListaValorCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.buscarListaValorCaracteristica(buscarListaValorCaracteristicaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaModeloPorCaracteristicaResponse buscarListaModeloPorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaModeloPorCaracteristicaRequest buscarListaModeloPorCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.buscarListaModeloPorCaracteristica(buscarListaModeloPorCaracteristicaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaCaracteristicaResponse buscarListaCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaCaracteristicaRequest buscarListaCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.buscarListaCaracteristica(buscarListaCaracteristicaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarValorCaracteristicaPorIdResponse buscarValorCaracteristicaPorId(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarValorCaracteristicaPorIdRequest buscarValorCaracteristicaPorIdRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.buscarValorCaracteristicaPorId(buscarValorCaracteristicaPorIdRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CriarValorCaracteristicaResponse criarValorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CriarValorCaracteristicaRequest criarValorCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.criarValorCaracteristica(criarValorCaracteristicaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ExcluirListaValorCaracteristicaResponse excluirListaValorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ExcluirListaValorCaracteristicaRequest excluirListaValorCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.excluirListaValorCaracteristica(excluirListaValorCaracteristicaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CriarCaracteristicaResponse criarCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CriarCaracteristicaRequest criarCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.criarCaracteristica(criarCaracteristicaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.DisponibilizarCaracteristicaResponse disponibilizarCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.DisponibilizarCaracteristicaRequest disponibilizarCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.disponibilizarCaracteristica(disponibilizarCaracteristicaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarModelosPorValorCaracteristicaResponse buscarModelosPorValorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarModelosPorValorCaracteristicaRequest buscarModelosPorValorCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.buscarModelosPorValorCaracteristica(buscarModelosPorValorCaracteristicaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaCaracteristicaComValorPorModeloResponse buscarListaCaracteristicaComValorPorModelo(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaCaracteristicaComValorPorModeloRequest buscarListaCaracteristicaComValorPorModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.buscarListaCaracteristicaComValorPorModelo(buscarListaCaracteristicaComValorPorModeloRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarCaracteristicaPorIdResponse buscarCaracteristicaPorId(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarCaracteristicaPorIdRequest buscarCaracteristicaPorIdRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.buscarCaracteristicaPorId(buscarCaracteristicaPorIdRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.AlterarValorCaracteristicaResponse alterarValorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.AlterarValorCaracteristicaRequest alterarValorCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.alterarValorCaracteristica(alterarValorCaracteristicaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaCaracteristicaComValorResponse buscarListaCaracteristicaComValor(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaCaracteristicaComValorRequest buscarListaCaracteristicaComValorRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo{
    if (caracteristicaPortType == null)
      _initCaracteristicaPortTypeProxy();
    return caracteristicaPortType.buscarListaCaracteristicaComValor(buscarListaCaracteristicaComValorRequest,username,password);
  }
  
  
}