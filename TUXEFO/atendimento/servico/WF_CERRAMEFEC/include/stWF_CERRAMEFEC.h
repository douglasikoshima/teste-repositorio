/**
 * @modulo  Atendimento
 * @usecase Fechamento em massa
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#ifndef STWF_CERRAMEFEC
#define STWF_CERRAMEFEC

#define CF_NUMERO_MAXIMO_PROCESSOS  20

struct ST_DADOS_PROCESSO
{
	char cidAtendimento[21+1];
	char cidContato[21+1];
	char cidAgrupamentoEstadoTProcFut[21+1];
	char cidAgrupamentoEstadoTProcAt[21+1];
	char cidAtividadeMassa[21+1];
	char cinCri[21+1];
	char cidFase[21+1];
	char cnmUrlDestino[255+1];
	char cidGrupo[21+1];
	char cidMotivo[21+1];
	char cdsObservacao[1000+1];
	char cidPessoaUsuario[21+1];
	char cdtContato[21+1];
	char chrContato[21+1];
	char cidBaixa[21+1];
	char cidMensagemBaixa[21+1];
};

struct ST_DADOS_ENTRADA
{
	char cidUsuario[21+1];
	struct ST_DADOS_PROCESSO stDadosProcesso[CF_NUMERO_MAXIMO_PROCESSOS];
	struct ST_DADOS_PROCESSO stDadosFixos;
	int    iTotalProcessos;
};

struct ST_SYSTEMA
{
	char cData[10+1];
	char cHora[10+1];
	char cDataHora[20+1];
};

struct ST_GETATENDIMENTO
{
	char cidAndamento[21+1];
	char cidAtendimento[21+1];
	char cDataAbertura[21+1];
	char cidContato[21+1]; 
	char cDataPrazoFinalInterno[21+1]; 
	char cidTipoRetornoContato[21+1];
	char cidCanal[21+1]; 
	char cnmCanal[255+1]; 
	char cidProcedencia[21+1]; 
	char cdsProcedencia[255+1]; 
	char cidSegmentacao[21+1]; 
	char cdsSegmentacao[255+1];
	//char cidDiscador[21+1]; 
	char cidFase[21+1]; 
	char cidPessoaUsuarioAbertura[21+1]; 
	char cnmLoginUsuario[255+1]; 
	char cidGrupoAbertura[21+1]; 
	char cnmGrupo[255+1];
	char cidTipoCarteira[21+1]; 
	char cdsTipoCarteira[255+1];
	char cnrNivel[21+1];
	char cqtInsistencia[21+1];
	char cidAgrupamentoEstadoTpProc[21+1];
	char cidEstado[21+1]; 
	char cdsEstado[255+1]; 
	char cidSubestado[21+1]; 
	char cdsSubestado[255+1];
	char ccdConta[255+1];
	char cnrLinha[21+1];
	char cidAtendimentoBaixaHistorico[21+1];
	char cidAtendimentoOrigem[21+1];
	char cDataFechamento[21+1];
	char cidGrupoAndamento[21+1];
	char cINCRI[21+1];
	//Dados vindos da tabela ATENDIMENTOGRUPOATUAL ou ATENDIMENTO.GRUPOCRI
	char cidGrupoAtual[21+1];
	char cINCRIAtual[21+1];
	//Dados vindos da tabela ATENDIMENTO.GRUPOCRI
	char cidPessoaLInhaHistorico[21+1];
    char cidAtendimentoProtocolo[38+1];
};

struct ST_GETATENDIMENTOIND
{
	short sidAndamento;
	short sidAtendimento;
	short sDataAbertura;
	short sidContato; 
	short sDataPrazoFinalInterno; 
	short sidTipoRetornoContato;
	short sidCanal; 
	short snmCanal; 
	short sidProcedencia; 
	short sdsProcedencia; 
	short sidSegmentacao; 
	short sdsSegmentacao;
	//short sidDiscador; 
	short sidFase; 
	short sidPessoaUsuarioAbertura; 
	short snmLoginUsuario; 
	short sidGrupoAbertura; 
	short snmGrupo;
	short sidTipoCarteira; 
	short sdsTipoCarteira;
	short snrNivel;
	short sqtInsistencia;
	short sidAgrupamentoEstadoTpProc;
	short sidEstado; 
	short sdsEstado; 
	short sidSubestado; 
	short sdsSubestado;
	short scdConta;
	short snrLinha;
	short sidAtendimentoBaixaHistorico;
	short sidAtendimentoOrigem;
	short sDataFechamento;
	short sidGrupoAndamento;
	short sINCRI;
	//Dados vindos da tabela ATENDIMENTOGRUPOATUAL ou ATENDIMENTO.GRUPOCRI
	short sidGrupoAtual;
	short sINCRIAtual;
	//Dados vindos da tabela ATENDIMENTO.GRUPOCRI
	short sidPessoaLInhaHistorico;
	short sidAtendimentoProtocolo;
};

struct ST_ATENDIMENTOFORMULARIOCAMPO
{
	char cdsValor[255+1];
	char cidDominio[21+1];
};

struct ST_ATENDIMENTOFORMULARIOCAMPOIND
{
	short sdsValor;
	short sidDominio;
};

struct ST_ATENDIMENTOFORMULARIOCAMPOREG
{
	struct ST_ATENDIMENTOFORMULARIOCAMPO* stCampo;
	int    iQuantidade;
};

struct ST_ATENDIMENTOFORMULARIOREG
{
	struct ST_ATENDIMENTOFORMULARIO* stAtendimentoFrm;
	int    iQuantidade;
};

struct ST_ATENDIMENTOFORMULARIO
{
	char cidAtendimentoFrm[21+1];
	char cidAtendimento[21+1];
	char cidCampo[21+1];
	struct ST_ATENDIMENTOFORMULARIOCAMPOREG stCampoReg;
};

struct ST_ATENDIMENTOFORMULARIOIND
{
	short sidAtendimentoFrm;
	short sidAtendimento;
	short sidCampo;
};

struct stMailBaixaHistorico
{
    long idAtendimento;
    int  idBxa;
    int  inEnvEmail;
    int  inEnvSms;
    int  inEnvTel;
    char dsMsg[256];
    char dsCom[256];
};

struct stMailMensagemBaixa
{
    int  idBxa;
    int  idMsgBxa;
    int  idBaixaMensagem;
    
};

#endif

