package ControleAcces;

/**
 * Interface definition : Logs
 * 
 * @author OpenORB Compiler
 */
public class _LogsStub extends org.omg.CORBA.portable.ObjectImpl
        implements Logs
{
    static final String[] _ids_list =
    {
        "IDL:ControleAcces/Logs:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = ControleAcces.LogsOperations.class;

    /**
     * Operation demandeConsultationLog
     */
    public String demandeConsultationLog(int date, String apiPrive)
        throws ControleAcces.AucunLogDisponible
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
                    if (_exception_id.equals(ControleAcces.AucunLogDisponibleHelper.id()))
                    {
                        throw ControleAcces.AucunLogDisponibleHelper.read(_exception.getInputStream());
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
                ControleAcces.LogsOperations _self = (ControleAcces.LogsOperations) _so.servant;
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
    public void ecrireLog(String typeLog, String descriptionLog)
        throws ControleAcces.EcritureImpossible
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("ecrireLog",false);
                    ControleAcces.TypeLogHelper.write(_output,typeLog);
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
                    if (_exception_id.equals(ControleAcces.EcritureImpossibleHelper.id()))
                    {
                        throw ControleAcces.EcritureImpossibleHelper.read(_exception.getInputStream());
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
                ControleAcces.LogsOperations _self = (ControleAcces.LogsOperations) _so.servant;
                try
                {
                    _self.ecrireLog( typeLog,  descriptionLog);
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
