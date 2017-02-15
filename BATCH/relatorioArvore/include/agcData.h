// agcData.h

#ifndef _AGIT_DLLS_agcData_h_INCLUIDO_
#define _AGIT_DLLS_agcData_h_INCLUIDO_

/* 
	Nesta vers�o o campo "m_bOK" foi suprimido.
	Isto porque a vari�vel m_iDiaCorrido 
	tamb�m pode ser usada como "flag".
	Pois, como n�o existe o dia corrido "zero",
	podemos fazer m_iDiaCorrido = 0
	para assinalar uma Data como incorreta.
*/
class agcData
{
	private:	
		// ======== membros de dados:

		// ====	atributos de uso direto:

		char  m_cDia		;
		char  m_cMes		;
		short m_shAno		;		

		// ==== vari�veis auxiliares para valida��o e c�lculo

		short m_shAnoMin	;
		short m_shAnoMax	;
		int	  m_iDiaCorrido ;

		// ==== membros est�ticos:

		// = abaixo, a vari�vel "m_dtHoje" servir�
		//	 para armazenar o "dia de hoje" em cada
		//	 chamada � fun��o HojeCalc();
		//	 esse membro deve ser EST�TICO
		//	 j� que o "dia de hoje" � �nico,
		//   tratando-se portanto de uma vari�vel
		//   que ter� tempo de vida global
		//   e que N�O ser� instanciada para cada
		//   objeto da classe que venha a ser criado:
		static agcData m_dtHoje;

		/*	abaixo, uma matriz 
			que armazena os dias acumulados
			do in�cio do ano at� o fim de cada m�s;
			ser� usada para simplificar c�lculos 
			de soma e subtra��o entre agcDatas
			(� um membro EST�TICO e CONSTANTE, j� que
			 seus valores s�o �nicos e, al�m disso,
			 n�o ser�o alterados ap�s a inicializa��o):
		*/
		static const short m_shDiasAcumAteMes[13];

	public:
		
		// ==== Constantes padronizadas da classe:

		/*	constantes para estabelecer qual � o dia da semana 
			que deve ser considerado como "primeiro dia da semana", 
			sendo a base para c�lculo do "dia da semana" de uma Data:
			(as constantes s�o baseadas no dia 1/1/0000, s�bado)
		*/
		enum {  DS_INISEGUNDA=4, 
				DS_INIDOMINGO=5, // domingo ser� usado como "default"
				DS_INISABADO =6
			};

		/*	valores "default" para anos m�nimo e m�ximo
			usados para validar o ano de cada Data: 
		*/
		enum { ANO_MIN_DEF=1 , ANO_MAX_DEF=9999};

		/*	meses de corte para c�lculo
			do �ltimo dia de cada m�s:
		*/
		enum { FEVEREIRO = 2, JULHO = 7 };

		// ==== Construtoras:

		/*	construtora "default", pois
			admite chamada sem par�metros:
		*/
		agcData( short shAnoMin = ANO_MIN_DEF, 
			  short shAnoMax = ANO_MAX_DEF ); 
		
		/*	a construtora abaixo
			recebe dia, m�s e ano,
			podendo receber tamb�m os valores
			m�nimo e m�ximo para ano:
		*/
		agcData( char cDia, char cMes, short shAno,
				short shAnoMin = ANO_MIN_DEF, 
				short shAnoMax = ANO_MAX_DEF);

		/*	a construtora abaixo recebe um
			n�mero de dia corrido desde 01/01/0000
			e calcula dia, m�s e ano a partir dele
			(e N�O permitir� convers�es IMPL�CITAS
			 de "int" para "agcData"):
		*/
		explicit agcData( int iDiaCorrido );
		
		/*	a construtora abaixo servir� para
			simplificar soma e subtra��o de dias
			(usada pelos operadores +(int) e -(int));
			ela recebe uma refer�ncia a uma Data 
			e uma quantidade de dias a somar ou subtrair 
			a partir dessa Data de Origem:
		*/
		agcData( const agcData & dtOrigem, int nDiasSomar);

