package imran.resource;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;
import imran.dao.TasksDao;
import imran.dao.TasksDaoInMemory;
import imran.database.DataSource;
import imran.service.TasksService;
import imran.service.TasksServiceImpl;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class ResourceITest {

    private static final URI BASE_URI = getBaseURI();

    private static HttpServer server;
    private static Injector injector;
    protected static WebResource service;

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(9998).build();
    }

    @BeforeClass
    public static void startServer() throws IOException {
        System.out.println("Starting grizzly...");

        injector = Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(DataSource.class);
                bind(TasksService.class).to(TasksServiceImpl.class);
                bind(TasksDao.class).to(TasksDaoInMemory.class);
            }
        });

        ResourceConfig rc = new PackagesResourceConfig("imran.resource");
        IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory(rc, injector);
        server = GrizzlyServerFactory.createHttpServer(BASE_URI + "tasks-service/", rc, ioc);

        System.out.println(String.format("Jersey app started with WADL available at "
                        + "%srest/application.wadl\nHit enter to stop it...",
                BASE_URI, BASE_URI));

        Client client = Client.create(new DefaultClientConfig());
        service = client.resource(getBaseURI());

    }

    @AfterClass
    public static void stopServer() {
        server.stop();
    }

    @Provides
    protected static DataSource dataSource() {
        return injector.getInstance(DataSource.class);
    }

}
