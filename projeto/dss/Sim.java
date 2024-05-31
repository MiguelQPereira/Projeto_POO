package dss;

public interface Sim {
    
    void runSim();
    void addEv(Event ev);
    double getTime();
    int getMaxTime();
    int getEvsNum();
    void cleanPec();
    void emptiesPec();
}
