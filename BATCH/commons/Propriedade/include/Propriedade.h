/**
 * Essa classe provem uma forma fácil de acesso a arquivos de pares de configuração.
 * 
 * Os arquivos podem conter comentários, desde que a linha seja iniciada pelo caracter '#'.
 * 
 * Todos atributos devem ser separados por um sinal de igualdade '='.
 * 
 */

#ifndef HPROPRIEDADE
#define  HPROPRIEDADE

#include "MFile.h"
#include "Atributo.h"
#include "../../Collection/include/Collection.h"
#include "../../Log/include/Log.h"

#define NIVEL_LOG ERROR

class Propriedade
{

public:

	Propriedade( );
	Propriedade( char* _nomeArquivo );
	Propriedade( char* _nomeArquivo, int _nivelLog );

	~Propriedade();
		
	void  setOrigem(char* _nomeArquivo);
	char* getParametro(char* _atributo);

	void  setNivelLog(int _nivelLog);

private:

	Log		log;

	MFile		mfConfig;
	Collection	collection;

	char* nomeArquivo;

	void openFile();
	void loadAttributes();

};

#endif

