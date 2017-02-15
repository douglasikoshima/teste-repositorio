#ifndef	PPGLOBALPORT
#define	PPGLOBALPORT


/*******************************************************************************
 * definicoes referentes ao	processo de	obtencao dos dados XML
 *******************************************************************************/
#define	OBRIGATORIO						1
#define	NOBRIGATORIO					0


/*******************************************************************************
 * definicoes comuns
 *******************************************************************************/
#define	LEN_EOS							1
#define	LEN_AUX							512
#define	LEN_AUX_ORA						255
#define	LEN_AUX_TOOLS					255
#define	LEN_RETURN_MESSAGE				255
#define	LEN_MESSAGE_EXCEPTION			255
#define	LEN_NUMBER_ORA					25
#define	LEN_DATE_ORA					14	  // YYYYMMDDHHMMSS
#define	LEN_HEADER						LEN_NUMBER_ORA
#define	LEN_TPOPERACAO					2
#define	QTD_ARRAY_PESSOA_DE_PARA		2
#define	LEN_INENDPRINCIPAL				1

/*******************************************************************************
 * definicoes referentes aos tipos de exceçoes.
 *******************************************************************************/
#define	ERRO_EXECUCAO					1
#define	ERRO_PARAMETRO_NULL				2
#define	ERRO_SEQUENCE					3
#define	ERRO_REGISTRO_NAO_ENCONTRADO	4
#define	ERRO_LEGADO_SET_INTERCEPTACAO	5
#define	ERRO_LEGADO_SET_CLIENTE			6


/*******************************************************************************
 * definicoes referentes aos tipos de sistemas
 *******************************************************************************/
#define	ID_ATLYS						"1"
#define	ID_PPS							"2"
#define	ID_ACS							"3"
#define	ID_DW							"4"
#define	ID_URA							"5"
#define	ID_EASYPHONE					"6"
#define	ID_FRONTOFFICE					"7"
#define	ID_NGIN							"272"


/*******************************************************************************
 * definicoes referentes ao	Tipo de	Relacionamento
 *******************************************************************************/
#define	ID_TIPO_RELACIONAMENTO_U		 "1"  // Usuario
#define	ID_TIPO_RELACIONAMENTO_C		 "2"  // Cliente
#define	ID_TIPO_RELACIONAMENTO_R		 "3"  // Consultor Relacionamento
#define	ID_TIPO_RELACIONAMENTO_V		 "4"  // Executivo de Vendas
#define	ID_TIPO_RELACIONAMENTO_GC		 "5"  // Gestor	da Conta
#define	ID_TIPO_RELACIONAMENTO_P		 "6"  // Prospect
#define	ID_TIPO_RELACIONAMENTO_NC		 "7"  // Não Cliente
#define	ID_TIPO_RELACIONAMENTO_GRUPOC	 "8"  // Conta Grupo


/*******************************************************************************
 * definicoes referentes aos retornos da execucao do servico
 *******************************************************************************/
#define	SUCESSO_EXECUCAO				"60I0000"
#define	E_APLICACAO_PREPAGO				"12E1251"
#define	E_PARAMETRO_PREPAGO				"12E1252"
#define	W_DELETE_PREPAGO				"12W1250"
#define	W_SEQUENCE_PREPAGO				"12W1251"


/*******************************************************************************
 * definicoes referentes aos tipos de estado civil
 *******************************************************************************/
#define	ID_TIPO_EC_SOLTEIRO				"1"
#define	ID_TIPO_EC_CASADO				"2"
#define	ID_TIPO_EC_VIUVO				"4"
#define	ID_TIPO_EC_DIVORCIADO			"5"

#define	SG_TIPO_EC_SOLTEIRO				"S"
#define	SG_TIPO_EC_CASADO				"C"
#define	SG_TIPO_EC_VIUVO				"V"
#define	SG_TIPO_EC_DIVORCIADO			"D"


/*******************************************************************************
 * definicoes referentes aos tipos de comunicacao
 *******************************************************************************/
#define	ID_TIPO_COMUNICACAO_TEL_RES		"1"
#define	ID_TIPO_COMUNICACAO_TEL_COM		"2"
#define	ID_TIPO_COMUNICACAO_FAX			"5"
#define	ID_TIPO_COMUNICACAO_EMAIL_PART	"6"
#define	ID_TIPO_COMUNICACAO_PASSAPORTE	"9"


/*******************************************************************************
 * definicoes referentes aos tipos de documentos (SIGLAS)
 *******************************************************************************/
#define	SG_CPF							"CPF"
#define	SG_FCPF							"FCPF"
#define	SG_KLCPF						"KLCPF"
#define	SG_RG							"RG"
#define	SG_PASS							"PAS"
#define	SG_RNE							"RNE"
#define	SG_CPR							"CPR"
#define	SG_CN							"CIP"
#define	SG_CNPJ							"CNPJ"
#define	SG_OCNPJ						"OCNPJ"
#define	SG_UCNPJ						"UCNPJ"
#define	SG_IE							"IE"
#define	SG_EM							"IM"
#define	SG_CNAE							"CNAE"


/*******************************************************************************
 * definicoes referentes aos tipos de sistemas
 *******************************************************************************/
#define	SG_FRONTOFFICCE					"FO"


/*******************************************************************************
 * definicoes referentes aos tipos de sexo
 *******************************************************************************/
#define	ID_SEXO_MASCULINO				"1"
#define	ID_SEXO_FEMININO				"2"
#define	ID_SEXO_NAO_CLASSIFICADO		"0"

#define	SG_SEXO_MASCULINO				"M"
#define	SG_SEXO_FEMININO				"F"
#define	SG_SEXO_NAO_CLASSIFICADO		"NC"


/*******************************************************************************
 * definicoes referentes ao	processo de	inclusao de	dados para o NGIN
 *******************************************************************************/
#define	LEN_NUMBER_LEGADO				15
#define	LEN_NOME_PESSOA_LEGADO			50
#define	LEN_IE_LEGADO					15
#define	LEN_CPF_LEGADO					16
#define	LEN_RG_LEGADO					15
#define	LEN_ORGAO_EXPEDITOR_LEGADO		20
#define	LEN_ESTADO_EXPEDICAO_LEGADO		 2
#define	LEN_PASSAPORTE_LEGADO			 8
#define	LEN_CNPJ_LEGADO					15
#define	LEN_CNAE_LEGADO					 3
#define	LEN_TELEFONE_LEGADO				20
#define	LEN_NOME_CONTATO_LEGADO			50
#define	LEN_LOGRADOURO_LEGADO			10
#define	LEN_ARRUAMENTO_LEGADO			60
#define	LEN_COMPLEMENTO_LEGADO			32
#define	LEN_BAIRRO_LEGADO				32
#define	LEN_CEP_LEGADO					 8
#define	LEN_CIDADE_LEGADO				60
#define	LEN_FAX_LEGADO					20
#define	LEN_EMAIL_LEGADO				80
#define	LEN_ESTADO_LEGADO				 2
#define	LEN_NUMERO_LEGADO				10
#define	LEN_DATE_LEGADO					10


/*******************************************************************************
 * definicoes referentes ao	endereco de	cobranca
 *******************************************************************************/
#define	TIPO_ENDERECO_COBRANCA			"CO"


/*******************************************************************************
 * definicoes referentes a apoio.sexo
 *******************************************************************************/
#define	LEN_IDSEXO						LEN_NUMBER_ORA
#define	LEN_SGSEXO						255
#define	LEN_DSSEXO						255


/*******************************************************************************
 * definicoes referentes a apoio.estadocivil
 *******************************************************************************/
#define	LEN_IDESTADOCIVIL				LEN_NUMBER_ORA
#define	LEN_SGESTADOCIVIL				255
#define	LEN_DSESTADOCIVIL				255


