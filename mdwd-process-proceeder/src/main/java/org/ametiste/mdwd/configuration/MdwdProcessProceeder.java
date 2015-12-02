package org.ametiste.mdwd.configuration;

import org.ametiste.mdwd.core.ContextKeeper;
import org.ametiste.mdwd.sleuth.ProcessProceederContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by ametiste on 8/20/15.
 */
@Configuration
@Import(MdwdBundleConfiguration.class)
public class MdwdProcessProceeder {

    @Bean
    public ContextKeeper processContext() {
        return new ProcessProceederContext();
    }
}
