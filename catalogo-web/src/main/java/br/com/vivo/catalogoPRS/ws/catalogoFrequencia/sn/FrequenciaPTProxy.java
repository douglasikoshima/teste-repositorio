package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class FrequenciaPTProxy implements br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.FrequenciaPT {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.FrequenciaPT frequenciaPT = null;
  
  public FrequenciaPTProxy() {
    _initFrequenciaPTProxy();
  }
  
  public FrequenciaPTProxy(String endpoint) {
    _endpoint = endpoint;
    _initFrequenciaPTProxy();
  }
  
  private void _initFrequenciaPTProxy() {
    try {
      frequenciaPT = (new br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.FrequenciaSoapServiceLocator()).getFrequenciaSoapPort();
      if (frequenciaPT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)frequenciaPT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)frequenciaPT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (frequenciaPT != null)
      ((javax.xml.rpc.Stub)frequenciaPT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.FrequenciaPT getFrequenciaPT() {
    if (frequenciaPT == null)
      _initFrequenciaPTProxy();
    return frequenciaPT;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaModeloPorValorFrequenciaResponse buscarListaModeloPorValorFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaModeloPorValorFrequenciaRequest buscarListaModeloPorValorFrequenciaRequest , String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFrequencia.mc.geral.ErroInfo{
    if (frequenciaPT == null)
      _initFrequenciaPTProxy();
    return frequenciaPT.buscarListaModeloPorValorFrequencia(buscarListaModeloPorValorFrequenciaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.CriarTipoFrequenciaResponse criarTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.CriarTipoFrequenciaRequest criarTipoFrequenciaRequest , String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFrequencia.mc.geral.ErroInfo{
    if (frequenciaPT == null)
      _initFrequenciaPTProxy();
    return frequenciaPT.criarTipoFrequencia(criarTipoFrequenciaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.CriarValorFrequenciaResponse criarValorFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.CriarValorFrequenciaRequest criarValorFrequenciaRequest , String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFrequencia.mc.geral.ErroInfo{
    if (frequenciaPT == null)
      _initFrequenciaPTProxy();
    return frequenciaPT.criarValorFrequencia(criarValorFrequenciaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ExcluirValorFrequenciaResponse excluirValorFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ExcluirValorFrequenciaRequest excluirValorFrequenciaRequest , String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFrequencia.mc.geral.ErroInfo{
    if (frequenciaPT == null)
      _initFrequenciaPTProxy();
    return frequenciaPT.excluirValorFrequencia(excluirValorFrequenciaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaModeloPorTipoFrequenciaResponse buscarListaModeloPorTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaModeloPorTipoFrequenciaRequest buscarListaModeloPorTipoFrequenciaRequest , String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFrequencia.mc.geral.ErroInfo{
    if (frequenciaPT == null)
      _initFrequenciaPTProxy();
    return frequenciaPT.buscarListaModeloPorTipoFrequencia(buscarListaModeloPorTipoFrequenciaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ExcluirTipoFrequenciaResponse excluirTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ExcluirTipoFrequenciaRequest excluirTipoFrequenciaRequest , String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFrequencia.mc.geral.ErroInfo{
    if (frequenciaPT == null)
      _initFrequenciaPTProxy();
    return frequenciaPT.excluirTipoFrequencia(excluirTipoFrequenciaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaModeloPorTecnologiaTipoFrequenciaResponse buscarListaModeloPorTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaModeloPorTecnologiaTipoFrequenciaRequest buscarListaModeloPorTecnologiaTipoFrequenciaRequest , String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFrequencia.mc.geral.ErroInfo{
    if (frequenciaPT == null)
      _initFrequenciaPTProxy();
    return frequenciaPT.buscarListaModeloPorTecnologiaTipoFrequencia(buscarListaModeloPorTecnologiaTipoFrequenciaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse buscarListaValorFrequenciaPorTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest buscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest , String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFrequencia.mc.geral.ErroInfo{
    if (frequenciaPT == null)
      _initFrequenciaPTProxy();
    return frequenciaPT.buscarListaValorFrequenciaPorTecnologiaTipoFrequencia(buscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaTipoFrequenciaComValorPorTecnologiaResponse buscarListaTipoFrequenciaComValorPorTecnologia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaTipoFrequenciaComValorPorTecnologiaRequest buscarListaTipoFrequenciaComValorPorTecnologiaRequest , String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFrequencia.mc.geral.ErroInfo{
    if (frequenciaPT == null)
      _initFrequenciaPTProxy();
    return frequenciaPT.buscarListaTipoFrequenciaComValorPorTecnologia(buscarListaTipoFrequenciaComValorPorTecnologiaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaTipoFrequenciaResponse buscarListaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaTipoFrequenciaRequest buscarListaTipoFrequenciaRequest , String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFrequencia.mc.geral.ErroInfo{
    if (frequenciaPT == null)
      _initFrequenciaPTProxy();
    return frequenciaPT.buscarListaTipoFrequencia(buscarListaTipoFrequenciaRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaValorFrequenciaResponse buscarListaValorFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaValorFrequenciaRequest buscarListaValorFrequenciaRequest , String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFrequencia.mc.geral.ErroInfo{
    if (frequenciaPT == null)
      _initFrequenciaPTProxy();
    return frequenciaPT.buscarListaValorFrequencia(buscarListaValorFrequenciaRequest, username, password);
  }
  
  
}