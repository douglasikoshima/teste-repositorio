package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class TecnologiaPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.TecnologiaPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.TecnologiaPortType tecnologiaPortType = null;
  
  public TecnologiaPortTypeProxy() {
    _initTecnologiaPortTypeProxy();
  }
  
  public TecnologiaPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initTecnologiaPortTypeProxy();
  }
  
  private void _initTecnologiaPortTypeProxy() {
    try {
      tecnologiaPortType = (new br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.TecnologiaSoapServiceLocator()).getTecnologiaSoapPort();
      if (tecnologiaPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)tecnologiaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)tecnologiaPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (tecnologiaPortType != null)
      ((javax.xml.rpc.Stub)tecnologiaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.TecnologiaPortType getTecnologiaPortType() {
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.CriarTecnologiaResponse criarTecnologia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.CriarTecnologiaRequest criarTecnologiaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.criarTecnologia(criarTecnologiaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.AlterarTecnologiaTipoFrequenciaResponse alterarTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.AlterarTecnologiaTipoFrequenciaRequest alterarTecnologiaTipoFrequenciaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.alterarTecnologiaTipoFrequencia(alterarTecnologiaTipoFrequenciaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.AssociarTecnologiaTipoFrequenciaResponse associarTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.AssociarTecnologiaTipoFrequenciaRequest associarTecnologiaTipoFrequenciaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.associarTecnologiaTipoFrequencia(associarTecnologiaTipoFrequenciaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaTipoFrequenciaResponse buscarListaTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaTipoFrequenciaRequest buscarListaTecnologiaTipoFrequenciaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.buscarListaTecnologiaTipoFrequencia(buscarListaTecnologiaTipoFrequenciaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaModeloPorTecnologiaResponse buscarListaModeloPorTecnologia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaModeloPorTecnologiaRequest buscarListaModeloPorTecnologiaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.buscarListaModeloPorTecnologia(buscarListaModeloPorTecnologiaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ExcluirListaTecnologiaResponse excluirListaTecnologia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ExcluirListaTecnologiaRequest excluirListaTecnologiaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.excluirListaTecnologia(excluirListaTecnologiaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarAssociacaoTecnologiaTpFrequenciaResponse buscarAssociacaoTecnologiaTpFrequencia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarAssociacaoTecnologiaTpFrequenciaRequest buscarAssociacaoTecnologiaTpFrequenciaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.buscarAssociacaoTecnologiaTpFrequencia(buscarAssociacaoTecnologiaTpFrequenciaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaResponse buscarListaTecnologia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaRequest buscarListaTecnologiaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.buscarListaTecnologia(buscarListaTecnologiaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaPorModeloResponse buscarListaTecnologiaPorModelo(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaPorModeloRequest buscarListaTecnologiaPorModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.buscarListaTecnologiaPorModelo(buscarListaTecnologiaPorModeloRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaSimplesResponse buscarListaTecnologiaSimples(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaSimplesRequest buscarListaTecnologiaSimplesRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.buscarListaTecnologiaSimples(buscarListaTecnologiaSimplesRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.DesassociarListaTecnologiaTipoFrequenciaResponse desassociarListaTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.DesassociarListaTecnologiaTipoFrequenciaRequest desassociarListaTecnologiaTipoFrequenciaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.desassociarListaTecnologiaTipoFrequencia(desassociarListaTecnologiaTipoFrequenciaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.AssociarTecnologiaTipoFrequenciaValorResponse associarTecnologiaTipoFrequenciaValor(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.AssociarTecnologiaTipoFrequenciaValorRequest associarTecnologiaTipoFrequenciaValorRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.associarTecnologiaTipoFrequenciaValor(associarTecnologiaTipoFrequenciaValorRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.DesassociarTecnologiaTpFrequenciaVlResponse desassociarTecnologiaTpFrequenciaVl(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.DesassociarTecnologiaTpFrequenciaVlRequest desassociarTecnologiaTpFrequenciaVlRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.mc.geral.ErroInfo{
    if (tecnologiaPortType == null)
      _initTecnologiaPortTypeProxy();
    return tecnologiaPortType.desassociarTecnologiaTpFrequenciaVl(desassociarTecnologiaTpFrequenciaVlRequest,username,password);
  }
  
  
}