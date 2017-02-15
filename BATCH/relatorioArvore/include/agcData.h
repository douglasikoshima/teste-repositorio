// agcData.h

#ifndef _AGIT_DLLS_agcData_h_INCLUIDO_
#define _AGIT_DLLS_agcData_h_INCLUIDO_

/* 
	Nesta versão o campo "m_bOK" foi suprimido.
	Isto porque a variável m_iDiaCorrido 
	também pode ser usada como "flag".
	Pois, como não existe o dia corrido "zero",
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

		// ==== variáveis auxiliares para validação e cálculo

		short m_shAnoMin	;
		short m_shAnoMax	;
		int	  m_iDiaCorrido ;

		// ==== membros estáticos:

		// = abaixo, a variável "m_dtHoje" servirá
		//	 para armazenar o "dia de hoje" em cada
		//	 chamada à função HojeCalc();
		//	 esse membro deve ser ESTÁTICO
		//	 já que o "dia de hoje" é único,
		//   tratando-se portanto de uma variável
		//   que terá tempo de vida global
		//   e que NÃO será instanciada para cada
		//   objeto da classe que venha a ser criado:
		static agcData m_dtHoje;

		/*	abaixo, uma matriz 
			que armazena os dias acumulados
			do início do ano até o fim de cada mês;
			será usada para simplificar cálculos 
			de soma e subtração entre agcDatas
			(é um membro ESTÁTICO e CONSTANTE, já que
			 seus valores são únicos e, além disso,
			 não serão alterados após a inicialização):
		*/
		static const short m_shDiasAcumAteMes[13];

	public:
		
		// ==== Constantes padronizadas da classe:

		/*	constantes para estabelecer qual é o dia da semana 
			que deve ser considerado como "primeiro dia da semana", 
			sendo a base para cálculo do "dia da semana" de uma Data:
			(as constantes são baseadas no dia 1/1/0000, sábado)
		*/
		enum {  DS_INISEGUNDA=4, 
				DS_INIDOMINGO=5, // domingo será usado como "default"
				DS_INISABADO =6
			};

		/*	valores "default" para anos mínimo e máximo
			usados para validar o ano de cada Data: 
		*/
		enum { ANO_MIN_DEF=1 , ANO_MAX_DEF=9999};

		/*	meses de corte para cálculo
			do último dia de cada mês:
		*/
		enum { FEVEREIRO = 2, JULHO = 7 };

		// ==== Construtoras:

		/*	construtora "default", pois
			admite chamada sem parâmetros:
		*/
		agcData( short shAnoMin = ANO_MIN_DEF, 
			  short shAnoMax = ANO_MAX_DEF ); 
		
		/*	a construtora abaixo
			recebe dia, mês e ano,
			podendo receber também os valores
			mínimo e máximo para ano:
		*/
		agcData( char cDia, char cMes, short shAno,
				short shAnoMin = ANO_MIN_DEF, 
				short shAnoMax = ANO_MAX_DEF);

		/*	a construtora abaixo recebe um
			número de dia corrido desde 01/01/0000
			e calcula dia, mês e ano a partir dele
			(e NÃO permitirá conversões IMPLÍCITAS
			 de "int" para "agcData"):
		*/
		explicit agcData( int iDiaCorrido );
		
		/*	a construtora abaixo servirá para
			simplificar soma e subtração de dias
			(usada pelos operadores +(int) e -(int));
			ela recebe uma referência a uma Data 
			e uma quantidade de dias a somar ou subtrair 
			a partir dessa Data de Origem:
		*/
		agcData( const agcData & dtOrigem, int nDiasSomar);

	public:
		// ==== Métodos:

		//	Altera uma Data a partir de Dia, Mês e Ano:
		void Altera ( char cDia, char cMes, short shAno);

		/*	Altera Anos mínimo e máximo
			usados para validação do Ano de cada Data:
		*/
		inline void AnoMinMax(short shAnoMin, short shAnoMax);

		// ==== operadores de soma e subtração:

		/*	subtrai uma quantidade de dias da Data
			e retorna a Data calculada:
		*/
		inline agcData operator - (int nDias) const ;

		/*	subtrai uma Data desta 
			e retorna a diferença em dias:
		*/
		inline int  operator - (const agcData & dt2) const ;

		/*	soma uma quantidade de dia à agcData
			e retorna a Data calculada:
		*/
		inline agcData operator + (int nDias) const ;
		
		/*	subtrai uma quantidade de dias da Data,
			alterando-a, e retorna o dia corrido:
		*/
		inline int operator -= (int nDias) ;

		/*	soma uma quantidade de dias à Data,
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
		inline char  Mes() const { return m_cMes ; } // mês
		inline short Ano() const { return m_shAno ; }// Ano

		// = agcData está correta? (true/false):
		inline bool OK () const { return m_iDiaCorrido>0; }

		/*	Quantidade de dias corridos entre a Data
			e o dia 01/01/0000:
		*/
		inline int DiaCorrido() const { return m_iDiaCorrido; }

		//	retorna último dia de cada mês:
		inline char UltimoDiaMes() const;

		//	retorna se o ano é bissexto(true/false):
		inline bool AnoBissexto() const;

		//	==== calculam e retornam informações sobre a Data:
		
		/*	Dia da semana (1 a 7), contado
			a partir de domingo(default), segunda ou sábado,
			sabendo-se que o dia 01/01/0000 foi sábado:
		*/
		inline int DiaSemana(int DiaIni=DS_INIDOMINGO) const;

		/*	retorna a quantidade de dias dos 
			Anos anteriores ao Ano da Data:
		*/
		inline int DiasAnosAnteriores() const;
		
		/*	retorna a quantidade de dias acumulados
			do início do Ano até o fim de um determinado mês:
		*/
		inline int DiasAcumAteMes(int nMes);

		/*	Obter a Data do sistema, armazenar essa Data
			na estática private "m_Hoje"
			e retornar uma referência para "m_Hoje":
		*/

		//	obtem a Data e retorna uma referência:
		static agcData & HojeCalc();
		//	apenas retorna Data do sistema já obtida:
		static inline agcData & Hoje() {return m_dtHoje;}

		//	imprime dia/mês/ano:
		void Imprime() const;
        
        //  Retorna conteudo data invertido
        void Get( char * sData ) const;


	private:

		// ==== funções auxiliares internas:

		/*	analisa se a Data está correta
			sempre que é alterada posicionando
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
		
};  // FIM da declaração da classe agcData


// ==== IMPLEMENTAÇÃO DAS FUNÇÕES INLINE PÚBLICAS:


/*	Altera anos mínimo e máximo
	usados para validação do ano de cada Data:
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
	// até o ano anterior:
	int Ant=m_shAno-1;
	return m_shAno*365 + int(Ant/4) 
					   - int(Ant/100) + int(Ant/400)+1;
}

/*	retorna a quantidade de dias acumulados
	do início do ano até o fim de um determinado mês:
*/
inline int agcData::DiasAcumAteMes(int nMes)
{
	return m_shDiasAcumAteMes[nMes] + 
						(AnoBissexto() && nMes>1);
	//+1 se Ano for bissexto e mês superior a janeiro
}

