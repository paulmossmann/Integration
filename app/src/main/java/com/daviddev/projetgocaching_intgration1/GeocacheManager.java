package com.daviddev.projetgocaching_intgration1;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class GeocacheManager {

    static int INVALID_ARGUMENT = -1;

    public static String readGeocacheId(Intent intent){
        String payload = "";
        if (intent == null)
            return null;
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        payload = readNdefMessage(tag);
        return payload;
    }

    public static int writeGeocacheId(Tag tag, String txt){
        if (tag == null || txt == null)
            return INVALID_ARGUMENT;
        writeNdefMessage(tag, txt);
        return 1;
    }

    //Methode to read
    private static String readNdefMessage(Tag tag)
    {

        //Check if the tag object is null
        if (tag == null) {
            //  Toast.makeText(this, "Tag object cannot be null.", Toast.LENGTH_SHORT).show();
            return "";
        }

        String payloadString = "";

        try {
            /* Methode to extract NdefString from a cached tag:
             * Tag
             *     -> Ndef
             *             -> NdefMessage
             *                            -> NdefRecords
             *                                           -> recordString
             *  V V V The methode is implemented below V V V
             */

            //Extract ndef object from the tag
            Ndef ndef = Ndef.get(tag);

            //Extract ndefmessage from the ndef object
            NdefMessage ndefMessage = ndef.getCachedNdefMessage();

            //Extract ndef records from the ndef message
            NdefRecord[] records = ndefMessage.getRecords();

            //If records is empty
            if (records.length == 0)
            {
                return "";
            }
            //If records is not empty
            else {
                //Parse records and extract the record string
                for (NdefRecord Record : records) {

                    if (Record.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(Record.getType(), NdefRecord.RTD_TEXT)) {
                        //Get payload array from the record
                        byte[] contentpayload = Record.getPayload();

                        String Encoding = ((contentpayload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
                        int languageCodeLength = contentpayload[0] & 0063;

                        //Decode payload array and create payloadString.
                        payloadString = new String(contentpayload, languageCodeLength + 1, contentpayload.length - languageCodeLength - 1, Encoding);
                    }
                }
            }

            // Toast.makeText(this, "Extraction of the NDEF message.", Toast.LENGTH_LONG).show();
        }
        catch (UnsupportedEncodingException e) {
            //  Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return payloadString;
    }

    private static void writeNdefMessage(Tag tag, String writeText) {

        try {
            // Translate string text to ndef message
            NdefRecord ndefRecord = NdefRecord.createTextRecord( null, writeText);
            NdefMessage ndefMessage = new NdefMessage(ndefRecord);

            //Acquiring the Ndef object of the tag
            Ndef ndef = Ndef.get(tag);

            //If the tag is not Ndef formatble format it
            if (ndef == null) {
                formatTag(tag, ndefMessage);
            }
            //If the tag is Ndef formatble
            else {
                ndef.connect();
                //Check if the tag is writable
                if (!ndef.isWritable()) {
                    //  Toast.makeText(this, "Tag is not writable!", Toast.LENGTH_SHORT).show();
                    ndef.close();
                    return;
                }

                ndef.writeNdefMessage(ndefMessage);
                ndef.close();

                // Toast.makeText(this, "\"" + writeText + "\" has been writen.", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.i("formatTag: ","error writing ndef message");
        }
    }

    private static void formatTag(Tag tag, NdefMessage ndefMessage) {

        try {

            NdefFormatable ndefFormatable = NdefFormatable.get(tag);

            if (ndefFormatable == null) {
                // Toast.makeText(this, "NFC TAG is not ndef formatable!", Toast.LENGTH_SHORT).show();
                return;
            }

            ndefFormatable.connect();
            ndefFormatable.format(ndefMessage);
            ndefFormatable.close();

        } catch (Exception e) {
            Log.e("Tag formatting error", e.getMessage());
        }
    }

    // MainActivity take the hand on NFC intents
    public static void enableCatchingNfcIntents(Context context) {

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);

        // FLAG_RECEIVER_REPLACE_PENDING allow the MainActivity to stay in the foreground

        Intent intent = new Intent(context, ScanActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        // Argument one for enableForegroundDIspatch
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        // Argument two for enableForegroundDIspatch
        IntentFilter[] intentFilter = new IntentFilter[]{};
        // Allow to handle NFC events in this activity
        nfcAdapter.enableForegroundDispatch((Activity) context, pendingIntent, intentFilter, null);
    }

    // MainActivity lost the hand on NFC intents
    public static void disableCatchingNfcIntents(Context context) {

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        nfcAdapter.disableForegroundDispatch((Activity) context);
    }

    public static boolean isNfcEnable(Context context) {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        if (nfcAdapter == null || !nfcAdapter.isEnabled())
            return false;
        else
            return true;
    }
}
