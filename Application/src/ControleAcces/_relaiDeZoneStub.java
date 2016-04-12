package ControleAcces;

/**
 * Interface definition : relaiDeZone
 * 
 * @author OpenORB Compiler
 */
public class _relaiDeZoneStub extends org.omg.CORBA.portable.ObjectImpl
        implements relaiDeZone
{
    static final String[] _ids_list =
    {
        "IDL:ControleAcces/relaiDeZone:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = ControleAcces.relaiDeZoneOperations.class;

    /**
     * Operation demandeAcces
     */
    public void demandeAcces(int idPorte, String photo, String empreinte)
        throws ControleAcces.PorteInconnue, ControleAcces.accesRefuse
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("demandeAcces",true);
                    ControleAcces.IdPorteHelper.write(_output,idPorte);
                    ControleAcces.PhotoHelper.write(_output,photo);
                    ControleAcces.EmpreinteHelper.write(_output,empreinte);
                    _input = this._invoke(_output);
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    if (_exception_id.equals(ControleAcces.PorteInconnueHelper.id()))
                    {
                        throw ControleAcces.PorteInconnueHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(ControleAcces.accesRefuseHelper.id()))
                    {
                        throw ControleAcces.accesRefuseHelper.read(_exception.getInputStream());
                    }

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("demandeAcces",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.relaiDeZoneOperations _self = (ControleAcces.relaiDeZoneOperations) _so.servant;
                try
                {
                    _self.demandeAcces( idPorte,  photo,  empreinte);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