/*	Ano é bissexto? (true/false)
*/
inline bool agcData::AnoBissexto() const
{
// par ser bissexto, deve ser divisível por 4 
// [ !(m_shAno & 3) => mais rápido que !(m_shAno%4) ]

// E NÃO ser divisível por 100 [ m_shAno%100 ]
// EXCETO quando for divisível por 400 [ !(m_shAno%400) ]
	
	return ( !(m_shAno & 3) && 
				( (m_shAno % 100) || !(m_shAno % 400) ) );

// As comparações acima são efetuadas do caso 
// mais frequente para o menos frequente.
}

/*	Último dia do mês em uso:
*/
inline char agcData::UltimoDiaMes() const
{
	return(m_cMes==FEVEREIRO)? 28 + AnoBissexto()
							 : 30 + ( (m_cMes&1)^(m_cMes>JULHO) );
}
/* 
Demonstração da função acima:

Se FEVEREIRO: 28 + 0 = 28 <<- Anos não bissextos
              28 + 1 = 29 <<- Anos bissextos
			  
De janeiro a julho : meses ímpares têm 31 dias, pares têm 30.
De julho a dezembro: meses ímpares têm 30 dias, pares têm 31.

Se ímpar ( mes & 1 ) ->> 1; 
Se par   ( mes & 1 ) ->> 0;

De janeiro a julho    ->> ( mes > JULHO ) ->> 0;
De agosto  a dezembro ->> ( mes > JULHO ) ->> 1; 

Então basta fazer: 30 + (( impar ) XOR ( >JULHO ))

EXEMPLOS:
junho-    mes 6 ->> 30 + ( 0 ^ 0 ) ->> 30 + 0 ->> 30
julho-    mes 7 ->> 30 + ( 1 ^ 0 ) ->> 30 + 1 ->> 31
agosto-   mes 8 ->> 30 + ( 0 ^ 1 ) ->> 30 + 1 ->> 31
setembro- mes 9 ->> 30 + ( 1 ^ 1 ) ->> 30 + 0 ->> 30

Veja também o ítem "Operadores de bit" 
no capítulo "Operadores" e a demonstração lá feita
de uma função "UltimoDiaMes".
*/


/*	número do dia na semana, 
	contado a partir de "DiaIni":
*/
inline int agcData::DiaSemana(int DiaIni)  const
{
	return int(( m_iDiaCorrido+DiaIni)%7)+1;

// se DiaIni for: 
//DS_INIDOMINGO: (default) -> domingo=1, segunda=2, ..., sábado =7;
//DS_INISEGUNDA: (Segunda) -> segunda=1, terça  =2, ..., domingo=7;
//DS_INISABADO : (Sábado ) -> sábado =1, domingo=2, ..., sexta  =7;
}

/*	subtrai uma Data(passada no parâmetro explícito)
	e retorna a diferença em dias:
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
					// (pois é posfixado)
}

/*	Decrementa a Data (POSfixado: Var--):
*/
inline int agcData::operator --(int)
{	
	int iTemp = m_iDiaCorrido; //salva
	_Decrementa(); // decrementa
	return iTemp; // retorna valor ANTERIOR ao decremento
					// (pois é posfixado)
}

// ======== Operadores Relacionais:

// ==== igualdade
inline bool agcData::operator == ( const agcData & dt2) const
{
	return ( m_iDiaCorrido == dt2.m_iDiaCorrido);
}

// ==== não igual (diferente de)
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
