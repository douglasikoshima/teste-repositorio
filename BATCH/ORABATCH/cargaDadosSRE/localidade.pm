#ident @(#) P2K: src/cacs/tools/loadDNE/localidade.pm 77.2 11/02/02 07:47:36
# (c) 2007,2008,2009,2011, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   localidade.pm  -  03/23/2004 by aldebaran perseke
#
#   Brazil's address localidade class
################################################################################

package localidade;

use     strict;

use     unidade_federacao;

################################################################################
#   constants declaration
################################################################################

use constant SUCCESS => 0;
use constant ERROR   => 1;

################################################################################
#   construct a new localidade object
################################################################################

sub new {
    my  $self = {};

    $self->{ COD_LOCALIDADE }          = undef;
    $self->{ DSC_LOCALIDADE }          = undef;
    $self->{ DSC_ABREV_LOCALIDADE }    = undef;
    $self->{ COD_LOCALIDADE_PRINC }    = undef;
    $self->{ COD_UF }                  = undef;
    $self->{ COD_AREA_TARIFARIA }      = undef;
    $self->{ NUM_COD_NAC_LOCALIDADE }  = undef;
    $self->{ SGL_COD_NAC_LOCALIDADE }  = undef;
    $self->{ HOR_FUSO_HORARIO }        = undef;
    $self->{ DAT_ATUALIZACAO }         = undef;
    $self->{ USUARIO } = undef;
    $self->{ NUM_COD_IBGE }            = undef;
    $self->{ VER_NBR }                 = undef;
    $self->{ DAT_CARGA }         	   = undef;

    bless( $self );

    return $self;
}

################################################################################
#   set/get COD_LOCALIDADE attribute
################################################################################

sub codigo {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_LOCALIDADE } = shift;
    }

    $self->{ COD_LOCALIDADE };
}

################################################################################
#   set/get DSC_LOCALIDADE attribute
################################################################################

sub descricao {
    my  $self = shift;

    if( @_ ) {
        $self->{ DSC_LOCALIDADE } = substr( shift, 0, 50 );
    }

    $self->{ DSC_LOCALIDADE };
}

################################################################################
#   set/get DSC_ABREV_LOCALIDADE attribute
################################################################################

sub descricaoAbreviada {
    my  $self = shift;

    if( @_ ) {
        $self->{ DSC_ABREV_LOCALIDADE } = substr( shift, 0, 20 );
    }

    $self->{ DSC_ABREV_LOCALIDADE };
}

################################################################################
#   set/get COD_LOCALIDADE_PRINC attribute
################################################################################

sub codigoLocalidadePrincipal {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_LOCALIDADE_PRINC } = shift;
    }

    $self->{ COD_LOCALIDADE_PRINC };
}

################################################################################
#   set/get COD_UF attribute
################################################################################

sub UF {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_UF } = shift;
    }

    $self->{ COD_UF };
}

################################################################################
#   set/get COD_AREA_TARIFARIA attribute
################################################################################

sub codigoAreaTarifaria {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_AREA_TARIFARIA } = shift;
    }

    $self->{ COD_AREA_TARIFARIA };
}

################################################################################
#   set/get NUM_COD_NAC_LOCALIDADE attribute
################################################################################

sub codigoNacionalLocalidade {
    my  $self = shift;

    if( @_ ) {
        $self->{ NUM_COD_NAC_LOCALIDADE } = substr( shift, 0, 8 );
    }

    $self->{ NUM_COD_NAC_LOCALIDADE };
}

################################################################################
#   set/get SGL_COD_NAC_LOCALIDADE attribute
################################################################################

sub siglaNacionalLocalidade {
    my  $self = shift;

    if( @_ ) {
        $self->{ SGL_COD_NAC_LOCALIDADE } = substr( shift, 0, 10 );
    }

    $self->{ SGL_COD_NAC_LOCALIDADE };
}

################################################################################
#   set/get HOR_FUSO_HORARIO attribute
################################################################################

sub fusoHorario {
    my  $self = shift;

    if( @_ ) {
        $self->{ HOR_FUSO_HORARIO } = shift;
    }

    $self->{ HOR_FUSO_HORARIO };
}

################################################################################
#   set/get DAT_ATUALIZACAO attribute
################################################################################

