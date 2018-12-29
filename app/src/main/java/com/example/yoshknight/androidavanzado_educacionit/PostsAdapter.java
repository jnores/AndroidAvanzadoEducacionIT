package com.example.yoshknight.androidavanzado_educacionit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yoshknight.androidavanzado_educacionit.modelos.Post;
import com.example.yoshknight.androidavanzado_educacionit.modelos.Usuario;

import java.util.List;

class PostsAdapter extends BaseAdapter {
        List<Post> posts;

        public PostsAdapter(List<Post> posts) {
            this.posts = posts;
        }

        @Override
        public int getCount() {
            return posts.size();
        }

        @Override
        public Object getItem(int position) {
            return posts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return posts.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v;
            if (convertView == null) {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, null);
            } else {
                v = convertView;
            }

            TextView id= v.findViewById(R.id.postId);
            id.setText(""+posts.get(position).getId());

            TextView title = v.findViewById(R.id.title);
            title.setText(posts.get(position).getTitle());

            TextView body = v.findViewById(R.id.body);
            body.setText(posts.get(position).getBody());

            return v;
        }
    }
