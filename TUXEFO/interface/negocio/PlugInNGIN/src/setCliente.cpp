#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;


void PlugInNGIN::setCliente()
{
	// Variaveis para valores vindos do java
	char* pc_ProxyLinha            = getIdLinhaSistemaOrigem();
	char* pc_idGrupo               = NULL;
	char* pc_usuario               = NULL;
	char* pc_xmlns                 = NULL;
	char* pc_tipoCliente           = NULL;
	char* pc_nome                  = NULL;
	char* pc_nomeAbreviado         = NULL;
	char* pc_confidencial          = NULL;
	char* pc_CPF                   = NULL;
	char* pc_tipoCPF               = NULL;
	char* pc_RG                    = NULL;
	char* pc_tipoRG                = NULL;
	char* pc_dataExpiracao         = NULL;
	char* pc_orgaoExpeditor        = NULL;
	char* pc_estadoExpedicao       = NULL;
	char* pc_dataNascimento        = NULL;
	char* pc_estadoCivil           = NULL;
	char* pc_codSexo               = NULL;
	char* pc_telefone              = NULL;
	char* pc_fax                   = NULL;
	char* pc_eMail                 = NULL;
	char* pc_numDepend             = NULL;
	char* pc_passaporte            = NULL;
	char* pc_tipoPassaporte        = NULL;
	char* pc_cartaConducao         = NULL;
	char* pc_tipoCartaCond         = NULL;
	char* pc_aoCuidadoDe           = NULL;
	char* pc_obs                   = NULL;
	char* pc_conservatoriaRegistro = NULL;
	char* pc_CNPJ                  = NULL;
	char* pc_CNAE                  = NULL;
	char* pc_habilitacoes          = NULL;
	char* pc_outroCelular          = NULL;
	char* pc_tipoConta             = NULL;
	char* pc_subTipo               = NULL;
	char* pc_rendaMensal           = NULL;
	char* pc_contaCorrent          = NULL;
	char* pc_banco                 = NULL;
	char* pc_IE                    = NULL;
	char* pc_nomeSufixo            = NULL;
	char* pc_primeiroNome          = NULL;
	char* pc_sobreNome             = NULL;
	char* pc_nomeContato           = NULL;
	char* pc_carteiraTrabalho      = NULL;
	char* pc_endereco              = NULL;
	char* pc_complemento           = NULL;
	char* pc_bairro                = NULL;
	char* pc_CEP                   = NULL;
	char* pc_cidade                = NULL;
	char* pc_estado                = NULL;
	char* pc_pais                  = NULL;
	char* pc_logradouro            = NULL;
	char* pc_numero                = NULL;
	char* pc_cdLogradouro		   = NULL;
	char* pc_InCnl				   = NULL;
	char* pc_InCodigoIBGE		   = NULL;
	char* pc_inTelContato		   = NULL;

	tuxhp->setTranslateSpecialChars(true);

	// Trata a TAG 'usuario'
	pc_usuario = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	if (pc_usuario == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_USUARIO, EMSG_TAG_XML_IN_NE_USUARIO);
	
	if (*pc_usuario == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_USUARIO, EMSG_TAG_XML_IN_VV_USUARIO);


	// Associacao dos valores
	pc_idGrupo               = tuxhp->walkTree(dnode, TAG_XML_IN_ID_GRUPO, 0);	
	pc_tipoCliente           = tuxhp->walkTree(dnode, TAG_XML_IN_TIPO_CLIENTE, 0);	
	pc_nome                  = tuxhp->walkTree(dnode, TAG_XML_IN_NOME, 0);
	pc_nomeAbreviado         = tuxhp->walkTree(dnode, TAG_XML_IN_NOME_ABREVIADO, 0);
	pc_confidencial          = tuxhp->walkTree(dnode, TAG_XML_IN_CONFIDENCIAL, 0);
	pc_CPF                   = tuxhp->walkTree(dnode, TAG_XML_IN_CPF, 0);
	pc_tipoCPF               = tuxhp->walkTree(dnode, TAG_XML_IN_TIPO_CPF, 0);
	pc_RG                    = tuxhp->walkTree(dnode, TAG_XML_IN_RG, 0);
	pc_tipoRG                = tuxhp->walkTree(dnode, TAG_XML_IN_TIPO_RG, 0);
	pc_dataExpiracao         = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_EXPIRACAO, 0);
	pc_orgaoExpeditor        = tuxhp->walkTree(dnode, TAG_XML_IN_ORGAO_EXPEDITOR, 0);
	pc_estadoExpedicao       = tuxhp->walkTree(dnode, TAG_XML_IN_ESTADO_EXPEDICAO, 0);
	pc_dataNascimento        = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_NASCIMENTO, 0);
	pc_estadoCivil           = tuxhp->walkTree(dnode, TAG_XML_IN_ESTADO_CIVIL, 0);
	pc_codSexo               = tuxhp->walkTree(dnode, TAG_XML_IN_COD_SEXO, 0);
	pc_telefone              = tuxhp->walkTree(dnode, TAG_XML_IN_TELEFONE, 0);
	pc_fax                   = tuxhp->walkTree(dnode, TAG_XML_IN_FAX, 0);
	pc_eMail                 = tuxhp->walkTree(dnode, TAG_XML_IN_EMAIL, 0);
	pc_numDepend             = tuxhp->walkTree(dnode, TAG_XML_IN_NUM_DEPEND, 0);
	pc_passaporte            = tuxhp->walkTree(dnode, TAG_XML_IN_PASSAPORTE, 0);
	pc_tipoPassaporte        = tuxhp->walkTree(dnode, TAG_XML_IN_TIPO_PASSAPORTE, 0);
	pc_cartaConducao         = tuxhp->walkTree(dnode, TAG_XML_IN_CARTA_CONDUCAO, 0);
	pc_tipoCartaCond         = tuxhp->walkTree(dnode, TAG_XML_IN_TIPO_CARTA_COND, 0);
	pc_aoCuidadoDe           = tuxhp->walkTree(dnode, TAG_XML_IN_AO_CUIDADO_DE, 0);
	pc_obs                   = tuxhp->walkTree(dnode, TAG_XML_IN_OBS, 0);
	pc_conservatoriaRegistro = tuxhp->walkTree(dnode, TAG_XML_IN_CONSERVATORIA_REGISTRO, 0);
	pc_CNPJ                  = tuxhp->walkTree(dnode, TAG_XML_IN_CNPJ, 0);
	pc_CNAE                  = tuxhp->walkTree(dnode, TAG_XML_IN_CNAE, 0);
	pc_habilitacoes          = tuxhp->walkTree(dnode, TAG_XML_IN_HABILITACOES, 0);
	pc_outroCelular          = tuxhp->walkTree(dnode, TAG_XML_IN_OUTRO_CELULAR, 0);
	pc_tipoConta             = tuxhp->walkTree(dnode, TAG_XML_IN_TIPO_CONTA, 0);
	pc_subTipo               = tuxhp->walkTree(dnode, TAG_XML_IN_SUB_TIPO, 0);
	pc_rendaMensal           = tuxhp->walkTree(dnode, TAG_XML_IN_RENDA_MENSAL, 0);
	pc_contaCorrent          = tuxhp->walkTree(dnode, TAG_XML_IN_CONTA_CORRENT, 0);
	pc_banco                 = tuxhp->walkTree(dnode, TAG_XML_IN_BANCO, 0);
	pc_IE                    = tuxhp->walkTree(dnode, TAG_XML_IN_IE, 0);
	pc_nomeSufixo            = tuxhp->walkTree(dnode, TAG_XML_IN_NOME_SUFIXO, 0);
	pc_primeiroNome          = tuxhp->walkTree(dnode, TAG_XML_IN_PRIMEIRO_NOME, 0);
	pc_sobreNome             = tuxhp->walkTree(dnode, TAG_XML_IN_SOBRE_NOME, 0);
	pc_nomeContato           = tuxhp->walkTree(dnode, TAG_XML_IN_NOME_CONTATO, 0);
	pc_carteiraTrabalho      = tuxhp->walkTree(dnode, TAG_XML_IN_CARTEIRA_TRABALHO, 0);
	pc_endereco              = tuxhp->walkTree(dnode, TAG_XML_IN_ENDERECO, 0);
	pc_complemento           = tuxhp->walkTree(dnode, TAG_XML_IN_COMPLEMENTO, 0);
	pc_bairro                = tuxhp->walkTree(dnode, TAG_XML_IN_BAIRRO, 0);
	pc_CEP                   = tuxhp->walkTree(dnode, TAG_XML_IN_CEP, 0);
	pc_cidade                = tuxhp->walkTree(dnode, TAG_XML_IN_CIDADE, 0);
	pc_estado                = tuxhp->walkTree(dnode, TAG_XML_IN_ESTADO, 0);
	pc_pais                  = tuxhp->walkTree(dnode, TAG_XML_IN_PAIS, 0);
	pc_logradouro            = tuxhp->walkTree(dnode, TAG_XML_IN_LOGRADOURO, 0);
	pc_numero                = tuxhp->walkTree(dnode, TAG_XML_IN_NUMERO, 0);
	// novas tags
	pc_InCnl				 = tuxhp->walkTree(dnode, "inCnl", 0);
	pc_InCodigoIBGE			 = tuxhp->walkTree(dnode, "cdIbge", 0);
	pc_inTelContato          = tuxhp->walkTree(dnode, XML_NGN_ATTNM_IN_TEL_CONTATO, 0);


	// Variaveis usadas no pacote de envio ao GW
	char vc_idTrans[256];
	char  vc_inSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char  vc_accountType[2];
	char  vc_CEP5[6];
	char  vc_CEP3[4];
	char  vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_SET];
	char  vc_logradouro[256];
	CParametro oParametro;

	memset(vc_idTrans, '\0', sizeof(vc_idTrans));			// Limpa idTrans
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));   // Limpa XMLToGWNGIN
	memset(vc_CEP5, '\0', sizeof(vc_CEP5));                 // Limpa CEP5
	memset(vc_CEP3, '\0', sizeof(vc_CEP3));                 // Limpa CEP3

	if ((pc_CEP != NULL) && ((strlen(pc_CEP) == 8) || (strlen(pc_CEP) == 9)))
	{
		strncpy(vc_CEP5, pc_CEP, 5);

		if(strlen(pc_CEP) == 8)
			strncpy(vc_CEP3, (pc_CEP + 5), 3);
		else
			strncpy(vc_CEP3, (pc_CEP + 6), 3);
	}

	if(pc_idGrupo != NULL && strlen(pc_idGrupo) > 0)
		strcpy(vc_accountType, XML_NGN_VAL_GRUPO);
	else
		strcpy(vc_accountType, XML_NGN_VAL_PRIVADO);
		

	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);	

	char* pc_Linha   =   tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_usuario, XML_NGN_SVC_SET_CLIENT_INFO);


	// Obtem numero randomico para idTrans
	Util::rand(vc_idTrans);

	// TAG '<attribute name="InSid">InSid_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idUser">idUser_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_USER, pc_usuario, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idTrans">idTrans_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_TRANS, vc_idTrans, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idLinha">idLinha_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_LINHA, pc_ProxyLinha, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="accountType">accountType_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ACCOUNT_TYPE, vc_accountType, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="codTipoentidade">codTipoentidade_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_CD_TP_ENTIDADE, (pc_tipoCliente == NULL)? "" : pc_tipoCliente, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Nome">Nome_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_NOME, (pc_nome == NULL)? "" : pc_nome, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="nomeAbreviado">nomeAbreviado_val</attribute>'
	if(pc_nomeAbreviado != NULL && strlen(pc_nomeAbreviado) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_NOME_ABREVIADO, (pc_nomeAbreviado == NULL)? "" : pc_nomeAbreviado, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Confidencial">Confidencial_val</attribute>'
	if(pc_confidencial != NULL && strlen(pc_confidencial) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_CONFIDENCIAL, (pc_confidencial == NULL)? "" : pc_confidencial, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="nif">nif_val</attribute>'
	if(pc_CPF != NULL && strlen(pc_CPF) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_NIF, (pc_CPF == NULL)? "" : pc_CPF, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="tipoNif">tipoNif_val</attribute>'
	if(pc_tipoCPF != NULL && strlen(pc_tipoCPF) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_TP_NIF, (pc_tipoCPF == NULL)? "" : pc_tipoCPF, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="bi">bi_val</attribute>'
	if(pc_RG != NULL && strlen(pc_RG) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_BI, (pc_RG == NULL)? "" : pc_RG, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="tipoBi">tipoBi_val</attribute>'
	if(pc_tipoRG != NULL && strlen(pc_tipoRG) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_TP_BI, (pc_tipoRG == NULL)? "" : pc_tipoRG, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="rgDataEmiss">rgDataEmiss_val</attribute>'
	if(pc_dataExpiracao != NULL && strlen(pc_dataExpiracao) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_RG_DT_EMISS, (pc_dataExpiracao == NULL)? "" : pc_dataExpiracao, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="rgOrgExp">rgOrgExp_val</attribute>'
	if(pc_orgaoExpeditor != NULL && strlen(pc_orgaoExpeditor) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_RG_ORG_EXP, (pc_orgaoExpeditor == NULL)? "" : pc_orgaoExpeditor, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="rgState">rgState_val</attribute>'
	if(pc_estadoExpedicao != NULL && strlen(pc_estadoExpedicao) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_RG_STATE, (pc_estadoExpedicao == NULL)? "" : pc_estadoExpedicao, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="dataNascimento">dataNascimento_val</attribute>'
	if(pc_dataNascimento != NULL && strlen(pc_dataNascimento) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_DT_NASCIMENTO, (pc_dataNascimento == NULL)? "" : pc_dataNascimento, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="estadoCivil">estadoCivil_val</attribute>'
	if(pc_estadoCivil != NULL && strlen(pc_estadoCivil) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ESTADO_CIVIL, (pc_estadoCivil == NULL)? "" : pc_estadoCivil, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="codSexo">codSexo_val</attribute>'
	if(pc_codSexo != NULL && strlen(pc_codSexo) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_CD_SEXO, (pc_codSexo == NULL)? "" : pc_codSexo, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Telefone">Telefone_val</attribute>'
	if(pc_telefone != NULL && strlen(pc_telefone) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_TELEFONE, (pc_telefone == NULL)? "" : pc_telefone, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Fax">Fax_val</attribute>'
	if(pc_fax != NULL && strlen(pc_fax) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_FAX, (pc_fax == NULL)? "" : pc_fax, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="e-mail">e-mail_val</attribute>'
	if(pc_eMail != NULL && strlen(pc_eMail) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_EMAIL, (pc_eMail == NULL)? "" : pc_eMail, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="agregFamiliar">agregFamiliar_val</attribute>'
	if(pc_numDepend != NULL && strlen(pc_numDepend) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_AGREG_FAMILIAR, (pc_numDepend == NULL)? "" : pc_numDepend, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="passaporte">passaporte_val</attribute>'
	if(pc_passaporte != NULL && strlen(pc_passaporte) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_PASSAPORTE, (pc_passaporte == NULL)? "" : pc_passaporte, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="tipoPassaporte">tipoPassaporte_val</attribute>'
	if(pc_tipoPassaporte != NULL && strlen(pc_tipoPassaporte) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_TP_PASSAPORTE, (pc_tipoPassaporte == NULL)? "" : pc_tipoPassaporte, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="cartaConducao">cartaConducao_val</attribute>'
	if(pc_cartaConducao != NULL && strlen(pc_cartaConducao) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_CARTA_CONDUCAO, (pc_cartaConducao == NULL)? "" : pc_cartaConducao, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="tipoCartaCond">tipoCartaCond_val</attribute>'
	if(pc_tipoCartaCond != NULL && strlen(pc_tipoCartaCond) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_TP_CARTA_COND, (pc_tipoCartaCond == NULL)? "" : pc_tipoCartaCond, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="aoCuidadoDe">aoCuidadoDe_val</attribute>'
	if(pc_aoCuidadoDe != NULL && strlen(pc_aoCuidadoDe) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_AO_CUIDADO_DE, (pc_aoCuidadoDe == NULL)? "" : pc_aoCuidadoDe, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Obs">Obs_val</attribute>'
	if(pc_obs != NULL && strlen(pc_obs) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_OBS, (pc_obs == NULL)? "" : pc_obs, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="conservatoriaRegistro">conservatoriaRegistro_val</attribute>'
	if(pc_conservatoriaRegistro != NULL && strlen(pc_conservatoriaRegistro) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_CONSERVATORIA_REGISTRO, (pc_conservatoriaRegistro == NULL)? "" : pc_conservatoriaRegistro, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="registroComercial">registroComercial_val</attribute>'
	if(pc_CNPJ != NULL && strlen(pc_CNPJ) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_REGISTRO_COMERCIAL, (pc_CNPJ == NULL)? "" : pc_CNPJ, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Profissao">Profissao_val</attribute>'
	//if(pc_CNAE != NULL && strlen(pc_CNAE) > 0)
	//	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_PROFISSAO, (pc_CNAE == NULL)? "" : pc_CNAE, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Habilitacoes">Habilitacoes_val</attribute>'
	if(pc_habilitacoes != NULL && strlen(pc_habilitacoes) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_HABILITACOES, (pc_habilitacoes == NULL)? "" : pc_habilitacoes, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Telemovel">Telemovel_val</attribute>'
	if(pc_outroCelular != NULL && strlen(pc_outroCelular) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_TELEMOVEL, (pc_outroCelular == NULL)? "" : pc_outroCelular, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="tipoConta">tipoConta_val</attribute>'
	if(pc_tipoConta != NULL && strlen(pc_tipoConta) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_TP_CONTA, (pc_tipoConta == NULL)? "" : pc_tipoConta, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="subTipo">subTipo_val</attribute>'
	if(pc_subTipo != NULL && strlen(pc_subTipo) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_SUB_TIPO, (pc_subTipo == NULL)? "" : pc_subTipo, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="rendaMensal">rendaMensal_val</attribute>'
	if(pc_rendaMensal != NULL && strlen(pc_rendaMensal) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_RENDA_MENSAL, (pc_rendaMensal == NULL)? "" : pc_rendaMensal, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Cc">Cc_val</attribute>'
	if(pc_contaCorrent != NULL && strlen(pc_contaCorrent) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_CC, (pc_contaCorrent == NULL)? "" : pc_contaCorrent, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Bank">Bank_val</attribute>'
	if(pc_banco != NULL && strlen(pc_banco) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_BANK, (pc_banco == NULL)? "" : pc_banco, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Iepj">Iepj_val</attribute>'
	if(pc_IE != NULL && strlen(pc_IE) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IEPJ, (pc_IE == NULL)? "" : pc_IE, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="nameSuffix">nameSuffix_val</attribute>'
	if(pc_nomeSufixo != NULL && strlen(pc_nomeSufixo) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_NAME_SUFFIX, (pc_nomeSufixo == NULL)? "" : pc_nomeSufixo, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="firstName">firstName_val</attribute>'
	if(pc_primeiroNome != NULL && strlen(pc_primeiroNome) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_FIRST_NAME, (pc_primeiroNome == NULL)? "" : pc_primeiroNome, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="lastName">lastName_val</attribute>'
	if(pc_sobreNome != NULL && strlen(pc_sobreNome) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_LAST_NAME, (pc_sobreNome == NULL)? "" : pc_sobreNome, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="nomeContacto">nomeContacto_val</attribute>'
	if(pc_nomeContato != NULL && strlen(pc_nomeContato) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_NOME_CONTACTO, (pc_nomeContato == NULL)? "" : pc_nomeContato, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="carteiraTrabalho">carteiraTrabalho_val</attribute>'
	if(pc_carteiraTrabalho != NULL && strlen(pc_carteiraTrabalho) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_CARTEIRA_TRABALHO, (pc_carteiraTrabalho == NULL)? "" : pc_carteiraTrabalho, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Arruamento">Arruamento_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ARRUAMENTO, (pc_endereco == NULL)? "" : pc_endereco, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Complemento">Complemento_val</attribute>'
	if(pc_complemento != NULL && strlen(pc_complemento) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_COMPLEMENTO, (pc_complemento == NULL)? "" : pc_complemento, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Bairro">Bairro_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_BAIRRO, (pc_bairro == NULL)? "" : pc_bairro, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="codPostal">codPostal_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_CD_POSTAL, (vc_CEP5 == NULL)? "" : vc_CEP5, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="codPostal2">codPostal2_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_CD_POSTAL2, (vc_CEP3 == NULL)? "" : vc_CEP3, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Cidade">Cidade_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_CIDADE, (pc_cidade == NULL)? "" : pc_cidade, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Estado">Estado_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ESTADO, (pc_estado == NULL)? "" : pc_estado, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="indicativoPais">indicativoPais_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_INDICATIVO_PAIS, (pc_pais == NULL)? "" : pc_pais, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="tipoEndereco">tipoEndereco_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_TP_ENDERECO, this->traduzTipoEndereco(vc_logradouro, pc_logradouro), XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="Numero">Numero_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_NUMERO, (pc_numero == NULL)? "" : pc_numero, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="attendancePasswd">attendancePasswd_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ATTENDANCE_PASSWD, "", XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="InCnl">InCnl</attribute>'
	if(pc_InCnl != NULL)
    	if(strlen(pc_InCnl) > 0)
	    	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, "InCnl", pc_InCnl, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="InCodigoIBGE">InCodigoIBGE</attribute>'
	if(pc_InCodigoIBGE != NULL)
    	if(strlen(pc_InCodigoIBGE) > 0)
    		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, "InCodigoIBGE", pc_InCodigoIBGE, XML_NGN_IN_ATTRIBUTE);
			
	// TAG '<attribute name="InTelContato">attendancePasswd_val</attribute>'
	if(pc_inTelContato != NULL)
		if(strlen(pc_inTelContato) > 0)
			sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, "Telefone", pc_inTelContato, XML_NGN_IN_ATTRIBUTE);			


	// Objetos usados para chamada remota
	DOMNode* po_XMLfromGWNGIN = NULL;
	XMLGen   o_XMLtoGWNGIN;

	o_XMLtoGWNGIN.aggregateXML(vc_XMLToGWNGIN);		// Associa o xml


	if (pc_usuario != NULL)               XMLString::release(&pc_usuario);
	if (pc_xmlns != NULL)                 XMLString::release(&pc_xmlns);
	if (pc_tipoCliente != NULL)           XMLString::release(&pc_tipoCliente);
	if (pc_nome != NULL)                  XMLString::release(&pc_nome);
	if (pc_nomeAbreviado != NULL)         XMLString::release(&pc_nomeAbreviado);
	if (pc_confidencial != NULL)          XMLString::release(&pc_confidencial);
	if (pc_CPF != NULL)                   XMLString::release(&pc_CPF);
	if (pc_tipoCPF != NULL)               XMLString::release(&pc_tipoCPF);
	if (pc_RG != NULL)                    XMLString::release(&pc_RG);
	if (pc_tipoRG != NULL)                XMLString::release(&pc_tipoRG);
	if (pc_dataExpiracao != NULL)         XMLString::release(&pc_dataExpiracao);
	if (pc_orgaoExpeditor != NULL)        XMLString::release(&pc_orgaoExpeditor);
	if (pc_estadoExpedicao != NULL)       XMLString::release(&pc_estadoExpedicao);
	if (pc_dataNascimento != NULL)        XMLString::release(&pc_dataNascimento);
	if (pc_estadoCivil != NULL)           XMLString::release(&pc_estadoCivil);
	if (pc_codSexo != NULL)               XMLString::release(&pc_codSexo);
	if (pc_telefone != NULL)              XMLString::release(&pc_telefone);
	if (pc_fax != NULL)                   XMLString::release(&pc_fax);
	if (pc_eMail != NULL)                 XMLString::release(&pc_eMail);
	if (pc_numDepend != NULL)             XMLString::release(&pc_numDepend);
	if (pc_passaporte != NULL)            XMLString::release(&pc_passaporte);
	if (pc_tipoPassaporte != NULL)        XMLString::release(&pc_tipoPassaporte);
	if (pc_cartaConducao != NULL)         XMLString::release(&pc_cartaConducao);
	if (pc_tipoCartaCond != NULL)         XMLString::release(&pc_tipoCartaCond);
	if (pc_aoCuidadoDe != NULL)           XMLString::release(&pc_aoCuidadoDe);
	if (pc_obs != NULL)                   XMLString::release(&pc_obs);
	if (pc_conservatoriaRegistro != NULL) XMLString::release(&pc_conservatoriaRegistro);
	if (pc_CNPJ != NULL)                  XMLString::release(&pc_CNPJ);
	if (pc_CNAE != NULL)                  XMLString::release(&pc_CNAE);
	if (pc_habilitacoes != NULL)          XMLString::release(&pc_habilitacoes);
	if (pc_outroCelular != NULL)          XMLString::release(&pc_outroCelular);
	if (pc_tipoConta != NULL)             XMLString::release(&pc_tipoConta);
	if (pc_subTipo != NULL)               XMLString::release(&pc_subTipo);
	if (pc_rendaMensal != NULL)           XMLString::release(&pc_rendaMensal);
	if (pc_contaCorrent != NULL)          XMLString::release(&pc_contaCorrent);
	if (pc_banco != NULL)                 XMLString::release(&pc_banco);
	if (pc_IE != NULL)                    XMLString::release(&pc_IE);
	if (pc_nomeSufixo != NULL)            XMLString::release(&pc_nomeSufixo);
	if (pc_primeiroNome != NULL)          XMLString::release(&pc_primeiroNome);
	if (pc_sobreNome != NULL)             XMLString::release(&pc_sobreNome);
	if (pc_nomeContato != NULL)           XMLString::release(&pc_nomeContato);
	if (pc_carteiraTrabalho != NULL)      XMLString::release(&pc_carteiraTrabalho);
	if (pc_endereco != NULL)              XMLString::release(&pc_endereco);
	if (pc_complemento != NULL)           XMLString::release(&pc_complemento);
	if (pc_bairro != NULL)                XMLString::release(&pc_bairro);
	if (pc_CEP != NULL)                   XMLString::release(&pc_CEP);
	if (pc_cidade != NULL)                XMLString::release(&pc_cidade);
	if (pc_estado != NULL)                XMLString::release(&pc_estado);
	if (pc_pais != NULL)                  XMLString::release(&pc_pais);
	if (pc_logradouro != NULL)            XMLString::release(&pc_logradouro);
	if (pc_numero != NULL)                XMLString::release(&pc_numero);
	if (pc_InCnl != NULL)                 XMLString::release(&pc_InCnl);
	if (pc_InCodigoIBGE != NULL)          XMLString::release(&pc_InCodigoIBGE);
	if (pc_inTelContato != NULL)          XMLString::release(&pc_inTelContato);


	// Chama o serviço e verifica o retorno
	po_XMLfromGWNGIN = callRemoteAPI(this->getServiceName(), &o_XMLtoGWNGIN, XML_NGN_SVC_SET_CLIENT_INFO);
	
	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	
	if (po_XMLfromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);



	// Objeto para armazenar a resposta
	DOMNode *po_response = NULL;

	// Verifica a tag 'response'
	po_response = tuxhp->walkDOM(po_XMLfromGWNGIN, XML_NGN_OUT_RESPONSE, 0);
	
	if (po_response == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_RESPONSE, EMSG_NGN_OUT_TNE_RESPONSE);
	
	// trata erro genérico do NGIN
	this->trataErro(po_response);

	// Objeto para armazenar o corpo da resposta
	DOMNode *po_body = NULL;

	// Verifica a tag 'body'
	po_body = tuxhp->walkDOM(po_response, XML_NGN_OUT_BODY, 0);

	if (po_body == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_BODY, EMSG_NGN_OUT_TNE_BODY);



	xml_g->createTag(TAG_XML_OUT_CLIENTE_VO);

		pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}
	// Registro de contato
	this->registrarContato(xml_g,false);
	xml_g->closeTag();


}
