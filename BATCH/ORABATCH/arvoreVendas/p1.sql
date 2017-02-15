/* Formatted on 05/07/2014 14:54:39 (QP5 v5.115.810.9015) */
DECLARE
   v_rid                ROWID;
   vi_n1                VARCHAR2 (200);
   vi_n2                VARCHAR2 (200);
   vi_n3                VARCHAR2 (200);
   vi_n4                VARCHAR2 (200);
   vi_n5                VARCHAR2 (200);
   vi_idclassificacao   NUMBER;
   vo_idcontato         NUMBER;
   vo_cderro            NUMBER;
   vo_dserro            VARCHAR2 (200);

   CURSOR cr
   IS
      SELECT   ROWID rid,
               TRIM (c.n1),
               TRIM (c.n2),
               TRIM (c.n3),
               TRIM (c.n4),
               TRIM (c.n5),
               c.cdclasificacao
        FROM   load.cargacontato c
       WHERE       idcontato IS NULL
               AND tipo LIKE 'C%';


   v_n0                 VARCHAR2 (255) := 'VIVO';
   v_idcontatonome      NUMBER;
   v_idcontato          NUMBER;
   v_nmpath             VARCHAR2 (1000);
   v_count              NUMBER;
   v_idcontaton1        NUMBER;
   v_idcontaton2        NUMBER;
   v_idcontaton3        NUMBER;
   v_idcontaton4        NUMBER;
   v_idcontaton5        NUMBER;
   vt_idcontato         NUMBER;
   v_infolha            NUMBER;
