/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_ile_lrs_veri_gonderme;

import gov.adlnet.xapi.client.StatementClient;
import gov.adlnet.xapi.model.Activity;
import gov.adlnet.xapi.model.ActivityDefinition;
import gov.adlnet.xapi.model.Agent;
import gov.adlnet.xapi.model.InteractionComponent;
import gov.adlnet.xapi.model.Statement;
import gov.adlnet.xapi.model.Verb;
import gov.adlnet.xapi.model.Verbs;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author MehmetEminÖz
 */
public class Java_ile_LRS_Veri_Gonderme {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        
        //Programı çalıştırmadan önce aşağıdaki LRS verilerini kendi bilgilerinizle doldurun.
        
        String LRS_URI = ""; //LRS endpointi girin
        String USERNAME = ""; //LRS kullanıcı adı girin
        String PASSWORD = ""; //LRS şifresi girin
        
        StatementClient client = new StatementClient(LRS_URI, USERNAME,
                PASSWORD);
        Statement statement = new Statement();
        Agent agent = new Agent();
        Verb verb = Verbs.experienced();
        agent.setMbox("mailto:test@example.com");
        agent.setName("Tester McTesterson");
        statement.setActor(agent);
        statement.setId(UUID.randomUUID().toString());
        statement.setVerb(verb);
        Activity a = new Activity();
        a.setId("http://example.com");
        statement.setObject(a);
        ActivityDefinition ad = new ActivityDefinition();
        ad.setChoices(new ArrayList<InteractionComponent>());
        InteractionComponent ic = new InteractionComponent();
        ic.setId("http://example.com");
        ic.setDescription(new HashMap<String, String>());
        ic.getDescription().put("en-US", "test");
        ad.getChoices().add(ic);
        ad.setInteractionType("choice");
        ad.setMoreInfo("http://example.com");
        a.setDefinition(ad);
        String publishedId = client.postStatement(statement);
    }

}
