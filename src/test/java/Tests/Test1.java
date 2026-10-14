package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 extends BaseTest {

    @Test
    public void test1() {
        Assert.assertEquals(2 + 2, 4);
    }
}
