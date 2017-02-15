package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class PlanoPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PlanoPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PlanoPortType planoPortType = null;
  
  public PlanoPortTypeProxy() {
    _initPlanoPortTypeProxy();
  }
  
  public PlanoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initPlanoPortTypeProxy();
  }
  
  private void _initPlanoPortTypeProxy() {
    try {
      planoPortType = (new br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PlanoServiceLocator()).getPlanoPort();
      if (planoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)planoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)planoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (planoPortType != null)
      ((javax.xml.rpc.Stub)planoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PlanoPortType getPlanoPortType() {
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ExportarPlanoResponse exportarPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ExportarPlanoRequest exportarPlanoRequest,String username,String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo{
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType.exportarPlano(exportarPlanoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoParametrizacaoResponse buscarListaPlanoParametrizacao(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoParametrizacaoRequest buscarListaPlanoParametrizacaoRequest,String username,String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo{
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType.buscarListaPlanoParametrizacao(buscarListaPlanoParametrizacaoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoPorIdResponse buscarListaPlanoPorId(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoPorIdRequest buscarListaPlanoPorIdRequest,String username,String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo{
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType.buscarListaPlanoPorId(buscarListaPlanoPorIdRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.AlterarPlanoParametrizacaoResponse alterarPlanoParametrizacao(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.AlterarPlanoParametrizacaoRequest alterarPlanoParametrizacaoRequest,String username,String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo{
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType.alterarPlanoParametrizacao(alterarPlanoParametrizacaoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoPendValidacaoResponse buscarListaPlanoPendValidacao(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoPendValidacaoRequest buscarListaPlanoPendValidacaoRequest,String username,String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo{
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType.buscarListaPlanoPendValidacao(buscarListaPlanoPendValidacaoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ValidarListaPlanoPorIdResponse validarListaPlanoPorId(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ValidarListaPlanoPorIdRequest validarListaPlanoPorIdRequest,String username,String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo{
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType.validarListaPlanoPorId(validarListaPlanoPorIdRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarPlanoResponse buscarPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarPlanoRequest buscarPlanoRequest,String username,String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo{
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType.buscarPlano(buscarPlanoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarDescricaoPlanoResponse buscarDescricaoPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarDescricaoPlanoRequest buscarDescricaoPlanoRequest,String username,String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo{
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType.buscarDescricaoPlano(buscarDescricaoPlanoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaVariaveisPorPlanoResponse buscarListaVariaveisPorPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaVariaveisPorPlanoRequest buscarListaVariaveisPorPlanoRequest,String username,String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo{
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType.buscarListaVariaveisPorPlano(buscarListaVariaveisPorPlanoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoResponse buscarListaPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoRequest buscarListaPlanoRequest,String username,String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo{
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType.buscarListaPlano(buscarListaPlanoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaTipoPlanoResponse buscarListaTipoPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaTipoPlanoRequest buscarListaTipoPlanoRequest,String username,String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo{
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType.buscarListaTipoPlano(buscarListaTipoPlanoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.AlterarAtivacaoWiFiResponse alterarAtivacaoWiFi(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.AlterarAtivacaoWiFiRequest alterarAtivacaoWiFiRequest,String username,String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo{
    if (planoPortType == null)
      _initPlanoPortTypeProxy();
    return planoPortType.alterarAtivacaoWiFi(alterarAtivacaoWiFiRequest,username,password);
  }
  
  
}