package raft.roles;

public enum State {

    /**
     * on startup, the node is in follower state
     * and starts a timer
     * if timeout, then turn to candidate and starts a new round of election
     */
    FOLLOWER,
    /**
     * the leader is responsible for sending commands to all other followers
     * which serves to stopping these servers from becoming candidates
     */
    LEADER,

    /**
     * if received most votes, then becomes leader
     * else continue another round election
     */
    CANDIDATE;
}
