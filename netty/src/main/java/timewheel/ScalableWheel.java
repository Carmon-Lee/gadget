package timewheel;

/**
 * @author liguang
 * Created on 2021-05-12
 */
public class ScalableWheel {

    private final JobNode[] jobNodes;
    private int scaleCount;

    public ScalableWheel(int scaleCount) {
        this.scaleCount = scaleCount;
        this.jobNodes = new JobNode[scaleCount];
        for (int i = 0; i < scaleCount; i++) {
            this.jobNodes[i] = new JobNode();
        }
    }

    public void addJob(JobInfo jobInfo, int idx) {
        jobNodes[idx].add(jobInfo);
    }
}
