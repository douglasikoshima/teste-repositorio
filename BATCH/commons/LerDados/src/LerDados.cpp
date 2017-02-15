/**
 * Implementa as funcionalidades da classe LerDados.
 *
 * @author Renato Teixeira
 */

#include "../include/LerDados.h"
//#include "../../SplitLine.h"
#include "../include/SplitLine.h"

LerDados::LerDados(char* _nomeArquivo)
{
	log.setNivel( NIVEL_LOG );

	log.logDebug( "Iniciando LerDados..." );

	nomeArquivo = _nomeArquivo;

	openFile();

	loadAttributes();

	log.logDebug( "PRONTO - LerDados iniciada..." );
}

LerDados::LerDados(char* _nomeArquivo, int _nivelLog)
{
	log.setNivel( _nivelLog );

	log.logDebug( "Iniciando LerDados..." );

	nomeArquivo = _nomeArquivo;

	openFile();

	loadAttributes();

	log.logDebug( "PRONTO - LerDados iniciada..." );
}

/**
Sempre que for usado o construtor padrão, deve-se chamar o método "setOrigem(char* _nomeArquivo)",
caso contrário, a classe não tera como responde as demais requisições.
*/
LerDados::LerDados()
{
	log.setNivel( NIVEL_LOG );

	nomeArquivo = 0;

	log.logDebug( "Usado o construtor padrão." );
}

LerDados::~LerDados()
{
	log.logDebug( "Destruindo LerDados..." );

	Atributo* atributo;
    int i ;
	for (  i = 0; i < collection.GetCount(); i++ )
	{
		atributo = (Atributo*) collection.GetItem( i );

		log.logDebug( "LerDados - Limpando atributo..." );

		atributo->~Atributo();
	}

	

	for (  i = 0; i < collectionContatos.GetCount(); i++ )
	{
		atributo = (Atributo*) collectionContatos.GetItem( i );

		log.logDebug( "LerDados - Limpando atributo..." );

		atributo->~Atributo();
	}



	log.logDebug( "PRONTO - LerDados iniciada..." ); 
//    atributo->~Atributo();
}

/** 
Esse método é responsável pela carga dos atributos quando a construção da 
classe passe pelo construtor padrão.
*/
void LerDados::setOrigem(char* _nomeArquivo)
{

	log.logDebug( "Iniciando LerDados via setOrigem..." );

	nomeArquivo = _nomeArquivo;

	openFile();

	loadAttributes();

	log.logDebug( "PRONTO - LerDados iniciada via setOrigem..." );
}

/** 
Esse método é responsavel por oferecer acesso ao controle de nível de log do processo.
*/
void LerDados::setNivelLog(int _nivelLog)
{

	log.logDebug( "Setando nivel de log do modulo de LerDados..." );

	log.setNivel( _nivelLog );

	log.logDebug( "PRONTO - Setado nivel de log do modulo de LerDados..." );
}

void LerDados::openFile()
{
	log.logDebug( "LerDados - Executando openFile()..." );
	
	mfConfig.setPath(nomeArquivo);

	if(!mfConfig.abrir()) {
		log.logError("Não foi possível abrir o arquivo de entrada de dados, o sistema esta saido do ar ...");
		throw("Não foi possível abrir o arquivo de entrada de dados, o sistema esta saido do ar ...");
	}
}

void LerDados::close()
{
	log.logDebug( "Fechar Arquivo de entrada - Executando openFile()..." );

	if(!mfConfig.fechar()) {
		log.logError("Não foi possível fechar o arquivo de entrada de dados, o sistema esta saido do ar ...");
		throw("Não foi possível fechar o arquivo de entrada de dados, o sistema esta saido do ar ...");
	}
}

void LerDados::remove()
{
	log.logDebug( "Remove Arquivo de entrada - Executando openFile()..." );

	if(!mfConfig.removeFile()) {
		log.logError("Não foi possível remove o arquivo de entrada de dados, o sistema esta saido do ar ...");
		throw("Não foi possível remove o arquivo de entrada de dados, o sistema esta saido do ar ...");
	}
}

