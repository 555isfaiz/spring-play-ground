package morgan;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Unit test for simple App.
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
// @SpringBootTest(
//         webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//         classes = App.class)
@WebMvcTest(MyController.class)
@ExtendWith(MockitoExtension.class)
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MyService myService;

    // @MockBean
    // private MyDataRepo myDataRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetService() throws Exception {
        Mockito.when(myService.getServiceKey()).thenReturn("test key");
        Mockito.when(myService.getServiceVal()).thenReturn("test val");
        mockMvc.perform(get("/service"))
            .andExpect(status().isOk())
            .andExpect(content().string("key and val: test key test val"));
    }
}
