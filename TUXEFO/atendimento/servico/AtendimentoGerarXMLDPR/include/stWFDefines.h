/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef STWFDEFINES
    #define STWFDEFINES

#include "../../../commons/definesAtendimento.h"

//==============================================================================
// Operações
#define OPCODE_INSERT 1
#define OPCODE_DELETE 3
#define OPCODE_UPDATE 4

//==============================================================================
// Start de agrupamento estado x tipo de processo para processos técnicos
#define INI_AGRPTPPROC_TECNICO 14
#define FIM_AGRPTPPROC_TECNICO 26

//==============================================================================
// Nome de VOs

#define ANDAMENTOVO                     "AndamentoVO"
#define TGO_ANDAMENTOVO                 "<"ANDAMENTOVO">"
#define TGC_ANDAMENTOVO                 "<"ANDAMENTOVO"/>"

#define ANDAMENTOOBSERVACAOVO            "AndamentoObservacaoVO"
#define TGO_ANDAMENTOOBSERVACAOVO        "<"ANDAMENTOOBSERVACAOVO">"
#define TGC_ANDAMENTOOBSERVACAOVO        "<"ANDAMENTOOBSERVACAOVO"/>"

#define ATENDIMENTOBAIXAHISTORICOVO     "AtendimentoBaixaHistoricoVO"
#define TGO_ATENDIMENTOBAIXAHISTORICOVO "<"ATENDIMENTOBAIXAHISTORICOVO">"
#define TGC_ATENDIMENTOBAIXAHISTORICOVO "<"ATENDIMENTOBAIXAHISTORICOVO"/>"

#define ATENDIMENTOCONTATOCOMUNICVO     "AtendimentoContatoComunicVO"
#define TGO_ATENDIMENTOCONTATOCOMUNICVO "<"ATENDIMENTOCONTATOCOMUNICVO">"
#define TGC_ATENDIMENTOCONTATOCOMUNICVO "<"ATENDIMENTOCONTATOCOMUNICVO"/>"

#define ATENDIMENTOFECHAMENTOVO         "AtendimentoFechamentoVO"
#define TGO_ATENDIMENTOFECHAMENTOVO     "<"ATENDIMENTOFECHAMENTOVO">"
#define TGC_ATENDIMENTOFECHAMENTOVO     "<"ATENDIMENTOFECHAMENTOVO"/>"

#define ATENDIMENTOFRMCAMPOVO           "AtendimentoFRMCampoVO"
#define TGO_ATENDIMENTOFRMCAMPOVO       "<"ATENDIMENTOFRMCAMPOVO">"
#define TGC_ATENDIMENTOFRMCAMPOVO       "<"ATENDIMENTOFRMCAMPOVO"/>"

#define ATENDIMENTOFRMVO                "AtendimentoFRMVO"
#define TGO_ATENDIMENTOFRMVO            "<"ATENDIMENTOFRMVO">"
#define TGC_ATENDIMENTOFRMVO            "<"ATENDIMENTOFRMVO"/>"

#define ATENDIMENTOGRUPOATUALVO         "AtendimentoGrupoAtualVO"
#define TGO_ATENDIMENTOGRUPOATUALVO     "<"ATENDIMENTOGRUPOATUALVO">"
#define TGC_ATENDIMENTOGRUPOATUALVO     "<"ATENDIMENTOGRUPOATUALVO"/>"

#define ATENDIMENTOMENSAGEMVO           "AtendimentoMensagemVO"
#define TGO_ATENDIMENTOMENSAGEMVO       "<"ATENDIMENTOMENSAGEMVO">"
#define TGC_ATENDIMENTOMENSAGEMVO       "<"ATENDIMENTOMENSAGEMVO"/>"

#define ATENDIMENTOPESSOAVO             "AtendimentoPessoaVO"
#define TGO_ATENDIMENTOPESSOAVO         "<"ATENDIMENTOPESSOAVO">"
#define TGC_ATENDIMENTOPESSOAVO         "<"ATENDIMENTOPESSOAVO"/>"

#define ATENDIMENTOTESTEVO              "AtendimentoTesteVO"
#define TGO_ATENDIMENTOTESTEVO          "<"ATENDIMENTOTESTEVO">"
#define TGC_ATENDIMENTOTESTEVO          "<"ATENDIMENTOTESTEVO"/>"

