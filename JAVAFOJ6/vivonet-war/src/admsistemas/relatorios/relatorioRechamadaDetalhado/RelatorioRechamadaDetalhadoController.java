package admsistemas.relatorios.relatorioRechamadaDetalhado;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.ItDocument.It;

import com.indracompany.actions.AbstractAction;

public class RelatorioRechamadaDetalhadoController extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private RelatorioForm relatorioForm = new RelatorioForm();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("clean".equals(mapping.getParameter())) {
			return clean(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("exportar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	public ActionForward begin(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) {
		
        getRelatorioForm().setListaDDD(getListaDDD());
        getRelatorioForm().setListaFornecedor(getListaFornecedor());
        getRelatorioForm().setListaTipoLinha(getListaTipoLinha());
        getRelatorioForm().setListaMotivoRecontato(getListaMotivoRecontato());
        getRelatorioForm().setListaSegmento(getListaSegmento());
        getRelatorioForm().setListaSite(getListaSite());
        getRelatorioForm().setListaGrupo(getListaGrupo());		
		
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW,this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}
	
	
	
	 private Disponivel getListaGrupo(){
         String query = "select idgrupo, nmgrupo from  acesso.grupo order by nmgrupo";
         Pesquisar pesquisar = new Pesquisar();
         Resultset rsVO = pesquisar.executar(query);
         Disponivel listaTpLinha = Disponivel.Factory.newInstance();
         for(int j=0;j<rsVO.getLinhas().getLinhaArray().length;j++){
             Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
             It it = listaTpLinha.addNewIt();
             it.setId(linha.getValorArray(0));
             it.setDs(linha.getValorArray(1));
         }
         return listaTpLinha;
     }         
 
 
private Disponivel getListaSegmento(){
     String query = "select idsegmentacao, dssegmentacao from apoio.segmentacao";
     Pesquisar pesquisar = new Pesquisar();
     Resultset rsVO = pesquisar.executar(query);
     Disponivel listaTpLinha = Disponivel.Factory.newInstance();
     for(int j=0;j<rsVO.getLinhas().getLinhaArray().length;j++){
         Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
         It it = listaTpLinha.addNewIt();
         it.setId(linha.getValorArray(0));
         it.setDs(linha.getValorArray(1));
     }
     return listaTpLinha;
 }          
 
 private Disponivel getListaDDD(){
     String query = "select idarearegistro, CDAREAREGISTRO||' - '||nmarearegistro from apoio.arearegistro";
     Pesquisar pesquisar = new Pesquisar();
     Resultset rsVO = pesquisar.executar(query);
     Disponivel listaTpLinha = Disponivel.Factory.newInstance();
     for(int j=0;j<rsVO.getLinhas().getLinhaArray().length;j++){
         Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
         It it = listaTpLinha.addNewIt();
         it.setId(linha.getValorArray(0));
         it.setDs(linha.getValorArray(1));
     }
     return listaTpLinha;
 }         
 
 private Disponivel getListaTipoLinha(){
     String query = "select idtipolinha, dstipolinha from apoio.tipolinha order by dstipolinha";
     Pesquisar pesquisar = new Pesquisar();
     Resultset rsVO = pesquisar.executar(query);
     Disponivel listaTpLinha = Disponivel.Factory.newInstance();
     for(int j=0;j<rsVO.getLinhas().getLinhaArray().length;j++){
         Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
         It it = listaTpLinha.addNewIt();
         it.setId(linha.getValorArray(0));
         it.setDs(linha.getValorArray(1));
     }
     return listaTpLinha;
 }     
 
 private Disponivel getListaMotivoRecontato(){
     String query = "select idmotivorecontato, nmmotivorecontato from contatoadm.motivorecontato";
     Pesquisar pesquisar = new Pesquisar();
     Resultset rsVO = pesquisar.executar(query);
     Disponivel listaTpLinha = Disponivel.Factory.newInstance();
     for(int j=0;j<rsVO.getLinhas().getLinhaArray().length;j++){
         Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
         It it = listaTpLinha.addNewIt();
         it.setId(linha.getValorArray(0));
         it.setDs(linha.getValorArray(1));
     }
     return listaTpLinha;
 }       
 
 
 private Disponivel getListaFornecedor(){
     String query = "select idfornecedorconsultoratd, DSFORNECEDORCONSULTORATD from apoio.fornecedorconsultoratd ";
     Pesquisar pesquisar = new Pesquisar();
     Resultset rsVO = pesquisar.executar(query);
     Disponivel listaTpLinha = Disponivel.Factory.newInstance();
     for(int j=0;j<rsVO.getLinhas().getLinhaArray().length;j++){
         Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
         It it = listaTpLinha.addNewIt();
         it.setId(linha.getValorArray(0));
         it.setDs(linha.getValorArray(1));
     }
     return listaTpLinha;
 }       
 
 private Disponivel getListaSite(){
     String query = "select idsiteconsultoratd, dssiteconsultoratd from apoio.siteconsultoratd";
     Pesquisar pesquisar = new Pesquisar();
     Resultset rsVO = pesquisar.executar(query);
     Disponivel listaTpLinha = Disponivel.Factory.newInstance();
     for(int j=0;j<rsVO.getLinhas().getLinhaArray().length;j++){
         Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
         It it = listaTpLinha.addNewIt();
         it.setId(linha.getValorArray(0));
         it.setDs(linha.getValorArray(1));
     }
     return listaTpLinha;
 }      	
	

	public ActionForward exportar(ActionMapping mapping, ActionForm formParam,
			HttpServletRequest request, HttpServletResponse response) {


		return mapping.findForward(ConstantesCRM.SUCCESS);
	} 
	
	

	public ActionForward clean(ActionMapping mapping, ActionForm formParam,
			HttpServletRequest request, HttpServletResponse response) {
		// Tempo de atualização do relatorio

		Resultset rsVO = Resultset.Factory.newInstance();
		rsVO.addNewLinhas();

		getRelatorioForm().setDataInicio("-1");
		getRelatorioForm().setDataTermino("-1");
		getRelatorioForm().setResultset(rsVO);

		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public ActionForward pesquisar(ActionMapping mapping, ActionForm formParam,
			HttpServletRequest request, HttpServletResponse response) {

		String dataInicio      = request.getParameter("dataInicio");
        String dataTermino     = request.getParameter("dataTermino");
        String ddd             = request.getParameter("ddd");
        String numeroTelefone  = request.getParameter("numeroTelefone");
        String grupo           = request.getParameter("grupo");
        String fornecedor      = request.getParameter("fornecedor");    
        String site            = request.getParameter("site");
        String tipoLinha       = request.getParameter("tipoLinha");
        String motivoRecontato = request.getParameter("motivoRecontato");
        String segmentacao     = request.getParameter("segmentacao");        
        
        
		StringBuffer query = new StringBuffer();

        query.append(" select rownum, ");
        query.append("        canal.nmcanal, ");
        query.append("        to_char(recontato.dtultimaalteracao, 'dd/mm/yyyy hh24:mi:ss') as data, ");
        query.append("        grupo.nmgrupo,        ");
        query.append("        fornecedor.dsfornecedorconsultoratd, ");       
        query.append("        site.dssiteconsultoratd,    ");     
        query.append("        usuario.nmloginusuario, ");
        query.append("        motivo.nmmotivorecontato, ");
        query.append("        submotivo.nmsubmotivorecontato, ");
        query.append("        atendimentoprotocolo.cdarearegistro||'-'||atendimentoprotocolo.nrtelefone, ");
        query.append("        tipolinha.dstipolinha, ");       
        query.append("        segmentacao.dssegmentacao, ");
        query.append("        recontato.idatendimentoprotocolo                      ");
        query.append("   from atendimento.dadosrecontato       recontato, ");
        query.append("        apoio.canal                      canal, ");
        query.append("        contatoadm.motivorecontato       motivo, ");
        query.append("        contatoadm.submotivorecontato    submotivo, ");
        query.append("        apoio.fornecedorconsultoratd     fornecedor, ");
        query.append("        acesso.grupo                     grupo, ");
        query.append("        apoio.siteconsultoratd           site, ");
        query.append("        atendimento.atendimento          atendimento, ");
        query.append("        apoio.segmentacao                segmentacao, ");
        query.append("        apoio.tipolinha                  tipolinha, ");
        query.append("        acesso.usuario                   usuario, ");
        query.append("        atendimento.atendimentoprotocolo atendimentoprotocolo ");
        query.append("         ");
        query.append("  where recontato.idatendimentoprotocolo    = atendimentoprotocolo.idatendimentoprotocolo ");
        query.append("    and atendimento.idatendimentoprotocolo  = atendimentoprotocolo.idatendimentoprotocolo ");
        query.append("    and atendimento.idsegmentacao           = segmentacao.idsegmentacao ");
        query.append("    and atendimento.idtipolinha             = tipolinha.idtipolinha ");
        query.append("    and recontato.idcanal                   = canal.idcanal(+) ");
        query.append("    and recontato.idmotivorecontato         = motivo.idmotivorecontato(+)    ");
        query.append("    and recontato.idsubmotivorecontato      = submotivo.idsubmotivorecontato(+) ");
        query.append("    and recontato.idfornecedorconsultoratd  = fornecedor.idfornecedorconsultoratd(+) ");
        query.append("    and recontato.idgrupo              = grupo.idgrupo(+) ");
        query.append("    and recontato.idsiteconsultoratd        = site.idsiteconsultoratd(+) ");
        query.append("    and recontato.idusuarioalteracao        = usuario.idpessoausuario(+) ");   

        if (dataInicio!= null && dataTermino != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                
                query.append(" and to_char(recontato.dtultimaalteracao, 'yyyymmdd') <= '").append(sdf2.format(sdf.parse(dataTermino))).append("'");    
                query.append(" and to_char(recontato.dtultimaalteracao, 'yyyymmdd') >= '").append(sdf2.format(sdf.parse(dataInicio))).append("'");     
            } catch (Exception e) {
            }
        }
        
        if (ddd != null && ddd.length() > 0) {
            query.append(" and atendimentoprotocolo.cdarearegistro = ").append(ddd);
        }
        
        if (numeroTelefone != null && numeroTelefone.length() > 0) {
            query.append(" and atendimentoprotocolo.nrtelefone = ").append(numeroTelefone);
        }        

        if (tipoLinha != null && tipoLinha.length() > 0) {
            query.append(" and atendimento.idtipolinha = ").append(tipoLinha);
        }                

        if (motivoRecontato != null && motivoRecontato.length() > 0) {
            query.append(" and recontato.idmotivorecontato   = ").append(motivoRecontato);
        }                        
        
        if (segmentacao != null && segmentacao.length() > 0) {
            query.append(" and atendimento.idsegmentacao   = ").append(segmentacao);
        }                                

        if (fornecedor != null && fornecedor.length() > 0) {
            query.append(" and recontato.idfornecedorconsultoratd   = ").append(fornecedor);
        }                                        
        
        if (site != null && site.length() > 0) {
            query.append(" and recontato.idsiteconsultoratd  = ").append(site);
        }           

		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = pesquisar.executar(query.toString());

		if (rsVO == null || rsVO.getLinhas() == null) {
			rsVO = Resultset.Factory.newInstance();
			rsVO.addNewLinhas();

		}

		getRelatorioForm().setDataInicio(dataInicio);
		getRelatorioForm().setDataTermino(dataTermino);
		getRelatorioForm().setResultset(rsVO);        

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW,this);
		
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	

	public RelatorioForm getRelatorioForm() {
        if (this.relatorioForm == null) 
            this.relatorioForm = new RelatorioForm();
		return relatorioForm;
	}

	public static class RelatorioForm extends ActionForm {
		private Resultset resultset;

		private String dataInicio = ConstantesCRM.SVAZIO;
		private String dataTermino = ConstantesCRM.SVAZIO;
		private String ddd = ConstantesCRM.SVAZIO;
		private String numeroTelefone = ConstantesCRM.SVAZIO;
		private String tipoLinha = ConstantesCRM.SVAZIO;
		private String motivoRecontato = ConstantesCRM.SVAZIO;
		private String segmentacao = ConstantesCRM.SVAZIO;
		private String fornecedor = ConstantesCRM.SVAZIO;
		private String site = ConstantesCRM.SVAZIO;
		private String grupo = ConstantesCRM.SVAZIO;

		private Disponivel listaDDD;
		private Disponivel listaTipoLinha;
		private Disponivel listaMotivoRecontato;
		private Disponivel listaSegmento;
		private Disponivel listaFornecedor;
		private Disponivel listaSite;
		private Disponivel listaGrupo;

		// Inicio Listas
		public void setListaDDD(Disponivel listaDDD) {
			this.listaDDD = listaDDD;
		}

		public Disponivel getListaDDD() {
			return this.listaDDD;
		}

		public void setListaTipoLinha(Disponivel listaTipoLinha) {
			this.listaTipoLinha = listaTipoLinha;
		}

		public Disponivel getListaTipoLinha() {
			return this.listaTipoLinha;
		}

		public void setListaMotivoRecontato(Disponivel listaMotivoRecontato) {
			this.listaMotivoRecontato = listaMotivoRecontato;
		}

		public Disponivel getListaMotivoRecontato() {
			return this.listaMotivoRecontato;
		}

		public void setListaSegmento(Disponivel listaSegmento) {
			this.listaSegmento = listaSegmento;
		}

		public Disponivel getListaSegmento() {
			return this.listaSegmento;
		}

		public void setListaFornecedor(Disponivel listaFornecedor) {
			this.listaFornecedor = listaFornecedor;
		}

		public Disponivel getListaFornecedor() {
			return this.listaFornecedor;
		}

		public void setListaSite(Disponivel listaSite) {
			this.listaSite = listaSite;
		}

		public Disponivel getListaSite() {
			return this.listaSite;
		}

		public void setListaGrupo(Disponivel listaGrupo) {
			this.listaGrupo = listaGrupo;
		}

		public Disponivel getListaGrupo() {
			return this.listaGrupo;
		}

		// Termino Listas

		public String getGrupo() {
			return grupo;
		}

		public void setGrupo(String grupo) {
			this.grupo = grupo;
		}

		public String getDataInicio() {
			return dataInicio;
		}

		public void setDataInicio(String dataInicio) {
			this.dataInicio = dataInicio;
		}

		public String getDataTermino() {
			return dataTermino;
		}

		public void setDataTermino(String dataTermino) {
			this.dataTermino = dataTermino;
		}

		public String getDdd() {
			return ddd;
		}

		public void setDdd(String ddd) {
			this.ddd = ddd;
		}

		public String getNumeroTelefone() {
			return numeroTelefone;
		}

		public void setNumeroTelefone(String numeroTelefone) {
			this.numeroTelefone = numeroTelefone;
		}

		public String getTipoLinha() {
			return tipoLinha;
		}

		public void setTipoLinha(String tipoLinha) {
			this.tipoLinha = tipoLinha;
		}

		public String getMotivoRecontato() {
			return motivoRecontato;
		}

		public void setMotivoRecontato(String motivoRecontato) {
			this.motivoRecontato = motivoRecontato;
		}

		public String getSegmentacao() {
			return segmentacao;
		}

		public void setSegmentacao(String segmentacao) {
			this.segmentacao = segmentacao;
		}

		public String getFornecedor() {
			return fornecedor;
		}

		public void setFornecedor(String fornecedor) {
			this.fornecedor = fornecedor;
		}

		public String getSite() {
			return site;
		}

		public void setSite(String site) {
			this.site = site;
		}

		public void setResultset(Resultset resultset) {
			this.resultset = resultset;
		}

		public Resultset getResultset() {
			return this.resultset;
		}
	}

}
