package ControleAcces;

/**
 * Interface definition : Biometrique
 * 
 * @author OpenORB Compiler
 */
public class _BiometriqueStub extends org.omg.CORBA.portable.ObjectImpl
        implements Biometrique
{
    static final String[] _ids_list =
    {
        "IDL:ControleAcces/Biometrique:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = ControleAcces.BiometriqueOperations.class;

    /**
     * Operation verificationEmpreinte
     */
    public boolean verificationEmpreinte(String idEmpreinteCrypte, String empreinte, String apiPublic)
        throws ControleAcces.EmpreinteInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("verificationEmpreinte",true);
                    ControleAcces.IdEmpreinteHelper.write(_output,idEmpreinteCrypte);
                    ControleAcces.EmpreinteHelper.write(_output,empreinte);
                    ControleAcces.APIPublicHelper.write(_output,apiPublic);
                    _input = this._invoke(_output);
                    boolean _arg_ret = _input.read_boolean();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    if (_exception_id.equals(ControleAcces.EmpreinteInconnueHelper.id()))
                    {
                        throw ControleAcces.EmpreinteInconnueHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("verificationEmpreinte",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.BiometriqueOperations _self = (ControleAcces.BiometriqueOperations) _so.servant;
                try
                {
                    return _self.verificationEmpreinte( idEmpreinteCrypte,  empreinte,  apiPublic);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation ajoutEmpreinte
     */
    public String ajoutEmpreinte(String empreinte, String apiPublic)
        throws ControleAcces.EmpreinteDejaExistante, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("ajoutEmpreinte",true);
                    ControleAcces.EmpreinteHelper.write(_output,empreinte);
                    ControleAcces.APIPublicHelper.write(_output,apiPublic);
                    _input = this._invoke(_output);
                    String _arg_ret = ControleAcces.IdEmpreinteHelper.read(_input);
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    if (_exception_id.equals(ControleAcces.EmpreinteDejaExistanteHelper.id()))
                    {
                        throw ControleAcces.EmpreinteDejaExistanteHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("ajoutEmpreinte",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.BiometriqueOperations _self = (ControleAcces.BiometriqueOperations) _so.servant;
                try
                {
                    return _self.ajoutEmpreinte( empreinte,  apiPublic);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation modificationEmpreinte
     */
    public void modificationEmpreinte(String idEmpreinteCrypte, String nouvelleEmpreinte, String apiPublic)
        throws ControleAcces.EmpreinteInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("modificationEmpreinte",true);
                    ControleAcces.IdEmpreinteHelper.write(_output,idEmpreinteCrypte);
                    ControleAcces.EmpreinteHelper.write(_output,nouvelleEmpreinte);
                    ControleAcces.APIPublicHelper.write(_output,apiPublic);
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
                    if (_exception_id.equals(ControleAcces.EmpreinteInconnueHelper.id()))
                    {
                        throw ControleAcces.EmpreinteInconnueHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("modificationEmpreinte",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.BiometriqueOperations _self = (ControleAcces.BiometriqueOperations) _so.servant;
                try
                {
                    _self.modificationEmpreinte( idEmpreinteCrypte,  nouvelleEmpreinte,  apiPublic);
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
     * Operation suppressionEmpreinte
     */
    public void suppressionEmpreinte(String empreinte, String apiPublic)
        throws ControleAcces.EmpreinteInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("suppressionEmpreinte",true);
                    ControleAcces.EmpreinteHelper.write(_output,empreinte);
                    ControleAcces.APIPublicHelper.write(_output,apiPublic);
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
                    if (_exception_id.equals(ControleAcces.EmpreinteInconnueHelper.id()))
                    {
                        throw ControleAcces.EmpreinteInconnueHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("suppressionEmpreinte",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.BiometriqueOperations _self = (ControleAcces.BiometriqueOperations) _so.servant;
                try
                {
                    _self.suppressionEmpreinte( empreinte,  apiPublic);
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
     * Operation identificationBiometrique
     */
    public String identificationBiometrique(String empreinte, String apiPublic)
        throws ControleAcces.EmpreinteInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("identificationBiometrique",true);
                    ControleAcces.EmpreinteHelper.write(_output,empreinte);
                    ControleAcces.APIPublicHelper.write(_output,apiPublic);
                    _input = this._invoke(_output);
                    String _arg_ret = ControleAcces.IdEmpreinteHelper.read(_input);
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    if (_exception_id.equals(ControleAcces.EmpreinteInconnueHelper.id()))
                    {
                        throw ControleAcces.EmpreinteInconnueHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("identificationBiometrique",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.BiometriqueOperations _self = (ControleAcces.BiometriqueOperations) _so.servant;
                try
                {
                    return _self.identificationBiometrique( empreinte,  apiPublic);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
