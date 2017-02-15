#ifndef TXPB_IBACKEND_H
#define TXPB_IBACKEND_H 1


#include <tuxfw.h>
#include "ManagerBackEndDOMNode.h"
#include "TuxHelperClever.h"
#include "RemoteLog.h"
 

// PlugInBE
#define TXPB_STATUS_CODE_SIZE                              7
#define TXPB_STATUS_TEXT_SIZE                              1024
#define TXPB_ID_LINHA_SIS_ORIG_SIZE                        255
#define TXPB_SERVICE_NAME_SIZE                             128

// Constantes gerais
#define DATE_FORMAT_JAVA                                   "YYYY-MM-DD hh:mm:ss"
#define DATE_FORMAT_JAVA_ZERO_TIME                         "YYYY-MM-DD 00:00:00"
#define XML_VAL_YES                                        "yes"
#define XML_VAL_NO                                         "no"
#define XML_VAL_TRUE                                       "true"
#define XML_VAL_FALSE                                      "false"

// TAGs XML de entrada do FO 
#define TAG_XML_IN_LINHA                                   "ProxyLinha"
#define TAG_XML_IN_ID_CONTA_SIS_ORIGEM                     "idcontasistemaorigem"
#define TAG_XML_IN_ID_LINHA_SIS_ORIGEM                     "idlinhasistemaorigem"
#define TAG_XML_IN_USUARIO                                 "usuario"
#define TAG_XML_IN_PERIODO	                               "Periodo"
#define TAG_XML_IN_DATA_INI                                "dataIni"
#define TAG_XML_IN_DATA_FIM                                "dataFim"
#define TAG_XML_IN_OPERACAO                                "operacao"
#define TAG_XML_IN_SERVICO                                 "servico"
#define TAG_XML_IN_MOTIVO                                  "motivo"
#define TAG_XML_IN_OBSERVACAO                              "observacao"
#define TAG_XML_IN_NUMERO_SERIAL                           "numeroSerial"
#define TAG_XML_IN_BO_NUMERO                               "BONumero"
#define TAG_XML_IN_BO_DATA                                 "BOData"
#define TAG_XML_IN_BO_DELEGACIA                            "BODelegacia"
#define TAG_XML_IN_NUMERO_NOVO                             "numeroNovo"
#define TAG_XML_IN_NUMERO_ANTIGO                           "numeroAntigo"
#define TAG_XML_IN_XMLNS                                   "xmlns"
#define TAG_XML_IN_TIPO_CLIENTE                            "tipoCliente"
#define TAG_XML_IN_NOME                                    "nome"
#define TAG_XML_IN_NOME_ABREVIADO                          "nomeAbreviado"
#define TAG_XML_IN_CONFIDENCIAL                            "confidencial"
#define TAG_XML_IN_CPF                                     "CPF"
#define TAG_XML_IN_TIPO_CPF                                "tipoCPF"
#define TAG_XML_IN_RG                                      "RG"
#define TAG_XML_IN_TIPO_RG                                 "tipoRG"
#define TAG_XML_IN_DATA_EXPIRACAO                          "dataExpiracao"
#define TAG_XML_IN_ORGAO_EXPEDITOR                         "orgaoExpeditor"
#define TAG_XML_IN_ESTADO_EXPEDICAO                        "estadoExpedicao"
#define TAG_XML_IN_DATA_NASCIMENTO                         "dataNascimento"
#define TAG_XML_IN_ESTADO_CIVIL                            "estadoCivil"
#define TAG_XML_IN_COD_SEXO                                "codSexo"
#define TAG_XML_IN_TELEFONE                                "telefone"
#define TAG_XML_IN_FAX                                     "fax"
#define TAG_XML_IN_EMAIL                                   "eMail"
#define TAG_XML_IN_NUM_DEPEND                              "numDepend"
#define TAG_XML_IN_PASSAPORTE                              "passaporte"
#define TAG_XML_IN_TIPO_PASSAPORTE                         "tipoPassaporte"
#define TAG_XML_IN_CARTA_CONDUCAO                          "cartaConducao"
#define TAG_XML_IN_TIPO_CARTA_COND                         "tipoCartaCond"
#define TAG_XML_IN_AO_CUIDADO_DE                           "aoCuidadoDe"
#define TAG_XML_IN_OBS                                     "obs"
#define TAG_XML_IN_CONSERVATORIA_REGISTRO                  "conservatoriaRegistro"
#define TAG_XML_IN_CNPJ                                    "CNPJ"
#define TAG_XML_IN_CNAE                                    "CNAE"
#define TAG_XML_IN_HABILITACOES                            "habilitacoes"
#define TAG_XML_IN_OUTRO_CELULAR                           "outroCelular"
#define TAG_XML_IN_TIPO_CONTA                              "tipoConta"
#define TAG_XML_IN_SUB_TIPO                                "subTipo"
#define TAG_XML_IN_RENDA_MENSAL                            "rendaMensal"
#define TAG_XML_IN_CONTA_CORRENT                           "contaCorrent"
#define TAG_XML_IN_BANCO                                   "banco"
#define TAG_XML_IN_IE                                      "IE"
#define TAG_XML_IN_NOME_SUFIXO                             "nomeSufixo"
#define TAG_XML_IN_PRIMEIRO_NOME                           "primeiroNome"
#define TAG_XML_IN_SOBRE_NOME                              "sobreNome"
#define TAG_XML_IN_NOME_CONTATO                            "nomeContato"
#define TAG_XML_IN_CARTEIRA_TRABALHO                       "carteiraTrabalho"
#define TAG_XML_IN_ENDERECO                                "endereco"
#define TAG_XML_IN_COMPLEMENTO                             "complemento"
#define TAG_XML_IN_BAIRRO                                  "bairro"
#define TAG_XML_IN_CEP                                     "CEP"
#define TAG_XML_IN_CIDADE                                  "cidade"
#define TAG_XML_IN_ESTADO                                  "estado"
#define TAG_XML_IN_PAIS                                    "pais"
#define TAG_XML_IN_LOGRADOURO                              "logradouro"
#define TAG_XML_IN_NUMERO                                  "numero"
#define TAG_XML_IN_NOTA_FISCAL                             "notaFiscal"
#define TAG_XML_IN_DATA                                    "data"
#define TAG_XML_IN_DATAPEDIDO                              "dataPedido"
#define TAG_XML_IN_CLASSE                                  "classe"
#define TAG_XML_IN_INREGISTRAR_CONTATO                     "inRegistrarContato"
#define TAG_XML_IN_CD_AREA_REGISTRO                        "cdAreaRegistro"
#define TAG_XML_IN_NR_LINHA                                "nrLinha"
#define TAG_XML_IN_ID_TIPO_RELACIONAMENTO                  "idTipoRelacionamento"
#define TAG_XML_IN_CD_CONTATO                              "cdContato"
#define TAG_XML_IN_ID_CANAL                                "idCanal"
#define TAG_XML_IN_ID_TERMINAL                             "idTerminal"
#define TAG_XML_IN_SG_TP_COM_SMS                           "CELULAR"
#define TAG_XML_IN_SG_TP_COM_EMAIL                         "EM PART"
#define TAG_XML_IN_INCOMUNICAR_USUARIO                     "inComunicarUsuario"
#define TAG_XML_IN_CD_MSG                                  "cdMsg"
#define TAG_XML_IN_NOVO_PLANO                              "novoPlano"
#define TAG_XML_IN_DIA_TARIFA_REDUZIDA                     "diaTarifaReduzida"
#define TAG_XML_IN_QT_FATURAMENTOS                         "qtFaturamentos"
#define TAG_XML_IN_ID_GRUPO                                "idGrupo"
#define TAG_XML_IN_REF_FATURA                              "refFatura"
#define TAG_XML_IN_DATA_PGTO                               "dataPgto"
#define TAG_XML_IN_VALOR                                   "valor"
#define TAG_XML_IN_QT_MAX_INCLUSAO                         "qtMaxInclusao"
#define TAG_XML_IN_POSSUI_FAVORITOS                        "IND_POSSUIFAVORITOS"

