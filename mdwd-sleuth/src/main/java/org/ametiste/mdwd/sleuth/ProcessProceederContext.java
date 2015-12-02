package org.ametiste.mdwd.sleuth;

import org.ametiste.mdwd.core.ContextKeeper;
import org.springframework.cloud.sleuth.TraceContextHolder;

import java.util.UUID;

/**
 *
 * Context that logically defines service supporting only proceeding of some process, and not
 * possible to start own one.
 *
 * @author ametiste
 * @since 0.1.0
 *
 */
public class ProcessProceederContext implements ContextKeeper {

    /**
     * Operation id is id of service call itself, its always presents and always unique for this service call
     * @return operation id
     */
    @Override
    public UUID getOperationId() {
        ContextVerifier.assertWithinContext();
        return UUID.fromString(TraceContextHolder.getCurrentSpan().getSpanId());
    }

    /**
     * Defines whether this call is part of process or its separate operation
     * @return true if service is called within process started in other service, false otherwise
     */
    @Override
    public boolean isProcess() {
        ContextVerifier.assertWithinContext();
        return !TraceContextHolder.getCurrentSpan().getParents().isEmpty();

    }

    /**
     * Process id is id of process uniting several operations, logically defining some complete execution
     * including several service calls
     * @return id of process if operation is part of process, null otherwise
     */
    @Override
    public UUID getProcessId() {
        ContextVerifier.assertWithinContext();
        if(!isProcess()) {
            return null;
        }
        return UUID.fromString(TraceContextHolder.getCurrentSpan().getTraceId());
    }

    /**
     * Nothing happens here at version 0.1.0 however some additional actions that are required
     * for process continuation, might be added in future
     * @param processId id of already started earlier process
     */
    @Override
    public void continueProcess(UUID processId) {
        //might be some annotations to context added here in future
    }

    /**
     * Nothing happens here at version 0.1.0 however some additional actions that are required
     * for process continuation, might be added in future
     * @param name name of starting process
     */
    @Override
    public void startProcess(String name) {
        //might be some annotations to context added here in future
    }

    /**
     * Nothing happens here at version 0.1.0 however some additional actions that are required
     * for process continuation, might be added in future
     * @param name name of operation
     */
    @Override
    public void startOperation(String name) {
        //might be some annotations to context added here in future
    }
}
