#include <tuxfw.h>

struct stMensagemSitef
{
	char idMensagemRecarga[10+1];
	char dsMensagemSitef[500+1];
	char dsMensagemTav[500+1];
 	char dtUltimaAlteracao[21];
	char nmLoginUsuario[255+1];
};

class CadastroMSGSitef
{
	public:
		CadastroMSGSitef();
		~CadastroMSGSitef();
		// Retorna todas as mensagens parametrizadas na tabela VOL.MENSAGEMRECARGA
		int getMensagens(XMLGen *xmlgen);
		// Adiciona uma nova mensagem na tabela
		int addMensagem(char*msgOrigem,char*msgDestino,char*login);
		// Atualiza uma mensagem na tabela por id
		int updateMensagem(char*idMensagemRecarga,char*msgDestino);
		// Remove a mensagem por id
		int deleteMensagem(char*idMensagemRecarga);

	private:

};