// TAGs XML de saida do FO
#define TAG_XML_OUT_HEXA_VO                                "HexaVO"
#define TAG_XML_OUT_ESN                                    "serial"
#define TAG_XML_OUT_DETALHE_LINHA_VO                       "DetalheLinhaVO"
#define TAG_XML_OUT_MODELO                                 "modelo"
#define TAG_XML_OUT_DESCRICAO                              "descricao"
#define TAG_XML_OUT_MARCA                                  "marca"
#define TAG_XML_OUT_DS_TECNOLOGIA                          "dsTecnologia"
#define TAG_XML_OUT_DS_MULTA_CONTRATO                      "dsMultaContrato"
#define TAG_XML_OUT_CONTRATO_FIDELIZACAO                   "contratoFidelizacao"
#define TAG_XML_OUT_DETALHES_SALDO_VO                      "DetalhesSaldoVO"
#define TAG_XML_OUT_CD_SEGURANCA                           "cdSeguranca"
#define TAG_XML_OUT_ESTADO_LINHA                           "estadoLinha"
#define TAG_XML_OUT_PR_VALIDADE_REAL                       "prValidadeReal"
#define TAG_XML_OUT_DETALHES_SALDO_ITEM                    "DetalhesSaldoItem"
#define TAG_XML_OUT_PR_VALIDADE                            "prValidade"
#define TAG_XML_OUT_SALDO                                  "saldo"               
#define TAG_XML_OUT_TIPO_SALDO                             "tipoSaldo"
#define TAG_XML_OUT_HISTORICO_MOVIMENTOS_VO                "HistoricoMovimentosVO"
#define TAG_XML_OUT_HISTORICO_MOVIMENTOS_ITEM              "HistoricoMovimentosItem"
#define TAG_XML_OUT_HISTORICO_ATENDIMENTO_VO               "HistoricoAtendimentoVO"
#define TAG_XML_OUT_HISTORICO_ATENDIMENTO_ITEM             "HistoricoAtendimentoItem"
#define TAG_XML_OUT_RECARGA_VALOR                          "recargaValor"
#define TAG_XML_OUT_RECARGA_DATA                           "recargaData"
#define TAG_XML_OUT_RECARGA_DATA_PROC                      "recargaDataProc"
#define TAG_XML_OUT_ORIGEM                                 "origem"
#define TAG_XML_OUT_MOVIMENTO                              "movimento"
#define TAG_XML_OUT_NOTA_FISCAL                            "notaFiscal"
#define TAG_XML_OUT_FLAG                                   "flag"
#define TAG_XML_OUT_EXTRATO_VO                             "ExtratoVO"
#define TAG_XML_OUT_EXTRACTO_DETALHADO					   "EXTRACTO_DETALHADO"
#define TAG_XML_OUT_CABECALHO							   "CABECALHO"
#define TAG_XML_OUT_INFO								   "INFO"
#define TAG_XML_OUT_EMISSAO								   "EMISSAO"
#define TAG_XML_OUT_PERIODO								   "PERIODO"
#define TAG_XML_OUT_CEL									   "CELULAR"
#define TAG_XML_OUT_CONTA								   "CONTA"
#define TAG_XML_OUT_PERFIL								   "PERFIL"
#define TAG_XML_OUT_MORADA								   "MORADA"
#define TAG_XML_OUT_LINHA								   "LINHA"
#define TAG_XML_OUT_IMG_ESQ								   "IMG_ESQ"
#define TAG_XML_OUT_IMG_DIR								   "IMG_DIR"
#define TAG_XML_OUT_TABELA								   "TABELA"
#define TAG_XML_OUT_REGISTRO							   "REGISTRO"
#define TAG_XML_OUT_ID									   "ID"
#define TAG_XML_OUT_ITEM								   "ITEM"
#define TAG_XML_OUT_DATAHORA							   "DATAHORA"
#define TAG_XML_OUT_DTEXP								   "DTEXP"
#define TAG_XML_OUT_CDTIPOBONUS							   "CDTIPOBONUS"
#define TAG_XML_OUT_TIPOBONUS							   "TIPOBONUS"
#define TAG_XML_OUT_VLBONUS								   "VLBONUS"
#define TAG_XML_OUT_TOTALRECARGAS						   "TOTALRECARGAS"
#define TAG_XML_OUT_CDTIPOSALDO							   "CDTIPOSALDO"
#define TAG_XML_OUT_TIPOSALDO							   "TIPOSALDO"
#define TAG_XML_OUT_CREDITOS							   "CREDITOS"
#define TAG_XML_OUT_DEBITOS								   "DEBITOS"
#define TAG_XML_OUT_SALDOATUAL							   "SALDOATUAL"
#define TAG_XML_OUT_MENSAGEM							   "MENSAGEM"
#define TAG_XML_OUT_MENSAGENS							   "MENSAGENS"
#define TAG_XML_OUT_MOTIVO								   "MOTIVO"
#define TAG_XML_OUT_OPERACAO							   "OPERACAO"
#define TAG_XML_OUT_CDTIPOAJUSTE						   "CDTIPOAJUSTE"
#define TAG_XML_OUT_TIPOAJUSTE							   "TIPOAJUSTE"
#define TAG_XML_OUT_DESCR								   "DESCRICAO"
#define TAG_XML_OUT_NRDESTINO							   "NRDESTINO"
#define TAG_XML_OUT_TIPOCHAMADA							   "TIPOCHAMADA"
#define TAG_XML_OUT_DURACAO								   "DURACAO"
#define TAG_XML_OUT_TOTAL  								   "TOTAL"
#define TAG_XML_OUT_BUFFER                                 "buffer"
#define TAG_XML_OUT_BACKEND                                "backEnd"
#define TAG_XML_OUT_PROMOCOES_VO                           "PromocoesVO"
#define TAG_XML_OUT_PROMOCOES_ITEM                         "PromocoesItem"
#define TAG_XML_OUT_DATA_SUBSCRIPCAO                       "dataSubscripcao"
#define TAG_XML_OUT_DATA_INI                               "dataIni"
#define TAG_XML_OUT_DATA_FIM                               "dataFim"
#define TAG_XML_OUT_SERVICOS_VO                            "ServicoVO"
#define TAG_XML_OUT_SERVICOS_ITEM                          "ServicosItem"
#define TAG_XML_OUT_NOME                                   "nome"
#define TAG_XML_OUT_CODIGO                                 "codigo"
#define TAG_XML_OUT_STATUS                                 "status"
#define TAG_XML_OUT_VALIDADE                               "validade"
#define TAG_XML_OUT_VALIDADEINICIAL                        "validadeInicial"
#define TAG_XML_OUT_FAVORITOS_VO                           "FavoritosVO"
#define TAG_XML_OUT_FAVORITOS_ITEM                         "FavoritosItem"
#define TAG_XML_OUT_NUMERO                                 "numero"
#define TAG_XML_OUT_SERVICO                                "servico"
#define TAG_XML_OUT_SERVICO_VO                             "ServicoVO"
#define TAG_XML_OUT_SUSPENDE_CELULAR_VO                    "SuspendeCelularVO"
#define TAG_XML_OUT_RELIGUE_CELULAR_VO                     "ReligueCelularVO"
#define TAG_XML_OUT_FAVORITO_VO                            "FavoritoVO"
#define TAG_XML_OUT_INTERCEPTACAO_VO                       "InterceptacaoVO"
#define TAG_XML_OUT_CLIENTE_VO                             "ClienteVO"
#define TAG_XML_OUT_DATA                                   "data"
#define TAG_XML_OUT_IN_PRIORIDADE                          "inPrioridade"
#define TAG_XML_OUT_NOTA_FISCAL_VO                         "NotaFiscalVO"
#define TAG_XML_OUT_NOTA_SERIE                             "notaSerie"
#define TAG_XML_OUT_DATA_EMISSAO                           "dataEmissao"
#define TAG_XML_OUT_CFOP                                   "CFOP"
#define TAG_XML_OUT_NOME_CLIENTE                           "nomeCliente"
#define TAG_XML_OUT_END_CLIENTE                            "endCliente"
#define TAG_XML_OUT_CEP_CLIENTE                            "cepCliente"
#define TAG_XML_OUT_MUNIC_CLIENTE                          "municCliente"
#define TAG_XML_OUT_UF_CLIENTE                             "ufCliente"
#define TAG_XML_OUT_CELULAR                                "celular"
#define TAG_XML_OUT_LOCAL                                  "local"
#define TAG_XML_OUT_QUITACAO                               "quitacao"
#define TAG_XML_OUT_VL_QUITACAO                            "vlQuitacao"
#define TAG_XML_OUT_BASE_ICMS                              "baseICMS"
#define TAG_XML_OUT_ICMS                                   "ICMS"
#define TAG_XML_OUT_PER_ICMS                               "perICMS"
#define TAG_XML_OUT_BASE_FUST                              "baseFUST"
#define TAG_XML_OUT_FUST                                   "FUST"
#define TAG_XML_OUT_BASE_FUNTEL                            "baseFUNTEL"
#define TAG_XML_OUT_FUNTEL                                 "FUNTEL"
#define TAG_XML_OUT_VL_SERVICO                             "vlServico"
#define TAG_XML_OUT_TOTAL_NOTA                             "totalNota"
#define TAG_XML_OUT_VL_PAGO                                "vlPago"
#define TAG_XML_OUT_HASHCODE                               "hashcode"
#define TAG_XML_OUT_INSC_ESTADUAL                          "inscEstadual"
#define TAG_XML_OUT_CPF                                    "CPF"
#define TAG_XML_OUT_CNPJ                                   "CNPJ"
#define TAG_XML_OUT_SALDO_TOTAL                            "saldoTotal"
#define TAG_XML_OUT_ABA_SERVICOS_VO                        "AbaServicosVO"
#define TAG_XML_OUT_LISTA_IMPEDIMENTO_VO                   "ListaImpedimentoVO"
#define TAG_XML_OUT_LISTA_SERVICOS                         "ListaServicos"
#define TAG_XML_OUT_TI_FATURAMENTO                         "TIFaturamento"
#define TAG_XML_OUT_IMPEDIMENTO_VO                         "ImpedimentoVO"
#define TAG_XML_OUT_LF_AJUSTES                             "LFAjustes"
#define TAG_XML_OUT_ID_CONTA                               "idConta"
#define TAG_XML_OUT_DT_AJUSTE                              "dtAjuste"
#define TAG_XML_OUT_VL_AJUSTE                              "vlAjuste"
#define TAG_XML_OUT_DT_NOTA                                "dtNota"
#define TAG_XML_OUT_DT_IMPEDIMENTO                         "dtImpedimento"
#define TAG_XML_OUT_DS_NOTA                                "dsNota"
#define TAG_XML_OUT_NM_SEQUENCIA                           "nmSequencia"
#define TAG_XML_OUT_SERIAL                                 "serial"
#define TAG_XML_OUT_DS_CICLO_FATURA                        "dsCicloFatura"
#define TAG_XML_OUT_IN_CONTA_COBRANCA                      "inContaCobranca"
#define TAG_XML_OUT_DS_FORMA_PAGAMENTO                     "dsFormaPagamento"
#define TAG_XML_OUT_CD_CODIGO_RETORNO                      "cdCodigoRetorno"
#define TAG_XML_OUT_NUM_VALOR_CONTA_CLIENTE_USUARIO        "numValorContaClienteUsuario"
#define TAG_XML_OUT_SG_CREDOR_DEVEDOR                      "sgCredorDevedor"
#define TAG_XML_OUT_DT_FECHAMENTO_VALORES                  "dtFechamentoValores"
#define TAG_XML_OUT_DT_VENCIMENTO_CONTA                    "dtVencimentoConta"
#define TAG_XML_OUT_SG_VERIFICACAO_DEBITO_AUTOMATICO       "sgVerificacaoDebitoAutomatico"
#define TAG_XML_OUT_DT_MES_REFERENCIA_PAGAMENTO            "dtMesReferenciaPagamento"
#define TAG_XML_OUT_DS_NOME_COMPLETO_CLIENTE               "dsNomeCompletoCliente"
#define TAG_XML_OUT_DT_DATA_EMISSAO_CONTA                  "dtDataEmissaoConta"
#define TAG_XML_OUT_DS_NUMERO_CPF_CLIENTE                  "dsNumeroCPFCliente"    
#define TAG_XML_OUT_DS_NUMERO_DA_CONTA                     "dsNumeroDaConta"
#define TAG_XML_OUT_NUM_IDENTIFICACAO_DEBITO_AUTOMATICO    "numIdentificacaoDebitoAutomatico"
#define TAG_XML_OUT_DT_DATA_VENCIMENTO_CONTA               "dtDataVencimentoConta"
#define TAG_XML_OUT_NUM_SALDO_CONTA_ABERTO                 "numSaldoContaAberto"
#define TAG_XML_OUT_NUM_PAGAMENTO_ABERTO                   "numPagamentoAberto"
#define TAG_XML_OUT_DS_NUMERO_CEP_CLIENTE                  "dsNumeroCEPCliente"
#define TAG_XML_OUT_DS_ENDERECO_CLIENTE                    "dsEnderecoCliente"
#define TAG_XML_OUT_DS_BAIRRO                              "dsBairro"
#define TAG_XML_OUT_DS_IMPEDIMENTO                         "dsImpedimento"
#define TAG_XML_OUT_DESC                                   "desc"
#define TAG_XML_OUT_IN_PERSONALIZADA                       "inPersonalizada"
#define TAG_XML_OUT_LUPA_FATURAMENTO_POS_VO                "LupaFaturamentoPosVO"
#define TAG_XML_OUT_LF_COBRANCA                            "LFCobranca"
#define TAG_XML_OUT_LF_FORMA_PAGAMENTO                     "LFFormaPagamento"
#define TAG_XML_OUT_DT_FATURAMENTO                         "dtFaturamento"
#define TAG_XML_OUT_VL_FATURADO                            "vlFaturado" 
#define TAG_XML_OUT_VL_ABERTO_ATUAL                        "vlAbertoAtual"
#define TAG_XML_OUT_VL_30_DIAS                             "vl30Dias"
#define TAG_XML_OUT_VL_60_DIAS                             "vl60Dias"
#define TAG_XML_OUT_VL_90_DIAS                             "vl90Dias"
#define TAG_XML_OUT_VL_MAIS_DIAS                           "vlMaisDias"
#define TAG_XML_OUT_DT_PAGAMENTO                           "dtPagamento"
#define TAG_XML_OUT_VL_PAGAMENTO                           "vlPagamento"
#define TAG_XML_OUT_DS_TIPO_PAGAMENTO                      "dsTipoPagamento"
#define TAG_XML_OUT_DS_REFERENCIA                          "dsReferencia"
#define TAG_XML_OUT_DS_MOTIVO_REVERSAO                     "dsMotivoReversao"
#define TAG_XML_OUT_DT_REVERSAO                            "dtReversao"
#define TAG_XML_OUT_CONTRATO                               "contrato"
#define TAG_XML_OUT_VALOR_MULTA                            "valorMulta"
#define TAG_XML_OUT_DATA_FIM_CONTRATO                      "dtFimContrato"
#define TAG_XML_OUT_DATA_INICIO_CONTRATO				   "dtInicioContrato"
#define TAG_XML_OUT_DT_VENCIMENTO                          "dtVencimento"
#define TAG_XML_OUT_LF_ESTIMATIVA_SALDO                    "LFEstimativaSaldo"
#define TAG_XML_OUT_DS_ESTIMATIVA_SALDO                    "dsEstimativaSaldo"
#define TAG_XML_OUT_DS_ESTIMATIVA_PROX_FATURA			   "dsEstimativaProxFatura"
#define TAG_XML_OUT_DS_DIVIDA							   "dsDivida"
#define TAG_XML_OUT_DT_FECHAMENTO_CICLO                    "dtFechamentoCiclo"
#define TAG_XML_OUT_DT_PROXIMA_FATURA                      "dtProximaFatura"
#define TAG_XML_OUT_QT_MINUTOS_UTILIZADOS                  "qtMinutosUtilizados"
#define TAG_XML_OUT_QT_TORPEDOS_UTILIZADOS                 "qtTorpedosUtilizados"
#define TAG_XML_OUT_LF_PAGAMENTO                           "LFPagamento"
#define TAG_XML_OUT_CLASSE                                 "classe"
#define TAG_XML_OUT_PLANO_VO                               "PlanoVO"
#define TAG_XML_OUT_IN_TARIFA                              "indTarifa"
#define TAG_XML_OUT_VAL_TARIFA                             "valTarifa"
#define TAG_XML_OUT_PLANO_ATUAL                            "planoAtual"
#define TAG_XML_OUT_TARIFA_REDUZIDA_VO                     "TarifaReduzidaVO"
#define TAG_XML_OUT_DIA_TARIFA_REDUZIDA                    "diaTarifaReduzida"
#define TAG_XML_OUT_QT_INC_RESTANTES                       "qtIncRestantes"
#define TAG_XML_OUT_QT_CAD_RESTANTES                       "qtCadRestantes"
#define TAG_XML_OUT_QT_ALT_GRATUITAS                       "qtAltGratuitas"
#define TAG_XML_OUT_QT_LIN_FAV_ATIVAS                      "qtLinFavAtivas"
#define TAG_XML_OUT_QT_LIN_FAV_JA_CADASTRADAS              "qtLinFavJaCadastradas"
#define TAG_XML_OUT_IND_TARIDA                             "indTarifa"
#define TAG_XML_OUT_VAL_TARIFA_INCLUSAO                    "valTarifaInclusao"
#define TAG_XML_OUT_VAL_TARIFA_ALTERACAO                   "valTarifaAlteracao"
#define TAG_XML_OUT_QT_MAX_INCLUSAO                        "qtMaxInclusao"
#define TAG_XML_OUT_DT_PRORROGACAO						   "dtProrrogacao"
#define TAG_XML_OUT_STATUS_PRORROGACAO					   "statusProrrogacao"
#define TAG_XML_OUT_QTDE_CONTAS_ABERTO					   "qtdeContasAberto"
#define TAG_XML_OUT_DT_PROXIMO_VENCIMENTO				   "dtProximoVencimento"
#define TAG_XML_OUT_SALDO_PARCIAL						   "saldoParcial"	
#define TAG_XML_OUT_DS_TIPO                                "dsTipo"
#define TAG_XML_OUT_DESTINO                                "destino"
#define TAG_XML_OUT_UNID_CONSUMIDAS                        "unidConsumidas"
#define TAG_XML_OUT_VALOR                                  "valor"
#define TAG_XML_OUT_CONSUMO                                "consumo"
#define TAG_XML_OUT_PLANO_NGIN								"planoNGIN"
#define TAG_XML_OUT_INSID									"inSid"

