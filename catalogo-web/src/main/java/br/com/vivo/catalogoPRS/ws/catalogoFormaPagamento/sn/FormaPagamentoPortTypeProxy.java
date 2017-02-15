package br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn;

public class FormaPagamentoPortTypeProxy implements br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.FormaPagamentoPortType {
  private String _endpoint = null;
  private br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.FormaPagamentoPortType formaPagamentoPortType = null;
  
  public FormaPagamentoPortTypeProxy() {
    _initFormaPagamentoPortTypeProxy();
  }
  
  public FormaPagamentoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initFormaPagamentoPortTypeProxy();
  }
  
  private void _initFormaPagamentoPortTypeProxy() {
    try {
      formaPagamentoPortType = (new br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.FormaPagamentoSoapServiceLocator()).getFormaPagamentoSoapPort();
      if (formaPagamentoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)formaPagamentoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)formaPagamentoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (formaPagamentoPortType != null)
      ((javax.xml.rpc.Stub)formaPagamentoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.FormaPagamentoPortType getFormaPagamentoPortType() {
    if (formaPagamentoPortType == null)
      _initFormaPagamentoPortTypeProxy();
    return formaPagamentoPortType;
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaCondPagtoResponse buscarListaFormaCondPagto(br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaCondPagtoRequest buscarListaFormaCondPagtoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.mc.geral.ErroInfo{
    if (formaPagamentoPortType == null)
      _initFormaPagamentoPortTypeProxy();
    return formaPagamentoPortType.buscarListaFormaCondPagto(buscarListaFormaCondPagtoRequest, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaPagamentoResponse buscarListaFormaPagamento(br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaPagamentoRequest parameters, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.mc.geral.ErroInfo{
    if (formaPagamentoPortType == null)
      _initFormaPagamentoPortTypeProxy();
    return formaPagamentoPortType.buscarListaFormaPagamento(parameters, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaCondPagtoPorTipoProdutoResponse buscarListaFormaCondPagtoPorTipoProduto(br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaCondPagtoPorTipoProdutoRequest parameters, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.mc.geral.ErroInfo{
    if (formaPagamentoPortType == null)
      _initFormaPagamentoPortTypeProxy();
    return formaPagamentoPortType.buscarListaFormaCondPagtoPorTipoProduto(parameters, username, password);
  }
  
  public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.AlterarFormaCondPagtoResponse alterarFormaCondPagto(br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.AlterarFormaCondPagtoRequest parameters, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.mc.geral.ErroInfo{
    if (formaPagamentoPortType == null)
      _initFormaPagamentoPortTypeProxy();
    return formaPagamentoPortType.alterarFormaCondPagto(parameters, username, password);
  }
  
  
}