package br.com.vivo.fo.xml;

import br.com.vivo.fo.exception.TuxedoErrorException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

/**
 * Classe respons�vel pela manipula��o do xml derivado do tuxedo.
 **/
public class RetornoTuxedo {

	// Monta o mecanismo de log
	private static Logger log = new Logger("start");

	/**
	 * Analisa dados recebidos do servi�o Tuxedo, previamente processados, e propaga exce��o em caso de ocorr�ncia de
	 * error/warning.
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static void TrataCodigoExecucao(Class classe, String[] retTux) throws TuxedoException {
		log.debug("RetornoTuxedo:TrataCodigoExecucao started");

		// trata Erro ocorrido no Tuxedo
		if (retTux[1].equalsIgnoreCase("E")) {
			TuxedoErrorException tuxEx = new TuxedoErrorException( retTux[2] + " - " + retTux[3]);
			log.error("RetornoTuxedo:TrataCodigoExecucao: " + retTux[2] + " : " + retTux[3]);
			throw (tuxEx);
		}

		// trata mensagem de Warning ocorrida no Tuxedo
		if (retTux[1].equalsIgnoreCase("W")) {
			TuxedoWarningException tuxEx = new TuxedoWarningException( retTux[2] + " - " + retTux[3]);
			log.warn("RetornoTuxedo:TrataCodigoExecucao: " + retTux[2] + " : " + retTux[3]);
			throw (tuxEx);
		}

		// trata mensagem de Info ocorrida no Tuxedo
		if (retTux[1].equalsIgnoreCase("I") && (retTux[3].trim().length() > 0)) {
			// Monta o log da opera��o se poss�vel
			log.debug("RetornoTuxedo:TrataCodigoExecucao: " + retTux[2] + " : " + retTux[3]);
		}

		log.debug("RetornoTuxedo:TrataCodigoExecucao ended");
	}
}
