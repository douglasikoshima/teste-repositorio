#define STRINGCURTA		15
#define STRINGLONGA		255
#define CLAUSULAWHERE	1000

//relatorios
#define REL_RETENCAO			1
#define REL_MOV_DIARIAS			2
#define REL_TODAS_OFERTAS		3
#define REL_RES_DESTINO			4
#define REL_RET_OFERTA			5
#define REL_RES_DEST_DISTINCAO	6
#define REL_CTRL_CREDITO		7
#define REL_OPERADOR			8
#define REL_LIG_RETENCAO		9
#define REL_RETENCAODISTINCAO	10
#define REL_RET_PLANOS			11
#define REL_RET_PLAN_DISTINCAO	12
#define REL_OFERTAS_TOT_MENSAL	13
#define REL_OFERTAS_DIA_PONTOS	14
#define REL_NOTES_LOJA			15

struct Filtro{
	char  szDataIni[STRINGCURTA];		//Data Inicio
	char  szDataFim[STRINGCURTA];		//Data Fim
	char  szClassificacao[STRINGCURTA];	//id da Segmentação
	char  szGrupo[STRINGCURTA];			//id do Grupo
	char  szCustoPonto[STRINGCURTA];	//Valor em reais do ponto
	char  szOferta[STRINGCURTA];		//id da Oferta
	char  szTipoCliente[STRINGCURTA];	//id do Tipo de Cliente
	char  szOperadora[STRINGLONGA];		//id da Oferta
	char  szPlano[STRINGCURTA];			//???	
	char  szLogin[STRINGCURTA];			//login usuario
};


//Para todos os relatórios será necessário que
//os campos de filtro sejam sempre os mesmos
