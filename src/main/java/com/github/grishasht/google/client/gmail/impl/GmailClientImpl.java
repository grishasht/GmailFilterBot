package com.github.grishasht.google.client.gmail.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.github.grishasht.google.client.gmail.exception.GmailClientException;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;

import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;

public class GmailClientImpl implements GmailClient {

    private static final String GOOGLE_SERVICE_URL = "https://gmail.googleapis.com/";
    private static final String APPLICATION_NAME = "Gmail client";

    private final GoogleCredential credential;

    private final String serviceAccountEmail;
    private final PrivateKey privateKey;
    private final String privateKeyId;

    private final Gmail gmailService;

    private String signedJwt;

    private GmailClientImpl(GoogleCredential credential) {
        this.credential = credential;
        this.gmailService = createGmailService();
        this.serviceAccountEmail = credential.getServiceAccountId();
        this.privateKey = credential.getServiceAccountPrivateKey();
        this.privateKeyId = credential.getServiceAccountPrivateKeyId();
    }

    public static GmailClientImpl ForGoogleCredential(GoogleCredential credential) {
        return new GmailClientImpl(credential);
    }

    @Override
    public void authorize() {

        final long now = System.currentTimeMillis();

        final Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) privateKey);

        final Date jwtIssuingTimestamp = new Date(now);
        final Date jwtExpirationTimestamp = new Date(now + 3600 * 10000L);

        JWT.create()
                .withKeyId(privateKeyId)
                .withIssuer(serviceAccountEmail)
                .withSubject(serviceAccountEmail)
                .withAudience(GOOGLE_SERVICE_URL)
                .withIssuedAt(jwtIssuingTimestamp)
                .withExpiresAt(jwtExpirationTimestamp)
                .sign(algorithm);
    }

    private Gmail createGmailService() {

        try {
            final HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            final JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

            return new Gmail.Builder(httpTransport, jsonFactory, credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        } catch (Exception e) {
            throw new GmailClientException("Can't create GoogleNetHttpTransport", e);
        }
    }

}
