package fr.medhead.urgence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.medhead.urgence.controller.UrgenceRestController;
import fr.medhead.urgence.model.Urgence;
import fr.medhead.urgence.service.UrgenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UrgenceRestController.class)
@ExtendWith(MockitoExtension.class)
class UrgenceApplicationTests {
    @Autowired
    MockMvc mvc;

    @MockBean
    private UrgenceService urgenceService;

    private Urgence urgenceDemande;
    private Urgence urgenceAttendue;
    private List<Urgence> toutesLesUrgences = new ArrayList<>();
    private JacksonTester<List<Urgence>> jsonUrgences;

    //    @SneakyThrows
    @BeforeEach
    void setup() {
        Urgence urgence1 = new Urgence(1111,"spec1",1,1,1,"aaa",0);
        Urgence urgence2 = new Urgence(1111,"spec2",2,2,2,"bbb",0);
        Urgence urgence3 = new Urgence(1111,"spec3",3,3,3,"ccc",0);

        toutesLesUrgences.add(urgence1);
        toutesLesUrgences.add(urgence2);
        toutesLesUrgences.add(urgence3);

        urgenceDemande = new Urgence(1234,"cardiologie",5,5,0,"",0);
        urgenceDemande.setId(0);
        urgenceAttendue = new Urgence(1234,"cardiologie",5,5,1,"Fred",0);
        urgenceAttendue.setId(1);
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void givenSupervision_whenVoirTout_thenToutvoir() throws Exception {

        BDDMockito.given(urgenceService.tous()).willReturn(toutesLesUrgences);
        MockHttpServletResponse reponse = mvc
                .perform(MockMvcRequestBuilders.get("/urgences")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertEquals(200, reponse.getStatus());
        assertEquals(jsonUrgences.write(toutesLesUrgences).getJson(), reponse.getContentAsString());
    }

    @Test
    void givenCreeUrgence_whenEnvoieUneUrgence_thenEnregistreUneUrgence() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String bodyJson=ow.writeValueAsString(urgenceDemande);
        String bodyJsonRetour=ow.writeValueAsString(urgenceAttendue);

        BDDMockito.given(urgenceService.nouvelleUrgence(ArgumentMatchers.any(Urgence.class))).willReturn(urgenceAttendue);

        mvc.perform(MockMvcRequestBuilders.post("/urgences/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(bodyJson)
                        .accept("*/*"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(content().json(bodyJsonRetour));
        BDDMockito.then(urgenceService).should().nouvelleUrgence(ArgumentMatchers.any(Urgence.class));
    }
}
