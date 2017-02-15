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
Sempre que for usado o construtor padrão, deve-se chamar o método "setOrigem(char* _nomeArquivo)",
caso contrário, a classe não tera como responde as demais requisições.
*/
Propriedade::Propriedade()
{
/*
	log.setNivel( NIVEL_LOG );
*/

	nomeArquivo = 0;

/*
	log.logDebug( "Usado o construtor padrão." );
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
Esse método é responsável pela carga dos atributos quando a construção da 
classe passe pelo construtor padrão.
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
Esse método é responsavel por oferecer acesso ao controle de nível de log do processo.
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
		log.logError("Não foi possível abrir o arquivo de configuração...");
*/
		throw("Não foi possível abrir o arquivo de configuração...");
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
	Caso o atributo buscado não for encontrado será retornado o valor null.

	Quando a classe for construida pelo construtor padrão e não executado o método
	setOrigem, todos retornos desse método serão nulos.
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
	log.logDebug( "Propriedade - Não foi encontrado o parametro..." );
*/

	return NULL;
}
