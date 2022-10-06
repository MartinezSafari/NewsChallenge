package org.thereachtrust.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newschallenge.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG="RecyclerViewAdapter";

    private Context context;

    private ArrayList<NewsItem> news= new ArrayList<>();

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public RecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_item, viewGroup,false);
        ViewHolder holder= new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        Log.d(TAG,"onBiendViewHolder: called");

        viewHolder.txtTitle.setText(news.get(i).getTitle());
        viewHolder.txtDesc.setText(news.get(i).getDesc());
        viewHolder.txtDate.setText(news.get(i).getDate());

        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to the next news
                Intent intent=new Intent(context, WebViewActivity.class);
                intent.putExtra("url", news.get(i).getLink());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return news.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtDate, txtDesc, txtTitle;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDate= (TextView) itemView.findViewById(R.id.txtDate);
            txtDesc= (TextView) itemView.findViewById(R.id.txtDesc);
            txtTitle= (TextView) itemView.findViewById(R.id.txtTitle);

            parent= (CardView) itemView.findViewById(R.id.parent);
        }
    }

    public void setNews(ArrayList<NewsItem> news) {
        this.news = news;
        notifyDataSetChanged();
    }
}
