package com.fcm.server;

import java.io.FileInputStream;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FCMThread implements Runnable{

	private static final String DBNAME = "https://notificationexample-62fe7.firebaseio.com/";
	private static final String SERVICE_ACCOUNT_KEY_FILE_PATH = "NotificationExample-edaaf78cd3b1.json";

	
	@Override
	public void run() {


		try {

			// Initialize the app with a service account, granting admin
			// privileges
			FirebaseOptions options = new FirebaseOptions.Builder().setDatabaseUrl(DBNAME)
					.setServiceAccount(new FileInputStream(SERVICE_ACCOUNT_KEY_FILE_PATH)).build();
			FirebaseApp.initializeApp(options);

			// As an admin, the app has access to read and write all data,
			// regardless of Security Rules
			DatabaseReference pref = FirebaseDatabase.getInstance().getReference("dairy");
			pref.addChildEventListener(new ChildEventListener() {

				@Override
				public void onChildRemoved(DataSnapshot dataSnapshot) {
					Object document = dataSnapshot.getValue();
					System.out.println(document);

				}

				@Override
				public void onChildMoved(DataSnapshot dataSnapshot, String arg1) {
					Object document = dataSnapshot.getValue();
					System.out.println(document);

				}

				@Override
				public void onChildChanged(DataSnapshot dataSnapshot, String arg1) {
					Object document = dataSnapshot.getValue();
					System.out.println(document);

				}

				@Override
				public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
					Object document = dataSnapshot.getValue();
					System.out.println(document);

				}

				@Override
				public void onCancelled(DatabaseError arg0) {

				}
			});

			DatabaseReference ref = FirebaseDatabase.getInstance().getReference("dairy/Item1");
			//ref.setValue("Egg");

			ref.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					Object document = dataSnapshot.getValue();
					System.out.println(document);
				}

				@Override
				public void onCancelled(DatabaseError arg0) {
					System.out.println("OnCancelled");

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}

}
