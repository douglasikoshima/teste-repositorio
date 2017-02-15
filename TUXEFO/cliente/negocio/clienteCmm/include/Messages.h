/*
 * Define todas as mensagens a serem utilizadas pelos serviços de cliente.
 */

#ifndef MESSAGES
#define MESSAGES

#ifndef MSG_NONE
char sNrMsg[8];
char sMsg[256];
#else
extern char sNrMsg[8];
extern char sMsg[256];
#endif

// Número do subsistema
#define SERVICE				"24"

// Macros para montar ID de mensagens
#define ERROR(C) \
{ \
	sprintf(sNrMsg, "%sE%04d", SERVICE, C); \
}

#define WARNING(C) \
{ \
	sprintf(sNrMsg, "%sW%04d", SERVICE, C); \
}

#define INFORMATION(C) \
{ \
	sprintf(sNrMsg, "%sI%04d", SERVICE, C); \
}

// Macros para montar mensagens genéricas
#define TAG_INEXISTENTE(TAG) \
{\
	sprintf(sMsg, MSG_NO_TAG, TAG); \
}

#define TAG_VALOR_INVALIDO(TAG) \
{\
	sprintf(sMsg, MSG_VALOR_INVALIDO, TAG); \
}

#define TAG_VALOR_VAZIO(TAG) \
{\
	sprintf(sMsg, MSG_VALOR_VAZIO, TAG); \
}

#define EXEC_METODO(MET) \
{\
	sprintf(sMsg, MSG_METODO, MET); \
}

// Mensagens comuns das macros
#define MSG_VALOR_INVALIDO	"O valor da TAG %s invalido"
#define MSG_VALOR_VAZIO		"O valor da TAG %s vazio"
#define MSG_NO_TAG   		"TAG %s inexistente"

// Mensagens comuns
#define MSG_OK                           "Execução OK"
#define MSG_NOK                          "Execução com erro"
#define MSG_SEM_REGISTRO                 "Nao foi encontrado nenhum registro na base"
#define MSG_CHAVE_NAO_INFORMADA          "Ao menos um parâmetro de busca deve ser informado"
#define MSG_HEXA_HLR                     "Erro recuperando HEXA da HLR"
#define MSG_HEXA_ATLYS                   "Erro recuperando HEXA do Atlys"
#define MSG_MEMORIA                      "Erro de alocação de memória"
#define MSG_METODO                       "Erro ao executar o metodo %s"
#define MSG_BD_VAZIO					 "Nao foi encontrado dados na Base de Dados"
#define MSG_VL_POSS_NAO_ENV				 "Valor Possível não enviado para o atributo"
#define MSG_DELETE_NAO_EFETUADO          "Nao foi possivel deletar nenhum registro"
#define MSG_INSERT_NAO_EFETUADO          "Nao foi possivel inserir nenhum registro"
#define MSG_UPDATE_NAO_EFETUADO          "Nao foi possivel alterar nenhum registro"

// Números das mensagens de TAGs
#define NRO_TP_CORR_ID_NE	             1 // TAG Id de tipo de correspondencia não encontrado
#define NRO_TP_CORR_ID_VV                2 // TAG Id de tipo de correspondencia com valor vazio
#define NRO_TP_CORR_ID_VI               22 // TAG Id de tipo de correspondencia com valor inválido

#define NRO_MOT_DEV_ID_NE               23 // TAG Id de motivo de devolucao não encontrado
#define NRO_MOT_DEV_ID_VV               24 // TAG Id de motivo de devolucao com valor vazio
#define NRO_MOT_DEV_ID_VI               25 // TAG Id de motivo de devolucao com valor inválido

