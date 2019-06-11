package com.daviddev.salscaching;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;

import java.io.IOException;

/**
 * Classe pour manager la lecture et l'écriture de tag nfc correspondant à des géocaches
 */
public class GeocacheManager {
    /**
     * Méthode pour lire l'identifiant contenu dans un géocache.
     * @param intent
     * @return
     * @throws Exception
     */
    public static String readGeocacheId(Intent intent) throws Exception {

        if (intent == null || !intent.hasExtra(NfcAdapter.EXTRA_TAG))
            throw new Exception("Erreur de lecture du tag NFC");

        String id;

        try{
            //Extraction de l'objet tag de l'intent
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            //Lecture des données contenue dans l'objet tag
            id = readNdefMessage(tag);

            //Vérification de la conformité de l'identifiant
            if (Integer.parseInt(id) <0 || Integer.parseInt(id) >=1000)
                throw new BadTagIdException();

        } catch (NullPointerException e){
            throw new Exception("Erreur de lecture du tag NFC");
        } catch (NumberFormatException e) {
            throw new BadTagIdException();
        }

        //On retrourne l'identifiant
        return id;
    }

    /**
     * Méthode pour lire le NDEF Message contenu dans un tag NFC.
     * @param tag
     * @return
     * @throws Exception
     */
    private static String readNdefMessage(Tag tag) throws Exception {

        String payloadString = null;

        try {

            /*
             Methode pour extraire les données contenues dans un objet tag:
             Tag -> Ndef -> NdefMessage -> NdefRecords -> byteArray -> recordString
            */

            //Obtenention d'une instance de NDEF pour le tag donné en paramétre
            Ndef ndef = Ndef.get(tag);

            //Obtenention du NDEF Message qui à été lue lors du scan du tag
            NdefMessage ndefMessage = ndef.getCachedNdefMessage();

            //Récupération des NDEF Records contenus dans le NDEF Message.
            NdefRecord[] records = ndefMessage.getRecords();

            //Il doit exister un unique NDEF Record qui contient l'identifiant du géocache
            if (records.length != 1)
                throw new BadTagIdException();

            else {
                //Extraction de l'unique NDEF Record
                NdefRecord record = records[0];

                //Convertion du NDEF Record dans un tableau de bytes
                byte[] contentpayload = record.getPayload();

                String Encoding = ((contentpayload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
                int languageCodeLength = contentpayload[0] & 0063;

                //Decodage du tableau de bytes et conversion en chaine de caractéres par le constructeur String
                payloadString = new String(contentpayload, languageCodeLength + 1, contentpayload.length - languageCodeLength - 1, Encoding);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            throw new Exception("Erreur de lecture du tag NFC");
        }

        return payloadString;
    }

    /**
     * Méthode pour écrire un identifiant dans un géocache.
     * @param intent
     * @param id
     * @throws Exception
     */
    public static void writeGeocacheId(Intent intent, String id) throws Exception {

        try {

            if (intent == null || id == null || !intent.hasExtra(NfcAdapter.EXTRA_TAG))
                throw new Exception("Erreur d'écriture du tag NFC");

            //Extraction de l'objet tag de l'intent
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            //Vérification de la conformité de l'identifiant
            if (Integer.parseInt(id) <0 || Integer.parseInt(id) >=1000)
                throw new Exception("L'identifiant entré est invalide");

            writeNdefMessage(tag, id);

        } catch (NullPointerException e){
            throw new Exception("Erreur d'écriture du tag NFC");
        } catch (NumberFormatException e) {
            throw new Exception("L'identifiant entré est invalide");
        }

    }

    /**
     * Méthode pour écrire un NDEF Message dans un tag NFC.
     * @param tag
     * @param writeText
     * @return
     */
    private static void writeNdefMessage(Tag tag, String writeText) throws Exception {

        try {
            // Construction d'un Ndef Record avec le texte à instruire
            NdefRecord ndefRecord = NdefRecord.createTextRecord("", writeText);

            // Construction d'un Ndef Message avec le Ndef Record
            NdefMessage ndefMessage = new NdefMessage(ndefRecord);

            //Acquisition de l'objet ndef à partir du tag scanner
            Ndef ndef = Ndef.get(tag);

            //Si le tag NFC n'est pas au format NDEF, on le formate
            if (ndef == null) {
                formatTag(tag, ndefMessage);
                return;
            }

            //Si le tag NFC estu au format NDEF, on l'instruit directement
            else {

                ndef.connect();

                //Verification de la possibilité d'écritude du tag
                if (!ndef.isWritable()) {
                    ndef.close();
                    throw new TagNotWritableException();
                }

                //Ecrase le message écrit dans le tag NFC avec le nouveau ndefMessage
                ndef.writeNdefMessage(ndefMessage);
                ndef.close();

            }

        }catch(NullPointerException e){
            throw new Exception("Erreur d'écriture du tag NFC");
        }
    }

    /**
     * Méthode pour formater un tag NFC.
     * @param tag
     * @param ndefMessage
     * @return
     */
    private static void formatTag(Tag tag, NdefMessage ndefMessage) throws Exception {

        try {

            //Acquisition de l'objet ndefFormatable à partir du Tag
            NdefFormatable ndefFormatable = NdefFormatable.get(tag);

            ndefFormatable.connect();

            //Formatage du Tag NFC et écriture du ndefMessage
            ndefFormatable.format(ndefMessage);

            ndefFormatable.close();

        } catch (Exception e) {
            throw new Exception("Erreur de formatage du tag NC");
        }
    }

    /**
     * Méthode pour activer la détection des intentions NFC.
     * @param context
     */
    public static void enableCatchingNfcIntents(Context context) {

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);

        // FLAG_RECEIVER_REPLACE_PENDING Permet à l'activité de rester au premier plan
        Intent intent = new Intent(context, context.getClass()).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        // Argument one for enableForegroundDIspatch
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        //Donne la priorité à l'activité de premier plan lors de la détection d'une intention NFC.
        nfcAdapter.enableForegroundDispatch((Activity) context, pendingIntent, null, null);
    }

    /**
     * Méthode pour désactiver la détection des intentions NFC.
     * @param context
     */
    public static void disableCatchingNfcIntents(Context context) {

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        nfcAdapter.disableForegroundDispatch((Activity) context);
    }

    /**
     * Méthode pour vérifier l'activation de la fonction NFC sur le smartphone.
     * @param context
     */
    public static boolean isNfcEnable(Context context) {

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);

        if (nfcAdapter == null ||!nfcAdapter.isEnabled())
            return false;
        else
            return true;
    }

    /**
     * Exception levée lorsqu'un identifiant géocache est incorect (format correct: numérique et compris entre 0 et 999).
     */
    static class BadTagIdException extends Exception {
        BadTagIdException() {
            super("Le contenue du tag NFC ne correspond pas à un identifiant de géocache.");
        }
    }

    /**
     * Exception levée lorsqu'on tente d'instruire un tag NFC vérouillé en lecture seule.
     */
    static class TagNotWritableException extends Exception {
        TagNotWritableException() {
            super("Le tag NFC est en lecture seule.");
        }
    }
}
