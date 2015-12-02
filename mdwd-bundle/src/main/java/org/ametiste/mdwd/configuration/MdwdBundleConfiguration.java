package org.ametiste.mdwd.configuration;


import org.ametiste.mdwd.core.ContextKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.instrument.web.client.TraceRestTemplateInterceptor;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;


@Configuration
public class MdwdBundleConfiguration {

    @Autowired
    private ContextKeeper processContext;

    @Bean(name="outboundProcessInterceptor")
    public ClientHttpRequestInterceptor outboundProcessInterceptor() {
        return new TraceRestTemplateInterceptor();
    }

    @Bean
    public Sampler defaultSampler() {
        return new AlwaysSampler();
    }

//    @Bean(name="processOperationEnvironment")
//    public EnvironmentData processOperationEnvironment() {
//        return new ProcessOperationEnvironment(processContext);
//    }
//TODO after reports



}
