package showcase.project.dsc.eathere.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import showcase.project.dsc.eathere.R;
import showcase.project.dsc.eathere.model.Cart;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context context;
    ArrayList<Cart> cartList;


    public OrderAdapter(Context context, ArrayList<Cart> cartList){
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_order_card, viewGroup, false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {
        orderViewHolder.tvName.setText(cartList.get(i).getMenuName());
        orderViewHolder.tvPrice.setText(String.valueOf("Price : " + cartList.get(i).getMenuPrice()));
        int quantity = 1;
        //quantity = Integer.parseInt(orderViewHolder.etQuantity.getText().toString());
        orderViewHolder.tvSubtotal.setText("Subtotal : IDR" + quantity * cartList.get(i).getMenuPrice());
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void setData(ArrayList<Cart> cartList){
        this.cartList = cartList;
    }

    public int getTotalPrice(){
        int total = 0;
        for (int i = 0; i < cartList.size(); i++){
            total += cartList.get(i).getMenuPrice();
        }
        return total;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvPrice,tvSubtotal;
        private EditText etQuantity;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_order_name);
            tvPrice = itemView.findViewById(R.id.tv_order_price);
            tvSubtotal = itemView.findViewById(R.id.tv_order_subtotal);
            etQuantity = itemView.findViewById(R.id.et_quantity);
        }
    }
}