
CREATE OR REPLACE PACKAGE CONTATOADM.Encaminhamentopkg is
  vTL  contatoadm.vTipoLinha;
  vTC  contatoadm.vTipoCliente;
  vGA  contatoadm.vGrupoAbertura;
  vCa  contatoadm.vCanal;
  vPr  contatoadm.vProcedencia;
  vRg  contatoadm.vRegional;
  vNt  contatoadm.vNatureza;
  vSeg contatoadm.vSegmentacao;
  vCar contatoadm.vCarteirizacao;



  Cursor c_grupo (p_idgrupo acesso.grupo.idgrupo%type)is
       Select IdGrupo, nmGrupo
         from acesso.grupo grp
        where grp.idgrupo = p_idgrupo;

  Cursor c_grupoS (p_idgruposkill acesso.grupoSkill.idgruposkill%type) is
       Select IdGrupoSkill, DsGrupoSkill
         from acesso.grupoSkill gs
        where gs.idgruposkill = p_idgruposkill;


  Procedure pcEncamGrupos (p_idGrupo in acesso.grupo.idgrupo%type
                          , Mensagem in out varchar2
                          , p_usuario in varchar2) ;

  Procedure pcParGrupoSkill (p_idGrupoSkill in acesso.grupoSkill.idgruposkill%type
                            , Mensagem in out varchar2
                            , p_usuario in varchar2) ;

  Procedure prVerQtdeParam (p_idGrupoS in  acesso.grupo.idgrupo%type
                            , Tipo varchar2);

  Procedure prConsultaParam (p_idGrupo in acesso.grupo.idgrupo%type
                             , pTL  out contatoadm.vTipoLinha
                             , pTC  out contatoadm.vTipoCliente
                             , pGA  out contatoadm.vGrupoAbertura
                             , pCa  out contatoadm.vCanal
                             , pPr  out contatoadm.vProcedencia
                             , pRg  out contatoadm.vRegional
                             , pNt  out contatoadm.vNatureza
                             , pSeg out contatoadm.vSegmentacao
                             , pCar out contatoadm.vCarteirizacao
                             , pMensagem out boolean );


  Procedure prVerVariveis ( p_idGrupo in acesso.grupo.idgrupo%type
                             , pTL  in Varchar2
                             , pTC  in Varchar2
                             , pGA  in Varchar2
                             , pCa  in Varchar2
                             , pPr  in Varchar2
                             , pRg  in Varchar2
                             , pNt  in Varchar2
                             , pSeg in Varchar2
                             , pCar in Varchar2
                             , pMensagem out boolean );


end;
/