/*******************************************************************************
 * definicoes referentes a apoio.tipodocumento
 *******************************************************************************/
#define	LEN_IDTIPODOCUMENTO				LEN_NUMBER_ORA
#define	LEN_DSTIPODOCUMENTO				255
#define	LEN_SGCLASSIFICACAO				255


/*******************************************************************************
 * definicoes referentes a apoio.tipopessoa
 *******************************************************************************/
#define	LEN_IDTIPOPESSOA				LEN_NUMBER_ORA
#define	LEN_SGTIPOPESSOA				255
#define	LEN_DSTIPOPESSOA				255


/*******************************************************************************
 * definicoes referentes a apoio.tipolinha
 *******************************************************************************/
#define	LEN_IDTIPOLINHA				   LEN_NUMBER_ORA
#define	LEN_SGTIPOLINHA				   255
#define	LEN_DSTIPOLINHA				   255


/*******************************************************************************
 * definicoes referentes a apoio.tipocarteira
 *******************************************************************************/
#define	LEN_IDTIPOCARTEIRA			   LEN_NUMBER_ORA
#define	LEN_SGTIPOCARTEIRA			   255
#define	LEN_DSTIPOCARTEIRA			   255


/******************************************************************************
 * definicoes referentes a apoio.uf
 *******************************************************************************/
#define	LEN_IDUF						LEN_NUMBER_ORA
#define	LEN_DSUF						255


/*******************************************************************************
 * definicoes referentes a apoio.tipoendereco
 *******************************************************************************/
#define	LEN_IDTIPOENDERECO				LEN_NUMBER_ORA
#define	LEN_SGTIPOENDERECO				255
#define	LEN_DSTIPOENDERECO				255


/*******************************************************************************
 * definicoes referentes a apoio.tipocomunicacao
 *******************************************************************************/
#define	LEN_IDTIPOCOMUNICACAO			LEN_NUMBER_ORA
#define	LEN_SGTIPOCOMUNICACAO			255
#define	LEN_DSTIPOCOMUNICACAO			255
#define	LEN_IDFORMARETORNO				LEN_NUMBER_ORA
#define	LEN_SGCLASSIFICACAO				255


/*******************************************************************************
 * definicoes referentes a apoio.cfop
 *******************************************************************************/
#define	LEN_IDCFOP						LEN_NUMBER_ORA
#define	LEN_DSCFOP						255
#define	LEN_SGCFOP						255


/*******************************************************************************
 * definicoes referentes a customer.valorpossivel
 *******************************************************************************/
#define	LEN_SGTIPOCOMUNICACAO			255


/*******************************************************************************
 * definicoes referentes a customer.valorpossivel
 *******************************************************************************/
#define	LEN_DSVALORPOSSIVEL				255


/*******************************************************************************
 * definicoes referentes a customer.atributo
 *******************************************************************************/
#define	LEN_IDATRIBUTO					LEN_NUMBER_ORA


/*******************************************************************************
 * definicoes referentes a tibco_ow.p_registracontato
 *******************************************************************************/
#define	LEN_XML_REGCONTATO				4000
#define	LEN_IDREGISTRACONTATO			LEN_NUMBER_ORA


/*******************************************************************************
 * definicoes referentes a apoio.arearegistrobloqueado (soh	definicoes)
 *******************************************************************************/
#define	LEN_IDAREAREGISTROBLOQUEADO		LEN_NUMBER_ORA
#define	LEN_IDAREAREGISTRO				LEN_NUMBER_ORA
#define	LEN_INBLOQUEADO					LEN_NUMBER_ORA
#define	LEN_CDAREAREGISTROBLOQ			LEN_NUMBER_ORA


/*******************************************************************************
 * definicoes referentes a customer.linhaconta
 *******************************************************************************/
#define	LEN_IDLINHACONTA				LEN_NUMBER_ORA
#define	LEN_IDLINHATELEFONICA			LEN_NUMBER_ORA
#define	LEN_IDCONTA						LEN_NUMBER_ORA
#define	LEN_IDTIPORELACIONAMENTO		LEN_NUMBER_ORA
#define	LEN_DTLINHACONTA				LEN_DATE_ORA
#define	LEN_INLINHAMASTER				LEN_NUMBER_ORA
#define	LEN_TSSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_SQSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_DTEXPIRACAO					LEN_DATE_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a apoio.sistemaorigem
 *******************************************************************************/
#define	LEN_IDSISTEMAORIGEM				LEN_NUMBER_ORA
#define	LEN_SGSISTEMAORIGEM				255
#define	LEN_NMSISTEMAORIGEM				255
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA
#define	LEN_INDISPONIVEL				LEN_NUMBER_ORA


/*******************************************************************************
 * definicoes referentes a customer.contaendereco
 *******************************************************************************/
#define	LEN_IDCONTAENDERECO				LEN_NUMBER_ORA
#define	LEN_IDPESSOAENDERECO			LEN_NUMBER_ORA
#define	LEN_IDCONTA						LEN_NUMBER_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA
#define	LEN_IDTIPOENDERECOCOBRANCA		LEN_NUMBER_ORA


/*******************************************************************************
 * definicoes referentes a infra.compservicodesativado
 *******************************************************************************/
#define	LEN_IDCOMPSERVICODESATIVADO		LEN_NUMBER_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoalinhapre
 *******************************************************************************/
#define	LEN_IDPESSOALINHA				LEN_NUMBER_ORA
#define	LEN_TSSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_SQSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_INMUDANCATITULARIDADE		1
#define	LEN_INSINCRONISMO				1
#define	LEN_INUSUARIONAOINFORMADO		LEN_NUMBER_ORA


/*******************************************************************************
 * definicoes referentes a apoio.tipodocumento
 *******************************************************************************/
#define	LEN_IDTIPODOCUMENTO				LEN_NUMBER_ORA
#define	LEN_SGTIPODOCUMENTO				255
#define	LEN_DSTIPODOCUMENTO				255
#define	LEN_IDTIPOPESSOA				LEN_NUMBER_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA
#define	LEN_NRPRIORIDADE				LEN_NUMBER_ORA
#define	LEN_SGCLASSIFICACAO				255


/*******************************************************************************
 * definicoes referentes a apoio.parametro
 *******************************************************************************/
#define	LEN_IDPARAMETRO				   LEN_NUMBER_ORA
#define	LEN_CDPARAMETRO				   255
#define	LEN_DSPARAMETRO				   255
#define	LEN_DSVALORPARAMETRO		   255
#define	LEN_IDUSUARIOALTERACAO		   LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO		   LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a apoio.uf
 *******************************************************************************/
#define	LEN_IDUF						LEN_NUMBER_ORA
#define	LEN_SGUF						255
#define	LEN_NMUF						255
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA
#define	LEN_NRFUSOHORARIO				LEN_NUMBER_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoalinha
 *******************************************************************************/
#define	LEN_IDPESSOALINHA				LEN_NUMBER_ORA
#define	LEN_DTPESSOALINHA				LEN_DATE_ORA
#define	LEN_IDTIPORELACIONAMENTO		LEN_NUMBER_ORA
#define	LEN_IDPESSOADEPARA				LEN_NUMBER_ORA
#define	LEN_IDLINHATELEFONICA			LEN_NUMBER_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoasegmentacao
 *******************************************************************************/
#define	LEN_IDPESSOADEPARA				LEN_NUMBER_ORA
#define	LEN_IDPESSOASEGMENTACAO			LEN_NUMBER_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoaportabilidadehist
 *******************************************************************************/
