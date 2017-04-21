package pt.rht;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import pt.rht.Models.Round;
import pt.rht.Helpers.RoundOperations;
import java.util.List;

public class RoundActivity extends ListActivity {
    private RoundOperations roundOps;
    List<Round> rounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);

        roundOps = new RoundOperations(this);
        roundOps.open();
        rounds = roundOps.getAllRounds();
        roundOps.close();
        ArrayAdapter<Round> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, rounds);
        setListAdapter(adapter);
    }
}
