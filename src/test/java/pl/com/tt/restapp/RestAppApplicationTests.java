package pl.com.tt.restapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestAppApplication.class)
public class RestAppApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test()
    {
        RestAppApplication.main(new String[]{});
    }

}
