package com.example.sourpower.ui.recipe_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sourpower.R;

import java.util.LinkedList;

public class InstructionListAdapter extends RecyclerView.Adapter<InstructionListAdapter.InstructionViewHolder> {
    private LayoutInflater mInflater;
    private final LinkedList<Instruction> mInstructionList;
    private Context mContext;

    public InstructionListAdapter(Context context, LinkedList<Instruction> instructions) {
        mInflater = LayoutInflater.from(context);
        this.mInstructionList = instructions;
        this.mContext = context;
    }
    class InstructionViewHolder extends RecyclerView.ViewHolder {
        public final ImageView instructionImageView;
        public final TextView instructionTextView;
        final InstructionListAdapter mAdapter;

        public InstructionViewHolder(View itemView, InstructionListAdapter adapter) {
            super(itemView);
            instructionImageView = itemView.findViewById(R.id.instruction_image);
            instructionTextView = itemView.findViewById(R.id.instruction_text);
            this.mAdapter = adapter;
        }
    }

    @NonNull
    @Override
    public InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.instructions_item, parent, false);
        return new InstructionViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionViewHolder holder, int position) {
        Instruction mCurrent = mInstructionList.get(position);
        holder.instructionTextView.setText(mCurrent.getText());
        Glide.with(mContext)
                .load(mCurrent.getImageURL())
                .into(holder.instructionImageView);
    }

    @Override
    public int getItemCount() {
        return mInstructionList.size();
    }
}
