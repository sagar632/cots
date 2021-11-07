package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cots.R;

import java.util.List;

import model.read_article;

public class read_articles extends RecyclerView.Adapter<read_articles.viewholder> {

    private List<model.read_article> articleList;
private OnItemCLickListener listener;
    public read_articles(List<read_article> articleList,OnItemCLickListener listener) {
        this.articleList = articleList;
        this.listener=listener;
    }

    public interface OnItemCLickListener{
        void OnItemClick(model.read_article read_article);
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.res_prooject_layout, viewGroup, false);
        return new viewholder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull read_articles.viewholder viewholder, int i) {
        String heading = articleList.get(i).getTitle() ;


        viewholder.setdata(heading);
        viewholder.bind(articleList.get(i),listener);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private Button articleheading;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            articleheading = itemView.findViewById(R.id.heading);


        }

        private void setdata(String heading_title) {
            articleheading.setText(heading_title);

        }


        public void bind(final read_article read_article, final OnItemCLickListener listener) {
            articleheading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(read_article);
                }
            });
        }
    }
}
