package com.example.oso.versosbiblicos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Message> list;
    RecyclerView rv;
    MyAdapter adapter;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        rv = findViewById(R.id.lista);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rv.setLayoutManager(mLayoutManager);

        reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference productRef = reference.child("mensajes");

        adapter = new MyAdapter(list);
        rv.setAdapter(adapter);

        if (Utility.verificarInternet(this)){
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
            conectFirebase(productRef);
        }else{
            readDataBase();
            Toast.makeText(this, "Necesitas conectarte a Internet.", Toast.LENGTH_SHORT).show();
        }


    }

    private void readDataBase() {
        MDBHelper helper = new MDBHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        Cursor cursor  = database.query(MContract.MColumn.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            String content = cursor.getString(MContract.MColumn.COLUMN_CONTENT);
            String address = cursor.getString(MContract.MColumn.COLUMN_ADDRESS);
            list.add(new Message(content, address));
        }
        cursor.close();
    }

    private void saveOnDataBase(List<Message> list) {
        MDBHelper helper = new MDBHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();
        helper.onUpgrade(database,2,2);

        for (Message message : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MContract.MColumn.CONTENT, message.getVerseContent());
            contentValues.put(MContract.MColumn.ADDRESS, message.getVerseAddress());

            database.insert(MContract.MColumn.TABLE_NAME, null, contentValues);
        }
    }

    private void conectFirebase(DatabaseReference productRef) {
        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot msgSnapshot : dataSnapshot.getChildren()){
                    Message m = msgSnapshot.getValue(Message.class);
                    list.add(m);
                    adapter.notifyDataSetChanged();
                }
                saveOnDataBase(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
