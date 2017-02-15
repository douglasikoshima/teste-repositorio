/**
	Executa as funções de associção de campanha no discador.
 
	@modulo  Campanha
	@usecase Função de associação de campanhas no discador.
	@author  Renato Teixeira
	@version $Revision: 1.1 $
	@CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:42 $
*/


#include "../include/cAssocCampanha.h"

cAssocCampanha::cAssocCampanha() {
}

/**
	Busca os dados da campanha, gera o arquivo de carga e monta o link
	para a recuperação do arquivo pelo sistema cliente.
*/
void cAssocCampanha::associaCampanha(int idSubCampanhaHistorico)
{

	st_DadosCampanha dadosCampanha;

	cAssocCampanhaPC* bd = new cAssocCampanhaPC();

	dadosCampanha = bd->buscaDadosCampanha(idSubCampanhaHistorico);

	char* endereco = geraArquivo(idSubCampanhaHistorico);

	strcpy(dadosCampanha.fileName, montaLinkFTP( endereco ));
	dadosCampanha.npu = 1;

	/**
		Rotina de integração entre FO e o discador !!!
	*/

	// Carrega a classe de comunicação com o sistema de EAI.
	AssociaContatosCampanha* acc = new AssociaContatosCampanha();

	acc->setIdCampanha(dadosCampanha.idCampanha);
	acc->setHoraLigacoesInicio(dadosCampanha.horaInicioLigacoes);
	acc->setHoraLigacoesFim(dadosCampanha.horaFimLigacoes);
	acc->setDataHoraCampanhaInicio(dadosCampanha.dataHoraInicioCampanha);
	acc->setDataHoraCampanhaFim(dadosCampanha.dataHoraFimCampanha);
	acc->setNumeroTentativas(dadosCampanha.numeroTentativas);
	acc->setIntervaloTentativa(dadosCampanha.intervaloEntreTentativas);
	acc->setFileName(dadosCampanha.fileName);

	// Executa a chamada para o servidor de integração
	try
	{
		acc->enviar();
	}
    catch (TuxException e)      
    {
		ULOGI("Erro ao tentar associar campanha no discador...");
        throw e;
    }

	/*******************************************************/

	delete bd;
	delete acc;

}

/**
	Busca os usuários associados ao grupos da campanha e faz a chamada 
	ao discador de associação do usuário com a campanha.
*/
void cAssocCampanha::associaUsuariosCampanha(int idSubCampanhaHistorico)
{

	cAssocCampanhaPC* bd = new cAssocCampanhaPC();

	Collection* usuariosCampanha = bd->buscaDadosUsuarioCampanha(idSubCampanhaHistorico);

	/**
		Rotina de integração entre FO e o discador !!!
	*/
 	for (int i = 0; i < usuariosCampanha->GetCount(); i++)
	{

		// Carrega a classe de comunicação com o sistema de EAI.
		AssociaUsuarioCampanha* auc = new AssociaUsuarioCampanha();
		AssociaUsuario* au = (AssociaUsuario*)usuariosCampanha->GetItem(i);

		auc->setNPU(1);
		auc->setIdCampanha(idSubCampanhaHistorico);
		auc->setIdUsuario(au->idPessoaUsuario);
		auc->setLoginUsuarioCTI(au->nmLoginUsuarioCTI);

		// Executa a chamada para o servidor de integração
		try
		{
			auc->enviar();
		}
		catch (TuxException e)      
		{
			ULOGI("Problemas ao tentar associar usuário a campanha...");
			throw e;
		}

		delete auc;
	}

	/*******************************************************/

	delete bd;
	delete usuariosCampanha;

}

/**
	Gera o arquivo de carga do discador.
*/
char* cAssocCampanha::geraArquivo(int idSubCampanhaHistorico)
{

	char  nomeArquivo[256];
	char* pathRegistro = 0;
	char* endereco = 0;
	
	nomeArquivo[0] = 0;

	cAssocCampanhaPC* bd = new cAssocCampanhaPC();

	Collection* registros = bd->buscaDadosCarga(idSubCampanhaHistorico);

	// Captura o valor da variavel de ambiente que indica onde será registrado o arquivos.
	pathRegistro = getenv("CC_FTP_PATH");
		
	if (pathRegistro == NULL)
	{
		throw TuxException(CC_ERRO_PATH_NAO_ENCONTRADO,"A variável CC_FTP_PATH não foi encontrada para registrar o arquivo de contatos.");
	}

	// Gera o nome do arquivo.
	int tamanho = sprintf( nomeArquivo, "CargaContatoCampanha_%d.txt", idSubCampanhaHistorico);
	nomeArquivo[tamanho] = 0;

	strcat(pathRegistro, nomeArquivo);		
	
	// Cria o arquivo fisicamente.
	FILE* arquivo = criaAquivo(pathRegistro);

	registraContatos(arquivo, registros);

	// Fecha o arquivo gerado.
	fclose(arquivo);

	delete bd;
	delete registros;

	return pathRegistro;
}

void cAssocCampanha::registraContatos(FILE* arquivo, Collection* registros)
{

	RegistroContato* rc;

	for (int i = 0; i < registros->GetCount(); i++)
	{

		rc = (RegistroContato*) registros->GetItem(i);


		fprintf( arquivo, "%d|%d||||\n", rc->idListaConteudo, rc->nrTelefone); 
	}

}


/**
	Essa função é responsável por criar um arquivo onde será escrita a lista de chamadas.
*/
FILE* cAssocCampanha::criaAquivo(char* novoArquivo)
{

	FILE* arquivo;

	arquivo = fopen(novoArquivo ,"w+");

	if (arquivo == NULL)
		throw TuxException(CC_ERRO_CRIAR_ARQUIVO, "Erro ao criar o arquivo de carga das chamadas da campanha.");

	return arquivo;
}

char* cAssocCampanha::montaLinkFTP(char* endereco)
{

	char* serverName = getenv("CC_SERVER_NAME");
		
	if (serverName == NULL)
	{
		throw TuxException(CC_ERRO_IDENTIFICACAO_SERVIDOR,"Não foi possível identificar o servidor de origem.");
	}

	char* linkFTP;

	linkFTP = (char*) malloc( 6 + strlen(serverName) + strlen(endereco) + 1 );

	sprintf( linkFTP, "ftp://%s%s", serverName, endereco);

	return linkFTP;

}