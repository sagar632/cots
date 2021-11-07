package viewholder;

import android.content.ReceiverCallNotAllowedException;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.cots.R;

public class category extends RecyclerView.ViewHolder
{
    Button heading;

    public category(@NonNull View itemView) {
        super(itemView);

        heading=itemView.findViewById(R.id.notes);
    }
}
