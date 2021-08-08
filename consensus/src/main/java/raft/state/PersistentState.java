package raft.state;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

import lombok.Getter;
import raft.log.RaftLog;
import raft.roles.HostInfo;

/**
 * @author liguang
 * Created on 2021-05-23
 */
@Getter
public class PersistentState {

    private long currentTerm;

    private HostInfo votedFor;

    private List<RaftLog> log;



}