#define NRO_STAT_CORR_QT_DIAS_VI         3 // TAG de Quantidade de dias de validade do status de correspondência inválido
#define NRO_STAT_CORR_PROX_ID_VI         4 // TAG de Próximo ID de status inválido
#define NRO_STAT_CORR_ID_ANT_VI          5 // TAG de ID anterior de status inválido
#define NRO_STAT_CORR_ID_NE              6 // TAG Id de status de correspondencia não encontrado
#define NRO_STAT_CORR_ID_VV              7 // TAG Id de status de correspondencia com valor vazio
#define NRO_STAT_CORR_ID_VI              8 // TAG Id de status de correspondencia com valor inválido
#define NRO_STAT_DT_STATUS_NE           46 // TAG de data de status não existe
#define NRO_STAT_DT_STATUS_VV           47 // TAG de data de status inválida

#define NRO_ID_PAIS_NE                   9 // TAG de ID de pais nao encontrada
#define NRO_ID_PAIS_VV                  10 // TAG de ID de pais com valor vazio
#define NRO_ID_PAIS_VI                  11 // TAG de ID de pais com valor invalido

#define NRO_ID_UF_NE                    48 // TAG de ID de estado nao encontrada
#define NRO_ID_UF_VV                    49 // TAG de ID de estado com valor vazio
#define NRO_ID_UF_VI                    50 // TAG de ID de estado com valor invalido

#define NRO_ID_TP_PESSOA_NE             12 // TAG de id de tipo de pessoa nao existe
#define NRO_ID_TP_PESSOA_VV             13 // TAG de id de tipo de pessoa com valor vazio
#define NRO_ID_TP_PESSOA_VI             14 // TAG de id de tipo de pessoa com valor invalido

#define NRO_ID_PESSOA_NE                27 // TAG de id de pessoa nao existe
#define NRO_ID_PESSOA_VV                28 // TAG de id de pessoa com valor vazio
#define NRO_ID_PESSOA_VI                29 // TAG de id de pessoa com valor invalido

#define NRO_ID_TP_DOCTO_NE              15 // TAG de id de tipo de documento nao existe
#define NRO_ID_TP_DOCTO_VV              16 // TAG de id de tipo de documento com valor vazio
#define NRO_ID_TP_DOCTO_VI              17 // TAG de id de tipo de documento com valor invalido

#define NRO_NM_PROSPECT_NE              18 // TAG de nome de prospect nao encontrada
#define NRO_NM_PROSPECT_VV              19 // TAG de nome de prospect com valor vazio

#define NRO_PRI_NM_PROSPECT_NE          167 // TAG de primeiro nome de prospect nao encontrada
#define NRO_PRI_NM_PROSPECT_VV          168 // TAG de primeiro nome de prospect com valor vazio

#define NRO_MEIO_NM_PROSPECT_NE         169 // TAG de nome do meio de prospect nao encontrada
#define NRO_MEIO_NM_PROSPECT_VV         170 // TAG de nome do meio de prospect com valor vazio

#define NRO_SOB_NM_PROSPECT_NE          171 // TAG de sobrenome de prospect nao encontrada
#define NRO_SOB_NM_PROSPECT_VV          172 // TAG de sobrenome de prospect com valor vazio

#define NRO_DOCTO_NE                    20 // TAG de documento nao encontrada
#define NRO_DOCTO_VV                    21 // TAG de documento com valor vazio
#define NRO_DOCTO_VI                    22 // TAG de documento com valor invalido

#define NRO_ID_TP_COMUNICACAO_VI        26 // TAG de ID de tipo de comunicação com valor inválido

#define NRO_DT_INICIAL_NE               31 // TAG de data inicial não informada
#define NRO_DT_INICIAL_VV               32 // TAG de data inicial com valor vazio
#define NRO_DT_FINAL_NE                 33 // TAG de data final não informada
#define NRO_DT_FINAL_VV                 34 // TAG de data final com valor vazio

#define NRO_ID_LINHA_NE                 35 // TAG de id de linha nao existe
#define NRO_ID_LINHA_VV                 36 // TAG de id de linha com valor vazio
#define NRO_ID_LINHA_VI                 37 // TAG de id de linha com valor invalido