CREATE OR REPLACE PACKAGE BODY CONTATOADM.Encaminhamentopkg is

  PROCEDURE pcEncamGrupos (p_idGrupo in acesso.grupo.idgrupo%type
                          , Mensagem in out varchar2
                          , p_usuario in varchar2) IS
      Begin
         For c_grupo1 in c_grupo(p_idGrupo) Loop
            vTL := null;
            Select dstipolinha          BULK COLLECT INTO vTL
              from apoio.tipolinha tl
                 , contatoadm.tipolinhagrupo tlg
             where tl.idtipolinha = tlg.idtipolinha
               and tlg.idgrupo    = c_grupo1.idGrupo;
              vTC  := contatoadm.vTipoCliente();
            Select nmtiporelacionamento BULK COLLECT INTO vTC
              from customer.tiporelacionamento tr
                 , contatoadm.tiporelacionamentogrupo trg
             where tr.idtiporelacionamento = trg.idtiporelacionamento
               and trg.idgrupo             = c_grupo1.idGrupo;
               vGA := contatoadm.vGrupoAbertura();
            Select nmGrupo              BULK COLLECT INTO vGA
              from acesso.grupo  G
                 , contatoadm.Grupoaberturagrupo GAG
             where g.idgrupo = gag.idgrupo
               and gag.idgrupopai = c_grupo1.idGrupo;
               vCa := null;
            Select nmCanal              BULK COLLECT INTO vCa
              from apoio.canal  c
                 , acesso.grupocanal gc
             where c.idcanal = gc.idcanal
               and gc.idgrupo = c_grupo1.idGrupo;
              vPR := null;
            Select dsProcedencia        BULK COLLECT INTO vPr
              from apoio.procedencia p
                 , contatoadm.procedenciagrupo  pg
             where p.idprocedencia = pg.idprocedencia
               and pg.idgrupo = c_grupo1.idGrupo;
              vRG := null;
            Select nmuf                 BULK COLLECT INTO vRg
              from contatoadm.ufoperadoragrupo ufg
                   , customer.ufoperadora        ufo
                   , apoio.uf                    uf
              where ufg.idufoperadora = ufo.idufoperadora
                and ufo.iduf          = uf.iduf
                and ufg.idgrupo       = c_grupo1.idGrupo;
             vNT := null;
            Select dstipopessoa         BULK COLLECT INTO vNt
              from apoio.tipopessoa           tp
                 , contatoadm.tipopessoagrupo tpg
             where tp.idtipopessoa  = tpg.idtipopessoa
               and tpg.idgrupo = c_grupo1.idGrupo;
             vSEG := null;
            Select dssegmentacao        BULK COLLECT INTO vSeg
              from apoio.segmentacao           sg
                 , contatoadm.segmentacaogrupo sgg
            where sg.idsegmentacao  = sgg.idsegmentacao
              and sgg.idgrupo       = c_grupo1.idGrupo;
            vCAR := null;
            Select dstipocarteira       BULK COLLECT INTO vCar
              from apoio.tipocarteira           tc
                 , contatoadm.tipocarteiragrupo tcg
            where tc.idtipocarteira = tcg.idtipocarteira
              and tcg.idgrupo       = c_grupo1.idGrupo;

            If (vTL.count > 0 )
                 and vTC.count >0
                    and vGA.count > 0
                       and vCA.count > 0
                          and vPR.count > 0
                             and vRG.count > 0
                               and vNT.count > 0
                                  and vSeg.count > 0
                                     and vCAR.count > 0 Then
              Begin
                INSERT INTO contatoadm.ENCAMINHAMENTOHIST  (
                                                        idgrupo
                                                        ,nmgrupo
                                                         ,NmTipoLinha
                                                          ,NmTipoCliente
                                                           ,NmGrupoAbertura
                                                            ,NmCanal
                                                             ,NmProcedencia
                                                              ,NmRegional
                                                               ,NmNatureza
                                                                ,NmSegmentacao
                                                                 ,NmCartirizacao
                                                                  ,dtAtualizacao
                                                                   ,Usuario)
                                               values (
                                                        c_grupo1.idGrupo
                                                        ,c_grupo1.nmGrupo
                                                         ,vTL
                                                          ,vTC
                                                           ,vGA
                                                            ,vCA
                                                             ,vPR
                                                              ,vRG
                                                               ,vNT
                                                                ,vSeg
                                                                 ,vCAR
                                                                  ,Sysdate
                                                                   ,p_usuario   );
                   Mensagem := 'Regras de Encaminhamento inseridas com sucesso!!';
              Exception
                When Others Then
                   Mensagem := 'Erro ao Inserir Encaminhamento '||SQLCODE||' - '||Substr(sqlerrm,1,150);
              End;
            End If;
         End Loop;
        End;

  PROCEDURE pcParGrupoSkill (p_idGrupoSkill in acesso.grupoSkill.idgruposkill%type
                            , Mensagem in out varchar2
                            , p_usuario in varchar2) IS
        Begin

           For c_grupo1 in c_grupoS(p_idGrupoSkill) Loop
              vTL := null;
              Select dstipolinha          BULK COLLECT INTO vTL
                from apoio.tipolinha tl
                   , acesso.tipolinhagrupoSkill tlg
               where tl.idtipolinha   = tlg.idtipolinha
                 and tlg.idgrupoSkill = c_grupo1.idGrupoSkill;
                vTC := null;
              Select nmtiporelacionamento BULK COLLECT INTO vTC
                from customer.tiporelacionamento tr
                   , acesso.tprelacionamentogrupoSkill trg
               where tr.idtiporelacionamento = trg.idtiporelacionamento
                 and trg.idGrupoSkill        = c_grupo1.idGrupoSkill;
                 vGA := contatoadm.vGrupoAbertura();
              Select g.nmgrupo           BULK COLLECT INTO vGA
                from acesso.grupo       g
                   , acesso.GrupoaberturagrupoSkill GAG
               where gag.idgrupo       = g.idgrupo
                 and gag.idGrupoSkill  = c_grupo1.idGrupoSkill
                 and rownum<=200;
                 vCa := null;
              Select nmCanal              BULK COLLECT INTO vCa
                from apoio.canal  c
                   , acesso.CanalGrupoSkill gc
               where c.idcanal = gc.idcanal
                 and gc.idGrupoSkill = c_grupo1.idGrupoSkill;
                vPR := null;
              Select dsProcedencia        BULK COLLECT INTO vPr
                from apoio.procedencia p
                   , acesso.procedenciagrupoSkill  pg
               where p.idprocedencia = pg.idprocedencia
                 and pg.idGrupoSkill = c_grupo1.idGrupoSkill;
                vRG := null;
              Select nmuf                 BULK COLLECT INTO vRg
                from acesso.ufoperadoragrupoSkill ufg
                   , customer.ufoperadora        ufo
                   , apoio.uf                    uf
                where ufg.idufoperadora = ufo.idufoperadora
                  and ufo.iduf          = uf.iduf
                  and ufg.idGrupoSkill       = c_grupo1.idGrupoSkill;
               vNT := null;
              Select dstipopessoa         BULK COLLECT INTO vNt
                from apoio.tipopessoa           tp
                   , acesso.tipopessoagrupoSkill tpg
               where tp.idtipopessoa  = tpg.idtipopessoa
                 and tpg.idGrupoSkill = c_grupo1.idGrupoSkill;
               vSEG := null;
              Select dssegmentacao        BULK COLLECT INTO vSeg
                from apoio.segmentacao           sg
                   , acesso.segmentacaogrupoSkill sgg
              where sg.idsegmentacao  = sgg.idsegmentacao
                and sgg.idGrupoSkill       = c_grupo1.idGrupoSkill;
              vCAR := null;
              Select dstipocarteira       BULK COLLECT INTO vCar
                from apoio.tipocarteira           tc
                   , acesso.tipocarteiragrupoSkill tcg
              where tc.idtipocarteira = tcg.idtipocarteira
                and tcg.idGrupoSkill       = c_grupo1.idGrupoSkill;

              If (vTL.count > 0 )
                   and vTC.count >0
                      and vGA.count > 0
                         and vCA.count > 0
                            and vPR.count > 0
                               and vRG.count > 0
                                 and vNT.count > 0
                                    and vSeg.count > 0
                                       and vCAR.count > 0 Then
                Begin
                  INSERT INTO contatoadm.PARAMGRUPOSKILLHIST (
                                                       idGrupoSkill
                                                       ,dsgrupoSkill
                                                        ,NmTipoLinha
                                                         ,NmTipoCliente
                                                          ,NmGrupoAbertura
                                                           ,NmCanal
                                                            ,NmProcedencia
                                                             ,NmRegional
                                                              ,NmNatureza
                                                               ,NmSegmentacao
                                                                ,NmCartirizacao
                                                                 ,dtAtualizacao
                                                                  ,Usuario)
                                              values (c_grupo1.idGrupoSkill
                                                       ,c_grupo1.dsGrupoSkill
                                                        ,vTL
                                                         ,vTC
                                                          ,vGA
                                                           ,vCA
                                                            ,vPR
                                                             ,vRG
                                                              ,vNT
                                                               ,vSeg
                                                                ,vCAR
                                                                 ,Sysdate
                                                                  ,p_usuario   );

                     Mensagem := 'Parametros do Skill inseridos com sucesso!!';

                Exception
                  When Others Then
                     Mensagem := 'Erro ao inserir Skill '||SQLCODE||' - '||Substr(sqlerrm,1,150);
                End;
              End If;
           End Loop;
        End;

    PROCEDURE prVerQtdeParam (p_idGrupoS in  acesso.grupo.idgrupo%type
                            , Tipo varchar2) IS

     Qtde  Number := 0;

       Begin
          If Tipo = 'S' Then -- Skill
            Begin
             Select count(1) into Qtde
               from contatoadm.PARAMGRUPOSKILLHIST ph
              where ph.idgrupoSkill = p_idGrupoS;
              If Qtde = 5 Then
                 Delete contatoadm.PARAMGRUPOSKILLHIST ph
                    where ph.idgrupoSkill = p_idGrupoS
                      and ph.dtatualizacao = ( Select Min(phs.dtatualizacao)
                                                 from contatoadm.PARAMGRUPOSKILLHIST phs
                                                where phs.idgrupoSkill = p_idGrupoS);
              End If;
            End;

          ElsIf Tipo = 'G' Then -- Grupo
            Begin
             Select count(1) into Qtde
               from contatoadm.ENCAMINHAMENTOHIST eh
              where eh.idgrupo = p_idGrupoS;
              If Qtde = 5 Then
                 Delete contatoadm.ENCAMINHAMENTOHIST eh
                    where eh.idgrupo = p_idGrupoS
                      and eh.dtatualizacao = ( Select Min(ehg.dtatualizacao)
                                                 from contatoadm.ENCAMINHAMENTOHIST ehg
                                                where ehg.idgrupo = p_idGrupoS);
              End If;

            End;

          End If;

       End;

  Procedure prConsultaParam (p_idGrupo in acesso.grupo.idgrupo%type
                             , pTL  out contatoadm.vTipoLinha
                             , pTC  out contatoadm.vTipoCliente
                             , pGA  out contatoadm.vGrupoAbertura
                             , pCa  out contatoadm.vCanal
                             , pPr  out contatoadm.vProcedencia
                             , pRg  out contatoadm.vRegional
                             , pNt  out contatoadm.vNatureza
                             , pSeg out contatoadm.vSegmentacao
                             , pCar out contatoadm.vCarteirizacao
                             , pMensagem out boolean ) is

    Begin
       Select nmTipoLinha, nmTipoCliente, nmGrupoAbertura, nmCanal,
              nmProcedencia, nmRegional,  nmNatureza,  nmSegmentacao, nmCartirizacao
         into
              pTL, pTC, pGA, pCa, pPr, pRg, pNt, pSeg,  pCar
         from contatoadm.ENCAMINHAMENTOHIST eh
        where eh.idgrupo = p_idGrupo
          and eh.dtatualizacao = ( select max(dtatualizacao)
                                          from contatoadm.ENCAMINHAMENTOHIST ehg
                                         where ehg.idgrupo = p_idGrupo);

          pMensagem := true;

     Exception
       When No_Data_Found Then
          pMensagem := false;
       When Others Then
          pMensagem := false;


    End;

  Procedure prVerVariveis ( p_idGrupo in acesso.grupo.idgrupo%type
                           , pTL  in Varchar2
                           , pTC  in Varchar2
                           , pGA  in Varchar2
                           , pCa  in Varchar2
                           , pPr  in Varchar2
                           , pRg  in Varchar2
                           , pNt  in Varchar2
                           , pSeg in Varchar2
                           , pCar in Varchar2
                           , pMensagem out boolean ) IS

    achei  boolean;
    achou  boolean := false;
    achouc boolean := true;

    Begin

    prConsultaParam (p_idGrupo, vTL, vTC, vGA, vCa, vPr, vRg, vNt, vSeg, vCar , achei);

    if achei then
     For i in vtl.first .. vtl.last Loop
      For i in vtc.first .. vtc.last Loop
       For i in vga.first .. vga.last Loop
        For i in vca.first .. vca.last Loop
         For i in vpr.first .. vpr.last Loop
          For i in vrg.first .. vrg.last Loop
           For i in vnt.first .. vnt.last Loop
            For i in vseg.first .. vseg.last Loop
             For i in vcar.first .. vcar.last Loop
               if vcar(i) = pcar Then
                 achou  := true;
                 exit;
               end if;
               if i = vcar.count then
                 achouc := false;
               End if;
             End Loop;
             if not  achouc then
                exit;
             end if;
               if vseg(i) = pseg Then
                 achou := true;
                 exit;
               end if;
               if i = vseg.count then
                  achouc := false;
               End if;
            End Loop;
            if not achouc then
               exit;
            end if;
             if vnt(i) = pnt Then
               achou := true;
                exit;
             end if;
             if i = vnt.count then
                achouc := false;
             end if;
           End Loop;
           if not achouc then
              exit;
           end if;
             if vrg(i) = prg Then
               achou := true;
                 exit;
             end if;
             if i = vrg.count then
                achouc := false;
             end if;
          End Loop;
          if not achouc then
             exit;
          end if;
           if vpr(i) = ppr Then
             achou := true;
             exit;
           end if;
           if i = vpr.count then
              achouc := false;
           end if;
         End Loop;
         if not achouc then
            exit;
         end if;
           if vca(i) = pca Then
             achou := true;
             exit;
           end if;
           if i = vca.count then
              achouc := false;
           end if;
        End Loop;
        if not achouc then
           exit;
        end if;
         if vga(i) = pga Then
           achou := true;
           exit;
         end if;
         if i = vga.count then
            achouc := false;
         end if;
       End Loop;
       if not achouc then
          exit;
       end if;
         if vtc(i) = ptc Then
           achou := true;
           exit;
         end if;
         if i = vtc.count then
            achouc := false;
         end if;
      End Loop;
      if not achouc then
         exit;
      end if;
       if vtl(i) = ptl then
          achou := true;
          exit;
       end if;
       if i = vtl.count then
          achouc := false;
       end if;
     End Loop;
     if achouc then
        pMensagem := true;
--        dbms_output.put_line ('Variaveis conferem!');
     Else
        pMensagem := false;
--        dbms_output.put_line ('Variaveis NÃO conferem!');
     end if;
    Else
      pMensagem := false;
--      dbms_output.put_line ('Grupo NÃO possui Regras de Encaminhamento!');
    End if;

    End;

end;
/