BEGIN
   OPEN cr;

   LOOP
      FETCH cr
         INTO   v_rid, vi_n1, vi_n2, vi_n3, vi_n4, vi_n5, vi_idclassificacao;

      EXIT WHEN cr%NOTFOUND;

      BEGIN
         --------DBMS_OUTPUT.PUT_LINE (' ');
         v_nmpath := 'VIVO/';

         IF vi_n1 IS NULL
         THEN
            RAISE NO_DATA_FOUND;
            vo_cderro := 1;
         END IF;

         IF vi_n1 IS NOT NULL
         THEN
            vo_cderro := 2;

            SELECT   COUNT (1)
              INTO   v_count
              FROM   contatoadm.nomecontato
             WHERE   UPPER (nmcontato) = UPPER (vi_n1);

            IF v_count = 0
            THEN
               ------DBMS_OUTPUT.PUT_LINE ('N1_1');

               INSERT INTO contatoadm.nomecontato (idnomecontato,
                                                   nmcontato,
                                                   idusuarioalteracao,
                                                   dtultimaalteracao)
                 VALUES   (nomecontatosq.NEXTVAL,
                           vi_n1,
                           1,
                           SYSDATE);

               COMMIT;
            END IF;

            /* VERIFICANDO SE EXISTE ALGUM VALOR PARA O PATH QUE EXISTA UMA FOLHA */

            SELECT   COUNT (1)
              INTO   v_count
              FROM   contatoadm.contato t1
             WHERE   UPPER (t1.nmpath) = UPPER (v_n0 || '/' || vi_n1);

            IF v_count > 0 AND vi_n2 IS NOT NULL
            THEN
               --DBMS_OUTPUT.PUT_LINE ('11');

               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                WHERE       UPPER (t1.nmpath) = UPPER (v_n0 || '/' || vi_n1)
                        AND t1.idcontato = t2.idcontato(+)
                        AND DECODE (t2.idcontato, NULL, 0, 1) = 0;

               IF v_count = 1
               THEN
                  --DBMS_OUTPUT.PUT_LINE ('12');

                  SELECT   t1.idcontato
                    INTO   v_idcontaton1
                    FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                   WHERE   UPPER (t1.nmpath) = UPPER (v_n0 || '/' || vi_n1)
                           AND t1.idcontato = t2.idcontato(+)
                           AND DECODE (t2.idcontato, NULL, 0, 1) = 0;

                  v_count := 1;
                  v_nmpath := 'VIVO/' || vi_n1;
                  vo_idcontato := v_idcontaton1;
               ELSE
                  v_count := 0;
               END IF;
            ELSIF v_count > 0 AND vi_n2 IS NULL
            THEN
               --DBMS_OUTPUT.PUT_LINE ('13');

               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                WHERE       UPPER (t1.nmpath) = UPPER (v_n0 || '/' || vi_n1)
                        AND t1.idcontato = t2.idcontato(+)
                        AND DECODE (t2.idcontato, NULL, 0, 1) = 1;

               IF v_count = 1
               THEN
                  --DBMS_OUTPUT.PUT_LINE ('14');

                  SELECT   t1.idcontato
                    INTO   v_idcontaton1
                    FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                   WHERE   UPPER (t1.nmpath) = UPPER (v_n0 || '/' || vi_n1)
                           AND t1.idcontato = t2.idcontato(+)
                           AND DECODE (t2.idcontato, NULL, 0, 1) = 1;

                  v_count := 1;
                  v_nmpath := 'VIVO/' || vi_n1;
                  vo_idcontato := v_idcontaton1;
               ELSE
                  v_count := 0;
               END IF;
            ELSE
               v_count := 0;
            END IF;

            /* FIM*/
            IF v_count = 0
            THEN
               ------DBMS_OUTPUT.PUT_LINE ('N1_2');

               INSERT INTO contatoadm.contato (idcontato,
                                               idnomecontato,
                                               indisponibilidade,
                                               nmpath,
                                               idtipoarvore,
                                               idusuarioalteracao,
                                               dtultimaalteracao)
                 VALUES   (contatosq.NEXTVAL,
                           (SELECT   MAX (idnomecontato)
                              FROM   contatoadm.nomecontato
                             WHERE   UPPER (nmcontato) = UPPER (vi_n1)),
                           1,
                           v_n0 || '/' || vi_n1,
                           1,
                           1,
                           SYSDATE);

               SELECT   contatoadm.contatosq.CURRVAL
                 INTO   v_idcontaton1
                 FROM   DUAL;

               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.contatohierarquia
                WHERE   idcontato = v_idcontaton1;

               IF v_count = 0
               THEN
                  ------DBMS_OUTPUT.PUT_LINE ('N1_3');

                  INSERT INTO contatoadm.contatohierarquia (
                                                               idcontatopai,
                                                               idcontato,
                                                               idusuarioalteracao,
                                                               dtultimaalteracao
                             )
                    VALUES   (1,
                              v_idcontaton1,
                              1,
                              SYSDATE);

                  COMMIT;
               END IF;

               /*
                           else
                                 ------DBMS_OUTPUT.PUT_LINE ('N1_4');

                                 select   idcontato
                                   into   v_idcontaton1
                                   from   contatoadm.contato
                                  where   UPPER (nmpath) = UPPER (v_n0 || '/' || vi_n1);
               */


               v_nmpath := 'VIVO/' || vi_n1;
               vo_idcontato := v_idcontaton1;
            END IF;
         END IF;

         IF vi_n2 IS NOT NULL
         THEN
            vo_cderro := 3;

            SELECT   COUNT (1)
              INTO   v_count
              FROM   contatoadm.nomecontato
             WHERE   UPPER (nmcontato) = UPPER (vi_n2);

            IF v_count = 0
            THEN
               ------DBMS_OUTPUT.PUT_LINE ('N2_1');

               INSERT INTO contatoadm.nomecontato (idnomecontato,
                                                   nmcontato,
                                                   idusuarioalteracao,
                                                   dtultimaalteracao)
                 VALUES   (nomecontatosq.NEXTVAL,
                           vi_n2,
                           1,
                           SYSDATE);

               COMMIT;
            END IF;

            /* VERIFICANDO SE EXISTE ALGUM VALOR PARA O PATH QUE EXISTA UMA FOLHA */
            SELECT   COUNT (1)
              INTO   v_count
              FROM   contatoadm.contato t1
             WHERE   UPPER (t1.nmpath) =
                        UPPER (v_n0 || '/' || vi_n1 || '/' || vi_n2);

            IF v_count > 0 AND vi_n3 IS NOT NULL
            THEN
               --DBMS_OUTPUT.PUT_LINE ('21');

               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                WHERE   UPPER (t1.nmpath) =
                           UPPER (v_n0 || '/' || vi_n1 || '/' || vi_n2)
                        AND t1.idcontato = t2.idcontato(+)
                        AND DECODE (t2.idcontato, NULL, 0, 1) = 0;

               IF v_count = 1
               THEN
                  --DBMS_OUTPUT.PUT_LINE ('22');

                  SELECT   t1.idcontato
                    INTO   v_idcontaton2
                    FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                   WHERE   UPPER (t1.nmpath) =
                              UPPER (v_n0 || '/' || vi_n1 || '/' || vi_n2)
                           AND t1.idcontato = t2.idcontato(+)
                           AND DECODE (t2.idcontato, NULL, 0, 1) = 0;

                  v_count := 1;
                  v_nmpath := 'VIVO/' || vi_n1 || '/' || vi_n2;
                  vo_idcontato := v_idcontaton2;
               ELSE
                  v_count := 0;
               END IF;
            ELSIF v_count > 0 AND vi_n3 IS NULL
            THEN
               --DBMS_OUTPUT.PUT_LINE ('23');

               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                WHERE   UPPER (t1.nmpath) =
                           UPPER (v_n0 || '/' || vi_n1 || '/' || vi_n2)
                        AND t1.idcontato = t2.idcontato(+)
                        AND DECODE (t2.idcontato, NULL, 0, 1) = 1;

               IF v_count = 1
               THEN
                  --DBMS_OUTPUT.PUT_LINE ('24');

                  SELECT   t1.idcontato
                    INTO   v_idcontaton2
                    FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                   WHERE   UPPER (t1.nmpath) =
                              UPPER (v_n0 || '/' || vi_n1 || '/' || vi_n2)
                           AND t1.idcontato = t2.idcontato(+)
                           AND DECODE (t2.idcontato, NULL, 0, 1) = 1;

                  v_count := 1;
                  v_nmpath := 'VIVO/' || vi_n1 || '/' || vi_n2;
                  vo_idcontato := v_idcontaton2;
               ELSE
                  v_count := 0;
               END IF;
            ELSE
               v_count := 0;
            END IF;


            ----DBMS_OUTPUT.PUT_LINE ('v_idcontaton2 A = ' || v_idcontaton2);

            /* FIM*/

            IF v_count = 0
            THEN
               ------DBMS_OUTPUT.PUT_LINE ('N2_2B');

               INSERT INTO contatoadm.contato (idcontato,
                                               idnomecontato,
                                               indisponibilidade,
                                               nmpath,
                                               idtipoarvore,
                                               idusuarioalteracao,
                                               dtultimaalteracao)
                 VALUES   (contatosq.NEXTVAL,
                           (SELECT   MAX (idnomecontato)
                              FROM   contatoadm.nomecontato
                             WHERE   UPPER (nmcontato) = UPPER (vi_n2)),
                           1,
                           v_n0 || '/' || vi_n1 || '/' || vi_n2,
                           1,
                           1,
                           SYSDATE);

               SELECT   contatoadm.contatosq.CURRVAL
                 INTO   v_idcontaton2
                 FROM   DUAL;

               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.contatohierarquia
                WHERE   idcontato = v_idcontaton2;

               ----DBMS_OUTPUT.PUT_LINE ('v_idcontaton2 B = ' || v_idcontaton2);


               IF v_count = 0
               THEN
                  ------DBMS_OUTPUT.PUT_LINE ('N2_3');

                  INSERT INTO contatoadm.contatohierarquia (
                                                               idcontatopai,
                                                               idcontato,
                                                               idusuarioalteracao,
                                                               dtultimaalteracao
                             )
                    VALUES   (v_idcontaton1,
                              v_idcontaton2,
                              1,
                              SYSDATE);

                  COMMIT;
               END IF;

               COMMIT;
               /*else
                  ------DBMS_OUTPUT.PUT_LINE ('N2_4');

                  select   idcontato
                    into   v_idcontaton2
                    from   contatoadm.contato
                   where   UPPER (nmpath) =
                              UPPER (v_n0 || '/' || vi_n1 || '/' || vi_n2);
               end if;
              */

               v_nmpath := 'VIVO/' || vi_n1 || '/' || vi_n2;
               vo_idcontato := v_idcontaton2;
            ------DBMS_OUTPUT.PUT_LINE ('v_idcontaton2= '||v_idcontaton2);
            END IF;

            IF vi_n3 IS NOT NULL
            THEN
               vo_cderro := 4;

               ------DBMS_OUTPUT.PUT_LINE ('N3_1');

               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.nomecontato
                WHERE   UPPER (nmcontato) = UPPER (vi_n3);

               IF v_count = 0
               THEN
                  ------DBMS_OUTPUT.PUT_LINE ('N3_1A');

                  INSERT INTO contatoadm.nomecontato (idnomecontato,
                                                      nmcontato,
                                                      idusuarioalteracao,
                                                      dtultimaalteracao)
                    VALUES   (nomecontatosq.NEXTVAL,
                              vi_n3,
                              1,
                              SYSDATE);

                  COMMIT;
               END IF;

               /* VERIFICANDO SE EXISTE ALGUM VALOR PARA O PATH QUE EXISTA UMA FOLHA */
               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.contato t1
                WHERE   UPPER (t1.nmpath) =
                           UPPER(   v_n0
                                 || '/'
                                 || vi_n1
                                 || '/'
                                 || vi_n2
                                 || '/'
                                 || vi_n3);

               IF v_count > 0 AND vi_n4 IS NOT NULL
               THEN
                  --DBMS_OUTPUT.PUT_LINE ('31');

                  SELECT   COUNT (1)
                    INTO   v_count
                    FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                   WHERE   UPPER (t1.nmpath) =
                              UPPER(   v_n0
                                    || '/'
                                    || vi_n1
                                    || '/'
                                    || vi_n2
                                    || '/'
                                    || vi_n3)
                           AND t1.idcontato = t2.idcontato(+)
                           AND DECODE (t2.idcontato, NULL, 0, 1) = 0;

                  IF v_count = 1
                  THEN
                     --DBMS_OUTPUT.PUT_LINE ('32');

                     SELECT   t1.idcontato
                       INTO   v_idcontaton3
                       FROM   contatoadm.contato t1,
                              contatoadm.contatofolha t2
                      WHERE   UPPER (t1.nmpath) =
                                 UPPER(   v_n0
                                       || '/'
                                       || vi_n1
                                       || '/'
                                       || vi_n2
                                       || '/'
                                       || vi_n3)
                              AND t1.idcontato = t2.idcontato(+)
                              AND DECODE (t2.idcontato, NULL, 0, 1) = 0;

                     v_count := 1;
                     v_nmpath :=
                        'VIVO/' || vi_n1 || '/' || vi_n2 || '/' || vi_n3;
                     vo_idcontato := v_idcontaton3;
                  ELSE
                     v_count := 0;
                  END IF;
               ELSIF v_count > 0 AND vi_n4 IS NULL
               THEN
                  --DBMS_OUTPUT.PUT_LINE ('33');

                  SELECT   COUNT (1)
                    INTO   v_count
                    FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                   WHERE   UPPER (t1.nmpath) =
                              UPPER(   v_n0
                                    || '/'
                                    || vi_n1
                                    || '/'
                                    || vi_n2
                                    || '/'
                                    || vi_n3)
                           AND t1.idcontato = t2.idcontato(+)
                           AND DECODE (t2.idcontato, NULL, 0, 1) = 1;

                  IF v_count = 1
                  THEN
                     --DBMS_OUTPUT.PUT_LINE ('34');

                     SELECT   t1.idcontato
                       INTO   v_idcontaton3
                       FROM   contatoadm.contato t1,
                              contatoadm.contatofolha t2
                      WHERE   UPPER (t1.nmpath) =
                                 UPPER(   v_n0
                                       || '/'
                                       || vi_n1
                                       || '/'
                                       || vi_n2
                                       || '/'
                                       || vi_n3)
                              AND t1.idcontato = t2.idcontato(+)
                              AND DECODE (t2.idcontato, NULL, 0, 1) = 1;

                     v_count := 1;
                     v_nmpath :=
                        'VIVO/' || vi_n1 || '/' || vi_n2 || '/' || vi_n3;
                     vo_idcontato := v_idcontaton3;
                  ELSE
                     v_count := 0;
                  END IF;
               ELSE
                  v_count := 0;
               END IF;


               ----DBMS_OUTPUT.PUT_LINE ('v_idcontaton3 A = ' || v_idcontaton3);

               /* FIM*/

               IF v_count = 0
               THEN
                  ------DBMS_OUTPUT.PUT_LINE ('N3_2');

                  INSERT INTO contatoadm.contato (
                                                     idcontato,
                                                     idnomecontato,
                                                     indisponibilidade,
                                                     nmpath,
                                                     idtipoarvore,
                                                     idusuarioalteracao,
                                                     dtultimaalteracao
                             )
                    VALUES   (
                                 contatosq.NEXTVAL,
                                 (SELECT   MAX (idnomecontato)
                                    FROM   contatoadm.nomecontato
                                   WHERE   UPPER (nmcontato) = UPPER (vi_n3)),
                                 1,
                                    v_n0
                                 || '/'
                                 || vi_n1
                                 || '/'
                                 || vi_n2
                                 || '/'
                                 || vi_n3,
                                 1,
                                 1,
                                 SYSDATE
                             );

                  COMMIT;

                  SELECT   contatoadm.contatosq.CURRVAL
                    INTO   v_idcontaton3
                    FROM   DUAL;

                  SELECT   COUNT (1)
                    INTO   v_count
                    FROM   contatoadm.contatohierarquia
                   WHERE   idcontato = v_idcontaton3;

                  ----DBMS_OUTPUT.PUT_LINE (                     'v_idcontaton3 B = ' || v_idcontaton3                  );


                  IF v_count = 0
                  THEN
                     ------DBMS_OUTPUT.PUT_LINE ('N3_3');

                     INSERT INTO contatoadm.contatohierarquia (
                                                                  idcontatopai,
                                                                  idcontato,
                                                                  idusuarioalteracao,
                                                                  dtultimaalteracao
                                )
                       VALUES   (v_idcontaton2,
                                 v_idcontaton3,
                                 1,
                                 SYSDATE);

                     COMMIT;
                  END IF;

                  COMMIT;
                  /*else
                     ------DBMS_OUTPUT.PUT_LINE ('N3_4');

                     select   idcontato
                       into   v_idcontaton3
                       from   contatoadm.contato
                      where   UPPER (nmpath) =
                                 UPPER(   v_n0
                                       || '/'
                                       || vi_n1
                                       || '/'
                                       || vi_n2
                                       || '/'
                                       || vi_n3);
                                       */

                  v_nmpath := 'VIVO/' || vi_n1 || '/' || vi_n2 || '/' || vi_n3;
                  vo_idcontato := v_idcontaton3;
               END IF;
            ------DBMS_OUTPUT.PUT_LINE ('v_idcontaton3= '||v_idcontaton3);
            END IF;

            --N4
            IF vi_n4 IS NOT NULL
            THEN
               vo_cderro := 5;

               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.nomecontato
                WHERE   UPPER (nmcontato) = UPPER (vi_n4);



               IF v_count = 0
               THEN
                  ------DBMS_OUTPUT.PUT_LINE ('N4_1');

                  INSERT INTO contatoadm.nomecontato (idnomecontato,
                                                      nmcontato,
                                                      idusuarioalteracao,
                                                      dtultimaalteracao)
                    VALUES   (nomecontatosq.NEXTVAL,
                              vi_n4,
                              1,
                              SYSDATE);

                  COMMIT;
               END IF;

               /* VERIFICANDO SE EXISTE ALGUM VALOR PARA O PATH QUE EXISTA UMA FOLHA */
               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.contato t1
                WHERE   UPPER (t1.nmpath) =
                           UPPER(   v_n0
                                 || '/'
                                 || vi_n1
                                 || '/'
                                 || vi_n2
                                 || '/'
                                 || vi_n3
                                 || '/'
                                 || vi_n4);

               IF v_count > 0 AND vi_n5 IS NOT NULL
               THEN
                  --DBMS_OUTPUT.PUT_LINE ('41');

                  SELECT   COUNT (1)
                    INTO   v_count
                    FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                   WHERE   UPPER (t1.nmpath) =
                              UPPER(   v_n0
                                    || '/'
                                    || vi_n1
                                    || '/'
                                    || vi_n2
                                    || '/'
                                    || vi_n3
                                    || '/'
                                    || vi_n4)
                           AND t1.idcontato = t2.idcontato(+)
                           AND DECODE (t2.idcontato, NULL, 0, 1) = 0;

                  IF v_count = 1
                  THEN
                     --DBMS_OUTPUT.PUT_LINE ('42');

                     SELECT   t1.idcontato
                       INTO   v_idcontaton4
                       FROM   contatoadm.contato t1,
                              contatoadm.contatofolha t2
                      WHERE   UPPER (t1.nmpath) =
                                 UPPER(   v_n0
                                       || '/'
                                       || vi_n1
                                       || '/'
                                       || vi_n2
                                       || '/'
                                       || vi_n3
                                       || '/'
                                       || vi_n4)
                              AND t1.idcontato = t2.idcontato(+)
                              AND DECODE (t2.idcontato, NULL, 0, 1) = 0;

                     v_count := 1;
                     v_nmpath :=
                           'VIVO/'
                        || vi_n1
                        || '/'
                        || vi_n2
                        || '/'
                        || vi_n3
                        || '/'
                        || vi_n4;
                     vo_idcontato := v_idcontaton4;
                  ELSE
                     v_count := 0;
                  END IF;
               ELSIF v_count > 0 AND vi_n5 IS NULL
               THEN
                  --DBMS_OUTPUT.PUT_LINE ('43');

                  SELECT   COUNT (1)
                    INTO   v_count
                    FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                   WHERE   UPPER (t1.nmpath) =
                              UPPER(   v_n0
                                    || '/'
                                    || vi_n1
                                    || '/'
                                    || vi_n2
                                    || '/'
                                    || vi_n3
                                    || '/'
                                    || vi_n4)
                           AND t1.idcontato = t2.idcontato(+)
                           AND DECODE (t2.idcontato, NULL, 0, 1) = 1;

                  IF v_count = 1
                  THEN
                     --DBMS_OUTPUT.PUT_LINE ('44');

                     SELECT   t1.idcontato
                       INTO   v_idcontaton4
                       FROM   contatoadm.contato t1,
                              contatoadm.contatofolha t2
                      WHERE   UPPER (t1.nmpath) =
                                 UPPER(   v_n0
                                       || '/'
                                       || vi_n1
                                       || '/'
                                       || vi_n2
                                       || '/'
                                       || vi_n3
                                       || '/'
                                       || vi_n4)
                              AND t1.idcontato = t2.idcontato
                              AND DECODE (t2.idcontato, NULL, 0, 1) = 1;

                     v_count := 1;
                     v_nmpath :=
                           'VIVO/'
                        || vi_n1
                        || '/'
                        || vi_n2
                        || '/'
                        || vi_n3
                        || '/'
                        || vi_n4;
                     vo_idcontato := v_idcontaton4;
                  ELSE
                     v_count := 0;
                  END IF;
               ELSE
                  v_count := 0;
               END IF;


               ----DBMS_OUTPUT.PUT_LINE ('v_idcontaton4 A = ' || v_idcontaton4);


               /* FIM*/
               IF v_count = 0
               THEN
                  ------DBMS_OUTPUT.PUT_LINE ('N4_2');

                  INSERT INTO contatoadm.contato (
                                                     idcontato,
                                                     idnomecontato,
                                                     indisponibilidade,
                                                     nmpath,
                                                     idtipoarvore,
                                                     idusuarioalteracao,
                                                     dtultimaalteracao
                             )
                    VALUES   (
                                 contatosq.NEXTVAL,
                                 (SELECT   MAX (idnomecontato)
                                    FROM   contatoadm.nomecontato
                                   WHERE   UPPER (nmcontato) = UPPER (vi_n4)),
                                 1,
                                    v_n0
                                 || '/'
                                 || vi_n1
                                 || '/'
                                 || vi_n2
                                 || '/'
                                 || vi_n3
                                 || '/'
                                 || vi_n4,
                                 1,
                                 1,
                                 SYSDATE
                             );


                  SELECT   contatoadm.contatosq.CURRVAL
                    INTO   v_idcontaton4
                    FROM   DUAL;

                  SELECT   COUNT (1)
                    INTO   v_count
                    FROM   contatoadm.contatohierarquia
                   WHERE   idcontato = v_idcontaton4;

                  ----DBMS_OUTPUT.PUT_LINE (                     'v_idcontaton4 B = ' || v_idcontaton4                  );


                  IF v_count = 0
                  THEN
                     ------DBMS_OUTPUT.PUT_LINE ('N4_3');

                     INSERT INTO contatoadm.contatohierarquia (
                                                                  idcontatopai,
                                                                  idcontato,
                                                                  idusuarioalteracao,
                                                                  dtultimaalteracao
                                )
                       VALUES   (v_idcontaton3,
                                 v_idcontaton4,
                                 1,
                                 SYSDATE);

                     COMMIT;
                  END IF;

                  COMMIT;
                  /*            ELSE
                                    ------DBMS_OUTPUT.PUT_LINE ('N4_4');

                                    SELECT   idcontato
                                      INTO   v_idcontaton4
                                      FROM   contatoadm.contato
                                     WHERE   UPPER (nmpath) =
                                                UPPER(   v_n0
                                                      || '/'
                                                      || vi_n1
                                                      || '/'
                                                      || vi_n2
                                                      || '/'
                                                      || vi_n3
                                                      || '/'
                                                      || vi_n4);




                  */
                  v_nmpath :=
                        'VIVO/'
                     || vi_n1
                     || '/'
                     || vi_n2
                     || '/'
                     || vi_n3
                     || '/'
                     || vi_n4;
                  vo_idcontato := v_idcontaton4;
               END IF;
            ------DBMS_OUTPUT.PUT_LINE ('vo_idcontaton4 =' || v_idcontaton4);*/
            END IF;

            --N5
            IF vi_n5 IS NOT NULL
            THEN
               vo_cderro := 5;


               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.nomecontato
                WHERE   UPPER (nmcontato) = UPPER (vi_n5);

               IF v_count = 0
               THEN
                  ------DBMS_OUTPUT.PUT_LINE ('N5_1');

                  INSERT INTO contatoadm.nomecontato (idnomecontato,
                                                      nmcontato,
                                                      idusuarioalteracao,
                                                      dtultimaalteracao)
                    VALUES   (nomecontatosq.NEXTVAL,
                              vi_n5,
                              1,
                              SYSDATE);

                  COMMIT;
               END IF;

               /* VERIFICANDO SE EXISTE ALGUM VALOR PARA O PATH QUE EXISTA UMA FOLHA */
               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.contato t1
                WHERE   UPPER (t1.nmpath) =
                           UPPER(   v_n0
                                 || '/'
                                 || vi_n1
                                 || '/'
                                 || vi_n2
                                 || '/'
                                 || vi_n3
                                 || '/'
                                 || vi_n4
                                 || '/'
                                 || vi_n5);

               IF v_count > 0
               THEN
                  SELECT   COUNT (1)
                    INTO   v_count
                    FROM   contatoadm.contato t1, contatoadm.contatofolha t2
                   WHERE   UPPER (t1.nmpath) =
                              UPPER(   v_n0
                                    || '/'
                                    || vi_n1
                                    || '/'
                                    || vi_n2
                                    || '/'
                                    || vi_n3
                                    || '/'
                                    || vi_n4
                                    || '/'
                                    || vi_n5)
                           AND t1.idcontato = t2.idcontato(+)
                           AND DECODE (t2.idcontato, NULL, 0, 1) = 1;

                  IF v_count = 1
                  THEN
                     SELECT   t1.idcontato
                       INTO   v_idcontaton5
                       FROM   contatoadm.contato t1,
                              contatoadm.contatofolha t2
                      WHERE   UPPER (t1.nmpath) =
                                 UPPER(   v_n0
                                       || '/'
                                       || vi_n1
                                       || '/'
                                       || vi_n2
                                       || '/'
                                       || vi_n3
                                       || '/'
                                       || vi_n4
                                       || '/'
                                       || vi_n5)
                              AND t1.idcontato = t2.idcontato(+)
                              AND DECODE (t2.idcontato, NULL, 0, 1) = 1;

                     v_count := 1;
                     v_nmpath :=
                           'VIVO/'
                        || vi_n1
                        || '/'
                        || vi_n2
                        || '/'
                        || vi_n3
                        || '/'
                        || vi_n4
                        || '/'
                        || vi_n5;
                     vo_idcontato := v_idcontaton5;
                  ELSE
                     v_count := 0;
                  END IF;
               ELSE
                  v_count := 0;
               END IF;

               ----DBMS_OUTPUT.PUT_LINE ('v_idcontaton5 A = ' || v_idcontaton5);

               /* FIM*/

               IF v_count = 0
               THEN
                  ------DBMS_OUTPUT.PUT_LINE ('N5_2');

                  INSERT INTO contatoadm.contato (
                                                     idcontato,
                                                     idnomecontato,
                                                     indisponibilidade,
                                                     nmpath,
                                                     idtipoarvore,
                                                     idusuarioalteracao,
                                                     dtultimaalteracao
                             )
                    VALUES   (
                                 contatosq.NEXTVAL,
                                 (SELECT   MAX (idnomecontato)
                                    FROM   contatoadm.nomecontato
                                   WHERE   UPPER (nmcontato) = UPPER (vi_n5)),
                                 1,
                                    v_n0
                                 || '/'
                                 || vi_n1
                                 || '/'
                                 || vi_n2
                                 || '/'
                                 || vi_n3
                                 || '/'
                                 || vi_n4
                                 || '/'
                                 || vi_n5,
                                 1,
                                 1,
                                 SYSDATE
                             );

                  COMMIT;

                  SELECT   contatoadm.contatosq.CURRVAL
                    INTO   v_idcontaton5
                    FROM   DUAL;

                  SELECT   COUNT (1)
                    INTO   v_count
                    FROM   contatoadm.contatohierarquia
                   WHERE   idcontato = v_idcontaton5;

                  ----DBMS_OUTPUT.PUT_LINE (                     'v_idcontaton5 B = ' || v_idcontaton5                  );


                  IF v_count = 0
                  THEN
                     ------DBMS_OUTPUT.PUT_LINE ('N5_3');

                     INSERT INTO contatoadm.contatohierarquia (
                                                                  idcontatopai,
                                                                  idcontato,
                                                                  idusuarioalteracao,
                                                                  dtultimaalteracao
                                )
                       VALUES   (v_idcontaton4,
                                 v_idcontaton5,
                                 1,
                                 SYSDATE);

                     COMMIT;
                  END IF;

                  /*            else
                                 ------DBMS_OUTPUT.PUT_LINE ('N5_4');

                                 select   idcontato
                                   into   v_idcontaton5
                                   from   contatoadm.contato
                                  where   UPPER (nmpath) =
                                             UPPER(   v_n0
                                                   || '/'
                                                   || vi_n1
                                                   || '/'
                                                   || vi_n2
                                                   || '/'
                                                   || vi_n3
                                                   || '/'
                                                   || vi_n4
                                                   || '/'
                                                   || vi_n5);
                                                   */

                  v_nmpath :=
                        'VIVO/'
                     || vi_n1
                     || '/'
                     || vi_n2
                     || '/'
                     || vi_n3
                     || '/'
                     || vi_n4
                     || '/'
                     || vi_n5;
                  vo_idcontato := v_idcontaton5;
               END IF;
            END IF;

            vo_cderro := 6;

            --------------INSERINDO FOLHA
            SELECT   COUNT (1)
              INTO   v_count
              FROM   contatoadm.contatofolha
             WHERE   idcontato = vo_idcontato;    --ultimo contato configurado

            --DBMS_OUTPUT.PUT_LINE (               'ultimo contato configurado= ' || vo_idcontato            );


            IF v_count = 0
            THEN
               --DBMS_OUTPUT.PUT_LINE ('CRIANDO FOLHA');
               vo_cderro := 7;

               SELECT   COUNT (1)
                 INTO   v_count
                 FROM   contatoadm.contatohierarquia c
                WHERE   c.idcontatopai = vo_idcontato;

               IF v_count = 0
               THEN
                  vo_cderro := 8;

                  ------DBMS_OUTPUT.PUT_LINE ('vo_idcontato '||vo_idcontato);

                  INSERT INTO contatoadm.contatofolha (
                                                          idunidade,
                                                          idcontato,
                                                          qthorasprazocontato,
                                                          infechamentoimediato,
                                                          vlpesocontato,
                                                          idtiporetornocontato,
                                                          inprocessotecnico,
                                                          idusuarioalteracao,
                                                          dtultimaalteracao,
                                                          idtipofechamentocontato,
                                                          idtipoprocesso,
                                                          inaberturacontato,
                                                          qthorasprazoanatel,
                                                          insms,
                                                          dssms,
                                                          dtstatussms,
                                                          idusuariostatussms,
                                                          inrelacionamento,
                                                          inprotocolo,
                                                          inexibeprotocolo,
                                                          dscontatocanais,
                                                          dsmsgexcecao,
                                                          incancelamento,
                                                          idclassificacaosms,
                                                          idtipofechamentoprotocolo,
                                                          sgregraencaminhamento,
                                                          sgfluxoatendimento
                             )
                    VALUES   (0,                                  --IDUNIDADE,
                              vo_idcontato,                       --IDCONTATO,
                              120,                      --QTHORASPRAZOCONTATO,
                              0,                       --INFECHAMENTOIMEDIATO,
                              2,                              --VLPESOCONTATO,
                              0,                       --IDTIPORETORNOCONTATO,
                              0,                          --INPROCESSOTECNICO,
                              777,                       --IDUSUARIOALTERACAO,
                              SYSDATE,                    --DTULTIMAALTERACAO,
                              1,                    --IDTIPOFECHAMENTOCONTATO,
                              1,                             --IDTIPOPROCESSO,
                              '0',                        --INABEROTURACNTATO,
                              NULL,                      --QTHORASPRAZOANATEL,
                              1,                                      --INSMS,
                              NULL,                                   --DSSMS,
                              SYSDATE,                          --DTSTATUSSMS,
                              777,                       --IDUSUARIOSTATUSSMS,
                              1,                           --INRELACIONAMENTO,
                              1,                                --INPROTOCOLO,
                              1,                           --INEXIBEPROTOCOLO,
                              NULL,                         --DSCONTATOCANAIS,
                              NULL,                            --DSMSGEXCECAO,
                              0,                             --INCANCELAMENTO,
                              vi_idclassificacao,        --IDCLASSIFICACAOSMS,
                              NULL,               --IDTIPOFECHAMENTOPROTOCOLO,
                              NULL,                   --SGREGRAENCAMINHAMENTO,
                              NULL                       --SGFLUXOATENDIMENTO,
                                  );

                  COMMIT;
               END IF;
            END IF;
         END IF;

         vo_cderro := 0;
      EXCEPTION
         WHEN OTHERS
         THEN
            vo_dserro := SQLERRM;
      END;

      --final da procedure

      --DBMS_OUTPUT.PUT_LINE ('vo_idcontato FINAL = ' || vo_idcontato);


      UPDATE   load.cargacontato c
         SET   c.idcontato = vo_idcontato,
               c.cderro = vo_cderro,
               c.dserro = vo_dserro
       WHERE   ROWID = v_rid;

      COMMIT;
   END LOOP;

   COMMIT;

   INSERT INTO contatoadm.contatotipolinha (idcontatotipolinha,
                                            idtipolinha,
                                            idcontato,
                                            idusuarioalteracao,
                                            dtultimaalteracao)
      SELECT   contatoadm.contatotipolinhasq.NEXTVAL,
               tl.idtipolinha,
               cf.idcontato,
               777,
               SYSDATE
        FROM   contatoadm.contatofolha cf,
               (SELECT   DISTINCT idcontato
                  FROM   load.cargacontato
                 WHERE   tipo LIKE 'C%') cg,
               contatoadm.contato c,
               apoio.tipolinha tl
       WHERE       cf.idcontato = c.idcontato
               AND cf.idcontato = cg.idcontato
               AND c.idtipoarvore = 1
               --and tl.idtipolinha > 8; somente para TV - Reginaldo
               AND tl.sgtipolinha = 'TV';

   COMMIT;
END;