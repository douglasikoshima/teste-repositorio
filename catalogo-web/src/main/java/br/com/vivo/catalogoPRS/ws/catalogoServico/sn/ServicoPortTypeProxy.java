package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ServicoPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoPortType servicoPortType = null;
  
  public ServicoPortTypeProxy() {
    _initServicoPortTypeProxy();
  }
  
  public ServicoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initServicoPortTypeProxy();
  }
  
  private void _initServicoPortTypeProxy() {
    try {
      servicoPortType = (new br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoSoapServiceLocator()).getServicoSoapPort();
      if (servicoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)servicoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)servicoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (servicoPortType != null)
      ((javax.xml.rpc.Stub)servicoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoPortType getServicoPortType() {
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoParametrizacaoResponse buscarListaServicoParametrizacao(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoParametrizacaoRequest buscarListaServicoParametrizacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.buscarListaServicoParametrizacao(buscarListaServicoParametrizacaoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.AlterarServicoParametrizacaoResponse alterarServicoParametrizacao(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.AlterarServicoParametrizacaoRequest alterarServicoParametrizacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.alterarServicoParametrizacao(alterarServicoParametrizacaoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.AlterarCategoriaListaServicoResponse alterarCategoriaListaServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.AlterarCategoriaListaServicoRequest alterarCategoriaListaServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.alterarCategoriaListaServico(alterarCategoriaListaServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaQtdeMaxAtivacaoResponse buscarListaQtdeMaxAtivacao(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaQtdeMaxAtivacaoRequest buscarListaQtdeMaxAtivacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.buscarListaQtdeMaxAtivacao(buscarListaQtdeMaxAtivacaoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoPendValidacaoResponse buscarListaServicoPendValidacao(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoPendValidacaoRequest buscarListaServicoPendValidacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.buscarListaServicoPendValidacao(buscarListaServicoPendValidacaoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ExportarServicoResponse exportarServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ExportarServicoRequest exportarServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.exportarServico(exportarServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ValidarListaServicoPorIdResponse validarListaServicoPorId(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ValidarListaServicoPorIdRequest validarListaServicoPorIdRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.validarListaServicoPorId(validarListaServicoPorIdRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoResponse buscarListaServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoRequest buscarListaServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.buscarListaServico(buscarListaServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoTarifaPorIdServicoResponse buscarListaServicoTarifaPorIdServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoTarifaPorIdServicoRequest buscarListaServicoTarifaPorIdServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.buscarListaServicoTarifaPorIdServico(buscarListaServicoTarifaPorIdServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaRestricoesPorServicoResponse buscarListaRestricoesPorServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaRestricoesPorServicoRequest buscarListaRestricoesPorServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.buscarListaRestricoesPorServico(buscarListaRestricoesPorServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaAtributoPorIdServicoResponse buscarListaAtributoPorIdServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaAtributoPorIdServicoRequest buscarListaAtributoPorIdServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.buscarListaAtributoPorIdServico(buscarListaAtributoPorIdServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarDetalhesServicoPorIdPlanoResponse buscarDetalhesServicoPorIdPlano(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarDetalhesServicoPorIdPlanoRequest buscarDetalhesServicoPorIdPlanoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.buscarDetalhesServicoPorIdPlano(buscarDetalhesServicoPorIdPlanoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarDetalhesServicoResponse buscarDetalhesServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarDetalhesServicoRequest buscarDetalhesServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.buscarDetalhesServico(buscarDetalhesServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaTipoServicoResponse buscarListaTipoServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaTipoServicoRequest buscarListaTipoServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.buscarListaTipoServico(buscarListaTipoServicoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoPorSvTypeCodeResponse buscarListaServicoPorSvTypeCode(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoPorSvTypeCodeRequest buscarListaServicoPorSvTypeCodeRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.buscarListaServicoPorSvTypeCode(buscarListaServicoPorSvTypeCodeRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoParametrizacaoSemPagResponse buscarListaServicoParametrizacaoSemPag(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoParametrizacaoSemPagRequest buscarListaServicoParametrizacaoSemPagRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.buscarListaServicoParametrizacaoSemPag(buscarListaServicoParametrizacaoSemPagRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.AlterarAtivacaoWiFiResponse alterarAtivacaoWiFi(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.AlterarAtivacaoWiFiRequest alterarAtivacaoWiFiRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo{
    if (servicoPortType == null)
      _initServicoPortTypeProxy();
    return servicoPortType.alterarAtivacaoWiFi(alterarAtivacaoWiFiRequest, username, password);
  }
  
  
}