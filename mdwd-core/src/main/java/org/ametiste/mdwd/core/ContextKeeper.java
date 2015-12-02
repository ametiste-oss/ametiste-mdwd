package org.ametiste.mdwd.core;

import java.util.UUID;

/**
 * Defines whether current service call is part of process (either started by same service or proceeded)
 * or its separate operation
 * Logic of starting and proceeding processes and operations is defined by implementations
 * @author ametiste
 * @since 0.1.0
 */
public interface ContextKeeper {

    /**
     *
     * @return id of current started operation or null if operation wasnt started
     */
    UUID getOperationId();

    /**
     *
     * @return true if operation is part of process started, false if just separate operation
     */
    boolean isProcess();

    /**
     *
     * @return id of current started process, or null if process wasnt started (possible work case with independent operation
     */
    UUID getProcessId();

    /**
     * Instruction to continue already started process of complex operations, usually applied on second operation and next ones
     * @param processId id of already started earlier process
     */
    void continueProcess(UUID processId);

    /**
     * Indication to context keeper about start of new process. Applied only to operations that logically supposed to serve as process initiator
     * @param name name of starting process
     */
    void startProcess(String name);

    /**
     *  Indication to context keeper about start of new operation. Applied on any operation within process or independent
     * @param name name of operation
     */
     void startOperation(String name);

}
