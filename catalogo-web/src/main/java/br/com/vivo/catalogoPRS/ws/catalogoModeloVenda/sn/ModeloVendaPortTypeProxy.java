package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class ModeloVendaPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ModeloVendaPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ModeloVendaPortType modeloVendaPortType = null;
  
  public ModeloVendaPortTypeProxy() {
    _initModeloVendaPortTypeProxy();
  }
  
  public ModeloVendaPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initModeloVendaPortTypeProxy();
  }
  
  private void _initModeloVendaPortTypeProxy() {
    try {
      modeloVendaPortType = (new br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ModeloVendaServiceLocator()).getModeloVendaSoapPort();
      if (modeloVendaPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)modeloVendaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)modeloVendaPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (modeloVendaPortType != null)
      ((javax.xml.rpc.Stub)modeloVendaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ModeloVendaPortType getModeloVendaPortType() {
    if (modeloVendaPortType == null)
      _initModeloVendaPortTypeProxy();
    return modeloVendaPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.AssociarProdutoModeloVendaResponse associarProdutoModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.AssociarProdutoModeloVendaRequest associarProdutoModeloVendaRequest) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.mc.geral.ErroInfo{
    if (modeloVendaPortType == null)
      _initModeloVendaPortTypeProxy();
    return modeloVendaPortType.associarProdutoModeloVenda(associarProdutoModeloVendaRequest);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.AlterarModeloVendaResponse alterarModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.AlterarModeloVendaRequest alterarModeloVendaRequest) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.mc.geral.ErroInfo{
    if (modeloVendaPortType == null)
      _initModeloVendaPortTypeProxy();
    return modeloVendaPortType.alterarModeloVenda(alterarModeloVendaRequest);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.BuscarDadosModeloVendaResponse buscarDadosModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.BuscarDadosModeloVendaRequest buscarDadosModeloVendaRequest) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.mc.geral.ErroInfo{
    if (modeloVendaPortType == null)
      _initModeloVendaPortTypeProxy();
    return modeloVendaPortType.buscarDadosModeloVenda(buscarDadosModeloVendaRequest);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.BuscarListaModeloVendaResponse buscarListaModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.BuscarListaModeloVendaRequest buscarListaModeloVendaRequest) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.mc.geral.ErroInfo{
    if (modeloVendaPortType == null)
      _initModeloVendaPortTypeProxy();
    return modeloVendaPortType.buscarListaModeloVenda(buscarListaModeloVendaRequest);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.BuscarListaProdComModeloVendaResponse buscarListaProdComModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.BuscarListaProdComModeloVendaRequest buscarListaProdComModeloVendaRequest) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.mc.geral.ErroInfo{
    if (modeloVendaPortType == null)
      _initModeloVendaPortTypeProxy();
    return modeloVendaPortType.buscarListaProdComModeloVenda(buscarListaProdComModeloVendaRequest);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.BuscarListaProdSemModeloVendaResponse buscarListaProdSemModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.BuscarListaProdSemModeloVendaRequest buscarListaProdSemModeloVendaRequest) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.mc.geral.ErroInfo{
    if (modeloVendaPortType == null)
      _initModeloVendaPortTypeProxy();
    return modeloVendaPortType.buscarListaProdSemModeloVenda(buscarListaProdSemModeloVendaRequest);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.CriarModeloVendaResponse criarModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.CriarModeloVendaRequest criarModeloVendaRequest) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.mc.geral.ErroInfo{
    if (modeloVendaPortType == null)
      _initModeloVendaPortTypeProxy();
    return modeloVendaPortType.criarModeloVenda(criarModeloVendaRequest);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ExcluirModeloVendaResponse excluirModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ExcluirModeloVendaRequest excluirModeloVendaRequest) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.mc.geral.ErroInfo{
    if (modeloVendaPortType == null)
      _initModeloVendaPortTypeProxy();
    return modeloVendaPortType.excluirModeloVenda(excluirModeloVendaRequest);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ExcluirProdutoModeloVendaResponse excluirProdutoModeloVenda(br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ExcluirProdutoModeloVendaRequest excluirProdutoModeloVendaRequest) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.mc.geral.ErroInfo{
    if (modeloVendaPortType == null)
      _initModeloVendaPortTypeProxy();
    return modeloVendaPortType.excluirProdutoModeloVenda(excluirProdutoModeloVendaRequest);
  }
  
  
}