#define ATENDIMENTOUSUARIOATUALVO       "AtendimentoUsuarioAtualVO"
#define TGO_ATENDIMENTOUSUARIOATUALVO   "<"ATENDIMENTOUSUARIOATUALVO">"
#define TGC_ATENDIMENTOUSUARIOATUALVO   "<"ATENDIMENTOUSUARIOATUALVO"/>"

#define ATENDIMENTOVO                   "AtendimentoVO"
#define TGO_ATENDIMENTOVO               "<"ATENDIMENTOVO">"
#define TGC_ATENDIMENTOVO               "<"ATENDIMENTOVO"/>"

#define ATENDIMENTOCONTATOVO            "AtendimentoContatoVO"
#define TGO_ATENDIMENTOCONTATOVO        "<"ATENDIMENTOCONTATOVO">"
#define TGC_ATENDIMENTOCONTATOVO        "<"ATENDIMENTOCONTATOVO"/>"

#define ATENDIMENTOHIERARQUIAVO         "AtendimentoHierarquiaVO"
#define TGO_ATENDIMENTOHIERARQUIAVO     "<"ATENDIMENTOHIERARQUIAVO">"
#define TGC_ATENDIMENTOHIERARQUIAVO     "<"ATENDIMENTOHIERARQUIAVO"/>"

#define ATENDIMENTOLINHAVO              "AtendimentoLinhaVO"
#define TGO_ATENDIMENTOLINHAVO          "<"ATENDIMENTOLINHAVO">"
#define TGC_ATENDIMENTOLINHAVO          "<"ATENDIMENTOLINHAVO"/>"

#define ATENDIMENTOOBSERVACAOVO         "AtendimentoObservacaoVO"
#define TGO_ATENDIMENTOOBSERVACAOVO     "<"ATENDIMENTOOBSERVACAOVO">"
#define TGC_ATENDIMENTOOBSERVACAOVO     "<"ATENDIMENTOOBSERVACAOVO"/>"

#define ATENDIMENTOORIGEMVO             "AtendimentoOrigemVO"
#define TGO_ATENDIMENTOORIGEMVO         "<"ATENDIMENTOORIGEMVO">"
#define TGC_ATENDIMENTOORIGEMVO         "<"ATENDIMENTOORIGEMVO"/>"

#define CLIENTEVO                       "ClienteVO"
#define TGO_CLIENTEVO                   "<"CLIENTEVO">"
#define TGC_CLIENTEVO                   "<"CLIENTEVO"/>"

#define PESSOADEPARAVO                  "PessoaDeParaVO"
#define TGO_PESSOADEPARAVO              "<"PESSOADEPARAVO">"
#define TGC_PESSOADEPARAVO              "<"PESSOADEPARAVO"/>"

#define PESSOALINHAHISTORICOVO          "PessoaLinhaHistoricoVO"
#define TGO_PESSOALINHAHISTORICOVO      "<"PESSOALINHAHISTORICOVO">"
#define TGC_PESSOALINHAHISTORICOVO      "<"PESSOALINHAHISTORICOVO"/>"

#define PESSOAVO                        "PessoaVO"
#define TGO_PESSOAVO                    "<"PESSOAVO">"
#define TGC_PESSOAVO                    "<"PESSOAVO"/>"

#define USUARIOVO                       "UsuarioVO"
#define TGO_USUARIOVO                   "<"USUARIOVO">"
#define TGC_USUARIOVO                   "<"USUARIOVO"/>"


//==============================================================================
// Nome de Tags

#define CDAREAREGISTRO                  "cdAreaRegistro"
#define TGO_CDAREAREGISTRO              "<"CDAREAREGISTRO">"
#define TGC_CDAREAREGISTRO              "<"CDAREAREGISTRO"/>"

#define DSANDAMENTOOBSERVACAO           "dsAndamentoObservacao"
#define TGO_DSANDAMENTOOBSERVACAO       "<"DSANDAMENTOOBSERVACAO">"
#define TGC_DSANDAMENTOOBSERVACAO       "<"DSANDAMENTOOBSERVACAO"/>"

#define DSCOMUNICACAO                   "dsComunicacao"
#define TGO_DSCOMUNICACAO               "<"DSCOMUNICACAO">"
#define TGC_DSCOMUNICACAO               "<"DSCOMUNICACAO"/>"

