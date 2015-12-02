package org.ametiste.mdwd.sleuth;

import org.springframework.cloud.sleuth.TraceContextHolder;

/**
 * Since sleuth can provide information about span only in boundaries of same thread, the check is required to
 * avoid NPE and provide more sane information in wrong environment configuration
 * @author ametiste
 * @since 0.1.0
 */
public class ContextVerifier {

    /**
     * If current span is null, service is in wrong context, and IllegalStateException is thrown
     */
    public static void assertWithinContext() {
        if(TraceContextHolder.getCurrentSpan() ==null) {
            throw new IllegalStateException("Request out of context boundaries, check configuration");
        }
    }
}
