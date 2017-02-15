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

	// Métodos de acesso aos atributos
 	// Getters
	int		getIdBanco();
	int		getIdTipoBanco();
	char *  getNmBanco();
	char *  getDsURLBanco();

 	// Setters
	void setIdBanco(int value);
	void setIdTipoBanco(int value);
	void setNmBanco(char *value);
	void setDsURLBanco(char *value);

	// Operação de Negocio (Interface)
	static void consultarBancos(int iIdTipoBanco,
                                list< CBanco > & listaBanco);
	
private:
  
	// Dados de Negocio
	int  m_iIdBanco;
	int  m_iIdTipoBanco;
	char m_cNmBanco[256];
	char m_cDsURLBanco[256];

    // Métodos de acesso a banco de dados
    static void carregarBancosDB(int iIdTipoBanco,
                                 list<CBanco> & listaBanco);
};

#endif // CBANCO