void LerDados::loadAttributes()
{

	log.logDebug( "LerDados - Carregando LerDadoss do arquivo..." );

	SplitLine separador;

	char linha[513];
	char nomeAtributo[256];
	char valorAtributo[256];

	Atributo* atributo;
			
	separador.SetDiv('=');
	while(mfConfig.getLine(linha) != 0)
	{
		separador.SetLine(linha);
		separador.GetBeforeDiv(nomeAtributo);
		separador.GetAfterDiv(valorAtributo);

		if (strlen(nomeAtributo) > 0)
		{
			nomeAtributo[ strlen(nomeAtributo) ] = 0;
			valorAtributo[ strlen(valorAtributo) ] = 0;

			atributo = new Atributo(nomeAtributo, valorAtributo);

			if( strcmp(nomeAtributo,"idContato") == 0 )
			{
			   collectionContatos.AddItem( atributo );
            } 

            else
			{
			   collection.AddItem( atributo );
            } 
		}

	}

	log.logDebug( "PRONTO - LerDados - Carregandos LerDadoss do arquivo..." );
}

//
// copia o conteudo do arquivo para lista passadada como parametro
//
int LerDados::CarregaLista(Collection	*pcollection,char *_atributo)
{

	log.logDebug( "LerDados - Obtendo parametro..." );

	if (!nomeArquivo)
	{
		log.logDebug( "LerDados - Nao foi executado o metodo setOrigem ou aconteceu um erro..." );
		return 0;
	}

	Atributo* atributo;

	for ( int i = 0; i < collection.GetCount(); i++ )
	{
		atributo = (Atributo*) collection.GetItem( i );

		if (log.getNivel() == DEBUG)
		{
			log.logDebug( "LerDados - Testando Atributo..." );
		}

		if (strcmp(atributo->getNomeAtributo(), _atributo) == 0)
		{
//		    pcollection->AddItem(atributo->getValorAtributo());
			pcollection->AddItem(atributo);
		}
	}

	log.logDebug( "LerDados - Não foi encontrado o parametro..." );

	return 1;
}


// retorna um valor da lista 

char* LerDados::getConteudoLista( Collection	*pcollection ,int  ind )
{

	log.logDebug( " > LerDados - Obtendo parametro..." );

	if (!nomeArquivo)
	{
		log.logDebug( " < LerDados - Nao foi executado o metodo setOrigem ou aconteceu um erro..." );
		return 0;
	}

	Atributo* atributo;

	atributo = (Atributo*) pcollection->GetItem( ind );

	if (log.getNivel() == DEBUG)
	{
		log.logDebug( "LerDados - Testando Atributo..." );
	}

	log.logDebug( "< LerDados - " );

	return atributo->getValorAtributo();



}

char* LerDados::getConteudoListaContato( int  ind )
{

	log.logDebug( " > LerDados - Obtendo parametro..." );

	if (!nomeArquivo)
	{
		log.logDebug( " < LerDados - Nao foi executado o metodo setOrigem ou aconteceu um erro..." );
		return 0;
	}

	Atributo* atributo;

	atributo = (Atributo*) collectionContatos.GetItem( ind );

	if (log.getNivel() == DEBUG)
	{
		log.logDebug( "LerDados - Testando Atributo..." );
	}

	log.logDebug( "< LerDados - " );

	return atributo->getValorAtributo();



}

int  LerDados::getNumeroContato( void )
{
	log.logDebug( "> getNumeroContato( void )" );
	return  collectionContatos.GetCount();
	log.logDebug( "PRONTO - LerDados iniciada..." ); 
}


// destroi o conteudo de uma lista

void LerDados::DestroiList( Collection	*pcollection)
{
	log.logDebug( "Destruindo LerDados..." );

	Atributo* atributo;

	for ( int i = 0; i < pcollection->GetCount(); i++ )
	{
		atributo = (Atributo*) pcollection->GetItem( i );

		log.logDebug( "LerDados - Limpando atributo..." );

		atributo->~Atributo();
	}



	log.logDebug( "PRONTO - LerDados iniciada..." ); 
}


/** 
	Retorna o valor do atributo buscado.
	Caso o atributo buscado não for encontrado será retornado o valor null.

	Quando a classe for construida pelo construtor padrão e não executado o método
	setOrigem, todos retornos desse método serão nulos.
*/
char* LerDados::getParametro(char* _atributo)
{

	log.logDebug( "LerDados - Obtendo parametro..." );

	if (!nomeArquivo)
	{
		log.logDebug( "LerDados - Nao foi executado o metodo setOrigem ou aconteceu um erro..." );
		return 0;
	}

	Atributo* atributo;

	for ( int i = 0; i < collection.GetCount(); i++ )
	{
		atributo = (Atributo*) collection.GetItem( i );

		if (log.getNivel() == DEBUG)
		{
			log.logDebug( "LerDados - Testando Atributo..." );
		}

		if (strcmp(atributo->getNomeAtributo(), _atributo) == 0)
		{
			return atributo->getValorAtributo();
		}
	}

	log.logDebug( "LerDados - Não foi encontrado o parametro..." );

	return NULL;
}


