package ControleAcces;

/**
 * Interface definition : Autorisation
 * 
 * @author OpenORB Compiler
 */
public class _AutorisationStub extends org.omg.CORBA.portable.ObjectImpl
        implements Autorisation
{
    static final String[] _ids_list =
    {
        "IDL:ControleAcces/Autorisation:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = ControleAcces.AutorisationOperations.class;

    /**
     * Operation ajoutAutorisation
     */
    public void ajoutAutorisation(ControleAcces.Personne personne, String zone, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("ajoutAutorisation",true);
                    ControleAcces.PersonneHelper.write(_output,personne);
                    ControleAcces.NomZoneHelper.write(_output,zone);
                    ControleAcces.APIPriveHelper.write(_output,apiPrive);
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
                    if (_exception_id.equals(ControleAcces.PersonneInconnueHelper.id()))
                    {
                        throw ControleAcces.PersonneInconnueHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(ControleAcces.APIIncorrecteHelper.id()))
                    {
                        throw ControleAcces.APIIncorrecteHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("ajoutAutorisation",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AutorisationOperations _self = (ControleAcces.AutorisationOperations) _so.servant;
                try
                {
                    _self.ajoutAutorisation( personne,  zone,  apiPrive);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation modificationAutorisation
     */
    public void modificationAutorisation(ControleAcces.Personne personne, String nouvelleZone, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("modificationAutorisation",true);
                    ControleAcces.PersonneHelper.write(_output,personne);
                    ControleAcces.NomZoneHelper.write(_output,nouvelleZone);
                    ControleAcces.APIPriveHelper.write(_output,apiPrive);
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
                    if (_exception_id.equals(ControleAcces.PersonneInconnueHelper.id()))
                    {
                        throw ControleAcces.PersonneInconnueHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(ControleAcces.APIIncorrecteHelper.id()))
                    {
                        throw ControleAcces.APIIncorrecteHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("modificationAutorisation",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AutorisationOperations _self = (ControleAcces.AutorisationOperations) _so.servant;
                try
                {
                    _self.modificationAutorisation( personne,  nouvelleZone,  apiPrive);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation supprimerAutorisation
     */
    public void supprimerAutorisation(ControleAcces.Personne personne, String zone, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("supprimerAutorisation",true);
                    ControleAcces.PersonneHelper.write(_output,personne);
                    ControleAcces.NomZoneHelper.write(_output,zone);
                    ControleAcces.APIPriveHelper.write(_output,apiPrive);
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
                    if (_exception_id.equals(ControleAcces.PersonneInconnueHelper.id()))
                    {
                        throw ControleAcces.PersonneInconnueHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(ControleAcces.APIIncorrecteHelper.id()))
                    {
                        throw ControleAcces.APIIncorrecteHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("supprimerAutorisation",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AutorisationOperations _self = (ControleAcces.AutorisationOperations) _so.servant;
                try
                {
                    _self.supprimerAutorisation( personne,  zone,  apiPrive);
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
