package org.ametiste.mdwd.configuration;


import org.ametiste.mdwd.core.ContextKeeper;
import org.ametiste.mdwd.sleuth.ProcessStarterContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MdwdBundleConfiguration.class)
public class MdwdProcessStarter {


    @Bean
    public ContextKeeper processContext() {
        return new ProcessStarterContext();
    }
}
