package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ModeloPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ModeloPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ModeloPortType modeloPortType = null;
  
  public ModeloPortTypeProxy() {
    _initModeloPortTypeProxy();
  }
  
  public ModeloPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initModeloPortTypeProxy();
  }
  
  private void _initModeloPortTypeProxy() {
    try {
      modeloPortType = (new br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ModeloSoapServiceLocator()).getModeloSoapPort();
      if (modeloPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)modeloPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)modeloPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (modeloPortType != null)
      ((javax.xml.rpc.Stub)modeloPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ModeloPortType getModeloPortType() {
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.UpdateGrupoProdutoResponse updateGrupoProduto(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.UpdateGrupoProdutoRequest updateGrupoProdutoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.updateGrupoProduto(updateGrupoProdutoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaCaracteristicaDispProdutoModeloResponse buscarListaCaracteristicaDispProdutoModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaCaracteristicaDispProdutoModeloRequest buscarListaCaracteristicaDispProdutoModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.buscarListaCaracteristicaDispProdutoModelo(buscarListaCaracteristicaDispProdutoModeloRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ExportarModeloResponse exportarModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ExportarModeloRequest exportarModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.exportarModelo(exportarModeloRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ExportarRestricoesModeloResponse exportarRestricoesModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ExportarRestricoesModeloRequest exportarRestricoesModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.exportarRestricoesModelo(exportarRestricoesModeloRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloPorTipoProdutoFabricanteResponse buscarListaModeloPorTipoProdutoFabricante(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloPorTipoProdutoFabricanteRequest buscarListaModeloPorTipoProdutoFabricanteRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.buscarListaModeloPorTipoProdutoFabricante(buscarListaModeloPorTipoProdutoFabricanteRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarGrupoProdutoPorIdResponse buscarGrupoProdutoPorId(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarGrupoProdutoPorIdRequest buscarGrupoProdutoPorIdRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.buscarGrupoProdutoPorId(buscarGrupoProdutoPorIdRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloPorTecnologiaTpFrequenciaVlResponse buscarListaModeloPorTecnologiaTpFrequenciaVl(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest buscarListaModeloPorTecnologiaTpFrequenciaVlRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.buscarListaModeloPorTecnologiaTpFrequenciaVl(buscarListaModeloPorTecnologiaTpFrequenciaVlRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaResponse buscarModeloListaTecnologiaTipoFrequenciaValorFrequencia(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest buscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.buscarModeloListaTecnologiaTipoFrequenciaValorFrequencia(buscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.DisponibilizarModeloResponse disponibilizarModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.DisponibilizarModeloRequest disponibilizarModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.disponibilizarModelo(disponibilizarModeloRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarDetalhesModeloResponse buscarDetalhesModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarDetalhesModeloRequest buscarDetalhesModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.buscarDetalhesModelo(buscarDetalhesModeloRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloResponse buscarListaModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloRequest buscarListaModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.buscarListaModelo(buscarListaModeloRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloDispComFabricanteResponse buscarListaModeloDispComFabricante(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloDispComFabricanteRequest buscarListaModeloDispComFabricanteRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.buscarListaModeloDispComFabricante(buscarListaModeloDispComFabricanteRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaRestricoesModeloResponse buscarListaRestricoesModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaRestricoesModeloRequest buscarListaRestricoesModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.buscarListaRestricoesModelo(buscarListaRestricoesModeloRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaCaracteristicaProdutoModeloResponse buscarListaCaracteristicaProdutoModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaCaracteristicaProdutoModeloRequest buscarListaCaracteristicaProdutoModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.buscarListaCaracteristicaProdutoModelo(buscarListaCaracteristicaProdutoModeloRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloPorIdResponse buscarModeloPorId(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloPorIdRequest buscarModeloPorIdRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.buscarModeloPorId(buscarModeloPorIdRequest, username, password);
  }
  
  public java.lang.Object alterarModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.AlterarModeloRequest alterarModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.alterarModelo(alterarModeloRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.CriarModeloResponse criarModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.CriarModeloRequest criarModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.criarModelo(criarModeloRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloPorTecnologiaTpFrequenciaVlResponse buscarModeloPorTecnologiaTpFrequenciaVl(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloPorTecnologiaTpFrequenciaVlRequest buscarModeloPorTecnologiaTpFrequenciaVlRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.buscarModeloPorTecnologiaTpFrequenciaVl(buscarModeloPorTecnologiaTpFrequenciaVlRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ExcluirListaModeloResponse excluirListaModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ExcluirListaModeloRequest excluirListaModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null) 
      _initModeloPortTypeProxy();
    return modeloPortType.excluirListaModelo(excluirListaModeloRequest, username, password);
  }
  
  public java.lang.Object alterarModeloCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.AlterarModeloCaracteristicaRequest alterarModeloCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.alterarModeloCaracteristica(alterarModeloCaracteristicaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.AlterarFimVidaModeloResponse alterarFimVidaModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.AlterarFimVidaModeloRequest alterarFimVidaModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.alterarFimVidaModelo(alterarFimVidaModeloRequest, username, password);
  }
  
  public java.lang.Object associarModeloCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.AssociarModeloCaracteristicaRequest associarModeloCaracteristicaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo{
    if (modeloPortType == null)
      _initModeloPortTypeProxy();
    return modeloPortType.associarModeloCaracteristica(associarModeloCaracteristicaRequest, username, password);
  }
  
  
}