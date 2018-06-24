package com.example.qq.testadmin1462561;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private ListView listViewArtists;
    private List<Database_Register> artistList;
    private AdminListAdapter adminListAdapter;
    private ProgressDialog progressDialog;
    private Database_Register database_register;
    private Button goMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistList = new ArrayList<>();
        listViewArtists = (ListView) findViewById(R.id.listViewArtists);
        database_register = new Database_Register();
        goMap = findViewById(R.id.goMap);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait loading list ...");
        progressDialog.show();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    database_register = snapshot.getValue(Database_Register.class);
                    artistList.add(database_register);
                }
                adminListAdapter = new AdminListAdapter(MainActivity.this, R.layout.list_layout, artistList);
                listViewArtists.setAdapter(adminListAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
        listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                database_register = artistList.get(position);
                String email = database_register.get_email();
                String username = database_register.get_username();


               showUpdateDialog(email, username);

            }
        });

        goMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showUpdateDialog(String artistEmail, String artlistName) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.update_dialog,null);

        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText)dialogView.findViewById(R.id.editTextName);
        final EditText editTextTel = (EditText)dialogView.findViewById(R.id.editTextTel);
        final Button buttonUpdate = (Button)dialogView.findViewById(R.id.buttonUpdate);

        dialogBuilder.setTitle("Updating" + artistEmail);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
