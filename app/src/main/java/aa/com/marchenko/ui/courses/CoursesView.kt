package aa.com.marchenko.ui.courses

import aa.com.marchenko.data.storage.model.Course
import aa.com.marchenko.ui.base.BaseView

interface CoursesView : BaseView {

    fun submitList(list: List<Course>)
}