package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class MatrizOfertaPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.MatrizOfertaPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.MatrizOfertaPortType matrizOfertaPortType = null;
  
  public MatrizOfertaPortTypeProxy() {
    _initMatrizOfertaPortTypeProxy();
  }
  
  public MatrizOfertaPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initMatrizOfertaPortTypeProxy();
  }
  
  private void _initMatrizOfertaPortTypeProxy() {
    try {
      matrizOfertaPortType = (new br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.MatrizOfertaServiceLocator()).getMatrizOfertaSoapPort();
      if (matrizOfertaPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)matrizOfertaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)matrizOfertaPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (matrizOfertaPortType != null)
      ((javax.xml.rpc.Stub)matrizOfertaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.MatrizOfertaPortType getMatrizOfertaPortType() {
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoResponse obterExistenciaItemPreco(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoRequest obterExistenciaItemPrecoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.obterExistenciaItemPreco(obterExistenciaItemPrecoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesResponse obterVLAVistaVL10Vezes(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesRequest obterVLAVistaVL10VezesRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.obterVLAVistaVL10Vezes(obterVLAVistaVL10VezesRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemDataResponse alterarItemData(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemDataRequest alterarItemDataRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.alterarItemData(alterarItemDataRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemPrecoResponse alterarItemPreco(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemPrecoRequest alterarItemPrecoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.alterarItemPreco(alterarItemPrecoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarCriticasImportacaoResponse buscarCriticasImportacao(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarCriticasImportacaoRequest buscarCriticasImportacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.buscarCriticasImportacao(buscarCriticasImportacaoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemResponse exportarMatrizOfertaArquivoItem(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemRequest exportarMatrizOfertaArquivoItemRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.exportarMatrizOfertaArquivoItem(exportarMatrizOfertaArquivoItemRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoResponse buscarListaResultadoImportacao(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoRequest buscarListaResultadoImportacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.buscarListaResultadoImportacao(buscarListaResultadoImportacaoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizResponse buscarListaNmMatriz(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizRequest buscarListaNmMatrizRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.buscarListaNmMatriz(buscarListaNmMatrizRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoResponse buscarListaStatusImportacao(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoRequest buscarListaStatusImportacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.buscarListaStatusImportacao(buscarListaStatusImportacaoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaArquivoResponse importarMatrizOfertaArquivo(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaArquivoRequest importarMatrizOfertaArquivoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.importarMatrizOfertaArquivo(importarMatrizOfertaArquivoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.CriarCabecalhoResponse criarCabecalho(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.CriarCabecalhoRequest criarCabecalhoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.criarCabecalho(criarCabecalhoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizResponse buscarListaTipoMatriz(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizRequest buscarListaTipoMatrizRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.buscarListaTipoMatriz(buscarListaTipoMatrizRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarCabecalhoResponse alterarCabecalho(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarCabecalhoRequest alterarCabecalhoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.alterarCabecalho(alterarCabecalhoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemResponse alterarItem(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemRequest alterarItemRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.alterarItem(alterarItemRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoResponse buscarListaCabecalho(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoRequest buscarListaCabecalhoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.buscarListaCabecalho(buscarListaCabecalhoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisResponse buscarListaVariaveis(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisRequest buscarListaVariaveisRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.buscarListaVariaveis(buscarListaVariaveisRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirCabecalhoResponse excluirCabecalho(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirCabecalhoRequest excluirCabecalhoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.excluirCabecalho(excluirCabecalhoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirVariaveisResponse excluirVariaveis(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirVariaveisRequest excluirVariaveisRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.excluirVariaveis(excluirVariaveisRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AssociarVariaveisResponse associarVariaveis(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AssociarVariaveisRequest associarVariaveisRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.associarVariaveis(associarVariaveisRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensResponse buscarListaItens(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensRequest buscarListaItensRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.buscarListaItens(buscarListaItensRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizResponse buscarListaNmOfSAPPorMatriz(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizRequest buscarListaNmOfSAPPorMatrizRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.buscarListaNmOfSAPPorMatriz(buscarListaNmOfSAPPorMatrizRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaTipoContratoResponse importarMatrizOfertaTipoContrato(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaTipoContratoRequest importarMatrizOfertaTipoContratoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.importarMatrizOfertaTipoContrato(importarMatrizOfertaTipoContratoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoResponse buscarListaDetalhesImportacao(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoRequest buscarListaDetalhesImportacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.buscarListaDetalhesImportacao(buscarListaDetalhesImportacaoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarIdMatrizOfertaArquivoResponse buscarIdMatrizOfertaArquivo(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarIdMatrizOfertaArquivoRequest buscarIdMatrizOfertaArquivoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.buscarIdMatrizOfertaArquivo(buscarIdMatrizOfertaArquivoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ProcessarMatrizOfertaArquivoResponse processarMatrizOfertaArquivo(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ProcessarMatrizOfertaArquivoRequest processarMatrizOfertaArquivoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo{
    if (matrizOfertaPortType == null)
      _initMatrizOfertaPortTypeProxy();
    return matrizOfertaPortType.processarMatrizOfertaArquivo(processarMatrizOfertaArquivoRequest, username, password);
  }
  
  
}