package aa.com.marchenko.ui

import aa.com.marchenko.R
import aa.com.marchenko.data.storage.model.Course
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class CourseAdapter(
        val onSelectCourseClick: (selected: Int, courseId: Long) -> Unit,
        val onSkipCourseClick: (courseId: Long, courseElo: Double) -> Unit,
        val onFinishCourseClick: (courseId: Long, courseElo: Double) -> Unit) : ListAdapter<Course, CourseAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_course, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(getItem(p1))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val description: TextView = itemView.findViewById(R.id.tvDescription)
        val addButton: Button = itemView.findViewById(R.id.btnSelectCourse)
        val finishButton: Button = itemView.findViewById(R.id.btnFinishCourse)
        val skipButton: Button = itemView.findViewById(R.id.btnSkipCourse)
        fun bind(course: Course) {
            name.text = course.name
            description.text = course.description
            if (course.selected == 0) {
                addButton.visibility = VISIBLE
                addButton.setOnClickListener {
                    onSelectCourseClick(if (course.selected == 0) {
                        1
                    } else {
                        0
                    }, course.id)
                }
                finishButton.visibility = GONE
                skipButton.visibility = GONE
            } else {
                addButton.visibility = GONE
                finishButton.visibility = VISIBLE
                finishButton.setOnClickListener {
                    onFinishCourseClick(course.id, course.elo)
                }
                skipButton.visibility = VISIBLE
                skipButton.setOnClickListener {
                    onSkipCourseClick(course.id, course.elo)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Course>() {
            override fun areItemsTheSame(newItem: Course, oldItem: Course): Boolean {
                return newItem == oldItem
            }

            override fun areContentsTheSame(newItem: Course, oldItem: Course): Boolean {
                return newItem == oldItem
            }

        }
    }
}