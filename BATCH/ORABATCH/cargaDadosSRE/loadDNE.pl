#. $P2KHOME/scripts/use_perl
#!perl
#ident @(#) P2K: src/cacs/tools/loadDNE/loadDNE.pl 77.3 11/02/02 07:47:17
# (c) 2007,2008,2009,2010,2011, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   loadDNE.pl  -  03/22/2004 by aldebaran perseke
#
#   Load the INTEGRIS_PROJECT schema tables from DNE flat files
#   (load information from 'correios CD' into the database)
#
#   PARAMETERS:
#   ./loadDNE.pl --noUpdates --reportFile=reportAC --state=AC --input=/path/to/DNE/files
#   --help - print this help message
#   --state=XX - import only the addresses for state XX
#   --noUpdates - do not update/create records in the database
#   --reportFile=name - create file "name" with a report of duplicate records
#   --input=path - read input (DNE) files from this path
#
#   REQUIRES:
#   - Files: stringUtility.pm, pais.pm, unidade_federacao.pm,
#   centro_distribuicao.pm, tipo_logradouro.pm, titulo_logradouro.pm,
#   localidade.pm, bairro.pm, CEP.pm, logradouro.pm, rel_cep_logradouro_bairro.pm,
#   abrevBairro.txt, abrevLocalidade.txt, connectives.txt, caixa_postal_comunitaria.pm
#
#   - Oracle and Perl installation (and correct version set at the top of perl files)
#
#   - Environment variables:
#     ADDRVLD_DSN (database string - dbi:Oracle:host=10.128.4.53;port=1522;sid=telac)
#                 (can also be obtained using ./tnsnames2DBIDSN.pl $TWO_TASK)
#     ADDRVLD_USER (database user)
#     ADDRVLD_PASWD (database password)
#
#   - Disable triggers before running:
#     (remember to re-enable them after you're done)
#     alter trigger INTEGRIS_PROJECT.TRG_RECELOBA_B_I disable;
#     alter trigger INTEGRIS_PROJECT.TRG_RECELOBA_B_U disable;
################################################################################

use strict;
use DBI;
use DBD::Oracle qw(:ora_types);

use stringUtility;
use pais;
use unidade_federacao;
use centro_distribuicao;
use tipo_logradouro;
use titulo_logradouro;
use localidade;
use bairro;
use CEP;
use logradouro;
use rel_cep_logradouro_bairro;
use caixa_postal_comunitaria;

#   constants

use constant SUCCESS  => 0;
use constant ERROR    => 1;

use constant TRUE     => 1;
use constant FALSE    => 0;

#   database connection parameters

my  $DSN      = $ENV{ 'ADDRVLD_DSN' };
my  $user     = $ENV{ 'ADDRVLD_USER' };
my  $password = $ENV{ 'ADDRVLD_PASWD' };
my  $opts     = {AutoCommit => 0, PrintError => 1, RaiseError => 0};

my  $connection;

#   global variables for the script

my  $brazil;
my  %hash_unidade_federacao;
my  %hash_centro_distribuicao;
my  %hash_centro_distribuicaoUF;
my  %hash_centro_distribuicaoLocalidade;
my  %hash_tipo_logradouro;
my  %hash_titulo_logradouro;
my  %hash_abrevLocalidade;
my  %hash_localidade;
my  %hash_abrevBairro;
my  %hash_bairro;
my  %hash_CEP;
my  %hash_abrevLogradouro;
my  %hash_logradouro;
my  %hash_caixa_postal_comunitaria;

my  $today;
my  $importUser = 'loadDNE';
my  $selectedState = '';
my  $noUpdates = 0;
my  $reportFile = '';
my  $inputPath = '.';

my $sysDebug = 0;

use Term::ANSIColor;

################################################################################
#   load all unidade_federacao for Brazil
################################################################################

sub loadUnidade_Federacao {
    print color('yellow') . "loadUnidade_Federacao() in" . color('reset') . "\n" unless !$sysDebug;


    print STDOUT qq/loading Unidade_Federacao reference data ...\n/;

    my  @key;

    %hash_unidade_federacao = &unidade_federacao::queryByPais( $connection, $brazil );
    @key = keys( %hash_unidade_federacao );

    printf STDOUT qq/\t%d lines loaded\n/, $#key + 1;

    print color('yellow') . "loadUnidade_Federacao() out" . color('reset') . "\n" unless !$sysDebug;
}

################################################################################
#   import "DNE_GU_UNIDADES_OPERACIONAIS.TXT" flat file function
################################################################################

sub importCentroDistribuicao {
    print color('yellow') . "importCentroDistribuicao() in" . color('reset') . "\n" unless !$sysDebug;
    my  $flatFileName = $_[ 0 ];
    my  $recordType = $_[ 1 ];

    print STDOUT qq/importing "$flatFileName" DNE flat file ...\n/;

    my  $siglaUF;
    my  $ID_localidade;
    my  $DNE_ID_localidade;
    my  $nome_localidade;
    my  $ID_bairro;
    my  $DNE_ID_bairro;
    my  $nome_bairro;
    my  $tipoUnidade;
    my  $numeroCEP;
    my  $ID_cdd;
    my  $DNE_ID_cdd;
    my  $nome_cdd;
    my  $tipoNumeracaoCaixaPostal1;
    my  $numeracaoInicialCaixaPostal1;
    my  $numeracaoFinalCaixaPostal1;
    my  $tipoNumeracaoCaixaPostal2;
    my  $numeracaoInicialCaixaPostal2;
    my  $numeracaoFinalCaixaPostal2;
    my  $tipoNumeracaoCaixaPostal3;
    my  $numeracaoInicialCaixaPostal3;
    my  $numeracaoFinalCaixaPostal3;

    my  $desc_tipo_logradouro;
    my  $preposicao;
    my  $desc_titulo_logradouro;
    my  $ID_logradouro;
    my  $DNE_ID_logradouro;
    my  $nome_logradouro;
    my  $nome_abrev_logradouro;
    my  $numero;

    my  $localidade;
    my  $tipo_logradouro;
    my  $titulo_logradouro;
    my  $logradouro;
    my  $rel_cep_logradouro_bairro;

    my  $centro_distribuicao;
    my  $exists;
    my  $dontUpdate;
    my  $numReadedLines = 0;
    my  $numImportedLines = 0;
    my  $numeroInicial;
    my  $numeroFinal;
    my  $paridade;

    my  $dbErrors = 0;
    my  $returnCode;
    my  $commitCount = 0;

#   open the flat file and import the data

    open CENTRO_DISTRIBUICAO, "< $flatFileName"
            or die qq/couldn\'t open "$flatFileName" flat file\n/;

    while( <CENTRO_DISTRIBUICAO> ) {
        chop;
        $numReadedLines++;
        print STDOUT "\t$numReadedLines\r";

#        $dbErrors = 0;
        $dontUpdate = 0;
#   header line

        if( 1 == $numReadedLines
                && $_ =~ /^C/ ) {
            if( $_ !~ /UNIDADES OPERACIONAIS\s*$/ ) {
                die qq/"$flatFileName" isn\'t the DNE UNIDADES OPERACIONAIS flat file\n/;
            }

            next;
        }

#   detail line

        if( 1 < $numReadedLines
                && $_ =~ /^D/ )
        {
            if( 426 != length ( $_ ) ) {
                die qq/wrong "DNE_GU_UNIDADES_OPERACIONAIS" detail record length\n/;
            }

            $siglaUF = stringUtility::trim( substr( $_, 1, 2 ) );
            $ID_localidade = substr( $_, 3, 6 );
            $DNE_ID_localidade = substr( $_, 9, 8 );
            $nome_localidade = stringUtility::trim( substr( $_, 17, 72 ) );
            $ID_bairro = substr( $_, 89, 5 );
            $DNE_ID_bairro = substr( $_, 94, 8 );
            $nome_bairro = stringUtility::trim( substr( $_, 102, 72 ) );
            $tipoUnidade = stringUtility::trim( substr( $_, 174, 72 ) );
            $numeroCEP = substr( $_, 246, 8 );
            $ID_cdd = substr( $_, 254, 6 );
            $DNE_ID_cdd = substr( $_, 260, 8 );
            $nome_cdd = stringUtility::trim( substr( $_, 268, 72 ) );
            $tipoNumeracaoCaixaPostal1 = stringUtility::trim( substr( $_, 376, 1 ) );
            $numeracaoInicialCaixaPostal1 = substr( $_, 377, 7 );
            $numeracaoFinalCaixaPostal1 = substr( $_, 384, 7 );
            $tipoNumeracaoCaixaPostal2 = stringUtility::trim( substr( $_, 391, 1 ) );
            $numeracaoInicialCaixaPostal2 = substr( $_, 392, 7 );
            $numeracaoFinalCaixaPostal2 = substr( $_, 399, 7 );
            $tipoNumeracaoCaixaPostal3 = stringUtility::trim( substr( $_, 406, 1 ) );
            $numeracaoInicialCaixaPostal3 = substr( $_, 407, 7 );
            $numeracaoFinalCaixaPostal3 = substr( $_, 414, 7 );

#   input record validation

            if( $selectedState ne ''
                    && $siglaUF ne $selectedState ) {
                next;
            }

            if( 'D' ne $recordType ) {
                next;
            }

#   construct a new CDD object

            $centro_distribuicao = centro_distribuicao::queryByNome( $connection
                    , $nome_cdd );

            if( ! defined $centro_distribuicao
                    || 0 == $centro_distribuicao ) {
                $centro_distribuicao = centro_distribuicao::queryByNome( $connection
                        , qq/CDD $nome_cdd/ );
            }

            if( ! defined $centro_distribuicao
                    || 0 == $centro_distribuicao ) {
                $centro_distribuicao = centro_distribuicao::queryByNome( $connection
                        , qq/CDD $nome_cdd\/$siglaUF/ );
            }

            if( ! defined $centro_distribuicao
                    || 0 == $centro_distribuicao ) {
                $centro_distribuicao = centro_distribuicao::queryByNome( $connection
                        , qq/$nome_cdd - $siglaUF/ );
            }

            if( ! defined $centro_distribuicao
                    || 0 == $centro_distribuicao ) {
                $centro_distribuicao = centro_distribuicao::new();
                $exists = 0;
            } else {
                $exists = 1;
                if( ! exists $hash_centro_distribuicaoUF{ $hash_unidade_federacao{ $siglaUF }->codigo() } ) {
                    $hash_centro_distribuicaoUF{ $hash_unidade_federacao{ $siglaUF }->codigo() } = $centro_distribuicao;
                }
                if( ! exists $hash_centro_distribuicaoLocalidade{ $DNE_ID_localidade } ) {
                    $hash_centro_distribuicaoLocalidade{ $DNE_ID_localidade } = $centro_distribuicao;
                }
                if( ($centro_distribuicao->nome() eq $nome_cdd
                        && $centro_distribuicao->descricao() eq $nome_cdd )
                        || $centro_distribuicao->nome() ne $centro_distribuicao->descricao() ) {
                    $dontUpdate = 1;
                }
                if( 1 == $noUpdates
                        && '' ne $reportFile
                        && ( $centro_distribuicao->nome() ne $nome_cdd
                        || $centro_distribuicao->descricao() ne $nome_cdd ) ) {
                    printf REPORT qq/CENTRO_DISTRIBUICAO: record "$nome_cdd" already exists on database and it is different from the flat file:%s\n\n/
                            , $centro_distribuicao->to_string();
                }
            }

            $centro_distribuicao->nome( $nome_cdd );
            $centro_distribuicao->descricao( $nome_cdd );
            $centro_distribuicao->dataAtualizacao( $today );
            $centro_distribuicao->usuarioAtualizacao( $importUser );

#   store a map between database ID_cdd and DNE_ID_cdd

            $hash_centro_distribuicao{ $DNE_ID_cdd } = $centro_distribuicao;
            if( ! exists $hash_centro_distribuicaoUF{ $hash_unidade_federacao{ $siglaUF }->codigo() } ) {
                $hash_centro_distribuicaoUF{ $hash_unidade_federacao{ $siglaUF }->codigo() } = $centro_distribuicao;
            }
            if( ! exists $hash_centro_distribuicaoLocalidade{ $DNE_ID_localidade } ) {
                $hash_centro_distribuicaoLocalidade{ $DNE_ID_localidade } = $centro_distribuicao;
            }

            if( ( 0 == $exists
               ||  0 == $dontUpdate )
                   && 0 == $noUpdates  ) {
                $returnCode = $centro_distribuicao->persist( $connection );

                if( SUCCESS != $returnCode) {
                    $dbErrors++;
                } else {
                    $commitCount++;
                }
            }
            else{
                next;
            }
            $hash_centro_distribuicao{ $DNE_ID_cdd } = $centro_distribuicao;
            if( ! exists $hash_centro_distribuicaoUF{ $hash_unidade_federacao{ $siglaUF }->codigo() } ) {
                $hash_centro_distribuicaoUF{ $hash_unidade_federacao{ $siglaUF }->codigo() } = $centro_distribuicao;
            }
            if( ! exists $hash_centro_distribuicaoLocalidade{ $DNE_ID_localidade } ) {
                $hash_centro_distribuicaoLocalidade{ $DNE_ID_localidade } = $centro_distribuicao;
            }

            $numImportedLines++;

#   Finished a database transaction, make rollback or commit per line

            if(# 0 == $dbErrors &&
               $commitCount >= 10000) {
                $connection->commit();
                $commitCount = 0;
#            } else {
#                $connection->rollback();
            }

            next;
        }

#   address line

        if( 1 < $numReadedLines
                && $_ =~ /^E/ ) {
            if( 426 != length ( $_ ) ) {
                die qq/wrong address record length\n/;
            }

            $desc_tipo_logradouro = stringUtility::trim( substr( $_, 15, 72 ) );
            $preposicao = stringUtility::trim( substr( $_, 87, 3 ) );
            $desc_titulo_logradouro = stringUtility::trim( substr( $_, 90, 72 ) );
            $ID_logradouro = stringUtility::trim( substr( $_, 162, 6 ) );
            $DNE_ID_logradouro = stringUtility::trim( substr( $_, 168, 8 ) );
            $nome_logradouro = stringUtility::trim( substr( $_, 176, 72 ) );
            $numero = stringUtility::trim( substr( $_, 248, 11 ) );

#   input record validation

            if( '' ne $selectedState
                    && $siglaUF ne $selectedState ) {
                next;
            }

            if( substr( $_, 1, 6 ) ne $ID_cdd
                    || substr( $_, 7, 8 ) ne $DNE_ID_cdd ) {
                next;
            }

            if( 'E' ne $recordType ) {
                next;
            }

#   record adjustements

            if( '' ne $preposicao ) {
                $nome_logradouro = qq/$preposicao $nome_logradouro/;
            }

            $numero =~ s/,//g;

            # remove trailing spaces
            $numero =~ s/ *$//g;

            # if $numero is not empty and is numeric only
            if( ('' ne $numero) and ($numero !~ m/\D/) ) {

                $numeroInicial = $numero;
                $numeroFinal = $numero;
                if( 1 == $numero % 2 ) {
                    $paridade = 1;
                } else {
                    $paridade = 2;
                }

            } else {

                $numeroInicial = 1;
                $numeroFinal = 99999;
                $paridade = 0;

            }

#   construct the related objects

            if( ! exists $hash_localidade{ $DNE_ID_localidade } ) {
                $localidade = undef;
            } else {
                $localidade = $hash_localidade{ $DNE_ID_localidade };
            }

            if( ! exists $hash_tipo_logradouro{ $desc_tipo_logradouro } ) {
                $tipo_logradouro = undef;
            } else {
                $tipo_logradouro = $hash_tipo_logradouro{ $desc_tipo_logradouro };
            }

            if( ! exists $hash_titulo_logradouro{ $desc_titulo_logradouro } ) {
                $titulo_logradouro = undef;
            } else {
                $titulo_logradouro = $hash_titulo_logradouro{ $desc_titulo_logradouro };
            }

#   construct a new logradouro object
            $dontUpdate = 0;
            $logradouro = logradouro::queryByLocalidadeNome( $connection
                    , $localidade
                    , $tipo_logradouro
                    , $titulo_logradouro
                    , substr( $nome_logradouro, 0, 60 ) );

            if( ! defined $logradouro
                    || 0 == $logradouro ) {
                $logradouro = logradouro::new();
                $exists = 0;
            } else {
                $exists = 1;
                if(  ((defined $tipo_logradouro && $logradouro->codigoTipo() == $tipo_logradouro->codigo())
                    || !defined $tipo_logradouro)
                    && ((defined $titulo_logradouro && $logradouro->codigoTitulo() == $titulo_logradouro->codigo())
                    || !defined $titulo_logradouro)
                        && $logradouro->codigoLocalidade() == $localidade->codigo()
                        && $logradouro->nome() eq $nome_logradouro  ) {
                    $dontUpdate = 1;
                }
                if( 1 == $noUpdates
                        && '' ne $reportFile
                        && ( $logradouro->codigoLocalidade() != $localidade->codigo()
                        || $logradouro->nome() ne $nome_logradouro ) )
                {
                    if( ! defined $tipo_logradouro )
                    {
                        printf REPORT qq/LOGRADOURO: record "$nome_logradouro" already exists on database:%s\n\n/
                            , $logradouro->to_string();
                    }
                    elsif( $logradouro->codigoTipo() != $tipo_logradouro->codigo() )
                    {
                        printf REPORT qq/LOGRADOURO: record "$nome_logradouro" already exists on database:%s\n\n/
                            , $logradouro->to_string();
                    }
                }
            }

            if( defined $tipo_logradouro ) {
            $logradouro->codigoTipo( $tipo_logradouro->codigo() );
            }
            if( defined $titulo_logradouro ) {
                $logradouro->codigoTitulo( $titulo_logradouro->codigo() );
            }
            $logradouro->codigoLocalidade( $localidade->codigo() );
            $logradouro->nome( $nome_logradouro );
            $logradouro->nomeAbreviado( &stringUtility::abbreviate( $nome_logradouro, \%hash_abrevLogradouro ) );

            if( 'S/N' eq $numero ) {
                $logradouro->indNumeracao( 0 );
            } elsif( '' ne $numero ) {
                $logradouro->indNumeracao( 1 );
            } else {
                $logradouro->indNumeracao( 2 );
            }

            $logradouro->flagComplemento( 0 );
            $logradouro->dataAtualizacao( $today );
            $logradouro->usuarioAtualizacao( $importUser );

#   store a map between database ID_cdd and DNE_ID_cdd
            $hash_logradouro{ $DNE_ID_logradouro } = $logradouro;

            if( ( 0 == $exists
               ||  0 == $dontUpdate )
                   && 0 == $noUpdates  ) {
                $returnCode = $logradouro->persist( $connection );

                if( SUCCESS != $returnCode) {
                    $dbErrors++;
                } else {
                    $commitCount++;
                }
            }

            $hash_logradouro{ $DNE_ID_logradouro } = $logradouro;

#   construct a new CEP and rel_cep_logradouro_bairro object
            if( '' ne $numeroCEP ) {
                &importCEP( $numeroCEP, $DNE_ID_localidade );
            }
            if( exists $hash_CEP{ $numeroCEP }
                    && exists $hash_bairro{ $DNE_ID_bairro } ) {
                $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::queryByCodigo( $connection
                        , $logradouro->codigo()
                        , $hash_CEP{ $numeroCEP }->codigo()
                        , $numeroInicial
                        , $hash_bairro{ $DNE_ID_bairro }->codigo() );

                if( 0 == $noUpdates
                        || ! defined $rel_cep_logradouro_bairro
                        || 0 == $rel_cep_logradouro_bairro ) {
                    $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::new();

                    $rel_cep_logradouro_bairro->codigoLogradouro( $logradouro->codigo() );
                    $rel_cep_logradouro_bairro->codigoCEP( $hash_CEP{ $numeroCEP }->codigo() );
                    $rel_cep_logradouro_bairro->numeroInicial( $numeroInicial );
                    $rel_cep_logradouro_bairro->codigoBairro( $hash_bairro{ $DNE_ID_bairro }->codigo() );
                    $rel_cep_logradouro_bairro->numeroFinal( $numeroFinal );
                    $rel_cep_logradouro_bairro->indLadoNumeracao( $paridade );
                    $rel_cep_logradouro_bairro->dataAtualizacao( $today );
                    $rel_cep_logradouro_bairro->usuarioAtualizacao( $importUser );

                    $returnCode = $rel_cep_logradouro_bairro->persist( $connection );

                    if( SUCCESS != $returnCode) {
                        $dbErrors++;
                    }

                } elsif( 1 == $noUpdates
                        && '' ne $reportFile
                        && $rel_cep_logradouro_bairro->numeroFinal() != $numeroFinal ) {
                    printf REPORT qq/REL_CEP_LOGRAD_BAIRRO: record "%d - %d - %d - %d" already exists on database:%s\n\n/
                            , $logradouro->codigo()
                            , $hash_CEP{ $numeroCEP }->codigo()
                            , $numeroInicial
                            , $hash_bairro{ $DNE_ID_bairro }->codigo()
                            , $rel_cep_logradouro_bairro->to_string();
                }
            }

#   construct a new CDD object
                $dontUpdate = 0;
                $centro_distribuicao = centro_distribuicao::queryByNome( $connection
                    , $nome_cdd );
                if( defined $centro_distribuicao
                    && 0 != $centro_distribuicao ) {
                    if( ($centro_distribuicao->nome() ne $nome_cdd
                                || $centro_distribuicao->descricao() ne $nome_cdd )
                                || $centro_distribuicao->codigoCEP() != $hash_CEP{ $numeroCEP }->codigo()
                                || $centro_distribuicao->codigoBairro() != $hash_bairro{ $DNE_ID_bairro }->codigo()
                                || $centro_distribuicao->codigoLogradouro() != $hash_logradouro{ $DNE_ID_logradouro }->codigo() ) {
                            $centro_distribuicao->nome( $nome_cdd );
                            $centro_distribuicao->descricao( $nome_cdd );
                            $centro_distribuicao->codigoCEP($hash_CEP{ $numeroCEP }->codigo());
                            $centro_distribuicao->codigoBairro($hash_bairro{ $DNE_ID_bairro }->codigo());
                            $centro_distribuicao->codigoLogradouro($hash_logradouro{ $DNE_ID_logradouro }->codigo());
                            $centro_distribuicao->dataAtualizacao( $today );
                            $centro_distribuicao->usuarioAtualizacao( $importUser );

                    } else {
                        $dontUpdate = 1;
                    }
                    if( 1 == $noUpdates
                            && '' ne $reportFile
                            && ( $centro_distribuicao->nome() ne $nome_cdd
                            || $centro_distribuicao->descricao() ne $nome_cdd ) ) {
                        printf REPORT qq/CENTRO_DISTRIBUICAO: record "$nome_cdd" already exists on database and it is different from the flat file:%s\n\n/
                                , $centro_distribuicao->to_string();
                    }



#   store a map between database ID_cdd and DNE_ID_cdd
                    $hash_centro_distribuicao{ $DNE_ID_cdd } = $centro_distribuicao;
                    if( ! exists $hash_centro_distribuicaoUF{ $hash_unidade_federacao{ $siglaUF }->codigo() } ) {
                        $hash_centro_distribuicaoUF{ $hash_unidade_federacao{ $siglaUF }->codigo() } = $centro_distribuicao;
                    }
                    if( ! exists $hash_centro_distribuicaoLocalidade{ $DNE_ID_localidade } ) {
                        $hash_centro_distribuicaoLocalidade{ $DNE_ID_localidade } = $centro_distribuicao;
                            }

                    if( 0 == $dontUpdate
                           && 0 == $noUpdates ) {
                        $returnCode = $centro_distribuicao->persist( $connection );

                        if( SUCCESS != $returnCode) {
                            $dbErrors++;
                        } else {
                            $commitCount++;
                        }
                    }
                    else{
                        next;
                    }
                }

            $ID_cdd = '';
            $DNE_ID_cdd = '';

            $numImportedLines++;

#   Finished a database transaction, make rollback or commit per line

            if( $commitCount > 10000) {
                $connection->commit();
                $commitCount = 0;

            }

            next;
        }


#   trailing line

        if( $_ =~ /^#/ ) {
            last;
        }

        die qq/$_: unknown record type in line\n/;
    }
    if ( $commitCount != 0 ) {
        $connection->commit();
        $commitCount = 0;
    }

    close CENTRO_DISTRIBUICAO;

    print STDOUT qq/\t$numReadedLines lines readed\/ $numImportedLines lines imported\n/;

    print color('yellow') . "importCentroDistribuicao() out" . color('reset') . "\n" unless !$sysDebug;
}


