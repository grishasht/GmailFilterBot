package com.github.grishasht.google.client.gmail.factory;

import com.github.grishasht.google.client.gmail.exception.GmailClientException;
import com.github.grishasht.google.client.gmail.impl.GmailClient;
import com.github.grishasht.google.client.gmail.impl.GmailClientImpl;
import com.github.grishasht.util.ResourceReader;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.gmail.GmailScopes;

import java.io.ByteArrayInputStream;
import java.util.Collections;

public class GmailClientFactory {

    public GmailClient createInstance(String credentialsFileContentPath)
    {
        try
        {
            final byte[] jsonByteArray = ResourceReader.readAllBytesFromResources(credentialsFileContentPath);

            final GoogleCredential credential = GoogleCredential.fromStream(new ByteArrayInputStream(jsonByteArray))
                    // TODO: 05-Aug-20 Clarify about GmailScopes
                    .createScoped(Collections.singletonList(GmailScopes.GMAIL_READONLY));

            return GmailClientImpl.ForGoogleCredential(credential);
        }
        catch (Exception e)
        {
            throw new GmailClientException("Can't process file by content path: " + credentialsFileContentPath, e);
        }
    }

}
