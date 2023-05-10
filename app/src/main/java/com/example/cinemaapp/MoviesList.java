package com.example.cinemaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MoviesList extends AppCompatActivity {

    private String[][] action_movies =
            {
                    {"Avengers", "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.", "Imax-3D", "20:00", "15"},
                    {"Fast & Furious", "A group of racers who take cars to another level. All about family.", "Standard", "21:00", "10"},
                    {"Top Gun: Maverick", "After thirty years, Maverick is still pushing the envelope as a top naval aviator, but must confront ghosts of his past when he leads TOP GUN's elite graduates on a mission that demands the ultimate sacrifice from those chosen to fly it.", "Imax-3D", "20:00", "15"},
                    {"Spider-Man: No Way Home", "With Spider-Man's identity now revealed, Peter asks Doctor Strange for help. When a spell goes wrong, dangerous foes from other worlds start to appear, forcing Peter to discover what it truly means to be Spider-Man.", "Imax-3D", "19:00", "15"},
                    {"The Batman", "When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.", "Imax-3D", "18:00", "15"},
            };
    private String[][] comedy_movies =
            {
                    {"Jumanji: The Next Level", "In Jumanji: The Next Level, the gang is back but the game has changed. As they return to rescue one of their own, the players will have to brave parts unknown from arid deserts to snowy mountains, to escape the world's most dangerous game.", "Standard", "20:00", "10"},
                    {"Free Guy", "A bank teller discovers that he's actually an NPC inside a brutal, open world video game.", "Standard", "21:00", "10"},
                    {"Central Intelligence", "After he reconnects with an awkward pal from high school through Facebook, a mild-mannered accountant is lured into the world of international espionage.", "Standard", "17:00", "10"},
                    {"Guardians of the Galaxy Vol. 3", "Still reeling from the loss of Gamora, Peter Quill rallies his team to defend the universe and one of their own - a mission that could mean the end of the Guardians if not successful.", "Imax-3D", "22:00", "15"},
                    {"Minions: The Rise of Gru", "The untold story of one twelve-year-old's dream to become the world's greatest super villain.", "Imax-3D", "18:00", "15"},
            };
    private String[][] romance_movies =
            {
                    {"La La Land", "While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.", "Standard", "20:00", "10"},
                    {"Eternal Sunshine of the Spotless Mind", "When their relationship turns sour, a couple undergoes a medical procedure to have each other erased from their memories for ever.", "Standard", "21:00", "10"},
                    {"The Notebook", "A poor yet passionate young man falls in love with a rich young woman, giving her a sense of freedom. However, social differences soon get in the way.", "Imax-3D", "20:00", "15"},
                    {"500 Days of Summer", "After being dumped by the girl he believes to be his soulmate, hopeless romantic Tom Hansen reflects on their relationship to try and figure out where things went wrong and how he can win her back.", "Imax-3D", "19:00", "15"},
                    {"About Time", "At the age of 21, Tim discovers he can travel in time and change what happens and has happened in his own life. His decision to make his world a better place by getting a girlfriend turns out not to be as easy as you might think.", "Imax-3D", "18:00", "15"},
            };
    private String[][] drama_movies =
            {
                    {"Good Will Hunting", "Will Hunting, a janitor at M.I.T., has a gift for mathematics, but needs help from a psychologist to find direction in his life.", "Standard", "20:00", "10"},
                    {"The Shawshank Redemption", "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.", "Standard", "21:00", "10"},
                    {"Fight Club", "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.", "Imax-3D", "20:00", "15"},
                    {"The Dark Knight", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "Imax-3D", "19:00", "15"},
                    {"Pulp Fiction", "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.", "Imax-3D", "18:00", "15"},
            };

    private int[] action_images = {
            R.drawable.action1,
            R.drawable.fast,
            R.drawable.top,
            R.drawable.spiderman,
            R.drawable.batman,
    };

    private int[] comedy_images = {
            R.drawable.jumanji,
            R.drawable.freeguy,
            R.drawable.central,
            R.drawable.guardians,
            R.drawable.minions,
    };

    private int[] romance_images = {
            R.drawable.land,
            R.drawable.eternal,
            R.drawable.notebook,
            R.drawable.days,
            R.drawable.abouttime,
    };

    private int[] drama_images = {
            R.drawable.good,
            R.drawable.shawshank,
            R.drawable.fight,
            R.drawable.dark,
            R.drawable.pulp,
    };

    String[][] movies_list = {};
    int[] images = {};
    TextView tv;
    Button btn;
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        tv = (TextView) findViewById(R.id.textViewAMTitle);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Action Movies") == 0){
            movies_list = action_movies;
            images = action_images;
        }
        else if(title.compareTo("Comedy Movies") == 0){
            movies_list = comedy_movies;
            images = comedy_images;
        }
        else if(title.compareTo("Romance Movies") == 0){
            movies_list = romance_movies;
            images = romance_images;
        }
        else{
            movies_list = drama_movies;
            images = drama_images;
        }



        btn = (Button) findViewById(R.id.BackAMButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoviesList.this,HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        list = new ArrayList();
        for (int i=0; i<movies_list.length; i++){
            item = new HashMap<String,String>();
            item.put("line1",movies_list[i][0]);
            item.put("line2",movies_list[i][1]);
            item.put("line3",movies_list[i][2]);
            item.put("line4",movies_list[i][3]);
            item.put("line5","Ticket: " + movies_list[i][4] + "$");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );

        ListView lst = findViewById(R.id.ListViewAM);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(MoviesList.this,MoviesDetailsActivity.class);
                it.putExtra("text1",movies_list[i][0]);
                it.putExtra("text2",movies_list[i][4]);
                it.putExtra("text4",movies_list[i][3]);
                it.putExtra("text5",movies_list[i][2]);
                it.putExtra("text3",images[i]);
                startActivity(it);
            }
        });
    }
}