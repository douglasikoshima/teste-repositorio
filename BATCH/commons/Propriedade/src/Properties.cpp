/**
 * Implementa as funcionalidades da classe Propriedade.
 *
 * @author Renato Teixeira
 */

#include "../include/Propriedade.h"
#include "../../SplitLine.h"

Propriedade::Propriedade(char* _nomeArquivo)
{
/*
	log.setNivel( NIVEL_LOG );

	log.logDebug( "Iniciando Propriedade..." );
*/

	nomeArquivo = _nomeArquivo;

	openFile();

	loadAttributes();

/*
	log.logDebug( "PRONTO - Propriedade iniciada..." );
*/
}

Propriedade::Propriedade(char* _nomeArquivo, int _nivelLog)
{
/*
	log.setNivel( _nivelLog );

	log.logDebug( "Iniciando Propriedade..." );
*/

	nomeArquivo = _nomeArquivo;

	openFile();

	loadAttributes();

/*
	log.logDebug( "PRONTO - Propriedade iniciada..." );
*/
}

/**
Sempre que for usado o construtor padr�o, deve-se chamar o m�todo "setOrigem(char* _nomeArquivo)",
caso contr�rio, a classe n�o tera como responde as demais requisi��es.
*/
Propriedade::Propriedade()
{
/*
	log.setNivel( NIVEL_LOG );
*/

	nomeArquivo = 0;

/*
	log.logDebug( "Usado o construtor padr�o." );
*/
}


Propriedade::~Propriedade()
{
/*
	log.logDebug( "Destruindo propriedade..." );
*/

	Atributo* atributo;

	for ( int i = 0; i < collection.GetCount(); i++ )
	{
		atributo = (Atributo*) collection.GetItem( i );

/*
		log.logDebug( "Propriedade - Limpando atributo..." );
*/

		atributo->~Atributo();
	}

/*
	log.logDebug( "PRONTO - Propriedade iniciada..." ); 
*/
}

/** 
Esse m�todo � respons�vel pela carga dos atributos quando a constru��o da 
classe passe pelo construtor padr�o.
*/
void Propriedade::setOrigem(char* _nomeArquivo)
{

/*
	log.logDebug( "Iniciando Propriedade via setOrigem..." );
*/

	nomeArquivo = _nomeArquivo;

	openFile();

	loadAttributes();

/*
	log.logDebug( "PRONTO - Propriedade iniciada via setOrigem..." );
*/
}

/** 
Esse m�todo � responsavel por oferecer acesso ao controle de n�vel de log do processo.
*/
void Propriedade::setNivelLog(int _nivelLog)
{

/*
	log.logDebug( "Setando nivel de log do modulo de propriedade..." );
*/

	log.setNivel( _nivelLog );

/*
	log.logDebug( "PRONTO - Setado nivel de log do modulo de propriedade..." );
*/
}

void Propriedade::openFile()
{
/*
	log.logDebug( "Propriedade - Executando openFile()..." );
*/
	
	mfConfig.setPath(nomeArquivo);

	if(!mfConfig.abrir()) {
/*
		log.logError("N�o foi poss�vel abrir o arquivo de configura��o...");
*/
		throw("N�o foi poss�vel abrir o arquivo de configura��o...");
	}
}

void Propriedade::loadAttributes()
{

/*
	log.logDebug( "Propriedade - Carregando propriedades do arquivo..." );
*/

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

			collection.AddItem( atributo );
		}

	}

/*
	log.logDebug( "PRONTO - Propriedade - Carregandos propriedades do arquivo..." );
*/
}

/** 
	Retorna o valor do atributo buscado.
	Caso o atributo buscado n�o for encontrado ser� retornado o valor null.

	Quando a classe for construida pelo construtor padr�o e n�o executado o m�todo
	setOrigem, todos retornos desse m�todo ser�o nulos.
*/
char* Propriedade::getParametro(char* _atributo)
{

/*
	log.logDebug( "Propriedade - Obtendo parametro..." );
*/

	if (!nomeArquivo)
	{
/*
		log.logDebug( "Propriedade - Nao foi executado o metodo setOrigem ou aconteceu um erro..." );
*/
		return 0;
	}

	Atributo* atributo;

	for ( int i = 0; i < collection.GetCount(); i++ )
	{
		atributo = (Atributo*) collection.GetItem( i );

/*
		if (log.getNivel() == DEBUG)
		{
			log.logDebug( "Propriedade - Testando Atributo..." );
		}
*/

		if (strcmp(atributo->getNomeAtributo(), _atributo) == 0)
		{
			return atributo->getValorAtributo();
		}
	}

/*
	log.logDebug( "Propriedade - N�o foi encontrado o parametro..." );
*/

	return NULL;
}
