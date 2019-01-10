package showcase.project.dsc.eathere.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import showcase.project.dsc.eathere.MenuList;
import showcase.project.dsc.eathere.R;
import showcase.project.dsc.eathere.model.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private Context context;
    ArrayList<Restaurant> restaurantList;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurantList){
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_restaurant_card, viewGroup, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantViewHolder myViewHolder, final int i) {
        myViewHolder.tvName.setText(restaurantList.get(i).getRestaurantName());
        myViewHolder.tvDescription.setText(restaurantList.get(i).getRestaurantDescription());
        myViewHolder.tvOpenCloseHour.setText("Opens at(" + restaurantList.get(i).getOpenHour() + ".00-" +restaurantList.get(i).getCloseHour() + ".00)");
        myViewHolder.btnEatHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MenuList.class);
                int restaurantID = restaurantList.get(i).getRestaurantID();
                intent.putExtra("restaurantID", restaurantID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public void setData(ArrayList<Restaurant> restaurantList){
        this.restaurantList = restaurantList;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvDescription, tvOpenCloseHour;
        private Button btnEatHere;

        public RestaurantViewHolder(final View view){
            super(view);

            btnEatHere = view.findViewById(R.id.btn_eat_here);
            tvName = view.findViewById(R.id.tv_restaurant_name);
            tvDescription = view.findViewById(R.id.tv_restaurant_description);
            tvOpenCloseHour = view.findViewById(R.id.tv_open_close_hour);

        }
    }
}
