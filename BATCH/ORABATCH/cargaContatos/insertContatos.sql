set echo on
set serveroutput on

DECLARE 
  v_rid rowid;	
  VI_N1 VARCHAR2(200);
  VI_N2 VARCHAR2(200);
  VI_N3 VARCHAR2(200);
  VI_N4 VARCHAR2(200);
  VI_N5 VARCHAR2(200);
  VI_IDCLASSIFICACAO NUMBER;
  VO_IDCONTATO NUMBER;
  VO_CDERRO NUMBER;
  VO_DSERRO VARCHAR2(200);


CURSOR CR IS 
SELECT rowid rid,
   C.N1, 
   C.N2, 
   C.N3, 
   C.N4, 
   C.N5, 
   C.DSCLASSIFICACAO 
FROM LOAD.CARGACONTATO C
where idcontato is null;



BEGIN 


OPEN CR;

	

LOOP
	fetch cr into     v_rid,
					  VI_N1,
                      VI_N2,
                      VI_N3,
                      VI_N4,
                      VI_N5,
  					  VI_IDCLASSIFICACAO;
  exit when cr%notfound;

  LOAD.PRC_CRIACONTATO ( VI_N1, VI_N2, VI_N3, VI_N4, VI_N5, VI_IDCLASSIFICACAO, VO_IDCONTATO, VO_CDERRO, VO_DSERRO );

  update LOAD.CARGACONTATO c
	set c.IDCONTATO=vo_idcontato,
		c.CDERRO=vo_cderro,
		c.DSERRO=vo_dserro
	where rowid=v_rid;
	commit;

END LOOP;

  COMMIT; 
END; 
/

EXIT

set serveroutput off
set echo off
