package com.dph.mdwd.reports;



import org.ametiste.mdwd.core.ContextKeeper;
import org.ametiste.sns.client.creators.injectors.EnvironmentInjector;
import org.ametiste.sns.client.creators.injectors.model.EnvironmentData;

import java.util.UUID;

/**
 * Created by ametiste on 8/20/15.
 */
public class ProcessOperationEnvironment implements EnvironmentData {

    private ContextKeeper context;

    public ProcessOperationEnvironment(ContextKeeper context) {

        this.context = context;
    }

    @Override
    public void inject(EnvironmentInjector injector) {
        if(context.isProcess()){
            injector.injectContextEntry("processId",context.getProcessId());
        }
        UUID opId = context.getOperationId();
        if(opId!=null) {
            //should not be a situation like this, but just in case this system
            // used in environment where operation interceptor isnt added, no report fail suppose to happen
            injector.injectContextEntry("operationId", opId);
        }

    }
}
