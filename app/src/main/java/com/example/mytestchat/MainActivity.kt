package com.example.mytestchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytestchat.databinding.ActivityMainBinding
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message").child("Andrey")
        binding.bSend.setOnClickListener {
            myRef.setValue(binding.EdMessage.text.toString())
        }

        onChangeListener(myRef)
    }

    private fun onChangeListener(dref: DatabaseReference){
        dref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               binding.apply {
                   tvRcview.append("\n")
                   tvRcview.append("Andrey: ${snapshot.value.toString()}")
               }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}