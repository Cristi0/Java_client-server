import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SourceTest {

    private Source s;

    @Before
    public void setUp() throws Exception {
        s=new Source();
    }

    @After
    public void tearDown() throws Exception {
        s=null;
    }


}