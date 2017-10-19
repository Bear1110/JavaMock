import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Assert;

public class YourRocketControllerTest {
    
    YourRocketController rc;
    RocketSystem rs;
    ByteArrayOutputStream outputStream;
    PrintStream out;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    @Before
    public void setUp() throws Exception {
        rs = new MockRocketSystem();
        rc = new YourRocketController(rs);
    }

    @After
    public void tearDown() throws Exception {
        rs = null;
        rc = null;
    }

    @org.junit.Test
    public void normalTest() {
        rc.fireUp("IGNITE 0 1000 AT 0\nIGNITE 1 500 AT 2\nSHUTOFF 0 AT 5\nSHUTOFF 1 AT 6\nIGNITE 0 1000 AT 8");
        assertTrue(rs.nozzle[0]);        
        assertFalse(rs.nozzle[1]);
    }

    @org.junit.Test
    public void nozzleNumberOverthree() {
        iniSystemPrint();
        rc.fireUp("IGNITE 9 1000 AT 0");        
        String answer = outputStream.toString().trim();
        assertEquals("nozzle number is wrong" , answer );
    }
    
    @org.junit.Test
    public void otherCommand() {
        iniSystemPrint();
        rc.fireUp("IGNITES 0 1000 AT 0");        
        String answer = outputStream.toString().trim();
        assertEquals("command is not SHUTOFF or IGNITE" , answer );
                
        iniSystemPrint();
        rc.fireUp("SHUTOFF 0 1000 AT 0");        
        answer = outputStream.toString().trim();
        assertEquals("nozzle or pressure or second not number" , answer );
    }
    
    @org.junit.Test
    public void inputNotNumber() {
        iniSystemPrint();
        rc.fireUp("IGNITE 0 1000sa AT 0");        
        String answer = outputStream.toString().trim();
        assertEquals("nozzle or pressure or second not number" , answer );
    }
    @org.junit.Test
    public void inputNegativeNumber() {
        iniSystemPrint();
        rc.fireUp("IGNITE 0 1000 AT -1");
        String answer = outputStream.toString().trim();
        assertEquals("Second is wrong" , answer );
        
        iniSystemPrint();
        rc.fireUp("IGNITE 0 -15 AT 5");
        answer = outputStream.toString().trim();
        assertEquals("pressure is wrong" , answer );
        
        
        iniSystemPrint();
        rc.fireUp("IGNITE -100 100 AT 5");
        answer = outputStream.toString().trim();
        assertEquals("nozzle number is wrong" , answer );
        
        
        iniSystemPrint();
        rc.fireUp("SHUTOFF -100 AT 100");
        answer = outputStream.toString().trim();
        assertEquals("nozzle number is wrong" , answer );
        
        
    }
    public void iniSystemPrint(){
        outputStream = new ByteArrayOutputStream();
        out = new PrintStream(outputStream);
        System.setOut(out);
    }
}
