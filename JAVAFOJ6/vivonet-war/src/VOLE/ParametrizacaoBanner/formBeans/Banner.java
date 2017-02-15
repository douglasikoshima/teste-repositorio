package VOLE.ParametrizacaoBanner.formBeans;

import br.com.vivo.fo.constantes.ConstantesCRM;
import java.io.Serializable;
import org.apache.struts.upload.FormFile;

public class Banner implements Serializable {

    private long idBanner;

    private String nmArquivoBanner;

    private String dsBanner;

    private String urlBanner;

    private String extensaoBanner;

    private long idAreaBanner;

    private long idTipoBanner;

    private String nrIPBanner;

    private String dtVigenciaDe;

    private String dtVigenciaAte;

    private boolean autenticacao;

    private boolean contadorAcessos;

    private FormFile arquivo;

    public Banner() {}

    public String toString(Banner obj) {
        StringBuffer toString = new StringBuffer();
        toString.append("idBanner = ").append(obj.idBanner).append("\n")
                .append("dsBanner = ").append(obj.dsBanner).append("\n")
                .append("nmArquivoBanner = ").append(obj.nmArquivoBanner).append("\n")
                .append("urlBanner = ").append(obj.urlBanner).append("\n")
                .append("idAreaBanner = ").append(obj.idAreaBanner).append("\n")
                .append("idTipoBanner = ").append(obj.idTipoBanner).append("\n")
                .append("nrIPBanner = ").append(obj.nrIPBanner).append("\n")
                .append("tVigenciaDe = ").append(obj.dtVigenciaDe).append("\n")
                .append("dtVigenciaAte = ").append(obj.dtVigenciaAte).append("\n")
                .append("autenticacao = ").append(obj.autenticacao).append("\n")
                .append("contadorAcessos = ").append(obj.contadorAcessos).append("\n");
        return toString.toString();
    }

    public Banner(long idBanner, String dsBanner, String nmArquivoBanner, String urlBanner, long idAreaBanner, long idTipoBanner,
                  String nrIPBanner, String dtVigenciaDe, String dtVigenciaAte, boolean autenticacao,
                  boolean contadorAcessos) {
        this.idBanner = idBanner;
        this.dsBanner = dsBanner;
        this.nmArquivoBanner = nmArquivoBanner;
        this.urlBanner = urlBanner;
        this.idAreaBanner = idAreaBanner;
        this.idTipoBanner = idTipoBanner;
        this.nrIPBanner = nrIPBanner;
        this.dtVigenciaDe = dtVigenciaDe;
        this.dtVigenciaAte = dtVigenciaAte;
        this.autenticacao = autenticacao;
        this.contadorAcessos = contadorAcessos;
    }

    public long getIdBanner() {
        return this.idBanner;
    }

    public void setIdBanner(long arg) {
        this.idBanner = arg;
    }

    public String getDsBanner() {
        if (this.dsBanner == null) {
            dsBanner = ConstantesCRM.SVAZIO;
        }
        return this.dsBanner;
    }

    public void setDsBanner(String arg) {
        this.dsBanner = arg;
    }

    public String getNmArquivoBanner() {
        if (this.nmArquivoBanner == null) {
            nmArquivoBanner = ConstantesCRM.SVAZIO;
        }
        return this.nmArquivoBanner;
    }

    public void setNmArquivoBanner(String arg) {
        this.nmArquivoBanner = arg;
    }

    public String getUrlBanner() {
        if (this.urlBanner == null) {
            urlBanner = ConstantesCRM.SVAZIO;
        }
        return this.urlBanner;
    }

    public void setUrlBanner(String arg) {
        this.urlBanner = arg;
    }

    public String getExtensaoBanner() {
        if (this.extensaoBanner == null) {
            extensaoBanner = ConstantesCRM.SVAZIO;
        }
        return this.extensaoBanner;
    }

    public void setExtensaoBanner(String arg) {
        this.extensaoBanner = arg;
    }

    public long getIdAreaBanner() {
        return this.idAreaBanner;
    }

    public void setIdAreaBanner(long arg) {
        this.idAreaBanner = arg;
    }

    public long getIdTipoBanner() {
        return this.idTipoBanner;
    }

    public void setIdTipoBanner(long arg) {
        this.idTipoBanner = arg;
    }

    public String getNrIPBanner() {
        if (this.nrIPBanner == null) {
            nrIPBanner = ConstantesCRM.SVAZIO;
        }
        return this.nrIPBanner;
    }

    public void setNrIPBanner(String arg) {
        this.nrIPBanner = arg;
    }

    public String getDtVigenciaDe() {
        if (this.dtVigenciaDe == null) {
            dtVigenciaDe = ConstantesCRM.SVAZIO;
        }
        return this.dtVigenciaDe;
    }

    public void setDtVigenciaDe(String arg) {
        this.dtVigenciaDe = arg;
    }

    public String getDtVigenciaAte() {
        if (this.dtVigenciaAte == null) {
            dtVigenciaAte = ConstantesCRM.SVAZIO;
        }
        return this.dtVigenciaAte;
    }

    public void setDtVigenciaAte(String arg) {
        this.dtVigenciaAte = arg;
    }

    public boolean isAutenticacao() {
        return this.autenticacao;
    }

    public void setAutenticacao(boolean arg) {
        this.autenticacao = arg;
    }

    public boolean isContadorAcessos() {
        return this.contadorAcessos;
    }

    public void setContadorAcessos(boolean arg) {
        this.contadorAcessos = arg;
    }

    public void setArquivo(FormFile arg) {
        this.arquivo = arg;
    }

    public FormFile getArquivo() {
        return this.arquivo;
    }

}
