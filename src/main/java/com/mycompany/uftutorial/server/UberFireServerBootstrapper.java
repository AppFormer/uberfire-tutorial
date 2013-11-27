package com.mycompany.uftutorial.server;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uberfire.backend.server.config.ConfigurationService;
import org.uberfire.commons.services.cdi.Startup;

/**
 * This class is a workaround to ensure the UberFire ConfigurationService has
 * been initialized before the first client request comes in (it has side
 * effects, including bootstrapping the filesystem service). See JIRA issue
 * UF-17 for details.
 * <p>
 * Unfortunately, we can't simply inject {@link ConfigurationService} into our
 * {@link UberFireConfiguration} bean, because that creates a circular injection
 * dependency chain.
 */
@Startup
@ApplicationScoped
public class UberFireServerBootstrapper {

  private static final Logger logger = LoggerFactory.getLogger(UberFireServerBootstrapper.class);

  @Inject ConfigurationService configurationService;

  @PostConstruct
  private void init() {
    configurationService.toString(); // this line ensures the ConfigurationService bean is really instantiated. do not remove!
    logger.info("Finished creating UberFire ConfigurationService");
  }
}
