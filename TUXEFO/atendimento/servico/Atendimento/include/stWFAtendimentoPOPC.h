/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:44 $
 **/

#ifndef _STWFATENDIMENTOPOPC_H_
	#define _STWFATENDIMENTOPOPC_H_

// Funções de pesquisa de fila de processos não atrelados a consultores de relacionamento
extern bool proCConsultorRelacionamentoSimNao(int idPessoaUsuario);

extern bool proCConsultaWFFilaPOPorAtendimento(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOPorAtendimentoQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOPorProtocolo(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOPorProtocoloQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOPorProtocoloPO(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOPorProtocoloPOQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOAbertosPorLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOAbertosPorLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOFechadosPorLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOFechadosPorLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFAvanzadaFilaPO(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFAvanzadaFilaPOQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOFechadosLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOFechadosLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOFechadosUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOFechadosUsuarioQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOUsuarioLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOUsuarioLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOUsuarioQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPO(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOAbertosPorConta(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOAbertosPorContaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOFechadosPorConta(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOFechadosPorContaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

// Funções de pesquisa de fila de processos atrelados a consultores de relacionamento
extern bool proCConsultaWFFilaPOCRPorAtendimento(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRPorAtendimentoQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOCRPorProtocolo(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRPorProtocoloQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOCRPorProtocoloPO(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRPorProtocoloPOQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOCRAbertosPorLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRAbertosPorLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOCRFechadosPorLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRFechadosPorLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFAvanzadaFilaPOCR(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFAvanzadaFilaPOCRQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOCRFechadosLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRFechadosLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOCRFechadosUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRFechadosUsuarioQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOCRUsuarioLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRUsuarioLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOCRLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOCRUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRUsuarioQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOCR(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOCRAbertosPorConta(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRAbertosPorContaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPOCRFechadosPorConta(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPOCRFechadosPorContaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

#endif

