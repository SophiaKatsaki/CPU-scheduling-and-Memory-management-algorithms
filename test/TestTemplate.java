import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestTemplate {

    @BeforeEach
    public void setUp(){
        System.out.println("setUp Test");
    }

    @Test
    public void test1(){
        System.out.println("Test1 Success");
        assertEquals(1, 1);
    }

    @Test
    public void test2(){
        System.out.println("Test2 Success");
        assertFalse(false);
    }

    @Test
    public void test3(){
        System.out.println("Test3 Fail");
        assertEquals(1, 2);
    }

    @Test
    public void test4(){
        System.out.println("Test4 Fail");
        fail();
    }


}
