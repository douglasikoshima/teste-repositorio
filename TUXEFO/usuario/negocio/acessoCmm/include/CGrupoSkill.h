#ifndef CGRUPOSKILLH
#define CGRUPOSKILLH

#include<tuxfw.h>

/*******************************************************************************
 * Macros para manipulacao de dados ProC/C++
 *******************************************************************************/
#define STRCPY_TO_ORA(dest, source) { \
    dest.len = (unsigned short) strlen(source);\
    strncpy((char *) dest.arr, (const char *) source, (size_t)dest.len); \
}
#define STRCPY_FROM_ORA(dest, source) { \
    dest[source.len] = 0; \
    strncpy((char *)dest,(const char *)source.arr, source.len); \
}


class CGrupoSkill
{
    private:
        void retornoVazio(XMLGen *xml_g);
        char _szIdUsuarioAlteracao[21 + 1];
        char *getIdUsuarioAlteracao(void);

    public:
        CGrupoSkill(char *pszIdUsuarioAlteracao);
        CGrupoSkill();
        ~CGrupoSkill();

		void inserirSkill(char *pszIdGrupoSkill, char *pszIdGrupo, char *pszDsGrupoSkill);
		void alterarSkill(char *pszIdGrupoSkill, char *pszDsGrupoSkill);
		void excluirSkill(char *pszIdGrupoSkill);
		void listarSkill(char *pszIdGrupo, char *pszDsGrupoSkill, XMLGen* xml_g);
        bool existeSkill(char *pszDsGrupoSkill);

        // deleta um skill de um grupo
		void apagaTpRelacionamentoGrupoSkill(char *pszIdGrupoSkill);
		void apagaTipoPessoaGrupoSkill(char *pszIdGrupoSkill);
		void apagaSegmentacaoGrupoSkill(char *pszIdGrupoSkill);
		void apagaUfOperadoraGrupoSkill(char *pszIdGrupoSkill);
		void apagaProcedenciaGrupoSkill(char *pszIdGrupoSkill);
		void apagaGrupoAberturaGrupoSkill(char *pszIdGrupoSkill);
		void apagaTipoCarteiraGrupoSkill(char *pszIdGrupoSkill);
		void apagaTipoLinhaGrupoSkill(char *pszIdGrupoSkill);
        void apagaContatoFolhaUsuario(char *pszIdGrupoSkill);

		void apagaCanalGrupoSkill(char *pszIdGrupoSkill);
		
		//Grava os parametros para um certo skill
        void gravaTpRelacionamentoGrupoSkill(char *pszIdGrupoSkill, char *pszIdTipoPessoa);
        void gravaTipoPessoaGrupoSkill(char *pszIdGrupoSkill, char *pszIdTipoRelacionamento);
		void gravaSegmentacaoGrupoSkill(char *pszIdGrupoSkill, char *pszIdSegmentacao);
		void gravaUfOperadoraGrupoSkill(char *pszIdGrupoSkill, char *pszIdUfOperadora);
		void gravaProcedenciaGrupoSkill(char *pszIdGrupoSkill, char *pszIdProcedencia);
		void gravaGrupoAberturaGrupoSkill(char *pszIdGrupoSkill, char *pszIdGrupo);
		void gravaTipoCarteiraGrupoSkill(char *pszIdGrupoSkill, char *pszIdTipoCarteira);
		void gravaTipoLinhaGrupoSkill(char *pszIdGrupoSkill, char *pszIdTipoLinha);
		void gravaCanalGrupoSkill(char *pszIdGrupoSkill, char *pszIdCanal);
    
        //Recupera os parametros associados ao grupo no qual o skill foi criado
        //Nao recupera TODOS os parametros, apenas os associados ao grupo no qual o skill foi criado
        int ConsultaRegionalSkill( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaCanalSkill( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaTipoCarteiraSkill( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaGrupoSkill( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaProcedenciaSkill( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaSegmentacaoSkill( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaTipoPessoaSkill( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaTipoRelacionamentoSkill( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaTipoLinhaSkill( char *pzcidGrupoSkill, XMLGen* Saida );

		//Recupera os parametros associados a um skill
        int ConsultaRegionalSkillSelecionado( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaCanalSkillSelecionado( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaTipoCarteiraSkillSelecionado( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaGrupoSkillSelecionado( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaProcedenciaSkillSelecionado( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaSegmentacaoSkillSelecionado( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaTipoPessoaSkillSelecionado( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaTipoRelacionamentoSkillSelecionado( char *pzcidGrupoSkill, XMLGen* Saida );
        int ConsultaTipoLinhaSkillSelecionado( char *pzcidGrupoSkill, XMLGen* Saida );

};

#endif