#define NRO_ID_USUARIO_NE               38 // TAG de id de usuario nao existe
#define NRO_ID_USUARIO_VV               39 // TAG de id de usuario com valor vazio
#define NRO_ID_USUARIO_VI               40 // TAG de id de usuario com valor invalido

#define NRO_ID_CORR_DEV_NE              43 // TAG de id de correspondencia devolvida nao existe
#define NRO_ID_CORR_DEV_VV              44 // TAG de id de correspondencia devolvida com valor vazio
#define NRO_ID_CORR_DEV_VI              45 // TAG de id de correspondencia devolvida com valor invalido

#define NRO_CONTA_ID_NE                 52 // TAG de id de conta nao existe
#define NRO_CONTA_ID_VV                 53 // TAG de id de conta com valor vazio
#define NRO_CONTA_ID_VI                 54 // TAG de id de conta com valor invalido

#define NRO_CONTA_NR_NE                 55 // TAG de id de conta nao existe
#define NRO_CONTA_NR_VV                 56 // TAG de id de conta com valor vazio
#define NRO_CONTA_NR_VI                 57 // TAG de id de conta com valor invalido

#define NRO_ID_ASSUNTO_NR_NE            58 // TAG de id de assunto nao existe
#define NRO_ID_ASSUNTO_NR_VI            59 // TAG de id de assunto com valor invalido
#define NRO_DS_ASSUNTO_NR_NE            60 // TAG de descricao de assunto nao existe
#define NRO_DS_ASSUNTO_NR_VI            61 // TAG de descricao de assunto com valor invalido
#define NRO_DISPONIB_NR_NE              62 // TAG de disponibilidade de assunto nao existe
#define NRO_DISPONIB_NR_VI              63 // TAG de disponibilidade de assunto com valor invalido
	
#define NRO_TP_DET_NE                   64 // TAG de tipo de detalhe de faturamento nao existe
#define NRO_TP_DET_VV                   65 // TAG de tipo de detalhe de faturamento com valor vazio
#define NRO_TP_DET_VI                   66 // TAG de tipo de detalhe de faturamento com valor invalido

#define NRO_DS_CICLO_FATURA_NE          67 // TAG de descrição de ciclo de fatura não encontrada
#define NRO_DS_CICLO_FATURA_VV          68 // TAG de descrição de ciclo de fatura com valor vazio

#define NRO_ID_ATRIBUTO_NR_NE           69 // TAG de id de atributo nao existe
#define NRO_ID_ATRIBUTO_NR_VV          109 // TAG de id de atributo com valor invalido
#define NRO_ID_ATRIBUTO_NR_VI           70 // TAG de id de atributo com valor invalido
#define NRO_DS_ATRIBUTO_NR_NE           71 // TAG de descricao de atributo nao existe
#define NRO_DS_ATRIBUTO_NR_VI           72 // TAG de descricao de atributo com valor invalido
#define NRO_SQ_ATRIBUTO_NR_NE           73 // TAG de sequencia de apresentacao nao existe
#define NRO_SQ_ATRIBUTO_NR_VI           74 // TAG de sequencia de apresentacao com valor invalido
#define NRO_ID_TPAPRESENTACAO_NR_NE     75 // TAG de id tipo de apresentacao nao existe
#define NRO_ID_TPAPRESENTACAO_NR_VV    110 // TAG de id tipo de apresentacao com valor invalido
#define NRO_ID_TPAPRESENTACAO_NR_VI     76 // TAG de id tipo de apresentacao com valor invalido
#define NRO_IN_DISPONIBILIDADE_NR_NE    77 // TAG de disponibilidade nao existe
#define NRO_IN_DISPONIBILIDADE_NR_VI    78 // TAG de disponibilidade com valor invalido

#define NRO_ID_SUBASSUNTO_NR_NE         79 // TAG de id de subassunto nao existe
#define NRO_ID_SUBVASSUNTO_NR_VI        80 // TAG de id de subassunto com valor invalido
#define NRO_ID_SUBVASSUNTO_NR_VV       144 // TAG de id de subassunto com valor vazio

