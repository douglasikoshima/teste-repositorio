package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class MultimidiaPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.MultimidiaPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.MultimidiaPortType multimidiaPortType = null;
  
  public MultimidiaPortTypeProxy() {
    _initMultimidiaPortTypeProxy();
  }
  
  public MultimidiaPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initMultimidiaPortTypeProxy();
  }
  
  private void _initMultimidiaPortTypeProxy() {
    try {
      multimidiaPortType = (new br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.MultimidiaServiceLocator()).getMultimidiaSoapPort();
      if (multimidiaPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)multimidiaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)multimidiaPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (multimidiaPortType != null)
      ((javax.xml.rpc.Stub)multimidiaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.MultimidiaPortType getMultimidiaPortType() {
    if (multimidiaPortType == null)
      _initMultimidiaPortTypeProxy();
    return multimidiaPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ObterExistenciaClassMultResponse obterExistenciaClassMult(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ObterExistenciaClassMultRequest obterExistenciaClassMultRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMultimidia.mc.geral.ErroInfo{
    if (multimidiaPortType == null)
      _initMultimidiaPortTypeProxy();
    return multimidiaPortType.obterExistenciaClassMult(obterExistenciaClassMultRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaMultTMCorClassResponse buscarListaMultTMCorClass(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaMultTMCorClassRequest buscarListaMultTMCorClassRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMultimidia.mc.geral.ErroInfo{
    if (multimidiaPortType == null)
      _initMultimidiaPortTypeProxy();
    return multimidiaPortType.buscarListaMultTMCorClass(buscarListaMultTMCorClassRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.AlterarMultimidiaResponse alterarMultimidia(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.AlterarMultimidiaRequest alterarMultimidiaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMultimidia.mc.geral.ErroInfo{
    if (multimidiaPortType == null)
      _initMultimidiaPortTypeProxy();
    return multimidiaPortType.alterarMultimidia(alterarMultimidiaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaClassificacaoMultimidiaResponse buscarListaClassificacaoMultimidia(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaClassificacaoMultimidiaRequest buscarListaClassificacaoMultimidiaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMultimidia.mc.geral.ErroInfo{
    if (multimidiaPortType == null)
      _initMultimidiaPortTypeProxy();
    return multimidiaPortType.buscarListaClassificacaoMultimidia(buscarListaClassificacaoMultimidiaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaCorResponse buscarListaCor(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaCorRequest buscarListaCorRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMultimidia.mc.geral.ErroInfo{
    if (multimidiaPortType == null)
      _initMultimidiaPortTypeProxy();
    return multimidiaPortType.buscarListaCor(buscarListaCorRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaMultimidiaResponse buscarListaMultimidia(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaMultimidiaRequest buscarListaMultimidiaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMultimidia.mc.geral.ErroInfo{
    if (multimidiaPortType == null)
      _initMultimidiaPortTypeProxy();
    return multimidiaPortType.buscarListaMultimidia(buscarListaMultimidiaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaTipoMultimidiaResponse buscarListaTipoMultimidia(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaTipoMultimidiaRequest buscarListaTipoMultimidiaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMultimidia.mc.geral.ErroInfo{
    if (multimidiaPortType == null)
      _initMultimidiaPortTypeProxy();
    return multimidiaPortType.buscarListaTipoMultimidia(buscarListaTipoMultimidiaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.DesassociarListaMultimidiaResponse desassociarListaMultimidia(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.DesassociarListaMultimidiaRequest desassociarListaMultimidiaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMultimidia.mc.geral.ErroInfo{
    if (multimidiaPortType == null)
      _initMultimidiaPortTypeProxy();
    return multimidiaPortType.desassociarListaMultimidia(desassociarListaMultimidiaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.AssociarMultimidiaResponse associarMultimidia(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.AssociarMultimidiaRequest associarMultimidiaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMultimidia.mc.geral.ErroInfo{
    if (multimidiaPortType == null)
      _initMultimidiaPortTypeProxy();
    return multimidiaPortType.associarMultimidia(associarMultimidiaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaImagemPorModeloResponse buscarListaImagemPorModelo(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaImagemPorModeloRequest buscarListaImagemPorModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMultimidia.mc.geral.ErroInfo{
    if (multimidiaPortType == null)
      _initMultimidiaPortTypeProxy();
    return multimidiaPortType.buscarListaImagemPorModelo(buscarListaImagemPorModeloRequest, username, password);
  }
  
  
}