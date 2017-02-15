
class TuxHelperImpl:public TuxHelper
{
    public:
        DOMNode * walkDOMImpl( DOMNode*a , char*b , int*c , int d )
        {
                return walkDOM( a , b , c , d );
        }
};
