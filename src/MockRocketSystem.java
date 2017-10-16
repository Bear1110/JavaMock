
public class MockRocketSystem extends RocketSystem{
    
    public void ignite(int i,int pressure){
        nozzle[i] = true;
    }
    
    public void shutoff(int i){
        nozzle[i] = false;
    }
}
