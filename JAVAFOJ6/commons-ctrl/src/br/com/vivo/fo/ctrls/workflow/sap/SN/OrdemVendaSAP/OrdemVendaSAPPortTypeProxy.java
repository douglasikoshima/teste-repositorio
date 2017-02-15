package br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP;

public class OrdemVendaSAPPortTypeProxy implements br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.OrdemVendaSAPPortType {
  private String _endpoint = null;
  private br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.OrdemVendaSAPPortType ordemVendaSAPPortType = null;
  
  public OrdemVendaSAPPortTypeProxy() {
    _initOrdemVendaSAPPortTypeProxy();
  }
  
  public OrdemVendaSAPPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initOrdemVendaSAPPortTypeProxy();
  }
  
  private void _initOrdemVendaSAPPortTypeProxy() {
    try {
      ordemVendaSAPPortType = (new br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.OrdemVendaSAPBindingQSServiceLocator()).getOrdemVendaSAPBindingQSPort();
      if (ordemVendaSAPPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)ordemVendaSAPPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)ordemVendaSAPPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (ordemVendaSAPPortType != null)
      ((javax.xml.rpc.Stub)ordemVendaSAPPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.OrdemVendaSAPPortType getOrdemVendaSAPPortType() {
    if (ordemVendaSAPPortType == null)
      _initOrdemVendaSAPPortTypeProxy();
    return ordemVendaSAPPortType;
  }
  
  public java.lang.Object atualizarStatusOrdemVenda(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosAtualizarStatusOrdemVenda atualizarStatusOrdemVendaRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.workflow.sap.MC.Geral.ErroInfo{
    if (ordemVendaSAPPortType == null)
      _initOrdemVendaSAPPortTypeProxy();
    return ordemVendaSAPPortType.atualizarStatusOrdemVenda(atualizarStatusOrdemVendaRequest);
  }
  
  public java.lang.Object atualizarCadastroCliente(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosAtualizarCadastroCliente atualizarCadastroClienteRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.workflow.sap.MC.Geral.ErroInfo{
    if (ordemVendaSAPPortType == null)
      _initOrdemVendaSAPPortTypeProxy();
    return ordemVendaSAPPortType.atualizarCadastroCliente(atualizarCadastroClienteRequest);
  }
  
  public br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda.NotaFiscal[] consultarNotaFiscal(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosConsultarNotaFiscal consultarNotaFiscalRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.workflow.sap.MC.Geral.ErroInfo{
    if (ordemVendaSAPPortType == null)
      _initOrdemVendaSAPPortTypeProxy();
    return ordemVendaSAPPortType.consultarNotaFiscal(consultarNotaFiscalRequest);
  }
  
  public br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda.OrdemVenda criarOrdemClienteSAP(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAP criarOrdemClienteSAPRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.workflow.sap.MC.Geral.ErroInfo{
    if (ordemVendaSAPPortType == null)
      _initOrdemVendaSAPPortTypeProxy();
    return ordemVendaSAPPortType.criarOrdemClienteSAP(criarOrdemClienteSAPRequest);
  }
  
  
}