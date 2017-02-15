package admsistemas.dataCleasing;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

public class DataCleasingController extends AbstractAction {

	private static final long serialVersionUID = 6088885154328010890L;

	private FiltroForm filtroForm = new FiltroForm();
	private transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);

		} else if ("verificaFiltro".equals(mapping.getParameter())) {
			return verificaFiltro(mapping, form, request, response);

		} else if ("gravaFiltro".equals(mapping.getParameter())) {
			return gravaFiltro(mapping, form, request, response);

		} else if ("listarCodArea".equals(mapping.getParameter())) {
			return listarCodArea(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) {
		try {
			Collection<Option> optionsUF = new ArrayList<Option>();

			StringBuffer query = new StringBuffer("select uf.iduf, uf.sguf ");
			query.append("from apoio.uf uf, customer.ufoperadora ufo ");
			query.append("where uf.iduf = ufo.iduf  ");
			query.append("and uf.iduf > 0 ");
			query.append("order by uf.sguf ");

			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = pesquisar.executar(query.toString());

			if (rsVO != null && rsVO.getLinhas() != null) {
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					String id = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
					String lb = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
					Option option = new Option(id, lb);
					optionsUF.add(option);
				}
				getFiltroForm().setOptionsUFDisp(optionsUF);
			} else {
				getFiltroForm().setOptionsUFDisp(new ArrayList<Option>());
			}

		} catch (Exception e) {
			log.error("DataCleasingController::begin", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	public ActionForward verificaFiltro(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {

		StringBuffer sb = new StringBuffer();
		sb.append("<message>");
		try {
			StringBuffer query = new StringBuffer("select count(intipofiltro) qtd from customer.dc_filtroextracao");

			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = pesquisar.executar(query.toString());

			if (rsVO != null && rsVO.getLinhas() != null) {
				sb.append("<qtd>").append(rsVO.getLinhas().getLinhaArray(0).getValorArray(0)).append("</qtd>");
			}

		} catch (Exception e) {
			log.error("DataCleasingController::verificaFiltro", e);

		} finally {
			sb.append("</message>");
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(sb.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		}
		return null;
	}

	/**
	 * @jpf:action
	 */
	public ActionForward gravaFiltro(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {

		FiltroForm form = (FiltroForm) formParam;
		StringBuffer sb = new StringBuffer();
		sb.append("<message>");
		try{
			String user = ConstantesCRM.getCurrentUser(request.getSession());

			Pesquisar pesquisar = new Pesquisar();
			StringBuffer query = new StringBuffer("delete from customer.dc_filtroextracao");
			pesquisar.executar(query.toString());

			query = new StringBuffer();
			if(form.getTpFiltro()!=null && ConstantesCRM.SONE.equals(form.getTpFiltro())){
				String[] idSelUF  = form.getIdSelUF();
				String[] idSelDDD = form.getIdSelDDD();

				if(idSelUF.length>0){
					if(idSelDDD.length>0){
						query.append("insert into customer.dc_filtroextracao (intipofiltro,iduf,idarearegistro,dtatualizacao,idusuarioalteracao) ");
						query.append("select 1 intipofiltro, uf.iduf, ar.idarearegistro,");
						if(form.getDtAtualiza()!=null){
							query.append("to_date('").append(form.getDtAtualiza()).append("','dd/mm/yyyy') ");
						}else{
							query.append("null ");
						}
						query.append("dtatualizacao, ");
						query.append(user).append(" idusuarioalteracao ");
						query.append("from apoio.arearegistro ar, customer.ufoperadora ufo, apoio.uf uf ");
						query.append("where ar.idufoperadora = ufo.idufoperadora ");
						query.append("and ufo.iduf = uf.iduf ");
						query.append("and uf.iduf in (");
						for(int i=0;i<idSelUF.length;i++){
							query.append(idSelUF[i]);
							if(i<idSelUF.length-1){
								query.append(",");
							}
						}
						query.append(") ");
						query.append("and ar.idarearegistro in (");
						for(int i=0;i<idSelDDD.length;i++){
							query.append(idSelDDD[i]);
							if(i<idSelDDD.length-1){
								query.append(",");
							}
						}
						query.append(") ");

						Resultset rsVO = pesquisar.executar(query.toString());
						if(rsVO!=null && rsVO.getMsg()==null){
							sb.append("<msg>Inclusão efetuada com sucesso!</msg>");
						}else{
							sb.append("<msg>").append(rsVO.getMsg()).append("</msg>");
						}

					}else{
						for(int i=0;i<idSelUF.length;i++){
							query.append("insert into customer.dc_filtroextracao (intipofiltro, iduf, dtatualizacao, idusuarioalteracao) ");
							query.append("values (1,").append(idSelUF[i]).append(",");
							if(form.getDtAtualiza()!=null){
								query.append("to_date('").append(form.getDtAtualiza()).append("','dd/mm/yyyy'), ");
							}else{
								query.append("null, ");
							}
							query.append(user).append(") ");

							Resultset rsVO = pesquisar.executar(query.toString());
							if(rsVO!=null && rsVO.getMsg()==null){
								sb.append("<msg>Inclusão efetuada com sucesso!</msg>");
							}else{
								sb.append("<msg>").append(rsVO.getMsg()).append("</msg>");
							}
						}
					}
				}
			}else if(form.getTpFiltro()!=null && "2".equals(form.getTpFiltro())){
				query.append("insert into customer.dc_filtroextracao (intipofiltro,dtatualizacao,nrcepinicial,nrcepfinal,idusuarioalteracao)");
				query.append(" values (2,");
				if(form.getDtAtualiza()!=null){
					query.append("to_date('").append(form.getDtAtualiza()).append("','dd/mm/yyyy'),");
				}else{
					query.append("null,");
				}
				query.append(form.getNrCEPIni()).append(",").append(form.getNrCEPFim()).append(",").append(user).append(") ");

				Resultset rsVO = pesquisar.executar(query.toString());
				if(rsVO!=null && rsVO.getMsg()==null){
					sb.append("<msg>Inclusão efetuada com sucesso!</msg>");
				}else{
					sb.append("<msg>").append(rsVO.getMsg()).append("</msg>");
				}

			}else if(form.getTpFiltro()!=null && "0".equals(form.getTpFiltro())){
				query.append("insert into customer.dc_filtroextracao (intipofiltro,idusuarioalteracao)");
				query.append(" values (0,").append(user).append(") ");

				Resultset rsVO = pesquisar.executar(query.toString());
				if(rsVO!=null && rsVO.getMsg()==null){
					sb.append("<msg>Inclusão efetuada com sucesso!</msg>");
				}else{
					sb.append("<msg>").append(rsVO.getMsg()).append("</msg>");
				}
			}

		}catch(Exception e){
			log.error("DataCleasingController::gravaFiltro", e);
			sb.append("<msg>").append(e.getMessage()).append("</msg>");

		}finally{
			sb.append("</message>");
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(sb.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		}
		return null;
	}

	/**
	 * @jpf:action
	 */
	public ActionForward listarCodArea(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {
		FiltroForm form = (FiltroForm) formParam;
		StringBuffer sb = new StringBuffer();
		sb.append("<select>");
		try {
			StringBuffer query = new StringBuffer();
			query.append("select ar.idarearegistro, ar.cdarearegistro  ");
			query.append("from apoio.arearegistro ar, customer.ufoperadora ufo, apoio.uf uf ");
			query.append("where ar.idufoperadora = ufo.idufoperadora  ");
			query.append("and ufo.iduf = uf.iduf and uf.iduf > 0  ");
			query.append("and uf.iduf in (");

			if (form.getIdSelUF() != null) {
				String[] idSelUF = form.getIdSelUF();

				if (idSelUF != null && idSelUF.length > 0) {
					for (int i = 0; i < idSelUF.length; i++) {
						query.append(idSelUF[i]);
						if (i < idSelUF.length - 1) {
							query.append(",");
						}
					}
					query.append(") ");
					query.append("order by ar.cdarearegistro ");

					Pesquisar pesquisar = new Pesquisar();
					Resultset rsVO = pesquisar.executar(query.toString());

					if (rsVO != null && rsVO.getLinhas() != null) {
						for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
							String id = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
							String lb = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
							sb.append("<item>");
							sb.append("<id>").append(id).append("</id>");
							sb.append("<ds>").append(lb).append("</ds>");
							sb.append("</item>");
						}
					}
				}
			}

		} catch (Exception e) {
			log.error("DataCleasingController::listarCodArea", e);

		} finally {
			sb.append("</select>");
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(sb.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		}
		return null;
	}

	public FiltroForm getFiltroForm() {
		if (this.filtroForm == null) {
			this.filtroForm = new FiltroForm();
		}
		return this.filtroForm;
	}

	public static class FiltroForm extends ActionForm {

		private static final long serialVersionUID = 2013033577864294457L;

		private String dtAtualiza = ConstantesCRM.SVAZIO;
		private String nrCEPFim = ConstantesCRM.SVAZIO;
		private String nrCEPIni = ConstantesCRM.SVAZIO;
		private String[] idSelDDD;
		private String idDispDDD = ConstantesCRM.SVAZIO;
		private String[] idSelUF;
		private String idDispUF = ConstantesCRM.SVAZIO;
		private String tpFiltro = ConstantesCRM.SVAZIO;
		private Collection<Option> optionsUFDisp;
		private Collection<Option> optionsCdAreaDisp;

		public FiltroForm() {
		}

		public void setTpFiltro(String tpFiltro) {
			this.tpFiltro = tpFiltro;
		}

		public String getTpFiltro() {
			return this.tpFiltro;
		}

		public void setIdDispUF(String idDispUF) {
			this.idDispUF = idDispUF;
		}

		public String getIdDispUF() {
			return this.idDispUF;
		}

		public void setIdSelUF(String[] idSelUF) {
			this.idSelUF = idSelUF;
		}

		public String[] getIdSelUF() {
			if (this.idSelUF == null || this.idSelUF.length == 0) {
				this.idSelUF = new String[0];
			}
			return this.idSelUF;
		}

		public void setIdDispDDD(String idDispDDD) {
			this.idDispDDD = idDispDDD;
		}

		public String getIdDispDDD() {
			return this.idDispDDD;
		}

		public void setIdSelDDD(String[] idSelDDD) {
			this.idSelDDD = idSelDDD;
		}

		public String[] getIdSelDDD() {
			if (this.idSelDDD == null || this.idSelDDD.length == 0) {
				this.idSelDDD = new String[0];
			}
			return this.idSelDDD;
		}

		public void setNrCEPIni(String nrCEPIni) {
			this.nrCEPIni = nrCEPIni;
		}

		public String getNrCEPIni() {
			return this.nrCEPIni;
		}

		public void setNrCEPFim(String nrCEPFim) {
			this.nrCEPFim = nrCEPFim;
		}

		public String getNrCEPFim() {
			return this.nrCEPFim;
		}

		public void setDtAtualiza(String dtAtualiza) {
			this.dtAtualiza = dtAtualiza;
		}

		public String getDtAtualiza() {
			return this.dtAtualiza;
		}

		public void setOptionsUFDisp(Collection<Option> optionsUFDisp) {
			this.optionsUFDisp = optionsUFDisp;
		}

		public Collection<Option> getOptionsUFDisp() {
			return this.optionsUFDisp;
		}

		public void setOptionsCdAreaDisp(Collection<Option> optionsCdAreaDisp) {
			this.optionsCdAreaDisp = optionsCdAreaDisp;
		}

		public Collection<Option> getOptionsCdAreaDisp() {
			return this.optionsCdAreaDisp;
		}
	}

	public class Option {

		private String id;
		private String name;

		public Option() {
		}

		public Option(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
