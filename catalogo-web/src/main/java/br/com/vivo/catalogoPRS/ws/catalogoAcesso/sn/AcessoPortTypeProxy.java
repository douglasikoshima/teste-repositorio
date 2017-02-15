package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class AcessoPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AcessoPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AcessoPortType acessoPortType = null;
  
  public AcessoPortTypeProxy() {
    _initAcessoPortTypeProxy();
  }
  
  public AcessoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initAcessoPortTypeProxy();
  }
  
  private void _initAcessoPortTypeProxy() {
    try {
      acessoPortType = (new br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AcessoServiceLocator()).getAcessoSoapPort();
      if (acessoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)acessoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)acessoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (acessoPortType != null)
      ((javax.xml.rpc.Stub)acessoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AcessoPortType getAcessoPortType() {
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoPlanoPorIdResponse buscarListaAcessoPlanoPorId(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoPlanoPorIdRequest buscarListaAcessoPlanoPorIdRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.buscarListaAcessoPlanoPorId(buscarListaAcessoPlanoPorIdRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoServicoPorIdResponse buscarListaAcessoServicoPorId(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoServicoPorIdRequest buscarListaAcessoServicoPorIdRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.buscarListaAcessoServicoPorId(buscarListaAcessoServicoPorIdRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarQtdeRestricaoPerfilResponse buscarQtdeRestricaoPerfil(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarQtdeRestricaoPerfilRequest buscarQtdeRestricaoPerfilRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.buscarQtdeRestricaoPerfil(buscarQtdeRestricaoPerfilRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ExcluirAcessoServicoResponse excluirAcessoServico(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ExcluirAcessoServicoRequest excluirAcessoServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.excluirAcessoServico(excluirAcessoServicoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ExcluirAcessoPlanoResponse excluirAcessoPlano(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ExcluirAcessoPlanoRequest excluirAcessoPlanoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.excluirAcessoPlano(excluirAcessoPlanoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AlterarListaAcessoServicoResponse alterarListaAcessoServico(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AlterarListaAcessoServicoRequest alterarListaAcessoServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.alterarListaAcessoServico(alterarListaAcessoServicoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.CopiarPerfilResponse copiarPerfil(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.CopiarPerfilRequest copiarPerfilRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.copiarPerfil(copiarPerfilRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoServicoResponse buscarListaAcessoServico(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoServicoRequest buscarListaAcessoServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.buscarListaAcessoServico(buscarListaAcessoServicoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoPlanoResponse buscarListaAcessoPlano(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoPlanoRequest buscarListaAcessoPlanoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.buscarListaAcessoPlano(buscarListaAcessoPlanoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.IncluirAcessoServicoResponse incluirAcessoServico(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.IncluirAcessoServicoRequest incluirAcessoServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.incluirAcessoServico(incluirAcessoServicoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.IncluirAcessoPlanoResponse incluirAcessoPlano(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.IncluirAcessoPlanoRequest incluirAcessoPlanoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.incluirAcessoPlano(incluirAcessoPlanoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AlterarAcessoServicoResponse alterarAcessoServico(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AlterarAcessoServicoRequest alterarAcessoServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.alterarAcessoServico(alterarAcessoServicoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AlterarAcessoPlanoResponse alterarAcessoPlano(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AlterarAcessoPlanoRequest alterarAcessoPlanoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.alterarAcessoPlano(alterarAcessoPlanoRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AlterarListaAcessoPlanoResponse alterarListaAcessoPlano(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AlterarListaAcessoPlanoRequest alterarListaAcessoPlanoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo{
    if (acessoPortType == null)
      _initAcessoPortTypeProxy();
    return acessoPortType.alterarListaAcessoPlano(alterarListaAcessoPlanoRequest,username,password);
  }
  
  
}