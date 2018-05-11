package com.alef.jump;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.utils.Utils;

import java.text.DateFormat;
import java.util.ArrayList;

import People.ChatLine;

public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private Context mContext;
    private ArrayList<ChatLine> mMessageList;


    public MessageListAdapter(Context mContext, ArrayList<ChatLine> mMessageList) {
        this.mContext = mContext;
        this.mMessageList = mMessageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_menssage_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatLine message = (ChatLine) mMessageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).asignarDatos(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).asignarDatos(message);
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }


    @Override
    public int getItemViewType(int position) {
        ChatLine message = (ChatLine) mMessageList.get(position);

        if (message.getId() == Globals.getInstance().getId()) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView tvMessage, tvTime, tvName;
        ImageView ivProfile;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            tvMessage = (TextView) itemView.findViewById(R.id.tv_Mensaje);
            tvTime = (TextView) itemView.findViewById(R.id.tv_Time);
            tvName = (TextView) itemView.findViewById(R.id.tv_Name);
            //ivProfile = (ImageView) itemView.findViewById(R.id.iv_Profile);
        }

        void asignarDatos(ChatLine message) {
            tvMessage.setText(message.getLineText());

            // Format the stored timestamp into a readable String using method.
            android.text.format.DateFormat df = new android.text.format.DateFormat();
            tvTime.setText(df.format("hh:mm:ss",message.getDate()));
            tvName.setText(message.getName());

            // Insert the profile image from the URL into the ImageView.
            /*ivProfile.setImageDrawable(R.drawable.ic_action_user);
            Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);*/
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView tvMessage, tvTime;
        ImageView ivProfile;

        SentMessageHolder(View itemView) {
            super(itemView);
            tvMessage = (TextView) itemView.findViewById(R.id.tv_Mensaje);
            tvTime = (TextView) itemView.findViewById(R.id.tv_Time);
        }

        void asignarDatos(ChatLine message) {
            tvMessage.setText(message.getLineText());

            // Format the stored timestamp into a readable String using method.
            android.text.format.DateFormat df = new android.text.format.DateFormat();
            tvTime.setText(df.format("hh:mm:ss",message.getDate()));

        }
    }

}