// Propriedades de TAGs XML de saida do FO
#define PROP_XML_OUT_XMLNS                                 "xmlns"
#define PROP_XML_OUT_VAL_XMLNS                             "cliente.fo.vivo.com.br/vo"

#define ECD_TAG_XML_IN_NE_LINHA                                   "24E0001"
#define ECD_TAG_XML_IN_VV_LINHA                                   "24E0002"
#define ECD_TAG_XML_IN_VI_LINHA                                   "24E0003"


// Codigos de erro (0500 a 0999)
#define ECD_TAG_XML_IN_NE_ID_CONTA_SIS_ORIGEM                     "24E0501"
#define ECD_TAG_XML_IN_VV_ID_CONTA_SIS_ORIGEM                     "24E0502"
#define ECD_TAG_XML_IN_VI_ID_CONTA_SIS_ORIGEM                     "24E0503"
#define ECD_TAG_XML_IN_NE_ID_LINHA_SIS_ORIGEM                     "24E0504"
#define ECD_TAG_XML_IN_VV_ID_LINHA_SIS_ORIGEM                     "24E0505"
#define ECD_TAG_XML_IN_VI_ID_LINHA_SIS_ORIGEM                     "24E0506"
#define ECD_TAG_XML_IN_NE_USUARIO                                 "24E0507"
#define ECD_TAG_XML_IN_VV_USUARIO                                 "24E0508"
#define ECD_TAG_XML_IN_VI_USUARIO                                 "24E0509"
#define ECD_TAG_XML_IN_NE_DATA_INI                                "24E0510"
#define ECD_TAG_XML_IN_VV_DATA_INI                                "24E0511"
#define ECD_TAG_XML_IN_VI_DATA_INI                                "24E0512"
#define ECD_TAG_XML_IN_NE_DATA_FIM                                "24E0513"
#define ECD_TAG_XML_IN_VV_DATA_FIM                                "24E0514"
#define ECD_TAG_XML_IN_VI_DATA_FIM                                "24E0515"
#define ECD_TAG_XML_IN_NE_OPERACAO                                "24E0516"
#define ECD_TAG_XML_IN_VV_OPERACAO                                "24E0517"
#define ECD_TAG_XML_IN_VI_OPERACAO                                "24E0518"
#define ECD_TAG_XML_IN_NE_SERVICO                                 "24E0519"
#define ECD_TAG_XML_IN_VV_SERVICO                                 "24E0520"
#define ECD_TAG_XML_IN_VI_SERVICO                                 "24E0521"
#define ECD_TAG_XML_IN_NE_MOTIVO                                  "24E0522"
#define ECD_TAG_XML_IN_VV_MOTIVO                                  "24E0523"
#define ECD_TAG_XML_IN_VI_MOTIVO                                  "24E0524"
#define ECD_TAG_XML_IN_NE_OBSERVACAO                              "24E0525"
#define ECD_TAG_XML_IN_VV_OBSERVACAO                              "24E0526"
#define ECD_TAG_XML_IN_VI_OBSERVACAO                              "24E0527"
#define ECD_TAG_XML_IN_NE_NUMERO_SERIAL                           "24E0528"
#define ECD_TAG_XML_IN_VV_NUMERO_SERIAL                           "24E0529"
#define ECD_TAG_XML_IN_VI_NUMERO_SERIAL                           "24E0530"
#define ECD_TAG_XML_IN_NE_BO_NUMERO                               "24E0531"
#define ECD_TAG_XML_IN_VV_BO_NUMERO                               "24E0532"
#define ECD_TAG_XML_IN_VI_BO_NUMERO                               "24E0533"
#define ECD_TAG_XML_IN_NE_BO_DATA                                 "24E0534"
#define ECD_TAG_XML_IN_VV_BO_DATA                                 "24E0535"
#define ECD_TAG_XML_IN_VI_BO_DATA                                 "24E0536"
#define ECD_TAG_XML_IN_NE_BO_DELEGACIA                            "24E0537"
#define ECD_TAG_XML_IN_VV_BO_DELEGACIA                            "24E0538"
#define ECD_TAG_XML_IN_VI_BO_DELEGACIA                            "24E0539"
#define ECD_TAG_XML_IN_NE_NUMERO_NOVO                             "24E0540"
#define ECD_TAG_XML_IN_VV_NUMERO_NOVO                             "24E0541"
#define ECD_TAG_XML_IN_VI_NUMERO_NOVO                             "24E0542"
#define ECD_TAG_XML_IN_NE_NUMERO_ANTIGO                           "24E0543"
#define ECD_TAG_XML_IN_VV_NUMERO_ANTIGO                           "24E0544"
#define ECD_TAG_XML_IN_VI_NUMERO_ANTIGO                           "24E0545"
#define ECD_TAG_XML_IN_NE_XMLNS                                   "24E0546"
#define ECD_TAG_XML_IN_VV_XMLNS                                   "24E0547"
#define ECD_TAG_XML_IN_VI_XMLNS                                   "24E0548"
#define ECD_TAG_XML_IN_NE_TIPO_CLIENTE                            "24E0549"
#define ECD_TAG_XML_IN_VV_TIPO_CLIENTE                            "24E0550"
#define ECD_TAG_XML_IN_VI_TIPO_CLIENTE                            "24E0551"
#define ECD_TAG_XML_IN_NE_NOME                                    "24E0552"
#define ECD_TAG_XML_IN_VV_NOME                                    "24E0553"
#define ECD_TAG_XML_IN_VI_NOME                                    "24E0554"
#define ECD_TAG_XML_IN_NE_NOME_ABREVIADO                          "24E0555"
#define ECD_TAG_XML_IN_VV_NOME_ABREVIADO                          "24E0556"
#define ECD_TAG_XML_IN_VI_NOME_ABREVIADO                          "24E0557"
#define ECD_TAG_XML_IN_NE_CONFIDENCIAL                            "24E0558"
#define ECD_TAG_XML_IN_VV_CONFIDENCIAL                            "24E0559"
#define ECD_TAG_XML_IN_VI_CONFIDENCIAL                            "24E0560"
#define ECD_TAG_XML_IN_NE_CPF                                     "24E0561"
#define ECD_TAG_XML_IN_VV_CPF                                     "24E0562"
#define ECD_TAG_XML_IN_VI_CPF                                     "24E0563"
#define ECD_TAG_XML_IN_NE_TIPO_CPF                                "24E0564"
#define ECD_TAG_XML_IN_VV_TIPO_CPF                                "24E0565"
#define ECD_TAG_XML_IN_VI_TIPO_CPF                                "24E0566"
#define ECD_TAG_XML_IN_NE_RG                                      "24E0567"
#define ECD_TAG_XML_IN_VV_RG                                      "24E0568"
#define ECD_TAG_XML_IN_VI_RG                                      "24E0569"
#define ECD_TAG_XML_IN_NE_TIPO_RG                                 "24E0570"
#define ECD_TAG_XML_IN_VV_TIPO_RG                                 "24E0571"
#define ECD_TAG_XML_IN_VI_TIPO_RG                                 "24E0572"
#define ECD_TAG_XML_IN_NE_DATA_EXPIRACAO                          "24E0573"
#define ECD_TAG_XML_IN_VV_DATA_EXPIRACAO                          "24E0574"
#define ECD_TAG_XML_IN_VI_DATA_EXPIRACAO                          "24E0575"
#define ECD_TAG_XML_IN_NE_ORGAO_EXPEDITOR                         "24E0576"
#define ECD_TAG_XML_IN_VV_ORGAO_EXPEDITOR                         "24E0577"
#define ECD_TAG_XML_IN_VI_ORGAO_EXPEDITOR                         "24E0578"
#define ECD_TAG_XML_IN_NE_ESTADO_EXPEDICAO                        "24E0579"
#define ECD_TAG_XML_IN_VV_ESTADO_EXPEDICAO                        "24E0580"
#define ECD_TAG_XML_IN_VI_ESTADO_EXPEDICAO                        "24E0581"
#define ECD_TAG_XML_IN_NE_DATA_NASCIMENTO                         "24E0582"
#define ECD_TAG_XML_IN_VV_DATA_NASCIMENTO                         "24E0583"
#define ECD_TAG_XML_IN_VI_DATA_NASCIMENTO                         "24E0584"
#define ECD_TAG_XML_IN_NE_ESTADO_CIVIL                            "24E0585"
#define ECD_TAG_XML_IN_VV_ESTADO_CIVIL                            "24E0586"
#define ECD_TAG_XML_IN_VI_ESTADO_CIVIL                            "24E0587"
#define ECD_TAG_XML_IN_NE_COD_SEXO                                "24E0588"
#define ECD_TAG_XML_IN_VV_COD_SEXO                                "24E0589"
#define ECD_TAG_XML_IN_VI_COD_SEXO                                "24E0590"
#define ECD_TAG_XML_IN_NE_TELEFONE                                "24E0591"
#define ECD_TAG_XML_IN_VV_TELEFONE                                "24E0592"
#define ECD_TAG_XML_IN_VI_TELEFONE                                "24E0593"
#define ECD_TAG_XML_IN_NE_FAX                                     "24E0594"
#define ECD_TAG_XML_IN_VV_FAX                                     "24E0595"
#define ECD_TAG_XML_IN_VI_FAX                                     "24E0596"
#define ECD_TAG_XML_IN_NE_EMAIL                                   "24E0597"
#define ECD_TAG_XML_IN_VV_EMAIL                                   "24E0598"
#define ECD_TAG_XML_IN_VI_EMAIL                                   "24E0599"
#define ECD_TAG_XML_IN_NE_NUM_DEPEND                              "24E0600"
#define ECD_TAG_XML_IN_VV_NUM_DEPEND                              "24E0601"
#define ECD_TAG_XML_IN_VI_NUM_DEPEND                              "24E0602"
#define ECD_TAG_XML_IN_NE_PASSAPORTE                              "24E0603"
#define ECD_TAG_XML_IN_VV_PASSAPORTE                              "24E0604"
#define ECD_TAG_XML_IN_VI_PASSAPORTE                              "24E0605"
#define ECD_TAG_XML_IN_NE_TIPO_PASSAPORTE                         "24E0606"
#define ECD_TAG_XML_IN_VV_TIPO_PASSAPORTE                         "24E0607"
#define ECD_TAG_XML_IN_VI_TIPO_PASSAPORTE                         "24E0608"
#define ECD_TAG_XML_IN_NE_CARTA_CONDUCAO                          "24E0609"
#define ECD_TAG_XML_IN_VV_CARTA_CONDUCAO                          "24E0610"
#define ECD_TAG_XML_IN_VI_CARTA_CONDUCAO                          "24E0611"
#define ECD_TAG_XML_IN_NE_TIPO_CARTA_COND                         "24E0612"
#define ECD_TAG_XML_IN_VV_TIPO_CARTA_COND                         "24E0613"
#define ECD_TAG_XML_IN_VI_TIPO_CARTA_COND                         "24E0614"
#define ECD_TAG_XML_IN_NE_AO_CUIDADO_DE                           "24E0615"
#define ECD_TAG_XML_IN_VV_AO_CUIDADO_DE                           "24E0616"
#define ECD_TAG_XML_IN_VI_AO_CUIDADO_DE                           "24E0617"
#define ECD_TAG_XML_IN_NE_OBS                                     "24E0618"
#define ECD_TAG_XML_IN_VV_OBS                                     "24E0619"
#define ECD_TAG_XML_IN_VI_OBS                                     "24E0620"
#define ECD_TAG_XML_IN_NE_CONSERVATORIA_REGISTRO                  "24E0621"
#define ECD_TAG_XML_IN_VV_CONSERVATORIA_REGISTRO                  "24E0622"
#define ECD_TAG_XML_IN_VI_CONSERVATORIA_REGISTRO                  "24E0623"
#define ECD_TAG_XML_IN_NE_CNPJ                                    "24E0624"
#define ECD_TAG_XML_IN_VV_CNPJ                                    "24E0625"
#define ECD_TAG_XML_IN_VI_CNPJ                                    "24E0626"
#define ECD_TAG_XML_IN_NE_CNAE                                    "24E0627"
#define ECD_TAG_XML_IN_VV_CNAE                                    "24E0628"
#define ECD_TAG_XML_IN_VI_CNAE                                    "24E0629"
#define ECD_TAG_XML_IN_NE_HABILITACOES                            "24E0630"
#define ECD_TAG_XML_IN_VV_HABILITACOES                            "24E0631"
#define ECD_TAG_XML_IN_VI_HABILITACOES                            "24E0632"
#define ECD_TAG_XML_IN_NE_OUTRO_CELULAR                           "24E0633"
#define ECD_TAG_XML_IN_VV_OUTRO_CELULAR                           "24E0634"
#define ECD_TAG_XML_IN_VI_OUTRO_CELULAR                           "24E0635"
#define ECD_TAG_XML_IN_NE_TIPO_CONTA                              "24E0636"
#define ECD_TAG_XML_IN_VV_TIPO_CONTA                              "24E0637"
#define ECD_TAG_XML_IN_VI_TIPO_CONTA                              "24E0638"
#define ECD_TAG_XML_IN_NE_SUB_TIPO                                "24E0639"
#define ECD_TAG_XML_IN_VV_SUB_TIPO                                "24E0640"
#define ECD_TAG_XML_IN_VI_SUB_TIPO                                "24E0641"
#define ECD_TAG_XML_IN_NE_RENDA_MENSAL                            "24E0642"
#define ECD_TAG_XML_IN_VV_RENDA_MENSAL                            "24E0643"
#define ECD_TAG_XML_IN_VI_RENDA_MENSAL                            "24E0644"
#define ECD_TAG_XML_IN_NE_CONTA_CORRENT                           "24E0645"
#define ECD_TAG_XML_IN_VV_CONTA_CORRENT                           "24E0646"
#define ECD_TAG_XML_IN_VI_CONTA_CORRENT                           "24E0647"
#define ECD_TAG_XML_IN_NE_BANCO                                   "24E0648"
#define ECD_TAG_XML_IN_VV_BANCO                                   "24E0649"
#define ECD_TAG_XML_IN_VI_BANCO                                   "24E0650"
#define ECD_TAG_XML_IN_NE_IE                                      "24E0651"
#define ECD_TAG_XML_IN_VV_IE                                      "24E0652"
#define ECD_TAG_XML_IN_VI_IE                                      "24E0653"
#define ECD_TAG_XML_IN_NE_NOME_SUFIXO                             "24E0654"
#define ECD_TAG_XML_IN_VV_NOME_SUFIXO                             "24E0655"
#define ECD_TAG_XML_IN_VI_NOME_SUFIXO                             "24E0656"
#define ECD_TAG_XML_IN_NE_PRIMEIRO_NOME                           "24E0657"
#define ECD_TAG_XML_IN_VV_PRIMEIRO_NOME                           "24E0658"
#define ECD_TAG_XML_IN_VI_PRIMEIRO_NOME                           "24E0659"
#define ECD_TAG_XML_IN_NE_SOBRE_NOME                              "24E0660"
#define ECD_TAG_XML_IN_VV_SOBRE_NOME                              "24E0661"
#define ECD_TAG_XML_IN_VI_SOBRE_NOME                              "24E0662"
#define ECD_TAG_XML_IN_NE_NOME_CONTATO                            "24E0663"
#define ECD_TAG_XML_IN_VV_NOME_CONTATO                            "24E0664"
#define ECD_TAG_XML_IN_VI_NOME_CONTATO                            "24E0665"
#define ECD_TAG_XML_IN_NE_CARTEIRA_TRABALHO                       "24E0666"
#define ECD_TAG_XML_IN_VV_CARTEIRA_TRABALHO                       "24E0667"
#define ECD_TAG_XML_IN_VI_CARTEIRA_TRABALHO                       "24E0668"
#define ECD_TAG_XML_IN_NE_ENDERECO                                "24E0669"
#define ECD_TAG_XML_IN_VV_ENDERECO                                "24E0670"
#define ECD_TAG_XML_IN_VI_ENDERECO                                "24E0671"
#define ECD_TAG_XML_IN_NE_COMPLEMENTO                             "24E0672"
#define ECD_TAG_XML_IN_VV_COMPLEMENTO                             "24E0673"
#define ECD_TAG_XML_IN_VI_COMPLEMENTO                             "24E0674"
#define ECD_TAG_XML_IN_NE_BAIRRO                                  "24E0675"
#define ECD_TAG_XML_IN_VV_BAIRRO                                  "24E0676"
#define ECD_TAG_XML_IN_VI_BAIRRO                                  "24E0677"
#define ECD_TAG_XML_IN_NE_CEP                                     "24E0678"
#define ECD_TAG_XML_IN_VV_CEP                                     "24E0679"
#define ECD_TAG_XML_IN_VI_CEP                                     "24E0680"
#define ECD_TAG_XML_IN_NE_CIDADE                                  "24E0681"
#define ECD_TAG_XML_IN_VV_CIDADE                                  "24E0682"
#define ECD_TAG_XML_IN_VI_CIDADE                                  "24E0683"
#define ECD_TAG_XML_IN_NE_ESTADO                                  "24E0684"
#define ECD_TAG_XML_IN_VV_ESTADO                                  "24E0685"
#define ECD_TAG_XML_IN_VI_ESTADO                                  "24E0686"
#define ECD_TAG_XML_IN_NE_PAIS                                    "24E0687"
#define ECD_TAG_XML_IN_VV_PAIS                                    "24E0688"
#define ECD_TAG_XML_IN_VI_PAIS                                    "24E0689"
#define ECD_TAG_XML_IN_NE_LOGRADOURO                              "24E0690"
#define ECD_TAG_XML_IN_VV_LOGRADOURO                              "24E0691"
#define ECD_TAG_XML_IN_VI_LOGRADOURO                              "24E0692"
#define ECD_TAG_XML_IN_NE_NUMERO                                  "24E0693"
#define ECD_TAG_XML_IN_VV_NUMERO                                  "24E0694"
#define ECD_TAG_XML_IN_VI_NUMERO                                  "24E0695"
#define ECD_TAG_XML_IN_NE_NOTA_FISCAL                             "24E0696"
#define ECD_TAG_XML_IN_VV_NOTA_FISCAL                             "24E0697"
#define ECD_TAG_XML_IN_VI_NOTA_FISCAL                             "24E0698"
#define ECD_TAG_XML_IN_NE_DATA                                    "24E0699"
#define ECD_TAG_XML_IN_VV_DATA                                    "24E0700"
#define ECD_TAG_XML_IN_VI_DATA                                    "24E0701"
#define ECD_TAG_XML_IN_NE_DATAPEDIDO                              "24E0702"
#define ECD_TAG_XML_IN_VV_DATAPEDIDO                              "24E0703"
#define ECD_TAG_XML_IN_VI_DATAPEDIDO                              "24E0704"
#define ECD_TAG_XML_IN_NE_CLASSE                                  "24E0705"
#define ECD_TAG_XML_IN_VV_CLASSE                                  "24E0706"
#define ECD_TAG_XML_IN_VI_CLASSE                                  "24E0707"
#define ECD_TAG_XML_IN_NE_NOVO_PLANO                              "24E0708"
#define ECD_TAG_XML_IN_VV_NOVO_PLANO                              "24E0709"
#define ECD_TAG_XML_IN_VI_NOVO_PLANO                              "24E0710"
#define ECD_TAG_XML_IN_NE_DIA_TARIFA_REDUZIDA                     "24E0711"
#define ECD_TAG_XML_IN_VV_DIA_TARIFA_REDUZIDA                     "24E0712"
#define ECD_TAG_XML_IN_VI_DIA_TARIFA_REDUZIDA                     "24E0713"
#define ECD_TAG_XML_IN_MV_DIA_TARIFA_REDUZIDA                     "24E0714"
#define ECD_TAG_XML_IN_NE_REF_FATURA                              "24E0715"
#define ECD_TAG_XML_IN_VV_REF_FATURA                              "24E0716"
#define ECD_TAG_XML_IN_NE_DATA_PGTO                               "24E0717"
#define ECD_TAG_XML_IN_VV_DATA_PGTO                               "24E0718"
#define ECD_TAG_XML_IN_NE_VALOR                                   "24E0719"
#define ECD_TAG_XML_IN_VV_VALOR                                   "24E0720"
#define ECD_TAG_XML_IN_VV_PERIODO								  "24E0721"
#define ECD_TAG_XML_IN_NE_PERIODO								  "24E0722"
#define ECD_TAG_XML_IN_INVOICEID								  "24E0723"


