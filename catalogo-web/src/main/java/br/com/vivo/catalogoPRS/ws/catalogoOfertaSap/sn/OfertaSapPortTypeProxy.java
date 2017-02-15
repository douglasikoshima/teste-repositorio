package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class OfertaSapPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapPortType ofertaSapPortType = null;
  
  public OfertaSapPortTypeProxy() {
    _initOfertaSapPortTypeProxy();
  }
  
  public OfertaSapPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initOfertaSapPortTypeProxy();
  }
  
  private void _initOfertaSapPortTypeProxy() {
    try {
      ofertaSapPortType = (new br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapServiceLocator()).getOfertaSapSoapPort();
      if (ofertaSapPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)ofertaSapPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)ofertaSapPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (ofertaSapPortType != null)
      ((javax.xml.rpc.Stub)ofertaSapPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapPortType getOfertaSapPortType() {
    if (ofertaSapPortType == null)
      _initOfertaSapPortTypeProxy();
    return ofertaSapPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.VerificarAssociacaoOfertaSapResponse verificarAssociacaoOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.VerificarAssociacaoOfertaSapRequest verificarAssociacaoOfertaSapRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo{
    if (ofertaSapPortType == null)
      _initOfertaSapPortTypeProxy();
    return ofertaSapPortType.verificarAssociacaoOfertaSap(verificarAssociacaoOfertaSapRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaOfertaSapResponse buscarListaOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaOfertaSapRequest buscarListaOfertaSapRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo{
    if (ofertaSapPortType == null)
      _initOfertaSapPortTypeProxy();
    return ofertaSapPortType.buscarListaOfertaSap(buscarListaOfertaSapRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaComposicaoResponse buscarListaComposicao(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaComposicaoRequest buscarListaComposicaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo{
    if (ofertaSapPortType == null)
      _initOfertaSapPortTypeProxy();
    return ofertaSapPortType.buscarListaComposicao(buscarListaComposicaoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.CriarNovaOfertaSapResponse criarNovaOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.CriarNovaOfertaSapRequest criarNovaOfertaSapRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo{
    if (ofertaSapPortType == null)
      _initOfertaSapPortTypeProxy();
    return ofertaSapPortType.criarNovaOfertaSap(criarNovaOfertaSapRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.CriarComposicaoOfertaSapResponse criarComposicaoOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.CriarComposicaoOfertaSapRequest criarComposicaoOfertaSapRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo{
    if (ofertaSapPortType == null)
      _initOfertaSapPortTypeProxy();
    return ofertaSapPortType.criarComposicaoOfertaSap(criarComposicaoOfertaSapRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaPlanosSemOfertaResponse buscarListaPlanosSemOferta(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaPlanosSemOfertaRequest buscarListaPlanosSemOfertaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo{
    if (ofertaSapPortType == null)
      _initOfertaSapPortTypeProxy();
    return ofertaSapPortType.buscarListaPlanosSemOferta(buscarListaPlanosSemOfertaRequest,username,password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.VerificarExistenciaComposicaoResponse verificarExistenciaComposicao(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.VerificarExistenciaComposicaoRequest verificarExistenciaComposicaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo{
    if (ofertaSapPortType == null)
      _initOfertaSapPortTypeProxy();
    return ofertaSapPortType.verificarExistenciaComposicao(verificarExistenciaComposicaoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.AlterarOfertaSapResponse alterarOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.AlterarOfertaSapRequest alterarOfertaSapRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo{
    if (ofertaSapPortType == null)
      _initOfertaSapPortTypeProxy();
    return ofertaSapPortType.alterarOfertaSap(alterarOfertaSapRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.DeletarOfertaSapResponse deletarOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.DeletarOfertaSapRequest deletarOfertaSapRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo{
    if (ofertaSapPortType == null)
      _initOfertaSapPortTypeProxy();
    return ofertaSapPortType.deletarOfertaSap(deletarOfertaSapRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ExcluirComposicaoOfertaResponse excluirComposicaoOferta(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ExcluirComposicaoOfertaRequest excluirComposicaoOfertaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo{
    if (ofertaSapPortType == null)
      _initOfertaSapPortTypeProxy();
    return ofertaSapPortType.excluirComposicaoOferta(excluirComposicaoOfertaRequest, username, password);
  }
  
  
}