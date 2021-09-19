package com.example.constraintlayoutdemo.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.constraintlayoutdemo.R
import com.example.constraintlayoutdemo.models.Person
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.person_grid_item.view.*

class ResizableRecyclerViewAdapter(
        context: Context,
        personList : List<Person>
) : RecyclerView.Adapter<ResizableRecyclerViewAdapter.ViewHolder>() {

    private val TAG = ResizableRecyclerViewAdapter::class.simpleName

    private var context : Context
    private var personList : List<Person>

    init {
        this.context = context;
        this.personList = personList;
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder()");
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.person_grid_item, parent, false)
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder() : position = " + position);
        if (position < 0) return

        val person = personList[position];
        if (null == person) {
            Log.d(TAG, "person is null")
            return;
        }
        Glide.with(context)
                .load(R.drawable.sample_image_1)
                .centerCrop()
                .into(holder.personImageView);
        holder.personNameTextView.text = person.name
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    inner class ViewHolder(rootView : View) : RecyclerView.ViewHolder(rootView) {
        var personImageView : CircleImageView = rootView.personImageView;
        var personNameTextView : TextView = rootView.personNameTextView;

        init {
            rootView.setOnClickListener {
                val position = adapterPosition
                if (position < 0) return@setOnClickListener;
                Log.d(TAG, "Item: $position")
            }
        }
    }
}