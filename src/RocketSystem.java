
public abstract class RocketSystem {
    
    public boolean nozzle[] = new boolean[4];    
    
    public abstract void  ignite(int i,int pressure);    
    public abstract void shutoff(int i);

}
