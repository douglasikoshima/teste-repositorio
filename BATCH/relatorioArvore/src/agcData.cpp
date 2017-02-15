#include <iostream>
using namespace std;

#include <time.h>

#include "../include/cRelatArvore.h"

/* 
	Nesta versão o campo "m_bOK" foi suprimido.
	Isto porque a variável m_iDiaCorrido 
	também pode ser usada como "flag".
	Pois, como não existe o dia corrido "zero",
	podemos fazer m_iDiaCorrido = 0
	para assinalar uma Data como incorreta.
*/

// ==== inicializa o membro estático:
const short agcData::m_shDiasAcumAteMes[13] =
{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
// a matriz acima serve apenas para tornar mais
// rápidas as operações de soma e subtração.

// ==== construtoras

//	== construtora default:
agcData::agcData(short shAnoMin, short shAnoMax)
{		
	AnoMinMax(shAnoMin, shAnoMax);
	m_iDiaCorrido = 0; // agcData inválida
}
//	== recebe dados para alimentar a Data:
agcData::agcData(char cDia, char cMes, short shAno, short shAnoMin, short shAnoMax) 
{		
	AnoMinMax(shAnoMin, shAnoMax);
	Altera(cDia, cMes, shAno);
}
//	== recebe um inteiro para calcular dia, mês e ano:
agcData::agcData(int iDiaCorrido)
{
	AnoMinMax(ANO_MIN_DEF, ANO_MAX_DEF);
	m_iDiaCorrido = iDiaCorrido;
	_SomaDias(0); 
}
//	== recebe uma Data e uma quantidade de dias a somar/subtrair
	// (usada por operadores de soma e subtração)
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

	/*	para encontrar o ano, iremos inverter o cálculo
		feito na apuração do dia corrido visando
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

	/*	o cálculo do ano não é exato porque 
		a parte fracionária NÃO foi 
		armazenada em m_iDiaCorrido;
		assim ocorrerá falha
		para alguns dias 31/12 e alguns 01/01.
		Abaixo, é feita a CORREÇÃO quando necessário:
	*/
		// == alguns 31/12 OU alguns 01/01:
	if ( nDiasSomar < 1 || nDiasSomar > 365+AnoBissexto() ) 
	{
		// subtrai ou soma 1
		// (o ano correto é o anterior ou o próximo)
		m_shAno+=(nDiasSomar<1)?-1:1; 
		nDiasSomar = m_iDiaCorrido - DiasAnosAnteriores();
	}

	// apurar o mês:
	m_cMes = (nDiasSomar-1)/31 +1;

	// o cálculo não é exato; para ALGUNS dias
	// o mês correto é o próximo:
	if (nDiasSomar > DiasAcumAteMes(m_cMes) )
		++m_cMes; // mês foi corrigido

	// apurar o dia:
	// subtrai os dias acumulados até mês anterior:
	m_cDia = nDiasSomar-DiasAcumAteMes(m_cMes-1);
	
	// verificar se ultrapassou anos mínimo ou máximo
	if ( m_shAno < m_shAnoMin || m_shAno > m_shAnoMax)
		 m_iDiaCorrido = 0 ; // ano inválido
}

/* ====	funções "_Incrementa" e "_Decrementa":
		por razões de performance, essas 2 funções,
		usadas pelos 2 operadores "++" 
		e pelos 2 operadores "--"
		têm uma implementação específica,
		não usando a função "_SomaDias(1)" ou "_SomaDias(-1)"
*/

// ====	incrementar:
void agcData::_Incrementa()
{
	if ( m_iDiaCorrido < 1 ) return;

	// o código abaixo é mais rápido do que:
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
				 m_iDiaCorrido = 0; // ano inválido
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

	// o código abaixo é mais rápido do que
	// _SomaDias( -1 );

	--m_iDiaCorrido;

	if ( m_cDia == 1 )
	{
		if ( m_cMes == 1 )
		{
			--m_shAno;
			if ( m_shAno < m_shAnoMin)
			{
				 m_iDiaCorrido = 0; // ano inválido
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

/* ====	Verificar se a Data está correta:
*/
inline void agcData::_Valida()
{
	// se a Data estiver inválida, anula m_iDiaCorrido,
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




/* ==== inicializa o membro estático que irá
		armazenar a Data do sistema(hoje):
*/
agcData agcData::m_dtHoje = agcData::HojeCalc();

/* ====	Função para capturar a Data do sistema
		e armazená-la na estática "m_Hoje",
		retornando uma referência para "m_dtHoje":
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
