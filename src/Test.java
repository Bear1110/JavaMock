import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Assert;

public class Test {
    
    YourRocketController rc;
    RocketSystem rs; 
    
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
    public void test() {
        rc.fireUp("IGNITE 0 1000 AT 0\nIGNITE 1 500 AT 2\nSHUTOFF 0 AT 5\nSHUTOFF 1 AT 6");
        assertFalse(rs.nozzle[0]);
        assertFalse(rs.nozzle[1]);
    }
    
    

    @org.junit.Test
    public void tesdt() {
        fail("Not yet implemented");
    }
    
}
