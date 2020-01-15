package org.kitfox.stm;

import javax.ws.rs.HttpMethod;
import javax.xml.bind.JAXBContext;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.GregorianCalendar;

import org.kitfox.stm.msg.MessageLegal;
import org.kitfox.stm.v1.*;

/**
 * @author Jean-Francois Larouche
 * xjc -d ../../.. -p org.kitfox.stm.v1 -b bindings.xml PreparationDemandeCarteEtudiant.xsd
 *
 */
public class PreparerDemandeCarte {

    public static void main(String[] args) throws Exception {
        //obtenirMessageLegal("fr-CA");
        main3();
    }

    public static void main3() throws Exception {
        // Write your XML to the OutputStream (JAXB is used in this example)
        JAXBContext jaxbContext = JAXBContext.newInstance(
                PreparationDemandeCarteEtudiant.class,
                Contact.class,
                Etudiant.class,
                Institution.class,
                Erreurs.class,
                Erreur.class);

        PreparationDemandeCarteEtudiant prep = createPreparation();

        try(ByteArrayOutputStream os = new ByteArrayOutputStream()) {

            jaxbContext.createMarshaller().marshal(prep, os);
            os.flush();

            System.out.println(new String(os.toByteArray(), "UTF-8"));

        }
        catch (Exception e) {

        }
    }

    public static void main2() {
        try {
            URL url = new URL("https://");
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

    private static HttpURLConnection createConnection(boolean input, boolean output, String method) throws IOException {
        URL url = new URL("https://");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(input);
        connection.setDoOutput(output);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(method);
        //connection.setRequestProperty("Content-Type", "application/xml");

        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);

        connection.setUseCaches (false);
        connection.setDefaultUseCaches (false);

        //Credentials
        connection.setRequestProperty("Authorization", "Basic "+
                Base64.getEncoder().encodeToString(("(xxxxxxxxxxxx)").getBytes()));

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
                "Base64 encoded");
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