#define NRO_ID_MOTIVO_NR_NE				81 // TAG de id do motivo de devolução não encontrado no XML de entrada
#define NRO_ID_MOTIVO_NR_VI				82 // TAG de motivo de devolução com valor invalido

#define NRO_NR_LINHA_NE                 83 // TAG de numero da linha nao existe
#define NRO_NR_LINHA_VV                 84 // TAG de numero da linha com valor vazio

#define NRO_DS_CONTATO_NE	            85 // TAG de descricao contato nao existe
#define NRO_DS_CONTATO_VV		        86 // TAG de descricao contato com valor vazio
#define NRO_DS_CONTATO_VI			    87 // TAG de descricao contato com valor invalido

#define NRO_ACAO_NE						88 // TAG de acao nao existe
#define NRO_ACAO_VV						89 // TAG de acao com valor vazio
#define NRO_ACAO_VI						90 // TAG de acao com valor invalido

#define NRO_ID_PESSOA_COM_NE			91 // TAG de id pessoa comunicacao nao existe
#define NRO_ID_PESSOA_COM_VV			92 // TAG de id pessoa comunicacao com valor vazio

#define NRO_IN_DIVULGA_NUM_NE			93 // TAG de divulga numero da linha nao existe
#define NRO_IN_DIVULGA_NUM_VV			94 // TAG de divulga numero da linha com valor vazio

#define NRO_CODIGO_NE					95 // TAG codigo nao existe
#define NRO_CODIGO_VV					96 // TAG codigo com valor vazio
#define NRO_CODIGO_VI				   145// TAG codigo com valor invalido

#define NRO_DS_SASSUNTO_NR_NE           97 // TAG de descricao de sub assunto nao existe
#define NRO_DS_SASSUNTO_NR_VI           98 // TAG de descricao de sub assunto com valor invalido

#define NRO_INDISPON_NE					99 // TAG de indisponibilidade nao existe
#define NRO_INDISPON_VI				   100 // TAG de indisponibilidade com valor invalido

#define NRO_ID_CONTEUDO_NE			   101 // TAG de id conteudo nao existe
#define NRO_ID_CONTEUDO_VI			   102 // TAG de id conteudo com valor invalido

#define NRO_DS_VALORPOSS_NE            103 // TAG de descricao de valor possivel nao existe
#define NRO_DS_VALORPOSS_VI            104 // TAG de descricao de valor possivel com valor invalido
#define NRO_SQ_VALORPOSS_NE            105 // TAG de sequencia de apresentacao nao existe
#define NRO_SQ_VALORPOSS_VI            106 // TAG de sequencia de apresentacao com valor invalido
#define NRO_ID_VALORPOSS_NE            107 // TAG de descricao de valor possivel nao existe
#define NRO_ID_VALORPOSS_VI            108 // TAG de descricao de valor possivel com valor invalido
#define NRO_ID_VALORPOSS_VV			   117 // TAG de descricao de valor possivel com valor vazio

#define NRO_DS_VALORLIVR_NE            111 // TAG de descricao de valor livre nao existe
#define NRO_DS_VALORLIVR_VI            112 // TAG de descricao de valor livre com valor invalido
#define NRO_DS_VALORLIVR_VV            116 // TAG de descricao de valor livre com valor invalido
#define NRO_ID_VALORLIVR_NE            113 // TAG de descricao de valor livre nao existe
#define NRO_ID_VALORLIVR_VI            114 // TAG de descricao de valor livre com valor invalido
#define NRO_ID_VALORLIVR_VV            115 // TAG de descricao de valor livre com valor invalido

#define NRO_TAG_ACAO_NE                119 // TAG de indicacao do tipo de operacao nao existe
#define NRO_TAG_ACAO_VI                120 // TAG de indicacao do tipo de operacao valor invalido

