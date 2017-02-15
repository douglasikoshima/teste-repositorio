#!/bin/sh

. ${HOME}/.profile

MYDIR=$(dirname $0)
cd $MYDIR
. ./expurgoSMS.cfg

DIR_LOG="./log"

DATA=`date '+%Y%m%d%H%M%S'`
LOGFILE="$DIR_LOG/expurgoSMS-$DATA.log"

sqlplus /@$SID  <<SQL >> $LOGFILE

set pagesize 50000
set linesize 500
set timi on
set define off
set feed on
set echo on
set serveroutput on 
set trimspool on

declare
    v_numlinhas   NUMBER :=  5000;
    
    SUBTYPE t_idap IS NUMBER;

    TYPE t_tab_idap IS TABLE OF t_idap
        INDEX BY PLS_INTEGER;

    coll1_idap   t_tab_idap;
    coll2_idap   t_tab_idap;

    CURSOR c1
    IS
        SELECT smsp.idatendimentoprotocolo
          FROM atendimento.smsprotocolo smsp
         WHERE smsp.inestadosms = 1;

    CURSOR c2
    IS
	SELECT /*+ LEADING(CF2) PARALLEL(A2,4) PARALLEL(AP2,4) PARALLEL(SMSP,4) */ SMSP.IDATENDIMENTOPROTOCOLO
	  FROM ATENDIMENTO.SMSPROTOCOLO         SMSP,
		   ATENDIMENTO.ATENDIMENTOPROTOCOLO AP2,
		   ATENDIMENTO.ATENDIMENTO          A2,
		   CONTATOADM.CONTATOFOLHA          CF2
	WHERE SMSP.IDATENDIMENTOPROTOCOLO = AP2.IDATENDIMENTOPROTOCOLO
	   AND AP2.IDATENDIMENTOPROTOCOLO = SMSP.IDATENDIMENTOPROTOCOLO
	   AND AP2.IDATENDIMENTOPROTOCOLO = A2.IDATENDIMENTOPROTOCOLO
	   AND A2.IDCONTATO = CF2.IDCONTATO
	   AND CF2.INSMS = 0 ;
					  
BEGIN
    DBMS_OUTPUT.put_line ('Vai limpar SMSs enviados...');
    DBMS_OUTPUT.put_line (   'start time = '
                          || TO_CHAR (SYSDATE, 'dd/mm/yyyy hh24:mi:ss')
                         );

    OPEN c1;

    LOOP
        FETCH c1
        BULK COLLECT INTO coll1_idap LIMIT v_numlinhas;

        EXIT WHEN coll1_idap.COUNT = 0;
        FORALL id_ap1 IN coll1_idap.FIRST .. coll1_idap.LAST
            DELETE FROM atendimento.smsprotocolo
                  WHERE idatendimentoprotocolo = coll1_idap (id_ap1);
        COMMIT;
    END LOOP;

    CLOSE c1;

    COMMIT;
    DBMS_OUTPUT.put_line (   '  end time = '
                          || TO_CHAR (SYSDATE, 'dd/mm/yyyy hh24:mi:ss')
                         );
    /*========================================================================*/
    DBMS_OUTPUT.put_line ('Vai limpar SMSs que nunca serão enviados...');
    DBMS_OUTPUT.put_line (   'start time = '
                          || TO_CHAR (SYSDATE, 'dd/mm/yyyy hh24:mi:ss')
                         );

    OPEN c2;

    LOOP
        FETCH c2
        BULK COLLECT INTO coll2_idap LIMIT v_numlinhas;

        EXIT WHEN coll2_idap.COUNT = 0;
        FORALL id_ap2 IN coll2_idap.FIRST .. coll2_idap.LAST
            DELETE FROM atendimento.smsprotocolo
                  WHERE idatendimentoprotocolo = coll2_idap (id_ap2);
        COMMIT;
    END LOOP;

    CLOSE c2;

    COMMIT;
    DBMS_OUTPUT.put_line (   '  end time = '
                          || TO_CHAR (SYSDATE, 'dd/mm/yyyy hh24:mi:ss')
                         );
    DBMS_OUTPUT.put_line ('Fim.');
END;
/

exit

SQL

if [ $? -ne 0 ] ; then
   exit 1
fi

