package showcase.project.dsc.eathere.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import showcase.project.dsc.eathere.R;
import showcase.project.dsc.eathere.model.Cart;
import showcase.project.dsc.eathere.model.Menu;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context context;
    ArrayList<Menu> menuList;
    ArrayList<Cart> cartList;

    public MenuAdapter(Context context, ArrayList<Menu> menuList){
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_menu_card, viewGroup, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuViewHolder menuViewHolder, final int i) {
        menuViewHolder.tvName.setText(menuList.get(i).getMenuName());
        menuViewHolder.tvDescription.setText(menuList.get(i).getMenuDescription());
        menuViewHolder.tvPrice.setText("IDR" + menuList.get(i).getMenuPrice());

        menuViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart();
                cart.setMenuID(menuList.get(i).getMenuID());
                cart.setMenuName(menuList.get(i).getMenuName());
                cart.setMenuPrice(menuList.get(i).getMenuPrice());
                //cart.setMenuQuantity(cart.getMenuID() + 1);
                cartList.add(cart);
                Toast.makeText(context, "Success added to order list", Toast.LENGTH_LONG).show();
            }
        });

        menuViewHolder.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart();
                cart.setMenuID(menuList.get(i).getMenuID());
                cart.setMenuName(menuList.get(i).getMenuName());
                cart.setMenuPrice(menuList.get(i).getMenuPrice());
                //cart.setMenuQuantity(cart.getMenuID() + 1);
                cartList.add(cart);
                Toast.makeText(context, "Success added to order list", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public ArrayList<Cart> getData(){
        return this.cartList;
    }

    public void clearCart(){
        cartList.clear();
    }

    public void setData(ArrayList<Menu> menuList){
        this.menuList = menuList;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvDescription, tvPrice;
        private Button btnOrder;
        private CardView cardView;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_menu_name);
            tvDescription = itemView.findViewById(R.id.tv_menu_description);
            tvPrice = itemView.findViewById(R.id.tv_menu_price);
            btnOrder = itemView.findViewById(R.id.btn_order);
            cardView = itemView.findViewById(R.id.parent_layout_menu_card);
            cartList = new ArrayList<>();
        }
    }
}
