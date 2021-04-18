package com.example.homework2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.homework2.VolleyConfigSingleton;
import com.example.homework2.interfaces.OnAlbumItemClick;
import com.example.homework2.interfaces.OnButtonClick;
import com.example.homework2.interfaces.OnUserItemClick;
import com.example.homework2.models.Entertainment;
import com.example.homework2.models.EntertainmentType;
import com.example.homework2.models.Photo;
import com.example.homework2.models.Post;
import com.example.homework2.models.User;
import com.example.homework2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Entertainment> entertainmentList;
    OnUserItemClick onUserItemClick;
    OnButtonClick onButtonClick;
    OnAlbumItemClick onAlbumItemClick;

    public MyAdapter(ArrayList<Entertainment> entertainmentList, OnUserItemClick onMovieItemClick, OnButtonClick onButtonClick) {
        this.entertainmentList = entertainmentList;
        this.onUserItemClick =onMovieItemClick;
        this.onButtonClick=onButtonClick;
    }

    public MyAdapter(ArrayList<Entertainment> entertainmentList, OnAlbumItemClick onAlbumItemClick)
    {
        this.entertainmentList = entertainmentList;
        this.onAlbumItemClick=onAlbumItemClick;
    }

    public MyAdapter(ArrayList<Entertainment> entertainmentList)
    {
        this.entertainmentList = entertainmentList;
    }

    @Override
    public int getItemViewType(int position) {
        if (entertainmentList.get(position).getType() == EntertainmentType.USER) {
            return 0;
        } else if (entertainmentList.get(position).getType() == EntertainmentType.POST) {
            return 1;
        } else if (entertainmentList.get(position).getType() == EntertainmentType.PHOTO) {
            return 2;
        }
        return super.getItemViewType(position);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 0) {
            View view = inflater.inflate(R.layout.item_user, parent, false);
            UserViewHolder userViewHolder = new UserViewHolder(view);
            return userViewHolder;
        } else if(viewType == 1) {
            View view = inflater.inflate(R.layout.item_post, parent, false);
            PostHolder postHolder = new PostHolder(view);
            return postHolder;
        }else if(viewType == 2) {
            View view = inflater.inflate(R.layout.item_photo, parent, false);
            PhotoHolder photoHolder = new PhotoHolder(view);
            return photoHolder;
        }

        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof UserViewHolder) {
            User user = (User)entertainmentList.get(position);
            ((UserViewHolder) holder).bind(user);
        } else if(holder instanceof PostHolder) {
            Post post = (Post) entertainmentList.get(position);
            ((PostHolder) holder).bind(post);
        }else if(holder instanceof PhotoHolder) {
            Photo photo = (Photo) entertainmentList.get(position);
            ((PhotoHolder) holder).bind(photo);
        }

    }

    @Override
    public int getItemCount() {
        return this.entertainmentList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView name;
        private TextView username;
        private TextView street;
        private TextView suite;
        private TextView city;
        private TextView zipcode;
        private TextView lat;
        private TextView lng;
        private TextView phone;
        private TextView website;
        private TextView companyName;
        private TextView catchPhrase;
        private TextView bs;
        private FloatingActionButton button;
        private View view;

       UserViewHolder(View view) {
            super(view);
           id = view.findViewById(R.id.id);
           name = view.findViewById(R.id.name);
           username=view.findViewById(R.id.username);
           street=view.findViewById(R.id.street);
          suite=view.findViewById(R.id.suite);
           city=view.findViewById(R.id.city);
           zipcode=view.findViewById(R.id.zipcode);
           lat=view.findViewById(R.id.lat);
           lng=view.findViewById(R.id.lng);
          phone=view.findViewById(R.id.phone);
          website=view.findViewById(R.id.website);
           companyName=view.findViewById(R.id.companyName);
           catchPhrase=view.findViewById(R.id.catchPhrase);
           bs=view.findViewById(R.id.bs);
           button=view.findViewById(R.id.button);
           this.view = view;
       }



        void bind(User user) {
            id.setText(user.getId());
            name.setText(user.getName());
            username.setText(user.getUsername());
            street.setText(user.getStreet());
            suite.setText(user.getSuite());
            city.setText(user.getCity());
            zipcode.setText(user.getZipcode());
            lat.setText(user.getLat());
            lng.setText(user.getLng());
            phone.setText(user.getPhone());
            website.setText(user.getWebsite());
            companyName.setText(user.getCompanyName());
            catchPhrase.setText(user.getCatchPhrase());
            bs.setText(user.getBs());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onButtonClick!=null){
                        onButtonClick.onButtonClick(user);
                    }
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onUserItemClick != null) {
                        onUserItemClick.onClick(user);
                    }
                }

            });



        }

    }


    class PostHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView userId;
        private TextView title;
        private TextView body;
        private View view;

        PostHolder(View view) {
            super(view);
            id = view.findViewById(R.id.id_post);
            userId = view.findViewById(R.id.userId);
            title=view.findViewById(R.id.title);
            body=view.findViewById(R.id.body);
            this.view=view;
        }

        void bind(Post post) {
            id.setText(post.getId());
           userId.setText(post.getUserId());
            title.setText(post.getTitle());
            body.setText(post.getBody());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onAlbumItemClick!= null){
                        onAlbumItemClick.onClick(post);
                    }
                }

            });
        }

    }


    class PhotoHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView albumId;
        private TextView title;
        private ImageView image;

        PhotoHolder(View view) {
            super(view);
            id = view.findViewById(R.id.id_photo);
            albumId = view.findViewById(R.id.albumId);
            title=view.findViewById(R.id.title_photo);
            image=view.findViewById(R.id.imageView);
        }

        void bind(Photo photo) {
            id.setText(photo.getId());
            albumId.setText(photo.getAlbumId());
            title.setText(photo.getTitle());


            String imageViewUrl=photo.thumbnailUrl.concat(".jpg");
            ImageLoader imageLoader = VolleyConfigSingleton.getInstance(image.getContext().getApplicationContext()).getImageLoader();
            imageLoader.get(imageViewUrl, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    image.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }

    }
}
