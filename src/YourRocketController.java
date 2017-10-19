import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class YourRocketController {
    
    private RocketSystem rs;
    
    public YourRocketController(RocketSystem rs){
        this.rs = rs;
    }
    
    public void fireUp(String control_string){
        
       String[] inputs = control_string.split("\n");
       ExecutorService threadExecutor = null;
       for(String input : inputs){
           threadExecutor = Executors.newFixedThreadPool(20);           
           threadExecutor.submit(new workThread(input));
       }
       threadExecutor.shutdown();
       
       try {
           while (!threadExecutor.awaitTermination(1, TimeUnit.SECONDS)) {  
               //System.out.println("Thread is running!");
           }  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private boolean checkValue(int nozzle,int pressure,int atSecond){    
        
        if(nozzle < 0 || nozzle >3){
            System.out.println("nozzle number is wrong");
            return false;
        }
        if(pressure < 0){
            System.out.println("pressure is wrong");
            return false;
        }
        if(atSecond < 0){
            System.out.println("Second is wrong");
            return false;
        }
        return true;        
    }
    
    public class workThread extends Thread {
        String input;
        public workThread(String input){
            this.input = input;
        }
        public void run() {
            String[] str = input.split(" ");
            String command = str[0];
            int nozzle = 0,pressure = 0,atSecond = 0;
            
            if( !command.equals("IGNITE") && !command.equals("SHUTOFF")){
                System.out.println("command is not SHUTOFF or IGNITE");
                return;
            }
            try{
                nozzle = Integer.parseInt(str[1]);
                if(command.equals("IGNITE")){
                    pressure = Integer.parseInt(str[2]);
                    atSecond = Integer.parseInt(str[4]);
                }else{
                    atSecond = Integer.parseInt(str[3]);
                }
            }catch(Exception ex){
                System.out.println("nozzle or pressure or second not number");
                return;
            }
            
            if(!checkValue(nozzle,pressure,atSecond)){
                return;
            }
            System.out.println(command+"指令 在"+ atSecond+"秒後 執行");
            try {
                Thread.sleep(atSecond*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(command.equals("IGNITE"))
                rs.ignite(nozzle, pressure);
            else if (command.equals("SHUTOFF"))
                rs.shutoff(nozzle);
        }
    }
}
