package br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn;

public class GrupoServicoPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoPortType grupoServicoPortType = null;
  
  public GrupoServicoPortTypeProxy() {
    _initGrupoServicoPortTypeProxy();
  }
  
  public GrupoServicoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initGrupoServicoPortTypeProxy();
  }
  
  private void _initGrupoServicoPortTypeProxy() {
    try {
      grupoServicoPortType = (new br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoServiceLocator()).getGrupoServicoSoapPort();
      if (grupoServicoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)grupoServicoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)grupoServicoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (grupoServicoPortType != null)
      ((javax.xml.rpc.Stub)grupoServicoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoPortType getGrupoServicoPortType() {
    if (grupoServicoPortType == null)
      _initGrupoServicoPortTypeProxy();
    return grupoServicoPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoResponse criarGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoRequest criarGrupoServicoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo{
    if (grupoServicoPortType == null)
      _initGrupoServicoPortTypeProxy();
    return grupoServicoPortType.criarGrupoServicoCatalogo(criarGrupoServicoCatalogoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoResponse alterarGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoRequest alterarGrupoServicoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo{
    if (grupoServicoPortType == null)
      _initGrupoServicoPortTypeProxy();
    return grupoServicoPortType.alterarGrupoServicoCatalogo(alterarGrupoServicoCatalogoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse buscarListaGrupoServico(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoRequest buscarListaGrupoServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo{
    if (grupoServicoPortType == null)
      _initGrupoServicoPortTypeProxy();
    return grupoServicoPortType.buscarListaGrupoServico(buscarListaGrupoServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoResponse buscarServicoPorGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoRequest buscarServicoPorGrupoServicoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo{
    if (grupoServicoPortType == null)
      _initGrupoServicoPortTypeProxy();
    return grupoServicoPortType.buscarServicoPorGrupoServicoCatalogo(buscarServicoPorGrupoServicoCatalogoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoResponse excluirListaGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoRequest excluirListaGrupoServicoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo{
    if (grupoServicoPortType == null)
      _initGrupoServicoPortTypeProxy();
    return grupoServicoPortType.excluirListaGrupoServicoCatalogo(excluirListaGrupoServicoCatalogoRequest, username, password);
  }
  
  
}