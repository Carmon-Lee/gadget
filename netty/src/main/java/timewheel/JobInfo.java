package timewheel;

/**
 * @author liguang
 * Created on 2021-05-12
 */
public class JobInfo {

    private long timestamp;
    private Runnable job;

    public JobInfo(long timestamp, Runnable job) {
        this.timestamp = timestamp;
        this.job = job;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Runnable getJob() {
        return job;
    }

    public void setJob(Runnable job) {
        this.job = job;
    }
}
