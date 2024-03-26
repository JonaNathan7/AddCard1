package com.example.flashcard_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupérer les vues (Views) nécessaires depuis le layout XML
        val toggleButton: ImageView = findViewById(R.id.toggle123)
        val questionTextView: TextView = findViewById(R.id.flashcard_question)
        val answerTextView: TextView = findViewById(R.id.flashcard_reponse)

        // Enregistrement d'un résultat de l'activité pour obtenir les données renvoyées
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    // Si l'activité renvoie un résultat OK, extraire les données de l'intent
                    val data: Intent? = result.data
                    if (data != null) {
                        // Extraire la question et la réponse des données renvoyées
                        val question = data.getStringExtra("question")
                        val answer = data.getStringExtra("answer")

                        // Mettre à jour les TextViews dans MainActivity avec les nouvelles données ajoutées
                        questionTextView.text = question
                        answerTextView.text = answer
                    } else {
                        // Aucune donnée renvoyée
                        Log.i("MainActivity", "No data returned")
                    }
                } else {
                    // L'opération d'enregistrement a été annulée
                    Log.i("MainActivity", "Save operation cancelled")
                }
            }

        // Définir un OnClickListener pour le TextView de question
        questionTextView.setOnClickListener {
            // Lorsque le TextView de question est cliqué, le cacher et afficher le TextView de réponse
            questionTextView.visibility = View.INVISIBLE
            answerTextView.visibility = View.VISIBLE
        }

        // Définir un OnClickListener pour le TextView de réponse
        answerTextView.setOnClickListener {
            // Lorsque le TextView de réponse est cliqué, le cacher et afficher le TextView de question
            questionTextView.visibility = View.VISIBLE
            answerTextView.visibility = View.INVISIBLE
        }

        // Définir un OnClickListener pour le bouton de bascule (toggleButton)
        toggleButton.setOnClickListener {
            // Créer un intent pour démarrer l'activité AddCard
            val intent = Intent(this, AddCard::class.java)
            // Lancer l'activité AddCard avec le résultatLauncher, afin de récupérer les données renvoyées
            resultLauncher.launch(intent)
        }
    }
}

