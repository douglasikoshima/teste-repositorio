/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:30 $
 **/


//======================================================================================
// Todos os valores fixos e constantes do módulo de atendimento - Maio, 2006 - Cassio
//======================================================================================

#ifndef __DEFINES_ATENDIMENTO_H__

#define SAFE_STRNCPY(dst,src) strncpy(dst,src?src:"",sizeof(dst)-1);dst[sizeof(dst)-1]=0;

////////////////////////////////////////////////////////////////////////////////////////
// Códigos de erros de serviços
//
#define TUXEXCE_ID_OBRIGATORIO             "01E0001"
#define TUXEXCE_ID_DADOS_OBRIGATORIO       "01E0002"
#define TUXEXCE_PONTEIRO_INVALIDO          "02E0001"

////////////////////////////////////////////////////////////////////////////////////////
// Tipo de abertura de um processo (WORKFLOW.FASE)
//
#define ABERTURA    1
#define TRATAMENTO  2
#define RETORNO     3

////////////////////////////////////////////////////////////////////////////////////////
// Tipos de carteira (APOIO.TIPOCARTEIRA)
//
#define TPC_BABY_SITTING            37
#define TPC_CLIENTE_ESPECIAL        2
#define TPC_CLIENTE_NACIONAL        6
#define TPC_CN_GOV_FEDERAL          36
#define TPC_FUNCIONARIO_VIVO        42
#define TPC_GC_GOV_ESTADUAL         8
#define TPC_GC_GOV_FEDERAL          9
#define TPC_GC_GOV_MUNICIPAL        7
#define TPC_GDE_CONTA               5
#define TPC_GRANDE_PUBLICO          38
#define TPC_NAO_CLASSIFICADO        13
#define TPC_PESSOA_FISICA           20
#define TPC_PME_VD                  10
#define TPC_PME_VD_ASSOCIACAO       14
#define TPC_PME_VD_ASSOCIADO        26
#define TPC_PME_VD_EMPREGADO        4
#define TPC_PME_VD_GOV_ESTADUAL     23
#define TPC_PME_VD_GOV_FEDERAL      22
#define TPC_PME_VD_GOV_MUNICIPAL    24
#define TPC_PME_VD_PRODUTOR RURAL   39
#define TPC_PME_VD_SOHO             11
#define TPC_PME_VD_TOP_PME          27
#define TPC_PME_VI                  3
#define TPC_PME_VI_ASSOCIACAO       1
#define TPC_PME_VI_ASSOCIADO        34
#define TPC_PME_VI_EMPREGADO        33
#define TPC_PME_VI_GOV_ESTADUAL     28
#define TPC_PME_VI_GOV_FEDERAL      29
#define TPC_PME_VI_GOV_MUNICIPAL    30
#define TPC_PME_VI_PRODUTOR_RURAL   40
#define TPC_PME_VI_SOHO             32
#define TPC_PME_VI_TOP_PME          35

////////////////////////////////////////////////////////////////////////////////////////
// Status de usuários
//
#define USUARIO_ATIVO     1
#define USUARIO_INATIVO   2
#define USUARIO_DESLIGADO 3

////////////////////////////////////////////////////////////////////////////////////////
// Usuários
//
#define URA_MIGRACAO  0
#define ADMINFO       1
#define USUARIO_VOL   2
#define USUARIO_TAV   3
#define INTERFACE_DW 16
#define UNIFICACAO   20
#define USUARIO_URA  30

////////////////////////////////////////////////////////////////////////////////////////
// Tipos de Comunicação (APOIO.TIPOCOMUNICACAO)
//
#define TPCOMM_NAO_CLASSIFICADO 0
#define TPCOMM_EMAIL_PARTICULAR 6
#define TPCOMM_SMS              9
#define TPCOMM_EMAIL_COMERCIAL 12

////////////////////////////////////////////////////////////////////////////////////////
// Tipo de retorno do contato (CONTATOADM.TIPORETORNOCONTATO)
//
#define TP_RET_SEM_RETORNO     0
#define TP_RET_COM_RET_GRP_BKO 1
#define TP_RET_COM_RET_GRP_RET 2
#define TP_RET_SEM_RET_ONLINE  3
#define TP_RET_COM_RET_GRP_CRI 4