// Código para linha inválida
#define ECD_INVALID_LINE                                          "24E9001"

// Código para linha não ativa
#define ECD_DISABLED_LINE                                         "24E9002"

#define ECD_ID_CONTA_NOT_FOUND                                    "24E9003"

#define ECD_PLANO_INVALIDO                                        "24E9004"

#define ECD_BLOQUEIO_DDI_INVALIDO								  "24E9005"

//Quando é efetuada a reinicialização de caixa postal e a mesma encontra-se desativada.
#define ECD_REINICIA_CXPOSTAL_DES								  "24E1040"
#define EMSG_REINICIA_CXPOSTAL_DES								  "Não é possível reinicializar a Caixa Postal. Este serviço encontra-se desativado!."

// Mensagens de erro
#define EMSG_TAG_XML_IN_NE_LINHA                                  "TAG ProxyLinha não encontrada"
#define EMSG_TAG_XML_IN_VV_LINHA                                  "TAG ProxyLinha com valor vazio"
#define EMSG_TAG_XML_IN_VI_LINHA                                  "TAG ProxyLinha com valor inválido"
#define EMSG_TAG_XML_IN_NE_ID_CONTA_SIS_ORIGEM                    "TAG idcontasistemaorigem não encontrada"
#define EMSG_TAG_XML_IN_VV_ID_CONTA_SIS_ORIGEM                    "TAG idcontasistemaorigem com valor vazio"
#define EMSG_TAG_XML_IN_VI_ID_CONTA_SIS_ORIGEM                    "TAG idcontasistemaorigem com valor inválido"
#define EMSG_TAG_XML_IN_NE_ID_LINHA_SIS_ORIGEM                    "TAG idlinhasistemaorigem não encontrada"
#define EMSG_TAG_XML_IN_VV_ID_LINHA_SIS_ORIGEM                    "TAG idlinhasistemaorigem com valor vazio"
#define EMSG_TAG_XML_IN_VI_ID_LINHA_SIS_ORIGEM                    "TAG idlinhasistemaorigem com valor inválido"
#define EMSG_TAG_XML_IN_NE_USUARIO                                "TAG usuario não encontrada"
#define EMSG_TAG_XML_IN_VV_USUARIO                                "TAG usuario com valor vazio"
#define EMSG_TAG_XML_IN_VI_USUARIO                                "TAG usuario com valor inválido"
#define EMSG_TAG_XML_IN_NE_DATA_INI                               "TAG dataIni não encontrada"
#define EMSG_TAG_XML_IN_VV_DATA_INI                               "TAG dataIni com valor vazio"
#define EMSG_TAG_XML_IN_VI_DATA_INI                               "TAG dataIni com valor inválido"
#define EMSG_TAG_XML_IN_NE_DATA_FIM                               "TAG dataFim não encontrada"
#define EMSG_TAG_XML_IN_VV_DATA_FIM                               "TAG dataFim com valor vazio"
#define EMSG_TAG_XML_IN_VI_DATA_FIM                               "TAG dataFim com valor inválido"
#define EMSG_TAG_XML_IN_NE_OPERACAO                               "TAG operacao não encontrada"
#define EMSG_TAG_XML_IN_VV_OPERACAO                               "TAG operacao com valor vazio"
#define EMSG_TAG_XML_IN_VI_OPERACAO                               "TAG operacao com valor inválido"
#define EMSG_TAG_XML_IN_NE_SERVICO                                "TAG servico não encontrada"
#define EMSG_TAG_XML_IN_VV_SERVICO                                "TAG servico com valor vazio"
#define EMSG_TAG_XML_IN_VI_SERVICO                                "TAG servico com valor inválido"
#define EMSG_TAG_XML_IN_NE_MOTIVO                                 "TAG motivo não encontrada"
#define EMSG_TAG_XML_IN_VV_MOTIVO                                 "TAG motivo com valor vazio"
#define EMSG_TAG_XML_IN_VI_MOTIVO                                 "TAG motivo com valor inválido"
#define EMSG_TAG_XML_IN_NE_OBSERVACAO                             "TAG observacao não encontrada"
#define EMSG_TAG_XML_IN_VV_OBSERVACAO                             "TAG observacao com valor vazio"
#define EMSG_TAG_XML_IN_VI_OBSERVACAO                             "TAG observacao com valor inválido"
#define EMSG_TAG_XML_IN_NE_NUMERO_SERIAL                          "TAG numeroSerial não encontrada"
#define EMSG_TAG_XML_IN_VV_NUMERO_SERIAL                          "TAG numeroSerial com valor vazio"
#define EMSG_TAG_XML_IN_VI_NUMERO_SERIAL                          "TAG numeroSerial com valor inválido"
#define EMSG_TAG_XML_IN_NE_BO_NUMERO                              "TAG BONumero não encontrada"
#define EMSG_TAG_XML_IN_VV_BO_NUMERO                              "TAG BONumero com valor vazio"
#define EMSG_TAG_XML_IN_VI_BO_NUMERO                              "TAG BONumero com valor inválido"
#define EMSG_TAG_XML_IN_NE_BO_DATA                                "TAG BOData não encontrada"
#define EMSG_TAG_XML_IN_VV_BO_DATA                                "TAG BOData com valor vazio"
#define EMSG_TAG_XML_IN_VI_BO_DATA                                "TAG BOData com valor inválido"
#define EMSG_TAG_XML_IN_NE_BO_DELEGACIA                           "TAG BODelegacia não encontrada"
#define EMSG_TAG_XML_IN_VV_BO_DELEGACIA                           "TAG BODelegacia com valor vazio"
#define EMSG_TAG_XML_IN_VI_BO_DELEGACIA                           "TAG BODelegacia com valor inválido"
#define EMSG_TAG_XML_IN_NE_NUMERO_NOVO                            "TAG numeroNovo não encontrada"
#define EMSG_TAG_XML_IN_VV_NUMERO_NOVO                            "TAG numeroNovo com valor vazio"
#define EMSG_TAG_XML_IN_VI_NUMERO_NOVO                            "TAG numeroNovo com valor inválido"
#define EMSG_TAG_XML_IN_NE_NUMERO_ANTIGO                          "TAG numeroAntigo não encontrada"
#define EMSG_TAG_XML_IN_VV_NUMERO_ANTIGO                          "TAG numeroAntigo com valor vazio"
#define EMSG_TAG_XML_IN_VI_NUMERO_ANTIGO                          "TAG numeroAntigo com valor inválido"
#define EMSG_TAG_XML_IN_NE_XMLNS                                  "TAG xmlns não encontrada"
#define EMSG_TAG_XML_IN_VV_XMLNS                                  "TAG xmlns com valor vazio"
#define EMSG_TAG_XML_IN_VI_XMLNS                                  "TAG xmlns com valor inválido"
#define EMSG_TAG_XML_IN_NE_TIPO_CLIENTE                           "TAG tipoCliente não encontrada"
#define EMSG_TAG_XML_IN_VV_TIPO_CLIENTE                           "TAG tipoCliente com valor vazio"
#define EMSG_TAG_XML_IN_VI_TIPO_CLIENTE                           "TAG tipoCliente com valor inválido"
#define EMSG_TAG_XML_IN_NE_NOME                                   "TAG nome não encontrada"
#define EMSG_TAG_XML_IN_VV_NOME                                   "TAG nome com valor vazio"
#define EMSG_TAG_XML_IN_VI_NOME                                   "TAG nome com valor inválido"
#define EMSG_TAG_XML_IN_NE_NOME_ABREVIADO                         "TAG nomeAbreviado não encontrada"
#define EMSG_TAG_XML_IN_VV_NOME_ABREVIADO                         "TAG nomeAbreviado com valor vazio"
#define EMSG_TAG_XML_IN_VI_NOME_ABREVIADO                         "TAG nomeAbreviado com valor inválido"
#define EMSG_TAG_XML_IN_NE_CONFIDENCIAL                           "TAG confidencial não encontrada"
#define EMSG_TAG_XML_IN_VV_CONFIDENCIAL                           "TAG confidencial com valor vazio"
#define EMSG_TAG_XML_IN_VI_CONFIDENCIAL                           "TAG confidencial com valor inválido"
#define EMSG_TAG_XML_IN_NE_CPF                                    "TAG CPF não encontrada"
#define EMSG_TAG_XML_IN_VV_CPF                                    "TAG CPF com valor vazio"
#define EMSG_TAG_XML_IN_VI_CPF                                    "TAG CPF com valor inválido"
#define EMSG_TAG_XML_IN_NE_TIPO_CPF                               "TAG tipoCPF não encontrada"
#define EMSG_TAG_XML_IN_VV_TIPO_CPF                               "TAG tipoCPF com valor vazio"
#define EMSG_TAG_XML_IN_VI_TIPO_CPF                               "TAG tipoCPF com valor inválido"
#define EMSG_TAG_XML_IN_NE_RG                                     "TAG RG não encontrada"
#define EMSG_TAG_XML_IN_VV_RG                                     "TAG RG com valor vazio"
#define EMSG_TAG_XML_IN_VI_RG                                     "TAG RG com valor inválido"
#define EMSG_TAG_XML_IN_NE_TIPO_RG                                "TAG tipoRG não encontrada"
#define EMSG_TAG_XML_IN_VV_TIPO_RG                                "TAG tipoRG com valor vazio"
#define EMSG_TAG_XML_IN_VI_TIPO_RG                                "TAG tipoRG com valor inválido"
#define EMSG_TAG_XML_IN_NE_DATA_EXPIRACAO                         "TAG dataExpiracao não encontrada"
#define EMSG_TAG_XML_IN_VV_DATA_EXPIRACAO                         "TAG dataExpiracao com valor vazio"
#define EMSG_TAG_XML_IN_VI_DATA_EXPIRACAO                         "TAG dataExpiracao com valor inválido"
#define EMSG_TAG_XML_IN_NE_ORGAO_EXPEDITOR                        "TAG orgaoExpeditor não encontrada"
#define EMSG_TAG_XML_IN_VV_ORGAO_EXPEDITOR                        "TAG orgaoExpeditor com valor vazio"
#define EMSG_TAG_XML_IN_VI_ORGAO_EXPEDITOR                        "TAG orgaoExpeditor com valor inválido"
#define EMSG_TAG_XML_IN_NE_ESTADO_EXPEDICAO                       "TAG estadoExpedicao não encontrada"
#define EMSG_TAG_XML_IN_VV_ESTADO_EXPEDICAO                       "TAG estadoExpedicao com valor vazio"
#define EMSG_TAG_XML_IN_VI_ESTADO_EXPEDICAO                       "TAG estadoExpedicao com valor inválido"
#define EMSG_TAG_XML_IN_NE_DATA_NASCIMENTO                        "TAG dataNascimento não encontrada"
#define EMSG_TAG_XML_IN_VV_DATA_NASCIMENTO                        "TAG dataNascimento com valor vazio"
#define EMSG_TAG_XML_IN_VI_DATA_NASCIMENTO                        "TAG dataNascimento com valor inválido"
#define EMSG_TAG_XML_IN_NE_ESTADO_CIVIL                           "TAG estadoCivil não encontrada"
#define EMSG_TAG_XML_IN_VV_ESTADO_CIVIL                           "TAG estadoCivil com valor vazio"
#define EMSG_TAG_XML_IN_VI_ESTADO_CIVIL                           "TAG estadoCivil com valor inválido"
#define EMSG_TAG_XML_IN_NE_COD_SEXO                               "TAG codSexo não encontrada"
#define EMSG_TAG_XML_IN_VV_COD_SEXO                               "TAG codSexo com valor vazio"
#define EMSG_TAG_XML_IN_VI_COD_SEXO                               "TAG codSexo com valor inválido"
#define EMSG_TAG_XML_IN_NE_TELEFONE                               "TAG telefone não encontrada"
#define EMSG_TAG_XML_IN_VV_TELEFONE                               "TAG telefone com valor vazio"
#define EMSG_TAG_XML_IN_VI_TELEFONE                               "TAG telefone com valor inválido"
#define EMSG_TAG_XML_IN_NE_FAX                                    "TAG fax não encontrada"
#define EMSG_TAG_XML_IN_VV_FAX                                    "TAG fax com valor vazio"
#define EMSG_TAG_XML_IN_VI_FAX                                    "TAG fax com valor inválido"
#define EMSG_TAG_XML_IN_NE_EMAIL                                  "TAG eMail não encontrada"
#define EMSG_TAG_XML_IN_VV_EMAIL                                  "TAG eMail com valor vazio"
#define EMSG_TAG_XML_IN_VI_EMAIL                                  "TAG eMail com valor inválido"
#define EMSG_TAG_XML_IN_NE_NUM_DEPEND                             "TAG numDepend não encontrada"
#define EMSG_TAG_XML_IN_VV_NUM_DEPEND                             "TAG numDepend com valor vazio"
#define EMSG_TAG_XML_IN_VI_NUM_DEPEND                             "TAG numDepend com valor inválido"
#define EMSG_TAG_XML_IN_NE_PASSAPORTE                             "TAG passaporte não encontrada"
#define EMSG_TAG_XML_IN_VV_PASSAPORTE                             "TAG passaporte com valor vazio"
#define EMSG_TAG_XML_IN_VI_PASSAPORTE                             "TAG passaporte com valor inválido"
#define EMSG_TAG_XML_IN_NE_TIPO_PASSAPORTE                        "TAG tipoPassaporte não encontrada"
#define EMSG_TAG_XML_IN_VV_TIPO_PASSAPORTE                        "TAG tipoPassaporte com valor vazio"
#define EMSG_TAG_XML_IN_VI_TIPO_PASSAPORTE                        "TAG tipoPassaporte com valor inválido"
#define EMSG_TAG_XML_IN_NE_CARTA_CONDUCAO                         "TAG cartaConducao não encontrada"
#define EMSG_TAG_XML_IN_VV_CARTA_CONDUCAO                         "TAG cartaConducao com valor vazio"
#define EMSG_TAG_XML_IN_VI_CARTA_CONDUCAO                         "TAG cartaConducao com valor inválido"
#define EMSG_TAG_XML_IN_NE_TIPO_CARTA_COND                        "TAG tipoCartaCond não encontrada"
#define EMSG_TAG_XML_IN_VV_TIPO_CARTA_COND                        "TAG tipoCartaCond com valor vazio"
#define EMSG_TAG_XML_IN_VI_TIPO_CARTA_COND                        "TAG tipoCartaCond com valor inválido"
#define EMSG_TAG_XML_IN_NE_AO_CUIDADO_DE                          "TAG aoCuidadoDe não encontrada"
#define EMSG_TAG_XML_IN_VV_AO_CUIDADO_DE                          "TAG aoCuidadoDe com valor vazio"
#define EMSG_TAG_XML_IN_VI_AO_CUIDADO_DE                          "TAG aoCuidadoDe com valor inválido"
#define EMSG_TAG_XML_IN_NE_OBS                                    "TAG obs não encontrada"
#define EMSG_TAG_XML_IN_VV_OBS                                    "TAG obs com valor vazio"
#define EMSG_TAG_XML_IN_VI_OBS                                    "TAG obs com valor inválido"
#define EMSG_TAG_XML_IN_NE_CONSERVATORIA_REGISTRO                 "TAG conservatoriaRegistro não encontrada"
#define EMSG_TAG_XML_IN_VV_CONSERVATORIA_REGISTRO                 "TAG conservatoriaRegistro com valor vazio"
#define EMSG_TAG_XML_IN_VI_CONSERVATORIA_REGISTRO                 "TAG conservatoriaRegistro com valor inválido"
#define EMSG_TAG_XML_IN_NE_CNPJ                                   "TAG CNPJ não encontrada"
#define EMSG_TAG_XML_IN_VV_CNPJ                                   "TAG CNPJ com valor vazio"
#define EMSG_TAG_XML_IN_VI_CNPJ                                   "TAG CNPJ com valor inválido"
#define EMSG_TAG_XML_IN_NE_CNAE                                   "TAG CNAE não encontrada"
#define EMSG_TAG_XML_IN_VV_CNAE                                   "TAG CNAE com valor vazio"
#define EMSG_TAG_XML_IN_VI_CNAE                                   "TAG CNAE com valor inválido"
#define EMSG_TAG_XML_IN_NE_HABILITACOES                           "TAG habilitacoes não encontrada"
#define EMSG_TAG_XML_IN_VV_HABILITACOES                           "TAG habilitacoes com valor vazio"
#define EMSG_TAG_XML_IN_VI_HABILITACOES                           "TAG habilitacoes com valor inválido"
#define EMSG_TAG_XML_IN_NE_OUTRO_CELULAR                          "TAG outroCelular não encontrada"
#define EMSG_TAG_XML_IN_VV_OUTRO_CELULAR                          "TAG outroCelular com valor vazio"
#define EMSG_TAG_XML_IN_VI_OUTRO_CELULAR                          "TAG outroCelular com valor inválido"
#define EMSG_TAG_XML_IN_NE_TIPO_CONTA                             "TAG tipoConta não encontrada"
#define EMSG_TAG_XML_IN_VV_TIPO_CONTA                             "TAG tipoConta com valor vazio"
#define EMSG_TAG_XML_IN_VI_TIPO_CONTA                             "TAG tipoConta com valor inválido"
#define EMSG_TAG_XML_IN_NE_SUB_TIPO                               "TAG subTipo não encontrada"
#define EMSG_TAG_XML_IN_VV_SUB_TIPO                               "TAG subTipo com valor vazio"
#define EMSG_TAG_XML_IN_VI_SUB_TIPO                               "TAG subTipo com valor inválido"
#define EMSG_TAG_XML_IN_NE_RENDA_MENSAL                           "TAG rendaMensal não encontrada"
#define EMSG_TAG_XML_IN_VV_RENDA_MENSAL                           "TAG rendaMensal com valor vazio"
#define EMSG_TAG_XML_IN_VI_RENDA_MENSAL                           "TAG rendaMensal com valor inválido"
#define EMSG_TAG_XML_IN_NE_CONTA_CORRENT                          "TAG contaCorrent não encontrada"
#define EMSG_TAG_XML_IN_VV_CONTA_CORRENT                          "TAG contaCorrent com valor vazio"
#define EMSG_TAG_XML_IN_VI_CONTA_CORRENT                          "TAG contaCorrent com valor inválido"
#define EMSG_TAG_XML_IN_NE_BANCO                                  "TAG banco não encontrada"
#define EMSG_TAG_XML_IN_VV_BANCO                                  "TAG banco com valor vazio"
#define EMSG_TAG_XML_IN_VI_BANCO                                  "TAG banco com valor inválido"
#define EMSG_TAG_XML_IN_NE_IE                                     "TAG IE não encontrada"
#define EMSG_TAG_XML_IN_VV_IE                                     "TAG IE com valor vazio"
#define EMSG_TAG_XML_IN_VI_IE                                     "TAG IE com valor inválido"
#define EMSG_TAG_XML_IN_NE_NOME_SUFIXO                            "TAG nomeSufixo não encontrada"
#define EMSG_TAG_XML_IN_VV_NOME_SUFIXO                            "TAG nomeSufixo com valor vazio"
#define EMSG_TAG_XML_IN_VI_NOME_SUFIXO                            "TAG nomeSufixo com valor inválido"
#define EMSG_TAG_XML_IN_NE_PRIMEIRO_NOME                          "TAG primeiroNome não encontrada"
#define EMSG_TAG_XML_IN_VV_PRIMEIRO_NOME                          "TAG primeiroNome com valor vazio"
#define EMSG_TAG_XML_IN_VI_PRIMEIRO_NOME                          "TAG primeiroNome com valor inválido"
#define EMSG_TAG_XML_IN_NE_SOBRE_NOME                             "TAG sobreNome não encontrada"
#define EMSG_TAG_XML_IN_VV_SOBRE_NOME                             "TAG sobreNome com valor vazio"
#define EMSG_TAG_XML_IN_VI_SOBRE_NOME                             "TAG sobreNome com valor inválido"
#define EMSG_TAG_XML_IN_NE_NOME_CONTATO                           "TAG nomeContato não encontrada"
#define EMSG_TAG_XML_IN_VV_NOME_CONTATO                           "TAG nomeContato com valor vazio"
#define EMSG_TAG_XML_IN_VI_NOME_CONTATO                           "TAG nomeContato com valor inválido"
#define EMSG_TAG_XML_IN_NE_CARTEIRA_TRABALHO                      "TAG carteiraTrabalho não encontrada"
#define EMSG_TAG_XML_IN_VV_CARTEIRA_TRABALHO                      "TAG carteiraTrabalho com valor vazio"
#define EMSG_TAG_XML_IN_VI_CARTEIRA_TRABALHO                      "TAG carteiraTrabalho com valor inválido"
#define EMSG_TAG_XML_IN_NE_ENDERECO                               "TAG endereco não encontrada"
#define EMSG_TAG_XML_IN_VV_ENDERECO                               "TAG endereco com valor vazio"
#define EMSG_TAG_XML_IN_VI_ENDERECO                               "TAG endereco com valor inválido"
#define EMSG_TAG_XML_IN_NE_COMPLEMENTO                            "TAG complemento não encontrada"
#define EMSG_TAG_XML_IN_VV_COMPLEMENTO                            "TAG complemento com valor vazio"
#define EMSG_TAG_XML_IN_VI_COMPLEMENTO                            "TAG complemento com valor inválido"
#define EMSG_TAG_XML_IN_NE_BAIRRO                                 "TAG bairro não encontrada"
#define EMSG_TAG_XML_IN_VV_BAIRRO                                 "TAG bairro com valor vazio"
#define EMSG_TAG_XML_IN_VI_BAIRRO                                 "TAG bairro com valor inválido"
#define EMSG_TAG_XML_IN_NE_CEP                                    "TAG CEP não encontrada"
#define EMSG_TAG_XML_IN_VV_CEP                                    "TAG CEP com valor vazio"
#define EMSG_TAG_XML_IN_VI_CEP                                    "TAG CEP com valor inválido"
#define EMSG_TAG_XML_IN_NE_CIDADE                                 "TAG cidade não encontrada"
#define EMSG_TAG_XML_IN_VV_CIDADE                                 "TAG cidade com valor vazio"
#define EMSG_TAG_XML_IN_VI_CIDADE                                 "TAG cidade com valor inválido"
#define EMSG_TAG_XML_IN_NE_ESTADO                                 "TAG estado não encontrada"
#define EMSG_TAG_XML_IN_VV_ESTADO                                 "TAG estado com valor vazio"
#define EMSG_TAG_XML_IN_VI_ESTADO                                 "TAG estado com valor inválido"
#define EMSG_TAG_XML_IN_NE_PAIS                                   "TAG pais não encontrada"
#define EMSG_TAG_XML_IN_VV_PAIS                                   "TAG pais com valor vazio"
#define EMSG_TAG_XML_IN_VI_PAIS                                   "TAG pais com valor inválido"
#define EMSG_TAG_XML_IN_NE_LOGRADOURO                             "TAG logradouro não encontrada"
#define EMSG_TAG_XML_IN_VV_LOGRADOURO                             "TAG logradouro com valor vazio"
#define EMSG_TAG_XML_IN_VI_LOGRADOURO                             "TAG logradouro com valor inválido"
#define EMSG_TAG_XML_IN_NE_NUMERO                                 "TAG numero não encontrada"
#define EMSG_TAG_XML_IN_VV_NUMERO                                 "TAG numero com valor vazio"
#define EMSG_TAG_XML_IN_VI_NUMERO                                 "TAG numero com valor inválido"
#define EMSG_TAG_XML_IN_NE_NOTA_FISCAL                            "TAG notaFiscal não encontrada"
#define EMSG_TAG_XML_IN_VV_NOTA_FISCAL                            "TAG notaFiscal com valor vazio"
#define EMSG_TAG_XML_IN_VI_NOTA_FISCAL                            "TAG notaFiscal com valor inválido"
#define EMSG_TAG_XML_IN_NE_DATA                                   "TAG data não encontrada"
#define EMSG_TAG_XML_IN_VV_DATA                                   "TAG data com valor vazio"
#define EMSG_TAG_XML_IN_VI_DATA                                   "TAG data com valor inválido"
#define EMSG_TAG_XML_IN_NE_DATAPEDIDO                             "TAG dataPedido não encontrada"
#define EMSG_TAG_XML_IN_VV_DATAPEDIDO                             "TAG dataPedido com valor vazio"
#define EMSG_TAG_XML_IN_VI_DATAPEDIDO                             "TAG dataPedido com valor inválido"
#define EMSG_TAG_XML_IN_NE_CLASSE                                 "TAG classe não encontrada"
#define EMSG_TAG_XML_IN_VV_CLASSE                                 "TAG classe com valor vazio"
#define EMSG_TAG_XML_IN_VI_CLASSE                                 "TAG classe com valor inválido"
#define EMSG_TAG_XML_IN_NE_NOVO_PLANO                             "TAG novoPlano não encontrada"
#define EMSG_TAG_XML_IN_VV_NOVO_PLANO                             "TAG novoPlano com valor vazio"
#define EMSG_TAG_XML_IN_VI_NOVO_PLANO                             "TAG novoPlano com valor inválido"
#define EMSG_TAG_XML_IN_NE_DIA_TARIFA_REDUZIDA                    "TAG diaTarifaReduzida não encontrada"
#define EMSG_TAG_XML_IN_VV_DIA_TARIFA_REDUZIDA                    "TAG diaTarifaReduzida com valor vazio"
#define EMSG_TAG_XML_IN_VI_DIA_TARIFA_REDUZIDA                    "TAG diaTarifaReduzida com valor inválido"
#define EMSG_TAG_XML_IN_VM_DIA_TARIFA_REDUZIDA                    "TAG diaTarifaReduzida com mesmo valor do dia atual"
#define EMSG_TAG_XML_IN_NE_REF_FATURA                             "TAG refFatura não encontrada"
#define EMSG_TAG_XML_IN_VV_REF_FATURA                             "TAG refFatura com valor vazio"
#define EMSG_TAG_XML_IN_NE_DATA_PGTO                              "TAG dataPgto não encontrada"
#define EMSG_TAG_XML_IN_VV_DATA_PGTO                              "TAG dataPgto com valor vazio"
#define EMSG_TAG_XML_IN_NE_VALOR                                  "TAG valor não encontrada"
#define EMSG_TAG_XML_IN_VV_VALOR                                  "TAG valor com valor vazio"
#define EMSG_TAG_XML_IN_VV_PERIODO								  "TAG Periodo não encontrada"
#define EMSG_TAG_XML_IN_NE_PERIODO								  "TAG Periodo com valor vazio"
#define EMSG_TAG_XML_IN_INVOICEID								  "TAG inInvoiceId não encontrada"

