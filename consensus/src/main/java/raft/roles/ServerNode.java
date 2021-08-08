package raft.roles;

import java.util.List;

import raft.log.RaftLog;

public class ServerNode {

    /**
     * role of the node
     */
    private Role role;

    // persistent state on all servers
    private long currentTerm;

    private HostInfo votedFor;

    private List<RaftLog> log;

    // volatile state for all servers


    // volatile state for on leaders
    private List<Long> nextIndex;
    private List<Long> matchIndex;



}
