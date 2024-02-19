package com.anatoliykichuk.cardiolog.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

object FirestoreClient {

    fun getClient(): FirebaseFirestore {
        return FirebaseFirestore.getInstance().apply {
            firestoreSettings = FirebaseFirestoreSettings
                .Builder()
                .setPersistenceEnabled(false)
                .build()
        }
    }
}