#define DSOBSERVACAO                    "dsObservacao"
#define TGO_DSOBSERVACAO                "<"DSOBSERVACAO">"
#define TGC_DSOBSERVACAO                "<"DSOBSERVACAO"/>"

#define DSVALOR                         "dsValor"
#define TGO_DSVALOR                     "<"DSVALOR">"
#define TGC_DSVALOR                     "<"DSVALOR"/>"

#define DTABERTURA                      "dtAbertura"
#define TGO_DTABERTURA                  "<"DTABERTURA">"
#define TGC_DTABERTURA                  "<"DTABERTURA"/>"

#define DTANDAMENTO                     "dtAndamento"
#define TGO_DTANDAMENTO                 "<"DTANDAMENTO">"
#define TGC_DTANDAMENTO                 "<"DTANDAMENTO"/>"

#define DTBAIXA                         "dtBaixa"
#define TGO_DTBAIXA                     "<"DTBAIXA">"
#define TGC_DTBAIXA                     "<"DTBAIXA"/>"

#define DTFECHAMENTO                    "dtFechamento"
#define TGO_DTFECHAMENTO                "<"DTFECHAMENTO">"
#define TGC_DTFECHAMENTO                "<"DTFECHAMENTO"/>"

#define DTMENSAGEM                      "dtMensagem"
#define TGO_DTMENSAGEM                  "<"DTMENSAGEM">"
#define TGC_DTMENSAGEM                  "<"DTMENSAGEM"/>"

#define DTPRAZOFINALINTERNO             "dtPrazoFinalInterno"
#define TGO_DTPRAZOFINALINTERNO         "<"DTPRAZOFINALINTERNO">"
#define TGC_DTPRAZOFINALINTERNO         "<"DTPRAZOFINALINTERNO"/>"

#define DTRELACIONAMENTO                "dtRelacionamento"
#define TGO_DTRELACIONAMENTO            "<"DTRELACIONAMENTO">"
#define TGC_DTRELACIONAMENTO            "<"DTRELACIONAMENTO"/>"

#define DTTESTE                         "dtTeste"
#define TGO_DTTESTE                     "<"DTTESTE">"
#define TGC_DTTESTE                     "<"DTTESTE"/>"

#define IDAGRUPAMENTOESTADOTPPROC       "idAgrupamentoEstadoTpProc"
#define TGO_IDAGRUPAMENTOESTADOTPPROC   "<"IDAGRUPAMENTOESTADOTPPROC">"
#define TGC_IDAGRUPAMENTOESTADOTPPROC   "<"IDAGRUPAMENTOESTADOTPPROC"/>"

#define IDANDAMENTO                     "idAndamento"
#define TGO_IDANDAMENTO                 "<"IDANDAMENTO">"
#define TGC_IDANDAMENTO                 "<"IDANDAMENTO"/>"

#define IDATENDIMENTO                   "idAtendimento"
#define TGO_IDATENDIMENTO               "<"IDATENDIMENTO">"
#define TGC_IDATENDIMENTO               "<"IDATENDIMENTO"/>"

#define IDATENDIMENTOBAIXAHISTORICO     "idAtendimentoBaixaHistorico"
#define TGO_IDATENDIMENTOBAIXAHISTORICO  "<"IDATENDIMENTOBAIXAHISTORICO">"
#define TGC_IDATENDIMENTOBAIXAHISTORICO  "<"IDATENDIMENTOBAIXAHISTORICO"/>"

#define IDATENDIMENTOCONTATOCOMUNIC     "idAtendimentoContatoComunic"
#define TGO_IDATENDIMENTOCONTATOCOMUNIC  "<"IDATENDIMENTOCONTATOCOMUNIC">"
#define TGC_IDATENDIMENTOCONTATOCOMUNIC  "<"IDATENDIMENTOCONTATOCOMUNIC"/>"

#define IDATENDIMENTODPR                "idAtendimentoDPR"
#define TGO_IDATENDIMENTODPR            "<"IDATENDIMENTODPR">"
#define TGC_IDATENDIMENTODPR            "<"IDATENDIMENTODPR"/>"

#define IDATENDIMENTOFRM                "idAtendimentoFrm"
#define TGO_IDATENDIMENTOFRM            "<"IDATENDIMENTOFRM">"
#define TGC_IDATENDIMENTOFRM            "<"IDATENDIMENTOFRM"/>"

