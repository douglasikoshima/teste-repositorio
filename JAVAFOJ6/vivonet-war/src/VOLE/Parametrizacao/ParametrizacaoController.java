package VOLE.Parametrizacao;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.vole.vo.VOLEManterMenuVODocument.VOLEManterMenuVO;

import com.indracompany.actions.AbstractAction;

@SuppressWarnings({"rawtypes","unchecked","unused"})
public class ParametrizacaoController extends AbstractAction {

	private static final long serialVersionUID = -2161237300534939757L;

	protected global.Global globalApp = new global.Global();

	private transient Logger log = new Logger("VOLE");

	private FormMenu formMenu = null;
	private int qtdItens = 0;
	private final int NUM_REG_PAG = 50;

	private StringBuffer queryPesquisa = new StringBuffer(1536);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("abaFiltro".equals(mapping.getParameter())) {
			return abaFiltro(mapping, form, request, response);
		} else if ("resultadoPesquisa".equals(mapping.getParameter())) {
			return resultadoPesquisa(mapping, form, request, response);
		} else if ("gravarParametro".equals(mapping.getParameter())) {
			return gravarParametro(mapping, form, request, response);
		} else if ("manterAcesso".equals(mapping.getParameter())) {
			return manterAcesso(mapping, form, request, response);
		} else if ("alterarStatusMenu".equals(mapping.getParameter())) {
			return alterarStatusMenu(mapping, form, request, response);
		} else if ("paginarPesquisa".equals(mapping.getParameter())) {
			return paginarPesquisa(mapping, form, request, response);
		} else if ("gerarArquivo".equals(mapping.getParameter())) {
			return gerarArquivo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaFiltro.jsp"
	 */
	protected ActionForward abaFiltro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){
		FormMenu form = (FormMenu)formParam;
		loadSelect();
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void loadSelect(){
		try{
			Collection opCanalDisp = new ArrayList();
			Collection opUFDisp    = new ArrayList();
			Collection opCartDisp  = new ArrayList();
			Collection opSegmDisp  = new ArrayList();
			Collection opIMenuDisp = new ArrayList();

			StringBuffer  query = new StringBuffer(ConstantesCRM.SVAZIO);
			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = null;

			//Carregamento das Opções de Canais
			query = new StringBuffer("select idcanal, nmcanal from apoio.canal where cdcanal = 'VOLE' ");

			rsVO = pesquisar.executar(query.toString());
			if(rsVO!=null && rsVO.getLinhas()!=null){
				for(int i=0;i<rsVO.getLinhas().getLinhaArray().length;i++){
					String id = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
					String lb = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
					Option option = new Option(id, lb);
					opCanalDisp.add(option);
				}
			}
			getFormMenu().setOpCanalDisp(opCanalDisp);

			//Carregamento das Opções de UF
			query = new StringBuffer(ConstantesCRM.SVAZIO);
			query.append("select ufo.idufoperadora, uf.sguf ");
			query.append("from apoio.uf uf, customer.ufoperadora ufo ");
			query.append("where uf.iduf = ufo.iduf ");
			query.append("order by uf.sguf ");

			rsVO = pesquisar.executar(query.toString());
			if(rsVO!=null && rsVO.getLinhas()!=null){
				for(int i=0;i<rsVO.getLinhas().getLinhaArray().length;i++){
					String id = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
					String lb = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
					Option option = new Option(id, lb);
					opUFDisp.add(option);
				}
			}
			getFormMenu().setOpUFDisp(opUFDisp);

			//Carregamento das Opções de Carteira
			query = new StringBuffer("select idtipocarteira, dstipocarteira from apoio.tipocarteira order by dstipocarteira ");

			rsVO = pesquisar.executar(query.toString());
			if(rsVO!=null && rsVO.getLinhas()!=null){
				for(int i=0;i<rsVO.getLinhas().getLinhaArray().length;i++){
					String id = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
					String lb = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
					Option option = new Option(id, lb);
					opCartDisp.add(option);
				}
			}
			getFormMenu().setOpCartDisp(opCartDisp);

			//Carregamento das Opções de Segmentacao
			query = new StringBuffer("select idsegmentacao, dssegmentacao from apoio.segmentacao order by idsegmentacao ");

			rsVO = pesquisar.executar(query.toString());
			if(rsVO!=null && rsVO.getLinhas()!=null){
				for(int i=0;i<rsVO.getLinhas().getLinhaArray().length;i++){
					String id = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
					String lb = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
					Option option = new Option(id, lb);
					opSegmDisp.add(option);
				}
			}
			getFormMenu().setOpSegmDisp(opSegmDisp);

			//Carregamento das Opções de Item Menu
			query = new StringBuffer(ConstantesCRM.SVAZIO);
			query.append("select im.iditemmenu, im.nmitem ");
			query.append("from acesso.itemmenu im, acesso.subsistema ss, acesso.sistema s ");
			query.append("where im.idsubsistema = ss.idsubsistema ");
			query.append("and ss.idsistema = s.idsistema ");
			query.append("and s.sgsistemaoperacional = 'VOLE' ");
			query.append("order by im.nmitem ");

			rsVO = pesquisar.executar(query.toString());
			if(rsVO!=null && rsVO.getLinhas()!=null){
				for(int i=0;i<rsVO.getLinhas().getLinhaArray().length;i++){
					String id = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
					String lb = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
					Option option = new Option(id, lb);
					opIMenuDisp.add(option);
				}
			}
			getFormMenu().setOpIMenuDisp(opIMenuDisp);

		}catch(Exception e){
			log.error("Exception::", e);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 */
	protected ActionForward resultadoPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){

		FormMenu form = (FormMenu)formParam;

		try{
			VOLEManterMenuVO manterMenuVO = VOLEManterMenuVO.Factory.newInstance();

			Connection conn = getConnection();

			//Monta a Query principal de Pesquisa
			StringBuffer  query = new StringBuffer(1536);
			query.append("select distinct rownum linha, ");
			query.append("       pimv.inativo, ");
			query.append("       pimv.iditemmenu, ");
			query.append("       decode(imh.iditemmenupai, '', 0, imh.iditemmenupai) as iditemmenupai, ");
			query.append("       (select nmitem from acesso.itemmenu where iditemmenu = imh.iditemmenupai) as nmpai, ");
			query.append("       itemmenu.nmitem, ");
			query.append("       pimv.idsegmentacao, ");
			query.append("       segm.dssegmentacao, ");
			query.append("       pimv.idtipocarteira, ");
			query.append("       tcart.dstipocarteira, ");
			query.append("       pimv.idufoperadora, ");
			query.append("       uf.sguf ");
			query.append("  from acesso.itemmenu itemmenu, ");
			query.append("       acesso.itemmenuhierarquia imh, ");
			query.append("       acesso.permissaoitemmenuvole pimv, ");
			query.append("       apoio.tipocarteira tcart, ");
			query.append("       apoio.segmentacao segm, ");
			query.append("       apoio.uf uf, ");
			query.append("       customer.ufoperadora ufo ");
			query.append(" where itemmenu.iditemmenu = pimv.iditemmenu ");
			query.append("   and itemmenu.iditemmenu = imh.iditemmenu(+) ");
			query.append("   and tcart.idtipocarteira = pimv.idtipocarteira ");
			query.append("   and ufo.idufoperadora = pimv.idufoperadora ");
			query.append("   and uf.iduf = ufo.iduf ");
			query.append("   and segm.idsegmentacao = pimv.idsegmentacao ");

			if(form.getIdSelUF()!=null && form.getIdSelUF().length>0){
				query.append("   and ufo.idufoperadora in (");
				for(int i=0;i<form.getIdSelUF().length;i++){
					query.append(form.getIdSelUF()[i]);
					if(i<form.getIdSelUF().length-1){
						query.append(",");
					}
				}
				query.append(") ");
			}

			if(form.getIdSelCart()!=null && form.getIdSelCart().length>0){
				query.append("   and tcart.idtipocarteira in (");
				for(int i=0;i<form.getIdSelCart().length;i++){
					query.append(form.getIdSelCart()[i]);
					if(i<form.getIdSelCart().length-1){
						query.append(",");
					}
				}
				query.append(") ");
			}

			if(form.getIdSelSegm()!=null && form.getIdSelSegm().length>0){
				query.append("   and segm.idsegmentacao in (");
				for(int i=0;i<form.getIdSelSegm().length;i++){
					query.append(form.getIdSelSegm()[i]);
					if(i<form.getIdSelSegm().length-1){
						query.append(",");
					}
				}
				query.append(") ");
			}

			if(form.getIdSelIMenu()!=null && form.getIdSelIMenu().length>0){
				query.append("   and itemmenu.iditemmenu in (");
				for(int i=0;i<form.getIdSelIMenu().length;i++){
					query.append(form.getIdSelIMenu()[i]);
					if(i<form.getIdSelIMenu().length-1){
						query.append(",");
					}
				}
				query.append(") ");
			}

			this.queryPesquisa = new StringBuffer(query.toString());

			//Monta a Query para a contagem de Itens que atendem a Pesquisa
			StringBuffer queryCont = new StringBuffer("select count(iditemmenu) as qtItens from (");
			queryCont.append(query.toString()).append(" )");

			PreparedStatement ps = conn.prepareStatement(queryCont.toString());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				qtdItens = rs.getInt("qtItens");

				manterMenuVO.setPgCur(ConstantesCRM.SONE);
				manterMenuVO.setPgIni(ConstantesCRM.SONE);

				int numPg = qtdItens / NUM_REG_PAG;
				if(numPg<10){
					if(numPg==0) {
						numPg = 1;
					}
					manterMenuVO.setPgFim(String.valueOf(numPg));
				}else{
					manterMenuVO.setPgFim("10");
				}
				manterMenuVO.setPgNum(String.valueOf(numPg));

				StringBuffer queryPesq = new StringBuffer("select * from (");
				queryPesq.append(query.toString()).append(" )");
				queryPesq.append(" where linha > 0 AND linha < 51 ");

				ps = conn.prepareStatement(queryPesq.toString());
				rs = ps.executeQuery();
				while(rs.next()){
					VOLEManterMenuVO.ListaItemMenu lista = manterMenuVO.addNewListaItemMenu();
					lista.setRowNum(rs.getString("linha"));
					lista.setInAtivo(rs.getString("inativo"));
					lista.setIdItemMenu(rs.getString("iditemmenu"));
					if(ConstantesCRM.SZERO.equals(rs.getString("iditemmenupai"))){
						lista.setNmItem("\\"+rs.getString("nmitem"));
					}else{
						lista.setNmItem("\\"+rs.getString("nmpai")+" \\"+rs.getString("nmitem"));
					}
					lista.setIdCanal(ConstantesCRM.SVAZIO);
					lista.setDsCanal("VOL-E");
					lista.setIdUfOperadora(rs.getString("idufoperadora"));
					lista.setSgUf(rs.getString("sguf"));
					lista.setIdTipoCarteira(rs.getString("idtipocarteira"));
					lista.setDsTipoCarteira(rs.getString("dstipocarteira"));
					lista.setIdSegmentacao(rs.getString("idsegmentacao"));
					lista.setDsSegmentacao(rs.getString("dssegmentacao"));
				}
			}else{
				qtdItens = 0;
			}

			setFormMenu(form);
			getFormMenu().setResultado(manterMenuVO);
			getFormMenu().setOffSet(ConstantesCRM.SONE);

			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(conn!=null) {
				conn.close();
			}

		}catch(Exception e){
			log.error("Exception::", e);
			getFormMenu().setResultado(VOLEManterMenuVO.Factory.newInstance());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward gravarParametro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<message>");

		FormMenu form = (FormMenu)formParam;

		try{
			String user = ConstantesCRM.getCurrentUser(request.getSession());

			Pesquisar pesquisar = new Pesquisar();

			StringBuffer  query = new StringBuffer(ConstantesCRM.SVAZIO);
			query.append("update apoio.parametro set dsvalorparametro = '").append(form.getTpBlqNVivo()).append("', ");
			query.append(" idusuarioalteracao = ").append(user).append(", dtultimaalteracao=sysdate ");
			query.append("where cdparametro = 'ACEITA_LOGIN_NAO_VIVO' ");
			Resultset rsVO = pesquisar.executar(query.toString());
			if(rsVO!=null && rsVO.getMsg()==null){
				sb.append("<msg>Parâmetros de acesso ao VOLE configurados com sucesso.</msg>");

				query = new StringBuffer("");
				query.append("update apoio.parametro set dsvalorparametro = '").append(form.getTpBlqJaNVivo()).append("', ");
				query.append(" idusuarioalteracao = ").append(user).append(", dtultimaalteracao=sysdate ");
				query.append("where cdparametro = 'ACEITA_LOGIN_NAO_VIVO_NAO_PRIMEIRO_ACESSO' ");
				rsVO = pesquisar.executar(query.toString());
				if(rsVO!=null && rsVO.getMsg()!=null){
					sb.append("<msg>").append(rsVO.getMsg()).append("</msg>");
				}

				if(ConstantesCRM.SFALSE.equals(form.getTpBlqNVivo())){

					query = new StringBuffer()
					.append("UPDATE customer.pessoagestorsenha pgs ")
					.append("   SET idtipostatussenha = 4 ")
					.append(" WHERE pgs.IDPESSOAGESTOR IN (SELECT pg.IDPESSOAGESTOR ")
					.append("                                FROM customer.pessoagestor pg ")
					.append("                               WHERE NOT EXISTS (SELECT 1  ")
					.append("                                                   FROM linha.linhabase lb, ")
					.append("                                                        apoio.arearegistro ar ")
					.append("                                                  WHERE (ar.cdarearegistro = SUBSTR(pg.NRTELEFONECELULARVIVO,1,2) ")
					.append("                                                    AND lb.nrlinha = SUBSTR(pg.NRTELEFONECELULARVIVO, 3, length(pg.NRTELEFONECELULARVIVO)) ")
					.append("                                                     OR ar.cdarearegistro = SUBSTR(pg.NRTELEFONEFIXO,1,2) ")
					.append("                                                    AND lb.nrlinha = SUBSTR(pg.NRTELEFONEFIXO, 3, length(pg.NRTELEFONEFIXO)) ")
					.append("                                                    AND (pg.NRTELEFONEFIXO IS NULL OR pg.NRTELEFONEFIXO IS NULL) ")
					.append("                                                        ) ")
					.append("                                                 ) ")
					.append("                             ) ");

					rsVO = pesquisar.executar(query.toString());

					if(rsVO!=null && rsVO.getMsg()!=null){
						sb.append("<msg>").append(rsVO.getMsg()).append("</msg>");
					}
				}

			}else{
				sb.append("<msg>").append(rsVO.getMsg()).append("</msg>");
			}

		}catch(Exception e){
			log.error("ParametrizacaoController::gravarParametro", e);
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
	 * @jpf:forward name="success" path="ManterAcesso.jsp"
	 */
	protected ActionForward manterAcesso(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){

		FormMenu form = (FormMenu)formParam;

		try{
			Pesquisar pesquisar = new Pesquisar();
			StringBuffer query = new StringBuffer("select param.dsvalorparametro, usu.nmloginusuario, to_char(param.dtultimaalteracao,'dd/mm/yyyy hh24:mi:ss') dtultimaalteracao ");
			query.append("from acesso.usuario usu, apoio.parametro param ");
			query.append("where usu.idpessoausuario = param.idusuarioalteracao ");
			query.append("and param.cdparametro = 'ACEITA_LOGIN_NAO_VIVO' ");

			Resultset rsVO = pesquisar.executar(query.toString());
			if(rsVO!=null && rsVO.getLinhas()!=null && rsVO.getLinhas().getLinhaArray().length>0){
				String bool = rsVO.getLinhas().getLinhaArray(0).getValorArray(0);
				getFormMenu().setTpBlqNVivo(bool);
				getFormMenu().setNmLogin(rsVO.getLinhas().getLinhaArray(0).getValorArray(1));
				getFormMenu().setDtUltimaAlt(rsVO.getLinhas().getLinhaArray(0).getValorArray(2));

			}else{
				getFormMenu().setTpBlqNVivo(ConstantesCRM.STRUE);
			}
			query = new StringBuffer("select dsvalorparametro from apoio.parametro where cdparametro = 'ACEITA_LOGIN_NAO_VIVO_NAO_PRIMEIRO_ACESSO'");
			rsVO = pesquisar.executar(query.toString());
			if(rsVO!=null && rsVO.getLinhas()!=null && rsVO.getLinhas().getLinhaArray().length>0){
				String bool = rsVO.getLinhas().getLinhaArray(0).getValorArray(0);
				getFormMenu().setTpBlqJaNVivo(bool);
			}else{
				getFormMenu().setTpBlqJaNVivo(ConstantesCRM.STRUE);
			}

		}catch(Exception e){
			log.error("ParametrizacaoController::gravarParametro", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward alterarStatusMenu(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<message>");

		FormMenu form = (FormMenu)formParam;

		try{
			Pesquisar pesquisar = new Pesquisar();

			StringBuffer  query = new StringBuffer(ConstantesCRM.SVAZIO);
			query.append("update acesso.permissaoitemmenuvole set inAtivo = ");

			String inAtivo = request.getParameter("inAtivo");
			if(inAtivo!=null && !ConstantesCRM.SVAZIO.equals(inAtivo)){
				if(ConstantesCRM.SONE.equals(inAtivo)){
					query.append("0 ");
				}else{
					query.append("1 ");
				}
			}else{
				throw new Exception("Não foi possivel determinar o valor anterior para o campo inAtivo");
			}
			query.append(" where ");

			String idItemMenu = request.getParameter("idItemMenu");
			if(idItemMenu!=null && !ConstantesCRM.SVAZIO.equals(idItemMenu)){
				query.append("idItemMenu = ").append(idItemMenu);
			}else{
				throw new Exception("Não foi possivel receber o valor do campo idItemMenu");
			}

			String idSegmentacao = request.getParameter("idSegmentacao");
			if(idSegmentacao!=null && !ConstantesCRM.SVAZIO.equals(idSegmentacao)){
				query.append(" and idSegmentacao = ").append(idSegmentacao);
			}

			String idUf = request.getParameter("idUf");
			if(idUf!=null && !ConstantesCRM.SVAZIO.equals(idUf)){
				query.append(" and idufoperadora = ").append(idUf);
			}

			String idTipoCarteira = request.getParameter("idTipoCarteira");
			if(idTipoCarteira!=null && !ConstantesCRM.SVAZIO.equals(idTipoCarteira)){
				query.append(" and idTipoCarteira = ").append(idTipoCarteira);
			}

			Resultset rsVO = pesquisar.executar(query.toString());
			if(rsVO!=null && rsVO.getMsg()==null){
				sb.append("<msg>success</msg>");
			}else{
				sb.append("<msg>").append(rsVO.getMsg()).append("</msg>");
			}

		}catch(Exception e){
			log.error("ParametrizacaoController::alterarStatusMenu", e);
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
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 */
	protected ActionForward paginarPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){

		FormMenu form = (FormMenu)formParam;

		try{
			Connection conn = null;

			int offset = 1;
			if(form.getOffSet()!=null && !ConstantesCRM.SVAZIO.equals(form.getOffSet())){
				offset = Integer.parseInt(form.getOffSet());
			}
			int iniLin = 0;
			int fimLin = 51;
			int iniPg  = Integer.parseInt(form.getResultado().getPgIni());
			int fimPg  = Integer.parseInt(form.getResultado().getPgFim());
			int curPg  = Integer.parseInt(form.getResultado().getPgCur());
			int numPg  = Integer.parseInt(form.getResultado().getPgNum());
			//int numPg  = qtdItens / NUM_REG_PAG;
			if(numPg==0) {
				numPg = 1;
			}

			if(offset>0){
				curPg = offset;
				fimLin = (offset * NUM_REG_PAG) + 1;
				iniLin = (fimLin - NUM_REG_PAG) - 1;

			}else{
				curPg  = curPg - 1;
				offset = curPg;
				fimLin = (offset * NUM_REG_PAG) + 1;
				iniLin = (fimLin - NUM_REG_PAG) - 1;
			}

			if(curPg>fimPg){
				iniPg = curPg;
				fimPg = iniPg + 9;
			}else{
				int resto = (curPg%10);
				resto = resto==0?9:resto;
				iniPg = curPg-resto;
				fimPg = iniPg + 9;
			}

			if(iniPg==0) {
				iniPg = 1;
			}
			if(fimPg>numPg){
				fimPg = numPg;
			}

			conn = getConnection();

			VOLEManterMenuVO manterMenuVO = VOLEManterMenuVO.Factory.newInstance();

			manterMenuVO.setPgCur(String.valueOf(curPg));
			manterMenuVO.setPgIni(String.valueOf(iniPg));
			manterMenuVO.setPgFim(String.valueOf(fimPg));
			manterMenuVO.setPgNum(String.valueOf(numPg));

			StringBuffer queryPesq = new StringBuffer(1536);
			queryPesq.append("select * from (");
			queryPesq.append(queryPesquisa.toString()).append(" )");
			queryPesq.append(" where linha > ").append(iniLin).append(" AND linha < ").append(fimLin).append(" ");

			PreparedStatement ps = conn.prepareStatement(queryPesq.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				VOLEManterMenuVO.ListaItemMenu lista = manterMenuVO.addNewListaItemMenu();

				lista.setRowNum(rs.getString("linha"));
				lista.setInAtivo(rs.getString("inativo"));
				lista.setIdItemMenu(rs.getString("iditemmenu"));
				if(ConstantesCRM.SZERO.equals(rs.getString("iditemmenupai"))){
					lista.setNmItem("\\"+rs.getString("nmitem"));
				}else{
					lista.setNmItem("\\"+rs.getString("nmpai")+" \\"+rs.getString("nmitem"));
				}
				lista.setIdCanal(ConstantesCRM.SVAZIO);
				lista.setDsCanal("VOL-E");
				lista.setIdUfOperadora(rs.getString("idufoperadora"));
				lista.setSgUf(rs.getString("sguf"));
				lista.setIdTipoCarteira(rs.getString("idtipocarteira"));
				lista.setDsTipoCarteira(rs.getString("dstipocarteira"));
				lista.setIdSegmentacao(rs.getString("idsegmentacao"));
				lista.setDsSegmentacao(rs.getString("dssegmentacao"));
			}

			getFormMenu().setResultado(manterMenuVO);
			getFormMenu().setOffSet(form.getOffSet());

			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(conn!=null) {
				conn.close();
			}

		}catch(Exception e){
			log.error("Exception::", e);
			getFormMenu().setResultado(VOLEManterMenuVO.Factory.newInstance());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward gerarArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){
		FormMenu form = (FormMenu) formParam;
		try {
			PrintWriter out = response.getWriter();

			StringBuffer relatorio = new StringBuffer(32768);
			String tipo = request.getParameter("tpRelNVivo");

			if(tipo!=null && tipo!=ConstantesCRM.SVAZIO){
				//Define o nome do Arquivo
				response.addHeader("Content-Disposition","attachment; filename=");
				response.addHeader("Content-Type","application/force-download");
				response.addHeader("Content-Transfer-Encoding","binary");
				response.addHeader("Pragma","no-cache");
				response.addHeader("Expires","0");

				out.print(relatorio);
			}
		}catch(Exception e){
			response.addHeader("Content-Type","text/html");
			response.addHeader("Pragma","no-cache");
			response.addHeader("Expires","0");

		}finally{
		}
		return null;
	}

	public static class FormMenu extends ActionForm {

		private static final long serialVersionUID = -7893111509562771800L;

		private String dtUltimaAlt  = ConstantesCRM.SVAZIO;
		private String nmLogin      = ConstantesCRM.SVAZIO;
		private String offSet;

		private String pgCur        = ConstantesCRM.SZERO;
		private String pgFim        = ConstantesCRM.SZERO;
		private String pgIni        = ConstantesCRM.SZERO;
		private String tpBlqJaNVivo = ConstantesCRM.SVAZIO;
		private String tpRelNVivo   = ConstantesCRM.SVAZIO;
		private String tpBlqNVivo   = ConstantesCRM.SVAZIO;

		private Collection opIMenuDisp;
		private Collection opSegmDisp;
		private Collection opCartDisp;
		private Collection opUFDisp;
		private Collection opCanalDisp;

		private String[] idSelIMenu;
		private String[] idSelSegm;
		private String[] idSelCart;
		private String[] idSelUF;
		private String[] idSelCanal;

		private String[] idDispIMenu;
		private String[] idDispSegm;
		private String[] idDispCart;
		private String[] idDispUF;
		private String[] idDispCanal;

		private VOLEManterMenuVO resultado;

		public VOLEManterMenuVO getResultado(){
			if(this.resultado==null){
				this.resultado = VOLEManterMenuVO.Factory.newInstance();
				this.resultado.setPgCur(ConstantesCRM.SZERO);
				this.resultado.setPgFim(ConstantesCRM.SZERO);
				this.resultado.setPgIni(ConstantesCRM.SZERO);
				this.resultado.setPgNum(ConstantesCRM.SZERO);
			}
			return this.resultado;
		}

		public void setResultado(VOLEManterMenuVO resultado){
			this.resultado = resultado;
		}

		public void setIdDispCanal(String[] idDispCanal){
			this.idDispCanal = idDispCanal;
		}

		public String[] getIdDispCanal(){
			if(this.idDispCanal == null){
				this.idDispCanal = new String[0];
			}
			return this.idDispCanal;
		}

		public void setIdDispUF(String[] idDispUF){
			this.idDispUF = idDispUF;
		}

		public String[] getIdDispUF(){
			if(this.idDispUF == null){
				this.idDispUF = new String[0];
			}
			return this.idDispUF;
		}

		public void setIdDispCart(String[] idDispCart){
			this.idDispCart = idDispCart;
		}

		public String[] getIdDispCart(){
			if(this.idDispCart == null){
				this.idDispCart = new String[0];
			}
			return this.idDispCart;
		}

		public void setIdDispSegm(String[] idDispSegm){
			this.idDispSegm = idDispSegm;
		}

		public String[] getIdDispSegm(){
			if(this.idDispSegm == null){
				this.idDispSegm = new String[0];
			}
			return this.idDispSegm;
		}

		public void setIdDispIMenu(String[] idDispIMenu){
			this.idDispIMenu = idDispIMenu;
		}

		public String[] getIdDispIMenu(){
			if(this.idDispIMenu == null){
				this.idDispIMenu = new String[0];
			}
			return this.idDispIMenu;
		}

		public void setIdSelCanal(String[] idSelCanal){
			this.idSelCanal = idSelCanal;
		}

		public String[] getIdSelCanal(){
			if(this.idSelCanal == null){
				this.idSelCanal = new String[0];
			}
			return this.idSelCanal;
		}

		public void setIdSelUF(String[] idSelUF){
			this.idSelUF = idSelUF;
		}

		public String[] getIdSelUF(){
			if(this.idSelUF == null){
				this.idSelUF = new String[0];
			}
			return this.idSelUF;
		}

		public void setIdSelCart(String[] idSelCart){
			this.idSelCart = idSelCart;
		}

		public String[] getIdSelCart(){
			if(this.idSelCart == null){
				this.idSelCart = new String[0];
			}
			return this.idSelCart;
		}

		public void setIdSelSegm(String[] idSelSegm){
			this.idSelSegm = idSelSegm;
		}

		public String[] getIdSelSegm(){
			if(this.idSelSegm == null){
				this.idSelSegm = new String[0];
			}
			return this.idSelSegm;
		}

		public void setIdSelIMenu(String[] idSelIMenu){
			this.idSelIMenu = idSelIMenu;
		}

		public String[] getIdSelIMenu(){
			if(this.idSelIMenu == null){
				this.idSelIMenu = new String[0];
			}
			return this.idSelIMenu;
		}

		public void setOpCanalDisp(Collection opCanalDisp){
			this.opCanalDisp = opCanalDisp;
		}

		public Collection getOpCanalDisp(){
			return this.opCanalDisp;
		}

		public void setOpUFDisp(Collection opUFDisp){
			this.opUFDisp = opUFDisp;
		}

		public Collection getOpUFDisp(){
			return this.opUFDisp;
		}

		public void setOpCartDisp(Collection opCartDisp){
			this.opCartDisp = opCartDisp;
		}

		public Collection getOpCartDisp(){
			return this.opCartDisp;
		}

		public void setOpSegmDisp(Collection opSegmDisp){
			this.opSegmDisp = opSegmDisp;
		}

		public Collection getOpSegmDisp(){
			return this.opSegmDisp;
		}

		public void setOpIMenuDisp(Collection opIMenuDisp){
			this.opIMenuDisp = opIMenuDisp;
		}

		public Collection getOpIMenuDisp(){
			return this.opIMenuDisp;
		}

		public void setTpBlqNVivo(String tpBlqNVivo){
			this.tpBlqNVivo = tpBlqNVivo;
		}

		public String getTpBlqNVivo(){
			return this.tpBlqNVivo;
		}

		public void setTpRelNVivo(String tpRelNVivo){
			this.tpRelNVivo = tpRelNVivo;
		}

		public String getTpRelNVivo(){
			return this.tpRelNVivo;
		}

		public void setTpBlqJaNVivo(String tpBlqJaNVivo){
			this.tpBlqJaNVivo = tpBlqJaNVivo;
		}

		public String getTpBlqJaNVivo(){
			return this.tpBlqJaNVivo;
		}

		public void setPgIni(String pgIni){
			this.pgIni = pgIni;
		}

		public String getPgIni(){
			return this.pgIni;
		}

		public void setPgFim(String pgFim){
			this.pgFim = pgFim;
		}

		public String getPgFim(){
			return this.pgFim;
		}

		public void setPgCur(String pgCur){
			this.pgCur = pgCur;
		}

		public String getPgCur(){
			return this.pgCur;
		}

		public void setOffSet(String offSet){
			this.offSet = offSet;
		}

		public String getOffSet(){
			return this.offSet;
		}

		public void setNmLogin(String nmLogin){
			this.nmLogin = nmLogin;
		}

		public String getNmLogin(){
			return this.nmLogin;
		}

		public void setDtUltimaAlt(String dtUltimaAlt){
			this.dtUltimaAlt = dtUltimaAlt;
		}

		public String getDtUltimaAlt(){
			return this.dtUltimaAlt;
		}
	}

	public FormMenu getFormMenu(){
		if(this.formMenu == null){
			this.formMenu = new FormMenu();
		}
		return this.formMenu;
	}

	public void setFormMenu(FormMenu formMenu){
		this.formMenu = formMenu;
	}

	public class Option {
		private String id;
		private String name;

		public Option(){
		}

		public Option(String id, String name){
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

	private Connection getConnection(){
		Hashtable    ht = new Hashtable();
		Context     ctx = null;
		Connection conn = null;
		try{
			ht.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			ctx = new InitialContext(ht);
			javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("jdbc.OracleDS");
			conn = ds.getConnection();
            while(conn.isClosed()) {
            	conn = ds.getConnection();
            }
			conn.setAutoCommit(false);

		}catch(SQLException e){
			conn = null;
			log.error("SQLException::", e);

		}catch(NamingException e){
			conn = null;
			log.error("NamingException::", e);

		}
		return conn;
	}
}
