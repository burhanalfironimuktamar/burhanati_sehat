package mahirsoft.diet.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mahirsoft.diet.R;
import mahirsoft.diet.data.Food;

/**
 * Created by ati on 2/20/2016.
 */
public class SerapanAdapter extends RecyclerView.Adapter<SerapanAdapter.ViewHolder> {

    private Context context;
    private Cursor cursor;

    public SerapanAdapter(Context context) {
        this.context = context;
    }

    public void swapCursor(Cursor cursor) {
        if (cursor == this.cursor) {
            return;
        }
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data_serapan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.food.setText(cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_NAME)));
        holder.kalori.setText(cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_KALORI)));
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        } else {
            return cursor.getCount();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView food;
        TextView kalori;

        ViewHolder(View view) {
            super(view);
            food = (TextView) view.findViewById(R.id.food);
            kalori = (TextView) view.findViewById(R.id.kalori);
        }
    }
}
