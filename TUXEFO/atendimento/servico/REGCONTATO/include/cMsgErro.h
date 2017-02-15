/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:05 $
 **/

#ifndef CMSGERROREGCONTATO
	#define CMSGERROREGCONTATO

// NE = N�O ENCONTRADO

#define E_PZATD_NE "07E0002"
#define M_PZATD_NE "Prazo de atendimento n�o encontrado."

#define E_PSATD_NE "07W0003"
#define M_PSATD_NE "Peso de prioriza��o do atendimento n�o encontrado"

#define E_PZANA_NE "07E0004"
#define M_PZANA_NE "Prazo ANATEL n�o encontrado na tabela de parametros"

#define E_IDESL_NE "07E0005"
#define M_IDESL_NE "O idEstadoLinha n�o foi encontrado"

#define E_IDTPL_NE "07E0006"
#define M_IDTPL_NE "O idTipoLinha n�o foi encontrado"

#define E_IDSEQ_NE "07E0007"
#define M_IDSEQ_NE "Identificador da Sequencia de Abertura n�o foi encontrado"

#define E_PZAIN_NE "07W0008"
#define M_PZAIN_NE "Prazo de atendimento n�o encontrado ou com valor menor que 1."

#define E_PCPIN_NE "07E0009"
#define M_PCPIN_NE "Prazo de contato pr�vio n�o encontrado ou com valor menor que 1."

#define E_DIAUT_NE "07W0010"
#define M_DIAUT_NE "Tabela de dias �teis n�o configurada para o c�digo de �rea da linha informada"

#define E_CONTA_NE "07W0011"
#define M_CONTA_NE "N�o foram encontrados dados da conta do cliente"

#define E_FCHPRXESTADO_NE "07W0012"
#define M_FCHPRXESTADO_NE "N�o encontrado pr�ximo estado para o contato. Verifique parametriza��o."

#define E_IDMSGBAIXA_NE "07W0013"
#define M_IDMSGBAIXA_NE "Identificador de Mensagem de Baixa n�o encontrado"

#define E_ENCPRXESTADO_NE "07W0014"
#define M_ENCPRXESTADO_NE "Encaminhamento: N�o encontrado pr�ximo estado para o contato. Verifique parametriza��o."

#define E_PREVLDPXGRU_NE "07W0015"
#define M_PREVLDPXGRU_NE "Pr�ximo Grupo de Tratamento n�o foi associado, verifique a parametriza��o"

#define E_PREVLDSQTRA_NE "07W0016"
#define M_PREVLDSQTRA_NE "Identificador de Sequ�ncia para pr�ximo grupo de tratamento, n�o encontrado. Verifique parametriza��o."

#define E_CDPESSOADEPARA_NE "07W0017"
#define M_CDPESSOADEPARA_NE "Identificador Pessoa de Para, n�o foi encontrado."

#define E_CDLINHAATENDIM_NE "07W0018"
#define M_CDLINHAATENDIM_NE "Identificador Linha de Atendimento, n�o foi encontrado."

#define E_CDESTADOLINHA_NE "07W0019"
#define M_CDESTADOLINHA_NE "Identificador Estado da Linha, n�o foi encontrado."

#define E_CDTIPOLINHA_NE "07W0020"
#define M_CDTIPOLINHA_NE "Identificador Tipo da Linha, n�o foi encontrado."

#define E_CDPESSOALINHAHIST_NE "07W0021"
#define M_CDPESSOALINHAHIST_NE "Identificador Pessoa Linha Hist�rico, n�o foi encontrado."

#define E_TAGIDCONTATO_NE "07W0022"
#define M_TAGIDCONTATO_NE "Valor da tag 'idContato' nao informado no XML de entrada."

#define E_TAGIDSEGMENTACAO_NE "07W0023"
#define M_TAGIDSEGMENTACAO_NE "Valor da tag 'idSegmentacao' nao informado no XML de entrada."

#define E_TAGIDPROCEDENCIA_NE "07W0024"
#define M_TAGIDPROCEDENCIA_NE "Valor da tag 'idProcedencia' nao informado no XML de entrada."

#define E_TAGNRTELCONTATO_NE "07W0025"
#define M_TAGNRTELCONTATO_NE "N�mero de telefone do contato n�o informado no XML de entrada."

#define E_GRPTRATPOUT_NE "07W0026"
#define M_GRPTRATPOUT_NE "Grupo inicial de tratamento de processo de portabilidade n�o informado."

#define E_NRPROTOPOUT_NE "07W0027"
#define M_NRPROTOPOUT_NE "N�mero de protocolo de portout obrigat�rio e n�o informado."

#define E_JANELAPOUT_NE "07W0028"
#define M_JANELAPOUT_NE "Janela de portout obrigat�ria e n�o informada."

#define E_OPERSOLICPOUT_NE "07W0029"
#define M_OPERSOLICPOUT_NE "Operadora solicitante � campo obrigat�rio para processo de portout."

#define E_PALITPORTOUT_NE "07W0030"
#define M_PALITPORTOUT_NE "N�o � permitido palitar processos de portout"

#define E_PALITAGEMOUT_NE "07W0031"
#define M_PALITAGEMOUT_NE "O tipo de abertura informado n�o condiz com o tipo de abertura original do protocolo"

#endif