#define LEN_IDPESSOAPORTABILIDADEHISTPORT   15
#define LEN_IDTIPOLINHAPORT                 LEN_NUMBER_ORA
#define LEN_CDAREAREGISTROPORT              3
#define LEN_NRLINHAPORT                     16
#define LEN_IDTIPOPESSOAPORT                LEN_NUMBER_ORA
#define LEN_NMPESSOAPORT                    255
#define LEN_IDTIPODOCUMENTOPORT             LEN_NUMBER_ORA
#define LEN_NRDOCUMENTOPORT                 20
#define LEN_IDTIPOENDERECOPORT              LEN_NUMBER_ORA
#define LEN_NMTIPOLOGRADOUROPORT            20
#define LEN_NMLOGRADOUROPORT                150
#define LEN_NRENDERECOPORT                  150
#define LEN_NMMUNICIPIOPORT                 80
#define LEN_NMBAIRROPORT                    80
#define LEN_NRCEPPORT                       10
#define LEN_DSACAOPORTABILIDADEPORT         40
#define LEN_IDUSUARIOALTERACAOPORT          LEN_NUMBER_ORA
#define LEN_SGTIPOPORTABILIDADEPORT         15


/*******************************************************************************
 * definicoes referentes a infra.filasetclientinfo
 *******************************************************************************/
#define	LEN_IDFILASETCLIENTINFO			LEN_NUMBER_ORA
#define	LEN_DSERRO						255
#define	LEN_CDERRO						255
#define	LEN_DSERRO						255
#define	LEN_XML							4000


/*******************************************************************************
 * definicoes referentes a customer.pessoadepara
 *******************************************************************************/
#define	LEN_IDPESSOADEPARA				LEN_NUMBER_ORA
#define	LEN_IDPESSOA					LEN_NUMBER_ORA
#define	LEN_IDPESSOAORIGEM				LEN_NUMBER_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoajuridica
 *******************************************************************************/
#define	LEN_IDPESSOA					LEN_NUMBER_ORA
#define	LEN_NMPESSOAFILIAL				255
#define	LEN_NMFANTASIA					255
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA
#define	LEN_DTFUNDACAO					LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoadocumento
 *******************************************************************************/
#define	LEN_IDPESSOADOCUMENTO			LEN_NUMBER_ORA
#define	LEN_IDPESSOA					LEN_NUMBER_ORA
#define	LEN_IDDOCUMENTO					LEN_NUMBER_ORA
#define	LEN_TSSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_SQSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_IDSISTEMAORIGEM				LEN_NUMBER_ORA
#define	LEN_IDDOCUMENTOSISTEMAORIGEM	255
#define	LEN_DTEXPIRACAO					LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoalinhahistorico
 *******************************************************************************/
#define	LEN_IDPESSOALINHAHISTORICO		LEN_NUMBER_ORA
#define	LEN_DTRELACIONAMENTO			LEN_DATE_ORA
#define	LEN_CDAREAREGISTRO				LEN_NUMBER_ORA
#define	LEN_NRLINHA						LEN_NUMBER_ORA
#define	LEN_IDTIPORELACIONAMENTO		LEN_NUMBER_ORA
#define	LEN_SGTIPORELACIONAMENTO		255
#define	LEN_IDPESSOADEPARA				LEN_NUMBER_ORA
#define	LEN_IDLINHATELEFONICA			LEN_NUMBER_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a contatoadm.contatofuncionalidade
 *******************************************************************************/
#define	LEN_CDFUNCIONALIDADE			50
#define	LEN_IDCONTATO					LEN_NUMBER_ORA
#define	LEN_SGTIPOPORTABILIDADE			15
#define	LEN_IDACAOPORTABILIDADE			3
#define	LEN_DSACAOPORTABILIDADE			255
#define	LEN_DSPATHCONTATO				2000
#define	LEN_INENVIASMS					1
#define	LEN_IDCANAL						15
#define	LEN_IDPROCEDENCIA				15
#define	LEN_IDGRUPOABERTURA				15
#define	LEN_IDGRUPOTRATAMENTO			15


/*******************************************************************************
 * definicoes referentes a linha.linhabase
 *******************************************************************************/
#define	LEN_IDLINHABASE					LEN_NUMBER_ORA
#define	LEN_IDAREAREGISTRO				LEN_NUMBER_ORA
#define	LEN_NRLINHA						LEN_NUMBER_ORA
#define	LEN_NRMIN						255
#define	LEN_NRDIGITOLINHA				LEN_NUMBER_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA
#define	LEN_IDESTADOLINHA				LEN_NUMBER_ORA
#define	LEN_SQSINCRONISMOESTADO			LEN_NUMBER_ORA
#define	LEN_TSSINCRONISMOESTADO			LEN_NUMBER_ORA
#define	LEN_DTESTADOLINHA				LEN_DATE_ORA
#define	LEN_DSMOTIVOESTADO				255
#define	LEN_IDNUMEROSISTEMAORIGEM		255
#define	LEN_IDSISTEMAORIGEM				LEN_NUMBER_ORA


/*******************************************************************************
 * definicoes referentes a apoio.acaoportabilidade
 *******************************************************************************/
#define	LEN_IDACAOPORTABILIDADE			3
#define	LEN_DSACAOPORTABILIDADE			255
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a linha.linhatelefonica
 *******************************************************************************/
#define	LEN_IDLINHATELEFONICA			LEN_NUMBER_ORA
#define	LEN_IDSISTEMAORIGEM				LEN_NUMBER_ORA
#define	LEN_IDLINHASISTEMAORIGEM		255
#define	LEN_IDTIPOLINHA					LEN_NUMBER_ORA
#define	LEN_INDIVULGACAONRLINHA			LEN_NUMBER_ORA
#define	LEN_DTAUTORIZACAODIVULGACAO		LEN_DATE_ORA
#define	LEN_DTHABILITACAO				LEN_DATE_ORA
#define	LEN_TSSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_SQSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_CDCHURNPROBABILIDADE		255
#define	LEN_VLRCHURNPROBABILIDADE		LEN_NUMBER_ORA
#define	LEN_DTEXPIRACAO					LEN_DATE_ORA
#define	LEN_IDLINHABASE					LEN_NUMBER_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA
#define	LEN_SQSINCRONISMOESTADO			LEN_NUMBER_ORA
#define	LEN_TSSINCRONISMOESTADO			LEN_NUMBER_ORA
#define	LEN_INUSUARIOINFORMADO			LEN_NUMBER_ORA
#define	LEN_DTCADASTROPUP				LEN_DATE_ORA
#define	LEN_IDUSUARIOINCLUSAOPUP		LEN_NUMBER_ORA
#define	LEN_CDSENHAPREATIVA				250


/*******************************************************************************
 * definicoes referentes a linha.permissaolinhapup
 *******************************************************************************/
#define	LEN_IDLINHATELEFONICA			LEN_NUMBER_ORA
#define	LEN_SGPERMISSAOPUP				10
#define	LEN_INATIVO						1
#define	LEN_DTEXPIRACAO_CUSTOM			LEN_NUMBER_ORA // contem os	dias a serem adicionados no	sysdate
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
//#define LEN_IDCANAL      			    LEN_NUMBER_ORA
#define LEN_INPROCON 					LEN_NUMBER_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoacomunicacao
 *******************************************************************************/
#define	LEN_IDPESSOACOMUNICACAO			LEN_NUMBER_ORA
#define	LEN_IDPESSOA					LEN_NUMBER_ORA
#define	LEN_IDTIPOCOMUNICACAO			LEN_NUMBER_ORA
#define	LEN_NRSEQUENCIA					LEN_NUMBER_ORA
#define	LEN_DSCONTATO					255
#define	LEN_TSSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_SQSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_DTCADASTRO					LEN_DATE_ORA
#define	LEN_IDCOMUNICACAOSISTEMAORIGEM	255
#define	LEN_IDSISTEMAORIGEM				LEN_NUMBER_ORA
#define	LEN_DTEXPIRACAO					LEN_DATE_ORA
#define	LEN_INCOMUNICACAOPREFERENCIAL	LEN_NUMBER_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA
#define	LEN_NMCONTATO					255


