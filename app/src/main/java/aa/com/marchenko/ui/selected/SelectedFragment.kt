package aa.com.marchenko.ui.selected

import aa.com.marchenko.R
import aa.com.marchenko.data.storage.model.Course
import aa.com.marchenko.ui.CourseAdapter
import aa.com.marchenko.ui.base.BaseFragment
import aa.com.marchenko.ui.base.BasePresenter
import aa.com.marchenko.ui.recomendate.RecomendatePresenter
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_courses.*

class SelectedFragment : BaseFragment(), SelectedView {

    private val adapter = CourseAdapter({ selected, id ->
        presenter.selectCourse(selected, id)
    }, { courseId, courseElo ->
        presenter.skipCourse(courseId, courseElo)
    }, { courseId, courseElo ->
        presenter.finishCourse(courseId, courseElo)
    })

    @InjectPresenter
    lateinit var presenter: SelectedPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
    }

    override fun submitList(list: List<Course>) {
        adapter.submitList(list)
    }

    override val layoutId: Int?
        get() = R.layout.activity_selected

    override fun getPresenter(): BasePresenter<*>? = null
}