#define EMSG_EXC_PLANO_INVALIDO                                   "Plano atual não permite efetuar a operação"
#define EMSG_EXC_MESMO_PLANO                                      "Não é possível alterar para o mesmo plano."
#define EMSG_EXC_BLOQUEIO_DDI_INVALIDO							  "Não é possível desativar o bloqueio LDI com o Bloqueio LDN Ativo."

// Codigo de servicos
#define	COD_CAIXA_POSTAL										  "VOICEMAIL"
#define COD_IDENTIFICADOR										  "CALLID"
#define COD_BLOQUEIO_DDI										  "UNBLOCKDDI"
#define COD_BLOQUEIO_DDD										  "UNBLOCKDDD"
#define COD_CHAMADA_ESPERA										  "CALLWAIT"
#define COD_TRANSF_CHAMADAS										  "CALLFWD"
#define COD_WAP													  "WAP"
#define COD_CONFERENCIA											  "CONFERENCIA"
#define COD_ANTIBINA											  "ANTIBINA"
#define COD_TORPEDOEMPRESAS										  "TORPEDOEMPRESAS"
#define COD_BLOQCOBRAR											  "BLOQCOBRAR"
 
// descricao de servicos Atlys
#define	DES_CAIXA_POSTAL										  "Caixa Postal"
#define DES_IDENTIFICADOR										  "Identificador de Chamadas"
#define DES_BLOQUEIO_DDI										  "Bloqueio LDI"
#define DES_BLOQUEIO_DDD										  "Bloqueio LDN/LDI"
#define DES_CHAMADA_ESPERA										  "Chamada em Espera"
#define DES_TRANSF_CHAMADAS										  "Desvio de Chamadas"
#define DES_WAP													  "Serviço WAP"
#define	DES_CONFERENCIA											  "Serviço de Conferência"
#define DES_ANTIBINA											  "Serviço anti-identificador de chamadas"
#define DES_TORPEDOEMPRESAS										  "Torpedo Empresas"
#define DES_BLOQCOBRAR											  "Serviço de bloqueio de chamadas à cobrar"


