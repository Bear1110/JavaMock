
public class MockRocketSystem extends RocketSystem{

    
    @Override
    public void ignite(int i,int pressure){
        nozzle[i] = true;
    }
    @Override
    public void shutoff(int i){
        nozzle[i] = false;
    }
    
}
