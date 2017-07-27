package org.glassfish.jersey.examples.entityfiltering;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class EntityFilteringApplication extends ResourceConfig {

    public EntityFilteringApplication() {
        packages("org.glassfish.jersey.examples.entityfiltering");
        register(EntityFilteringFeature.class);
        register(new MoxyJsonConfig().setFormattedOutput(true).resolver());

    }
}
