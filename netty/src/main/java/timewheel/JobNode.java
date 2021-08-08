package timewheel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liguang
 * Created on 2021-05-12
 */
public class JobNode {

    private List<JobInfo> jobInfos;

    public JobNode() {
        this.jobInfos = new ArrayList<>();
    }

    public void add(JobInfo jobInfo) {
        jobInfos.add(jobInfo);
    }
}
