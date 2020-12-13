package get.dk.si.route;

import java.io.Serializable;

public class Route implements Serializable {

    private boolean visited;
    private String queue;
    private String status;
    private int priority;

    public Route(boolean visited, String queue, String status, int priority) {
        this.visited = visited;
        this.queue = queue;
        this.status = status;
        this.priority = priority;
    }

    public Route() {
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


}
