package com.cricfant.conf;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class FirebaseConfig {

    @PostConstruct
    public void init() throws IOException {
        File conf = ResourceUtils.getFile("classpath:firebase_conf.json");
        FileInputStream serviceAccount = new FileInputStream(conf);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://cricfant-b3a8d.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);
    }
}
