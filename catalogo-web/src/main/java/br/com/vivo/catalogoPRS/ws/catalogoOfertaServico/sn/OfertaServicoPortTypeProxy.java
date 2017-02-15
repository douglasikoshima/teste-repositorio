package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class OfertaServicoPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.OfertaServicoPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.OfertaServicoPortType ofertaServicoPortType = null;
  
  public OfertaServicoPortTypeProxy() {
    _initOfertaServicoPortTypeProxy();
  }
  
  public OfertaServicoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initOfertaServicoPortTypeProxy();
  }
  
  private void _initOfertaServicoPortTypeProxy() {
    try {
      ofertaServicoPortType = (new br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.OfertaServicoServiceLocator()).getOfertaServicoSoapPort();
      if (ofertaServicoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)ofertaServicoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)ofertaServicoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (ofertaServicoPortType != null)
      ((javax.xml.rpc.Stub)ofertaServicoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.OfertaServicoPortType getOfertaServicoPortType() {
    if (ofertaServicoPortType == null)
      _initOfertaServicoPortTypeProxy();
    return ofertaServicoPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.VerificarItemMatrizOfertaServicoResponse verificarItemMatrizOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.VerificarItemMatrizOfertaServicoRequest verificarItemMatrizOfertaServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.mc.geral.ErroInfo{
    if (ofertaServicoPortType == null)
      _initOfertaServicoPortTypeProxy();
    return ofertaServicoPortType.verificarItemMatrizOfertaServico(verificarItemMatrizOfertaServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.AlterarOfertaServicoResponse alterarOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.AlterarOfertaServicoRequest alterarOfertaServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.mc.geral.ErroInfo{
    if (ofertaServicoPortType == null)
      _initOfertaServicoPortTypeProxy();
    return ofertaServicoPortType.alterarOfertaServico(alterarOfertaServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarListaServicoComOfertaResponse buscarListaServicoComOferta(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarListaServicoComOfertaRequest buscarListaServicoComOfertaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.mc.geral.ErroInfo{
    if (ofertaServicoPortType == null)
      _initOfertaServicoPortTypeProxy();
    return ofertaServicoPortType.buscarListaServicoComOferta(buscarListaServicoComOfertaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarListaOfertaServicoResponse buscarListaOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarListaOfertaServicoRequest buscarListaOfertaServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.mc.geral.ErroInfo{
    if (ofertaServicoPortType == null)
      _initOfertaServicoPortTypeProxy();
    return ofertaServicoPortType.buscarListaOfertaServico(buscarListaOfertaServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.CriarOfertaServicoResponse criarOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.CriarOfertaServicoRequest criarOfertaServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.mc.geral.ErroInfo{
    if (ofertaServicoPortType == null)
      _initOfertaServicoPortTypeProxy();
    return ofertaServicoPortType.criarOfertaServico(criarOfertaServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ExcluirServicoOfertaServicoResponse excluirServicoOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ExcluirServicoOfertaServicoRequest excluirServicoOfertaServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.mc.geral.ErroInfo{
    if (ofertaServicoPortType == null)
      _initOfertaServicoPortTypeProxy();
    return ofertaServicoPortType.excluirServicoOfertaServico(excluirServicoOfertaServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarDadosServicoOfertaServicoResponse buscarDadosServicoOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarDadosServicoOfertaServicoRequest buscarDadosServicoOfertaServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.mc.geral.ErroInfo{
    if (ofertaServicoPortType == null)
      _initOfertaServicoPortTypeProxy();
    return ofertaServicoPortType.buscarDadosServicoOfertaServico(buscarDadosServicoOfertaServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ExcluirOfertaServicoResponse excluirOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ExcluirOfertaServicoRequest excluirOfertaServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.mc.geral.ErroInfo{
    if (ofertaServicoPortType == null)
      _initOfertaServicoPortTypeProxy();
    return ofertaServicoPortType.excluirOfertaServico(excluirOfertaServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.AssociarServicoOfertaServicoResponse associarServicoOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.AssociarServicoOfertaServicoRequest associarServicoOfertaServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.mc.geral.ErroInfo{
    if (ofertaServicoPortType == null)
      _initOfertaServicoPortTypeProxy();
    return ofertaServicoPortType.associarServicoOfertaServico(associarServicoOfertaServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarListaNomeOfertaServicoResponse buscarListaNomeOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarListaNomeOfertaServicoRequest buscarListaNomeOfertaServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.mc.geral.ErroInfo{
    if (ofertaServicoPortType == null)
      _initOfertaServicoPortTypeProxy();
    return ofertaServicoPortType.buscarListaNomeOfertaServico(buscarListaNomeOfertaServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ValidarServicoOfertaServicoResponse validarServicoOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ValidarServicoOfertaServicoRequest validarServicoOfertaServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.mc.geral.ErroInfo{
    if (ofertaServicoPortType == null)
      _initOfertaServicoPortTypeProxy();
    return ofertaServicoPortType.validarServicoOfertaServico(validarServicoOfertaServicoRequest, username, password);
  }
  
  
}