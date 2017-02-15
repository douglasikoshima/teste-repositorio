#ifndef ENVIASMS
#define  ENVIASMS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "atmi.h"

#include "../../Log/include/Log.h"
#include "../../SmallString.h"
#include "../../Propriedade/include/MFile.h"
#include "../../Propriedade/include/Propriedade.h"

class EnviaSMS
{

	public:

		EnviaSMS();
		EnviaSMS( int _nivelLog );
		~EnviaSMS();

		void setMensagem(char* _mensagem);
		void setDestinatario(char* _destinatario);
		bool  enviar();

	private:

		char szAux[512];	// Variável receptora das mascaras para envio ao log.

		Log log;
		Propriedade prop;

		SmallString mensagem;
		SmallString destinatario;

		void conectaTuxedo();
		void montaXML(SmallString* _mensagem);


		char* genericPassword;
		char* userName;
		char* cltName;
		char* aplicationPassword;

};

#endif