################################################################################
#   import "DNE_GU_LOCALIDADES.TXT" flat file function
################################################################################

sub importLocalidade
{
    print color('yellow') . "importLocalidade() in" . color('reset') . "\n" unless !$sysDebug;

    my  $flatFileName = $_[ 0 ];

    print STDOUT qq/importing "$flatFileName" DNE flat file ...\n/;

    my  $siglaPais;
    my  $siglaUF;
    my  $ID_localidade;
    my  $DNE_ID_localidade;
    my  $nome_localidade;
    my  $numeroCEP;
    my  $abreviatura_localidade;
    my  $tipoLocalidade;
    my  $situacaoLocalidade;
    my  $ID_subordinacao;
    my  $DNE_ID_subordinacao;
    my  $sigla_CNL_localidade;
    my  $codigo_IBGE;

    my  $localidade;
    my  $numReadedLines = 0;
    my  $numImportedLines = 0;
    my  $numCommittedLines = 0;
    my  $numInsertedLines = 0;
    my  $numUpdatedLines = 0;
    my  $numExistingLines = 0;

    my  $createOrUpdateLocalidade;

    my  $dbErrors = 0;
    my  $returnCode;
    my  $commitCount = 0;

    #
    # The parent localidade might comes after child localidade. For these cases the script keeps
    # the localidade into this hash and try to find its parent after the whole localidade loading
    #
    my  %hashLateLocalidade;

    #
    # open the flat file and import the data
    #
    open LOCALIDADE, "< $flatFileName"
            or die qq/couldn\'t open "$flatFileName" flat file\n/;

    while( <LOCALIDADE> )
    {
        chop;

        $dbErrors = 0;
        $createOrUpdateLocalidade = FALSE;

        $numReadedLines++;
        print STDOUT "\t$numReadedLines\r";

        #
        # header line
        #
        if( 1 == $numReadedLines && $_ =~ /^C/ )
        {
            if( $_ !~ /LOCALIDADES\s*$/ )
            {
                die qq/"$flatFileName" isn\'t the DNE LOCALIDADES flat file\n/;
            }

            next;
        }

        #
        # detail line
        #
        if( 1 < $numReadedLines && $_ =~ /^D/ )
        {
            if( 162 != length ( $_ ) )
            {
                die qq/wrong "DNE_GU_LOCALIDADES" detail record length\n/;
            }

            $siglaPais = stringUtility::trim( substr( $_, 1, 2 ) );
            $siglaUF = stringUtility::trim( substr( $_, 3, 2 ) );
            $ID_localidade = substr( $_, 5, 6 );
            $DNE_ID_localidade = substr( $_, 11, 8 );
            $nome_localidade = stringUtility::trim( substr( $_, 19, 72 ) );
            $numeroCEP = stringUtility::trim( substr( $_, 91, 8 ) );
            $abreviatura_localidade = stringUtility::trim( substr( $_, 99, 36 ) );
            $tipoLocalidade = stringUtility::trim( substr( $_, 135, 1 ) );
            $situacaoLocalidade = stringUtility::trim( substr( $_, 136, 1 ) );
            $ID_subordinacao = stringUtility::trim( substr( $_, 137, 6 ) );
            $DNE_ID_subordinacao = stringUtility::trim( substr( $_, 146, 5 ) ); ##Alterado DE 143, 8
            $sigla_CNL_localidade = stringUtility::trim( substr( $_, 151, 3 ) );
            $codigo_IBGE = stringUtility::trim( substr( $_, 154, 7 ) );

            print color('red') . "DNE_ID_localidade: " . $DNE_ID_localidade . color('reset') . "\n" unless $sysDebug <= 1;


            #
            #   input record validation
            #
            if( $brazil->siglaISO() ne $siglaPais )
            {
                next;
            }

            if( $selectedState ne '' && $siglaUF ne $selectedState )
            {
                next;
            }

            $nome_localidade = &stringUtility::abbreviate( $nome_localidade, \%hash_abrevLocalidade );

            #
            # construct a new localidade object
            #
            $localidade = &localidade::queryByDescricaoUF( $connection
                    , $nome_localidade
                    , $hash_unidade_federacao{ $siglaUF } );

            #
            # If localidade exists on database, update it only if one of the
            # following fields were changed: cod_localidade_princ, num_cod_ibge
            # and/or dsc_abrev_localidade.
            #
            if ( defined $localidade && $localidade != 0 )
            {
                $numExistingLines++;

                #
                # Create an entry on report file
                #
                if( '' ne $reportFile )
                {
                    printf REPORT qq/LOCALIDADE: record "$nome_localidade" - "$siglaUF" already exists on database:%s\n\n/
                            , $localidade->to_string();
                }

                #
                # In case there is a localidade principal then try finding it and set
                #
                if (defined $DNE_ID_subordinacao && '' ne $DNE_ID_subordinacao) {

                    my $localidadePrincipal = $hash_localidade{ $DNE_ID_subordinacao };

                    #
                    # Only update if the value of cod_localidade_princ is changing
                    #
                    if (defined $localidadePrincipal && 0 != $localidadePrincipal)
                    {
                        if ( $localidadePrincipal->{ COD_LOCALIDADE } ne $localidade->{ COD_LOCALIDADE_PRINC } )
                        {
                            $localidade->codigoLocalidadePrincipal( $localidadePrincipal->codigo() );

                            $createOrUpdateLocalidade = TRUE;

                            print LOG_FILE "importLocalidade: $nome_localidade: Updating main localidade \n" if $sysDebug >= 3;
                        }

                    } else {
                        $hashLateLocalidade{ $DNE_ID_localidade }{LOCALIDADE} = $localidade;
                        $hashLateLocalidade{ $DNE_ID_localidade }{DNE_ID_subordinacao} = $DNE_ID_subordinacao;
                    }
                }

                #
                # Only update if the value of NUM_COD_IBGE is changing
                #
                if ( defined $codigo_IBGE
                         && '' ne $codigo_IBGE
                         && $localidade->{ NUM_COD_IBGE } ne $codigo_IBGE )
                {
                    $localidade->codigoIBGE( $codigo_IBGE );

                    $createOrUpdateLocalidade = TRUE;

                    print LOG_FILE "$nome_localidade: Updating ibge code \n" if $sysDebug >= 3;
                }

                if ( $createOrUpdateLocalidade == TRUE )
                {
				    $localidade->dataAtualizacao( $today );
                    $localidade->usuarioAtualizacao( $importUser );

                    $numUpdatedLines++;

                    print LOG_FILE "importLocalidade: Existing localidade updated = $nome_localidade | $siglaUF \n" if $sysDebug >= 3;
                }
                else
                {
                    print LOG_FILE "importLocalidade: Existing localidade not updated = $nome_localidade | $siglaUF \n" if $sysDebug >= 3;
                }
            }
            else
            {
                $createOrUpdateLocalidade = TRUE;

                print LOG_FILE "importLocalidade: New localidade = " . $nome_localidade->to_string() . "|" . $siglaUF->to_string() .  "\n" if $sysDebug >= 3;

                #
                # construct a new localidade object
                #
                $localidade = localidade::new();
                print color('red') . "Localidade does not exists" . color('reset') . "\n" unless $sysDebug <= 1;
                print color('red') . "nome_localidade" . $nome_localidade . color('reset') . "\n" unless $sysDebug <= 1;
                print color('red') . "abreviatura_localidade" . $abreviatura_localidade . color('reset') . "\n" unless $sysDebug <= 1;
                print color('red') . "sigla_CNL_localidade" . $sigla_CNL_localidade . color('reset') . "\n" unless $sysDebug <= 1;

                #
                # TR5461388: Cannot update the fields regarding CNL
                #
                $localidade->codigoNacionalLocalidade( $DNE_ID_localidade );

                if( '' ne $sigla_CNL_localidade )
                {
                    $localidade->siglaNacionalLocalidade( $sigla_CNL_localidade );
                }
                elsif( '' ne $abreviatura_localidade )
                {
                    $localidade->siglaNacionalLocalidade( $abreviatura_localidade );
                }
                else
                {
                    $localidade->siglaNacionalLocalidade( $DNE_ID_localidade );
                }

                #
                # The query filter used to search for existing localidade will not
                # be changed, so we will set them just when inserting
                #
                $localidade->descricao( $nome_localidade );
                $localidade->UF( $hash_unidade_federacao{ $siglaUF }->codigo() );

                $localidade->codigoAreaTarifaria( 1 );

                if ( defined $abreviatura_localidade && '' ne $abreviatura_localidade )
                {
                    $localidade->descricaoAbreviada( $abreviatura_localidade );
                }
                else
                {
                    $localidade->descricaoAbreviada( $nome_localidade );
                }

                #In case there is a localidade principal then try finding it and set
                if (defined $DNE_ID_subordinacao && '' ne $DNE_ID_subordinacao) {

                    my $localidadePrincipal = $hash_localidade{ $DNE_ID_subordinacao };
                    if (defined $localidadePrincipal && 0 != $localidadePrincipal) {

                        $localidade->codigoLocalidadePrincipal( $localidadePrincipal->codigo() );

                    } else {
                        $hashLateLocalidade{ $DNE_ID_localidade }{LOCALIDADE} = $localidade;
                        $hashLateLocalidade{ $DNE_ID_localidade }{DNE_ID_subordinacao} = $DNE_ID_subordinacao;
                    }
                }

                if ( defined $codigo_IBGE && '' ne $codigo_IBGE )
                {
                    $localidade->codigoIBGE( $codigo_IBGE );
                }

                $localidade->dataAtualizacao( $today );
                $localidade->usuarioAtualizacao( $importUser );

                if ( $createOrUpdateLocalidade == TRUE )
                {
                    $numInsertedLines++;
                }
            }

            #
            # store a map between database ID_localidade and DNE_ID_localidade
            #
            print color('red') . "createOrUpdateLocalidade:" . $createOrUpdateLocalidade . color('reset') . "\n" unless $sysDebug <= 1;
            print color('red') . "noUpdates:" . $noUpdates . color('reset') . "\n" unless $sysDebug <= 1;
            $hash_localidade{ $DNE_ID_localidade } = $localidade;

            if( $createOrUpdateLocalidade == TRUE && $noUpdates == 0 )
            {
                $returnCode = $localidade->persist( $connection );

                if( SUCCESS != $returnCode)
                {
                    $dbErrors++;
                }
                else
                {
                    $commitCount++;
                    $numCommittedLines++;
                }
            }
            else
            {
                print color('red') . "Next!!" . color('reset') . "\n" unless $sysDebug <= 1;
                next;
            }

            print color('red') . "hash_localidade{" . $DNE_ID_localidade . "}: " . $localidade . color('reset') . "\n" unless $sysDebug <= 1;

            #
            # If necessary, construct a new CEP object
            #
            if( '' ne $numeroCEP )
            {
                &importCEP( $numeroCEP, $DNE_ID_localidade );
            }

            $numImportedLines++;

            #
            # Finished a database transaction, make rollback or commit per line
            #
            if ( $commitCount >= 10000 )
            {
                if ( $noUpdates == 0 )
                {
                    $connection->commit();
                }
                else
                {
                    $connection->rollback();
                }

                $commitCount = 0;
            }

            next;
        }

        #
        # Trailing line
        #
        if( $_ =~ /^#/ )
        {
            last;
        }

        die qq/$_: unknown record type in line\n/;
    }

    if ( $commitCount != 0 )
    {
        if ( $noUpdates == 0 )
        {
            $connection->commit();
        }
        else
        {
            $connection->rollback();
        }

        $commitCount = 0;
    }

    close LOCALIDADE;

    my $localidade_principal;

    #
    # Try to update cod_localidade_princ for late localidade
    #
    foreach ( values(%hashLateLocalidade) )
    {
        $localidade = $$_{LOCALIDADE};
        $localidade_principal = $hash_localidade{ $$_{DNE_ID_subordinacao} };

        if (defined $localidade_principal && 0 != $localidade_principal)
        {
            $localidade->codigoLocalidadePrincipal( $localidade_principal->codigo() );
            $returnCode = $localidade->persist( $connection );

            if( SUCCESS == $returnCode && $noUpdates == 0 )
            {
                $connection->commit();
            }
            else
            {
                $connection->rollback();
            }
        }
    }

    print STDOUT qq/\t$numReadedLines lines readed\/ $numImportedLines lines imported\/ $numCommittedLines lines committed\/ $numUpdatedLines updated cities\/ $numInsertedLines inserted cities\/ $numExistingLines existing cities\n/;

    print color('yellow') . "importLocalidade() out" . color('reset') . "\n" unless !$sysDebug;
}

################################################################################
#   import "DNE_GU_BAIRROS.TXT" flat file function
################################################################################

sub importBairro {
    print color('yellow') . "importBairro() in" . color('reset') . "\n" unless !$sysDebug;

    my  $flatFileName = $_[ 0 ];

    print STDOUT qq/importing "$flatFileName" DNE flat file ...\n/;

    my  $siglaUF;
    my  $ID_localidade;
    my  $DNE_ID_localidade;
    my  $nome_localidade;
    my  $ID_bairro;
    my  $DNE_ID_bairro;
    my  $nome_bairro;
    my  $abreviatura_bairro;

    my  $bairro;
    my  $exists;
    my  $dontUpdate;
    my  $numReadedLines = 0;
    my  $numImportedLines = 0;

    my  $dbErrors = 0;
    my  $returnCode;
    my  $commitCount = 0;

#   open the flat file and import the data

    open BAIRRO, "< $flatFileName"
            or die qq/couldn\'t open "$flatFileName" flat file\n/;

    while( <BAIRRO> ) {
        chop;

        $dbErrors = 0;
        $dontUpdate = 0;

        $numReadedLines++;
        print STDOUT "\t$numReadedLines\r";

#   header line

        if( 1 == $numReadedLines
                && $_ =~ /^C/ ) {
            if( $_ !~ /BAIRROS\s*$/ ) {
                die qq/"$flatFileName" isn\'t the DNE BAIRROS flat file\n/;
            }

            next;
        }

#   detail line

        if( 1 < $numReadedLines
                && $_ =~ /^D/ ) {
            if( 211 != length ( $_ ) ) {
                die qq/wrong "DNE_GU_BAIRROS.TXT" detail record length\n/;
            }

            $siglaUF = stringUtility::trim( substr( $_, 1, 2 ) );
            $ID_localidade = stringUtility::trim( substr( $_, 3, 6 ) );
            $DNE_ID_localidade = stringUtility::trim( substr( $_, 9, 8 ) );
            $nome_localidade = stringUtility::trim( substr( $_, 17, 72 ) );
            $ID_bairro = substr( $_, 89, 5 );
            $DNE_ID_bairro = substr( $_, 94, 8 );
            $nome_bairro = stringUtility::trim( substr( $_, 102, 72 ) );
            $abreviatura_bairro = stringUtility::trim( substr( $_, 174, 36 ) );

#   input record validation

            if( $selectedState ne ''
                    && $siglaUF ne $selectedState ) {
                next;
            }

#   construct a new bairro object

            $bairro = bairro::queryByNome( $connection
                    , $nome_bairro );

            if( ! defined $bairro
                    || 0 == $bairro ) {
                $bairro = bairro::new();
                $exists = 0;
            } else {
                $exists = 1;

                if( $bairro->nome() eq $nome_bairro ) {
                    $dontUpdate = 1;

                }
                if( 1 == $noUpdates
                        && '' ne $reportFile ) {
                    printf REPORT qq/BAIRRO: record "$nome_bairro" already exists on database:%s\n\n/
                            , $bairro->to_string();
                }
            }

            $bairro->nome( $nome_bairro );
            if( '' eq $abreviatura_bairro ) {
                $bairro->nomeAbreviado( &stringUtility::abbreviate( $nome_bairro, \%hash_abrevBairro ) );
            } else {
                $bairro->nomeAbreviado( $abreviatura_bairro );
            }
            $bairro->dataAtualizacao( $today );
            $bairro->usuarioAtualizacao( $importUser );

#   store a map between database ID_bairro and DNE_ID_bairro
            $hash_bairro{ $DNE_ID_bairro } = $bairro;

            if( ( 0 == $exists
               ||  0 == $dontUpdate )
                   && 0 == $noUpdates  ) {

                $returnCode = $bairro->persist( $connection );

                if( SUCCESS != $returnCode) {
                    $dbErrors++;
                } else {
                    $commitCount++;
                }
            } else {
                next;
            }

            $numImportedLines++;

#   Finished a database transaction, make rollback or commit per line

            if(# 0 == $dbErrors &&
               $commitCount >= 10000) {
                $connection->commit();
                $commitCount = 0;
#            } else {
#                $connection->rollback();
            }

            next;
        }

#   trailing line

        if( $_ =~ /^#/ ) {
            last;
        }

        die qq/$_: unknown record type in line\n/;
    }
    if ( $commitCount != 0) {
        $connection->commit();
    }

    close BAIRRO;

    print STDOUT qq/\t$numReadedLines lines readed\/ $numImportedLines lines imported\n/;
    print color('yellow') . "importBairro() out" . color('reset') . "\n" unless !$sysDebug;
}

################################################################################
#   import CEP
################################################################################

sub importCEP {
    print color('yellow') . "importCEP() in" . color('reset') . "\n" unless !$sysDebug;

    my  $numeroCEP = $_[ 0 ];
    my  $DNE_ID_localidade = $_[ 1 ];

    my  $CEP;
    my  $exists;
    my  $dontUpdate = 0;
    my  $returnCode;

#   construct a new CEP object

    $CEP = CEP::queryByNumero( $connection
            , $numeroCEP );

    if( ! defined $CEP
            || 0 == $CEP ) {
        $CEP = CEP::new();
        $exists = 0;
    } else {
        $exists = 1;

        if(  $CEP->numero() eq $numeroCEP ) {
            $dontUpdate = 1;
        }
        if( 1 == $noUpdates
                && '' ne $reportFile
                && ( $CEP->numero() != $numeroCEP
                || $CEP->codigoLocalidade() != $hash_localidade{ $DNE_ID_localidade }->codigo() ) ) {
            printf REPORT qq/CEP: record $numeroCEP already exists on database:%s\n\n/
                    , $CEP->to_string();
        }
    }

    $CEP->numero( $numeroCEP );

    my $log1;
    my $log2;

    if( ! exists $hash_centro_distribuicaoLocalidade{ $DNE_ID_localidade }
        || '' eq $hash_centro_distribuicaoLocalidade{ $DNE_ID_localidade }->codigo() ) {
        $CEP->codigoCentroDistribuicao( $hash_centro_distribuicaoUF{ $hash_localidade{ $DNE_ID_localidade }->UF() }->codigo() );
    } else {
        $CEP->codigoCentroDistribuicao( $hash_centro_distribuicaoLocalidade{ $DNE_ID_localidade }->codigo() );
    }
    $CEP->codigoLocalidade( $hash_localidade{ $DNE_ID_localidade }->codigo() );
    $CEP->dataAtualizacao( $today );
    $CEP->usuarioAtualizacao( $importUser );

#   store a map between database ID and DNE ID

    if( ( 0 == $exists
       ||  0 == $dontUpdate )
           && 0 == $noUpdates  ) {
        $returnCode = $CEP->persist( $connection );

    }

    $hash_CEP{ $numeroCEP } = $CEP;

    print color('yellow') . "importCEP() out" . color('reset') . "\n" unless !$sysDebug;
}

################################################################################
#   import "DNE_GU_TIPOS_LOGRADOURO.TXT" flat file function
################################################################################

sub importTipo_logradouro {
    print color('yellow') . "importTipo_logradouro() in" . color('reset') . "\n" unless !$sysDebug;

    my  $flatFileName = $_[ 0 ];

    print STDOUT qq/importing "$flatFileName" DNE flat file ...\n/;

    my  $ID_tipo_logradouro;
    my  $DNE_ID_tipo_logradouro;
    my  $nome_tipo_logradouro;
    my  $abreviatura_tipo_logradouro;

    my  $tipo_logradouro;
    my  $exists;
    my  $dontUpdate;
    my  $numReadedLines = 0;
    my  $numImportedLines = 0;

    my  $dbErrors = 0;
    my  $returnCode;
    my  $commitCount = 0;

#   open the flat file and import the data

    open TIPO_LOGRADOURO, "< $flatFileName"
            or die qq/couldn\'t open "$flatFileName" flat file\n/;

    while( <TIPO_LOGRADOURO> ) {
        chop;

        $dbErrors = 0;
        $dontUpdate = 0;
        $numReadedLines++;
        print STDOUT "\t$numReadedLines\r";

#   header line

        if( 1 == $numReadedLines
                && $_ =~ /^C/ ) {
            if( $_ !~ /TIPOS DE LOGRADOURO\s*$/ ) {
                die qq/"$flatFileName" isn\'t the DNE TIPOS DE LOGRADOURO flat file\n/;
            }

            next;
        }

#   detail line

        if( 1 < $numReadedLines
                && $_ =~ /^D/ ) {
            if( 95 != length ( $_ ) ) {
                die qq/wrong "DNE_GU_TIPOS_LOGRADOURO.TXT" detail record length\n/;
            }

            $ID_tipo_logradouro = substr( $_, 1, 3 );
            $DNE_ID_tipo_logradouro = substr( $_, 4, 3 );
            $nome_tipo_logradouro = stringUtility::trim( substr( $_, 7, 72 ) );
            $abreviatura_tipo_logradouro = stringUtility::trim( substr( $_, 79, 15 ) );

#   construct a new tipo_logradouro object

            if( '' eq $abreviatura_tipo_logradouro ) {
                $tipo_logradouro = tipo_logradouro::queryByAbreviatura( $connection
                        , substr( $nome_tipo_logradouro, 0, 7 ) );
            } else {
                $tipo_logradouro = tipo_logradouro::queryByAbreviatura( $connection
                        , substr( $abreviatura_tipo_logradouro, 0, 7 ) );
            }

            if( ! defined $tipo_logradouro
                    || 0 == $tipo_logradouro ) {
                $tipo_logradouro = tipo_logradouro::new();
                $exists = 0;
            } else {
                $exists = 1;

                if( $tipo_logradouro->descricao() eq $nome_tipo_logradouro ) {
                    $dontUpdate = 1;
                }
                if( 1 == $noUpdates
                        && '' ne $reportFile
                        && $tipo_logradouro->descricao() ne $nome_tipo_logradouro ) {
                    printf REPORT qq/TIPO_LOGRADOURO: record "$nome_tipo_logradouro" already exists on database:%s\n\n/
                            , $tipo_logradouro->to_string();
                }

            }

            $tipo_logradouro->descricao( $nome_tipo_logradouro );
            if( '' eq $abreviatura_tipo_logradouro ) {
                $tipo_logradouro->abreviatura( $nome_tipo_logradouro );
            } else {
                $tipo_logradouro->abreviatura( $abreviatura_tipo_logradouro );
            }
            $tipo_logradouro->dataAtualizacao( $today );
            $tipo_logradouro->usuarioAtualizacao( $importUser );

#   store a map of nomes and abreviacoes

            $hash_tipo_logradouro{ $tipo_logradouro->descricao() } = $tipo_logradouro;

            if( ( 0 == $exists
               ||  0 == $dontUpdate )
                   && 0 == $noUpdates  ) {

                $returnCode = $tipo_logradouro->persist( $connection );

                if( SUCCESS != $returnCode) {
                    $dbErrors++;
                } else {
                    $commitCount++;
                }
            } else {
                next;
            }



            $numImportedLines++;
#   Finished a database transaction, make rollback or commit per line

            if(# 0 == $dbErrors &&
               $commitCount >= 10000) {
                $connection->commit();
                $commitCount = 0;
#            } else {
#                $connection->rollback();
            }

            next;
        }

#   trailing line

        if( $_ =~ /^#/ ) {
            last;
        }

        die qq/$_: unknown record type in line\n/;
    }
    if ( $commitCount != 0) {
        $connection->commit();
    }

    close TIPO_LOGRADOURO;

    print STDOUT qq/\t$numReadedLines lines readed\/ $numImportedLines lines imported\n/;
    print color('yellow') . "importTipo_logradouro() out" . color('reset') . "\n" unless !$sysDebug;
}

################################################################################
#   import "DNE_GU_TITULOS_PATENTES.TXT" flat file function
################################################################################

sub importTitulo_logradouro {
    print color('yellow') . "importTitulo_logradouro() in" . color('reset') . "\n" unless !$sysDebug;

    my  $flatFileName = $_[ 0 ];

    print STDOUT qq/importing "$flatFileName" DNE flat file ...\n/;

    my  $ID_titulo_logradouro;
    my  $DNE_ID_titulo_logradouro;
    my  $nome_titulo_logradouro;
    my  $abreviatura_titulo_logradouro;

    my  $titulo_logradouro;
    my  $exists;
    my  $dontUpdate;
    my  $numReadedLines = 0;
    my  $numImportedLines = 0;

    my  $dbErrors = 0;
    my  $returnCode;
    my  $commitCount = 0;

#   open the flat file and import the data

    open TITULO_LOGRADOURO, "< $flatFileName"
            or die qq/couldn\'t open "$flatFileName" flat file\n/;

    while( <TITULO_LOGRADOURO> ) {
        chop;

        $dbErrors = 0;
        $dontUpdate = 0;
        $numReadedLines++;
        print STDOUT "\t$numReadedLines\r";

#   header line

        if( 1 == $numReadedLines
                && $_ =~ /^C/ ) {
            if( $_ !~ /TITULOS E PATENTES\s*$/ ) {
                die qq/"$flatFileName" isn\'t the DNE TITULOS E PATENTES flat file\n/;
            }

            next;
        }

#   detail line

        if( 1 < $numReadedLines
                && $_ =~ /^D/ ) {
            if( 96 != length ( $_ ) ) {
                die qq/wrong "DNE_GU_TITULOS_PATENTES.TXT" detail record length\n/;
            }

            $ID_titulo_logradouro = substr( $_, 1, 3 );
            $DNE_ID_titulo_logradouro = substr( $_, 4, 4 );
            $nome_titulo_logradouro = stringUtility::trim( substr( $_, 8, 72 ) );
            $abreviatura_titulo_logradouro = stringUtility::trim( substr( $_, 80, 15 ) );

#   construct a new titulo_logradouro object

            if( '' eq $abreviatura_titulo_logradouro ) {
                $titulo_logradouro = titulo_logradouro::queryByAbreviatura( $connection
                        , substr( $nome_titulo_logradouro, 0, 7 ) );
            } else {
                $titulo_logradouro = titulo_logradouro::queryByAbreviatura( $connection
                        , substr( $abreviatura_titulo_logradouro, 0, 7 ) );
            }

            if( ! defined $titulo_logradouro
                    || 0 == $titulo_logradouro ) {
                $titulo_logradouro = titulo_logradouro::new();
                $exists = 0;
            } else {
                $exists = 1;
                if( $titulo_logradouro->descricao() eq $nome_titulo_logradouro ) {
                    $dontUpdate = 1;
                }
                if( 1 == $noUpdates
                        && '' ne $reportFile
                        && $titulo_logradouro->descricao() ne $nome_titulo_logradouro ) {
                    printf REPORT qq/TITULO_LOGRADOURO: record "$nome_titulo_logradouro" already exists on database:%s\n\n/
                            , $titulo_logradouro->to_string();
                }
            }

            $titulo_logradouro->descricao( $nome_titulo_logradouro );
            if( '' eq $abreviatura_titulo_logradouro ) {
                $titulo_logradouro->abreviatura( $nome_titulo_logradouro );
            } else {
                $titulo_logradouro->abreviatura( $abreviatura_titulo_logradouro );
            }
            $titulo_logradouro->dataAtualizacao( $today );
            $titulo_logradouro->usuarioAtualizacao( $importUser );

#   store a map of nomes and abreviacoes

            $hash_titulo_logradouro{ $titulo_logradouro->descricao() } = $titulo_logradouro;

            if( ( 0 == $exists
               ||  0 == $dontUpdate )
                   && 0 == $noUpdates  ) {

                $returnCode = $titulo_logradouro->persist( $connection );

                if( SUCCESS != $returnCode) {
                    $dbErrors++;
                } else {
                    $commitCount++;
                }
            } else {
                next;
            }

            $numImportedLines++;

#   Finished a database transaction, make rollback or commit per line

            if(# 0 == $dbErrors &&
               $commitCount >= 10000) {
                $connection->commit();
                $commitCount = 0;
#            } else {
#                $connection->rollback();
            }

            next;
        }

#   trailing line

        if( $_ =~ /^#/ ) {
            last;
        }

        die qq/$_: unknown record type in line\n/;
    }

    if ( $commitCount != 0) {
        $connection->commit();
    }

    close TITULO_LOGRADOURO;

    print STDOUT qq/\t$numReadedLines lines readed\/ $numImportedLines lines imported\n/;

    print color('yellow') . "importTitulo_logradouro() out" . color('reset') . "\n" unless !$sysDebug;
}

################################################################################
#   import "DNE_GU_XX_LOGRADOUROS.TXT" flat file function
################################################################################

sub importLogradouroUF {
    print color('yellow') . "importLogradouroUF() in" . color('reset') . "\n" unless !$sysDebug;

    my  $siglaUF;
    my  $ID_localidade;
    my  $DNE_ID_localidade;
    my  $nome_localidade;
    my  $ID_bairroInicial;
    my  $DNE_ID_bairroInicial;
    my  $nome_bairroInicial;
    my  $ID_bairroFinal;
    my  $DNE_ID_bairroFinal;
    my  $nome_bairroFinal;
    my  $desc_tipo_logradouro;
    my  $preposicao;
    my  $desc_titulo_logradouro;
    my  $ID_logradouro;
    my  $DNE_ID_logradouro;
    my  $nome_logradouro;
    my  $abreviatura_logradouro;
    my  $adicional;
    my  $numeroCEP;
    my  $grande_usuario;
    my  $numeroInicial;
    my  $numeroFinal;
    my  $paridade;
    my  $DNE_ID_seccionamento;
    my  $loteNumber;
    my  $complimentName;
    my  $complimentNumber;

    my  $localidade;
    my  $tipo_logradouro;
    my  $titulo_logradouro;
    my  $logradouro;
    my  $rel_cep_logradouro_bairro;

    my  $dbErrors = 0;
    my  $returnCode;
    my  $commitCount = 0;
    my  $linesCommitted = 0;
    my  $exstngStreetCount = 0;

#   for each unidade_federacao

    my  $UF;

    foreach $UF ( keys %hash_unidade_federacao )
    {

        if( '' ne $selectedState
                && $UF ne $selectedState ) {
            next;
        }

        my  $exists;
        my  $dontUpdate;
        my  $numReadedLines = 0;
        my  $numImportedLines = 0;

        #
        # open the flat file and import the data
        #
        my  $flatFileName;

        $flatFileName = sprintf '%s/DNE_GU_%s_LOGRADOUROS.TXT', $inputPath, $UF;

        open LOGRADOURO_UF, "< $flatFileName"
                or next;

        print STDOUT qq/importing "$flatFileName" flat file ...\n/;

        #
        # read each record in the flat file
        #
        while( <LOGRADOURO_UF> )
        {
            chop;

            $dbErrors = 0;
            $dontUpdate = 0;
            $numReadedLines++;
            print STDOUT "\t$numReadedLines\r";

            #
            #   header line
            #
            if( 1 == $numReadedLines
                    && $_ =~ /^C/ ) {
                if( $_ !~ /LOGRADOUROS\s*$/ ) {
                    die qq/"$flatFileName" isn\'t the DNE LOGRADOUROS flat file\n/;
                }
                next;
            }

            #
            #   trailing line
            #
            if( $_ =~ /^#/ ) {
                last;
            }

            print LOG_FILE "======================================================================= \n";
            print LOG_FILE "importLogradouroUF: Record # $numReadedLines \n";

            #
            #   detail line
            #   D = Dados do logradouro 1
            #
            if( 1 < $numReadedLines
                    && $_ =~ /^D/ )
            {
                if( 632 != length ( $_ ) ) {
                    die qq/wrong "DNE_GU_XX_LOGRADOUROS.TXT" detail record length\n/;
                }

                $siglaUF = stringUtility::trim( substr( $_, 1, 2 ) );
                $ID_localidade = stringUtility::trim( substr( $_, 3, 6 ) );
                $DNE_ID_localidade = stringUtility::trim( substr( $_, 9, 8 ) );
                $nome_localidade = stringUtility::trim( substr( $_, 17, 72 ) );
                $ID_bairroInicial = substr( $_, 89, 5 );
                $DNE_ID_bairroInicial = substr( $_, 94, 8 );
                $nome_bairroInicial = stringUtility::trim( substr( $_, 102, 72 ) );
                $ID_bairroFinal = substr( $_, 174, 5 );
                $DNE_ID_bairroFinal = substr( $_, 179, 8 );
                $nome_bairroFinal = stringUtility::trim( substr( $_, 187, 72 ) );
                $desc_tipo_logradouro = stringUtility::trim( substr( $_, 259, 26 ) );
                $preposicao = stringUtility::trim( substr( $_, 285, 3 ) );
                $desc_titulo_logradouro = stringUtility::trim( substr( $_, 288, 72 ) );
                $ID_logradouro = substr( $_, 360, 6 );
                $DNE_ID_logradouro = substr( $_, 366, 8 );
                $nome_logradouro = stringUtility::trim( substr( $_, 374, 72 ) );
                $abreviatura_logradouro = stringUtility::trim( substr( $_, 446, 36 ) );
                $adicional = stringUtility::trim( substr( $_, 482, 36 ) );
                $numeroCEP = stringUtility::trim( substr( $_, 518, 8 ) );
                $grande_usuario = stringUtility::trim( substr( $_, 526, 1 ) );

                #
                #   input record validation
                #
                if( $selectedState ne ''
                        && $siglaUF ne $selectedState ) {
                    next;
                }

                #
                #   record adjustements
                #
                if( '' ne $preposicao ) {
                    $nome_logradouro = qq/$preposicao $nome_logradouro/;
                }

                #
                #   construct the related objects
                #
                if( ! exists $hash_localidade{ $DNE_ID_localidade } ) {
                    print LOG_FILE "importLogradouroUF: Localidade not found to DNE_ID_localidade = $DNE_ID_localidade \n";
                    $localidade = undef;
                    next; #read next record if localidade is not found
                } else {
                    $localidade = $hash_localidade{ $DNE_ID_localidade };
                }

                if( ! exists $hash_tipo_logradouro{ $desc_tipo_logradouro } ) {
                    $tipo_logradouro = undef;
                    print LOG_FILE "importLogradouroUF: tipo_logradouro = NULL \n" if $sysDebug >= 3;
                } else {
                    $tipo_logradouro = $hash_tipo_logradouro{ $desc_tipo_logradouro };
                    print LOG_FILE "importLogradouroUF: tipo_logradouro = " . $tipo_logradouro->descricao() . "\n" if $sysDebug >= 3;
                }

                if( ! exists $hash_titulo_logradouro{ $desc_titulo_logradouro } ) {
                    print LOG_FILE "importLogradouroUF: titulo_logradouro = NULL \n" if $sysDebug >= 3;
                    $titulo_logradouro = undef;
                } else {
                    $titulo_logradouro = $hash_titulo_logradouro{ $desc_titulo_logradouro };
                    print LOG_FILE "importLogradouroUF: titulo_logradouro = " . $titulo_logradouro->descricao() . "\n" if $sysDebug >= 3;
                }

                #
                #   Checks if logradouro already exists in DB
                #
                $logradouro = logradouro::queryByLocalidadeNome( $connection
                        , $localidade
                        , $tipo_logradouro
                        , $titulo_logradouro
                        , substr( $nome_logradouro, 0, 60 ) );

                #
                #   if logradouro already exists, only report it if necessary
                #
                if( defined $logradouro
                        && 0 != $logradouro )
                {
                    $exstngStreetCount++;

                    print LOG_FILE "importLogradouroUF: Existing logradouro = " . $logradouro->to_string(). "\n" if $sysDebug >= 3;

                    if( '' ne $reportFile )
                    {
                        printf REPORT qq/LOGRADOURO: record "$nome_logradouro" already exists on database:%s\n\n/
                            , $logradouro->to_string();
                    }
                }
                #
                #   if logradouro do not exists, construct a new logradouro object
                #
                else
                {
                    $numImportedLines++;

                    $logradouro = logradouro::new();

                    #   Sets COD_TIPO_LOGRAD field
                    if( defined $tipo_logradouro ) {
                        $logradouro->codigoTipo( $tipo_logradouro->codigo() );
                    } else {
                        $logradouro->codigoTipo( undef );
                    }

                    #   Sets COD_TITULO_LOGRAD field
                    if( defined $titulo_logradouro ) {
                        $logradouro->codigoTitulo( $titulo_logradouro->codigo() );
                    } else {
                        $logradouro->codigoTitulo( undef );
                    }

                    #   Sets COD_LOCALIDADE field
                    $logradouro->codigoLocalidade( $localidade->codigo() );
                    $logradouro->nome( $nome_logradouro );

                    #   Sets NOM_ABREV_LOGRADOURO field
                    if (defined $abreviatura_logradouro and '' ne $abreviatura_logradouro) {
                        $logradouro->nomeAbreviado( &stringUtility::abbreviate( $abreviatura_logradouro, \%hash_abrevLogradouro ) );
                    } else {
                        $logradouro->nomeAbreviado( &stringUtility::abbreviate( $nome_logradouro, \%hash_abrevLogradouro ) );
                    }

                    #   Sets remaining field
                    $logradouro->indNumeracao( 2 );
                    $logradouro->flagComplemento( 0 );
                    $logradouro->dataAtualizacao( $today );
                    $logradouro->usuarioAtualizacao( $importUser );

                    print LOG_FILE "importLogradouroUF: New logradouro = " . $logradouro->to_string(). "\n" if $sysDebug >= 3;

                    #   store a map between database ID and DNE ID
                    $hash_logradouro{ $DNE_ID_logradouro } = $logradouro;

                    if ( 1 == $noUpdates )
                    {
                        print LOG_FILE "importLogradouroUF: Logradouro NOT commited \n";
                        next; # we must not proceed if the logradouro is not inserted in DB
                    }
                    else
                    {
                        $returnCode = $logradouro->persist( $connection );

                        if( SUCCESS != $returnCode) {
                            $dbErrors++;
                        } else {
                            $commitCount++;
                            $linesCommitted++;
                            print LOG_FILE "importLogradouroUF: Logradouro Created \n";
                        }
                    }
                }

                #
                #   if necessary, construct a new CEP and rel_cep_logradouro_bairro object
                #
                if( '' ne $numeroCEP )
                {
                    &importCEP( $numeroCEP, $DNE_ID_localidade );

                    if( exists $hash_CEP{ $numeroCEP }
                            && exists $hash_bairro{ $DNE_ID_bairroInicial } )
                    {
                        #
                        # Checks if there is an address to logradouro, cep and bairro inicial
                        #
                        $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::queryByCodigo( $connection
                                , $logradouro->codigo()
                                , $hash_CEP{ $numeroCEP }->codigo()
                                , 1
                                , $hash_bairro{ $DNE_ID_bairroInicial }->codigo() );

                        #
                        # If address does not exist, create new one
                        #
                        if( ! defined $rel_cep_logradouro_bairro
                            || 0 == $rel_cep_logradouro_bairro )
                        {
                            printf LOG_FILE qq/importLogradouroUF: No address found to "cod_logradouro = %d, cep_cod= %d, cod_bairro = %d \n/
                                    , $logradouro->codigo()
                                    , $hash_CEP{ $numeroCEP }->codigo()
                                    , $hash_bairro{ $DNE_ID_bairroInicial }->codigo() if $sysDebug >=3;

                            $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::new();

                            $rel_cep_logradouro_bairro->codigoLogradouro( $logradouro->codigo() );
                            $rel_cep_logradouro_bairro->codigoCEP( $hash_CEP{ $numeroCEP }->codigo() );
                            $rel_cep_logradouro_bairro->numeroInicial( 1 );
                            $rel_cep_logradouro_bairro->codigoBairro( $hash_bairro{ $DNE_ID_bairroInicial }->codigo() );
                            $rel_cep_logradouro_bairro->numeroFinal( 99999 );
                            $rel_cep_logradouro_bairro->indLadoNumeracao( 0 );
                            $rel_cep_logradouro_bairro->dataAtualizacao( $today );
                            $rel_cep_logradouro_bairro->usuarioAtualizacao( $importUser );

                            if ( 0 == $noUpdates )
                            {
                                $returnCode = $rel_cep_logradouro_bairro->persist( $connection );

                                if( SUCCESS != $returnCode) {
                                    $dbErrors++;
                                }
                                else {
                                    printf LOG_FILE qq/importLogradouroUF: REL_CEP_LOGRAD_BAIRRO record "%d - %d - %d - %d" created on database:%s\n\n/
                                            , $logradouro->codigo()
                                            , $hash_CEP{ $numeroCEP }->codigo()
                                            , 1
                                            , $hash_bairro{ $DNE_ID_bairroInicial }->codigo()
                                            , $rel_cep_logradouro_bairro->to_string() if $sysDebug >=3;
                                }
                            }
                        }
                        #
                        # Otherwise, reports it if necessary
                        #
                        else
                        {
                            print LOG_FILE "importLogradouroUF: Existing address = " . $rel_cep_logradouro_bairro->to_string(). "\n" if $sysDebug >= 3;

                            if ('' ne $reportFile)
                            {
                                printf REPORT qq/REL_CEP_LOGRAD_BAIRRO: record "%d - %d - %d - %d" already exists on database:%s\n\n/
                                        , $logradouro->codigo()
                                        , $hash_CEP{ $numeroCEP }->codigo()
                                        , 1
                                        , $hash_bairro{ $DNE_ID_bairroInicial }->codigo()
                                        , $rel_cep_logradouro_bairro->to_string();
                            }
                        }
                    }

                    if( exists $hash_CEP{ $numeroCEP }
                            && $DNE_ID_bairroFinal !~ /^0+$/
                            && exists $hash_bairro{ $DNE_ID_bairroFinal } )
                    {
                        #
                        # Checks if there is an address to logradouro, cep and bairro final
                        #
                        $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::queryByCodigo( $connection
                                , $logradouro->codigo()
                                , $hash_CEP{ $numeroCEP }->codigo()
                                , 1
                                , $hash_bairro{ $DNE_ID_bairroFinal }->codigo() );

                        #
                        # If address does not exist, create new one
                        #
                        if( ! defined $rel_cep_logradouro_bairro
                            || 0 == $rel_cep_logradouro_bairro )
                        {
                            printf LOG_FILE qq/importLogradouroUF: No address found to "cod_logradouro = %d, cep_cod= %d, cod_bairro = %d \n/
                                    , $logradouro->codigo()
                                    , $hash_CEP{ $numeroCEP }->codigo()
                                    , $hash_bairro{ $DNE_ID_bairroFinal }->codigo() if $sysDebug >=3;

                            $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::new();

                            $rel_cep_logradouro_bairro->codigoLogradouro( $logradouro->codigo() );
                            $rel_cep_logradouro_bairro->codigoCEP( $hash_CEP{ $numeroCEP }->codigo() );
                            $rel_cep_logradouro_bairro->numeroInicial( 1 );
                            $rel_cep_logradouro_bairro->codigoBairro( $hash_bairro{ $DNE_ID_bairroFinal }->codigo() );
                            $rel_cep_logradouro_bairro->numeroFinal( 99999 );
                            $rel_cep_logradouro_bairro->indLadoNumeracao( 0 );
                            $rel_cep_logradouro_bairro->dataAtualizacao( $today );
                            $rel_cep_logradouro_bairro->usuarioAtualizacao( $importUser );

                            if ( 0 == $noUpdates )
                            {
                                $returnCode = $rel_cep_logradouro_bairro->persist( $connection );

                                if( SUCCESS != $returnCode) {
                                    $dbErrors++;
                                }
                                else {
                                    printf LOG_FILE qq/importLogradouroUF: REL_CEP_LOGRAD_BAIRRO record "%d - %d - %d - %d" created on database:%s\n\n/
                                            , $logradouro->codigo()
                                            , $hash_CEP{ $numeroCEP }->codigo()
                                            , 1
                                            , $hash_bairro{ $DNE_ID_bairroFinal }->codigo()
                                            , $rel_cep_logradouro_bairro->to_string() if $sysDebug >=3;
                                }
                            }
                        }
                        #
                        # Otherwise, reports it if necessary
                        #
                        else
                        {
                            print LOG_FILE "importLogradouroUF: Existing address = " . $rel_cep_logradouro_bairro->to_string(). "\n" if $sysDebug >= 3;

                            if ('' ne $reportFile)
                            {
                                printf REPORT qq/REL_CEP_LOGRAD_BAIRRO: record "%d - %d - %d - %d" already exists on database:%s\n\n/
                                        , $logradouro->codigo()
                                        , $hash_CEP{ $numeroCEP }->codigo()
                                        , 1
                                        , $hash_bairro{ $DNE_ID_bairroFinal }->codigo()
                                        , $rel_cep_logradouro_bairro->to_string();
                            }
                        }
                    }
                }

                #
                # Finished a database transaction, make rollback or commit
                #
                if($commitCount >= 10000)
                {
                    if (0 == $noUpdates) {
                        $connection->commit();
                    } else {
                        $connection->rollback();
                    }
                    $commitCount = 0;
                }

                next; # next line
            }

            #
            #   sectioning detail line
            #   S = Seccionamento do Logradouro 1
            #
            if( 1 < $numReadedLines
                    && $_ =~ /^S/ )
            {
                if( 632 != length ( $_ ) ) {
                    die qq/wrong "DNE_GU_XX_LOGRADOUROS.TXT" detail record length\n/;
                }

                $siglaUF = stringUtility::trim( substr( $_, 1, 2 ) );
                $ID_localidade = stringUtility::trim( substr( $_, 3, 6 ) );
                $DNE_ID_localidade = stringUtility::trim( substr( $_, 9, 8 ) );
                $nome_localidade = stringUtility::trim( substr( $_, 17, 72 ) );
                $ID_bairroInicial = substr( $_, 89, 5 );
                $DNE_ID_bairroInicial = substr( $_, 94, 8 );
                $nome_bairroInicial = stringUtility::trim( substr( $_, 102, 72 ) );
                $ID_bairroFinal = substr( $_, 174, 5 );
                $DNE_ID_bairroFinal = substr( $_, 179, 8 );
                $nome_bairroFinal = stringUtility::trim( substr( $_, 187, 72 ) );
                $desc_tipo_logradouro = stringUtility::trim( substr( $_, 259, 26 ) );
                $preposicao = stringUtility::trim( substr( $_, 285, 3 ) );
                $desc_titulo_logradouro = stringUtility::trim( substr( $_, 288, 72 ) );
                $ID_logradouro = substr( $_, 360, 6 );
                $DNE_ID_logradouro = substr( $_, 366, 8 );
                $nome_logradouro = stringUtility::trim( substr( $_, 374, 72 ) );
                $abreviatura_logradouro = stringUtility::trim( substr( $_, 446, 36 ) );
                $adicional = stringUtility::trim( substr( $_, 482, 36 ) );
                $numeroCEP = substr( $_, 518, 8 );
                $grande_usuario = stringUtility::trim( substr( $_, 526, 1 ) );
                $numeroInicial = substr( $_, 527, 11 );
                $numeroFinal = substr( $_, 538, 11 );
                $paridade = substr( $_, 549, 1 );
                $DNE_ID_seccionamento = substr( $_, 550, 8 );

                #
                #   input record validation
                #
                if( $selectedState ne ''
                        && $siglaUF ne $selectedState ) {
                    next;
                }

                #
                #   record adjustements
                #
                if( '' ne $preposicao ) {
                    $nome_logradouro = qq/$preposicao $nome_logradouro/;
                }

                # remove comma
                $numeroInicial =~ s/,//g;
                $numeroFinal =~ s/,//g;

                # remove trailing spaces
                $numeroInicial =~ s/ *$//g;
                $numeroFinal =~ s/ *$//g;

                if( 'I' eq $paridade ) {
                    if( 0 == $numeroInicial % 2 ) {
                        $numeroInicial++;
                    }
                    if( 0 == $numeroFinal % 2 ) {
                        $numeroFinal--;
                    }
                } elsif( 'P' eq $paridade ) {
                    if( 1 == $numeroInicial % 2 ) {
                        $numeroInicial++;
                    }
                    if( 1 == $numeroFinal % 2 ) {
                        $numeroFinal--;
                    }
                }

                #
                #   construct the related objects
                #
                if( ! exists $hash_localidade{ $DNE_ID_localidade } ) {
                    print LOG_FILE "importLogradouroUF: Localidade not found to DNE_ID_localidade = $DNE_ID_localidade \n";
                    $localidade = undef;
                    next; #read next record if localidade is not found
                } else {
                    $localidade = $hash_localidade{ $DNE_ID_localidade };
                }

                if( ! exists $hash_tipo_logradouro{ $desc_tipo_logradouro } ) {
                    $tipo_logradouro = undef;
                    print LOG_FILE "importLogradouroUF: tipo_logradouro = NULL \n" if $sysDebug >= 3;
                } else {
                    $tipo_logradouro = $hash_tipo_logradouro{ $desc_tipo_logradouro };
                    print LOG_FILE "importLogradouroUF: tipo_logradouro = " . $tipo_logradouro->descricao() . "\n" if $sysDebug >= 3;
                }

                if( ! exists $hash_titulo_logradouro{ $desc_titulo_logradouro } ) {
                    print LOG_FILE "importLogradouroUF: titulo_logradouro = NULL \n" if $sysDebug >= 3;
                    $titulo_logradouro = undef;
                } else {
                    $titulo_logradouro = $hash_titulo_logradouro{ $desc_titulo_logradouro };
                    print LOG_FILE "importLogradouroUF: titulo_logradouro = " . $titulo_logradouro->descricao() . "\n" if $sysDebug >= 3;
                }

                #
                #   Checks if logradouro already exists in DB
                #
                $logradouro = logradouro::queryByLocalidadeNome( $connection
                        , $localidade
                        , $tipo_logradouro
                        , $titulo_logradouro
                        , substr( $nome_logradouro, 0, 60 ) );

                #
                #   if logradouro already exists, only report it if necessary
                #
                if( defined $logradouro
                        && 0 != $logradouro )
                {
                    $exstngStreetCount++;

                    print LOG_FILE "importLogradouroUF: Existing logradouro = " . $logradouro->to_string(). "\n" if $sysDebug >= 3;

                    if( '' ne $reportFile )
                    {
                        printf REPORT qq/LOGRADOURO: record "$nome_logradouro" already exists on database:%s\n\n/
                            , $logradouro->to_string();
                    }
                }
                #
                #   if logradouro do not exists, construct a new logradouro object
                #
                else
                {
                    $numImportedLines++;

                    $logradouro = logradouro::new();

                    #   Sets COD_TIPO_LOGRAD field
                    if( defined $tipo_logradouro ) {
                        $logradouro->codigoTipo( $tipo_logradouro->codigo() );
                    } else {
                        $logradouro->codigoTipo( undef );
                    }

                    #   Sets COD_TITULO_LOGRAD field
                    if( defined $titulo_logradouro ) {
                        $logradouro->codigoTitulo( $titulo_logradouro->codigo() );
                    } else {
                        $logradouro->codigoTitulo( undef );
                    }

                    #   Sets COD_LOCALIDADE field
                    $logradouro->codigoLocalidade( $localidade->codigo() );
                    $logradouro->nome( $nome_logradouro );

                    #   Sets NOM_ABREV_LOGRADOURO field
                    if (defined $abreviatura_logradouro and '' ne $abreviatura_logradouro) {
                        $logradouro->nomeAbreviado( &stringUtility::abbreviate( $abreviatura_logradouro, \%hash_abrevLogradouro ) );
                    } else {
                        $logradouro->nomeAbreviado( &stringUtility::abbreviate( $nome_logradouro, \%hash_abrevLogradouro ) );
                    }

                    #   Sets remaining field
                    $logradouro->indNumeracao( 2 );
                    $logradouro->flagComplemento( 0 );
                    $logradouro->dataAtualizacao( $today );
                    $logradouro->usuarioAtualizacao( $importUser );

                    print LOG_FILE "importLogradouroUF: New logradouro = " . $logradouro->to_string(). "\n" if $sysDebug >= 3;

                    #   store a map between database ID and DNE ID
                    $hash_logradouro{ $DNE_ID_logradouro } = $logradouro;

                    if ( 1 == $noUpdates )
                    {
                        print LOG_FILE "importLogradouroUF: Logradouro NOT commited \n";
                        next; # we must not proceed if the logradouro is not inserted in DB
                    }
                    else
                    {
                        $returnCode = $logradouro->persist( $connection );

                        if( SUCCESS != $returnCode) {
                            $dbErrors++;
                        } else {
                            $commitCount++;
                            $linesCommitted++;
                            print LOG_FILE "importLogradouroUF: Logradouro Created \n";
                        }
                    }
                }

                #
                #   if necessary, construct a new CEP and rel_cep_logradouro_bairro object
                #
                if( '' ne $numeroCEP )
                {
                    &importCEP( $numeroCEP, $DNE_ID_localidade );

                    if( exists $hash_CEP{ $numeroCEP }
                            && exists $hash_bairro{ $DNE_ID_bairroInicial } )
                    {
                        #
                        # Checks if there is an address to logradouro, cep and bairro inicial
                        #
                        $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::queryByCodigo( $connection
                                , $logradouro->codigo()
                                , $hash_CEP{ $numeroCEP }->codigo()
                                , $numeroInicial
                                , $hash_bairro{ $DNE_ID_bairroInicial }->codigo() );

                        #
                        # If address does not exist, create new one
                        #
                        if( ! defined $rel_cep_logradouro_bairro
                            || 0 == $rel_cep_logradouro_bairro )
                        {
                            printf LOG_FILE qq/importLogradouroUF: No address found to "cod_logradouro = %d, cep_cod= %d, cod_bairro = %d \n/
                                    , $logradouro->codigo()
                                    , $hash_CEP{ $numeroCEP }->codigo()
                                    , $hash_bairro{ $DNE_ID_bairroInicial }->codigo() if $sysDebug >=3;

                            $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::new();

                            $rel_cep_logradouro_bairro->codigoLogradouro( $logradouro->codigo() );
                            $rel_cep_logradouro_bairro->codigoCEP( $hash_CEP{ $numeroCEP }->codigo() );
                            $rel_cep_logradouro_bairro->numeroInicial( $numeroInicial );
                            $rel_cep_logradouro_bairro->codigoBairro( $hash_bairro{ $DNE_ID_bairroInicial }->codigo() );
                            $rel_cep_logradouro_bairro->numeroFinal( $numeroFinal );

                            if( 'A' eq $paridade ) {
                                $rel_cep_logradouro_bairro->indLadoNumeracao( 0 );
                            } elsif( 'I' eq $paridade
                                    || ( 'D' eq $paridade && 1 == $numeroInicial % 2 )
                                    || ( 'E' eq $paridade && 1 == $numeroInicial % 2 ) ) {
                                $rel_cep_logradouro_bairro->indLadoNumeracao( 1 );
                            } else {
                                $rel_cep_logradouro_bairro->indLadoNumeracao( 2 );
                            }

                            $rel_cep_logradouro_bairro->dataAtualizacao( $today );
                            $rel_cep_logradouro_bairro->usuarioAtualizacao( $importUser );

                            if ( 0 == $noUpdates )
                            {
                                $returnCode = $rel_cep_logradouro_bairro->persist( $connection );

                                if( SUCCESS != $returnCode) {
                                    $dbErrors++;
                                }
                                else {
                                    printf LOG_FILE qq/importLogradouroUF: REL_CEP_LOGRAD_BAIRRO record "%d - %d - %d - %d" created on database:%s\n\n/
                                            , $logradouro->codigo()
                                            , $hash_CEP{ $numeroCEP }->codigo()
                                            , 1
                                            , $hash_bairro{ $DNE_ID_bairroInicial }->codigo()
                                            , $rel_cep_logradouro_bairro->to_string() if $sysDebug >=3;
                                }
                            }
                        }
                        #
                        # Otherwise, reports it if necessary
                        #
                        else
                        {
                            print LOG_FILE "importLogradouroUF: Existing address = " . $rel_cep_logradouro_bairro->to_string(). "\n" if $sysDebug >= 3;

                            if ('' ne $reportFile)
                            {
                                printf REPORT qq/REL_CEP_LOGRAD_BAIRRO: record "%d - %d - %d - %d" already exists on database:%s\n\n/
                                        , $logradouro->codigo()
                                        , $hash_CEP{ $numeroCEP }->codigo()
                                        , 1
                                        , $hash_bairro{ $DNE_ID_bairroInicial }->codigo()
                                        , $rel_cep_logradouro_bairro->to_string();
                            }
                        }
                    }

                    if( exists $hash_CEP{ $numeroCEP }
                            && $DNE_ID_bairroFinal !~ /^0+$/
                            && exists $hash_bairro{ $DNE_ID_bairroFinal } )
                    {
                        #
                        # Checks if there is an address to logradouro, cep and bairro final
                        #
                        $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::queryByCodigo( $connection
                                , $logradouro->codigo()
                                , $hash_CEP{ $numeroCEP }->codigo()
                                , $numeroInicial
                                , $hash_bairro{ $DNE_ID_bairroFinal }->codigo() );

                        #
                        # If address does not exist, create new one
                        #
                        if( ! defined $rel_cep_logradouro_bairro
                            || 0 == $rel_cep_logradouro_bairro )
                        {
                            printf LOG_FILE qq/importLogradouroUF: No address found to "cod_logradouro = %d, cep_cod= %d, cod_bairro = %d \n/
                                    , $logradouro->codigo()
                                    , $hash_CEP{ $numeroCEP }->codigo()
                                    , $hash_bairro{ $DNE_ID_bairroFinal }->codigo() if $sysDebug >=3;

                            $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::new();
                            $rel_cep_logradouro_bairro->codigoLogradouro( $logradouro->codigo() );
                            $rel_cep_logradouro_bairro->codigoCEP( $hash_CEP{ $numeroCEP }->codigo() );
                            $rel_cep_logradouro_bairro->numeroInicial( $numeroInicial );
                            $rel_cep_logradouro_bairro->codigoBairro( $hash_bairro{ $DNE_ID_bairroFinal }->codigo() );
                            $rel_cep_logradouro_bairro->numeroFinal( $numeroFinal );

                            if( 'A' eq $paridade ) {
                                $rel_cep_logradouro_bairro->indLadoNumeracao( 0 );
                            } elsif( 'I' eq $paridade
                                    || ( 'D' eq $paridade && 1 == $numeroInicial % 2 )
                                    || ( 'E' eq $paridade && 1 == $numeroInicial % 2 ) ) {
                                $rel_cep_logradouro_bairro->indLadoNumeracao( 1 );
                            } else {
                                $rel_cep_logradouro_bairro->indLadoNumeracao( 2 );
                            }

                            $rel_cep_logradouro_bairro->dataAtualizacao( $today );
                            $rel_cep_logradouro_bairro->usuarioAtualizacao( $importUser );

                            if ( 0 == $noUpdates )
                            {
                                $returnCode = $rel_cep_logradouro_bairro->persist( $connection );

                                if( SUCCESS != $returnCode) {
                                    $dbErrors++;
                                }
                                else {
                                    printf LOG_FILE qq/importLogradouroUF: REL_CEP_LOGRAD_BAIRRO record "%d - %d - %d - %d" created on database:%s\n\n/
                                            , $logradouro->codigo()
                                            , $hash_CEP{ $numeroCEP }->codigo()
                                            , 1
                                            , $hash_bairro{ $DNE_ID_bairroFinal }->codigo()
                                            , $rel_cep_logradouro_bairro->to_string() if $sysDebug >=3;
                                }
                            }
                        }
                        #
                        # Otherwise, reports it if necessary
                        #
                        else
                        {
                            print LOG_FILE "importLogradouroUF: Existing address = " . $rel_cep_logradouro_bairro->to_string(). "\n" if $sysDebug >= 3;

                            if ('' ne $reportFile)
                            {
                                printf REPORT qq/REL_CEP_LOGRAD_BAIRRO: record "%d - %d - %d - %d" already exists on database:%s\n\n/
                                        , $logradouro->codigo()
                                        , $hash_CEP{ $numeroCEP }->codigo()
                                        , 1
                                        , $hash_bairro{ $DNE_ID_bairroFinal }->codigo()
                                        , $rel_cep_logradouro_bairro->to_string();
                            }
                        }
                    }
                }

                #
                # Finished a database transaction, make rollback or commit
                #
                if($commitCount >= 10000)
                {
                    if (0 == $noUpdates) {
                        $connection->commit();
                    } else {
                        $connection->rollback();
                    }
                    $commitCount = 0;
                }

                next; # next line
            }

            #
            #   range number detail line
            #   N  = Numeração de Lote do Logradouro 1
            #
            if( 1 < $numReadedLines && $_ =~ /^N/ )
            {
                if( 632 != length ( $_ ) )
                {
                    die qq/wrong "DNE_GU_XX_LOGRADOUROS.TXT" detail record length\n/;
                }

                $siglaUF = stringUtility::trim( substr( $_, 1, 2 ) );
                $ID_localidade = stringUtility::trim( substr( $_, 3, 6 ) );
                $DNE_ID_localidade = stringUtility::trim( substr( $_, 9, 8 ) );
                $nome_localidade = stringUtility::trim( substr( $_, 17, 72 ) );
                $ID_bairroInicial = substr( $_, 89, 5 );
                $DNE_ID_bairroInicial = substr( $_, 94, 8 );
                $nome_bairroInicial = stringUtility::trim( substr( $_, 102, 72 ) );
                $ID_bairroFinal = substr( $_, 174, 5 );
                $DNE_ID_bairroFinal = substr( $_, 179, 8 );
                $nome_bairroFinal = stringUtility::trim( substr( $_, 187, 72 ) );
                $desc_tipo_logradouro = stringUtility::trim( substr( $_, 259, 26 ) );
                $preposicao = stringUtility::trim( substr( $_, 285, 3 ) );
                $desc_titulo_logradouro = stringUtility::trim( substr( $_, 288, 72 ) );
                $ID_logradouro = substr( $_, 360, 6 );
                $DNE_ID_logradouro = substr( $_, 366, 8 );
                $nome_logradouro = stringUtility::trim( substr( $_, 374, 72 ) );
                $abreviatura_logradouro = stringUtility::trim( substr( $_, 446, 36 ) );
                $adicional = stringUtility::trim( substr( $_, 482, 36 ) );
                $numeroCEP = stringUtility::trim( substr( $_, 518, 8 ) );
                $grande_usuario = stringUtility::trim( substr( $_, 526, 1 ) );
                $loteNumber = stringUtility::trim( substr( $_, 527, 11 ) );

                #
                #   input record validation
                #
                if( $selectedState ne '' && $siglaUF ne $selectedState )
                {
                    next;
                }

                #
                #   record adjustements
                #
                if( '' ne $preposicao )
                {
                    $nome_logradouro = qq/$preposicao $nome_logradouro/;
                }

                #
                #   concatenate lote number to street name
                #
                if( '' ne $loteNumber )
                {
                    $nome_logradouro = qq/$nome_logradouro $loteNumber/;
                }

                #
                #   construct the related objects
                #
                if( ! exists $hash_localidade{ $DNE_ID_localidade } ) {
                    print LOG_FILE "importLogradouroUF: Localidade not found to DNE_ID_localidade = $DNE_ID_localidade \n";
                    $localidade = undef;
                    next; #read next record if localidade is not found
                } else {
                    $localidade = $hash_localidade{ $DNE_ID_localidade };
                }

                if( ! exists $hash_tipo_logradouro{ $desc_tipo_logradouro } ) {
                    $tipo_logradouro = undef;
                    print LOG_FILE "importLogradouroUF: tipo_logradouro = NULL \n" if $sysDebug >= 3;
                } else {
                    $tipo_logradouro = $hash_tipo_logradouro{ $desc_tipo_logradouro };
                    print LOG_FILE "importLogradouroUF: tipo_logradouro = " . $tipo_logradouro->descricao() . "\n" if $sysDebug >= 3;
                }

                if( ! exists $hash_titulo_logradouro{ $desc_titulo_logradouro } ) {
                    print LOG_FILE "importLogradouroUF: titulo_logradouro = NULL \n" if $sysDebug >= 3;
                    $titulo_logradouro = undef;
                } else {
                    $titulo_logradouro = $hash_titulo_logradouro{ $desc_titulo_logradouro };
                    print LOG_FILE "importLogradouroUF: titulo_logradouro = " . $titulo_logradouro->descricao() . "\n" if $sysDebug >= 3;
                }

                #
                #   Checks if logradouro already exists in DB
                #
                $logradouro = logradouro::queryByLocalidadeNome( $connection
                        , $localidade
                        , $tipo_logradouro
                        , $titulo_logradouro
                        , substr( $nome_logradouro, 0, 60 ) );

                #
                #   if logradouro already exists, only report it if necessary
                #
                if( defined $logradouro
                        && 0 != $logradouro )
                {
                    $exstngStreetCount++;

                    print LOG_FILE "importLogradouroUF: Existing logradouro = " . $logradouro->to_string(). "\n" if $sysDebug >= 3;

                    if( '' ne $reportFile )
                    {
                        printf REPORT qq/LOGRADOURO: record "$nome_logradouro" already exists on database:%s\n\n/
                            , $logradouro->to_string();
                    }
                }
                #
                #   if logradouro do not exists, construct a new logradouro object
                #
                else
                {
                    $numImportedLines++;

                    $logradouro = logradouro::new();

                    #   Sets COD_TIPO_LOGRAD field
                    if( defined $tipo_logradouro ) {
                        $logradouro->codigoTipo( $tipo_logradouro->codigo() );
                    } else {
                        $logradouro->codigoTipo( undef );
                    }

                    #   Sets COD_TITULO_LOGRAD field
                    if( defined $titulo_logradouro ) {
                        $logradouro->codigoTitulo( $titulo_logradouro->codigo() );
                    } else {
                        $logradouro->codigoTitulo( undef );
                    }

                    #   Sets COD_LOCALIDADE field
                    $logradouro->codigoLocalidade( $localidade->codigo() );

                    #   Sets NOM_LOGRADOURO field
                    $logradouro->nome( $nome_logradouro );

                    #   Sets NOM_ABREV_LOGRADOURO field
                    if (defined $abreviatura_logradouro and '' ne $abreviatura_logradouro) {
                        $logradouro->nomeAbreviado( &stringUtility::abbreviate( $abreviatura_logradouro, \%hash_abrevLogradouro ) );
                    } else {
                        $logradouro->nomeAbreviado( &stringUtility::abbreviate( $nome_logradouro, \%hash_abrevLogradouro ) );
                    }

                    #   Sets IND_NUMERACAO_ENDERECO field
                    $logradouro->indNumeracao( 2 );

                    #   Sets FLG_COMPLEMENTO field
                    $logradouro->flagComplemento( 0 );

                    #   Sets DAT_ATUALIZACAO field
                    $logradouro->dataAtualizacao( $today );

                    #   Sets USUARIO field
                    $logradouro->usuarioAtualizacao( $importUser );

                    print LOG_FILE "importLogradouroUF: New logradouro = " . $logradouro->to_string(). "\n" if $sysDebug >= 3;

                    #   store a map between database ID and DNE ID
                    $hash_logradouro{ $DNE_ID_logradouro } = $logradouro;

                    if ( 1 == $noUpdates )
                    {
                        print LOG_FILE "importLogradouroUF: Logradouro NOT commited \n";
                        next; # we must not proceed if the logradouro is not inserted in DB
                    }
                    else
                    {
                        $returnCode = $logradouro->persist( $connection );

                        if( SUCCESS != $returnCode) {
                            $dbErrors++;
                        } else {
                            $commitCount++;
                            $linesCommitted++;
                            print LOG_FILE "importLogradouroUF: Logradouro Created \n";
                        }
                    }
                }

                #
                #   if necessary, construct a new CEP and rel_cep_logradouro_bairro object
                #
                if( '' ne $numeroCEP )
                {
                    &importCEP( $numeroCEP, $DNE_ID_localidade );

                    if( exists $hash_CEP{ $numeroCEP }
                        && exists $hash_bairro{ $DNE_ID_bairroInicial } )
                    {
                        #
                        # Checks if there is an address to logradouro, cep and bairro inicial
                        #
                        $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::queryByCodigo( $connection
                                , $logradouro->codigo()
                                , $hash_CEP{ $numeroCEP }->codigo()
                                , 1
                                , $hash_bairro{ $DNE_ID_bairroInicial }->codigo() );

                        #
                        # If address does not exist, create new one
                        #
                        if( ! defined $rel_cep_logradouro_bairro
                            || 0 == $rel_cep_logradouro_bairro )
                        {
                            printf LOG_FILE qq/importLogradouroUF: No address found to "cod_logradouro = %d, cep_cod= %d, cod_bairro = %d \n/
                                    , $logradouro->codigo()
                                    , $hash_CEP{ $numeroCEP }->codigo()
                                    , $hash_bairro{ $DNE_ID_bairroInicial }->codigo() if $sysDebug >=3;

                            $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::new();

                            $rel_cep_logradouro_bairro->codigoLogradouro( $logradouro->codigo() );
                            $rel_cep_logradouro_bairro->codigoCEP( $hash_CEP{ $numeroCEP }->codigo() );
                            $rel_cep_logradouro_bairro->numeroInicial( 1 );
                            $rel_cep_logradouro_bairro->codigoBairro( $hash_bairro{ $DNE_ID_bairroInicial }->codigo() );
                            $rel_cep_logradouro_bairro->numeroFinal( 99999 );
                            $rel_cep_logradouro_bairro->indLadoNumeracao( 0 );
                            $rel_cep_logradouro_bairro->dataAtualizacao( $today );
                            $rel_cep_logradouro_bairro->usuarioAtualizacao( $importUser );

                            if ( 0 == $noUpdates )
                            {
                                $returnCode = $rel_cep_logradouro_bairro->persist( $connection );

                                if( SUCCESS != $returnCode) {
                                    $dbErrors++;
                                }
                                else {
                                    printf LOG_FILE qq/importLogradouroUF: REL_CEP_LOGRAD_BAIRRO record "%d - %d - %d - %d" created on database:%s\n\n/
                                            , $logradouro->codigo()
                                            , $hash_CEP{ $numeroCEP }->codigo()
                                            , 1
                                            , $hash_bairro{ $DNE_ID_bairroInicial }->codigo()
                                            , $rel_cep_logradouro_bairro->to_string() if $sysDebug >=3;
                                }
                            }
                        }
                        #
                        # Otherwise, reports it if necessary
                        #
                        else
                        {
                            print LOG_FILE "importLogradouroUF: Existing address = " . $rel_cep_logradouro_bairro->to_string(). "\n" if $sysDebug >= 3;

                            if ('' ne $reportFile)
                            {
                                printf REPORT qq/REL_CEP_LOGRAD_BAIRRO: record "%d - %d - %d - %d" already exists on database:%s\n\n/
                                        , $logradouro->codigo()
                                        , $hash_CEP{ $numeroCEP }->codigo()
                                        , 1
                                        , $hash_bairro{ $DNE_ID_bairroInicial }->codigo()
                                        , $rel_cep_logradouro_bairro->to_string();
                            }
                        }
                    }

                    if( exists $hash_CEP{ $numeroCEP }
                            && $DNE_ID_bairroFinal !~ /^0+$/
                            && exists $hash_bairro{ $DNE_ID_bairroFinal } )
                    {
                        #
                        # Checks if there is an address to logradouro, cep and bairro final
                        #
                        $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::queryByCodigo( $connection
                                , $logradouro->codigo()
                                , $hash_CEP{ $numeroCEP }->codigo()
                                , 1
                                , $hash_bairro{ $DNE_ID_bairroFinal }->codigo() );

                        #
                        # If address does not exist, create new one
                        #
                        if( ! defined $rel_cep_logradouro_bairro
                            || 0 == $rel_cep_logradouro_bairro )
                        {
                            printf LOG_FILE qq/importLogradouroUF: No address found to "cod_logradouro = %d, cep_cod= %d, cod_bairro = %d \n/
                                    , $logradouro->codigo()
                                    , $hash_CEP{ $numeroCEP }->codigo()
                                    , $hash_bairro{ $DNE_ID_bairroFinal }->codigo() if $sysDebug >=3;

                            $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::new();

                            $rel_cep_logradouro_bairro->codigoLogradouro( $logradouro->codigo() );
                            $rel_cep_logradouro_bairro->codigoCEP( $hash_CEP{ $numeroCEP }->codigo() );
                            $rel_cep_logradouro_bairro->numeroInicial( 1 );
                            $rel_cep_logradouro_bairro->codigoBairro( $hash_bairro{ $DNE_ID_bairroFinal }->codigo() );
                            $rel_cep_logradouro_bairro->numeroFinal( 99999 );
                            $rel_cep_logradouro_bairro->indLadoNumeracao( 0 );
                            $rel_cep_logradouro_bairro->dataAtualizacao( $today );
                            $rel_cep_logradouro_bairro->usuarioAtualizacao( $importUser );

                            if ( 0 == $noUpdates )
                            {
                                $returnCode = $rel_cep_logradouro_bairro->persist( $connection );

                                if( SUCCESS != $returnCode) {
                                    $dbErrors++;
                                }
                                else {
                                    printf LOG_FILE qq/importLogradouroUF: REL_CEP_LOGRAD_BAIRRO record "%d - %d - %d - %d" created on database:%s\n\n/
                                            , $logradouro->codigo()
                                            , $hash_CEP{ $numeroCEP }->codigo()
                                            , 1
                                            , $hash_bairro{ $DNE_ID_bairroFinal }->codigo()
                                            , $rel_cep_logradouro_bairro->to_string() if $sysDebug >=3;
                                }
                            }
                        }
                        #
                        # Otherwise, reports it if necessary
                        #
                        else
                        {
                            print LOG_FILE "importLogradouroUF: Existing address = " . $rel_cep_logradouro_bairro->to_string(). "\n" if $sysDebug >= 3;

                            if ('' ne $reportFile)
                            {
                                printf REPORT qq/REL_CEP_LOGRAD_BAIRRO: record "%d - %d - %d - %d" already exists on database:%s\n\n/
                                        , $logradouro->codigo()
                                        , $hash_CEP{ $numeroCEP }->codigo()
                                        , 1
                                        , $hash_bairro{ $DNE_ID_bairroFinal }->codigo()
                                        , $rel_cep_logradouro_bairro->to_string();
                            }
                        }
                    }
                }

                #
                # Finished a database transaction, make rollback or commit
                #
                if($commitCount >= 10000)
                {
                    if (0 == $noUpdates) {
                        $connection->commit();
                    } else {
                        $connection->rollback();
                    }
                    $commitCount = 0;
                }

                next;
            }

            #
            #   compliment detail line
            #   K = Complemento1 do Logradouro 1
            #
            if( 1 < $numReadedLines
                    && $_ =~ /^K/ )
            {
                if( 632 != length ( $_ ) )
                {
                    die qq/wrong "DNE_GU_XX_LOGRADOUROS.TXT" detail record length\n/;
                }

                $siglaUF = stringUtility::trim( substr( $_, 1, 2 ) );
                $ID_localidade = stringUtility::trim( substr( $_, 3, 6 ) );
                $DNE_ID_localidade = stringUtility::trim( substr( $_, 9, 8 ) );
                $nome_localidade = stringUtility::trim( substr( $_, 17, 72 ) );
                $ID_bairroInicial = substr( $_, 89, 5 );
                $DNE_ID_bairroInicial = substr( $_, 94, 8 );
                $nome_bairroInicial = stringUtility::trim( substr( $_, 102, 72 ) );
                $ID_bairroFinal = substr( $_, 174, 5 );
                $DNE_ID_bairroFinal = substr( $_, 179, 8 );
                $nome_bairroFinal = stringUtility::trim( substr( $_, 187, 72 ) );
                $desc_tipo_logradouro = stringUtility::trim( substr( $_, 259, 26 ) );
                $preposicao = stringUtility::trim( substr( $_, 285, 3 ) );
                $desc_titulo_logradouro = stringUtility::trim( substr( $_, 288, 72 ) );
                $ID_logradouro = substr( $_, 360, 6 );
                $DNE_ID_logradouro = substr( $_, 366, 8 );
                $nome_logradouro = stringUtility::trim( substr( $_, 374, 72 ) );
                $abreviatura_logradouro = stringUtility::trim( substr( $_, 446, 36 ) );
                $adicional = stringUtility::trim( substr( $_, 482, 36 ) );
                $numeroCEP = stringUtility::trim( substr( $_, 518, 8 ) );
                $grande_usuario = stringUtility::trim( substr( $_, 526, 1 ) );
                $loteNumber = stringUtility::trim( substr( $_, 527, 11 ) );
                $complimentName = stringUtility::trim( substr( $_, 538, 36 ) );
                $complimentNumber = stringUtility::trim( substr( $_, 574, 11 ) );

                #
                #   input record validation
                #
                if( $selectedState ne '' && $siglaUF ne $selectedState )
                {
                    next;
                }

                #
                #   record adjustements
                #
                if( '' ne $preposicao )
                {
                    $nome_logradouro = qq/$preposicao $nome_logradouro/;
                }

                if( '' ne $loteNumber )
                {
                    $nome_logradouro = qq/$nome_logradouro $loteNumber/;
                }

                if( '' ne $complimentName )
                {
                    $nome_logradouro = qq/$nome_logradouro $complimentName/;
                }

                if( '' ne $complimentNumber )
                {
                    $nome_logradouro = qq/$nome_logradouro $complimentNumber/;
                }

                #
                #   construct the related objects
                #
                if( ! exists $hash_localidade{ $DNE_ID_localidade } ) {
                    print LOG_FILE "importLogradouroUF: Localidade not found to DNE_ID_localidade = $DNE_ID_localidade \n";
                    $localidade = undef;
                    next; #read next record if localidade is not found
                } else {
                    $localidade = $hash_localidade{ $DNE_ID_localidade };
                }

                if( ! exists $hash_tipo_logradouro{ $desc_tipo_logradouro } ) {
                    $tipo_logradouro = undef;
                    print LOG_FILE "importLogradouroUF: tipo_logradouro = NULL \n" if $sysDebug >= 3;
                } else {
                    $tipo_logradouro = $hash_tipo_logradouro{ $desc_tipo_logradouro };
                    print LOG_FILE "importLogradouroUF: tipo_logradouro = " . $tipo_logradouro->descricao() . "\n" if $sysDebug >= 3;
                }

                if( ! exists $hash_titulo_logradouro{ $desc_titulo_logradouro } ) {
                    print LOG_FILE "importLogradouroUF: titulo_logradouro = NULL \n" if $sysDebug >= 3;
                    $titulo_logradouro = undef;
                } else {
                    $titulo_logradouro = $hash_titulo_logradouro{ $desc_titulo_logradouro };
                    print LOG_FILE "importLogradouroUF: titulo_logradouro = " . $titulo_logradouro->descricao() . "\n" if $sysDebug >= 3;
                }

                #
                #   Checks if logradouro already exists in DB
                #
                $logradouro = logradouro::queryByLocalidadeNome( $connection
                        , $localidade
                        , $tipo_logradouro
                        , $titulo_logradouro
                        , substr( $nome_logradouro, 0, 60 ) );

                #
                #   if logradouro already exists, only report it if necessary
                #
                if( defined $logradouro
                        && 0 != $logradouro )
                {
                    $exstngStreetCount++;

                    print LOG_FILE "importLogradouroUF: Existing logradouro = " . $logradouro->to_string(). "\n" if $sysDebug >= 3;

                    if( '' ne $reportFile )
                    {
                        printf REPORT qq/LOGRADOURO: record "$nome_logradouro" already exists on database:%s\n\n/
                            , $logradouro->to_string();
                    }
                }
                #
                #   if logradouro do not exists, construct a new logradouro object
                #
                else
                {
                    $numImportedLines++;

                    $logradouro = logradouro::new();

                    #   Sets COD_TIPO_LOGRAD field
                    if( defined $tipo_logradouro ) {
                        $logradouro->codigoTipo( $tipo_logradouro->codigo() );
                    } else {
                        $logradouro->codigoTipo( undef );
                    }

                    #   Sets COD_TITULO_LOGRAD field
                    if( defined $titulo_logradouro ) {
                        $logradouro->codigoTitulo( $titulo_logradouro->codigo() );
                    } else {
                        $logradouro->codigoTitulo( undef );
                    }

                    #   Sets COD_LOCALIDADE field
                    $logradouro->codigoLocalidade( $localidade->codigo() );
                    $logradouro->nome( $nome_logradouro );

                    #   Sets NOM_ABREV_LOGRADOURO field
                    if (defined $abreviatura_logradouro and '' ne $abreviatura_logradouro) {
                        $logradouro->nomeAbreviado( &stringUtility::abbreviate( $abreviatura_logradouro, \%hash_abrevLogradouro ) );
                    } else {
                        $logradouro->nomeAbreviado( &stringUtility::abbreviate( $nome_logradouro, \%hash_abrevLogradouro ) );
                    }

                    #   Sets IND_NUMERACAO_ENDERECO field
                    $logradouro->indNumeracao( 2 );

                    #   Sets FLG_COMPLEMENTO field
                    $logradouro->flagComplemento( 0 );

                    #   Sets DAT_ATUALIZACAO field
                    $logradouro->dataAtualizacao( $today );

                    #   Sets USUARIO field
                    $logradouro->usuarioAtualizacao( $importUser );

                    print LOG_FILE "importLogradouroUF: New logradouro = " . $logradouro->to_string(). "\n" if $sysDebug >= 3;

                    #   store a map between database ID and DNE ID
                    $hash_logradouro{ $DNE_ID_logradouro } = $logradouro;

                    if ( 1 == $noUpdates )
                    {
                        print LOG_FILE "importLogradouroUF: Logradouro NOT commited \n";
                        next; # we must not proceed if the logradouro is not inserted in DB
                    }
                    else
                    {
                        $returnCode = $logradouro->persist( $connection );

                        if( SUCCESS != $returnCode) {
                            $dbErrors++;
                        } else {
                            $commitCount++;
                            $linesCommitted++;
                            print LOG_FILE "importLogradouroUF: Logradouro Created \n";
                        }
                    }
                }

                #
                #   if necessary, construct a new CEP and rel_cep_logradouro_bairro object
                #
                if( '' ne $numeroCEP )
                {
                    &importCEP( $numeroCEP, $DNE_ID_localidade );

                    if( exists $hash_CEP{ $numeroCEP }
                        && exists $hash_bairro{ $DNE_ID_bairroInicial } )
                    {
                        #
                        # Checks if there is an address to logradouro, cep and bairro inicial
                        #
                        $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::queryByCodigo( $connection
                                , $logradouro->codigo()
                                , $hash_CEP{ $numeroCEP }->codigo()
                                , 1
                                , $hash_bairro{ $DNE_ID_bairroInicial }->codigo() );

                        #
                        # If address does not exist, create a new one
                        #
                        if( ! defined $rel_cep_logradouro_bairro
                            || 0 == $rel_cep_logradouro_bairro )
                        {
                            printf LOG_FILE qq/importLogradouroUF: No address found to "cod_logradouro = %d, cep_cod= %d, cod_bairro = %d \n/
                                    , $logradouro->codigo()
                                    , $hash_CEP{ $numeroCEP }->codigo()
                                    , $hash_bairro{ $DNE_ID_bairroInicial }->codigo() if $sysDebug >=3;

                            $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::new();

                            $rel_cep_logradouro_bairro->codigoLogradouro( $logradouro->codigo() );
                            $rel_cep_logradouro_bairro->codigoCEP( $hash_CEP{ $numeroCEP }->codigo() );
                            $rel_cep_logradouro_bairro->numeroInicial( 1 );
                            $rel_cep_logradouro_bairro->codigoBairro( $hash_bairro{ $DNE_ID_bairroInicial }->codigo() );
                            $rel_cep_logradouro_bairro->numeroFinal( 99999 );
                            $rel_cep_logradouro_bairro->indLadoNumeracao( 0 );
                            $rel_cep_logradouro_bairro->dataAtualizacao( $today );
                            $rel_cep_logradouro_bairro->usuarioAtualizacao( $importUser );

                            if ( 0 == $noUpdates )
                            {
                                $returnCode = $rel_cep_logradouro_bairro->persist( $connection );

                                if( SUCCESS != $returnCode) {
                                    $dbErrors++;
                                }
                                else {
                                    printf LOG_FILE qq/importLogradouroUF: REL_CEP_LOGRAD_BAIRRO record "%d - %d - %d - %d" created on database:%s\n\n/
                                            , $logradouro->codigo()
                                            , $hash_CEP{ $numeroCEP }->codigo()
                                            , 1
                                            , $hash_bairro{ $DNE_ID_bairroInicial }->codigo()
                                            , $rel_cep_logradouro_bairro->to_string() if $sysDebug >=3;
                                }
                            }
                        }
                        #
                        # Otherwise, reports it if necessary
                        #
                        else
                        {
                            print LOG_FILE "importLogradouroUF: Existing address = " . $rel_cep_logradouro_bairro->to_string(). "\n" if $sysDebug >= 3;

                            if ('' ne $reportFile)
                            {
                                printf REPORT qq/REL_CEP_LOGRAD_BAIRRO: record "%d - %d - %d - %d" already exists on database:%s\n\n/
                                        , $logradouro->codigo()
                                        , $hash_CEP{ $numeroCEP }->codigo()
                                        , 1
                                        , $hash_bairro{ $DNE_ID_bairroInicial }->codigo()
                                        , $rel_cep_logradouro_bairro->to_string();
                            }
                        }
                    }

                    if( exists $hash_CEP{ $numeroCEP }
                            && $DNE_ID_bairroFinal !~ /^0+$/
                            && exists $hash_bairro{ $DNE_ID_bairroFinal } )
                    {
                        #
                        # Checks if there is an address to logradouro, cep and bairro final
                        #
                        $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::queryByCodigo( $connection
                                , $logradouro->codigo()
                                , $hash_CEP{ $numeroCEP }->codigo()
                                , 1
                                , $hash_bairro{ $DNE_ID_bairroFinal }->codigo() );

                        #
                        # If address does not exist, create new one
                        #
                        if( ! defined $rel_cep_logradouro_bairro
                            || 0 == $rel_cep_logradouro_bairro )
                        {
                            printf LOG_FILE qq/importLogradouroUF: No address found to "cod_logradouro = %d, cep_cod= %d, cod_bairro = %d \n/
                                    , $logradouro->codigo()
                                    , $hash_CEP{ $numeroCEP }->codigo()
                                    , $hash_bairro{ $DNE_ID_bairroFinal }->codigo() if $sysDebug >=3;

                            $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::new();

                            $rel_cep_logradouro_bairro->codigoLogradouro( $logradouro->codigo() );
                            $rel_cep_logradouro_bairro->codigoCEP( $hash_CEP{ $numeroCEP }->codigo() );
                            $rel_cep_logradouro_bairro->numeroInicial( 1 );
                            $rel_cep_logradouro_bairro->codigoBairro( $hash_bairro{ $DNE_ID_bairroFinal }->codigo() );
                            $rel_cep_logradouro_bairro->numeroFinal( 99999 );
                            $rel_cep_logradouro_bairro->indLadoNumeracao( 0 );
                            $rel_cep_logradouro_bairro->dataAtualizacao( $today );
                            $rel_cep_logradouro_bairro->usuarioAtualizacao( $importUser );

                            if ( 0 == $noUpdates )
                            {
                                $returnCode = $rel_cep_logradouro_bairro->persist( $connection );

                                if( SUCCESS != $returnCode) {
                                    $dbErrors++;
                                }
                                else {
                                    printf LOG_FILE qq/importLogradouroUF: REL_CEP_LOGRAD_BAIRRO record "%d - %d - %d - %d" created on database:%s\n\n/
                                            , $logradouro->codigo()
                                            , $hash_CEP{ $numeroCEP }->codigo()
                                            , 1
                                            , $hash_bairro{ $DNE_ID_bairroFinal }->codigo()
                                            , $rel_cep_logradouro_bairro->to_string() if $sysDebug >=3;
                                }
                            }
                        }
                        #
                        # Otherwise, reports it if necessary
                        #
                        else
                        {
                            print LOG_FILE "importLogradouroUF: Existing address = " . $rel_cep_logradouro_bairro->to_string(). "\n" if $sysDebug >= 3;

                            if ('' ne $reportFile)
                            {
                                printf REPORT qq/REL_CEP_LOGRAD_BAIRRO: record "%d - %d - %d - %d" already exists on database:%s\n\n/
                                        , $logradouro->codigo()
                                        , $hash_CEP{ $numeroCEP }->codigo()
                                        , 1
                                        , $hash_bairro{ $DNE_ID_bairroFinal }->codigo()
                                        , $rel_cep_logradouro_bairro->to_string();
                            }
                        }
                    }
                }

                #
                # Finished a database transaction, make rollback or commit
                #
                if($commitCount >= 10000)
                {
                    if (0 == $noUpdates) {
                        $connection->commit();
                    } else {
                        $connection->rollback();
                    }
                    $commitCount = 0;
                }

                next; # next line
            }

            die qq/$_: unknown record type in line\n/;
        }

        if ( $commitCount != 0 )
        {
            if (0 == $noUpdates) {
                $connection->commit();
            } else {
                $connection->rollback();
            }
            $commitCount = 0;
        }

        close LOGRADOURO_UF;
        $numReadedLines -= 2; # removes header and trailing lines
        print STDOUT qq/\t$numReadedLines lines readed\/ $numImportedLines lines imported \/ $linesCommitted lines committed \/ $exstngStreetCount existing streets\n/;
    }

    print color('yellow') . "importLogradouroUF() out" . color('reset') . "\n" unless !$sysDebug;
}

################################################################################
#   import "DNE_GU_GRANDES_USUARIOS.TXT" flat file function
################################################################################

sub importGrandesUsuarios {
    my  $flatFileName = $_[ 0 ];

    print STDOUT qq/importing "$flatFileName" DNE flat file ...\n/;

    my  $siglaUF;
    my  $ID_localidade;
    my  $DNE_ID_localidade;
    my  $nome_localidade;
    my  $ID_bairro;
    my  $DNE_ID_bairro;
    my  $nome_bairro;
    my  $ID_grandes_usuarios;
    my  $DNE_ID_grandes_usuarios;
    my  $nome_grandes_usuarios;
    my  $numeroCEP;
    my  $abreviatura_grandes_usuarios;
    my  $desc_tipo_logradouro;
    my  $preposicao;
    my  $desc_titulo_logradouro;
    my  $ID_logradouro;
    my  $DNE_ID_logradouro;
    my  $nome_logradouro;
    my  $numero;

    my  $localidade;
    my  $tipo_logradouro;
    my  $titulo_logradouro;
    my  $logradouro;
    my  $rel_cep_logradouro_bairro;

    my  $exists;
    my  $dontUpdate;
    my  $numReadedLines = 0;
    my  $numImportedLines = 0;
    my  $numeroInicial;
    my  $numeroFinal;
    my  $paridade;

    my  $dbErrors = 0;
    my  $returnCode;
    my  $commitCount = 0;

#   open the flat file and import the data

    open GRANDE_USUARIO, "< $flatFileName"
            or die qq/couldn\'t open "$flatFileName" flat file\n/;

    while( <GRANDE_USUARIO> ) {
        chop;

        $dbErrors = 0;
        $dontUpdate = 0;
        $numReadedLines++;
        print STDOUT "\t$numReadedLines\r";

#   header line

        if( 1 == $numReadedLines
                && $_ =~ /^C/ ) {
            if( $_ !~ /GRANDES USUARIOS\s*$/ ) {
                die qq/"$flatFileName" isn\'t the DNE GRANDES USUARIOS flat file\n/;
            }

            next;
        }

#   detail line

        if( 1 < $numReadedLines
                && $_ =~ /^D/ ) {
            if( 426 != length ( $_ ) ) {
                die qq/wrong "DNE_GU_GRANDES_USUARIOS.TXT" detail record length\n/;
            }

            $siglaUF = stringUtility::trim( substr( $_, 1, 2 ) );
            $ID_localidade = stringUtility::trim( substr( $_, 3, 6 ) );
            $DNE_ID_localidade = stringUtility::trim( substr( $_, 9, 8 ) );
            $nome_localidade = stringUtility::trim( substr( $_, 17, 72 ) );
            $ID_bairro = substr( $_, 89, 5 );
            $DNE_ID_bairro = substr( $_, 94, 8 );
            $nome_bairro = stringUtility::trim( substr( $_, 102, 72 ) );
            $ID_grandes_usuarios = substr( $_, 174, 6 );
            $DNE_ID_grandes_usuarios = substr( $_, 180, 8 );
            $nome_grandes_usuarios = stringUtility::trim( substr( $_, 188, 72 ) );
            $numeroCEP = substr( $_, 260, 8 );
            $abreviatura_grandes_usuarios = stringUtility::trim( substr( $_, 268, 36 ) );

            next;
        }

#   address line

        if( 1 < $numReadedLines
                && $_ =~ /^E/ ) {
            if( 426 != length ( $_ ) ) {
                die qq/wrong address record length\n/;
            }

            $desc_tipo_logradouro = stringUtility::trim( substr( $_, 15, 72 ) );
            $preposicao = stringUtility::trim( substr( $_, 87, 3 ) );
            $desc_titulo_logradouro = stringUtility::trim( substr( $_, 90, 72 ) );
            $ID_logradouro = stringUtility::trim( substr( $_, 162, 6 ) );
            $DNE_ID_logradouro = stringUtility::trim( substr( $_, 168, 8 ) );
            $nome_logradouro = stringUtility::trim( substr( $_, 176, 72 ) );
            $numero = stringUtility::trim( substr( $_, 248, 11 ) );

#   input record validation

            if( '' ne $selectedState
                    && $siglaUF ne $selectedState ) {
                next;
            }

            if( substr( $_, 1, 6 ) ne $ID_grandes_usuarios
                    || substr( $_, 7, 8 ) ne $DNE_ID_grandes_usuarios ) {
                next;
            }

#   record adjustements

            if( '' ne $preposicao ) {
                $nome_logradouro = qq/$preposicao $nome_logradouro/;
            }

            $numero =~ s/,//g;

            # remove trailing spaces
            $numero =~ s/ *$//g;

            # if $numero is not empty and is numeric only
            if( ('' ne $numero) and ($numero !~ m/\D/) ) {

                $numeroInicial = $numero;
                $numeroFinal = $numero;
                if( 1 == $numero % 2 ) {
                    $paridade = 1;
                } else {
                    $paridade = 2;
                }

            } else {

                $numeroInicial = 1;
                $numeroFinal = 99999;
                $paridade = 0;
            }

#   construct the related objects

            if( ! exists $hash_localidade{ $DNE_ID_localidade } ) {
                $localidade = undef;
            } else {
                $localidade = $hash_localidade{ $DNE_ID_localidade };
            }

            if( ! exists $hash_tipo_logradouro{ $desc_tipo_logradouro } ) {
                $tipo_logradouro = undef;
            } else {
                $tipo_logradouro = $hash_tipo_logradouro{ $desc_tipo_logradouro };
            }

            if( ! exists $hash_titulo_logradouro{ $desc_titulo_logradouro } ) {
                $titulo_logradouro = undef;
            } else {
                $titulo_logradouro = $hash_titulo_logradouro{ $desc_titulo_logradouro };
            }

#   construct a new logradouro object

            $logradouro = logradouro::queryByLocalidadeNome( $connection
                    , $localidade
                    , $tipo_logradouro
                    , $titulo_logradouro
                    , substr( $nome_logradouro, 0, 60 ) );

            if( ! defined $logradouro
                    || 0 == $logradouro ) {
                $logradouro = logradouro::new();
                $exists = 0;
            } else {
                $exists = 1;

                if( (((defined $tipo_logradouro && $logradouro->codigoTipo() == $tipo_logradouro->codigo())
                    || ! defined $tipo_logradouro)
                    && (defined $titulo_logradouro && $logradouro->codigoLocalidade() == $localidade->codigo())
                    || ! defined $titulo_logradouro)
                        && $logradouro->nome() eq $nome_logradouro ) {
                    $dontUpdate = 1;
                }
                if( 1 == $noUpdates
                        && '' ne $reportFile
                        && ( $logradouro->codigoLocalidade() != $localidade->codigo()
                        || $logradouro->nome() ne $nome_logradouro ) )
                {
                    if( ! defined $tipo_logradouro )
                    {
                        printf REPORT qq/LOGRADOURO: record "$nome_logradouro" already exists on database:%s\n\n/
                            , $logradouro->to_string();
                    }
                    elsif( $logradouro->codigoTipo() != $tipo_logradouro->codigo() )
                    {
                        printf REPORT qq/LOGRADOURO: record "$nome_logradouro" already exists on database:%s\n\n/
                            , $logradouro->to_string();
                    }
                }
            }

            if( defined $tipo_logradouro ) {
                $logradouro->codigoTipo( $tipo_logradouro->codigo() );
            }
            if( defined $titulo_logradouro ) {
                $logradouro->codigoTitulo( $titulo_logradouro->codigo() );
            }

            $logradouro->codigoLocalidade( $localidade->codigo() );
            $logradouro->nome( $nome_logradouro );
            $logradouro->nomeAbreviado( &stringUtility::abbreviate( $nome_logradouro, \%hash_abrevLogradouro ) );

            if( 'S/N' eq $numero ) {
                $logradouro->indNumeracao( 0 );
            } elsif( '' ne $numero ) {
                $logradouro->indNumeracao( 1 );
            } else {
                $logradouro->indNumeracao( 2 );
            }

            $logradouro->flagComplemento( 0 );
            $logradouro->dataAtualizacao( $today );
            $logradouro->usuarioAtualizacao( $importUser );

#   store a map between database ID and DNE ID

            $hash_logradouro{ $DNE_ID_logradouro } = $logradouro;

            if( ( 0 == $exists
               ||  0 == $dontUpdate )
                   && 0 == $noUpdates  ) {

                $returnCode = $logradouro->persist( $connection );

                if( SUCCESS != $returnCode) {
                    $dbErrors++;
                } else {
                    $commitCount++;
                }
            } else {
                next;
            }

#   construct a new CEP and rel_cep_logradouro_bairro object

            &importCEP( $numeroCEP, $DNE_ID_localidade );

            if( exists $hash_CEP{ $numeroCEP }
                    && exists $hash_bairro{ $DNE_ID_bairro } ) {
                $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::queryByCodigo( $connection
                        , $logradouro->codigo()
                        , $hash_CEP{ $numeroCEP }->codigo()
                        , $numeroInicial
                        , $hash_bairro{ $DNE_ID_bairro }->codigo() );

                if( 0 == $noUpdates
                        || ! defined $rel_cep_logradouro_bairro
                        || 0 == $rel_cep_logradouro_bairro ) {
                    $rel_cep_logradouro_bairro = rel_cep_logradouro_bairro::new();

                    $rel_cep_logradouro_bairro->codigoLogradouro( $logradouro->codigo() );
                    $rel_cep_logradouro_bairro->codigoCEP( $hash_CEP{ $numeroCEP }->codigo() );
                    $rel_cep_logradouro_bairro->numeroInicial( $numeroInicial );
                    $rel_cep_logradouro_bairro->codigoBairro( $hash_bairro{ $DNE_ID_bairro }->codigo() );
                    $rel_cep_logradouro_bairro->numeroFinal( $numeroFinal );
                    $rel_cep_logradouro_bairro->indLadoNumeracao( $paridade );
                    $rel_cep_logradouro_bairro->dataAtualizacao( $today );
                    $rel_cep_logradouro_bairro->usuarioAtualizacao( $importUser );

                    $returnCode = $rel_cep_logradouro_bairro->persist( $connection );

                    if( SUCCESS != $returnCode) {
                        $dbErrors++;
                    }
                } elsif( 1 == $noUpdates
                        && '' ne $reportFile
                        && $rel_cep_logradouro_bairro->numeroFinal() != $numeroFinal ) {
                            printf REPORT qq/REL_CEP_LOGRAD_BAIRRO: record "%d - %d - %d - %d" already exists on database:%s\n\n/
                            , $logradouro->codigo()
                            , $hash_CEP{ $numeroCEP }->codigo()
                            , $numeroInicial
                            , $hash_bairro{ $DNE_ID_bairro }->codigo()
                            , $rel_cep_logradouro_bairro->to_string();
                }
            }

#   Finished a database transaction, make rollback or commit per line

                if(# 0 == $dbErrors &&
                   $commitCount >= 10000) {
                    $connection->commit();
                    $commitCount = 0;
#               } else {
#                   $connection->rollback();
                }

            $ID_grandes_usuarios = '';
            $DNE_ID_grandes_usuarios = '';

            $numImportedLines++;

            next;
        }

#   trailing line

        if( $_ =~ /^#/ ) {
            last;
        }

        die qq/$_: unknown record type in line\n/;
    }

    if ( $commitCount != 0) {
        $connection->commit();
    }

    close GRANDE_USUARIO;

    print STDOUT qq/\t$numReadedLines lines readed\/ $numImportedLines lines imported\n/;
}

################################################################################
#   import "DNE_GU_CAIXAS_POSTAIS_COMUNITA.TXT" flat file function
################################################################################

sub importCaixaPostalComunitaria {
    my  $flatFileName = $_[ 0 ];

    print STDOUT qq/importing "$flatFileName" DNE flat file ...\n/;

    my  $siglaUF;
    my  $ID_localidade;
    my  $DNE_ID_localidade;
    my  $nome_localidade;
    my  $CEP_caixa_postal_comunitaria;
    my  $nome_caixa_postal_comunitaria;
    my  $ID_caixa_postal_comunitaria;
    my  $endereco_caixa_postal_comunitaria;
    my  $numero_inicial_caixa_postal_comunitaria;
    my  $numero_final_caixa_postal_comunitaria;
    my  $area_caixa_postal_comunitaria;

    my  $localidade;
    my  $caixa_postal_comunitaria;
    my  $CEP;

    my  $exists;
    my  $dontUpdate;
    my  $numReadedLines = 0;
    my  $numImportedLines = 0;

    my  $dbErrors = 0;
    my  $returnCode;
    my  $commitCount = 0;

#   open the flat file and import the data

    open CAIXA_POSTAL_COMUNITARIA, "< $flatFileName"
            or die qq/couldn\'t open "$flatFileName" flat file\n/;

    while( <CAIXA_POSTAL_COMUNITARIA> ) {
        chop;

        $dbErrors = 0;
        $dontUpdate = 0;
        $numReadedLines++;
        print STDOUT "\t$numReadedLines\r";

#   header line

        if( 1 == $numReadedLines
                && $_ =~ /^C/ ) {
            if( $_ !~ /CAIXAS POSTAIS COMUNITARIAS\s*$/ ) {
                die qq/"$flatFileName" isn\'t the DNE CAIXAS POSTAIS COMUNIT flat file\n/;
            }

            next;
        }

#   detail line

        if( 1 < $numReadedLines
                && $_ =~ /^D/ ) {
            if( 334 != length ( $_ ) ) {
                die qq/wrong "DNE_GU_CAIXAS_POSTAIS_COMUNITA.TXT" detail record length\n/;
            }

            $siglaUF = stringUtility::trim( substr( $_, 1, 2 ) );
            $ID_localidade = stringUtility::trim( substr( $_, 3, 6 ) );
            $DNE_ID_localidade = stringUtility::trim( substr( $_, 9, 8 ) );
            $nome_localidade = stringUtility::trim( substr( $_, 17, 72 ) );
            $CEP_caixa_postal_comunitaria = substr( $_, 89, 8 );
            $nome_caixa_postal_comunitaria = substr( $_, 97, 72 );
            $ID_caixa_postal_comunitaria = stringUtility::trim( substr( $_, 169, 8 ) );
            $endereco_caixa_postal_comunitaria = substr( $_, 177, 72 );
            $numero_inicial_caixa_postal_comunitaria = substr( $_, 249, 6 );
            $numero_final_caixa_postal_comunitaria = stringUtility::trim( substr( $_, 255, 6 ) );
            $area_caixa_postal_comunitaria = substr( $_, 261, 72 );

#   input record validation

            if( '' ne $selectedState
                    && $siglaUF ne $selectedState ) {
                next;
            }

#   construct the related objects

            if( ! exists $hash_localidade{ $DNE_ID_localidade } ) {
                $localidade = undef;
            } else {
                $localidade = $hash_localidade{ $DNE_ID_localidade };
            }

#   if necessary, construct a new CEP object

            if( '' ne $CEP_caixa_postal_comunitaria ) {
                &importCEP( $CEP_caixa_postal_comunitaria, $DNE_ID_localidade );
            } else {
                next;
            }

#   construct a new caixa postal comunitaria object
            if( exists $hash_CEP{ $CEP_caixa_postal_comunitaria }
                || '' eq $hash_CEP{ $CEP_caixa_postal_comunitaria }) {

                $caixa_postal_comunitaria = caixa_postal_comunitaria::queryByNome( $connection
                        , $nome_caixa_postal_comunitaria );

                if( ! defined $caixa_postal_comunitaria
                        || 0 == $caixa_postal_comunitaria ) {
                    $caixa_postal_comunitaria = caixa_postal_comunitaria::new();
                    $exists = 0;
                } else {
                    $exists = 1;

                    if(  $caixa_postal_comunitaria->nome() eq $nome_caixa_postal_comunitaria
                        && $caixa_postal_comunitaria->cep() eq $hash_CEP{ $CEP_caixa_postal_comunitaria }->codigo()
                        && $caixa_postal_comunitaria->endereco() eq $endereco_caixa_postal_comunitaria
                        && $caixa_postal_comunitaria->area() eq $area_caixa_postal_comunitaria
                        && $caixa_postal_comunitaria->numeroInicial() == $numero_inicial_caixa_postal_comunitaria
                        && $caixa_postal_comunitaria->numeroFinal == $numero_final_caixa_postal_comunitaria ) {
                        $dontUpdate = 1;
                        $hash_caixa_postal_comunitaria{ $ID_caixa_postal_comunitaria } = $caixa_postal_comunitaria;
                    }
                    if( 1 == $noUpdates
                            && '' ne $reportFile
                            && $caixa_postal_comunitaria->nome() ne $nome_caixa_postal_comunitaria ) {
                        printf REPORT qq/CAIXA POSTAL COMUNITARIA: record "$nome_caixa_postal_comunitaria" already exists on database:%s\n\n/
                                , $caixa_postal_comunitaria->to_string();
                    }
                }

                if( defined $area_caixa_postal_comunitaria ) {
                    $caixa_postal_comunitaria->area( $area_caixa_postal_comunitaria );
                }

                $caixa_postal_comunitaria->codigo();
                $caixa_postal_comunitaria->cep($hash_CEP{ $CEP_caixa_postal_comunitaria }->codigo());
                $caixa_postal_comunitaria->nome($nome_caixa_postal_comunitaria);
                $caixa_postal_comunitaria->endereco($endereco_caixa_postal_comunitaria);
                $caixa_postal_comunitaria->numeroInicial($numero_inicial_caixa_postal_comunitaria);
                $caixa_postal_comunitaria->numeroFinal($numero_final_caixa_postal_comunitaria);
                $caixa_postal_comunitaria->area($area_caixa_postal_comunitaria);
                $caixa_postal_comunitaria->dataAtualizacao( $today );
                $caixa_postal_comunitaria->usuarioAtualizacao( $importUser );
            } else {
                    print STDOUT qq/\t$CEP vazio\n/;
                next;
            }

#   store a map between database ID and DNE ID

            $hash_caixa_postal_comunitaria{ $ID_caixa_postal_comunitaria } = $caixa_postal_comunitaria;

            if( ( 0 == $exists
               ||  0 == $dontUpdate )
                   && 0 == $noUpdates  ) {

                $returnCode = $caixa_postal_comunitaria->persist( $connection );

                if( SUCCESS != $returnCode) {
                    $dbErrors++;

                } else {
                    $commitCount++;

                }
            } else {
                next;
            }

#   Finished a database transaction, make rollback or commit per line

            if(# 0 == $dbErrors &&
               $commitCount >= 1000) {
                $connection->commit();
                $commitCount = 0;
#               } else {
#               $connection->rollback();
            }

            $ID_caixa_postal_comunitaria = '';

            $numImportedLines++;

            next;
        }

#   trailing line

        if( $_ =~ /^#/ ) {
            last;
        }

        die qq/$_: unknown record type in line\n/;
    }
    if ( $commitCount != 0) {
        $connection->commit();
    }

    close CAIXA_POSTAL_COMUNITARIA;

    print STDOUT qq/\t$numReadedLines lines readed\/ $numImportedLines lines imported\n/;
}

################################################################################
#   entry point
################################################################################

print STDOUT qq/loadDNE - DNE flat files import utility\n\n/;

#   command line arguments parsing

foreach my $argument( @ARGV ) {
    if( '--help' eq $argument ) {
        print STDOUT <<HELP_OUTPUT;
use: loadDNE [options]
e.g. loadDNE.pl --noUpdates --reportFile=reportSC --state=SC --input=/path/to/DNE/files
    --help - print this help message
    --state=XX - import only the addresses for state XX
    --noUpdates - do not update records already in the database
    --reportFile=name - create file "name" with a report of duplicate records
    --input=path - read input (DNE) files from this path

HELP_OUTPUT

        exit 0;
    }

    if( $argument =~ /^--state=(\w{2})$/ ) {
        $selectedState = uc $1;
        next;
    }

    if( $argument =~ /^--noUpdates$/ ) {
        $noUpdates = 1;
        next;
    }

    if( $argument =~ /^--reportFile=(\S+)$/ ) {
        $reportFile = $1;
        next;
    }

    if( $argument =~ /^--input=(\S+)$/ ) {
        $inputPath = $1;
        next;
    }

    if( $argument =~ /^--debug=(\S+)$/ ) {
        $sysDebug = $1;
        next;
    }

    die qq/invalid argument $argument\n/;
}

#   establish the connection to database
# Open a log file to report all of the errors found during processing
&OpenLogFile();
print LOG_FILE "loadDNE.pl starting with arguments: @ARGV \n";
print LOG_FILE "DSN: $DSN\n";
print LOG_FILE "user: $user\n";
print LOG_FILE "password: $password\n";
print LOG_FILE "state: $selectedState\n";
print LOG_FILE "noUpdates: $noUpdates\n";
print LOG_FILE "reportFile: $reportFile\n";
print LOG_FILE "sysDebug: $sysDebug\n";

# We must set the character set that is being used, otherwise the portuguese
# characters will be incorrectly translated by the DBI functions
$ENV{NLS_LANG}  = "american_america.WE8ISO8859P1";
$ENV{ORA_NLS}   = "$ENV{ORACLE_HOME}/ocommon/nls/admin/data";

$connection = DBI->connect( $DSN, $user, $password, $opts )
        or die qq/could not establish the connection to data source\n/;
print LOG_FILE "DB login successful\n";

#   if necessary, create the report file

if( '' ne $reportFile ) {
    open REPORT, "> $reportFile"
            or die qq/couldn\'t create "$reportFile" report file\n/;

    print REPORT "loadDNE starting with arguments: @ARGV \n";
    print REPORT "DSN: $DSN\n\n";
}

#   load necessary database reference data

$brazil = pais::queryByNome( $connection, 'BRASIL' );
&loadUnidade_Federacao();

my  @key;

print STDOUT qq/loading abbreviation flat files ...\n/;

%hash_abrevLocalidade = stringUtility::loadAbbreviationHash( 'abrevLocalidade.txt' );
@key = keys( %hash_abrevLocalidade );

%hash_abrevLogradouro = stringUtility::loadAbbreviationHash( 'abrevLogradouro.txt' );
@key = keys( %hash_abrevLogradouro );

printf STDOUT qq/\t%d lines loaded\n/, $#key + 1;

%hash_abrevBairro = stringUtility::loadAbbreviationHash( 'abrevBairro.txt' );
@key = keys( %hash_abrevBairro );

printf STDOUT qq/\t%d lines loaded\n/, $#key + 1;

#   validate state argument

if( '' ne $selectedState
        && ! exists( $hash_unidade_federacao{ $selectedState } ) ) {
    die qq/invalid selected unidade_federacao $selectedState\n/;
}

#   import all DNE flat files

my  @now = localtime( time() );

$today = sprintf( qq/to_date( '%02d-%02d-%04d', 'MM-DD-YYYY' )/
        , $now[ 4 ] + 1, $now[ 3 ], $now[ 5 ] + 1900 );

my $sysdate = $now[ 5 ] + 1900 . $now[ 4 ] + 1 . $now[ 3 ] . $now[ 2 ] . $now[1] . $now[0];

my $bkpath = "$inputPath/BACKUP-$sysdate";
mkdir $bkpath unless ( -d $bkpath );

&importCentroDistribuicao( "$inputPath/DNE_GU_UNIDADES_OPERACIONAIS.TXT", 'D' );
&importLocalidade( "$inputPath/DNE_GU_LOCALIDADES.TXT" );
&importBairro( "$inputPath/DNE_GU_BAIRROS.TXT" );
&importTipo_logradouro( "$inputPath/DNE_GU_TIPOS_LOGRADOURO.TXT" );
&importTitulo_logradouro( "$inputPath/DNE_GU_TITULOS_PATENTES.TXT" );
&importLogradouroUF();
&importGrandesUsuarios( "$inputPath/DNE_GU_GRANDES_USUARIOS.TXT" );
&importCentroDistribuicao( "$inputPath/DNE_GU_UNIDADES_OPERACIONAIS.TXT", 'E' );
&importCaixaPostalComunitaria( "$inputPath/DNE_GU_CAIXAS_POSTAIS_COMUNITA.TXT" );

rename("$inputPath/DNE_GU_UNIDADES_OPERACIONAIS.TXT", "$bkpath/DNE_GU_UNIDADES_OPERACIONAIS-$sysdate.TXT");
rename("$inputPath/DNE_GU_LOCALIDADES.TXT", "$bkpath/DNE_GU_LOCALIDADES-$sysdate.TXT");
rename("$inputPath/DNE_GU_BAIRROS.TXT", "$bkpath/DNE_GU_BAIRROS-$sysdate.TXT");
rename("$inputPath/DNE_GU_TIPOS_LOGRADOURO.TXT", "$bkpath/DNE_GU_TIPOS_LOGRADOURO-$sysdate.TXT");
rename("$inputPath/DNE_GU_TITULOS_PATENTES.TXT", "$bkpath/DNE_GU_TITULOS_PATENTES-$sysdate.TXT");
&bkpLogradouros();
rename("$inputPath/DNE_GU_GRANDES_USUARIOS.TXT", "$bkpath/DNE_GU_GRANDES_USUARIOS-$sysdate.TXT");
rename("$inputPath/DNE_GU_CAIXAS_POSTAIS_COMUNITA.TXT", "$bkpath/DNE_GU_CAIXAS_POSTAIS_COMUNITA-$sysdate.TXT");

&processoCarga();

#   if necessary, close the report file

if( '' ne $reportFile ) {
    print REPORT "\nloadDNE finished";
    close REPORT;
}

#   close the connection to database

$connection->disconnect() or die qq/could not finish the connection to data source\n/;;
print LOG_FILE "DB logoff successful\n";

&CloseLogFile();


################################################################################
# Open log file in order to report all of the erros found during processing
################################################################################
sub OpenLogFile()
{
    # Determine the current date and time
    my $dt = &getCurrentDate();
    my $tm = &getCurrentTime();

    my $log_file_name = &getScriptName()."_".$dt."_".$tm.".log";
    print "log : $log_file_name\n";
    open(LOG_FILE,">$log_file_name") or die "Failed to create file $log_file_name\n";

    # Log the date/time of the beginning of processing
    print LOG_FILE "Started processing on $dt $tm\n";
}

################################################################################
# Close log file
################################################################################
sub CloseLogFile()
{
    # Log the date/time of the end of processing
    my $dt = &getCurrentDate();
    my $tm = &getCurrentTime();
    print LOG_FILE "Finished processing on $dt $tm\n";

    # Close the log file
    close(LOG_FILE) || print "Closing log file failed\n";
}

##############################################################################

sub getCurrentDate
{
    my ($sec, $min, $hour, $mday, $month, $year, $wday, $yday, $isDst) = localtime;

    return (sprintf("%4d%02d%02d", $year+1900, $month+1, $mday));
}

##############################################################################

sub getCurrentTime
{
    my ($sec, $min, $hour, $mday, $month, $year, $wday, $yday, $isDst) = localtime;

    return (sprintf("%02d%02d%02d00", $hour, $min, $sec));
}

##############################################################################

sub getScriptName
{
    my $name = $0;
    $name =~ s/\.pl$//;
    $name =~ s/^.*\///;
    return $name;
}

###############################################################################

sub processoCarga
{
	my $sth = $connection->prepare( "INSERT INTO ADM_PROJECT.PROCESSO_CARGA (USUARIO, DAT_CARGA) VALUES ('$importUser', SYSDATE)" ) or die "Can't prepare SQL statement: $DBI::errstr\n";
	$sth->execute or die "Can't execute SQL statement: $DBI::errstr\n";
}

sub bkpLogradouros
{   
   my  $UF;

    foreach $UF ( keys %hash_unidade_federacao )
	{
        my $flatFileName;
		my $bkpFileName;
        $flatFileName = sprintf '%s/DNE_GU_%s_LOGRADOUROS.TXT', $inputPath, $UF;
		$bkpFileName = sprintf "%s/DNE_GU_%s_LOGRADOUROS-$sysdate.TXT", $bkpath, $UF;
		rename($flatFileName, $bkpFileName);
	}
}


