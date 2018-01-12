package com.example.admin.androidplayground.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.androidplayground.R;
import com.example.admin.androidplayground.content.MainActivity;
import com.example.admin.androidplayground.content.SpannableStringFragment;
import com.example.admin.androidplayground.model.Menu;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private ArrayList<Menu> menuArrayList;
    private Context context;
    private OnItemClickListener itemClickListener;

    public MenuAdapter(Context context, ArrayList<Menu> DataSet,OnItemClickListener clickListener){
        menuArrayList = DataSet;
        this.context = context;
        itemClickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main_item,parent,false);
        return new ViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.label.setText(menuArrayList.get(position).getItemName());
        holder.mLike_btn.setTag(R.drawable.ic_like);
    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    public Menu getItem(int adapterPosition){
        return menuArrayList.get(adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public TextView label;
        public ImageView mLike_btn,mImage;
        OnItemClickListener listener;


        public ViewHolder(View view, OnItemClickListener clickListener){
            super(view);
            label = (TextView) view.findViewById(R.id.tv);
            mLike_btn = (ImageView)view.findViewById(R.id.like_btn);
            mImage = (ImageView)view.findViewById(R.id.ic_image);

            listener = clickListener;
            mLike_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (int)mLike_btn.getTag();
                    if(id == R.drawable.ic_like) {
                        mLike_btn.setTag(R.drawable.ic_liked);
                        mLike_btn.setImageResource(R.drawable.ic_liked);
                    }else{
                        mLike_btn.setTag(R.drawable.ic_like);
                        mLike_btn.setImageResource(R.drawable.ic_like);
                    }
                }
            });
            label.setOnClickListener(this);
            mImage.setOnClickListener(this);
    }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv:
                    openFragment();
                case R.id.ic_image:
                   // openFragment();
            }
        }
        public void openFragment(){
            Menu menuItem = getItem(getAdapterPosition());
            Fragment fragment2 = new SpannableStringFragment();
            FragmentManager manager = ((MainActivity) context).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.slide_out);
            fragmentTransaction.add(R.id.container, fragment2);
            fragmentTransaction.commit();
            Bundle bundle = new Bundle();
            bundle.putString("title",menuItem.getItemName());
            bundle.putString("content",menuItem.getItemContent());
            fragment2.setArguments(bundle);
        }

    }

    public interface  OnItemClickListener{
      void onPostClick(long id);


    }
}