#define VOZ                                                       1
#define SMS                                                       2
#define DADOS                                                     3
#define DADOS_DOWNLOAD                                            4

#define DES_VOZ                                                   "Voz"
#define DES_SMS                                                   "SMS"
#define DES_DADOS                                                 "Dados"
#define DES_DADOS_DOWNLOAD                                        "Download"

// Mensagem para linha inválida
#define EMSG_INVALID_LINE                                         "Linha inválida"

// Mensagem para setFavorito, inclusão, linha inválida
#define EMSG_INVALID_LINE_ADD_FAVOR                               "Linha não encontrada. Favorito não adicionado"

// Mensagem para setFavorito, inclusão, linha não ativa
#define EMSG_DISABLED_LINE_ADD_FAVOR                              "Linha não está ativa. Favorito não adicionado"

// Mensagem para setFavorito, alteração, linha inválida
#define EMSG_INVALID_LINE_ALTER_FAVOR                             "Linha não encontrada. Favorito não alterado"

#define EMSG_ID_CONTA_NOT_FOUND                                   "IdContaSistemaOrigem não encontrado"

class PlugInBE
{
private:
	char mvc_ServiceName[TXPB_SERVICE_NAME_SIZE];

protected:
	
	TuxHelper tuxhelper;
	CTuxHelperClever* tuxhp;
	DOMNode* dnode;
	XMLGen* xml_g;
	char mvc_user[256];