sub dataAtualizacao {
    my  $self = shift;

    if( @_ ) {
        $self->{ DAT_ATUALIZACAO } = shift;
    }

    $self->{ DAT_ATUALIZACAO };
}

################################################################################
#   set/get DAT_CARGA attribute
################################################################################

sub dataCarga {
    my  $self = shift;

    if( @_ ) {
        $self->{ DAT_CARGA } = shift;
    }

    $self->{ DAT_CARGA };
}
################################################################################
#   set/get USUARIO attribute
################################################################################

sub usuarioAtualizacao {
    my  $self = shift;

    if( @_ ) {
        $self->{ USUARIO } = shift;
    }

    $self->{ USUARIO };
}

################################################################################
#   set/get NUM_COD_IBGE attribute
################################################################################

sub codigoIBGE {
    my  $self = shift;

    if( @_ ) {
        $self->{ NUM_COD_IBGE } = substr( shift, 0, 7 );
    }

    $self->{ NUM_COD_IBGE };
}

################################################################################
#   set/get VER_NBR attribute
################################################################################

sub verNbr {
    my  $self = shift;

    if( @_ ) {
        $self->{ VER_NBR } = shift;
    }

    $self->{ VER_NBR };
}

################################################################################
#   transform an object in a printable string
################################################################################

sub to_string {
    my  $self = shift;

    sprintf qq/codigo: %d\ndescricao: '%s'\nabreviacao: '%s'\nloc principal: %d\nUF: %d\narea tarifaria: %d\nnum nacional: %d\nsigla nacional: '%s'\nfuso: %d\ndata: %s\nusuario: %d\ncodigo IBGE: %d/
            , ( defined $self->{ COD_LOCALIDADE } )?$self->{ COD_LOCALIDADE }:0
            , ( defined $self->{ DSC_LOCALIDADE } )?$self->{ DSC_LOCALIDADE }:''
            , ( defined $self->{ DSC_ABREV_LOCALIDADE } )?$self->{ DSC_ABREV_LOCALIDADE }:''
            , ( defined $self->{ COD_LOCALIDADE_PRINC } )?$self->{ COD_LOCALIDADE_PRINC }:0
            , ( defined $self->{ COD_UF } )?$self->{ COD_UF }:0
            , ( defined $self->{ COD_AREA_TARIFARIA } )?$self->{ COD_AREA_TARIFARIA }:0
            , ( defined $self->{ NUM_COD_NAC_LOCALIDADE } )?$self->{ NUM_COD_NAC_LOCALIDADE }:0
            , ( defined $self->{ SGL_COD_NAC_LOCALIDADE } )?$self->{ SGL_COD_NAC_LOCALIDADE }:''
            , ( defined $self->{ HOR_FUSO_HORARIO } )?$self->{ HOR_FUSO_HORARIO }:0
            , ( defined $self->{ DAT_ATUALIZACAO } )?$self->{ DAT_ATUALIZACAO }:0
            , ( defined $self->{ USUARIO } )?$self->{ USUARIO }:''
            , ( defined $self->{ NUM_COD_IBGE } )?$self->{ NUM_COD_IBGE }:0
            , ( defined $self->{ VER_NBR } )?$self->{ VER_NBR }:0
            , ( defined $self->{ DAT_CARGA } )?$self->{ DAT_CARGA }:0;
}

################################################################################
#   persist the object in database
################################################################################

