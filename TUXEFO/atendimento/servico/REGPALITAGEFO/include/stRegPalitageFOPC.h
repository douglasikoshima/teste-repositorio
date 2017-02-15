/**
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Marcelo Nunes
 * @version $Revision: 1.1.2.3.76.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/07/26 14:53:22 $
 **/

#ifndef STREGPALITAGEFO
    #define STREGPALITAGEFO

struct st_DadosRegPalitagem
{
    char idTipoAberturaProtocolo[4];
    char nrProtocolo[39];
    char cdServico[51];
    char idSistemaOrigem[39];
    char dsComentario[1001];
    char nrTelefone[16];
    char nrTelefoneSMS[16];
    char cdAreaRegistro[4];
    char cdAreaRegistroSMS[4];
    char idCanal[39];
    char idContato[39];
    char idGrupoAbertura[39];
    char idProcedencia[39];
    char idSegmentacao[39];
    char idTipoCarteira[39];
    char idUFOperadora[39];
    char idPessoaDeParaUsuario[39];
    char idPessoaDeParaCliente[39];
    char idTipoPessoa[39];
    char nmPessoa[256];
    char idTipoRelacionamento[39];
    char idLinhaTelefonica[39];
    char idPessoaLinhaHistorico[39];
    char idTipoLinha[39];
    char idUsuario[39];
    char idTerminal[39];
    int cdStatusRejeicao;
    int idContatoFolhaCampo;

    st_DadosRegPalitagem() { memset(this,0,sizeof(st_DadosRegPalitagem)); }
};

#endif