	public:
		// ==== M�todos:

		//	Altera uma Data a partir de Dia, M�s e Ano:
		void Altera ( char cDia, char cMes, short shAno);

		/*	Altera Anos m�nimo e m�ximo
			usados para valida��o do Ano de cada Data:
		*/
		inline void AnoMinMax(short shAnoMin, short shAnoMax);

		// ==== operadores de soma e subtra��o:

		/*	subtrai uma quantidade de dias da Data
			e retorna a Data calculada:
		*/
		inline agcData operator - (int nDias) const ;

		/*	subtrai uma Data desta 
			e retorna a diferen�a em dias:
		*/
		inline int  operator - (const agcData & dt2) const ;

		/*	soma uma quantidade de dia � agcData
			e retorna a Data calculada:
		*/
		inline agcData operator + (int nDias) const ;
		
		/*	subtrai uma quantidade de dias da Data,
			alterando-a, e retorna o dia corrido:
		*/
		inline int operator -= (int nDias) ;

		/*	soma uma quantidade de dias � Data,
			alterando-a, e retorna o dia corrido:
		*/
		inline int operator += (int nDias) ;

		// === Operadores de incremento e decremento:

		inline int operator ++()   ; // PREfixado
		inline int operator --()   ; // PREfixado
		inline int operator ++(int); // POSfixado
		inline int operator --(int); // POSfixado

		// ==== operadores relacionais:

		inline bool operator == (const agcData & dt2) const ;
		inline bool operator != (const agcData & dt2) const ;
		inline bool operator  < (const agcData & dt2) const ;
		inline bool operator <= (const agcData & dt2) const ;
		inline bool operator  > (const agcData & dt2) const ;
		inline bool operator >= (const agcData & dt2) const ;
		
		// ==== retornam atributos:

		inline char  Dia() const { return m_cDia ; } // dia
		inline char  Mes() const { return m_cMes ; } // m�s
		inline short Ano() const { return m_shAno ; }// Ano

		// = agcData est� correta? (true/false):
		inline bool OK () const { return m_iDiaCorrido>0; }

		/*	Quantidade de dias corridos entre a Data
			e o dia 01/01/0000:
		*/
		inline int DiaCorrido() const { return m_iDiaCorrido; }

		//	retorna �ltimo dia de cada m�s:
		inline char UltimoDiaMes() const;

		//	retorna se o ano � bissexto(true/false):
		inline bool AnoBissexto() const;

		//	==== calculam e retornam informa��es sobre a Data:
		
		/*	Dia da semana (1 a 7), contado
			a partir de domingo(default), segunda ou s�bado,
			sabendo-se que o dia 01/01/0000 foi s�bado:
		*/
		inline int DiaSemana(int DiaIni=DS_INIDOMINGO) const;

		/*	retorna a quantidade de dias dos 
			Anos anteriores ao Ano da Data:
		*/
		inline int DiasAnosAnteriores() const;
		
		/*	retorna a quantidade de dias acumulados
			do in�cio do Ano at� o fim de um determinado m�s:
		*/
		inline int DiasAcumAteMes(int nMes);

		/*	Obter a Data do sistema, armazenar essa Data
			na est�tica private "m_Hoje"
			e retornar uma refer�ncia para "m_Hoje":
		*/

		//	obtem a Data e retorna uma refer�ncia:
		static agcData & HojeCalc();
		//	apenas retorna Data do sistema j� obtida:
		static inline agcData & Hoje() {return m_dtHoje;}

		//	imprime dia/m�s/ano:
		void Imprime() const;
        
        //  Retorna conteudo data invertido
        void Get( char * sData ) const;


	private:

		// ==== fun��es auxiliares internas:

		/*	analisa se a Data est� correta
			sempre que � alterada posicionando
			m_iDiaCorrido para zero, caso errada:
		*/
		inline void _Valida();

