package org.kitfox.stm;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ws.rs.HttpMethod;
import javax.xml.bind.JAXBContext;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.kitfox.stm.msg.MessageLegal;
import org.kitfox.stm.v1.Contact;
import org.kitfox.stm.v1.Erreur;
import org.kitfox.stm.v1.Erreurs;
import org.kitfox.stm.v1.Etudiant;
import org.kitfox.stm.v1.Institution;
import org.kitfox.stm.v1.ObjectFactory;
import org.kitfox.stm.v1.PreparationDemandeCarteEtudiant;

/**
 * @author Jean-Francois Larouche
 * xjc -d ../../.. -p org.kitfox.stm.v1 -b bindings.xml PreparationDemandeCarteEtudiant.xsd
 *
 */
public class PreparerDemandeCarte {

    public static void main(String[] args) {
        obtenirMessageLegal("fr-CA");
    }


    public static void main2() {
        try {
            URL url = new URL("https://qa-api.stm.info/pub/ceel/v1pp/preparerdemandecarteetudiant");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setRequestProperty("stm-ceel-institution", "xxxxxxxx");
            connection.setRequestProperty("stm-ceel-token", "xxxxxxxxxxxxxxxxxxx");

            connection.setUseCaches(false);
            connection.setDefaultUseCaches(false);

            //Credentials
            String userpassword = "xxxxxxxxxxxxxxxxx" + ":" + "xxxxxxxxxxxxxxx";
            String encodedAuthorization = Base64.getEncoder().encodeToString(userpassword.getBytes());
            connection.setRequestProperty(
                    "Authorization",
                    "Basic "
                            +
                            encodedAuthorization);

            OutputStream os = connection.getOutputStream();
            // Write your XML to the OutputStream (JAXB is used in this example)
            JAXBContext jaxbContext = JAXBContext.newInstance(
                    PreparationDemandeCarteEtudiant.class,
                    Contact.class,
                    Etudiant.class,
                    Institution.class,
                    Erreurs.class,
                    Erreur.class);

            PreparationDemandeCarteEtudiant prep = createPreparation();

            jaxbContext.createMarshaller().marshal(prep, os);
            os.flush();
            int code = connection.getResponseCode();
            System.out.println(code);

            // reading the response
            InputStreamReader reader;
            if (code >= 500) {
                //reader = new InputStreamReader( connection.getErrorStream() );
                //outputResponse(reader);

                Erreurs errors = (Erreurs) jaxbContext.createUnmarshaller().unmarshal(connection.getErrorStream());
                System.out.println(errors.getSommaire());
            } else {
                reader = new InputStreamReader(connection.getInputStream());

                outputResponse(reader);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void outputResponse(InputStreamReader reader) throws IOException {
        StringBuilder buf = new StringBuilder();
        char[] cbuf = new char[2048];
        int num;
        while (-1 != (num = reader.read(cbuf))) {
            buf.append(cbuf, 0, num);
        }

        System.out.println(buf.toString());
    }

    public static void obtenirMessageLegal(String codeLangue) {

        InputStream is = null;
        try {
            Date date = new Date();
            HttpURLConnection connection = createConnection(true, false, HttpMethod.GET);

            int code = connection.getResponseCode();

            JAXBContext jaxbContext = JAXBContext.newInstance(org.kitfox.stm.msg.ObjectFactory.class.getPackage().getName());

            // reading the response
            if(code != 200) {
                //????
            } else {
                is = connection.getInputStream();
                //outputResponse(new InputStreamReader(is));

                MessageLegal reponse = (MessageLegal)jaxbContext.createUnmarshaller().unmarshal(is);

                Date date2 = new Date();
                System.out.println("millis to execute: " + (date2.getTime() - date.getTime()));

                reponse.getContenu().forEach( contenu -> System.out.println(contenu.getTexte()) );
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private static HttpURLConnection createConnection(boolean input, boolean output, String method) throws MalformedURLException, IOException, ProtocolException {
        URL url = new URL("https://qa-api.stm.info/pub/ceel/v1pp/obtenirmessagelegal");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(input);
        connection.setDoOutput(output);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(method);
        //connection.setRequestProperty("Content-Type", "application/xml");

        //TODO Combien de timeout?
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);

        connection.setUseCaches (false);
        connection.setDefaultUseCaches (false);

        //Credentials
        connection.setRequestProperty("Authorization", "Basic "+
                Base64.getEncoder().encodeToString(("xxxxxxxxxxxx)").getBytes()));

        return connection;
    }

    private static PreparationDemandeCarteEtudiant createPreparation() throws DatatypeConfigurationException {
        ObjectFactory factory = new ObjectFactory();
        PreparationDemandeCarteEtudiant prep = factory.createPreparationDemandeCarteEtudiant();
        Etudiant etudiant = factory.createEtudiant();
        etudiant.setIdEtudiant("1000");
        etudiant.setNom("Test");
        etudiant.setPrenom("Test");

        GregorianCalendar c = new GregorianCalendar(1970, 3, 12);
        String FORMATER = "yyyy-MM-dd";

        DateFormat format = new SimpleDateFormat(FORMATER);

        XMLGregorianCalendar gDateFormatted = DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(c.getTime()));

        etudiant.setDateNaissance(gDateFormatted);
        etudiant.setLangue("fr-CA");
        etudiant.setCodePermanent("DDDD12037001");
        etudiant.setTempsPartiel(false);
        etudiant.setPhoto(
                "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");
        prep.setEtudiant(etudiant);

        Institution institution = factory.createInstitution();
        //institution.setAnneeScolaire("2018-2019");
        institution.setNoInstitution("xxxxxxxx");
        prep.setInstitution(institution);

        Contact contact = factory.createContact();
        contact.setCourriel("none@none.com");
        contact.setNumeroRue("555 i fait frete");
        contact.setVille("Québec");
        contact.setProvince("QC");
        contact.setCodePostal("h0h0h0");
        //contact.setTelephone("4186562131");
        //contact.setPoste("4186");
        prep.setContact(contact);

        //        Tuteur t = factory.createPreparationDemandeCarteEtudiantTuteur();
        //        t.setNom("ergwerg");
        //        t.setPrenom("gwerweRG");
        //        prep.setTuteur(t);

        return prep;
    }

}