/*******************************************************************************
 * definicoes referentes a customer.pessoafisica
 *******************************************************************************/
#define	LEN_IDPESSOA					LEN_NUMBER_ORA
#define	LEN_DTNASCIMENTO				LEN_DATE_ORA
#define	LEN_NMMAE						255
#define	LEN_NMPAI						255
#define	LEN_IDTRATAMENTO				LEN_NUMBER_ORA
#define	LEN_IDESTADOCIVIL				LEN_NUMBER_ORA
#define	LEN_IDPAIS						LEN_NUMBER_ORA
#define	LEN_IDSEXO						LEN_NUMBER_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoavalorpossivel
 *******************************************************************************/
#define	LEN_IDPESSOAVALORPOSSIVEL		LEN_NUMBER_ORA
#define	LEN_IDVALORPOSSIVEL				LEN_NUMBER_ORA
#define	LEN_IDPESSOA					LEN_NUMBER_ORA
#define	LEN_DTEXCLUSAO					LEN_DATE_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoa
 *******************************************************************************/
#define	LEN_IDPESSOA					LEN_NUMBER_ORA
#define	LEN_IDSISTEMAORIGEM				LEN_NUMBER_ORA
#define	LEN_IDPESSOASISTEMAORIGEM		255
#define	LEN_NMPESSOA					255
#define	LEN_NMNOME						255
#define	LEN_NMNOMEMEIO					255
#define	LEN_NMSOBRENOME					255
#define	LEN_DTCHURN						LEN_DATE_ORA
#define	LEN_INFALECIMENTOINFORMADO		LEN_NUMBER_ORA
#define	LEN_DTFALECIMENTO				LEN_DATE_ORA
#define	LEN_IDTIPOPESSOA				LEN_NUMBER_ORA
#define	LEN_TSSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_SQSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_IDTIPOCARTEIRA				LEN_NUMBER_ORA
#define	LEN_IDUF						LEN_NUMBER_ORA
#define	LEN_VLRCHURNPROBABILIDADE		LEN_NUMBER_ORA
#define	LEN_DTTIPOCARTEIRA				LEN_DATE_ORA
#define	LEN_IDPROBINADIMPLENCIA			LEN_NUMBER_ORA
#define	LEN_IDCHURNPROBABILIDADE		LEN_NUMBER_ORA
#define	LEN_DTCADASTRO					LEN_DATE_ORA
#define	LEN_DSCARGOCONTATO				255
#define	LEN_DSDEPTOCONTATO				255
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoasegmentacaohistorico
 *******************************************************************************/
#define	LEN_IDPESSOASEGMENTACAO			LEN_NUMBER_ORA
#define	LEN_IDPESSOADEPARA				LEN_NUMBER_ORA
#define	LEN_IDSEGMENTACAO				LEN_NUMBER_ORA
#define	LEN_VLRENTABILIDADE				LEN_NUMBER_ORA
#define	LEN_DTSEGMENTACAO				LEN_DATE_ORA
#define	LEN_DTRENTABILIDADE				LEN_DATE_ORA
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoaendereco
 *******************************************************************************/
#define	LEN_IDPESSOAENDERECO			LEN_NUMBER_ORA
#define	LEN_IDPESSOA					LEN_NUMBER_ORA
#define	LEN_NRSEQUENCIA					LEN_NUMBER_ORA
#define	LEN_IDPAIS						LEN_NUMBER_ORA
#define	LEN_NMMUNICIPIO					255
#define	LEN_NMLOCALIDADE				255
#define	LEN_NMBAIRRO					255
#define	LEN_NMTIPOLOGRADOURO			255
#define	LEN_NMTITULOLOGRADOURO			255
#define	LEN_NMLOGRADOURO				255
#define	LEN_NRENDERECO					255
#define	LEN_DSENDERECOCOMPLEMENTO		255
#define	LEN_INENDERECOPREFERENCIAL		LEN_NUMBER_ORA
#define	LEN_NRCEP						255
#define	LEN_DTCADASTRO					LEN_DATE_ORA
#define	LEN_DTCADASTROOUT				10
#define	LEN_IDTIPOENDERECO				LEN_NUMBER_ORA
#define	LEN_TSSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_DTEXPIRACAO					LEN_DATE_ORA
#define	LEN_DTEXPIRACAOOUT				10
#define	LEN_SQSINCRONISMO				LEN_NUMBER_ORA
#define	LEN_IDUF						LEN_NUMBER_ORA
#define	LEN_DSAOSCUIDADOS				255
#define	LEN_CDCAIXAPOSTAL				255
#define	LEN_IDSISTEMAORIGEM				LEN_NUMBER_ORA
#define	LEN_IDENDERECOSISTEMAORIGEM		255
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA
#define	LEN_DTULTIMAALTERACAOOUT		10
#define	LEN_INENDERECOSUJO				1


/*******************************************************************************
 * definicoes referentes a customer.pessoaconta
 *******************************************************************************/
#define	LEN_IDPESSOACONTA				LEN_NUMBER_ORA
#define	LEN_IDTIPORELACIONAMENTO		LEN_NUMBER_ORA
#define	LEN_IDPESSOADEPARA				LEN_NUMBER_ORA
#define	LEN_IDCONTA						LEN_NUMBER_ORA
#define	LEN_DTPESSOACONTA				LEN_DATE_ORA
#define	LEN_DTEXPIRACAO					LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a customer.pessoaportabilidade
 *******************************************************************************/
#define	LEN_INSINCRONIZADO				LEN_NUMBER_ORA
#define	LEN_IDTIPOPESSOA				LEN_NUMBER_ORA
#define	LEN_IDPESSOADEPARA				LEN_NUMBER_ORA
#define	LEN_IDTIPOLINHA					LEN_NUMBER_ORA
#define	LEN_CDAREAREGISTRO				LEN_NUMBER_ORA
#define	LEN_NRLINHA						LEN_NUMBER_ORA
#define	LEN_IDACAOPORTABILIDADE			3
#define	LEN_IDUSUARIOALTERACAO			LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO			LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a apoio.tipoenderecocobranca
 *******************************************************************************/
#define	LEN_IDTIPOENDERECOCOBRANCA	   LEN_NUMBER_ORA
#define	LEN_SGTIPOENDERECOCOBRANCA	   255
#define	LEN_SGCLASSIFICACAO			   255
#define	LEN_DSTIPOENDERECOCOBRANCA	   255
#define	LEN_IDUSUARIOALTERACAO		   LEN_NUMBER_ORA
#define	LEN_DTULTIMAALTERACAO		   LEN_DATE_ORA


/*******************************************************************************
 * definicoes referentes a customer.documento
 *******************************************************************************/
#define	LEN_IDDOCUMENTO					LEN_NUMBER_ORA
#define	LEN_CDCPFCNPJBASE				255
#define	LEN_CDCNPJFILIAL				255
#define	LEN_CDCPFCNPJCONTROLE			255
#define	LEN_NRDOCUMENTO					255
#define	LEN_SGORGAOEXPEDIDOR			255
#define	LEN_DTEMISSAO					LEN_DATE_ORA
#define	LEN_IDPAIS						LEN_NUMBER_ORA
#define	LEN_IDUF					 LEN_NUMBER_ORA
#define	LEN_IDTIPODOCUMENTO				LEN_NUMBER_ORA


/*******************************************************************************
 * definicoes referentes ao	processo de	desmembramento de nomes
 *******************************************************************************/
#define	LEN_NOME						255
#define	LEN_NOME_PRIMEIRO				LEN_NOME
#define	LEN_NOME_MEIO					LEN_NOME
#define	LEN_NOME_FIM					LEN_NOME


/*******************************************************************************
 * Estrutura referente ao processamento	do SETCLIENTPORT
 *******************************************************************************/
