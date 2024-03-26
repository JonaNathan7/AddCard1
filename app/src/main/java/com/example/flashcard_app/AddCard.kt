package com.example.flashcard_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
class AddCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        // Récupérer les EditTexts et ImageViews nécessaires depuis le layout XML
        val questionEditText = findViewById<EditText>(R.id.editTextField)
        val answerEditText = findViewById<EditText>(R.id.editTextField1)
        val showAnswerButton = findViewById<ImageView>(R.id.icone_X)
        val saveButton = findViewById<ImageView>(R.id.icone_save)

        // Définir un OnClickListener pour le bouton "Montrer la réponse"
        showAnswerButton.setOnClickListener {
            // Créer un intent pour retourner à l'activité précédente (Activity)
            val intent = Intent(this, Activity::class.java)
            startActivity(intent)
        }

// Définir un OnClickListener pour le bouton "Enregistrer"
        saveButton.setOnClickListener {
            // Récupérer le texte entré dans les champs de texte (EditText)
            val question = questionEditText.text.toString()
            val answer = answerEditText.text.toString()

            // Vérifier si les champs de texte sont vides
            if (question.isEmpty() || answer.isEmpty()) {
                // Afficher un message d'erreur dans un Toast
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            } else {
                // Si les champs sont remplis, créer un intent pour renvoyer les données à l'activité précédente
                val returnIntent = Intent()
                returnIntent.putExtra("question", question) // Ajouter la question à l'intent
                returnIntent.putExtra("answer", answer)     // Ajouter la réponse à l'intent

                // Définir le résultat de l'activité comme "RESULT_OK" et inclure l'intent avec les données
                setResult(RESULT_OK, returnIntent)

                // Fermer l'activité actuelle et retourner à l'activité précédente
                finish()
            }
        }
    }
}