		//	soma/subtrai uma quantidade de dias a uma Data:
		void _SomaDias(int nDiasSomar); 

		/*	incrementa (+1) uma Data 
			(usada pelos 2 operadores ++):
		*/
		void _Incrementa();

		/*	decrementa (-1) uma Data 
			(usada pelos 2 operadores --):
		*/
		void _Decrementa();
		
};  // FIM da declara��o da classe agcData


// ==== IMPLEMENTA��O DAS FUN��ES INLINE P�BLICAS:


/*	Altera anos m�nimo e m�ximo
	usados para valida��o do ano de cada Data:
*/
inline void agcData::AnoMinMax(short shAnoMin, short shAnoMax)
{
	m_shAnoMin = shAnoMin;
	m_shAnoMax = shAnoMax;
}

/*	retorna a quantidade de dias dos 
	anos anteriores ao ano desta Data:
*/
inline int agcData::DiasAnosAnteriores() const
{
	// multiplica o ano por 365 dias
	// e soma a quantidade de bissextos
	// at� o ano anterior:
	int Ant=m_shAno-1;
	return m_shAno*365 + int(Ant/4) 
					   - int(Ant/100) + int(Ant/400)+1;
}

/*	retorna a quantidade de dias acumulados
	do in�cio do ano at� o fim de um determinado m�s:
*/
inline int agcData::DiasAcumAteMes(int nMes)
{
	return m_shDiasAcumAteMes[nMes] + 
						(AnoBissexto() && nMes>1);
	//+1 se Ano for bissexto e m�s superior a janeiro
}

/*	Ano � bissexto? (true/false)
*/
inline bool agcData::AnoBissexto() const
{
// par ser bissexto, deve ser divis�vel por 4 
// [ !(m_shAno & 3) => mais r�pido que !(m_shAno%4) ]

// E N�O ser divis�vel por 100 [ m_shAno%100 ]
// EXCETO quando for divis�vel por 400 [ !(m_shAno%400) ]
	
	return ( !(m_shAno & 3) && 
				( (m_shAno % 100) || !(m_shAno % 400) ) );

// As compara��es acima s�o efetuadas do caso 
// mais frequente para o menos frequente.
}

/*	�ltimo dia do m�s em uso:
*/
inline char agcData::UltimoDiaMes() const
{
	return(m_cMes==FEVEREIRO)? 28 + AnoBissexto()
							 : 30 + ( (m_cMes&1)^(m_cMes>JULHO) );
}
/* 
Demonstra��o da fun��o acima:

Se FEVEREIRO: 28 + 0 = 28 <<- Anos n�o bissextos
              28 + 1 = 29 <<- Anos bissextos
			  
De janeiro a julho : meses �mpares t�m 31 dias, pares t�m 30.
De julho a dezembro: meses �mpares t�m 30 dias, pares t�m 31.

Se �mpar ( mes & 1 ) ->> 1; 
Se par   ( mes & 1 ) ->> 0;

De janeiro a julho    ->> ( mes > JULHO ) ->> 0;
De agosto  a dezembro ->> ( mes > JULHO ) ->> 1; 

Ent�o basta fazer: 30 + (( impar ) XOR ( >JULHO ))

EXEMPLOS:
junho-    mes 6 ->> 30 + ( 0 ^ 0 ) ->> 30 + 0 ->> 30
julho-    mes 7 ->> 30 + ( 1 ^ 0 ) ->> 30 + 1 ->> 31
agosto-   mes 8 ->> 30 + ( 0 ^ 1 ) ->> 30 + 1 ->> 31
setembro- mes 9 ->> 30 + ( 1 ^ 1 ) ->> 30 + 0 ->> 30

Veja tamb�m o �tem "Operadores de bit" 
no cap�tulo "Operadores" e a demonstra��o l� feita
de uma fun��o "UltimoDiaMes".
*/


