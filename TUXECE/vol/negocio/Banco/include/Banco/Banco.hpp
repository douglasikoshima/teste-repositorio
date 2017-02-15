// Banco.h: interface for the CBanco class.
//
//////////////////////////////////////////////////////////////////////

#ifndef CBANCO
#define CBANCO

#include <list>
using namespace std;

class CBanco
{
public:
    CBanco();
    virtual ~CBanco();

	// M�todos de acesso aos atributos
 	// Getters
	int		getIdBanco();
	int		getIdTipoBanco();
	int		getInSiteaSite();
	char *  getNmBanco();
	char *  getDsURLBanco();

 	// Setters
	void setIdBanco(int value);
	void setIdTipoBanco(int value);
	void setNmBanco(char *value);
	void setDsURLBanco(char *value);
	void setInSiteaSite(int value);

	// Opera��o de Negocio (Interface)
	static void consultarBancos(int iIdTipoBanco,
                                list< CBanco > & listaBanco, int iCdAreaRegistro);
	// Opera��o de Neg�cio para inserir nova chave de acesso para pagamento de boleto
	void setTransacaoBanco(char*chave,char*codigoBarras);
	// Opera��o para recuperar a chave de transa��o do banco
	void getCodigoBarras(char*chave,char *codigoBarras);
	// Operacao para remover a chave de transa��o do banco
	void removeTransacaoBanco(char*chave);
	
private:
  
	// Dados de Negocio
	int  m_iIdBanco;
	int  m_iIdTipoBanco;
	int  m_inSiteaSite;
	char m_cNmBanco[256];
	char m_cDsURLBanco[256];

    // M�todos de acesso a banco de dados
    static void carregarBancosDB(int iIdTipoBanco,
                                 list<CBanco> & listaBanco, int iCdAreaRegistro);
};

#endif // CBANCO
