import CoordinatorResourceCommunication.CoordinatorInterfaceHelper;
import ProcessResourceCommunication.ProcessInterface;
import ProcessResourceCommunication.ProcessInterfaceHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yurir on 27/01/2017.
 */
public class CorbaManager {

    String processUsingResource;
    List<String> processQueue;
    UICommunication listener;
    String [] args;
    ORB orb;

    public CorbaManager(UICommunication listener, String [] args){
        this.processQueue = new ArrayList<>();
        this.listener = listener;
        this.args = args;
    }

    public void initCorba(){
        try{
            orb = ORB.init(args,null);
            org.omg.CORBA.Object objPoa = orb.resolve_initial_references("RootPOA");
            POA rootPOA = POAHelper.narrow(objPoa);
            org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");
            NamingContext naming = NamingContextHelper.narrow(obj);
            CoordinatorInterfaceImpl coordinatorInterface = new CoordinatorInterfaceImpl(listener);
            org.omg.CORBA.Object   objRef =	 rootPOA.servant_to_reference(coordinatorInterface);
            NameComponent[] name = {new NameComponent("Coordinator","Exemplo")};
            naming.rebind(name,objRef);
            rootPOA.the_POAManager().activate();
            listener.onStatusChanged("Coordenador pronto");
            //orb.run();

        }catch (Exception ex){
            System.out.println("Erro");
            ex.printStackTrace();
        }
    }

    public void requestResource(String pId){
        if (isResourceFree()){
            sendResourceConfirmation(pId);
            processUsingResource = pId;
            listener.onStatusChanged("Recurso sendo usado pelo processo " + pId);
        }
        else{
            processQueue.add(pId);
            listener.onQueueChanged(processQueue);
        }
    }

    public void releaseResource() {
        processUsingResource = null;
        listener.onStatusChanged("Recurso livre");
        if (processQueue.size() > 0) {
            sendResourceConfirmation(processQueue.get(0));
            processUsingResource = processQueue.get(0);
            listener.onStatusChanged("Recurso sendo usado pelo processo " + processUsingResource);
            processQueue.remove(0);
            listener.onQueueChanged(processQueue);
        }
    }

    public void sendResourceConfirmation(String pId){
        try{
            org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");
            NamingContext naming = NamingContextHelper.narrow(obj);
            NameComponent[] name = {new NameComponent("Processo" + pId,"Exemplo")};
            org.omg.CORBA.Object objRef =  naming.resolve(name);
            ProcessInterface process = ProcessInterfaceHelper.narrow(objRef);
            process.concedeResource();

        }catch (Exception e) {
            System.out.println("Outro Erro : " + e);
            e.printStackTrace(System.out);
        }
    }

    public Boolean isResourceFree(){
        if (processUsingResource != null){
            return false;
        }
        return true;
    }
}


