package imran.server;

import com.google.inject.Module;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.Arrays;

class ModuleFactory {

    static Module[] getModules() {
        return Arrays.asList(
                servletModule(),
                projectModule()
        ).toArray(new Module[0]);
    }

    private static Module projectModule() {
        return new TasksModule();
    }

    private static ServletModule servletModule() {
        return new ServletModule() {
            @Override
            protected void configureServlets() {
                super.configureServlets();
                ResourceConfig resourceConfig = new PackagesResourceConfig("imran/resource");
                resourceConfig.getClasses().forEach(this::bind);
                serve("/tasks-service*").with(GuiceContainer.class);
            }
        };
    }
}