sub persist {
    my  $self = shift;
    my  $connection = shift;

#   nullable fields

    my  $COD_LOCALIDADE_PRINC;
    my  $HOR_FUSO_HORARIO;

    if( defined $self->{ COD_LOCALIDADE_PRINC } ) {
        $COD_LOCALIDADE_PRINC = $self->{ COD_LOCALIDADE_PRINC };
    } else {
        $COD_LOCALIDADE_PRINC = undef;
    }

    if( defined $self->{ HOR_FUSO_HORARIO } ) {
        $HOR_FUSO_HORARIO = $self->{ HOR_FUSO_HORARIO };
    } else {
        $HOR_FUSO_HORARIO = undef;
    }

#   if there's no codigo, insert it

    if( ! defined $self->{ COD_LOCALIDADE }
            || 0 == $self->{ COD_LOCALIDADE } ) {

        my  $SQLQuery = <<SQL_QUERY;
insert into
    LOCALIDADE
( COD_LOCALIDADE
    , DSC_LOCALIDADE
    , DSC_ABREV_LOCALIDADE
    , COD_LOCALIDADE_PRINC
    , COD_UF
    , COD_AREA_TARIFARIA
    , NUM_COD_NAC_LOCALIDADE
    , SGL_COD_NAC_LOCALIDADE
    , HOR_FUSO_HORARIO
    , DAT_ATUALIZACAO
    , USUARIO
    , NUM_COD_IBGE
    , VER_NBR
    , DAT_CARGA )
values
( SEQ_LOCALIDA_01.nextval
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , $self->{ DAT_ATUALIZACAO }
    , ?
    , ?
    , '1'
    , SYSDATE )
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ DSC_LOCALIDADE }
                , $self->{ DSC_ABREV_LOCALIDADE }
                , $COD_LOCALIDADE_PRINC
                , $self->{ COD_UF }
                , $self->{ COD_AREA_TARIFARIA }
                , $self->{ NUM_COD_NAC_LOCALIDADE }
                , $self->{ SGL_COD_NAC_LOCALIDADE }
                , $HOR_FUSO_HORARIO
                , $self->{ USUARIO }
                , $self->{ NUM_COD_IBGE } )
                or return ERROR;

        $SQLQuery = <<SQL_QUERY;
select
    max( COD_LOCALIDADE )
from
    LOCALIDADE
SQL_QUERY
        my  @resultRow;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute()
                or return ERROR;

        if( @resultRow = $recordSet->fetchrow_array() ) {
            $self->{ COD_LOCALIDADE } = $resultRow[ 0 ];
        }

    } else {

        my  $SQLQuery = <<SQL_QUERY;
update
    LOCALIDADE
set
    DSC_ABREV_LOCALIDADE = ?
    , COD_LOCALIDADE_PRINC = ?
    , COD_AREA_TARIFARIA = ?
    , HOR_FUSO_HORARIO = ?
    , DAT_ATUALIZACAO = $self->{ DAT_ATUALIZACAO }
    , USUARIO = ?
    , NUM_COD_IBGE = ?
    , VER_NBR = ?
    , DAT_CARGA = SYSDATE
where
    COD_LOCALIDADE = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ DSC_ABREV_LOCALIDADE }
                , $COD_LOCALIDADE_PRINC
                , $self->{ COD_AREA_TARIFARIA }
                , $HOR_FUSO_HORARIO
                , $self->{ USUARIO }
                , $self->{ NUM_COD_IBGE }
                , ($self->{ VER_NBR })+1 
                , $self->{ COD_LOCALIDADE } )
                or return ERROR;
    }

    SUCCESS;
}

################################################################################
#   get an object from database by codigo
################################################################################

sub queryByCodigo {
    my  $connection = shift;
    my  $COD_LOCALIDADE = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_LOCALIDADE
    , DSC_LOCALIDADE
    , DSC_ABREV_LOCALIDADE
    , COD_LOCALIDADE_PRINC
    , COD_UF
    , COD_AREA_TARIFARIA
    , NUM_COD_NAC_LOCALIDADE
    , SGL_COD_NAC_LOCALIDADE
    , HOR_FUSO_HORARIO
    , DAT_ATUALIZACAO
    , USUARIO
    , NUM_COD_IBGE
    , VER_NBR
    , DAT_CARGA
from
    LOCALIDADE
where
    COD_LOCALIDADE = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $COD_LOCALIDADE )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_LOCALIDADE } = $resultRow[ 0 ];
        $new->{ DSC_LOCALIDADE } = $resultRow[ 1 ];
        $new->{ DSC_ABREV_LOCALIDADE } = $resultRow[ 2 ];
        $new->{ COD_LOCALIDADE_PRINC } = $resultRow[ 3 ];
        $new->{ COD_UF } = $resultRow[ 4 ];
        $new->{ COD_AREA_TARIFARIA } = $resultRow[ 5 ];
        $new->{ NUM_COD_NAC_LOCALIDADE } = $resultRow[ 6 ];
        $new->{ SGL_COD_NAC_LOCALIDADE } = $resultRow[ 7 ];
        $new->{ HOR_FUSO_HORARIO } = $resultRow[ 8 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 9 ];
        $new->{ USUARIO } = $resultRow[ 10 ];
        $new->{ NUM_COD_IBGE } = $resultRow[ 11 ];
        $new->{ VER_NBR } = $resultRow[ 12 ];
        $new->{ DAT_CARGA } = $resultRow[ 13 ];

        $recordSet->finish();
        bless( $new );

        $new;
    }
}