typedef	struct {
	char szUser[LEN_HEADER + LEN_EOS];
	char szSubSystem[LEN_HEADER	+ LEN_EOS];
	char szMessageException[LEN_MESSAGE_EXCEPTION +	LEN_EOS];
	char szTpOperacao[LEN_TPOPERACAO + LEN_EOS];
	char szSgTipoPessoa[LEN_SGTIPOPESSOA + LEN_EOS];
	char szNrLinha[LEN_NRLINHA + LEN_EOS];
	char szSgTipoLinha[LEN_SGTIPOLINHA + LEN_EOS];
	char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
} TDataProc;


/*******************************************************************************
 * Estrutura referente a customer.pessoavalorpossivel
 *******************************************************************************/
typedef	struct {
	char szIdPessoaValorPossivel[LEN_IDPESSOAVALORPOSSIVEL + LEN_EOS];
	char szIdValorPossivel[LEN_IDVALORPOSSIVEL + LEN_EOS];
	char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
	char szDtExclusao[LEN_DTEXCLUSAO + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaValorPossivel;


/*******************************************************************************
 * Estrutura referente a customer.pessoadepara
 *******************************************************************************/
typedef	struct {
	char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
	char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
	char szIdPessoaOrigem[LEN_IDPESSOAORIGEM + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaDePara;


/*******************************************************************************
 * Estrutura referente a customer.pessoa
 *******************************************************************************/
typedef	struct {
	char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
	char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
	char szIdPessoaSistemaOrigem[LEN_IDPESSOASISTEMAORIGEM + LEN_EOS];
	char szNmPessoa[LEN_NMPESSOA + LEN_EOS];
	char szNmNome[LEN_NMNOME + LEN_EOS];
	char szNmNomeMeio[LEN_NMNOMEMEIO + LEN_EOS];
	char szNmSobreNome[LEN_NMSOBRENOME + LEN_EOS];
	char szDtChurn[LEN_DTCHURN + LEN_EOS];
	char szDtCadastro[LEN_DTCADASTRO + LEN_EOS];
	char szInFalecimentoInformado[LEN_INFALECIMENTOINFORMADO + LEN_EOS];
	char szDtFalecimento[LEN_DTFALECIMENTO + LEN_EOS];
	char szIdTipoPessoa[LEN_IDTIPOPESSOA + LEN_EOS];
	char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
	char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
	char szIdTipoCarteira[LEN_IDTIPOCARTEIRA + LEN_EOS];
	char szIdUF[LEN_IDUF + LEN_EOS];
	char szVlrChurnProbabilidade[LEN_VLRCHURNPROBABILIDADE + LEN_EOS];
	char szDtTipoCarteira[LEN_DTTIPOCARTEIRA + LEN_EOS];
	char szIdProbInadimplencia[LEN_IDPROBINADIMPLENCIA + LEN_EOS];
	char szIdChurnProbabilidade[LEN_IDCHURNPROBABILIDADE + LEN_EOS];
	char szDsCargoContato[LEN_DSCARGOCONTATO + LEN_EOS];
	char szDsDeptoContato[LEN_DSDEPTOCONTATO + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoa;


/*******************************************************************************
 * Estrutura referente a customer.pessoasegmentacaohistorico
 *******************************************************************************/
typedef	struct {
	char szIdPessoaSegmentacao[LEN_IDPESSOASEGMENTACAO + LEN_EOS];
	char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
	char szIdSegmentacao[LEN_IDSEGMENTACAO + LEN_EOS];
	char szVlRentabilidade[LEN_VLRENTABILIDADE + LEN_EOS];
	char szDtSegmentacao[LEN_DTSEGMENTACAO + LEN_EOS];
	char szDtRentabilidade[LEN_DTRENTABILIDADE + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaSegmentacaoHistorico;


/*******************************************************************************
 * Estrutura referente a apoio.acaoportabilidade
 *******************************************************************************/
typedef	struct {
	char szIdAcaoPortabilidade[LEN_IDACAOPORTABILIDADE + LEN_EOS];
	char szDsAcaoPortabilidade[LEN_DSACAOPORTABILIDADE + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TAcaoPortabilidade;


/*******************************************************************************
 * Estrutura referente a customer.pessoacomunicacao
 *******************************************************************************/
typedef	struct {
	char szIdPessoaComunicacao[LEN_IDPESSOACOMUNICACAO + LEN_EOS];
	char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
	char szIdTipoComunicacao[LEN_IDTIPOCOMUNICACAO + LEN_EOS];
	char szNrSequencia[LEN_NRSEQUENCIA + LEN_EOS];
	char szDsContato[LEN_DSCONTATO + LEN_EOS];
	char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
	char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
	char szDtCadastro[LEN_DTCADASTRO + LEN_EOS];
	char szIdComunicacaoSistemaOrigem[LEN_IDCOMUNICACAOSISTEMAORIGEM + LEN_EOS];
	char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
	char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
	char szInComunicacaoPreferencial[LEN_INCOMUNICACAOPREFERENCIAL + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
	char szNmContato[LEN_NMCONTATO + LEN_EOS];
} TPessoaComunicacao;


/*******************************************************************************
 * Estrutura referente a tibco_ow.p_registracontato
 *******************************************************************************/
typedef	struct{
	char szIdRegistraContato[LEN_IDREGISTRACONTATO + LEN_EOS];
	char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
	char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
	char szCdAreaRegistro[LEN_CDAREAREGISTRO + LEN_EOS];
	char szNrLinha[LEN_NRLINHA + LEN_EOS];
	char szIdPessoaLinhaHistorico[LEN_IDPESSOALINHAHISTORICO + LEN_EOS];
	char szXML[LEN_XML_REGCONTATO +	LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
} TRegistraContato;


/*******************************************************************************
 * Estrutura referente a customer.pessoadocumento
 *******************************************************************************/
typedef	struct {
	char szIdPessoaDocumento[LEN_IDPESSOADOCUMENTO + LEN_EOS];
	char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
	char szIdDocumento[LEN_IDDOCUMENTO + LEN_EOS];
	char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
	char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
	char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
	char szIdDocumentoSistemaOrigem[LEN_IDDOCUMENTOSISTEMAORIGEM + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
} TPessoaDocumento;


/*******************************************************************************
 * Estrutura referente a linha.pessoalinha
 *******************************************************************************/
typedef	struct{
	char szIdPessoaLinha[LEN_IDPESSOALINHA + LEN_EOS];
	char szDtPessoaLinha[LEN_DTPESSOALINHA + LEN_EOS];
	char szIdTipoRelacionamento[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
	char szSgTipoRelacionamento[LEN_SGTIPORELACIONAMENTO + LEN_EOS];
	char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
	char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaLinha;


/*******************************************************************************
 * Estrutura referente a apoio.uf
 *******************************************************************************/
typedef	struct {
	char szIdUF[LEN_IDUF + LEN_EOS];
	char szSgUF[LEN_SGUF + LEN_EOS];
	char szNmUF[LEN_NMUF + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
	char szNrFusoHorario[LEN_NRFUSOHORARIO + LEN_EOS];
} TUF;


/*******************************************************************************
 * Estrutura referente a contatoadm.contatofuncionalidade
 *******************************************************************************/
typedef	struct {
	char szIdContato[LEN_IDCONTATO + LEN_EOS];
	char szCdFuncionalidade[LEN_CDFUNCIONALIDADE + LEN_EOS];
} TContatoFuncionalidade;


/*******************************************************************************
 * Estrutura referente a linha.linhabase
 *******************************************************************************/
typedef	struct {
	char szIdLinhaBase[LEN_IDLINHABASE + LEN_EOS];
	char szIdAreaRegistro[LEN_IDAREAREGISTRO + LEN_EOS];
	char szNrLinha[LEN_NRLINHA + LEN_EOS];
	char szNrMin[LEN_NRMIN + LEN_EOS];
	char szNrDigitoLinha[LEN_NRDIGITOLINHA + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
	char szIdEstadoLinha[LEN_IDESTADOLINHA + LEN_EOS];
	char szSqSincronismoEstado[LEN_SQSINCRONISMOESTADO + LEN_EOS];
	char szTsSincronismoEstado[LEN_TSSINCRONISMOESTADO + LEN_EOS];
	char szDtEstadoLinha[LEN_DTESTADOLINHA + LEN_EOS];
	char szDsMotivoEstado[LEN_DSMOTIVOESTADO + LEN_EOS];
	char szIdNumeroSistemaOrigem[LEN_IDNUMEROSISTEMAORIGEM + LEN_EOS];
	char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
} TLinhaBase;


/*******************************************************************************
 * Estrutura referente a linha.linhatelefonica
 *******************************************************************************/
typedef	struct {
	char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
	char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
	char szIdLinhasistemaOrigem[LEN_IDLINHASISTEMAORIGEM + LEN_EOS];
	char szIdTipoLinha[LEN_IDTIPOLINHA + LEN_EOS];
	char szInDivulgacaoNrLinha[LEN_INDIVULGACAONRLINHA + LEN_EOS];
	char szDtAutorizacaoDivulgacao[LEN_DTAUTORIZACAODIVULGACAO + LEN_EOS];
	char szDtHabilitacao[LEN_DTHABILITACAO + LEN_EOS];
	char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
	char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
	char szCdChurnProbabilidade[LEN_CDCHURNPROBABILIDADE + LEN_EOS];
	char szVlrChurnProbabilidade[LEN_VLRCHURNPROBABILIDADE + LEN_EOS];
	char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
	char szIdLinhaBase[LEN_IDLINHABASE + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
	char szSqSincronismoEstado[LEN_SQSINCRONISMOESTADO + LEN_EOS];
	char szTsSincronismoEstado[LEN_TSSINCRONISMOESTADO + LEN_EOS];
	char szInUsuarioInformado[LEN_INUSUARIOINFORMADO + LEN_EOS];
	char szDtCadastroPup[LEN_DTCADASTROPUP + LEN_EOS];
	char szIdUsuarioInclusaoPup[LEN_IDUSUARIOINCLUSAOPUP + LEN_EOS];
	char szCdSenhaPreativa[LEN_CDSENHAPREATIVA + LEN_EOS];
} TLinhaTelefonica;


/*******************************************************************************
 * Estrutura referente a customer.pessoalinhahistorico
 *******************************************************************************/
typedef	struct {
	char szIdPessoaLinhaHistorico[LEN_IDPESSOALINHAHISTORICO + LEN_EOS];
	char szDtRelacionamento[LEN_DTRELACIONAMENTO + LEN_EOS];
	char szCdAreaRegistro[LEN_CDAREAREGISTRO + LEN_EOS];
	char szNrLinha[LEN_NRLINHA + LEN_EOS];
	char szIdTipoRelacionamento[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
	char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
	char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaLinhaHistorico;


/*******************************************************************************
 * Estrutura referente a customer.pessoasegmentacao
 *******************************************************************************/
typedef	struct {
	char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
	char szIdPessoaSegmentacao[LEN_IDPESSOASEGMENTACAO + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaSegmentacao;


/*******************************************************************************
 * Estrutura referente a linha.permissaolinhapup
 *******************************************************************************/
typedef	struct {
	char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
	char szSgPermissaoPUP[LEN_SGPERMISSAOPUP + LEN_EOS];
	char szInAtivo[LEN_INATIVO + LEN_EOS];
	char szDtExpiracao[LEN_DTEXPIRACAO_CUSTOM +	LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szInProcon[LEN_INPROCON + LEN_EOS];
    char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdCanal[LEN_IDCANAL + LEN_EOS];
} TPermissaoLinhaPUP;


/*******************************************************************************
 * Estrutura referente a infra.filasetclientinfo
 *******************************************************************************/
typedef	struct {
	char szIdFilaSetClientInfo[LEN_IDFILASETCLIENTINFO + LEN_EOS];
	char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
	char szXml[LEN_XML + LEN_EOS];
} TFilaSetClientInfo;


/*******************************************************************************
 * Estrutura referente a customer.contaendereco
 *******************************************************************************/
typedef	struct {
	char szIdContaEndereco[LEN_IDCONTAENDERECO + LEN_EOS];
	char szIdPessoaEndereco[LEN_IDPESSOAENDERECO + LEN_EOS];
	char szIdConta[LEN_IDCONTA + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
	char szIdTipoEnderecoCobranca[LEN_IDTIPOENDERECOCOBRANCA + LEN_EOS];
} TContaEndereco;


#define	LEN_IDCFOP						LEN_NUMBER_ORA
#define	LEN_DSCFOP						255
#define	LEN_SGCFOP						255

/*******************************************************************************
 * Estrutura referente a apoio.cfop
 *******************************************************************************/
typedef	struct {
	char szIdCFOP[LEN_IDCFOP + LEN_EOS];
	char szDsCFOP[LEN_DSCFOP + LEN_EOS];
	char szSgCFOP[LEN_SGCFOP + LEN_EOS];
} TCFOP;


/*******************************************************************************
 * Estrutura referente a apoio.sexo
 *******************************************************************************/
typedef	struct {
	char szIdSexo[LEN_IDSEXO + LEN_EOS];
	char szSgSexo[LEN_SGSEXO + LEN_EOS];
	char szDsSexo[LEN_DSSEXO + LEN_EOS];
} TSexo;


/*******************************************************************************
 * Estrutura referente a apoio.estadocivil
 *******************************************************************************/
typedef	struct {
	char szIdEstadoCivil[LEN_IDESTADOCIVIL + LEN_EOS];
	char szSgEstadoCivil[LEN_SGESTADOCIVIL + LEN_EOS];
	char szDsEstadoCivil[LEN_DSESTADOCIVIL + LEN_EOS];
} TEstadoCivil;


/*******************************************************************************
 * Estrutura referente a customer.pessoafisica
 *******************************************************************************/
typedef	struct {
	char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
	char szDtNascimento[LEN_DTNASCIMENTO + LEN_EOS];
	char szNmMae[LEN_NMMAE + LEN_EOS];
	char szNmPai[LEN_NMPAI + LEN_EOS];
	char szIdTratamento[LEN_IDTRATAMENTO + LEN_EOS];
	char szIdEstadoCivil[LEN_IDESTADOCIVIL + LEN_EOS];
	char szIdPais[LEN_IDPAIS + LEN_EOS];
	char szIdSexo[LEN_IDSEXO + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaFisica;


/*******************************************************************************
 * Estrutura referente a customer.pessoajuridica
 *******************************************************************************/
typedef	struct {
	char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
	char szNmPessoaFilial[LEN_NMPESSOAFILIAL + LEN_EOS];
	char szNmFantasia[LEN_NMFANTASIA + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
	char szDtFundacao[LEN_DTFUNDACAO + LEN_EOS];
	char szIdCFOP[LEN_IDCFOP + LEN_EOS];
} TPessoaJuridica;


/*******************************************************************************
 * Estrutura referente a customer.pessoaendereco
 *******************************************************************************/
typedef	struct {
	char szIdPessoaEndereco[LEN_IDPESSOAENDERECO + LEN_EOS];
	char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
	char szNrSequencia[LEN_NRSEQUENCIA + LEN_EOS];
	char szIdPais[LEN_IDPAIS + LEN_EOS];
	char szNmMunicipio[LEN_NMMUNICIPIO + LEN_EOS];
	char szNmLocalidade[LEN_NMLOCALIDADE + LEN_EOS];
	char szNmBairro[LEN_NMBAIRRO + LEN_EOS];
	char szNmTipoLogradouro[LEN_NMTIPOLOGRADOURO + LEN_EOS];
	char szNmTituloLogradouro[LEN_NMTITULOLOGRADOURO + LEN_EOS];
	char szNmLogradouro[LEN_NMLOGRADOURO + LEN_EOS];
	char szNrEndereco[LEN_NRENDERECO + LEN_EOS];
	char szDsEnderecoComplemento[LEN_DSENDERECOCOMPLEMENTO + LEN_EOS];
	char szInEnderecoPreferencial[LEN_INENDERECOPREFERENCIAL + LEN_EOS];
	char szNrCep[LEN_NRCEP + LEN_EOS];
	char szDtCadastro[LEN_DTCADASTRO + LEN_EOS];
	char szIdTipoEndereco[LEN_IDTIPOENDERECO + LEN_EOS];
	char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
	char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
	char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
	char szIdUF[LEN_IDUF + LEN_EOS];
	char szDsAosCuidados[LEN_DSAOSCUIDADOS + LEN_EOS];
	char szCdCaixaPostal[LEN_CDCAIXAPOSTAL + LEN_EOS];
	char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
	char szIdEnderecoSistemaOrigem[LEN_IDENDERECOSISTEMAORIGEM + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
	char szInEnderecoSujo[LEN_INENDERECOSUJO + LEN_EOS];
} TPessoaEndereco;


/*******************************************************************************
 * Estrutura referente a customer.pessoaportabilidadehist
 *******************************************************************************/
typedef	struct {
    char szIdPessoaPortabilidadeHist[LEN_IDPESSOAPORTABILIDADEHISTPORT + LEN_EOS];
    char szIdTipoLinha[LEN_IDTIPOLINHAPORT + LEN_EOS];
    char szCdAreaRegistro[LEN_CDAREAREGISTROPORT + LEN_EOS];
    char szNrLinha[LEN_NRLINHAPORT + LEN_EOS];
    char szIdTipoPessoa[LEN_IDTIPOPESSOAPORT + LEN_EOS];
    char szNmPessoa[LEN_NMPESSOAPORT + LEN_EOS];
    char szIdTipoDocumento[LEN_IDTIPODOCUMENTOPORT + LEN_EOS];
    char szNrDocumento[LEN_NRDOCUMENTOPORT + LEN_EOS];
    char szIdTipoEndereco[LEN_IDTIPOENDERECOPORT + LEN_EOS];
    char szNmTipoLogradouro[LEN_NMTIPOLOGRADOUROPORT + LEN_EOS];
    char szNmLogradouro[LEN_NMLOGRADOUROPORT + LEN_EOS];
    char szNrEndereco[LEN_NRENDERECOPORT + LEN_EOS];
    char szNmMunicipio[LEN_NMMUNICIPIOPORT + LEN_EOS];
    char szNmBairro[LEN_NMBAIRROPORT + LEN_EOS];
    char szNrCep[LEN_NRCEPPORT + LEN_EOS];
    char szDsAcaoPortabilidade[LEN_DSACAOPORTABILIDADEPORT + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAOPORT + LEN_EOS];
    char szSgTipoPortabilidade[LEN_SGTIPOPORTABILIDADEPORT + LEN_EOS];
} TPessoaPortabilidadeHist;


/*******************************************************************************
 * Estrutura referente a customer.linhaconta
 *******************************************************************************/
typedef	struct {
	char szIdLinhaConta[LEN_IDLINHACONTA + LEN_EOS];
	char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
	char szIdConta[LEN_IDCONTA + LEN_EOS];
	char szIdTipoRelacionamento[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
	char szDtLinhaConta[LEN_DTLINHACONTA + LEN_EOS];
	char szInLinhaMaster[LEN_INLINHAMASTER + LEN_EOS];
	char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
	char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
	char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TLinhaConta;


/*******************************************************************************
 * Estrutura referente a apoio.arearegistro
 *******************************************************************************/
typedef	struct {
	char szInBloqueado[LEN_INBLOQUEADO + LEN_EOS];
	char szCdAreaRegistro[LEN_CDAREAREGISTROBLOQ + LEN_EOS];
} TAreaRegistroBloqueado;


/*******************************************************************************
 * Estrutura referente a customer.documento
 *******************************************************************************/
typedef	struct {
	char szIdDocumento[LEN_IDDOCUMENTO + LEN_EOS];
	char szCdCpfCnpjBase[LEN_CDCPFCNPJBASE + LEN_EOS];
	char szCdCnpjFilial[LEN_CDCNPJFILIAL + LEN_EOS];
	char szCdCpfCnpjControle[LEN_CDCPFCNPJCONTROLE + LEN_EOS];
	char szNrDocumento[LEN_NRDOCUMENTO + LEN_EOS];
	char szSgOrgaoExpedidor[LEN_SGORGAOEXPEDIDOR + LEN_EOS];
	char szDtEmissao[LEN_DTEMISSAO + LEN_EOS];
	char szIdPais[LEN_IDPAIS + LEN_EOS];
	char szIdUF[LEN_IDUF + LEN_EOS];
	char szIdTipoDocumento[LEN_IDTIPODOCUMENTO + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
} TDocumento;


/*******************************************************************************
 * Estrutura referente a apoio.sistemaorigem
 *******************************************************************************/
typedef	struct {
	char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
	char szSgSistemaOrigem[LEN_SGSISTEMAORIGEM + LEN_EOS];
	char szNmSistemaOrigem[LEN_NMSISTEMAORIGEM + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
	char szIndisponivel[LEN_INDISPONIVEL + LEN_EOS];
} TSistemaOrigem;


/*******************************************************************************
 * Estrutura referente a apoio.tipodocumento
 *******************************************************************************/
typedef	struct {
	char szIdTipoDocumento[LEN_IDTIPODOCUMENTO + LEN_EOS];
	char szSgTipoDocumento[LEN_SGTIPODOCUMENTO + LEN_EOS];
	char szDsTipoDocumento[LEN_DSTIPODOCUMENTO + LEN_EOS];
	char szIdTipoPessoa[LEN_IDTIPOPESSOA + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
	char szNrPrioridade[LEN_NRPRIORIDADE + LEN_EOS];
	char szSgClassificacao[LEN_SGCLASSIFICACAO + LEN_EOS];
} TTipoDocumento;


/*******************************************************************************
 * Estrutura referente a apoio.tipopessoa
 *******************************************************************************/
typedef	struct {
	char szIdTipoPessoa[LEN_IDTIPOPESSOA + LEN_EOS];
	char szSgTipoPessoa[LEN_SGTIPOPESSOA + LEN_EOS];
	char szDsTipoPessoa[LEN_DSTIPOPESSOA + LEN_EOS];
} TTipoPessoa;


/*******************************************************************************
 * Estrutura referente a apoio.tipocomunicacao
 *******************************************************************************/
typedef	struct {
	char szIdTipoComunicacao[LEN_IDTIPOCOMUNICACAO + LEN_EOS];
	char szSgTipoComunicacao[LEN_SGTIPOCOMUNICACAO + LEN_EOS];
	char szDsTipoComunicacao[LEN_DSTIPOCOMUNICACAO + LEN_EOS];
	char szIdFormaRetorno[LEN_IDFORMARETORNO + LEN_EOS];
	char szSgClassificacao[LEN_SGCLASSIFICACAO + LEN_EOS];
} TTipoComunicacao;


/*******************************************************************************
 * Estrutura referente a apoio.tipolinha
 *******************************************************************************/
typedef	struct {
	char szIdTipoLinha[LEN_IDTIPOLINHA + LEN_EOS];
	char szSgTipoLinha[LEN_SGTIPOLINHA + LEN_EOS];
	char szDsTipoLinha[LEN_DSTIPOLINHA + LEN_EOS];
} TTipoLinha;


/*******************************************************************************
 * Estrutura referente a apoio.tipocarteira
 *******************************************************************************/
typedef	struct {
	char szIdTipoCarteira[LEN_IDTIPOCARTEIRA + LEN_EOS];
	char szSgTipoCarteira[LEN_SGTIPOCARTEIRA + LEN_EOS];
	char szDsTipoCarteira[LEN_DSTIPOCARTEIRA + LEN_EOS];
} TTipoCarteira;


/*******************************************************************************
 * Estrutura referente a Misc (conjunto	de tabelas)
 *******************************************************************************/
typedef	struct {
	char szIdTipoLinha[LEN_IDTIPOLINHA + LEN_EOS];
	char szIdTipoPessoa[LEN_IDTIPOPESSOA + LEN_EOS];
	char szCdAreaRegistro[LEN_CDAREAREGISTRO + LEN_EOS];
	char szNrLinha[LEN_NRLINHA + LEN_EOS];
	char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
} TMisc;


/*******************************************************************************
 * Estrutura referente a customer.pessoaportabilidade
 *******************************************************************************/
typedef	struct {
	char szInSincronizado[LEN_INSINCRONIZADO + LEN_EOS];
	char szIdTipoPessoa[LEN_IDTIPOPESSOA + LEN_EOS];
	char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
	char szIdTipoLinha[LEN_IDTIPOLINHA + LEN_EOS];
	char szCdAreaRegistro[LEN_CDAREAREGISTRO + LEN_EOS];
	char szNrLinha[LEN_NRLINHA + LEN_EOS];
	char szIdAcaoPortabilidade[LEN_IDACAOPORTABILIDADE + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaPortabilidade;


/*******************************************************************************
 * Estrutura referente a customer.pessoaconta
 *******************************************************************************/
typedef	struct {
	char szIdPessoaConta[LEN_IDPESSOACONTA + LEN_EOS];
	char szIdTipoRelacionamento[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
	char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
	char szIdConta[LEN_IDCONTA + LEN_EOS];
	char szDtPessoaConta[LEN_DTPESSOACONTA + LEN_EOS];
	char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
} TPessoaConta;


/*******************************************************************************
 * Estrutura referente a apoio.parametro
 *******************************************************************************/
typedef	struct {
	char szIdParametro[LEN_IDPARAMETRO + LEN_EOS];
	char szCdParametro[LEN_CDPARAMETRO + LEN_EOS];
	char szDsParametro[LEN_DSPARAMETRO + LEN_EOS];
	char szDsValorParametro[LEN_DSVALORPARAMETRO + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
}TParametro;


/*******************************************************************************
 * Estrutura referente a apoio.tipoenderecocobranca
 *******************************************************************************/
typedef	struct {
	char szIdTipoEnderecoCobranca[LEN_IDTIPOENDERECOCOBRANCA + LEN_EOS];
	char szSgTipoEnderecoCobranca[LEN_SGTIPOENDERECOCOBRANCA + LEN_EOS];
	char szSgClassificacao[LEN_SGCLASSIFICACAO + LEN_EOS];
	char szDsTipoEnderecoCobranca[LEN_DSTIPOENDERECOCOBRANCA + LEN_EOS];
	char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
	char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
}TTipoEnderecoCobranca;


/*******************************************************************************
 * Estrutura referente a apoio.tipoendereco
 *******************************************************************************/
typedef	struct {
	char szIdTipoEndereco[LEN_IDTIPOENDERECO + LEN_EOS];
	char szSgTipoEndereco[LEN_SGTIPOENDERECO + LEN_EOS];
	char szDsTipoEndereco[LEN_DSTIPOENDERECO + LEN_EOS];
}TTipoEndereco;


/*******************************************************************************
 * Estrutura referente a customer.pessoalinhapre
 *******************************************************************************/
typedef	struct {
	char szIdPessoaLinha[LEN_IDPESSOALINHA + LEN_EOS];
	char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
	char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
	char szInMudancaTitularidade[LEN_INMUDANCATITULARIDADE + LEN_EOS];
	char szInSincronismo[LEN_INSINCRONISMO + LEN_EOS];
	char szInUsuarioNaoInformado[LEN_INUSUARIONAOINFORMADO + LEN_EOS];
} TPessoaLinhaPre;


/*******************************************************************************
 * Estrutura referente ao processo de desmembramento de	nomes
 *******************************************************************************/
typedef	struct {
	char szNomeCompleto[LEN_NOME + LEN_EOS];
	char szNomePrimeiro[LEN_NOME_PRIMEIRO +	LEN_EOS];
	char szNomeMeio[LEN_NOME_MEIO +	LEN_EOS];
	char szNomeFim[LEN_NOME_FIM	+ LEN_EOS];
} TDesmembraNome;


/**************************************************************************************
 * Macro para obtencao dos dados, validacao	e desalocacao de memoria do	XML	de entrada
 **************************************************************************************/
#define	GETTREE(CampoDestino, DOMNode, Tag,	Nivel, Obrigatoriedade,	Mensagem) \
{\
	ULOG("GETTREE -	CampoDestino[%s](%d) Tag[%s] Nivel[%s] Obrigatoriedade[%s]", #CampoDestino,	sizeof(CampoDestino), Tag, #Nivel, #Obrigatoriedade); \
	memset(CampoDestino, 0x00, sizeof(CampoDestino)); \
	if((pTree =	walkTree(DOMNode, Tag, Nivel)) != NULL)	\
	{\
		if((sizeof(CampoDestino)-LEN_EOS) <	(int)strlen(pTree))	\
		{\
			sprintf(szMessage, "Campo %s maior que o esperado",	strlen(Mensagem) > 0 ? Mensagem	: Tag);	\
			XMLString::release(&pTree);	\
			throw PPExceptionPORT(ERRO_PARAMETRO_NULL, szMessage); \
		}\
		else if((strlen(pTree) == 0) &&	(Obrigatoriedade ==	OBRIGATORIO)) \
		{\
			sprintf(szMessage, "Campo %s sem valor", strlen(Mensagem) >	0 ?	Mensagem : Tag); \
			XMLString::release(&pTree);	\
			throw PPExceptionPORT(ERRO_PARAMETRO_NULL, szMessage); \
		}\
		strcpy(CampoDestino, pTree); \
		ULOG("GETTREE -	CampoDestino[%s] Valor[%s]", #CampoDestino,	pTree);	\
		XMLString::release(&pTree);	\
	}\
	else \
	{\
		if(Obrigatoriedade == OBRIGATORIO) \
		{\
			sprintf(szMessage, "Campo %s obrigatorio", strlen(Mensagem)	> 0	? Mensagem : Tag); \
			throw PPExceptionPORT(ERRO_PARAMETRO_NULL, szMessage); \
		}\
	}\
}


/*******************************************************************************
 * Macros para manipulacao de dados	ProC/C++
 *******************************************************************************/
#define	STRCPY_TO_ORA(dest,	source)	{ \
	dest.len = (unsigned short)	strlen(source);\
	strncpy((char *) dest.arr, (const char *) source, (size_t)dest.len); \
	ULOG("STRCPY_TO_ORA	-> Valor[%.*s] Destino[%s] Origem[%s]",	(int)dest.len, dest.arr, #dest,	#source); }

#define	STRCPY_FROM_ORA(dest, source) {	\
	dest[source.len] = 0; \
	strncpy((char *)dest,(const	char *)source.arr, source.len);	\
	ULOG("STRCPY_FROM_ORA -> Valor[%s] Destino[%s] Origem[%s]",	dest, #dest, #source); }

#define	MEMSET_ORA(dest) { \
	dest.len=0;	\
	/* ULOG("MEMSET_ORA	-> sizeof(dest.arr)(%d)", sizeof(dest.arr)); */	\
	memset(dest.arr, 0x00, sizeof(dest.arr)); }

#endif /* PPGLOBALPORT */