#define NRO_ID_TPENDERECO_NE           121 // TAG de id tipo de endereco nao existe
#define NRO_ID_TPENDERECO_VV           122 // TAG de id tipo de endereco valor vazio
#define NRO_ID_TPENDERECO_VI           123 // TAG de id tipo de endereco valor invalido


#define NRO_ID_SISTORIGEM_NE           124 // TAG de id sistema origem nao existe
#define NRO_ID_SISTORIGEM_VI           125 // TAG de id sistema origem valor invalido
#define NRO_ID_SISTORIGEM_VV           126 // TAG de id sistema origem valor vasio

#define NRO_NR_CEP_NE		           127 // TAG de numero do cep nao existe
#define NRO_NR_CEP_VI		           128 // TAG de numero do cep valor invalido
#define NRO_NR_CEP_VV		           129 // TAG de numero do cep valor vazio

#define NRO_ID_PESSOAENDERECO_NE       130 // TAG de id tipo pessoa endereco nao existe
#define NRO_ID_PESSOAENDERECO_VV       131 // TAG de id tipo pessoa endereco valor vazio
#define NRO_ID_PESSOAENDERECO_VI       132 // TAG de id tipo pessoa endereco valor invalido

#define NRO_ID_TPCOMUNICACAO_NE       130 // TAG de id tipo comunicacao nao existe
#define NRO_ID_TPCOMUNICACAO_VV       131 // TAG de id tipo comunicacao valor vazio
#define NRO_ID_TPCOMUNICACAO_VI       132 // TAG de id tipo comunicacao valor invalido

#define NRO_ID_PESSOA_COM_VI		   133 // TAG de id pessoa comunicacao com valor invalido

#define NRO_NR_LINHA_VI                134 // TAG de numero da linha com valor invalido

#define NRO_IN_DIVULGA_NUM_VI		   138 // TAG de divulga numero da linha com valor invalido

#define NRO_NR_LINHA_DE_VI             139 // TAG de numero da linha de com valor invalido

#define NRO_NR_LINHA_ATE_VI            140 // TAG de numero da linha ate com valor invalido

#define NRO_ID_STATUSLINHA_VI          142 // TAG id statud da linha com valor invalido

#define NRO_ID_ASSUNTO_NR_VV           143 // TAG de id de assunto com valor vazio

#define NRO_ID_SEGMENT_VV			   146 // TAG de id de segmentação com valor vazio
#define NRO_ID_SEGMENT_VI			   147 // TAG de id de segmentação com valor inválido
#define NRO_ID_SEGMENT_NE			   148 // TAG de id de segmentação não existe

#define NRO_ID_CART_VV				   149 // TAG de id de carteirização com valor vazio
#define NRO_ID_CART_VI				   150 // TAG de id de carteirização com valor inválido
#define NRO_ID_CART_NE				   151 // TAG de id de carteirização não existe

#define NRO_PESQUISA_VV				   153 // TAG de pesquisa com valor vazio
#define NRO_PESQUISA_VI				   154 // TAG de pesquisa com valor inválido
#define NRO_PESQUISA_NE				   155 // TAG de pesquisa não existe

#define NRO_VALOR_VV				   156 // TAG de valor com valor vazio
#define NRO_VALOR_VI				   157 // TAG de valor com valor inválido
#define NRO_VALOR_NE				   158 // TAG de Valor não existe

#define NRO_ID_TPRELACIONA_VV		   159 // TAG de id tipo relacionamento com valor vazio
#define NRO_ID_TPRELACIONA_VI		   160 // TAG de id tipo relacionamento com valor inválido
#define NRO_ID_TPRELACIONA_NE		   161 // TAG de id tipo relacionamento não existe

#define NRO_FILTRO_QUERY_NE		       162 // TAG de filtro da query valor possivel nao existe
#define NRO_FILTRO_QUERY_VV		       163 // TAG de filtro da query valor possivel valor vazio


#define NRO_ID_ENDERECO_VV		       164 // TAG de id endereo com valor vazio
#define NRO_ID_ENDERECO_VI		       165 // TAG de id endereco com valor inválido
#define NRO_ID_ENDERECO_NE		       166 // TAG de id endereco não existe

