package org.onekr.trial.datarest;

import org.onekr.trial.datarest.entity.Post;
import org.onekr.trial.datarest.entity.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.Entity;
import java.util.Set;

/**
 * @author billy-work
 */
@Component
public class MyRestMvcConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);

        ExposureConfiguration exposureConfiguration = config.getExposureConfiguration();
        exposureConfiguration
                .disablePutForCreation()
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.DELETE));

        // 自动批量设置
        var provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        final Set<BeanDefinition> beans = provider.findCandidateComponents("org.onekr.trial.datarest");
        for (final BeanDefinition bean : beans) {
            try {
                config.exposeIdsFor(Class.forName(bean.getBeanClassName()));
            } catch (final ClassNotFoundException e) {
                // Can't throw ClassNotFoundException due to the method signature. Need to cast it
                throw new IllegalStateException("Failed to expose `id` field due to", e);
            }
        }

        // 手动设置
//        config.exposeIdsFor(User.class, Post.class);


    }


//
//    /**
//     * 为了解决Spring Data Rest不暴露ID字段的问题。
//     * 参考：http://tommyziegler.com/how-to-expose-the-resourceid-with-spring-data-rest/
//     * Created by Dante on 2016/8/18.
//     */
//    @Bean
//    public RepositoryRestConfigurer repositoryRestConfigurer() {
//
//        return new RepositoryRestConfigurerDelegate() {
//
//            @Override
//            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//
//                final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
//                provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
//                final Set<BeanDefinition> beans = provider.findCandidateComponents("org.onekr.trial.datarest");
//                for (final BeanDefinition bean : beans) {
//                    try {
//                        config.exposeIdsFor(Class.forName(bean.getBeanClassName()));
//                    } catch (final ClassNotFoundException e) {
//                        // Can't throw ClassNotFoundException due to the method signature. Need to cast it
//                        throw new IllegalStateException("Failed to expose `id` field due to", e);
//                    }
//                }
//            }
//
//        };
//    }

}
