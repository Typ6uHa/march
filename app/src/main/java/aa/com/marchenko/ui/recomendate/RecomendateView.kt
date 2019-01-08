package aa.com.marchenko.ui.recomendate

import aa.com.marchenko.data.storage.model.Course
import aa.com.marchenko.ui.base.BaseView

interface RecomendateView : BaseView {
    fun submitList(list: List<Course>)
}