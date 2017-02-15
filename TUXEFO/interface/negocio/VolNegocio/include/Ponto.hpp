#ifndef PONTOSH
#define PONTOSH

class CPonto 
{

public:
	
	CPonto();
	virtual ~CPonto();
	
	void consultarExtrato(int iCdAreaRegistro,
                          int iNrLinha);
	
	//Metodos de acessos aos atributos

	//Getters
	int		getNrLancamentos();
	char*	getXMLExtrato();
	char*	getUser();

	// Setters
	void	setNrLancamentos(int value);
	void    setUser(char *value);
	
private:
	
	int		m_iNrLancamentos;
	char*	m_pxmlExtrato;
	char	m_cUser[ 128 ];
};

#endif
