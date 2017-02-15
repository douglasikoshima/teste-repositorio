/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:18 $
 **/

// #include "../../Usuario/include/cWFUsuario.h"
// #include "../../Andamento/include/cWFAndamento.h"
// #include "../../AndamentoMotivo/include/cWFAndamentoMotivo.h"
// #include "../../AndamentoObservacao/include/cWFAndamentoObservacao.h"
// #include "../../AndamentoTransferencia/include/cWFAndamentoTransferencia.h"
// #include "../../Atendimento/include/cWFAtendimento.h"
// #include "../../AtendimentoAndamentoAtual/include/cWFAtendimentoAndamentoAtual.h"
// #include "../../AtendimentoGrupoAtual/include/cWFAtendimentoGrupoAtual.h"
// #include "../../AtendimentoLinha/include/cWFAtendimentoLinha.h"
// #include "../../AtendimentoNivel/include/cWFAtendimentoNivel.h"
// #include "../../AtendimentoPessoa/include/cWFAtendimentoPessoa.h"
// #include "../../AtendimentoSuspeito/include/cWFAtendimentoSuspeito.h"
// #include "../../AtendimentoUsuarioAtual/include/cWFAtendimentoUsuarioAtual.h"
// #include "../../CancelamentoSolicitado/include/cWFCancelamentoSolicitado.h"
// #include "../include/cWFAtdConAnalise.h"
// 
// extern bool proCObterIdTipoLinha(int *_idTipoLinha,int _idPesLinha);
// extern bool proCBuscarDataHora( st_AtdConAnalise *dados );
// extern bool proCBuscarDataHoraSS( st_AtdConAnalise *dados );
// 
// cWFAtdConAnalise::cWFAtdConAnalise(DOMNode *dnode, XMLGen *xml_g)
// {
//     entrada = dnode;
//     saida = xml_g;
// 
//     memset(&m_stDados,0,sizeof(m_stDados));
//     memset(&m_vlDados,-1,sizeof(m_vlDados));
// 
//     memset(&m_stDadosResp,0,sizeof(m_stDadosResp));
//     memset(&m_vlDadosResp,-1,sizeof(m_vlDadosResp));
// 
//     carregaDados();
// }
// 
// bool cWFAtdConAnalise::Executar(const char *idWebUser)
// {
//     ULOG_START("cWFAtdConAnalise::Executar()"); 
// 
//     proCBuscarDataHora( &atdConAnalise );
//     proCBuscarDataHoraSS( &atdConAnalise );
// 
//     m_stDadosResp.UsuarioAtual = ObterValorTag(entrada,"ScriptExecucaoVO","idPessoaUsuario");
//     m_vlDadosResp.UsuarioAtual = 1;
// 
//     m_stDadosResp.idAtendimento = ObterValorTag(entrada,"ScriptExecucaoVO","idAtendimento");
//     m_vlDadosResp.idAtendimento = 1;
// 
//     m_stDadosResp.idAgrEstTPrFt = ObterValorTag(entrada,"ScriptExecucaoVO","idAgrupamentoEstadoTProcFut");
//     m_vlDadosResp.idAgrEstTPrFt = 1;
// 
//     m_stDadosResp.idAgrEstTPrAt = ObterValorTag(entrada,"ScriptExecucaoVO","idAgrupamentoEstadoTProcAt");
//     m_vlDadosResp.idAgrEstTPrAt = 1;
// 
//     m_stDadosResp.idAtividade = ObterValorTag(entrada,"ScriptExecucaoVO","idAtividade");
//     m_vlDadosResp.idAtividade = 1;
// 
//     m_stDadosResp.idFase = ObterValorTag(entrada,"ScriptExecucaoVO","idFase");
//     m_vlDadosResp.idFase = 1;
// 
//     sprintf(m_stDadosResp.urlDestino,"%.*s",sizeof(m_stDadosResp.urlDestino)-1,
//                 ObterValorTag(entrada,"ScriptExecucaoVO","nmUrlDestino"));
// 
//     ObterDetalheAtd();
// 
//     ObterAtendPessoa();
// 
//     ObterDetalheAtendLinha();
// 
// 
//     if ( m_stDadosResp.idPesLinha > 0 )
//     {
//         proCObterIdTipoLinha(&m_stDadosResp.idTipoLinha
//                             ,m_stDadosResp.idPesLinha);
//     }
// 
//     if ( ConsultarAtdUsuAtual() )
//     { // Se a consulta gerou um xml não vazio ...
// 
//         ExcluirAtdUsuAtual();
// 
//         if ( ObterAndamentoTransferencia() )
//         { // Se a consulta gerou um xml não vazio ...
//             
//             AlterarAndamentoTransferencia();
//        }
//     }
// 
//     ConsultarAtdGrpAtual();
// 
//     PesquisarUsuGrpProxNivel();
// 
//     AlterarAtdGrpAtual();
// 
//     ExcluirAtdSuspeito();
// 
//     ExcluirCancSolicitado();
// 
//     IncluirAtdNivel();
// 
//     AlterarAtd();
// 
//     IncluirAndamento();
// 
//     AlterarAtdAtual();
// 
//     IncluirAndamentoObs();
// 
//     m_stDadosResp.idMotivo = ObterValorTag(entrada,"WFMotivoVO","idMotivo");
//     m_vlDadosResp.idMotivo = 1;
// 
//     if ( m_stDadosResp.idMotivo > 0 )
//     {
//         IncluirAndamentoMotivo();
//     }
//
//     saida->createTag("WFAcaoRetornoVO");
//     saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
//         saida->addItem("acaoExecucao","S");
//         saida->addItem("mensagem","Análise Concluída");
//         saida->addItem("urlDestino",m_stDadosResp.urlDestino);
//     saida->closeTag();
// 
//     ULOG_END("cWFAtdConAnalise::Executar()"); 
// 
//     return true;
// }
// 
// void cWFAtdConAnalise::ObterDetalheAtd()
// {
//     ULOG_START("cWFAtdConAnalise::ObterDetalheAtd()"); 
// 
//     char *err=0;
//     cWFAtendimento cwfAtendimento;
//     int tamSaida;
//     XMLGen saidaDetAtd;
// 
//     if ( !cwfAtendimento.ObtemDetalheAtend(m_stDados.idAtendimento,&saidaDetAtd) )
//     {
//         ULOG("cWFAtendimento::ObtemDetalheAtend() falhou");
//     }
//     else
//     {
//         SmallString xmlSaida;
//         xmlSaida += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
//         xmlSaida += saidaDetAtd.retrieveXML(&tamSaida);
//         tamSaida = xmlSaida.Size();
// 
//         XercesDOMParser *pParser = new XercesDOMParser;
//         MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)xmlSaida.c_str(),tamSaida,gerarIDDom());
// 
//         if ( pParser && pMemBuf )
//         {
//             char *wt;
//             pParser->parse(*pMemBuf);
//             DOMNode* pDoc = pParser->getDocument();
// 
//             if ( wt=tx.walkTree(pDoc,"nrNivel",0),wt )
//             {
//                 m_stDadosResp.nrNivel = atoi(wt);
//                 m_vlDadosResp.nrNivel = 1;
// 			    XMLString::release(&wt);
//             }
//             if ( wt=tx.walkTree(pDoc,"idSegmentacao",0),wt )
//             {
//                 m_stDadosResp.idSegmentacao = atoi(wt);
//                 m_vlDadosResp.idSegmentacao = 1;
// 			    XMLString::release(&wt);
//             }
//             if ( wt=tx.walkTree(pDoc,"idTipoCarteira",0),wt )
//             {
//                 m_stDadosResp.idTipoCarteira = atoi(wt);
//                 m_vlDadosResp.idTipoCarteira = 1;
// 			    XMLString::release(&wt);
//             }
//             if ( wt=tx.walkTree(pDoc,"idProcedencia",0),wt )
//             {
//                 m_stDadosResp.idProcedencia = atoi(wt);
//                 m_vlDadosResp.idProcedencia = 1;
// 			    XMLString::release(&wt);
//             }
//             if ( wt=tx.walkTree(pDoc,"idContato",0),wt )
//             {
//                 m_stDadosResp.idContato = atoi(wt);
//                 m_vlDadosResp.idContato = 1;
// 			    XMLString::release(&wt);
//             }
//             if ( wt=tx.walkTree(pDoc,"idGrupoAbertura",0),wt )
//             {
//                 m_stDadosResp.idGrupoAbertura = atoi(wt);
//                 m_vlDadosResp.idGrupoAbertura = 1;
// 			    XMLString::release(&wt);
//             }
//             if ( wt=tx.walkTree(pDoc,"idCanal",0),wt )
//             {
//                 m_stDadosResp.idCanal = atoi(wt);
//                 m_vlDadosResp.idCanal = 1;
// 			    XMLString::release(&wt);
//             }
//         }
//         else
//         {
//             err = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
//         }
// 
//         delete pParser;
//         delete pMemBuf;
//     }
// 
//     if ( err )
//     {
//         ULOGE("%s",mensagemSimples(err));
//         ULOG_END("cWFAtdConAnalise::ObterDetalheAtd()"); 
//         throw new TuxBasicSvcException("00E0000",mensagemSimples(err));
//     }
// 
//     ULOG_END("cWFAtdConAnalise::ObterDetalheAtd()"); 
// }
// 
// void cWFAtdConAnalise::ObterAtendPessoa()
// {
//     ULOG_START("cWFAtdConAnalise::ObterAtendPessoa()"); 
// 
//     int tamSaida;
//     XMLGen saida;
//     cWFAtendimentoPessoa cwfAtendimentoPessoa(entrada, &saida);
//     AtendimentoPessoa ap;
// 
//     if ( !cwfAtendimentoPessoa.ObtemAtendPessoa(&ap) )
//     {
//         ULOG("cWFAtendimentoPessoa::ObtemAtendPessoa() falhou execução");
//         ULOG_END("cWFAtdConAnalise::ObterAtendPessoa()"); 
//         return;
//     }
// 
//     char *xml = saida.retrieveXML(&tamSaida);
// 
//     m_stDadosResp.idTipoPessoa = tamSaida ? ap.idTipoPessoa : 1;
//     m_vlDadosResp.idTipoPessoa = 1;
// 
//     m_stDadosResp.idTipoRel = tamSaida ? ap.inRspAbertura : 0;
//     m_vlDadosResp.idTipoRel = 1;
// 
//     ULOG_END("cWFAtdConAnalise::ObterAtendPessoa()"); 
// }
// 
// void cWFAtdConAnalise::ObterDetalheAtendLinha()
// {
//     ULOG_START("cWFAtdConAnalise::ObterAtendPessoa()"); 
// 
//     int tamSaida;
//     XMLGen saida;
//     cWFAtendimentoLinha cWFAtendimentoLinha;
// 
//     if ( cWFAtendimentoLinha.ObtemDetalheAtend(m_stDados.idAtendimento,&saida) )
//     {
//         m_stDadosResp.idPesLinha = ObterValorTag(saida.retrieveXML(&tamSaida),"idPessoaLinhaHistorico");
//         m_vlDadosResp.idPesLinha = 1;
//     }
//     else
//     {
//         ULOG("cWFAtendimentoLinha::ObtemDetalheAtend() falhou");
//     }
// 
//     ULOG_END("cWFAtdConAnalise::ObterAtendPessoa()"); 
// }
// 
// bool cWFAtdConAnalise::ConsultarAtdUsuAtual()
// {
//     ULOG_START("cWFAtdConAnalise::ConsultarAtdUsuAtual()"); 
// 
//     int tamSaida=0;
//     XMLGen saida;
//     cWFAtendimentoUsuarioAtual cwfAtendimentoUsuarioAtual(entrada,&saida);
// 
//     if ( !cwfAtendimentoUsuarioAtual.consultar() )
//     {
//         ULOG("cWFAtendimentoUsuarioAtual::consultar() falhou");
//     }
//     else
//     {
//         char *p = saida.retrieveXML(&tamSaida);
//     }
// 
//     ULOG_END("cWFAtdConAnalise::ConsultarAtdUsuAtual()"); 
// 
//     return tamSaida ? true : false;
// }
// 
// void cWFAtdConAnalise::ExcluirAtdUsuAtual()
// {
//     ULOG_START("ExcluirAtdUsuAtual()"); 
// 
//     XMLGen saida;
//     cWFAtendimentoUsuarioAtual cwfAtendimentoUsuarioAtual(entrada,&saida);
// 
//     if ( !cwfAtendimentoUsuarioAtual.excluir(&xmlDpr) )
//     {
//         ULOG("cWFAtendimentoUsuarioAtual::excluir() falhou");
//     }
// 
//     ULOG_END("ExcluirAtdUsuAtual()"); 
// }
// 
// bool cWFAtdConAnalise::ObterAndamentoTransferencia()
// {
//     ULOG_START("cWFAtdConAnalise::ObterAndamentoTransferencia()"); 
// 
//     int tamSaida=0;
//     XMLGen saida;
//     cWFAndamentoTransferencia cwfAndamentoTransferencia;
// 
//     if ( cwfAndamentoTransferencia.obtemTrf() )
//     {   
//         char *p = saida.retrieveXML(&tamSaida);
//         m_stDadosResp.idAndamento = ObterValorTag(p,"idAndamento");
//         m_vlDadosResp.idAndamento = 1;
//     }
//     else
//     {
//         ULOG(" obtemTrf() falhou");
//     }
// 
//     ULOG_END("cWFAtdConAnalise::ObterAndamentoTransferencia()"); 
// 
//     return tamSaida ? true : false;
// }
// 
// void cWFAtdConAnalise::AlterarAndamentoTransferencia()
// {
//     ULOG_START("cWFAtdConAnalise::AlterarAndamentoTransferencia()"); 
// 
//     XMLGen saida;
//     
//     st_AndamentoTransferencia dados;
//     st_vlAndamentoTransferencia status;
// 
//     memset(&dados,0,sizeof(dados));
//     memset(&status,-1,sizeof(status));
// 
//     dados.idAndamento = m_stDadosResp.idAndamento;
//     status.idAndamento = m_vlDadosResp.idAndamento;
// 
//     sprintf(dados.dtFinTransferencia,"%.*s",sizeof(dados.dtFinTransferencia)-1,atdConAnalise.dataAtual);
//     status.dtFinTransferencia = 1;
// 
//     cWFAndamentoTransferencia cwfAndamentoTransferencia(&dados,&status,&saida);
// 
//     if ( !cwfAndamentoTransferencia.alterar() )
//     {
//         ULOG("cWFAndamentoTransferencia::alterar() "
//                                                                             "falhou"));
//     }
// 
//     ULOG_END("cWFAtdConAnalise::AlterarAndamentoTransferencia)"); 
// }
// 
// void cWFAtdConAnalise::ConsultarAtdGrpAtual()
// {
//     ULOG("> ConsultarAtdGrpAtual()"));
// 
//     int tamSaida;
//     XMLGen saida;
//     cWFAtendimentoGrupoAtual cwfAtendimentoGrupoAtual(entrada,&saida);
// 
//     if ( cwfAtendimentoGrupoAtual.consultar() )
//     {
//         char *p = saida.retrieveXML(&tamSaida);
// 
//         if ( tamSaida )
//         {
//             m_stDadosResp.idGrupoAtual = ObterValorTag(p,"idGrupo");
//             m_vlDadosResp.idGrupoAtual = 1;
// 
//             m_stDadosResp.idSequencia = ObterValorTag(p,"idSequencia");
//             m_vlDadosResp.idSequencia = 1;
//         }
//     }
//     else
//     {
//         ULOG("cWFAtendimentoGrupoAtual::consultar() "
//                                                                                 "falhou"));
//     }
// 
//     ULOG("< ConsultarAtdGrpAtual()"));
// }
// 
// void cWFAtdConAnalise::PesquisarUsuGrpProxNivel()
// {
//     ULOG_START(" cWFAtdConAnalise::PesquisarUsuGrpProxNivel()"); 
// 
//     cWFUsuario cwfUsuario;
//     int tamSaida=0;
//     st_VariaveisUsuario dados;
//     XMLGen saida;
// 
//     memset(&dados,0,sizeof(dados));
// 
//     dados.idAtendimento = m_stDados.idAtendimento;
//     dados.idSegmentacao = m_stDadosResp.idSegmentacao;
//     dados.idContato = m_stDadosResp.idContato;
//     dados.idTipoCarteira = m_stDadosResp.idTipoCarteira;
//     dados.idProcedencia = m_stDadosResp.idProcedencia;
//     dados.idGrupoAbertura = m_stDadosResp.idGrupoAbertura;
//     dados.idTipoPessoa = m_stDadosResp.idTipoPessoa;
//     dados.idTipoLinha = m_stDadosResp.idTipoLinha;
//     dados.idTipoRelacionamento = m_stDadosResp.idTipoRel;
//     dados.idCanal = m_stDadosResp.idCanal;
// 
//     if ( cwfUsuario.pesqUsuGrpProxNivel(&dados, &saida) )
//     {
//         char *p = saida.retrieveXML(&tamSaida);
// 
//         if ( tamSaida )
//         {
//             SmallString xmlSaida;
//             xmlSaida += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
//             xmlSaida += p;
//             tamSaida = xmlSaida.Size();
// 
//             XercesDOMParser *pParser = new XercesDOMParser;
//             MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)xmlSaida.c_str(),tamSaida,gerarIDDom());
// 
//             if ( pParser && pMemBuf )
//             {
//                 pParser->parse(*pMemBuf);
//                 DOMNode* pDoc = pParser->getDocument();
// 
//                 m_stDadosResp.idGrupoFase = ObterValorTag(pDoc,"xmlGrFase","idGrupo");
//                 m_vlDadosResp.idGrupoFase = 1;
// 
//                 delete pParser;
//                 delete pMemBuf;
//             }
//         }
//         else
//         {
//             ObterNivGrAt();
//         }
//     }
//     else
//     {
//         ULOG("cWFUsuario::pesqUsuGrpProxNivel() "
//                                                                                 "falhou"));
//     }
// 
//     ULOG_END(" cWFAtdConAnalise::PesquisarUsuGrpProxNivel()"); 
// }
// 
// void cWFAtdConAnalise::ObterNivGrAt()
// {
//     ULOG_START(" cWFAtdConAnalise::ObterNivGrAt()"); 
// 
//     int tamSaida;
//     XMLGen saida;
//     cWFAtendimentoNivel cwfAtendimentoNivel;
// 
//     if ( cwfAtendimentoNivel.ObtemNivGrAt(m_stDados.idAtendimento,&saida) )
//     {
//         char *p = saida.retrieveXML(&tamSaida);
// 
//         if ( tamSaida )
//         {
//             SmallString xmlSaida;
//             xmlSaida += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
//             xmlSaida += p;
//             tamSaida = xmlSaida.Size();
// 
//             XercesDOMParser *pParser = new XercesDOMParser;
//             MemBufInputSource *pMemBuf = new MemBufInputSource((const XMLByte*)xmlSaida.c_str(),tamSaida,gerarIDDom());
// 
//             if ( pParser && pMemBuf )
//             {
//                 pParser->parse(*pMemBuf);
//                 DOMNode* pDoc = pParser->getDocument();
// 
//                 m_stDadosResp.idGrupoFase = ObterValorTag(pDoc,"xmlGrFaseSig","idGrupo");
//                 m_vlDadosResp.idGrupoFase = 1;
// 
//                 delete pParser;
//                 delete pMemBuf;
//             }
//         }
//     }
//     else
//     {
//         ULOG("cWFAtendimentoNivel::ObtemNivGrAt() falhou");
//     }
// 
//     ULOG_END(" cWFAtdConAnalise::ObterNivGrAt()"); 
// }
// 
// void cWFAtdConAnalise::AlterarAtdGrpAtual()
// {
//     ULOG_START(" cWFAtdConAnalise::AlterarAtdGrpAtual()"); 
// 
//     XMLGen saida;
//     
//     st_AtendimentoGrupoAtual dados;
//     st_vlAtendimentoGrupoAtual status;
// 
//     memset(&dados,0,sizeof(dados));
//     memset(&status,-1,sizeof(status));
// 
//     dados.idAtendimento = m_stDados.idAtendimento;
//     status.idAtendimento = m_vlDados.idAtendimento;
// 
//     dados.idGrupo = m_stDadosResp.idGrupoFase;
//     status.idGrupo = m_vlDadosResp.idGrupoFase;
// 
//     dados.idUsuarioAlteracao = m_stDadosResp.UsuarioAtual;
//     status.idUsuarioAlteracao = m_vlDadosResp.UsuarioAtual;
// 
//     sprintf(dados.dtUltimaAlteracao,"%.*s",
//             sizeof(dados.dtUltimaAlteracao)-1,atdConAnalise.dataAtual);
//     status.dtUltimaAlteracao = 1;
//         
//     cWFAtendimentoGrupoAtual cwfAtendimentoGrupoAtual(&dados,&status,&saida);
// 
//     if ( !cwfAtendimentoGrupoAtual.alterar(&xmlDpr) )
//     {
//         ULOG("cWFAtendimentoGrupoAtual::alterar() falhou");
//     }
// 
//     ULOG_END(" cWFAtdConAnalise::AlterarAtdGrpAtual()"); 
// }
// 
// void cWFAtdConAnalise::ExcluirAtdSuspeito()
// {
//     ULOG_START(" cWFAtdConAnalise::ExcluirAtdSuspeito()"); 
// 
//     XMLGen saida;
//     
//     st_AtendimentoSuspeito dados;
//     st_vlAtendimentoSuspeito status;
// 
//     memset(&dados,0,sizeof(dados));
//     memset(&status,-1,sizeof(status));
// 
//     dados.idAtendimento = m_stDados.idAtendimento;
//     status.idAtendimento = 1;
// 
//     cWFAtendimentoSuspeito cwfAtendimentoSuspeito(&dados,&status,&saida);
// 
//     if ( !cwfAtendimentoSuspeito.excluir() )
//     {
//         ULOG("cWFAtendimentoSuspeito::excluir() falhou");
//     }
// 
//     ULOG_END(" cWFAtdConAnalise::ExcluirAtdSuspeito()"); 
// }
// 
// void cWFAtdConAnalise::ExcluirCancSolicitado()
// {
//     ULOG_START(" cWFAtdConAnalise::ExcluirCancSolicitado()"); 
// 
//     XMLGen saida;
//     
//     st_CancelamentoSolicitado dados;
//     st_vlCancelamentoSolicitado status;
// 
//     memset(&dados,0,sizeof(dados));
//     memset(&status,-1,sizeof(status));
// 
//     dados.idAtendimento = m_stDados.idAtendimento;
//     status.idAtendimento = 1;
// 
//     cWFCancelamentoSolicitado cwfCancelamentoSolicitado(&dados,&status,&saida);
// 
//     if ( !cwfCancelamentoSolicitado.excluir() )
//     {
//         ULOG("cWFCancelamentoSolicitado::excluir() falhou");
//     }
// 
//     ULOG_END(" cWFAtdConAnalise::ExcluirCancSolicitado()"); 
// }
// 
// void cWFAtdConAnalise::IncluirAtdNivel()
// {
//     ULOG_START(" cWFAtdConAnalise::IncluirAtdNivel()"); 
// 
//     XMLGen saida;
//     
//     st_AtendimentoNivel dados;
//     st_vlAtendimentoNivel status;
// 
//     memset(&dados,0,sizeof(dados));
//     memset(&status,-1,sizeof(status));
// 
//     dados.idAtendimento = m_stDados.idAtendimento;
//     status.idAtendimento = m_vlDados.idAtendimento;
// 
//     dados.idGrupo = m_stDadosResp.idGrupoAtual;
//     status.idGrupo = m_vlDadosResp.idGrupoAtual;
// 
//     dados.idFase = m_stDadosResp.idFase;
//     status.idFase = m_vlDadosResp.idFase;
// 
//     dados.idAtividade = m_stDadosResp.idAtividade;
//     status.idAtividade = m_vlDadosResp.idAtividade;
// 
//     dados.idUsuarioAlteracao = m_stDadosResp.UsuarioAtual;
//     status.idUsuarioAlteracao = m_vlDadosResp.UsuarioAtual;
// 
//     sprintf(dados.dtUltimaAlteracao,"%.*s",
//             sizeof(dados.dtUltimaAlteracao)-1,atdConAnalise.dataAtual);
//     status.dtUltimaAlteracao = 1;
// 
//     cWFAtendimentoNivel cwfAtendimentoNivel(&dados,&status,&saida);
// 
//     if ( !cwfAtendimentoNivel.incluir() )
//     {
//         ULOG("cWFAtendimentoNivel::incluir() falhou");
//     }
// 
//     ULOG_END(" cWFAtdConAnalise::IncluirAtdNivel()"); 
// }
// 
// void cWFAtdConAnalise::AlterarAtd()
// {
//    ULOG_START(" cWFAtdConAnalise::AlterarAtd()"); 
// 
//     XMLGen saida;
// 
//     st_Atendimento dados;
//     st_vlAtendimento status;
// 
//     memset(&dados,0,sizeof(dados));
//     memset(&status,-1,sizeof(status));
// 
//     dados.idAtendimento = m_stDados.idAtendimento;
//     status.idAtendimento = m_vlDados.idAtendimento;
// 
//     dados.nrNivel = m_stDadosResp.nrNivel + 1;
//     status.nrNivel = m_vlDadosResp.nrNivel;
// 
//     dados.idUsuarioAlteracao = m_stDadosResp.UsuarioAtual;
//     status.idUsuarioAlteracao = m_vlDadosResp.UsuarioAtual;
// 
//     cWFAtendimento cwfAtendimento(&dados,&status,&saida);
// 
//     if ( !cwfAtendimento.alterar(&xmlDpr) )
//     {
//         ULOG("cWFAtendimento::alterar() falhou");
//     }
// 
//     ULOG_END(" cWFAtdConAnalise::AlterarAtd()"); 
// }
// 
// void cWFAtdConAnalise::IncluirAndamento()
// {
//     ULOG_START(" cWFAtdConAnalise::IncluirAndamento()"); 
// 
//     XMLGen saida;
// 
//     st_Andamento dados;
//     st_vlAndamento status;
// 
//     memset(&dados,0,sizeof(dados));
//     memset(&status,-1,sizeof(status));
// 
//     dados.idAtendimento = m_stDados.idAtendimento;
//     status.idAtendimento = m_vlDados.idAtendimento;
// 
//     dados.idAtividade = m_stDadosResp.idAtividade;
//     status.idAtividade = m_vlDadosResp.idAtendimento;
// 
//     dados.idAgrupamentoEstadoTpProc = m_stDadosResp.idAgrEstTPrFt;
//     status.idAgrupamentoEstadoTpProc = m_vlDadosResp.idAgrEstTPrFt;
// 
//     dados.idUsuarioAlteracao = m_stDadosResp.UsuarioAtual;
//     status.idUsuarioAlteracao = m_vlDadosResp.UsuarioAtual;
// 
//     dados.idGrupo = m_stDadosResp.idGrupoAtual;
//     status.idGrupo = m_vlDadosResp.idGrupoAtual;
// 
//     sprintf(dados.dtAndamento,"%.*s",
//             sizeof(dados.dtAndamento)-1,atdConAnalise.dataAndamento);
//     status.dtAndamento = 1;
// 
//     sprintf(dados.dtUltimaAlteracao,"%.*s",
//             sizeof(dados.dtUltimaAlteracao)-1,atdConAnalise.dataAtual);
//     status.dtUltimaAlteracao = 1;
// 
//     cWFAndamento cwfAndamento(&dados,&status,&saida);
// 
//     m_stDadosResp.idAndamentoIns = cwfAndamento.incluir(&xmlDpr); // retorna o novo idAndamento
//     m_vlDadosResp.idAndamentoIns = 1;
// 
//     ULOG_END(" cWFAtdConAnalise::IncluirAndamento()"); 
// }
// 
// void cWFAtdConAnalise::AlterarAtdAtual()
// {
//     ULOG_START(" cWFAtdConAnalise::AlterarAtdAtual()"); 
// 
//     XMLGen saida;
// 
//     st_AtendimentoAndamentoAtual dados;
//     st_vlAtendimentoAndamentoAtual status;
// 
//     memset(&dados,0,sizeof(dados));
//     memset(&status,-1,sizeof(status));
// 
//     dados.idAtendimento = m_stDados.idAtendimento;
//     status.idAtendimento = m_vlDados.idAtendimento;
// 
//     dados.idAndamento = m_stDadosResp.idAndamentoIns;
//     status.idAndamento = m_vlDadosResp.idAndamentoIns;
// 
//     dados.idUsuarioAlteracao = m_stDadosResp.UsuarioAtual;
//     status.idUsuarioAlteracao = m_vlDadosResp.UsuarioAtual;
// 
//     sprintf(dados.dtUltimaAlteracao,"%.*s",
//             sizeof(dados.dtUltimaAlteracao)-1,atdConAnalise.dataAtual);
//     status.dtUltimaAlteracao = 1;
// 
//     cWFAtendimentoAndamentoAtual cwfAtendimentoAndamentoAtual(&dados,&status,&saida);
// 
//     if ( !cwfAtendimentoAndamentoAtual.alterar() )
//     {
//         ULOG("cWFAtendimentoAndamentoAtual::alterar() falhou");
//     }
// 
//     ULOG_END(" cWFAtdConAnalise::AlterarAtdAtual()"); 
// }
// 
// void cWFAtdConAnalise::IncluirAndamentoObs()
// {
//     ULOG_start(" cWFAtdConAnalise::IncluirAndamentoObs()"); 
// 
//     char dsAndamentoObservacao[512];
//     XMLGen saida;
// 
//     st_AndamentoObservacao dados;
//     st_vlAndamentoObservacao status;
// 
//     memset(&dados,0,sizeof(dados));
//     memset(&status,-1,sizeof(status));
// 
//     strcpy(dsAndamentoObservacao,
//             ObterValorTag(entrada,"AtendimentoWorkflowComumVO","dsObservacao"));
// 
//     if ( dsAndamentoObservacao[0] )
//     {
//         sprintf(dados.dsAndamentoObservacao,"%.*s"
//                ,sizeof(dados.dsAndamentoObservacao)-1
//                ,dsAndamentoObservacao);
//         dados.pdsAndamentoObservacao = dados.dsAndamentoObservacao;
//         status.dsAndamentoObservacao = 1;
//     }
// 
//     dados.idAndamento = m_stDadosResp.idAndamentoIns;
//     status.idAndamento = m_vlDadosResp.idAndamentoIns;
// 
//     dados.idUsuarioAlteracao = m_stDadosResp.UsuarioAtual;
//     status.idUsuarioAlteracao = m_vlDadosResp.UsuarioAtual;
// 
//     sprintf(dados.dtUltimaAlteracao,"%.*s",
//             sizeof(dados.dtUltimaAlteracao)-1,atdConAnalise.dataAtual);
//     status.dtUltimaAlteracao = 1;
// 
//     cWFAndamentoObservacao cwfAndamentoObservacao(&dados,&status,&saida);
// 
//     if ( !cwfAndamentoObservacao.incluir(&xmlDpr) )
//     {
//         ULOG("cWFAndamentoObservacao::incluir() "falhou");
//     }
// 
//     ULOG_END(" cWFAtdConAnalise::IncluirAndamentoObs()"); 
// }
// 
// void cWFAtdConAnalise::IncluirAndamentoMotivo()
// {
//     ULOG_START(" cWFAtdConAnalise::IncluirAndamentoMotivo()"); 
// 
//     char idMotivo[32];
//     XMLGen saida;
//     
//     st_AndamentoMotivo dados;
//     st_vlAndamentoMotivo status;
// 
//     memset(&dados,0,sizeof(dados));
//     memset(&status,-1,sizeof(status));
// 
//     dados.idAndamento = m_stDadosResp.idAndamentoIns;
//     status.idAndamento = m_vlDadosResp.idAndamentoIns;
// 
//     strcpy(idMotivo,ObterValorTag(entrada,"WFMotivoVO","idMotivo"));
// 
//     if ( idMotivo[0] )
//     {
//         dados.idMotivo = atoi(idMotivo);
//         status.idMotivo = 1;
//     }
// 
//     dados.idUsuarioAlteracao = m_stDadosResp.UsuarioAtual;
//     status.idUsuarioAlteracao = m_vlDadosResp.UsuarioAtual;
// 
//     sprintf(dados.dtUltimaAlteracao,"%.*s",
//             sizeof(dados.dtUltimaAlteracao)-1,atdConAnalise.dataAtual);
//     status.dtUltimaAlteracao = 1;
// 
//     cWFAndamentoMotivo cwfAndamentoMotivo(&dados,&status,&saida);
// 
//     if ( !cwfAndamentoMotivo.incluir() )
//     {
//         ULOG("cWFAndamentoMotivo::incluir() falhou");
//     }
// 
//     ULOG_END(" cWFAtdConAnalise::IncluirAndamentoMotivo()"); 
// }
// 
// void cWFAtdConAnalise::carregaDados()
// {
//     ULOG_START(" cWFAtdConAnalise::carregaDados()"); 
// 
//     char *p;
// 
//     if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
//     {
//         m_stDados.idAtendimento = atol(p);
//         m_vlDados.idAtendimento = 1;
// 		XMLString::release(&p);
//     }
// 
//     ULOG("idAtendimento=%d",m_stDados.idAtendimento);
// 
//     ULOG_END(" cWFAtdConAnalise::carregaDados()"); 
// }
// 
// ////////////////////////////////////////////////////////////////////////////////////////////////
// // OPERAÇÕES DE ACESSO A MÉTODOS DOM / XMLGEN
// //
// char *ObterValorTag::_ObterValorTag(char *ss,const char *idTag)
// {
//     char *p=0;
//     MemBufInputSource *pMemBuf;
//     XercesDOMParser *pParser;
// 
//     valor = 0;
// 
//     if ( !ss )
//     {
//         return 0;
//     }
// 
//     SmallString ssLocal;
// 
//     if ( !strstr(ss,"encoding") )
//     {
//         ssLocal += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
//     }
// 
//     ssLocal += ss;
// 
//     int tamSaida = ssLocal.Size();
// 
//     pParser = new XercesDOMParser;
//     pMemBuf = new MemBufInputSource((const XMLByte*)ssLocal.c_str(), tamSaida, gerarIDDom());
// 
//     if ( pParser && pMemBuf )
//     {
//         pParser->parse(*pMemBuf);
//         DOMNode* pDoc = pParser->getDocument();
// 
//         char *valorResult = tx.walkTree(pDoc,(char*)idTag,0);
// 
//         if ( valorResult && *valorResult ) 
//         {
//             valor = new char [ strlen(valorResult) + 1 ];
//             if ( valor )
//             {
//                 strcpy(valor,valorResult);
//             }
//             else
//             {
//                 p = "Erro de alocação de memória";
//             }
// 
//             ULOG("ObterValorTag(1): valor da tag '%s'=%d"
//                         ,idTag,valorResult);
// 
//             XMLString::release(&valorResult);
//         }
//     }
//     else
//     {
//         p = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
//     }
// 
//     delete pParser;
//     delete pMemBuf;
// 
//     if ( p )
//     {
//         ULOGE("%s",mensagemSimples(p));
//         throw new TuxBasicSvcException("00E0000",mensagemSimples(p));
//     }
// 
//     return valor;
// }
// 
// ////////////////////////////////////////////////////////////////////////////////////////////////
// char *ObterValorTag::_ObterValorTag(SmallString *ss,const char *idTag)
// {
//     char *p=0;
//     MemBufInputSource *pMemBuf;
//     XercesDOMParser *pParser;
// 
//     valor = 0;
// 
//     if ( !ss || !ss->Size() )
//     {
//         return 0;
//     }
// 
//     SmallString ssLocal;
// 
//     if ( !strstr(ss->c_str(),"encoding") )
//     {
//         ssLocal += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
//     }
// 
//     ssLocal += (char*)ss->c_str();
// 
//     int tamSaida = ssLocal.Size();
// 
//     pParser = new XercesDOMParser;
//     pMemBuf = new MemBufInputSource((const XMLByte*)ssLocal.c_str(),tamSaida,gerarIDDom());
// 
//     if ( pParser && pMemBuf )
//     {
//         pParser->parse(*pMemBuf);
//         DOMNode* pDoc = pParser->getDocument();
// 
//         char *valorResult = tx.walkTree(pDoc,(char*)idTag,0);
// 
//         if ( valorResult && *valorResult ) 
//         {
//             valor = new char [ strlen(valorResult) + 1 ];
//             if ( valor )
//             {
//                 strcpy(valor,valorResult);
//             }
//             else
//             {
//                 p = "Erro de alocação de memória";
//             }
// 
//             ULOG("ObterValorTag(1): valor da tag '%s'=%d"
//                         ,idTag,valorResult);
// 
//             XMLString::release(&valorResult);
//         }
//     }
//     else
//     {
//         p = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
//     }
// 
//     delete pParser;
//     delete pMemBuf;
// 
//     if ( p )
//     {
//         ulog(p);
//         throw new TuxBasicSvcException("00E0000",mensagemSimples(p));
//     }
// 
//     return valor;
// }
// 
// ////////////////////////////////////////////////////////////////////////////////////////////////
// char *ObterValorTag::_ObterValorTag(DOMNode *entrada,const char *dnode,const char *idTag)
// {
//     char *p=0;
// 
//     valor = 0;
// 
//     if ( entrada && dnode && idTag )
//     {
//         DOMNode *dn;
//         int index = 0;
// 
//         while ( (dn = tx.walkDOM(entrada,(char*)dnode,index++ )),dn )
//         {
//             char *valorResult = tx.walkTree(dn,(char*)idTag,0);
// 
//             if ( valorResult && *valorResult ) 
//             {
//                 valor = new char [ strlen(valorResult) + 1 ];
//                 if ( valor )
//                 {
//                     strcpy(valor,valorResult);
//                 }
//                 else
//                 {
//                     p = "Erro de alocacao de memoria";
//                 }
// 
//                 ULOG("ObterValorTag(2): valor da tag '%s'=%d"
//                             ,idTag,valorResult);
// 
//                 XMLString::release(&valorResult);
// 
//                 break;
//             }
//         }
//     }
//     else
//     {
//         p = "Ponteiro invalido passado como parametro de entrada";
//     }
// 
//     if ( p )
//     {
//         ULOGE(p);
//         throw new TuxBasicSvcException("00E0000",mensagemSimples(p));
//     }
// 
//     return valor;
// }
// 
// 
// ////////////////////////////////////////////////////////////////////////////////////////////////
// char *ObterValorTag::_ObterValorTag(DOMNode *dn,const char *idTag)
// {
//     char *p=0;
// 
//     valor = 0;
// 
//     if ( dn && idTag )
//     {
//         char *valorResult = tx.walkTree(dn,(char*)idTag,0);
// 
//         if ( valorResult && *valorResult ) 
//         {
//             valor = new char [ strlen(valorResult) + 1 ];
//             if ( valor )
//             {
//                 strcpy(valor,valorResult);
//             }
//             else
//             {
//                 p = "Erro de alocacao de memoria";
//             }
//             ULOG("ObterValorTag(3): valor da tag '%s'=%d"
//                         ,idTag,valorResult);
// 
//             XMLString::release(&valorResult);
//         }
//     }
//     else
//     {
//         p = "Ponteiro invalido passado como parametro de entrada";
//     }
// 
//     if ( p )
//     {
//         ULOGE(p);
//         throw new TuxBasicSvcException("00E0000",mensagemSimples(p));
//     }
// 
//     return valor;
// }
// 
// Fim
////////////////////////////////////////////////////////////////////////////////////////////////