	// Para armazenamento da mensagem/codigo de erro
	char mvc_LastStatusCode[TXPB_STATUS_CODE_SIZE + 1];
	char mvc_LastStatusText[TXPB_STATUS_TEXT_SIZE + 1];

	// Inlcusão do Subscriptoin ID da Linha
	char mvc_IdLinhaSistemaOrigem[TXPB_ID_LINHA_SIS_ORIG_SIZE + 1];

	char mvc_IdContaSistemaOrigem[TXPB_ID_LINHA_SIS_ORIG_SIZE + 1];

	// Gerenciador da Alocação de Memoria para os DOMNode dos PlugIns
	ManagerBackEndDOMNode* m_pManangerDOMNode;

	// Metodo para Criar de formar gerendcioavel um DOMNode* (DOMDOcument)
	DOMNode* createDOMNode(char*);

	// Metodo para invocar o back end "serviceName".
	DOMNode* callRemoteAPI(char*, XMLGen*);
	DOMNode* callRemoteAPI(char*, XMLGen*, char*);

	// Sistema Origem
	char mvc_sgSistemaOrigem[255 + 1];

	// Recupera LastStatusCode/LastStatusText
	char* getLastStatusCode(void);
	char* getLastStatusText(void);

	// Attribui valor para LastStatusCode/LastStatusText
	void setLastStatusCode(char*);
	void setLastStatusText(char*);