################################################################################
#   get an object from database by descricao + UF
################################################################################

sub queryByDescricaoUF {
    my  $connection = shift;
    my  $DSC_LOCALIDADE = substr( shift, 0, 50 );
    my  $unidade_federacao = shift;

#   the select query

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_LOCALIDADE
    , DSC_LOCALIDADE
    , DSC_ABREV_LOCALIDADE
    , COD_LOCALIDADE_PRINC
    , COD_UF
    , COD_AREA_TARIFARIA
    , NUM_COD_NAC_LOCALIDADE
    , SGL_COD_NAC_LOCALIDADE
    , HOR_FUSO_HORARIO
    , DAT_ATUALIZACAO
    , USUARIO
    , NUM_COD_IBGE
    , VER_NBR
    , DAT_CARGA
from
    LOCALIDADE
where
    DSC_LOCALIDADE = ?
    and COD_UF = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $DSC_LOCALIDADE, $unidade_federacao->codigo() )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_LOCALIDADE } = $resultRow[ 0 ];
        $new->{ DSC_LOCALIDADE } = $resultRow[ 1 ];
        $new->{ DSC_ABREV_LOCALIDADE } = $resultRow[ 2 ];
        $new->{ COD_LOCALIDADE_PRINC } = $resultRow[ 3 ];
        $new->{ COD_UF } = $resultRow[ 4 ];
        $new->{ COD_AREA_TARIFARIA } = $resultRow[ 5 ];
        $new->{ NUM_COD_NAC_LOCALIDADE } = $resultRow[ 6 ];
        $new->{ SGL_COD_NAC_LOCALIDADE } = $resultRow[ 7 ];
        $new->{ HOR_FUSO_HORARIO } = $resultRow[ 8 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 9 ];
        $new->{ USUARIO } = $resultRow[ 10 ];
        $new->{ NUM_COD_IBGE } = $resultRow[ 11 ];
        $new->{ VER_NBR } = $resultRow[ 12 ];
        $new->{ DAT_CARGA } = $resultRow[ 13 ];

        $recordSet->finish();
        bless( $new );

        $new;
    }
}

################################################################################
#   get a hash of objects from database by UF
################################################################################

sub queryByUF {
    my  $connection = shift;
    my  $unidade_federacao = shift;

#   the select query

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_LOCALIDADE
    , DSC_LOCALIDADE
    , DSC_ABREV_LOCALIDADE
    , COD_LOCALIDADE_PRINC
    , COD_UF
    , COD_AREA_TARIFARIA
    , NUM_COD_NAC_LOCALIDADE
    , SGL_COD_NAC_LOCALIDADE
    , HOR_FUSO_HORARIO
    , DAT_ATUALIZACAO
    , USUARIO
    , NUM_COD_IBGE
    , VER_NBR
    , DAT_CARGA
from
    LOCALIDADE
where
    COD_UF = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;
    my  %container;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $unidade_federacao->codigo() )
            or die "error running the query\n$SQLQuery\non database server\n";

    while( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_LOCALIDADE } = $resultRow[ 0 ];
        $new->{ DSC_LOCALIDADE } = $resultRow[ 1 ];
        $new->{ DSC_ABREV_LOCALIDADE } = $resultRow[ 2 ];
        $new->{ COD_LOCALIDADE_PRINC } = $resultRow[ 3 ];
        $new->{ COD_UF } = $resultRow[ 4 ];
        $new->{ COD_AREA_TARIFARIA } = $resultRow[ 5 ];
        $new->{ NUM_COD_NAC_LOCALIDADE } = $resultRow[ 6 ];
        $new->{ SGL_COD_NAC_LOCALIDADE } = $resultRow[ 7 ];
        $new->{ HOR_FUSO_HORARIO } = $resultRow[ 8 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 9 ];
        $new->{ USUARIO } = $resultRow[ 10 ];
        $new->{ NUM_COD_IBGE } = $resultRow[ 11 ];
        $new->{ VER_NBR } = $resultRow[ 12 ];
        $new->{ DAT_CARGA } = $resultRow[ 13 ];

        bless( $new );

        $container{ $new->{ DSC_LOCALIDADE } } = $new;
    }

    $recordSet->finish();

    %container;
}

