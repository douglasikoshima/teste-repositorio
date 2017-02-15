package br.com.vivo.fo.ctrls.VOLTAV.manterRedirecionamento.db;

import java.io.Serializable;

import javax.ejb.Local;

@Local
public interface ManterRedirecionamentoDB {

	static final long serialVersionUID = 1L;

	public static class ItemRedirecionamento implements Serializable {

		private static final long serialVersionUID = 6964706396741393388L;
		private String idRedirecionamento;
		private String primeiroNivel;
		private String segundoNivel;
		private String status;
		private String idSistema;
		private String idCampanha;
		private String urlDestino;
		private String idMenu;
		private String idSubMenu;
		private String idUsuario;
		private String dataAlteracao;

		public String getIdRedirecionamento() {
			return this.idRedirecionamento;
		}

		public void setIdRedirecionamento(String string) {
			this.idRedirecionamento = string;
		}

		public String getPrimeiroNivel() {
			return this.primeiroNivel;
		}

		public void setPrimeiroNivel(String string) {
			this.primeiroNivel = string;
		}

		public String getSegundoNivel() {
			return this.segundoNivel;
		}

		public void setSegundoNivel(String string) {
			this.segundoNivel = string;
		}

		public String getStatus() {
			return "1".equals(this.status) ? "Ativo" : "Inativo";
		}

		public void setStatus(String string) {
			this.status = string;
		}

		public String getIdSistema() {
			return this.idSistema;
		}

		public void setIdSistema(String string) {
			this.idSistema = string;
		}

		public String getIdCampanha() {
			return this.idCampanha;
		}

		public void setIdCampanha(String string) {
			this.idCampanha = string;
		}

		public String getUrlDestino() {
			return this.urlDestino;
		}

		public void setUrlDestino(String string) {
			this.urlDestino = string;
		}

		public String getIdMenu() {
			return this.idMenu;
		}

		public void setIdMenu(String string) {
			this.idMenu = string;
		}

		public String getIdSubMenu() {
			return this.idSubMenu;
		}

		public void setIdSubMenu(String string) {
			this.idSubMenu = string;
		}

		public String getIdUsuario() {
			return this.idUsuario;
		}

		public void setIdUsuario(String string) {
			this.idUsuario = string;
		}

		public String getDataAlteracao() {
			return this.dataAlteracao;
		}

		public void setDataAlteracao(String string) {
			this.dataAlteracao = string;
		}
	}

	/**
	 * @jc:sql array-max-length="all" statement="{sql: query}"
	 */
	ItemRedirecionamento[] getListaRedirecionamento(String query);
}
