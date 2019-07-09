package pl.com.tt.restapp.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import pl.com.tt.restapp.domain.Actor;
import pl.com.tt.restapp.service.ActorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ActorRestControllerTests {

    @Mock
    Actor actor;

    @Mock
    ActorServiceImpl actorService;

    @InjectMocks
    ActorRestController controller;

    private static final Long ID = 1L;

    @Test
    public void asd(){
        Assert.assertEquals(HttpStatus.OK, controller.getAllActors().getStatusCode());
    }

}