#define IDATENDIMENTOFRMCAMPO           "idAtendimentoFrmCampo"
#define TGO_IDATENDIMENTOFRMCAMPO       "<"IDATENDIMENTOFRMCAMPO">"
#define TGC_IDATENDIMENTOFRMCAMPO       "<"IDATENDIMENTOFRMCAMPO"/>"

#define IDATENDIMENTOMENSAGEM           "idAtendimentoMensagem"
#define TGO_IDATENDIMENTOMENSAGEM       "<"IDATENDIMENTOMENSAGEM">"
#define TGC_IDATENDIMENTOMENSAGEM       "<"IDATENDIMENTOMENSAGEM"/>"

#define IDATENDIMENTOORIGEM             "idAtendimentoOrigem"
#define TGO_IDATENDIMENTOORIGEM         "<"IDATENDIMENTOORIGEM">"
#define TGC_IDATENDIMENTOORIGEM         "<"IDATENDIMENTOORIGEM"/>"

#define IDATENDIMENTOPAI                "idAtendimentoPai"
#define TGO_IDATENDIMENTOPAI            "<"IDATENDIMENTOPAI">"
#define TGC_IDATENDIMENTOPAI            "<"IDATENDIMENTOPAI"/>"

#define IDATENDIMENTOPESSOA             "idAtendimentoPessoa"
#define TGO_IDATENDIMENTOPESSOA         "<"IDATENDIMENTOPESSOA">"
#define TGC_IDATENDIMENTOPESSOA         "<"IDATENDIMENTOPESSOA"/>"

#define IDATENDIMENTOTESTE              "idAtendimentoTeste"
#define TGO_IDATENDIMENTOTESTE          "<"IDATENDIMENTOTESTE">"
#define TGC_IDATENDIMENTOTESTE          "<"IDATENDIMENTOTESTE"/>"

#define IDATIVIDADE                     "idAtividade"
#define TGO_IDATIVIDADE                 "<"IDATIVIDADE">"
#define TGC_IDATIVIDADE                 "<"IDATIVIDADE"/>"

#define IDBAIXA                         "idBaixa"
#define TGO_IDBAIXA                     "<"IDBAIXA">"
#define TGC_IDBAIXA                     "<"IDBAIXA"/>"

#define IDCAMPO                         "idCampo"
#define TGO_IDCAMPO                     "<"IDCAMPO">"
#define TGC_IDCAMPO                     "<"IDCAMPO"/>"

#define IDCANAL                         "idCanal"
#define TGO_IDCANAL                     "<"IDCANAL">"
#define TGC_IDCANAL                     "<"IDCANAL"/>"

#define IDCONTATO                       "idContato"
#define TGO_IDCONTATO                   "<"IDCONTATO">"
#define TGC_IDCONTATO                   "<"IDCONTATO"/>"

#define IDDOMINIO                       "idDominio"
#define TGO_IDDOMINIO                   "<"IDDOMINIO">"
#define TGC_IDDOMINIO                   "<"IDDOMINIO"/>"

#define IDESTADOLINHA                   "idEstadoLinha"
#define TGO_IDESTADOLINHA               "<"IDESTADOLINHA">"
#define TGC_IDESTADOLINHA               "<"IDESTADOLINHA"/>"

#define IDGRUPO                         "idGrupo"
#define TGO_IDGRUPO                     "<"IDGRUPO">"
#define TGC_IDGRUPO                     "<"IDGRUPO"/>"

#define IDLINHATELEFONICA               "idLinhaTelefonica"
#define TGO_IDLINHATELEFONICA           "<"IDLINHATELEFONICA">"
#define TGC_IDLINHATELEFONICA           "<"IDLINHATELEFONICA"/>"

#define IDMENSAGEMATENDIMENTO           "idMensagemAtendimento"
#define TGO_IDMENSAGEMATENDIMENTO       "<"IDMENSAGEMATENDIMENTO">"
#define TGC_IDMENSAGEMATENDIMENTO       "<"IDMENSAGEMATENDIMENTO"/>"

#define IDPESSOA                        "idPessoa"
#define TGO_IDPESSOA                    "<"IDPESSOA">"
#define TGC_IDPESSOA                    "<"IDPESSOA"/>"

