package com.example.notes;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

// for the recycler view
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    Context context;
    RealmResults<Note> notesList;

    public Adapter(Context context, RealmResults<Note> notesList) { // constructor
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {  //creates a new recycle view - dynamic list
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {  //displays data at a specified position
        Note note = notesList.get(position);
        holder.titleOutput.setText(note.getTitle());  //displays data to the interface after user saves
        holder.detailOutput.setText(note.getDetail());
        String time = DateFormat.getDateTimeInstance().format(note.time);
        holder.timeOutput.setText(time);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {  //if user long-clicks
            @Override
            public boolean onLongClick(View view) {
                PopupMenu menu = new PopupMenu(context,view);
                menu.getMenu().add("DELETE");
                Realm realm = Realm.getDefaultInstance();
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) { //if user clicks on delete
                        if(menuItem.getTitle().equals("DELETE")){
                            realm.beginTransaction();
                            note.deleteFromRealm();  // delete from database
                            realm.commitTransaction();
                            Toast.makeText(context,"Reminder Deleted!",Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        // returns the number of items avalible in adapter
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleOutput;
        TextView detailOutput;
        TextView timeOutput;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOutput = itemView.findViewById(R.id.titleoutput);
            detailOutput = itemView.findViewById(R.id.detailoutput);
            timeOutput = itemView.findViewById(R.id.timeoutput);

        }
    }
}
