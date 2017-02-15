package cliente.TelaInicial.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetToken implements Runnable {

	private static final String STRING_TOKEN = "token=";
	private static final String METODO_POST = "POST";
	private static final String NEWLINE = "\n";

	private String caminhoURL;
	private String body;
	private String resposta;
	private IntegradorException erro;

	/**
	 * Construtor da classe
	 */
	public GetToken(String caminhoURL, String body) {
		this.caminhoURL = caminhoURL;
		this.body = body;
		this.resposta = null;
		this.erro = null;
	}

	/**
	 * Executa a comunicacão
	 */
	public void run() {
		try {
			HttpURLConnection conn = getHttpURLConnection(caminhoURL, body.length());
			try {
				enviaMensagem(conn, body);
				String respostaAutenticador = getRespostaAutenticador(conn);
				int indexOfToken = respostaAutenticador.indexOf(STRING_TOKEN);
				if (indexOfToken != -1) {
					resposta = respostaAutenticador.substring(indexOfToken + STRING_TOKEN.length()).trim();
					return;
				}
				throw new IntegradorException("Resposta do autenticador invalida.\nResposta:" + respostaAutenticador + ".\n URL Autenticador:" + caminhoURL + ".\n Body:" + body + "\n");
			} finally {
				conn.disconnect();
			}
		} catch (IntegradorException e) {
			erro = e;
		}
	}

	/**
	 * Obtem o token do autenticador.
	 * 
	 * @param caminhoURL
	 *            String com a URL do autenticador.
	 * @param body
	 *            corpo da mensagem enviada ao autenticador.
	 * @return resposta do autenticador. Pode ser o token ou um erro.
	 * @throws IntegradorException
	 *             caso ocorra alguma exceção ao obter uma resposta do autenticador.
	 */
	public static String get(String caminhoURL, String body) throws IntegradorException {
		GetToken gt = new GetToken(caminhoURL, body);
		Thread t = new Thread(gt);
		t.start();
		int secs = 0;
		while (secs++ < 10 && gt.resposta == null && gt.erro == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		if (gt.erro != null) {
			throw gt.erro;
		}
		if (gt.resposta == null) {
			throw new IntegradorException("Nao foi possivel autenticar no tempo previsto: " + caminhoURL + "\nParametros: " + body);
		}
		return gt.resposta;
	}

	/**
	 * Estabele conexão com o autenticador.
	 * 
	 * @param caminhoURL
	 *            String com a URL do autenticador.
	 * @param bodyLength
	 *            tamanho do corpo da mensagem enviada ao autenticador.
	 * @return a conexão que foi estabelecida com o autenticador.
	 * @throws IntegradorException
	 *             caso ocorra erro ao obter o objeto java.net.URL a partir do parâmetro caminhoURL. Ou caso ocorra erro
	 *             ao abrir a conexão com o autenticador.
	 */
	private HttpURLConnection getHttpURLConnection(String caminhoURL, int bodyLength) throws IntegradorException {
		try {
			URL url = new URL(caminhoURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(METODO_POST);
			conn.setAllowUserInteraction(false);
			conn.setRequestProperty("Content-length", Integer.toString(bodyLength));
			return conn;
		} catch (MalformedURLException e) {
			throw new IntegradorException("Erro ao obter URL do autenticador. URL:" + caminhoURL, e);
		} catch (IOException e) {
			throw new IntegradorException("Erro ao obter conexao com o autenticador. URL:" + caminhoURL, e);
		}
	}

	/**
	 * Envia mensagem ao autenticador.
	 * 
	 * @param conn
	 *            conexão HTTP com o autenticador.
	 * @param body
	 *            corpo da mensagem enviada ao autenticador.
	 * @throws IntegradorException
	 *             caso ocorra uma exceção ao enviar mensagem ao autenticador.
	 */
	private void enviaMensagem(HttpURLConnection conn, String body) throws IntegradorException {
		try {
			PrintWriter pw = new PrintWriter(conn.getOutputStream());
			pw.print(body);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			throw new IntegradorException("Erro ao enviar corpo da mensagem ao autenticador. " + "Body: " + body, e);
		}
	}

	/**
	 * Recupera a resposta do autenticador.
	 * 
	 * @param conn
	 *            conexão HTTP com o autenticador.
	 * @return resposta do autenticador.
	 * @throws IntegradorException
	 *             caso ocorra uma exceção ao obter a resposta do autenticador.
	 */
	private String getRespostaAutenticador(HttpURLConnection conn) throws IntegradorException {
		InputStream inputStream;
		try {
			inputStream = conn.getInputStream();
		} catch (IOException e) {
			throw new IntegradorException("Erro ao obter stream de resposta do autenticador.", e);
		}
		BufferedReader rdr = new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer resposta = new StringBuffer();
		try {
			for (String line; (line = rdr.readLine()) != null; resposta.append(line).append(NEWLINE)) {
				;
			}
		} catch (IOException e) {
			throw new IntegradorException("Erro ao ler linha da resposta do autenticador.", e);
		}
		return resposta.toString();
	}
}
