#ifndef REGISTROCONTATO
	#define REGISTROCONTATO


/**
	Essa classe é usada para registrar os contatos na collection.
*/

class RegistroContato
{

	public:

		RegistroContato();
		~RegistroContato();

		int   idListaConteudo;
		int   nrTelefone;

};

class AssociaUsuario
{

	public:

		AssociaUsuario();
		~AssociaUsuario();

		int   idPessoaUsuario;
		char  nmLoginUsuarioCTI[256];

};

#endif

