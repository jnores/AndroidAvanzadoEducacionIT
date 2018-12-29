package com.example.yoshknight.androidavanzado_educacionit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yoshknight.androidavanzado_educacionit.modelos.Post;

import java.util.List;

/**
 *
 * fixme
 * android.content.res.Resources$NotFoundException: String resource ID #0x29
 *         at android.content.res.Resources.getText(Resources.java:348)
 *         at android.widget.TextView.setText(TextView.java:5831)
 *         at com.example.yoshknight.androidavanzado_educacionit.PostsRecyclerAdapter.onBindViewHolder(PostsRecyclerAdapter.java:34)
 *         at com.example.yoshknight.androidavanzado_educacionit.PostsRecyclerAdapter.onBindViewHolder(PostsRecyclerAdapter.java:14)
 */
public class PostsRecyclerAdapter  extends RecyclerView.Adapter<PostsRecyclerAdapter.PostHolder>{

    private List<Post> posts;
    private PostClickListener listener;

    public PostsRecyclerAdapter(List<Post> posts, PostClickListener listener) {
        this.posts = posts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.post_item,viewGroup,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder postHolder, int position) {
        postHolder.tvId.setText("" + posts.get(position).getId());
        postHolder.tvTitle.setText(posts.get(position).getTitle());
        postHolder.tvBody.setText(posts.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{

        TextView tvId,tvTitle, tvBody;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.postId);
            assert tvId != null;
            tvTitle = itemView.findViewById(R.id.title);
            assert tvTitle != null;
            tvBody = itemView.findViewById(R.id.body);
            assert tvBody != null;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(posts.get(getAdapterPosition()).getTitle());
                }
            });
        }
    }
}