#define IDPESSOADEPARA                  "idPessoaDePara"
#define TGO_IDPESSOADEPARA              "<"IDPESSOADEPARA">"
#define TGC_IDPESSOADEPARA              "<"IDPESSOADEPARA"/>"

#define IDPESSOALINHAHISTORICO          "idPessoaLinhaHistorico"
#define TGO_IDPESSOALINHAHISTORICO      "<"IDPESSOALINHAHISTORICO">"
#define TGC_IDPESSOALINHAHISTORICO      "<"IDPESSOALINHAHISTORICO"/>"

#define IDPESSOAORIGEM                  "idPessoaOrigem"
#define TGO_IDPESSOAORIGEM              "<"IDPESSOAORIGEM">"
#define TGC_IDPESSOAORIGEM              "<"IDPESSOAORIGEM"/>"

#define IDPESSOAUSUARIO                 "idPessoaUsuario"
#define TGO_IDPESSOAUSUARIO             "<"IDPESSOAUSUARIO">"
#define TGC_IDPESSOAUSUARIO             "<"IDPESSOAUSUARIO"/>"

#define IDPROCEDENCIA                   "idProcedencia"
#define TGO_IDPROCEDENCIA               "<"IDPROCEDENCIA">"
#define TGC_IDPROCEDENCIA               "<"IDPROCEDENCIA"/>"

#define IDSEGMENTACAO                   "idSegmentacao"
#define TGO_IDSEGMENTACAO               "<"IDSEGMENTACAO">"
#define TGC_IDSEGMENTACAO               "<"IDSEGMENTACAO"/>"

#define IDTIPOCARTEIRA                  "idTipoCarteira"
#define TGO_IDTIPOCARTEIRA              "<"IDTIPOCARTEIRA">"
#define TGC_IDTIPOCARTEIRA              "<"IDTIPOCARTEIRA"/>"

#define IDTIPOCOMUNICACAO               "idTipoComunicacao"
#define TGO_IDTIPOCOMUNICACAO           "<"IDTIPOCOMUNICACAO">"
#define TGC_IDTIPOCOMUNICACAO           "<"IDTIPOCOMUNICACAO"/>"

#define IDTIPOPESSOA                    "idTipoPessoa"
#define TGO_IDTIPOPESSOA                "<"IDTIPOPESSOA">"
#define TGC_IDTIPOPESSOA                "<"IDTIPOPESSOA"/>"

#define IDTIPORELACIONAMENTO            "idTipoRelacionamento"
#define TGO_IDTIPORELACIONAMENTO        "<"IDTIPORELACIONAMENTO">"
#define TGC_IDTIPORELACIONAMENTO        "<"IDTIPORELACIONAMENTO"/>"

#define NMCONTATO                       "nmContato"
#define TGO_NMCONTATO                   "<"NMCONTATO">"
#define TGC_NMCONTATO                   "<"NMCONTATO"/>"

#define NMPESSOA                        "nmPessoa"
#define TGO_NMPESSOA                    "<"NMPESSOA">"
#define TGC_NMPESSOA                    "<"NMPESSOA"/>"

#define NRLINHA                         "nrLinha"
#define TGO_NRLINHA                     "<"NRLINHA">"
#define TGC_NRLINHA                     "<"NRLINHA"/>"

#define NRORDEMUTILIZACAO               "nrOrdemutilizacao"
#define TGO_NRORDEMUTILIZACAO           "<"NRORDEMUTILIZACAO">"
#define TGC_NRORDEMUTILIZACAO           "<"NRORDEMUTILIZACAO"/>"

#define OPCODE                          "opCode"
#define TGO_OPCODE                      "<"OPCODE">"
#define TGC_OPCODE                      "<"OPCODE"/>"

#define QTINSISTENCIA                   "qtInsistencia"
#define TGO_QTINSISTENCIA               "<"QTINSISTENCIA">"
#define TGC_QTINSISTENCIA               "<"QTINSISTENCIA"/>"

#define VLPESOATENDIMENTO               "vlPesoAtendimento"
#define TGO_VLPESOATENDIMENTO           "<"VLPESOATENDIMENTO">"
#define TGC_VLPESOATENDIMENTO           "<"VLPESOATENDIMENTO"/>"

#endif