/*	n�mero do dia na semana, 
	contado a partir de "DiaIni":
*/
inline int agcData::DiaSemana(int DiaIni)  const
{
	return int(( m_iDiaCorrido+DiaIni)%7)+1;

// se DiaIni for: 
//DS_INIDOMINGO: (default) -> domingo=1, segunda=2, ..., s�bado =7;
//DS_INISEGUNDA: (Segunda) -> segunda=1, ter�a  =2, ..., domingo=7;
//DS_INISABADO : (S�bado ) -> s�bado =1, domingo=2, ..., sexta  =7;
}

/*	subtrai uma Data(passada no par�metro expl�cito)
	e retorna a diferen�a em dias:
*/
inline int agcData::operator - (const agcData & dt2) const
{
	return m_iDiaCorrido - dt2.m_iDiaCorrido ;
}

/*	subtrai uma quantidade de dias
	e retorna a Data resultante:
*/
inline agcData agcData::operator - (int nDias) const
{
	return agcData( *this, -nDias );
}

/*	soma uma quantidade de dias
	e retorna a Data resultante:
*/
inline agcData agcData::operator + (int nDias)  const
{
	return agcData( *this, nDias );
}

/*	altera a Data, subtraindo uma quantidade de dias,
	e retorna o dia corrido:
*/
inline int agcData::operator -= (int nDias) 
{
	_SomaDias( -nDias );
	return m_iDiaCorrido;
}

/*	altera a Data, somando uma quantidade de dias,
	e retorna o dia corrido:
*/
inline int agcData::operator += (int nDias) 
{
	_SomaDias ( nDias);
	return m_iDiaCorrido;
}

/*	Incrementa a Data (PREfixado: ++Var) :
*/
inline int agcData::operator ++() 
{
	_Incrementa(); // sendo prefixado: incrementa
	return m_iDiaCorrido; // retorna resultado
}

/*	Decrementa a Data (PREfixado: --Var): 
*/
inline int agcData::operator --()
{
	_Decrementa(); // sendo prefixado: decrementa
	return m_iDiaCorrido; // retorna resultado;
}

/*	Incrementa a Data (POSfixado: Var++):
*/
inline int agcData::operator ++(int)
{
	int iTemp = m_iDiaCorrido; // salva valor atual;
	_Incrementa(); //incrementa
	return iTemp; // retorna valor ANTERIOR ao incremento 
					// (pois � posfixado)
}

/*	Decrementa a Data (POSfixado: Var--):
*/
inline int agcData::operator --(int)
{	
	int iTemp = m_iDiaCorrido; //salva
	_Decrementa(); // decrementa
	return iTemp; // retorna valor ANTERIOR ao decremento
					// (pois � posfixado)
}

// ======== Operadores Relacionais:

// ==== igualdade
inline bool agcData::operator == ( const agcData & dt2) const
{
	return ( m_iDiaCorrido == dt2.m_iDiaCorrido);
}

// ==== n�o igual (diferente de)
inline bool agcData::operator != (const agcData & dt2) const
{
	return ( m_iDiaCorrido != dt2.m_iDiaCorrido);	
}

// ==== menor que:
inline bool agcData::operator  < (const agcData & dt2) const
{
	return ( m_iDiaCorrido < dt2.m_iDiaCorrido);	
}

// ==== menor ou igual que:
inline bool agcData::operator <= (const agcData & dt2) const
{
	return ( m_iDiaCorrido <= dt2.m_iDiaCorrido);	
}

// ==== maior que:
inline bool agcData::operator  > (const agcData & dt2) const
{
	return ( m_iDiaCorrido > dt2.m_iDiaCorrido);	
}

// ==== maior ou igual que:
inline bool agcData::operator >= (const agcData & dt2) const
{
	return ( m_iDiaCorrido >= dt2.m_iDiaCorrido);	
}

#endif // ( #ifndef _AGIT_DLLS_agcData_h_INCLUIDO_ )