#define NRO_ID_UF_DOC_VV		       173 // TAG de id UF documento com valor vazio
#define NRO_ID_UF_DOC_VI		       174 // TAG de id UF documento com valor inválido
#define NRO_ID_UF_DOC_NE		       175 // TAG de id UF documento não existe


#define NRO_MEIO_NM_PESSOA_NE          176 // TAG de nome do meio da pessoa nao encontrada
#define NRO_MEIO_NM_PESSOA_VV          177 // TAG de nome do meio da pessoa com valor vazio

#define NRO_SOB_NM_PESSOA_NE           178 // TAG de sobrenome da pessoa nao encontrada
#define NRO_SOB_NM_PESSOA_VV           179 // TAG de sobrenome da pessoa com valor vazio


#define NRO_ID_PESSOA_DE_PARA_NE       180 // TAG de id de pessoa de para nao existe
#define NRO_ID_PESSOA_DE_PARA_VV       181 // TAG de id de pessoa de para com valor vazio
#define NRO_ID_PESSOA_DE_PARA_VI       182 // TAG de id de pessoa de para com valor invalido

#define NRO_ID_LIN_SIST_ORIG_NE        183 // TAG de id linha sistema origem nao existe
#define NRO_ID_LIN_SIST_ORIG_VV        184 // TAG de id linha sistema origem com valor vazio
#define NRO_ID_LIN_SIST_ORIG_VI        185 // TAG de id linha sistema origem com valor invalido

#define NRO_NR_SEQUENCIA_NE            186 // TAG de nrSequencia nao existe
#define NRO_NR_SEQUENCIA_VV            187 // TAG de nrSequencia com valor vazio
#define NRO_NR_SEQUENCIA_VI            188 // TAG de nrSequencia com valor invalido

#define NRO_ID_GRUPO_NE                189 // TAG de idGrupo nao existe
#define NRO_ID_GRUPO_VV                190 // TAG de idGrupo com valor vazio
#define NRO_ID_GRUPO_VI                191 // TAG de idGrupo com valor invalido

#define NRO_ID_CANAL_NE                192 // TAG de idCanal nao existe
#define NRO_ID_CANAL_VV                193 // TAG de idCanal com valor vazio
#define NRO_ID_CANAL_VI                194 // TAG de idCanal com valor invalido

#define NRO_ID_CANAL_OU_ID_GRUPO       195 // Id Canal ou Id Grupo obrigatorio

#define NRO_CD_PARAMETRO_NE            201 // TAG cdParametro nao existe vazia
#define NRO_CD_PARAMETRO_VV            202 // TAG cdParametro vazia

#define NRO_NM_LOGIN_NE                210 // TAG Nome Login nao existe
#define NRO_NM_LOGIN_VV                211 // TAG Nome Login vazio

#define NRO_CD_HEXA_NE                 220 // TAG Codigo Hexa nao existe
#define NRO_CD_HEXA_VV                 221 // TAG Codigo Hexa vazio

#define NRO_CD_DECIMAL_NE              230 // TAG Codigo Decimal nao existe
#define NRO_CD_DECIMAL_VV              231 // TAG Codigo Decimal vazio

#define NRO_NR_ICCID_NE                240 // TAG de nrICCID nao existe
#define NRO_NR_ICCID_VV                241 // TAG de nrICCID com valor vazio
#define NRO_NR_ICCID_VI                242 // TAG de nrICCID com valor invalido

#define NRO_NR_PUK1_NE                 250 // TAG de nrPUK1 nao existe
#define NRO_NR_PUK1_VV                 251 // TAG de nrPUK1 com valor vazio
#define NRO_NR_PUK1_VI                 252 // TAG de nrPUK1 com valor invalido

#define NRO_NR_PUK2_NE                 260 // TAG de nrPUK2 nao existe
#define NRO_NR_PUK2_VV                 261 // TAG de nrPUK2 com valor vazio
#define NRO_NR_PUK2_VI                 262 // TAG de nrPUK2 com valor invalido

