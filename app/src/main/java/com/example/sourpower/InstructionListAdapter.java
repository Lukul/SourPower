package com.example.sourpower;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.LinkedList;

public class InstructionListAdapter extends RecyclerView.Adapter<InstructionListAdapter.InstructionViewHolder> {
    private final LinkedList<Instruction> mInstructionList;
    private LayoutInflater mInflater;
    private Context mContext;

    public InstructionListAdapter(Context context, LinkedList<Instruction> instructionList) {
        mInflater = LayoutInflater.from(context);
        this.mInstructionList = instructionList;
        this.mContext = context;
    }


    class InstructionViewHolder extends RecyclerView.ViewHolder {
        public final ImageView instructionImageView;
        public final TextView instructionTextView;
        final InstructionListAdapter mAdapter;

        public InstructionViewHolder(View itemView, InstructionListAdapter adapter) {
            super(itemView);
            instructionImageView = itemView.findViewById(R.id.instruction_image);
            instructionTextView = itemView.findViewById(R.id.instruction_name);
            this.mAdapter = adapter;
        }
    }
    @NonNull
    @Override
    public InstructionListAdapter.InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.instructions_item, parent, false);
        return new InstructionViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionListAdapter.InstructionViewHolder holder, int position) {
        Instruction mCurrent = mInstructionList.get(position);
        holder.instructionTextView.setText(mCurrent.getInstructions());
        Glide.with(mContext)
                .load(mCurrent.getImage())
                .circleCrop()
                .into(holder.instructionImageView);

    }

    @Override
    public int getItemCount() {
        return mInstructionList.size();
    }
}