	// Recupera subscription id da linha
	char* getIdLinhaSistemaOrigem(void);

	// recupera a conta
	char* getIdContaSistemaOrigem(void);

	// Attribui valor para subscription id da linha
	void setIdLinhaSistemaOrigem(char*);

	// Atribui a conta
	void setIdContaSistemaOrigem(char*);

	// atribui o valor da sigla do sistema origem
	void setSgSistemaOrigem(char*);

	// retorna o valor da sigla do sistema origem
	char* getSgSistemaOrigem();

	// Registro de contatos
	int registrarContato();
	// Registro de contatos e criar tags de contato
	int registrarContato(XMLGen*xml_g,bool tags);	

	// Comunicacao com usuario
	int comunicarUsuario();

	char*		getNrProtocolo();
	char*		getInProtocolo();
	char*		getInRelacionamento();
	char*		getExibeProtocolo();

	void setNrProtocolo(char*value);
	void setInProtocolo(char*value);
	void setInRelacionamento(char*value);
	void setExibeProtocolo(char*value);

	// criar tags de protocolo
	void createTagProtocolo(XMLGen *xml_g,bool properties);

public:

	PlugInBE();

	// recupera o usuario
	char* getUser();

	// atribui o usuario
	void setUser(char*);

	// Destutor Virtual para Garantir a Chamada do Destrutores das Classes Derivadas	
	virtual ~PlugInBE();

	// Operacoes da Interface
	// Getters
	virtual char* getServiceName();

	//
	virtual void getDetalheLinha()          = 0;
	virtual void getDetalhesSaldo()         = 0;
	virtual void getHistoricoMovimentos()   = 0;
	virtual void getExtrato()               = 0;
	virtual void getPromocoes()             = 0;
	virtual void getServicos()              = 0;
	virtual void getFavoritos()             = 0;
	virtual void getHistoricoAtendimento()  = 0;
	virtual void getAjustes()               = 0;
	virtual void getEstimativa()            = 0;
	virtual void getEstimativaSaldo()       = 0;
	virtual void getFormaPagamento()        = 0;
	virtual void getFidelizacao()           = 0;
	virtual void getHistoricoFaturamentos() = 0;
	virtual void getImpedimentos()          = 0;
	virtual void getInfoContaCobranca()     = 0;
	virtual void getPagamentos()            = 0;
	virtual void getInfoFaturamento()       = 0;
	virtual void getURASegundaViaConta()    = 0;
	virtual void getURABoletoFax()          = 0;
	virtual void getURAConta()              = 0;
	virtual void getESN()                   = 0;
	virtual void getNotaFiscal()            = 0;
	virtual void getServicosURA()           = 0;


	virtual void getAvailableServices()     {}
	virtual void getDetalheAparelho()       {}
	virtual void getTarifaReduzida()        {}
	virtual void getPlano()                 {}

	virtual void getDetalhesConsumo()       {}
	virtual void getTipoFatura()            {}
	virtual void getDetalheFatura()			{}

	// Setters
	virtual void setServiceName(char *serviceName);

	//
	virtual void setServico()         = 0;
	virtual void setSuspendeCelular() = 0;
	virtual void setFavorito()        = 0;
	virtual void setInterceptacao()   = 0;
	virtual void setCliente()         = 0;

	virtual void setReligueCelular()  {}
	virtual void setTarifaReduzida()  {}
	virtual void setPlano()           {}
	virtual void setCaixaPostal()	  {}
	virtual void setTipoFatura()      {}

	CRemoteLog m_log;
	char  m_nrProtocolo[256];
	char  m_inProtocolo[2];
	char  m_inRelacionamento[2];
	char  m_exibeProtocolo[2];

};


#endif 
