package com.example.newproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder> () {
    private var tasks: List<Task> = ArrayList()
    private var listener: OnItemClickListenerTask? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.sve_godine_item, parent, false)
        return TaskHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val trenutniDrugiKolegiji: Task = tasks[position]
        holder.textViewTitle.text = trenutniDrugiKolegiji.naslov
        holder.textViewNositelj.text = trenutniDrugiKolegiji.datum

    }

    override fun getItemCount(): Int {
        return  tasks.size
    }

    fun setDrugiKolegijiList(drugiKolegiji: List<Task?>?) {
        this.tasks = drugiKolegiji as List<Task>
        notifyDataSetChanged()
    }

    fun getDrugiKolegijiAt(position: Int): Task {
        return tasks[position]
    }

    inner class TaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView
        val textViewNositelj: TextView
        val textViewECTS: TextView

        init {
            textViewTitle = itemView.findViewById(R.id.text_view_naziv)
            textViewNositelj = itemView.findViewById(R.id.text_view_nositelj)
            textViewECTS = itemView.findViewById(R.id.text_view_ECTS)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) //NO_POSITION je uvijek -1
                    listener!!.onItemClickTask(tasks[position])
            }
        }
    }

    interface OnItemClickListenerTask {
        fun onItemClickTask(task: Task?)
    }

    fun setOnItemClickListener(listener: OnItemClickListenerTask?){
        this.listener = listener
    }
}