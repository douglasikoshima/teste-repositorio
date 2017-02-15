#include <iostream>
using namespace std;

#include <time.h>

#include "../include/cRelatArvore.h"

/* 
	Nesta vers�o o campo "m_bOK" foi suprimido.
	Isto porque a vari�vel m_iDiaCorrido 
	tamb�m pode ser usada como "flag".
	Pois, como n�o existe o dia corrido "zero",
	podemos fazer m_iDiaCorrido = 0
	para assinalar uma Data como incorreta.
*/

// ==== inicializa o membro est�tico:
const short agcData::m_shDiasAcumAteMes[13] =
{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
// a matriz acima serve apenas para tornar mais
// r�pidas as opera��es de soma e subtra��o.

// ==== construtoras

//	== construtora default:
agcData::agcData(short shAnoMin, short shAnoMax)
{		
	AnoMinMax(shAnoMin, shAnoMax);
	m_iDiaCorrido = 0; // agcData inv�lida
}
//	== recebe dados para alimentar a Data:
agcData::agcData(char cDia, char cMes, short shAno, short shAnoMin, short shAnoMax) 
{		
	AnoMinMax(shAnoMin, shAnoMax);
	Altera(cDia, cMes, shAno);
}
//	== recebe um inteiro para calcular dia, m�s e ano:
agcData::agcData(int iDiaCorrido)
{
	AnoMinMax(ANO_MIN_DEF, ANO_MAX_DEF);
	m_iDiaCorrido = iDiaCorrido;
	_SomaDias(0); 
}
//	== recebe uma Data e uma quantidade de dias a somar/subtrair
	// (usada por operadores de soma e subtra��o)
agcData::agcData(const agcData & dtOrigem, int nDiasSomar)
{
	AnoMinMax(dtOrigem.m_shAnoMin, dtOrigem.m_shAnoMax);

	m_shAno	= dtOrigem.m_shAno;
	m_cMes	= dtOrigem.m_cMes;
	m_cDia	= dtOrigem.m_cDia;
	m_iDiaCorrido = dtOrigem.m_iDiaCorrido;	

	_SomaDias(nDiasSomar);
}

/* ====	Alterar a Data
*/
void agcData::Altera (char cDia, char cMes, short shAno)
{
	m_cDia	= cDia;
	m_cMes	= cMes;
	m_shAno = shAno;

	// 1) calcula quantidade de dias 
	// dos anos anteriores:
	m_iDiaCorrido = DiasAnosAnteriores();
	
	// 2) calcula e soma dias do ano:
	//	  (soma dias dos meses anteriores e o dia):
	m_iDiaCorrido += DiasAcumAteMes(m_cMes-1)+m_cDia;

	_Valida();
}

/* ====	Soma uma quantidade de dias a uma Data
*/
void agcData::_SomaDias(int nDiasSomar) 
{
	if ( m_iDiaCorrido < 1 ) return;

	m_iDiaCorrido += nDiasSomar ;

	/*	para encontrar o ano, iremos inverter o c�lculo
		feito na apura��o do dia corrido visando
		encontrar o total de dias dos anos anteriores:
		
		DiasAnosAnteriores = Ano*365 + int((Ano-1)/4)
		- int((Ano-1)/100) + int((Ano-1)/400)+1;
		==> = Ano*365 + (100*(Ano-1)-4*(Ano-1)+(Ano-1))/400+1
		==> = Ano*365 + (97*(Ano-1)/400)+1
		==> = Ano*365 +  Ano*.2425 - .2425 +1
		==> = Ano*365.2425 - .7575
		<====> Ano = (DiasAnosAnteriores-.7575)/365.2425
	*/
	m_shAno = int((m_iDiaCorrido-.7575)/365.2425); 
	
	nDiasSomar = m_iDiaCorrido - DiasAnosAnteriores();

	/*	o c�lculo do ano n�o � exato porque 
		a parte fracion�ria N�O foi 
		armazenada em m_iDiaCorrido;
		assim ocorrer� falha
		para alguns dias 31/12 e alguns 01/01.
		Abaixo, � feita a CORRE��O quando necess�rio:
	*/
		// == alguns 31/12 OU alguns 01/01:
	if ( nDiasSomar < 1 || nDiasSomar > 365+AnoBissexto() ) 
	{
		// subtrai ou soma 1
		// (o ano correto � o anterior ou o pr�ximo)
		m_shAno+=(nDiasSomar<1)?-1:1; 
		nDiasSomar = m_iDiaCorrido - DiasAnosAnteriores();
	}

	// apurar o m�s:
	m_cMes = (nDiasSomar-1)/31 +1;

	// o c�lculo n�o � exato; para ALGUNS dias
	// o m�s correto � o pr�ximo:
	if (nDiasSomar > DiasAcumAteMes(m_cMes) )
		++m_cMes; // m�s foi corrigido

	// apurar o dia:
	// subtrai os dias acumulados at� m�s anterior:
	m_cDia = nDiasSomar-DiasAcumAteMes(m_cMes-1);
	
	// verificar se ultrapassou anos m�nimo ou m�ximo
	if ( m_shAno < m_shAnoMin || m_shAno > m_shAnoMax)
		 m_iDiaCorrido = 0 ; // ano inv�lido
}

/* ====	fun��es "_Incrementa" e "_Decrementa":
		por raz�es de performance, essas 2 fun��es,
		usadas pelos 2 operadores "++" 
		e pelos 2 operadores "--"
		t�m uma implementa��o espec�fica,
		n�o usando a fun��o "_SomaDias(1)" ou "_SomaDias(-1)"
*/

// ====	incrementar:
void agcData::_Incrementa()
{
	if ( m_iDiaCorrido < 1 ) return;

	// o c�digo abaixo � mais r�pido do que:
	// _SomaDias( 1 );

	++m_iDiaCorrido;
	if ( m_cDia == UltimoDiaMes())
	{
		m_cDia=1;
		if ( m_cMes == 12 )
		{
			++m_shAno;
			if ( m_shAno > m_shAnoMax)
			{
				 m_iDiaCorrido = 0; // ano inv�lido
				return;
			}
			m_cMes=1;
		} 
		else
			++m_cMes;
	}
	else
		++m_cDia;
}

// ==== decrementar:
void agcData::_Decrementa()
{
	if ( m_iDiaCorrido < 1 ) return;

	// o c�digo abaixo � mais r�pido do que
	// _SomaDias( -1 );

	--m_iDiaCorrido;

	if ( m_cDia == 1 )
	{
		if ( m_cMes == 1 )
		{
			--m_shAno;
			if ( m_shAno < m_shAnoMin)
			{
				 m_iDiaCorrido = 0; // ano inv�lido
				return;
			}

			m_cMes = 12;
			m_cDia = 31;
		}
		else
		{
			--m_cMes;
			m_cDia = UltimoDiaMes();
		}
	}
	else 
		--m_cDia;
}

/* ====	Verificar se a Data est� correta:
*/
inline void agcData::_Valida()
{
	// se a Data estiver inv�lida, anula m_iDiaCorrido,
	// marcando-a com zero:
	if (  m_shAno < m_shAnoMin || m_shAno > m_shAnoMax
				|| m_cMes < 1  || m_cMes  > 12
				|| m_cDia < 1  || m_cDia  > UltimoDiaMes() 
		)
	{
		m_iDiaCorrido = 0;
	}
}

/* ====	Imprime a Data em console:
*/
void agcData::Imprime() const
{
	if (  m_iDiaCorrido > 0 )
	{
		cout.fill( '0');
		cout.width(2);
		cout << int(m_cDia) << '/';
		cout.width(2);
		cout << int(m_cMes) << '/' ;
		cout.width(4);
		cout << m_shAno << endl;
	}
	else
		cout << "??:??:????" << endl;
}




void agcData::Get( char * sData ) const
{
	if (  m_iDiaCorrido > 0 )
	{
        sprintf( sData, "%04d%02d%02d",int(m_shAno),int(m_cMes),int(m_cDia) );
	}
	else
        sData[0] = 0x0;
}




/* ==== inicializa o membro est�tico que ir�
		armazenar a Data do sistema(hoje):
*/
agcData agcData::m_dtHoje = agcData::HojeCalc();

/* ====	Fun��o para capturar a Data do sistema
		e armazen�-la na est�tica "m_Hoje",
		retornando uma refer�ncia para "m_dtHoje":
*/
agcData & agcData::HojeCalc()
{
	time_t tAgora;
	tm * tmAgora;

	// captura total de segundos
	// decorridos desde 1/1/1970
	time ( &tAgora );
	
	// Agora, captura Data atual do sistema.
	tmAgora = localtime( &tAgora );
	
	m_dtHoje.AnoMinMax(ANO_MIN_DEF, ANO_MAX_DEF);
	m_dtHoje.Altera(tmAgora->tm_mday, tmAgora->tm_mon+1,
						tmAgora->tm_year + 1900);
	return m_dtHoje;
}