#define NRO_DS_TIPO_CHIP_NE            270 // TAG de dsTipoChip nao existe
#define NRO_DS_TIPO_CHIP_VV            271 // TAG de dsTipoChip com valor vazio
#define NRO_DS_TIPO_CHIP_VI            272 // TAG de dsTipoChip com valor invalido

#define NRO_DS_TAMANHO_CHIP_NE         280 // TAG de dsTamanhoChip nao existe
#define NRO_DS_TAMANHO_CHIP_VV         281 // TAG de dsTamanhoChip com valor vazio
#define NRO_DS_TAMANHO_CHIP_VI         282 // TAG de dsTamanhoChip com valor invalido

#define NRO_NR_ERROR_CODE_NE           290 // TAG de ErrorCode nao existe
#define NRO_NR_ERROR_CODE_VV           291 // TAG de ErrorCode com valor vazio
#define NRO_NR_ERROR_CODE_VI           292 // TAG de ErrorCode com valor invalido

#define NRO_DS_ERROR_DESCRIPTION_NE    300 // TAG de ErrorDescription nao existe
#define NRO_DS_ERROR_DESCRIPTION_VV    301 // TAG de ErrorDescription com valor vazio
#define NRO_DS_ERROR_DESCRIPTION_VI    302 // TAG de ErrorDescription com valor invalido

#define NRO_ID_TIPOLINHA_NE            312 // TAG id tipo de linha nao existe
#define NRO_ID_TIPOLINHA_VV            313 // TAG id tipo de linha com valor vazio
#define NRO_ID_TIPOLINHA_VI            314 // TAG id tipo de linha com valor invalido

#define NRO_NR_IMEI_NE                 320 // TAG IMEI nao existe
#define NRO_NR_IMEI_VV                 321 // TAG IMEI com valor vazio
#define NRO_NR_IMEI_VI                 322 // TAG IMEI com valor invalido

#define NRO_NR_IP_NE                   330 // TAG IP nao existe
#define NRO_NR_IP_VV                   331 // TAG IP com valor vazio
#define NRO_NR_IP_VI                   332 // TAG IP com valor invalido

#define NRO_ID_GRUPO_ABERTURA_NE       340 // TAG IDGrupoAbertura nao existe
#define NRO_ID_GRUPO_ABERTURA_VV       341 // TAG IDGrupoAbertura com valor vazio
#define NRO_ID_GRUPO_ABERTURA_VI       342 // TAG IDGrupoAbertura com valor invalido

#define NRO_CD_AREA_REGISTRO_NE        350 // TAG CdAreaRegistro nao existe
#define NRO_CD_AREA_REGISTRO_VV        351 // TAG CdAreaRegistro com valor vazio
#define NRO_CD_AREA_REGISTRO_VI        352 // TAG CdAreaRegistro com valor invalido

#define NRO_SIM_LOCK_NE                360 // TAG SIMLock nao existe
#define NRO_SIM_LOCK_VV                361 // TAG SIMLock com valor vazio
#define NRO_SIM_LOCK_VI                362 // TAG SIMLock com valor invalido

#define NRO_MSIDN_NE                   370 // TAG MSIDN nao existe
#define NRO_MSIDN_VV                   371 // TAG MSIDN com valor vazio
#define NRO_MSIDN_VI                   372 // TAG MSIDN com valor invalido

#define NRO_TIPOOPERACAO_NE            380 // TAG TipoOperacao nao existe
#define NRO_TIPOOPERACAO_VV            381 // TAG TipoOperacao com valor vazio
#define NRO_TIPOOPERACAO_VI            382 // TAG TipoOperacao com valor invalido

#define NRO_IDLINHATELEFONICA_NE       390 // TAG IdLinhaTelefonica nao existe
#define NRO_IDLINHATELEFONICA_VV       391 // TAG IdLinhaTelefonica com valor vazio
#define NRO_IDLINHATELEFONICA_VI       392 // TAG IdLinhaTelefonica com valor invalido

