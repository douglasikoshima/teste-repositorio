package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ProdutosPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosPortType produtosPortType = null;
  
  public ProdutosPortTypeProxy() {
    _initProdutosPortTypeProxy();
  }
  
  public ProdutosPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initProdutosPortTypeProxy();
  }
  
  private void _initProdutosPortTypeProxy() {
    try {
      produtosPortType = (new br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosSoapServiceLocator()).getProdutosSoapPort();
      if (produtosPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)produtosPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)produtosPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (produtosPortType != null)
      ((javax.xml.rpc.Stub)produtosPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosPortType getProdutosPortType() {
    if (produtosPortType == null)
      _initProdutosPortTypeProxy();
    return produtosPortType;
  }
  
  public java.lang.Object atualizaCorProdutos(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AtualizaCorProdutosRequest atualizaCorProdutosRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo{
    if (produtosPortType == null)
      _initProdutosPortTypeProxy();
    return produtosPortType.atualizaCorProdutos(atualizaCorProdutosRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloResponse buscarListaCoresPorModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloRequest buscarListaCoresPorModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo{
    if (produtosPortType == null)
      _initProdutosPortTypeProxy();
    return produtosPortType.buscarListaCoresPorModelo(buscarListaCoresPorModeloRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloResponse buscarListaProdutoPorModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloRequest buscarListaProdutoPorModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo{
    if (produtosPortType == null)
      _initProdutosPortTypeProxy();
    return produtosPortType.buscarListaProdutoPorModelo(buscarListaProdutoPorModeloRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoResponse buscarListaProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoRequest buscarListaProdutoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo{
    if (produtosPortType == null)
      _initProdutosPortTypeProxy();
    return produtosPortType.buscarListaProduto(buscarListaProdutoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisResponse associarProdModeloHabilitarVariaveis(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisRequest associarProdModeloHabilitarVariaveisRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo{
    if (produtosPortType == null)
      _initProdutosPortTypeProxy();
    return produtosPortType.associarProdModeloHabilitarVariaveis(associarProdModeloHabilitarVariaveisRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoResponse buscarListaProdutoSemCodigoAssociado(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoRequest buscarListaProdutoSemCodigoAssociadoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo{
    if (produtosPortType == null)
      _initProdutosPortTypeProxy();
    return produtosPortType.buscarListaProdutoSemCodigoAssociado(buscarListaProdutoSemCodigoAssociadoRequest, username, password);
  }
  
  public java.lang.Object alterarDescricaoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AlterarDescricaoCatalogoRequest alterarDescricaoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo{
    if (produtosPortType == null)
      _initProdutosPortTypeProxy();
    return produtosPortType.alterarDescricaoCatalogo(alterarDescricaoCatalogoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoResponse disponibilizarProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoRequest disponibilizarProdutoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo{
    if (produtosPortType == null)
      _initProdutosPortTypeProxy();
    return produtosPortType.disponibilizarProduto(disponibilizarProdutoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisResponse desassociarProdModDesabilitarVariaveis(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisRequest desassociarProdModDesabilitarVariaveisRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo{
    if (produtosPortType == null)
      _initProdutosPortTypeProxy();
    return produtosPortType.desassociarProdModDesabilitarVariaveis(desassociarProdModDesabilitarVariaveisRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoResponse buscarQuantidadeModeloPorProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoRequest buscarQuantidadeModeloPorProdutoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo{
    if (produtosPortType == null)
      _initProdutosPortTypeProxy();
    return produtosPortType.buscarQuantidadeModeloPorProduto(buscarQuantidadeModeloPorProdutoRequest, username, password);
  }
  
  
}