/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:05 $
 **/

#ifndef CMSGERROREGCONTATO
	#define CMSGERROREGCONTATO

// NE = NÃO ENCONTRADO

#define E_PZATD_NE "07E0002"
#define M_PZATD_NE "Prazo de atendimento não encontrado."

#define E_PSATD_NE "07W0003"
#define M_PSATD_NE "Peso de priorização do atendimento não encontrado"

#define E_PZANA_NE "07E0004"
#define M_PZANA_NE "Prazo ANATEL não encontrado na tabela de parametros"

#define E_IDESL_NE "07E0005"
#define M_IDESL_NE "O idEstadoLinha não foi encontrado"

#define E_IDTPL_NE "07E0006"
#define M_IDTPL_NE "O idTipoLinha não foi encontrado"

#define E_IDSEQ_NE "07E0007"
#define M_IDSEQ_NE "Identificador da Sequencia de Abertura não foi encontrado"

#define E_PZAIN_NE "07W0008"
#define M_PZAIN_NE "Prazo de atendimento não encontrado ou com valor menor que 1."

#define E_PCPIN_NE "07E0009"
#define M_PCPIN_NE "Prazo de contato prévio não encontrado ou com valor menor que 1."

#define E_DIAUT_NE "07W0010"
#define M_DIAUT_NE "Tabela de dias úteis não configurada para o código de área da linha informada"

#define E_CONTA_NE "07W0011"
#define M_CONTA_NE "Não foram encontrados dados da conta do cliente"

#define E_FCHPRXESTADO_NE "07W0012"
#define M_FCHPRXESTADO_NE "Não encontrado próximo estado para o contato. Verifique parametrização."

#define E_IDMSGBAIXA_NE "07W0013"
#define M_IDMSGBAIXA_NE "Identificador de Mensagem de Baixa não encontrado"

#define E_ENCPRXESTADO_NE "07W0014"
#define M_ENCPRXESTADO_NE "Encaminhamento: Não encontrado próximo estado para o contato. Verifique parametrização."

#define E_PREVLDPXGRU_NE "07W0015"
#define M_PREVLDPXGRU_NE "Próximo Grupo de Tratamento não foi associado, verifique a parametrização"

#define E_PREVLDSQTRA_NE "07W0016"
#define M_PREVLDSQTRA_NE "Identificador de Sequência para próximo grupo de tratamento, não encontrado. Verifique parametrização."

#define E_CDPESSOADEPARA_NE "07W0017"
#define M_CDPESSOADEPARA_NE "Identificador Pessoa de Para, não foi encontrado."

#define E_CDLINHAATENDIM_NE "07W0018"
#define M_CDLINHAATENDIM_NE "Identificador Linha de Atendimento, não foi encontrado."

#define E_CDESTADOLINHA_NE "07W0019"
#define M_CDESTADOLINHA_NE "Identificador Estado da Linha, não foi encontrado."

#define E_CDTIPOLINHA_NE "07W0020"
#define M_CDTIPOLINHA_NE "Identificador Tipo da Linha, não foi encontrado."

#define E_CDPESSOALINHAHIST_NE "07W0021"
#define M_CDPESSOALINHAHIST_NE "Identificador Pessoa Linha Histórico, não foi encontrado."

#define E_TAGIDCONTATO_NE "07W0022"
#define M_TAGIDCONTATO_NE "Valor da tag 'idContato' nao informado no XML de entrada."

#define E_TAGIDSEGMENTACAO_NE "07W0023"
#define M_TAGIDSEGMENTACAO_NE "Valor da tag 'idSegmentacao' nao informado no XML de entrada."

#define E_TAGIDPROCEDENCIA_NE "07W0024"
#define M_TAGIDPROCEDENCIA_NE "Valor da tag 'idProcedencia' nao informado no XML de entrada."

#define E_TAGNRTELCONTATO_NE "07W0025"
#define M_TAGNRTELCONTATO_NE "Número de telefone do contato não informado no XML de entrada."

#define E_GRPTRATPOUT_NE "07W0026"
#define M_GRPTRATPOUT_NE "Grupo inicial de tratamento de processo de portabilidade não informado."

#define E_NRPROTOPOUT_NE "07W0027"
#define M_NRPROTOPOUT_NE "Número de protocolo de portout obrigatório e não informado."

#define E_JANELAPOUT_NE "07W0028"
#define M_JANELAPOUT_NE "Janela de portout obrigatória e não informada."

#define E_OPERSOLICPOUT_NE "07W0029"
#define M_OPERSOLICPOUT_NE "Operadora solicitante é campo obrigatório para processo de portout."

#define E_PALITPORTOUT_NE "07W0030"
#define M_PALITPORTOUT_NE "Não é permitido palitar processos de portout"

#define E_PALITAGEMOUT_NE "07W0031"
#define M_PALITAGEMOUT_NE "O tipo de abertura informado não condiz com o tipo de abertura original do protocolo"

#endif