################################################################################
#   get a hash of objects from database
################################################################################

sub queryAll {
    my  $connection = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_LOCALIDADE
    , DSC_LOCALIDADE
    , DSC_ABREV_LOCALIDADE
    , COD_LOCALIDADE_PRINC
    , COD_UF
    , COD_AREA_TARIFARIA
    , NUM_COD_NAC_LOCALIDADE
    , SGL_COD_NAC_LOCALIDADE
    , HOR_FUSO_HORARIO
    , DAT_ATUALIZACAO
    , USUARIO
    , NUM_COD_IBGE
    , VER_NBR
    , DAT_CARGA
from
    LOCALIDADE
SQL_QUERY
    my  $recordSet;
    my  @resultRow;
    my  %container;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute()
            or die "error running the query\n$SQLQuery\non database server\n";

    while( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_LOCALIDADE } = $resultRow[ 0 ];
        $new->{ DSC_LOCALIDADE } = $resultRow[ 1 ];
        $new->{ DSC_ABREV_LOCALIDADE } = $resultRow[ 2 ];
        $new->{ COD_LOCALIDADE_PRINC } = $resultRow[ 3 ];
        $new->{ COD_UF } = $resultRow[ 4 ];
        $new->{ COD_AREA_TARIFARIA } = $resultRow[ 5 ];
        $new->{ NUM_COD_NAC_LOCALIDADE } = $resultRow[ 6 ];
        $new->{ SGL_COD_NAC_LOCALIDADE } = $resultRow[ 7 ];
        $new->{ HOR_FUSO_HORARIO } = $resultRow[ 8 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 9 ];
        $new->{ USUARIO } = $resultRow[ 10 ];
        $new->{ NUM_COD_IBGE } = $resultRow[ 11 ];
        $new->{ VER_NBR } = $resultRow[ 12 ];
        $new->{ DAT_CARGA } = $resultRow[ 13 ];

        bless( $new );

        $container{ $new->{ COD_LOCALIDADE } } = $new;
    }

    $recordSet->finish();

    %container;
}

################################################################################
#   Check whether LOCALIDADE has relationship
################################################################################
sub hasRelationship {

    my $self       = shift;
    my $connection = shift;
    my $returnCode = 0;

    if( defined $self->{ COD_LOCALIDADE } ) {

        my  $SQLQuery = <<SQL_QUERY;
select
    nvl(count(*), 0)
from
    localidade l
where
       l.cod_localidade = ?
   and (exists (select 1 from cep c
                where c.cod_localidade = l.cod_localidade)
        or
        exists (select 1 from logradouro lo
                where lo.cod_localidade = l.cod_localidade)
        or
        exists (select 1 from localidade lp
                where lp.cod_localidade_princ = l.cod_localidade)
       )
SQL_QUERY

        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_LOCALIDADE } )
            or die "error running the query\n$SQLQuery\non database server\n";

        $returnCode = $recordSet->fetchrow_array();
    }

    $returnCode;
}


################################################################################
#   delete an object from database
################################################################################

sub delete {
    my $self       = shift;
    my $connection = shift;

#   nullable fields

    my $COD_LOCALIDADE;
    my $returnCode        = SUCCESS;

    if( defined $self->{ COD_LOCALIDADE } ) {
        $COD_LOCALIDADE =  $self->{ COD_LOCALIDADE };

        my $SQLQuery = <<SQL_QUERY;
delete from LOCALIDADE
where COD_LOCALIDADE = ?
SQL_QUERY

        my $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_LOCALIDADE } )
                or $returnCode = ERROR;

        if( $recordSet->rows == 0 ) {
            $returnCode = ERROR;
        }

    } else {
        $returnCode = ERROR;
    }

    $returnCode;
}

#   return 1 to module requiring or using it

1;

