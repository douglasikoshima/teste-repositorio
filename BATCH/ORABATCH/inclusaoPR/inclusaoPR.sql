DECLARE
    
    -- DOCUMENTO PARA INCLUSAO NO PR
    CURSOR cPROGRAMARELACIONAMENTO IS
        SELECT /*+ PARALLEL(LT) PARALLEL(LB) PARALLEL(PL) PARALLEL(PDP) PARALLEL(PD) PARALLEL(D) */
            AR.CDAREAREGISTRO,
            LB.NRLINHA,
            SUBSTR(D.NRDOCUMENTO,1,20) AS NRDOCUMENTO
        FROM
            LINHA.LINHATELEFONICA LT
            JOIN LINHA.LINHABASE LB USING (IDLINHABASE)
            JOIN APOIO.AREAREGISTRO AR USING (IDAREAREGISTRO)
            JOIN CUSTOMER.PESSOALINHA PL USING (IDLINHATELEFONICA)
            JOIN CUSTOMER.PESSOADEPARA PDP USING (IDPESSOADEPARA)
            JOIN CUSTOMER.PESSOADOCUMENTO PD USING (IDPESSOA)
            JOIN CUSTOMER.DOCUMENTO D USING (IDDOCUMENTO)
            JOIN APOIO.TIPODOCUMENTO TD USING (IDTIPODOCUMENTO)
            LEFT JOIN CUSTOMER.PROGRAMARELACIONAMENTOPOS PR ON (D.NRDOCUMENTO = PR.NRDOCUMENTO)
        WHERE
            LT.IDTIPOLINHA IN (1,5) -- LINHAS POS
            AND LT.DTEXPIRACAO IS NULL  -- ATIVAS
            AND TD.SGCLASSIFICACAO = 'CPF' -- APENAS CPF
            AND PL.IDTIPORELACIONAMENTO = 2 -- DONO DA LINHA
            AND PR.NRDOCUMENTO IS NULL -- AINDA NAO CADASTRADO NO PR
            ;
    
    TYPE R_PROGRAMARELACIONAMENTO IS RECORD (
        CDAREAREGISTRO  NUMBER,
        NRLINHA         NUMBER,
        NRDOCUMENTO     VARCHAR2(20)
    );

    TYPE T_PROGRAMARELACIONAMENTO IS TABLE OF R_PROGRAMARELACIONAMENTO;
    
    L_PROGRAMARELACIONAMENTO T_PROGRAMARELACIONAMENTO;
    
    V_LIMIT NUMBER := 1000;

    V_HORA_LIMITE VARCHAR2(4) := '0600';

    V_COUNT NUMBER := 0;

    V_NRLINHA NUMBER;
    
BEGIN
    
    OPEN cPROGRAMARELACIONAMENTO;
    LOOP
        --EXIT WHEN TO_CHAR(SYSDATE,'HH24MI') > V_HORA_LIMITE;

        FETCH cPROGRAMARELACIONAMENTO BULK COLLECT INTO L_PROGRAMARELACIONAMENTO LIMIT V_LIMIT;

        FOR I IN 1 .. L_PROGRAMARELACIONAMENTO.COUNT LOOP

            SAVEPOINT D;

            BEGIN
                INSERT INTO CUSTOMER.PROGRAMARELACIONAMENTOPOS
                (
                    IDPROGRAMARELACIONAMENTO,
                    NRDOCUMENTO,
                    DTCADASTRO,
                    DTEXCLUSAO,
                    DTULTIMAATUALIZACAO,
                    IDUSUARIOATUALIZACAO,
                    IDPESSOAENDERECO,
                    NRLINHASOLICITANTE,
                    INTIPOINSCRICAO
                ) 
                VALUES 
                (
                    1,
                    L_PROGRAMARELACIONAMENTO(I).NRDOCUMENTO,
                    SYSDATE,
                    NULL,
                    SYSDATE,
                    666,
                    'null',
                    TO_NUMBER(L_PROGRAMARELACIONAMENTO(I).CDAREAREGISTRO||L_PROGRAMARELACIONAMENTO(I).NRLINHA),
                    1
                );
            EXCEPTION
                WHEN OTHERS THEN
                    ROLLBACK TO SAVEPOINT D;
            END;


            SAVEPOINT C;

            BEGIN

                V_NRLINHA := TO_NUMBER(L_PROGRAMARELACIONAMENTO(I).CDAREAREGISTRO||L_PROGRAMARELACIONAMENTO(I).NRLINHA);

                SELECT
                    COUNT(1)
                INTO
                    V_COUNT
                FROM
                    CUSTOMER.CARGAPROGRAMARELACIONAMENTO 
                WHERE
                    NRTELEFONE = V_NRLINHA;

                IF V_COUNT = 0 THEN

                    INSERT INTO CUSTOMER.CARGAPROGRAMARELACIONAMENTO
                    (
                        NRDOCUMENTO,
                        NRTELEFONE,
                        INPR,
                        DTCADASTROPR,
                        INCADASTROMANUAL,
                        NMLOGINATUALIZACAOPR,
                        IDSISTEMAORIGEMPR
                    )
                    VALUES
                    (
                        L_PROGRAMARELACIONAMENTO(I).NRDOCUMENTO,
                        V_NRLINHA,
                        1,
                        SYSDATE, 
                        0,
                        'vnet',
                        7
                    );
                    
                END IF;
                
            EXCEPTION
                WHEN OTHERS THEN
                    ROLLBACK TO SAVEPOINT C;
            END;
            
        END LOOP;

        COMMIT;

        EXIT WHEN L_PROGRAMARELACIONAMENTO.COUNT < V_LIMIT;

   END LOOP;

   CLOSE cPROGRAMARELACIONAMENTO; 

END;
/

