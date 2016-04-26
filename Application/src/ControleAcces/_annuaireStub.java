package ControleAcces;

/**
 * Interface definition : Annuaire
 * 
 * @author OpenORB Compiler
 */
public class _AnnuaireStub extends org.omg.CORBA.portable.ObjectImpl
        implements Annuaire
{
    static final String[] _ids_list =
    {
        "IDL:ControleAcces/Annuaire:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = ControleAcces.AnnuaireOperations.class;

    /**
     * Operation verificationPhoto
     */
    public String verificationPhoto(String photo, String apiPublic)
        throws ControleAcces.PhotoInexistante, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("verificationPhoto",true);
                    ControleAcces.PhotoHelper.write(_output,photo);
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
                    if (_exception_id.equals(ControleAcces.PhotoInexistanteHelper.id()))
                    {
                        throw ControleAcces.PhotoInexistanteHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("verificationPhoto",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AnnuaireOperations _self = (ControleAcces.AnnuaireOperations) _so.servant;
                try
                {
                    return _self.verificationPhoto( photo,  apiPublic);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation verificationPersonne
     */
    public ControleAcces.Personne verificationPersonne(String nom, String prenom, String photo, String apiPublic)
        throws ControleAcces.PhotoInexistante, ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("verificationPersonne",true);
                    ControleAcces.NomHelper.write(_output,nom);
                    ControleAcces.PrenomHelper.write(_output,prenom);
                    ControleAcces.PhotoHelper.write(_output,photo);
                    ControleAcces.APIPublicHelper.write(_output,apiPublic);
                    _input = this._invoke(_output);
                    ControleAcces.Personne _arg_ret = ControleAcces.PersonneHelper.read(_input);
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    if (_exception_id.equals(ControleAcces.PhotoInexistanteHelper.id()))
                    {
                        throw ControleAcces.PhotoInexistanteHelper.read(_exception.getInputStream());
                    }

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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("verificationPersonne",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AnnuaireOperations _self = (ControleAcces.AnnuaireOperations) _so.servant;
                try
                {
                    return _self.verificationPersonne( nom,  prenom,  photo,  apiPublic);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation authentification
     */
    public String authentification(String login, String password, String apiPublic)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("authentification",true);
                    ControleAcces.LoginHelper.write(_output,login);
                    ControleAcces.PasswordHelper.write(_output,password);
                    ControleAcces.APIPublicHelper.write(_output,apiPublic);
                    _input = this._invoke(_output);
                    String _arg_ret = ControleAcces.APIPriveHelper.read(_input);
                    return _arg_ret;
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("authentification",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AnnuaireOperations _self = (ControleAcces.AnnuaireOperations) _so.servant;
                try
                {
                    return _self.authentification( login,  password,  apiPublic);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation ajoutPersonne
     */
    public void ajoutPersonne(String nom, String prenom, String login, String password, String photo, String apiPrive)
        throws ControleAcces.PersonneDejaExistante, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("ajoutPersonne",true);
                    ControleAcces.NomHelper.write(_output,nom);
                    ControleAcces.PrenomHelper.write(_output,prenom);
                    ControleAcces.LoginHelper.write(_output,login);
                    ControleAcces.PasswordHelper.write(_output,password);
                    ControleAcces.PhotoHelper.write(_output,photo);
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
                    if (_exception_id.equals(ControleAcces.PersonneDejaExistanteHelper.id()))
                    {
                        throw ControleAcces.PersonneDejaExistanteHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("ajoutPersonne",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AnnuaireOperations _self = (ControleAcces.AnnuaireOperations) _so.servant;
                try
                {
                    _self.ajoutPersonne( nom,  prenom,  login,  password,  photo,  apiPrive);
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
     * Operation ajoutPhotoPersonne
     */
    public void ajoutPhotoPersonne(ControleAcces.Personne personne, String photo, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("ajoutPhotoPersonne",true);
                    ControleAcces.PersonneHelper.write(_output,personne);
                    ControleAcces.PhotoHelper.write(_output,photo);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("ajoutPhotoPersonne",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AnnuaireOperations _self = (ControleAcces.AnnuaireOperations) _so.servant;
                try
                {
                    _self.ajoutPhotoPersonne( personne,  photo,  apiPrive);
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
     * Operation modificationEmpreintePersonne
     */
    public void modificationEmpreintePersonne(ControleAcces.Personne personne, String idEmpreinteCrypte, String apiPublic)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("modificationEmpreintePersonne",true);
                    ControleAcces.PersonneHelper.write(_output,personne);
                    ControleAcces.IdEmpreinteHelper.write(_output,idEmpreinteCrypte);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("modificationEmpreintePersonne",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AnnuaireOperations _self = (ControleAcces.AnnuaireOperations) _so.servant;
                try
                {
                    _self.modificationEmpreintePersonne( personne,  idEmpreinteCrypte,  apiPublic);
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
     * Operation modificationPersonne
     */
    public void modificationPersonne(ControleAcces.Personne personne, String nomNew, String prenomNew, String photoNew, String loginNew, String passwordNew, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("modificationPersonne",true);
                    ControleAcces.PersonneHelper.write(_output,personne);
                    ControleAcces.NomHelper.write(_output,nomNew);
                    ControleAcces.PrenomHelper.write(_output,prenomNew);
                    ControleAcces.PhotoHelper.write(_output,photoNew);
                    ControleAcces.LoginHelper.write(_output,loginNew);
                    ControleAcces.PasswordHelper.write(_output,passwordNew);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("modificationPersonne",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AnnuaireOperations _self = (ControleAcces.AnnuaireOperations) _so.servant;
                try
                {
                    _self.modificationPersonne( personne,  nomNew,  prenomNew,  photoNew,  loginNew,  passwordNew,  apiPrive);
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
     * Operation supprimerPersonne
     */
    public void supprimerPersonne(ControleAcces.Personne personne, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("supprimerPersonne",true);
                    ControleAcces.PersonneHelper.write(_output,personne);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("supprimerPersonne",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AnnuaireOperations _self = (ControleAcces.AnnuaireOperations) _so.servant;
                try
                {
                    _self.supprimerPersonne( personne,  apiPrive);
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
     * Operation consultationPersonne
     */
    public ControleAcces.Personne consultationPersonne(String nom, String prenom, String apiPrive)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("consultationPersonne",true);
                    ControleAcces.NomHelper.write(_output,nom);
                    ControleAcces.PrenomHelper.write(_output,prenom);
                    ControleAcces.APIPriveHelper.write(_output,apiPrive);
                    _input = this._invoke(_output);
                    ControleAcces.Personne _arg_ret = ControleAcces.PersonneHelper.read(_input);
                    return _arg_ret;
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("consultationPersonne",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AnnuaireOperations _self = (ControleAcces.AnnuaireOperations) _so.servant;
                try
                {
                    return _self.consultationPersonne( nom,  prenom,  apiPrive);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation consultationPersonneBiometrique
     */
    public ControleAcces.Personne consultationPersonneBiometrique(String idEmpreinteCrypte, String apiPublic)
        throws ControleAcces.PersonneInconnue, ControleAcces.APIIncorrecte
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("consultationPersonneBiometrique",true);
                    ControleAcces.IdEmpreinteHelper.write(_output,idEmpreinteCrypte);
                    ControleAcces.APIPublicHelper.write(_output,apiPublic);
                    _input = this._invoke(_output);
                    ControleAcces.Personne _arg_ret = ControleAcces.PersonneHelper.read(_input);
                    return _arg_ret;
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("consultationPersonneBiometrique",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AnnuaireOperations _self = (ControleAcces.AnnuaireOperations) _so.servant;
                try
                {
                    return _self.consultationPersonneBiometrique( idEmpreinteCrypte,  apiPublic);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation demandeSuppressionEmpreinte
     */
    public String[] demandeSuppressionEmpreinte(String apiPublic)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("demandeSuppressionEmpreinte",true);
                    ControleAcces.APIPublicHelper.write(_output,apiPublic);
                    _input = this._invoke(_output);
                    String[] _arg_ret = ControleAcces.SequenceIdEmpreinteHelper.read(_input);
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("demandeSuppressionEmpreinte",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.AnnuaireOperations _self = (ControleAcces.AnnuaireOperations) _so.servant;
                try
                {
                    return _self.demandeSuppressionEmpreinte( apiPublic);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
