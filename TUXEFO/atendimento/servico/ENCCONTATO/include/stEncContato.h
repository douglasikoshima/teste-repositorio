/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.5 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:15 $
 **/

#ifndef STENCCONTATO
	#define STENCCONTATO

struct st_EncContato
{
	long idAtendimento;
	int   idUsuarioBKO;
	int   idContato;
    int   idPessoaDeParaCliente;
	int   idAtividade;
	int   idGrupoAbertura;
	char* nrTelefone;
	int   idFase;
	int   idTipoCarteira;
	int   idSegmentacao;
	int   idProcedencia;
	int   idTipoPessoa;
	int   idTipoLinha;
	int   idTipoRelacionamento;
	int   idCanal;
    int   idUFOperadora;
    int   idFormulario;
	int   idAgrupamentoEstadoTpProc;
	char* observacaoFechamento;
	int   idGrupo;
	long   idPessoaLinhaHistorico;
	long idAtendimentoOrigem;
	int   idTipoReaberturaProcesso;
    char* dtAbertura;
    bool  isPortout;
    bool  isVolE;
    char* sgRegraEncaminhamento;
    char* sgFluxoAtendimento;
    char* inConsultor;
    char* nrDocumento;
} ;

struct st_CRI
{
    unsigned long idContato;
    unsigned long idTipoLinha;
    unsigned long idUFOperadora;
    unsigned long idTipoRelacionamento;
    unsigned long idTipoPessoa;
    unsigned long idSegmentacao;
    unsigned long idTipoCarteira;
    unsigned long idCanal;
    unsigned long idProcedencia;
    unsigned long idGrupoAbertura;
    int idTipoReabertura;
//  bool atendimentoPorConta;
} ;


struct st_EncContatoPreValidacao
{
    int inAssociado;
    int idProxGrupo;
    int idSequencia;
    int proximoAgrupamento;

    st_EncContatoPreValidacao() 
    { inAssociado = idProxGrupo = idSequencia = proximoAgrupamento = 0; }
};

#endif
