package ControleAcces;

/**
 * Interface definition : logs
 * 
 * @author OpenORB Compiler
 */
public class _logsStub extends org.omg.CORBA.portable.ObjectImpl
        implements logs
{
    static final String[] _ids_list =
    {
        "IDL:ControleAcces/logs:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = ControleAcces.logsOperations.class;

    /**
     * Operation demandeConsultationLog
     */
    public String demandeConsultationLog(int date, String apiPrive)
        throws ControleAcces.aucunLogDisponible
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("demandeConsultationLog",true);
                    ControleAcces.DateHelper.write(_output,date);
                    ControleAcces.APIPriveHelper.write(_output,apiPrive);
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
                    if (_exception_id.equals(ControleAcces.aucunLogDisponibleHelper.id()))
                    {
                        throw ControleAcces.aucunLogDisponibleHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("demandeConsultationLog",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.logsOperations _self = (ControleAcces.logsOperations) _so.servant;
                try
                {
                    return _self.demandeConsultationLog( date,  apiPrive);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation ecrireLog
     */
    public void ecrireLog(int idPersonne, String nom, String prenom, String acces, String zone, String descriptionLog)
        throws ControleAcces.ecritureImpossible, ControleAcces.zoneInconnue, ControleAcces.personneInconnue
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("ecrireLog",false);
                    ControleAcces.IdPersonneHelper.write(_output,idPersonne);
                    ControleAcces.NomHelper.write(_output,nom);
                    ControleAcces.PrenomHelper.write(_output,prenom);
                    ControleAcces.AccesHelper.write(_output,acces);
                    ControleAcces.NomZoneHelper.write(_output,zone);
                    ControleAcces.DescriptionLogHelper.write(_output,descriptionLog);
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
                    if (_exception_id.equals(ControleAcces.ecritureImpossibleHelper.id()))
                    {
                        throw ControleAcces.ecritureImpossibleHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(ControleAcces.zoneInconnueHelper.id()))
                    {
                        throw ControleAcces.zoneInconnueHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(ControleAcces.personneInconnueHelper.id()))
                    {
                        throw ControleAcces.personneInconnueHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("ecrireLog",_opsClass);
                if (_so == null)
                   continue;
                ControleAcces.logsOperations _self = (ControleAcces.logsOperations) _so.servant;
                try
                {
                    _self.ecrireLog( idPersonne,  nom,  prenom,  acces,  zone,  descriptionLog);
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
