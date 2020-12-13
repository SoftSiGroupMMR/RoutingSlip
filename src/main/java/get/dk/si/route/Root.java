package get.dk.si.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Root implements Serializable {

    private MetaData metaData;
    private Error error;
    private List<Route> routingSlip = new ArrayList<>();
    protected Logger logger = LoggerFactory.getLogger(Root.class.getName());
    public Root() {
    }

    public Route nextRoute(){
        logger.info("Finding the next route for message");
        List<Route> routingSlip = this.getRoutingSlip().stream().filter(route -> route.isVisited() == false).collect(Collectors.toList());
        routingSlip.sort(Comparator.comparing(Route::getPriority).reversed());
        Route nextRoute = routingSlip.get(0);
        nextRoute.setVisited(true);
        return nextRoute;
    }


    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public List<Route> getRoutingSlip() {
        return routingSlip;
    }

    public void setRoutingSlip(List<Route> routingSlip) {
        this.routingSlip = routingSlip;
    }
}
