package br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses;

public class ServicosDePara implements java.io.Serializable {

    private static final long serialVersionUID = 7838655750500599566L;
    private long idPlano;
    private String cdPlano;
    private String dsPlano;
    private String cdPlanoAtlys;
    private int inAtivo;

    public long getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(long idPlano) {
        this.idPlano = idPlano;
    }

    public String getCdPlano() {
        return cdPlano;
    }

    public void setCdPlano(String cdPlano) {
        this.cdPlano = cdPlano;
    }

    public String getDsPlano() {
        return dsPlano;
    }

    public void setDsPlano(String dsPlano) {
        this.dsPlano = dsPlano;
    }

    public String getCdPlanoAtlys() {
        return cdPlanoAtlys;
    }

    public void setCdPlanoAtlys(String cdPlanoAtlys) {
        this.cdPlanoAtlys = cdPlanoAtlys;
    }

    public int getInAtivo() {
        return this.inAtivo;
    }

    public void setInAtivo(int arg) {
        this.inAtivo = arg;
    }
}