////////////////////////////////////////////////////////////////////////////////////////
// Atividades (WORKFLOW.ATIVIDADE)
//
#define ABRIR_A                  1
#define ADQUIRIR_PROCESSO_ADBKO 29
#define ADQUIRIR_PROCESSO_ADCRI 28
#define ADQUIRIR_PROCESSO_ADRC  33
#define AGENDAR_A                5
#define AGENDAR_AR              16
#define CANCELAR_C              11
#define CONCLUIR_ANALISE_CA      7
#define DEVOLVER_BKO_D           9
#define DEVOLVER_BKO_DCRI       31
#define DEVOLVER_BKO_DR         18
#define DOCUMENTACAO_TECNICA_DT 19
#define ENCAMINHADO_CPREVIO_ERC 34
#define ENCAMINHAR_E             3
#define ENCAMINHAR_CRI_ECRI     26
#define ENCERRAR_BKO_EB          8
#define ENCERRAR_BKO_EBR        13
#define ENCERRAR_BKO_EBS        14
#define ENCERRAR_BKO_MASSA_EBM  23
#define FECHAMENTO_IMEDIATO_FI   2
#define FECHAR_F                10
#define FECHAR_FR               17
#define FINALIZAR_ANALISE_FAN   30
#define INSERIR_COMENTARIO_IC   24
#define INSERIR_INSISTENCIA_I    4
#define PROSSEGUIR_BKO_PBKO     27
#define REABRIR_RB              15
#define REANALISE_RE            22
#define REGISTRAR_RC            21
#define REGISTRAR_CPREVIO_RC    32
#define REINCIDENCIA_R          12
#define SUSPEITO_S               6
#define TESTES_ATENDIMENTO_TA   20
#define TROCAR_CRI_TRCRI        25
#define ASSOCIAR_LINHAS         36

////////////////////////////////////////////////////////////////////////////////////////
// Tipos de grupos (APOIO.TIPOGRUPO)
//
#define TIPO_GRUPO_BKO 1 // grupo normal
#define TIPO_GRUPO_CRI 2
#define TIPO_GRUPO_RC  3

////////////////////////////////////////////////////////////////////////////////////////
// Indicador de fonte de acesso (fila ou inbox)
//
#define ACESSO_PELO_INBOX 1 // Acesso pelo inbox é feito somente por operador BKO/CRI/RC/ETC
#define ACESSO_PELA_FILA  2 // Acesso pela fila é feito somente por supervisor

////////////////////////////////////////////////////////////////////////////////////////
// Tipos de Relacionamento (CUSTOMER.TIPORELACIONAMENTO)
//
#define TIPO_RELA_FI_FALSO_INSERT       0
#define TIPO_RELA_U_USUARIO             1
#define TIPO_RELA_C_CLIENTE             2
#define TIPO_RELA_R_CONS_RELACIONAMENTO 3
#define TIPO_RELA_V_EXECUTIVO_DE_VENDAS 4
#define TIPO_RELA_GC_GESTOR_DA_CONTA    5
#define TIPO_RELA_P_PROSPECT            6
#define TIPO_RELA_NC_NAO_CLIENTE        7
#define TIPO_RELA_GRP_CONTA_GRUPO       8

////////////////////////////////////////////////////////////////////////////////////////
// Siglas de Tipos de Relacionamento (CUSTOMER.TIPORELACIONAMENTO)
//
#define SG_TPRELA_U_USUARIO             "U"
#define SG_TPRELA_C_CLIENTE             "C"
#define SG_TPRELA_R_CONS_RELACIONAMENTO "R"
#define SG_TPRELA_V_EXECUTIVO_DE_VENDAS "V"
#define SG_TPRELA_GC_GESTOR_DA_CONTA    "GC"
#define SG_TPRELA_P_PROSPECT            "P"
#define SG_TPRELA_NC_NAO_CLIENTE        "NC"
#define SG_TPRELA_GRP_CONTA_GRUPO       "GRP"

#endif // __DEFINES_ATENDIMENTO_H__
