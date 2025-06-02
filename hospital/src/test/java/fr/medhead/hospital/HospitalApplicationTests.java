package fr.medhead.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.medhead.hospital.controller.HopitalRestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import fr.medhead.hospital.exceptions.SpecialiteNotFoundException;
import fr.medhead.hospital.model.Hopital;
import fr.medhead.hospital.model.Specialite;
import fr.medhead.hospital.service.HopitalService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@WebMvcTest(HopitalRestController.class)
class HospitalApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HopitalService hopitalService;

    private List<Hopital> hopitauxAttendus;
    private Hopital hoptialAttendu;

    private JacksonTester<List<Hopital>> jsonHopitaux;
    private JacksonTester<Hopital> jsonHopital;

    String erreur = "La specialite suivant n'a pas ete trouve : impossible";

    @SneakyThrows
    @BeforeEach
    void setup() {
        Specialite speCardiologie = new Specialite("medecine generale","cardiologie");
        Specialite speImmunologie = new Specialite("pathologie", "immunologie");
        Specialite speNeuropathologieDiag = new Specialite("pathologie", "neuropathologie diagnostique");

        List<Specialite> specFB = new ArrayList<>();
        specFB.add(speCardiologie);
        specFB.add(speImmunologie);
        List<Specialite> specJC = new ArrayList<>();
        specJC.add(speImmunologie);
        List<Specialite> specBB = new ArrayList<>();
        specBB.add(speImmunologie);
        specBB.add(speNeuropathologieDiag);

        hopitauxAttendus = new ArrayList<>();
        hopitauxAttendus.add(new Hopital("Hopital Fred Brooks", 1,  1, 2, specFB));
        hopitauxAttendus.add(new Hopital("Hopital Julia Crusher", 2, 2, 0, specJC));
        hopitauxAttendus.add(new Hopital("Hopital Beverly Bashir", 4, 6,  5, specBB));

        hoptialAttendu = new Hopital("Hopital Fred Brooks", 1, 1, 2, specFB);

        JacksonTester.initFields(this, new ObjectMapper());

    }

    @Test
    void givenTousHopitaux_whenListeHopitaux_thenListe() throws Exception {
        // Given
        given(hopitalService.tous()).willReturn(hopitauxAttendus);

        // When
        MockHttpServletResponse response = mockMvc
                .perform(get("/hopitaux")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        then(hopitalService).should().tous();
        assertEquals(200, response.getStatus());
        assertEquals(jsonHopitaux.write(hopitauxAttendus).getJson(), response.getContentAsString());
    }

    @Test
    void givenHopitalParSpecialite_whenDemandeSpecialite_thenHopitalSpecialite() throws Exception {
        String specialiteTest = "cardiologie";
        Point coordonneOrigine = new Point(5,5);

        given(hopitalService
                .trouverUnHopitalProcheParSpecialite(specialiteTest,coordonneOrigine.x, coordonneOrigine.y))
                .willReturn(hoptialAttendu);

        MockHttpServletResponse reponse = mockMvc
                .perform(get("/hopitaux/" + specialiteTest
                        + "/"+ coordonneOrigine.x
                        + "/" + coordonneOrigine.y)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        then(hopitalService).should()
                .trouverUnHopitalProcheParSpecialite(specialiteTest,coordonneOrigine.x, coordonneOrigine.y);
        assertEquals(HttpStatus.OK.value(), reponse.getStatus());
        assertEquals(jsonHopital.write(hoptialAttendu).getJson(),reponse.getContentAsString());
    }

    @Test
    void givenSpecialite_whenDemandeNonSpecialite_thenNonSpecialite() throws Exception {
        String specialiteTest = "impossible";
        Point coordonneOrigine = new Point(5,5);

        given(hopitalService
                .trouverUnHopitalProcheParSpecialite(specialiteTest,coordonneOrigine.x, coordonneOrigine.y))
                .willThrow(new SpecialiteNotFoundException(specialiteTest));

        MockHttpServletResponse reponse = mockMvc
                .perform(get("/hopitaux/" + specialiteTest
                        + "/"+ coordonneOrigine.x
                        + "/" + coordonneOrigine.y)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        then(hopitalService).should()
                .trouverUnHopitalProcheParSpecialite(specialiteTest,coordonneOrigine.x, coordonneOrigine.y);
        assertEquals(HttpStatus.NO_CONTENT.value(), reponse.getStatus());
        assertEquals(erreur, reponse.getContentAsString());
    }
}