#define NRO_DECRIPTION_NE              400 // TAG Description nao existe
#define NRO_DECRIPTION_VV              401 // TAG Description com valor vazio
#define NRO_DECRIPTION_VI              402 // TAG Description com valor invalido

#define NRO_MODELO_NE                  410 // TAG Modelo nao existe
#define NRO_MODELO_VV                  411 // TAG Modelo com valor vazio
#define NRO_MODELO_VI                  412 // TAG Modelo com valor invalido

#define NRO_TECNOLOGIA_NE              420 // TAG Tecnologia nao existe
#define NRO_TECNOLOGIA_VV              421 // TAG Tecnologia com valor vazio
#define NRO_TECNOLOGIA_VI              422 // TAG Tecnologia com valor invalido

#define NRO_FABRICANTE_NE              430 // TAG Fabricante nao existe
#define NRO_FABRICANTE_VV              431 // TAG Fabricante com valor vazio
#define NRO_FABRICANTE_VI              432 // TAG Fabricante com valor invalido

#define NRO_DTASSOCIACAO_NE            440 // TAG DtAssociacao nao existe
#define NRO_DTASSOCIACAO_VV            441 // TAG DtAssociacao com valor vazio
#define NRO_DTASSOCIACAO_VI            442 // TAG DtAssociacao com valor invalido

#define NRO_PARAMETRO_NOME_NE          450 // TAG Parametro Nome nao existe
#define NRO_PARAMETRO_NOME_VV          451 // TAG Parametro Nome com valor vazio
#define NRO_PARAMETRO_NOME_VI          452 // TAG Parametro Nome com valor invalido

#define NRO_ENDERECO_PRINC_NE          460 // TAG Parametro Nome nao existe
#define NRO_ENDERECO_PRINC_VV          461 // TAG Parametro Nome com valor vazio
#define NRO_ENDERECO_PRINC_VI          462 // TAG Parametro Nome com valor invalido

#define NRO_DS_MARCA_NE                470 // TAG Parametro Nome nao existe
#define NRO_DS_MARCA_VV                471 // TAG Parametro Nome com valor vazio
#define NRO_DS_MARCA_VI                472 // TAG Parametro Nome com valor invalido

#define NRO_DS_MODELO_NE               480 // TAG Parametro Nome nao existe
#define NRO_DS_MODELO_VV               481 // TAG Parametro Nome com valor vazio
#define NRO_DS_MODELO_VI               482 // TAG Parametro Nome com valor invalido

#define NRO_ID_NR_LINHA_PROSPECT_NE    483 // TAG Parametro telefone Prospect nao existe
#define NRO_ID_NR_LINHA_PROSPECT_VV    484 // TAG Parametro telefone Prospect com valor vazio
#define NRO_ID_NR_LINHA_PROSPECT_VI    485 // TAG Parametro telefone Prospect com valor invalido

// Número de mensagens de negócio
#define NRO_OK                           0 // Execução OK
#define NRO_NOK                         99 // Execução com problema

#define NRO_CHAVE_NAO_INFORMADA         30 // Ao menos uma chave de busca não foi informada
#define NRO_HEXA_HLR                    41 // Erro recuperando HEXA da HLR
#define NRO_HEXA_ATLYS                  42 // Erro recuperando HEXA do Atlys
#define NRO_MEMORIA                     51 // Erro de alocação de memória
#define NRO_VL_POSS_NAO_ENV			   118 // Valor possível não enviado em um atributo
#define NRO_DELETE_NAO_EFETUADO        135 // Erro ao tentar deletar o registro
#define NRO_INSERT_NAO_EFETUADO        136 // Erro ao tentar inserir o registro
#define NRO_UPDATE_NAO_EFETUADO        137 // Erro ao tentar alterar o registro

#define NRO_SERVICO						9999 //Erro na execucao de servico

#endif
