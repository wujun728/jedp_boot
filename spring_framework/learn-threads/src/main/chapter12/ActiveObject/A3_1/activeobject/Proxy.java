package activeobject;

class Proxy implements ActiveObject {
    private final SchedulerThread scheduler;
    private final Servant servant;
    public Proxy(SchedulerThread scheduler, Servant servant) {
        this.scheduler = scheduler;
        this.servant = servant;
    }
    public Result search(String word) {
        FutureResult future = new FutureResult();
        scheduler.invoke(new SearchRequest(servant, future, word));
        return future;
    }
}
