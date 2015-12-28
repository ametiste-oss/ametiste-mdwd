package com.dph.mdwd.reports;

import org.ametiste.mdwd.core.ContextKeeper;
import org.ametiste.sns.client.creators.injectors.EnvironmentInjector;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.*;

/**
 * Created by ametiste on 8/20/15.
 */
public class ProcessOperationEnvironmentTest {

    private ProcessOperationEnvironment environment;

    @Mock
    private ContextKeeper context;

    @Mock
    private EnvironmentInjector injector;

    private UUID operationId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
    private UUID processId =  UUID.fromString("550e8400-e29b-41d4-a716-446655440001");

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        environment = new ProcessOperationEnvironment(context);
    }

    @Test
    public void testInjectWithNoProcess() throws Exception {
        when(context.isProcess()).thenReturn(false);
        when(context.getOperationId()).thenReturn(operationId);
        environment.inject(injector);
        verify(context, times(1)).isProcess();
        verify(context, times(0)).getProcessId();
        verify(context, times(1)).getOperationId();

        verify(injector, times(0)).injectContextEntry(eq("processId"), any(UUID.class));
        verify(injector, times(1)).injectContextEntry("operationId", operationId);
    }

    @Test
    public void testInjectWithProcess() throws Exception {
        when(context.isProcess()).thenReturn(true);
        when(context.getOperationId()).thenReturn(operationId);
        when(context.getProcessId()).thenReturn(processId);
        environment.inject(injector);
        verify(context, times(1)).isProcess();
        verify(context, times(1)).getProcessId();
        verify(context, times(1)).getOperationId();

        verify(injector, times(1)).injectContextEntry("processId", processId);
        verify(injector, times(1)).injectContextEntry("operationId", operationId);
    }

    @Test
    public void testInjectWithNoProcessNoOperation() throws Exception {
        when(context.isProcess()).thenReturn(false);
        when(context.getOperationId()).thenReturn(null);
        environment.inject(injector);
        verify(context, times(1)).isProcess();
        verify(context, times(0)).getProcessId();
        verify(context, times(1)).getOperationId();

        verify(injector, times(0)).injectContextEntry(eq("processId"), any(UUID.class));
        verify(injector, times(0)).injectContextEntry(eq("operationId"), any(UUID.class));
    }
}