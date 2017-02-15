/**
 * Essa classe provem uma forma f�cil de acesso a arquivos de pares de configura��o.
 * 
 * Os arquivos podem conter coment�rios, desde que a linha seja iniciada pelo caracter '#'.
 * 
 * Todos atributos devem ser separados por um sinal de igualdade '='.
 * 
 */

#ifndef HLerDados
#define  HLerDados

#include "MFile.h"
#include "Atributo.h"
#include "../../Collection/include/Collection.h"
#include "../../Log/include/Log.h"

#define NIVEL_LOG ERROR

class LerDados
{

public:

	LerDados( );
	LerDados( char* _nomeArquivo );
	LerDados( char* _nomeArquivo, int _nivelLog );

	~LerDados();
		
	void  setOrigem(char* _nomeArquivo);
	char  *getParametro(char* _atributo);
	void  setNivelLog(int _nivelLog);
	int   CarregaLista (Collection	*pcollection,char *_atributo);
	char  *getConteudoLista( Collection	*pcollection ,int  ind );
	char  *getConteudoListaContato( int  ind );
	int   getNumeroContato( void );
	void  DestroiList( Collection	*pcollection);
	void   close();
    void   remove();

	

private:

	Log		log;

	MFile		mfConfig;
	Collection	collection;
	Collection	collectionContatos;



	char* nomeArquivo;

	void openFile();
	void loadAttributes();

};

#endif

