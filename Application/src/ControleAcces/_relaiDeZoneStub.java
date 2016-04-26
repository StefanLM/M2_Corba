package ControleAcces;

/**
 * Interface definition : RelaiDeZone
 * 
 * @author OpenORB Compiler
 */
public class _RelaiDeZoneStub extends org.omg.CORBA.portable.ObjectImpl
        implements RelaiDeZone
{
    static final String[] _ids_list =
    {
        "IDL:ControleAcces/RelaiDeZone:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = ControleAcces.RelaiDeZoneOperations.class;

    /**
     * Operation demandeAcces
     */
    public String demandeAcces(String photo, String empreinte)
        throws ControleAcces.PorteInconnue, ControleAcces.AccesRefuse
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("demandeAcces",true);
                    ControleAcces.PhotoHelper.write(_output,photo);
                    ControleAcces.EmpreinteHelper.write(_output,empreinte);
                    _input = this._invoke(_output);
                    String _arg_ret = _input.read_string();
                    return _arg_ret;
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

                    if (_exception_id.equals(ControleAcces.AccesRefuseHelper.id()))
                    {
                        throw ControleAcces.AccesRefuseHelper.read(_exception.getInputStream());
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
                ControleAcces.RelaiDeZoneOperations _self = (ControleAcces.RelaiDeZoneOperations) _so.servant;
                try
                {
                    return _self.demandeAcces( photo,  empreinte);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
