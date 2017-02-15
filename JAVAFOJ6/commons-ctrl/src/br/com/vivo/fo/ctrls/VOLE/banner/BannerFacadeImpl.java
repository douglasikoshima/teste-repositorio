package br.com.vivo.fo.ctrls.VOLE.banner;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.dbclasses.AcessoBannerVOLE;
import br.com.vivo.fo.dbclasses.ApoioAreaBannerVOLE;
import br.com.vivo.fo.dbclasses.ApoioTipoBannerVOLE;
import br.com.vivo.fo.dbclasses.RelacionamentoBannerVOLE;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Stateless(name = "BannerFacade", mappedName = "BannerFacade")
@Local(BannerFacade.class)
@Session(ejbName = "BannerFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BannerFacadeImpl implements BannerFacade {

	@EJB
	private br.com.vivo.fo.ctrls.VOLE.banner.BannerDB bannerDBControl;

	private static final long serialVersionUID = 1000254887411L;

	private final static transient Logger log = new Logger("vol");

	private int[] getMinMaxRows(int nrPagina, int itensPorPagina) {
		int minRow, maxRow;
		int[] minMaxRows = new int[2];
		minRow = (nrPagina == 1) ? 1 : ((nrPagina - 1) * itensPorPagina) + 1;
		maxRow = (minRow + itensPorPagina) - 1;
		minMaxRows[0] = minRow;
		minMaxRows[1] = maxRow;
		return minMaxRows;
	}

	/**
	 * @common:operation
	 */
	public HashMap buscarListaAreasBanner() throws Exception {

		HashMap listaAreas = new HashMap();
		ApoioAreaBannerVOLE[] areaBannerArray = bannerDBControl.getListaAreasBanner();

		for (int i = 0; i < areaBannerArray.length; i++) {
			listaAreas.put(Long.toString(areaBannerArray[i].getIdAreaBannerVOLE()), areaBannerArray[i].getDsAreaBannerVOLE());
		}

		return listaAreas;
	}

	/**
	 * @common:operation
	 */
	public HashMap buscarListaTiposBanner() throws Exception {

		HashMap listaTiposBanner = new HashMap();
		ApoioTipoBannerVOLE[] tipoBannerArray = bannerDBControl.getListaTiposBanner();

		for (int i = 0; i < tipoBannerArray.length; i++) {
			listaTiposBanner.put(Long.toString(tipoBannerArray[i].getIdTipoBannerVOLE()), tipoBannerArray[i].getDsTipoBannerVOLE());
		}

		return listaTiposBanner;
	}

	/**
	 * @common:operation
	 */
	public AcessoBannerVOLE buscarDadosBanner(String idUsuario, long idBanner) throws FacadeException {

		log.info("[BannerFacadeImpl:buscarDadosBanner] [user:" + idUsuario + "] Método iniciado.");
		try {
			AcessoBannerVOLE banner = new AcessoBannerVOLE();
			banner = bannerDBControl.getBannerVOLE(idBanner);

			log.info("[BannerFacadeImpl:buscarDadosBanner] [user:" + idUsuario + "] Método finalizado.");

			return banner;

		} catch (SQLException e) {
			log.error("[BannerFacadeImpl:buscarDadosBanner] [user:" + idUsuario + "] [SQLException] " + e.getMessage());
			throw (new FacadeException(e));
		}
	}

	/**
	 * @common:operation
	 */
	public PaginacaoBannersVOLE buscarListaBannersPorArea(String idUsuario, int idAreaBanner, int nrPagina, int itensPorPagina) throws FacadeException {
		try {
			int minRow = getMinMaxRows(nrPagina, itensPorPagina)[0];
			int maxRow = getMinMaxRows(nrPagina, itensPorPagina)[1];

			PaginacaoBannersVOLE retorno = new PaginacaoBannersVOLE();
			log.info("[BannerFacadeImpl:buscarListaBannersPorArea] [user:" + idUsuario + "] Método iniciado.");

			AcessoBannerVOLE[] listaBanners = null;

			int qtdeTotal = 0;
			try {
				String countString = "SELECT COUNT(*) FROM ACESSO.BANNERVOLE WHERE IDAREABANNERVOLE = " + idAreaBanner;
				qtdeTotal = bannerDBControl.countListaBanner(countString);
				retorno.setTotalItens(qtdeTotal);
			} catch (Exception e) {
			}

			if (qtdeTotal <= maxRow || qtdeTotal == 0) {
				retorno.setUltimaPagina(true);
				maxRow = qtdeTotal;
			}

			StringBuffer query = new StringBuffer();
			query.append(" SELECT * ").append("   FROM (SELECT A.*, ").append("                ROWNUM RNUM ").append("           FROM (  SELECT IDBANNERVOLE, ").append("       NMBANNERVOLE, ").append("       URLBANNERVOLE, ").append("       IDAREABANNERVOLE, ").append("       IDTIPOBANNERVOLE, ").append("       DSBANNERVOLE, ").append("       IPBANNERVOLE, ").append("       DTINICIAL, ").append("       DTFINAL, ").append("       INCONTADOR, ").append("       NRCONTADOR ")
					.append("  FROM ACESSO.BANNERVOLE ").append(" WHERE IDAREABANNERVOLE = ").append(idAreaBanner).append("                 ORDER BY DSBANNERVOLE) A ").append("          WHERE ROWNUM <= " + maxRow + ") ").append("  WHERE RNUM >= " + minRow);

			listaBanners = bannerDBControl.getListaBanners(query.toString());

			log.info("[BannerFacadeImpl:buscarListaBannersPorArea] [user:" + idUsuario + "] Método finalizado.");

			retorno.setListaBanners(listaBanners);
			return retorno;

		} catch (SQLException e) {
			log.error("[BannerFacadeImpl:buscarListaBannersPorArea] [user:" + idUsuario + "] [SQLException] " + e.getMessage());
			throw (new FacadeException(e));

		} catch (Exception e) {
			log.error("[BannerFacadeImpl:buscarListaBannersPorArea] [user:" + idUsuario + "] [Exception] " + e.getMessage());
			throw (new FacadeException(e));
		}

	}

	/**
	 * @common:operation
	 */
	public void atualizarRelacionamentoBannerVOLE(String idUsuario, String operacao, long[] idRelacionamentoBannerVOLE, long idBanner, String urlBanner) throws FacadeException {

		if (idRelacionamentoBannerVOLE == null || idRelacionamentoBannerVOLE.length == 0) {
			log.error("[BannerFacadeImpl:atualizarRelacionamentoBannerVOLE] [user:" + idUsuario + "] [ASException] Nenhum relacionamento de banner selecionado.");
			throw new FacadeException("Nenhum relacionamento de banner selecionado.");

		} else if ("associacao".equals(operacao) && idBanner == 0) {
			log.error("[BannerFacadeImpl:atualizarRelacionamentoBannerVOLE] [user:" + idUsuario + "] [ASException] Banner não selecionado para associação.");
			throw new FacadeException("Banner não selecionado para associação.");
		}

		try {

			StringBuffer query = new StringBuffer();
			StringBuffer where = new StringBuffer();

			if (idRelacionamentoBannerVOLE.length == 1) {
				where.append("IDRELACIONAMENTOBANNERVOLE = ").append(idRelacionamentoBannerVOLE[0]);

			} else {
				where.append("IDRELACIONAMENTOBANNERVOLE IN (");
				for (int i = 0; i < idRelacionamentoBannerVOLE.length; i++) {
					where.append(idRelacionamentoBannerVOLE[i]);
					if (i < idRelacionamentoBannerVOLE.length - 1) {
						where.append(", ");
					}
				}
				where.append(")");
			}

			if ("associacao".equals(operacao)) {
				urlBanner = (urlBanner == null) ? "NULL" : ("'" + urlBanner + "'");
				// Primeiramente será atualizado o link do banner.
				query.append("UPDATE ACESSO.BANNERVOLE ").append("   SET URLBANNERVOLE = ").append(urlBanner).append(" WHERE IDBANNERVOLE = ").append(idBanner);
				bannerDBControl.updateRelacionamentoBannerVOLE(query.toString());

				// Em seguida os relacionamentos serão atualizados.
				query = new StringBuffer();
				query.append("UPDATE ACESSO.RELACIONAMENTOBANNERVOLE ").append("   SET IDBANNERVOLE = ").append(idBanner).append(",").append("       IDUSUARIOALTERACAO = ").append(idUsuario).append(",").append("       DTULTIMAATUALIZACAO = SYSDATE ").append(" WHERE ").append(where.toString());

			} else if ("desassociacao".equals(operacao)) {
				query.append("UPDATE ACESSO.RELACIONAMENTOBANNERVOLE ").append("   SET IDBANNERVOLE = NULL,").append("       IDUSUARIOALTERACAO = ").append(idUsuario).append(",").append("       DTULTIMAATUALIZACAO = SYSDATE ").append(" WHERE ").append(where.toString());

			}

			bannerDBControl.updateRelacionamentoBannerVOLE(query.toString());

			return;

		} catch (SQLException e) {
			log.error("[BannerFacadeImpl:buscarListaBannersPorArea] [user:" + idUsuario + "] [SQLException] " + e.getMessage());
			throw (new FacadeException(e));

		} catch (Exception e) {
			log.error("[BannerFacadeImpl:buscarListaBannersPorArea] [user:" + idUsuario + "] [Exception] " + e.getMessage());
			throw (new FacadeException(e));
		}
	}

	/**
	 * @common:operation
	 */
	public PaginacaoBannersVOLE buscarListaBanners(String idUsuario, String[] areas, int nrPagina, int itensPorPagina) throws FacadeException {

		log.info("[BannerFacadeImpl:buscarListaBanners] [user:" + idUsuario + "] Método iniciado.");

		int minRow = getMinMaxRows(nrPagina, itensPorPagina)[0];
		int maxRow = getMinMaxRows(nrPagina, itensPorPagina)[1];

		StringBuffer query = new StringBuffer();
		StringBuffer where = new StringBuffer();

		PaginacaoBannersVOLE retorno = new PaginacaoBannersVOLE();

		if (areas == null || areas.length == 0) {
			throw new FacadeException("Nenhuma área selecionada.");
		}

		if (areas.length == 1) {
			where.append("IDAREABANNERVOLE = ").append(areas[0]);
		} else {
			where.append("IDAREABANNERVOLE IN (");
			for (int i = 0; i < areas.length; i++) {
				where.append(areas[i]);
				if (i < areas.length - 1) {
					where.append(", ");
				}
			}
			where.append(") ");
		}

		int qtdeTotal = 0;
		try {
			String countString = "SELECT COUNT(*) FROM ACESSO.BANNERVOLE WHERE " + where.toString();
			qtdeTotal = bannerDBControl.countListaBanner(countString);
			retorno.setTotalItens(qtdeTotal);
		} catch (Exception e) {
		}

		if (qtdeTotal <= maxRow || qtdeTotal == 0) {
			retorno.setUltimaPagina(true);
			maxRow = qtdeTotal;
		}

		query.append(" SELECT * ").append("   FROM (SELECT A.*, ").append("                ROWNUM RNUM ").append("           FROM (  SELECT IDBANNERVOLE, ").append("                          NMBANNERVOLE, ").append("                          URLBANNERVOLE, ").append("                          IDAREABANNERVOLE, ").append("                          IDTIPOBANNERVOLE, ").append("                          DSBANNERVOLE, ").append("                          IPBANNERVOLE, ")
				.append("                          DTINICIAL, ").append("                          DTFINAL, ").append("                          INCONTADOR, ").append("                          NRCONTADOR ").append("                     FROM ACESSO.BANNERVOLE ").append("                    WHERE " + where.toString()).append("                 ORDER BY DSBANNERVOLE) A ").append("          WHERE ROWNUM <= " + maxRow + ") ").append(" WHERE RNUM >= " + minRow);

		try {
			AcessoBannerVOLE[] listaBanners = bannerDBControl.getListaBanners(query.toString());

			retorno.setListaBanners(listaBanners);
			retorno.setPaginaAtual(nrPagina);

			log.info("[BannerFacadeImpl:buscarListaBanners] [user:" + idUsuario + "] Método finalizado.");

			return retorno;

		} catch (Exception ex) {
			log.error("[BannerFacadeImpl:buscarListaBanners] [exception] [user:" + idUsuario + "] " + ex.getMessage());
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public PaginacaoBannersVOLE buscarListaRelacionamentos(String idUsuario, String[] areas, String[] uf, int nrPagina, int itensPorPagina) throws FacadeException {
		try {
			log.info("[BannerFacadeImpl:buscarListaRelacionamentos] [user:" + idUsuario + "] Método iniciado.");

			StringBuffer query = new StringBuffer();
			StringBuffer where = new StringBuffer();

			if (areas == null || areas.length == 0) {
				throw new FacadeException("Nenhuma área selecionada.");
			} else if (uf == null || uf.length == 0) {
				throw new FacadeException("Nenhuma UF selecionada.");
			}

			if (areas.length == 1) {
				where.append("RBV.IDAREABANNERVOLE = ").append(areas[0]);

			} else {
				where.append("RBV.IDAREABANNERVOLE IN (");
				for (int i = 0; i < areas.length; i++) {
					where.append(areas[i]);
					if (i < areas.length - 1) {
						where.append(", ");
					}
				}
				where.append(") ");
			}

			where.append(" AND ");
			if (uf.length == 1) {
				where.append("RBV.IDUF = ").append(uf[0]);

			} else {
				where.append("RBV.IDUF IN (");
				for (int i = 0; i < uf.length; i++) {
					where.append(uf[i]);
					if (i < uf.length - 1) {
						where.append(", ");
					}
				}
				where.append(") ");
			}

			int minRow = getMinMaxRows(nrPagina, itensPorPagina)[0];
			int maxRow = getMinMaxRows(nrPagina, itensPorPagina)[1];

			PaginacaoBannersVOLE retorno = new PaginacaoBannersVOLE();

			int qtdeTotal = 0;
			try {
				String countString = "SELECT COUNT(*) FROM ACESSO.RELACIONAMENTOBANNERVOLE RBV WHERE " + where.toString();
				qtdeTotal = bannerDBControl.countListaBanner(countString);
			} catch (Exception e) {
			}

			// Linha inserida para remover paginação desta busca. Atualmente o retorno será sempre igual à quantidade de
			// UFs.
			maxRow = qtdeTotal;

			if (qtdeTotal <= maxRow || qtdeTotal == 0) {
				retorno.setUltimaPagina(true);
				maxRow = qtdeTotal;
			}

			RelacionamentoBannerVOLE[] listaRelacionamentos = new RelacionamentoBannerVOLE[0];

			query.append("SELECT * ").append("  FROM ( SELECT A.*, ").append("                ROWNUM RNUM ").append("           FROM (  SELECT RBV.IDRELACIONAMENTOBANNERVOLE, ").append("                          UF.SGUF, ").append("                          RBV.IDBANNERVOLE, ").append("                          BV.DSBANNERVOLE, ").append("                          BV.NMBANNERVOLE, ").append("                          BV.URLBANNERVOLE, ").append("                          ABV.DSAREABANNERVOLE ")
					.append("                     FROM ACESSO.RELACIONAMENTOBANNERVOLE RBV, ").append("                          ACESSO.BANNERVOLE BV, ").append("                          APOIO.AREABANNERVOLE ABV, ").append("                          APOIO.UF UF ").append("                    WHERE " + where.toString()).append("                      AND UF.IDUF = RBV.IDUF ").append("                      AND RBV.IDAREABANNERVOLE = ABV.IDAREABANNERVOLE ")
					.append("                      AND RBV.IDBANNERVOLE = BV.IDBANNERVOLE(+) ").append("                 ORDER BY RBV.IDUF) A ").append("          WHERE ROWNUM <= " + maxRow + ") ").append(" WHERE RNUM >= " + minRow);

			if (qtdeTotal > 0) {
				listaRelacionamentos = bannerDBControl.getListaRelacionamentos(query.toString());
			}

			retorno.setListaRelacionamentos(listaRelacionamentos);
			retorno.setPaginaAtual(nrPagina);

			log.info("[BannerFacadeImpl:buscarListaRelacionamentos] [user:" + idUsuario + "] Método finalizado.");

			return retorno;

		} catch (Exception ex) {
			log.error("[BannerFacadeImpl:buscarListaRelacionamentos] [exception] [user:" + idUsuario + "] " + ex.getMessage());
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void incluirBanner(String idUsuario, AcessoBannerVOLE banner) throws FacadeException {

		log.info("[BannerFacadeImpl:incluirBanner] [user:" + idUsuario + "] Método iniciado.");
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			String dtInicial = formatter.format(banner.getDtInicial());
			String dtFinal = null;
			if (banner.getDtFinal() != null) {
				dtFinal = formatter.format(banner.getDtFinal());
			}

			bannerDBControl.insertBannerVOLE(banner.getNmBannerVOLE(), banner.getUrlBannerVOLE(), banner.getIdAreaBannerVOLE(), banner.getIdTipoBannerVOLE(), banner.getDsBannerVOLE(), banner.getIPBannerVOLE(), dtInicial, dtFinal, banner.getInContador());
			log.info("[BannerFacadeImpl:incluirBanner] [user:" + idUsuario + "] Método finalizado.");

			return;

		} catch (Exception ex) {
			log.error("[BannerFacadeImpl:incluirBanner] [exception] [user:" + idUsuario + "] " + ex.getMessage());
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void alterarBanner(String idUsuario, AcessoBannerVOLE banner) throws FacadeException {

		log.info("[BannerFacadeImpl:alterarBanner] [user:" + idUsuario + "] Método iniciado.");
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String dtInicial = formatter.format(banner.getDtInicial());
			String dtFinal = null;
			if (banner.getDtFinal() != null) {
				dtFinal = formatter.format(banner.getDtFinal());
			}

			bannerDBControl.updateBannerVOLE(banner.getIdBannerVOLE(), banner.getNmBannerVOLE(), banner.getUrlBannerVOLE(), banner.getIdAreaBannerVOLE(), banner.getIdTipoBannerVOLE(), banner.getDsBannerVOLE(), banner.getIPBannerVOLE(), dtInicial, dtFinal, banner.getInContador());
			log.info("[BannerFacadeImpl:alterarBanner] [user:" + idUsuario + "] Método finalizado.");

			return;

		} catch (Exception ex) {
			log.error("[BannerFacadeImpl:alterarBanner] [exception] [user:" + idUsuario + "] " + ex.getMessage());
			throw (new FacadeException(ex));
		}
	}
}
