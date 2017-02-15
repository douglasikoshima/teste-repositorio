/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.2.4.2 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/12/08 19:21:30 $
 **/

#ifndef _STWFATENDIMENTOMCPC_H_
	#define _STWFATENDIMENTOMCPC_H_

extern bool proCConsultorRelacionamentoSimNao(int idPessoaUsuario);
extern bool proCObterInfoConsultorAtd(st_AtendimentoFila* dados,st_vlAtendimentoFila* status);

// Funções de pesquisa de fila de processos não atrelados a consultores de relacionamento
extern bool proCConsultaWFFilaMCPorAtendimento(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCPorAtendimentoQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCPorProtocolo(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCPorProtocoloQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCPorProtocoloMC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCPorProtocoloMCQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCAbertosPorLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCAbertosPorLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCFechadosPorLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCFechadosPorLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

//extern bool proCConsultaWFAvanzadaFilaMC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
//extern bool proCConsultaWFAvanzadaFilaMCQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCFechadosLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCFechadosLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCFechadosUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCFechadosUsuarioQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCUsuarioLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCUsuarioLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCUsuarioQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCAbertosPorConta(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCAbertosPorContaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCFechadosPorConta(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCFechadosPorContaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCAbertosPorContaMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCAbertosPorContaQtdMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCAbertosPorLinhaMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCAbertosPorLinhaQtdMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCLinhaMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCLinhaQtdMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCPorAtendimentoMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCPorAtendimentoQtdMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCPorProtocoloMCMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCPorProtocoloMCQtdMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCPorProtocoloMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCPorProtocoloQtdMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCQtdMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCUsuarioLinhaMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCUsuarioLinhaQtdMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCUsuarioMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCUsuarioQtdMP(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultorRelacionamentoSimNaoMP(int idPessoaUsuario);
extern bool proCObterInfoConsultorAtdMP(st_AtendimentoFila* dados,st_vlAtendimentoFila* status);


// Funções de pesquisa de fila de processos atrelados a consultores de relacionamento
/*
extern bool proCConsultaWFFilaMCCRPorAtendimento(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRPorAtendimentoQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCCRPorProtocolo(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRPorProtocoloQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCCRPorProtocoloMC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRPorProtocoloMCQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCCRAbertosPorLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRAbertosPorLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCCRFechadosPorLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRFechadosPorLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFAvanzadaFilaMCCR(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFAvanzadaFilaMCCRQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCCRFechadosLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRFechadosLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCCRFechadosUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRFechadosUsuarioQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCCRUsuarioLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRUsuarioLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCCRLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCCRUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRUsuarioQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCCR(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCCRAbertosPorConta(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRAbertosPorContaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaMCCRFechadosPorConta(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMCCRFechadosPorContaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
*/
#endif

